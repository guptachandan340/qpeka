package com.qpeka.db.handler.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.qpeka.db.Constants;
import com.qpeka.db.Constants.GENDER;
import com.qpeka.db.conf.ResourceManager;
import com.qpeka.db.dao.user.UserProfileDao;
import com.qpeka.db.exceptions.user.UserProfileException;
import com.qpeka.db.handler.AbstractHandler;
import com.qpeka.db.user.profile.Name;
import com.qpeka.db.user.profile.UserProfile;

public class UserProfileHandler extends AbstractHandler implements
		UserProfileDao {

	/**
	 * The factory class for this DAO has two versions of the create() method -
	 * one that takes no arguments and one that takes a Connection argument. If
	 * the Connection version is chosen then the connection will be stored in
	 * this attribute and will be used by all calls to this DAO, otherwise a new
	 * Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger
			.getLogger(UserProfileHandler.class);

	public static UserProfileHandler instance;

	/**
	 * All finder methods in this class use this SELECT constant to build their
	 * queries
	 */
	protected final String SQL_SELECT = "SELECT userid, penname, firstname, "
			+ "middlename, lastname, gender, dob, nationality, website, biography, profilepic, level FROM "
			+ getTableName() + "";

	/**
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/**
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO "
			+ getTableName()
			+ " ( userid, penname, firstname, middlename, lastname, gender, "
			+ "dob, nationality, website, biography, level, profilepic ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	/**
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE "
			+ getTableName()
			+ " SET userid = ?, penname = ?, firstname = ?, middlename = ?, "
			+ "lastname = ?, gender = ?, dob = ?, nationality = ?, website = ?, biography = ?, profilepic = ?, level = ? WHERE userid = ?";

	/**
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName()
			+ " WHERE userid = ?";

	/**
	 * Index of column userid
	 */
	protected static final int COLUMN_USERID = 1;

	/**
	 * Index of column penname
	 */
	protected static final int COLUMN_PENNAME = 2;

	/**
	 * Index of column firstname
	 */
	protected static final int COLUMN_FIRSTNAME = 3;

	/**
	 * Index of column middlename
	 */
	protected static final int COLUMN_MIDDLENAME = 4;

	/**
	 * Index of column lastname
	 */
	protected static final int COLUMN_LASTNAME = 5;

	/**
	 * Index of column gender
	 */
	protected static final int COLUMN_GENDER = 6;

	/**
	 * Index of column dob
	 */
	protected static final int COLUMN_DOB = 7;

	/**
	 * Index of column nationality
	 */
	protected static final int COLUMN_NATIONALITY = 8;

	/**
	 * Index of column website
	 */
	protected static final int COLUMN_WEBSITE = 9;

	/**
	 * Index of column biography
	 */
	protected static final int COLUMN_BIOGRAPHY = 10;

	/**
	 * Index of column profilepic
	 */
	protected static final int COLUMN_PROFILEPIC = 11;

	/**
	 * Index of column level
	 */
	protected static final int COLUMN_LEVEL = 12;

	/**
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 12;

	/**
	 * Index of primary-key column userid
	 */
	protected static final int PK_COLUMN_USERID = 1;

	public UserProfileHandler() {

	}

	public UserProfileHandler(Connection userConn) {
		this.userConn = userConn;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName() {
		return "qpeka.userprofile";
	}

	@Override
	public long insert(UserProfile user) throws UserProfileException {
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		// ResultSet rs = null;

		try {
			// get the user-specified connection or get a connection from the
			// ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();

			StringBuffer sql = new StringBuffer();
			StringBuffer values = new StringBuffer();
			sql.append("INSERT INTO " + getTableName() + " (");
			int modifiedCount = 0;
			if (user.isUseridModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("userid");
				values.append("?");
				modifiedCount++;
			}

			if (user.isPennameModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("penname");
				values.append("?");
				modifiedCount++;
			}

			if (user.isNameModified()) {
				if (user.getName().isFirstnameModified()) {
					if (modifiedCount > 0) {
						sql.append(", ");
						values.append(", ");
					}

					sql.append("firstname");
					values.append("?");
					modifiedCount++;
				}

				if (user.getName().isMiddlenameModified()) {
					if (modifiedCount > 0) {
						sql.append(", ");
						values.append(", ");
					}

					sql.append("middlename");
					values.append("?");
					modifiedCount++;
				}

				if (user.getName().isLastnameModified()) {
					if (modifiedCount > 0) {
						sql.append(", ");
						values.append(", ");
					}

					sql.append("lastname");
					values.append("?");
					modifiedCount++;
				}
			}

			if (user.isGenderModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("gender");
				values.append("?");
				modifiedCount++;
			}

			if (user.isDobModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("dob");
				values.append("?");
				modifiedCount++;
			}

			if (user.isNationalityModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("nationality");
				values.append("?");
				modifiedCount++;
			}

			if (user.isWebsiteModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("website");
				values.append("?");
				modifiedCount++;
			}

			if (user.isBiographyModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("biography");
				values.append("?");
				modifiedCount++;
			}

			if (user.isProfilepicModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("profilepic");
				values.append("?");
				modifiedCount++;
			}

			if (user.isUserlevelModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("level");
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
			stmt = conn.prepareStatement(sql.toString());
			int index = 1;
			if (user.isUseridModified()) {
				stmt.setLong(index++, user.getUserid());
			}

			if (user.isPennameModified()) {
				stmt.setString(index++, user.getPenname());
			}

			if (user.isNameModified()) {
				if (user.getName().isFirstnameModified()) {
					stmt.setString(index++, user.getName().getFirstname());
				}

				if (user.getName().isMiddlenameModified()) {
					stmt.setString(index++, user.getName().getMiddlename());
				}

				if (user.getName().isLastnameModified()) {
					stmt.setString(index++, user.getName().getLastname());
				}
			}

			if (user.isGenderModified()) {
				stmt.setString(index++, user.getGender().toString());
			}

			if (user.isDobModified()) {
				stmt.setLong(index++, user.getDob().getTime() / 1000);
			}

			if (user.isNationalityModified()) {
				stmt.setShort(index++, user.getNationality());
			}

			if (user.isWebsiteModified()) {
				stmt.setString(index++, user.getWebsite());
			}

			if (user.isBiographyModified()) {
				stmt.setString(index++, user.getBiography());
			}

			if (user.isProfilepicModified()) {
				if (user.isProfilepicNull()) {
					stmt.setNull(index++, java.sql.Types.INTEGER);
				} else {
					stmt.setLong(index++, user.getProfilepic());
				}

			}

			if (user.isUserlevelModified()) {
				stmt.setShort(index++, (short) user.getUserlevel().ordinal());
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ user);
			}

			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

			reset(user);
			return user.getUserid();
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new UserProfileException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public short update(long userid, UserProfile user)
			throws UserProfileException {
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
			if (user.isUseridModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("userid=?");
				modified = true;
			}

			if (user.isPennameModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("penname=?");
				modified = true;
			}

			if (user.isNameModified()) {
				if (user.getName().isFirstnameModified()) {
					if (modified) {
						sql.append(", ");
					}

					sql.append("firstname=?");
					modified = true;
				}

				if (user.getName().isMiddlenameModified()) {
					if (modified) {
						sql.append(", ");
					}

					sql.append("middlename=?");
					modified = true;
				}

				if (user.getName().isLastnameModified()) {
					if (modified) {
						sql.append(", ");
					}

					sql.append("lastname=?");
					modified = true;
				}
			}

			if (user.isGenderModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("gender=?");
				modified = true;
			}

			if (user.isDobModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("dob=?");
				modified = true;
			}

			if (user.isNationalityModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("nationality=?");
				modified = true;
			}

			if (user.isWebsiteModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("website=?");
				modified = true;
			}

			if (user.isBiographyModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("biography=?");
				modified = true;
			}

			if (user.isProfilepicModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("profilepic=?");
				modified = true;
			}

			if (user.isUserlevelModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("level=?");
				modified = true;
			}

			if (!modified) {
				// nothing to update
				return -1;
			}

			sql.append(" WHERE userid=?");
			if (logger.isDebugEnabled()) {
				logger.debug("Executing " + sql.toString() + " with values: "
						+ user);
			}

			stmt = conn.prepareStatement(sql.toString());
			int index = 1;
			if (user.isUseridModified()) {
				stmt.setLong(index++, user.getUserid());
			}

			if (user.isPennameModified()) {
				stmt.setString(index++, user.getPenname());
			}

			if (user.isNameModified()) {
				if (user.getName().isFirstnameModified()) {
					stmt.setString(index++, user.getName().getFirstname());
				}

				if (user.getName().isMiddlenameModified()) {
					stmt.setString(index++, user.getName().getMiddlename());
				}

				if (user.getName().isLastnameModified()) {
					stmt.setString(index++, user.getName().getLastname());
				}
			}

			if (user.isGenderModified()) {
				stmt.setString(index++, user.getGender().toString());
			}

			if (user.isDobModified()) {
				stmt.setLong(index++, user.getDob().getTime() / 1000);
			}

			if (user.isNationalityModified()) {
				stmt.setShort(index++, user.getNationality());
			}

			if (user.isWebsiteModified()) {
				stmt.setString(index++, user.getWebsite());
			}

			if (user.isBiographyModified()) {
				stmt.setString(index++, user.getBiography());
			}

			if (user.isProfilepicModified()) {
				if (user.isProfilepicNull()) {
					stmt.setNull(index++, java.sql.Types.INTEGER);
				} else {
					stmt.setLong(index++, user.getProfilepic());
				}
			}

			if (user.isUserlevelModified()) {
				stmt.setShort(index++, (short) user.getUserlevel().ordinal());
			}

			stmt.setLong(index++, userid);
			short rows = (short)stmt.executeUpdate();
			reset(user);
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}
			return rows;
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new UserProfileException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		}

	}

	@Override
	public void delete(long userid) throws UserProfileException {
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
				logger.debug("Executing " + SQL_DELETE + " with PK: " + userid);
			}

			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setLong(1, userid);
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
			}

		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new UserProfileException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}

	}

	@Override
	public UserProfile findByPrimaryKey(long userid)
			throws UserProfileException {
		List<UserProfile> ret = findByDynamicSelect(SQL_SELECT
				+ " WHERE userid = ?",
				Arrays.asList(new Object[] { new Long(userid) }));
		return ret.size() == 0 ? null : ret.get(0);
	}

	@Override
	public List<UserProfile> findAll() throws UserProfileException {
		return findByDynamicSelect(SQL_SELECT + " ORDER BY userid", null);
	}

	@Override
	public List<UserProfile> findByFiles(int profilepic)
			throws UserProfileException {
		return findByDynamicSelect(SQL_SELECT + " WHERE profilepic = ?",
				Arrays.asList(new Object[] { new Integer(profilepic) }));
	}

	@Override
	public List<UserProfile> findByUser(long userid)
			throws UserProfileException {
		return findByDynamicSelect(SQL_SELECT + " WHERE userid = ?",
				Arrays.asList(new Object[] { new Long(userid) }));
	}

	@Override
	public List<UserProfile> findByCountry(short nationality)
			throws UserProfileException {
		return findByDynamicSelect(SQL_SELECT + " WHERE nationality = ?",
				Arrays.asList(new Object[] { new Short(nationality) }));
	}

	@Override
	public List<UserProfile> findWhereUseridEquals(long userid)
			throws UserProfileException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE userid = ? ORDER BY userid",
				Arrays.asList(new Object[] { new Long(userid) }));
	}

	@Override
	public List<UserProfile> findWherePennameEquals(String penname)
			throws UserProfileException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE penname = ? ORDER BY penname",
				Arrays.asList(new Object[] { penname }));
	}

	@Override
	public List<UserProfile> findWhereFirstnameEquals(String firstname)
			throws UserProfileException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE lastname = ? ORDER BY lastname",
				Arrays.asList(new Object[] { firstname }));
	}

	@Override
	public List<UserProfile> findWhereMiddlenameEquals(String middlename)
			throws UserProfileException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE middlename = ? ORDER BY middlename",
				Arrays.asList(new Object[] { middlename }));
	}

	@Override
	public List<UserProfile> findWhereLastnameEquals(String lastname)
			throws UserProfileException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE lastname = ? ORDER BY lastname",
				Arrays.asList(new Object[] { lastname }));
	}

	@Override
	public List<UserProfile> findWhereGenderEquals(String gender)
			throws UserProfileException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE gender = ? ORDER BY gender",
				Arrays.asList(new Object[] { gender }));
	}

	@Override
	public List<UserProfile> findWhereDobEquals(int dob)
			throws UserProfileException {
		return findByDynamicSelect(SQL_SELECT + " WHERE dob = ? ORDER BY dob",
				Arrays.asList(new Object[] { new Integer(dob) }));
	}

	@Override
	public List<UserProfile> findWhereNationalityEquals(short nationality)
			throws UserProfileException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE nationality = ? ORDER BY nationality",
				Arrays.asList(new Object[] { new Short(nationality) }));
	}

	@Override
	public List<UserProfile> findWhereWebsiteEquals(String website)
			throws UserProfileException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE website = ? ORDER BY website",
				Arrays.asList(new Object[] { website }));
	}

	@Override
	public List<UserProfile> findWhereBiographyEquals(String biography)
			throws UserProfileException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE biography = ? ORDER BY biography",
				Arrays.asList(new Object[] { biography }));
	}

	@Override
	public List<UserProfile> findWhereProfilepicEquals(int profilepic)
			throws UserProfileException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE profilepic = ? ORDER BY profilepic",
				Arrays.asList(new Object[] { new Integer(profilepic) }));
	}
	
	@Override
	public List<UserProfile> findWhereLevelEquals(short level)
			throws UserProfileException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE level = ? ORDER BY level",
				Arrays.asList(new Object[] { new Short(level) }));
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
	public List<UserProfile> findByDynamicSelect(String sql,
			List<Object> sqlParams) throws UserProfileException {
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
			throw new UserProfileException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public List<UserProfile> findByDynamicWhere(String sql,
			List<Object> sqlParams) throws UserProfileException {
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
			throw new UserProfileException("Exception: " + _e.getMessage(), _e);
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
	protected UserProfile fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			UserProfile user = new UserProfile();
			populateUserProfile(user, rs);
			return user;
		} else {
			return null;
		}

	}

	/**
	 * Fetches multiple rows from the result set
	 */
	protected List<UserProfile> fetchMultiResults(ResultSet rs)
			throws SQLException {
		List<UserProfile> resultList = new ArrayList<UserProfile>();
		while (rs.next()) {
			UserProfile user = new UserProfile();
			populateUserProfile(user, rs);
			resultList.add(user);
		}

		return resultList;
	}

	/**
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateUserProfile(UserProfile user, ResultSet rs)
			throws SQLException {
		user.setUserid(rs.getInt(COLUMN_USERID));
		user.setPenname(rs.getString(COLUMN_PENNAME));
		user.getName().setFirstname(rs.getString(COLUMN_FIRSTNAME));
		user.getName().setMiddlename(rs.getString(COLUMN_MIDDLENAME));
		user.getName().setLastname(rs.getString(COLUMN_LASTNAME));
		user.setGender(Constants.GENDER.valueOf(rs.getString(COLUMN_GENDER)));
		user.setDob(new Date(rs.getLong(COLUMN_DOB) * 1000));

		user.setNationality(rs.getShort(COLUMN_NATIONALITY));

		user.setWebsite(rs.getString(COLUMN_WEBSITE));
		user.setBiography(rs.getString(COLUMN_BIOGRAPHY));
		user.setProfilepic(rs.getInt(COLUMN_PROFILEPIC));
		if (rs.wasNull()) {
			user.setProfilepicNull(true);
		}

		reset(user);
	}

	/**
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(UserProfile user) {
		user.setUseridModified(false);
		user.setPennameModified(false);
		user.getName().setFirstnameModified(false);
		user.getName().setMiddlenameModified(false);
		user.getName().setLastnameModified(false);
		user.setGenderModified(false);
		user.setDobModified(false);
		user.setNationalityModified(false);
		user.setWebsiteModified(false);
		user.setBiographyModified(false);
		user.setProfilepicModified(false);
	}

	/**
	 * User profile handler instance
	 * 
	 * @return instance of user profile
	 */
	public static UserProfileHandler getInstance() {
		return (instance == null ? (instance = new UserProfileHandler())
				: instance);
	}

	/**
	 * Compute age of a person
	 */
	public short deriveAge(Date dob) {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.setTime(dob);
		Calendar today = Calendar.getInstance();

		int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
		if (today.get(Calendar.MONDAY) < dateOfBirth.get(Calendar.MONTH)) {
			age--;
		} else if (today.get(Calendar.MONTH) == dateOfBirth.get(Calendar.MONTH)
				&& today.get(Calendar.DAY_OF_MONTH) < dateOfBirth
						.get(Calendar.DAY_OF_MONTH)) {
			age--;
		}

		return (short) age;
	}

	public static void main(String[] args) throws ParseException,
			UserProfileException {
		UserProfileHandler up = new UserProfileHandler();
		Name name = new Name("Rahul", "Baban", "Shelke");
		String biography = "An Optimist with zest for life, fluid thinking, adventure, network, and programming!!! I love to meet people, get in touch with."
				+ "I have been working on opensource technologies like Java, Drupal, Pentaho, Linux, Perl, etc. It has been a great source of sharing the knowledge and making impact online all over the world.";
		// Address address = new Address("India", "India", "", "Thane",
		// "Maharashtra", "India", 400603);
		Date dob = new Date();
		UserProfile user = new UserProfile(1, "Rauline", name,
				GENDER.valueOf("Male"), dob, up.deriveAge(dob), (short) 102,
				"rahulshelke.com", biography, 1);
		// "/home/rahul/Pictures/Webcam/2013-06-10-123500.jpg"
		// user.setNameModified(true);
		// user.setUseridModified(true);
		// user.setPennameModified(true);
		// user.setGenderModified(true);
		// user.setDobModified(true);
		// user.setAgeModified(true);
		// user.setNationalityModified(true);
		// user.setWebsiteModified(true);
		user.setBiographyModified(true);
		// user.setProfilepicModified(true);
		// user.setNameModified(true);
		// user.getName().setFirstnameModified(true);
		// user.getName().setMiddlenameModified(true);
		// user.getName().setLastnameModified(true);

		up.update(1, user);

		// System.out.println(user.getDob());
		// DateFormat df = new SimpleDateFormat("yyyy-MM-dd");// EEE MMM dd
		// // HH:mm:ss z yyyy
		// String temp = df.format(user.getDob());
		// Date d = (Date) df.parse("1986-06-14");
		// System.out.println(up.deriveAge(d));
	}

	// private static UserProfileHandler instance = new UserProfileHandler();
	// private DB db = null;
	// private DBCollection users = null;
	//
	// private UserProfileHandler()
	// {
	// db = MongoAccessor.getInstance().getMongo().getDB("bookstore");
	// if(!db.isAuthenticated())
	// db.authenticate("qpeka", new char[]{'q','p','e','k','a'});
	// users = db.getCollection("users");
	// }
	//
	// public static UserProfileHandler getInstance()
	// {
	// return instance;
	// }
	//
	// public String addUser(UserProfile user)
	// {
	//
	// BasicDBObject dObj = (BasicDBObject)user.toDBObject(true);
	// WriteResult result = users.insert(dObj, WriteConcern.SAFE);
	// ObjectId id = dObj.getObjectId("_id");
	//
	// if(user.isWriter())
	// {
	// Set<CATEGORY> category = new HashSet<CATEGORY>();
	//
	// Author author = new Author(id.toString(), user.getName(),
	// user.getGender(), user.getDob(), user.getNationality(),
	// user.getImageFile(),
	// user.getDesc(), "" , category, AUTHORTYPE.LEVEL1);
	//
	// AuthorHandler.getInstance().addUserAsAuthor(author);
	// }
	// return id.toString();
	// }
	//
	// public String addUser(UserProfile user, Set<CATEGORY> genre, AUTHORTYPE
	// type , String infoLink)
	// {
	//
	// BasicDBObject dObj = (BasicDBObject)user.toDBObject(true);
	// WriteResult result = users.insert(dObj, WriteConcern.SAFE);
	// ObjectId id = dObj.getObjectId("_id");
	//
	// if(user.isWriter())
	// {
	//
	// Author author = new Author(id.toString(), user.getName(),
	// user.getGender(), user.getDob(), user.getNationality(),
	// user.getImageFile(),
	// user.getDesc(), infoLink , genre, type);
	//
	// AuthorHandler.getInstance().addUserAsAuthor(author);
	// }
	//
	// return id.toString();
	// }
	//
	// public void updateUser(UserProfile user)
	// {
	// BasicDBObject q = new BasicDBObject();
	// q.put(UserProfile.ID, new ObjectId(user.get_id()));
	//
	// users.update(q, new BasicDBObject("$set" , user.toDBObject(true)), true,
	// false, WriteConcern.SAFE);
	// }
	//
	// public void updateRLang(String uid , LANGUAGES l)
	// {
	// BasicDBObject q = new BasicDBObject();
	// q.put(UserProfile.ID, new ObjectId(uid));
	//
	// users.update(q, new BasicDBObject("$push" , new
	// BasicDBObject(UserProfile.RLANG, l.toString())), true, false,
	// WriteConcern.SAFE);
	// }
	//
	// public void updateWLang(String uid , LANGUAGES l)
	// {
	// BasicDBObject q = new BasicDBObject();
	// q.put(UserProfile.ID, new ObjectId(uid));
	//
	// users.update(q, new BasicDBObject("$push" , new
	// BasicDBObject(UserProfile.WLANG, l.toString())), true, false,
	// WriteConcern.SAFE);
	// }
	//
	// public void updateUser(JSONObject userAttrs)
	// {
	// try
	// {
	// BasicDBObject q = new BasicDBObject();
	// q.put(UserProfile.ID, new ObjectId(userAttrs.getString("id")));
	//
	// BasicDBObject bdobj = new BasicDBObject();
	// Iterator<String> i = userAttrs.keys();
	// while(i.hasNext())
	// {
	// String key = i.next();
	// if(!key.equalsIgnoreCase("id"))
	// bdobj.put(key, userAttrs.getString(key));
	//
	// if(userAttrs.get(key) instanceof JSONObject)
	// {
	// BasicDBObject bsubobj = new BasicDBObject();
	// Iterator<String> j = ((JSONObject)userAttrs.get(key)).keys();
	//
	// while(j.hasNext())
	// {
	// String skey = j.next();
	// bsubobj.put(skey, ((JSONObject)userAttrs.get(key)).getString(skey));
	// }
	//
	// bdobj.put(key, bsubobj);
	// }
	// }
	//
	// users.update(q, new BasicDBObject("$set" , bdobj), true, false,
	// WriteConcern.SAFE);
	// }
	// catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public UserProfile getUser(String userId)
	// {
	// BasicDBObject q = new BasicDBObject();
	// q.put(UserProfile.ID, new ObjectId(userId));
	//
	// DBCursor cursor = users.find(q);
	//
	// try
	// {
	// if(cursor.hasNext())
	// {
	// BasicDBObject dObj = (BasicDBObject)cursor.next();
	// UserProfile user = UserProfile.getUserfromDBObject(dObj);
	// return user;
	// }
	// else
	// return null;
	// }
	// catch (Exception e)
	// {
	// e.printStackTrace();
	// return null;
	// }
	// finally {
	// cursor.close();
	// }
	// }
	//
	// public UserProfile getUserByUserName(String userName)
	// {
	// BasicDBObject q = new BasicDBObject();
	// q.put(UserProfile.USERNAME, userName);
	//
	// DBCursor cursor = users.find(q);
	//
	// try
	// {
	// if(cursor.hasNext())
	// {
	// BasicDBObject dObj = (BasicDBObject)cursor.next();
	// UserProfile user = UserProfile.getUserfromDBObject(dObj);
	// return user;
	// }
	// else
	// return null;
	// }
	// catch (Exception e)
	// {
	// e.printStackTrace();
	// return null;
	// }
	// finally {
	// cursor.close();
	// }
	// }
	//
	// public List<UserProfile> getUsersBySearchKey(String searchKey)
	// {
	// List<UserProfile> retList = new ArrayList<UserProfile>();
	//
	// BasicDBList bdl = new BasicDBList();
	// bdl.add(new BasicDBObject(UserProfile.USERNAME,
	// java.util.regex.Pattern.compile(searchKey,Pattern.CASE_INSENSITIVE)));
	// bdl.add(new BasicDBObject(UserProfile.NAME+"."+Name.FIRSTNAME,
	// Pattern.compile(searchKey,Pattern.CASE_INSENSITIVE)));
	// bdl.add(new BasicDBObject(UserProfile.NAME+"."+Name.LASTNAME,
	// Pattern.compile(searchKey,Pattern.CASE_INSENSITIVE)));
	//
	// BasicDBObject q = new BasicDBObject();
	// q.put("$or", bdl);
	//
	// DBCursor cursor = users.find(q);
	//
	// try
	// {
	// while(cursor.hasNext())
	// {
	// BasicDBObject dObj = (BasicDBObject)cursor.next();
	// UserProfile user = UserProfile.getUserfromDBObject(dObj);
	// retList.add(user);
	// }
	// return retList;
	//
	// }
	// catch (Exception e)
	// {
	// e.printStackTrace();
	// return retList;
	// }
	// finally {
	// cursor.close();
	//
	// }
	//
	// }
}
