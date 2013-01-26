package com.qpeka.db.book.store;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.qpeka.db.book.store.tuples.User;
import com.qpeka.db.book.store.tuples.UserAuth;

public class UserAuthHandler {

	private static UserAuthHandler instance = new UserAuthHandler();
	private DB db = null;
	private DBCollection users = null;
	
	private UserAuthHandler()
	{
		db = MongoAccessor.getInstance().getMongo().getDB("bookstore");
		if(!db.isAuthenticated())
			db.authenticate("qpeka", new char[]{'q','p','e','k','a'});
		
		users = db.getCollection("userAuth");
		users.ensureIndex(new BasicDBObject("username", "1"), new BasicDBObject("unique", true));
	}
	
	public static UserAuthHandler getInstance()
	{
		return instance;
	}
	
	public String addUserAuth(UserAuth userAuth)
	{
		
		BasicDBObject dObj = (BasicDBObject)userAuth.toDBObject(false);
		WriteResult result = users.insert(dObj, WriteConcern.SAFE);
		ObjectId id =  dObj.getObjectId("_id");
		return id.toString();
	}
	
	public void updateUser(UserAuth user)
	{
		BasicDBObject q = new BasicDBObject();
		q.put("username", user.getUserName());
		
		users.update(q, new BasicDBObject("$set" , user.toDBObject(true)), true, false, WriteConcern.SAFE);
	}
	
	public UserAuth getUser(String userName)
	{
		BasicDBObject q = new BasicDBObject();
		q.put("username", userName);
		
		DBCursor cursor = users.find(q);
		
        try 
        {
            if(cursor.hasNext()) 
            {
                BasicDBObject dObj = (BasicDBObject)cursor.next();
                UserAuth user = UserAuth.getUserfromDBObject(dObj);
                return user;
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
	
	public boolean getUser(String userName, String password)
	{
		BasicDBObject q = new BasicDBObject();
		q.put("username", userName);
		
		DBCursor cursor = users.find(q);
		
        try 
        {
            if(cursor.hasNext()) 
            {
            	return true;
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
}
