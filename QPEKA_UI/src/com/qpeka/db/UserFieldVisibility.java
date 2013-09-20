package com.qpeka.db;

import com.qpeka.db.Constants.VISIBILITY;

public class UserFieldVisibility {

	public static final String VISIBILITYID = "visibilityid";
	public static final String USERID = "userid";
	public static final String FIELDNAME = "fieldname";
	public static final String STATUS = "status";
	
	// These attributes maps to the columns of the userFeildVisibility table.
		private long visibilityid;
		private long userid;
		private String fieldName;
		private VISIBILITY status = Constants.VISIBILITY.PRIVATE;
		
		// These attributes represents whether the above attributes has been
		// modified since being read from the database.
		protected boolean visibilityidModified = false;
		protected boolean useridModified = false;
		protected boolean fieldNameModified = false;
		protected boolean statusModified = false;
		
		public static UserFieldVisibility instance = null;
	
		/* CONSTRUCTORS
		 */
		
		public UserFieldVisibility() {
			super();
		}
		
		public UserFieldVisibility(long visibilityid, long userid, String fieldName, VISIBILITY status) {
			super();
			this.setVisibilityid(visibilityid);
			this.setUserid(userid);
			this.setFieldName(fieldName);
			this.setStatus(status);
		}
		
		public static UserFieldVisibility getInstance() {
			return instance == null ? (instance = new UserFieldVisibility()) : instance;
		}
		
		/** Getter and Setter for Attributes
		 * 
		 * @return
		 */
		public long getVisibilityid() {
			return visibilityid;
		}

		public void setVisibilityid(long visibilityid) {
			this.visibilityid = visibilityid;
			this.visibilityidModified = true;
		}

		public long getUserid() {
			return userid;
		}

		public void setUserid(long userid) {
			this.userid = userid;
			this.useridModified = true;
		}

		public String getFieldName() {
			return fieldName;
		}

		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
			this.fieldNameModified = true;
		}

		public VISIBILITY getStatus() {
			return status;
		}

		public void setStatus(VISIBILITY status) {
			this.status = status;
			this.statusModified = true;
		}
		
		/*
		 * * Getter and Setter for Attributes modified
		 */
		
		public boolean isVisibilityidModified() {
			return visibilityidModified;
		}

		public void setVisibilityidModified(boolean visibilityidModified) {
			this.visibilityidModified = visibilityidModified;
		}
		
		public boolean isUseridModified() {
			return useridModified;
		}

		public void setUseridModified(boolean useridModified) {
			this.useridModified = useridModified;
		}
		
		public boolean isFieldNameModified() {
			return fieldNameModified;
		}

		public void setFieldNameModified(boolean fieldNameModified) {
			this.fieldNameModified = fieldNameModified;
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
			
			if (!(_other instanceof UserFieldVisibility)) {
				return false;
			}
			
			final UserFieldVisibility _cast = (UserFieldVisibility) _other;
			if (visibilityid != _cast.visibilityid) {
				return false;
			}
			
			if (visibilityidModified != _cast.visibilityidModified) {
				return false;
			}
			
			if (userid != _cast.userid) {
				return false;
			}
			
			if (useridModified != _cast.useridModified) {
				return false;
			}
			
			if (fieldName == null ? _cast.fieldName != fieldName
					: !fieldName.equals(_cast.fieldName)) {
				return false;
			}
			

			if (fieldNameModified != _cast.fieldNameModified) {
				return false;
			}
			
			if (status == null ? _cast.status != status
					: !status.equals(_cast.status)) {
				return false;
			}
			
			
			if (statusModified != _cast.statusModified) {
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
			_hashCode = (int) (29 * _hashCode +  visibilityid);
			_hashCode = 29 * _hashCode + (visibilityidModified ? 1 : 0);
			_hashCode = (int) (29 * _hashCode + userid);
			_hashCode = 29 * _hashCode + (useridModified ? 1 : 0);
			
			if (fieldName != null) {
				_hashCode = 29 * _hashCode + fieldName.hashCode();
			}

			_hashCode = 29 * _hashCode + (fieldNameModified ? 1 : 0);
			
			if (status != null) {
				_hashCode = 29 * _hashCode + status.hashCode();
			}

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
			ret.append( "UserFieldVisibility: " );
			ret.append( VISIBILITYID + "=" + visibilityid );
			ret.append(", " + USERID + "=" + userid );
			ret.append( ", " + FIELDNAME + "=" + fieldName);
			ret.append( ", " + STATUS + "=" + status);
			return ret.toString();
		}
		
}
