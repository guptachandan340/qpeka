package com.qpeka.epub.provider.servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qpeka.db.book.store.BookViewLogHandler;


/**
 * Servlet implementation class PageViewLogger
 */
public class PageViewLogger extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Pattern p = Pattern.compile("<page id=\"[0-9]*\"></page>");
	
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
			String workId = request.getParameter("wid");
			workId = (workId == null || workId.length() == 0)?"sampleWork":workId;
			String uid = request.getParameter("uid");
			uid = (uid == null || uid.length() == 0)?"anonymous":uid;
			String ts = request.getParameter("ts");
			ts = (ts == null || ts.length() == 0)?System.currentTimeMillis()+"":ts;
			request.getParameter("log");
			Matcher m = p.matcher(pgRd);
			while(m.find())
			{
				String pgId = m.group().split("id=")[1].split("><")[0].split("\"")[1];
				BookViewLogHandler.getInstance().updateLog(uid, workId, pgId, Long.parseLong(ts));
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
	
	public static void main(String[] args) {
		String x = "<page id=\"78\"></page>";
		System.out.println(x.split("id=")[1].split("><")[0].split("\"")[1]);
		
	}
}
