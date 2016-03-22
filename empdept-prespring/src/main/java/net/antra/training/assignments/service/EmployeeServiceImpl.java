package net.antra.training.assignments.service;

import java.util.ArrayList;
import java.util.List;

import net.antra.training.assignments.dao.EmployeeDao;
import net.antra.training.assignments.entity.Employee;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    public EmployeeDao getEmployeeDao() {
	return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
	this.employeeDao = employeeDao;
    }

    @Override
    public void saveEmployee(String firstName, String lastName, String age) throws Exception {
	try {
	    Employee employee = new Employee();
	    employee.setFirstName(firstName);
	    employee.setLastName(lastName);
	    employee.setAge(Integer.parseInt(age));

	    getEmployeeDao().save(employee);
	} catch (Exception e) {
	    throw e;
	}
    }

    @Override
    public List<Employee> getEmployees() throws Exception {
	List<Employee> employees = null;
	try {
	    employees = getEmployeeDao().findAll();
	} catch (Exception e) {
	    throw e;
	}
	return employees;
    }

    @Override
    public List<Employee> getEmployeesByIds(String[] employeeIds) throws Exception {
	List<Employee> employees = null;
	try {
	    List<Integer> empIds = new ArrayList<Integer>();
	    for (int i = 0; i < employeeIds.length; i++) {
		empIds.add(Integer.valueOf(employeeIds[i]));
	    }

	    employees = getEmployeeDao().findByIds(empIds);
	} catch (Exception e) {
	    throw e;
	}
	return employees;
    }

}
