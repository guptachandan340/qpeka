package com.qpeka.db.dao.user;

import java.util.List;

import com.qpeka.db.exceptions.user.UserException;
import com.qpeka.db.exceptions.user.UserFieldVisibilityException;
import com.qpeka.db.user.User;
import com.qpeka.db.user.profile.UserFieldVisibility;

public interface UserFieldVisibilityDao {
	/** 
	 * Inserts a new row in the UserFieldVisibility table.
	 */
	public long insert(UserFieldVisibility userFieldVisibility) throws UserFieldVisibilityException;

	/** 
	 * Updates a single row in the UserFieldVisibility table.
	 */
	public short update(long userid, UserFieldVisibility userFieldVisibility) throws UserFieldVisibilityException;
	
	/**
	 * Deletes a single row in the UserFieldVisibility table.
	 */
	void delete(long visibilityid) throws UserFieldVisibilityException;

	/** 
	 * Returns all rows from the UserFieldVisibility table that match the criteria 'visibilityid = :visibilityid'.
	 */
	public UserFieldVisibility findByPrimaryKey(long visibilityid) throws UserFieldVisibilityException;

	/** 
	 * Returns all rows from the UserFieldVisibility table that match the criteria ''.
	 */
	public List<UserFieldVisibility> findAll() throws UserFieldVisibilityException;

	/** 
	 * Returns all rows from the UserFieldVisibility table that match the criteria 'visibilityid = :visibilityid'.
	 */
	public List<UserFieldVisibility> findWhereVisibilityidEquals(long visibilityid) throws UserFieldVisibilityException;

	/** 
	 * Returns all rows from the UserFieldVisibility table that match the criteria 'userid = :userid'.
	 */
	public List<UserFieldVisibility> findWhereUseridEquals(long userid) throws UserFieldVisibilityException;

	/** 
	 * Returns all rows from the user table that match the criteria 'fieldName = :fieldName'.
	 */
	public List<UserFieldVisibility> findWhereFieldNameEquals(String fieldName) throws UserFieldVisibilityException;

	/** 
	 * Returns all rows from the user table that match the criteria 'status = :status'.
	 */
	public List<UserFieldVisibility> findWhereStatusEquals(short status) throws UserFieldVisibilityException;


	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the UserFieldVisibility table that match the specified arbitrary SQL statement
	 */
	public List<UserFieldVisibility> findByDynamicSelect(String sql, List<Object> sqlParams) throws UserFieldVisibilityException;

	/** 
	 * Returns all rows from the user table that match the specified arbitrary SQL statement
	 */
	public List<UserFieldVisibility> findByDynamicWhere(String sql, List<Object> sqlParams) throws UserFieldVisibilityException;

}
