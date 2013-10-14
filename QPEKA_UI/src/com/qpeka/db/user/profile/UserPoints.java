package com.qpeka.db.user.profile;

import java.io.Serializable;

public class UserPoints implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6494086252399597078L;
	public static final String USERID = "userid";
	public static final String TYPE = "type";
	public static final String POINTS = "points";
	
	private long userid;
	private short type;
	private long points;
	
	protected boolean useridModified = false;
	protected boolean typeModified = false;
	protected boolean pointsModified = false;
	//public static UserPoints instance = null;
	
	public UserPoints() {
		super();
	}

	public UserPoints(long userid, short type, long points) {
		super();
		this.userid = userid;
		this.type = type;
		this.points = points;
	}

	public static UserPoints getInstance() {
		return new UserPoints(); //(instance == null ? instance = new UserPoints() : instance);
	}
	
	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
		this.useridModified = true;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
		this.typeModified = true;
	}

	public long getPoints() {
		return points;
	}

	public void setPoints(long points) {
		this.points = points;
		this.pointsModified = true;
	}

	public boolean isUseridModified() {
		return useridModified;
	}

	public void setUseridModified(boolean useridModified) {
		this.useridModified = useridModified;
	}

	public boolean isTypeModified() {
		return typeModified;
	}

	public void setTypeModified(boolean typeModified) {
		this.typeModified = typeModified;
	}

	public boolean isPointsModified() {
		return pointsModified;
	}

	public void setPointsModified(boolean pointsModified) {
		this.pointsModified = pointsModified;
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
		
		if (!(_other instanceof UserPoints)) {
			return false;
		}
		
		final UserPoints _cast = (UserPoints) _other;
		if (userid != _cast.userid) {
			return false;
		}
		
		if (useridModified != _cast.useridModified) {
			return false;
		}
		
		if (type != _cast.type) {
			return false;
		}
		
		if (typeModified != _cast.typeModified) {
			return false;
		}
		
		if (points != _cast.points) {
			return false;
		}
		
		if (pointsModified != _cast.pointsModified) {
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
		_hashCode = (int) (29 * _hashCode + userid);
		_hashCode = 29 * _hashCode + (useridModified ? 1 : 0);
		_hashCode = 29 * _hashCode + (int) type;
		_hashCode = 29 * _hashCode + (typeModified ? 1 : 0);
		_hashCode = (int) (29 * _hashCode + points);
		_hashCode = 29 * _hashCode + (pointsModified ? 1 : 0);
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
		ret.append( "Userpoints: " );
		ret.append( USERID + "=" + userid );
		ret.append( ", " + TYPE + "=" + type );
		ret.append( ", " + POINTS + "=" + points );
		return ret.toString();
	}
}
