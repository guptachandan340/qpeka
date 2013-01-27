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


public class QuizHandler {
	
	private static QuizHandler instance = new QuizHandler();
	private DB db = null;
	private DBCollection quizes = null;
	
	private QuizHandler()
	{
		db = MongoAccessor.getInstance().getMongo().getDB("bookstore");
		if(!db.isAuthenticated())
			db.authenticate("qpeka", new char[]{'q','p','e','k','a'});
		quizes = db.getCollection("quizes");
	}
	
	public static QuizHandler getInstance()
	{
		return instance;
	}
	
	public String addQuiz(String quiz, List<String> answers, String answer)
	{
		BasicDBObject dObj = new BasicDBObject();
		dObj.put("quiz", quiz);
		
		BasicDBObject ans = new BasicDBObject();
		for(String  s : answers)
			ans.put(s, new BasicDBList());
		
		dObj.put("answers", ans);
		dObj.put("answer", answer);
		
		WriteResult result = quizes.insert(dObj, WriteConcern.SAFE);
		ObjectId id =  dObj.getObjectId("_id");
		return id.toString();
	}
	
	public void addAnswer(String id, String answer, String userId)
	{
		BasicDBObject q = new BasicDBObject();
		q.put("_id", new ObjectId(id));
		
		BasicDBObject d = new BasicDBObject();
		d.put("$addToSet", new BasicDBObject("answers."+answer, userId));
		
		quizes.update(q, d, false, false, WriteConcern.SAFE);
	}
	
	public JSONObject getQuizResults(String id)
	{
		JSONObject jRet = new JSONObject();
		
		BasicDBObject q = new BasicDBObject();
		q.put("_id", new ObjectId(id));
		
		DBCursor cursor = quizes.find(q);
		
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
//		QuizHandler.getInstance().addQuiz("What according to you is the best work of Agatah Christy?", l, "Work3");
		
//		QuizHandler.getInstance().addAnswer("51055f57d2732aa47677f827", "Work1", "872364921364284");
//		QuizHandler.getInstance().addAnswer("51055f57d2732aa47677f827", "Work3", "87236492136428544");
//		QuizHandler.getInstance().addAnswer("51055f57d2732aa47677f827", "Work2", "874921364284");
//		QuizHandler.getInstance().addAnswer("51055f57d2732aa47677f827", "Work1", "8723641364284");
//		QuizHandler.getInstance().addAnswer("51055f57d2732aa47677f827", "Work3", "872364921-00364284");
		System.out.println(QuizHandler.getInstance().getQuizResults("51055f57d2732aa47677f827"));
	}
	
}
