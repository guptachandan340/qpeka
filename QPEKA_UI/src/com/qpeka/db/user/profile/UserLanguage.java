package com.qpeka.db.user.profile;

import java.io.Serializable;


public class UserLanguage implements Serializable {

	public static final String USERID = "userid";
	public static final String LANGUAGEID = "languageid";
	public static final String TYPE = "type";

	private long userid;
	private short languageid;
	private String type;

	protected boolean useridModified = false;
	protected boolean languageidModified = false;
	protected boolean typeModified = false;
	
	public static UserLanguage instance = null;

	public UserLanguage() {
		super();
	}

	public UserLanguage(long userid, short languageid, String type) {
		super();
		this.userid = userid;
		this.languageid = languageid;
		this.type = type;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
		this.useridModified = true;
	}

	public short getLanguageid() {
		return languageid;
	}

	public void setLanguageid(short languageid) {
		this.languageid = languageid;
		this.languageidModified = true;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		this.typeModified = true;
	}

	public boolean isUseridModified() {
		return useridModified;
	}

	public void setUseridModified(boolean useridModified) {
		this.useridModified = useridModified;
	}

	public boolean isLanguageidModified() {
		return languageidModified;
	}

	public void setLanguageidModified(boolean languageidModified) {
		this.languageidModified = languageidModified;
	}

	public boolean isTypeModified() {
		return typeModified;
	}

	public void setTypeModified(boolean typeModified) {
		this.typeModified = typeModified;
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

		if (!(_other instanceof UserLanguage)) {
			return false;
		}

		final UserLanguage _cast = (UserLanguage) _other;
		if (userid != _cast.userid) {
			return false;
		}

		if (useridModified != _cast.useridModified) {
			return false;
		}

		if (languageid != _cast.languageid) {
			return false;
		}

		if (languageidModified != _cast.languageidModified) {
			return false;
		}

		if (type == null ? _cast.type != type : !type.equals(_cast.type)) {
			return false;
		}

		if (typeModified != _cast.typeModified) {
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
		_hashCode = (int) (29 * _hashCode + userid);
		_hashCode = 29 * _hashCode + (useridModified ? 1 : 0);
		_hashCode = 29 * _hashCode + (int) languageid;
		_hashCode = 29 * _hashCode + (languageidModified ? 1 : 0);
		if (type != null) {
			_hashCode = 29 * _hashCode + type.hashCode();
		}

		_hashCode = 29 * _hashCode + (typeModified ? 1 : 0);
		return _hashCode;
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("Userlanguage: ");
		ret.append(USERID + "=" + userid);
		ret.append(", " + LANGUAGEID + "=" + languageid);
		ret.append(", " + TYPE + "=" + type);
		return ret.toString();
	}
	
	public static UserLanguage getInstance() {
		return ((instance == null) ? (new UserLanguage()) : instance);
	}
}
