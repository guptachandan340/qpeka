package com.qpeka.db.book.store.tuples;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class UserAuth {
	
	private String userName = "";
	private String password = "";
	private String id = "";
	
	
	public UserAuth(String userName, String password, String id) {
		super();
		this.userName = userName;
		this.password = password;
		this.id = id;
	}
	public UserAuth(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public DBObject toDBObject(boolean insert)
	{
		BasicDBObject dbObj = new BasicDBObject();
		if(!insert)
			dbObj.put("username", userName);
		dbObj.put("password", password);
		
		return dbObj;
	}
	
	public static UserAuth getUserfromDBObject(BasicDBObject obj)
	{
		return new UserAuth(obj.getString("username"), obj.getString("password"));
	}
	
}
