package server;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddEvent
 */
@WebServlet("/AddEvent")
public class AddEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection connection;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEvent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = "";
		int loginId = 0;
		HttpSession session = request.getSession(false);
    	if (session != null) {
    		user = session.getAttribute("user").toString();
    		loginId = Integer.parseInt(session.getAttribute("loginId").toString());
    	}
		
    	String title, content, subland;
    	int category, mainland, status, locationId;
    	Timestamp startTime, endTime;
    	
    	try {
			title = request.getParameter("title");
			content = request.getParameter("content");
			subland = request.getParameter("subland");
			category = Integer.parseInt(request.getParameter("category"));
			mainland = Integer.parseInt(request.getParameter("mainland"));
			status = Integer.parseInt(request.getParameter("status"));
			locationId = 0;
			startTime = Timestamp.valueOf(request.getParameter("starttime"));
			endTime = Timestamp.valueOf(request.getParameter("endtime"));
    	} catch (Exception e){
    		System.out.println(e.toString());
    		request.getSession().setAttribute("error", e.toString());
    		request.getRequestDispatcher("/Secured/AddEvent.jsp").forward(request, response);
    		return;
    	}
		
		System.out.println(user);
		System.out.println(title);
		System.out.println(content);
		System.out.println(subland);
		System.out.println(category);
		System.out.println(mainland);
		System.out.println(status);
		
		try{
			String mysqlUser = "root";
	        String mysqlPass = "root";
	        String url = "jdbc:mysql://localhost:3306/EventsMapServer";
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	        connection = DriverManager.getConnection(url, mysqlUser, mysqlPass);
	        if(connection!=null){
	        	Statement s = connection.createStatement();
	        	Statement s1 = connection.createStatement();
	        	s1.executeQuery("SELECT * FROM Location WHERE mainLandId = '"+mainland+"' AND subLand = '"+subland+"'");
	        	ResultSet rs = s1.getResultSet();
	        	if(!rs.next()){
	        		s.executeUpdate("INSERT INTO Location(mainLandId, subLand) VALUES ('"+mainland+"', '"+subland+"')");
	        		s.executeQuery("SELECT * FROM Location WHERE mainLandId = '"+mainland+"' AND subLand = '"+subland+"'");
	        		ResultSet rs1 = s.getResultSet();
	        		try {
	        			rs1.next();
	        			locationId = rs1.getInt("locationId");
	        		} catch (Exception e){
	        			System.out.println("Cannot insert into the database :(");
	        			closeConnection();
	        			request.getRequestDispatcher("/Events.jsp").forward(request, response);
	        			return;
	        		}
		        	rs1.close();
	        	}
	        	else{
	        		locationId = rs.getInt("locationId");
	        	}
	        	
	        	String query = "INSERT INTO Event(title, content, postedBy, categoryId, status, locationId, startTime, endTime) " +
	        			"VALUES ('"+title+"', '"+content+"', '"+loginId+"', '"+category+"', '"+status+"', '"+locationId+"', '"+startTime+"', '"+endTime+"')";
	        	System.out.println(query);
	        	s.executeUpdate(query);
	        	s.close();
	        	s1.close();
	        	rs.close();

	        	closeConnection();
	        	request.getRequestDispatcher("/Secured/AddEvent.jsp").forward(request, response);
	        	return;
	        }
	        
		}catch(Exception e){
			closeConnection();
			System.out.println(e.toString());
			request.getRequestDispatcher("/Secured/AddEvent.jsp").forward(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
