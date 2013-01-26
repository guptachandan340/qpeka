package com.qpeka.db.book.store;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.qpeka.db.book.store.tuples.BookPageViewLog;

/*
 * TBD in future
 * {
 * 		bookId:2396425427348029534243f,
 * 		viewLog : {
 * 			pageId:4,
 * 			pageLog:{
 * 				userId:2735648723547238,
 * 				viewTime:{1625397123193,....}
 * 			}
 *		}
 * }
 */

public class BookViewLogHandler {
	
	private static BookViewLogHandler instance = new BookViewLogHandler();
	private DB db = null;
	private DBCollection bookViewLogs = null;
	
	private BookViewLogHandler()
	{
		db = MongoAccessor.getInstance().getMongo().getDB("bookstore");
		db.authenticate("manoj.thakur66@gmail.com", new char[]{'A','v','a','y','a','1','2','3'});
		bookViewLogs = db.getCollection("bookViewLog");
		
		BasicDBObject compoundIndex = new BasicDBObject(BookPageViewLog.BOOKID, 1);
		compoundIndex.put(BookPageViewLog.USERID, 1);
		
		bookViewLogs.createIndex(new BasicDBObject(BookPageViewLog.BOOKID, 1));
		bookViewLogs.createIndex(new BasicDBObject(BookPageViewLog.USERID, 1));
		bookViewLogs.createIndex(compoundIndex);
		
	}
	
	public static BookViewLogHandler getInstance()
	{
		return instance;
	}
	
	public void addLog(BookPageViewLog log)
	{
		BasicDBObject dObj = (BasicDBObject)log.toDBObject(true);
		WriteResult result = bookViewLogs.insert(dObj, WriteConcern.SAFE);
	}
	
	public List<BookPageViewLog> getBookPageViewLogGivenBook(String bookId)
	{
		List<BookPageViewLog> listToReturn = new ArrayList<BookPageViewLog>();
		
		BasicDBObject q = new BasicDBObject();
		q.put(BookPageViewLog.BOOKID, bookId);
		
		DBCursor cursor = bookViewLogs.find(q);
		
        try 
        {
        	 while(cursor.hasNext()) 
             {
                 BasicDBObject dObj = (BasicDBObject)cursor.next();
                 BookPageViewLog log = BookPageViewLog.getBookPageViewLogfromDBObject(dObj);
                 listToReturn.add(log);
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
	
	public List<BookPageViewLog> getBookPageViewLogGivenUserId(String userId)
	{
		List<BookPageViewLog> listToReturn = new ArrayList<BookPageViewLog>();
		
		BasicDBObject q = new BasicDBObject();
		q.put(BookPageViewLog.USERID, userId);
		
		DBCursor cursor = bookViewLogs.find(q);
		
        try 
        {
        	 while(cursor.hasNext()) 
             {
                 BasicDBObject dObj = (BasicDBObject)cursor.next();
                 BookPageViewLog log = BookPageViewLog.getBookPageViewLogfromDBObject(dObj);
                 listToReturn.add(log);
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
}