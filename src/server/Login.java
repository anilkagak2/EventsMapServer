package server;

import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Connection connection;
	private String user;
	List<EventDetail> events = new ArrayList<EventDetail>();
	
	/*
	 * Initialise these 3 arrays from use data from database. 
	 */
	public String[] status_enum = new String[4];

	public Login (){
		super();
		
		connection = null;
		// status enum
		status_enum[0] = "COMPLETED";
		status_enum[1] = "ONGOING";
		status_enum[2] = "SCHEDULED";
		status_enum[3] = "CANCELLED";
	}
	
	 /* Hash function */
	 protected String hash(String passwrd)
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
	} /* End Hash */
	
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
	
	// Redirect to user's Home
	/*protected void redirectToDashBoard (HttpServletRequest request, HttpServletResponse response)
		throws IOException {
		try {
	    	request.setAttribute("user", user);
	    	request.setAttribute("events", events);
	    	request.getRequestDispatcher("/Events.jsp").forward(request, response);
		} catch (Exception e){
			
		}
	} */
	
	protected void redirectToDashBoard (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			if(user.equals("admin")) {
				request.setAttribute("user", user);
				request.setAttribute("events",events);
				request.getRequestDispatcher("/Admin.jsp").forward(request, response);
				return;
			}
			else {
				request.setAttribute("user", user);
				request.setAttribute("events", events);
				request.getRequestDispatcher("/Events.jsp").forward(request, response);
				return;
			}
		} catch (Exception e){
			System.out.println("Some error occurred.. go n have fun.. :P");
			request.getRequestDispatcher("/Events.jsp").forward(request, response);
			return;
		}
	}
	
	// Redirect to Login Screen
	protected void redirectToLoginHome (HttpServletRequest request, HttpServletResponse response)
		throws IOException {
		try {
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		} catch (Exception e){
			
		}
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
 
        try {
        	/* Use previous session, if it exists. */
        	HttpSession session = request.getSession(false);
        	if ( (session != null) && (session.getAttribute("user") != null)) {
        		redirectToDashBoard (request, response);
        	}
        	
        	/* No Previous Session */
            user = request.getParameter("user");
            String pass = request.getParameter("pass");
            String final_hash = "";

            try{
                final_hash = hash(pass);
                System.out.println("Password hash : " + final_hash);
            }
                catch(Exception e){
                	System.out.println("error..!!" );
            }
            
            // Create Connection
            String mysqlUser = "root";
            String mysqlPass = "root";
            String url = "jdbc:mysql://localhost:3306/EventsMapServer";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, mysqlUser, mysqlPass);
            
            System.out.println("Checking your identity.."+ user +"\n");

            // Execute Queries by checking passHash
            if (connection != null) {
                Statement s = connection.createStatement();
                /* Changed the Table structure--> loginId = INT & userName = user */
                //s.executeQuery("SELECT passwdHash FROM Login where loginId = '"+ user +"'");
                s.executeQuery("SELECT * FROM Login where userName = '"+ user +"'");
                ResultSet rs = s.getResultSet();
                boolean found = false;
                while (rs.next()) {
                    String pass_hash = rs.getString("passwdHash");
                    System.out.println("final_hash " + final_hash);
                    System.out.println("pass_hash " + pass_hash);
                    if (final_hash.equals(pass_hash)) {
                    	int loginId = rs.getInt("loginId");
                    	session = request.getSession(true);

                    	// TODO use session object instead of passed user data
                    	session.setAttribute("user", user);
                    	session.setAttribute("loginId", loginId);
                    	System.out.println("");
                    	found = true;
                    	
                    	// NOT USEFULL --> will go into server log & not to the user
                    	System.out.println ("You are authenticated now.\n"+ user +
                    			" Please proceed further\n");
                    	
                    	Statement s1 = connection.createStatement();
                    	String query = "Select E.eventId, E.title, E.startTime,E.modifiedTime, M.mainLand, L.subLand, E.endTime, " +
                    					"C.category, E.status FROM Event E, Location L, Category C, MainLand M WHERE E.postedBy = '"+user+"'" +
                    					" AND L.locationId = E.locationId AND M.mainLandId = L.mainLandId AND C.categoryId = E.categoryId";
                    	System.out.println(query);
                    	s1.executeQuery(query);
                    	ResultSet rs1 = s.getResultSet();
                    	while(rs1.next())
                    	{
                    		EventDetail event = new EventDetail();
                    		event.eventId = rs1.getInt("eventId");
                    		event.title = rs1.getString("title");
                    		event.startTime = rs1.getTime("startTime");
                    		event.modifiedTime = rs1.getTime("modifiedTime");		// Newly Introduced
                    		event.endTime = rs1.getTime("endTime");
                    		event.category = rs1.getString("category");
                    		event.status = status_enum[rs1.getInt("status")];
                    		event.location = rs1.getString("mainLand") + " (" +rs1.getString("subLand")+ ")";
                 		    events.add(event);
                    	}

                    	rs1.close ();
                    	s1.close ();
                    	break;
                    }
                }
                // close the result set
                rs.close();
                s.close();
                
                closeConnection ();
                System.out.println("Response.");
                if (!found) {
                	System.out.println("Home.");
                	redirectToLoginHome(request, response);
                }
                else {
                	System.out.println("Dash.");
                	redirectToDashBoard(request, response);
                }
            }
            
            else System.out.println("Cannot connect to the database\n");
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
}
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}

