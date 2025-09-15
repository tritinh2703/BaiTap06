package com.example.controller;

import com.example.dao.UserDAO;
import com.example.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final UserDAO dao = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Hiển thị form đăng nhập
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        List<User> users = dao.searchByUsername(username);
        User found = users.isEmpty() ? null : users.get(0);

        if (found != null && found.getPassword().equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("currentUser", found);

            // Chuyển hướng tùy theo role
            if ("admin".equalsIgnoreCase(found.getRole())) {
                resp.sendRedirect(req.getContextPath() + "/admin/category");
            } else {
                resp.sendRedirect(req.getContextPath() + "/");
            }
        } else {
            req.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}