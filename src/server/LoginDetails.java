package server;

/* Login abstraction of the Login Table of the DB. */
public class LoginDetails{
	int loginId;
	String userName;
	String emailId;
	String post;
	
	public int getLoginId() {
		return loginId;
	}
	
	public String getUserName () {
		return userName;
	}
	
	public String getEmailId () {
		return emailId;
	}
	
	public String getPost () {
		return post;
	}
}