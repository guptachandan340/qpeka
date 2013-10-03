package com.qpeka.db.dao;

import java.util.List;

import com.qpeka.db.Session;
import com.qpeka.db.exceptions.user.SessionException;

public interface SessionDao {
	
	/**
	 * Inserts a new row in the sessions table.
	 */
	public long insert(Session session) throws SessionException;

	/**
	 * Updates a single row in the sessions table.
	 */
	public short update(long sessionid, Session session) throws SessionException;

	/**
	 * Deletes a single row in the sessions table.
	 */
	public void delete(long sessionid) throws SessionException;
 
	/**
	 * Returns the rows from the sessions table that matches the specified
	 * primary-key value.
	 */
	public Session findByPrimaryKey(long sessionid) throws SessionException;

	/**
	 * Returns all rows from the sessions table that match the criteria ''.
	 */
	public List<Session> findAll() throws SessionException;

	/**
	 * Returns all rows from the sessions table that match the criteria 'sessionid =
	 * :sessionid'.
	 */
	public List<Session> findWhereSessionidEquals(long sessionid) throws SessionException;
	
	/**
	 * Returns all rows from the sessions table that match the criteria 'userid =
	 * :userid'.
	 */
	public List<Session> findWhereUseridEquals(long userid) throws SessionException;

	/**
	 * Returns all rows from the sessions table that match the criteria 'hostname =
	 * :hostname'.
	 */
	public List<Session> findWhereHostnameEquals(String hostname)
			throws SessionException;

	/** 
	 * Returns all rows from the sessions table that match the criteria 'created =
	 * :created'.
	 */
	public List<Session> findWherecreatedEquals(long created) throws SessionException;

	/**
	 * Returns all rows from the sessions table that match the criteria 'session =
	 * :session'.
	 */
	public List<Session> findWhereSessionEquals(String session) throws SessionException;

	/**
	 * Returns all rows from the sessions table that match the criteria 'status =
	 * :status'.
	 */
	public List<Session> findWhereStatusEquals(short status) throws SessionException;

	/**
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/**
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/**
	 * Returns all rows from the sessions table that match the specified arbitrary
	 * SQL statement
	 */
	public List<Session> findByDynamicSelect(String sql, List<Object> sqlParams)
			throws SessionException;

	/**
	 * Returns all rows from the sessions table that match the specified arbitrary
	 * SQL statement
	 */
	public List<Session> findByDynamicWhere(String sql, List<Object> sqlParams)
			throws SessionException;
}
