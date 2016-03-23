package projects.kiran.programming.myapp.dao;

import java.util.List;

import projects.kiran.programming.myapp.entity.Department;

public interface DepartmentDao extends BaseDao {

    public void save(Department department) throws Exception;

    public List<Department> findAll() throws Exception; 

}
