package com.qpeka.db.dao.user;

import java.util.List;

import com.qpeka.db.exceptions.user.TypeException;
import com.qpeka.db.user.profile.Type;

public interface TypeDao {

	/**
	 * Inserts a new row in the usertype table.
	 */
	public short insert(Type type) throws TypeException;

	/**
	 * Updates a single row in the usertype table.
	 */
	public short update(short typeid, Type type) throws TypeException;

	/**
	 * Deletes a single row in the usertype table.
	 */
	public void delete(short typeid) throws TypeException;

	/**
	 * Returns the rows from the usertype table that matches the specified
	 * primary-key value.
	 */
	public Type findByPrimaryKey(short typeid) throws TypeException;

	/**
	 * Returns all rows from the usertype table that match the criteria ''.
	 */
	public List<Type> findAll() throws TypeException;

	/**
	 * Returns all rows from the usertype table that match the criteria 'typeid
	 * = :typeid'.
	 */
	public List<Type> findWhereTypeidEquals(short typeid) throws TypeException;

	/**
	 * Returns all rows from the usertype table that match the criteria 'type =
	 * :type'.
	 */
	public List<Type> findWhereTypeEquals(String type) throws TypeException;

	/**
	 * Returns all rows from the usertype table that match the criteria
	 * 'typename = :typename'.
	 */
	public List<Type> findWhereTypenameEquals(String typename)
			throws TypeException;
	
	/**
	 * Returns all rows from the usertype table that match the criteria
	 * 'typeIdentifier = :typeIdentifier'.
	 */
	public List<Type> findWhereTypeidentifierEquals(String typeidentifier)
			throws TypeException;


	/**
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/**
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/**
	 * Returns all rows from the usertype table that match the specified
	 * arbitrary SQL statement
	 */
	public List<Type> findByDynamicSelect(String sql, List<Object> sqlParams)
			throws TypeException;

	/**
	 * Returns all rows from the usertype table that match the specified
	 * arbitrary SQL statement
	 */
	public List<Type> findByDynamicWhere(String sql, List<Object> sqlParams)
			throws TypeException;

}
