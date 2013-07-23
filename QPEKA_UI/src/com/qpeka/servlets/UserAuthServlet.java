package com.qpeka.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qpeka.db.handler.user.UserHandler;
import com.qpeka.db.handler.user.UserProfileHandler;
import com.qpeka.db.user.User;
import com.qpeka.db.user.profile.UserProfile;

/**
 * Servlet implementation class UserAuthServlet
 */
public class UserAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAuthServlet() {
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
		String requestype = request.getParameter("rType"); // password + user  availability 
		if(requestype.equalsIgnoreCase("login"))
		{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			UserHandler userHandler = new UserHandler();
			User user;
			
			if(!username.contains("@")) {
				// Username
				user = userHandler.authenticateByUsername(username, password);
			} else {
				// Email
				user = userHandler.authenticateByEmail(username, password);
			}
			
			boolean isAuth = (user!=null) && user.getPassword().equals(password);
			
			if(isAuth)
			{
				UserProfile u = UserProfileHandler.getInstance().getUserByUserName(uname);
				
				request.getSession().setAttribute("uid", u.get_id());
				request.getSession().setAttribute("uname", username);
				
				request.getRequestDispatcher("/myProfile.jsp?uid="+u.get_id()).forward(request, response);
				return;
			}
			else
			{
				request.getRequestDispatcher("/login.jsp?error=true").forward(request, response);
				return;
			}
		}
		else if(requestype.equalsIgnoreCase("fpwd"))
		{
			String uname = request.getParameter("username");
			String email = request.getParameter("email");
			
			User ua = UserHandler.getInstance().getUser(uname);
			
			if(ua != null)
			{
				UserProfile u = UserProfileHandler.getInstance().getUserByUserName(uname);
				
				if(u.getEmail().equals(email))
				{
					request.getRequestDispatcher("/enterNewPwd.jsp").forward(request, response);
					return;
				}
				else
				{
					request.getRequestDispatcher("/forgotpassword.jsp?error=true").forward(request, response);
					return;
				}
			}
			else
			{
				request.getRequestDispatcher("/forgotpassword.jsp?error=true").forward(request, response);
				return;
			}
		}
		else if(requestype.equalsIgnoreCase("setpwd"))
		{
			String uname = request.getParameter("username");
			String password = request.getParameter("password");
			
			User ua = UserHandler.getInstance().getUser(uname);
			
			if(ua != null)
			{
				ua.setPassword(password);
				UserHandler.getInstance().updateUser(ua);
				
				UserProfile u = UserProfileHandler.getInstance().getUserByUserName(uname);
				
				request.getSession().setAttribute("uid", u.get_id());
				request.getSession().setAttribute("uname", uname);
				
				request.getRequestDispatcher("/userHome.jsp").forward(request, response);
				return;
			}
			else
			{
				request.getRequestDispatcher("/enterNewPwd.jsp?error=true").forward(request, response);
				return;
			}
		}
	}

}
