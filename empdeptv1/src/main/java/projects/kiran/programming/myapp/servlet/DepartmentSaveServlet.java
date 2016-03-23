package projects.kiran.programming.myapp.servlet;

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
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projects.kiran.programming.myapp.model.Department;
import projects.kiran.programming.myapp.model.Employee;

/**
 * Servlet implementation class DepartmentServlet
 */
@WebServlet("/DepartmentSaveServlet")
public class DepartmentSaveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/practice";

    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "antra";

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
	response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	// TODO Auto-generated method stub
	Connection conn = null;
	PreparedStatement preparedStatement = null;
	try {
	    String deptName = request.getParameter("departmentName");
	    String deptEmail = request.getParameter("departmentEmail");

	    System.out.println("Register JDBC connection ");

	    // Register JDBC connection
	    Class.forName(JDBC_DRIVER);

	    // connecting to database
	    System.out.println("connecting  to databsase ");
	    conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

	    String query = "insert into department(DEPARTMENT_NAME, DEPARTMENT_EMAIL) values (?, ?)";

	    preparedStatement = conn.prepareStatement(query);
	    preparedStatement.setString(1, deptName);
	    preparedStatement.setString(2, deptEmail);
	    preparedStatement.executeUpdate();

	    String[] employeeIds = request.getParameterValues("employeeId");
	    if (employeeIds != null) {
		query = "update employee set department_id = LAST_INSERT_ID() where employee_id = ?";
		for (String employeeId : employeeIds) {
		    preparedStatement = conn.prepareStatement(query);
		    preparedStatement.setString(1, employeeId);
		    preparedStatement.executeUpdate();
		}
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
	    // finally block used to close resource
	    if (conn != null) {
		try {
		    conn.close();
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	}
	RequestDispatcher dispatcher = request.getRequestDispatcher("/DepartmentAddServlet");
	dispatcher.include(request, response);

    }

}
