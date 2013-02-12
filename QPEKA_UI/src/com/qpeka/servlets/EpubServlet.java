package com.qpeka.servlets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.qpeka.utils.SystemConfigHandler;

/**
 * Servlet implementation class EpubServlet
 */
public class EpubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EpubServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	private String encodeFileToBase64Binary(String fileName)
	throws IOException {
	 
		File file = new File(fileName);
		byte[] bytes = loadFile(file);
		byte[] encoded = Base64.encodeBase64(bytes);
		String encodedString = new String(encoded);
		 
		return encodedString;
	}
	
	private static byte[] loadFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		 
		long length = file.length();
		if (length > Integer.MAX_VALUE) {
		// File is too large
		}
		byte[] bytes = new byte[(int)length];
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
		&& (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
		offset += numRead;
		}
		 
		if (offset < bytes.length) {
		throw new IOException("Could not completely read file "+file.getName());
		}
		 
		is.close();
		return bytes;
		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * returns the cover image given a book. It assumes the following dir structure /coverimages/books/<bookId>/<imagefile>.jpg  
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
			
			response.setContentType("application/epub+zip");
			
			try
			{
				
				File f = new File("/home/manoj/The.epub");
				FileInputStream ir = new FileInputStream(f);
				
				System.out.println("[FILE] value=" + f.getAbsolutePath());
				response.getWriter().write(encodeFileToBase64Binary("/home/manoj/The.epub"));
//				byte[] n = new byte[10000000];
//				ir.read(n);
//				OutputStream os = response.getOutputStream();
//				os.write(n);
//				os.flush();
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				
			}
	}
}
