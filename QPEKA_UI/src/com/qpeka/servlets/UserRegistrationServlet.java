package com.qpeka.servlets;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.qpeka.db.book.store.UserAuthHandler;
import com.qpeka.db.book.store.UserHandler;
import com.qpeka.db.book.store.tuples.Constants.CATEGORY;
import com.qpeka.db.book.store.tuples.Constants.GENDER;
import com.qpeka.db.book.store.tuples.Constants.LANGUAGES;
import com.qpeka.db.book.store.tuples.Constants.USERLEVEL;
import com.qpeka.db.book.store.tuples.Constants.USERTYPE;
import com.qpeka.db.book.store.tuples.Address;
import com.qpeka.db.book.store.tuples.Name;
import com.qpeka.db.book.store.tuples.User;
import com.qpeka.db.book.store.tuples.UserAuth;
import com.qpeka.managers.UserManager;

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
			
		 	Writer wr = response.getWriter();
        	
			if(UserAuthHandler.getInstance().getUser(userName) == null)
			{
				wr.write("{\"status\":\"ok\"}");
				wr.flush();
				return;
			}
			
			wr.write("{\"status\":\"none\"}");
			wr.flush();
        	return;
		}
		else if(requestype.equalsIgnoreCase("getUser"))
		{
			String user = UserManager.getInstance().getUser(request.getParameter("uid"));
			
			Writer wr = response.getWriter();
        	wr.write(user);
			wr.flush();
        	return;
		}
		else if(requestype.equalsIgnoreCase("getUsersBysearchKey"))
		{
			JSONArray jsa = new JSONArray();
			for(User u : UserManager.getInstance().getUsersBySearchKey(request.getParameter("key")))
			{
				jsa.put(u.toDBObject(false));
			}
			Writer wr = response.getWriter();
        	wr.write(jsa.toString());
			wr.flush();
        	return;
		}
		else if(requestype.equalsIgnoreCase("logout"))
		{
			request.getSession().invalidate();
			request.getRequestDispatcher("/landing.jsp").forward(request, response);
			
		}
		else if(requestype.equalsIgnoreCase("login"))
		{
			Writer wr = response.getWriter();
        	
			if(UserAuthHandler.getInstance().getUser(request.getParameter("uid"), request.getParameter("password")))
			{
				
				String id = UserHandler.getInstance().getUserByUserName(request.getParameter("uid")).get_id();
				//request.getRequestDispatcher("/myProfile.jsp?uid="+id).forward(request, response);
				response.sendRedirect("http://localhost:8080/QpekaWeb/myProfile.jsp?uid="+id);
				return;
//				JSONObject jo = new JSONObject();
//				try {
//					jo.put("status", "authenticated");
//					jo.put("uid", id);
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				wr.write(jo.toString());
//				wr.flush();
//				return;
			}
			else
			{
				response.sendRedirect("http://localhost:8080/QpekaWeb/home.jsp?error=true");
				
				//request.getRequestDispatcher("/home.jsp?error=true").forward(request, response);
//				wr.write("{\"status\":\"error\"}");
//				wr.flush();
	        	return;
			}
//			if(true)
//			{
//				User u = new User();//add here 
//				UserAuth ua = new UserAuth("", "");
//				request.getSession().setAttribute("uid", "werwer");
//				request.getSession().setAttribute("uname", "23652");
//				
//				request.getRequestDispatcher("/userHome.jsp").forward(request, response);
//			}
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
		String city = "";
		String state = "";
		String addressLine1 = "";
		String addressLine2 = "";
		String addressLine3 = "";
		String pincode = "";
		USERTYPE userType = USERTYPE.READER;
		Set<CATEGORY> prefs = new HashSet<CATEGORY>();
		String nationality = "";
		
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
	                    	if(name.equalsIgnoreCase(Name.FIRSTNAME))
	                    		firstName = value;
	                    	if(name.equalsIgnoreCase(User.USERNAME))
	                    		username = value;
	                    	if(name.equalsIgnoreCase(Name.LASTNAME))
	                    		lastName = value;
	                    	if(name.equalsIgnoreCase(Name.MIDDLENAME))
	                    		middleName = value;
	                    	if(name.equalsIgnoreCase(User.DESC))
	                    		desc = value;
	                    	if(name.equalsIgnoreCase(User.GENDER))
	                    		gender = GENDER.valueOf(value);
	                    	if(name.equalsIgnoreCase(User.RLANG))//
	                    		rLang = value;
	                    	if(name.equalsIgnoreCase(User.WLANG))//
	                    		wLang = value;
	                    	if(name.equalsIgnoreCase("dday"))//
	                    		day = value;
	                    	if(name.equalsIgnoreCase("dmonth"))//
	                    		month = value;
	                    	if(name.equalsIgnoreCase("dyear"))//
	                    		year = value;
	                    	if(name.equalsIgnoreCase(User.PENNAME))
	                    		penName = value;
	                    	if(name.equalsIgnoreCase(User.PHONE))
	                    		phone = value;
	                    	if(name.equalsIgnoreCase(User.EMAIL))
	                    		email = value;
	                    	if(name.equalsIgnoreCase("password"))
	                    		password = value;
	                    	if(name.equalsIgnoreCase(Address.CITY))
	                    		city = value;
	                    	if(name.equalsIgnoreCase(Address.STATE))
	                    		state = value;	                    	
	                    	if(name.equalsIgnoreCase(Address.PINCODE))
	                    		pincode = value;
	                    	if(name.equalsIgnoreCase(Address.ADDRESSLINE1))
	                    		addressLine1 = value;
	                    	if(name.equalsIgnoreCase(Address.ADDRESSLINE2))
	                    		addressLine2 = value;
	                    	if(name.equalsIgnoreCase(Address.ADDRESSLINE3))
	                    		addressLine3 = value;
	                    	if(name.equalsIgnoreCase(User.USERTYPE))
	                    		userType = USERTYPE.valueOf(value);
	                    	if(name.equalsIgnoreCase(User.NATIONALITY))
	                    		nationality = value;
	                    	if(name.equalsIgnoreCase(User.INTERESTS))
	                    	{
	                    		String[] interestes = value.split(",");
	                    		for(String interest : interestes)
	                    		{
	                    			prefs.add(CATEGORY.valueOf(interest));
	                    		}
	                    	}
	                    	
	                    }
                    }
                }
                
                
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
		
        Date dt = new Date();
        try
        {
	       	dt.setYear(Integer.parseInt(year));
	       	dt.setMonth(Integer.parseInt(month));
	       	dt.setDate(Integer.parseInt(day));
        }
        catch (Exception e) {
			e.printStackTrace();
		}
        
		if(username == null || username.length() == 0)
		{
			if(email != null && email.length() != 0)
				username = email;
		}
       	
		int age = new Date().getYear() - dt.getYear();
			
		UserAuthHandler.getInstance().addUserAuth(new UserAuth(username, password));
		
		Set<LANGUAGES> rLangs =  new HashSet<LANGUAGES>();
		Set<LANGUAGES> wLangs =  new HashSet<LANGUAGES>();
		
		
			for(String r : rLang.split(","))
			{
				try
				{
					rLangs.add(LANGUAGES.valueOf(r));
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
		
		for(String w : wLang.split(","))
		{
			try
			{
				wLangs.add(LANGUAGES.valueOf(w));
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		String uid = UserManager.getInstance().addUser(firstName, middleName, lastName, gender, email, 
				city, state, addressLine1, addressLine2, addressLine3, pincode,userType, prefs, 
				age, dt, nationality, phone, desc, rLangs, wLangs, USERLEVEL.FREE, username, penName, filePath);
		
		request.getSession().setAttribute("uid", uid);
		request.getSession().setAttribute("uname", username);
		
		request.getRequestDispatcher("/userHome.jsp?firstTime=true").forward(request, response);
		
		
		
	}
	}
}
