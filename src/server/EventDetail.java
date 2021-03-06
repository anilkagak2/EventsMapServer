package server;

import java.sql.*;

/* EventDetails abstraction of the Event Table of the DB. */
public class EventDetail {
	public long eventId;
	public Timestamp startTime;
	public Timestamp endTime;
	public Timestamp modifiedTime;
	public String title;
	public String content;
	public String location;
	public String category;
	public String status;

	public String categoryValue;
	public String postedByName;
	public String postedByPost;
	public String postedByMail;

	// New attributes
	public String mainLand;
	public String subLand;
	public String mapId;

	public int postedBy;

	public String getPostedByName() {
		return postedByName;
	}
	public String getPostedByPost() {
		return postedByPost;
	}
	public String getPostedByMail() {
		return postedByMail;
	}
	
	public int getPostedBy() {
		return postedBy;
	}

	public String getCategoryValue() {
		return categoryValue;
	}

	public String getsubLand() {
		return subLand;
	}

	public String getmapId() {
		return mapId;
	}

	public String getmainLand() {
		return mainLand;
	}

	public String getSubLand() {
		return subLand;
	}

	public String getMainLand() {
		return mainLand;
	}

	public String getCategory() {
		return category;
	}

	public String getLocation() {
		return location;
	}

	public long getEventId() {
		return eventId;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public Timestamp getModifiedTime() {
		return modifiedTime;
	}

	public String getTitle() {
		return title;
	}

	public String getStatus() {
		return status;
	}

	public String getContent() {
		return content;
	}
}