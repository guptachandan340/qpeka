package com.qpeka.db;

public class UserFieldVisibility {

	public static final String VISIBILITYID = "visibilityid";
	public static final String USERID = "userid";
	public static final String FIELDNAME = "fieldname";
	public static final String STATUS = "status";
	
	// These attributes maps to the columns of the useraddress table.
		private long visibilityid;
		private long userid;
		private String fieldName;
		private short status;
		
		// These attributes represents whether the above attributes has been
		// modified since being read from the database.
		protected boolean visibilityModified = false;
		protected boolean useridModified = false;
		protected boolean fieldNameModified = false;
		protected boolean statusModified = false;
		
		public static UserFieldVisibility instance = null;
	
		/* CONSTRUCTORS
		 */
		
		public UserFieldVisibility() {
			super();
		}
		
		public UserFieldVisibility(long visibilityid, long userid, String fieldName, Short status) {
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
		}

		public long getUserid() {
			return userid;
		}

		public void setUserid(long userid) {
			this.userid = userid;
		}

		public String getFieldName() {
			return fieldName;
		}

		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}

		public short getStatus() {
			return status;
		}

		public void setStatus(short status) {
			this.status = status;
		}
		
		/** Getter and Setter for Attributes modified
		 */
		
		public boolean isVisibilityidModified() {
			return addressidModified;
		}

		public void setAddressidModified(boolean addressidModified) {
			this.addressidModified = addressidModified;
		}
}
