package com.qpeka.db.user.session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.qpeka.db.conf.ResourceManager;
import com.qpeka.db.exceptions.user.SessionException;
import com.qpeka.db.handler.AbstractHandler;

public class SessionHandler extends AbstractHandler implements SessionDao {

	/**
	 * The factory class for this DAO has two versions of the create() method -
	 * one that takes no arguments and one that takes a Connection argument. If
	 * the Connection version is chosen then the connection will be stored in
	 * this attribute and will be used by all calls to this DAO, otherwise a new
	 * Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger
			.getLogger(SessionHandler.class);

	/**
	 * All finder methods in this class use this SELECT constant to build their
	 * queries
	 */
	protected final String SQL_SELECT = "SELECT userid, sessionid, hostname, "
			+ "timestamp, session FROM " + getTableName() + "";

	/**
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/**
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName()
			+ " ( userid, sessionid, hostname, timestamp, session ) "
			+ "VALUES ( ?, ?, ?, ?, ? )";

	/**
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE " + getTableName()
			+ " SET userid = ?, sessionid = ?, hostname = ?, "
			+ "timestamp = ?, session = ? WHERE sessionid = ?";

	/**
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName()
			+ " WHERE sessionid = ?";

	/**
	 * Index of column userid
	 */
	protected static final int COLUMN_USERID = 1;

	/**
	 * Index of column sessionid
	 */
	protected static final int COLUMN_SESSIONID = 2;

	/**
	 * Index of column hostname
	 */
	protected static final int COLUMN_HOSTNAME = 3;

	/**
	 * Index of column timestamp
	 */
	protected static final int COLUMN_TIMESTAMP = 4;

	/**
	 * Index of column session
	 */
	protected static final int COLUMN_SESSION = 5;

	/**
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 5;

	/**
	 * Index of primary-key column sessionid
	 */
	protected static final int PK_COLUMN_SESSIONID = 1;

	public SessionHandler() {
		super();
	}

	public SessionHandler(Connection userConn) {
		super();
		this.userConn = userConn;
	}

	public SessionHandler(Connection userConn, int maxRows) {
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
		return "qpeka.sessions";
	}

	@Override
	public long insert(Sessions session) throws SessionException {
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
			if (session.isUseridModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("userid");
				values.append("?");
				modifiedCount++;
			}

			if (session.isSessionidModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("sessionid");
				values.append("?");
				modifiedCount++;
			}

			if (session.isHostnameModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("hostname");
				values.append("?");
				modifiedCount++;
			}

			if (session.isTimestampModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("timestamp");
				values.append("?");
				modifiedCount++;
			}

			if (session.isSessionModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("session");
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
			stmt = conn.prepareStatement(sql.toString());
			int index = 1;
			if (session.isUseridModified()) {
				stmt.setLong(index++, session.getUserid());
			}

			if (session.isSessionidModified()) {
				stmt.setLong(index++, session.getSessionid());
			}

			if (session.isHostnameModified()) {
				stmt.setString(index++, session.getHostname());
			}

			if (session.isTimestampModified()) {
				stmt.setLong(index++, session.getTimestamp());
			}

			if (session.isSessionModified()) {
				stmt.setString(index++, session.getSession());
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ session);
			}

			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

			// retrieve values from auto-increment columns
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				session.setSessionid(rs.getLong(1));
			}
			
