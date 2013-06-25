package com.qpeka.db.user.dao;

import java.util.List;

import com.qpeka.db.user.exceptions.UserPointsException;
import com.qpeka.db.user.profile.UserPoints;

public interface UserPointsDao {
	
	/** 
	 * Inserts a new row in the userpoints table.
	 */
	public UserPoints insert(UserPoints userpoints) throws UserPointsException;

	/** 
	 * Updates a single row in the userpoints table.
	 */
	public void update(UserPoints olduserpoints, UserPoints userpoints) throws UserPointsException;

	/** 
	 * Deletes a single row in the userpoints table.
	 */
	public void delete(UserPoints userpoints) throws UserPointsException;

	/** 
	 * Returns all rows from the userpoints table that match the criteria 'userid = :userid AND type = :type'.
	 */
	public UserPoints findByPrimaryKey(long userid, short type) throws UserPointsException;

	/** 
	 * Returns all rows from the userpoints table that match the criteria ''.
	 */
	public List<UserPoints> findAll() throws UserPointsException;

	/** 
	 * Returns all rows from the userpoints table that match the criteria 'type = :type'.
	 */
	public List<UserPoints> findByUsertype(short type) throws UserPointsException;

	/** 
	 * Returns all rows from the userpoints table that match the criteria 'userid = :userid'.
	 */
	public List<UserPoints> findByUser(long userid) throws UserPointsException;

	/** 
	 * Returns all rows from the userpoints table that match the criteria 'userid = :userid'.
	 */
	public List<UserPoints> findWhereUseridEquals(long userid) throws UserPointsException;

	/** 
	 * Returns all rows from the userpoints table that match the criteria 'type = :type'.
	 */
	public List<UserPoints> findWhereTypeEquals(short type) throws UserPointsException;

	/** 
	 * Returns all rows from the userpoints table that match the criteria 'points = :points'.
	 */
	public List<UserPoints> findWherePointsEquals(int points) throws UserPointsException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the userpoints table that match the specified arbitrary SQL statement
	 */
	public List<UserPoints> findByDynamicSelect(String sql, List<Object> sqlParams) throws UserPointsException;

	/** 
	 * Returns all rows from the userpoints table that match the specified arbitrary SQL statement
	 */
	public List<UserPoints> findByDynamicWhere(String sql, List<Object> sqlParams) throws UserPointsException;
	
}
