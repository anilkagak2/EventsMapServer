package server;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
	
	/* Possible actions to be performed by this class. */
	private static final String insert="INSERT";
	private static final String update="UPDATE";
	private static final String delete="DELETE";

	/* Connection object. */
    Connection connection;   

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEvent() {
        super();
    }

    /* Closes the connection, if the object is not null. */
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
  
    /* LocationId corresponding to mainland,subland pair.
     * If the mainland,subland pair exists then pick the locationId corresponding to that
     * otherwise insert a new entry in location table.
     *  */
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
    
    /* Execute update queries. */
    protected boolean modificationQuery (PreparedStatement s) {
    	boolean result = true;
    	if (s == null) return false;
    	try {
	    	s.executeUpdate();
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
	 * 
	 * Adds a new event or updates an existing one, to the user's account &
	 *  updates the events list in session for the user
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = "";
		int loginId = 0;
		String action="";
		String returnHome = Declarations.homePage(request);
		HttpSession session = request.getSession(false);
    	if (session != null) {
    		user = session.getAttribute("user").toString();
    		loginId = Integer.parseInt(session.getAttribute("loginId").toString());
    	}
		
    	String title, content, subland;
    	String eventId = "-1";
    	int category, mainland, status, locationId;
    	Timestamp startTime, endTime;
    	
    	/* Extract the parameters from the JSP form.
    	 * And handle the errors gently. 
    	 * */
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
			
    	} catch (Exception e){
    		System.out.println(e.toString()+ "\n Exception Stack: \n");
            e.printStackTrace();
    		request.setAttribute("error", e.getMessage());
    		request.getRequestDispatcher(Declarations.addEventHome).forward(request, response);
    		return;
    	}
		
    	/*
    	 * Check the validity of the parameters obtained
    	 * */
    	String validityError = "";
    	if (title.isEmpty()) validityError += "<br /> Title not valid \n";
		if (content.isEmpty()) validityError += "<br /> Content not valid \n";
		if (subland.isEmpty()) validityError += "<br /> Subland not valid \n";		// Check if it's really required
		if (category < 1) validityError += "<br /> Category not valid \n";
		if (mainland < 1) validityError += "<br /> Mainland not valid \n";
		if (status < 1) validityError += "<br /> Status not valid \n";
		java.util.Date modified = new java.util.Date();
		Timestamp modifiedTime = new Timestamp(modified.getTime());
		if (startTime.before(modifiedTime)) validityError += "<br /> Start time of the event should be atleast" +
				" the current time \n";
		if (endTime.before(startTime)) validityError += "<br /> End time is before the start time of the event \n";
		if (action.isEmpty()) validityError += "<br /> Something went wrong in submission of form \n";
    	    	
		// check the validity error string for any errors
		if (!validityError.isEmpty()) {
			System.out.println (validityError);
    		request.setAttribute("error", validityError);
    		request.getRequestDispatcher(Declarations.addEventHome).forward(request, response);
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
			String mysqlUser = Declarations.mysqlUser;
	        String mysqlPass = Declarations.mysqlPass;
	        String url = Declarations.url;
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	        connection = DriverManager.getConnection(url, mysqlUser, mysqlPass);
	        if(connection!=null){
	        	locationId = getLocationId(mainland, subland);

	        	/* According to action field in the form prepare the query & execute 
	        	 * via */
	        	PreparedStatement s = null;
	        	String query = "";
	        	if (action.equals(insert)) {
	        		query= "INSERT INTO Event(title, content, postedBy, categoryId, status, locationId, startTime, endTime) " +
		        			"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        		s = connection.prepareStatement(query);
	        		s.setString (1, title);
					s.setString (2, content);
					s.setInt (3, loginId);
					s.setInt (4, category);
					s.setInt (5, status);
					s.setInt (6, locationId);
					s.setTimestamp(7, startTime);
					s.setTimestamp(8, endTime);
	        	}
	        	else if (action.equals(update)) {
	        		eventId = request.getParameter("eventId");
	        		System.out.println(eventId);
	        		query= " UPDATE Event SET title=?, content=?, postedBy=?, categoryId=?, status=?, locationId=?, startTime=?, endTime=?, modifiedTime=? " +
		        		   " WHERE eventId=? ";
	        		
	        		s = connection.prepareStatement(query);
	        		s.setString (1, title);
					s.setString (2, content);
					s.setInt (3, loginId);
					s.setInt (4, category);
					s.setInt (5, status);
					s.setInt (6, locationId);
					s.setTimestamp(7, startTime);
					s.setTimestamp(8, endTime);
					s.setTimestamp(9, modifiedTime);
					s.setInt (10, Integer.parseInt(eventId));
	        	}
	        	
	        	/* Not in USE
//        		 * */
//	        	else if (action.equals(delete)) {
//	        		eventId = request.getParameter("eventId");
//	        		System.out.println(eventId);
//	        		query= "DELETE FROM Event WHERE eventId=?";
//	        		s = connection.prepareStatement(query);
//	        		s.setInt (1, Integer.parseInt(eventId));
//	        	}
	        	else {
	        		String error = "INVALID QUERY";
	        		request.setAttribute("error", error);
	        		closeConnection();
		        	request.getRequestDispatcher("FetchLocationCategory").forward(request, response);
		        	return;
	        	}

	        	String[] enum_status = {"ONGOING", "SCHEDULED", "CANCELLED", "COMPLETED"};
	        	System.out.println(query);
	        	
	        	/* Execute the query for update or insert. */
	        	if (modificationQuery(s)) {
	        		/* Successfully executed the modification query.
	        		 * Now, get the event's details from the database & add those details in the
	        		 * session's events list.
	        		 * */
	        		EventDetail event = new EventDetail();
	        		event.title= title;
                    event.content=content;
                    event.postedBy=loginId;
    				event.startTime=startTime;
    				event.endTime=endTime;
    				List<EventDetail> events = (List<EventDetail>) request.getSession().getAttribute("events");
    				
    				if (action.equals(insert)) {
    					query = " Select E.eventId, M.mainLand, C.category, E.status, E.modifiedTime" +
            					" FROM Event E, Location L, Category C, MainLand M WHERE " +
            					" E.title= ? AND E.content= ? AND E.postedBy= ? AND E.categoryId= ? AND E.status= ?" +
            					" AND E.startTime= ? AND E.endTime= ? AND E.locationId=? "+
            					" AND L.locationId = E.locationId AND M.mainLandId = L.mainLandId AND C.categoryId = E.categoryId";
    					s = connection.prepareStatement(query);
    					s.setString (1, title);
    					s.setString (2, content);
    					s.setInt (3, loginId);
    					s.setInt (4, category);
    					s.setString (5, enum_status[status-1]);
    					s.setTimestamp(6, startTime);
    					s.setTimestamp(7, endTime);
    					s.setInt(8, locationId);
    				}

    				
    				else if (action.equals(update)) {
    					query = "Select E.eventId, M.mainLand, C.category, E.status, E.modifiedTime " +
            					" FROM Event E, Location L, Category C, MainLand M " +
            					" WHERE E.eventId= ? AND L.locationId = E.locationId AND M.mainLandId = L.mainLandId " +
            					" AND C.categoryId = E.categoryId";
    				
    					s = connection.prepareStatement(query);
    					s.setInt(1, Integer.parseInt(eventId));
    				}

            		System.out.println (query);
            		System.out.println (s.toString());
            		s.executeQuery();
                    ResultSet rs = s.getResultSet();                    
                    if (rs.next()) {
                        event.eventId = rs.getInt("eventId");
                        event.status  = rs.getString("status");
        				event.category = rs.getString("category");
        				event.modifiedTime = rs.getTimestamp("modifiedTime");
                		event.location = rs.getString("mainLand") + " (" +subland+")";
                        System.out.println("inserted event's id is " + event.eventId);
                    } else {
                    	String error = "Error: could not add the event to events list for this session";
                    	request.setAttribute("error", error);
                		request.getRequestDispatcher(Declarations.addEventHome).forward(request, response);
                		return;
                    }
                    
                    /* Update the current event's list in the sessio so as to reflect the changes
                     * be it update or insert. 
        			 * */
	        		if (action.equals(insert)) {
        				events.add(event);
	        		} else if (action.equals(update)) {
	        			ListIterator<EventDetail> it = events.listIterator();
    					System.out.println ("Size "+events.size ());
    					
    					/* This version of the loop takes O(n^2) time */
	        			/*for (int i=0; i<events.size (); i++) {
	        				EventDetail e = events.get(i);
	        				if (e.eventId == event.eventId) {
	        					System.out.println ("Setting the new event instead of current.");
	        					events.set (i, event);
	        					System.out.println ("new eventid " + events.get(i).eventId);
	        					break;
	        				}
	        			}*/
    					
    					/* Equivalent to the previous one but it takes linear time. */
	        			while (it.hasNext()) {
	        				EventDetail e = it.next ();
	        				if (e.eventId == event.eventId) {
	        					System.out.println ("Setting the new event instead of current.");
	        					System.out.println (e.eventId);
	        					System.out.println (event.eventId);
	        					it.set(event);
	        					System.out.println ("After setting");
	        					
	        					break;
	        				}
	        			}
	        			
	        			/* Should be equivalent but not tested since the time it's commented. */
	        			/*int index = events.indexOf(event);
	        			if (index == -1) {
	        				String error = "Error: could not update the event in events list for this session";
	                    	request.setAttribute("error", error);
	                		request.getRequestDispatcher(Declarations.addEventHome).forward(request, response);
	                		return;
	        			}
	        			else events.set(index, event); */
	        		}
	        		
	        		/* Replace the previous events object with the current one. */
	        		session.setAttribute("events", events);
	        		closeConnection();
	        		request.getRequestDispatcher(returnHome).forward(request, response);
		        	return;
	        	}
	        	// Query Failed
	        	else {
	        		System.out.println ("Cannot insert some problem occurred.\n");
	        		String error = "Error: could not update or insert the event in the database";
                	request.setAttribute("error", error);
            		request.getRequestDispatcher(Declarations.addEventHome).forward(request, response);
            		return;
	        	}
	        }
		}
		/* Handle the SQLException & return the appropriate error message back to user. */
		catch (SQLException e) {
		    	System.out.println("Error : " + e.toString());
		    	e.printStackTrace();
		    	String error = e.getMessage();
		    	System.out.println (e.getMessage());
				request.setAttribute("error", error);
		        request.getRequestDispatcher(Declarations.addEventHome).forward(request, response);
		        return;
		}
		/* Handle other exceptions & send back to user's home. */
		catch(Exception e) {
			closeConnection();
			System.out.println(e.toString()+ "\n Exception Stack: \n");
	        e.printStackTrace();
	        
	        if (loginId == Declarations.adminId)
	        	request.getRequestDispatcher(Declarations.adminHome).forward(request, response);
	        else
	        	request.getRequestDispatcher(Declarations.userHome).forward(request, response);
			return;
		}
	}

}
