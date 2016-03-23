package projects.kiran.programming.myapp.dao;

import javax.persistence.EntityManager;

public class BaseDaoImpl implements BaseDao {

	private EntityManager entityManager;

	public BaseDaoImpl() throws Exception {

	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
