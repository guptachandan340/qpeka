package com.qpeka.db;

import java.io.Serializable;

public class Country implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1371115505185244493L;

	public static final String COUNTRYID = "countryid";
	public static final String ISO2 = "iso2";
	public static final String SHORTNAME = "shortname";
	public static final String LONGNAME = "longname";
	public static final String ISO3 = "iso3";
	public static final String NUMCODE = "numcode";
	public static final String UNMEMBER = "unmember";
	public static final String CALLINGCODE = "callingcode";
	public static final String CCTLD = "cctld";
	
	/**
	 * These attributes maps to the columns of the country table.
	 */
	private short countryid;
	private String iso2;
	private String shortname;
	private String longname;
	private String iso3;
	private String numcode;
	private String unmember;
	private String callingcode;
	private String cctld;

	/**
	 * These attributes represents whether the above attributes has been
	 * modified since being read from the database.
	 */
	protected boolean countryidModified = false;
	protected boolean iso2Modified = false;
	protected boolean shortnameModified = false;
	protected boolean longnameModified = false;
	protected boolean iso3Modified = false;
	protected boolean numcodeModified = false;
	protected boolean unmemberModified = false;
	protected boolean callingcodeModified = false;
	protected boolean cctldModified = false;
	public static Country instance = null;
	/*
	 * Constructors
	 */
	public Country() {
		super();
	}

	public Country(short countryid, String iso2, String shortname,
			String longname, String iso3, String numcode, String unmember,
			String callingcode, String cctld) {
		super();
		this.countryid = countryid;
		this.iso2 = iso2;
		this.shortname = shortname;
		this.longname = longname;
		this.iso3 = iso3;
		this.numcode = numcode;
		this.unmember = unmember;
		this.callingcode = callingcode;
		this.cctld = cctld;
	}

	public static Country getInstance() {
		return (instance == null ? (instance = new Country()) : instance);
	}
	/*
	 * Getters and setters for attributes
	 */
	public short getCountryid() {
		return countryid;
	}

	public void setCountryid(short countryid) {
		this.countryid = countryid;
		this.countryidModified = true;
	}

	public String getIso2() {
		return iso2;
	}

	public void setIso2(String iso2) {
		this.iso2 = iso2;
		this.iso2Modified = true;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
		this.shortnameModified = true;
	}

	public String getLongname() {
		return longname;
	}

	public void setLongname(String longname) {
		this.longname = longname;
		this.longnameModified = true;
	}

	public String getIso3() {
		return iso3;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
		this.iso3Modified = true;
	}

	public String getNumcode() {
		return numcode;
	}

	public void setNumcode(String numcode) {
		this.numcode = numcode;
		this.numcodeModified = true;
	}

	public String getUnmember() {
		return unmember;
	}

	public void setUnmember(String unmember) {
		this.unmember = unmember;
		this.unmemberModified = true;
	}

	public String getCallingcode() {
		return callingcode;
	}

	public void setCallingcode(String callingcode) {
		this.callingcode = callingcode;
		this.callingcodeModified = true;
	}

	public String getCctld() {
		return cctld;
	}

	public void setCctld(String cctld) {
		this.cctld = cctld;
		this.cctldModified = true;
	}

	/*
	 * Getters and setters for attribute modified status
	 */
	public boolean isCountryidModified() {
		return countryidModified;
	}

	public boolean isIso2Modified() {
		return iso2Modified;
	}

	public void setIso2Modified(boolean iso2Modified) {
		this.iso2Modified = iso2Modified;
	}

	public boolean isShortnameModified() {
		return shortnameModified;
	}

	public void setShortnameModified(boolean shortnameModified) {
		this.shortnameModified = shortnameModified;
	}

	public boolean isLongnameModified() {
		return longnameModified;
	}

	public void setLongnameModified(boolean longnameModified) {
		this.longnameModified = longnameModified;
	}

	public boolean isIso3Modified() {
		return iso3Modified;
	}

	public void setIso3Modified(boolean iso3Modified) {
		this.iso3Modified = iso3Modified;
	}

	public boolean isNumcodeModified() {
		return numcodeModified;
	}

	public void setNumcodeModified(boolean numcodeModified) {
		this.numcodeModified = numcodeModified;
	}

	public boolean isUnmemberModified() {
		return unmemberModified;
	}

	public void setUnmemberModified(boolean unmemberModified) {
		this.unmemberModified = unmemberModified;
	}

	public boolean isCallingcodeModified() {
		return callingcodeModified;
	}

	public void setCallingcodeModified(boolean callingcodeModified) {
		this.callingcodeModified = callingcodeModified;
	}

	public boolean isCctldModified() {
		return cctldModified;
	}

	public void setCctldModified(boolean cctldModified) {
		this.cctldModified = cctldModified;
	}

	public void setCountryidModified(boolean countryidModified) {
		this.countryidModified = countryidModified;
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

		if (!(_other instanceof Country)) {
			return false;
		}

		final Country _cast = (Country) _other;
		if (countryid != _cast.countryid) {
			return false;
		}

		if (countryidModified != _cast.countryidModified) {
			return false;
		}

		if (iso2 == null ? _cast.iso2 != iso2 : !iso2.equals(_cast.iso2)) {
			return false;
		}

		if (iso2Modified != _cast.iso2Modified) {
			return false;
		}

		if (shortname == null ? _cast.shortname != shortname : !shortname
				.equals(_cast.shortname)) {
			return false;
		}

		if (shortnameModified != _cast.shortnameModified) {
			return false;
		}

		if (longname == null ? _cast.longname != longname : !longname
				.equals(_cast.longname)) {
			return false;
		}

		if (longnameModified != _cast.longnameModified) {
			return false;
		}

		if (iso3 == null ? _cast.iso3 != iso3 : !iso3.equals(_cast.iso3)) {
			return false;
		}

		if (iso3Modified != _cast.iso3Modified) {
			return false;
		}

		if (numcode == null ? _cast.numcode != numcode : !numcode
				.equals(_cast.numcode)) {
			return false;
		}

		if (numcodeModified != _cast.numcodeModified) {
			return false;
		}

		if (unmember == null ? _cast.unmember != unmember : !unmember
				.equals(_cast.unmember)) {
			return false;
		}

		if (unmemberModified != _cast.unmemberModified) {
			return false;
		}

		if (callingcode == null ? _cast.callingcode != callingcode
				: !callingcode.equals(_cast.callingcode)) {
			return false;
		}

		if (callingcodeModified != _cast.callingcodeModified) {
			return false;
		}

		if (cctld == null ? _cast.cctld != cctld : !cctld.equals(_cast.cctld)) {
			return false;
		}

		if (cctldModified != _cast.cctldModified) {
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

		_hashCode = 29 * _hashCode + (int) countryid;
		_hashCode = 29 * _hashCode + (countryidModified ? 1 : 0);
		if (iso2 != null) {
			_hashCode = 29 * _hashCode + iso2.hashCode();
		}

		_hashCode = 29 * _hashCode + (iso2Modified ? 1 : 0);
		if (shortname != null) {
			_hashCode = 29 * _hashCode + shortname.hashCode();
		}

		_hashCode = 29 * _hashCode + (shortnameModified ? 1 : 0);
		if (longname != null) {
			_hashCode = 29 * _hashCode + longname.hashCode();
		}

		_hashCode = 29 * _hashCode + (longnameModified ? 1 : 0);
		if (iso3 != null) {
			_hashCode = 29 * _hashCode + iso3.hashCode();
		}

		_hashCode = 29 * _hashCode + (iso3Modified ? 1 : 0);
		if (numcode != null) {
			_hashCode = 29 * _hashCode + numcode.hashCode();
		}

		_hashCode = 29 * _hashCode + (numcodeModified ? 1 : 0);
		if (unmember != null) {
			_hashCode = 29 * _hashCode + unmember.hashCode();
		}

		_hashCode = 29 * _hashCode + (unmemberModified ? 1 : 0);
		if (callingcode != null) {
			_hashCode = 29 * _hashCode + callingcode.hashCode();
		}

		_hashCode = 29 * _hashCode + (callingcodeModified ? 1 : 0);
		if (cctld != null) {
			_hashCode = 29 * _hashCode + cctld.hashCode();
		}

		_hashCode = 29 * _hashCode + (cctldModified ? 1 : 0);

		return _hashCode;
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuffer ret = new StringBuffer();
		
		ret.append( "Country: " );
		ret.append( COUNTRYID + "=" + countryid );
		ret.append( ", " + ISO2 + "=" + iso2 );
		ret.append( ", " + SHORTNAME + "=" + shortname );
		ret.append( ", " + LONGNAME + "=" + longname );
		ret.append( ", " + ISO3 + "=" + iso3 );
		ret.append( ", " + NUMCODE + "=" + numcode );
		ret.append( ", " + UNMEMBER + "=" + unmember );
		ret.append( ", " + CALLINGCODE + "=" + callingcode );
		ret.append( ", " + CCTLD + "=" + cctld );
		
		return ret.toString();
	}

}
