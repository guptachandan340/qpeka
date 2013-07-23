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

import com.qpeka.db.Category;
import com.qpeka.db.conf.ResourceManager;
import com.qpeka.db.dao.CategoryDao;
import com.qpeka.db.exceptions.CategoryException;
import com.qpeka.db.handler.user.UserHandler;

public class CategoryHandler extends AbstractHandler implements CategoryDao {

	/**
	 * The factory class for this DAO has two versions of the create() method -
	 * one that takes no arguments and one that takes a Connection argument. If
	 * the Connection version is chosen then the connection will be stored in
	 * this attribute and will be used by all calls to this DAO, otherwise a new
	 * Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger
			.getLogger(CategoryHandler.class);

	public static CategoryHandler instance = null;

	/**
	 * All finder methods in this class use this SELECT constant to build their
	 * queries
	 */
	protected final String SQL_SELECT = "SELECT categoryid, type, category, genre, points FROM "
			+ getTableName() + "";

	/**
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/**
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName()
			+ " ( categoryid, type, category, genre, points ) "
			+ "VALUES ( ?, ?, ?, ?, ? )";

	/**
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE " + getTableName()
			+ " SET categoryid = ?, type = ?, category = ?, genre = ?, "
			+ "points = ? WHERE categoryid = ?";

	/**
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName()
			+ " WHERE categoryid = ?";

	/**
	 * Index of column categoryid
	 */
	protected static final int COLUMN_CATEGORYID = 1;

	/**
	 * Index of column type
	 */
	protected static final int COLUMN_TYPE = 2;

	/**
	 * Index of column category
	 */
	protected static final int COLUMN_CATEGORY = 3;

	/**
	 * Index of column genre
	 */
	protected static final int COLUMN_GENRE = 4;

	/**
	 * Index of column points
	 */
	protected static final int COLUMN_POINTS = 5;

	/**
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 5;

	/**
	 * Index of primary-key column categoryid
	 */
	protected static final int PK_COLUMN_CATEGORYID = 1;

	public CategoryHandler() {
		super();
	}

	public CategoryHandler(Connection userConn) {
		super();
		this.userConn = userConn;
	}

	public CategoryHandler(Connection userConn, int maxRows) {
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
		return "qpeka.category";
	}

	@Override
	public short insert(Category category) throws CategoryException {
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
			if (category.isCategoryidModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("categoryid");
				values.append("?");
				modifiedCount++;
			}

			if (category.isTypeModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("type");
				values.append("?");
				modifiedCount++;
			}

			if (category.isCategoryModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("category");
				values.append("?");
				modifiedCount++;
			}

			if (category.isGenreModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("genre");
				values.append("?");
				modifiedCount++;
			}

			if (category.isPointsModified()) {
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
			if (category.isCategoryidModified()) {
				stmt.setShort(index++, category.getCategoryid());
			}

			if (category.isTypeModified()) {
				stmt.setString(index++, category.getType());
			}

			if (category.isCategoryModified()) {
				stmt.setString(index++, category.getCategory());
			}

			if (category.isGenreModified()) {
				stmt.setString(index++, category.getGenre());
			}

			if (category.isPointsModified()) {
				stmt.setInt(index++, category.getPoints());
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ category);
			}

			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

			// retrieve values from auto-increment columns
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				category.setCategoryid(rs.getShort(1));
			}

			reset(category);
			return category.getCategoryid();
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new CategoryException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public short update(short categoryid, Category category)
			throws CategoryException {
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
			if (category.isCategoryidModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("categoryid=?");
				modified = true;
			}

			if (category.isTypeModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("type=?");
				modified = true;
			}

			if (category.isCategoryModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("category=?");
				modified = true;
			}

			if (category.isGenreModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("genre=?");
				modified = true;
			}

			if (category.isPointsModified()) {
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

			sql.append(" WHERE categoryid=?");
			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ category);
			}

