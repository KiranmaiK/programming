package net.antra.training.assignments.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.antra.training.assignments.entity.Department;
import net.antra.training.assignments.entity.Employee;

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
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
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

		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("net.antra.training.assignments");
			entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();

			String deptName = request.getParameter("departmentName");
			String deptEmail = request.getParameter("departmentEmail");
			String[] employeeIds = request.getParameterValues("employeeId");

			Department department = new Department();
			department.setDepartmentName(deptName);
			department.setDepartmentEmail(deptEmail);

			List<Integer> empIds = new ArrayList<Integer>();
			for (int i = 0; i < employeeIds.length; i++) {
				empIds.add(Integer.valueOf(employeeIds[i]));
			}

			Query query = entityManager.createQuery("SELECT e from Employee e where e.id in :ids");
			query.setParameter("ids", empIds);
			List<Employee> employees = query.getResultList();

			for (Employee employee : employees) {
				employee.setDepartment(department);
			}

			entityManager.persist(department);

			List<Department> departments = entityManager.createQuery("from Department", Department.class)
					.getResultList();

			entityManager.getTransaction().commit();

			request.setAttribute("departments", departments);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/DepartmentAddServlet");
			dispatcher.include(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("isError", "true");
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
			if (entityManagerFactory != null) {
				entityManagerFactory.close();
			}
		}

	}

}
