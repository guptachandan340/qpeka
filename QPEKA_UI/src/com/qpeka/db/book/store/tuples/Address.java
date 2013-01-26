package com.qpeka.db.book.store.tuples;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Address {
	
	
	public static final String CITY = "city";
	public static final String STATE = "state";
	public static final String ADDRESSLINE1 = "addressLine1";
	public static final String ADDRESSLINE2 = "addressLine2";
	public static final String ADDRESSLINE3 = "addressLine3";
	public static final String PINCODE = "pincode";
	
	private String city;
	private String state;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String pincode;
	
	public Address(String city, String state, String addressLine1,
			String addressLine2, String addressLine3, String pincode) {
		super();
		this.city = city;
		this.state = state;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressLine3 = addressLine3;
		this.pincode = pincode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getAddressLine3() {
		return addressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	public DBObject toDBObject()
	{
		BasicDBObject dbObj = new BasicDBObject();
		
		dbObj.put(STATE, state);
		dbObj.put(CITY, city);
		dbObj.put(PINCODE, pincode);
		dbObj.put(ADDRESSLINE1, addressLine1);
		dbObj.put(ADDRESSLINE2, addressLine2);
		dbObj.put(ADDRESSLINE3, addressLine3);
		
		return dbObj;
	}
}
