package com.qpeka.db.book.store;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;


public class WorkEncryptionHandler {
	
	private static WorkEncryptionHandler instance = new WorkEncryptionHandler();
	private DB db = null;
	private DBCollection keys = null;
	
	private WorkEncryptionHandler()
	{
		db = MongoAccessor.getInstance().getMongo().getDB("bookstore");
		if(!db.isAuthenticated())
			db.authenticate("qpeka", new char[]{'q','p','e','k','a'});
		keys = db.getCollection("encryption");
	}
	
	public static WorkEncryptionHandler getInstance()
	{
		return instance;
	}
	
	public void addKey(String workId, String key)
	{
		BasicDBObject dObj = new BasicDBObject();
		dObj.put("workId", workId);
		dObj.put("key", key);
		WriteResult result = keys.insert(dObj, WriteConcern.SAFE);
	}
	
	public String getKey(String id)
	{
		BasicDBObject q = new BasicDBObject();
		q.put("workId", new ObjectId(id));
	
		DBCursor cursor = keys.find(q);
        try 
        {
            if(cursor.hasNext()) 
            {
                BasicDBObject dObj = (BasicDBObject)cursor.next();
                String key = dObj.getString("key");
                return key;
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
