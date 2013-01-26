package com.qpeka.test;

import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
 
/**
 * Java + MongoDB Hello world Example
 * 
 */
public class TestBooks {
	
	public static void main(String[] args) {
 
		try {
			// connect to mongoDB, ip and port number
			Mongo mongo = new Mongo("localhost", 27017);
 
			// get database from MongoDB,
			// if database doesn't exists, mongoDB will create it automatically
			DB db = mongo.getDB("testStore");
 
			// Get collection from MongoDB, database named "yourDB"
			// if collection doesn't exists, mongoDB will create it automatically
			DBCollection collection = db.getCollection("test1");
 
			// create a document to store key and value
			BasicDBObject document = new BasicDBObject();
			//document.put("_id",1234567890);
			document.put("title","Harry Potter_0");
			
			Set<BasicDBObject> op = new HashSet<BasicDBObject>();
			
			BasicDBObject testdb = new BasicDBObject();
			testdb.put("name", "manoj");
			
			BasicDBObject testdb1 = new BasicDBObject();
			testdb1.put("name", "manoj1");
			op.add(testdb1);
			op.add(testdb);
			
			document.put("testName",op);
			
			// save it into collection named "yourCollection"
			collection.insert(document);
 
			// search query
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("_id", 1234567890);
 
			// query it
			DBCursor cursor = collection.find(searchQuery);
 
			// loop over the cursor and display the retrieved result
			while (cursor.hasNext()) {
				BasicDBObject o = (BasicDBObject)cursor.next();
			
				System.out.println(o);
				
				long test1 = o.getLong("_id");
				String title = o.getString("title");
				BasicDBList n = (BasicDBList) o.get("testName");
				System.out.println(n);
			}
			
 
			System.out.println("Done");
 
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
 
	}
}
