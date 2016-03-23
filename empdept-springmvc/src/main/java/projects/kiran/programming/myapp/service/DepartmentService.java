package projects.kiran.programming.myapp.service;

import java.util.List;

import projects.kiran.programming.myapp.dao.DepartmentDao;
import projects.kiran.programming.myapp.entity.Department;

public interface DepartmentService {

    public DepartmentDao getDepartmentDao();

    public EmployeeService getEmployeeService();

    public void setEmployeeService(EmployeeService employeeService);

    public void setDepartmentDao(DepartmentDao departmentDao);

    public void saveDepartment(String departmentName, String departmentEmail, String[] employeeIds) throws Exception;

    public List<Department> getDepartments() throws Exception;

}
