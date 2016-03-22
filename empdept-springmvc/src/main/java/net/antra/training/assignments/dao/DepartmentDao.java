package net.antra.training.assignments.dao;

import java.util.List;

import net.antra.training.assignments.entity.Department;

public interface DepartmentDao extends BaseDao {

    public void save(Department department) throws Exception;

    public List<Department> findAll() throws Exception; 

}
