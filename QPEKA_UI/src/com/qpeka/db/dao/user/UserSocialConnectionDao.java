package com.qpeka.db.dao.user;

import java.util.List;

import com.qpeka.db.exceptions.QpekaException;
import com.qpeka.db.user.profile.UserSocialConnection;

public interface UserSocialConnectionDao {

	/**
	 * Inserts a new row in the UserSocialConnection table.
	 */
	public long insert(UserSocialConnection userSocialConnection) throws QpekaException;

	/**
	 * Updates a single row in the UserSocialConnection table.
	 */
	public short update(long userSocialConnid, UserSocialConnection userSocialConnection) throws QpekaException;

	/**
	 * Deletes a single row in the UserSocialConnection table.
	 */
	public void delete(long userSocialConnid) throws QpekaException;

	/**
	 * Returns the rows from the UserSocialConnection table that matches the specified
	 * primary-key value.
	 */
	public UserSocialConnection findByPrimaryKey(long userSocialConnid) throws QpekaException;

	/**
	 * Returns all rows from the UserSocialConnection table that match the criteria ''.
	 */
	public List<UserSocialConnection> findAll () throws QpekaException;

	/**
	 * Returns all rows from the UserSocialConnection table that match the criteria 'usersocialconnid =
	 * :usersocialconnid'.
	 */
	public List<UserSocialConnection> findWhereUserSocialConnidEquals(long usersocialconnid)
			throws QpekaException;

	/**
	 * Returns all rows from the UserSocialConnection table that match the criteria 'userid =
	 * :userid'.
	 */
	public List<UserSocialConnection> findWhereUseridEquals(long userid)
			throws QpekaException;

	/**
	 * Returns all rows from the UserSocialConnection table that match the criteria 'platform =
	 * :platform'.
	 */
	public List<UserSocialConnection> findWherePlatformEquals(String platform)
			throws QpekaException;

	/**
	 * Returns all rows from the UserSocialConnection table that match the criteria 'socialid =
	 * :socialid'.
	 */
	public List<UserSocialConnection> findWhereSocialidEquals(String socialid)
			throws QpekaException;

	/**
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/**
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/**
	 * Returns all rows from the UserSocialConnection table that match the specified arbitrary
	 * SQL statement
	 */
	public List<UserSocialConnection> findByDynamicSelect(String sql, List<Object> sqlParams)
			throws QpekaException;

	/**
	 * Returns all rows from the UserSocialConnection table that match the specified arbitrary
	 * SQL statement
	 */
	public List<UserSocialConnection> findByDynamicWhere(String sql, List<Object> sqlParams)
			throws QpekaException;
}
