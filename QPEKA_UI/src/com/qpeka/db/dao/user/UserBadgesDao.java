package com.qpeka.db.dao.user;

import java.util.List;

import com.qpeka.db.exceptions.user.UserBadgesException;
import com.qpeka.db.user.profile.UserBadges;

public interface UserBadgesDao {
	
	/** 
	 * Inserts a new row in the userbadges table.
	 */
	public UserBadges insert(UserBadges userbadges) throws UserBadgesException;

	/** 
	 * Updates a single row in the userbadges table.
	 */
	public void update(UserBadges oldUserbadge, UserBadges userbadges) throws UserBadgesException;

	/** 
	 * Deletes a single row in the userbadges table.
	 */
	public void delete(UserBadges oldUserbadge) throws UserBadgesException;

	/** 
	 * Returns all rows from the userbadges table that match the criteria 'userid = :userid AND badgeid = :badgeid'.
	 */
	public UserBadges findByPrimaryKey(long userid, short badgeid) throws UserBadgesException;

	/** 
	 * Returns all rows from the userbadges table that match the criteria ''.
	 */
	public List<UserBadges> findAll() throws UserBadgesException;

	/** 
	 * Returns all rows from the userbadges table that match the criteria 'userid = :userid'.
	 */
	public List<UserBadges> findByUser(long userid) throws UserBadgesException;

	/** 
	 * Returns all rows from the userbadges table that match the criteria 'badgeid = :badgeid'.
	 */
	public List<UserBadges> findByBadges(short badgeid) throws UserBadgesException;

	/** 
	 * Returns all rows from the userbadges table that match the criteria 'userid = :userid'.
	 */
	public List<UserBadges> findWhereUseridEquals(long userid) throws UserBadgesException;

	/** 
	 * Returns all rows from the userbadges table that match the criteria 'badgeid = :badgeid'.
	 */
	public List<UserBadges> findWhereBadgeidEquals(short badgeid) throws UserBadgesException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the userbadges table that match the specified arbitrary SQL statement
	 */
	public List<UserBadges> findByDynamicSelect(String sql, List<Object> sqlParams) throws UserBadgesException;

	/** 
	 * Returns all rows from the userbadges table that match the specified arbitrary SQL statement
	 */
	public List<UserBadges> findByDynamicWhere(String sql, List<Object> sqlParams) throws UserBadgesException;
	
}
