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

import com.qpeka.db.conf.ResourceManager;
import com.qpeka.db.dao.ServiceResponseDao;
import com.qpeka.db.exceptions.QpekaException;
import com.qpeka.services.Errors.ServiceResponse;

public class ServiceResponseHandler extends AbstractHandler implements ServiceResponseDao{

	/**
	 * The factory class for this DAO has two versions of the create() method -
	 * one that takes no arguments and one that takes a Connection argument. If
	 * the Connection version is chosen then the connection will be stored in
	 * this attribute and will be used by all calls to this DAO, otherwise a new
	 * Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger
			.getLogger(ServiceResponseHandler.class);
	
	public static ServiceResponseHandler instance = null;

	/**
	 * All finder methods in this class use this SELECT constant to build their
	 * queries
	 */
	protected final String SQL_SELECT = "SELECT errorid, status, name, message FROM "
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
			+ " ( errorid, status, name, message ) VALUES (?, ?, ?, ? )";

	/**
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE "
			+ getTableName()
			+ " SET errorid = ?, status = ?, name = ?, message = ? WHERE errorid = ?";

	/**
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName()
			+ " WHERE errorid = ?";

	/**
	 * Index of column errorid
	 */
	protected static final int COLUMN_ERRORID = 1;

	/**
	 * Index of column status
	 */
	protected static final int COLUMN_STATUS = 2;

	/**
	 * Index of column name
	 */
	protected static final int COLUMN_NAME = 3;

	/**
	 * Index of column message
	 */
	protected static final int COLUMN_MESSAGE = 4;

	/**
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 4;

	/**
	 * Index of primary-key column badgeid
	 */
	protected static final int PK_COLUMN_ERRORID = 1;

    public ServiceResponseHandler() {
		super();
	}
    
    public ServiceResponseHandler(Connection userConn, int maxRows) {
		super();
		this.userConn = userConn;
		this.maxRows = maxRows;
	}

	public ServiceResponseHandler(Connection userConn) {
		super();
		this.userConn = userConn;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName() {
		return "qpeka.serviceresponse";
	}
	
	public short insert(ServiceResponse serviceResponse) throws QpekaException {
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
			if(serviceResponse.isErroridModified()) {
				if(modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}
				sql.append("errorid");
				values.append("?");
				modifiedCount++;
			}
			if(serviceResponse.isStatusModified()) {
				if(modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}
				sql.append("status");
				values.append("?");
				modifiedCount++;
			}			
			if(serviceResponse.isNameModified()) {
				if(modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}
				sql.append("name");
				values.append("?");
				modifiedCount++;
			}

			if (serviceResponse.isMessageModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}
				sql.append("message");
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
			if (serviceResponse.isErroridModified()) {
				stmt.setShort(index++, serviceResponse.getErrorid());
			}

			if (serviceResponse.isStatusModified()) {
				stmt.setInt(index++, serviceResponse.getStatus());
			}

			if (serviceResponse.isNameModified()) {
				stmt.setString(index++, serviceResponse.getName());
			}

			if (serviceResponse.isMessageModified()) {
				stmt.setString(index++, serviceResponse.getMessage());
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ serviceResponse);
			}

			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

			// retrieve values from auto-increment columns
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				serviceResponse.setErrorid(rs.getShort(1));
			}

