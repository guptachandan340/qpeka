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

import com.qpeka.db.conf.ResourceManager;
import com.qpeka.db.dao.UserSocialConnectionDao;
import com.qpeka.db.exceptions.QpekaException;
import com.qpeka.db.user.profile.UserSocialConnection;

public class UserSocialConnectionHandler implements UserSocialConnectionDao{

	/**
	 * The factory class for this DAO has two versions of the create() method -
	 * one that takes no arguments and one that takes a Connection argument. If
	 * the Connection version is chosen then the connection will be stored in
	 * this attribute and will be used by all calls to this DAO, otherwise a new
	 * Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger.getLogger(UserSocialConnectionHandler.class);
	
	public static UserSocialConnectionHandler instance = null;

	/**
	 * All finder methods in this class use this SELECT constant to build their
	 * queries
	 */
	protected final String SQL_SELECT = "SELECT usersocialconnid, userid, platform, socialid FROM "
			+ getTableName() + "";

	/**
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/**
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName()
			+ " ( usersocialconnid, userid, platform, socialid ) VALUES ( ?, ?, ?, ? )";

	/**
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE " + getTableName()
			+ " SET userid = ?, platform = ?, socialid = ? WHERE usersocialconnid = ?";

	/**
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName()
			+ " WHERE usersocialconnid = ?";

	/**
	 * Index of column usersocialid
	 */
	protected static final int COLUMN_USERSOCIALCONNID = 1;

	/**
	 * Index of column userid
	 */
	protected static final int COLUMN_USERID = 2;

	/**
	 * Index of column platform
	 */
	protected static final int COLUMN_PLATFORM = 3;

	/**
	 * Index of column socialid
	 */
	protected static final int COLUMN_SOCIALID = 4;

	/**
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 4;

	/**
	 * Index of primary-key column usersocialid
	 */
	protected static final int PK_COLUMN_USERSOCIALCONNID = 1;

	public UserSocialConnectionHandler() {
		super();
	}

	public UserSocialConnectionHandler(Connection userConn) {
		super();
		this.userConn = userConn;
	}

	public UserSocialConnectionHandler(Connection userConn, int maxRows) {
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
		return "qpeka.usersocialconnection";
	}

	@Override
	public long insert(UserSocialConnection userSocialConnection) throws QpekaException {
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
			sql.append("INSERT IGNORE INTO " + getTableName() + " (");
			int modifiedCount = 0;
			if (userSocialConnection.isUserSocialConnidModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("usersocialconnid");
				values.append("?");
				modifiedCount++;
			}

			if (userSocialConnection.isUseridModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("userid");
				values.append("?");
				modifiedCount++;
			}

			if (userSocialConnection.isPlatformModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("platform");
				values.append("?");
				modifiedCount++;
			}
			
			if(userSocialConnection.isSocialidModified()) {
				if(modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}
				
				sql.append("socialid");
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
			if (userSocialConnection.isUserSocialConnidModified()) {
				stmt.setLong(index++, userSocialConnection.getUsersocialconnid());
			}

			if (userSocialConnection.isUseridModified()) {
				stmt.setLong(index++, userSocialConnection.getUserid());
			}

			if (userSocialConnection.isPlatformModified()) {
				stmt.setString(index++, userSocialConnection.getPlatform());
			}
			
			if(userSocialConnection.isSocialidModified()) {
				stmt.setString(index++, userSocialConnection.getSocialid());
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ userSocialConnection);
			}

			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

			// retrieve values from auto-increment columns
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				userSocialConnection.setUsersocialconnid(rs.getLong(1));
			}
			
