package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

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
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userId = Integer.parseInt(request.getParameter("id"));
		String pass = request.getParameter("password");
		boolean del = false;
		if (request.getParameter("del") != null){
			del = true;
		}
		try{
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
            if(del){
            	query = "DELETE FROM `Login` WHERE `loginId` = ?";
            	s = connection.prepareStatement(query);
            	s.setInt(1, userId);
            }else{
              query = "update Login set passwdHash=sha2(?,256) where loginId= ?";
              s = connection.prepareStatement(query);
              s.setString (1, pass);
          	  s.setInt (2, userId);
          	  System.out.println(s.toString());
            }
            s.executeUpdate();
        }
		}catch(Exception e){
			System.out.println("Error in change password : "+e.getMessage());
		}
	}

}
