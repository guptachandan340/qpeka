package com.qpeka.epub.provider.servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.domain.TOCReference;
import nl.siegmann.epublib.epub.EpubReader;

import org.apache.commons.lang.StringEscapeUtils;

import com.qpeka.utils.SystemConfigHandler;

/**
 * Servlet implementation class ContentProviderServlet
 */

public class ContentProviderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private enum ACTION {
		GETOPFRESOURCE,
		GETRESOURCE
	};
	private EpubReader epubReader = new EpubReader();
	
    /**
     * Default constructor. 
     */
    public ContentProviderServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String fileparam = request.getParameter("file");
		int action = Integer.parseInt(request.getParameter("action"));
		
		if(action == ACTION.GETOPFRESOURCE.ordinal())
		{
			String file = "";
			if(fileparam != null && fileparam.length() > 0 )
				file = fileparam;
			else
				file = SystemConfigHandler.getInstance().getSrcBookFolder()+"/"+id+".epub";
			Book book = epubReader.readEpub(new FileInputStream(file));
			Writer w = response.getWriter();
			
			response.setContentType("text/xml");
			w.write(new String(book.getOpfResource().getData()));
			w.flush();
		}
		else if(action == ACTION.GETRESOURCE.ordinal())
		{
			String resource = request.getParameter("res");
			String file = "";
			if(fileparam != null && fileparam.length() > 0 )
				file = fileparam;
			else
				file = SystemConfigHandler.getInstance().getSrcBookFolder()+"/"+id+".epub";
			Book book = epubReader.readEpub(new FileInputStream(file));
			
			Writer w = response.getWriter();
			
			response.setContentType("text/html;charset=UTF-8");
			
			for(String x :  book.getResources().getResourceMap().keySet())
			{
				Resource res = book.getResources().getResourceMap().get(x);
				if(res.getHref().equalsIgnoreCase(resource))
				{
					String ret  = new String(res.getData());
					System.out.println(ret);
					w.write(ret);
				}
			}
			w.flush();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	public static void main(String[] args) throws FileNotFoundException, IOException {
		EpubReader epubReader = new EpubReader();
		
		String file = "/home/manoj/Downloads/Learn You Some Erlang for Great Good!.epub";
		Book book = epubReader.readEpub(new FileInputStream(file));
		
		for(String x :  book.getResources().getResourceMap().keySet())
		{
			System.out.println(new String(x + "=" + book.getResources().getResourceMap().get(x)));
		}
	}

}