			reset(userSocialConnection);
			return userSocialConnection.getUsersocialconnid();
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new QpekaException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public short update(long usersocialconnid, UserSocialConnection userSocialConnection) throws QpekaException {
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
			if (userSocialConnection.isUserSocialConnidModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("usersocialconnid=?");
				modified = true;
			}

			if (userSocialConnection.isUseridModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("userid=?");
				modified = true;
			}

			if (userSocialConnection.isPlatformModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("platform=?");
				modified = true;
			}
			
			if(userSocialConnection.isSocialidModified()) {
				if(modified) {
					sql.append(", ");
				}
				
				sql.append("socialid=?");
				modified = true;				
			}

			if (!modified) {
				// nothing to update
				return -1;
			}

			sql.append(" WHERE usersocialconnid=?");
			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ userSocialConnection);
			}

			stmt = conn.prepareStatement(sql.toString());
			int index = 1;
			if (userSocialConnection.isUserSocialConnidModified()) {
				stmt.setLong(index++, userSocialConnection.getUsersocialconnid());
			}

			if (userSocialConnection.isUseridModified()) {
				stmt.setLong(index++, userSocialConnection.getUserid());
			}

			if (userSocialConnection.isPlatformModified()) {
				stmt.setString(index++, userSocialConnection.getPlatform());
			}
			
			if(userSocialConnection.isSocialidModified()) {
				stmt.setString(index++, userSocialConnection.getSocialid());
			}

			stmt.setLong(index++, usersocialconnid);
			short rows = (short)stmt.executeUpdate();
			reset(userSocialConnection);
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}
			return rows;
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new QpekaException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public void delete(long usersocialconnid) throws QpekaException {
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
				logger.debug("Executing " + SQL_DELETE + " with PK: " + usersocialconnid);
			}

			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setLong(1, usersocialconnid);
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new QpekaException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public UserSocialConnection findByPrimaryKey(long usersocialconnid) throws QpekaException {
		List<UserSocialConnection> ret = findByDynamicSelect(SQL_SELECT + " WHERE usersocialconnid = ?",
				Arrays.asList(new Object[] { new Long(usersocialconnid) }));
		return ret.size() == 0 ? null : ret.get(0);
	}

	@Override
	public List<UserSocialConnection> findAll() throws QpekaException {
		return findByDynamicSelect(SQL_SELECT + " ORDER BY usersocialconnid", null);
	}

	@Override
	public List<UserSocialConnection> findWhereUserSocialConnidEquals(long usersocialconnid) throws QpekaException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE usersocialconnid = ? ORDER BY usersocialconnid",
				Arrays.asList(new Object[] { new Long(usersocialconnid) }));
	}

	@Override
	public List<UserSocialConnection> findWhereUseridEquals(long userid) throws QpekaException {
		return findByDynamicSelect(
				SQL_SELECT + " WHERE userid = ? ORDER BY userid",
				Arrays.asList(new Object[] { userid }));
	}

	@Override
	public List<UserSocialConnection> findWherePlatformEquals(String platform)
			throws QpekaException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE platform = ? ORDER BY platform",
				Arrays.asList(new Object[] { platform }));
	}
	
	@Override
	public List<UserSocialConnection> findWhereSocialidEquals(String socialid)
			throws QpekaException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE socialid = ? ORDER BY socialid",
				Arrays.asList(new Object[] { socialid }));
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
	public List<UserSocialConnection> findByDynamicSelect(String sql, List<Object> sqlParams)
			throws QpekaException {
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
			throw new QpekaException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public List<UserSocialConnection> findByDynamicWhere(String sql, List<Object> sqlParams)
			throws QpekaException {
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
			throw new QpekaException("Exception: " + _e.getMessage(), _e);
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
	protected UserSocialConnection fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			UserSocialConnection usersocialconnection = new UserSocialConnection();
			populateUsersocialconnection(usersocialconnection, rs);
			return usersocialconnection;
		} else {
			return null;
		}

	}

	/**
	 * Fetches multiple rows from the result set
	 */
	protected List<UserSocialConnection> fetchMultiResults(ResultSet rs) throws SQLException {
		List<UserSocialConnection> resultList = new ArrayList<UserSocialConnection>();
		while (rs.next()) {
			UserSocialConnection userSocialconnection = new UserSocialConnection();
			populateUsersocialconnection(userSocialconnection, rs);
			resultList.add(userSocialconnection);
		}
		return resultList;
	}

	/**
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateUsersocialconnection(UserSocialConnection usersocialconnection, ResultSet rs) throws SQLException {
		usersocialconnection.setUsersocialconnid(rs.getLong(COLUMN_USERSOCIALCONNID));
		usersocialconnection.setUserid(rs.getLong(COLUMN_USERID));
		usersocialconnection.setPlatform(rs.getString(COLUMN_PLATFORM));
		usersocialconnection.setSocialid(rs.getString(COLUMN_SOCIALID));
		reset(usersocialconnection);
	}

	/**
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(UserSocialConnection usersocialConnection) {
		usersocialConnection.setUserSocialConnidModified(false);
		usersocialConnection.setUseridModified(false);
		usersocialConnection.setPlatformModified(false);
		usersocialConnection.setSocialidModified(false);
	}
	
	/**
	 * Get UserHandler object instance
	 * @return instance of UserHandler
	 */
	public static UserSocialConnectionHandler getInstance() {
		return (instance == null ? (instance = new UserSocialConnectionHandler()) : instance);
	}
}

