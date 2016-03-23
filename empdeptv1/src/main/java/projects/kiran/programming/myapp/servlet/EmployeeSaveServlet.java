package projects.kiran.programming.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

import projects.kiran.programming.myapp.model.Employee;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeSaveServlet")
public class EmployeeSaveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/practice";

    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "antra";

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
	Connection conn = null;
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	try {

	    String firstName = request.getParameter("firstName");
	    String lastName = request.getParameter("lastName");
	    String age = request.getParameter("age");

	    System.out.println("Register JDBC connection ");

	    // Register JDBC connection
	    Class.forName(JDBC_DRIVER);

	    // connecting to database
	    System.out.println("connecting  to databsase ");
	    conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

	    String query = "insert into employee(first_name, last_name, age) values (?, ?, ?)";

	    preparedStatement = conn.prepareStatement(query);
	    preparedStatement.setString(1, firstName);
	    preparedStatement.setString(2, lastName);
	    preparedStatement.setInt(3, Integer.parseInt(age));

	    preparedStatement.executeUpdate();

	    String selectQuery = "select employee_id, first_name, last_name, age from employee";
	    statement = conn.createStatement();
	    resultSet = statement.executeQuery(selectQuery);
	    if (resultSet != null && resultSet.next()) {
		List<Employee> employees = new ArrayList<Employee>();
		do {
		    Employee employee = new Employee();
		    employee.setId(resultSet.getInt("employee_id"));
		    employee.setFirstName(resultSet.getString("first_name"));
		    employee.setLastName(resultSet.getString("last_name"));
		    employee.setAge(resultSet.getInt("age"));

		    employees.add(employee);
		} while (resultSet.next());

		request.setAttribute("employees", employees);
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
	    // finally block used to close resources
	    if (conn != null) {
		try {
		    conn.close();
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }

	    RequestDispatcher dispatcher = request.getRequestDispatcher("/EmployeeAddServlet");
	    dispatcher.include(request, response);

	}

    }

}
