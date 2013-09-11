package com.qpeka.db.handler.user;

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
import com.qpeka.db.dao.user.UserDao;
import com.qpeka.db.exceptions.user.UserException;
import com.qpeka.db.handler.AbstractHandler;
import com.qpeka.db.user.User;

public class UserHandler extends AbstractHandler implements UserDao {

	/**
	 * The factory class for this DAO has two versions of the create() method -
	 * one that takes no arguments and one that takes a Connection argument. If
	 * the Connection version is chosen then the connection will be stored in
	 * this attribute and will be used by all calls to this DAO, otherwise a new
	 * Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger.getLogger(UserHandler.class);
	
	public static UserHandler instance;

	/**
	 * All finder methods in this class use this SELECT constant to build their
	 * queries
	 */
	protected final String SQL_SELECT = "SELECT userid, penname, password, email, "
			+ "created, lastaccess, lastlogin, status, type, timezone FROM "
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
			+ " ( userid, penname, password, email, created, lastaccess, lastlogin, "
			+ "status, type, timezone ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	/**
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE "
			+ getTableName()
			+ " SET userid = ?, penname = ?, password = ?, email = ?, created = ?, lastaccess = ?, "
			+ "lastlogin = ?, status = ?, type = ?, timezone = ? WHERE userid = ?";

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
	 * Index of column username
	 */
	protected static final int COLUMN_PENNAME = 2;

	/**
	 * Index of column password
	 */
	protected static final int COLUMN_PASSWORD = 3;

	/**
	 * Index of column email
	 */
	protected static final int COLUMN_EMAIL = 4;

	/**
	 * Index of column created
	 */
	protected static final int COLUMN_CREATED = 5;

	/**
	 * Index of column lastaccess
	 */
	protected static final int COLUMN_LASTACCESS = 6;

	/**
	 * Index of column lastlogin
	 */
	protected static final int COLUMN_LASTLOGIN = 7;

	/**
	 * Index of column status
	 */
	protected static final int COLUMN_STATUS = 8;
	
	/**
	 * Index of column type
	 */
	protected static final int COLUMN_TYPE = 9;

	/**
	 * Index of column timezone
	 */
	protected static final int COLUMN_TIMEZONE = 10;

	/**
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 10;

	/**
	 * Index of primary-key column userid
	 */
	protected static final int PK_COLUMN_USERID = 1;

	public UserHandler() {
		super();
	}

