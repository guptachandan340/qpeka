package com.qpeka.db.user;

import java.io.Serializable;

import com.qpeka.db.Constants;
import com.qpeka.managers.user.UserManager;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5342974486315750933L;

	public static final String PROFILEID = "userid";
	public static final String PENNAME = "penname";
	public static final String PASSWORD = "password";
	public static final String EMAIL = "email";
	public static final String CREATED = "created";
	public static final String LASTACCESS = "lastaccess";
	public static final String LASTLOGIN = "lastlogin";
	public static final String STATUS = "status";
	public static final String TYPE = "type";
	public static final String TIMEZONE = "timezone";

	// These attributes maps to the columns of the user table.
	private long userid = 0;
	private String penname = "";
	private String password = "";
	private String email = "";
	/**
	 * Time when user is created
	 */
	private long created = 0;
	/**
	 * Last time user has accessed his/her qpeka account
	 */
	private long lastaccess = 0;
	/**
	 * Last time user has logged in to qpeka
	 */
	private long lastlogin = 0;
	/**
	 * Status of a user specifies following things. 0 => created (new user, not
	 * yet logged in), 1 => active (User active on qpeka, including first time
	 * login), 2 => passive (User is infrequent on qpeka), 3 => blocked (user
	 * blocked by qpeka), 4 => deleted (user account has been deleted)
	 */
	private short status = 0;

	/**
	 * Type of user 0 => Authenticated 1 => Qpeka Admin 2 => Apeka Manager
	 */
	private short type = 0;
	/**
	 * Users timezone.
	 */
	private String timezone = "";
	// private String language = "";
	private static User instance = null;
	// These attributes represents whether the above attributes has been
	// modified since being read from the database.
	protected boolean useridModified = false;
	protected boolean pennameModified = false;
	protected boolean passwordModified = false;
	protected boolean emailModified = false;
	protected boolean createdModified = false;
	protected boolean lastaccessModified = false;
	protected boolean lastloginModified = false;
	protected boolean statusModified = false;
	protected boolean typeModified = false;
	protected boolean timezoneModified = false;

	// protected boolean languageModified = false;
	public static User getInstance() {
		return (instance == null ? (instance = new User()) : instance);
	}
	/*
	 * Constructors
	 */
	public User() {
		super();
	}

	public User(String penname, String password, String email, long created,
			String timezone) { // , String language
		super();
		this.penname = penname;
		this.password = password;
		this.email = email;
		this.created = created;
		this.timezone = timezone;
		// this.language = language;
	}

	public User(long userid, String penname, String password) {
		super();
		this.userid = userid;
		this.penname = penname;
		this.password = password;
	}

	public User(String penname, String password) {
		super();
		this.penname = penname;
		this.password = password;
	}

	/*
	 * Getters and setters for attributes
	 */
	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
		this.useridModified = true;
	}

	public String getPenname() {
		return penname;
	}

	public void setPenname(String penname) {
		this.penname = penname;
		this.pennameModified = true;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		this.passwordModified = true;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		this.emailModified = true;
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
		this.createdModified = true;
	}

	public long getLastaccess() {
		return lastaccess;
	}

	public void setLastaccess(long lastaccess) {
		this.lastaccess = lastaccess;
		this.lastaccessModified = true;
	}

	public long getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(long lastlogin) {
		this.lastlogin = lastlogin;
		this.lastloginModified = true;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
		this.statusModified = true;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
		this.typeModified = true;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
		this.timezoneModified = true;
	}

	/*
	 * Getters and setters for attribute modified status
	 */
	public boolean isUseridModified() {
		return useridModified;
	}

	public void setUseridModified(boolean useridModified) {
		this.useridModified = useridModified;
	}

	public boolean isPennameModified() {
		return pennameModified;
	}

	public void setPennameModified(boolean pennameModified) {
		this.pennameModified = pennameModified;
	}

	public boolean isPasswordModified() {
		return passwordModified;
	}

	public void setPasswordModified(boolean passwordModified) {
		this.passwordModified = passwordModified;
	}

	public boolean isEmailModified() {
		return emailModified;
	}

	public void setEmailModified(boolean emailModified) {
		this.emailModified = emailModified;
	}

	public boolean isCreatedModified() {
		return createdModified;
	}

	public void setCreatedModified(boolean createdModified) {
		this.createdModified = createdModified;
	}

	public boolean isLastaccessModified() {
		return lastaccessModified;
	}

	public void setLastaccessModified(boolean lastaccessModified) {
		this.lastaccessModified = lastaccessModified;
	}

	public boolean isLastloginModified() {
		return lastloginModified;
	}

	public void setLastloginModified(boolean lastloginModified) {
		this.lastloginModified = lastloginModified;
	}

	public boolean isStatusModified() {
		return statusModified;
	}

	public void setStatusModified(boolean statusModified) {
		this.statusModified = statusModified;
	}

	public boolean isTypeModified() {
		return typeModified;
	}

	public void setTypeModified(boolean typeModified) {
		this.typeModified = typeModified;
	}

	public boolean isTimezoneModified() {
		return timezoneModified;
	}

	public void setTimezoneModified(boolean timezoneModified) {
		this.timezoneModified = timezoneModified;
	}

	/*
	 * Other Methods
	 */
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

		if (!(_other instanceof User)) {
			return false;
		}

		final User _cast = (User) _other;
		if (userid != _cast.userid) {
			return false;
		}

		if (useridModified != _cast.useridModified) {
			return false;
		}

		if (penname == null ? _cast.penname != penname : !penname
				.equals(_cast.penname)) {
			return false;
		}

		if (pennameModified != _cast.pennameModified) {
			return false;
		}

		if (password == null ? _cast.password != password : !password
				.equals(_cast.password)) {
			return false;
		}

		if (passwordModified != _cast.passwordModified) {
			return false;
		}

		if (email == null ? _cast.email != email : !email.equals(_cast.email)) {
			return false;
		}

		if (emailModified != _cast.emailModified) {
			return false;
		}

		if (created != _cast.created) {
			return false;
		}

		if (createdModified != _cast.createdModified) {
			return false;
		}

		if (lastaccess != _cast.lastaccess) {
			return false;
		}

		if (lastaccessModified != _cast.lastaccessModified) {
			return false;
		}

		if (lastlogin != _cast.lastlogin) {
			return false;
		}

		if (lastloginModified != _cast.lastloginModified) {
			return false;
		}

		if (status != _cast.status) {
			return false;
		}

		if (statusModified != _cast.statusModified) {
			return false;
		}

		if (type != _cast.type) {
			return false;
		}

		if (typeModified != _cast.typeModified) {
			return false;
		}

		if (timezone == null ? _cast.timezone != timezone : !timezone
				.equals(_cast.timezone)) {
			return false;
		}

		if (timezoneModified != _cast.timezoneModified) {
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
		long _hashCode = 0;

		_hashCode = 29 * _hashCode + userid;
		_hashCode = 29 * _hashCode + (useridModified ? 1 : 0);
		if (penname != null) {
			_hashCode = 29 * _hashCode + penname.hashCode();
		}

		_hashCode = 29 * _hashCode + (pennameModified ? 1 : 0);
		if (password != null) {
			_hashCode = 29 * _hashCode + password.hashCode();
		}

		_hashCode = 29 * _hashCode + (passwordModified ? 1 : 0);
		if (email != null) {
			_hashCode = 29 * _hashCode + email.hashCode();
		}

		_hashCode = 29 * _hashCode + (emailModified ? 1 : 0);
		_hashCode = 29 * _hashCode + created;
		_hashCode = 29 * _hashCode + (createdModified ? 1 : 0);
		_hashCode = 29 * _hashCode + lastaccess;
		_hashCode = 29 * _hashCode + (lastaccessModified ? 1 : 0);
		_hashCode = 29 * _hashCode + lastlogin;
		_hashCode = 29 * _hashCode + (lastloginModified ? 1 : 0);
		_hashCode = 29 * _hashCode + (int) status;
		_hashCode = 29 * _hashCode + (statusModified ? 1 : 0);
		_hashCode = 29 * _hashCode + (int) type;
		_hashCode = 29 * _hashCode + (typeModified ? 1 : 0);
		if (timezone != null) {
			_hashCode = 29 * _hashCode + timezone.hashCode();
		}

		_hashCode = 29 * _hashCode + (timezoneModified ? 1 : 0);

		return (int) _hashCode;
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuffer ret = new StringBuffer();

		ret.append("User: ");
		ret.append(PROFILEID + "=" + userid);
		ret.append(", " + PENNAME + "=" + penname);
		ret.append(", " + PASSWORD + "=" + password);
		ret.append(", " + EMAIL + "=" + email);
		ret.append(", " + CREATED + "=" + created);
		ret.append(", " + LASTACCESS + "=" + lastaccess);
		ret.append(", " + LASTLOGIN + "=" + lastlogin);
		ret.append(", " + STATUS + "=" + status);
		ret.append(", " + TYPE + "=" + type);
		ret.append(", " + TIMEZONE + "=" + timezone);

		return ret.toString();
	}

	/**
	 * Verify Type
	 * TODO check this and verify whether perfect or not
	 */
	public boolean isAuthentic() {
		return this.type == Constants.TYPE.AUTHENTIC.ordinal();
	}

	public boolean isQpekaAdmin() {
		return this.type == Constants.TYPE.QPEKAADMIN.ordinal();
	}

	public boolean isQpekaManager() {
		return this.type == Constants.TYPE.QPEKAMANAGER.ordinal();
	}

	public static void main(String[] args) {
		User u = new User("srahul07", "123456", "srahul07@rediffmail.com", 1,
				"India");

		System.out.println(u.hashCode());
		System.out.println(u.toString());

	}
}
