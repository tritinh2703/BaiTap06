package com.example.util;

import jakarta.persistence.*;

public class JPAUtil {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("VideoPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
