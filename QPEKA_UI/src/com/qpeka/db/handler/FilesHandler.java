package com.qpeka.db.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.qpeka.db.Files;
import com.qpeka.db.conf.ResourceManager;
import com.qpeka.db.dao.FilesDao;
import com.qpeka.db.exceptions.FileException;

public class FilesHandler extends AbstractHandler implements FilesDao {

	/**
	 * The factory class for this DAO has two versions of the create() method -
	 * one that takes no arguments and one that takes a Connection argument. If
	 * the Connection version is chosen then the connection will be stored in
	 * this attribute and will be used by all calls to this DAO, otherwise a new
	 * Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger.getLogger(FilesHandler.class);

	public static FilesHandler instance = null;

	/**
	 * All finder methods in this class use this SELECT constant to build their
	 * queries
	 */
	protected final String SQL_SELECT = "SELECT fileid, userid, filetype, filename, filepath, filemime, extension, filesize, status, timestamp FROM "
			+ getTableName() + "";

	/**
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/**
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO "
			+ getTableName()
			+ " ( fileid, userid, filetype, filename, filepath, filemime, extension, filesize, status, timestamp ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	/**
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE "
			+ getTableName()
			+ " SET fileid = ?, userid = ?, filetype = ?, filename = ?, filepath = ?, filemime = ?, extension = ?, filesize = ?, status = ?, timestamp = ? WHERE fileid = ?";

	/**
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName()
			+ " WHERE fileid = ?";

	/**
	 * Index of column fileid
	 */
	protected static final int COLUMN_FILEID = 1;

	/**
	 * Index of column userid
	 */
	protected static final int COLUMN_USERID = 2;

	/**
	 * Index of column filetype
	 */
	protected static final int COLUMN_FILETYPE = 3;

	/**
	 * Index of column filename
	 */
	protected static final int COLUMN_FILENAME = 4;

	/**
	 * Index of column filepath
	 */
	protected static final int COLUMN_FILEPATH = 5;

	/**
	 * Index of column filemime
	 */
	protected static final int COLUMN_FILEMIME = 6;

	/**
	 * Index of column extension
	 */
	protected static final int COLUMN_EXTENSION = 7;

	/**
	 * Index of column filesize
	 */
	protected static final int COLUMN_FILESIZE = 8;

	/**
	 * Index of column status
	 */
	protected static final int COLUMN_STATUS = 9;

	/**
	 * Index of column timestamp
	 */
	protected static final int COLUMN_TIMESTAMP = 10;

	/**
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 10;

	/**
	 * Index of primary-key column fileid
	 */
	protected static final int PK_COLUMN_FILEID = 1;

	public FilesHandler() {
		super();
	}

	public FilesHandler(Connection userConn, int maxRows) {
		super();
		this.userConn = userConn;
		this.maxRows = maxRows;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName() {
		return "qpeka.files";
	}

	@Override
	public long insert(Files file) throws FileException {
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			// get the user-specified connection or get a connection from the
			// ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();

			StringBuffer sql = new StringBuffer();
			StringBuffer values = new StringBuffer();
			sql.append("INSERT INTO " + getTableName() + " (");
			int modifiedCount = 0;
			if (file.isFileidModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("fileid");
				values.append("?");
				modifiedCount++;
			}

			if (file.isUseridModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("userid");
				values.append("?");
				modifiedCount++;
			}

			if (file.isFiletypeModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("filetype");
				values.append("?");
				modifiedCount++;
			}

			if (file.isFilenameModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("filename");
				values.append("?");
				modifiedCount++;
			}

			if (file.isFilepathModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("filepath");
				values.append("?");
				modifiedCount++;
			}

			if (file.isFilemimeModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("filemime");
				values.append("?");
				modifiedCount++;
			}

			if (file.isExtensionModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("extension");
				values.append("?");
				modifiedCount++;
			}

			
			if (file.isFilesizeModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("filesize");
				values.append("?");
				modifiedCount++;
			}

			if (file.isStatusModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("status");
				values.append("?");
				modifiedCount++;
			}

			if (file.isTimestampModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("timestamp");
				values.append("?");
				modifiedCount++;
			}

			if (modifiedCount == 0) {
				// nothing to insert
				throw new IllegalStateException("Nothing to insert");
			}

			sql.append(") VALUES (");
			sql.append(values);
			sql.append(")");
			stmt = conn.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			int index = 1;
			if (file.isFileidModified()) {
				stmt.setLong(index++, file.getFileid());
			}

			if (file.isUseridModified()) {
				stmt.setLong(index++, file.getUserid());
			}

			if (file.isFiletypeModified()) {
				stmt.setString(index++, file.getFiletype());
			}

			if (file.isFilenameModified()) {
				stmt.setString(index++, file.getFilename());
			}

			if (file.isFilepathModified()) {
				stmt.setString(index++, file.getFilepath());
			}

			if (file.isFilemimeModified()) {
				stmt.setString(index++, file.getFilemime());
			}
			
			if (file.isExtensionModified()) {
				stmt.setString(index++, file.getExtension());
			}

			if (file.isFilesizeModified()) {
				stmt.setInt(index++, file.getFilesize());
			}

			if (file.isStatusModified()) {
				stmt.setInt(index++, file.getStatus());
			}

			if (file.isTimestampModified()) {
				stmt.setLong(index++, file.getTimestamp());
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ file);
			}

			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

			// retrieve values from auto-increment columns
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				file.setFileid(rs.getInt(1));
			}

