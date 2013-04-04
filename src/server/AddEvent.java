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
  
    /* LocationId corresponding to mainland,subland pair. */
    protected int getLocationId (int mainland, String subland) {
    	int locationId = 0;
    	
    	try {
    		Statement s = connection.createStatement();
	    	s.executeQuery("SELECT * FROM Location WHERE mainLandId = '"+mainland+"' AND subLand = '"+subland+"'");
	    	ResultSet rs = s.getResultSet();
	    	if(!rs.next()) {
	    		s.executeUpdate("INSERT INTO Location(mainLandId, subLand) VALUES ('"+mainland+"', '"+subland+"')");
	    		s.executeQuery("SELECT * FROM Location WHERE mainLandId = '"+mainland+"' AND subLand = '"+subland+"'");
	    		ResultSet rs1 = s.getResultSet();
	    	
	    		rs1.next();
	    		locationId = rs1.getInt("locationId");
	        	rs1.close();
	    	}
	    	else locationId = rs.getInt("locationId");

	    	rs.close ();
	    	s.close ();
    	} catch (Exception e){
			System.out.println("Cannot insert into the database :(");
			System.out.println(e.toString()+ "\n Exception Stack: \n");
	        e.printStackTrace();
			closeConnection();
		//	request.getRequestDispatcher("/Events.jsp").forward(request, response);
		}
    	System.out.println ("");
    	return locationId;
    }
    
    /* Execute insert query. */
    protected boolean modificationQuery (String query) {
    	boolean result = true;
    	
    	try {
	    	Statement s = connection.createStatement();
	    	System.out.println(query);
	    	s.executeUpdate(query);
	    	System.out.println("done query");
	    	s.close();
    	}
    	catch (Exception e) {
    		result = false;
    		System.out.println(e.toString()+ "\n Exception Stack: \n");
	        e.printStackTrace();
    	}
    	return result;
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = "";
		int loginId = 0;
		String action="";
		HttpSession session = request.getSession(false);
    	if (session != null) {
    		user = session.getAttribute("user").toString();
    		loginId = Integer.parseInt(session.getAttribute("loginId").toString());
    	}
		
    	String title, content, subland;
    	String eventId;
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
			action = request.getParameter("action");
			
			//action = "INSERT";
    	} catch (Exception e){
    		System.out.println(e.toString()+ "\n Exception Stack: \n");
            e.printStackTrace();
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
		System.out.println(startTime);
		System.out.println(endTime);
		
		System.out.println("action");
		System.out.println(action);
		
		try{
			String mysqlUser = "root";
	        String mysqlPass = "root";
	        String url = "jdbc:mysql://localhost:3306/EventsMapServer";
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	        connection = DriverManager.getConnection(url, mysqlUser, mysqlPass);
	        if(connection!=null){
	        	locationId = getLocationId(mainland, subland);

	        	// TODO Think about where should the action parameter be in the form
	        	// --> in session or in request ?
	        	String query = "";
	        	if (action.equals("INSERT")) {
	        		query= "INSERT INTO Event(title, content, postedBy, categoryId, status, locationId, startTime, endTime) " +
	        			"VALUES ('"+title+"', '"+content+"', '"+loginId+"', '"+category+"', '"+status+"', '"+locationId+"', '"+startTime+"', '"+endTime+"')";
	        	}
	        	else if (action.equals("UPDATE")) {
	        		eventId = request.getParameter("eventId");
	        		System.out.println(eventId);
	        		query= "UPDATE Event SET title='"+ title +"', content='"+content+"', postedBy='"+loginId+"'," +
	        				" categoryId='"+category+"', status='"+status+"', locationId='"+locationId+"', startTime='"+startTime+"', endTime='" +endTime+"'"
		        			+"WHERE eventId='"+eventId+"'";
	        	}
	        	else if (action.equals("DELETE")) {
	        		eventId = request.getParameter("eventId");
	        		System.out.println(eventId);
	        		query= "DELETE FROM Event WHERE eventId='"+eventId+"'";
	        	}
	        	else {
	        		String error = "INVALID QUERY";
	        		request.setAttribute("error", error);
	        		closeConnection();
		        	//request.getRequestDispatcher("/Secured/AddEvent.jsp").forward(request, response);
		        	request.getRequestDispatcher("FetchLocationCategory").forward(request, response);
		        	return;
	        	}

	        	System.out.println(query);
	        	// Query successfully executed
	        	if (modificationQuery(query)) {
	        		closeConnection();
		        	//request.getRequestDispatcher("/Secured/AddEvent.jsp").forward(request, response);
		        	request.getRequestDispatcher("FetchLocationCategory").forward(request, response);
		        	return;
	        	}
	        	// Query Failed
	        	else System.out.println ("Cannot insert some problem occurred.\n");
	        } 
		} catch(Exception e) {
			closeConnection();
			System.out.println(e.toString()+ "\n Exception Stack: \n");
	        e.printStackTrace();
	        
	        if (loginId == Declarations.adminId)
	        	request.getRequestDispatcher("/Secured/Admin.jsp").forward(request, response);
	        else
	        	request.getRequestDispatcher("/General/Events.jsp").forward(request, response);
			return;
		}
	}

}
