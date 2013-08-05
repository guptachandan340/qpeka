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

import com.qpeka.db.Country;
import com.qpeka.db.conf.ResourceManager;
import com.qpeka.db.dao.CountryDao;
import com.qpeka.db.exceptions.CountryException;

public class CountryHandler extends AbstractHandler implements CountryDao {

	/**
	 * The factory class for this DAO has two versions of the create() method -
	 * one that takes no arguments and one that takes a Connection argument. If
	 * the Connection version is chosen then the connection will be stored in
	 * this attribute and will be used by all calls to this DAO, otherwise a new
	 * Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger
			.getLogger(CountryHandler.class);
	
	public static CountryHandler instance;

	/**
	 * All finder methods in this class use this SELECT constant to build their
	 * queries
	 */
	protected final String SQL_SELECT = "SELECT countryid, iso2, shortname, longname, iso3, numcode, unmember, callingcode, cctld FROM "
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
			+ " ( countryid, iso2, shortname, longname, iso3, numcode, unmember, callingcode, cctld ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	/**
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE "
			+ getTableName()
			+ " SET countryid = ?, iso2 = ?, shortname = ?, longname = ?, iso3 = ?, numcode = ?, unmember = ?, callingcode = ?, cctld = ? WHERE countryid = ?";

	/**
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName()
			+ " WHERE countryid = ?";

	/**
	 * Index of column countryid
	 */
	
	/* ToDo : change ID to String 
	 * problem during :
	 *  public List<Country> RetrieveCountry() {
			List<Country> country = null;
				try {
					country = CountryHandler.getInstance().findByDynamicSelect("SELECT shortname, iso2 from qpeka.country ",null);
				} catch (CountryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return country;
		}
		Error : Invalid value for getShort() */ 
	protected static final int COLUMN_COUNTRYID = 1;

	/** 
	 * Index of column iso2
	 */
	protected static final int COLUMN_ISO2 = 2;

	/**
	 * Index of column shortname
	 */
	protected static final int COLUMN_SHORTNAME = 3;

	/**
	 * Index of column longname
	 */
	protected static final int COLUMN_LONGNAME = 4;

	/**
	 * Index of column iso3
	 */
	protected static final int COLUMN_ISO3 = 5;

	/**
	 * Index of column numcode
	 */
	protected static final int COLUMN_NUMCODE = 6;

	/**
	 * Index of column unmember
	 */
	protected static final int COLUMN_UNMEMBER = 7;

	/**
	 * Index of column callingcode
	 */
	protected static final int COLUMN_CALLINGCODE = 8;

	/**
	 * Index of column cctld
	 */
	protected static final int COLUMN_CCTLD = 9;

	/**
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 9;

	/**
	 * Index of primary-key column countryid
	 */
	protected static final int PK_COLUMN_COUNTRYID = 1;

	public CountryHandler() {
		super();
	}

