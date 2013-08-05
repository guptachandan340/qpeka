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

import com.qpeka.db.Languages;
import com.qpeka.db.conf.ResourceManager;
import com.qpeka.db.dao.LanguagesDao;
import com.qpeka.db.exceptions.LanguagesException;

public class LanguagesHandler extends AbstractHandler implements LanguagesDao {

	/**
	 * The factory class for this DAO has two versions of the create() method -
	 * one that takes no arguments and one that takes a Connection argument. If
	 * the Connection version is chosen then the connection will be stored in
	 * this attribute and will be used by all calls to this DAO, otherwise a new
	 * Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger
			.getLogger(LanguagesHandler.class);
	
	public static LanguagesHandler instance = null;

	/**
	 * All finder methods in this class use this SELECT constant to build their
	 * queries
	 */
	protected final String SQL_SELECT = "SELECT languageid, language, name, native, direction, enabled FROM "
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
			+ " ( languageid, language, name, native, direction, enabled ) VALUES ( ?, ?, ?, ?, ?, ? )";

	/**
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE "
			+ getTableName()
			+ " SET languageid = ?, language = ?, name = ?, native = ?, direction = ?, enabled = ? WHERE languageid = ?";

	/**
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName()
			+ " WHERE languageid = ?";

	/**
	 * Index of column languageid
	 */
	protected static final int COLUMN_LANGUAGEID = 1;

	/**
	 * Index of column language
	 */
	protected static final int COLUMN_LANGUAGE = 2;

	/**
	 * Index of column name
	 */
	protected static final int COLUMN_NAME = 3;

	/**
	 * Index of column native
	 */
	protected static final int COLUMN_A_NATIVE = 4;

	/**
	 * Index of column direction
	 */
	protected static final int COLUMN_DIRECTION = 5;

	/**
	 * Index of column enabled
	 */
	protected static final int COLUMN_ENABLED = 6;

	/**
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 6;

	/**
	 * Index of primary-key column languageid
	 */
	protected static final int PK_COLUMN_LANGUAGEID = 1;

	public LanguagesHandler() {
		super();
	}

	public LanguagesHandler(Connection userConn, int maxRows) {
		super();
		this.userConn = userConn;
		this.maxRows = maxRows;
	}

	public LanguagesHandler(Connection userConn) {
		super();
		this.userConn = userConn;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName() {
		return "qpeka.languages";
	}

	@Override
	public short insert(Languages language) throws LanguagesException {
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
			if (language.isLanguageidModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("languageid");
				values.append("?");
				modifiedCount++;
			}

			if (language.isLanguageModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("language");
				values.append("?");
				modifiedCount++;
			}

			if (language.isNameModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("name");
				values.append("?");
				modifiedCount++;
			}

			if (language.isANativeModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("native");
				values.append("?");
				modifiedCount++;
			}

			if (language.isDirectionModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("direction");
				values.append("?");
				modifiedCount++;
			}

			if (language.isEnabledModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("enabled");
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
			if (language.isLanguageidModified()) {
				stmt.setShort(index++, language.getLanguageid());
			}

			if (language.isLanguageModified()) {
				stmt.setString(index++, language.getLanguage());
			}

			if (language.isNameModified()) {
				stmt.setString(index++, language.getName());
			}

			if (language.isANativeModified()) {
				stmt.setShort(index++, language.getANative());
			}

			if (language.isDirectionModified()) {
				stmt.setShort(index++, language.getDirection());
			}

			if (language.isEnabledModified()) {
				stmt.setShort(index++, language.getEnabled());
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ language);
			}

			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

			// retrieve values from auto-increment columns
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				language.setLanguageid(rs.getShort(1));
			}

			reset(language);
			return language.getLanguageid();
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new LanguagesException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public short update(short languageid, Languages language)
			throws LanguagesException {
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
			if (language.isLanguageidModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("languageid=?");
				modified = true;
			}

			if (language.isLanguageModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("language=?");
				modified = true;
			}

			if (language.isNameModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("name=?");
				modified = true;
			}

			if (language.isANativeModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("native=?");
				modified = true;
			}

			if (language.isDirectionModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("direction=?");
				modified = true;
			}

			if (language.isEnabledModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("enabled=?");
				modified = true;
			}

			if (!modified) {
				// nothing to update
				return -1;
			}

			sql.append(" WHERE languageid=?");
			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ language);
			}

			stmt = conn.prepareStatement(sql.toString());
			int index = 1;
			if (language.isLanguageidModified()) {
				stmt.setShort(index++, language.getLanguageid());
			}

