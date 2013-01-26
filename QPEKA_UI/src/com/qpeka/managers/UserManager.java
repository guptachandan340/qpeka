package com.qpeka.managers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.qpeka.db.book.store.UserHandler;
import com.qpeka.db.book.store.tuples.Address;
import com.qpeka.db.book.store.tuples.BookMark;
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
			String addressLine2, String addressLine3, String pincode , USERTYPE userType, String[] preferences, int age , Date dob, String nationality, String phone,
			String desc, LANGUAGES rLang, LANGUAGES wLang, USERLEVEL level, String userName, String penName, String imageFile)
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
		
		String uID =  UserHandler.getInstance().addUser(u);
		
		Utils.createImageFile(imageFile,SystemConfigHandler.getInstance().getUserCoverImg() + uID + ".jpg");
		
		//add the Image File here
		return uID;
	}
	
	public void updateUser(String firstName, String middleName, String lastName, GENDER gender, String email, String city, String state, String addressLine1,
			String addressLine2, String addressLine3, String pincode , USERTYPE userType, String[] preferences, int age , Date dob, String nationality, String phone,
			String desc, LANGUAGES rLang, LANGUAGES wLang, USERLEVEL level, String userName, String penName, String imageFile)
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
}
