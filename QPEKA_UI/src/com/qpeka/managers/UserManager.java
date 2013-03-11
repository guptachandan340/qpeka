package com.qpeka.managers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.qpeka.db.book.store.AuthorHandler;
import com.qpeka.db.book.store.UserHandler;
import com.qpeka.db.book.store.tuples.Address;
import com.qpeka.db.book.store.tuples.Author;
import com.qpeka.db.book.store.tuples.BookMark;
import com.qpeka.db.book.store.tuples.Constants.AUTHORTYPE;
import com.qpeka.db.book.store.tuples.Constants.CATEGORY;
import com.qpeka.db.book.store.tuples.Constants.GENDER;
import com.qpeka.db.book.store.tuples.Constants.LANGUAGES;
import com.qpeka.db.book.store.tuples.Constants.USERLEVEL;
import com.qpeka.db.book.store.tuples.Constants.USERTYPE;
import com.qpeka.db.book.store.tuples.Name;
import com.qpeka.db.book.store.tuples.User;
import com.qpeka.utils.SystemConfigHandler;
import com.qpeka.utils.Utils;

public class UserManager {

private static UserManager instance = null;
	
	private UserManager()
	{
		
	}
	
	public static UserManager getInstance()
	{
		if(instance == null)
		{	
			synchronized (UserManager.class) {
			
				instance = new UserManager();
			}
			
		}
		
		return instance;
	}
	
	public String addUser(String firstName, String middleName, String lastName, GENDER gender, String email, String city, String state, String addressLine1,
			String addressLine2, String addressLine3, String pincode , USERTYPE userType, Set<CATEGORY> interests, int age , Date dob, String nationality, String phone,
			String desc, Set<LANGUAGES> rLang, Set<LANGUAGES> wLang, USERLEVEL level, String userName, String penName, String imageFile)
	{
		Address addr = new Address(city, state, addressLine1, addressLine2, addressLine3, pincode);
		
		
		User u = new User(userName, "", new Name(firstName, middleName, lastName), gender, email, addr, interests, level, new ArrayList<BookMark>(),
				age, dob, nationality, imageFile , phone, userType);
		u.setrLang(rLang);
		u.setwLang(wLang);
		u.setDesc(desc);
		u.setPenName(penName);
		
		String uID =  UserHandler.getInstance().addUser(u);
		
		Utils.createImageFile(imageFile,SystemConfigHandler.getInstance().getUserCoverImg() + uID + ".jpg");
		
		//add the Image File here
		return uID;
	}
	
	public void updateUser(String firstName, String middleName, String lastName, GENDER gender, String email, String city, String state, String addressLine1,
			String addressLine2, String addressLine3, String pincode , USERTYPE userType, String[] preferences, int age , Date dob, String nationality, String phone,
			String desc, Set<LANGUAGES> rLang, Set<LANGUAGES> wLang, USERLEVEL level, String userName, String penName, String imageFile)
	{
		Address addr = new Address(city, state, addressLine1, addressLine2, addressLine3, pincode);
		Set<CATEGORY> interests = new HashSet<CATEGORY>();
		for(String cat : preferences)
		{
			try
			{
				interests.add(CATEGORY.valueOf(cat));
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		User u = new User(userName, "", new Name(firstName, middleName, lastName), gender, email, addr, interests, level, new ArrayList<BookMark>(),
				age, dob, nationality, imageFile , phone, userType);
		u.setrLang(rLang);
		u.setwLang(wLang);
		u.setDesc(desc);
		u.setPenName(penName);
		
		UserHandler.getInstance().updateUser(u);
	}
	
	public void updateUser(JSONObject jReq)
	{
		try 
		{
			String uid = jReq.getString("id");
			if(jReq.has("type") && jReq.getString("type").equalsIgnoreCase("AUTHOR"))
			{
				User u = UserHandler.getInstance().getUser(uid);
				if(!u.isWriter())
				{
					Author a  = new Author(u.get_id(), u.getName(), u.getGender(), u.getDob(), u.getNationality(), "", u.getDesc(), "", u.getInterests(), AUTHORTYPE.LEVEL1);
					AuthorHandler.getInstance().addAuthor(a, false);
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserHandler.getInstance().updateUser(jReq);
	}
	
	public String getUser(String id)
	{
		return UserHandler.getInstance().getUser(id).toDBObject(false).toString();
	}
	
	public void addBookMark(String uid , String bookId, int page)
	{
		Set<Integer> pageIds = new HashSet<Integer>();
		pageIds.add(page);
		BookMark bmk = new BookMark(bookId, pageIds);
		User u = UserHandler.getInstance().getUser(uid);
		List<BookMark> l = u.getBookMarks();
		if(!l.contains(bmk))
		{
			l.add(bmk);
		}
		
		//sdfjg
		
	}
	
	public List<User> getUsersBySearchKey(String searchKey)
	{
		return UserHandler.getInstance().getUsersBySearchKey(searchKey);
	}
}
