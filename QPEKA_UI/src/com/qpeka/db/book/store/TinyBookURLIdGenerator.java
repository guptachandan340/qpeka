package com.qpeka.db.book.store;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;

public class TinyBookURLIdGenerator {

	private static TinyBookURLIdGenerator instance = new TinyBookURLIdGenerator();
	private DB db = null;
	private DBCollection mapping = null;
	
	private static final String BOOKID = "bookId";
	private static final String TINYURLID = "_id";
	
	private TinyBookURLIdGenerator()
	{
		db = MongoAccessor.getInstance().getMongo().getDB("bookstore");
		if(!db.isAuthenticated())
			db.authenticate("qpeka", new char[]{'q','p','e','k','a'});
		mapping = db.getCollection("tinyUrlMapping");
	}
	
	public static TinyBookURLIdGenerator getInstance()
	{
		return instance;
	}
	
	public boolean addMapping(String bookId , String tinyUrlId)
	{
		try
		{
			BasicDBObject dObj = new BasicDBObject();
			dObj.put(TINYURLID, tinyUrlId);
			dObj.put(BOOKID, bookId);
			WriteResult result = mapping.insert(dObj, WriteConcern.SAFE);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String getBookId(String tinyURLId)
	{
		BasicDBObject q = new BasicDBObject();
		q.put(TINYURLID, new ObjectId(tinyURLId));
		
		
		DBCursor cursor = mapping.find(q);
		
        try 
        {
            if(cursor.hasNext()) 
            {
                BasicDBObject dObj = (BasicDBObject)cursor.next();
                
                return dObj.getString(BOOKID);
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
	
	public String getTinyURLId(String bookId)
	{
		BasicDBObject q = new BasicDBObject();
		q.put(BOOKID, new ObjectId(bookId));
		
		
		DBCursor cursor = mapping.find(q);
		
        try 
        {
            if(cursor.hasNext()) 
            {
                BasicDBObject dObj = (BasicDBObject)cursor.next();
                
                return dObj.getString(TINYURLID);
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
