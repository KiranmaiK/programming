package projects.kiran.programming.myapp.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projects.kiran.programming.myapp.dao.DepartmentDao;
import projects.kiran.programming.myapp.dao.DepartmentDaoImpl;
import projects.kiran.programming.myapp.dao.EmployeeDao;
import projects.kiran.programming.myapp.dao.EmployeeDaoImpl;
import projects.kiran.programming.myapp.service.DepartmentService;
import projects.kiran.programming.myapp.service.DepartmentServiceImpl;
import projects.kiran.programming.myapp.service.EmployeeService;
import projects.kiran.programming.myapp.service.EmployeeServiceImpl;

/**
 * Servlet implementation class InitializationServlet
 */
@WebServlet(urlPatterns = "/InitializationServlet", loadOnStartup = 1)
public class InitializationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitializationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

		try {
			EntityManagerFactory entityManagerFactory = Persistence
					.createEntityManagerFactory("net.antra.training.assignments");

			EntityManager entityManager = entityManagerFactory.createEntityManager();

			EmployeeDao employeeDao = new EmployeeDaoImpl();
			employeeDao.setEntityManager(entityManager);

			DepartmentDao departmentDao = new DepartmentDaoImpl();
			departmentDao.setEntityManager(entityManager);

			EmployeeService employeeService = new EmployeeServiceImpl();
			employeeService.setEmployeeDao(employeeDao);

			DepartmentService departmentService = new DepartmentServiceImpl();
			departmentService.setEmployeeService(employeeService);
			departmentService.setDepartmentDao(departmentDao);

			config.getServletContext().setAttribute("employeeService", employeeService);
			config.getServletContext().setAttribute("departmentService", departmentService);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}

	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
