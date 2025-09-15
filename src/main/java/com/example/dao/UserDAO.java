package com.example.dao;

import com.example.entity.User;
import com.example.util.JPAUtil;
import jakarta.persistence.*;
import java.util.List;

public class UserDAO {
    public void create(User u) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        em.close();
    }

    public User find(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        return em.find(User.class, id);
    }

    public void update(User u) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(u);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        User u = em.find(User.class, id);
        if (u != null) em.remove(u);
        em.getTransaction().commit();
        em.close();
    }

    public List<User> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public List<User> searchByUsername(String keyword) {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<User> q = em.createQuery(
            "SELECT u FROM User u WHERE u.username LIKE :kw", User.class);
        q.setParameter("kw", "%" + keyword + "%");
        return q.getResultList();
    }
}
