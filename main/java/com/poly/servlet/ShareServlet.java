package com.poly.servlet;

import com.poly.dao.ProductDAO;
import com.poly.dao.ShareDAO;
import com.poly.entity.Products;
import com.poly.entity.Shares;
import com.poly.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;

@WebServlet("/share")
public class ShareServlet extends HttpServlet {

    ShareDAO shareDAO = new ShareDAO();
    ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        Users user = (Users) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String productId = req.getParameter("id");
        Products product = productDAO.findById(productId);

        req.setAttribute("product", product);
        req.getRequestDispatcher("/views/share/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        Users user = (Users) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String productId = req.getParameter("productId");
        String emails = req.getParameter("emails");

        Products product = productDAO.findById(productId);

        Shares s = new Shares();
        s.setUser(user);
        s.setProduct(product);
        s.setEmails(emails);
        s.setShareDate(new Date());

        shareDAO.create(s);

        resp.sendRedirect(req.getContextPath() + "/product/detail?id=" + productId);
    }
}
