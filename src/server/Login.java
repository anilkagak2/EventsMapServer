package server;

import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/*
 * Checks the authenticity of the User for the post,email & redirects accordingly
 * */
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Connection connection;
	private String user;
	private String email;
	private String post;
	private int loginId;
	
	/*
	 * Initialize these 3 arrays from use data from database. 
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
	
	// Redirect to user's Home
	protected void redirectToDashBoard (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			// TODO: Should loginId be of type long or int 
			if(loginId == Declarations.adminId) {
				request.getRequestDispatcher("/Secured/Admin.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/General/Events.jsp").forward(request, response);
			}
		} catch (Exception e){
			System.out.println("Some error occurred.. go n have fun.. :P");
			System.out.println(e.toString()+ "\n Exception Stack: \n");
	        e.printStackTrace();
			request.getRequestDispatcher("/General/Events.jsp").forward(request, response);
			return;
		}
	}
	
	// Redirect to Login Screen
	protected void redirectToLoginHome (HttpServletRequest request, HttpServletResponse response)
		throws IOException {
		try {
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		} catch (Exception e){
			System.out.println(e.toString()+ "\n Exception Stack: \n");
	        e.printStackTrace();
		}
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
 
        	List<String> posts = null;
        try {
        	posts = Declarations.fetchPosts(null);
        } catch (Exception e) {
        	System.out.println("error..!!" );
        	System.out.println(e.toString()+ "\n Exception Stack: \n");
            e.printStackTrace();
            Declarations.errorNotFound(response);
            return;
        }
        
        try {
        	/* Use previous session, if it exists. */
        	HttpSession session = request.getSession(false);
        	if ( (session != null) && (session.getAttribute("user") != null)
        			&& (session.getAttribute("loginId") != null)) {
        		user = session.getAttribute("user").toString();
        		email = session.getAttribute("email").toString();
        		post = session.getAttribute("post").toString();
        		System.out.println("loginId "+ session.getAttribute("loginId").toString());
        		loginId = Integer.parseInt(session.getAttribute("loginId").toString());
        		redirectToDashBoard (request, response);
        		return;
        	}
        	
        	/* No Previous Session */
        	email = request.getParameter ("email");
        	post = request.getParameter ("post");
        	loginId = -1;		// ERROR
        	String pass = request.getParameter("pass");
        	String final_hash = "";
        	if (!Declarations.isValidEmail(email) || pass.equals("") || post.isEmpty()) {
        		System.out.println ("Invalid email or password entries.\n");
        		String error = "Error: Email or Password or Post entries are not valid";
            	request.setAttribute("error", error);
            	request.setAttribute("posts", posts);
        		request.getRequestDispatcher(Declarations.loginHome).forward(request, response);
        		return;
        	}

            try{
                final_hash = hash(pass);
                System.out.println("Password hash : " + final_hash);
            }
                catch(Exception e){
                	System.out.println("error..!!" );
                	System.out.println(e.toString()+ "\n Exception Stack: \n");
                    e.printStackTrace();
                    Declarations.errorNotFound(response);
                    return;
            }
            
            // Create Connection
            String mysqlUser = Declarations.mysqlUser;
            String mysqlPass = Declarations.mysqlPass;
            String url = Declarations.url;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, mysqlUser, mysqlPass);
            
            System.out.println("Checking your identity.."+ email+" at post "+ post +"\n");

            // Execute Queries by checking passHash
            if (connection != null) {
              String query = "SELECT * FROM Login where email = ? AND post= ?";
              PreparedStatement s = connection.prepareStatement(query);
              s.setString(1, email);
              s.setString(2, post);
              System.out.println (query);
              System.out.println (s.toString());
              s.executeQuery();
              ResultSet rs = s.getResultSet();
              System.out.println (query);
              boolean found = false;
              while (rs.next()) {
	                String pass_hash = rs.getString("passwdHash");
	                System.out.println("final_hash " + final_hash);
	                System.out.println("pass_hash " + pass_hash);
	                if (final_hash.equals(pass_hash)) {
	                	loginId = rs.getInt("loginId");
	                	user = rs.getString("userName");
	                	session = request.getSession(true);
	
	                	// TODO use session object instead of passed user data
	                	session.setAttribute("user", user);
	                	session.setAttribute("email", email);
	                	session.setAttribute("post", post);
	                	session.setAttribute("loginId", loginId);
	                	session.removeAttribute("events");
	                	System.out.println("login  Id is "+loginId);
	                	found = true;
	                	
	                	// NOT USEFUL --> will go into server log & not to the user
	                	System.out.println ("You are authenticated now.\n"+ user +
	                			" Please proceed further\n");
	                	
	                	query = "Select E.eventId, E.content, E.title, E.startTime,E.modifiedTime, M.mainLand, L.subLand, E.endTime, " +
	        					"C.category, E.status FROM Event E, Location L, Category C, MainLand M WHERE E.postedBy = ? " +
	        					" AND L.locationId = E.locationId AND M.mainLandId = L.mainLandId AND C.categoryId = E.categoryId";
	                	PreparedStatement s1 = connection.prepareStatement(query);
	                	s1.setInt(1, loginId);
	                	
	                	System.out.println(query);
	                	System.out.println(s.toString());
	                	s1.executeQuery();
	                	ResultSet rs1 = s1.getResultSet();
	                	boolean eventsEmpty = true;
	                	List<EventDetail> events = new ArrayList<EventDetail>();
	                	while(rs1.next())
	                	{
	                		if (eventsEmpty) eventsEmpty = false;
	                		System.out.println ("GOt a record matching");
	                		EventDetail event = new EventDetail();
	                		event.eventId = rs1.getLong("eventId");
	                		event.title = rs1.getString("title");
	                		event.content = rs1.getString("content");
	                		event.startTime = rs1.getTimestamp("startTime");
	                		event.modifiedTime = rs1.getTimestamp("modifiedTime");		// Newly Introduced
	                		event.endTime = rs1.getTimestamp("endTime");
	                		event.category = rs1.getString("category");
	                		event.status = rs1.getString("status");
	                		event.location = rs1.getString("mainLand") + " (" +rs1.getString("subLand")+ ")";
	             		    events.add(event);
	                	}
	                 	
	                	rs1.close ();
	                	s1.close ();
	                	session.setAttribute("events", events);

	                	session.setAttribute("posts", posts);
	                	
	                	break;
	                }
                }
                // close the result set
                rs.close();
                s.close();
                
                // no cache
          /*    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                response.setHeader("Pragma", "no-cache"); 	// HTTP 1.0.
                response.setDateHeader("Expires", 0); 		// Proxies.
             */   
                closeConnection ();
                System.out.println("Response.");
                if (!found) {
                	System.out.println ("Invalid email or password entries.\n");
            		String error = "Error: No user with this (email, password ,post) tuple \n" +
            				"Please pay attention to the post field [as of now autocomplete is not supported on it :(]";
                	request.setAttribute("error", error);
                	request.setAttribute("posts", posts);
            		request.getRequestDispatcher(Declarations.loginHome).forward(request, response);
                	System.out.println("Home.");
                	return;
                }
                else {
                	System.out.println("Dash.");
                	redirectToDashBoard(request, response);
                	return;
                }
            }
            
            else System.out.println("Cannot connect to the database\n");
        } catch (Exception e) {
        	System.out.println(e.toString()+ "\n Exception Stack: \n");
            e.printStackTrace();
        	request.setAttribute("posts", posts);
    		request.getRequestDispatcher(Declarations.loginHome).forward(request, response);
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

