package net.antra.training.assignments.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import net.antra.training.assignments.entity.Department;
import net.antra.training.assignments.entity.Employee;
import net.antra.training.assignments.service.DepartmentService;
import net.antra.training.assignments.service.EmployeeService;

@WebServlet("/DepartmentAddServlet")
public class DepartmentAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DepartmentAddServlet() {
	super();
	// TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	try {

	    ApplicationContext applicationContext = WebApplicationContextUtils
	            .getWebApplicationContext(this.getServletContext());
	    
	    EmployeeService employeeService = (EmployeeService) applicationContext.getBean("employeeService");
	    List<Employee> employees = employeeService.getEmployees();
	    request.setAttribute("employees", employees);

	    DepartmentService departmentService = (DepartmentService) applicationContext.getBean("departmentService");
	    List<Department> departments = departmentService.getDepartments();
	    request.setAttribute("departments", departments);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/department-add.jsp");
	    dispatcher.include(request, response);
	} catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("isError", "true");
	}
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	doGet(request, response);
    }

}
