package net.antra.training.assignments.servlet;

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

import net.antra.training.assignments.entity.Employee;

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

		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("net.antra.training.assignments");
			entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();

			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String age = request.getParameter("age");

			Employee employee = new Employee();
			employee.setFirstName(firstName);
			employee.setLastName(lastName);
			employee.setAge(Integer.parseInt(age));

			entityManager.persist(employee);

			List<Employee> employees = entityManager.createQuery("from Employee", Employee.class).getResultList();

			entityManager.getTransaction().commit();

			request.setAttribute("employees", employees);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/EmployeeAddServlet");
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
