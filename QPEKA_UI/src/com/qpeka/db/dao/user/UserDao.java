package com.qpeka.db.dao.user;

import java.util.List;

import com.qpeka.db.exceptions.user.UserException;
import com.qpeka.db.user.User;

public interface UserDao {
	/** 
	 * Inserts a new row in the user table.
	 */
	public long insert(User user) throws UserException;

	/** 
	 * Updates a single row in the user table.
	 */
	public void update(long userid, User user) throws UserException;
	
	/**
	 * Deletes a single row in the user table.
	 */
	void delete(long userid) throws UserException;

	/** 
	 * Returns all rows from the user table that match the criteria 'userid = :userid'.
	 */
	public User findByPrimaryKey(long userid) throws UserException;

	/** 
	 * Returns all rows from the user table that match the criteria ''.
	 */
	public List<User> findAll() throws UserException;

	/** 
	 * Returns all rows from the user table that match the criteria 'userid = :userid'.
	 */
	public List<User> findWhereUseridEquals(long userid) throws UserException;

	/** 
	 * Returns all rows from the user table that match the criteria 'username = :username'.
	 */
	public List<User> findWhereUsernameEquals(String username) throws UserException;

	/** 
	 * Returns all rows from the user table that match the criteria 'password = :password'.
	 */
	public List<User> findWherePasswordEquals(String password) throws UserException;

	/** 
	 * Returns all rows from the user table that match the criteria 'email = :email'.
	 */
	public List<User> findWhereEmailEquals(String email) throws UserException;

	/** 
	 * Returns all rows from the user table that match the criteria 'created = :created'.
	 */
	public List<User> findWhereCreatedEquals(long created) throws UserException;

	/** 
	 * Returns all rows from the user table that match the criteria 'lastaccess = :lastaccess'.
	 */
	public List<User> findWhereLastaccessEquals(long lastaccess) throws UserException;

	/** 
	 * Returns all rows from the user table that match the criteria 'lastlogin = :lastlogin'.
	 */
	public List<User> findWhereLastloginEquals(long lastlogin) throws UserException;

	/** 
	 * Returns all rows from the user table that match the criteria 'status = :status'.
	 */
	public List<User> findWhereStatusEquals(short status) throws UserException;
	
	/** 
	 * Returns all rows from the user table that match the criteria 'type = :type'.
	 */
	public List<User> findWhereTypeEquals(short type) throws UserException;

	/** 
	 * Returns all rows from the user table that match the criteria 'timezone = :timezone'.
	 */
	public List<User> findWhereTimezoneEquals(String timezone) throws UserException;

	/** 
	 * Returns all rows from the user table that match the criteria 'language = :language'.
	 */
//	public List<User> findWhereLanguageEquals(short language) throws UserException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the user table that match the specified arbitrary SQL statement
	 */
	public List<User> findByDynamicSelect(String sql, List<Object> sqlParams) throws UserException;

	/** 
	 * Returns all rows from the user table that match the specified arbitrary SQL statement
	 */
	public List<User> findByDynamicWhere(String sql, List<Object> sqlParams) throws UserException;

}
