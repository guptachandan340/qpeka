package com.qpeka.db.user.session;

import java.util.List;

import com.qpeka.db.user.exceptions.SessionException;

public interface SessionDao {

	/** 
	 * Inserts a new row in the sessions table.
	 */
	public long insert(Sessions session) throws SessionException;

	/** 
	 * Updates a single row in the sessions table.
	 */
	public void update(long sessionid, Sessions session) throws SessionException;

	/** 
	 * Deletes a single row in the sessions table.
	 */
	public void delete(long sessionid) throws SessionException;

	/** 
	 * Returns the rows from the sessions table that matches the specified primary-key value.
	 */
	public Sessions findByPrimaryKey(long sessionid) throws SessionException;

	/** 
	 * Returns all rows from the sessions table that match the criteria ''.
	 */
	public List<Sessions> findAll() throws SessionException;

	/** 
	 * Returns all rows from the sessions table that match the criteria 'userid = :userid'.
	 */
	public List<Sessions> findByUser(long userid) throws SessionException;

	/** 
	 * Returns all rows from the sessions table that match the criteria 'userid = :userid'.
	 */
	public List<Sessions> findWhereUseridEquals(long userid) throws SessionException;

	/** 
	 * Returns all rows from the sessions table that match the criteria 'sessionid = :sessionid'.
	 */
	public List<Sessions> findWhereSessionidEquals(long sessionid) throws SessionException;

	/** 
	 * Returns all rows from the sessions table that match the criteria 'hostname = :hostname'.
	 */
	public List<Sessions> findWhereHostnameEquals(String hostname) throws SessionException;

	/** 
	 * Returns all rows from the sessions table that match the criteria 'timestamp = :timestamp'.
	 */
	public List<Sessions> findWhereTimestampEquals(int timestamp) throws SessionException;

	/** 
	 * Returns all rows from the sessions table that match the criteria 'session = :session'.
	 */
	public List<Sessions> findWhereSessionEquals(String session) throws SessionException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the sessions table that match the specified arbitrary SQL statement
	 */
	public List<Sessions> findByDynamicSelect(String sql, List<Object> sqlParams) throws SessionException;

	/** 
	 * Returns all rows from the sessions table that match the specified arbitrary SQL statement
	 */
	public List<Sessions> findByDynamicWhere(String sql, List<Object> sqlParams) throws SessionException;
	
}
