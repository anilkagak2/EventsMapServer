package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Reset account facility to the Administrator for resetting different accounts 
	 * of Secys, Profs, etc.
	 * 
	 * Give proper thought to the Prepared Statements in executing queries
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		String pass = request.getParameter("password");
		String userName = request.getParameter("userName");
		String webmail = request.getParameter("webmail");
		
		boolean Ispass = false;
		if (request.getParameter("password") != null) {
			Ispass = true;
		}
		try {
			// Create Connection
	        String mysqlUser = Declarations.mysqlUser;
	        String mysqlPass = Declarations.mysqlPass;
	        String url = Declarations.url;
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	        Connection connection = DriverManager.getConnection(url, mysqlUser, mysqlPass);

        // Execute Queries by checking passHash
        if (connection != null) {
            PreparedStatement s = null;
            String query = "";

            query = "update Login set ";
			if (Ispass)
				query += " passwdHash=sha2(?,256) ,";
			query += " userName=? ,email=? where loginId=? ";
			s = connection.prepareStatement(query);
			int j=1;
			if (Ispass) s.setString(j++, pass);
			s.setString (j++, userName);
			s.setString (j++, webmail);
			s.setInt (j++, userId);
			System.out.println(s.toString());

  			s.executeUpdate();
        }
		}catch(Exception e){
			System.out.println("Error in change password : "+e.getMessage());
			e.printStackTrace();
//    		request.setAttribute("error", e.getMessage());
			/*
			 * Send it to someplace so that appliation keeps working
			 * */
//    		request.getRequestDispatcher(Declarations.).forward(request, response);
		}
	}

}