			reset(file);
			return file.getFileid();
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new FileException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public short update(long fileid, Files file) throws FileException {
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			// get the user-specified connection or get a connection from the
			// ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE " + getTableName() + " SET ");
			boolean modified = false;
			if (file.isFileidModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("fileid=?");
				modified = true;
			}

			if (file.isUseridModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("userid=?");
				modified = true;
			}

			if (file.isFiletypeModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("filetype=?");
				modified = true;
			}

			if (file.isFilenameModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("filename=?");
				modified = true;
			}

			if (file.isFilepathModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("filepath=?");
				modified = true;
			}

			if (file.isFilemimeModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("filemime=?");
				modified = true;
			}
			
			if (file.isExtensionModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("extension=?");
				modified = true;
			}

			if (file.isFilesizeModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("filesize=?");
				modified = true;
			}

			if (file.isStatusModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("status=?");
				modified = true;
			}

			if (file.isTimestampModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("timestamp=?");
				modified = true;
			}

			if (!modified) {
				// nothing to update
				return -1;
			}

			sql.append(" WHERE fileid=?");
			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ file);
			}

			stmt = conn.prepareStatement(sql.toString());
			int index = 1;
			if (file.isFileidModified()) {
				stmt.setLong(index++, file.getFileid());
			}

			if (file.isUseridModified()) {
				stmt.setLong(index++, file.getUserid());
			}

			if (file.isFiletypeModified()) {
				stmt.setString(index++, file.getFiletype());
			}

			if (file.isFilenameModified()) {
				stmt.setString(index++, file.getFilename());
			}

			if (file.isFilepathModified()) {
				stmt.setString(index++, file.getFilepath());
			}

			if (file.isFilemimeModified()) {
				stmt.setString(index++, file.getFilemime());
			}
			
			if (file.isExtensionModified()) {
				stmt.setString(index++, file.getExtension());
			}

			if (file.isFilesizeModified()) {
				stmt.setInt(index++, file.getFilesize());
			}

			if (file.isStatusModified()) {
				stmt.setInt(index++, file.getStatus());
			}

			if (file.isTimestampModified()) {
				stmt.setLong(index++, file.getTimestamp());
			}

			stmt.setLong(index++, fileid);
			short rows = (short)stmt.executeUpdate();
			reset(file);
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}
			return rows;
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new FileException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public void delete(long fileid) throws FileException {
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			// get the user-specified connection or get a connection from the
			// ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();

			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + SQL_DELETE + " with PK: " + fileid);
			}

			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setLong(1, fileid);
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new FileException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public Files findByPrimaryKey(long fileid) throws FileException {
		List<Files> ret = findByDynamicSelect(SQL_SELECT + " WHERE fileid = ?",
				Arrays.asList(new Object[] { new Long(fileid) }));
		return ret.size() == 0 ? null : ret.get(0);
	}

	@Override
	public List<Files> findAll() throws FileException {
		return findByDynamicSelect(SQL_SELECT + " ORDER BY fileid", null);
	}

	@Override
	public List<Files> findWhereFileidEquals(long fileid) throws FileException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE fileid = ? ORDER BY fileid",
				Arrays.asList(new Object[] { new Long(fileid) }));
	}

	@Override
	public List<Files> findWhereUseridEquals(long userid) throws FileException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE userid = ? ORDER BY userid",
				Arrays.asList(new Object[] { new Long(userid) }));
	}

	@Override
	public List<Files> findWhereFiletypeEquals(String filetype)
			throws FileException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE filetype = ? ORDER BY filetype",
				Arrays.asList(new Object[] { filetype }));
	}

	@Override
	public List<Files> findWhereFilenameEquals(String filename)
			throws FileException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE filename = ? ORDER BY filename",
				Arrays.asList(new Object[] { filename }));
	}

	@Override
	public List<Files> findWhereFilepathEquals(String filepath)
			throws FileException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE filepath = ? ORDER BY filepath",
				Arrays.asList(new Object[] { filepath }));
	}

	@Override
	public List<Files> findWhereFilemimeEquals(String filemime)
			throws FileException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE filemime = ? ORDER BY filemime",
				Arrays.asList(new Object[] { filemime }));
	}
	
	@Override
	public List<Files> findWhereExtensionEquals(String extension)
			throws FileException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE extension = ? ORDER BY extension",
				Arrays.asList(new Object[] { extension }));
	}

	@Override
	public List<Files> findWhereFilesizeEquals(long filesize)
			throws FileException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE filesize = ? ORDER BY filesize",
				Arrays.asList(new Object[] { new Long(filesize) }));
	}

	@Override
	public List<Files> findWhereStatusEquals(int status) throws FileException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE status = ? ORDER BY status",
				Arrays.asList(new Object[] { new Integer(status) }));
	}

	@Override
	public List<Files> findWhereTimestampEquals(long timestamp)
			throws FileException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE timestamp = ? ORDER BY timestamp",
				Arrays.asList(new Object[] { new Long(timestamp) }));
	}

	@Override
	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}

	@Override
	public int getMaxRows() {
		return maxRows;
	}

	@Override
	public List<Files> findByDynamicSelect(String sql, List<Object> sqlParams)
			throws FileException {
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the
			// ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();

			// construct the SQL statement
			final String SQL = sql;

			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + SQL);
			}
			
			// prepare statement
			stmt = conn.prepareStatement(SQL);
			stmt.setMaxRows(maxRows);
			
			// bind parameters
			for (int counter = 0; sqlParams != null
					&& counter < sqlParams.size(); counter++) {
				stmt.setObject(counter + 1, sqlParams.get(counter));
			}
			rs = stmt.executeQuery();

			// fetch the results
			return fetchMultiResults(rs);
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new FileException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public List<Files> findByDynamicWhere(String sql, List<Object> sqlParams)
			throws FileException {
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		

		try {
			// get the user-specified connection or get a connection from the
			// ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();

			// construct the SQL statement
			final String SQL = SQL_SELECT + " WHERE " + sql;
			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + SQL);
			}
			
			// prepare statement
			stmt = conn.prepareStatement(SQL);
			stmt.setMaxRows(maxRows);
			// bind parameters
			for (int counter = 0; sqlParams != null
					&& counter < sqlParams.size(); counter++) {
				System.out.println(sqlParams.get(counter));
				stmt.setObject(counter + 1, sqlParams.get(counter));
			}
			rs = stmt.executeQuery();
			// fetch the results
			return fetchMultiResults(rs);
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new FileException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	/**
	 * Fetches a single row from the result set
	 */
	protected Files fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			Files files = new Files();
			populateFiles(files, rs);
			return files;
		} else {
			return null;
		}

	}

	/**
	 * Fetches multiple rows from the result set
	 */
	protected List<Files> fetchMultiResults(ResultSet rs) throws SQLException {
		List<Files> resultList = new ArrayList<Files>();
		while (rs.next()) {
			Files files = new Files();
			populateFiles(files, rs);
			resultList.add(files);
		}

		return resultList;
	}

	/**
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateFiles(Files files, ResultSet rs) throws SQLException {
		files.setFileid(rs.getInt(COLUMN_FILEID));
		files.setUserid(rs.getInt(COLUMN_USERID));
		files.setFiletype(rs.getString(COLUMN_FILETYPE));
		files.setFilename(rs.getString(COLUMN_FILENAME));
		files.setFilepath(rs.getString(COLUMN_FILEPATH));
		files.setFilemime(rs.getString(COLUMN_FILEMIME));
		files.setExtension(rs.getString(COLUMN_EXTENSION));
		files.setFilesize(rs.getInt(COLUMN_FILESIZE));
		files.setStatus(rs.getInt(COLUMN_STATUS));
		files.setTimestamp(rs.getLong(COLUMN_TIMESTAMP));
		reset(files);
	}

	/**
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(Files files) {
		files.setFileidModified(false);
		files.setUseridModified(false);
		files.setFiletypeModified(false);
		files.setFilenameModified(false);
		files.setFilepathModified(false);
		files.setFilemimeModified(false);
		files.setExtensionModified(false);
		files.setFilesizeModified(false);
		files.setStatusModified(false);
		files.setTimestampModified(false);
	}

	/**
	 * Get FilesHandler object instance
	 * 
	 * @return instance of FilesHandler
	 */
	public static FilesHandler getInstance() {
		return (instance == null ? (instance = new FilesHandler()) : instance);
	}

}
