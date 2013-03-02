package com.qpeka.epub.provider.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import com.qpeka.epub.provider.EpubManager;

/**
 * Servlet implementation class ContentProviderServlet
 */

public class ContentProviderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ContentProviderServlet() {
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
		
		String action = request.getParameter("action");
		String id = request.getParameter("bookId");
		String resp = "";
		if(action.equalsIgnoreCase("getToc"))
		{
			resp = EpubManager.getInstance().getTableOfContents(id);
		}
		else if(action.equalsIgnoreCase("getTitle"))
		{
			resp = EpubManager.getInstance().getTitle(id);
		}
		else if(action.equalsIgnoreCase("getContent"))
		{
			String resId = request.getParameter("resId");
			resp = EpubManager.getInstance().getContentGivenResourceId(id, resId);
		}
		//resp = StringEscapeUtils.escapeHtml(resp);
		Writer wr = response.getWriter();
    	
		wr.write(resp);
		wr.flush();
    	return;
		
	}
	public static void main(String[] args) {
		System.out.println( StringEscapeUtils.escapeHtml("<p>\"MANOJ\"</p>"));
	}

}
