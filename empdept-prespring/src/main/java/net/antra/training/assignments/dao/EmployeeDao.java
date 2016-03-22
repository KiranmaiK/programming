package net.antra.training.assignments.dao;

import java.util.List;

import net.antra.training.assignments.entity.Employee;

public interface EmployeeDao extends BaseDao {

    public void save(Employee employee) throws Exception;

    public List<Employee> findAll() throws Exception;

    public List<Employee> findByIds(List<Integer> employeeIds) throws Exception;

}
