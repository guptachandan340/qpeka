package com.qpeka.db.user.profile;

public class UserInterests {
	
	public static final String USERID = "userid";
	public static final String GENREID = "genreid";
	
	private long userid;
	private short genreid;
	
	protected boolean useridModified = false;
	protected boolean genreidModified = false;
	
	//public static UserInterests instance = null;
	
	public UserInterests() {
		super();
	}

	public UserInterests(long userid, short genreid) {
		super();
		this.userid = userid;
		this.genreid = genreid;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
		this.useridModified = true;
	}

	public short getGenreid() {
		return genreid;
	}

	public void setGenreid(short genreid) {
		this.genreid = genreid;
		this.genreidModified = true;
	}

	public boolean isUseridModified() {
		return useridModified;
	}

	public void setUseridModified(boolean useridModified) {
		this.useridModified = useridModified;
	}

	public boolean isGenreidModified() {
		return genreidModified;
	}

	public void setGenreidModified(boolean genreidModified) {
		this.genreidModified = genreidModified;
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
		
		if (!(_other instanceof UserInterests)) {
			return false;
		}
		
		final UserInterests _cast = (UserInterests) _other;
		if (userid != _cast.userid) {
			return false;
		}
		
		if (useridModified != _cast.useridModified) {
			return false;
		}
		
		if (genreid != _cast.genreid) {
			return false;
		}
		
		if (genreidModified != _cast.genreidModified) {
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
		_hashCode = 29 * _hashCode + (int) genreid;
		_hashCode = 29 * _hashCode + (genreidModified ? 1 : 0);
		return _hashCode;
	}
	
	/**
	 * Get Instance of User Interests class
	 * @return user interest object
	 */
	public static UserInterests getInstance() {
		return new UserInterests(); // ((instance == null) ? (instance = new UserInterests()) : instance);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "Userinterests: " );
		ret.append( USERID + "=" + userid );
		ret.append( ", " + GENREID + "=" + genreid );
		return ret.toString();
	}
	
}
