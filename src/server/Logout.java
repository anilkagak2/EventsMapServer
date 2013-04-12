package server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
    }

    private void processRequest (HttpServletRequest request, HttpServletResponse response) throws IOException {
    	System.out.println("Logging out.");
		HttpSession session = request.getSession(false);
		if (session != null) {
			System.out.println("Removing the attributes");
			session.removeAttribute("user");
			session.removeAttribute("loginId");
			
			System.out.println("events "+ session.getAttribute("events"));
			session.removeAttribute("events");
			session.invalidate();
			System.out.println("events "+ session);
		}
		
		response.sendRedirect("Login.jsp");
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * NEVER CALLED --> CAN BE DELETED
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Called when LOGGIN OUT
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
