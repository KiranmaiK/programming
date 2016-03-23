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
	StandardServiceRegistry registry = null;
	SessionFactory sessionFactory = null;
	Session session = null;

	try {

	    String firstName = request.getParameter("firstName");
	    String lastName = request.getParameter("lastName");
	    String age = request.getParameter("age");

	    registry = new StandardServiceRegistryBuilder().configure() // configures settings from hibernate.cfg.xml
	            .build();
	    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	    session = sessionFactory.openSession();
	    session.beginTransaction();


	    Employee employee = new Employee();
	    employee.setFirstName(firstName);
	    employee.setLastName(lastName);
	    employee.setAge(Integer.parseInt(age));

	    session.save(employee);
	    
	    session.getTransaction().commit();


	    session.beginTransaction();
	    List<Employee> employees = session.createQuery("from Employee").list();
	    request.setAttribute("employees", employees);
	    session.getTransaction().commit();

	} catch (Exception e) {
	    // Handle errors for Class.forName
	    e.printStackTrace();
	    request.setAttribute("isError", "true");
	} finally {
	    // finally block used to close resources
	    if (session != null) {
		session.close();
	    }
	    if (sessionFactory != null) {
		sessionFactory.close();
	    }
	}

	RequestDispatcher dispatcher = request.getRequestDispatcher("/EmployeeAddServlet");
	dispatcher.include(request, response);

    }

}
