package projects.kiran.programming.myapp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projects.kiran.programming.myapp.dao.DepartmentDao;
import projects.kiran.programming.myapp.dao.DepartmentDaoImpl;
import projects.kiran.programming.myapp.entity.Department;
import projects.kiran.programming.myapp.entity.Employee;
import projects.kiran.programming.myapp.service.DepartmentService;
import projects.kiran.programming.myapp.service.DepartmentServiceImpl;
import projects.kiran.programming.myapp.service.EmployeeService;
import projects.kiran.programming.myapp.service.EmployeeServiceImpl;

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
			EmployeeService employeeService = (EmployeeService) getServletConfig().getServletContext()
					.getAttribute("employeeService");

			List<Employee> employees = employeeService.getEmployees();
			request.setAttribute("employees", employees);

			DepartmentService departmentService = (DepartmentService) getServletConfig().getServletContext()
					.getAttribute("departmentService");

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
