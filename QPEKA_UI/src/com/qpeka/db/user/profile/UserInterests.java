package com.qpeka.db.user.profile;

public class UserInterests {
	
	public static final String USERID = "userid";
	public static final String CATEGORYID = "categoryid";
	
	private long userid;
	private short categoryid;
	
	protected boolean useridModified = false;
	protected boolean categoryidModified = false;
	
	public UserInterests() {
		super();
	}

	public UserInterests(long userid, short categoryid) {
		super();
		this.userid = userid;
		this.categoryid = categoryid;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
		this.useridModified = true;
	}

	public short getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(short categoryid) {
		this.categoryid = categoryid;
		this.categoryidModified = true;
	}

	public boolean isUseridModified() {
		return useridModified;
	}

	public void setUseridModified(boolean useridModified) {
		this.useridModified = useridModified;
	}

	public boolean isCategoryidModified() {
		return categoryidModified;
	}

	public void setCategoryidModified(boolean categoryidModified) {
		this.categoryidModified = categoryidModified;
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
		
		if (categoryid != _cast.categoryid) {
			return false;
		}
		
		if (categoryidModified != _cast.categoryidModified) {
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
		_hashCode = 29 * _hashCode + (int) categoryid;
		_hashCode = 29 * _hashCode + (categoryidModified ? 1 : 0);
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
		ret.append( "Userinterests: " );
		ret.append( USERID + "=" + userid );
		ret.append( ", " + CATEGORYID + "=" + categoryid );
		return ret.toString();
	}
	
}
