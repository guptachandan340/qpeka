package com.qpeka.db.book.store;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.qpeka.db.book.store.tuples.UserBookViewLog;


public class UserBookViewLogHandler {
	
	private static UserBookViewLogHandler instance = new UserBookViewLogHandler();
	private DB db = null;
	private DBCollection logs = null;
	private static final Logger logger = Logger.getLogger(UserBookViewLogHandler.class.getName());

	private UserBookViewLogHandler()
	{
		db = MongoAccessor.getInstance().getMongo().getDB("bookstore");
		if(!db.isAuthenticated())
			db.authenticate("qpeka", new char[]{'q','p','e','k','a'});
		logs = db.getCollection("useBookViewLog");
		
		logs.ensureIndex(new BasicDBObject(UserBookViewLog.LASTVIEWTIME, -1));
//		logs.ensureIndex(new BasicDBObject(UserBookViewLog.BOOKID, 1));

		
		BasicDBObject o = new BasicDBObject(UserBookViewLog.BOOKID, 1);
		o.put(UserBookViewLog.USERID, 1);
		
		logs.ensureIndex(o, new BasicDBObject("unique","true"));
	}
	
	public static UserBookViewLogHandler getInstance()
	{
		return instance;
	}
	
	public void addLog(UserBookViewLog log)
	{
		long start = System.currentTimeMillis();
		
		BasicDBObject dObj = new BasicDBObject();
		dObj.put("$addToSet", new BasicDBObject(UserBookViewLog.VIEWTIME, log.getViewTimes().get(0)));
		dObj.put("$set", new BasicDBObject(UserBookViewLog.LASTVIEWTIME , log.getLastViewTime()));
		
		BasicDBObject qObj = log.toDBObject(true);
		
		logs.update(qObj, dObj, true, false);
		
		logger.log(Level.INFO, "time(addLog) = " + (System.currentTimeMillis() - start));
	}
	
	public List<UserBookViewLog> getLogsForUser(String userId)
	{
		List<UserBookViewLog> list = new ArrayList<UserBookViewLog>();
		
		BasicDBObject q = new BasicDBObject();
		q.put(UserBookViewLog.USERID, userId);
		
		DBCursor cursor = logs.find(q);
		
        try 
        {
        	 while(cursor.hasNext()) 
             {
                 BasicDBObject dObj = (BasicDBObject)cursor.next();              
                 UserBookViewLog log = UserBookViewLog.getBookPageViewLogfromDBObject(dObj);
                 list.add(log);
                
             }
        	 return list;
             
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return list;
		}
        finally {
        	
            cursor.close();
        }

	}
	
	public UserBookViewLog getLogsForUser(String userId, String bookId)
	{
		
		BasicDBObject q = new BasicDBObject();
		q.put(UserBookViewLog.USERID, userId);
		q.put(UserBookViewLog.BOOKID, bookId);
		
		DBCursor cursor = logs.find(q);
		
        try 
        {
        	 if(cursor.hasNext()) 
             {
                 BasicDBObject dObj = (BasicDBObject)cursor.next();              
                 UserBookViewLog log = UserBookViewLog.getBookPageViewLogfromDBObject(dObj);
                 
                 return log;
             }
        	 else
        		 return null;
        	 
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return null;
		}
        finally {
        	
            cursor.close();
        }

	}
	
	public List<String> getLastNBooksReadForUser(String userId, int n)
	{
		List<String> toReturn = new ArrayList<String>();
		
		BasicDBObject q = new BasicDBObject();
		q.put(UserBookViewLog.USERID, userId);
		
		DBCursor cursor = logs.find(q).sort(new BasicDBObject(UserBookViewLog.LASTVIEWTIME,-1)).limit(n);
		
        try 
        {
        	 while(cursor.hasNext()) 
             {
                 BasicDBObject dObj = (BasicDBObject)cursor.next();              
                 
                 toReturn.add(dObj.getString(UserBookViewLog.BOOKID));
             }
        	 
        	 return toReturn;
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return toReturn;
		}
        finally {
        	
            cursor.close();
        }

	}
	
	public static void main(String[] args) {
//		
//		
//		for(int i = 0 ; i < 10 ; i ++)
//		{
//			BasicDBList l = new BasicDBList();
//			long x = System.currentTimeMillis() + i;
//			l.add(x);
//			UserBookViewLogHandler.getInstance().addLog(new UserBookViewLog("saj34a"+i, "1321",l,x));
//		}
		
		System.out.println(UserBookViewLogHandler.getInstance().getLastNBooksReadForUser("1321", 5));
	}
	
}
