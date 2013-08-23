package com.qpeka.book.converter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hwpf.HWPFDocumentCore;
import org.apache.poi.hwpf.converter.WordToHtmlUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.json.JSONException;
import org.json.JSONObject;

public class FileValidationUtil {
	
	public static JSONObject validateDoc(File file)
	{
		JSONObject jo = new JSONObject();
		
		try {
			
			HWPFDocumentCore wordDocument = WordToHtmlUtils.loadDoc(new FileInputStream(file));
			String text = wordDocument.getText().toString();
			if(!text.contains("<Chapter-0>"))
			{
				jo.put("error", "Cannot find initial chapter");
				jo.put("succes",false);
			}
			else
			{
				int chapters = text.split("<Chapter-[0-9]*>").length - 1;
				int words = text.split(" ").length/200;
				if(chapters > 0)
				{
					jo.put("succes",true);
				}
				else
				{
					jo.put("error", "Insufficient number of chapters");
					jo.put("succes",false);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				jo.put("error", e.getMessage());
				jo.put("succes",false);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
		return jo;
	}
	
	private static JSONObject validateDocx(File f)
	{
		JSONObject jo = new JSONObject();
		
		XWPFDocument wordDocument = null;
		
	    try
	    {
	        wordDocument = new XWPFDocument(new FileInputStream(f));
	        int count = 0;
	        int paras = 0;
	        if(!wordDocument.getParagraphs().get(0).getText().contains("<Chapter-0>"))
	        {
	        	jo.put("error", "Cannot find initial chapter");
				jo.put("succes",false);
	        }
	        else
	        {
	        	for(XWPFParagraph para : wordDocument.getParagraphs())
	        	{
	        		if(para.getText().contains("<Chapter-"))
	        			paras++;
	        		else
	        			count = count + para.getText().split(" ").length;
	        	}
	        	paras = paras-1;
	        	count = count/200;
	        	if(paras > 1)
	        	{
					jo.put("succes",true);
				}
				else
				{
					jo.put("error", "Insufficient number of chapters");
					jo.put("succes",false);
				}	
	        }
	        
	        
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        try
	        {
		        jo.put("success", false);
		        jo.put("error", ex.getMessage());
	        }
	        catch (Exception e) {
				// TODO: handle exception
			}
	    }
	    return jo;
	}
	
	public static void main(String[] args) {
		System.out.println(validateDocx(new File("/home/manoj/test.docx")));
	}
}
