package projects.kiran.programming.myapp.dao;

import java.util.List;

import projects.kiran.programming.myapp.entity.Employee;

public interface EmployeeDao extends BaseDao {

    public void save(Employee employee) throws Exception;

    public List<Employee> findAll() throws Exception;

    public List<Employee> findByIds(List<Integer> employeeIds) throws Exception;

    public Employee findById(int employeeId) throws Exception;

}
