package com.qpeka.db.user.session;

import java.io.Serializable;

public class Sessions implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3992073354944449449L;
	
	public static final String SESSIONID = "sessionid";
	public static final String USERID = "userid";
	public static final String HOSTNAME = "hostname";
	public static final String TIMESTAMP = "timestamp";
	public static final String SESSION = "session";
	
	// These attributes maps to the columns of the sessions table.
	private long sessionid;
	private long userid;
	private String hostname;
	private long timestamp;
	private String session;
	
	// These attributes represents whether the above attributes has been
	// modified since being read from the database.
	protected boolean sessionidModified = false;
	protected boolean useridModified = false;
	protected boolean hostnameModified = false;
	protected boolean timestampModified = false;
	protected boolean sessionModified = false;
	
	/*
	 * Constructors
	 */
	public Sessions() {
		super();
	}


	public Sessions(long sessionid, long userid, String hostname,
			long timestamp, String session) {
		super();
		this.sessionid = sessionid;
		this.userid = userid;
		this.hostname = hostname;
		this.timestamp = timestamp;
		this.session = session;
	}


	public Sessions(long userid, String hostname, long timestamp, String session) {
		super();
		this.userid = userid;
		this.hostname = hostname;
		this.timestamp = timestamp;
		this.session = session;
	}


	public Sessions(String hostname, long timestamp, String session) {
		super();
		this.hostname = hostname;
		this.timestamp = timestamp;
		this.session = session;
	}
	

	/*
	 * Getters and setters for attributes
	 */
	public long getSessionid() {
		return sessionid;
	}

	public void setSessionid(long sessionid) {
		this.sessionid = sessionid;
		this.sessionidModified = true;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
		this.useridModified = true;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
		this.hostnameModified = true;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
		this.sessionModified = true;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
		this.timestampModified = true;
	}

	/*
	 * Getters and setters for attribute modified status
	 */
	public boolean isSessionidModified() {
		return sessionidModified;
	}

	public void setSessionidModified(boolean sessionidModified) {
		this.sessionidModified = sessionidModified;
	}

	public boolean isUseridModified() {
		return useridModified;
	}

	public void setUseridModified(boolean useridModified) {
		this.useridModified = useridModified;
	}

	public boolean isTimestampModified() {
		return timestampModified;
	}

	public void setTimestampModified(boolean timestampModified) {
		this.timestampModified = timestampModified;
	}

	public boolean isSessionModified() {
		return sessionModified;
	}

	public void setSessionModified(boolean sessionModified) {
		this.sessionModified = sessionModified;
	}
	
	public boolean isHostnameModified() {
		return hostnameModified;
	}

	public void setHostnameModified(boolean hostnameModified) {
		this.hostnameModified = hostnameModified;
	}
	
	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode()
	{
		int _hashCode = 0;
		_hashCode = 29 * _hashCode + (int)userid;
		_hashCode = 29 * _hashCode + (useridModified ? 1 : 0);
		_hashCode = 29 * _hashCode + (int)sessionid;
		_hashCode = 29 * _hashCode + (sessionidModified ? 1 : 0);
		if (hostname != null) {
			_hashCode = 29 * _hashCode + hostname.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (hostnameModified ? 1 : 0);
		_hashCode = 29 * _hashCode + (int)timestamp;
		_hashCode = 29 * _hashCode + (timestampModified ? 1 : 0);
		if (session != null) {
			_hashCode = 29 * _hashCode + session.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (sessionModified ? 1 : 0);
		
		return _hashCode;
	}
	
	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "Sessions: " );
		ret.append( USERID + "=" + userid );
		ret.append( ", " + SESSIONID + "=" + sessionid );
		ret.append( ", " + HOSTNAME + "=" + hostname );
		ret.append( ", " + TIMESTAMP + "=" + timestamp );
		ret.append( ", " + SESSION + "=" + session );
		
		return ret.toString();
	}
	
}
