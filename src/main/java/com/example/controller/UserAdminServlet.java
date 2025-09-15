package com.example.controller;

import com.example.dao.UserDAO;
import com.example.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/user")
public class UserAdminServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final UserDAO dao = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("u", dao.find(id));
            req.getRequestDispatcher("/WEB-INF/views/user-form.jsp").forward(req, resp);
        } else if ("delete".equals(action)) {
            dao.delete(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect("user");
        } else {
            String kw = req.getParameter("kw");
            List<User> list = (kw != null && !kw.isEmpty()) ?
                    dao.searchByUsername(kw) : dao.findAll();
            req.setAttribute("list", list);
            req.getRequestDispatcher("/WEB-INF/views/user-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User u = new User();
        u.setUsername(req.getParameter("username"));
        u.setPassword(req.getParameter("password"));
        u.setRole(req.getParameter("role"));

        String id = req.getParameter("id");
        if (id == null || id.isEmpty()) dao.create(u);
        else {
            u.setId(Integer.parseInt(id));
            dao.update(u);
        }
        resp.sendRedirect("user");
    }
}
