package projects.kiran.programming.myapp.dao;

import java.util.List;

import javax.persistence.Query;

import projects.kiran.programming.myapp.entity.Employee;

public class EmployeeDaoImpl extends BaseDaoImpl implements EmployeeDao {

	public EmployeeDaoImpl() throws Exception {
		super();
	}

	@Override
	public void save(Employee employee) throws Exception {
		try {
			getEntityManager().getTransaction().begin();
			getEntityManager().persist(employee);
			getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Employee> findAll() throws Exception {
		List<Employee> employees = null;
		try {
			employees = getEntityManager().createQuery("from Employee", Employee.class).getResultList();
		} catch (Exception e) {
			throw e;
		}
		return employees;
	}

	@Override
	public List<Employee> findByIds(List<Integer> employeeIds) throws Exception {
		List<Employee> employees = null;
		try {
			Query query = getEntityManager().createQuery("SELECT e from Employee e where e.id in :ids");
			query.setParameter("ids", employeeIds);
			employees = query.getResultList();
		} catch (Exception e) {
			throw e;
		}
		return employees;
	}

}
