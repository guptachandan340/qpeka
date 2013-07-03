package com.qpeka.db.user.profile.type;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qpeka.db.user.User;

public class Publisher extends User {

	public static final String NAME = "name";
	public static final String ID = "_id";
	
	private String name;
	private String _id;
	
	public Publisher() {
		
	}
	
	public Publisher(String name, String _id) {
		super();
		this.name = name;
		this._id = _id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	
	public DBObject toDBObject(boolean insert)
	{
		BasicDBObject dbObj = new BasicDBObject();
		if(!insert)
			dbObj.put(ID, _id);
		
		dbObj.put(NAME, name);
		
		
		return dbObj;
	}
	
	public static Publisher getPublisherfromDBObject(BasicDBObject obj)
	{
		return new Publisher(obj.getString(NAME), obj.getString(ID));
	}
}
