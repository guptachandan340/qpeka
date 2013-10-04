package com.qpeka.servlets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qpeka.db.book.store.tuples.Work;
import com.qpeka.managers.WorkContentManager;
import com.qpeka.utils.SystemResourceHandler;

/**
 * Servlet implementation class ImageServlet
 */
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * returns the cover image given a book. It assumes the following dir structure /coverimages/books/<bookId>/<imagefile>.jpg  
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub
		//FileInputStream fs = new FileInputStream(new File("/home/manoj/pride.LZZZZZZZ.jpg"));
		String action = request.getParameter("action");
		if(action == null || action.length() == 0 || action.equalsIgnoreCase("bookcover"))
		{
			String bookId = request.getParameter("book");
			if(bookId != null && bookId.length() > 0)
			{
				response.setContentType("image/jpeg");
				OutputStream out = null;
				try
				{
					
					File f = new File(SystemResourceHandler.getInstance().getBookCoverPageFolder()+"/"+bookId+".jpg");
					System.out.println("[FILE] value=" + f.getAbsolutePath());
					BufferedImage bi = ImageIO.read(f);
					out = response.getOutputStream();
					ImageIO.write(bi, "jpg", out);
					
					
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				finally{
					try 
					{
						out.close();
					}
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}
		else if (action.equalsIgnoreCase("userimg")){
			String uid = request.getParameter("uid");
			if(uid != null && uid.length() > 0)
			{
				response.setContentType("image/jpeg");
				OutputStream out = null;
				try
				{
					
					File f = new File(SystemResourceHandler.getInstance().getUserImageFolder()+"/"+uid+".jpg");
					if(!f.exists())
						f = new File(SystemResourceHandler.getInstance().getUserImageFolder()+"anon.jpg");
					System.out.println("[FILE] value=" + f.getAbsolutePath());
					BufferedImage bi = ImageIO.read(f);
					out = response.getOutputStream();
					ImageIO.write(bi, "jpg", out);
					
					
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				finally{
					try 
					{
						out.close();
					}
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
		}
	}
	}	
}
