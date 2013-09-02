package com.qpeka.db.dao;

import java.util.List;
import com.qpeka.db.exceptions.QpekaException;
import com.qpeka.services.Errors.ServiceError;

public interface ServiceErrorDao {
	/**
	 * Inserts a new row in the badges table.
	 */
	public short insert(ServiceError serviceError) throws QpekaException;

	/**
	 * Updates a single row in the badges table.
	 */
	public short update(short errorid, ServiceError serviceError) throws QpekaException;

	/**
	 * Deletes a single row in the badges table.
	 */
	public void delete(short errorid) throws QpekaException;

	/**
	 * Returns the rows from the ServiceError table that matches the specified
	 * primary-key value.
	 */
	public ServiceError findByPrimaryKey(short errorid) throws QpekaException;

	/**
	 * Returns all rows from the ServiceError table that match the criteria ''.
	 */
	public List<ServiceError> findAll() throws QpekaException;

	/**
	 * Returns all rows from the ServiceError table that match the criteria 'errorid =
	 * :errorid'.
	 */
	public List<ServiceError> findWhereErroridEquals(short errorid)
			throws QpekaException;
	
	/**
	 * Returns all rows from the ServiceError table that match the criteria 'status =
	 * :status'.
	 */
	public List<ServiceError> findWhereStatus(int status) throws QpekaException;
	
	/**
	 * Returns all rows from the ServiceError table that match the criteria 'name =
	 * :name'.
	 */
	public List<ServiceError> findWherenameEquals(String name)
			throws QpekaException;

	/**
	 * Returns all rows from the ServiceError table that match the criteria 'message =
	 * :messages'.
	 */
	public List<ServiceError> findWhereMessageEquals(String message)
			throws QpekaException;

	/**
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/**
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/**
	 * Returns all rows from the ServiceError table that match the specified arbitrary
	 * SQL statement
	 */
	public List<ServiceError> findByDynamicSelect(String sql, List<Object> sqlParams)
			throws QpekaException;

	/**
	 * Returns all rows from the badges table that match the specified arbitrary
	 * SQL statement
	 */
	public List<ServiceError> findByDynamicWhere(String sql, List<Object> sqlParams)
			throws QpekaException;
}
