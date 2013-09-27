package com.qpeka.db;

import java.io.Serializable;

public class Session implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -76887559770927038L;

	public static Session instance =null;
	public static final String SESSIONID = "sessionid";
	public static final String USERID = "userid";
	public static final String HOSTNAME = "hostname";
	public static final String CREATED = "created";
	public static final String SESSION = "session";
	public static final String STATUS = "status";
	
	// These attributes maps to the columns of the sessions table.
	private long sessionid;
	private long userid;
	private String hostname;
	private long created;
	private String session;
	
	// status => 0 if active, status => 1 if inactive, status => 2 if expired, status => 3 destroyed
	private short status;

	// These attributes represents whether the above attributes has been
	// modified since being read from the database.
	protected boolean sessionidModified = false;
	protected boolean useridModified = false;
	protected boolean hostnameModified = false;
	protected boolean createdModified = false;
	protected boolean sessionModified = false;
	protected boolean statusModified = false;
	
	/*
	 * Constructors
	 */
	
	public Session() {
		super();
	}

	public Session(long sessionid, long userid, String hostname, long created,
			String session, short status) {
		super();
		this.sessionid = sessionid;
		this.userid = userid;
		this.hostname = hostname;
		this.created = created;
		this.session = session;
		this.status = status;
	}

	public Session(long sessionid, long userid, String session, String hostname) {
		super();
		this.sessionid = sessionid;
		this.userid = userid;
		this.session = session;
		this.hostname = hostname;
	}

	public static Session getInstance() {
		return (instance == null ? instance = new Session() : instance);
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

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
		this.createdModified = true;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
		this.sessionModified = true;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
		this.statusModified = true;
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

	public boolean isHostnameModified() {
		return hostnameModified;
	}

	public void setHostnameModified(boolean hostnameModified) {
		this.hostnameModified = hostnameModified;
	}

	public boolean isCreatedModified() {
		return createdModified;
	}

	public void setcreatedModified(boolean createdModified) {
		this.createdModified = createdModified;
	}

	public boolean isSessionModified() {
		return sessionModified;
	}

	public void setSessionModified(boolean sessionModified) {
		this.sessionModified = sessionModified;
	}

	public boolean isStatusModified() {
		return statusModified;
	}

	public void setStatusModified(boolean statusModified) {
		this.statusModified = statusModified;
	}

	
	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}
		
		if (_other == this) {
			return true;
		}
		
		if (!(_other instanceof Session)) {
			return false;
		}
		
		final Session _cast = (Session) _other;
		if (sessionid != _cast.sessionid) {
			return false;
		}
		
		if (sessionidModified != _cast.sessionidModified) {
			return false;
		}
		
		if (userid != _cast.userid) {
			return false;
		}
		
		if (useridModified != _cast.useridModified) {
			return false;
		}
		
		if (hostname == null ? _cast.hostname != hostname : !hostname.equals( _cast.hostname )) {
			return false;
		}
		
		if (hostnameModified != _cast.hostnameModified) {
			return false;
		}
		
		if (created != _cast.created) {
			return false;
		}
		
		if(createdModified != _cast.createdModified) {
			return false;
		}
		
		if (session == null ? _cast.session != session : !session.equals( _cast.session )) {
			return false;
		}
		
		if (sessionModified != _cast.sessionidModified) {
			return false;
		}
		
		if (statusModified != _cast.statusModified) {
			return false;
		}
		
		if(sessionModified != _cast.sessionModified) {
			return false;
		}		
		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode()
	{
		int _hashCode = 0;
		_hashCode = 29 * _hashCode + (int) sessionid;
		_hashCode = 29 * _hashCode + (sessionidModified ? 1 : 0);
		_hashCode = 29 * _hashCode + (int) userid;
		_hashCode = 29 * _hashCode + (useridModified ? 1 : 0);
		if (hostname != null) {
			_hashCode = 29 * _hashCode + hostname.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (hostnameModified ? 1 : 0);
		_hashCode = 29 * _hashCode + (int) created;
		_hashCode = 29 * _hashCode + (createdModified ? 1 : 0);
		if (session != null) {
			_hashCode = 29 * _hashCode + session.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (sessionModified ? 1 : 0);
		
		_hashCode = 29 * _hashCode + status;
		_hashCode = 29 * _hashCode + (statusModified ? 1 : 0);
		
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
		ret.append( "session: " );
		ret.append( "sessionid=" + sessionid );
		ret.append( ", userid=" + userid );
		ret.append( ", hostname=" + hostname );
		ret.append( ", created=" + created );
		ret.append( ", session=" + session );
		ret.append( ", status=" +status);
		return ret.toString();
	}
}