	public CountryHandler(Connection userConn) {
		super();
		this.userConn = userConn;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName() {
		return "qpeka.country";
	}

	@Override
	public short insert(Country country) throws CountryException {
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
			if (country.isCountryidModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("countryid");
				values.append("?");
				modifiedCount++;
			}

			if (country.isIso2Modified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("iso2");
				values.append("?");
				modifiedCount++;
			}

			if (country.isShortnameModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("shortname");
				values.append("?");
				modifiedCount++;
			}

			if (country.isLongnameModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("longname");
				values.append("?");
				modifiedCount++;
			}

			if (country.isIso3Modified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("iso3");
				values.append("?");
				modifiedCount++;
			}

			if (country.isNumcodeModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("numcode");
				values.append("?");
				modifiedCount++;
			}

			if (country.isUnmemberModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("unmember");
				values.append("?");
				modifiedCount++;
			}

			if (country.isCallingcodeModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("callingcode");
				values.append("?");
				modifiedCount++;
			}

			if (country.isCctldModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("cctld");
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
			if (country.isCountryidModified()) {
				stmt.setShort(index++, country.getCountryid());
			}

			if (country.isIso2Modified()) {
				stmt.setString(index++, country.getIso2());
			}

			if (country.isShortnameModified()) {
				stmt.setString(index++, country.getShortname());
			}

			if (country.isLongnameModified()) {
				stmt.setString(index++, country.getLongname());
			}

			if (country.isIso3Modified()) {
				stmt.setString(index++, country.getIso3());
			}

			if (country.isNumcodeModified()) {
				stmt.setString(index++, country.getNumcode());
			}

			if (country.isUnmemberModified()) {
				stmt.setString(index++, country.getUnmember());
			}

			if (country.isCallingcodeModified()) {
				stmt.setString(index++, country.getCallingcode());
			}

			if (country.isCctldModified()) {
				stmt.setString(index++, country.getCctld());
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ country);
			}

			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

			// retrieve values from auto-increment columns
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				country.setCountryid(rs.getShort(1));
			}

			reset(country);
			return country.getCountryid();
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new CountryException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public short update(short countryid, Country country)
			throws CountryException {
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
			if (country.isCountryidModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("countryid=?");
				modified = true;
			}

			if (country.isIso2Modified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("iso2=?");
				modified = true;
			}

			if (country.isShortnameModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("shortname=?");
				modified = true;
			}

			if (country.isLongnameModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("longname=?");
				modified = true;
			}

			if (country.isIso3Modified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("iso3=?");
				modified = true;
			}

			if (country.isNumcodeModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("numcode=?");
				modified = true;
			}

			if (country.isUnmemberModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("unmember=?");
				modified = true;
			}

			if (country.isCallingcodeModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("callingcode=?");
				modified = true;
			}

			if (country.isCctldModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("cctld=?");
				modified = true;
			}

			if (!modified) {
				// nothing to update
				return -1;
			}

			sql.append(" WHERE countryid=?");
			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ country);
			}

			stmt = conn.prepareStatement(sql.toString());
			int index = 1;
			if (country.isCountryidModified()) {
				stmt.setShort(index++, country.getCountryid());
			}

			if (country.isIso2Modified()) {
				stmt.setString(index++, country.getIso2());
			}

			if (country.isShortnameModified()) {
				stmt.setString(index++, country.getShortname());
			}

			if (country.isLongnameModified()) {
				stmt.setString(index++, country.getLongname());
			}

			if (country.isIso3Modified()) {
				stmt.setString(index++, country.getIso3());
			}

			if (country.isNumcodeModified()) {
				stmt.setString(index++, country.getNumcode());
			}

			if (country.isUnmemberModified()) {
				stmt.setString(index++, country.getUnmember());
			}

			if (country.isCallingcodeModified()) {
				stmt.setString(index++, country.getCallingcode());
			}

			if (country.isCctldModified()) {
				stmt.setString(index++, country.getCctld());
			}

