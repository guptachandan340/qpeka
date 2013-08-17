package com.qpeka.managers;

import java.io.File;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;

import com.qpeka.db.Files;
import com.qpeka.db.exceptions.FileException;

import com.qpeka.db.handler.FilesHandler;

public class FilesManager {
	public static FilesManager instance = null;

	public FilesManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static FilesManager getInstance() {
		return (instance == null ? (instance = new FilesManager()) : instance);
	}

	public Files createFiles(long userId, String filetype, String filepath) {
		Files files = Files.getInstance();
		File file = new File(filepath); // Inbuilt File class usage
		MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
		files.setUserid(userId); // Set UserID
		if (file.exists()) {
			if (file.isFile()) {
				files.setFilename(file.getName().substring(0,
						(file.getName().lastIndexOf("."))));
				files.setFiletype(filetype);
				files.setExtension(setFileType(file.getName()));

				files.setFilepath(file.getParent()); // Set File Path
				files.setFilesize((int) (file.length() / (1000 * 1000)));
				files.setFilemime(mimeTypesMap.getContentType(file));
				files.setStatus(0);
				files.setTimestamp(System.currentTimeMillis() / 1000L);
			}
			try {
				FilesHandler.getInstance().insert(files);
			} catch (FileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("File is not available on specified path.");
		}
		return files;
	}

	private String setFileType(String fileName) {
		String fileType;
		if (fileName.lastIndexOf(".") == -1) {
			fileType = "Unknown";
		} else {
			fileType = fileName.substring(fileName.lastIndexOf(".") ,
					(fileName.length()));
		}
		return fileType;
	}

	/*
	 * public Files createFiles(long fileid, long userid, String filetype,
	 * String filename, String filepath, String filemime, int filesize, int
	 * status, long timestamp) { Files files = new Files();
	 * files.setFileid(fileid); files.setUserid(userid);
	 * files.setFiletype(filetype); files.setFilename(filename);
	 * files.setFilepath(filepath); files.setFilemime(filemime);
	 * files.setFilesize(filesize); files.setStatus(status);
	 * files.setTimestamp(timestamp); try {
	 * FilesHandler.getInstance().insert(files); } catch (FileException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }
	 * System.out.println(" file is created "); 
	 * return files; }
	 */

	
	/* Check delete function with userid for user */
	// Set file status to delete instead of deleting the files;
	public boolean deleteFiles(long fileId) {
		try {
			FilesHandler.getInstance().delete(fileId);
			return true;
		} catch (FileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	// updating file status through FileId
	public short updateFiles(short status, short fileid, String fileID) {
		List<Files> existingFiles = null;
		short counter = 0;
		if (fileID.equalsIgnoreCase(Files.FILEID)) {
			try {
				existingFiles = FilesHandler.getInstance()
						.findWhereFileidEquals(fileid);
			} catch (FileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (existingFiles != null) {
				for (Files file : existingFiles) {
					file.setStatus(status); // update status from enable to
											// disable or vice versa
					try {
						counter += FilesHandler.getInstance().update(fileid,
								file);
					} catch (FileException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return counter;
	}

	// @overloading
	// Check update function with userid for user
	public short updateFiles(Map<String, Object> updateFileMap) {
		List<Files> existingFiles = null;
		short counter = 0;
		if (updateFileMap.get(Files.FILEID) != null) {
			// List<Files> existingfile = null;
			try {
				existingFiles = FilesHandler.getInstance()
						.findWhereFileidEquals(
								Long.parseLong(updateFileMap.get(Files.FILEID)
										.toString()));
			} catch (FileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (existingFiles != null) {
				for (Files file : existingFiles) {
					/*
					 * if (updateFile.get(Files.USERID) != null) {
					 * file.setUserid
					 * (Long.parseLong(updateFile.get(Files.USERID)
					 * .toString())); }
					 */
					if (updateFileMap.get(Files.FILENAME) != null) {
						file.setFilename(updateFileMap.get(Files.FILENAME)
								.toString());
					}
					// Update filepath
					if (updateFileMap.get(Files.FILEPATH) != null) {
						file.setFilepath(updateFileMap.get(Files.FILEPATH)
								.toString());
					}

					if (updateFileMap.get(Files.FILETYPE) != null) {
						file.setFiletype(updateFileMap.get(Files.FILETYPE)
								.toString());
					}

					if (updateFileMap.get(Files.FILEMIME) != null) {
						file.setFilemime(updateFileMap.get(Files.FILEMIME)
								.toString());
					}

					if (updateFileMap.get(Files.FILESIZE) != null) {
						file.setFilesize(Integer.parseInt(updateFileMap.get(
								Files.FILESIZE).toString()));
					}

					if (updateFileMap.get(Files.STATUS) != null) {
						file.setStatus(Integer.parseInt(updateFileMap.get(
								Files.STATUS).toString()));
					}

					if (updateFileMap.get(Files.TIMESTAMP) != null) {
						file.setTimestamp(Integer.parseInt(updateFileMap.get(
								Files.TIMESTAMP).toString()));
					}

					try {
						counter += FilesHandler.getInstance().update(
								Long.parseLong(updateFileMap.get(Files.FILEID)
										.toString()), file);
					} catch (FileException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}
		return counter;
	}

	/* Check update function with userid for user */
	public List<Files> readFiles(String fileIdentifier,
			String fileIdentifierSting) {
		List<Files> existingFiles = null;
		// READING FILES THROUGH FILETYPE
		if (fileIdentifierSting.equalsIgnoreCase(Files.FILETYPE)) {
			try {
				existingFiles = FilesHandler.getInstance()
						.findWhereFiletypeEquals(fileIdentifier);
			} catch (FileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// READING FILES THROUGH FILENAME
		else if (fileIdentifierSting.equalsIgnoreCase(Files.FILENAME)) {
			try {
				existingFiles = FilesHandler.getInstance()
						.findWhereFiletypeEquals(fileIdentifier);
			} catch (FileException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// FILE CANNOT BE READ THROUGH OTHER ATTRIBUTES.
		else if (fileIdentifierSting.equalsIgnoreCase(Files.FILEPATH)
				|| fileIdentifierSting.equalsIgnoreCase(Files.FILEMIME)) {
			return existingFiles;
		}
		return existingFiles;
	}

	/* reading all fileds of file through userId and filetype */
	
	public List<Files> readFiles(long userId, String filetype,String filesAttribute) {
		List<Files> existingFiles = null;
		List<Object> readFilesobj = new ArrayList<Object>();
		readFilesobj.add(userId);
		readFilesobj.add(filetype);
		if (filesAttribute.equalsIgnoreCase(Files.FILETYPE)) {
			try {
				existingFiles = FilesHandler.getInstance().findByDynamicWhere(
						"userid = ? AND filetype IN (?)", readFilesobj);

			} catch (FileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return existingFiles;
	}

	/*
	public Map<String, Map.Entry<String, String>> readFiles(long userId, String filetype,
			String filesAttribute) {
		List<Files> existingFiles = null;
		List<Object> readFilesobj = new ArrayList<Object>();
		readFilesobj.add(userId);
		readFilesobj.add(filetype);
		if (filesAttribute.equalsIgnoreCase(Files.FILETYPE)) {
			try {
				existingFiles = FilesHandler.getInstance().findByDynamicWhere(
						"userid = ? AND filetype IN (?)", readFilesobj);

			} catch (FileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return retrieveFiles(existingFiles);
	}

	// Function for retrieving selected fields of file object 
	
	public Map<String, Map.Entry<String, String>> retrieveFiles(
			List<Files> existingFiles) {
		Map<String, Map.Entry<String, String>> outerMap = new HashMap<String, Map.Entry<String, String>>();
		Map<String, String> innerMap = new HashMap<String, String>();
		for (Files files : existingFiles) {
			innerMap.put(files.getFilepath(),files.getFilemime());
		}
		Iterator iterator = innerMap.entrySet().iterator();
		while (iterator.hasNext()) {
			for (Files files : existingFiles) {
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				outerMap.put(files.getFilename(), mapEntry);
			}
		}
		return outerMap;
	}

	/**
	 * @param args
	 */

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//FilesManager.getInstance().deleteFiles(6);
		FilesManager.getInstance().createFiles((long)42,"profilepic","/home/ankita/Downloads/Ankit final resume.docx");
	//	System.out.println(FilesManager.getInstance().readFiles("deb", "filetype"));
	//	System.out.println(FilesManager.getInstance().readFiles(6, "mp3", Files.FILETYPE));
	//  Map<String, Object> updateMap = new HashMap<String, Object>();
	//	updateMap.put("fileid", 3);
	//	updateMap.put("filetype", "sql");
	//	updateMap.put("filename", "qpeka");
	//	updateMap.put("filepath", "/home/ankita/desktop");
		//updateMap.put("filesize", 3333);
		
		//System.out.println(filesManager.updateFiles(updateMap)); // Done
	//	System.out.println(filesManager.updateFiles((short)1,(short)3,"fileid"));
	}
	
}
