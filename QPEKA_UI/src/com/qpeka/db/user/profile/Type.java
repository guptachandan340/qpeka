package com.qpeka.db.user.profile;

import java.io.Serializable;

public class Type implements Serializable {

	public static final String TYPEID = "typeid";
	public static final String TYPE = "type";
	public static final String TYPENAME = "typename";

	// These attributes maps to the columns of the usertype table.
	private short typeid;
	private String type;
	private String typename;

	// These attributes represents whether the above attributes has been
	// modified since being read from the database.
	protected boolean typeidModified = false;
	protected boolean typeModified = false;
	protected boolean typenameModified = false;

	/*
	 * Constructors
	 */
	public Type() {
		super();
	}

	public Type(String type, String typename) {
		super();
		this.type = type;
		this.typename = typename;
	}

	public Type(short typeid, String type, String typename) {
		super();
		this.typeid = typeid;
		this.type = type;
		this.typename = typename;
	}

	/*
	 * Getters and setters for attributes
	 */
	public short getTypeid() {
		return typeid;
	}

	public void setTypeid(short typeid) {
		this.typeid = typeid;
		this.typeidModified = true;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		this.typeModified = true;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
		this.typenameModified = true;
	}

	/*
	 * Getters and setters for attribute modified status
	 */
	public boolean isTypeidModified() {
		return typeidModified;
	}

	public void setTypeidModified(boolean typeidModified) {
		this.typeidModified = typeidModified;
	}

	public boolean isTypeModified() {
		return typeModified;
	}

	public void setTypeModified(boolean typeModified) {
		this.typeModified = typeModified;
	}

	public boolean isTypenameModified() {
		return typenameModified;
	}

	public void setTypenameModified(boolean typenameModified) {
		this.typenameModified = typenameModified;
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

		if (!(_other instanceof Type)) {
			return false;
		}

		final Type _cast = (Type) _other;
		if (typeid != _cast.typeid) {
			return false;
		}

		if (typeidModified != _cast.typeidModified) {
			return false;
		}

		if (type == null ? _cast.type != type : !type.equals(_cast.type)) {
			return false;
		}

		if (typeModified != _cast.typeModified) {
			return false;
		}

		if (typename == null ? _cast.typename != typename : !typename
				.equals(_cast.typename)) {
			return false;
		}

		if (typenameModified != _cast.typenameModified) {
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
		_hashCode = 29 * _hashCode + (int) typeid;
		_hashCode = 29 * _hashCode + (typeidModified ? 1 : 0);
		if (type != null) {
			_hashCode = 29 * _hashCode + type.hashCode();
		}

		_hashCode = 29 * _hashCode + (typeModified ? 1 : 0);
		if (typename != null) {
			_hashCode = 29 * _hashCode + typename.hashCode();
		}

		_hashCode = 29 * _hashCode + (typenameModified ? 1 : 0);
		return _hashCode;
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("Type: ");
		ret.append(TYPEID + "=" + typeid);
		ret.append(", " + TYPE + "=" + type);
		ret.append(", " + TYPENAME + "=" + typename);
		
		return ret.toString();
	}

}
