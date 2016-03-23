package projects.kiran.programming.myapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import projects.kiran.programming.myapp.entity.Department;
import projects.kiran.programming.myapp.entity.Employee;
import projects.kiran.programming.myapp.form.DepartmentForm;
import projects.kiran.programming.myapp.service.DepartmentService;
import projects.kiran.programming.myapp.service.EmployeeService;

@Controller
public class DepartmentController {

    DepartmentService departmentService;

    EmployeeService employeeService;

    public DepartmentService getDepartmentService() {
	return departmentService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
	this.departmentService = departmentService;
    }

    public EmployeeService getEmployeeService() {
	return employeeService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
	this.employeeService = employeeService;
    }

    @RequestMapping(value = "/addDepartment", method = RequestMethod.GET)
    public String addDepartment(Model model) {

	try {
	    DepartmentForm departmentForm = new DepartmentForm();
	    model.addAttribute("departmentForm", departmentForm);

	    List<Employee> employees = employeeService.getEmployees();
	    model.addAttribute("employees", employees);

	    List<Department> departments = departmentService.getDepartments();
	    model.addAttribute("departments", departments);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return "department-add";
    }

    @RequestMapping(value = "/saveDepartment", method = RequestMethod.POST)
    public String saveDepartment(@ModelAttribute("departmentForm") DepartmentForm departmentForm, ModelMap model) {

	try {
	    departmentService.saveDepartment(departmentForm.getDepartmentName(), departmentForm.getDepartmentEmail(),
	            departmentForm.getEmployeeIds());

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return "redirect:/addDepartment";
    }

}
