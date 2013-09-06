package com.qpeka.services.Response;

import java.util.List;

import com.qpeka.db.exceptions.QpekaException;

public interface ServiceResponseDao {
	/**
	 * Inserts a new row in the badges table.
	 */
	public short insert(ServiceResponse serviceResponse) throws QpekaException;

	/**
	 * Updates a single row in the badges table.
	 */
	public short update(short errorid, ServiceResponse serviceResponse) throws QpekaException;

	/**
	 * Deletes a single row in the badges table.
	 */
	public void delete(short errorid) throws QpekaException;

	/**
	 * Returns the rows from the ServiceResponse table that matches the specified
	 * primary-key value.
	 */
	public ServiceResponse findByPrimaryKey(short errorid) throws QpekaException;

	/**
	 * Returns all rows from the ServiceResponse table that match the criteria ''.
	 */
	public List<ServiceResponse> findAll() throws QpekaException;

	/**
	 * Returns all rows from the ServiceResponse table that match the criteria 'errorid =
	 * :errorid'.
	 */
	public List<ServiceResponse> findWhereErroridEquals(short errorid)
			throws QpekaException;
	
	/**
	 * Returns all rows from the ServiceResponse table that match the criteria 'status =
	 * :status'.
	 */
	public List<ServiceResponse> findWhereStatus(int status) throws QpekaException;
	
	/**
	 * Returns all rows from the ServiceResponse table that match the criteria 'name =
	 * :name'.
	 */
	public List<ServiceResponse> findWherenameEquals(String name)
			throws QpekaException;

	/**
	 * Returns all rows from the ServiceResponse table that match the criteria 'message =
	 * :messages'.
	 */
	public List<ServiceResponse> findWhereMessageEquals(String message)
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
	 * Returns all rows from the ServiceResponse table that match the specified arbitrary
	 * SQL statement
	 */
	public List<ServiceResponse> findByDynamicSelect(String sql, List<Object> sqlParams)
			throws QpekaException;

	/**
	 * Returns all rows from the badges table that match the specified arbitrary
	 * SQL statement
	 */
	public List<ServiceResponse> findByDynamicWhere(String sql, List<Object> sqlParams)
			throws QpekaException;
}
