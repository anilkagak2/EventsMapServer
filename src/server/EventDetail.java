package server;
import java.sql.*;

public class EventDetail {
	public long eventId; 	
	public Time startTime; 	
	public Time endTime;
	public Time modifiedTime; 
	public String title;
	public String content;	
	public  String location;
	public String category;			
	public String status;
	
	public String getCategory (){
		return category;
	}
	
	public String getLocation (){
		return location;
	}
	
	public long getEventId (){
		return eventId;
	}
	
	public Time getStartTime (){
		return startTime;
	}
	
	public Time getEndTime (){
		return endTime;
	}
	
	public Time getModifiedTime (){
		return modifiedTime;
	}
	
	public String getTitle (){
		return title;
	}
	
	public String getStatus (){
		return status;
	}
	
	public String getContent (){
		return content;
	}
}
