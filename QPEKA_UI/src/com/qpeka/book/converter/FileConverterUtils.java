package com.qpeka.book.converter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.MediaType;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hwpf.HWPFDocumentCore;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.converter.WordToHtmlUtils;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class FileConverterUtils {
	
	private static Pattern pattern = Pattern.compile("<Chapter-[0-9]*>");
	
	public static String  convertDocToEpub(File f, String title, String language) throws FileNotFoundException, IOException, ParserConfigurationException, TransformerException
	{
		HWPFDocumentCore wordDocument = WordToHtmlUtils.loadDoc(new FileInputStream(f));
		//InputStream is = new ByteArrayInputStream(str.getBytes());
		Range r = null;
	
		Book destBook = new Book();
		destBook.getMetadata().setLanguage(language);
		List<String> list = new ArrayList<String>();
		list.add(title);
		destBook.getMetadata().setTitles(list);
		int start = 0;
		int end = 0;
		boolean toend = false ;
		for(int i = 0 ;!toend;i++)
		{
			WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
		            DocumentBuilderFactory.newInstance().newDocumentBuilder()
		                    .newDocument());
			if(i == 0)
			{
				start = 0;
			}
			else
			{
				start = end + ("<Chapter-"+i+">").length();
			}
			end =  wordDocument.getText().toString().indexOf("<Chapter-"+(i+1)+">");
			
			if(end == -1)
			{
				end = wordDocument.getText().length();
				toend = true;
			}
			r = new Range(start,end, wordDocument); 
			
			wordToHtmlConverter.processDocumentPart(wordDocument, r);
			Document htmlDocument = wordToHtmlConverter.getDocument();
		    ByteArrayOutputStream out = new ByteArrayOutputStream();
		    
		    DOMSource domSource = new DOMSource(htmlDocument);
		    
		    StreamResult streamResult = new StreamResult(out);
	
		    TransformerFactory tf = TransformerFactory.newInstance();
		    Transformer serializer = tf.newTransformer();
		    serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		    serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		    serializer.setOutputProperty(OutputKeys.METHOD, "html");
		    serializer.transform(domSource, streamResult);
		    out.close();
		    
		    destBook.addSection("Chapter-"+i, new Resource("chapter"+i, out.toByteArray(), "", new MediaType("html", "html")));
		    
		    //System.out.println("Chapter "+i+" = " + result);
		}
		EpubWriter wr = new EpubWriter();
		String fileToret = f.getParent()+"/"+f.getName().split("\\.")[0]+".epub";
		wr.write(destBook, new FileOutputStream(new File(fileToret)));
	    return fileToret;
	}
	
	public static String convertDocxToEpub(File f, String title, String language) throws FileNotFoundException, IOException, ParserConfigurationException, TransformerException
	{
		int i = 1;
		Book destBook = new Book();
		destBook.getMetadata().setLanguage(language);
		List<String> list = new ArrayList<String>();
		list.add(title);
		destBook.getMetadata().setTitles(list);
	 	  
		XWPFDocument wordDocument = null;
	    try {
	        wordDocument = new XWPFDocument(new FileInputStream(f));
	        
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	    
	    WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
	    org.w3c.dom.Document htmlDocument = wordToHtmlConverter.getDocument();
	    
	    boolean isFirst = true;
	    
	    for(XWPFParagraph p : wordDocument.getParagraphs())
	    {
	    	if(p.isEmpty() || p.getParagraphText().length() == 0)
	    		continue;
	    	
	    	Matcher m = pattern.matcher(p.getParagraphText().trim());
	    	if(m.matches())
	    	{
	    		if(isFirst)
	    			isFirst = false;
	    		else
	    		{
		    		ByteArrayOutputStream out = new ByteArrayOutputStream();
				    DOMSource domSource = new DOMSource(htmlDocument);
				    StreamResult streamResult = new StreamResult(out);
	
				    TransformerFactory tf = TransformerFactory.newInstance();
				    Transformer serializer = tf.newTransformer();
				    serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				    serializer.setOutputProperty(OutputKeys.INDENT, "yes");
				    serializer.setOutputProperty(OutputKeys.METHOD, "html");
				    serializer.transform(domSource, streamResult);
				    out.close();
				    
				    destBook.addSection("Chapter-"+i, new Resource("chapter"+i, out.toByteArray(), "", new MediaType("html", "html")));
				    i++;
				    
				    wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
				    htmlDocument = wordToHtmlConverter.getDocument();
	    		}
	    	}
	    	else
	    	{
	    		Element ele = htmlDocument.createElement("p");
	    		ele.setTextContent(p.getText());
	    		htmlDocument.getElementsByTagName("body").item(0).appendChild(ele);
	    	}
	    	
	    }
	    
	    //added the below code to ensure that the last paragraph is always captured
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    DOMSource domSource = new DOMSource(htmlDocument);
	    StreamResult streamResult = new StreamResult(out);

	    TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer serializer = tf.newTransformer();
	    serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	    serializer.setOutputProperty(OutputKeys.INDENT, "yes");
	    serializer.setOutputProperty(OutputKeys.METHOD, "html");
	    serializer.transform(domSource, streamResult);
	    out.close();
	    
	    destBook.addSection("Chapter-"+i, new Resource("chapter"+i, out.toByteArray(), "", new MediaType("html", "html")));
	    i++;
	   
	    
	    EpubWriter wr = new EpubWriter();
		String fileToret = f.getParent()+"/"+f.getName().split("\\.")[0]+".epub";
		wr.write(destBook, new FileOutputStream(new File(fileToret)));
		
		return fileToret;
	}
	
	
	public static void main(String[] args) {
		
		try {
			String x = convertDocToEpub(new File("/home/manoj/Pride and Prejudice.doc"), "title", "language");
			//EpubProcessorNew.processEpub(x,"/home/manoj/testagain.epub");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main1(String[] args) throws FileNotFoundException, IOException, ParserConfigurationException, TransformerException {
		int i = 1;
		Book destBook = new Book();
		destBook.getMetadata().setLanguage("");
		List<String> list = new ArrayList<String>();
		list.add("");
		destBook.getMetadata().setTitles(list);
	 	  
		XWPFDocument wordDocument = null;
	    try {
	        wordDocument = new XWPFDocument(new FileInputStream("/home/manoj/test.docx"));
	        
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	    
	    WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
	    org.w3c.dom.Document htmlDocument = wordToHtmlConverter.getDocument();
	    
	    boolean isFirst = true;
	    
	    for(XWPFParagraph p : wordDocument.getParagraphs())
	    {
	    	if(p.isEmpty() || p.getParagraphText().length() == 0)
	    		continue;
	    	
	    	Matcher m = pattern.matcher(p.getParagraphText().trim());
	    	if(m.matches())
	    	{
	    		if(isFirst)
	    			isFirst = false;
	    		else
	    		{
		    		ByteArrayOutputStream out = new ByteArrayOutputStream();
				    DOMSource domSource = new DOMSource(htmlDocument);
				    StreamResult streamResult = new StreamResult(out);
	
				    TransformerFactory tf = TransformerFactory.newInstance();
				    Transformer serializer = tf.newTransformer();
				    serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				    serializer.setOutputProperty(OutputKeys.INDENT, "yes");
				    serializer.setOutputProperty(OutputKeys.METHOD, "html");
				    serializer.transform(domSource, streamResult);
				    out.close();
				    
				    destBook.addSection("Chapter-"+i, new Resource("chapter"+i, out.toByteArray(), "", new MediaType("html", "html")));
				    i++;
				    
				    wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
				    htmlDocument = wordToHtmlConverter.getDocument();
	    		}
	    	}
	    	else
	    	{
	    		Element ele = htmlDocument.createElement("p");
	    		ele.setTextContent(p.getText());
	    		htmlDocument.getElementsByTagName("body").item(0).appendChild(ele);
	    	}
	    	
	    }
	    
	    //added the below code to ensure that the last paragraph is always captured
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    DOMSource domSource = new DOMSource(htmlDocument);
	    StreamResult streamResult = new StreamResult(out);

	    TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer serializer = tf.newTransformer();
	    serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	    serializer.setOutputProperty(OutputKeys.INDENT, "yes");
	    serializer.setOutputProperty(OutputKeys.METHOD, "html");
	    serializer.transform(domSource, streamResult);
	    out.close();
	    
	    destBook.addSection("Chapter-"+i, new Resource("chapter"+i, out.toByteArray(), "", new MediaType("html", "html")));
	    i++;
	   
	    
	    EpubWriter wr = new EpubWriter();
		String fileToret = "/home/manoj/newtest.epub";
		wr.write(destBook, new FileOutputStream(new File(fileToret)));
		
	}

}
