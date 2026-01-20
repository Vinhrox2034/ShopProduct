package com.poly.dao;

import com.poly.entity.Users;

import javax.persistence.NoResultException;

public class UserDAO extends AbstractDAO<Users, String> {

    public UserDAO() {
        super(Users.class);
    }

    public Users findByUsernameAndPassword(String username, String password) {
        try {
            String jpql = "SELECT u FROM Users u WHERE u.username = :un AND u.password = :pw AND u.active = true";
            return em.createQuery(jpql, Users.class)
                    .setParameter("un", username)
                    .setParameter("pw", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
