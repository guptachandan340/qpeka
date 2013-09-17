package com.qpeka.db.handler.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.qpeka.db.UserFieldVisibility;
import com.qpeka.db.conf.ResourceManager;
import com.qpeka.db.dao.user.UserFieldVisibilityDao;
import com.qpeka.db.exceptions.user.UserFieldVisibilityException;


public class UserFieldVisibilityHandler implements UserFieldVisibilityDao {

	/**
	 * The factory class for this DAO has two versions of the create() method -
	 * one that takes no arguments and one that takes a Connection argument. If
	 * the Connection version is chosen then the connection will be stored in
	 * this attribute and will be used by all calls to this DAO, otherwise a new
	 * Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger.getLogger(UserFieldVisibilityHandler.class);
	
	public static UserFieldVisibilityHandler instance = null;

	/**
	 * All finder methods in this class use this SELECT constant to build their
	 * queries
	 */
	protected final String SQL_SELECT = "SELECT visibilityid, userid, fieldname, status FROM "
			+ getTableName() + "";

	/**
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/**
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName()
			+ " ( visibilityid, userid, fieldname, status ) VALUES ( ?, ?, ?, ? )";

	/**
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE " + getTableName()
			+ " SET visibilityid = ?, fieldname = ?, status = ? WHERE visibilityid = ?";

	/**
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName()
			+ " WHERE visibilityid = ?";

	/**
	 * Index of column visibilityid
	 */
	protected static final int COLUMN_VISIBILITYID = 1;

	/**
	 * Index of column userid
	 */
	protected static final int COLUMN_USERID = 2;

	/**
	 * Index of column fieldname
	 */
	protected static final int COLUMN_FIELDNAME = 3;

	/**
	 * Index of column status
	 */
	protected static final int COLUMN_STATUS = 4;

	/**
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 4;

	/**
	 * Index of primary-key column visibilityid
	 */
	protected static final int PK_COLUMN_VISIBILITYID = 1;

	public UserFieldVisibilityHandler() {
		super();
	}

	public UserFieldVisibilityHandler(Connection userConn) {
		super();
		this.userConn = userConn;
	}

	public UserFieldVisibilityHandler(Connection userConn, int maxRows) {
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
		return "qpeka.userfieldvisibility";
	}

	@Override
	public long insert(UserFieldVisibility userFieldVisibility) throws UserFieldVisibilityException {
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
			if (userFieldVisibility.isVisibilityidModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("visibilityid");
				values.append("?");
				modifiedCount++;
			}

			if (userFieldVisibility.isUseridModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("userid");
				values.append("?");
				modifiedCount++;
			}

			if (userFieldVisibility.isFieldNameModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("fieldname");
				values.append("?");
				modifiedCount++;
			}
			
			if(userFieldVisibility.isStatusModified()) {
				if(modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}
				
				sql.append("status");
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
			stmt = conn.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
			int index = 1;
			if (userFieldVisibility.isVisibilityidModified()) {
				stmt.setLong(index++, userFieldVisibility.getVisibilityid());
			}

			if (userFieldVisibility.isUseridModified()) {
				stmt.setLong(index++, userFieldVisibility.getUserid());
			}

			if (userFieldVisibility.isFieldNameModified()) {
				stmt.setString(index++, userFieldVisibility.getFieldName());
			}
			
			if(userFieldVisibility.isStatusModified()) {
				stmt.setShort(index++, userFieldVisibility.getStatus());
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ userFieldVisibility);
			}

			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

			// retrieve values from auto-increment columns
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				userFieldVisibility.setVisibilityid(rs.getShort(1));
			}

