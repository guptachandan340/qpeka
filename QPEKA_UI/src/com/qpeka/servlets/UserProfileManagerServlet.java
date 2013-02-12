package com.qpeka.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.qpeka.db.book.store.UserAuthHandler;
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
		
		if(action.equalsIgnoreCase("update"))
		{
			String req = request.getParameter("request");
//			BufferedReader buff = request.getReader();
//			
//			req = buff.readLine();
//			
//			while(req != null && req.length() > 0)
//				req += buff.readLine();
//			
//			req = req.trim();
//			
			try
			{
				UserManager.getInstance().updateUser(new JSONObject(req));
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
