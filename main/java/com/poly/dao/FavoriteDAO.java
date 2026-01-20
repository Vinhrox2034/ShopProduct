package com.poly.dao;

import com.poly.entity.Favorites;
import com.poly.entity.Products;
import com.poly.entity.Users;

import java.util.List;

public class FavoriteDAO extends AbstractDAO<Favorites, Long> {

    public FavoriteDAO() {
        super(Favorites.class);
    }

    // ===== Like product =====
    public void like(Users user, Products product) {
        Favorites f = new Favorites();
        f.setUser(user);
        f.setProduct(product);
        create(f);
    }

    // ===== Unlike product =====
    public void unlike(String userId, String productId) {
        String jpql = "DELETE FROM Favorites f " +
                "WHERE f.user.userId = :uid AND f.product.productId = :pid";

        em.createQuery(jpql)
                .setParameter("uid", userId)
                .setParameter("pid", productId)
                .executeUpdate();
    }

    // ===== Check liked =====
    public boolean isLiked(String userId, String productId) {
        String jpql = "SELECT COUNT(f) FROM Favorites f " +
                "WHERE f.user.userId = :uid AND f.product.productId = :pid";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("uid", userId)
                .setParameter("pid", productId)
                .getSingleResult();

        return count > 0;
    }

    // ===== Users who liked a product (Report) =====
    public List<Object[]> usersLikedProduct(String productId) {
        String jpql = "SELECT f.user.username, f.user.email " +
                "FROM Favorites f WHERE f.product.productId = :pid";

        return em.createQuery(jpql, Object[].class)
                .setParameter("pid", productId)
                .getResultList();
    }
}
