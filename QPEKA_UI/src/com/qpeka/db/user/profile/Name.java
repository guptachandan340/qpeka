package com.qpeka.db.user.profile;

import java.io.Serializable;

/*
 * name : {
 firstName : "manoj",
 middleName : "R"
 lastName : "thakur"
 },
 * 
 */
public class Name implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6418849114132369831L;

	public static final String FIRSTNAME = "firstname";
	public static final String MIDDLENAME = "middlename";
	public static final String LASTNAME = "lastname";

	// These attributes maps to the firstname, middlename and lastname columns
	// of the userprofile table.
	private String firstname;
	private String middlename;
	private String lastname;

	// These attributes represents whether the above attributes has been
	// modified since being read from the database.
	protected boolean firstnameModified = false;
	protected boolean middlenameModified = false;
	protected boolean lastnameModified = false;
	
	public static Name instance = null;

	/*
	 * Constructors
	 */
	public Name() {

	}

	public Name(String firstname, String middlename, String lastname) {
		super();
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
	}

	/*
	 * Getters and setters for attributes
	 */

	public static Name getInstance() {
		return (instance == null ? (instance = new Name()) : instance);
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
		this.firstnameModified = true;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
		this.middlenameModified = true;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
		this.lastnameModified = true;
	}

	/*
	 * Getters and setters for attribute modified status
	 */
	public boolean isFirstnameModified() {
		return firstnameModified;
	}

	public void setFirstnameModified(boolean firstnameModified) {
		this.firstnameModified = firstnameModified;
	}

	public boolean isMiddlenameModified() {
		return middlenameModified;
	}

	public void setMiddlenameModified(boolean middlenameModified) {
		this.middlenameModified = middlenameModified;
	}

	public boolean isLastnameModified() {
		return lastnameModified;
	}

	public void setLastnameModified(boolean lastnameModified) {
		this.lastnameModified = lastnameModified;
	}

	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other) {
		if (_other == null) {
			return false;
		}

		if (_other == this) {
			return true;
		}

		if (!(_other instanceof Name)) {
			return false;
		}

		final Name _cast = (Name) _other;

		if (firstname == null ? _cast.firstname != firstname : !firstname
				.equals(_cast.firstname)) {
			return false;
		}

		if (firstnameModified != _cast.firstnameModified) {
			return false;
		}

		if (middlename == null ? _cast.middlename != middlename : !middlename
				.equals(_cast.middlename)) {
			return false;
		}

		if (middlenameModified != _cast.middlenameModified) {
			return false;
		}

		if (lastname == null ? _cast.lastname != lastname : !lastname
				.equals(_cast.lastname)) {
			return false;
		}

		if (lastnameModified != _cast.lastnameModified) {
			return false;
		}

		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode() {
		int _hashCode = 0;

		if (firstname != null) {
			_hashCode = 29 * _hashCode + firstname.hashCode();
		}

		_hashCode = 29 * _hashCode + (firstnameModified ? 1 : 0);
		if (middlename != null) {
			_hashCode = 29 * _hashCode + middlename.hashCode();
		}

		_hashCode = 29 * _hashCode + (middlenameModified ? 1 : 0);
		if (lastname != null) {
			_hashCode = 29 * _hashCode + lastname.hashCode();
		}

		_hashCode = 29 * _hashCode + (lastnameModified ? 1 : 0);

		return _hashCode;
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuffer ret = new StringBuffer();

		ret.append("Name: ");
		ret.append("firstname=" + firstname);
		ret.append(", middlename=" + middlename);
		ret.append(", lastname=" + lastname);

		return ret.toString();
	}

	// public DBObject toDBObject() {
	// BasicDBObject dbObj = new BasicDBObject();
	// dbObj.put(FIRSTNAME, firstname);
	// dbObj.put(MIDDLENAME, middlename);
	// dbObj.put(LASTNAME, lastname);
	//
	// return dbObj;
	// }
	//
	// public static Name getNamefromDBObject(BasicDBObject obj) {
	// return new Name(obj.getString(FIRSTNAME), obj.getString(MIDDLENAME),
	// obj.getString(LASTNAME));
	// }

	// public static void main(String[] args) {
	// BasicDBObject dbObj = new BasicDBObject();
	// dbObj.put(FIRSTNAME, "firstName");
	// dbObj.put(MIDDLENAME, "middleName");
	// dbObj.put(LASTNAME, "lastNamde");
	//
	// System.out.println(dbObj.toString());
	// }
}
