package com.qpeka.db;

import java.io.Serializable;

public class Languages implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5817098627344661453L;
	public static final String LANGUAGEID = "languageid";
	public static final String LANGUAGE = "languages";
	public static final String SCRIPT = "script";
	public static final String ANATIVE = "anative";
	public static final String DIRECTION = "direction";
	public static final String ENABLED = "enabled";

	// These attributes maps to the columns of the languages table.
	private short languageid;
	private String language;
	private String script;
	private short aNative;
	
	//direction=>0 (left to right => LTR); direction=>1 (right to left => RTL)
	private short direction;
	
	//enabled=>0 (enabled); enabled=>1 (disabled);
	private short enabled;

	// These attributes represents whether the above attributes has been
	// modified since being read from the database.
	protected boolean languageidModified = false;
	protected boolean languageModified = false;
	protected boolean scriptModified = false;
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

	public Languages(short languageid, String language, String script,
			short aNative, short direction, short enabled) {
		super();
		this.languageid = languageid;
		this.language = language;
		this.script = script;
		this.aNative = aNative;
		this.direction = direction;
		this.enabled = enabled;
	}

	public Languages(String language, String name, short aNative,
			short direction, short enabled) {
		super();
		this.language = language;
		this.script = name;
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

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
		this.scriptModified = true;
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

	public boolean isScriptModified() {
		return scriptModified;
	}

	public void setScriptModified(boolean scriptModified) {
		this.scriptModified = scriptModified;
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

		if (script == null ? _cast.script != script : !script.equals(_cast.script)) {
			return false;
		}

		if (scriptModified != _cast.scriptModified) {
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
		if (script != null) {
			_hashCode = 29 * _hashCode + script.hashCode();
		}

		_hashCode = 29 * _hashCode + (scriptModified ? 1 : 0);

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
		ret.append(", " + SCRIPT + "=" + script);
		ret.append(", " + ANATIVE + "=" + aNative);
		ret.append(", " + DIRECTION + "=" + direction);
		ret.append(", " + ENABLED + "=" + enabled);
		return ret.toString();
	}

}
