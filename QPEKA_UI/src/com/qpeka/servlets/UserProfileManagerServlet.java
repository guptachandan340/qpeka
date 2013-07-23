package com.qpeka.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.qpeka.db.Constants.LANGUAGES;
import com.qpeka.db.handler.user.UserHandler;
import com.qpeka.db.handler.user.UserProfileHandler;
import com.qpeka.managers.UserManager;

/**
 * Servlet implementation class UserProfileManagerServlet
 */
public class UserProfileManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfileManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("rType");
		
		if(action.equalsIgnoreCase("addrlang"))
		{
			UserProfileHandler.getInstance().updateRLang(request.getParameter("uid"), LANGUAGES.valueOf(request.getParameter("lang")));
			Writer wr = response.getWriter();
        	
			wr.write("{\"status\":\"success\"}");
			wr.flush();
			return;
		}
		else if(action.equalsIgnoreCase("addwlang"))
		{
			UserProfileHandler.getInstance().updateWLang(request.getParameter("uid"), LANGUAGES.valueOf(request.getParameter("lang")));
			Writer wr = response.getWriter();
        	
			wr.write("{\"status\":\"success\"}");
			wr.flush();
			return;
		}
		else if(action.equalsIgnoreCase("update"))
		{
			String req = request.getParameter("request");
			try
			{
				JSONObject reqs = new JSONObject(req);
				if(reqs.has("year") || reqs.has("month")||reqs.has("day"))
				{
					Date dt = new Date();
					if(reqs.has("year"))
						dt.setYear(reqs.getInt("year"));
					if(reqs.has("month"))
						dt.setMonth(reqs.getInt("month"));
					if(reqs.has("day"))
						dt.setDate(reqs.getInt("day"));		
					
					reqs.put("dob", dt.getTime());
				}
				
				UserManager.getInstance().updateUser(reqs);
				Writer wr = response.getWriter();
	        	
				wr.write("{\"status\":\"success\"}");
				wr.flush();
				return;
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
