package com.qpeka.db.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.qpeka.db.ResourceManager;
import com.qpeka.db.handler.AbstractHandler;
import com.qpeka.db.user.dao.AddressDao;
import com.qpeka.db.user.exceptions.AddressException;
import com.qpeka.db.user.profile.Address;

public class AddressHandler extends AbstractHandler implements AddressDao {

	/**
	 * The factory class for this DAO has two versions of the create() method -
	 * one that takes no arguments and one that takes a Connection argument. If
	 * the Connection version is chosen then the connection will be stored in
	 * this attribute and will be used by all calls to this DAO, otherwise a new
	 * Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger
			.getLogger(AddressHandler.class);

	/**
	 * All finder methods in this class use this SELECT constant to build their
	 * queries
	 */
	protected final String SQL_SELECT = "SELECT addressid, "
			+ "userid, line1, line2, line3, city, state, country, "
			+ "pincode, timestamp FROM " + getTableName() + "";

	/**
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/**
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName()
			+ " ( addressid, userid, line1, line2, "
			+ "line3, city, state, country, pincode, timestamp ) "
			+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	/**
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE " + getTableName()
			+ " SET addressid = ?, userid = ?, line1 = ?, line2 = ?, "
			+ "line3 = ?, city = ?, state = ?, country = ?, pincode = ?, "
			+ "timestamp = ? WHERE addressid = ?";

	/**
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName()
			+ " WHERE addressid = ?";

	/**
	 * Index of column addressid
	 */
	protected static final int COLUMN_ADDRESSID = 1;

	/**
	 * Index of column userid
	 */
	protected static final int COLUMN_USERID = 2;

	/**
	 * Index of column line1
	 */
	protected static final int COLUMN_LINE1 = 3;

	/**
	 * Index of column line2
	 */
	protected static final int COLUMN_LINE2 = 4;

	/**
	 * Index of column line3
	 */
	protected static final int COLUMN_LINE3 = 5;

	/**
	 * Index of column city
	 */
	protected static final int COLUMN_CITY = 6;

	/**
	 * Index of column state
	 */
	protected static final int COLUMN_STATE = 7;

	/**
	 * Index of column country
	 */
	protected static final int COLUMN_COUNTRY = 8;

	/**
	 * Index of column pincode
	 */
	protected static final int COLUMN_PINCODE = 9;

	/**
	 * Index of column timestamp
	 */
	protected static final int COLUMN_TIMESTAMP = 10;

	/**
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 10;

	/**
	 * Index of primary-key column addressid
	 */
	protected static final int PK_COLUMN_ADDRESSID = 1;

	/**
	 * Constructor
	 */
	public AddressHandler() {
		super();
	}

	public AddressHandler(Connection userConn, int maxRows) {
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
		return "qpeka.useraddress";
	}

	/**
	 * Overridden methods
	 */
	@Override
	public long insert(Address address) throws AddressException {
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
			if (address.isAddressidModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("addressid");
				values.append("?");
				modifiedCount++;
			}

			if (address.isUseridModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("userid");
				values.append("?");
				modifiedCount++;
			}

			if (address.isAddressLine1Modified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("line1");
				values.append("?");
				modifiedCount++;
			}

			if (address.isAddressLine2Modified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("line2");
				values.append("?");
				modifiedCount++;
			}

			if (address.isAddressLine3Modified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("line3");
				values.append("?");
				modifiedCount++;
			}

			if (address.isCityModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("city");
				values.append("?");
				modifiedCount++;
			}

			if (address.isStateModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("state");
				values.append("?");
				modifiedCount++;
			}

			if (address.isCountryModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("country");
				values.append("?");
				modifiedCount++;
			}

			if (address.isPincodeModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("pincode");
				values.append("?");
				modifiedCount++;
			}

			if (address.isTimestampModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("timestamp");
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
			if (address.isAddressidModified()) {
				stmt.setLong(index++, address.getAddressid());
			}

			if (address.isUseridModified()) {
				stmt.setLong(index++, address.getUserid());
			}

			if (address.isAddressLine1Modified()) {
				stmt.setString(index++, address.getAddressLine1());
			}

			if (address.isAddressLine2Modified()) {
				stmt.setString(index++, address.getAddressLine2());
			}

			if (address.isAddressLine3Modified()) {
				stmt.setString(index++, address.getAddressLine3());
			}

			if (address.isCityModified()) {
				stmt.setString(index++, address.getCity());
			}

			if (address.isStateModified()) {
				stmt.setString(index++, address.getState());
			}

			if (address.isCountryModified()) {
				stmt.setShort(index++, address.getCountry());
			}

			if (address.isPincodeModified()) {
				stmt.setShort(index++, address.getPincode());
			}

			if (address.isTimestampModified()) {
				stmt.setLong(index++, address.getTimestamp());
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ address);
			}

			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

			// retrieve values from auto-increment columns
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				address.setAddressid(rs.getInt(1));
			}

