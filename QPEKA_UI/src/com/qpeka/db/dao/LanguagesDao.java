package com.qpeka.db.dao;

import java.util.List;

import com.qpeka.db.Languages;
import com.qpeka.db.exceptions.LanguagesException;

public interface LanguagesDao {
	
	/** 
	 * Inserts a new row in the languages table.
	 */
	public short insert(Languages language) throws LanguagesException;

	/** 
	 * Updates a single row in the languages table.
	 */
	public short update(short languageid, Languages language) throws LanguagesException;

	/** 
	 * Deletes a single row in the languages table.
	 */
	public void delete(short languageid) throws LanguagesException;

	/** 
	 * Returns the rows from the languages table that matches the specified primary-key value.
	 */
	public Languages findByPrimaryKey(short languageid) throws LanguagesException;

	/** 
	 * Returns all rows from the languages table that match the criteria ''.
	 */
	public List<Languages> findAll() throws LanguagesException;

	/** 
	 * Returns all rows from the languages table that match the criteria 'languageid = :languageid'.
	 */
	public List<Languages> findWhereLanguageidEquals(short languageid) throws LanguagesException;

	/** 
	 * Returns all rows from the languages table that match the criteria 'language = :language'.
	 */
	public List<Languages> findWhereLanguageEquals(String language) throws LanguagesException;

	/** 
	 * Returns all rows from the languages table that match the criteria 'script = :script'.
	 */
	public List<Languages> findWhereScriptEquals(String script) throws LanguagesException;

	/** 
	 * Returns all rows from the languages table that match the criteria 'native = :aNative'.
	 */
	public List<Languages> findWhereANativeEquals(String aNative) throws LanguagesException;

	/** 
	 * Returns all rows from the languages table that match the criteria 'direction = :direction'.
	 */
	public List<Languages> findWhereDirectionEquals(int direction) throws LanguagesException;

	/** 
	 * Returns all rows from the languages table that match the criteria 'enabled = :enabled'.
	 */
	public List<Languages> findWhereEnabledEquals(int enabled) throws LanguagesException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the languages table that match the specified arbitrary SQL statement
	 */
	public List<Languages> findByDynamicSelect(String sql, List<Object> sqlParams) throws LanguagesException;

	/** 
	 * Returns all rows from the languages table that match the specified arbitrary SQL statement
	 */
	public List<Languages> findByDynamicWhere(String sql, List<Object> sqlParams) throws LanguagesException;
	
}
