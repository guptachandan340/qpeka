package com.qpeka.servlets;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import com.qpeka.book.converter.FileConverterUtils;
import com.qpeka.book.converter.FileEncryptionUtils;
import com.qpeka.db.book.store.PublisherHandler;
import com.qpeka.db.book.store.WorkEncryptionHandler;
import com.qpeka.db.book.store.tuples.Constants.CATEGORY;
import com.qpeka.db.book.store.tuples.Constants.LANGUAGES;
import com.qpeka.db.book.store.tuples.Constants.TYPE;
import com.qpeka.db.book.store.tuples.Publisher;
import com.qpeka.db.book.store.tuples.Work;
import com.qpeka.epub.provider.EpubProcessorNew;
import com.qpeka.managers.WorkContentManager;
import com.qpeka.utils.SystemConfigHandler;


public class WorkUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean create = false;
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		//file upload part 
	    String filePath = "";
	    String title = "";
	    String bookDesc =  "";
	    CATEGORY bookCategory = CATEGORY.CHILDREN;
	    TYPE type = TYPE.BOOK;
	    String fileName = "";
	    String coverPage = "";
	    String bookContentFile="";
	    LANGUAGES language = LANGUAGES.ENGLISH;
	    boolean isPublished = false;
	    String authorId = "";
	    
	    int bookEdition = 0;
	    String publisherId = "";
	    String publisherName = "";
	    int pday = 0;
	    int pmonth = 0;
	    int pyear = 0;
	    long dateofPub = -1l;
	    String isbn = "";
	    
	    String searchKey = "";
	    
        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
 
            try {
                List items = upload.parseRequest(request);
                Iterator iterator = items.iterator();
                while (iterator.hasNext()) {
                    FileItem item = (FileItem) iterator.next();
                    
                    if(item != null)
                    {
	                    if (!item.isFormField()) {
	                        fileName = item.getName();
	                        String fieldName = item.getFieldName();
	                        String root = "";
	                        if(fieldName.equalsIgnoreCase("file"))
	                        {
	                        	root = SystemConfigHandler.getInstance().getSrcBookFolder();
	                        	bookContentFile = fileName;
	                        	
	                        }
	                        else if(fieldName.equalsIgnoreCase("cover"))
	                        {
	                        	root = SystemConfigHandler.getInstance().getBookCoverPageFolder();
	                        	coverPage = fileName;
	                        	
	                        }
	                       
	                        File path = new File(root);
	                        if (!path.exists()) {
	                            boolean status = path.mkdirs();
	                        }
	                        filePath = path + "/" + fileName;
	                        
	                        File uploadedFile = new File(path + "/" + fileName);	                 
	                        System.out.println(uploadedFile.getAbsolutePath());
	                        item.write(uploadedFile);
	                    }
	                    else
	                    {
	                    	
	                    	String name = item.getFieldName().trim();
	                    	String value = item.getString().trim();
	                    	if(name.equalsIgnoreCase(Work.TITLE))
	                    		title = value;
	                    	if(name.equalsIgnoreCase(Work.CATEGORY))
	                    	try
	                    	{	
	                    		bookCategory = CATEGORY.valueOf(value);
	                    	}
	                    	catch (Exception e) {
								// TODO: handle exception
							}
	                    	
	                    	if(name.equalsIgnoreCase(Work.TYPE))
	                    	try
	                    	{
	                    		type = TYPE.valueOf(value);
	                    	}
	                    	
	                    	catch (Exception e) {
								// TODO: handle exception
							}
	                    	
	                    	if(name.equalsIgnoreCase(Work.LANGUAGE))
		                    	try
		                    	{

		                    		language = LANGUAGES.valueOf(value);
		                    	}
		                    	
		                    	catch (Exception e) {
									// TODO: handle exception
								}
	                    	if(name.equalsIgnoreCase(Work.DESCRIPTION))
	                    		bookDesc = value;
	                    	if(name.equalsIgnoreCase(Work.AUTHORID))
	                    		authorId = value;
	                    	if(name.equalsIgnoreCase(Work.ISPUBLISHED))
	                    		isPublished = Boolean.parseBoolean(value);	  
	                    	if(name.equalsIgnoreCase(Work.SEARCHKEY))
	                    		searchKey = value;	
	                    	if(name.equalsIgnoreCase(Work.EDITION))//
	                    		bookEdition = Integer.parseInt(value);
	                    	if(name.equalsIgnoreCase(Work.PUBLISHERID))
	                    		publisherId = value;
	                    	if(name.equalsIgnoreCase("publisherName"))
	                    		publisherName = value;
	                    	if(name.equalsIgnoreCase("pday"))
	                    		pday = Integer.parseInt(value);
	                    	if(name.equalsIgnoreCase("pmonth"))
	                    		pmonth = Integer.parseInt(value);
	                    	if(name.equalsIgnoreCase("pyear"))
	                    		pyear = Integer.parseInt(value);
	                    	if(name.equalsIgnoreCase(Work.ISBN))
	                    		isbn = value;
	                    	
	                    }
                    }
                }
                
                
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try
        {
        	String pId = "";
        	if(publisherName != null && publisherName.length() > 0)
        	{
        		pId = PublisherHandler.getInstance().addPublisher(new Publisher(publisherName, ""));     	
        	}
        	else
        	{
        		pId = publisherId;
        	}
        	
        	String coverPageFile = SystemConfigHandler.getInstance().getBookCoverPageFolder()+"/"+title+".jpg";
        	JSONObject metadata =  new JSONObject();
        	metadata.put(Work.SEARCHKEY, searchKey);
        	
        	String _id = WorkContentManager.getInstance().addWork("", title, authorId, coverPageFile, bookCategory, type, 0, metadata , bookDesc,
        			language, isPublished, new Date(pyear, pmonth, pday).getTime(), bookEdition, isbn, pId);
        	
//        	int numPages = 0;			
//        	if(fileName.endsWith(".doc"))
//        		numPages = BookConverterUtils.convertDOCToQPEKA(SystemConfigHandler.getInstance().getBookContentFolder(),SystemConfigHandler.getInstance().getSrcBookFolder()+fileName, title);
//			else if(fileName.endsWith(".docx"))
//				BookConverterUtils.convertFromDOCXToQPEKA(SystemConfigHandler.getInstance().getBookContentFolder(), SystemConfigHandler.getInstance().getSrcBookFolder()+fileName, title);
        	//Rename files accordingly here
        	
        	File cvr = new File(SystemConfigHandler.getInstance().getBookCoverPageFolder()+ "/"+coverPage);
        	if(cvr != null && cvr.exists())
        	{
        		File idedFile = new File(SystemConfigHandler.getInstance().getBookCoverPageFolder()+ "/"+_id+".jpg");
        		FileUtils.copyFile(cvr, idedFile);	
        		cvr.delete();
        	}
        	
        	cvr = new File(SystemConfigHandler.getInstance().getSrcBookFolder()+ "/"+bookContentFile);
        	System.out.println("[MANOJ] File = " + cvr.getName());
        	if(cvr != null && cvr.exists())
        	{
        		if(cvr.getName().endsWith("epub"))
        		{
	        		EpubProcessorNew.processEpub(SystemConfigHandler.getInstance().getSrcBookFolder()+ "/"+bookContentFile,
	        				SystemConfigHandler.getInstance().getSrcBookFolder()+ "/"+_id+".epub");
        		}
        		else if(cvr.getName().endsWith("doc")) // handle DocCase
        		{
        			String src = FileConverterUtils.convertDocToEpub(cvr,title,language.toString());
        			EpubProcessorNew.processEpub(src, SystemConfigHandler.getInstance().getSrcBookFolder()+ "/"+_id+".epub");
        		}
        		else if(cvr.getName().endsWith("docx")) // handle DocCase
        		{
        			String src = FileConverterUtils.convertDocxToEpub(cvr,title,language.toString());
        			EpubProcessorNew.processEpub(src, SystemConfigHandler.getInstance().getSrcBookFolder()+ "/"+_id+".epub");
        		}
        		
        		//Encrypt and save the file
        		WorkEncryptionHandler.getInstance().addKey(_id, FileEncryptionUtils.generateKey(_id));
        		
        		
        		cvr.delete();
        	}
        	
        	response.setContentType("application/json");
        	JSONObject resp = new JSONObject();
        	resp.put("result", "success");
        	resp.put("_id", _id);
        	
        	Writer wr = response.getWriter();
        	wr.write(resp.toString()); 
        	wr.flush();
        	
        	return;
        	
        	
        }
        catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static long getDate(int month , int year , int date)
	{
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, month-1);  
		c.set(Calendar.DAY_OF_MONTH, date);  
		c.set(Calendar.YEAR, year);
		Date dt = c.getTime();
		System.out.println(dt.toString());
		return dt.getTime();
	}
	
	public static void main(String[] args) {
		
		long l = getDate(12,1987,23);
		
		System.out.println(new Date(l));
	}
}
