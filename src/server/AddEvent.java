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
	private static final String insert="INSERT";
	private static final String update="UPDATE";
	private static final String delete="DELETE";
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
    	String eventId = "-1";
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
    	} catch (Exception e){
    		System.out.println(e.toString()+ "\n Exception Stack: \n");
            e.printStackTrace();
    		request.setAttribute("error", e.getMessage());
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
			String mysqlUser = Declarations.mysqlUser;
	        String mysqlPass = Declarations.mysqlPass;
	        String url = Declarations.url;
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	        connection = DriverManager.getConnection(url, mysqlUser, mysqlPass);
	        if(connection!=null){
	        	locationId = getLocationId(mainland, subland);

	        	// TODO Think about where should the action parameter be in the form
	        	// --> in session or in request ?
	        	String query = "";
	        	if (action.equals(insert)) {
	        		query= "INSERT INTO Event(title, content, postedBy, categoryId, status, locationId, startTime, endTime) " +
	        			"VALUES ('"+title+"', '"+content+"', '"+loginId+"', '"+category+"', '"+status+"', '"+locationId+"', '"+startTime+"', '"+endTime+"')";
	        	}
	        	else if (action.equals(update)) {
	        		eventId = request.getParameter("eventId");
	        		System.out.println(eventId);
	        		query= "UPDATE Event SET title='"+ title +"', content='"+content+"', postedBy='"+loginId+"'," +
	        				" categoryId='"+category+"', status='"+status+"', locationId='"+locationId+"', startTime='"+startTime+"', endTime='" +endTime+"'"
		        			+"WHERE eventId='"+eventId+"'";
	        	}
	        	else if (action.equals(delete)) {
	        		eventId = request.getParameter("eventId");
	        		System.out.println(eventId);
	        		query= "DELETE FROM Event WHERE eventId='"+eventId+"'";
	        	}
	        	else {
	        		String error = "INVALID QUERY";
	        		request.setAttribute("error", error);
	        		closeConnection();
		        	request.getRequestDispatcher("FetchLocationCategory").forward(request, response);
		        	return;
	        	}

	        	String[] enum_status = {"ONGOING", "SCHEDULED", "CANCELLED", "COMPLETED"};
	        	System.out.println(query);
	        	// Query successfully executed
	        	if (modificationQuery(query)) {
	        		EventDetail event = new EventDetail();
	        		event.title= title;
                    event.content=content;
                    event.postedBy=loginId;
    				event.startTime=startTime;
    				event.endTime=endTime;
    				List<EventDetail> events = (List<EventDetail>) request.getSession().getAttribute("events");
    				//PreparedStatement s = null;
    				if (action.equals(insert)) {
    					query = "Select E.eventId, M.mainLand, C.category, E.status, E.modifiedTime" +
        					" FROM Event E, Location L, Category C, MainLand M WHERE " +
        					" E.title='"+ title +"' AND E.content='"+content+"' AND E.postedBy='"+loginId+"' AND " + "E.categoryId='"+category+
        					"' AND E.status='"+enum_status[status-1]+ "' AND E.startTime='"+startTime+"' AND E.endTime='" +endTime +"' AND E.locationId='"+locationId+"'"+
        					" AND L.locationId = E.locationId AND M.mainLandId = L.mainLandId AND C.categoryId = E.categoryId";
    					
    					/*query = "Select E.eventId, M.mainLand, C.category, E.status, E.modifiedTime" +
            					" FROM Event E, Location L, Category C, MainLand M WHERE " +
            					" E.title='"+ title +"' AND E.content='"+content+"' AND E.postedBy='"+loginId+"' AND " + "E.categoryId='"+category+
            					"' AND E.status='"+enum_status[status-1]+ "' AND E.startTime='"+startTime+"' AND E.endTime='" +endTime +"' AND E.locationId='"+locationId+"'"+
            					" AND L.locationId = E.locationId AND M.mainLandId = L.mainLandId AND C.categoryId = E.categoryId";
    					s = connection.prepareStatement(query);
    					s.setString (1, title);
    					s.setString (2, content);
    					s.setInt (3, loginId);
    					s.setInt (4, category);
    					s.setString (5, enum_status[status-1]);
    					s.setTimestamp(6, startTime);
    					s.setTimestamp(7, endTime);
    					s.setInt(8, locationId);*/
    				}
    				
    				else if (action.equals(update)) {
    					query = "Select E.eventId, M.mainLand, C.category, E.status, E.modifiedTime " +
            					" FROM Event E, Location L, Category C, MainLand M WHERE " +
            					" E.eventId='"+ eventId+ "' AND L.locationId = E.locationId AND M.mainLandId = L.mainLandId " +
            					" AND C.categoryId = E.categoryId";
    				}

            		System.out.println (query);
        			Statement s = connection.createStatement();
        			s.executeQuery(query);
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
                    
	        		if (action.equals(insert)) {
        				events.add(event);
	        		} else if (action.equals(update)) {
	        			//ListIterator<EventDetail> it = events.listIterator();
    					System.out.println ("Size "+events.size ());
	        			for (int i=0; i<events.size (); i++) {
	        				EventDetail e = events.get(i);
	        				if (e.eventId == event.eventId) {
	        					System.out.println ("Setting the new event instead of current.");
	        					events.set (i, event);
	        					System.out.println ("new eventid " + events.get(i).eventId);
	        					break;
	        				}
	        			}
	        			/*while (it.hasNext()) {
	        				EventDetail e = it.next ();
	        				if (e.eventId == event.eventId) {
	        					System.out.println ("Setting the new event instead of current.");
	        					System.out.println (e.eventId);
	        					System.out.println (event.eventId);
	        					it.set(event);
	        					System.out.println ("After setting");
	        					
	        					break;
	        				}
	        			}*/
	        			/*int index = events.indexOf(event);
	        			if (index == -1) {
	        				String error = "Error: could not update the event in events list for this session";
	                    	request.setAttribute("error", error);
	                		request.getRequestDispatcher(Declarations.addEventHome).forward(request, response);
	                		return;
	        			}
	        			else events.set(index, event); */
	        		}
	        		
	        		session.setAttribute("events", events);
	        		closeConnection();
		        	request.getRequestDispatcher("FetchLocationCategory").forward(request, response);
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
		catch (SQLException e) {
		    	System.out.println("Error : " + e.toString());
		    	e.printStackTrace();
		    	String error = e.getMessage();
		    	System.out.println (e.getMessage());
				request.setAttribute("error", error);
		        request.getRequestDispatcher(Declarations.addEventHome).forward(request, response);
		        return;
		}
		catch(Exception e) {
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