			reset(address);
			return address.getAddressid();
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new AddressException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public void update(long addressid, Address address) throws AddressException {
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
			if (address.isAddressidModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("addressid=?");
				modified = true;
			}

			if (address.isUseridModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("userid=?");
				modified = true;
			}

			if (address.isAddressLine1Modified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("line1=?");
				modified = true;
			}

			if (address.isAddressLine2Modified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("line2=?");
				modified = true;
			}

			if (address.isAddressLine3Modified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("line3=?");
				modified = true;
			}

			if (address.isCityModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("city=?");
				modified = true;
			}

			if (address.isStateModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("state=?");
				modified = true;
			}

			if (address.isCountryModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("country=?");
				modified = true;
			}

			if (address.isPincodeModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("pincode=?");
				modified = true;
			}

			if (address.isTimestampModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("timestamp=?");
				modified = true;
			}

			if (!modified) {
				// nothing to update
				return;
			}

			sql.append(" WHERE addressid=?");
			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ address);
			}

			stmt = conn.prepareStatement(sql.toString());
			int index = 1;
			if (address.isAddressidModified()) {
				stmt.setLong(index++, address.getAddressid());
			}

			if (address.isUseridModified()) {
				stmt.setLong(index++, address.getUserid());
			}

			if (address.isAddressLine1Modified()) {
				stmt.setString(index++, address.getAddressLine1());
			}

			if (address.isAddressLine2Modified()) {
				stmt.setString(index++, address.getAddressLine2());
			}

			if (address.isAddressLine3Modified()) {
				stmt.setString(index++, address.getAddressLine3());
			}

			if (address.isCityModified()) {
				stmt.setString(index++, address.getCity());
			}

			if (address.isStateModified()) {
				stmt.setString(index++, address.getState());
			}

			if (address.isCountryModified()) {
				stmt.setShort(index++, address.getCountry());
			}

			if (address.isPincodeModified()) {
				stmt.setShort(index++, address.getPincode());
			}

			if (address.isTimestampModified()) {
				stmt.setLong(index++, address.getTimestamp());
			}

			stmt.setLong(index++, addressid);
			int rows = stmt.executeUpdate();
			reset(address);
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new AddressException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public void delete(long addressid) throws AddressException {
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
						+ addressid);
			}

			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setLong(1, addressid);
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new AddressException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public Address findByPrimaryKey(long addressid) throws AddressException {
		List<Address> ret = findByDynamicSelect(SQL_SELECT
				+ " WHERE addressid = ?",
				Arrays.asList(new Object[] { new Long(addressid) }));
		return ret.size() == 0 ? null : ret.get(0);
	}

	@Override
	public List<Address> findAll() throws AddressException {
		return findByDynamicSelect(SQL_SELECT + " ORDER BY addressid", null);
	}

	@Override
	public List<Address> findByUser(long userid) throws AddressException {
		return findByDynamicSelect(SQL_SELECT + " WHERE userid = ?",
				Arrays.asList(new Object[] { new Long(userid) }));
	}

	@Override
	public List<Address> findWhereAddressidEquals(int addressid)
			throws AddressException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE addressid = ? ORDER BY addressid",
				Arrays.asList(new Object[] { new Integer(addressid) }));
	}

	@Override
	public List<Address> findWhereUseridEquals(int userid)
			throws AddressException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE userid = ? ORDER BY userid",
				Arrays.asList(new Object[] { new Integer(userid) }));
	}

	@Override
	public List<Address> findWhereAddressLine1Equals(String line1)
			throws AddressException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE line1 = ? ORDER BY line1",
				Arrays.asList(new Object[] { line1 }));
	}

	@Override
	public List<Address> findWhereAddressLine2Equals(String line2)
			throws AddressException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE line2 = ? ORDER BY line2",
				Arrays.asList(new Object[] { line2 }));
	}

	@Override
	public List<Address> findWhereAddressLine3Equals(String line3)
			throws AddressException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE line3 = ? ORDER BY line3",
				Arrays.asList(new Object[] { line3 }));
	}

	@Override
	public List<Address> findWhereCityEquals(String city)
			throws AddressException {
		return findByDynamicSelect(
				SQL_SELECT + " WHERE city = ? ORDER BY city",
				Arrays.asList(new Object[] { city }));
	}

	@Override
	public List<Address> findWhereStateEquals(String state)
			throws AddressException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE state = ? ORDER BY state",
				Arrays.asList(new Object[] { state }));
	}

	@Override
	public List<Address> findWhereCountryEquals(short country)
			throws AddressException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE country = ? ORDER BY country",
				Arrays.asList(new Object[] { new Short(country) }));
	}

	@Override
	public List<Address> findWherePincodeEquals(short pincode)
			throws AddressException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE pincode = ? ORDER BY pincode",
				Arrays.asList(new Object[] { new Short(pincode) }));
	}

	@Override
	public List<Address> findWhereTimestampEquals(long timestamp)
			throws AddressException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE timestamp = ? ORDER BY timestamp",
				Arrays.asList(new Object[] { new Long(timestamp) }));
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
	public List<Address> findByDynamicSelect(String sql, List<Object> sqlParams)
			throws AddressException {
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
			throw new AddressException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public List<Address> findByDynamicWhere(String sql, List<Object> sqlParams)
			throws AddressException {
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
			throw new AddressException("Exception: " + _e.getMessage(), _e);
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
	protected Address fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			Address address = new Address();
			populateAddress(address, rs);
			return address;
		} else {
			return null;
		}

	}

	/**
	 * Fetches multiple rows from the result set
	 */
	protected List<Address> fetchMultiResults(ResultSet rs) throws SQLException {
		List<Address> resultList = new ArrayList<Address>();
		while (rs.next()) {
			Address address = new Address();
			populateAddress(address, rs);
			resultList.add(address);
		}

		return resultList;
	}

	/**
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateAddress(Address address, ResultSet rs)
			throws SQLException {
		address.setAddressid(rs.getInt(COLUMN_ADDRESSID));
		address.setUserid(rs.getInt(COLUMN_USERID));
		address.setAddressLine1(rs.getString(COLUMN_LINE1));
		address.setAddressLine2(rs.getString(COLUMN_LINE2));
		address.setAddressLine3(rs.getString(COLUMN_LINE3));
		address.setCity(rs.getString(COLUMN_CITY));
		address.setState(rs.getString(COLUMN_STATE));
		address.setCountry(rs.getShort(COLUMN_COUNTRY));
		address.setPincode(rs.getShort(COLUMN_PINCODE));
		address.setTimestamp(rs.getInt(COLUMN_TIMESTAMP));

		reset(address);
	}

	/**
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(Address address) {
		address.setAddressidModified(false);
		address.setUseridModified(false);
		address.setAddressLine1Modified(false);
		address.setAddressLine2Modified(false);
		address.setAddressLine3Modified(false);
		address.setCityModified(false);
		address.setStateModified(false);
		address.setCountryModified(false);
		address.setPincodeModified(false);
		address.setTimestampModified(false);
	}

}
