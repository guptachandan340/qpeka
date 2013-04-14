package com.qpeka.book.converter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.w3c.dom.Document;

public class DocToHtmlConverter {
	
	public static boolean  convert() throws FileNotFoundException, IOException, ParserConfigurationException, TransformerException
	{
		HWPFDocumentCore wordDocument = WordToHtmlUtils.loadDoc(new FileInputStream("/home/manoj/Pride and Prejudice.doc"));
		//InputStream is = new ByteArrayInputStream(str.getBytes());
		String[] arr = wordDocument.getText().toString().split("<Chapter-[0-9]*>");
		Range r = null;
		WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
	            DocumentBuilderFactory.newInstance().newDocumentBuilder()
	                    .newDocument());
		Book destBook = new Book();
		destBook.getMetadata().setLanguage("English");
		List<String> list = new ArrayList<String>();
		list.add("Pride n Prejudice");
		destBook.getMetadata().setTitles(list);
		int start = 0;
		int end = 0;
		boolean toend = false ;
		for(int i = 0 ;!toend;i++)
		{
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
			System.out.println(start + " " + end);
			r = new Range(start, end, wordDocument); 
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
		    
		    destBook.addSection("Chapter-"+i, new Resource("chapter"+i, out.toByteArray(), "", new MediaType("", "")));
		    
		    //System.out.println("Chapter "+i+" = " + result);
		}
		EpubWriter wr = new EpubWriter();
		wr.write(destBook, new FileOutputStream(new File("/home/manoj/pnp.epub")));
	    return true;
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ParserConfigurationException, TransformerException {
		convert();
		
//		HWPFDocumentCore wordDocument = WordToHtmlUtils.loadDoc(new FileInputStream("/home/manoj/Pride and Prejudice.doc"));
//		Range r = new Range(0, wordDocument.getText().toString().indexOf("<Chapter-1>"), wordDocument);
//		System.out.println(r.text());
//		Pattern p = Pattern.compile("<Chapter-[0-9]+>");
//		Matcher m = p.matcher("<Chapter-1> Chapter 1 contents <Chapter-2> Chapter 2 contents");
//		while(m.find())
//		{
//			String pgId = m.group().split("pageid=")[1].split("><")[0];
//			
//			System.out.println(m.group());
//			//BookViewLogHandler.getInstance().updateLog(uid, workId, pgId, Long.parseLong(ts));
//		}
//		
//		
//		String x = "<Chapter-1 name=\"ch1\"> Chapter 1 contents <Chapter-2 name=\"ch2\"> Chapter 2 contents";
//		for(String y : x.split("<Chapter-[0-9]+>", -1))
//		{
//			System.out.println(y);
//		}
//		//{}
//		//Title, publisher, authors,language, desc, 
		
	}

}
