package projects.kiran.programming.myapp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see Servlet#init(ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
	// TODO Auto-generated method stub
    }

    /**
     * @see Servlet#destroy()
     */
    public void destroy() {
	// TODO Auto-generated method stub
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
	String username = request.getParameter("username");
	String password = request.getParameter("password");

	RequestDispatcher dispatcher = null;
	if ("1".equals(password)) {
	    HttpSession session = request.getSession();
	    session.setAttribute("username", username);

	    dispatcher = request.getRequestDispatcher("/main.jsp");
	    dispatcher.include(request, response);
	} else {
	    request.setAttribute("isLoginFailure", "true");

	    dispatcher = request.getRequestDispatcher("/login.jsp");
	    dispatcher.include(request, response);
	}
    }

}
