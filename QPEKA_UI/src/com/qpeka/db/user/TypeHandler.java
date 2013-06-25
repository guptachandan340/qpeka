package com.qpeka.db.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.qpeka.db.ResourceManager;
import com.qpeka.db.handler.AbstractHandler;
import com.qpeka.db.user.dao.TypeDao;
import com.qpeka.db.user.exceptions.TypeException;
import com.qpeka.db.user.profile.Type;

public class TypeHandler extends AbstractHandler implements TypeDao {

	/**
	 * The factory class for this DAO has two versions of the create() method -
	 * one that takes no arguments and one that takes a Connection argument. If
	 * the Connection version is chosen then the connection will be stored in
	 * this attribute and will be used by all calls to this DAO, otherwise a new
	 * Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger.getLogger(TypeHandler.class);

	/**
	 * All finder methods in this class use this SELECT constant to build their
	 * queries
	 */
	protected final String SQL_SELECT = "SELECT typeid, type, typename FROM "
			+ getTableName() + "";

	/**
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/**
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName()
			+ " ( typeid, type, typename ) VALUES ( ?, ?, ? )";

	/**
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE " + getTableName()
			+ " SET typeid = ?, type = ?, typename = ? WHERE typeid = ?";

	/**
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName()
			+ " WHERE typeid = ?";

	/**
	 * Index of column typeid
	 */
	protected static final int COLUMN_TYPEID = 1;

	/**
	 * Index of column type
	 */
	protected static final int COLUMN_TYPE = 2;

	/**
	 * Index of column typename
	 */
	protected static final int COLUMN_TYPENAME = 3;

	/**
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 3;

	/**
	 * Index of primary-key column typeid
	 */
	protected static final int PK_COLUMN_TYPEID = 1;

	public TypeHandler() {
		super();
	}

	public TypeHandler(Connection userConn) {
		super();
		this.userConn = userConn;
	}

	public TypeHandler(Connection userConn, int maxRows) {
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
		return "qpeka.usertype";
	}

	@Override
	public short insert(Type type) throws TypeException {
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
			if (type.isTypeidModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("typeid");
				values.append("?");
				modifiedCount++;
			}

			if (type.isTypeModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("type");
				values.append("?");
				modifiedCount++;
			}

			if (type.isTypenameModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("typename");
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
			if (type.isTypeidModified()) {
				stmt.setShort(index++, type.getTypeid());
			}

			if (type.isTypeModified()) {
				stmt.setString(index++, type.getType());
			}

			if (type.isTypenameModified()) {
				stmt.setString(index++, type.getTypename());
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ type);
			}

			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

			// retrieve values from auto-increment columns
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				type.setTypeid(rs.getShort(1));
			}

			reset(type);
			return type.getTypeid();
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new TypeException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public void update(short typeid, Type type) throws TypeException {
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
			if (type.isTypeidModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("typeid=?");
				modified = true;
			}

			if (type.isTypeModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("type=?");
				modified = true;
			}

			if (type.isTypenameModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("typename=?");
				modified = true;
			}

			if (!modified) {
				// nothing to update
				return;
			}

			sql.append(" WHERE typeid=?");
			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ type);
			}

			stmt = conn.prepareStatement(sql.toString());
			int index = 1;
			if (type.isTypeidModified()) {
				stmt.setShort(index++, type.getTypeid());
			}

			if (type.isTypeModified()) {
				stmt.setString(index++, type.getType());
			}

			if (type.isTypenameModified()) {
				stmt.setString(index++, type.getTypename());
			}

			stmt.setShort(index++, typeid);
			int rows = stmt.executeUpdate();
			reset(type);
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new TypeException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public void delete(short typeid) throws TypeException {
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
				logger.debug("Executing " + SQL_DELETE + " with PK: " + typeid);
			}

			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setShort(1, typeid);
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new TypeException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public Type findByPrimaryKey(short typeid) throws TypeException {
		List<Type> ret = findByDynamicSelect(SQL_SELECT + " WHERE typeid = ?",
				Arrays.asList(new Object[] { new Short(typeid) }));
		return ret.size() == 0 ? null : ret.get(0);
	}

	@Override
	public List<Type> findAll() throws TypeException {
		return findByDynamicSelect(SQL_SELECT + " ORDER BY typeid", null);
	}

	@Override
	public List<Type> findWhereTypeidEquals(short typeid) throws TypeException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE typeid = ? ORDER BY typeid",
				Arrays.asList(new Object[] { new Short(typeid) }));
	}

	@Override
	public List<Type> findWhereTypeEquals(String type) throws TypeException {
		return findByDynamicSelect(
				SQL_SELECT + " WHERE type = ? ORDER BY type",
				Arrays.asList(new Object[] { type }));
	}

	@Override
	public List<Type> findWhereTypenameEquals(String typename)
			throws TypeException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE typename = ? ORDER BY typename",
				Arrays.asList(new Object[] { typename }));
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
	public List<Type> findByDynamicSelect(String sql, List<Object> sqlParams)
			throws TypeException {
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
			throw new TypeException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public List<Type> findByDynamicWhere(String sql, List<Object> sqlParams)
			throws TypeException {
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
			throw new TypeException("Exception: " + _e.getMessage(), _e);
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
	protected Type fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			Type type = new Type();
			populateType(type, rs);
			return type;
		} else {
			return null;
		}

	}

	/**
	 * Fetches multiple rows from the result set
	 */
	protected List<Type> fetchMultiResults(ResultSet rs) throws SQLException {
		List<Type> resultList = new ArrayList<Type>();
		while (rs.next()) {
			Type type = new Type();
			populateType(type, rs);
			resultList.add(type);
		}

		return resultList;
	}

	/**
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateType(Type type, ResultSet rs) throws SQLException {
		type.setTypeid(rs.getShort(COLUMN_TYPEID));
		type.setType(rs.getString(COLUMN_TYPE));
		type.setTypename(rs.getString(COLUMN_TYPENAME));
		reset(type);
	}

	/**
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(Type type) {
		type.setTypeidModified(false);
		type.setTypeModified(false);
		type.setTypenameModified(false);
	}

}
