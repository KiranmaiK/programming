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

import projects.kiran.programming.myapp.model.Department;
import projects.kiran.programming.myapp.model.Employee;

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
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	// TODO Auto-generated method stub
	doPost(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	// TODO Auto-generated method stub
	StandardServiceRegistry registry = null;
	SessionFactory sessionFactory = null;
	Session session = null;

	try {
	    String deptName = request.getParameter("departmentName");
	    String deptEmail = request.getParameter("departmentEmail");
	    String[] employeeIds = request.getParameterValues("employeeId");

	    Integer[] empIds = new Integer[employeeIds.length];
	    for (int i = 0; i < employeeIds.length; i++) {
		empIds[i] = Integer.valueOf(employeeIds[i]);
	    }

	    registry = new StandardServiceRegistryBuilder().configure() // configures settings from hibernate.cfg.xml
	            .build();
	    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	    session = sessionFactory.openSession();
	    session.beginTransaction();

	    Department department = new Department();
	    department.setDepartmentName(deptName);
	    department.setDepartmentEmail(deptEmail);

	    String hql = "from Employee where id in (:ids)";
	    Query query = session.createQuery(hql);
	    //query.setParameter("ids", employeeIds);
	    query.setParameterList("ids", empIds);
	    List<Employee> employees = query.list();
	    for (Employee employee : employees) {
		employee.setDepartment(department);
		//session.save(employee);
	    }
	    
	    session.save(department);


	    List<Department> departments = session.createQuery("from Department").list();
	    request.setAttribute("departments", departments);


	    RequestDispatcher dispatcher = request.getRequestDispatcher("/DepartmentAddServlet");
	    dispatcher.include(request, response);
	    
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

    }

}
