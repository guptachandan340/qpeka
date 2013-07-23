package com.qpeka.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.qpeka.db.Constants.CATEGORY;
import com.qpeka.db.Constants.SECTION;
import com.qpeka.db.Constants.TYPE;
import com.qpeka.db.book.store.WorksHandler;
import com.qpeka.db.book.store.tuples.Work;
import com.qpeka.db.handler.user.AuthorHandler;

/**
 * Servlet implementation class WorkInfoServlet
 */
public class WorkInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String actiontype = request.getParameter("actionType");
		int start = (request.getParameter("start") != null?Integer.parseInt(request.getParameter("start") ):-1);
		int end = (request.getParameter("end") != null?Integer.parseInt(request.getParameter("end") ):-1);
		//work of the day
		if(actiontype.equalsIgnoreCase("workOfDay"))
		{
			TYPE type = TYPE.valueOf(request.getParameter("type"));
			Work w = WorksHandler.getInstance().getWorkOfTheDayByType(type);
			JSONObject jsonResp = new JSONObject();
			
			try {
				jsonResp = new JSONObject(w.toDBObject(false).toString());
				jsonResp.put("authorDetails", AuthorHandler.getInstance().getAuthor(w.getAuthorId()).toDBObject(false).toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(w != null)
				response.getWriter().write(jsonResp.toString());
			else
				response.getWriter().write("{\"error\":\"No work found\"}");
		}
		//featured work
		else if(actiontype.equalsIgnoreCase("featuredWork"))
		{
			Work w = WorksHandler.getInstance().getFeauredWorkByType(TYPE.BOOK);
			JSONObject jsonResp = new JSONObject();
			
			try {
				jsonResp = new JSONObject(w.toDBObject(false).toString());
				jsonResp.put("authorDetails", AuthorHandler.getInstance().getAuthor(jsonResp.getString("authorId")).toDBObject(false).toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(w != null)
				response.getWriter().write(jsonResp.toString());
			else
				response.getWriter().write("{\"error\":\"No work found\"}");
		}
		//recommended work
		else if(actiontype.equalsIgnoreCase("recommendedWork"))
		{
			TYPE type = TYPE.valueOf(request.getParameter("type"));
			List<Work> w = WorksHandler.getInstance().getWorksByType(type,start,end);
			if(w != null && w.size() > 0)
			{
				JSONArray arr = new JSONArray();
				for(Work work : w)
				{
					try {
						arr.put(new JSONObject(work.toDBObject(false).toString()));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				response.getWriter().write(arr.toString());
			}
			else
				response.getWriter().write("{\"error\":\"No work found\"}");
		}
		else if(actiontype.equalsIgnoreCase("getLibraryContent"))
		{
			JSONArray arr = new JSONArray();
			TYPE type = TYPE.valueOf(request.getParameter("type"));
			CATEGORY category = CATEGORY.valueOf(request.getParameter("category"));
			SECTION section = SECTION.valueOf(request.getParameter("section"));
			List<Work> lst = null;
			if(category == CATEGORY.ALL)
			{
				lst = WorksHandler.getInstance().getWorksByType(type,start,end);
			}
			else
			{
				lst = WorksHandler.getInstance().getWorksByTypeCategory(type, category, start, end);
			}
			
			if(lst != null && lst.size() > 0)
			{
				for(Work work : lst)
				{
					try {
						
						JSONObject jsonResp = new JSONObject(work.toDBObject(false).toString());
						jsonResp.put("authorDetails", AuthorHandler.getInstance().getAuthor(jsonResp.getJSONObject("authorId").getString("$oid")).toDBObject(false).toString());
						arr.put(jsonResp);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				response.getWriter().write(arr.toString());
			}
			else
				response.getWriter().write("{\"error\":\"No work found\"}");
		}
		else if(actiontype.equalsIgnoreCase("getbook"))
		{
			String id = request.getParameter("id");
			Work w = WorksHandler.getInstance().getWork(id);
			BasicDBObject bdo = (BasicDBObject)w.toDBObject(false);
			bdo.put("author", AuthorHandler.getInstance().getAuthor(bdo.getString(Work.AUTHORID)).toDBObject(false));
			response.getWriter().write(bdo.toString());
			return;
		}
		else if(actiontype.equalsIgnoreCase("getbookbycategory"))
		{
			String cat = request.getParameter("category");
			List<Work> w = WorksHandler.getInstance().getWorksByCategory(CATEGORY.valueOf(cat),start,end);
			BasicDBList list = new BasicDBList();
			for(Work wi : w)
			{
				BasicDBObject bdo = (BasicDBObject)wi.toDBObject(false);
				bdo.put("author", AuthorHandler.getInstance().getAuthor(bdo.getString(Work.AUTHORID)).toDBObject(false));
				list.add(bdo);
			}
			response.getWriter().write(list.toString());
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
