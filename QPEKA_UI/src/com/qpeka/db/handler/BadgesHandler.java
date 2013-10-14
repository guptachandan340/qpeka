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

import com.qpeka.db.Badges;
import com.qpeka.db.dao.BadgesDao;
import com.qpeka.db.exceptions.BadgesException;
import com.qpeka.utils.DBResourceHandler;

public class BadgesHandler extends AbstractHandler implements BadgesDao {

	/**
	 * The factory class for this DAO has two versions of the create() method -
	 * one that takes no arguments and one that takes a Connection argument. If
	 * the Connection version is chosen then the connection will be stored in
	 * this attribute and will be used by all calls to this DAO, otherwise a new
	 * Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger
			.getLogger(BadgesHandler.class);
	
	public static BadgesHandler instance = null;

	/**
	 * All finder methods in this class use this SELECT constant to build their
	 * queries
	 */
	protected final String SQL_SELECT = "SELECT badgeid, typeid, badge, level, points FROM "
			+ getTableName() + "";

	/**
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;
	protected int updatedRows;

	/**
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO "
			+ getTableName()
			+ " ( badgeid, typeid, badge, level, points ) VALUES ( ?, ?, ?, ?, ? )";

	/**
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE "
			+ getTableName()
			+ " SET badgeid = ?, typeid = ?, badge = ?, level = ?, points = ? WHERE badgeid = ?";

	/**
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName()
			+ " WHERE badgeid = ?";

	/**
	 * Index of column badgeid
	 */
	protected static final int COLUMN_BADGEID = 1;

	/**
	 * Index of column typeid
	 */
	protected static final int COLUMN_TYPEID = 2;

	/**
	 * Index of column badge
	 */
	protected static final int COLUMN_BADGE = 3;

	/**
	 * Index of column level
	 */
	protected static final int COLUMN_LEVEL = 4;

	/**
	 * Index of column points
	 */
	protected static final int COLUMN_POINTS = 5;

	/**
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 5;

	/**
	 * Index of primary-key column badgeid
	 */
	protected static final int PK_COLUMN_BADGEID = 1;

	public BadgesHandler() {
		super();
	}

	public BadgesHandler(Connection userConn, int maxRows) {
		super();
		this.userConn = userConn;
		this.maxRows = maxRows;
	}