			reset(session);
			return session.getSessionid();
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new SessionException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public void update(long sessionid, Sessions session)
			throws SessionException {
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
			if (session.isUseridModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("userid=?");
				modified = true;
			}

			if (session.isSessionidModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("sessionid=?");
				modified = true;
			}

			if (session.isHostnameModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("hostname=?");
				modified = true;
			}

			if (session.isTimestampModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("timestamp=?");
				modified = true;
			}

			if (session.isSessionModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("session=?");
				modified = true;
			}

			if (!modified) {
				// nothing to update
				return;
			}

			sql.append(" WHERE sessionid=?");
			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ session);
			}

			stmt = conn.prepareStatement(sql.toString());
			int index = 1;
			if (session.isUseridModified()) {
				stmt.setLong(index++, session.getUserid());
			}

			if (session.isSessionidModified()) {
				stmt.setLong(index++, session.getSessionid());
			}

			if (session.isHostnameModified()) {
				stmt.setString(index++, session.getHostname());
			}

			if (session.isTimestampModified()) {
				stmt.setLong(index++, session.getTimestamp());
			}

			if (session.isSessionModified()) {
				stmt.setString(index++, session.getSession());
			}

			stmt.setLong(index++, sessionid);
			int rows = stmt.executeUpdate();
			reset(session);
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new SessionException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public void delete(long sessionid) throws SessionException {
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
				logger.debug("Executing " + SQL_DELETE + " with PK: "
						+ sessionid);
			}

			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setLong(1, sessionid);
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new SessionException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public Sessions findByPrimaryKey(long sessionid) throws SessionException {
		List<Sessions> ret = findByDynamicSelect(SQL_SELECT
				+ " WHERE sessionid = ?",
				Arrays.asList(new Object[] { new Long(sessionid) }));
		return ret.size() == 0 ? null : ret.get(0);
	}

	@Override
	public List<Sessions> findAll() throws SessionException {
		return findByDynamicSelect(SQL_SELECT + " ORDER BY sessionid", null);
	}

	@Override
	public List<Sessions> findByUser(long userid) throws SessionException {
		return findByDynamicSelect(SQL_SELECT + " WHERE userid = ?",
				Arrays.asList(new Object[] { new Long(userid) }));
	}

	@Override
	public List<Sessions> findWhereUseridEquals(long userid)
			throws SessionException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE userid = ? ORDER BY userid",
				Arrays.asList(new Object[] { new Long(userid) }));
	}

	@Override
	public List<Sessions> findWhereSessionidEquals(long sessionid)
			throws SessionException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE sessionid = ? ORDER BY sessionid",
				Arrays.asList(new Object[] { new Long(sessionid) }));
	}

	@Override
	public List<Sessions> findWhereHostnameEquals(String hostname)
			throws SessionException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE hostname = ? ORDER BY hostname",
				Arrays.asList(new Object[] { hostname }));
	}

	@Override
	public List<Sessions> findWhereTimestampEquals(int timestamp)
			throws SessionException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE timestamp = ? ORDER BY timestamp",
				Arrays.asList(new Object[] { new Integer(timestamp) }));
	}

	@Override
	public List<Sessions> findWhereSessionEquals(String session)
			throws SessionException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE session = ? ORDER BY session",
				Arrays.asList(new Object[] { session }));
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
	public List<Sessions> findByDynamicSelect(String sql, List<Object> sqlParams)
			throws SessionException {
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
			throw new SessionException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public List<Sessions> findByDynamicWhere(String sql, List<Object> sqlParams)
			throws SessionException {
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
			throw new SessionException("Exception: " + _e.getMessage(), _e);
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
	protected Sessions fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			Sessions session = new Sessions();
			populateSession(session, rs);
			return session;
		} else {
			return null;
		}

	}

	/**
	 * Fetches multiple rows from the result set
	 */
	protected List<Sessions> fetchMultiResults(ResultSet rs)
			throws SQLException {
		List<Sessions> resultList = new ArrayList<Sessions>();
		while (rs.next()) {
			Sessions session = new Sessions();
			populateSession(session, rs);
			resultList.add(session);
		}

		return resultList;
	}

	/**
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateSession(Sessions session, ResultSet rs)
			throws SQLException {
		session.setUserid(rs.getInt(COLUMN_USERID));
		session.setSessionid(rs.getInt(COLUMN_SESSIONID));
		session.setHostname(rs.getString(COLUMN_HOSTNAME));
		session.setTimestamp(rs.getInt(COLUMN_TIMESTAMP));
		session.setSession(rs.getString(COLUMN_SESSION));
		reset(session);
	}

	/**
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(Sessions session) {
		session.setUseridModified(false);
		session.setSessionidModified(false);
		session.setHostnameModified(false);
		session.setTimestampModified(false);
		session.setSessionModified(false);
	}

}
