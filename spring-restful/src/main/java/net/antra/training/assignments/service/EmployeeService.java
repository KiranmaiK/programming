package net.antra.training.assignments.service;

import java.util.List;

import net.antra.training.assignments.dao.EmployeeDao;
import net.antra.training.assignments.entity.Employee;

public interface EmployeeService {

    public EmployeeDao getEmployeeDao();

    public void setEmployeeDao(EmployeeDao employeeDao);

    public void saveEmployee(String firstName, String lastName, String age) throws Exception;

    public void saveEmployee(Employee employee) throws Exception;

    public List<Employee> getEmployees() throws Exception;

    public List<Employee> getEmployeesByIds(String[] employeeIds) throws Exception;

    public Employee getEmployeeById(String employeeId) throws Exception;

}
