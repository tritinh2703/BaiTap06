package com.example.controller;

import com.example.dao.CategoryDAO;
import com.example.entity.Category;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/category")
public class CategoryAdminServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final CategoryDAO dao = new CategoryDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
	    String action = req.getParameter("action");

	    if ("edit".equals(action)) {
	        String idParam = req.getParameter("id");
	        if (idParam != null && !idParam.isEmpty()) {
	            // Edit existing category
	            int id = Integer.parseInt(idParam);
	            req.setAttribute("cat", dao.find(id));
	        }
	        // Nếu id null => thêm mới, cat vẫn null => form rỗng
	        req.getRequestDispatcher("/WEB-INF/views/category-form.jsp").forward(req, resp);
	        return;
	    }

	    if ("delete".equals(action)) {
	        String idParam = req.getParameter("id");
	        if (idParam != null && !idParam.isEmpty()) {
	            dao.delete(Integer.parseInt(idParam));
	        }
	        resp.sendRedirect("category");
	        return;
	    }

	    String kw = req.getParameter("kw");
	    List<Category> list = (kw != null && !kw.isEmpty())
	            ? dao.searchByName(kw)
	            : dao.findAll();
	    req.setAttribute("list", list);
	    req.getRequestDispatcher("/WEB-INF/views/category-list.jsp").forward(req, resp);
	}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Category c = new Category();
        c.setName(req.getParameter("name"));
        c.setDescription(req.getParameter("description"));

        String id = req.getParameter("id");
        if (id == null || id.isEmpty()) dao.create(c);
        else {
            c.setId(Integer.parseInt(id));
            dao.update(c);
        }
        resp.sendRedirect("category");
    }
}
