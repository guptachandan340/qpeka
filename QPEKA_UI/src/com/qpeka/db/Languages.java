package com.qpeka.db;

import java.io.Serializable;

public class Languages implements Serializable {

	public static final String LANGUAGEID = "languageid";
	public static final String LANGUAGE = "language";
	public static final String NAME = "name";
	public static final String ANATIVE = "anative";
	public static final String DIRECTION = "direction";
	public static final String ENABLED = "enabled";

	// These attributes maps to the columns of the languages table.
	private short languageid;
	private String language;
	private String name;
	private short aNative;
	private short direction;
	private short enabled;

	// These attributes represents whether the above attributes has been
	// modified since being read from the database.
	protected boolean languageidModified = false;
	protected boolean languageModified = false;
	protected boolean nameModified = false;
	protected boolean aNativeModified = false;
	protected boolean directionModified = false;
	protected boolean enabledModified = false;
	public static Languages instance = null;
	/*
	 * Constructors
	 */
	public Languages() {
		super();
	}

	public Languages(short languageid, String language, String name,
			short aNative, short direction, short enabled) {
		super();
		this.languageid = languageid;
		this.language = language;
		this.name = name;
		this.aNative = aNative;
		this.direction = direction;
		this.enabled = enabled;
	}

	public Languages(String language, String name, short aNative,
			short direction, short enabled) {
		super();
		this.language = language;
		this.name = name;
		this.aNative = aNative;
		this.direction = direction;
		this.enabled = enabled;
	}
	
	public static Languages getInstance() {
		return (instance == null ? (instance = new Languages()) : instance);
	}
	/*
	 * Getters and setters for attributes
	 */
	public short getLanguageid() {
		return languageid;
	}

	public void setLanguageid(short languageid) {
		this.languageid = languageid;
		this.languageidModified = true;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
		this.languageModified = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.nameModified = true;
	}

	public short getANative() {
		return aNative;
	}

	public void setANative(short aNative) {
		this.aNative = aNative;
		this.aNativeModified = true;
	}

	public short getDirection() {
		return direction;
	}

	public void setDirection(short direction) {
		this.direction = direction;
		this.directionModified = true;
	}

	public short getEnabled() {
		return enabled;
	}

	public void setEnabled(short enabled) {
		this.enabled = enabled;
		this.enabledModified = true;
	}

	/*
	 * Getters and setters for attribute modified status
	 */
	public boolean isLanguageidModified() {
		return languageidModified;
	}

	public void setLanguageidModified(boolean languageidModified) {
		this.languageidModified = languageidModified;
	}

	public boolean isLanguageModified() {
		return languageModified;
	}

	public void setLanguageModified(boolean languageModified) {
		this.languageModified = languageModified;
	}

	public boolean isNameModified() {
		return nameModified;
	}

	public void setNameModified(boolean nameModified) {
		this.nameModified = nameModified;
	}

	public boolean isANativeModified() {
		return aNativeModified;
	}

	public void setANativeModified(boolean aNativeModified) {
		this.aNativeModified = aNativeModified;
	}

	public boolean isDirectionModified() {
		return directionModified;
	}

	public void setDirectionModified(boolean directionModified) {
		this.directionModified = directionModified;
	}

	public boolean isEnabledModified() {
		return enabledModified;
	}

	public void setEnabledModified(boolean enabledModified) {
		this.enabledModified = enabledModified;
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

		if (!(_other instanceof Languages)) {
			return false;
		}

		final Languages _cast = (Languages) _other;
		if (languageid != _cast.languageid) {
			return false;
		}

		if (languageidModified != _cast.languageidModified) {
			return false;
		}

		if (language == null ? _cast.language != language : !language
				.equals(_cast.language)) {
			return false;
		}

		if (languageModified != _cast.languageModified) {
			return false;
		}

		if (name == null ? _cast.name != name : !name.equals(_cast.name)) {
			return false;
		}

		if (nameModified != _cast.nameModified) {
			return false;
		}

		if (aNative != _cast.aNative) {
			return false;
		}

		if (aNativeModified != _cast.aNativeModified) {
			return false;
		}

		if (direction != _cast.direction) {
			return false;
		}

		if (directionModified != _cast.directionModified) {
			return false;
		}

		if (enabled != _cast.enabled) {
			return false;
		}

		if (enabledModified != _cast.enabledModified) {
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
		_hashCode = 29 * _hashCode + (int) languageid;
		_hashCode = 29 * _hashCode + (languageidModified ? 1 : 0);
		if (language != null) {
			_hashCode = 29 * _hashCode + language.hashCode();
		}

		_hashCode = 29 * _hashCode + (languageModified ? 1 : 0);
		if (name != null) {
			_hashCode = 29 * _hashCode + name.hashCode();
		}

		_hashCode = 29 * _hashCode + (nameModified ? 1 : 0);

		_hashCode = 29 * _hashCode + (aNativeModified ? 1 : 0);
		_hashCode = 29 * _hashCode + direction;
		_hashCode = 29 * _hashCode + (directionModified ? 1 : 0);
		_hashCode = 29 * _hashCode + enabled;
		_hashCode = 29 * _hashCode + (enabledModified ? 1 : 0);
		return _hashCode;
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("Languages: ");
		ret.append(LANGUAGEID + "=" + languageid);
		ret.append(", " + LANGUAGE + "=" + language);
		ret.append(", " + NAME + "=" + name);
		ret.append(", " + ANATIVE + "=" + aNative);
		ret.append(", " + DIRECTION + "=" + direction);
		ret.append(", " + ENABLED + "=" + enabled);
		return ret.toString();
	}

}
