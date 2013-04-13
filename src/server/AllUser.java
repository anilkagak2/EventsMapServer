package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AllUser
 */
@WebServlet("/AllUser")
public class AllUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AllUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *      Fetches all the users from the Login table & shows their relevant
	 *      details to the Administrator.
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			String mysqlUser = Declarations.mysqlUser;
			String mysqlPass = Declarations.mysqlPass;
			String url = Declarations.url;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection(url, mysqlUser,
					mysqlPass);

			System.out.println("Fetching all users\n");

			// Execute Queries by checking passHash
			if (connection != null) {
				Statement s = connection.createStatement();
				/*
				 * Changed the Table structure--> loginId = INT & userName =
				 * user
				 */
				s.executeQuery("SELECT * FROM Login");
				ResultSet rs = s.getResultSet();
				List<LoginDetails> list_users = new ArrayList<LoginDetails>();

				while (rs.next()) {
					LoginDetails user = new LoginDetails();
					user.loginId = rs.getInt("loginId");
					user.userName = rs.getString("userName");
					user.emailId = rs.getString("email");
					user.post = rs.getString("post");
					list_users.add(user);
				}
				request.setAttribute("AllUserList", list_users);
			}
		} catch (Exception e) {
			System.out.println(e.toString() + "\n Exception Stack: \n");
			e.printStackTrace();
		}

		request.getRequestDispatcher("/Secured/AllUser.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
