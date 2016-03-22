package net.antra.training.assignments.dao;

import javax.persistence.EntityManager;

public interface BaseDao {
    public EntityManager getEntityManager();

    public void setEntityManager(EntityManager entityManager);
}
