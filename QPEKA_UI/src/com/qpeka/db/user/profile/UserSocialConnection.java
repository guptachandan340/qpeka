package com.qpeka.db.user.profile;

import java.io.Serializable;

public class UserSocialConnection implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9184136195849905242L;
	
	private static final String USERSOCIALCONNID = "usersocialconnid";
	private static final String USERID = "userid";
	private static final String PLATFORM = "platform";
	private static final String SOCIALID = "socialid";

	// These attributes maps to the usersocialconnid, userid, platform and socialid columns
		// of the userprofile table.
		private long usersocialconnid;
		private long userid;
		private String platform;
		private String socialid;

		// These attributes represents whether the above attributes has been
		// modified since being read from the database.
		protected boolean usersocialconnidModified = false;
		protected boolean useridModified = false;
		protected boolean platformModified = false;
		protected boolean socialidModified = false;
		
		public static UserSocialConnection instance = null;
		
		/*
		 * Constructors
		 */
		public UserSocialConnection() {

		}

		public UserSocialConnection(long usersocialconnid, long userid, String platform, String socialid) {
			super();
			this.setUsersocialconnid(usersocialconnid);
			this.setUserid(userid);
			this.setPlatform(platform);
			this.setSocialid(socialid);
		}

		/*
		 * Getters and setters for attributes
		 */

		public static UserSocialConnection getInstance() {
			return (instance == null ? (instance = new UserSocialConnection()) : instance);
		}

		public long getUsersocialconnid() {
			return usersocialconnid;
		}

		public void setUsersocialconnid(long usersocialconnid) {
			this.usersocialconnid = usersocialconnid;
			this.usersocialconnidModified = true;
		}

		public long getUserid() {
			return userid;
		}

		public void setUserid(long userid) {
			this.userid = userid;
			this.useridModified = true;
		}

		public String getPlatform() {
			return platform;
		}

		public void setPlatform(String platform) {
			this.platform = platform;
			this.platformModified = true;
		}

		/**
		 * @return the socialid
		 */
		public String getSocialid() {
			return socialid;
		}

		/**
		 * @param socialid the socialid to set
		 */
		public void setSocialid(String socialid) {
			this.socialid = socialid;
			this.socialidModified = true;
		}
		
		/*
		 * Getters and setters for attribute modified status
		 */
		public boolean isUserSocialConnidModified() {
			return usersocialconnidModified;
		}

		public void setUserSocialConnidModified(boolean usersocialconnidModified) {
			this.usersocialconnidModified = usersocialconnidModified;
		}

		public boolean isUseridModified() {
			return useridModified;
		}

		public void setUseridModified(boolean useridModified) {
			this.useridModified = useridModified;
		}

		public boolean isPlatformModified() {
			return platformModified;
		}

		public void setPlatformModified(boolean platformModified) {
			this.platformModified = platformModified;
		}
		
		public boolean isSocialidModified() {
			return socialidModified;
		}

		public void setSocialidModified(boolean socialidModified) {
			this.socialidModified = socialidModified;
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

			if (!(_other instanceof UserSocialConnection)) {
				return false;
			}

			final UserSocialConnection _cast = (UserSocialConnection) _other;

			if (usersocialconnid != _cast.usersocialconnid) {
				return false;
			}

			if (usersocialconnidModified != _cast.usersocialconnidModified) {
				return false;
			}
			
			if (userid != _cast.userid) {
				return false;
			}

			if (useridModified != _cast.useridModified) {
				return false;
			}
			
			if (platform == null ? _cast.platform != platform : !platform
					.equals(_cast.platform)) {
				return false;
			}

			if (platformModified != _cast.platformModified) {
				return false;
			}

			if (socialid == null ? _cast.socialid != socialid : !socialid
					.equals(_cast.socialid)) {
				return false;
			}

			if (socialidModified != _cast.socialidModified) {
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
			_hashCode = (int) (29 * _hashCode + usersocialconnid);
			_hashCode = 29 * _hashCode + (usersocialconnidModified ? 1 : 0);
			_hashCode = (int) (29 * _hashCode + userid);
			_hashCode = 29 * _hashCode + (useridModified ? 1 : 0);
			if (platform != null) {
				_hashCode = 29 * _hashCode + platform.hashCode();
			}

			_hashCode = 29 * _hashCode + (platformModified ? 1 : 0);
			if (socialid != null) {
				_hashCode = 29 * _hashCode + socialid.hashCode();
			}

			_hashCode = 29 * _hashCode + (socialidModified ? 1 : 0);

			return _hashCode;
		}

		/**
		 * Method 'toString'
		 * 
		 * @return String
		 */
		public String toString() {
			StringBuffer ret = new StringBuffer();

			ret.append("UserSocialConnection: ");
			ret.append("usersocialconnid=" + usersocialconnid);
			ret.append(", userid=" + userid);
			ret.append(", platform=" + platform);
			ret.append(", socialid=" + socialid);
			
			return ret.toString();
		}
}
