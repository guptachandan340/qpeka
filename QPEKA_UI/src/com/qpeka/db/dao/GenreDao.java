package com.qpeka.db.dao;

import java.util.List;

import com.qpeka.db.Genre;
import com.qpeka.db.exceptions.GenreException;

public interface GenreDao {
	
	/** 
	 * Inserts a new row in the category table.
	 */
	public short insert(Genre genre) throws GenreException;

	/** 
	 * Updates a single row in the category table.
	 */
	public short update(short genreid, Genre genre) throws GenreException;

	/** 
	 * Deletes a single row in the category table.
	 */
	public void delete(short genreid) throws GenreException;

	/** 
	 * Returns the rows from the category table that matches the specified primary-key value.
	 */
	public Genre findByPrimaryKey(short genreid) throws GenreException;

	/** 
	 * Returns all rows from the category table that match the criteria ''.
	 */
	public List<Genre> findAll() throws GenreException;

	/** 
	 * Returns all rows from the category table that match the criteria 'genreid = :genreid'.
	 */
	public List<Genre> findWhereGenreidEquals(short genreid) throws GenreException;


	/** 
	 * Returns all rows from the category table that match the criteria 'categoryid = :categoryid'.
	 */
	public List<Genre> findWhereCategoryidEquals(short categoryid) throws GenreException;

	
	/** 
	 * Returns all rows from the category table that match the criteria 'genre = :genre'.
	 */
	public List<Genre> findWhereGenreEquals(String genre) throws GenreException;

	/** 
	 * Returns all rows from the category table that match the criteria 'points = :points'.
	 */
	public List<Genre> findWherePointsEquals(int points) throws GenreException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the category table that match the specified arbitrary SQL statement
	 */
	public List<Genre> findByDynamicSelect(String sql, List<Object> sqlParams) throws GenreException;

	/** 
	 * Returns all rows from the category table that match the specified arbitrary SQL statement
	 */
	public List<Genre> findByDynamicWhere(String sql, List<Object> sqlParams) throws GenreException;
}