	public BadgesHandler(Connection userConn) {
		super();
		this.userConn = userConn;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName() {
		return "qpeka.badges";
	}

	@Override
	public short insert(Badges badges) throws BadgesException {
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
			if (badges.isBadgeidModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("badgeid");
				values.append("?");
				modifiedCount++;
			}

			if (badges.isTypeidModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("typeid");
				values.append("?");
				modifiedCount++;
			}

			if (badges.isBadgeModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("badge");
				values.append("?");
				modifiedCount++;
			}

			if (badges.isLevelModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("level");
				values.append("?");
				modifiedCount++;
			}

			if (badges.isPointsModified()) {
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
			stmt = conn.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			int index = 1;
			if (badges.isBadgeidModified()) {
				stmt.setInt(index++, badges.getBadgeid());
			}

			if (badges.isTypeidModified()) {
				stmt.setShort(index++, badges.getTypeid());
			}

			if (badges.isBadgeModified()) {
				stmt.setString(index++, badges.getBadge());
			}

			if (badges.isLevelModified()) {
				stmt.setShort(index++, badges.getLevel());
			}

			if (badges.isPointsModified()) {
				stmt.setInt(index++, badges.getPoints());
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ badges);
			}

			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

			// retrieve values from auto-increment columns
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				badges.setBadgeid(rs.getShort(1));
			}

			reset(badges);
			return badges.getBadgeid();
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new BadgesException("Exception: " + _e.getMessage(), _e);
		} finally {
			DBResourceHandler.close(stmt);
			if (!isConnSupplied) {
				DBResourceHandler.close(conn);
			}

		}
	}

	@Override
	public short update(short badgeid, Badges badges) throws BadgesException {
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
			if (badges.isBadgeidModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("badgeid=?");
				modified = true;
			}

			if (badges.isTypeidModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("typeid=?");
				modified = true;
			}

			if (badges.isBadgeModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("badge=?");
				modified = true;
			}

			if (badges.isLevelModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("level=?");
				modified = true;
			}

			if (badges.isPointsModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("points=?");
				modified = true;
			}

			if (!modified) {
				// nothing to update
				return -1;
			}

			sql.append(" WHERE badgeid=?");
			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ badges);
			}

			stmt = conn.prepareStatement(sql.toString());
			int index = 1;
			if (badges.isBadgeidModified()) {
				stmt.setInt(index++, badges.getBadgeid());
			}

			if (badges.isTypeidModified()) {
				stmt.setShort(index++, badges.getTypeid());
			}

			if (badges.isBadgeModified()) {
				stmt.setString(index++, badges.getBadge());
			}

			if (badges.isLevelModified()) {
				stmt.setShort(index++, badges.getLevel());
			}

			if (badges.isPointsModified()) {
				stmt.setInt(index++, badges.getPoints());
			}

			stmt.setInt(index++, badgeid);
			short rows = (short) stmt.executeUpdate();
			reset(badges);
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}
			return rows;
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new BadgesException("Exception: " + _e.getMessage(), _e);
		} finally {
			DBResourceHandler.close(stmt);
			if (!isConnSupplied) {
				DBResourceHandler.close(conn);
			}
		}
		
	}

	@Override
	public void delete(short badgeid) throws BadgesException {
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
				logger.debug("Executing " + SQL_DELETE + " with PK: " + badgeid);
			}

			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(1, badgeid);
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new BadgesException("Exception: " + _e.getMessage(), _e);
		} finally {
			DBResourceHandler.close(stmt);
			if (!isConnSupplied) {
				DBResourceHandler.close(conn);
			}

		}
	}

	@Override
	public Badges findByPrimaryKey(short badgeid) throws BadgesException {
		List<Badges> ret = findByDynamicSelect(SQL_SELECT
				+ " WHERE badgeid = ?",
				Arrays.asList(new Object[] { new Short(badgeid) }));
		return ret.size() == 0 ? null : ret.get(0);
	}

	@Override
	public List<Badges> findAll() throws BadgesException {
		return findByDynamicSelect(SQL_SELECT + " ORDER BY badgeid", null);
	}

	@Override
	public List<Badges> findByUsertype(short typeid) throws BadgesException {
		return findByDynamicSelect(SQL_SELECT + " WHERE typeid = ?",
				Arrays.asList(new Object[] { new Short(typeid) }));
	}

	@Override
	public List<Badges> findWhereBadgeidEquals(short badgeid)
			throws BadgesException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE badgeid = ? ORDER BY badgeid",
				Arrays.asList(new Object[] { new Short(badgeid) }));
	}

	@Override
	public List<Badges> findWhereTypeidEquals(short typeid)
			throws BadgesException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE typeid = ? ORDER BY typeid",
				Arrays.asList(new Object[] { new Short(typeid) }));
	}

	@Override
	public List<Badges> findWhereBadgeEquals(String badge)
			throws BadgesException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE badge = ? ORDER BY badge",
				Arrays.asList(new Object[] { badge }));
	}

	@Override
	public List<Badges> findWhereLevelEquals(short level)
			throws BadgesException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE level = ? ORDER BY level",
				Arrays.asList(new Object[] { new Short(level) }));
	}

	@Override
	public List<Badges> findWherePointsEquals(int points)
			throws BadgesException {
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
	
	public void setUpdatedRow(int updatedRows) {
		this.updatedRows= updatedRows;
	}
	
	public int getUpdatedRow() {
		return updatedRows;
	}

	

	@Override
	public List<Badges> findByDynamicSelect(String sql, List<Object> sqlParams)
			throws BadgesException {
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
			throw new BadgesException("Exception: " + _e.getMessage(), _e);
		} finally {
			DBResourceHandler.close(rs);
			DBResourceHandler.close(stmt);
			if (!isConnSupplied) {
				DBResourceHandler.close(conn);
			}

		}
	}

	@Override
	public List<Badges> findByDynamicWhere(String sql, List<Object> sqlParams)
			throws BadgesException {
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
			throw new BadgesException("Exception: " + _e.getMessage(), _e);
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
	protected Badges fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			Badges badges = new Badges();
			populateBadges(badges, rs);
			return badges;
		} else {
			return null;
		}

	}

	/**
	 * Fetches multiple rows from the result set
	 */
	protected List<Badges> fetchMultiResults(ResultSet rs) throws SQLException {
		List<Badges> resultList = new ArrayList<Badges>();
		while (rs.next()) {
			Badges badges = new Badges();
			populateBadges(badges, rs);
			resultList.add(badges);
		}

		return resultList;
	}

	/**
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateBadges(Badges badges, ResultSet rs)
			throws SQLException {
		badges.setBadgeid(rs.getShort(COLUMN_BADGEID));
		badges.setTypeid(rs.getShort(COLUMN_TYPEID));
		badges.setBadge(rs.getString(COLUMN_BADGE));
		badges.setLevel(rs.getShort(COLUMN_LEVEL));
		badges.setPoints(rs.getInt(COLUMN_POINTS));
		reset(badges);
	}

	/**
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(Badges badges) {
		badges.setBadgeidModified(false);
		badges.setTypeidModified(false);
		badges.setBadgeModified(false);
		badges.setLevelModified(false);
		badges.setPointsModified(false);
	}
	
	/**
	 * Get UserHandler object instance
	 * @return instance of UserHandler
	 */
	public static BadgesHandler getInstance() {
		return (instance == null ? instance = new BadgesHandler() : instance);
	}

}
