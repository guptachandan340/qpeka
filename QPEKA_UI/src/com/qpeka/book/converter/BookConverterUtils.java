package com.qpeka.book.converter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author manoj
 * 
 * Utility to convert any format to qpeka compliant format
 * 1) Doc  --> Qpeka
 * 2) Docx --> Qpeka
 *
 */
public class BookConverterUtils {
	
	public static int convertDOCToQPEKA(String baseDestDir, String srcFile, String destFilePrefix) throws FileNotFoundException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException 
	{

		int fileIndex = 1;
		
		File fTemp = null;
		Writer bw = null;
		
		HWPFDocument hwpfDocument = new HWPFDocument(new FileInputStream(srcFile));
		WordExtractor we = new WordExtractor(hwpfDocument);
		
		String[] arr = null;
		int pageNum = 1;
		int pageSize = 0;
		int lines = 0;
		JSONObject pagesJson = new JSONObject();
		StringBuffer pageText = new StringBuffer(); 
		boolean isChapterEnd = false;
		for(String paragraphText: we.getParagraphText())
		{
			paragraphText = paragraphText.trim();
			
			if(paragraphText.startsWith("---"))
			{
				System.out.println("paragraphText=" + paragraphText);
				isChapterEnd = true;
			}
			
			if(paragraphText.length() == 0)
			{ 
				if(lines > 0)
					pageText.append("<br>");
				continue;
			}
			
			arr = paragraphText.split("\\s");
 			
			for(int k = 0 ; k < arr.length ; k ++ )
			{
				if(!isChapterEnd)
				{
					pageText.append(arr[k] + " ");
					pageSize++;
				}
				
				if(pageSize > 150 || lines >= 12 || isChapterEnd) //
				{
					pageSize = 0; 
					lines = 0;
					
//					if(!pageText.toString().endsWith("."))
//						pageText.append(".......");
//					
					try 
					{
						System.out.println("Creating page " + pageNum);
						pagesJson.put(""+pageNum, pageText.toString());
						pageText = new StringBuffer();
						if(isChapterEnd)
						{
							pageText.append(paragraphText);
							k++;
							isChapterEnd = false;
						}
					}
					catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if(pageNum % 100 == 0)
					{
						try
						{
							fTemp = new File(baseDestDir + destFilePrefix + "-" + fileIndex);
							if(!fTemp.exists())
								fTemp.createNewFile();
							
							bw = new BufferedWriter(new FileWriter(fTemp));
							bw.write(pagesJson.toString());
						
						}
						catch (Exception e) {
							e.printStackTrace();
						}
						finally
						{
							bw.close();
						}
						
						fileIndex++;
						
						fTemp = new File(baseDestDir + destFilePrefix + "-" + fileIndex);
						
						if(!fTemp.exists())
							fTemp.createNewFile();
						
						bw = new BufferedWriter(new FileWriter(fTemp));
						pagesJson = new JSONObject();
						pageText = new StringBuffer();
						
					}
					pageNum++;
					
				}
				
			}
			
			pageText.append("<br>");
			lines++;
		
		}
		
		try 
		{
			System.out.println("Creating page " + pageNum);
			pagesJson.put(""+pageNum, pageText.toString());
		}
		catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(fTemp == null || bw == null)
		{
			fTemp = new File(baseDestDir + destFilePrefix + "-" + fileIndex);
			if(!fTemp.exists())
				fTemp.createNewFile();
			
			bw = new BufferedWriter(new FileWriter(fTemp));
		}
		
		bw.write(pagesJson.toString());
		bw.close();

		return pageNum;
	}
	
	public static void convertFromDOCXToQPEKA(String baseDestDir, String srcFile, String destFilePrefix) throws FileNotFoundException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException 
	{

		int fileIndex = 1;
		File fTemp = null;
		Writer bw = null;
		
		XWPFDocument hwpfDocument = new XWPFDocument(new FileInputStream(srcFile));
		XWPFWordExtractor we = new XWPFWordExtractor(hwpfDocument);
		
		String[] arr = null;
		int pageNum = 1;
		int pageSize = 0;
		int line = 0;
		JSONObject pagesJson = new JSONObject();
		StringBuffer pageText = new StringBuffer(); 
		
		String paragraphText = we.getText();
		
		arr = paragraphText.split("\\s");
 			
		for(int k = 0 ; k < arr.length ; k ++ )
		{
			pageText.append(arr[k] + " ");
			pageSize++;
				
			if(pageSize > 150 || line >= 12 )
			{
				line = 0;
				pageSize = 0; 
					
				if(!pageText.toString().endsWith("."))
					pageText.append(".......");
					
				try 
				{
					System.out.println("Creating page " + pageNum);
					pagesJson.put(""+pageNum, pageText.toString());
					pageText = new StringBuffer();
				}
				catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				if(pageNum % 100 == 0)
				{
					try
					{
						fTemp = new File(baseDestDir + destFilePrefix + "-" + fileIndex);
						if(!fTemp.exists())
							fTemp.createNewFile();
						
						bw = new BufferedWriter(new FileWriter(fTemp));
						bw.write(pagesJson.toString());
					
					}
					catch (Exception e) {
						e.printStackTrace();
					}
					finally
					{
						bw.close();
					}
						
					fileIndex++;
						
					fTemp = new File(baseDestDir + destFilePrefix + "-" + fileIndex);
					
					if(!fTemp.exists())
						fTemp.createNewFile();
						
					bw = new BufferedWriter(new FileWriter(fTemp));
					pagesJson = new JSONObject();
					pageText = new StringBuffer();
						
				}
				pageNum++;
					
			}
				
		}
			
		pageText.append("<br>");
		line++;
			
		try 
		{
			System.out.println("Creating page " + pageNum);
			pagesJson.put(""+pageNum, pageText.toString());
		}
		catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(fTemp == null || bw == null)
		{
			fTemp = new File(baseDestDir + destFilePrefix + "-" + fileIndex);
			if(!fTemp.exists())
				fTemp.createNewFile();
			
			bw = new BufferedWriter(new FileWriter(fTemp));
		}
		
		bw.write(pagesJson.toString());
		bw.close();

	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		
		System.out.println(convertDOCToQPEKA("/tmp/converted/", "/home/manoj/hindi.doc", "pnp-"));
		
	}
}