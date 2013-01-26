package com.qpeka.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qpeka.db.book.store.BookViewLogHandler;
import com.qpeka.db.book.store.tuples.BookPageViewLog;

/**
 * Servlet implementation class ViewLogHandlerServlet
 */
public class ViewLogHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewLogHandlerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actiontype = request.getParameter("action");// logPageView , logBookMark
		if(actiontype.equalsIgnoreCase("logPageView"))
		{
			String bookId = request.getParameter("book");
			String userId = request.getParameter("user");
			String page = request.getParameter("page");
			String timestamp = request.getParameter("ts");
			
			BookViewLogHandler.getInstance().addLog(new BookPageViewLog(bookId, userId, Integer.parseInt(page), Long.parseLong(timestamp)));
			
		}
		else if(actiontype.equalsIgnoreCase("logBookMark"))
		{
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