	public UserHandler(final Connection userConn) {
		this.userConn = userConn;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName() {
		return "qpeka.user";
	}

	/**
	 * Inserts a new row in the user table.
	 */
	@Override
	public long insert(User user) throws UserException {

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

			if (user.isPasswordModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("password");
				values.append("?");
				modifiedCount++;
			}

			if (user.isEmailModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("email");
				values.append("?");
				modifiedCount++;
			}

			if (user.isCreatedModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("created");
				values.append("?");
				modifiedCount++;
			}

			if (user.isLastaccessModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("lastaccess");
				values.append("?");
				modifiedCount++;
			}

			if (user.isLastloginModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("lastlogin");
				values.append("?");
				modifiedCount++;
			}

			if (user.isStatusModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("status");
				values.append("?");
				modifiedCount++;
			}
			
			if (user.isTypeModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("type");
				values.append("?");
				modifiedCount++;
			}

			if (user.isTimezoneModified()) {
				if (modifiedCount > 0) {
					sql.append(", ");
					values.append(", ");
				}

				sql.append("timezone");
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
			if (user.isUseridModified()) {
				stmt.setLong(index++, user.getUserid());
			}

			if (user.isPennameModified()) {
				stmt.setString(index++, user.getPenname());
			}

			if (user.isPasswordModified()) {
				stmt.setString(index++, user.getPassword());
			}

			if (user.isEmailModified()) {
				stmt.setString(index++, user.getEmail());
			}

			if (user.isCreatedModified()) {
				stmt.setLong(index++, user.getCreated());
			}

			if (user.isLastaccessModified()) {
				stmt.setLong(index++, user.getLastaccess());
			}

			if (user.isLastloginModified()) {
				stmt.setLong(index++, user.getLastlogin());
			}

			if (user.isStatusModified()) {
				stmt.setShort(index++, user.getStatus());
			}
			
			if (user.isTypeModified()) {
				stmt.setShort(index++, user.getType());
			}

			if (user.isTimezoneModified()) {
				stmt.setString(index++, user.getTimezone());
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

			// retrieve values from auto-increment columns
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				user.setUserid(rs.getInt(1));
			}

			reset(user);
			return user.getUserid();
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new UserException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		}
	}

	@Override
	public short update(long userid, User user) throws UserException {
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

			if (user.isPasswordModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("password=?");
				modified = true;
			}

			if (user.isEmailModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("email=?");
				modified = true;
			}

			if (user.isCreatedModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("created=?");
				modified = true;
			}

			if (user.isLastaccessModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("lastaccess=?");
				modified = true;
			}

			if (user.isLastloginModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("lastlogin=?");
				modified = true;
			}

			if (user.isStatusModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("status=?");
				modified = true;
			}
			
			if (user.isTypeModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("type=?");
				modified = true;
			}

			if (user.isTimezoneModified()) {
				if (modified) {
					sql.append(", ");
				}

				sql.append("timezone=?");
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

			if (user.isPasswordModified()) {
				stmt.setString(index++, user.getPassword());
			}
			
			if (user.isEmailModified()) {
				stmt.setString(index++, user.getEmail());
			}

			if (user.isCreatedModified()) {
				stmt.setLong(index++, user.getCreated());
			}

			if (user.isLastaccessModified()) {
				stmt.setLong(index++, user.getLastaccess());
			}

			if (user.isLastloginModified()) {
				stmt.setLong(index++, user.getLastlogin());
			}

			if (user.isStatusModified()) {
				stmt.setShort(index++, user.getStatus());
			}
			
			if (user.isTypeModified()) {
				stmt.setShort(index++, user.getType());
			}

			if (user.isTimezoneModified()) {
				stmt.setString(index++, user.getTimezone());
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
			throw new UserException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
		
	}

	@Override
	public void delete(long userid) throws UserException {

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
			throw new UserException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}

	}

	@Override
	public User findByPrimaryKey(long userid) throws UserException {
		List<User> ret = findByDynamicSelect(SQL_SELECT + " WHERE userid = ?",
				Arrays.asList(new Object[] { new Long(userid) }));
		return ret.size() == 0 ? null : ret.get(0);
	}

	@Override
	public List<User> findAll() throws UserException {
		return findByDynamicSelect(SQL_SELECT + " ORDER BY userid", null);
	}

	@Override
	public List<User> findWhereUseridEquals(long userid) throws UserException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE userid = ? ORDER BY userid",
				Arrays.asList(new Object[] { new Long(userid) }));
	}

