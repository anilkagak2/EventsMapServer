package server;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Details
 */

/*
 * TODO Find out the utility of this servlet
 * I think no one is using this servlet
 * */
@WebServlet("/Details")
public class Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	String user;
    String emailId;
	String post;
	String userName;
	Connection connection;
	List<LoginDetails> loginfo = new ArrayList<LoginDetails>();

	protected void closeConnection () {
		if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection terminated");
            } catch (Exception e) {
            	System.out.println(e.toString());
            }
        }
	}
	/*
	 * Fetches all 
	 * */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, SQLException {
		response.setContentType("text/html;charset=UTF-8");	
	
	//setting db connecn
	try{
	    String mysqlUser = Declarations.mysqlUser;
	    String mysqlPass = Declarations.mysqlPass;
	    String url = Declarations.url;
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
	    connection = DriverManager.getConnection(url, mysqlUser, mysqlPass);
	    System.out.println("Checking your identity.."+ user +"\n");
    }
	
	/*
	 * TODO Send it to some place, otherwise it'll just get blocked
	 * */
    catch(Exception e){
            System.out.println("Error : " + e.toString());
    }
	
	if (connection != null) {
        Statement s = connection.createStatement();
		
		String query = "Select E.user, E.userName, E.emailId, E.post FROM Login E";
		System.out.println(query);
		s.executeQuery(query);
		ResultSet rs = s.getResultSet();
		
		while (rs.next()) {
			LoginDetails log = new LoginDetails();
			log.loginId = rs.getInt("loginId");
			log.userName = rs.getString("userName");
			log.emailId = rs.getString("email");
			log.post = rs.getString("post");
			loginfo.add(log);
		}
		
		rs.close();
		s.close();
		closeConnection ();
	}
}
	
	/* Both get & post redirects to the common function
	 * */
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
		 try {
    	processRequest(request, response);
		 }catch (Exception e) {
			 
		 }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		try{
	    processRequest(request, response);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}	
}
