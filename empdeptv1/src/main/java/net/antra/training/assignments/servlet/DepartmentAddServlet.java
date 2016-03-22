package net.antra.training.assignments.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List; 

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.antra.training.assignments.model.Department;
import net.antra.training.assignments.model.Employee;

/**
 * Servlet implementation class DepartmentAddServlet
 */
@WebServlet("/DepartmentAddServlet")
public class DepartmentAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/practice";

    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "antra";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentAddServlet() {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	Connection conn = null;
	try {
	    System.out.println("Register JDBC connection ");

	    // Register JDBC connection
	    Class.forName(JDBC_DRIVER);

	    // connecting to database
	    System.out.println("connecting  to databsase ");
	    conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

	    String selectEmployeesQuery = "select employee_id, first_name, last_name, age from employee";
	    Statement statement1 = conn.createStatement();
	    ResultSet resultSet1 = statement1.executeQuery(selectEmployeesQuery);
	    if (resultSet1 != null && resultSet1.next()) {
		List<Employee> employees = new ArrayList<Employee>();
		do {
		    Employee employee = new Employee();
		    employee.setId(resultSet1.getInt("employee_id"));
		    employee.setFirstName(resultSet1.getString("first_name"));
		    employee.setLastName(resultSet1.getString("last_name"));
		    employee.setAge(resultSet1.getInt("age"));

		    employees.add(employee);
		} while (resultSet1.next());

		request.setAttribute("employees", employees);
	    }

	    String selectDepartmentQuery = "select department_id, department_name, department_email from department";
	    Statement statement2 = conn.createStatement();
	    ResultSet resultSet2 = statement2.executeQuery(selectDepartmentQuery);
	    if (resultSet2 != null && resultSet2.next()) {
		List<Department> departmentEmployees = new ArrayList<Department>();
		do {
		    Department departmentEmployee = new Department();
		    departmentEmployee.setDepartmentId(resultSet2.getInt("department_id"));
		    departmentEmployee.setDepartmentName(resultSet2.getString("department_name"));
		    departmentEmployee.setDepartmentEmail(resultSet2.getString("department_email"));

		    String selectEmployeeByDepartmentQuery = "select first_name, last_name, age from employee where department_id = ?";
		    PreparedStatement preparedStatement = conn.prepareStatement(selectEmployeeByDepartmentQuery);
		    preparedStatement.setInt(1, departmentEmployee.getDepartmentId());
		    ResultSet resultSet3 = preparedStatement.executeQuery();
		    if (resultSet3 != null && resultSet3.next()) {
			do {
			    Employee employee = new Employee();
			    employee.setFirstName(resultSet3.getString("first_name"));
			    employee.setLastName(resultSet3.getString("last_name"));
			    employee.setAge(resultSet3.getInt("age"));
			    departmentEmployee.addEmployee(employee);
			} while (resultSet3.next());
		    }
		    departmentEmployees.add(departmentEmployee);

		} while (resultSet2.next());
		request.setAttribute("departmentEmployees", departmentEmployees);
	    }

	} catch (SQLException se) {
	    // Handle errors for JDBC
	    se.printStackTrace();
	    request.setAttribute("isError", "true");
	} catch (Exception e) {
	    // Handle errors for Class.forName
	    e.printStackTrace();
	    request.setAttribute("isError", "true");
	} finally {
	    if (conn != null) {
		try {
		    conn.close();
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	}
	RequestDispatcher dispatcher = request.getRequestDispatcher("/department-add.jsp");
	dispatcher.include(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	doGet(request, response);
    }

}