			reset(serviceResponse);
			return serviceResponse.getErrorid();
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new QpekaException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		}
	}
	
	@Override
	public short update(short errorid, ServiceResponse serviceResponse) throws QpekaException {
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
			if (serviceResponse.isErroridModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("errorid=?");
				modified = true;
			}

			if (serviceResponse.isStatusModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("status=?");
				modified = true;
			}

			if (serviceResponse.isNameModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("name=?");
				modified = true;
			}

			if (serviceResponse.isMessageModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("message=?");
				modified = true;
			}

			if (!modified) {
				// nothing to update
				return -1;
			}

			sql.append(" WHERE errorid=?");
			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ serviceResponse);
			}

			stmt = conn.prepareStatement(sql.toString());
			int index = 1;
			if (serviceResponse.isErroridModified()) {
				stmt.setShort(index++, serviceResponse.getErrorid());
			}

			if (serviceResponse.isStatusModified()) {
				stmt.setInt(index++, serviceResponse.getStatus());
			}

			if (serviceResponse.isNameModified()) {
				stmt.setString(index++, serviceResponse.getName());
			}

			if (serviceResponse.isMessageModified()) {
				stmt.setString(index++, serviceResponse.getMessage());
			}

			stmt.setInt(index++, errorid);
			short rows = (short) stmt.executeUpdate();
			reset(serviceResponse);
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}
			return rows;
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new QpekaException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		}
	}
	
	@Override
	public void delete(short errorid) throws QpekaException {
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
				logger.debug("Executing " + SQL_DELETE + " with PK: " + errorid);
			}

			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(1, errorid);
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new QpekaException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		}
	}

	@Override
	public ServiceResponse findByPrimaryKey(short errorid) throws QpekaException {
		List<ServiceResponse> ret = findByDynamicSelect(SQL_SELECT
				+ " WHERE errorid = ?",
				Arrays.asList(new Object[] { new Short(errorid) }));
		return ret.size() == 0 ? null : ret.get(0);
	}

	@Override
	public List<ServiceResponse> findAll() throws QpekaException {
		return findByDynamicSelect(SQL_SELECT + " ORDER BY errorid", null);
	}

	@Override
	public List<ServiceResponse> findWhereErroridEquals(short errorid)
			throws QpekaException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE errorid = ? ORDER BY errorid",
				Arrays.asList(new Object[] { new Short(errorid) }));
	}

	@Override
	public List<ServiceResponse> findWhereStatus(int status) throws QpekaException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE status = ? ORDER BY status",
				Arrays.asList(new Object[] { new Integer(status) }));
	}

	@Override
	public List<ServiceResponse> findWherenameEquals(String name)
			throws QpekaException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE name = ? ORDER BY name",
				Arrays.asList(new Object[] { name }));
	}

	@Override
	public List<ServiceResponse> findWhereMessageEquals(String message)
			throws QpekaException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE message = ? ORDER BY message",
				Arrays.asList(new Object[] { new Short(message) }));
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
	public List<ServiceResponse> findByDynamicSelect(String sql, List<Object> sqlParams)
			throws QpekaException {
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
					throw new QpekaException("Exception: " + _e.getMessage(), _e);
				} finally {
					ResourceManager.close(rs);
					ResourceManager.close(stmt);
					if (!isConnSupplied) {
						ResourceManager.close(conn);
					}
				}
		}
	
	@Override
	public List<ServiceResponse> findByDynamicWhere(String sql, List<Object> sqlParams)
			throws QpekaException {
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
					throw new QpekaException("Exception: " + _e.getMessage(), _e);
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
	protected ServiceResponse fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			ServiceResponse serviceResponse = new ServiceResponse();
			populateServiceResponse(serviceResponse, rs);
			return serviceResponse;
		} else {
			return null;
		}

	}
	
	/**
	 * Fetches multiple rows from the result set
	 */
	protected List<ServiceResponse> fetchMultiResults(ResultSet rs) throws SQLException {
		List<ServiceResponse> resultList = new ArrayList<ServiceResponse>();
		while (rs.next()) {
			ServiceResponse serviceResponse = new ServiceResponse();
			populateServiceResponse(serviceResponse, rs);
			resultList.add(serviceResponse);
		}
		return resultList;
	}
	/**
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateServiceResponse(ServiceResponse serviceResponse, ResultSet rs)
			throws SQLException {
		serviceResponse.setErrorid(rs.getShort(COLUMN_ERRORID));
		serviceResponse.setStatus(rs.getInt(COLUMN_STATUS));
		serviceResponse.setName(rs.getString(COLUMN_NAME));
		serviceResponse.setMessage(rs.getString(COLUMN_MESSAGE));
		reset(serviceResponse);
	}

	/**
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(ServiceResponse serviceResponse) {
		serviceResponse.setErroridModified(false);
		serviceResponse.setStatusModified(false);
		serviceResponse.setNameModified(false);
		serviceResponse.setMessageModified(false);
	}
	
	/**
	 * Get ServiceResponseHandler object instance
	 * @return instance of ServiceResponseHandler
	 */
	public static ServiceResponseHandler getInstance() {
		return (instance == null ? instance = new ServiceResponseHandler() : instance);
	}

}
