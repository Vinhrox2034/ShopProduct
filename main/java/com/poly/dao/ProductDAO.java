package com.poly.dao;

import com.poly.entity.Products;

import javax.persistence.TypedQuery;
import java.util.List;

public class ProductDAO extends AbstractDAO<Products, String> {

    public ProductDAO() {
        super(Products.class);
    }

    // ===== Lấy sản phẩm đang active =====
    public List<Products> findActiveProducts() {
        String jpql = "SELECT p FROM Products p WHERE p.active = true";
        TypedQuery<Products> query = em.createQuery(jpql, Products.class);
        return query.getResultList();
    }

    // ===== Đếm lượt like =====
    public Long countLikes(String productId) {
        String jpql = "SELECT COUNT(f) FROM Favorites f WHERE f.product.productId = :pid";
        return em.createQuery(jpql, Long.class)
                .setParameter("pid", productId)
                .getSingleResult();
    }

    // ===== Tăng lượt xem =====
    public void increaseView(String productId) {
        String jpql = "UPDATE Products p SET p.views = p.views + 1 WHERE p.productId = :pid";
        em.createQuery(jpql)
                .setParameter("pid", productId)
                .executeUpdate();
    }
}
