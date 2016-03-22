package net.antra.training.assignments.service;

import java.util.List;

import net.antra.training.assignments.dao.DepartmentDao;
import net.antra.training.assignments.entity.Department;

public interface DepartmentService {

    public DepartmentDao getDepartmentDao();

    public EmployeeService getEmployeeService();

    public void setEmployeeService(EmployeeService employeeService);

    public void setDepartmentDao(DepartmentDao departmentDao);

    public void saveDepartment(String departmentName, String departmentEmail, String[] employeeIds) throws Exception;

    public List<Department> getDepartments() throws Exception;

}
