package net.antra.training.assignments.dao;

import java.util.List;

import net.antra.training.assignments.entity.Department;

public class DepartmentDaoImpl extends BaseDaoImpl implements DepartmentDao {

	public DepartmentDaoImpl() throws Exception {
		super();
	}

	@Override
	public void save(Department department) throws Exception {
		try {
			getEntityManager().getTransaction().begin();
			getEntityManager().persist(department);
			getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Department> findAll() throws Exception {
		List<Department> departments = null;
		try {
			departments = getEntityManager().createQuery("from Department", Department.class).getResultList();
		} catch (Exception e) {
			throw e;
		}
		return departments;
	}

}
