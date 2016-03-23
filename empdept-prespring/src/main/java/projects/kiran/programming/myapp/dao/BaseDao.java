package projects.kiran.programming.myapp.dao;

import javax.persistence.EntityManager;

public interface BaseDao {
	public EntityManager getEntityManager();

	public void setEntityManager(EntityManager entityManager);
}
