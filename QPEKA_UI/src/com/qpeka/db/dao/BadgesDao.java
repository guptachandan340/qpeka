package com.qpeka.db.dao;

import java.util.List;

import com.qpeka.db.Badges;
import com.qpeka.db.exceptions.BadgesException;

public interface BadgesDao {

	/**
	 * Inserts a new row in the badges table.
	 */
	public short insert(Badges badges) throws BadgesException;

	/**
	 * Updates a single row in the badges table.
	 */
	public short update(short badgeid, Badges badges) throws BadgesException;

	/**
	 * Deletes a single row in the badges table.
	 */
	public void delete(short badgeid) throws BadgesException;

	/**
	 * Returns the rows from the badges table that matches the specified
	 * primary-key value.
	 */
	public Badges findByPrimaryKey(short badgeid) throws BadgesException;

	/**
	 * Returns all rows from the badges table that match the criteria ''.
	 */
	public List<Badges> findAll() throws BadgesException;

	/**
	 * Returns all rows from the badges table that match the criteria 'typeid =
	 * :typeid'.
	 */
	public List<Badges> findByUsertype(short typeid) throws BadgesException;

	/**
	 * Returns all rows from the badges table that match the criteria 'badgeid =
	 * :badgeid'.
	 */
	public List<Badges> findWhereBadgeidEquals(short badgeid)
			throws BadgesException;

	/**
	 * Returns all rows from the badges table that match the criteria 'typeid =
	 * :typeid'.
	 */
	public List<Badges> findWhereTypeidEquals(short typeid)
			throws BadgesException;

	/**
	 * Returns all rows from the badges table that match the criteria 'badge =
	 * :badge'.
	 */
	public List<Badges> findWhereBadgeEquals(String badge)
			throws BadgesException;

	/**
	 * Returns all rows from the badges table that match the criteria 'level =
	 * :level'.
	 */
	public List<Badges> findWhereLevelEquals(short level)
			throws BadgesException;

	/**
	 * Returns all rows from the badges table that match the criteria 'points =
	 * :points'.
	 */
	public List<Badges> findWherePointsEquals(int points)
			throws BadgesException;

	/**
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/**
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/**
	 * Returns all rows from the badges table that match the specified arbitrary
	 * SQL statement
	 */
	public List<Badges> findByDynamicSelect(String sql, List<Object> sqlParams)
			throws BadgesException;

	/**
	 * Returns all rows from the badges table that match the specified arbitrary
	 * SQL statement
	 */
	public List<Badges> findByDynamicWhere(String sql, List<Object> sqlParams)
			throws BadgesException;

}
