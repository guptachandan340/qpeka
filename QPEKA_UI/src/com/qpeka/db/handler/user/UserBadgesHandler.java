package com.qpeka.db.handler.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.qpeka.db.dao.user.UserBadgesDao;
import com.qpeka.db.exceptions.user.UserBadgesException;
import com.qpeka.db.handler.AbstractHandler;
import com.qpeka.db.user.profile.UserBadges;
import com.qpeka.utils.DBResourceHandler;

public class UserBadgesHandler extends AbstractHandler implements UserBadgesDao {

	/**
	 * The factory class for this DAO has two versions of the create() method -
	 * one that takes no arguments and one that takes a Connection argument. If
	 * the Connection version is chosen then the connection will be stored in
	 * this attribute and will be used by all calls to this DAO, otherwise a new
	 * Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger
			.getLogger(UserBadgesHandler.class);
	
	public static UserBadgesHandler instance = null;

	/**
	 * All finder methods in this class use this SELECT constant to build their
	 * queries
	 */
	protected final String SQL_SELECT = "SELECT userid, badgeid FROM "
			+ getTableName() + "";

	/**
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/**
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName()
			+ " ( userid, badgeid ) VALUES ( ?, ? )";

	/**
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE " + getTableName()
			+ " SET userid = ?, badgeid = ? WHERE userid = ? AND badgeid = ?";

	/**
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName()
			+ " WHERE userid = ? AND badgeid = ?";

	/**
	 * Index of column userid
	 */
	protected static final int COLUMN_USERID = 1;

	/**
	 * Index of column badgeid
	 */
	protected static final int COLUMN_BADGEID = 2;

	/**
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 2;

	/**
	 * Index of primary-key column userid
	 */
	protected static final int PK_COLUMN_USERID = 1;

	/**
	 * Index of primary-key column badgeid
	 */
	protected static final int PK_COLUMN_BADGEID = 2;

	public UserBadgesHandler() {
		super();
	}

	public UserBadgesHandler(Connection userConn) {
		super();
		this.userConn = userConn;
	}

	public UserBadgesHandler(Connection userConn, int maxRows) {
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
		return "qpeka.userbadges";
	}

