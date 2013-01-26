package com.qpeka.db.book.store.tuples;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/*
 * name : {
				firstName : "manoj",
				middleName : "R"
				lastName : "thakur"
		},
 * 
 */
public class Name {
	
	public static final String FIRSTNAME = "firstName";
	public static final String MIDDLENAME = "middleName";
	public static final String LASTNAME = "lastName";
	
	private String firstName ;
	private String middleName ;
	private String lastName;
	
	public Name()
	{
		
	}
	
	public Name(String firstName, String middleName, String lastName) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public DBObject toDBObject()
	{
		BasicDBObject dbObj = new BasicDBObject();
		dbObj.put(FIRSTNAME, firstName);
		dbObj.put(MIDDLENAME, middleName);
		dbObj.put(LASTNAME, lastName);
		
		return dbObj;
	}
	
	public static Name getNamefromDBObject(BasicDBObject obj)
	{
		return new Name(obj.getString(FIRSTNAME), obj.getString(MIDDLENAME),obj.getString(LASTNAME));
	}
	
	public static void main(String[] args) {
		BasicDBObject dbObj = new BasicDBObject();
		dbObj.put(FIRSTNAME, "firstName");
		dbObj.put(MIDDLENAME, "middleName");
		dbObj.put(LASTNAME, "lastNamde");
		
		System.out.println(dbObj.toString());
	}
}
