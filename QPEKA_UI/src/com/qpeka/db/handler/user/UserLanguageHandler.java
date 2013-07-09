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
import com.qpeka.db.dao.user.UserLanguageDao;
import com.qpeka.db.exceptions.user.UserLanguageException;
import com.qpeka.db.handler.AbstractHandler;
import com.qpeka.db.user.profile.UserLanguage;

public class UserLanguageHandler extends AbstractHandler implements
		UserLanguageDao {

	/**
	 * The factory class for this DAO has two versions of the create() method -
	 * one that takes no arguments and one that takes a Connection argument. If
	 * the Connection version is chosen then the connection will be stored in
	 * this attribute and will be used by all calls to this DAO, otherwise a new
	 * Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger
			.getLogger(UserLanguageHandler.class);

	public static UserLanguageHandler instance = null;

	/**
	 * All finder methods in this class use this SELECT constant to build their
	 * queries
	 */
	protected final String SQL_SELECT = "SELECT userid, languageid, type FROM "
			+ getTableName() + "";

	/**
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/**
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName()
			+ " ( userid, languageid, type ) VALUES ( ?, ?, ? )";

	/**
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE "
			+ getTableName()
			+ " SET userid = ?, languageid = ?, type = ? WHERE userid = ? AND languageid = ?";

	/**
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName()
			+ " WHERE userid = ? AND languageid = ?";

	/**
	 * Index of column userid
	 */
	protected static final int COLUMN_USERID = 1;

	/**
	 * Index of column languageid
	 */
	protected static final int COLUMN_LANGUAGEID = 2;

	/**
	 * Index of column type
	 */
	protected static final int COLUMN_TYPE = 3;

	/**
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 3;

	/**
	 * Index of primary-key column userid
	 */
	protected static final int PK_COLUMN_USERID = 1;

	/**
	 * Index of primary-key column languageid
	 */
	protected static final int PK_COLUMN_LANGUAGEID = 2;

	public UserLanguageHandler() {
		super();
	}

	public UserLanguageHandler(Connection userConn) {
		super();
		this.userConn = userConn;
	}

	public UserLanguageHandler(Connection userConn, int maxRows) {
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
		return "qpeka.userlanguage";
	}

	@Override
	public UserLanguage insert(UserLanguage userlanguage)
			throws UserLanguageException {
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
			if (userlanguage.isUseridModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("userid");
				values.append("?");
				modifiedCount++;
			}

			if (userlanguage.isLanguageidModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("languageid");
				values.append("?");
				modifiedCount++;
			}

			if (userlanguage.isTypeModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("type");
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
			if (userlanguage.isUseridModified()) {
				stmt.setLong(index++, userlanguage.getUserid());
			}

			if (userlanguage.isLanguageidModified()) {
				stmt.setShort(index++, userlanguage.getLanguageid());
			}

			if (userlanguage.isTypeModified()) {
				stmt.setString(index++, userlanguage.getType());
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ userlanguage);
			}

			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

			reset(userlanguage);
			return userlanguage;
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new UserLanguageException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public void update(UserLanguage olduserlanguage, UserLanguage userlanguage)
			throws UserLanguageException {
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
			if (userlanguage.isUseridModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("userid=?");
				modified = true;
			}

			if (userlanguage.isLanguageidModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("languageid=?");
				modified = true;
			}

			if (userlanguage.isTypeModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("type=?");
				modified = true;
			}

			if (!modified) {
				// nothing to update
				return;
			}

			sql.append(" WHERE userid=? AND languageid=?");
			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ userlanguage);
			}

			stmt = conn.prepareStatement(sql.toString());
			int index = 1;
			if (userlanguage.isUseridModified()) {
				stmt.setLong(index++, userlanguage.getUserid());
			}

			if (userlanguage.isLanguageidModified()) {
				stmt.setShort(index++, userlanguage.getLanguageid());
			}

			if (userlanguage.isTypeModified()) {
				stmt.setString(index++, userlanguage.getType());
			}

			stmt.setLong(index++, olduserlanguage.getUserid());
			stmt.setShort(index++, olduserlanguage.getLanguageid());
			int rows = stmt.executeUpdate();

			reset(userlanguage);
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new UserLanguageException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public void delete(UserLanguage userlanguage) throws UserLanguageException {
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
						+ userlanguage);
			}

			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setLong(1, userlanguage.getUserid());
			stmt.setShort(2, userlanguage.getLanguageid());
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new UserLanguageException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public UserLanguage findByPrimaryKey(long userid, short languageid)
			throws UserLanguageException {
		List<UserLanguage> ret = findByDynamicSelect(
				SQL_SELECT + " WHERE userid = ? AND languageid = ?",
				Arrays.asList(new Object[] { new Long(userid),
						new Short(languageid) }));
		return ret.size() == 0 ? null : ret.get(0);
	}

	@Override
	public List<UserLanguage> findAll() throws UserLanguageException {
		return findByDynamicSelect(SQL_SELECT + " ORDER BY userid, languageid",
				null);
	}

	@Override
	public List<UserLanguage> findWhereUseridEquals(long userid)
			throws UserLanguageException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE userid = ? ORDER BY userid",
				Arrays.asList(new Object[] { new Long(userid) }));
	}

	@Override
	public List<UserLanguage> findWhereLanguageidEquals(short languageid)
			throws UserLanguageException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE languageid = ? ORDER BY languageid",
				Arrays.asList(new Object[] { new Short(languageid) }));
	}

	@Override
	public List<UserLanguage> findWhereTypeEquals(String type)
			throws UserLanguageException {
		return findByDynamicSelect(
				SQL_SELECT + " WHERE type = ? ORDER BY type",
				Arrays.asList(new Object[] { type }));
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
	public List<UserLanguage> findByDynamicSelect(String sql,
			List<Object> sqlParams) throws UserLanguageException {
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
			throw new UserLanguageException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public List<UserLanguage> findByDynamicWhere(String sql,
			List<Object> sqlParams) throws UserLanguageException {
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
			throw new UserLanguageException("Exception: " + _e.getMessage(), _e);
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
	protected UserLanguage fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			UserLanguage userlanguage = new UserLanguage();
			populateUserLanguage(userlanguage, rs);
			return userlanguage;
		} else {
			return null;
		}

	}

	/**
	 * Fetches multiple rows from the result set
	 */
	protected List<UserLanguage> fetchMultiResults(ResultSet rs)
			throws SQLException {
		List<UserLanguage> resultList = new ArrayList<UserLanguage>();
		while (rs.next()) {
			UserLanguage userlanguage = new UserLanguage();
			populateUserLanguage(userlanguage, rs);
			resultList.add(userlanguage);
		}

		return resultList;
	}

	/**
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateUserLanguage(UserLanguage userlanguage, ResultSet rs)
			throws SQLException {
		userlanguage.setUserid(rs.getInt(COLUMN_USERID));
		userlanguage.setLanguageid(rs.getShort(COLUMN_LANGUAGEID));
		userlanguage.setType(rs.getString(COLUMN_TYPE));
		reset(userlanguage);
	}

	/**
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(UserLanguage userlanguage) {
		userlanguage.setUseridModified(false);
		userlanguage.setLanguageidModified(false);
		userlanguage.setTypeModified(false);
	}

	/**
	 * Get UserHandler object instance
	 * 
	 * @return instance of UserHandler
	 */
	public static UserLanguageHandler getInstance() {
		return (instance == null ? (instance = new UserLanguageHandler())
				: instance);
	}

}
