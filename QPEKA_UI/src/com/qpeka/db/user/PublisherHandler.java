package com.qpeka.db.user;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.qpeka.db.book.store.MongoAccessor;
import com.qpeka.db.user.profile.type.Author;
import com.qpeka.db.user.profile.type.Publisher;


public class PublisherHandler {
	
	private static PublisherHandler instance = new PublisherHandler();
	private DB db = null;
	private DBCollection publishers = null;
	
	private PublisherHandler()
	{
		db = MongoAccessor.getInstance().getMongo().getDB("bookstore");
		if(!db.isAuthenticated())
			db.authenticate("qpeka", new char[]{'q','p','e','k','a'});
		publishers = db.getCollection("publishers");
	}
	
	public static PublisherHandler getInstance()
	{
		return instance;
	}
	
	public String addPublisher(Publisher publisher)
	{
		BasicDBObject dObj = (BasicDBObject)publisher.toDBObject(true);
		WriteResult result = publishers.insert(dObj, WriteConcern.SAFE);
		ObjectId id =  dObj.getObjectId("_id");
		return id.toString();
	}
	
	public void updatePublisher(Publisher publisher)
	{
		
	}
	
	public Publisher getPublisher(String id)
	{
		BasicDBObject q = new BasicDBObject();
		q.put(Publisher.ID, new ObjectId(id));
		
		
		DBCursor cursor = publishers.find(q);
		
        try 
        {
            if(cursor.hasNext()) 
            {
                BasicDBObject dObj = (BasicDBObject)cursor.next();
                Publisher publisher = Publisher.getPublisherfromDBObject(dObj);
                
                return publisher;
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
