package com.qpeka.db.handler;

import java.awt.image.DataBufferShort;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import com.qpeka.db.Session;
import com.qpeka.db.dao.SessionDao;
import com.qpeka.db.exceptions.user.SessionException;
import com.qpeka.utils.DBResourceHandler;

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

	public static SessionHandler instance = null;

	/**
	 * All finder methods in this class use this SELECT constant to build their
	 * queries
	 */
	protected final String SQL_SELECT = "SELECT sessionid, userid, hostname, created, session, status, sessionobj FROM "
			+ getTableName() + "";

	/**
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;
	protected int updatedRows;

	/**
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT IGNORE INTO "
			+ getTableName()
			+ " ( sessionid, userid, hostname, created, session, status, sessionobj ) VALUES ( ?, ?, ?, ?, ?, ?, ? )";

	/**
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE "
			+ getTableName()
			+ " SET sessionid = ?, userid = ?, hostname = ?, created = ?, session = ?, status = ?, sessionobj = ? WHERE sessionid = ?";

	/**
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName()
			+ " WHERE sessionid = ?";

	/**
	 * Index of column sessionid
	 */
	protected static final int COLUMN_SESSIONID = 1;

	/**
	 * Index of column userid
	 */
	protected static final int COLUMN_USERID = 2;

	/**
	 * Index of column hostname
	 */
	protected static final int COLUMN_HOSTNAME = 3;

	/**
	 * Index of column created
	 */
	protected static final int COLUMN_CREATED = 4;

	/**
	 * Index of column session
	 */
	protected static final int COLUMN_SESSION = 5;

	/**
	 * index of column status
	 */
	protected static final int COLUMN_STATUS = 6;
	
	/**
	 * index of column sessionobj
	 */
	protected static final int COLUMN_SESSIONOBJ = 7;

	/**
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 7;

	/**
	 * Index of primary-key column sessionid
	 */
	protected static final int PK_COLUMN_SESSIONID = 1;

	public SessionHandler() {
		super();
	}

	public SessionHandler(Connection userConn, int maxRows) {
		super();
		this.userConn = userConn;
		this.maxRows = maxRows;
	}

	public SessionHandler(Connection userConn) {
		super();
		this.userConn = userConn;
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
	public long insert(Session session) throws SessionException {

		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
		// get the user-specified connection or get a connection from the
		// ResourceManager
			conn = isConnSupplied ? userConn : DBResourceHandler.getConnection();
			StringBuffer sql = new StringBuffer();
			StringBuffer values = new StringBuffer();
			sql.append("INSERT IGNORE INTO " + getTableName() + " (");
			int modifiedCount = 0;
			if (session.isSessionidModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("session");
				values.append("?");
				modifiedCount++;
			}

			if (session.isUseridModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("userid");
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

			if (session.isCreatedModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("created");
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

			if (session.isStatusModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("status");
				values.append("?");
				modifiedCount++;
			}
			
			if(session.isSessionobjModified()) {
				if(modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}
				
				sql.append("sessionobj");
				values.append("?");
				modifiedCount++;
			} 

			if (modifiedCount == 0) {
				// Nothing to insert
				throw new IllegalStateException("Nothing to insert");
			}

			sql.append(") VALUES (");
			sql.append(values);
			sql.append(")");
			stmt = conn.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			int index = 1;
			if (session.isSessionidModified()) {
				stmt.setLong(index++, session.getSessionid());
			}

			if (session.isUseridModified()) {
				stmt.setLong(index++, session.getUserid());
			}

			if (session.isHostnameModified()) {
				stmt.setString(index++, session.getHostname());
			}

			if (session.isCreatedModified()) {
				stmt.setLong(index++, session.getCreated());
			}

			if (session.isSessionModified()) {
				stmt.setString(index++, session.getSession());
			}

			if (session.isStatusModified()) {
				stmt.setShort(index++, session.getStatus());
			}
			
			if(session.isSessionobjModified()) {
				stmt.setString(index++, session.getSessionobj().toString());
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + "with values: "
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
		} catch (SQLException _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new SessionException("Exception: " + _e.getMessage(), _e);
		} finally {
			DBResourceHandler.close(stmt);
			if (!isConnSupplied) {
				DBResourceHandler.close(conn);
			}

		}
	}

	@Override
	public short update(long sessionid, Session session)
			throws SessionException {
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			// get the user-specified connection or get a connection from the
			// ResourceManager
			conn = isConnSupplied ? userConn : DBResourceHandler.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE " + getTableName() + " SET ");
			boolean modified = false;
			if (session.isSessionidModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("sessionid=?");
				modified = true;
			}

			if (session.isUseridModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("userid=?");
				modified = true;
			}

			if (session.isHostnameModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("hostname=?");
				modified = true;
			}

			if (session.isCreatedModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("created=?");
				modified = true;
			}

			if (session.isSessionModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("session=?");
				modified = true;
			}

			if (session.isStatusModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("status=?");
				modified = true;
			}
			
			if(session.isSessionobjModified()) {
				if(modified) {
					sql.append(", ");
				}
				
				sql.append("sessionobj=?");
				modified = true;
			}

			if (!modified) {
				// nothing to update
				return -1;
			}

			sql.append(" WHERE sessionid=?");
			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ session);
			}

			stmt = conn.prepareStatement(sql.toString());
			int index = 1;
			if (session.isSessionidModified()) {
				stmt.setLong(index++, session.getSessionid());
			}

			if (session.isUseridModified()) {
				stmt.setLong(index++, session.getUserid());
			}

			if (session.isHostnameModified()) {
				stmt.setString(index++, session.getHostname());
			}

			if (session.isCreatedModified()) {
				stmt.setLong(index++, session.getCreated());
			}

			if (session.isSessionModified()) {
				stmt.setString(index++, session.getSession());
			}

			if (session.isStatusModified()) {
				stmt.setShort(index++, session.getStatus());
			}
			
			if(session.isSessionobjModified()) {
				stmt.setString(index++, session.getSessionobj().toString());
			}
			
			stmt.setLong(index++, sessionid);
			short rows = (short) stmt.executeUpdate();
			reset(session);
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}
			return rows;
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new SessionException("Exception: " + _e.getMessage(), _e);
		} finally {
			DBResourceHandler.close(stmt);
			if (!isConnSupplied) {
				DBResourceHandler.close(conn);
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
			conn = isConnSupplied ? userConn : DBResourceHandler.getConnection();

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
			DBResourceHandler.close(stmt);
			if (!isConnSupplied) {
				DBResourceHandler.close(conn);
			}
		}
	}

	@Override
	public Session findByPrimaryKey(long sessionid) throws SessionException {
		List<Session> ret = findByDynamicSelect(SQL_SELECT
				+ " WHERE sessionid = ?",
				Arrays.asList(new Object[] { new Long(sessionid) }));
		return ret.size() == 0 ? null : ret.get(0);

	}

	@Override
	public List<Session> findAll() throws SessionException {
		return findByDynamicSelect(SQL_SELECT + " ORDER BY sessionid", null);
	}

	@Override
	public List<Session> findWhereSessionidEquals(long sessionid)
			throws SessionException {
		return findByDynamicSelect(SQL_SELECT + " WHERE sessionid = ?",
				Arrays.asList(new Object[] { new Long(sessionid) }));
	}

	@Override
	public List<Session> findWhereUseridEquals(long userid)
			throws SessionException {
		return findByDynamicSelect(SQL_SELECT + " WHERE userid = ?",
				Arrays.asList(new Object[] { new Long(userid) }));
	}

	@Override
	public List<Session> findWhereHostnameEquals(String hostname)
			throws SessionException {
		return findByDynamicSelect(SQL_SELECT + " WHERE hostname = ?",
				Arrays.asList(new Object[] { hostname }));
	}

	@Override
	public List<Session> findWherecreatedEquals(long created)
			throws SessionException {
		return findByDynamicSelect(SQL_SELECT + " WHERE created = ?",
				Arrays.asList(new Object[] { new Long(created) }));
	}

	@Override
	public List<Session> findWhereSessionEquals(String session)
			throws SessionException {
		return findByDynamicSelect(SQL_SELECT + " WHERE session = ?",
				Arrays.asList(new Object[] { session }));
	}

	@Override
	public List<Session> findWhereStatusEquals(short status)
			throws SessionException {
		return findByDynamicSelect(SQL_SELECT + " WHERE status = ?",
				Arrays.asList(new Object[] { new Short(status) }));
	}
	
	@Override
	public List<Session> findWhereSessionobjEquals(String sessionobj)
			throws SessionException {
		return findByDynamicSelect(SQL_SELECT + " WHERE sessionobj = ?", Arrays.asList(new Object[] { new String(sessionobj) }));
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
	public List<Session> findByDynamicSelect(String sql, List<Object> sqlParams)
			throws SessionException {
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			// get the user-specified connection or get a connection from the
			// ResourceManager
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
			throw new SessionException("Exception: " + _e.getMessage(), _e);
		} finally {
			DBResourceHandler.close(rs);
			DBResourceHandler.close(stmt);
			if (!isConnSupplied) {
				DBResourceHandler.close(conn);
			}
		}
	}

	@Override
	public List<Session> findByDynamicWhere(String sql, List<Object> sqlParams)
			throws SessionException {
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			// get the user-specified connection or get a connection from the
			// ResourceManager
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
			throw new SessionException("Exception: " + _e.getMessage(), _e);
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
	protected Session fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			Session session = new Session();
			populateSession(session, rs);
			return session;
		} else {
			return null;
		}
	}

	/**
	 * Fetches multiple rows from the result set
	 */
	protected List<Session> fetchMultiResults(ResultSet rs) throws SQLException {
		List<Session> resultList = new ArrayList<Session>();
		while (rs.next()) {
			Session session = new Session();
			populateSession(session, rs);
			resultList.add(session);
		}
		return resultList;
	}

	/**
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateSession(Session session, ResultSet rs)
			throws SQLException {
		session.setSessionid(rs.getLong(COLUMN_SESSIONID));
		session.setUserid(rs.getLong(COLUMN_USERID));
		session.setHostname(rs.getString(COLUMN_HOSTNAME));
		session.setCreated(rs.getLong(COLUMN_CREATED));
		session.setSession(rs.getString(COLUMN_SESSION));
		session.setStatus(rs.getShort(COLUMN_STATUS));
		session.setSessionobj(rs.getString(COLUMN_SESSIONOBJ));
		reset(session);
	}

	/**
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(Session session) {
		session.setSessionidModified(false);
		session.setUseridModified(false);
		session.setHostnameModified(false);
		session.setcreatedModified(false);
		session.setSessionModified(false);
		session.setStatusModified(false);
		session.setSessionobjModified(false);
	}

	/**
	 * Get UserHandler object instance
	 * 
	 * @return instance of UserHandler
	 */
	public static SessionHandler getInstance() {
		return (instance == null ? instance = new SessionHandler() : instance);
	}
}