			reset(userFieldVisibility);
			return userFieldVisibility.getVisibilityid();
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new UserFieldVisibilityException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public short update(long visibilityid, UserFieldVisibility userFieldVisibility) throws UserFieldVisibilityException {
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
			if (userFieldVisibility.isVisibilityidModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("visibilityid=?");
				modified = true;
			}

			if (userFieldVisibility.isUseridModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("userid=?");
				modified = true;
			}

			if (userFieldVisibility.isFieldNameModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("fieldname=?");
				modified = true;
			}
			
			if(userFieldVisibility.isStatusModified()) {
				if(modified) {
					sql.append(", ");
				}
				
				sql.append("status=?");
				modified = true;				
			}

			if (!modified) {
				// nothing to update
				return -1;
			}

			sql.append(" WHERE visibilityid=?");
			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ userFieldVisibility);
			}

			stmt = conn.prepareStatement(sql.toString());
			int index = 1;
			if (userFieldVisibility.isVisibilityidModified()) {
				stmt.setLong(index++, userFieldVisibility.getVisibilityid());
			}

			if (userFieldVisibility.isUseridModified()) {
				stmt.setLong(index++, userFieldVisibility.getUserid());
			}

			if (userFieldVisibility.isFieldNameModified()) {
				stmt.setString(index++, userFieldVisibility.getFieldName());
			}
			
			if(userFieldVisibility.isStatusModified()) {
				stmt.setShort(index++, userFieldVisibility.getStatus());
			}

			stmt.setLong(index++, visibilityid);
			short rows = (short)stmt.executeUpdate();
			reset(userFieldVisibility);
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}
			return rows;
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new UserFieldVisibilityException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public void delete(long visibilityid) throws UserFieldVisibilityException {
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
				logger.debug("Executing " + SQL_DELETE + " with PK: " + visibilityid);
			}

			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setLong(1, visibilityid);
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new UserFieldVisibilityException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public UserFieldVisibility findByPrimaryKey(long visibilityid) throws UserFieldVisibilityException {
		List<UserFieldVisibility> ret = findByDynamicSelect(SQL_SELECT + " WHERE visibilityid = ?",
				Arrays.asList(new Object[] { new Long(visibilityid) }));
		return ret.size() == 0 ? null : ret.get(0);
	}

	@Override
	public List<UserFieldVisibility> findAll() throws UserFieldVisibilityException {
		return findByDynamicSelect(SQL_SELECT + " ORDER BY visibilityid", null);
	}

	@Override
	public List<UserFieldVisibility> findWhereVisibilityidEquals(long visibilityid) throws UserFieldVisibilityException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE visibilityid = ? ORDER BY visibilityid",
				Arrays.asList(new Object[] { new Long(visibilityid) }));
	}

	@Override
	public List<UserFieldVisibility> findWhereUseridEquals(long userid) throws UserFieldVisibilityException {
		return findByDynamicSelect(
				SQL_SELECT + " WHERE userid = ? ORDER BY userid",
				Arrays.asList(new Object[] { userid }));
	}

	@Override
	public List<UserFieldVisibility> findWhereFieldNameEquals(String fieldName)
			throws UserFieldVisibilityException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE fieldname = ? ORDER BY fieldname",
				Arrays.asList(new Object[] { fieldName }));
	}
	
	@Override
	public List<UserFieldVisibility> findWhereStatusEquals(short status)
			throws UserFieldVisibilityException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE status = ? ORDER BY status",
				Arrays.asList(new Object[] { status }));
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
	public List<UserFieldVisibility> findByDynamicSelect(String sql, List<Object> sqlParams)
			throws UserFieldVisibilityException {
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
			throw new UserFieldVisibilityException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public List<UserFieldVisibility> findByDynamicWhere(String sql, List<Object> sqlParams)
			throws UserFieldVisibilityException {
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
				stmt.setObject(counter + 1, sqlParams.get(counter));
			}

			rs = stmt.executeQuery();

			// fetch the results
			return fetchMultiResults(rs);
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new UserFieldVisibilityException("Exception: " + _e.getMessage(), _e);
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
	protected UserFieldVisibility fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			UserFieldVisibility userFieldVisibility = new UserFieldVisibility();
			populateUserFieldVisibility(userFieldVisibility, rs);
			return userFieldVisibility;
		} else {
			return null;
		}

	}

	/**
	 * Fetches multiple rows from the result set
	 */
	protected List<UserFieldVisibility> fetchMultiResults(ResultSet rs) throws SQLException {
		List<UserFieldVisibility> resultList = new ArrayList<UserFieldVisibility>();
		while (rs.next()) {
			UserFieldVisibility userFieldVisibility = new UserFieldVisibility();
			populateUserFieldVisibility(userFieldVisibility, rs);
			resultList.add(userFieldVisibility);
		}

		return resultList;
	}

	/**
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateUserFieldVisibility(UserFieldVisibility userFieldVisibility, ResultSet rs) throws SQLException {
		userFieldVisibility.setVisibilityid(rs.getLong(COLUMN_VISIBILITYID));
		userFieldVisibility.setUserid(rs.getLong(COLUMN_USERID));
		userFieldVisibility.setFieldName(rs.getString(COLUMN_FIELDNAME));
		userFieldVisibility.setStatus(rs.getShort(COLUMN_STATUS));
		reset(userFieldVisibility);
	}

	/**
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(UserFieldVisibility userFieldVisibility) {
		userFieldVisibility.setVisibilityidModified(false);
		userFieldVisibility.setUseridModified(false);
		userFieldVisibility.setFieldNameModified(false);
		userFieldVisibility.setStatusModified(false);
	}
	
	/**
	 * Get UserHandler object instance
	 * @return instance of UserHandler
	 */
	public static UserFieldVisibilityHandler getInstance() {
		return (instance == null ? (instance = new UserFieldVisibilityHandler()) : instance);
	}
}

