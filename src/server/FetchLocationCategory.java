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
	
	private boolean fetchEventToUpdate (HttpServletRequest request, int eventId) 
			throws ServletException, IOException {
		boolean ret = true;
		try {
			Statement s = connection.createStatement();
			String query = "Select E.title, E.content, E.startTime,E.modifiedTime, M.mainLand, L.subLand, E.endTime, " +
							"C.category, E.status FROM Event E, Location L, Category C, MainLand M WHERE E.eventId = '"+eventId+"'" +
							" AND L.locationId = E.locationId AND M.mainLandId = L.mainLandId AND C.categoryId = E.categoryId";
			
			s.executeQuery(query);
			System.out.println (query);
			ResultSet rs = s.getResultSet();
			EventDetail event = new EventDetail();
			event.eventId = eventId;
			if(rs.next ())
			{
				event.title = rs.getString("title");
				event.content = rs.getString("content");
				event.startTime = rs.getTimestamp("startTime");
				event.endTime = rs.getTimestamp("endTime");
				event.category = rs.getString("category");
				event.status = rs.getString("status");
			
        		event.modifiedTime = rs.getTimestamp("modifiedTime");		// Newly Introduced
        		event.status = rs.getString("status");
        		event.mainLand = rs.getString("mainLand");
        		event.subLand = rs.getString("subLand");
			} else ret=false;
			
			request.setAttribute("updateEvent", event);
			rs.close();
		} catch (Exception e){
			System.out.println(e.toString()+ "\n Exception Stack: \n");
            e.printStackTrace();
            ret = false;
		}
		return ret;
	}
	
	private void sendResponse (HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
        request.getRequestDispatcher(Declarations.addEventHome).forward(request, response);
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
			
			String action = request.getParameter("action");
			System.out.println(action);
			
			/* if (!mainland.isEmpty() && !category.isEmpty()) {
				System.out.println("mainland and category not null");
				sendResponse(request, response);
				return;
			} */

		    List<MainLand> mainland = new ArrayList<MainLand>();
		    List<Category> category = new ArrayList<Category>();
			HttpSession session = request.getSession(false);
			if (session != null) {
				user = session.getAttribute("user").toString();
			} else {
				// SERIOUS ERROR IN EXECUTION FLOW
				System.out.println("Serious erorr in execution flow.. session null");
				request.getRequestDispatcher(Declarations.loginHome).forward(request, response);
				return;
			}

			String mysqlUser = Declarations.mysqlUser;
			String mysqlPass = Declarations.mysqlPass;
			String url = Declarations.url;
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

				// UPDATE
				if (action.equals("UPDATE")) {
					int eventId = Integer.parseInt(request.getParameter("eventId"));
					System.out.println(eventId);
					if (!fetchEventToUpdate(request, eventId)) {
						System.out.println("FATAL ERROR: Update Action was set but no events to update.");
						request.getRequestDispatcher("/General/Events.jsp").forward(request, response);
						return;
					}
				}
				
				rs.close();
				s.close();
				connection.close();
				
				// set the attributes required
				request.setAttribute("user", user);
		        request.setAttribute("mainland", mainland);
		        request.setAttribute("category", category);
			}
			else{
				System.out.println("Database connection failed.");
				request.getRequestDispatcher("/General/Events.jsp").forward(request, response);
				return;
			}
			// List<Passenger> passengers = service.list();
			sendResponse(request, response);
		}catch(Exception e)
		{
			closeConnection();
			System.out.println("Fetch Location Category"+e.toString()+ "\n Exception Stack: \n");
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

