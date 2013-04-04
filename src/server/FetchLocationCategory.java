package server;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class FetchLocationCategory
 */

@WebServlet("/FetchLocationCategory")
public class FetchLocationCategory extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Connection connection;
    private String user;
    List<MainLand> mainland = new ArrayList<MainLand>();
    List<Category> category = new ArrayList<Category>();
   
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
		request.setAttribute("user", user);
        request.setAttribute("mainland", mainland);
        request.setAttribute("category", category);
        request.getRequestDispatcher("/Secured/AddEvent.jsp").forward(request, response);
	}
    
	protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			/*
			 * THIS FEATURE MAY LEAD TO TROUBLE WHEN SERVER IS UP & RUNNING
			 * BUT NEW LOCATIONS ARE ADDED & THEN mainland needs to be changed 
			 * OR
			 * BETTER APPROACH WOULD BE TO SET mainland & category in session
			 * OR 
			 * LOAD THE VARIABLES EACH TIME THE METHOD IS CALLED
			 */
			/*
			 * TODO Apply the strategy of querying the database every time request comes
			 * make mainland & category local to this function & hence everything will work as desired
			 * */
			if (!mainland.isEmpty() && !category.isEmpty()) {
				System.out.println("mainland and category not null");
				sendResponse(request, response);
				return;
			}

			HttpSession session = request.getSession(false);
			if (session != null) {
				user = session.getAttribute("user").toString();
			} else {
				// SERIOUS ERROR IN EXECUTION FLOW
				System.out.println("Serious erorr in execution flow.. session null");
				request.getRequestDispatcher("/Login.jsp").forward(request, response);
				return;
			}

			String mysqlUser = "root";
			String mysqlPass = "root";
			String url = "jdbc:mysql://localhost:3306/EventsMapServer";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, mysqlUser, mysqlPass);

			if(connection!=null){
				Statement s = connection.createStatement();
				String query = "SELECT * FROM MainLand";
				s.executeQuery(query);
				System.out.println (query);
				ResultSet rs = s.getResultSet();
				while(rs.next ())
				{
					MainLand temp = new MainLand();
					temp.mainLandId = rs.getInt("mainLandId");
					temp.mainLand = rs.getString("mainLand");
					mainland.add(temp);
				}
				rs.close();
				s.executeQuery("SELECT * FROM Category");
				rs = s.getResultSet();
				while(rs.next ())
				{
					Category temp = new Category();
					temp.categoryId = rs.getInt("categoryId");
					temp.category = rs.getString("category");
					category.add(temp);
				}

				rs.close();
				s.close();
				connection.close();
			}
			else{
				System.out.println("Database connection failed.");
				request.getRequestDispatcher("/General/Events.jsp").forward(request, response);
			}
			// List<Passenger> passengers = service.list();
			sendResponse(request, response);
		}catch(Exception e)
		{
			closeConnection();
			System.out.println(e.toString()+ "\n Exception Stack: \n");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	processRequest(request, response);    
    }
}

