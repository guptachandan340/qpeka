package com.qpeka.db.book.store;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.qpeka.db.book.store.tuples.UserRating;

public class UserRatingHandler {
	
	private static UserRatingHandler instance = new UserRatingHandler();
	private DB db = null;
	private DBCollection ratings = null;
	
	private UserRatingHandler()
	{
		db = MongoAccessor.getInstance().getMongo().getDB("bookstore");
		db.authenticate("manoj.thakur66@gmail.com", new char[]{'A','v','a','y','a','1','2','3'});
		ratings = db.getCollection("ratings");
		
		ratings.createIndex(new BasicDBObject(UserRating.BOOKID, 1));
		
		BasicDBObject compoundIndex = new BasicDBObject(UserRating.BOOKID, 1);
		compoundIndex.put(UserRating.USERID, 1);
		ratings.createIndex(compoundIndex , new BasicDBObject("unique",true));
		
	}
	
	public static UserRatingHandler getInstance()
	{
		return instance;
	}
	
	public void add(UserRating rating)
	{
		BasicDBObject dObj = (BasicDBObject)rating.toDBObject();
		WriteResult result = ratings.insert(dObj, WriteConcern.SAFE);
	}
	
	public List<UserRating> getRatingGivenBook(String bookId)
	{
		List<UserRating> listToReturn = new ArrayList<UserRating>();
		
		BasicDBObject q = new BasicDBObject();
		q.put(UserRating.BOOKID, bookId);
		
		DBCursor cursor = ratings.find(q);
		
        try 
        {
        	 while(cursor.hasNext()) 
             {
                 BasicDBObject dObj = (BasicDBObject)cursor.next();
                 UserRating rating = UserRating.getUserRatingfromDBObject(dObj);
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
	
	public List<UserRating> getRatingGivenUser(String userId)
	{
		List<UserRating> listToReturn = new ArrayList<UserRating>();
		
		BasicDBObject q = new BasicDBObject();
		q.put(UserRating.USERID, userId);
		
		DBCursor cursor = ratings.find(q);
		
        try 
        {
        	 while(cursor.hasNext()) 
             {
                 BasicDBObject dObj = (BasicDBObject)cursor.next();
                 UserRating rating = UserRating.getUserRatingfromDBObject(dObj);
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