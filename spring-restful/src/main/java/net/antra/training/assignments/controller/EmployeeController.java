package net.antra.training.assignments.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.antra.training.assignments.entity.Employee;
import net.antra.training.assignments.form.EmployeeForm;
import net.antra.training.assignments.service.EmployeeService;

@Controller
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
	public String addEmployee(Model model) {
		EmployeeForm employeeForm = new EmployeeForm();
		model.addAttribute("employeeForm", employeeForm);

		try {
			List<Employee> employees = employeeService.getEmployees();
			model.addAttribute("employees", employees);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "employee-add";
	}

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	public String saveEmployee(@ModelAttribute("employeeForm") EmployeeForm employeeForm, ModelMap model) {
		try {
			employeeService.saveEmployee(employeeForm.getFirstName(), employeeForm.getLastName(),
					employeeForm.getAge());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/addEmployee";
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public @ResponseBody Employee getEmployee(@PathVariable String id) {
		Employee employee = null;
		try {
			employee = getEmployeeService().getEmployeeById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}
}
