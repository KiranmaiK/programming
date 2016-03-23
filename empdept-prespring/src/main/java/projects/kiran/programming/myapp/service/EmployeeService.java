package projects.kiran.programming.myapp.service;

import java.util.List;

import projects.kiran.programming.myapp.dao.EmployeeDao;
import projects.kiran.programming.myapp.entity.Employee;

public interface EmployeeService {

    public EmployeeDao getEmployeeDao();

    public void setEmployeeDao(EmployeeDao employeeDao);

    public void saveEmployee(String firstName, String lastName, String age) throws Exception;

    public List<Employee> getEmployees() throws Exception;

    public List<Employee> getEmployeesByIds(String[] employeeIds) throws Exception;

}
