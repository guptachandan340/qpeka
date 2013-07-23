package com.qpeka.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;

import com.qpeka.db.Constants.CATEGORY;
import com.qpeka.db.Constants.GENDER;
import com.qpeka.db.Constants.LANGUAGES;
import com.qpeka.db.Constants.USERLEVEL;
import com.qpeka.db.Constants.USERTYPE;
import com.qpeka.db.handler.user.AuthorHandler;
import com.qpeka.db.handler.user.UserHandler;
import com.qpeka.db.handler.user.UserProfileHandler;
import com.qpeka.db.user.User;
import com.qpeka.db.user.profile.Address;
import com.qpeka.db.user.profile.Name;
import com.qpeka.db.user.profile.UserProfile;
import com.qpeka.db.user.profile.type.Author;
import com.qpeka.managers.SessionManager;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String requestype = request.getParameter("rType"); // password + user
															// availability
		if (requestype.equalsIgnoreCase("authAvail")) {
			String userName = request.getParameter("uname");
			String pwd = request.getParameter("pwd");

			Writer wr = response.getWriter();

			if (UserHandler.getInstance().usernameExists(userName) != true) {
				wr.write("{\"status\":\"ok\"}");
				wr.flush();
				return;
			}

			wr.write("{\"status\":\"none\"}");
			wr.flush();
			return;
		} else if (requestype.equalsIgnoreCase("getUser")) {
			String user = UserManager.getInstance().getUser(
					request.getParameter("uid"));

			Writer wr = response.getWriter();
			wr.write(user);
			wr.flush();
			return;
		} else if (requestype.equalsIgnoreCase("getAuthor")) {
			Author a = AuthorHandler.getInstance().getAuthor(
					request.getParameter("aid"));

			Writer wr = response.getWriter();
			wr.write(a.toDBObject(false).toString());
			wr.flush();
			return;
		} else if (requestype.equalsIgnoreCase("getUsersBysearchKey")) {
			JSONArray jsa = new JSONArray();
			for (UserProfile u : UserManager.getInstance().getUsersBySearchKey(
					request.getParameter("key"))) {
				jsa.put(u.toDBObject(false));
			}
			Writer wr = response.getWriter();
			wr.write(jsa.toString());
			wr.flush();
			return;
		} else if (requestype.equalsIgnoreCase("logout")) {
			request.getSession().invalidate();
			request.getRequestDispatcher("/landing.jsp").forward(request,
					response);

		} else if (requestype.equalsIgnoreCase("login")) {
			Writer wr = response.getWriter();

			if (UserHandler.getInstance().getUser(request.getParameter("uid"),
					request.getParameter("password"))) {

				String id = UserProfileHandler.getInstance()
						.getUserByUserName(request.getParameter("uid"))
						.get_id();
				// request.getRequestDispatcher("/myProfile.jsp?uid="+id).forward(request,
				// response);
				HttpSession sess = request.getSession();
				sess.setAttribute("uid", id);

				SessionManager.getInstance().addSession(id, sess);

				response.sendRedirect("http://"
						+ SystemConfigHandler.getInstance().getHost()
						+ "/QpekaWeb/myProfile.jsp?uid=" + id);
				return;

			} else {
				response.sendRedirect("http://"
						+ SystemConfigHandler.getInstance().getHost()
						+ "/QpekaWeb/home.jsp?error=true");

				return;
			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/*
		 * (String firstName, String middleName, String lastName, GENDER gender,
		 * String email, String city, String state, String addressLine1, String
		 * addressLine2, String addressLine3, String pincode , USERTYPE
		 * userType, String[] preferences, int age , Date dob, String
		 * nationality, String phone, String bio, LANGUAGES rLang, LANGUAGES
		 * wLang, USERLEVEL level, String userName, String penName, String
		 * imageFile)
		 */

		String firstName = "";// request.getParameter("firstName") == null ?
								// "":request.getParameter("firstName");
		String middleName = "";// request.getParameter("middleName") == null ?
								// "":request.getParameter("middleName");
		String lastName = "";// request.getParameter("lastName") == null ?
								// "":request.getParameter("lastName");
		GENDER gender = GENDER.MALE;// GENDER.valueOf(request.getParameter("gender")
									// == null ?
									// "MALE":request.getParameter("gender"));
		String email = "";// request.getParameter("email") == null ?
							// "":request.getParameter("email");
		String username = "";// request.getParameter("username") == null ?
								// "":request.getParameter("username");
		String password = "";// request.getParameter("password") == null ?
								// "":request.getParameter("password");
		String day = "";// request.getParameter("day") == null ?
						// "":request.getParameter("day");
		String month = "";// request.getParameter("month") == null ?
							// "":request.getParameter("month");
		String year = "";// request.getParameter("year") == null ?
							// "":request.getParameter("year");
		String rLang = "";// request.getParameter("rLang") == null ?
							// "":request.getParameter("rLang");
		String wLang = "";// request.getParameter("wLang") == null ?
							// "":request.getParameter("wLang");
		String phone = "";// request.getParameter("phone") == null ?
							// "":request.getParameter("phone");
		String bio = "";// request.getParameter("bio") == null ?
							// "":request.getParameter("bio");
		String penName = "";// request.getParameter("penName") == null ?
							// "":request.getParameter("penName");
		String city = "";
		String state = "";
		String addressLine1 = "";
		String addressLine2 = "";
		String addressLine3 = "";
		String pincode = "";
		USERTYPE userType = USERTYPE.AUTHENTIC;
		Set<CATEGORY> prefs = new HashSet<CATEGORY>();
		String nationality = "";
		String landing = "";
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String filePath = "";
		String fileName = "";
		FileItem fi = null;
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			try {
				List items = upload.parseRequest(request);
				Iterator iterator = items.iterator();
				while (iterator.hasNext()) {
					FileItem item = (FileItem) iterator.next();

					if (item != null) {
						if (!item.isFormField()) {
							fileName = item.getName();

							// String root = "/var/tmp";
							// File path = new File(root);
							// if (!path.exists()) {
							// boolean status = path.mkdirs();
							// }
							// filePath = path + "/" + fileName;
							fi = item;
							// File uploadedFile = new File(path + "/" +
							// fileName);
							// System.out.println(uploadedFile.getAbsolutePath());
							// item.write(uploadedFile);
						} else {

							String name = item.getFieldName().trim();
							String value = item.getString().trim();
							if (name.equalsIgnoreCase(Name.FIRSTNAME))
								firstName = value;
							if (name.equalsIgnoreCase(UserProfile.USERNAME))
								username = value;
							if (name.equalsIgnoreCase(Name.LASTNAME))
								lastName = value;
							if (name.equalsIgnoreCase(Name.MIDDLENAME))
								middleName = value;
							if (name.equalsIgnoreCase(UserProfile.BIOGRAPHY))
								bio = value;
							if (name.equalsIgnoreCase(UserProfile.GENDER))
								gender = GENDER.valueOf(value);
							if (name.equalsIgnoreCase(UserProfile.RLANG))//
								rLang = value;
							if (name.equalsIgnoreCase(UserProfile.WLANG))//
								wLang = value;
							if (name.equalsIgnoreCase("dday"))//
								day = value;
							if (name.equalsIgnoreCase("dmonth"))//
								month = value;
							if (name.equalsIgnoreCase("dyear"))//
								year = value;
							if (name.equalsIgnoreCase(UserProfile.PENNAME))
								penName = value;
//							if (name.equalsIgnoreCase(UserProfile.PHONE))
//								phone = value;
							if (name.equalsIgnoreCase(User.EMAIL))
								email = value;
							if (name.equalsIgnoreCase("password"))
								password = value;
							if (name.equalsIgnoreCase(Address.CITY))
								city = value;
							if (name.equalsIgnoreCase(Address.STATE))
								state = value;
							if (name.equalsIgnoreCase(Address.PINCODE))
								pincode = value;
							if (name.equalsIgnoreCase(Address.ADDRESSLINE1))
								addressLine1 = value;
							if (name.equalsIgnoreCase(Address.ADDRESSLINE2))
								addressLine2 = value;
							if (name.equalsIgnoreCase(Address.ADDRESSLINE3))
								addressLine3 = value;
							if (name.equalsIgnoreCase(UserProfile.USERTYPE))
								userType = USERTYPE.valueOf(value);
							if (name.equalsIgnoreCase(UserProfile.NATIONALITY))
								nationality = value;
							if (name.equalsIgnoreCase("landing"))
								landing = value;
							if (name.equalsIgnoreCase(UserProfile.INTERESTS)) {
								String[] interestes = value.split(",");
								for (String interest : interestes) {
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
			try {
				dt.setYear(Integer.parseInt(year));
				dt.setMonth(Integer.parseInt(month));
				dt.setDate(Integer.parseInt(day));
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (username == null || username.length() == 0) {
				if (email != null && email.length() != 0)
					username = email;
			}

			int age = new Date().getYear() - dt.getYear();

			if (landing != null && landing.length() > 0) {
				handleGuest(firstName, lastName, email, year, month, day, fi,
						response);
				return;
			}

			UserHandler.getInstance().addUserAuth(new User(username, password));

			Set<LANGUAGES> rLangs = new HashSet<LANGUAGES>();
			Set<LANGUAGES> wLangs = new HashSet<LANGUAGES>();

			for (String r : rLang.split(",")) {
				try {
					rLangs.add(LANGUAGES.valueOf(r));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			for (String w : wLang.split(",")) {
				try {
					wLangs.add(LANGUAGES.valueOf(w));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

//			String uid = UserManager.getInstance().addUser(firstName,
//					middleName, lastName, gender, email, city, state,
//					addressLine1, addressLine2, addressLine3, pincode,
//					userType, prefs, age, dt, nationality, phone, bio, rLangs,
//					wLangs, USERLEVEL.FREE, username, penName, filePath);
			User user = new User(username, password);
			long userid = UserHandler.getInstance().insert(user);
			
			UserProfile userprofile = new UserProfile(userid, penname, name, gender, dob, age, nationality, website, bio, profilepic);
			
			long uid = UserProfileHandler.getInstance().insert(userprofile);

			request.getSession().setAttribute("uid", uid);
			request.getSession().setAttribute("uname", username);

			// request.getRequestDispatcher("/myProfile.jsp?uid="+uid).forward(request,
			// response);
			response.sendRedirect("http://"
					+ SystemConfigHandler.getInstance().getHost()
					+ "/QpekaWeb/myProfile.jsp?uid=" + uid);
			// http://localhost:8080/QpekaWeb/myProfile.jsp?uid=5152878c86adc1fd5ad43dc5

		}
	}

	private void handleGuest(String firstName, String lastName, String email,
			String year, String month, String day, FileItem fi,
			HttpServletResponse response) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.set(Calendar.MONTH, Integer.parseInt(month));
			cal.set(Calendar.DATE, Integer.parseInt(day));
			cal.set(Calendar.YEAR, Integer.parseInt(year));

		} catch (Exception e) {
			e.printStackTrace();
		}
		int age = Calendar.getInstance().get(Calendar.YEAR)
				- cal.get(Calendar.YEAR);

		String uid = UserManager.getInstance().addUser(firstName, "", lastName,
				GENDER.MALE, email, "", "", "", "", "", "", USERTYPE.AUTHOR,
				new HashSet<CATEGORY>(), age, cal.getTime(), "Indian",
				"999999999", "", new HashSet<LANGUAGES>(),
				new HashSet<LANGUAGES>(), USERLEVEL.FREE, "", "", "");
		OutputStream os = null;
		if (fi != null)
			try {
				File f = new File(
						"/var/lib/openshift/5155a32ae0b8cd2c55000076/jbossews-1.0/data/"
								+ uid);
				if (!f.exists())
					f.createNewFile();

				fi.write(f);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (os != null)
					try {
						os.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}

		try {
			response.sendRedirect("http://landing-qpeka.rhcloud.com/QpekaWeb/landing.jsp?msg=success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
