package com.qpeka.db.user.profile;

import java.io.Serializable;

import com.qpeka.db.Constants;
import com.qpeka.db.Constants.INVITESTATUS;

public class UserInvites implements Serializable {

	private static final long serialVersionUID = 5632637867599534400L;

	public static final String INVITEID = "inviteid";
	public static final String USERID = "userid";
	public static final String TYPE = "type";
	public static final String INVITEIDENTIFIER = "inviteidentifier";
	public static final String HASHVALUE = "hashvalue";
	public static final String STATUS = "status";

	// These attributes maps to the columns of the userinvite table.
	private long inviteid = 0;
	private long userid;
	private String type = "";
	private String inviteidentifier = "";
	private String hashvalue = "";
	/**
	 * Status of a user specifies following things. 0 => Not sent (Users whom
	 * invitation has not been sent, 1 => Sent but not accepted, 2 => Accepted
	 * (invitation from accepted), 3 => Not acepted (Invitation from that user
	 * has not been accepted));
	 */
	private INVITESTATUS status = INVITESTATUS.PENDING;
	
	private static UserInvites instance = null;
	// These attributes represents whether the above attributes has been
	// modified since being read from the database.
	protected boolean inviteidModified = false;
	protected boolean useridModified = false;
	protected boolean typeModified = false;
	protected boolean inviteidentifierModified = false;
	protected boolean hashvalueModified = false;
	protected boolean statusModified = false;

	public static UserInvites getInstance() {
		return (instance == null ? (instance = new UserInvites()) : instance);
	}

	/*
	 * Constructors
	 */
	public UserInvites() {
		super();
	}

	public UserInvites(long inviteid, long userid, String type, String inviteidentifier, String hashvalue, INVITESTATUS status) {
		super();
		this.setInviteid(inviteid);
		this.userid = userid;
		this.type = type;
		this.inviteidentifier = inviteidentifier;
		this.hashvalue = hashvalue;
		this.status = status;
	}

	public UserInvites(String type, String inviteidentifier, String hashvalue) {
		super();
		this.type = type;
		this.inviteidentifier = inviteidentifier;
		this.hashvalue = hashvalue;
	}

	public UserInvites(String inviteidentifier, String hashvalue) {
		super();
		this.inviteidentifier = inviteidentifier;
		this.hashvalue = hashvalue;
	}

	/*
	 * Getter and Setter Method
	 */
	public long getInviteid() {
		return inviteid;
	}

