package com.qpeka.db.dao.user;

import java.util.List;

import com.qpeka.db.exceptions.user.UserLanguageException;
import com.qpeka.db.user.profile.UserLanguage;

public interface UserLanguageDao {
	
	/** 
	 * Inserts a new row in the userlanguage table.
	 */
	public UserLanguage insert(UserLanguage userlanguage) throws UserLanguageException;

	/** 
	 * Updates a single row in the userlanguage table.
	 */
	public short update(UserLanguage olduserlanguage, UserLanguage userlanguage) throws UserLanguageException;

	/** 
	 * Deletes a single row in the userlanguage table.
	 */
	public void delete(UserLanguage userlanguage) throws UserLanguageException;

	/** 
	 * Returns all rows from the userlanguage table that match the criteria 'userid = :userid AND languageid = :languageid'.
	 */
	public UserLanguage findByPrimaryKey(long userid, short languageid) throws UserLanguageException;

	/** 
	 * Returns all rows from the userlanguage table that match the criteria ''.
	 */
	public List<UserLanguage> findAll() throws UserLanguageException;

	/** 
	 * Returns all rows from the userlanguage table that match the criteria 'userid = :userid'.
	 */
	public List<UserLanguage> findWhereUseridEquals(long userid) throws UserLanguageException;

	/** 
	 * Returns all rows from the userlanguage table that match the criteria 'languageid = :languageid'.
	 */
	public List<UserLanguage> findWhereLanguageidEquals(short languageid) throws UserLanguageException;

	/** 
	 * Returns all rows from the userlanguage table that match the criteria 'type = :type'.
	 */
	public List<UserLanguage> findWhereTypeEquals(String type) throws UserLanguageException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the userlanguage table that match the specified arbitrary SQL statement
	 */
	public List<UserLanguage> findByDynamicSelect(String sql, List<Object> sqlParams) throws UserLanguageException;

	/** 
	 * Returns all rows from the userlanguage table that match the specified arbitrary SQL statement
	 */
	public List<UserLanguage> findByDynamicWhere(String sql, List<Object> sqlParams) throws UserLanguageException;
	
}
