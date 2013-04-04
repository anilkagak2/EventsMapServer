package server;
import java.sql.*;

public class EventDetail {
	public long eventId; 	
	public Timestamp startTime; 	
	public Timestamp endTime;
	public Timestamp modifiedTime; 
	public String title;
	public String content;	
	public  String location;
	public String category;			
	public String status;
	
	// New attributes
	public String mainLand;
	public String subLand;
	
	public String getSubLand (){
		return subLand;
	}
	
	public String getMainLand (){
		return mainLand;
	}
	
	public String getCategory (){
		return category;
	}
	
	public String getLocation (){
		return location;
	}
	
	public long getEventId (){
		return eventId;
	}
	
	public Timestamp getStartTime (){
		return startTime;
	}
	
	public Timestamp getEndTime (){
		return endTime;
	}
	
	public Timestamp getModifiedTime (){
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
