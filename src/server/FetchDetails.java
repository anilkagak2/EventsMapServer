package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FetchDetails
 */
@WebServlet("/FetchDetails")
public class FetchDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection connection;
    private int loginId=0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchDetails() {
        super();
    }

	protected void closeConnection () {
		if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection terminated");
            } catch (Exception e) {
            	System.out.println(e.toString()+ "\n Exception Stack: \n");
                e.printStackTrace();
            }
        }
	}
	
	private void sendResponse (HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		        request.getRequestDispatcher("/Secured/Details.jsp").forward(request, response);
	}
    
	protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession(false);
			if (session != null) {
				loginId = Integer.parseInt(session.getAttribute("loginId").toString());
			} else {
				// SERIOUS ERROR IN EXECUTION FLOW
				System.out.println("Serious erorr in execution flow.. session null");
				request.getRequestDispatcher("/Login.jsp").forward(request, response);
				return;
			}

			String mysqlUser = Declarations.mysqlUser;
			String mysqlPass = Declarations.mysqlPass;
			String url = Declarations.url;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, mysqlUser, mysqlPass);

			if(connection!=null){
				Statement s = connection.createStatement();
				String query = "SELECT * FROM Login WHERE loginId='"+loginId+"'";
				s.executeQuery(query);
				System.out.println (query);
				ResultSet rs = s.getResultSet();
				
				LoginDetails login = new LoginDetails();
				
				if(rs.next ())
				{
					login.userName = rs.getString("userName");
					login.loginId = rs.getInt("loginId");
					login.emailId = rs.getString("email");
					login.post = rs.getString("post");
				}
				rs.close();
				
				// set the attributes required
				request.setAttribute("user",login);
			}
			else{
				System.out.println("Database connection failed.");
				
				String home="/General/Events.jsp";
				if (loginId == 1)
					home = "/Secured/Admin.jsp";
				
				request.getRequestDispatcher(home).forward(request, response);
				return;
			}
			// List<Passenger> passengers = service.list();
			sendResponse(request, response);
		}catch(Exception e)
		{
			closeConnection();
			System.out.println("Fetch Details"+e.toString()+ "\n Exception Stack: \n");
			e.printStackTrace();
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
