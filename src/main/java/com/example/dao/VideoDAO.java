package com.example.dao;

import com.example.entity.Video;
import com.example.util.JPAUtil;
import jakarta.persistence.*;
import java.util.List;

public class VideoDAO {
    public void create(Video v) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(v);
        em.getTransaction().commit();
        em.close();
    }

    public Video find(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        return em.find(Video.class, id);
    }

    public void update(Video v) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(v);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        Video v = em.find(Video.class, id);
        if (v != null) em.remove(v);
        em.getTransaction().commit();
        em.close();
    }

    public List<Video> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        return em.createQuery("SELECT v FROM Video v", Video.class).getResultList();
    }

    public List<Video> searchByTitle(String keyword) {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Video> q = em.createQuery(
            "SELECT v FROM Video v WHERE v.title LIKE :kw", Video.class);
        q.setParameter("kw", "%" + keyword + "%");
        return q.getResultList();
    }
    public List<Video> findByCategory(int catId) {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Video> q = em.createQuery(
            "SELECT v FROM Video v WHERE v.category.id = :cid", Video.class);
        q.setParameter("cid", catId);
        return q.getResultList();
    }
}
