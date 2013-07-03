package com.qpeka.api;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.qpeka.db.book.store.MongoAccessor;

public class AuthHandler {

	private static AuthHandler instance = new AuthHandler();
	private DB db = null;
	private DBCollection authUsers = null;
	private static final Logger logger = Logger.getLogger(AuthHandler.class.getName());

	private AuthHandler()
	{
		db = MongoAccessor.getInstance().getMongo().getDB("auth");
		authUsers = db.getCollection("authUserData");

	}
	
	public static AuthHandler getInstance()
	{
		if(instance  == null)
			instance = new AuthHandler();
		return instance;
	}

	public void addUserAuth(String username , String password , String type , String id)
	{
		
		long start = System.currentTimeMillis();
		BasicDBObject dObj = new BasicDBObject();
		dObj.put("username", username);
		dObj.put("password", md5(password));
		dObj.put("type", type);
		dObj.put("_id", id);
		
		WriteResult result = authUsers.insert(dObj, WriteConcern.SAFE);
		logger.log(Level.INFO, "time(addUserAuth) = " + (System.currentTimeMillis() - start));
	}
	
	public boolean authenticate(String username , String password , String type)
	{
		BasicDBObject q = new BasicDBObject();
		q.put("username", username);
		q.put("password", md5(password));
		q.put("type",type);
		
		DBCursor cursor = authUsers.find(q);
		
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
	
	public static String md5(String input) {
        
        String md5 = null;
         
        if(null == input) return null;
         
        try {
             
        //Create MessageDigest object for MD5
        MessageDigest digest = MessageDigest.getInstance("MD5");
         
        //Update input string in message digest
        digest.update(input.getBytes(), 0, input.length());
 
        //Converts message digest value in base 16 (hex)
        md5 = new BigInteger(1, digest.digest()).toString(16);
 
        } catch (NoSuchAlgorithmException e) {
 
            e.printStackTrace();
        }
        return md5;
    }
	public static void main(String[] args) {
	
		String x = md5("manoj");
		String y = md5("manoj");
		
		System.out.println(x + " " + y + " " + x.equalsIgnoreCase(y));
	}
}