	public void setInviteid(long inviteid) {
		this.inviteid = inviteid;
	}
	
	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
		this.useridModified = true;
	}
	
		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
			this.typeModified = true;
		}

		public String getInviteidentifier() {
			return inviteidentifier;
		}

	public void setInviteidentifier(String inviteidentifier) {
		this.inviteidentifier = inviteidentifier;
		this.inviteidentifierModified = true;
	}

	public String getHashvalue() {
		return hashvalue;
	}
	
	public void setHashvalue(String hashvalue) {
		this.hashvalue = hashvalue;
		this.hashvalueModified = true;
	}
	
	public INVITESTATUS getStatus() {
		return status;
	}

	public void setStatus(INVITESTATUS status) {
		this.status = status;
		this.statusModified = true;
	}
	
	/**
	 * Getter and Setter method for Attrubutes modified
	 */

	public boolean isInviteidModified() {
		return inviteidModified;
	}
	
	public void setInviteidModified(boolean inviteModified) {
		this.inviteidentifierModified = inviteModified;
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
	
	public boolean isInviteidentifierModified() {
		return inviteidentifierModified;
	}
	
	public void setInviteidentifierModified(boolean inviteidentifierModified) {
		this.inviteidentifierModified = inviteidentifierModified;
	}
	
	public boolean isHashvalueModified() {
		return hashvalueModified;
	}
	
	public void setHashalueModified(boolean hashvalueModified) {
		this.hashvalueModified = hashvalueModified;
	}
	
	public boolean isStatusModified() {
		return statusModified;
	}
	public void setStatusModified(boolean statusModified) {
		this.statusModified = statusModified;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	
	@Override
	public int hashCode() {
		long _hashCode = 0;
		
		_hashCode = 29 * _hashCode + inviteid;
		_hashCode = 29 * _hashCode + (inviteidModified ? 1 : 0);
	
		_hashCode = 29 * _hashCode + userid;
		_hashCode = 29 * _hashCode + (useridModified ? 1 : 0);
		if (type != null) {
			_hashCode = 29 * _hashCode + type.hashCode();
		}

		_hashCode = 29 * _hashCode + (typeModified ? 1 : 0);
		if (inviteidentifier != null) {
			_hashCode = 29 * _hashCode + inviteidentifier.hashCode();
		}

		_hashCode = 29 * _hashCode + (inviteidentifierModified ? 1 : 0);
		if (hashvalue != null) {
			_hashCode = 29 * _hashCode + hashvalue.hashCode();
		}

		_hashCode = 29 * _hashCode + (hashvalueModified ? 1 : 0);
		if (status != null) {
			_hashCode = 29 * _hashCode + status.hashCode();
		}
		_hashCode = 29 * _hashCode + (statusModified ? 1 : 0);
		
		return (int) _hashCode;
	}

	public boolean equals(Object _other) {

		if (_other == null) {
			return false;
		}

		if (_other == this) {
			return true;
		}

		if (!(_other instanceof UserInvites)) {
			return false;
		}

		final UserInvites _cast = (UserInvites) _other;

		if (inviteid != _cast.inviteid) {
			return false;
		}

		if (inviteidModified != _cast.inviteidModified) {
			return false;
		}
		
		if (userid != _cast.userid) {
			return false;
		}

		if (useridModified != _cast.useridModified) {
			return false;
		}

		if (type == null ? _cast.type != type : !type
				.equals(_cast.type)) {
			return false;
		}

		if (typeModified != _cast.typeModified) {
			return false;
		}

		if (inviteidentifier == null ? _cast.inviteidentifier != inviteidentifier : !inviteidentifier
				.equals(_cast.inviteidentifier)) {
			return false;
		}

		if (inviteidentifierModified != _cast.inviteidentifierModified) {
			return false;
		}

		if (hashvalue == null ? _cast.hashvalue != hashvalue : !hashvalue
				.equals(_cast.hashvalue)) {
			return false;
		}

		if (hashvalueModified != _cast.hashvalueModified) {
			return false;
		}

		if (status == null ? _cast.status != status : !status.equals(_cast.hashvalue)) {
			return false;
		}

		if (statusModified != _cast.statusModified) {
			return false;
		}
	
		return true;
	}

	/*
	 * Method 'toString'
	   @return String
	 */
	public String toString() {
		StringBuffer ret = new StringBuffer();

		ret.append("UserInvites: ");
		ret.append(INVITEID + "=" + inviteid);
		ret.append(", " + USERID + "=" + userid);
		ret.append(", " + TYPE + "=" + type);
		ret.append(", " + INVITEIDENTIFIER + "=" + inviteidentifier);
		ret.append(", " + HASHVALUE + "=" + hashvalue);
		ret.append(", " + STATUS + "=" + status);

		return ret.toString();
	}

	/**
	 * Verify status
	 //TODO check this and verify whether perfect or not
	 */
	public boolean isInviteNotSent() {
		return this.status == Constants.INVITESTATUS.PENDING;
	}

	public boolean isInviteSent() {
		return this.status == Constants.INVITESTATUS.SENT;
	}

	public boolean isInviteAccepted() {
		return this.status == Constants.INVITESTATUS.ACCEPTED;
	}
	
	public boolean isInviteNotAccepted() {
		return this.status == Constants.INVITESTATUS.REJECTED;
	}

	
	/*
	public static void main(String[] args) {
		UserInvites u = new UserInvites((long)1, (long)20, "gmail", "anki546.malani@gmail.com", "vdgdgd", (short)0);
		
		System.out.println(u.hashCode());
		System.out.println(u.toString());
		
		System.out.println(Constants.INVITESTATUS.NOTSENT);
	}
*/
	
}
