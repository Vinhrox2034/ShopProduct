package com.poly.servlet;

import com.poly.dao.FavoriteDAO;
import com.poly.dao.ProductDAO;
import com.poly.entity.Products;
import com.poly.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/favorite/like")
public class FavoriteServlet extends HttpServlet {

    FavoriteDAO favoriteDAO = new FavoriteDAO();
    ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        Users user = (Users) session.getAttribute("user");

        // Login lock-on
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String productId = req.getParameter("id");

        // avoid get the same like
        if (favoriteDAO.isLiked(user.getUserId(), productId)) {
            resp.sendRedirect(req.getContextPath() + "/product/detail?id=" + productId);
            return;
        }

        Products product = productDAO.findById(productId);
        favoriteDAO.like(user, product);

        resp.sendRedirect(req.getContextPath() + "/product/detail?id=" + productId);
    }
}
