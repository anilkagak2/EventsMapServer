package server;

import java.security.MessageDigest;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	String user;
    String email;
    String password;
    String hash;
    String post;
    Connection conn;

     protected String hashFunc(String passwrd)
    		 throws Exception {
        String password = passwrd;

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println("Hex format : " + sb.toString());
        
        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
        for (int i=0;i<byteData.length;i++) {
                String hex=Integer.toHexString(0xff & byteData[i]);
                if(hex.length()==1) hexString.append('0');
                hexString.append(hex);
        }
        //System.out.println("Hex format : " + hexString.toString());
        return hexString.toString();
}

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");

    try{
        user = request.getParameter("user");
        email = request.getParameter("email");
        password = request.getParameter("pass");
        hash = hashFunc(password);
        post = request.getParameter("post");
    }

    catch(Exception e){
    	System.out.println("Error..!! - in register");
    }

    try{
	    String mysqlUser = Declarations.mysqlUser;
	    String mysqlPass = Declarations.mysqlPass;
	    String url = Declarations.url;
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
	    conn = DriverManager.getConnection(url, mysqlUser, mysqlPass);
	    System.out.println("Checking your identity.."+ user +"\n");

	    if(conn!= null){
	             Statement s = conn.createStatement();
	             //String query = "INSERT INTO Login (loginId, passwdHash, email, post) VALUES ('"+user+ "','" + hash +
	             String query = "INSERT INTO Login (userName, passwdHash, email, post) VALUES ('"+user+ "','" + hash + 
	            		 	"','" + email + "','" + post+"')";
	             System.out.println(query);
	             s.executeUpdate(query);
	             s.close();
	             
	             // TODO VP Section to VP's DashBoard
	             // request.getRequestDispatcher("/Events.jsp").forward(request, response);
	             request.getRequestDispatcher("/Login.jsp").forward(request, response);
	    }
	    else{
	            System.out.println("Cannot connect to the database\n");
	    }
    }
    catch(Exception e){
            System.out.println("Error : " + e.toString());
    }
}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    	// TODO VP Section
 /*   	HttpSession session = request.getSession(false);
    	if (session != null && (session.getAttribute("user").toString () != "vp") ) { */
    		processRequest(request, response);
    	/*}
    	else { 
    		request.getRequestDispatcher("/Login.jsp").forward(request, response);
    	}*/
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    processRequest(request, response);
	}
}