			stmt.setShort(index++, countryid);
			short rows = (short)stmt.executeUpdate();
			reset(country);
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}
			return rows;
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new CountryException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public void delete(short countryid) throws CountryException {
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
						+ countryid);
			}

			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setShort(1, countryid);
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new CountryException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public Country findByPrimaryKey(short countryid) throws CountryException {
		List<Country> ret = findByDynamicSelect(SQL_SELECT
				+ " WHERE countryid = ?",
				Arrays.asList(new Object[] { new Short(countryid) }));
		return ret.size() == 0 ? null : ret.get(0);
	}

	@Override
	public List<Country> findAll() throws CountryException {
		return findByDynamicSelect(SQL_SELECT + " ORDER BY iso2", null);
	}

	@Override
	public List<Country> findWhereCountryidEquals(short countryid)
			throws CountryException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE countryid = ? ORDER BY countryid",
				Arrays.asList(new Object[] { new Short(countryid) }));
	}

	@Override
	public List<Country> findWhereIso2Equals(String iso2)
			throws CountryException {
		return findByDynamicSelect(
				SQL_SELECT + " WHERE iso2 = ? ORDER BY iso2",
				Arrays.asList(new Object[] { iso2 }));
	}

	@Override
	public List<Country> findWhereShortnameEquals(String shortname)
			throws CountryException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE shortname = ? ORDER BY shortname",
				Arrays.asList(new Object[] { shortname }));
	}

	@Override
	public List<Country> findWhereLongnameEquals(String longname)
			throws CountryException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE longname = ? ORDER BY longname",
				Arrays.asList(new Object[] { longname }));
	}

	@Override
	public List<Country> findWhereIso3Equals(String iso3)
			throws CountryException {
		return findByDynamicSelect(
				SQL_SELECT + " WHERE iso3 = ? ORDER BY iso3",
				Arrays.asList(new Object[] { iso3 }));
	}

	@Override
	public List<Country> findWhereNumcodeEquals(String numcode)
			throws CountryException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE numcode = ? ORDER BY numcode",
				Arrays.asList(new Object[] { numcode }));
	}

	@Override
	public List<Country> findWhereUnmemberEquals(String unmember)
			throws CountryException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE unmember = ? ORDER BY unmember",
				Arrays.asList(new Object[] { unmember }));
	}

	@Override
	public List<Country> findWhereCallingcodeEquals(String callingcode)
			throws CountryException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE callingcode = ? ORDER BY callingcode",
				Arrays.asList(new Object[] { callingcode }));
	}

	@Override
	public List<Country> findWhereCctldEquals(String cctld)
			throws CountryException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE cctld = ? ORDER BY cctld",
				Arrays.asList(new Object[] { cctld }));
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
	public List<Country> findByDynamicSelect(String sql, List<Object> sqlParams)
			throws CountryException {
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
			throw new CountryException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		}
	}

	@Override
	public List<Country> findByDynamicWhere(String sql, List<Object> sqlParams)
			throws CountryException {
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
			throw new CountryException("Exception: " + _e.getMessage(), _e);
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
	protected Country fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			Country country = new Country();
			populateCountry(country, rs);
			return country;
		} else {
			return null;
		}

	}

	/**
	 * Fetches multiple rows from the result set
	 */
	protected List<Country> fetchMultiResults(ResultSet rs) throws SQLException {
		List<Country> resultList = new ArrayList<Country>();
		while (rs.next()) {
			Country country = new Country();
			populateCountry(country, rs);
			resultList.add(country);
		}

		return resultList;
	}

	/**
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateCountry(Country country, ResultSet rs)
			throws SQLException {
		country.setCountryid(rs.getShort(COLUMN_COUNTRYID));
		country.setIso2(rs.getString(COLUMN_ISO2));
		country.setShortname(rs.getString(COLUMN_SHORTNAME));
		country.setLongname(rs.getString(COLUMN_LONGNAME));
		country.setIso3(rs.getString(COLUMN_ISO3));
		country.setNumcode(rs.getString(COLUMN_NUMCODE));
		country.setUnmember(rs.getString(COLUMN_UNMEMBER));
		country.setCallingcode(rs.getString(COLUMN_CALLINGCODE));
		country.setCctld(rs.getString(COLUMN_CCTLD));

		reset(country);
	}

	/**
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(Country country) {
		country.setCountryidModified(false);
		country.setIso2Modified(false);
		country.setShortnameModified(false);
		country.setLongnameModified(false);
		country.setIso3Modified(false);
		country.setNumcodeModified(false);
		country.setUnmemberModified(false);
		country.setCallingcodeModified(false);
		country.setCctldModified(false);
	}

	/**
	 * User profile handler instance
	 * @return instance of user profile
	 */
	public static CountryHandler getInstance() {
		return (instance == null ? (instance = new CountryHandler()) : instance);
	}
}