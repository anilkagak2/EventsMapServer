package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlConnection {
	/* Connection object for the database query execution */
	private Connection connection;

	protected Connection openConnection () {
		String mysqlUser = Declarations.mysqlUser;
	    String mysqlPass = Declarations.mysqlPass;
	    String url = Declarations.url;
	    try {
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, mysqlUser, mysqlPass);
		}
	    catch (SQLException e) {
			System.out.println(e.toString()+ "\n SQL Exception Stack: \n");
			e.printStackTrace();
		}
	    catch (Exception e) {
			System.out.println(e.toString()+ "\n Exception Stack: "+ e.getClass().toString() +"\n");
			e.printStackTrace();
		}
	    return connection;
	}

	protected ResultSet executeQuery (String query) {
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
	    	stmt.executeQuery(query);
	    	rs = stmt.getResultSet();
		} catch (Exception e) {
			System.out.println(e.toString()+ "\n Exception Stack: "+ e.getClass().toString() +"\n");
			e.printStackTrace();
		}
    	return rs;
	}
	
	/* Close server connection. */
    protected void closeConnection () {
		if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed");
            } catch (Exception e) {
            	System.out.println(e.toString()+ "\n Exception Stack: \n");
                e.printStackTrace();
            }
        }
	}
    
}
