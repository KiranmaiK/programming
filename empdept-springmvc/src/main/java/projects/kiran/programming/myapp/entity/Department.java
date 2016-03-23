package projects.kiran.programming.myapp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DEPARTMENT")
public class Department {

	private int departmentId;
	private String departmentName;
	private String departmentEmail;
	private List<Employee> departmentEmployees;

	public Department() {

	}

	public Department(String departmentName, String departmentEmail) {
		this.departmentName = departmentName;
		this.departmentEmail = departmentEmail;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEPARTMENT_ID", unique = true, nullable = false)
	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "DEPARTMENT_NAME")
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Column(name = "DEPARTMENT_EMAIL")
	public String getDepartmentEmail() {
		return departmentEmail;
	}

	public void setDepartmentEmail(String departmentEmail) {
		this.departmentEmail = departmentEmail;
	}
	
	@OneToMany(mappedBy = "department", fetch = FetchType.EAGER, targetEntity = Employee.class)
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
