package com.qpeka.servlets;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.qpeka.managers.SessionManager;

/**
 * Servlet Filter implementation class AuthFilter
 */
public class AuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
//		String uid = request.getParameter("uid");
//		if(((HttpServletRequest)request).getRequestURI().endsWith("/register"))
//		{
//			if(request.getParameter("rType").equalsIgnoreCase("login"))
//			{
//				chain.doFilter(request, response);
//				return;
//			}
//		}
//		HttpSession  ses = SessionManager.getInstance().getSession(uid);
//		if(ses != null)
			chain.doFilter(request, response);
//		else 
//			response.getWriter().write("{\"status\":unauthorised}");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
