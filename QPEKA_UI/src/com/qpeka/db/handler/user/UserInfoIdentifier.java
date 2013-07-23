package com.qpeka.db.handler.user;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qpeka.db.user.profile.Name;

public class UserInfoIdentifier 
{
	private long _id;
	private String userName;
	private Name name;
	
	public static final String ID = "_id";
	public static final String USERNAME = "userName";
	public static final String NAME = "name";
	
	public UserInfoIdentifier()
	{
		super();
	}
	
	public UserInfoIdentifier(long _id, String userName, Name name) {
		super();
		this._id = _id;
		this.userName = userName;
		this.name = name;
	}
	
	public long get_id() {
		return _id;
	}
	public void set_id(long _id) {
		this._id = _id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	
	public DBObject toDBObject()
	{
		BasicDBObject dbObj = new BasicDBObject();
		dbObj.put(ID, _id);
		dbObj.put(NAME, name.toDBObject());
		dbObj.put(USERNAME, userName);
		
		return dbObj;
	}
	public static UserInfoIdentifier getUserInfoIdentifierfromDBObject(BasicDBObject obj)
	{
		return new UserInfoIdentifier(obj.getLong(ID), obj.getString(USERNAME),Name.getNamefromDBObject((BasicDBObject)obj.get(NAME)));
	}
}
