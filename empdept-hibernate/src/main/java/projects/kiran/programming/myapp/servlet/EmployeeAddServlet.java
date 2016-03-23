package projects.kiran.programming.myapp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import projects.kiran.programming.myapp.model.Employee;

/**
 * Servlet implementation class EmployeeAddServlet
 */
@WebServlet("/EmployeeAddServlet")
public class EmployeeAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeAddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Session session = null;
		StandardServiceRegistry registry = null;
		SessionFactory sessionFactory = null;
		try {
			registry = new StandardServiceRegistryBuilder().configure() // configures
																		// settings
																		// from
																		// hibernate.cfg.xml
					.build();
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Employee> employees = session.createQuery("from Employee").list();
			request.setAttribute("employees", employees);
			session.getTransaction().commit();

		} catch (Throwable e) {
			// Handle errors for Class.forName
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(registry);

			request.setAttribute("isError", "true");
		} finally {
			if (session != null) {
				session.close();
			}
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/employee-add.jsp");
		dispatcher.include(request, response);

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
