package com.qpeka.db.handler.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.qpeka.db.conf.ResourceManager;
import com.qpeka.db.dao.user.UserInterestsDao;
import com.qpeka.db.exceptions.user.UserInterestsException;
import com.qpeka.db.handler.AbstractHandler;
import com.qpeka.db.user.profile.UserInterests;

public class UserInterestsHandler extends AbstractHandler implements
		UserInterestsDao {

	/**
	 * The factory class for this DAO has two versions of the create() method -
	 * one that takes no arguments and one that takes a Connection argument. If
	 * the Connection version is chosen then the connection will be stored in
	 * this attribute and will be used by all calls to this DAO, otherwise a new
	 * Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger
			.getLogger(UserInterestsHandler.class);
	
	public static UserInterestsHandler instance = null;

	/**
	 * All finder methods in this class use this SELECT constant to build their
	 * queries
	 */
	protected final String SQL_SELECT = "SELECT userid, genreid FROM "
			+ getTableName() + "";

	/**
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/**
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName()
			+ " ( userid, genreid ) VALUES ( ?, ? )";

	/**
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE "
			+ getTableName()
			+ " SET userid = ?, genreid = ? WHERE userid = ? AND genreid = ?";

	/**
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName()
			+ " WHERE userid = ? AND genreid = ?";

	/**
	 * Index of column userid
	 */
	protected static final int COLUMN_USERID = 1;

	/**
	 * Index of column genreid
	 */
	protected static final int COLUMN_GENREID = 2;

	/**
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 2;

	/**
	 * Index of primary-key column userid
	 */
	protected static final int PK_COLUMN_USERID = 1;

	/**
	 * Index of primary-key column genreid
	 */
	protected static final int PK_COLUMN_GENREID = 2;

	public UserInterestsHandler() {
		super();
	}

	public UserInterestsHandler(Connection userConn) {
		super();
		this.userConn = userConn;
	}

	public UserInterestsHandler(Connection userConn, int maxRows) {
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
		return "qpeka.userinterests";
	}

	@Override
	public UserInterests insert(UserInterests userinterests)
			throws UserInterestsException {
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
			if (userinterests.isUseridModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("userid");
				values.append("?");
				modifiedCount++;
			}

			if (userinterests.isGenreidModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("genreid");
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
			if (userinterests.isUseridModified()) {
				stmt.setLong(index++, userinterests.getUserid());
			}

			if (userinterests.isGenreidModified()) {
				stmt.setShort(index++, userinterests.getGenreid());
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ userinterests);
			}

			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

			reset(userinterests);
			return userinterests;
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new UserInterestsException("Exception: " + _e.getMessage(),
					_e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public short update(UserInterests olduserinterests,
			UserInterests userinterests) throws UserInterestsException {
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
			if (userinterests.isUseridModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("userid=?");
				modified = true;
			}

			if (userinterests.isGenreidModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("genreid=?");
				modified = true;
			}

			if (!modified) {
				// nothing to update
				return -1;
			}

			sql.append(" WHERE userid=? AND genreid=?");
			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ userinterests);
			}

			stmt = conn.prepareStatement(sql.toString());
			int index = 1;
			if (userinterests.isUseridModified()) {
				stmt.setLong(index++, userinterests.getUserid());
			}

			if (userinterests.isGenreidModified()) {
				stmt.setShort(index++, userinterests.getGenreid());
			}

			stmt.setLong(index++, olduserinterests.getUserid());
			stmt.setShort(index++, olduserinterests.getGenreid());
			short rows = (short)stmt.executeUpdate();

			reset(userinterests);
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}
			return rows;
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new UserInterestsException("Exception: " + _e.getMessage(),
					_e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public void delete(UserInterests userinterests)
			throws UserInterestsException {
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
						+ userinterests);
			}

			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setLong(1, userinterests.getUserid());
			stmt.setShort(2, userinterests.getGenreid());
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new UserInterestsException("Exception: " + _e.getMessage(),
					_e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		}
	}

	@Override
	public UserInterests findByPrimaryKey(long userid, short genreid)
			throws UserInterestsException {
		List<UserInterests> ret = findByDynamicSelect(
				SQL_SELECT + " WHERE userid = ? AND genreid = ?",
				Arrays.asList(new Object[] { new Long(userid),
						new Short( genreid ) }));
		return ret.size() == 0 ? null : ret.get(0);
	}

	@Override
	public List<UserInterests> findAll() throws UserInterestsException {
		return findByDynamicSelect(SQL_SELECT + " ORDER BY userid, genreid",
				null);
	}

	@Override
	public List<UserInterests> findByUser(long userid)
			throws UserInterestsException {
		return findByDynamicSelect(SQL_SELECT + " WHERE userid = ?",
				Arrays.asList(new Object[] { new Long(userid) }));
	}

	@Override
	public List<UserInterests> findByGenre(short genreid)
			throws UserInterestsException {
		return findByDynamicSelect(SQL_SELECT + " WHERE genreid = ?",
				Arrays.asList(new Object[] { new Short(genreid) }));
	}

	@Override
	public List<UserInterests> findWhereUseridEquals(long userid)
			throws UserInterestsException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE userid = ? ORDER BY userid",
				Arrays.asList(new Object[] { new Long(userid) }));
	}

	@Override
	public List<UserInterests> findWhereGenreidEquals(short genreid)
			throws UserInterestsException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE genreid = ? ORDER BY genreid",
				Arrays.asList(new Object[] { new Short(genreid) }));
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
	public List<UserInterests> findByDynamicSelect(String sql,
			List<Object> sqlParams) throws UserInterestsException {
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
			throw new UserInterestsException("Exception: " + _e.getMessage(),
					_e);
		} finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public List<UserInterests> findByDynamicWhere(String sql,
			List<Object> sqlParams) throws UserInterestsException {
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
			throw new UserInterestsException("Exception: " + _e.getMessage(),
					_e);
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
	protected UserInterests fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			UserInterests userinterests = new UserInterests();
			populateUserInterests(userinterests, rs);
			return userinterests;
		} else {
			return null;
		}

	}

	/**
	 * Fetches multiple rows from the result set
	 */
	protected List<UserInterests> fetchMultiResults(ResultSet rs)
			throws SQLException {
		List<UserInterests> resultList = new ArrayList<UserInterests>();
		while (rs.next()) {
			UserInterests userinterests = new UserInterests();
			populateUserInterests(userinterests, rs);
			resultList.add(userinterests);
		}

		return resultList;
	}

	/**
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateUserInterests(UserInterests userinterests,
			ResultSet rs) throws SQLException {
		userinterests.setUserid(rs.getInt(COLUMN_USERID));
		userinterests.setGenreid(rs.getShort(COLUMN_GENREID));
		reset(userinterests);
	}

	/**
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(UserInterests userinterests) {
		userinterests.setUseridModified(false);
		userinterests.setGenreidModified(false);
	}
	
	/**
	 * Get UserInterestsHandler object instance
	 * @return instance of UserInterestHandler
	 */
	public static UserInterestsHandler getInstance() {
		return (instance == null ? (instance = new UserInterestsHandler()) : instance);
	}

}
