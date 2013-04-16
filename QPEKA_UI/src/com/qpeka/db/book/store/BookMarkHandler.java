package com.qpeka.db.book.store;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.qpeka.db.book.store.tuples.BookPageViewLog;

/*
 * {
 * 		"userId":"1264381bx1825ga321",
 * 		"bookmarks" : {
 * 				"rey3482368419624317":[{
 * 					"chId":12,
 * 					"sectionId":32,
 * 					"ts":2973562973
 * 				},...]
 * 		}
 * } 
 */

public class BookMarkHandler {
	
	private static BookMarkHandler instance = new BookMarkHandler();
	private DB db = null;
	private DBCollection bookmarks = null;
	private static final Logger logger = Logger.getLogger(BookMarkHandler.class.getName());

	private BookMarkHandler()
	{
		db = MongoAccessor.getInstance().getMongo().getDB("bookstore");
		if(!db.isAuthenticated())
			db.authenticate("qpeka", new char[]{'q','p','e','k','a'});
		bookmarks = db.getCollection("bookmarks");
		
	}
	
	public static BookMarkHandler getInstance()
	{
		return instance;
	}

	public void addBookMark(String userId, String bookId, int chapterId, int sectionId, long ts)
	{
		BasicDBObject mark = new BasicDBObject();
		mark.put("chapterId", chapterId);
		mark.put("sectionId", sectionId);
		mark.put("ts", ts);
		BasicDBObject q = new BasicDBObject("userId", userId);
		BasicDBObject mod = new BasicDBObject();
		mod.put("bookmarks."+bookId, mark);
		bookmarks.update(q, new BasicDBObject("$addToSet",mod), true, false);
	}
	
	public JSONArray getBookMarksForUserForBook(String userId,String bookId)
	{
		JSONArray listToReturn = new JSONArray();
		
		DBCursor cursor = bookmarks.find(new BasicDBObject("userId", userId), new BasicDBObject("bookmarks."+bookId, 1));
		
        try 
        {
        	 while(cursor.hasNext()) 
             {
        		 listToReturn.put(cursor.next());
             }
             return listToReturn;
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return listToReturn;
		}
        finally {
            cursor.close();
        }
	}
	
	public static void main(String[] args) {
//		new BookMarkHandler().getInstance().addBookMark("manoj", "2739423xbg8", 12, 23, System.currentTimeMillis());
//		new BookMarkHandler().getInstance().addBookMark("manoj", "2739423xbg9", 12, 23, System.currentTimeMillis());
//		new BookMarkHandler().getInstance().addBookMark("manoj", "2739423xbg2", 12, 23, System.currentTimeMillis());
//		new BookMarkHandler().getInstance().addBookMark("manoj", "2739423xbg3", 12, 23, System.currentTimeMillis());
//		
		System.out.println(new BookMarkHandler().getInstance().getBookMarksForUserForBook("manoj","2739423xbg3"));
	}
}
