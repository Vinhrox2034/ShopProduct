package com.poly.dao;

import com.poly.utils.XJPA;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class AbstractDAO<T, K> {

    protected EntityManager em = XJPA.getEntityManager();
    private Class<T> entityClass;

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public T findById(K id) {
        return em.find(entityClass, id);
    }

    public List<T> findAll() {
        String jpql = "SELECT o FROM " + entityClass.getSimpleName() + " o";
        TypedQuery<T> query = em.createQuery(jpql, entityClass);
        return query.getResultList();
    }

    public void close() {
        em.close();
    }
}
