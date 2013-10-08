package com.qpeka.db;

import java.io.Serializable;

import com.qpeka.db.handler.CategoryHandler;

public class Genre implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2504970655008686968L;
	public static final String GENREID = "genreid";
	public static final String CATEGORYID = "categoryid";
	public static final String GENRE = "genre";
	public static final String POINTS = "points";
	
	// These attributes maps to the columns of the category table.
	private short genreid;
	private short categoryid;
	private String genre;
	private int points;
	
	// These attributes represents whether the above attributes has been
	// modified since being read from the database.
	protected boolean genreidModified = false;
	protected boolean categoryidModified = false;
	protected boolean genreModified = false;
	protected boolean pointsModified = false;
	//public static Genre instance = null;
	/*
	 * Constructors
	 */
	public Genre() {
		super();
	}

	public Genre(short genreid, short categoryid, String genre, int points) {
		super();
		this.genreid = genreid;
		this.categoryid = categoryid;
		this.genre = genre;
		this.points = points;
	}

	public Genre(short categoryid, String genre, int points) {
		super();
		this.categoryid = categoryid;
		this.genre = genre;
		this.points = points;
	}

	public static Genre getInstance() {
		return new Genre(); // (instance == null ? (instance = new Genre()) : instance);
	}
	
	/*
	 * Getters and setters for attributes
	 */
	public short getGenreid() {
		return genreid;
	}

	public void setGenreid(short genreid) {
		this.genreid = genreid;
		this.genreidModified = true;
	}
	
	public short getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(short categoryid) {
		this.categoryid = categoryid;
		this.categoryidModified = true;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
		this.genreModified = true;
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
	
	public boolean isGenreidModified() {
		return genreidModified;
	}

	public void setGenreidModified(boolean genreidModified) {
		this.genreidModified = genreidModified;
	}

	public boolean isCategoryidModified() {
		return categoryidModified;
	}

	public void setCategoryidModified(boolean categoryidModified) {
		this.categoryidModified = categoryidModified;
	}

	public boolean isGenreModified() {
		return genreModified;
	}

	public void setGenreModified(boolean genreModified) {
		this.genreModified = genreModified;
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
		
		if (!(_other instanceof Genre)) {
			return false;
		}
		
		final Genre _cast = (Genre) _other;
		if (genreid != _cast.genreid) {
			return false;
		}
		
		if (genreidModified != _cast.genreidModified) {
			return false;
		}
		if (categoryid != _cast.categoryid) {
			return false;
		}
		
		if (categoryidModified != _cast.categoryidModified) {
			return false;
		}
		
		if (genre == null ? _cast.genre != genre : !genre.equals( _cast.genre )) {
			return false;
		}
		
		if (genreModified != _cast.genreModified) {
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
		_hashCode = 29 * _hashCode + (short) genreid;
		_hashCode = 29 * _hashCode + (genreidModified ? 1 : 0);
		_hashCode = 29 * _hashCode + (short) categoryid;
		_hashCode = 29 * _hashCode + (categoryidModified ? 1 : 0);
		if (genre != null) {
			_hashCode = 29 * _hashCode + genre.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (genreModified ? 1 : 0);
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
		ret.append( "Genre: " );
		ret.append(GENREID + "=" + genreid);
		ret.append(", " + CATEGORYID + "=" + categoryid );
		ret.append( ", " + GENRE + "=" + genre );
		ret.append( ", " + POINTS + "=" + points );
		
		return ret.toString();
	}

}
