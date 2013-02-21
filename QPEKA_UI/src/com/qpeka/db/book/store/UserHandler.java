package com.qpeka.db.book.store;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.bson.types.ObjectId;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.qpeka.db.book.store.tuples.Author;
import com.qpeka.db.book.store.tuples.Constants.AUTHORTYPE;
import com.qpeka.db.book.store.tuples.Constants.CATEGORY;
import com.qpeka.db.book.store.tuples.User;

public class UserHandler {

	private static UserHandler instance = new UserHandler();
	private DB db = null;
	private DBCollection users = null;
	
	private UserHandler()
	{
		db = MongoAccessor.getInstance().getMongo().getDB("bookstore");
		if(!db.isAuthenticated())
			db.authenticate("qpeka", new char[]{'q','p','e','k','a'});
		users = db.getCollection("users");
	}
	
	public static UserHandler getInstance()
	{
		return instance;
	}
	
	public String addUser(User user)
	{
		
		BasicDBObject dObj = (BasicDBObject)user.toDBObject(true);
		WriteResult result = users.insert(dObj, WriteConcern.SAFE);
		ObjectId id =  dObj.getObjectId("_id");
		
		if(user.isWriter())
		{
			Set<CATEGORY> category = new HashSet<CATEGORY>();
			
			Author author = new Author(id.toString(), user.getName(), user.getGender(), user.getDob(), user.getNationality(), user.getImageFile(),
					user.getDesc(), "" , category, AUTHORTYPE.LEVEL1);
			
			AuthorHandler.getInstance().addUserAsAuthor(author);
		}
		return id.toString();
	}
	
	public String addUser(User user, Set<CATEGORY> genre, AUTHORTYPE type , String infoLink)
	{
		
		BasicDBObject dObj = (BasicDBObject)user.toDBObject(true);
		WriteResult result = users.insert(dObj, WriteConcern.SAFE);
		ObjectId id =  dObj.getObjectId("_id");
		
		if(user.isWriter())
		{
			
			Author author = new Author(id.toString(), user.getName(), user.getGender(), user.getDob(), user.getNationality(), user.getImageFile(),
					user.getDesc(), infoLink , genre, type);
			
			AuthorHandler.getInstance().addUserAsAuthor(author);
		}
		
		return id.toString();
	}
	
	public void updateUser(User user)
	{
		BasicDBObject q = new BasicDBObject();
		q.put(User.ID, new ObjectId(user.get_id()));
		
		users.update(q, new BasicDBObject("$set" , user.toDBObject(true)), true, false, WriteConcern.SAFE);
	}
	
	public void updateUser(JSONObject userAttrs)
	{
		try
		{
			BasicDBObject q = new BasicDBObject();
			q.put(User.ID, new ObjectId(userAttrs.getString("id")));
			
			BasicDBObject bdobj = new BasicDBObject();
			Iterator<String> i = userAttrs.keys();
			while(i.hasNext())
			{
				String key = i.next();
				if(!key.equalsIgnoreCase("id"))
					bdobj.put(key, userAttrs.getString(key));
				
				if(userAttrs.get(key) instanceof JSONObject)
				{
					BasicDBObject bsubobj = new BasicDBObject();
					Iterator<String> j = ((JSONObject)userAttrs.get(key)).keys();
					
					while(j.hasNext())
					{
						String skey = j.next();					
						bsubobj.put(skey, ((JSONObject)userAttrs.get(key)).getString(skey));
					}
					
					bdobj.put(key, bsubobj);
				}
			}
			
			users.update(q, new BasicDBObject("$set" , bdobj), true, false, WriteConcern.SAFE);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public User getUser(String userId)
	{
		BasicDBObject q = new BasicDBObject();
		q.put(User.ID, new ObjectId(userId));
		
		DBCursor cursor = users.find(q);
		
        try 
        {
            if(cursor.hasNext()) 
            {
                BasicDBObject dObj = (BasicDBObject)cursor.next();
                User user = User.getUserfromDBObject(dObj);
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
	
	public User getUserByUserName(String userName)
	{
		BasicDBObject q = new BasicDBObject();
		q.put(User.USERNAME, userName);
		
		DBCursor cursor = users.find(q);
		
        try 
        {
            if(cursor.hasNext()) 
            {
                BasicDBObject dObj = (BasicDBObject)cursor.next();
                User user = User.getUserfromDBObject(dObj);
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
	
}
