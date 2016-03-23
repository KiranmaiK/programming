package projects.kiran.programming.myapp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import projects.kiran.programming.myapp.dao.DepartmentDao;
import projects.kiran.programming.myapp.dao.DepartmentDaoImpl;
import projects.kiran.programming.myapp.entity.Department;
import projects.kiran.programming.myapp.entity.Employee;
import projects.kiran.programming.myapp.service.DepartmentService;
import projects.kiran.programming.myapp.service.DepartmentServiceImpl;
import projects.kiran.programming.myapp.service.EmployeeService;
import projects.kiran.programming.myapp.service.EmployeeServiceImpl;

/**
 * Servlet implementation class DepartmentServlet
 */
@WebServlet("/DepartmentSaveServlet")
public class DepartmentSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DepartmentSaveServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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
			String deptName = request.getParameter("departmentName");
			String deptEmail = request.getParameter("departmentEmail");
			String[] employeeIds = request.getParameterValues("employeeId");

			DepartmentService departmentService = (DepartmentService) getServletConfig().getServletContext()
					.getAttribute("departmentService");

			departmentService.saveDepartment(deptName, deptEmail, employeeIds);

			List<Department> departments = departmentService.getDepartments();
			request.setAttribute("departments", departments);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/DepartmentAddServlet");
			dispatcher.include(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("isError", "true");
		}

	}

}
