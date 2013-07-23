package com.qpeka.db.book.store;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.json.JSONObject;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.qpeka.db.user.profile.type.Publisher;


public class PollHandler {
	
	private static PollHandler instance = new PollHandler();
	private DB db = null;
	private DBCollection polls = null;
	
	private PollHandler()
	{
		db = MongoAccessor.getInstance().getMongo().getDB("bookstore");
		if(!db.isAuthenticated())
			db.authenticate("qpeka", new char[]{'q','p','e','k','a'});
		polls = db.getCollection("polls");
	}
	
	public static PollHandler getInstance()
	{
		return instance;
	}
	
	public String addPoll(String poll, List<String> answers)
	{
		BasicDBObject dObj = new BasicDBObject();
		dObj.put("poll", poll);
		
		BasicDBObject ans = new BasicDBObject();
		for(String  s : answers)
			ans.put(s, new BasicDBList());
		
		dObj.put("answers", ans);
		
		WriteResult result = polls.insert(dObj, WriteConcern.SAFE);
		ObjectId id =  dObj.getObjectId("_id");
		return id.toString();
	}
	
	public void addAnswer(String id, String answer, String userId)
	{
		BasicDBObject q = new BasicDBObject();
		q.put("_id", new ObjectId(id));
		
		BasicDBObject d = new BasicDBObject();
		d.put("$addToSet", new BasicDBObject("answers."+answer, userId));
		
		polls.update(q, d, false, false, WriteConcern.SAFE);
	}
	
	public JSONObject getPollResults(String id)
	{
		JSONObject jRet = new JSONObject();
		
		BasicDBObject q = new BasicDBObject();
		q.put("_id", new ObjectId(id));
		
		DBCursor cursor = polls.find(q);
		
        try 
        {
        	jRet.put("id", id);
            if(cursor.hasNext()) 
            {
                BasicDBObject dObj = (BasicDBObject)cursor.next();
                BasicDBObject ret = (BasicDBObject)dObj.get("answers");
                
                for(String key : ret.keySet())
                {
                	jRet.put(key, ((BasicDBList)ret.get(key)).size());
                }
            }
            
            return jRet;
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
	
	
	public static void main(String[] args) {
		
//		ArrayList<String> l = new ArrayList<String>();
//		l.add("Work1");
//		l.add("Work2");
//		l.add("Work3");
//		PollHandler.getInstance().addPoll("What according to you is the best work of Agatah Christy?", l);
		
//		PollHandler.getInstance().addAnswer("51055ba4d27370d411ec42b0", "Work1", "872364921364284");
//		PollHandler.getInstance().addAnswer("51055ba4d27370d411ec42b0", "Work3", "87236492136428544");
//		PollHandler.getInstance().addAnswer("51055ba4d27370d411ec42b0", "Work2", "874921364284");
//		PollHandler.getInstance().addAnswer("51055ba4d27370d411ec42b0", "Work1", "8723641364284");
//		PollHandler.getInstance().addAnswer("51055ba4d27370d411ec42b0", "Work3", "872364921-00364284");
		System.out.println(PollHandler.getInstance().getPollResults("51055ba4d27370d411ec42b0"));
	}
	
}
