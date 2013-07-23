package com.qpeka.db.dao;

import java.util.List;

import com.qpeka.db.Country;
import com.qpeka.db.exceptions.CountryException;

public interface CountryDao {

	/**
	 * Inserts a new row in the country table.
	 */
	public short insert(Country country) throws CountryException;

	/**
	 * Updates a single row in the country table.
	 */
	public short update(short countryid, Country country)
			throws CountryException;

	/**
	 * Deletes a single row in the country table.
	 */
	public void delete(short countryid) throws CountryException;

	/**
	 * Returns all rows from the country table that match the criteria
	 * 'countryid = :countryid'.
	 */
	public Country findByPrimaryKey(short countryid) throws CountryException;

	/**
	 * Returns all rows from the country table that match the criteria ''.
	 */
	public List<Country> findAll() throws CountryException;

	/**
	 * Returns all rows from the country table that match the criteria
	 * 'countryid = :countryid'.
	 */
	public List<Country> findWhereCountryidEquals(short countryid)
			throws CountryException;

	/**
	 * Returns all rows from the country table that match the criteria 'iso2 =
	 * :iso2'.
	 */
	public List<Country> findWhereIso2Equals(String iso2)
			throws CountryException;

	/**
	 * Returns all rows from the country table that match the criteria
	 * 'shortname = :shortname'.
	 */
	public List<Country> findWhereShortnameEquals(String shortname)
			throws CountryException;

	/**
	 * Returns all rows from the country table that match the criteria 'longname
	 * = :longname'.
	 */
	public List<Country> findWhereLongnameEquals(String longname)
			throws CountryException;

	/**
	 * Returns all rows from the country table that match the criteria 'iso3 =
	 * :iso3'.
	 */
	public List<Country> findWhereIso3Equals(String iso3)
			throws CountryException;

	/**
	 * Returns all rows from the country table that match the criteria 'numcode
	 * = :numcode'.
	 */
	public List<Country> findWhereNumcodeEquals(String numcode)
			throws CountryException;

	/**
	 * Returns all rows from the country table that match the criteria 'unmember
	 * = :unmember'.
	 */
	public List<Country> findWhereUnmemberEquals(String unmember)
			throws CountryException;

	/**
	 * Returns all rows from the country table that match the criteria
	 * 'callingcode = :callingcode'.
	 */
	public List<Country> findWhereCallingcodeEquals(String callingcode)
			throws CountryException;

	/**
	 * Returns all rows from the country table that match the criteria 'cctld =
	 * :cctld'.
	 */
	public List<Country> findWhereCctldEquals(String cctld)
			throws CountryException;

	/**
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/**
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/**
	 * Returns all rows from the country table that match the specified
	 * arbitrary SQL statement
	 */
	public List<Country> findByDynamicSelect(String sql, List<Object> sqlParams)
			throws CountryException;

	/**
	 * Returns all rows from the country table that match the specified
	 * arbitrary SQL statement
	 */
	public List<Country> findByDynamicWhere(String sql, List<Object> sqlParams)
			throws CountryException;

}
