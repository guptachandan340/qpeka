
package com.qpeka.managers;

import java.io.File;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.activation.MimetypesFileTypeMap;

import com.qpeka.db.Files;
import com.qpeka.db.exceptions.FileException;

import com.qpeka.db.handler.FilesHandler;

public class FilesManager {
	public static FilesManager instance = null;
	Files files = new Files();
	List<Files> existingFiles = null;

	public FilesManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FilesManager getIntance() {
		return (instance == null ? (instance = new FilesManager()) : instance);
	}
	
	public Files createFiles(long fileId,long userId, String filepath) {
		File file= new File(filepath);										// Inbuilt File class usage
		MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();	
	    files.setFileid(fileId);
		files.setUserid(userId);											// Set UserID
		if (file.exists()) {
			if (file.isFile()) {
				files.setFiletype(setFileType(file.getName()));            // Set FileType and FileName;
				files.setFilepath(file.getParent());			           // Set File Path
				files.setFilesize((int) (file.length() / (1000 * 1000)));  // Convert byte to MB and Set file Size
				files.setFilemime(mimeTypesMap.getContentType(file));      // set File mime Type
				files.setStatus(0);										   // Set File Status
				files.setTimestamp(System.currentTimeMillis() / 1000L);     // Set File TimeStamp
			}
			try {
				FilesHandler.getInstance().insert(files);
			} catch (FileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("invalid path");
		}
		return files;
	}

	private String setFileType(String fileName) {
		int ind = fileName.lastIndexOf(".");
		String fileType;
		if (ind == -1) {
			fileType = "Unknown";
		} else {
			fileType = fileName.substring(ind + 1, (fileName.length()));
			// Set file Name;
			fileName= fileName.substring(0,ind);
			files.setFilename(fileName);
		}
		return fileType;
	}
	

	public Files createFiles(long fileid, long userid, String filetype,
			String filename, String filepath, String filemime, int filesize,
			int status, long timestamp) {

		files.setFileid(fileid);
		files.setUserid(userid);
		files.setFiletype(filetype);
		files.setFilename(filename);
		files.setFilepath(filepath);
		files.setFilemime(filemime);
		files.setFilesize(filesize);
		files.setStatus(status);
		files.setTimestamp(timestamp);
		try {
			FilesHandler.getInstance().insert(files);
		} catch (FileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" file is created ");
		return files;
	}

	/* Check delete function with userid for user */
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
		short counter = 0;
		if (fileID.equalsIgnoreCase("fileid")) {
			try {
				existingFiles = FilesHandler.getInstance()
						.findWhereFileidEquals(fileid);
			} catch (FileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (existingFiles != null) {
				for (Files file : existingFiles) {
					file.setStatus(status); // set status to eneble or disable
											// the file.
					try {
						counter += FilesHandler.getInstance().update(fileid, file);
					} catch (FileException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return counter;
	}
	//@overloading
	//Check update function with userid for user 
	public short updateFiles(Map<String, Object> updateFile) {
		short counter = 0;
		if (updateFile.get(Files.FILEID) != null) {
			//List<Files> existingfile = null;
			try {
				existingFiles = FilesHandler.getInstance()
						.findWhereFileidEquals(Long.parseLong(updateFile.get(Files.FILEID).toString()));
			} catch (FileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (existingFiles != null) {
				for (Files file : existingFiles) {
				/*	if (updateFile.get(Files.USERID) != null) {
						file.setUserid(Long.parseLong(updateFile.get(Files.USERID)
								.toString()));
					}*/
					if (updateFile.get(Files.FILENAME) != null) {
						file.setFilename(updateFile.get(Files.FILENAME)
								.toString());
					}
					// Update filepath
					if (updateFile.get(Files.FILEPATH) != null) {
						file.setFilepath(updateFile.get(Files.FILEPATH)
								.toString());
					}

					if (updateFile.get(Files.FILETYPE) != null) {
						file.setFiletype(updateFile.get(Files.FILETYPE)
								.toString());
					}

					if (updateFile.get(Files.FILEMIME) != null) {
						file.setFilemime(updateFile.get(Files.FILEMIME)
								.toString());
					}

					if (updateFile.get(Files.FILESIZE) != null) {
						file.setFilesize(Integer.parseInt(updateFile.get(
								Files.FILESIZE).toString()));
					}

					if (updateFile.get(Files.STATUS) != null) {
						file.setStatus(Integer.parseInt(updateFile.get(
								Files.STATUS).toString()));
					}

					if (updateFile.get(Files.TIMESTAMP) != null) {
						file.setTimestamp(Integer.parseInt(updateFile.get(
								Files.TIMESTAMP).toString()));
					}
					
					try {
						counter += FilesHandler.getInstance().update(Long.parseLong(updateFile.get(Files.FILEID).toString()), file);
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
	public List<Files> readFiles(String filefields, String fileAttribute) {
		//READING FILES THROUGH FILETYPE
		if (fileAttribute.equalsIgnoreCase(Files.FILETYPE)) {
			try {
				existingFiles = FilesHandler.getInstance().findWhereFiletypeEquals(
						filefields);
			} catch (FileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		// READING FILES THROUGH FILENAME
		else if (fileAttribute.equalsIgnoreCase(Files.FILENAME)) {
			try {
				existingFiles = FilesHandler.getInstance().findWhereFiletypeEquals(
						filefields);
			} catch (FileException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//FILE CANNOT BE READ THROUGH OTHER ATTRIBUTES.
		else {
			return existingFiles;
		}
		return existingFiles;
	}

	public List<Files> readFiles(String filetype, String filesAttribute,
			long userId) {
		List<Object> readFilesobj = new ArrayList<Object>();
		readFilesobj.add(userId);
		readFilesobj.add(filetype);
		if (filesAttribute.equalsIgnoreCase("filetype")) {
			try {
				existingFiles = FilesHandler.getInstance().findByDynamicWhere("userid = ? AND filetype IN (?)", readFilesobj);
				
			} catch (FileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return existingFiles;
		
	}


	/**
	 * @param args
	 */
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FilesManager filesManager = new FilesManager();
		filesManager.deleteFiles(6);
		filesManager.createFiles((long)6,(long)40,"/home/ankita/Downloads/google-chrome-stable_current_amd64.deb");
	    
		System.out.println(filesManager.readFiles("deb", "filetype"));
	    System.out.println(filesManager.readFiles("bin", "filetype", 20));
	    Map<String, Object> updateMap = new HashMap<String, Object>();
		updateMap.put("fileid", 3);
		updateMap.put("filetype", "sql");
	//	updateMap.put("filename", "qpeka");
	//	updateMap.put("filepath", "/home/ankita/desktop");
		//updateMap.put("filesize", 3333);
		
	//	System.out.println(filesManager.updateFiles(updateMap)); // Done
	//	System.out.println(filesManager.updateFiles((short)1,(short)3,"fileid"));
	}
	*/
}
