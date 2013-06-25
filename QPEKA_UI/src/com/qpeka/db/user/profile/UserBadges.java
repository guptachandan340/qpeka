package com.qpeka.db.user.profile;

public class UserBadges {

	public static final String USERID = "userid";
	public static final String BADGEID = "badgeid";

	private long userid;
	private short badgeid;

	protected boolean useridModified = false;
	protected boolean badgeidModified = false;

	public UserBadges() {
		super();
	}

	public UserBadges(long userid, short badgeid) {
		super();
		this.userid = userid;
		this.badgeid = badgeid;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
		this.useridModified = true;
	}

	public short getBadgeid() {
		return badgeid;
	}

	public void setBadgeid(short badgeid) {
		this.badgeid = badgeid;
		this.badgeidModified = true;
	}

	public boolean isUseridModified() {
		return useridModified;
	}

	public void setUseridModified(boolean useridModified) {
		this.useridModified = useridModified;
	}

	public boolean isBadgeidModified() {
		return badgeidModified;
	}

	public void setBadgeidModified(boolean badgeidModified) {
		this.badgeidModified = badgeidModified;
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

		if (!(_other instanceof UserBadges)) {
			return false;
		}

		final UserBadges _cast = (UserBadges) _other;
		if (userid != _cast.userid) {
			return false;
		}

		if (useridModified != _cast.useridModified) {
			return false;
		}

		if (badgeid != _cast.badgeid) {
			return false;
		}

		if (badgeidModified != _cast.badgeidModified) {
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
		_hashCode = 29 * _hashCode + badgeid;
		_hashCode = 29 * _hashCode + (badgeidModified ? 1 : 0);
		return _hashCode;
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("Userbadges: ");
		ret.append(USERID + "=" + userid);
		ret.append(", " + BADGEID + "=" + badgeid);
		return ret.toString();
	}

}