			if (language.isLanguageModified()) {
				stmt.setString(index++, language.getLanguage());
			}

			if (language.isNameModified()) {
				stmt.setString(index++, language.getName());
			}

			if (language.isANativeModified()) {
				stmt.setShort(index++, language.getANative());
			}

			if (language.isDirectionModified()) {
				stmt.setShort(index++, language.getDirection());
			}

			if (language.isEnabledModified()) {
				stmt.setShort(index++, language.getEnabled());
			}

			stmt.setShort(index++, languageid);
			short rows = (short)stmt.executeUpdate();
			reset(language);
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}
			return rows;
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new LanguagesException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
			
		}
	}

	@Override
	public void delete(short languageid) throws LanguagesException {
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
						+ languageid);
			}

			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setShort(1, languageid);
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new LanguagesException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public Languages findByPrimaryKey(short languageid)
			throws LanguagesException {
		List<Languages> ret = findByDynamicSelect(SQL_SELECT
				+ " WHERE languageid = ?",
				Arrays.asList(new Object[] { new Short(languageid) }));
		return ret.size() == 0 ? null : ret.get(0);
	}

	@Override
	public List<Languages> findAll() throws LanguagesException {
		return findByDynamicSelect(SQL_SELECT + " ORDER BY languageid", null);
	}

	@Override
	public List<Languages> findWhereLanguageidEquals(short languageid)
			throws LanguagesException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE languageid = ? ORDER BY languageid",
				Arrays.asList(new Object[] { new Short(languageid) }));
	}

	@Override
	public List<Languages> findWhereLanguageEquals(String language)
			throws LanguagesException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE language = ? ORDER BY language",
				Arrays.asList(new Object[] { language }));
	}

	@Override
	public List<Languages> findWhereNameEquals(String name)
			throws LanguagesException {
		return findByDynamicSelect(
				SQL_SELECT + " WHERE name = ? ORDER BY name",
				Arrays.asList(new Object[] { name }));
	}

	@Override
	public List<Languages> findWhereANativeEquals(String aNative)
			throws LanguagesException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE native = ? ORDER BY native",
				Arrays.asList(new Object[] { aNative }));
	}

	@Override
	public List<Languages> findWhereDirectionEquals(int direction)
			throws LanguagesException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE direction = ? ORDER BY direction",
				Arrays.asList(new Object[] { new Integer(direction) }));
	}

	@Override
	public List<Languages> findWhereEnabledEquals(int enabled)
			throws LanguagesException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE enabled = ? ORDER BY enabled",
				Arrays.asList(new Object[] { new Integer(enabled) }));
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
	public List<Languages> findByDynamicSelect(String sql,
			List<Object> sqlParams) throws LanguagesException {
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
			throw new LanguagesException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public List<Languages> findByDynamicWhere(String sql, List<Object> sqlParams)
			throws LanguagesException {
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
			throw new LanguagesException("Exception: " + _e.getMessage(), _e);
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
	protected Languages fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			Languages language = new Languages();
			populateLanguages(language, rs);
			return language;
		} else {
			return null;
		}

	}

	/**
	 * Fetches multiple rows from the result set
	 */
	protected List<Languages> fetchMultiResults(ResultSet rs)
			throws SQLException {
		List<Languages> resultList = new ArrayList<Languages>();
		while (rs.next()) {
			Languages language = new Languages();
			populateLanguages(language, rs);
			resultList.add(language);
		}

		return resultList;
	}

	/**
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateLanguages(Languages language, ResultSet rs)
			throws SQLException {
		language.setLanguageid(rs.getShort(COLUMN_LANGUAGEID));
		language.setLanguage(rs.getString(COLUMN_LANGUAGE));
		language.setName(rs.getString(COLUMN_NAME));
		language.setANative(rs.getShort(COLUMN_A_NATIVE));
		language.setDirection(rs.getShort(COLUMN_DIRECTION));
		language.setEnabled(rs.getShort(COLUMN_ENABLED));

		reset(language);
	}

	/**
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(Languages language) {
		language.setLanguageidModified(false);
		language.setLanguageModified(false);
		language.setNameModified(false);
		language.setANativeModified(false);
		language.setDirectionModified(false);
		language.setEnabledModified(false);
	}
	
	/**
	 * Get UserHandler object instance
	 * @return instance of UserHandler
	 */
	public static LanguagesHandler getInstance() {
		return (instance == null ? (instance = new LanguagesHandler()) : instance);
	}

}
