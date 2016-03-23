package projects.kiran.programming.myapp.servlet;

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

import projects.kiran.programming.myapp.entity.Employee;
import projects.kiran.programming.myapp.service.EmployeeService;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeSaveServlet")
public class EmployeeSaveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EmployeeSaveServlet() {
	super();
	// TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	// TODO Auto-generated method stub
	doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	try {
	    String firstName = request.getParameter("firstName");
	    String lastName = request.getParameter("lastName");
	    String age = request.getParameter("age");

	    ApplicationContext applicationContext = WebApplicationContextUtils
	            .getWebApplicationContext(this.getServletContext());

	    EmployeeService employeeService = (EmployeeService) applicationContext.getBean("employeeService");
	    employeeService.saveEmployee(firstName, lastName, age);

	    List<Employee> employees = employeeService.getEmployees();
	    request.setAttribute("employees", employees);

	    RequestDispatcher dispatcher = request.getRequestDispatcher("/EmployeeAddServlet");
	    dispatcher.include(request, response);
	} catch (Exception e) {
	    // Handle errors for Class.forName
	    e.printStackTrace();
	    request.setAttribute("isError", "true");
	}

    }

}
