package com.qpeka.servlets;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.qpeka.db.book.store.BookMarkHandler;

/**
 * Servlet implementation class BookmarkServlet
 */
public class BookmarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookmarkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId  = (request.getParameter("uid") == null) ? "anonymous":request.getParameter("uid");
		String bookId  = request.getParameter("workId");
		
		JSONArray jsonarr = BookMarkHandler.getInstance().getBookMarksForUserForBook(userId, bookId);
		Writer wr = null;
		try
		{
			response.setContentType("application/json");
			wr = response.getWriter();
			wr.write(jsonarr.toString());
			wr.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{if(wr != null) wr.close();}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId  = (request.getParameter("uid") == null) ? "anonymous":request.getParameter("uid");
		String bookId  = request.getParameter("workId");
		int chId = Integer.parseInt(request.getParameter("chId"));
		int secId = Integer.parseInt(request.getParameter("secId"));
		
		BookMarkHandler.getInstance().addBookMark(userId, bookId, chId, secId, System.currentTimeMillis());
	}

}