	@Override
	public List<User> findWherePennameEquals(String penname)
			throws UserException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE penname = ? ORDER BY penname",
				Arrays.asList(new Object[] { penname }));
	}

	@Override
	public List<User> findWherePasswordEquals(String password)
			throws UserException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE password = ? ORDER BY password",
				Arrays.asList(new Object[] { password }));
	}

	@Override
	public List<User> findWhereEmailEquals(String email) throws UserException {
		
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE email = ? ORDER BY email",
				Arrays.asList(new Object[] { email }));
	}

	@Override
	public List<User> findWhereCreatedEquals(long created) throws UserException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE created = ? ORDER BY created",
				Arrays.asList(new Object[] { new Long(created) }));
	}

	@Override
	public List<User> findWhereLastaccessEquals(long lastaccess)
			throws UserException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE lastaccess = ? ORDER BY lastaccess",
				Arrays.asList(new Object[] { new Long(lastaccess) }));
	}

	@Override
	public List<User> findWhereLastloginEquals(long lastlogin)
			throws UserException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE lastlogin = ? ORDER BY lastlogin",
				Arrays.asList(new Object[] { new Long(lastlogin) }));
	}

	@Override
	public List<User> findWhereStatusEquals(short status) throws UserException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE status = ? ORDER BY status",
				Arrays.asList(new Object[] { new Short(status) }));
	}
	
	@Override
	public List<User> findWhereTypeEquals(short type) throws UserException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE type = ? ORDER BY type",
				Arrays.asList(new Object[] { new Short(type) }));
		
	}

	@Override
	public List<User> findWhereTimezoneEquals(String timezone)
			throws UserException {
		return findByDynamicSelect(SQL_SELECT
				+ " WHERE timezone = ? ORDER BY timezone",
				Arrays.asList(new Object[] { timezone }));
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
	public List<User> findByDynamicSelect(String sql, List<Object> sqlParams)
			throws UserException {
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
			throw new UserException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}
	}

	@Override
	public List<User> findByDynamicWhere(String sql, List<Object> sqlParams)
			throws UserException {
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
			throw new UserException("Exception: " + _e.getMessage(), _e);
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
	protected User fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			User user = new User();
			populateUser(user, rs);
			return user;
		} else {
			return null;
		}

	}

	/**
	 * Fetches multiple rows from the result set
	 */
	protected List<User> fetchMultiResults(ResultSet rs) throws SQLException {
		List<User> resultList = new ArrayList<User>();
		while (rs.next()) {
			User user = new User();
			populateUser(user, rs);
			resultList.add(user);
		}
		return resultList;
	}

	/**
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateUser(User user, ResultSet rs) throws SQLException {
		user.setUserid(rs.getLong(COLUMN_USERID));
		user.setPenname(rs.getString(COLUMN_PENNAME));
		user.setPassword(rs.getString(COLUMN_PASSWORD));
		user.setEmail(rs.getString(COLUMN_EMAIL));
		user.setCreated(rs.getLong(COLUMN_CREATED));
		user.setLastaccess(rs.getLong(COLUMN_LASTACCESS));
		user.setLastlogin(rs.getLong(COLUMN_LASTLOGIN));
		user.setStatus(rs.getShort(COLUMN_STATUS));
		user.setType(rs.getShort(COLUMN_TYPE));
		user.setTimezone(rs.getString(COLUMN_TIMEZONE));
		// user.setLanguage( rs.getShort( COLUMN_LANGUAGE ) );
		reset(user);
	}

	/**
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(User user) {
		user.setUseridModified(false);
		user.setPennameModified(false);
		user.setPasswordModified(false);
		user.setEmailModified(false);
		user.setCreatedModified(false);
		user.setLastaccessModified(false);
		user.setLastloginModified(false);
		user.setStatusModified(false);
		user.setTypeModified(false);
		user.setTimezoneModified(false);
	}
	
	/**
	 * Get UserHandler object instance
	 * @return instance of UserHandler
	 */
	public static UserHandler getInstance() {
		return (instance == null ? (instance = new UserHandler()) : instance);
	}

