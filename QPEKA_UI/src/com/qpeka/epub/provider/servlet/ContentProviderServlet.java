package com.qpeka.epub.provider.servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubReader;

import com.qpeka.utils.SystemConfigHandler;

/**
 * Servlet implementation class ContentProviderServlet
 */

public class ContentProviderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private enum ACTION {
		GETOPFRESOURCE,
		GETRESOURCE,
		GETNCXRESOURCE
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
			
			response.setContentType("text/xml;charset=UTF-8");
			w.write(new String(book.getOpfResource().getData()));
			w.flush();
		}
		else if(action == ACTION.GETRESOURCE.ordinal())
		{
			String resource = request.getParameter("res");
			String subpart = request.getParameter("subpart");
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
					String ret  = null;
					if(subpart == null || subpart.length() == 0)
						ret = new String(res.getData());
					else
						ret = retrieveSubpart1(new String(res.getData()), subpart);
					ret = ret.replaceAll("<k>", "<p>");
					ret = ret.replaceAll("</k>", "</p>");
					w.write(ret);
				}
			}
			w.flush();
		}
		else if(action == ACTION.GETNCXRESOURCE.ordinal())
		{
			String file = "";
			if(fileparam != null && fileparam.length() > 0 )
				file = fileparam;
			else
				file = SystemConfigHandler.getInstance().getSrcBookFolder()+"/"+id+".epub";
			Book book = epubReader.readEpub(new FileInputStream(file));
			
			Writer w = response.getWriter();
			
			response.setContentType("text/xml;charset=UTF-8");
			
			w.write(new String(book.getNcxResource().getData()));
			
			w.flush();
		}
	}
	
	private boolean isMatch(Element e, String subpartName)
	{
		if(e == null)
			return false;
		if(e.id().equals(subpartName))
			return true;
		else
		{
			Iterator<Element> it = e.children().iterator();
			while(it.hasNext())
			{
				if(isMatch(it.next(), subpartName))
					return true;
			}
		}
		return false;
	}
	
	private String retrieveSubpart1(String mainPart, String subpartName)
	{
		Document doc = Jsoup.parse(mainPart, "UTF-8");
		
		Elements eles = doc.body().children();
		StringBuffer temp = new StringBuffer();
		boolean found = false;
		Iterator<Element> its = eles.iterator();
		while(its.hasNext())
		{
			Element elm = its.next();
			if(isMatch(elm,subpartName))
				found = true;
			
			if(found)
				temp.append(elm.toString());	
		}
		
		if(temp != null)
			return temp.toString();
		else 
			return "";
	}
	
	private String retrieveSubpart(String mainPart, String subpartName)
	{
		Document doc = Jsoup.parse(mainPart, "UTF-8");
		Element e = doc.getElementById(subpartName);
		
		Elements eles = doc.getAllElements();
		StringBuffer temp = new StringBuffer();
		boolean found = false;
		//jsholl recursivelyandar jaatahai 
		Element elem = eles.get(0);
		while(elem != null)
		{
			if(elem.id().equals(e.id()))
				found = true;
			
			if(found)
			{
				temp.append(elem.toString());	
			}
			if(elem.siblingIndex() == elem.siblingNodes().size())
				elem = null;
			else
				elem =  (Element)elem.siblingNodes().get(elem.siblingIndex()+1);
		}
		
		if(temp != null)
			return temp.toString();
		else 
			return "";
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	public static void main(String[] args) throws FileNotFoundException, IOException {
//		EpubReader epubReader = new EpubReader();
//		
//		String file = "/home/manoj/Downloads/Learn You Some Erlang for Great Good!.epub";
//		Book book = epubReader.readEpub(new FileInputStream(file));
//		
//		for(String x :  book.getResources().getResourceMap().keySet())
//		{
//			System.out.println(new String(x + "=" + book.getResources().getResourceMap().get(x)));
//		}
		
//		String s  = "<div>DIV1<p></p>P1</div><div>DIV2<p></p>P2</div><div>DIV3<p></p>P3</div><div>DIV4<p></p>P4</div>";
//		Document doc = Jsoup.parse(s, "UTF-8");
//		Elements e  = doc.body().children();
//		Iterator<Element> it = e.iterator();
//		while(it.hasNext())
//			System.out.println("Hello " + it.next().toString());
		System.out.println("</k><p><br /> <br /></p><k>".replaceAll("<k>", "<p>"));
	}

}
