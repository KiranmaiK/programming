package projects.kiran.programming.myapp.form;

public class DepartmentForm {
    private String departmentName;
    private String departmentEmail;
    private String[] employeeIds;

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

    public String[] getEmployeeIds() {
	return employeeIds;
    }

    public void setEmployeeIds(String[] employeeIds) {
	this.employeeIds = employeeIds;
    }

}
