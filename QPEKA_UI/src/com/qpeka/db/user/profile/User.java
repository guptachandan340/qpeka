package com.qpeka.db.user.profile;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5342974486315750933L;

	
	public static final String PROFILEID = "userid";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String EMAIL = "email";
	public static final String CREATED = "created";
	public static final String LASTACCESS = "lastaccess";
	public static final String LASTLOGIN = "lastlogin";
	public static final String STATUS = "status";
	public static final String TIMEZONE = "timezone";
	
	// These attributes maps to the columns of the user table.
	private long userid = 0;
	private String username = "";
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
	 * Status of a user specifies following things. 
	 * 0 => created (new user, not yet logged in), 
	 * 1 => active (User active on qpeka, including first time login), 
	 * 2 => passive (User is infrequent on qpeka), 
	 * 3 => blocked (user blocked by qpeka), 
	 * 4 => deleted (user account has been deleted)
	 */
	private short status = 0;
	/**
	 * Users timezone.
	 */
	private String timezone = "";
	// private String language = "";

	// These attributes represents whether the above attributes has been
	// modified since being read from the database.
	protected boolean useridModified = false;
	protected boolean usernameModified = false;
	protected boolean passwordModified = false;
	protected boolean emailModified = false;
	protected boolean createdModified = false;
	protected boolean lastaccessModified = false;
	protected boolean lastloginModified = false;
	protected boolean statusModified = false;
	protected boolean timezoneModified = false;

	// protected boolean languageModified = false;

	/*
	 * Constructors
	 */
	public User() {
		super();
	}

	public User(String username, String password, String email, long created,
			String timezone) { // , String language
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.created = created;
		this.timezone = timezone;
		// this.language = language;
	}

	public User(long userid, String username, String password) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
	}

	public User(String username, String password) {
		super();
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
		this.usernameModified = true;
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

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
		this.timezoneModified = true;
	}

	// public String getLanguage() {
	// return language;
	// }
	//
	// public void setLanguage(String language) {
	// this.language = language;
	// this.languageModified = true;
	// }

	/*
	 * Getters and setters for attribute modified status
	 */
	public boolean isUseridModified() {
		return useridModified;
	}

	public void setUseridModified(boolean useridModified) {
		this.useridModified = useridModified;
	}

	public boolean isUsernameModified() {
		return usernameModified;
	}

	public void setUsernameModified(boolean usernameModified) {
		this.usernameModified = usernameModified;
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

	public boolean isTimezoneModified() {
		return timezoneModified;
	}

	public void setTimezoneModified(boolean timezoneModified) {
		this.timezoneModified = timezoneModified;
	}

	// public boolean isLanguageModified() {
	// return languageModified;
	// }
	//
	// public void setLanguageModified(boolean languageModified) {
	// this.languageModified = languageModified;
	// }

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

		if (username == null ? _cast.username != username : !username
				.equals(_cast.username)) {
			return false;
		}

		if (usernameModified != _cast.usernameModified) {
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

		if (timezone == null ? _cast.timezone != timezone : !timezone
				.equals(_cast.timezone)) {
			return false;
		}

		if (timezoneModified != _cast.timezoneModified) {
			return false;
		}

		// if (language == null ? _cast.language != language : !language
		// .equals(_cast.language)) {
		// return false;
		// }
		//
		// if (languageModified != _cast.languageModified) {
		// return false;
		// }

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
		if (username != null) {
			_hashCode = 29 * _hashCode + username.hashCode();
		}

		_hashCode = 29 * _hashCode + (usernameModified ? 1 : 0);
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
		if (timezone != null) {
			_hashCode = 29 * _hashCode + timezone.hashCode();
		}

		_hashCode = 29 * _hashCode + (timezoneModified ? 1 : 0);
		// if (language != null) {
		// _hashCode = 29 * _hashCode + language.hashCode();
		// }
		//
		// _hashCode = 29 * _hashCode + (languageModified ? 1 : 0);

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
		ret.append(", " + USERNAME + "=" + username);
		ret.append(", " + PASSWORD + "=" + password);
		ret.append(", " + EMAIL + "=" + email);
		ret.append(", " + CREATED + "=" + created);
		ret.append(", " + LASTACCESS + "=" + lastaccess);
		ret.append(", " + LASTLOGIN + "=" + lastlogin);
		ret.append(", " + STATUS + "=" + status);
		ret.append(", " + TIMEZONE + "=" + timezone);
		// ret.append(", language=" + language);

		return ret.toString();
	}

	// public DBObject toDBObject(boolean insert)
	// {
	// BasicDBObject dbObj = new BasicDBObject();
	// if(!insert)
	// dbObj.put("username", username);
	// dbObj.put("password", password);
	//
	// return dbObj;
	// }
	//
	// public static User getUserfromDBObject(BasicDBObject obj)
	// {
	// return new User(obj.getString("username"), obj.getString("password"));
	// }

	public static void main(String[] args) {
		User u = new User("srahul07", "123456", "srahul07@rediffmail.com", 1,
				"India");

		System.out.println(u.hashCode());
		System.out.println(u.toString());

	}
}
