package server;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.validator.routines.EmailValidator;
import javax.servlet.http.HttpSession;

public class Declarations {
	public static final int adminId=1; 				// Define admin's post here
	public static final String mysqlUser = "root";
	public static final String mysqlPass = "root";
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
}
