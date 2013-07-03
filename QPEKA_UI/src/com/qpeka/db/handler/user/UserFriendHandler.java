package com.qpeka.db.handler.user;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.qpeka.db.book.store.MongoAccessor;

/*
 * 1) Primary key userId
 * 2) List of userIds as friends
 * 
 *
 */

public class UserFriendHandler {
	
	private static UserFriendHandler instance = new UserFriendHandler();
	private DB db = null;
	private DBCollection friends = null;
	private static final Logger logger = Logger.getLogger(UserFriendHandler.class.getName());

	private UserFriendHandler()
	{
		db = MongoAccessor.getInstance().getMongo().getDB("bookstore");
		if(!db.isAuthenticated())
			db.authenticate("qpeka", new char[]{'q','p','e','k','a'});
		friends = db.getCollection("friends");
		
		friends.createIndex(new BasicDBObject("userId", 1));

	}
	
	public static UserFriendHandler getInstance()
	{
		return instance;
	}
	
	public void addFriend(String userId, String friendId)
	{
		long start = System.currentTimeMillis();
		
		BasicDBObject dObj = new BasicDBObject();
		dObj.put("$addToSet", new BasicDBObject("friends", friendId));
		
		BasicDBObject qObj = new BasicDBObject();
		qObj.put("userId", userId);
		
		friends.update(qObj, dObj,true, false);
		
		logger.log(Level.INFO, "time(addFriend) = " + (System.currentTimeMillis() - start));
	}
	
	// is userId2 friend of userId1
	public boolean isFriend(String userId1, String userId2)
	{
		BasicDBObject projection = new BasicDBObject();
		projection.put("$elemMatch", new BasicDBObject("friends",userId2));
		
		DBCursor cursor  = friends.find(new BasicDBObject("userId",userId1));
		//DBCursor cursor  = friends.find(new BasicDBObject("userId",userId1), projection);
		
        try 
        {
            if(cursor.hasNext()) 
            {
            	BasicDBList ids = (BasicDBList)((BasicDBObject)cursor.next()).get("friends");
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
		
		DBCursor cursor = friends.find(q);
		
        try 
        {
        	 if(cursor.hasNext()) 
             {
                 BasicDBObject dObj = (BasicDBObject)cursor.next();              
                
                 return (BasicDBList)dObj.get("friends");
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
		UserFriendHandler.getInstance().addFriend("5119008472d0d2bbd6526d61", "51285c482c3d773d9757e7f3");
		UserFriendHandler.getInstance().addFriend("5119008472d0d2bbd6526d61", "51285c952c3d773d9757e7f5");
		UserFriendHandler.getInstance().addFriend("5119008472d0d2bbd6526d61", "51285cab2c3d773d9757e7f7");
		UserFriendHandler.getInstance().addFriend("5119008472d0d2bbd6526d61", "51285cc32c3d773d9757e7f9");
	}
	
}
