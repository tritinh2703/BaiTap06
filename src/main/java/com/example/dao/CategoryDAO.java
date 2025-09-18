package com.example.dao;

import com.example.entity.Category;
import com.example.util.JPAUtil;
import jakarta.persistence.*;
import java.util.List;

public class CategoryDAO {
    public void create(Category c) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        em.close();
    }

    public Category find(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        return em.find(Category.class, id);
    }

    public void update(Category c) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        Category c = em.find(Category.class, id);
        if (c != null) em.remove(c);
        em.getTransaction().commit();
        em.close();
    }

    public List<Category> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }

    public List<Category> searchByName(String keyword) {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Category> q = em.createQuery(
          "SELECT c FROM Category c WHERE c.name LIKE :kw", Category.class);
        q.setParameter("kw", "%" + keyword + "%");
        return q.getResultList();
    }

    public List<Category> findAllWithVideos() {
        EntityManager em = JPAUtil.getEntityManager();
        return em.createQuery(
            "SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.videos",
            Category.class
        ).getResultList();
    }
}
