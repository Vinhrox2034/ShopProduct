package com.poly.servlet;

import com.poly.dao.ProductDAO;
import com.poly.entity.Products;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet({"/products", "/product/detail"})
public class ProductServlet extends HttpServlet {

    ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String uri = req.getRequestURI();

        if (uri.contains("detail")) {
            this.detail(req, resp);
        } else {
            this.list(req, resp);
        }
    }

    // ===== Danh sách sản phẩm =====
    protected void list(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Products> list = productDAO.findActiveProducts();
        req.setAttribute("products", list);
        req.getRequestDispatcher("/views/product/list.jsp").forward(req, resp);
    }

    // ===== Chi tiết sản phẩm =====
    protected void detail(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");

        if (id == null) {
            resp.sendRedirect(req.getContextPath() + "/products");
            return;
        }

        Products product = productDAO.findById(id);

        if (product == null) {
            resp.sendRedirect(req.getContextPath() + "/products");
            return;
        }

        // ===== TĂNG LƯỢT XEM =====
        productDAO.increaseView(id);

        Long totalLikes = productDAO.countLikes(id);

        req.setAttribute("product", product);
        req.setAttribute("likes", totalLikes);

        req.getRequestDispatcher("/views/product/detail.jsp")
                .forward(req, resp);
    }

}

