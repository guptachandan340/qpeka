package com.qpeka.db.dao;

import java.util.List;
import com.qpeka.db.Files;
import com.qpeka.db.exceptions.FileException;

public interface FilesDao {
	

	/** 
	 * Inserts a new row in the files table.
	 * @throws FileException 
	 */
	public long insert(Files file) throws FileException;

	/** 
	 * Updates a single row in the files table.
	 */
	public void update(long fileid, Files file) throws FileException;

	/** 
	 * Deletes a single row in the files table.
	 * @throws FileException 
	 */
	public void delete(long fileid) throws FileException;

	/** 
	 * Returns the rows from the files table that matches the specified primary-key value.
	 */
	public Files findByPrimaryKey(long fileid) throws FileException;

	/** 
	 * Returns all rows from the files table that match the criteria ''.
	 */
	public List<Files> findAll() throws FileException;

	/** 
	 * Returns all rows from the files table that match the criteria 'fileid = :fileid'.
	 */
	public List<Files> findWhereFileidEquals(long fileid) throws FileException;

	/** 
	 * Returns all rows from the files table that match the criteria 'userid = :userid'.
	 */
	public List<Files> findWhereUseridEquals(long userid) throws FileException;

	/** 
	 * Returns all rows from the files table that match the criteria 'filetype = :filetype'.
	 */
	public List<Files> findWhereFiletypeEquals(String filetype) throws FileException;

	/** 
	 * Returns all rows from the files table that match the criteria 'filename = :filename'.
	 */
	public List<Files> findWhereFilenameEquals(String filename) throws FileException;

	/** 
	 * Returns all rows from the files table that match the criteria 'filepath = :filepath'.
	 */
	public List<Files> findWhereFilepathEquals(String filepath) throws FileException;

	/** 
	 * Returns all rows from the files table that match the criteria 'filemime = :filemime'.
	 */
	public List<Files> findWhereFilemimeEquals(String filemime) throws FileException;

	/** 
	 * Returns all rows from the files table that match the criteria 'filesize = :filesize'.
	 */
	public List<Files> findWhereFilesizeEquals(long filesize) throws FileException;

	/** 
	 * Returns all rows from the files table that match the criteria 'status = :status'.
	 */
	public List<Files> findWhereStatusEquals(int status) throws FileException;

	/** 
	 * Returns all rows from the files table that match the criteria 'timestamp = :timestamp'.
	 */
	public List<Files> findWhereTimestampEquals(long timestamp) throws FileException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the files table that match the specified arbitrary SQL statement
	 */
	public List<Files> findByDynamicSelect(String sql, List<Object> sqlParams) throws FileException;

	/** 
	 * Returns all rows from the files table that match the specified arbitrary SQL statement
	 */
	public List<Files> findByDynamicWhere(String sql, List<Object> sqlParams) throws FileException;
	
}