/*	*//**
	 * Authenticate User
	 * 
	 * @throws UserException
	 *//*
	public User authenticateByEmail(String email, String password)
			throws UserException {
		List<User> user = new ArrayList<User>();
		try {
			user = findWhereEmailEquals(email);
		} catch (UserException _e) {
			throw new UserException("User Authentication Exception: "
					+ _e.getMessage(), _e);
		}

		if (!user.isEmpty()) {
			return (BCrypt.checkpw(password, user.get(0).getPassword()) ? user
					.get(0) : null);
		} else {
			return null;
		}
	}

	*//**
	 * Authenticate User
	 * 
	 * @throws UserException
	 *//*
	public User authenticateByUsername(String username, String password)
			throws UserException {
		List<User> user = new ArrayList<User>();
		try {
			user = findWhereUsernameEquals(username);
		} catch (UserException _e) {
			throw new UserException("User Authentication Exception: "
					+ _e.getMessage(), _e);
		}

		if (!user.isEmpty()) {
			return (BCrypt.checkpw(password, user.get(0).getPassword()) ? user
					.get(0) : null);
		} else {
			return null;
		}

	}

	*//**
	 * Username exists?
	 * 
	 * @throws UserException
	 *//*
	public boolean usernameExists(String username) throws UserException {
		List<User> userList = new ArrayList<User>();
		try {
			userList = findWhereUsernameEquals(username);
		} catch (UserException _e) {
			throw new UserException("User Authentication Exception: "
					+ _e.getMessage(), _e);
		}

		if (!userList.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	*//**
	 * Email exists?
	 * 
	 * @throws UserException
	 *//*
	public boolean emailExists(String email) throws UserException {
		List<User> userList = new ArrayList<User>();
		try {
			userList = findWhereEmailEquals(email);
		} catch (UserException _e) {
			throw new UserException("User Authentication Exception: "
					+ _e.getMessage(), _e);
		}

		if (!userList.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	*//**
	 * Create user Account
	 * 
	 * @throws UserException
	 *//*
	
	*//**
	 * Change account password
	 * 
	 * @throws UserException
	 *//*
	public User changePassword(long userid, String password)
			throws UserException {
		User user = new User();
		try {
			user = findByPrimaryKey(userid);
			user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));

			update(userid, user);
		} catch (UserException _e) {
			throw new UserException("Update User Password Exception: "
					+ _e.getMessage(), _e);
		}

		return user;
	}

	*//**
	 * Reset users password
	 * 
	 * @throws UserException
	 *//*
	public User resetPassword(String username, boolean usernameStatus)
			throws UserException {
		List<User> user = new ArrayList<User>();

		if (!usernameStatus) {
			try {
				user = findWhereUsernameEquals(username);
			} catch (UserException _e) {
				throw new UserException("Reset user Password Exception: "
						+ _e.getMessage(), _e);
			}
		}

		return user.get(0);
	}

	*//**
	 * 
	 * @param args
	 * @throws UserException
	 *//*
	public static void main(String[] args) throws UserException {
		UserHandler handler = new UserHandler();
		User user = new User("qpeka1",
				BCrypt.hashpw("rahul", BCrypt.gensalt()),
				"srahul07.qpeka1@gmail.com",
				(System.currentTimeMillis()) / 1000, "East");
		user.setUsernameModified(true);
		user.setPasswordModified(true);
		user.setEmailModified(true);
		user.setCreatedModified(true);
		user.setTimezoneModified(true);
		if (!handler.emailExists(user.getEmail())) {
			handler.insert(user);
		}

		// user = handler.authenticateByUsername("srahul07", "diamirza");
		user = handler.authenticateByEmail("srahul07.qpeka2@gmail.com",
				"diamirza1");
		System.out.println(user);
	}
*/
	// private DB db = null;
	// private DBCollection users = null;
	//
	// private UserHandler()
	// {
	// db = MongoAccessor.getInstance().getMongo().getDB("bookstore");
	// if(!db.isAuthenticated())
	// db.authenticate("qpeka", new char[]{'q','p','e','k','a'});
	//
	// users = db.getCollection("userAuth");
	// users.ensureIndex(new BasicDBObject("username", "1"), new
	// BasicDBObject("unique", true));
	// }
	//
	// public static UserHandler getInstance()
	// {
	// return instance;
	// }
	//
	// public String addUserAuth(User userAuth)
	// {
	//
	// BasicDBObject dObj = (BasicDBObject)userAuth.toDBObject(false);
	// WriteResult result = users.insert(dObj, WriteConcern.SAFE);
	// ObjectId id = dObj.getObjectId("_id");
	// return id.toString();
	// }
	//
	// public void updateUser(User user)
	// {
	// BasicDBObject q = new BasicDBObject();
	// q.put("username", user.getUsername());
	//
	// users.update(q, new BasicDBObject("$set" , user.toDBObject(true)), true,
	// false, WriteConcern.SAFE);
	// }
	//
	// public User getUser(String userName)
	// {
	// BasicDBObject q = new BasicDBObject();
	// q.put("username", userName);
	//
	// DBCursor cursor = users.find(q);
	//
	// try
	// {
	// if(cursor.hasNext())
	// {
	// BasicDBObject dObj = (BasicDBObject)cursor.next();
	// User user = User.getUserfromDBObject(dObj);
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
	// public boolean getUser(String userName, String password)
	// {
	// BasicDBObject q = new BasicDBObject();
	// q.put("username", userName);
	//
	// DBCursor cursor = users.find(q);
	//
	// try
	// {
	// if(cursor.hasNext())
	// {
	// return true;
	// }
	// else
	// return false;
	// }
	// catch (Exception e)
	// {
	// e.printStackTrace();
	// return false;
	// }
	// finally {
	// cursor.close();
	// }
	// }
}
