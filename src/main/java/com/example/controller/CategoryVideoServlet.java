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
import java.util.List;

@WebServlet("/admin/category/video")
@MultipartConfig(maxFileSize = 50_000_000) // 50MB
public class CategoryVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final VideoDAO vdao = new VideoDAO();
	private final CategoryDAO cdao = new CategoryDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String catParam = req.getParameter("catId");
		if (catParam == null || catParam.isEmpty()) {
			resp.sendRedirect(req.getContextPath() + "/admin/category");
			return;
		}
		int catId = Integer.parseInt(catParam);
		Category category = cdao.find(catId);

		String action = req.getParameter("action");
		String idParam = req.getParameter("id");

		if ("edit".equals(action)) {
			// Nếu có id => sửa; nếu không => thêm mới
			if (idParam != null && !idParam.isEmpty()) {
				int id = Integer.parseInt(idParam);
				req.setAttribute("video", vdao.find(id));
			} else {
				Video v = new Video();
				v.setCategory(category);
				req.setAttribute("video", v);
			}
			req.setAttribute("category", category);
			req.getRequestDispatcher("/WEB-INF/views/category-video-form.jsp").forward(req, resp);
			return;
		}

		if ("delete".equals(action)) {
			if (idParam != null && !idParam.isEmpty()) {
				vdao.delete(Integer.parseInt(idParam));
			}
			resp.sendRedirect(req.getContextPath() + "/admin/category/video?catId=" + catId);
			return;
		}

		// Mặc định: list video theo category
		List<Video> list = vdao.findByCategory(catId);
		req.setAttribute("category", category);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/views/category-video-list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {

	    // Nhận catId an toàn (ưu tiên categoryId, fallback catId)
	    String catIdParam = req.getParameter("categoryId");
	    if (catIdParam == null || catIdParam.isEmpty()) {
	        catIdParam = req.getParameter("catId");
	    }
	    if (catIdParam == null || catIdParam.isEmpty()) {
	        // Không có catId -> quay về Category
	        resp.sendRedirect(req.getContextPath() + "/admin/category");
	        return;
	    }
	    int catId = Integer.parseInt(catIdParam);
	    Category cat = cdao.find(catId);

	    String title = req.getParameter("title");

	    // Xử lý file (có thể bỏ trống khi sửa)
	    String newUrl = null;
	    Part filePart = req.getPart("file");
	    if (filePart != null && filePart.getSize() > 0 &&
	        filePart.getSubmittedFileName() != null && !filePart.getSubmittedFileName().isEmpty()) {

	        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
	        String uploadDir = getServletContext().getRealPath("/uploads");
	        Files.createDirectories(Paths.get(uploadDir));
	        Path filePath = Paths.get(uploadDir, fileName);
	        try (InputStream in = filePart.getInputStream()) {
	            Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
	        }
	        newUrl = "uploads/" + fileName;
	    }

	    String id = req.getParameter("id");
	    if (id == null || id.isEmpty() || "0".equals(id)) {
	        // Thêm mới: bắt buộc có file
	        if (newUrl == null) {
	            // Trả về form với báo lỗi
	            Video v = new Video();
	            v.setTitle(title);
	            v.setCategory(cat);
	            req.setAttribute("video", v);
	            req.setAttribute("category", cat);
	            req.setAttribute("error", "Vui lòng chọn file video để upload.");
	            req.getRequestDispatcher("/WEB-INF/views/category-video-form.jsp").forward(req, resp);
	            return;
	        }
	        Video v = new Video();
	        v.setTitle(title);
	        v.setCategory(cat);
	        v.setUrl(newUrl);
	        vdao.create(v);
	    } else {
	        // Cập nhật: giữ URL cũ nếu không upload file mới
	        int vid = Integer.parseInt(id);
	        Video v = vdao.find(vid);
	        v.setTitle(title);
	        v.setCategory(cat);
	        if (newUrl != null) v.setUrl(newUrl);
	        vdao.update(v);
	    }

	    resp.sendRedirect(req.getContextPath() + "/admin/category/video?catId=" + catId);
	}

}
