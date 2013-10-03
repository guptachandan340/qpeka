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

import com.qpeka.db.Constants.INVITESTATUS;
import com.qpeka.db.exceptions.QpekaException;
import com.qpeka.db.handler.AbstractHandler;

import com.qpeka.db.dao.user.UserInvitesDao;
import com.qpeka.db.user.profile.UserInvites;
import com.qpeka.utils.DBResourceHandler;

public class UserInvitesHandler extends AbstractHandler implements
		UserInvitesDao {

	/**
	 * The factory class for this DAO has two versions of the create() method -
	 * one that takes no arguments and one that takes a Connection argument. If
	 * the Connection version is chosen then the connection will be stored in
	 * this attribute and will be used by all calls to this DAO, otherwise a new
	 * Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger
			.getLogger(UserInvitesHandler.class);

	public static UserInvitesHandler instance;

	/**
	 * All finder methods in this class use this SELECT constant to build their
	 * queries
	 */
	protected final String SQL_SELECT = "SELECT inviteid, userid, type, inviteIdentifier, hashvalue, status FROM "
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
			+ " ( inviteid, userid, type, inviteidentifier, hashvalue, status ) VALUES ( ?, ?, ?, ?, ?, ? )";

	/**
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE "
			+ getTableName()
			+ " SET inviteid = ?, userid = ?, type = ?, inviteidentifier = ?, hashvalue = ?, status = ? WHERE inviteid = ?";

	/**
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName()
			+ " WHERE inviteid = ?";

	/**
	 * Index of column inviteid
	 */
	protected static final int COLUMN_INVITEID = 1;

	/**
	 * Index of column userid
	 */
	protected static final int COLUMN_USERID = 2;

	/**
	 * Index of column type
	 */
	protected static final int COLUMN_TYPE = 3;

	/**
	 * Index of column inviteIdentifier
	 */
	protected static final int COLUMN_INVITEIDENTIFIER = 4;

	/**
	 * Index of column hashvalue
	 */
	protected static final int COLUMN_HASHVALUE = 5;

	/**
	 * Index of column status
	 */
	protected static final int COLUMN_STATUS = 6;

	/**
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 6;

	/**
	 * Index of primary-key column inviteid
	 */
	protected static final int PK_COLUMN_INVITEID = 1;

	public UserInvitesHandler() {
		super();
	}

	public UserInvitesHandler(final Connection userConn) {
		this.userConn = userConn;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName() {
		return "qpeka.userinvites";
	}

	/**
	 * Inserts a new row in the userinvites table.
	 */
	@Override
	public long insert(UserInvites userInvites) throws QpekaException {

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
			sql.append("INSERT IGNORE INTO " + getTableName() + " (");
			int modifiedCount = 0;
			if (userInvites.isInviteidModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("inviteid");
				values.append("?");
				modifiedCount++;
			}

			if (userInvites.isUseridModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("userid");
				values.append("?");
				modifiedCount++;
			}

			if (userInvites.isTypeModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("type");
				values.append("?");
				modifiedCount++;
			}

			if (userInvites.isInviteidentifierModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("inviteIdentifier");
				values.append("?");
				modifiedCount++;
			}

			if (userInvites.isHashvalueModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("hashvalue");
				values.append("?");
				modifiedCount++;
			}

			if (userInvites.isStatusModified()) {
				if (modifiedCount > 0) {
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
			stmt = conn.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			int index = 1;
			if (userInvites.isInviteidModified()) {
				stmt.setLong(index++, userInvites.getInviteid());
			}

			if (userInvites.isUseridModified()) {
				stmt.setLong(index++, userInvites.getUserid());
			}

			if (userInvites.isTypeModified()) {
				stmt.setString(index++, userInvites.getType());
			}

			if (userInvites.isInviteidentifierModified()) {
				stmt.setString(index++, userInvites.getInviteidentifier());
			}

			if (userInvites.isHashvalueModified()) {
				stmt.setString(index++, userInvites.getHashvalue());
			}

			if (userInvites.isStatusModified()) {
				stmt.setString(index++, userInvites.getStatus().toString());
			}
		
			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ userInvites);
			}

			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

			// retrieve values from auto-increment columns
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				userInvites.setInviteid(rs.getInt(1));
			}

			reset(userInvites);
			return userInvites.getInviteid();
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new QpekaException("Exception: " + _e.getMessage(), _e);
		} finally {
			DBResourceHandler.close(stmt);
			if (!isConnSupplied) {
				DBResourceHandler.close(conn);
			}
		}
	}

	@Override
	public short update(long inviteid, UserInvites userinvites)
			throws QpekaException {
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
			if (userinvites.isInviteidModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("inviteid=?");
				modified = true;
			}

			if (userinvites.isUseridModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("userid=?");
				modified = true;
			}

			if (userinvites.isTypeModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("type=?");
				modified = true;
			}

			if (userinvites.isInviteidentifierModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("inviteIdentifier=?");
				modified = true;
			}

			if (userinvites.isHashvalueModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("hashvalue=?");
				modified = true;
			}

			if (userinvites.isStatusModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("status=?");
				modified = true;
			}

			if (!modified) {
				// nothing to update
				return -1;
			}

			sql.append(" WHERE inviteid=?");
			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ userinvites);
			}

			stmt = conn.prepareStatement(sql.toString());
			int index = 1;
			if (userinvites.isInviteidModified()) {
				stmt.setLong(index++, userinvites.getInviteid());
			}

			if (userinvites.isUseridModified()) {
				stmt.setLong(index++, userinvites.getUserid());
			}

			if (userinvites.isTypeModified()) {
				stmt.setString(index++, userinvites.getType());
			}

			if (userinvites.isInviteidentifierModified()) {
				stmt.setString(index++, userinvites.getInviteidentifier());
			}

			if (userinvites.isHashvalueModified()) {
				stmt.setString(index++, userinvites.getHashvalue());
			}

			if (userinvites.isStatusModified()) {
				stmt.setString(index++, userinvites.getStatus().toString());
			}

			stmt.setLong(index++, inviteid);
			short rows = (short) stmt.executeUpdate();
			reset(userinvites);
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}
			return rows;
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new QpekaException("Exception: " + _e.getMessage(), _e);
		} finally {
			DBResourceHandler.close(stmt);
			if (!isConnSupplied) {
				DBResourceHandler.close(conn);
			}

		}

	}

	@Override
	public void delete(long inviteid) throws QpekaException {

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
						+ inviteid);
			}

			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setLong(1, inviteid);
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new QpekaException("Exception: " + _e.getMessage(), _e);
		} finally {
			DBResourceHandler.close(stmt);
			if (!isConnSupplied) {
				DBResourceHandler.close(conn);
			}

		}

	}

	@Override
	public UserInvites findByPrimaryKey(long inviteid) throws QpekaException {
		List<UserInvites> ret = findByDynamicSelect(SQL_SELECT
				+ " WHERE inviteid = ?",
				Arrays.asList(new Object[] { new Long(inviteid) }));
		return ret.size() == 0 ? null : ret.get(0);
	}

	@Override
	public List<UserInvites> findAll() throws QpekaException {
		return findByDynamicSelect(SQL_SELECT + " ORDER BY inviteid", null);
	}

	@Override
	public List<UserInvites> findWhereInviteidEquals(long inviteid)
			throws QpekaException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE inviteid = ? ORDER BY inviteid",
				Arrays.asList(new Object[] { new Long(inviteid) }));
	}

	@Override
	public List<UserInvites> findWhereUseridEquals(long userid)
			throws QpekaException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE userid = ? ORDER BY userid",
				Arrays.asList(new Object[] { userid }));
	}

	@Override
	public List<UserInvites> findWhereTypeEquals(String type)
			throws QpekaException {
		return findByDynamicSelect(
				SQL_SELECT + " WHERE type = ? ORDER BY type",
				Arrays.asList(new Object[] { type }));

	}

	@Override
	public List<UserInvites> findWhereInviteIdentifierEquals(
			String inviteIdentifier) throws QpekaException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE inviteIdentifier = ? ORDER BY inviteIdentifier",
				Arrays.asList(new Object[] { inviteIdentifier }));
	}

	@Override
	public List<UserInvites> findWhereHashvalueEquals(String hashvalue)
			throws QpekaException {

		return findByDynamicSelect(SQL_SELECT
				+ " WHERE hashvalue = ? ORDER BY hashvalue",
				Arrays.asList(new Object[] { hashvalue }));
	}

	@Override
	public List<UserInvites> findWhereStatusEquals(String status)
			throws QpekaException {
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
	public List<UserInvites> findByDynamicSelect(String sql,
			List<Object> sqlParams) throws QpekaException {
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
			throw new QpekaException("Exception: " + _e.getMessage(), _e);
		} finally {
			DBResourceHandler.close(rs);
			DBResourceHandler.close(stmt);
			if (!isConnSupplied) {
				DBResourceHandler.close(conn);
			}

		}
	}

	@Override
	public List<UserInvites> findByDynamicWhere(String sql,
			List<Object> sqlParams) throws QpekaException {
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
			throw new QpekaException("Exception: " + _e.getMessage(), _e);
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
	protected UserInvites fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			UserInvites userinvites = new UserInvites();
			populateUserInvites(userinvites, rs);
			return userinvites;
		} else {
			return null;
		}

	}

	/**
	 * Fetches multiple rows from the result set
	 */
	protected List<UserInvites> fetchMultiResults(ResultSet rs)
			throws SQLException {
		List<UserInvites> resultList = new ArrayList<UserInvites>();
		while (rs.next()) {
			UserInvites userinvites = new UserInvites();
			populateUserInvites(userinvites, rs);
			resultList.add(userinvites);
		}
		return resultList;
	}

	/**
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateUserInvites(UserInvites userinvites, ResultSet rs)
			throws SQLException {

		userinvites.setInviteid(rs.getLong(COLUMN_INVITEID));
		userinvites.setUserid(rs.getLong(COLUMN_USERID));
		userinvites.setType(rs.getString(COLUMN_TYPE));
		userinvites.setInviteidentifier(rs.getString(COLUMN_INVITEIDENTIFIER));
		userinvites.setHashvalue(rs.getString(COLUMN_HASHVALUE));
		userinvites.setStatus(INVITESTATUS.valueOf(rs.getString(COLUMN_STATUS).toUpperCase()));

		reset(userinvites);
	}

	/**
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(UserInvites userinvites) {

		userinvites.setInviteidModified(false);
		userinvites.setUseridModified(false);
		userinvites.setTypeModified(false);
		userinvites.setInviteidentifierModified(false);
		userinvites.setHashalueModified(false);
		userinvites.setStatusModified(false);
	}

	/**
	 * Get UserInvitesHandler object instance
	 * 
	 * @return instance of UserInvitesHandler
	 */
	public static UserInvitesHandler getInstance() {
		return (instance == null ? (instance = new UserInvitesHandler())
				: instance);
	}

}
