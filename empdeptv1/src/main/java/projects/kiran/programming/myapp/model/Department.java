package projects.kiran.programming.myapp.model;

import java.util.List;

public class Department {

	private int departmentId;
	private String departmentName;
	private String departmentEmail;
	private List<Employee> departmentEmployees;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentEmail() {
		return departmentEmail;
	}

	public void setDepartmentEmail(String departmentEmail) {
		this.departmentEmail = departmentEmail;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public List<Employee> getDepartmentEmployees() {
		return departmentEmployees;
	}

	public void setDepartmentEmployees(List<Employee> departmentEmployees) {
		this.departmentEmployees = departmentEmployees;
	}
	
	public void addEmployee(Employee employee) {
	    departmentEmployees.add(employee);
	}
	

}
