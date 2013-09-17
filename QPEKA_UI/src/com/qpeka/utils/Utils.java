package com.qpeka.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Utils {

	public static boolean isValiddEmail(String email) {
		try {
			new InternetAddress(email).validate();
			return true;
		} catch (Exception e) {
			// it's not valid
			return false;
		}
	}

	public static  String getWorkUploadText(String firstName)
	{
		String ret = "Dear "+firstName+"\n\n"+

		"Thank you very much for uploading your valuable works on Qpeka.\n"+

		"As a token of our appreciation, we are upgrading your account to Level 2. The works have been linked to your email id. Please do intimate us of any changes in your email id at info@qpeka.com.\n"+

		"Once we go live, we shall share with you a detailed agreement, which shall contain all the terms for uploading. If you agree to these, your works shall be published on Qpeka.\n"+

		"We would again like to thank you for your interest in us.\n\n"+

		"Have a great day!\n\n"+

		"Team Qpeka";
		
		return ret;
	}
	
	public static  String getWelcomeMailText(String firstName)
	{
		String ret = "Dear "+firstName+"\n\n"+

		"Thank you very much for registering yourself on Qpeka\n"+

		"Be ready to be amazed by Qpeka, which shall give you free access quality books, short stories, poems and articles.\n"+

		"If you also write, we would love to have your works on our website as well. We have a very unique and attractive monetization model for our authors new as well as established.\n"+

		"As part of our content collection exercise, we are giving a free level upgrade to authors  who upload their works on our website before our launch. This shall make them eligible for higher revenue from their works once we go live. To upload your works please click on 'Qpeka Offers' and upload your works.\n"+

		"If you are a book editor, proof reader, cover designer, literary agent or anyone who is in any way related to written works, we also have a dedicated section for professionals like yourselves to take up assignments from authors at Qpeka.\n"+

		"We at Qpeka believe in creating a unique environment focusing on quality as well as interactivity through our social networking platform.\n"+

		"We look forward to seeing you on Qpeka, when we go live. We shall keep you updated about our progress.\n"+

		"Please note, that we shall be linking this email id to your user account. Please let us know if you wish to change the same.\n\n"+

		"Have a great day!\n\n" +
		
		"Team Qpeka";
		
		return ret;
	}
	
	public static void sendMailViaGodaddy(String from, String password,	String to, String name, String text) {

		try {
			
//			Properties props = System.getProperties();
//			props.setProperty("mail.transport.protocol", "smtp");
//			props.setProperty("mail.host", "smtpout.secureserver.net");
//			props.setProperty("mail.port", "80");
//			props.setProperty("mail.smtp.auth", "true");
//			props.setProperty("mail.user", from);
//			props.setProperty("mail.password", password);
			
			Properties props = System.getProperties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp." +
					"+++++++++++++++++++++host", "smtpout.secureserver.net");
			props.put("mail.smtp.port", "80");
			props.put("mail.user", from);
			props.put("mail.password", password);
			
			Session mailSession = Session.getDefaultInstance(props, null);

			// mailSession.setDebug(true);

			Transport transport = mailSession.getTransport("smtp");
			MimeMessage message = new MimeMessage(mailSession);
			message.setSentDate(new java.util.Date());
			message.setSubject("Thanks for your work, " + name + " !");
			message.setFrom(new InternetAddress(from));
			
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			message.setText(text);
			transport.connect("smtpout.secureserver.net", from, password);
			transport.sendMessage(message,
			message.getRecipients(Message.RecipientType.TO));
			transport.close();

			System.out.println("Email via go daddy sent");

		} catch (Exception e) {

			System.out.println("Failed to send Email : " + e.getMessage());

		}
	}
	
	/************************** GET DATE MODULE *****************************/
	/**
	 *  Being called by register, editProfile and getProfile in UserManager
	 *  
	 * @return date format
	 */
	public static DateFormat getFormatedDate() {
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		format.setTimeZone(TimeZone.getTimeZone("UTC"));
		return format;
	}

	public static void main(String[] args) {
		System.out.println(getWelcomeMailText("MANOJ"));
//		List<String> l = new ArrayList<String>();
//		l.add("manoj.thakur66@gmail.com");
//		/sendMailViaGodaddy("welcome@qpeka.com", "qpeka", l, "Welcome to qpeka", "Welcome");
	}

	public static void createImageFile(String srcFile, String destFile) {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			File f1 = new File(srcFile);
			File f2 = new File(destFile);
			in = new FileInputStream(f1);
			out = new FileOutputStream(f2);

			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}

		} catch (FileNotFoundException ex) {
			System.out
					.println(ex.getMessage() + " in the specified directory.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				in.close();
				out.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println("File copied.");
		}
	}
}
