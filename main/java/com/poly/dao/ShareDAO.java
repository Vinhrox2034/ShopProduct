package com.poly.dao;

import com.poly.entity.Shares;

import java.util.List;

public class ShareDAO extends AbstractDAO<Shares, Long> {

    public ShareDAO() {
        super(Shares.class);
    }

    // Shares of a product
    public List<Object[]> sharesOfProduct(String productId) {
        String jpql = "SELECT s.user.username, s.emails, s.shareDate " +
                "FROM Shares s WHERE s.product.productId = :pid";
        return em.createQuery(jpql, Object[].class)
                .setParameter("pid", productId)
                .getResultList();
    }
}
