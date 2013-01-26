package com.qpeka.servlets;

import java.io.File;
import java.io.IOException;
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

import com.qpeka.db.book.store.UserAuthHandler;
import com.qpeka.db.book.store.UserHandler;
import com.qpeka.db.book.store.tuples.Constants.CATEGORY;
import com.qpeka.db.book.store.tuples.Constants.GENDER;
import com.qpeka.db.book.store.tuples.Constants.LANGUAGES;
import com.qpeka.db.book.store.tuples.Constants.USERLEVEL;
import com.qpeka.db.book.store.tuples.Constants.USERTYPE;
import com.qpeka.db.book.store.tuples.User;
import com.qpeka.db.book.store.tuples.UserAuth;
import com.qpeka.managers.UserManager;
import com.qpeka.utils.SystemConfigHandler;

/**
 * Servlet implementation class UserRegistrationServlet
 */
public class UserRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestype = request.getParameter("rType"); // password + user  availability 
		if(requestype.equalsIgnoreCase("authAvail"))
		{
			String userName = request.getParameter("uname");
			String pwd = request.getParameter("pwd");
			
			if(UserAuthHandler.getInstance().getUser(userName) == null)
			{
				response.getWriter().write("{status:ok}");
				return;
			}
			
			if(!UserAuthHandler.getInstance().getUser(userName,pwd))
			{
				response.getWriter().write("{status:ok}");
				return;
			}
			
			response.getWriter().write("{status:none}");
		}
		else if(requestype.equalsIgnoreCase("logout"))
		{
			request.getSession().invalidate();
			request.getRequestDispatcher("/landing.jsp").forward(request, response);
			
		}
		else if(requestype.equalsIgnoreCase("login"))
		{
			if(true)
			{
				User u = new User();//add here 
				UserAuth ua = new UserAuth("", "");
				request.getSession().setAttribute("uid", "werwer");
				request.getSession().setAttribute("uname", "23652");
				
				request.getRequestDispatcher("/userHome.jsp").forward(request, response);
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * (String firstName, String middleName, String lastName, GENDER gender, String email, String city, String state, String addressLine1,
			String addressLine2, String addressLine3, String pincode , USERTYPE userType, String[] preferences, int age , Date dob, String nationality, String phone,
			String desc, LANGUAGES rLang, LANGUAGES wLang, USERLEVEL level, String userName, String penName, String imageFile)
		 */
		
		String firstName = "";//request.getParameter("firstName") == null ? "":request.getParameter("firstName");
		String middleName = "";//request.getParameter("middleName") == null ? "":request.getParameter("middleName");
		String lastName = "";//request.getParameter("lastName") == null ? "":request.getParameter("lastName");
		GENDER gender = GENDER.MALE;//GENDER.valueOf(request.getParameter("gender") == null ? "MALE":request.getParameter("gender"));
		String email = "";//request.getParameter("email") == null ? "":request.getParameter("email");
		String username = "";//request.getParameter("username") == null ? "":request.getParameter("username"); 
		String password = "";//request.getParameter("password") == null ? "":request.getParameter("password");
		String day = "";//request.getParameter("day") == null ? "":request.getParameter("day");
		String month = "";//request.getParameter("month") == null ? "":request.getParameter("month");
		String year = "";//request.getParameter("year") == null ? "":request.getParameter("year");
		String rLang = "";//request.getParameter("rLang") == null ? "":request.getParameter("rLang");
		String wLang = "";//request.getParameter("wLang") == null ? "":request.getParameter("wLang");
		String phone = "";//request.getParameter("phone") == null ? "":request.getParameter("phone");
		String desc = "";//request.getParameter("desc") == null ? "":request.getParameter("desc");
		String penName = "";//request.getParameter("penName") == null ? "":request.getParameter("penName");
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String filePath = "";
		String fileName = "";
		
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
	 
	                        String root = "/var/tmp";
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
	                    	if(name.equalsIgnoreCase("firstName"))
	                    		firstName = value;
	                    	if(name.equalsIgnoreCase("username"))
	                    		username = value;
	                    	if(name.equalsIgnoreCase("lastName"))
	                    		lastName = value;
	                    	if(name.equalsIgnoreCase("middleName"))
	                    		middleName = value;
	                    	if(name.equalsIgnoreCase("desc"))
	                    		desc = value;
	                    	if(name.equalsIgnoreCase("gender"))
	                    		gender = GENDER.valueOf(value);
	                    	if(name.equalsIgnoreCase("rLang"))//
	                    		rLang = value;
	                    	if(name.equalsIgnoreCase("wLang"))//
	                    		wLang = value;
	                    	if(name.equalsIgnoreCase("day"))//
	                    		day = value;
	                    	if(name.equalsIgnoreCase("month"))//
	                    		month = value;
	                    	if(name.equalsIgnoreCase("year"))//
	                    		year = value;
	                    	if(name.equalsIgnoreCase("penName"))
	                    		penName = value;
	                    	if(name.equalsIgnoreCase("phone"))
	                    		phone = value;
	                    	if(name.equalsIgnoreCase("email"))
	                    		email = value;
	                    	if(name.equalsIgnoreCase("password"))
	                    		password = value;
	                    	
	                    }
                    }
                }
                
                
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
		
		//UserManager.getInstance().addUser(firstName, middleName, lastName, gender, email, city, state, addressLine1, addressLine2, addressLine3, pincode, userType, preferences, age, dob, nationality, phone, desc, rLang, wLang, level, userName, penName, imageFile)
		
		Date dt = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		
		if(!UserAuthHandler.getInstance().getUser(username, password))
		{
			UserAuthHandler.getInstance().addUserAuth(new UserAuth(username, password));
			String uid = UserManager.getInstance().addUser(firstName, middleName, lastName, gender, email, "", "", "", "", "", "", USERTYPE.READER, new String[]{""}, 12, dt, "", phone, desc, LANGUAGES.valueOf(rLang), LANGUAGES.valueOf(wLang), USERLEVEL.FREE, username, penName, filePath);
			
			request.getSession().setAttribute("uid", uid);
			request.getSession().setAttribute("uname", username);
			
			request.getRequestDispatcher("/userHome.jsp?firstTime=true").forward(request, response);
		}
		else
		{
			//send user already exists
		}
		
		
	}
	}
}
