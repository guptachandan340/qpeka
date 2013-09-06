package com.qpeka.db.dao;

import java.util.List;

import com.qpeka.db.Category;
import com.qpeka.db.exceptions.CategoryException;

public interface CategoryDao {
	
	/** 
	 * Inserts a new row in the category table.
	 */
	public short insert(Category category) throws CategoryException;

	/** 
	 * Updates a single row in the category table.
	 */
	public short update(short categoryid, Category category) throws CategoryException;

	/** 
	 * Deletes a single row in the category table.
	 */
	public void delete(short categoryid) throws CategoryException;

	/** 
	 * Returns the rows from the category table that matches the specified primary-key value.
	 */
	public Category findByPrimaryKey(short categoryid) throws CategoryException;

	/** 
	 * Returns all rows from the category table that match the criteria ''.
	 */
	public List<Category> findAll() throws CategoryException;

	/** 
	 * Returns all rows from the category table that match the criteria 'categoryid = :categoryid'.
	 */
	public List<Category> findWhereCategoryidEquals(short categoryid) throws CategoryException;

	/** 
	 * Returns all rows from the category table that match the criteria 'type = :type'.
	 */
	public List<Category> findWhereTypeEquals(String type) throws CategoryException;

	/** 
	 * Returns all rows from the category table that match the criteria 'category = :category'.
	 */
	public List<Category> findWhereCategoryEquals(String category) throws CategoryException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the category table that match the specified arbitrary SQL statement
	 */
	public List<Category> findByDynamicSelect(String sql, List<Object> sqlParams) throws CategoryException;

	/** 
	 * Returns all rows from the category table that match the specified arbitrary SQL statement
	 */
	public List<Category> findByDynamicWhere(String sql, List<Object> sqlParams) throws CategoryException;
}
