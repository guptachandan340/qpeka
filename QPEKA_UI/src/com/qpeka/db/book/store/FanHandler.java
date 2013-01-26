package com.qpeka.db.book.store;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

/*
 * 1) Primary key userId
 * 2) List of userIds as friends
 * 
 *
 */

public class FanHandler {
	
	private static FanHandler instance = new FanHandler();
	private DB db = null;
	private DBCollection fans = null;
	private static final Logger logger = Logger.getLogger(FanHandler.class.getName());

	private FanHandler()
	{
		db = MongoAccessor.getInstance().getMongo().getDB("bookstore");
		if(!db.isAuthenticated())
			db.authenticate("qpeka", new char[]{'q','p','e','k','a'});
		fans = db.getCollection("fans");
		
		fans.createIndex(new BasicDBObject("userId", 1));

	}
	
	public static FanHandler getInstance()
	{
		return instance;
	}
	
	public void addFan(String userId, String fan)
	{
		long start = System.currentTimeMillis();
		
		BasicDBObject dObj = new BasicDBObject();
		dObj.put("$addToSet", new BasicDBObject("fans", fan));
		
		BasicDBObject qObj = new BasicDBObject();
		qObj.put("userId", userId);
		
		fans.update(qObj, dObj,true, false);
		
		logger.log(Level.INFO, "time(addFriend) = " + (System.currentTimeMillis() - start));
	}
	
	// is userId2 friend of userId1
	public boolean isFan(String userId1, String userId2)
	{
		BasicDBObject projection = new BasicDBObject();
		projection.put("$elemMatch", new BasicDBObject("fans",userId2));
		
		DBCursor cursor  = fans.find(new BasicDBObject("userId",userId1));
		//DBCursor cursor  = friends.find(new BasicDBObject("userId",userId1), projection);
		
        try 
        {
            if(cursor.hasNext()) 
            {
            	BasicDBList ids = (BasicDBList)((BasicDBObject)cursor.next()).get("fans");
            	return ids.contains(userId2);
            	//return true;
            }
            else
            	return false;
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return false;
		}
        finally {
            cursor.close();
        }
	}
	
	
	
	public BasicDBList getFriends(String userId)
	{
		
		BasicDBObject q = new BasicDBObject();
		q.put("userId", userId);
		
		DBCursor cursor = fans.find(q);
		
        try 
        {
        	 if(cursor.hasNext()) 
             {
                 BasicDBObject dObj = (BasicDBObject)cursor.next();              
                
                 return (BasicDBList)dObj.get("fans");
             }
        	 else
        		return new BasicDBList(); 
             
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return new BasicDBList();
		}
        finally {
            cursor.close();
        }

	}
	
	
	
	public static void main(String[] args) {
		
		//UserFriendHandler.getInstance().addFriend("1234", "4353");
		System.err.println(FanHandler.getInstance().getFriends("123"));
	}
	
}
