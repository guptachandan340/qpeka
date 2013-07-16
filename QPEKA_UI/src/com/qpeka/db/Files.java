package com.qpeka.db;

import java.io.Serializable;

public class Files implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Files information
	public static final String FILEID = "fileid";
	public static final String USERID = "userid";
	public static final String FILETYPE = "filetype";
	public static final String FILENAME = "filename";
	public static final String FILEPATH = "filepath";
	public static final String FILEMIME = "filemime";
	public static final String FILESIZE = "filesize";
	public static final String STATUS = "status";
	public static final String TIMESTAMP = "timestamp";

	/**
	 * These attributes maps to the columns of the files table.
	 */
	private long fileid;
	private long userid;
	private String filetype;
	private String filename;
	private String filepath;
	private String filemime;
	private int filesize;
	private int status;
	private long timestamp;

	/**
	 * These attributes represents whether the above attributes has been
	 * modified since being read from the database.
	 */
	protected boolean fileidModified = false;
	protected boolean useridModified = false;
	protected boolean filetypeModified = false;
	protected boolean filenameModified = false;
	protected boolean filepathModified = false;
	protected boolean filemimeModified = false;
	protected boolean filesizeModified = false;
	protected boolean statusModified = false;
	protected boolean timestampModified = false;

	/*
	 * Constructors
	 */
	public Files() {
		super();
	}

	public Files(int fileid, int userid, String filetype, String filename,
			String filepath, String filemime, int filesize, int status,
			int timestamp) {
		super();
		this.fileid = fileid;
		this.userid = userid;
		this.filetype = filetype;
		this.filename = filename;
		this.filepath = filepath;
		this.filemime = filemime;
		this.filesize = filesize;
		this.status = status;
		this.timestamp = timestamp;
	}

	public Files(int userid, String filetype, String filename, String filepath,
			String filemime, int filesize, int status, int timestamp) {
		super();
		this.userid = userid;
		this.filetype = filetype;
		this.filename = filename;
		this.filepath = filepath;
		this.filemime = filemime;
		this.filesize = filesize;
		this.status = status;
		this.timestamp = timestamp;
	}

	/*
	 * Getters and setters for attributes
	 */
	public long getFileid() {
		return fileid;
	}

	public void setFileid(long fileid) {
		this.fileid = fileid;
		this.fileidModified = true;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
		this.useridModified = true;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
		this.filetypeModified = true;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
		this.filenameModified = true;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
		this.filepathModified = true;
	}

	public String getFilemime() {
		return filemime;
	}

	public void setFilemime(String filemime) {
		this.filemime = filemime;
		this.filemimeModified = true;
	}

	public int getFilesize() {
		return filesize;
	}

	public void setFilesize(int filesize) {
		this.filesize = filesize;
		this.filesizeModified = true;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		this.statusModified = true;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
		this.timestampModified = true;
	}

	/*
	 * Getters and setters for attribute modified status
	 */
	public boolean isFileidModified() {
		return fileidModified;
	}

	public void setFileidModified(boolean fileidModified) {
		this.fileidModified = fileidModified;
	}

	public boolean isUseridModified() {
		return useridModified;
	}

	public void setUseridModified(boolean useridModified) {
		this.useridModified = useridModified;
	}

	public boolean isFiletypeModified() {
		return filetypeModified;
	}

	public void setFiletypeModified(boolean filetypeModified) {
		this.filetypeModified = filetypeModified;
	}

	public boolean isFilenameModified() {
		return filenameModified;
	}

	public void setFilenameModified(boolean filenameModified) {
		this.filenameModified = filenameModified;
	}

	public boolean isFilepathModified() {
		return filepathModified;
	}

	public void setFilepathModified(boolean filepathModified) {
		this.filepathModified = filepathModified;
	}

	public boolean isFilemimeModified() {
		return filemimeModified;
	}

	public void setFilemimeModified(boolean filemimeModified) {
		this.filemimeModified = filemimeModified;
	}

	public boolean isFilesizeModified() {
		return filesizeModified;
	}

	public void setFilesizeModified(boolean filesizeModified) {
		this.filesizeModified = filesizeModified;
	}

	public boolean isStatusModified() {
		return statusModified;
	}

	public void setStatusModified(boolean statusModified) {
		this.statusModified = statusModified;
	}

	public boolean isTimestampModified() {
		return timestampModified;
	}

	public void setTimestampModified(boolean timestampModified) {
		this.timestampModified = timestampModified;
	}

	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other) {
		if (_other == null) {
			return false;
		}

		if (_other == this) {
			return true;
		}

		if (!(_other instanceof Files)) {
			return false;
		}

		final Files _cast = (Files) _other;
		if (fileid != _cast.fileid) {
			return false;
		}

		if (fileidModified != _cast.fileidModified) {
			return false;
		}

		if (userid != _cast.userid) {
			return false;
		}

		if (useridModified != _cast.useridModified) {
			return false;
		}

		if (filetype == null ? _cast.filetype != filetype : !filetype
				.equals(_cast.filetype)) {
			return false;
		}

		if (filetypeModified != _cast.filetypeModified) {
			return false;
		}

		if (filename == null ? _cast.filename != filename : !filename
				.equals(_cast.filename)) {
			return false;
		}

		if (filenameModified != _cast.filenameModified) {
			return false;
		}

		if (filepath == null ? _cast.filepath != filepath : !filepath
				.equals(_cast.filepath)) {
			return false;
		}

		if (filepathModified != _cast.filepathModified) {
			return false;
		}

		if (filemime == null ? _cast.filemime != filemime : !filemime
				.equals(_cast.filemime)) {
			return false;
		}

		if (filemimeModified != _cast.filemimeModified) {
			return false;
		}

		if (filesize != _cast.filesize) {
			return false;
		}

		if (filesizeModified != _cast.filesizeModified) {
			return false;
		}

		if (status != _cast.status) {
			return false;
		}

		if (statusModified != _cast.statusModified) {
			return false;
		}

		if (timestamp != _cast.timestamp) {
			return false;
		}

		if (timestampModified != _cast.timestampModified) {
			return false;
		}

		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode() {
		int _hashCode = 0;
		_hashCode = 29 * _hashCode + (int)fileid;
		_hashCode = 29 * _hashCode + (fileidModified ? 1 : 0);
		_hashCode = 29 * _hashCode + (int)userid;
		_hashCode = 29 * _hashCode + (useridModified ? 1 : 0);
		if (filetype != null) {
			_hashCode = 29 * _hashCode + filetype.hashCode();
		}

		_hashCode = 29 * _hashCode + (filetypeModified ? 1 : 0);
		if (filename != null) {
			_hashCode = 29 * _hashCode + filename.hashCode();
		}

		_hashCode = 29 * _hashCode + (filenameModified ? 1 : 0);
		if (filepath != null) {
			_hashCode = 29 * _hashCode + filepath.hashCode();
		}

		_hashCode = 29 * _hashCode + (filepathModified ? 1 : 0);
		if (filemime != null) {
			_hashCode = 29 * _hashCode + filemime.hashCode();
		}

		_hashCode = 29 * _hashCode + (filemimeModified ? 1 : 0);
		_hashCode = 29 * _hashCode + filesize; 
		_hashCode = 29 * _hashCode + (filesizeModified ? 1 : 0);
		_hashCode = 29 * _hashCode + status;
		_hashCode = 29 * _hashCode + (statusModified ? 1 : 0);
		_hashCode = 29 * _hashCode + (int)timestamp;
		_hashCode = 29 * _hashCode + (timestampModified ? 1 : 0);
		return _hashCode;
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("Files: ");
		ret.append(FILEID + "=" + fileid);
		ret.append(", " + USERID + "=" + userid);
		ret.append(", " + FILETYPE + "=" + filetype);
		ret.append(", " + FILENAME + "=" + filename);
		ret.append(", " + FILEPATH + "=" + filepath);
		ret.append(", " + FILEMIME + "=" + filemime);
		ret.append(", " + FILESIZE + "=" + filesize);
		ret.append(", " + STATUS + "=" + status);
		ret.append(", " + TIMESTAMP + "=" + timestamp);

		return ret.toString();
	}

}