	@Override
	public UserBadges insert(UserBadges userbadges) throws UserBadgesException {
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			// get the user-specified connection or get a connection from the
			// DBResourceHandler
			conn = isConnSupplied ? userConn : DBResourceHandler.getConnection();

			StringBuffer sql = new StringBuffer();
			StringBuffer values = new StringBuffer();
			sql.append("INSERT INTO " + getTableName() + " (");
			int modifiedCount = 0;
			if (userbadges.isUseridModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("userid");
				values.append("?");
				modifiedCount++;
			}

			if (userbadges.isBadgeidModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("badgeid");
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
			if (userbadges.isUseridModified()) {
				stmt.setLong(index++, userbadges.getUserid());
			}

			if (userbadges.isBadgeidModified()) {
				stmt.setShort(index++, userbadges.getBadgeid());
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ userbadges);
			}

			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

			reset(userbadges);
			return userbadges;
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new UserBadgesException("Exception: " + _e.getMessage(), _e);
		} finally {
			DBResourceHandler.close(stmt);
			if (!isConnSupplied) {
				DBResourceHandler.close(conn);
			}

		}
	}

	@Override
	public short update(UserBadges oldUserbadge, UserBadges userbadges)
			throws UserBadgesException {
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			// get the user-specified connection or get a connection from the
			// DBResourceHandler
			conn = isConnSupplied ? userConn : DBResourceHandler.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE " + getTableName() + " SET ");
			boolean modified = false;
			if (userbadges.isUseridModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("userid=?");
				modified = true;
			}

			if (userbadges.isBadgeidModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("badgeid=?");
				modified = true;
			}

			if (!modified) {
				// nothing to update
				return -1;
			}

			sql.append(" WHERE userid=? AND badgeid=?");
			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ userbadges);
			}

			stmt = conn.prepareStatement(sql.toString());
			int index = 1;
			if (userbadges.isUseridModified()) {
				stmt.setLong(index++, userbadges.getUserid());
			}

			if (userbadges.isBadgeidModified()) {
				stmt.setShort(index++, userbadges.getBadgeid());
			}

			stmt.setLong(index++, oldUserbadge.getUserid());
			stmt.setShort(index++, oldUserbadge.getBadgeid());
			short rows = (short)stmt.executeUpdate();

			reset(userbadges);

			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}
			return rows;
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new UserBadgesException("Exception: " + _e.getMessage(), _e);
		} finally {
			DBResourceHandler.close(stmt);
			if (!isConnSupplied) {
				DBResourceHandler.close(conn);
			}

		}
	}

	@Override
	public void delete(UserBadges oldUserbadge) throws UserBadgesException {
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			// get the user-specified connection or get a connection from the
			// DBResourceHandler
			conn = isConnSupplied ? userConn : DBResourceHandler.getConnection();

			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + SQL_DELETE + " with PK: "
						+ oldUserbadge);
			}

			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setLong(1, oldUserbadge.getUserid());
			stmt.setShort(2, oldUserbadge.getBadgeid());
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new UserBadgesException("Exception: " + _e.getMessage(), _e);
		} finally {
			DBResourceHandler.close(stmt);
			if (!isConnSupplied) {
				DBResourceHandler.close(conn);
			}

		}
	}

	@Override
	public UserBadges findByPrimaryKey(long userid, short badgeid)
			throws UserBadgesException {
		List<UserBadges> ret = findByDynamicSelect(
				SQL_SELECT + " WHERE userid = ? AND badgeid = ?",
				Arrays.asList(new Object[] { new Long(userid),
						new Short(badgeid) }));
		return ret.size() == 0 ? null : ret.get(0);
	}

	@Override
	public List<UserBadges> findAll() throws UserBadgesException {
		return findByDynamicSelect(SQL_SELECT + " ORDER BY userid, badgeid",
				null);
	}

	@Override
	public List<UserBadges> findByUser(long userid) throws UserBadgesException {
		return findByDynamicSelect(SQL_SELECT + " WHERE userid = ?",
				Arrays.asList(new Object[] { new Long(userid) }));
	}

	@Override
	public List<UserBadges> findByBadges(short badgeid)
			throws UserBadgesException {
		return findByDynamicSelect(SQL_SELECT + " WHERE badgeid = ?",
				Arrays.asList(new Object[] { new Short(badgeid) }));
	}

	@Override
	public List<UserBadges> findWhereUseridEquals(long userid)
			throws UserBadgesException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE userid = ? ORDER BY userid",
				Arrays.asList(new Object[] { new Long(userid) }));
	}

	@Override
	public List<UserBadges> findWhereBadgeidEquals(short badgeid)
			throws UserBadgesException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE badgeid = ? ORDER BY badgeid",
				Arrays.asList(new Object[] { new Integer(badgeid) }));
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
	public List<UserBadges> findByDynamicSelect(String sql,
			List<Object> sqlParams) throws UserBadgesException {
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			// get the user-specified connection or get a connection from the
			// DBResourceHandler
			conn = isConnSupplied ? userConn : DBResourceHandler.getConnection();

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
			throw new UserBadgesException("Exception: " + _e.getMessage(), _e);
		} finally {
			DBResourceHandler.close(rs);
			DBResourceHandler.close(stmt);
			if (!isConnSupplied) {
				DBResourceHandler.close(conn);
			}

		}
	}

	@Override
	public List<UserBadges> findByDynamicWhere(String sql,
			List<Object> sqlParams) throws UserBadgesException {
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			// get the user-specified connection or get a connection from the
			// DBResourceHandler
			conn = isConnSupplied ? userConn : DBResourceHandler.getConnection();

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
			throw new UserBadgesException("Exception: " + _e.getMessage(), _e);
		} finally {
			DBResourceHandler.close(rs);
			DBResourceHandler.close(stmt);
			if (!isConnSupplied) {
				DBResourceHandler.close(conn);
			}

		}
	}

	/**
	 * Fetches a single row from the result set
	 */
	protected UserBadges fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			UserBadges userbadges = new UserBadges();
			populateUserBadges(userbadges, rs);
			return userbadges;
		} else {
			return null;
		}

	}

	/**
	 * Fetches multiple rows from the result set
	 */
	protected List<UserBadges> fetchMultiResults(ResultSet rs)
			throws SQLException {
		List<UserBadges> resultList = new ArrayList<UserBadges>();
		while (rs.next()) {
			UserBadges userbadges = new UserBadges();
			populateUserBadges(userbadges, rs);
			resultList.add(userbadges);
		}

		return resultList;
	}

	/**
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateUserBadges(UserBadges userbadges, ResultSet rs)
			throws SQLException {
		userbadges.setUserid(rs.getInt(COLUMN_USERID));
		userbadges.setBadgeid(rs.getShort(COLUMN_BADGEID));
		reset(userbadges);
	}

	/**
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(UserBadges userbadges) {
		userbadges.setUseridModified(false);
		userbadges.setBadgeidModified(false);
	}
	
	/**
	 * Get UserHandler object instance
	 * @return instance of UserHandler
	 */
	public static UserBadgesHandler getInstance() {
		return (instance == null ? (instance = new UserBadgesHandler()) : instance);
	}

}
