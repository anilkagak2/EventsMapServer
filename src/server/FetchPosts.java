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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FetchPosts
 */
/*
 * Fetches the posts to be shown in the Login.jsp & Register.jsp 
 * */
@WebServlet("/FetchPosts")
public class FetchPosts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
    private int loginId=0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchPosts() {
        super();
        // TODO Auto-generated constructor stub
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
	
	protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println ("In fetchposts");
		try{
			String mysqlUser = Declarations.mysqlUser;
			String mysqlPass = Declarations.mysqlPass;
			String url = Declarations.url;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, mysqlUser, mysqlPass);

			if(connection!=null){
				List<String> posts = Declarations.fetchPosts(connection);
				request.setAttribute("posts",posts);
			}
			else{
				System.out.println("Database connection failed.");
				request.getRequestDispatcher(Declarations.loginHome).forward(request, response);
				return;
			}

			closeConnection();
			request.getRequestDispatcher(Declarations.loginHome).forward(request, response);
		}catch(Exception e)
		{
			closeConnection();
			System.out.println("Fetch Details"+e.toString()+ "\n Exception Stack: \n");
			e.printStackTrace();
			request.getRequestDispatcher(Declarations.loginHome).forward(request, response);
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
