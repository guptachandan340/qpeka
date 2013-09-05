package com.qpeka.db.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import com.qpeka.db.Genre;
import com.qpeka.db.conf.ResourceManager;
import com.qpeka.db.dao.GenreDao;
import com.qpeka.db.exceptions.GenreException;

public class GenreHandler extends AbstractHandler implements GenreDao {

		/**
		 * The factory class for this DAO has two versions of the create() method -
		 * one that takes no arguments and one that takes a Connection argument. If
		 * the Connection version is chosen then the connection will be stored in
		 * this attribute and will be used by all calls to this DAO, otherwise a new
		 * Connection will be allocated for each operation.
		 */
		protected java.sql.Connection userConn;

		protected static final Logger logger = Logger
				.getLogger(CategoryHandler.class);

		public static GenreHandler instance = null;

		/**
		 * All finder methods in this class use this SELECT constant to build their
		 * queries
		 */
		protected final String SQL_SELECT = "SELECT genreid, categoryid, genre, points FROM "
				+ getTableName() + "";

		/**
		 * Finder methods will pass this value to the JDBC setMaxRows method
		 */
		protected int maxRows;

		/**
		 * SQL INSERT statement for this table
		 */
		protected final String SQL_INSERT = "INSERT INTO " + getTableName()
				+ " ( genreid, categoryid, genre, points ) "
				+ "VALUES ( ?, ?, ?, ? )";

		/**
		 * SQL UPDATE statement for this table
		 */
		protected final String SQL_UPDATE = "UPDATE " + getTableName()
				+ " SET genreid = ?, categoryid = ?, genre = ?, "
				+ "points = ? WHERE genreid = ?";

		/**
		 * SQL DELETE statement for this table
		 */
		protected final String SQL_DELETE = "DELETE FROM " + getTableName()
				+ " WHERE genreid = ?";

		/**
		 * Index of column genreid
		 */
		protected static final int COLUMN_GENREID = 1;

		/**
		 * Index of column categoryid
		 */
		protected static final int COLUMN_CATEGORYID = 2;

		/**
		 * Index of column genre
		 */
		protected static final int COLUMN_GENRE = 3;

		/**
		 * Index of column points
		 */
		protected static final int COLUMN_POINTS = 4;

		/**
		 * Number of columns
		 */
		protected static final int NUMBER_OF_COLUMNS = 4;

		/**
		 * Index of primary-key column genreid
		 */
		protected static final int PK_COLUMN_GENREID = 1;

		public GenreHandler() {
			super();
		}

		public GenreHandler(Connection userConn) {
			super();
			this.userConn = userConn;
		}

		public GenreHandler(Connection userConn, int maxRows) {
			super();
			this.userConn = userConn;
			this.maxRows = maxRows;
		}

		/**
		 * Method 'getTableName'
		 * 
		 * @return String
		 */
		public String getTableName() {
			return "qpeka.genre";
		}

		@Override
		public short insert(Genre genre) throws GenreException {
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
				
				if(genre.isGenreidModified()) {
					if(modifiedCount > 0) {
						sql.append(", ");
						values.append(", ");
					}
					sql.append("genreid");
					values.append("?");
					modifiedCount++;
				}
				
				if (genre.isCategoryidModified()) {
					if (modifiedCount > 0) {
						sql.append(", ");
						values.append(", ");
					}

					sql.append("categoryid");
					values.append("?");
					modifiedCount++;
				}

				if (genre.isGenreModified()) {
					if (modifiedCount > 0) {
						sql.append(", ");
						values.append(", ");
					}

					sql.append("genre");
					values.append("?");
					modifiedCount++;
				}

				if (genre.isPointsModified()) {
					if (modifiedCount > 0) {
						sql.append(", ");
						values.append(", ");
					}

					sql.append("points");
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
				if (genre.isGenreidModified()) {
					stmt.setShort(index++, genre.getGenreid());
				}
				
				if (genre.isCategoryidModified()) {
					stmt.setShort(index++, genre.getCategoryid());
				}
				
				if (genre.isGenreModified()) {
					stmt.setString(index++, genre.getGenre());
				}

				if (genre.isPointsModified()) {
					stmt.setInt(index++, genre.getPoints());
				}

				if (logger.isDebugEnabled()) {
					logger.debug("Executing " + sql.toString() + " with values: "
							+ genre);
				}

				int rows = stmt.executeUpdate();
				long t2 = System.currentTimeMillis();
				if (logger.isDebugEnabled()) {
					logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
				}

				// retrieve values from auto-increment columns
				rs = stmt.getGeneratedKeys();
				if (rs != null && rs.next()) {
					genre.setGenreid(rs.getShort(1));
				}

				reset(genre);
				return genre.getGenreid();
			} catch (Exception _e) {
				logger.error("Exception: " + _e.getMessage(), _e);
				throw new GenreException("Exception: " + _e.getMessage(), _e);
			} finally {
				ResourceManager.close(stmt);
				if (!isConnSupplied) {
					ResourceManager.close(conn);
				}

			}
		}

