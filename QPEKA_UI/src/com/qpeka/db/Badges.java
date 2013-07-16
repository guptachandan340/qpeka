package com.qpeka.db;

import java.io.Serializable;

public class Badges implements Serializable {

	public static Badges instance =null;
	public static final String BADGEID = "badgeid";
	public static final String TYPEID = "typeid";
	public static final String BADGE = "badge";
	public static final String LEVEL = "level";
	public static final String POINTS = "points";
	
	// These attributes maps to the columns of the badges table.
	private short badgeid;
	private short typeid;
	private String badge;
	private short level;
	private int points;

	// These attributes represents whether the above attributes has been
	// modified since being read from the database.
	protected boolean badgeidModified = false;
	protected boolean typeidModified = false;
	protected boolean badgeModified = false;
	protected boolean levelModified = false;
	protected boolean pointsModified = false;

	/*
	 * Constructors
	 */
	public Badges() {
		super();
	}

	public Badges(short badgeid, short typeid, String badge, short level,
			int points) {
		super();
		this.badgeid = badgeid;
		this.typeid = typeid;
		this.badge = badge;
		this.level = level;
		this.points = points;
	}

	public Badges(short typeid, String badge, short level, int points) {
		super();
		this.typeid = typeid;
		this.badge = badge;
		this.level = level;
		this.points = points;
	}

	public static Badges getInstance() {
		return (instance == null ? instance = new Badges() : instance);
	}
	/*
	 * Getters and setters for attributes
	 */
	public short getBadgeid() {
		return badgeid;
	}

	public void setBadgeid(short badgeid) {
		this.badgeid = badgeid;
		this.badgeidModified = true;
	}

	public short getTypeid() {
		return typeid;
	}

	public void setTypeid(short typeid) {
		this.typeid = typeid;
		this.typeidModified = true;
	}

	public String getBadge() {
		return badge;
	}

	public void setBadge(String badge) {
		this.badge = badge;
		this.badgeModified = true;
	}

	public short getLevel() {
		return level;
	}

	public void setLevel(short level) {
		this.level = level;
		this.levelModified = true;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
		this.pointsModified = true;
	}

	/*
	 * Getters and setters for attribute modified status
	 */
	public boolean isBadgeidModified() {
		return badgeidModified;
	}

	public void setBadgeidModified(boolean badgeidModified) {
		this.badgeidModified = badgeidModified;
	}

	public boolean isTypeidModified() {
		return typeidModified;
	}

	public void setTypeidModified(boolean typeidModified) {
		this.typeidModified = typeidModified;
	}

	public boolean isBadgeModified() {
		return badgeModified;
	}

	public void setBadgeModified(boolean badgeModified) {
		this.badgeModified = badgeModified;
	}

	public boolean isLevelModified() {
		return levelModified;
	}

	public void setLevelModified(boolean levelModified) {
		this.levelModified = levelModified;
	}

	public boolean isPointsModified() {
		return pointsModified;
	}

	public void setPointsModified(boolean pointsModified) {
		this.pointsModified = pointsModified;
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
		
		if (!(_other instanceof Badges)) {
			return false;
		}
		
		final Badges _cast = (Badges) _other;
		if (badgeid != _cast.badgeid) {
			return false;
		}
		
		if (badgeidModified != _cast.badgeidModified) {
			return false;
		}
		
		if (typeid != _cast.typeid) {
			return false;
		}
		
		if (typeidModified != _cast.typeidModified) {
			return false;
		}
		
		if (badge == null ? _cast.badge != badge : !badge.equals( _cast.badge )) {
			return false;
		}
		
		if (badgeModified != _cast.badgeModified) {
			return false;
		}
		
		if (level != _cast.level) {
			return false;
		}
		
		if (levelModified != _cast.levelModified) {
			return false;
		}
		
		if (points != _cast.points) {
			return false;
		}
		
		if (pointsModified != _cast.pointsModified) {
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
		_hashCode = 29 * _hashCode + badgeid;
		_hashCode = 29 * _hashCode + (badgeidModified ? 1 : 0);
		_hashCode = 29 * _hashCode + (int) typeid;
		_hashCode = 29 * _hashCode + (typeidModified ? 1 : 0);
		if (badge != null) {
			_hashCode = 29 * _hashCode + badge.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (badgeModified ? 1 : 0);
		_hashCode = 29 * _hashCode + (int) level;
		_hashCode = 29 * _hashCode + (levelModified ? 1 : 0);
		_hashCode = 29 * _hashCode + points;
		_hashCode = 29 * _hashCode + (pointsModified ? 1 : 0);
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
		ret.append( "Badges: " );
		ret.append( "badgeid=" + badgeid );
		ret.append( ", typeid=" + typeid );
		ret.append( ", badge=" + badge );
		ret.append( ", level=" + level );
		ret.append( ", points=" + points );
		return ret.toString();
	}
	
}