package projects.kiran.programming.myapp.servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projects.kiran.programming.myapp.entity.Department;
import projects.kiran.programming.myapp.entity.Employee;

@WebServlet("/DepartmentAddServlet")
public class DepartmentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DepartmentAddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		try {

			entityManagerFactory = Persistence.createEntityManagerFactory("net.antra.training.assignments");
			entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();

			List<Employee> employees = entityManager.createQuery("from Employee", Employee.class).getResultList();

			List<Department> departments = entityManager.createQuery("from Department", Department.class)
					.getResultList();

			entityManager.getTransaction().commit();

			request.setAttribute("employees", employees);
			request.setAttribute("departments", departments);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/department-add.jsp");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
