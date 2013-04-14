package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.EmailValidator;
import javax.servlet.http.HttpSession;

public class Declarations {
	public static final int adminId=1; 				// Define admin's post here
	public static final String mysqlUser = "root";
	public static final String mysqlPass = "";
	public static final String url = "jdbc:mysql://localhost:3306/EventsMapServer";
	public static final String loginHome = "/Login.jsp";
	public static final String userHome = "/General/Events.jsp";
	public static final String userDetails = "/General/Details.jsp";
	public static final String addEventHome = "/General/AddEvent.jsp";
	public static final String registerHome = "/Secured/Register.jsp";
	public static final String adminHome = "/Secured/Admin.jsp";
	
	/*
	 * Maps loginId to HomePage of the user via checking the session field &
	 * comparing the loginId with adminId.
	 * */
	public static String homePage (HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (request == null || session == null) return loginHome;
		else {
			try{
				int loginId = Integer.parseInt(session.getAttribute("loginId").toString ());
				if (loginId == Declarations.adminId) return adminHome;
				else return userHome;
			} catch (Exception e) {
				System.out.println(e.toString()+ "\n Exception Stack: \n");
	            e.printStackTrace();
				return loginHome;
			}
		}
	}
	
	/* Checks */
	/* Email Field check */
	public static boolean isValidEmail (String email) {
		return EmailValidator.getInstance().isValid(email);
	}
	
	public static void errorNotFound (HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();

	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Error</title>");
	    out.println("</head>");
	    out.println("<body bgcolor=\"white\">");
	    out.println("</body>");
	    out.println ("Error 404: Object Not found!");
	    out.println("</html>");
	}
	
	/* Close an open connection. */
	public static void closeConnection (Connection connection) {
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
	
	/* Returns the list containing posts. */
	public static List<String> fetchPosts (Connection conn) throws SQLException  {
		boolean opened = false;
		if (conn == null) {
			/* Open the connection if the given object is null. */
			try{
				// Create Connection
		        String mysqlUser = Declarations.mysqlUser;
		        String mysqlPass = Declarations.mysqlPass;
		        String url = Declarations.url;
		        Class.forName("com.mysql.jdbc.Driver").newInstance();
		        conn = DriverManager.getConnection(url, mysqlUser, mysqlPass);
		        if (conn != null) opened = true;
		        else {
		        	System.out.println ("Cannot open the connection");
		        	return new ArrayList<String>();
		        }
			}
			catch (Exception e){
				System.out.println(e.toString()+ "\n Exception Stack: \n");
	            e.printStackTrace();
				return new ArrayList<String>();
			}
		}
		
		Statement s = conn.createStatement();
		String query = "SELECT DISTINCT post FROM Login";
		s.executeQuery(query);
		System.out.println (query);
		ResultSet rs = s.getResultSet();
		
		List<String> posts = new ArrayList<String>();
		while (rs.next ())
		{
			posts.add(rs.getString("post").toString());
		}
		rs.close();
		
		/* Close the connection if you opened it. */
		if (opened) closeConnection(conn);
		
		// set the attributes required
		System.out.println ("Setting posts. posts[0] = "+posts.get(0));
		
		return posts; 
	}
}
