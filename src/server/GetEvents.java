package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class GetEvents
 */
@WebServlet("/GetEvents")
public class GetEvents extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetEvents() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		// TODO Auto-generated method stub
		Calendar currenttime = Calendar.getInstance();
		Date sqldate = new Date((currenttime.getTime()).getTime());

		String mapid = request.getParameter("mapId");
		String isjson = request.getParameter("json");

		if (mapid == null)
			mapid = "";

		List<EventDetail> events = fetchEvents(
				new Timestamp(sqldate.getTime()), "", "", mapid, "");

		request.setAttribute("mapEvents", events);

		if (isjson == null) {
			String json = new Gson().toJson(events);
			out.println(json);
		} else {
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
		}
	}

	private List<EventDetail> fetchEvents(Timestamp endingAfter,
			String postedByName, String postedByPost, String mapId,
			String category) {

		List<EventDetail> listEvents = new ArrayList<EventDetail>();

		String query = "";
		
		query = "Select * FROM Event E , MainLand ML ,Login L,Category C, Location LOC WHERE E.endTime > ? ";
		if (category != "")
			query += " and C.category= ? ";
		if (mapId != "")
//			query += " and ML.mainLand= ? ";
			query += " and ML.mapId= ? ";
		if (postedByName != "")
			query += " and L.userName= ? ";
		if (postedByPost != "")
			query += " and L.post= ? ";
		query += " and C.categoryId = E.categoryId and LOC.locationId=E.locationId and ML.mainLandId=LOC.mainLandId and L.loginId=E.postedBy ORDER BY E.startTime";
		
//		query += "Select * FROM Event E , MainLand ML ,Login L,Category C, Location LOC WHERE ";
//		query += "E.endTime>'" + endingAfter + "'";
//		if (category != "")
//			query += " and C.category=" + category;
//		if (mainLand != "")
//			query += " and ML.mainLand=" + mainLand;
//		if (postedByName != "")
//			query += " and L.userName=" + postedByName;
//		if (postedByPost != "")
//			query += " and L.post=" + postedByPost;
//
//		query += " and C.categoryId = E.categoryId and LOC.locationId=E.locationId and ML.mainLandId=LOC.mainLandId and L.loginId=E.postedBy ";
		System.out.println(query);

		try {
			String mysqlUser = Declarations.mysqlUser;
			String mysqlPass = Declarations.mysqlPass;
			String url = Declarations.url;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection(url, mysqlUser, mysqlPass);
			
			if (connection != null) {
				PreparedStatement s = connection.prepareStatement(query);
				s.setTimestamp(1, endingAfter);

				/* Set the parameters now. */
				int j=2;
				if (category != "") s.setString (j++, category);
				if (mapId != "") s.setString (j++, mapId);
				if (postedByName != "") s.setString(j++, postedByName);
				if (postedByPost != "") s.setString(j++, postedByPost);
				
				s.executeQuery();
				System.out.println(s.toString());
				System.out.println(query);
				ResultSet rs = s.getResultSet();
		
				while (rs.next()) {
					EventDetail event = new EventDetail();
					event.title = rs.getString("title");
					event.categoryValue = rs.getString("Category");
					event.content = rs.getString("content");
					event.endTime = rs.getTimestamp("endTime");
					event.eventId = rs.getLong("eventId");
					event.mainLand = rs.getString("mainLand");
					event.subLand = rs.getString("subLand");
					event.mapId = rs.getString("mapId");
					event.modifiedTime = rs.getTimestamp("modifiedTime");
					event.postedByMail = rs.getString("email");
					event.postedByName = rs.getString("userName");
					event.postedByPost = rs.getString("post");
					event.startTime = rs.getTimestamp("startTime");
					event.status = rs.getString("status");
					//System.out.println("Added in json\n");
					listEvents.add(event);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listEvents;
	}
}