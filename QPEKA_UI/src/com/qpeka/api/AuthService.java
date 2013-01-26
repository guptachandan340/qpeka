package com.qpeka.api;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthService {

	private static AuthService instance = null;
	
	
	private AuthService()
	{
		
	}
	
	public static AuthService getInstance()
	{
		if(instance == null)
			instance = new AuthService();
		
		return instance;
		
	}
	
	public void authenticate(HttpServletRequest request , HttpServletResponse response ,String uName , String pwd , String type)
	{
		boolean isAuth = AuthHandler.getInstance().authenticate(uName, pwd, type);
		
		if(isAuth)
		{
			try 
			{
				if(request.getParameter("method").equalsIgnoreCase("login"))
				{
					HttpSession session = request.getSession();
					session.setAttribute("uName", uName);
					session.setAttribute("type", type);
					session.setAttribute("uid", type);
					session.setMaxInactiveInterval(60 * 60);
					
					RequestDispatcher rDisp = request.getRequestDispatcher("user.jsp");
					
					rDisp.forward(request, response);
				}
				else if(request.getParameter("method").equalsIgnoreCase("logout"))
				{
					HttpSession session = request.getSession();
					session.invalidate();
					
					RequestDispatcher rDisp = request.getRequestDispatcher("index.jsp");
					
					rDisp.forward(request, response);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
