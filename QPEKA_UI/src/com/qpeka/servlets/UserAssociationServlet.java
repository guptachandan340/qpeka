package com.qpeka.servlets;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.qpeka.db.handler.user.FanHandler;
import com.qpeka.db.handler.user.UserFriendHandler;
import com.qpeka.db.handler.user.UserProfileHandler;
import com.qpeka.db.user.profile.UserProfile;

/**
 * Servlet implementation class UserAssociationServlet
 */
public class UserAssociationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAssociationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("getFriends"))
		{
			String uid = request.getParameter("uid"); 
			JSONArray json = new JSONArray();
			
			BasicDBList b = UserFriendHandler.getInstance().getFriends(uid);
			for(int i = 0 ; i < b.size();i++)
			{
				String fid = (String)b.get(i);
				UserProfile u = UserProfileHandler.getInstance().getUser(fid);
				JSONObject fJson = new JSONObject();
				try {
					fJson.put("id", fid);
					fJson.put("name", u.getName().toDBObject().toString());
					fJson.put("type", u.getType().toString());
					
					json.put(fJson);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			Writer wr = response.getWriter();
        	wr.write(json.toString()); 
        	wr.flush();
        	return;
		}
		else if(action.equalsIgnoreCase("getFans"))
		{
			String uid = request.getParameter("uid"); 
			JSONArray json = new JSONArray();
			
			BasicDBList b = FanHandler.getInstance().getFans(uid);
			for(int i = 0 ; i < b.size();i++)
			{
				String fid = (String)b.get(i);
				UserProfile u = UserProfileHandler.getInstance().getUser(fid);
				JSONObject fJson = new JSONObject();
				try {
					fJson.put("id", fid);
					fJson.put("name", u.getName().toDBObject().toString());
					fJson.put("type", u.getType().toString());
					
					json.put(fJson);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			Writer wr = response.getWriter();
        	wr.write(json.toString()); 
        	wr.flush();
        	return;
		}
	}

}