		@Override
		public short update(short genreid, Genre genre)
				throws GenreException {
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
				if (genre.isGenreidModified()) {
					if (modified) {
						sql.append(", ");
					}
					sql.append("genreid=?");
					modified = true;
				}

				if (genre.isCategoryidModified()) {
					if (modified) {
						sql.append(", ");
					}
					sql.append("categoryid=?");
					modified = true;
				}
				
				if (genre.isGenreModified()) {
					if (modified) {
						sql.append(", ");
					}

					sql.append("genre=?");
					modified = true;
				}

				if (genre.isPointsModified()) {
					if (modified) {
						sql.append(", ");
					}

					sql.append("points=?");
					modified = true;
				}

				if (!modified) {
					// nothing to update
					return -1;
				}

				sql.append(" WHERE genreid=?");
				if (logger.isDebugEnabled()) {
					logger.debug("Executing " + sql.toString() + " with values: "
							+ genre);
				}

				stmt = conn.prepareStatement(sql.toString());
				int index = 1;
				if(genre.isGenreidModified()) {
					stmt.setShort(index++, genre.getGenreid());
				}
				
				if (genre.isCategoryidModified()) {
					stmt.setShort(index++, genre.getCategoryid());
				}

				if (genre.isGenreModified()) {
					stmt.setString(index++, genre.getGenre());
				}

				if (genre.isPointsModified()) {
					stmt.setInt(index++, genre.getPoints());
				}

				stmt.setShort(index++, genreid);
				short rows = (short) stmt.executeUpdate();
				reset(genre);
				long t2 = System.currentTimeMillis();
				if (logger.isDebugEnabled()) {
					logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
				}
				return rows;
			} catch (Exception _e) {
				logger.error("Exception: " + _e.getMessage(), _e);
				throw new GenreException("Exception: " + _e.getMessage(), _e);
			} finally {
				ResourceManager.close(stmt);
				if (!isConnSupplied) {
					ResourceManager.close(conn);
				}

			}
		}

		@Override
		public void delete(short genreid) throws GenreException {
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
					logger.debug("Executing " + SQL_DELETE + " with PK: "
							+ genreid);
				}

				stmt = conn.prepareStatement(SQL_DELETE);
				stmt.setShort(1, genreid);
				int rows = stmt.executeUpdate();
				long t2 = System.currentTimeMillis();
				if (logger.isDebugEnabled()) {
					logger.debug(rows + " rows affected (" + (t2 - t1) + " ms)");
				}

			} catch (Exception _e) {
				logger.error("Exception: " + _e.getMessage(), _e);
				throw new GenreException("Exception: " + _e.getMessage(), _e);
			} finally {
				ResourceManager.close(stmt);
				if (!isConnSupplied) {
					ResourceManager.close(conn);
				}

			}
		}

		@Override
		public Genre findByPrimaryKey(short genreid) throws GenreException {
			List<Genre> ret = findByDynamicSelect(SQL_SELECT
					+ " WHERE genreid = ?",
					Arrays.asList(new Object[] { new Short(genreid) }));
			return ret.size() == 0 ? null : ret.get(0);
		}

		@Override
		public List<Genre> findAll() throws GenreException {
			return findByDynamicSelect(SQL_SELECT + " ORDER BY genreid", null);
		}

		@Override
		public List<Genre> findWhereGenreidEquals(short genreid)
				throws GenreException {
			return findByDynamicSelect(SQL_SELECT
					+ " WHERE genreid = ? ORDER BY genreid",
					Arrays.asList(new Object[] { new Short(genreid) }));
		}

		@Override
		public List<Genre> findWhereCategoryidEquals(short categoryid)
				throws GenreException {
			return findByDynamicSelect(SQL_SELECT
					+ " WHERE categoryid = ? ORDER BY categoryid",
					Arrays.asList(new Object[] { new Short(categoryid) }));
		}
		
		@Override
		public List<Genre> findWhereGenreEquals(String genre)
				throws GenreException {
			return findByDynamicSelect(SQL_SELECT
					+ " WHERE genre = ? ORDER BY genre",
					Arrays.asList(new Object[] { genre }));
		}

		@Override
		public List<Genre> findWherePointsEquals(int points)
				throws GenreException {
			return findByDynamicSelect(SQL_SELECT
					+ " WHERE points = ? ORDER BY points",
					Arrays.asList(new Object[] { new Integer(points) }));
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
		public List<Genre> findByDynamicSelect(String sql, List<Object> sqlParams)
				throws GenreException {
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
				throw new GenreException("Exception: " + _e.getMessage(), _e);
			} finally {
				ResourceManager.close(rs);
				ResourceManager.close(stmt);
				if (!isConnSupplied) {
					ResourceManager.close(conn);
				}
			}
		}

		@Override
		public List<Genre> findByDynamicWhere(String sql, List<Object> sqlParams)
				throws GenreException {
			final boolean isConnSupplied = (userConn != null);
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;		
			// get the user-specified connection or get a connection from the
			// ResourceManager
			try {
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
				for (int counter = 0; (!sqlParams.isEmpty())
						&& counter < sqlParams.size(); counter++) {
					stmt.setObject(counter + 1, sqlParams.get(counter));
				}
			
			rs = stmt.executeQuery();
			// fetch the results
			return fetchMultiResults(rs);
		} catch (Exception _e) {
			logger.error("Exception: " + _e.getMessage(), _e);
			throw new GenreException("Exception: " + _e.getMessage(), _e);
		} finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		}
		}
			/*// declare variables
			final boolean isConnSupplied = (userConn != null);
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			System.out.println(sql);
			System.out.println(sqlParams);
			
			sql = "categoryid IN (?)";
			sqlParams.clear();
			sqlParams.add(4);
			try {
				// get the user-specified connection or get a connection from the
				// ResourceManager
				conn = isConnSupplied ? userConn : ResourceManager.getConnection();

				// construct the SQL statement
			    final String SQL = SQL_SELECT + " WHERE " + sql;
				//final String SQL = "SELECT categoryid, type, category, genre, points FROM qpeka.category where category IN ('horror', 'thriller', 'small child', 'technical', 'love', 'Fiction', 'NonFiction', 'Educational', 'Love')";
				System.out.println(SQL);
				if (logger.isDebugEnabled()) {
					logger.debug("Executing " + SQL);
				}

				// prepare statement
				stmt = conn.prepareStatement(SQL);
				System.out.println(stmt);
				stmt.setMaxRows(maxRows);
				System.out.println(stmt.getMaxRows());

				// bind parameters
				for (int counter = 0; sqlParams != null
						&& counter < sqlParams.size(); counter++) {
					//System.out.println(sqlParams.get(counter));
					stmt.setObject(counter + 1, sqlParams.get(counter));
				}
				System.out.println("hello");
				System.out.println(stmt.getResultSet());
				rs = stmt.executeQuery();
				System.out.println("hii0");
				System.out.println(rs);
				// fetch the results
				return fetchMultiResults(rs);
			} catch (Exception _e) {
				logger.error("Exception: " + _e.getMessage(), _e);
				throw new CategoryException("Exception: " + _e.getMessage(), _e);
			} finally {
				ResourceManager.close(rs);
				ResourceManager.close(stmt);
				if (!isConnSupplied) {
					ResourceManager.close(conn);
				}
			}
	*/
			

		/**
		 * Fetches a single row from the result set
		 */
		protected Genre fetchSingleResult(ResultSet rs) throws SQLException {
			if (rs.next()) {
				Genre genre = new Genre();
				populateGenre(genre, rs);
				return genre;
			} else {
				return null;
			}

		}

		/**
		 * Fetches multiple rows from the result set
		 */
		protected List<Genre> fetchMultiResults(ResultSet rs)
				throws SQLException {
			List<Genre> resultList = new ArrayList<Genre>();
			while (rs.next()) {
				Genre genre = new Genre();
				populateGenre(genre, rs);
				resultList.add(genre);
			}
			return resultList;
		}

		/**
		 * Populates a DTO with data from a ResultSet
		 */
		protected void populateGenre(Genre genre, ResultSet rs)
				throws SQLException {
			genre.setGenreid(rs.getShort(COLUMN_GENREID));
			genre.setCategoryid(rs.getShort(COLUMN_CATEGORYID));
			genre.setGenre(rs.getString(COLUMN_GENRE));
			genre.setPoints(rs.getInt(COLUMN_POINTS));
			reset(genre);
		}

		/**
		 * Resets the modified attributes in the DTO
		 */
		protected void reset(Genre genre) {
			genre.setGenreidModified(false);
			genre.setCategoryidModified(false);
			genre.setGenreModified(false);
			genre.setPointsModified(false);
		}

		/**
		 * Get UserHandler object instance
		 * 
		 * @return instance of UserHandler
		 */
		public static GenreHandler getInstance() {
			return (instance == null ? (instance = new GenreHandler())
					: instance);
		}
	}
