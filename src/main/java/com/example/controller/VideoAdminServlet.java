package com.example.controller;

import com.example.dao.VideoDAO;
import com.example.dao.CategoryDAO;
import com.example.entity.Video;
import com.example.entity.Category;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/video")
public class VideoAdminServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final VideoDAO vdao = new VideoDAO();
    private final CategoryDAO cdao = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("video", vdao.find(id));
            req.setAttribute("categories", cdao.findAll());
            req.getRequestDispatcher("/WEB-INF/views/video-form.jsp").forward(req, resp);
        } else if ("delete".equals(action)) {
            vdao.delete(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect("video");
        } else {
            String kw = req.getParameter("kw");
            List<Video> list = (kw != null && !kw.isEmpty()) ?
                    vdao.searchByTitle(kw) : vdao.findAll();
            req.setAttribute("list", list);
            req.getRequestDispatcher("/WEB-INF/views/video-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Video v = new Video();
        v.setTitle(req.getParameter("title"));
        v.setUrl(req.getParameter("url"));

        int catId = Integer.parseInt(req.getParameter("categoryId"));
        Category cat = cdao.find(catId);
        v.setCategory(cat);

        String id = req.getParameter("id");
        if (id == null || id.isEmpty()) vdao.create(v);
        else {
            v.setId(Integer.parseInt(id));
            vdao.update(v);
        }
        resp.sendRedirect("video");
    }
}
