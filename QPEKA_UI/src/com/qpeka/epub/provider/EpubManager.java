package com.qpeka.epub.provider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.qpeka.utils.SystemConfigHandler;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.domain.TOCReference;
import nl.siegmann.epublib.epub.EpubReader;

public class EpubManager {

	private static EpubManager instance = null;
	private EpubReader epubReader = new EpubReader();
	
	private EpubManager(){
		
	}
	
	public static EpubManager getInstance()
	{
		if(instance == null)
			instance = new EpubManager();
		
		return instance;
	}
	
	private static void logTableOfContents(Book book,List<TOCReference> tocReferences, int depth , StringBuffer buffer,String id) throws IOException {
	    if (tocReferences == null) {
	      return;
	    }
	    for (TOCReference tocReference : tocReferences) {
	      StringBuilder tocString = new StringBuilder();
	      tocString.append("<li><a href=\"#");
	      String url = "";//"http://localhost:8080/EpubReader?resId="+tocReference.getResourceId();
	      tocString.append(url + "\" onclick=\"loadContent('"+id+"','"+tocReference.getResourceId()+"');return false;\" >");
	      for (int i = 0; i < depth; i++) {
	        tocString.append("\t");
	      }
	      tocString.append(tocReference.getTitle());
	      tocString.append("</a></li>\n");
	      buffer.append(tocString);
	    
	      logTableOfContents(book,tocReference.getChildren(), depth + 1, buffer,id);
	    }
	  }
	
	public String getTableOfContents(String id)
	{
		try
		{		
			String file = SystemConfigHandler.getInstance().getSrcBookFolder()+"/"+id+".epub";
			Book book = epubReader.readEpub(new FileInputStream(file));
			StringBuffer buf = new StringBuffer();
			logTableOfContents(book, book.getTableOfContents().getTocReferences(), 0, buf,id);
			String ret = "<ul>" + buf.toString() + "</ul>";
			return ret;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public String getTitle(String id)
	{
		try {
			String fileName = SystemConfigHandler.getInstance().getSrcBookFolder()+"/"+id+".epub";
			Book book = epubReader.readEpub(new FileInputStream(fileName));
			return book.getTitle();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public String getContentGivenResourceId(String id , String resId)
	{
		try {
			String fileName = SystemConfigHandler.getInstance().getSrcBookFolder()+"/"+id+".epub";
			Book book = epubReader.readEpub(new FileInputStream(fileName));
			for(Resource r : book.getTableOfContents().getAllUniqueResources())
			{
				if(r.getId().equalsIgnoreCase(resId))
					return new String(r.getData());
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
	}
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		//System.out.println(EpubManager.getInstance().getTableOfContents("/home/manoj/Sam Williams - Free As In Freedom.epub"));
		String fileName = "/home/manoj/The.epub";
		Book book = new EpubReader().readEpub(new FileInputStream(fileName));
		System.out.println(new String(book.getNcxResource().getData()));
	}
}
