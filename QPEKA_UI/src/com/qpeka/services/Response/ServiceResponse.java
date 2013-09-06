package com.qpeka.services.Response;

import java.io.Serializable;

public class ServiceResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String ERRORID = "errorid";
	public static final String STATUS = "status";
	public static final String NAME = "name";
	public static final String MESSAGE = "message";

	// These attributes maps to the columns of the user table.
		private short errorid = 0;
		private int status = 200;
		private String name = "OK";
		private String message;
	
		private static ServiceResponse instance = null;
		// These attributes represents whether the above attributes has been
		// modified since being read from the database.
		
		protected boolean erroridModified = false;
		protected boolean statusModified = false;
		protected boolean nameModified = false;
		protected boolean messageModified = false;
		
		public static ServiceResponse getInstance() {
			return (instance == null ? (instance = new ServiceResponse()) : instance);
		}
	 
	public ServiceResponse() {
		super();
	}
	
	public ServiceResponse(short errorid, int status, String name, String message) { 
		super();
		this.errorid = errorid;
		this.name = name;
		this.status = status;
		this.message = message;
	}
	
	public ServiceResponse(int status, String name) { 
		super();
		this.name = name;
		this.status = status;
	}


	/*
	 * Getters and setters for attributes
	 */
	
	/**
	 * @return the errorid
	 */
	public short getErrorid() {
		return errorid;
	}

	/**
	 * @param errorid the errorid to set
	 */
	public void setErrorid(short errorid) {
		this.errorid = errorid;
		this.erroridModified = true;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
		this.statusModified = true;
	}
	/**
	 * @return the Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param Name the Name to set
	 */
	public void setName(String name) {
		this.name = name;
		this.nameModified = true;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
		this.messageModified = true;
	}

	/*
	 * Getters and setters for attribute modified status
	 */
	
	public boolean isErroridModified() {
		return erroridModified;
	}

	public void setErroridModified(boolean erroridModified) {
		this.erroridModified = erroridModified;
	}
	
	public boolean isStatusModified() {
		return statusModified;
	}

	public void setStatusModified(boolean statusModified) {
		this.statusModified = statusModified;
	}
	
	public boolean isNameModified() {
		return nameModified;
	}

	public void setNameModified(boolean nameModified) {
		this.nameModified = nameModified;
	}
	
	public boolean isMessageModified() {
		return messageModified;
	}

	public void setMessageModified(boolean messageModified) {
		this.messageModified = messageModified;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	
	@Override
	public int hashCode() {
		long _hashCode = 0;
		
		_hashCode = 29 * _hashCode +  errorid;
		_hashCode = 29 * _hashCode + (erroridModified ? 1 : 0);
		_hashCode = 29 * _hashCode + (int) status;
		_hashCode = 29 * _hashCode + (statusModified ? 1 : 0);
		if (name != null) {
			_hashCode = 29 * _hashCode + name.hashCode();
		}

		_hashCode = 29 * _hashCode + (nameModified ? 1 : 0);
		
		if (message != null) {
			_hashCode = 29 * _hashCode + message.hashCode();
		}

		_hashCode = 29 * _hashCode + (messageModified ? 1 : 0);
		return (int) _hashCode;
	}
	
	/*
	 * Other Methods
	 */
	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ServiceResponse)) {
			return false;
		}
		ServiceResponse other = (ServiceResponse) obj;
		if (errorid != other.errorid) {
			return false;
		}
		if (erroridModified != other.erroridModified) {
			return false;
		}
		if (message == null) {
			if (other.message != null) {
				return false;
			}
		} else if (!message.equals(other.message)) {
			return false;
		}
		if (messageModified != other.messageModified) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (nameModified != other.nameModified) {
			return false;
		}
		if (status != other.status) {
			return false;
		}
		if (statusModified != other.statusModified) {
			return false;
		}
		return true;
	}	
	
	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		StringBuffer ret = new StringBuffer();

		ret.append("ServiceResponse: ");
		ret.append(ERRORID + "=" + errorid);
		ret.append(", " + STATUS + "=" + status);
		ret.append(", " + NAME + "=" + name);
		ret.append(", " + MESSAGE + "=" + message);

		return ret.toString();
	}

	/*
	public static void main(String[] args) {
		
		ServiceResponse se = new ServiceResponse(1, 215, "bad authentication request", "username not found");

		System.out.println(se.hashCode());
		System.out.println(se.toString());

	}
	*/
}