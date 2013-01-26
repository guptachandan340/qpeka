package com.qpeka.db.book.store;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.qpeka.db.book.store.tuples.UserComments;

public class UserCommentHandler {
	
	private static UserCommentHandler instance = new UserCommentHandler();
	private DB db = null;
	private DBCollection comments = null;
	
	private UserCommentHandler()
	{
		db = MongoAccessor.getInstance().getMongo().getDB("bookstore");
		db.authenticate("manoj.thakur66@gmail.com", new char[]{'A','v','a','y','a','1','2','3'});
		comments = db.getCollection("comments");
		
		comments.createIndex(new BasicDBObject(UserComments.BOOKID, 1));
		comments.createIndex(new BasicDBObject(UserComments.USERID, 1));
	}
	
	public static UserCommentHandler getInstance()
	{
		return instance;
	}
	
	public void add(UserComments comment)
	{
		BasicDBObject dObj = (BasicDBObject)comment.toDBObject(true);
		WriteResult result = comments.insert(dObj, WriteConcern.SAFE);
	}
	
	public List<UserComments> getCommentsGivenBook(String bookId)
	{
		List<UserComments> listToReturn = new ArrayList<UserComments>();
		
		BasicDBObject q = new BasicDBObject();
		q.put(UserComments.BOOKID, bookId);
		
		DBCursor cursor = comments.find(q);
		
        try 
        {
        	 while(cursor.hasNext()) 
             {
                 BasicDBObject dObj = (BasicDBObject)cursor.next();
                 UserComments rating = UserComments.getUserCommentfromDBObject(dObj);
                 listToReturn.add(rating);
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
	
	public List<UserComments> getCommentsGivenUserId(String userId)
	{
		List<UserComments> listToReturn = new ArrayList<UserComments>();
		
		BasicDBObject q = new BasicDBObject();
		q.put(UserComments.USERID, userId);
		
		DBCursor cursor = comments.find(q);
		
        try 
        {
        	 while(cursor.hasNext()) 
             {
                 BasicDBObject dObj = (BasicDBObject)cursor.next();
                 UserComments rating = UserComments.getUserCommentfromDBObject(dObj);
                 listToReturn.add(rating);
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