			stmt = conn.prepareStatement(sql.toString());
			int index = 1;
			if (category.isCategoryidModified()) {
				stmt.setShort(index++, category.getCategoryid());
			}

			if (category.isTypeModified()) {
				stmt.setString(index++, category.getType());
			}

			if (category.isCategoryModified()) {
				stmt.setString(index++, category.getCategory());
			}

			if (category.isGenreModified()) {
				stmt.setString(index++, category.getGenre());
			}

			if (category.isPointsModified()) {
				stmt.setInt(index++, category.getPoints());
			}

			stmt.setShort(index++, categoryid);
			short rows = (short)stmt.executeUpdate();
			reset(category);
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}
		return rows;
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new CategoryException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public void delete(short categoryid) throws CategoryException {
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
						+ categoryid);
			}

			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setShort(1, categoryid);
			 int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new CategoryException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public Category findByPrimaryKey(short categoryid) throws CategoryException {
		List<Category> ret = findByDynamicSelect(SQL_SELECT
				+ " WHERE categoryid = ?",
				Arrays.asList(new Object[] { new Short(categoryid) }));
		return ret.size() == 0 ? null : ret.get(0);
	}

	@Override
	public List<Category> findAll() throws CategoryException {
		return findByDynamicSelect(SQL_SELECT + " ORDER BY categoryid", null);
	}

	@Override
	public List<Category> findWhereCategoryidEquals(short categoryid)
			throws CategoryException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE categoryid = ? ORDER BY categoryid",
				Arrays.asList(new Object[] { new Short(categoryid) }));
	}

	@Override
	public List<Category> findWhereTypeEquals(String type)
			throws CategoryException {
		return findByDynamicSelect(
				SQL_SELECT + " WHERE type = ? ORDER BY type",
				Arrays.asList(new Object[] { type }));
	}

	@Override
	public List<Category> findWhereCategoryEquals(String category)
			throws CategoryException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE category = ? ORDER BY category",
				Arrays.asList(new Object[] { category }));
	}

	@Override
	public List<Category> findWhereGenreEquals(String genre)
			throws CategoryException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE genre = ? ORDER BY genre",
				Arrays.asList(new Object[] { genre }));
	}

	@Override
	public List<Category> findWherePointsEquals(int points)
			throws CategoryException {
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
	public List<Category> findByDynamicSelect(String sql, List<Object> sqlParams)
			throws CategoryException {
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
			throw new CategoryException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public List<Category> findByDynamicWhere(String sql, List<Object> sqlParams)
			throws CategoryException {
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
			throw new CategoryException("Exception: " + _e.getMessage(), _e);
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
	protected Category fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			Category category = new Category();
			populateCategory(category, rs);
			return category;
		} else {
			return null;
		}

	}

	/**
	 * Fetches multiple rows from the result set
	 */
	protected List<Category> fetchMultiResults(ResultSet rs)
			throws SQLException {
		List<Category> resultList = new ArrayList<Category>();
		while (rs.next()) {
			Category category = new Category();
			populateCategory(category, rs);
			resultList.add(category);
		}

		return resultList;
	}

	/**
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateCategory(Category category, ResultSet rs)
			throws SQLException {
		category.setCategoryid(rs.getShort(COLUMN_CATEGORYID));
		category.setType(rs.getString(COLUMN_TYPE));
		category.setCategory(rs.getString(COLUMN_CATEGORY));
		category.setGenre(rs.getString(COLUMN_GENRE));
		category.setPoints(rs.getInt(COLUMN_POINTS));
		reset(category);
	}

	/**
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(Category category) {
		category.setCategoryidModified(false);
		category.setTypeModified(false);
		category.setCategoryModified(false);
		category.setGenreModified(false);
		category.setPointsModified(false);
	}

	/**
	 * Get UserHandler object instance
	 * @return instance of UserHandler
	 */
	public static CategoryHandler getInstance() {
		return (instance == null ? (instance = new CategoryHandler()) : instance);
	}
}
