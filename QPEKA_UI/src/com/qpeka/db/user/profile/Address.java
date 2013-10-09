package com.qpeka.db.user.profile;

import java.io.Serializable;

public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5632637867599534400L;

	public static final String ADDRESSID = "addressid";
	public static final String USERID = "userid";
	public static final String ADDRESSLINE1 = "addressline1";
	public static final String ADDRESSLINE2 = "addressline2";
	public static final String ADDRESSLINE3 = "addressline3";
	public static final String CITY = "city";
	public static final String STATE = "state";
	public static final String COUNTRY = "country";
	public static final String PINCODE = "pincode";
	public static final String TIMESTAMP = "timestamp";

	// These attributes maps to the columns of the useraddress table.
	private long addressid;
	private long userid;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String city;
	private String state;
	private short country;
	private int pincode;
	private long timestamp;

	// These attributes represents whether the above attributes has been
	// modified since being read from the database.
	protected boolean addressidModified = false;
	protected boolean useridModified = false;
	protected boolean addressLine1Modified = false;
	protected boolean addressLine2Modified = false;
	protected boolean addressLine3Modified = false;
	protected boolean cityModified = false;
	protected boolean stateModified = false;
	protected boolean countryModified = false;
	protected boolean pincodeModified = false;
	protected boolean timestampModified = false;
	
	//public static Address instance = null;
	

	/*
	 * Constructors
	 */
	public Address() {
		super();
	}

	public Address(String addressLine1, String addressLine2,
			String addressLine3, String city, String state, short country,
			int pincode) {
		super();
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressLine3 = addressLine3;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}

	public Address(long userid, String addressLine1, String addressLine2,
			String addressLine3, String city, String state, short country,
			int pincode) {
		super();
		this.userid = userid;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressLine3 = addressLine3;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}

	public Address(long addressid, long userid, String addressLine1,
			String addressLine2, String addressLine3, String city,
			String state, short country, int pincode) {
		super();
		this.addressid = addressid;
		this.userid = userid;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressLine3 = addressLine3;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}

	public static Address getInstance() {
		return new Address(); // (instance == null ? (instance = new Address()) : instance);
	}
	/*
	 * Getters and setters for attributes
	 */
	public long getAddressid() {
		return addressid;
	}

	public void setAddressid(long addressid) {
		this.addressid = addressid;
		this.addressidModified = true;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
		this.useridModified = true;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
		this.addressLine1Modified = true;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
		this.addressLine2Modified = true;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
		this.addressLine3Modified = true;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
		this.cityModified = true;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
		this.stateModified = true;
	}

	public short getCountry() {
		return country;
	}

	public void setCountry(short country) {
		this.country = country;
		this.countryModified = true;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
		this.pincodeModified = true;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
		this.timestampModified = true;
	}

	/*
	 * Getters and setters for attribute modified status
	 */
	public boolean isAddressidModified() {
		return addressidModified;
	}

	public void setAddressidModified(boolean addressidModified) {
		this.addressidModified = addressidModified;
	}

	public boolean isUseridModified() {
		return useridModified;
	}

	public void setUseridModified(boolean useridModified) {
		this.useridModified = useridModified;
	}

	public boolean isAddressLine1Modified() {
		return addressLine1Modified;
	}

	public void setAddressLine1Modified(boolean addressLine1Modified) {
		this.addressLine1Modified = addressLine1Modified;
	}

	public boolean isAddressLine2Modified() {
		return addressLine2Modified;
	}

	public void setAddressLine2Modified(boolean addressLine2Modified) {
		this.addressLine2Modified = addressLine2Modified;
	}

	public boolean isAddressLine3Modified() {
		return addressLine3Modified;
	}

	public void setAddressLine3Modified(boolean addressLine3Modified) {
		this.addressLine3Modified = addressLine3Modified;
	}

	public boolean isCityModified() {
		return cityModified;
	}

	public void setCityModified(boolean cityModified) {
		this.cityModified = cityModified;
	}

	public boolean isStateModified() {
		return stateModified;
	}

	public void setStateModified(boolean stateModified) {
		this.stateModified = stateModified;
	}

	public boolean isCountryModified() {
		return countryModified;
	}

	public void setCountryModified(boolean countryModified) {
		this.countryModified = countryModified;
	}

	public boolean isPincodeModified() {
		return pincodeModified;
	}

	public void setPincodeModified(boolean pincodeModified) {
		this.pincodeModified = pincodeModified;
	}

	public boolean isTimestampModified() {
		return timestampModified;
	}

	public void setTimestampModified(boolean timestampModified) {
		this.timestampModified = timestampModified;
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

		if (!(_other instanceof Address)) {
			return false;
		}

		final Address _cast = (Address) _other;
		if (addressid != _cast.addressid) {
			return false;
		}

		if (addressidModified != _cast.addressidModified) {
			return false;
		}

		if (userid != _cast.userid) {
			return false;
		}

		if (useridModified != _cast.useridModified) {
			return false;
		}

		if (addressLine1 == null ? _cast.addressLine1 != addressLine1
				: !addressLine1.equals(_cast.addressLine1)) {
			return false;
		}

		if (addressLine1Modified != _cast.addressLine1Modified) {
			return false;
		}

		if (addressLine2 == null ? _cast.addressLine2 != addressLine2
				: !addressLine2.equals(_cast.addressLine2)) {
			return false;
		}

		if (addressLine2Modified != _cast.addressLine2Modified) {
			return false;
		}

		if (addressLine3 == null ? _cast.addressLine3 != addressLine3
				: !addressLine3.equals(_cast.addressLine3)) {
			return false;
		}

		if (addressLine3Modified != _cast.addressLine3Modified) {
			return false;
		}

		if (city == null ? _cast.city != city : !city.equals(_cast.city)) {
			return false;
		}

		if (cityModified != _cast.cityModified) {
			return false;
		}

		if (state == null ? _cast.state != state : !state.equals(_cast.state)) {
			return false;
		}

		if (stateModified != _cast.stateModified) {
			return false;
		}

		if (country != _cast.country) {
			return false;
		}

		if (countryModified != _cast.countryModified) {
			return false;
		}

		if (pincode != _cast.pincode) {
			return false;
		}

		if (pincodeModified != _cast.pincodeModified) {
			return false;
		}
		
		if(timestamp != _cast.timestamp) {
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
		_hashCode = (int) (29 * _hashCode + addressid);
		_hashCode = 29 * _hashCode + (addressidModified ? 1 : 0);
		_hashCode = (int) (29 * _hashCode + userid);
		_hashCode = 29 * _hashCode + (useridModified ? 1 : 0);
		if (addressLine1 != null) {
			_hashCode = 29 * _hashCode + addressLine1.hashCode();
		}

		_hashCode = 29 * _hashCode + (addressLine1Modified ? 1 : 0);
		if (addressLine2 != null) {
			_hashCode = 29 * _hashCode + addressLine2.hashCode();
		}

		_hashCode = 29 * _hashCode + (addressLine2Modified ? 1 : 0);
		if (addressLine3 != null) {
			_hashCode = 29 * _hashCode + addressLine3.hashCode();
		}

		_hashCode = 29 * _hashCode + (addressLine3Modified ? 1 : 0);

		if (city != null) {
			_hashCode = 29 * _hashCode + city.hashCode();
		}

		_hashCode = 29 * _hashCode + (cityModified ? 1 : 0);
		if (state != null) {
			_hashCode = 29 * _hashCode + state.hashCode();
		}

		_hashCode = 29 * _hashCode + (stateModified ? 1 : 0);
		_hashCode = 29 * _hashCode + country;
		_hashCode = 29 * _hashCode + (countryModified ? 1 : 0);
		_hashCode = 29 * _hashCode + (int) pincode;
		_hashCode = 29 * _hashCode + (pincodeModified ? 1 : 0);
		_hashCode = (int) (29 * _hashCode + timestamp);
		_hashCode = 29 * _hashCode + (timestampModified ? 1 : 0);

		return _hashCode;
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuffer ret = new StringBuffer();

		ret.append("Address: ");
		ret.append(ADDRESSID + "=" + addressid);
		ret.append(", " + USERID + "=" + userid);
		ret.append(", " + ADDRESSLINE1 + "=" + addressLine1);
		ret.append(", " + ADDRESSLINE2 + "=" + addressLine2);
		ret.append(", " + ADDRESSLINE3 + "=" + addressLine3);
		ret.append(", " + CITY + "=" + city);
		ret.append(", " + STATE + "=" + state);
		ret.append(", " + COUNTRY + "=" + country);
		ret.append(", " + PINCODE + "=" + pincode);
		ret.append(", " + TIMESTAMP + "=" + timestamp);

		return ret.toString();
	}

	// public DBObject toDBObject()
	// {
	// BasicDBObject dbObj = new BasicDBObject();
	//
	// dbObj.put(STATE, state);
	// dbObj.put(CITY, city);
	// dbObj.put(PINCODE, pincode);
	// dbObj.put(ADDRESSLINE1, addressLine1);
	// dbObj.put(ADDRESSLINE2, addressLine2);
	// dbObj.put(ADDRESSLINE3, addressLine3);
	//
	// return dbObj;
	// }
}
