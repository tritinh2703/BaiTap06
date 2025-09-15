package com.example.controller;

import com.example.dao.VideoDAO;
import com.example.dao.CategoryDAO;
import com.example.entity.Video;
import com.example.entity.Category;

import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.nio.file.*;

@WebServlet("/admin/video")
@MultipartConfig(maxFileSize = 50_000_000)
public class VideoAdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final VideoDAO vdao = new VideoDAO();
    private final CategoryDAO cdao = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("edit".equals(action)) {
            String idParam = req.getParameter("id");
            String catIdParam = req.getParameter("catId");

            if (idParam != null && !idParam.isEmpty()) {
                int id = Integer.parseInt(idParam);
                req.setAttribute("video", vdao.find(id));
            } else if (catIdParam != null && !catIdParam.isEmpty()) {
                int catId = Integer.parseInt(catIdParam);
                Video newVideo = new Video();
                newVideo.setCategory(cdao.find(catId));
                req.setAttribute("video", newVideo);
            }

            req.setAttribute("categories", cdao.findAll());
            req.getRequestDispatcher("/WEB-INF/views/video-form.jsp").forward(req, resp);
            return;
        }

        if ("delete".equals(action)) {
            vdao.delete(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect("video");
            return;
        }

        String kw = req.getParameter("kw");
        var list = (kw != null && !kw.isEmpty())
                ? vdao.searchByTitle(kw)
                : vdao.findAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/WEB-INF/views/video-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Part filePart = req.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uploadDir = getServletContext().getRealPath("/uploads");
        Files.createDirectories(Paths.get(uploadDir));
        Path filePath = Paths.get(uploadDir, fileName);
        try (InputStream in = filePart.getInputStream()) {
            Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        Video v = new Video();
        v.setTitle(req.getParameter("title"));
        v.setUrl("uploads/" + fileName); // lưu đường dẫn file

        int catId = Integer.parseInt(req.getParameter("categoryId"));
        v.setCategory(cdao.find(catId));

        String id = req.getParameter("id");
        if (id == null || id.isEmpty()) {
            vdao.create(v);
            resp.sendRedirect(req.getContextPath() + "/admin/category");
            return; // QUAN TRỌNG để kết thúc sau redirect
        } else {
            v.setId(Integer.parseInt(id));
            vdao.update(v);
            resp.sendRedirect(req.getContextPath() + "/admin/video");
            return;
        }
    }
}
