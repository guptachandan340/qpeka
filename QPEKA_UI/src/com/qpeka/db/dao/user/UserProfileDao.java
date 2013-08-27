package com.qpeka.db.dao.user;

import java.util.List;

import com.qpeka.db.exceptions.user.UserProfileException;
import com.qpeka.db.user.profile.UserProfile;

public interface UserProfileDao {
	
	/** 
	 * Inserts a new row in the userprofile table.
	 */
	public long insert(UserProfile user) throws UserProfileException;

	/** 
	 * Updates a single row in the userprofile table.
	 */
	public short update(long userid, UserProfile user) throws UserProfileException;

	/** 
	 * Deletes a single row in the userprofile table.
	 */
	public void delete(long userid) throws UserProfileException;

	/** 
	 * Returns the rows from the userprofile table that matches the specified primary-key value.
	 */
	public UserProfile findByPrimaryKey(long userid) throws UserProfileException;

	/** 
	 * Returns all rows from the userprofile table that match the criteria ''.
	 */
	public List<UserProfile> findAll() throws UserProfileException;
	
	/** 
	 * Returns all rows from the userprofile table that match the criteria 'profilepic = :profilepic'.
	 */
	public List<UserProfile> findByFiles(int profilepic) throws UserProfileException;

	/** 
	 * Returns all rows from the userprofile table that match the criteria 'userid = :userid'.
	 */
	public List<UserProfile> findByUser(long userid) throws UserProfileException;
	
	/** 
	 * Returns all rows from the userprofile table that match the criteria 'nationality = :nationality'.
	 */
	public List<UserProfile> findByCountry(short nationality) throws UserProfileException;

	/** 
	 * Returns all rows from the userprofile table that match the criteria 'userid = :userid'.
	 */
	public List<UserProfile> findWhereUseridEquals(long userid) throws UserProfileException;

	/** 
	 * Returns all rows from the userprofile table that match the criteria 'firstname = :firstname'.
	 */
	public List<UserProfile> findWhereFirstnameEquals(String firstname) throws UserProfileException;

	/** 
	 * Returns all rows from the userprofile table that match the criteria 'middlename = :middlename'.
	 */
	public List<UserProfile> findWhereMiddlenameEquals(String middlename) throws UserProfileException;

	/** 
	 * Returns all rows from the userprofile table that match the criteria 'lastname = :lastname'.
	 */
	public List<UserProfile> findWhereLastnameEquals(String lastname) throws UserProfileException;

	/** 
	 * Returns all rows from the userprofile table that match the criteria 'gender = :gender'.
	 */
	public List<UserProfile> findWhereGenderEquals(String gender) throws UserProfileException;

	/** 
	 * Returns all rows from the userprofile table that match the criteria 'dob = :dob'.
	 */
	public List<UserProfile> findWhereDobEquals(int dob) throws UserProfileException;

	/** 
	 * Returns all rows from the userprofile table that match the criteria 'nationality = :nationality'.
	 */
	public List<UserProfile> findWhereNationalityEquals(short nationality) throws UserProfileException;

	/** 
	 * Returns all rows from the userprofile table that match the criteria 'website = :website'.
	 */
	public List<UserProfile> findWhereWebsiteEquals(String website) throws UserProfileException;

	/** 
	 * Returns all rows from the userprofile table that match the criteria 'biography = :biography'.
	 */
	public List<UserProfile> findWhereBiographyEquals(String biography) throws UserProfileException;
	
	/** 
	 * Returns all rows from the userprofile table that match the criteria 'profilepic = :profilepic'.
	 */
	public List<UserProfile> findWhereProfilepicEquals(int profilepic) throws UserProfileException;
	
	/** 
	 * Returns all rows from the userprofile table that match the criteria 'level = :level'.
	 */
	public List<UserProfile> findWhereLevelEquals(short level) throws UserProfileException;

	/** 
	 * Returns all rows from the userprofile table that match the criteria 'tnc = :tnc'.
	 */
	public List<UserProfile> findWhereTncEquals(short tnc) throws UserProfileException;
	
	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the userprofile table that match the specified arbitrary SQL statement
	 */
	public List<UserProfile> findByDynamicSelect(String sql, List<Object> sqlParams) throws UserProfileException;

	/** 
	 * Returns all rows from the userprofile table that match the specified arbitrary SQL statement
	 */
	public List<UserProfile> findByDynamicWhere(String sql, List<Object> sqlParams) throws UserProfileException;

	
	
}
