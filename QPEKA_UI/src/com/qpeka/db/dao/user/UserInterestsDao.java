package com.qpeka.db.dao.user;

import java.util.List;

import com.qpeka.db.exceptions.user.UserInterestsException;
import com.qpeka.db.user.profile.UserInterests;

public interface UserInterestsDao {

	/**
	 * Inserts a new row in the userinterests table.
	 */
	public UserInterests insert(UserInterests userinterests)
			throws UserInterestsException;

	/**
	 * Updates a single row in the userinterests table.
	 */
	public short update(UserInterests olduserinterests, UserInterests userinterests)
			throws UserInterestsException;

	/**
	 * Deletes a single row in the userinterests table.
	 */
	public void delete(UserInterests userinterests)
			throws UserInterestsException;

	/**
	 * Returns all rows from the userinterests table that match the criteria
	 * 'userid = :userid AND genreid = :genreid'.
	 */
	public UserInterests findByPrimaryKey(long userid, short genreid)
			throws UserInterestsException;

	/**
	 * Returns all rows from the userinterests table that match the criteria ''.
	 */
	public List<UserInterests> findAll() throws UserInterestsException;

	/**
	 * Returns all rows from the userinterests table that match the criteria
	 * 'userid = :userid'.
	 */
	public List<UserInterests> findByUser(long userid)
			throws UserInterestsException;

	/**
	 * Returns all rows from the userinterests table that match the criteria
	 * 'genreid = :genreid'.
	 */
	public List<UserInterests> findByGenre(short genreid)
			throws UserInterestsException;

	/**
	 * Returns all rows from the userinterests table that match the criteria
	 * 'userid = :userid'.
	 */
	public List<UserInterests> findWhereUseridEquals(long userid)
			throws UserInterestsException;

	/**
	 * Returns all rows from the userinterests table that match the criteria
	 * 'categoryid = :categoryid'.
	 */
	public List<UserInterests> findWhereGenreidEquals(short genreid)
			throws UserInterestsException;

	/**
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/**
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/**
	 * Returns all rows from the userinterests table that match the specified
	 * arbitrary SQL statement
	 */
	public List<UserInterests> findByDynamicSelect(String sql,
			List<Object> sqlParams) throws UserInterestsException;

	/**
	 * Returns all rows from the userinterests table that match the specified
	 * arbitrary SQL statement
	 */
	public List<UserInterests> findByDynamicWhere(String sql,
			List<Object> sqlParams) throws UserInterestsException;

}
