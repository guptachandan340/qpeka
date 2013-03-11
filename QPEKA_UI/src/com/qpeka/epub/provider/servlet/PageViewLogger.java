package com.qpeka.epub.provider.servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class PageViewLogger
 */
public class PageViewLogger extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Pattern p = Pattern.compile("<page=[0-9]*>");
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageViewLogger() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			String pgRd = request.getParameter("log");
			Matcher m = p.matcher(pgRd);
			while(m.find())
			{
				System.out.println(m.group());
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
