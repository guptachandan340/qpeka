package com.qpeka.db.handler.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.qpeka.db.ResourceManager;
import com.qpeka.db.dao.user.UserPointsDao;
import com.qpeka.db.exceptions.user.UserPointsException;
import com.qpeka.db.handler.AbstractHandler;
import com.qpeka.db.user.profile.UserPoints;

public class UserPointsHandler extends AbstractHandler implements UserPointsDao {

	/**
	 * The factory class for this DAO has two versions of the create() method -
	 * one that takes no arguments and one that takes a Connection argument. If
	 * the Connection version is chosen then the connection will be stored in
	 * this attribute and will be used by all calls to this DAO, otherwise a new
	 * Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger
			.getLogger(UserPointsHandler.class);

	/**
	 * All finder methods in this class use this SELECT constant to build their
	 * queries
	 */
	protected final String SQL_SELECT = "SELECT userid, type, points FROM "
			+ getTableName() + "";

	/**
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/**
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName()
			+ " ( userid, type, points ) VALUES ( ?, ?, ? )";

	/**
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE "
			+ getTableName()
			+ " SET userid = ?, type = ?, points = ? WHERE userid = ? AND type = ?";

	/**
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName()
			+ " WHERE userid = ? AND type = ?";

	/**
	 * Index of column userid
	 */
	protected static final int COLUMN_USERID = 1;

	/**
	 * Index of column type
	 */
	protected static final int COLUMN_TYPE = 2;

	/**
	 * Index of column points
	 */
	protected static final int COLUMN_POINTS = 3;

	/**
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 3;

	/**
	 * Index of primary-key column userid
	 */
	protected static final int PK_COLUMN_USERID = 1;

	/**
	 * Index of primary-key column type
	 */
	protected static final int PK_COLUMN_TYPE = 2;

	public UserPointsHandler() {
		super();
	}

	public UserPointsHandler(Connection userConn) {
		super();
		this.userConn = userConn;
	}

	public UserPointsHandler(Connection userConn, int maxRows) {
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
		return "qpeka.userpoints";
	}

	@Override
	public UserPoints insert(UserPoints userpoints) throws UserPointsException {

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
			if (userpoints.isUseridModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("userid");
				values.append("?");
				modifiedCount++;
			}

			if (userpoints.isTypeModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("type");
				values.append("?");
				modifiedCount++;
			}

			if (userpoints.isPointsModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("points");
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
			if (userpoints.isUseridModified()) {
				stmt.setLong(index++, userpoints.getUserid());
			}

			if (userpoints.isTypeModified()) {
				stmt.setShort(index++, userpoints.getType());
			}

			if (userpoints.isPointsModified()) {
				stmt.setLong(index++, userpoints.getPoints());
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ userpoints);
			}

			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

			reset(userpoints);
			return userpoints;
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new UserPointsException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}

	}

	@Override
	public void update(UserPoints olduserpoints, UserPoints userpoints)
			throws UserPointsException {
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
			if (userpoints.isUseridModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("userid=?");
				modified = true;
			}

			if (userpoints.isTypeModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("type=?");
				modified = true;
			}

			if (userpoints.isPointsModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("points=?");
				modified = true;
			}

			if (!modified) {
				// nothing to update
				return;
			}

			sql.append(" WHERE userid=? AND type=?");
			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ userpoints);
			}

			stmt = conn.prepareStatement(sql.toString());
			int index = 1;
			if (userpoints.isUseridModified()) {
				stmt.setLong(index++, userpoints.getUserid());
			}

			if (userpoints.isTypeModified()) {
				stmt.setShort(index++, userpoints.getType());
			}

			if (userpoints.isPointsModified()) {
				stmt.setLong(index++, userpoints.getPoints());
			}

			stmt.setLong(index++, olduserpoints.getUserid());
			stmt.setShort(index++, olduserpoints.getType());
			int rows = stmt.executeUpdate();

			reset(userpoints);
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new UserPointsException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public void delete(UserPoints userpoints) throws UserPointsException {
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
						+ userpoints);
			}

			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setLong(1, userpoints.getUserid());
			stmt.setShort(2, userpoints.getType());
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new UserPointsException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public UserPoints findByPrimaryKey(long userid, short type)
			throws UserPointsException {
		List<UserPoints> ret = findByDynamicSelect(SQL_SELECT
				+ " WHERE userid = ? AND type = ?", Arrays.asList(new Object[] {
				new Long(userid), new Short(type) }));
		return ret.size() == 0 ? null : ret.get(0);
	}

	@Override
	public List<UserPoints> findAll() throws UserPointsException {
		return findByDynamicSelect(SQL_SELECT + " ORDER BY userid, type", null);
	}

	@Override
	public List<UserPoints> findByUsertype(short type)
			throws UserPointsException {
		return findByDynamicSelect(SQL_SELECT + " WHERE type = ?",
				Arrays.asList(new Object[] { new Short(type) }));
	}

	@Override
	public List<UserPoints> findByUser(long userid) throws UserPointsException {
		return findByDynamicSelect(SQL_SELECT + " WHERE userid = ?",
				Arrays.asList(new Object[] { new Long(userid) }));
	}

	@Override
	public List<UserPoints> findWhereUseridEquals(long userid)
			throws UserPointsException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE userid = ? ORDER BY userid",
				Arrays.asList(new Object[] { new Long(userid) }));
	}

	@Override
	public List<UserPoints> findWhereTypeEquals(short type)
			throws UserPointsException {
		return findByDynamicSelect(
				SQL_SELECT + " WHERE type = ? ORDER BY type",
				Arrays.asList(new Object[] { new Short(type) }));
	}

	@Override
	public List<UserPoints> findWherePointsEquals(int points)
			throws UserPointsException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE points = ? ORDER BY points",
				Arrays.asList(new Object[] { new Integer(points) }));
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
	public List<UserPoints> findByDynamicSelect(String sql,
			List<Object> sqlParams) throws UserPointsException {
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
			throw new UserPointsException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public List<UserPoints> findByDynamicWhere(String sql,
			List<Object> sqlParams) throws UserPointsException {
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
			throw new UserPointsException("Exception: " + _e.getMessage(), _e);
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
	protected UserPoints fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			UserPoints userpoints = new UserPoints();
			populateUserPoints(userpoints, rs);
			return userpoints;
		} else {
			return null;
		}

	}

	/**
	 * Fetches multiple rows from the result set
	 */
	protected List<UserPoints> fetchMultiResults(ResultSet rs)
			throws SQLException {
		List<UserPoints> resultList = new ArrayList<UserPoints>();
		while (rs.next()) {
			UserPoints userpoints = new UserPoints();
			populateUserPoints(userpoints, rs);
			resultList.add(userpoints);
		}

		return resultList;
	}

	/**
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateUserPoints(UserPoints userpoints, ResultSet rs)
			throws SQLException {
		userpoints.setUserid(rs.getInt(COLUMN_USERID));
		userpoints.setType(rs.getShort(COLUMN_TYPE));
		userpoints.setPoints(rs.getInt(COLUMN_POINTS));
		reset(userpoints);
	}

	/**
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(UserPoints userpoints) {
		userpoints.setUseridModified(false);
		userpoints.setTypeModified(false);
		userpoints.setPointsModified(false);
	}

}
