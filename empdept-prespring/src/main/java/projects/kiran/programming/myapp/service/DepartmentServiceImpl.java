package projects.kiran.programming.myapp.service;

import java.util.List;

import projects.kiran.programming.myapp.dao.DepartmentDao;
import projects.kiran.programming.myapp.entity.Department;
import projects.kiran.programming.myapp.entity.Employee;

public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao departmentDao = null;

    private EmployeeService employeeService = null;

    public EmployeeService getEmployeeService() {
	return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
	this.employeeService = employeeService;
    }

    public DepartmentDao getDepartmentDao() {
	return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
	this.departmentDao = departmentDao;
    }

    @Override
    public void saveDepartment(String departmentName, String departmentEmail, String[] employeeIds) throws Exception {
	try {
	    Department department = new Department();
	    department.setDepartmentName(departmentName);
	    department.setDepartmentEmail(departmentEmail);

	    List<Employee> employees = getEmployeeService().getEmployeesByIds(employeeIds);
	    for (Employee employee : employees) {
		employee.setDepartment(department);
	    }

	    getDepartmentDao().save(department);
	} catch (Exception e) {
	    throw e;
	}
    }

    @Override
    public List<Department> getDepartments() throws Exception {
	List<Department> departments = null;
	try {
	    departments = getDepartmentDao().findAll();
	} catch (Exception e) {
	    throw e;
	}
	return departments;
    }

}
