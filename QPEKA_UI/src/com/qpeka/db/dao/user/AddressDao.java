package com.qpeka.db.dao.user;

import java.util.List;

import com.qpeka.db.exceptions.user.AddressException;
import com.qpeka.db.user.profile.Address;

public interface AddressDao {

	/** 
	 * Inserts a new row in the useraddress table.
	 */
	public long insert(Address address) throws AddressException;

	/** 
	 * Updates a single row in the useraddress table.
	 */
	public short update(long addressid, Address address) throws AddressException;

	/** 
	 * Deletes a single row in the useraddress table.
	 */
	public void delete(long addressid) throws AddressException;

	/** 
	 * Returns the rows from the useraddress table that matches the specified primary-key value.
	 */
	public Address findByPrimaryKey(long addressid) throws AddressException;

	/** 
	 * Returns all rows from the useraddress table that match the criteria ''.
	 */
	public List<Address> findAll() throws AddressException;

	/** 
	 * Returns all rows from the useraddress table that match the criteria 'userid = :userid'.
	 */
	public List<Address> findByUser(long userid) throws AddressException;
	
	/** 
	 * Returns all rows from the useraddress table that match the criteria 'addressid = :addressid'.
	 */
	public List<Address> findWhereAddressidEquals(int addressid) throws AddressException;

	/** 
	 * Returns all rows from the useraddress table that match the criteria 'userid = :userid'.
	 */
	public List<Address> findWhereUseridEquals(long userid) throws AddressException;

	/** 
	 * Returns all rows from the useraddress table that match the criteria 'line1 = :line1'.
	 */
	public List<Address> findWhereAddressLine1Equals(String line1) throws AddressException;

	/** 
	 * Returns all rows from the useraddress table that match the criteria 'line2 = :line2'.
	 */
	public List<Address> findWhereAddressLine2Equals(String line2) throws AddressException;

	/** 
	 * Returns all rows from the useraddress table that match the criteria 'line3 = :line3'.
	 */
	public List<Address> findWhereAddressLine3Equals(String line3) throws AddressException;

	/** 
	 * Returns all rows from the useraddress table that match the criteria 'city = :city'.
	 */
	public List<Address> findWhereCityEquals(String city) throws AddressException;

	/** 
	 * Returns all rows from the useraddress table that match the criteria 'state = :state'.
	 */
	public List<Address> findWhereStateEquals(String state) throws AddressException;

	/** 
	 * Returns all rows from the useraddress table that match the criteria 'country = :country'.
	 */
	public List<Address> findWhereCountryEquals(short country) throws AddressException;

	/** 
	 * Returns all rows from the useraddress table that match the criteria 'pincode = :pincode'.
	 */
	public List<Address> findWherePincodeEquals(short pincode) throws AddressException;
	
	/** 
	 * Returns all rows from the useraddress table that match the criteria 'timestamp = :timestamp'.
	 */
	public List<Address> findWhereTimestampEquals(long timestamp) throws AddressException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the useraddress table that match the specified arbitrary SQL statement
	 */
	public List<Address> findByDynamicSelect(String sql, List<Object> sqlParams) throws AddressException;

	/** 
	 * Returns all rows from the useraddress table that match the specified arbitrary SQL statement
	 */
	public List<Address> findByDynamicWhere(String sql, List<Object> sqlParams) throws AddressException;

}
