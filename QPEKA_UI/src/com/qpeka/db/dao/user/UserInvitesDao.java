package com.qpeka.db.dao.user;

import java.util.List;

import com.qpeka.db.exceptions.QpekaException;
import com.qpeka.db.user.profile.UserInvites;

public interface UserInvitesDao {
	/** 
	 * Inserts a new row in the UserInvite table.
	 */
	public long insert(UserInvites userInvites) throws QpekaException;

	/** 
	 * Updates a single row in the UserInvite table.
	 */
	public short update(long inviteid, UserInvites userInvites) throws QpekaException;
	
	/**
	 * Deletes a single row in the userInvites table.
	 */
	void delete(long inviteid) throws QpekaException;

	/** 
	 * Returns all rows from the userInvites table that match the criteria 'inviteid = :inviteid'.
	 */
	public UserInvites findByPrimaryKey(long inviteid) throws QpekaException;

	/** 
	 * Returns all rows from the userInvites table that match the criteria ''.
	 */
	public List<UserInvites> findAll() throws QpekaException;

	/** 
	 * Returns all rows from the userInvites table that match the criteria 'inviteid = :inviteid'.
	 */
	public List<UserInvites> findWhereInviteidEquals(long inviteid) throws QpekaException;
	
	/** 
	 * Returns all rows from the userInvites table that match the criteria 'userid = :userid'.
	 */
	public List<UserInvites> findWhereUseridEquals(long userid) throws QpekaException;

	/** 
	 * Returns all rows from the userInvites table that match the criteria 'type = :type'.
	 */
	public List<UserInvites> findWhereTypeEquals(String type) throws QpekaException;

	/** 
	 * Returns all rows from the userInvites table that match the criteria 'inviteidentifier = :inviteidentifier'.
	 */
	public List<UserInvites> findWhereInviteIdentifierEquals(String inviteidentifier) throws QpekaException;

	/** 
	 * Returns all rows from the userInvites table that match the criteria 'hashvalue = :hashvalue'.
	 */
	public List<UserInvites> findWhereHashvalueEquals(String hashvalue) throws QpekaException;

	/** 
	 * Returns all rows from the userInvites table that match the criteria 'status = :status'.
	 */
	public List<UserInvites> findWhereStatusEquals(short status) throws QpekaException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the user table that match the specified arbitrary SQL statement
	 */
	public List<UserInvites> findByDynamicSelect(String sql, List<Object> sqlParams) throws QpekaException;

	/** 
	 * Returns all rows from the user table that match the specified arbitrary SQL statement
	 */
	public List<UserInvites> findByDynamicWhere(String sql, List<Object> sqlParams) throws QpekaException;

}
