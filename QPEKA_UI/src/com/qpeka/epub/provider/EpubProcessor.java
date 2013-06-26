package com.qpeka.epub.provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubReader;
import nl.siegmann.epublib.epub.EpubWriter;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Tag;

public class EpubProcessor {
	
	private static final int PAGE_COUNT = 200;
	public static void processEpub(String bookPath, String destPath) throws FileNotFoundException, IOException{
		EpubReader reader =  new EpubReader();
		Book b = reader.readEpub(new FileInputStream(new File(bookPath)));
		String content = "";
		int pagecount = 1;
		int tempCounter;
		Count cnt = new Count(0, 0);
		for(Resource res : b.getContents())
		{	
			content = new String(res.getData());
			Document doc = Jsoup.parse(content, "UTF-8");
			Element ele = doc.body();
			Count cTemp = modify(ele, cnt);
			cnt.setCount(cTemp.getCount());
			cnt.setPgCount(cTemp.getPgCount());
			doc.body().html(ele.html());
			res.setData(doc.html().getBytes());
//			List<Node> nodes = ele.childNodes();
//			for(Node n : nodes)
//			{
//				System.out.println(n.outerHtml());
//			}
		}
		EpubWriter wr = new EpubWriter();
		wr.write(b, new FileOutputStream(new File(destPath)));
		
	}
	
	private static Count modify(Element e, Count c)
    {
    	List<Node> o = e.childNodes();
    	if(o.size() == 0  && e.textNodes().size() == 0) return new Count(c.getCount(), c.getPgCount()); 	
    	for(Node n : o)
    	{
    		if(n instanceof TextNode)
    		{
    			TextNode nd = (TextNode)n;
    			String[] arr = nd.text().trim().split("\\s");
        		String txt = "";
        		for(int i = 0 ; i < arr.length ; i++)
        		{
        			if(arr[i].length() > 0)
        				c.incrementCount();
        			if(c.getCount() > PAGE_COUNT)
        			{
        				//"<!--page id="+c.getPgCount()+ "--!>" + " " +  arr[i]);
        				txt = txt + " " + "<!--page id="+c.getPgCount()+ "--!>"  + " " + arr[i]; //<div style='visibility:hidden'>Page="+pageCount+"</div>
        				c.incrementPgCount();
        				c.setCount(0);
        				
        			}
        			else{
        				txt = txt + " " + arr[i];
        			}
        		}
        		
        		nd.text(txt);
        		
        		
    		}
    		else if(n instanceof Element)
    		{
    			Count ctemp = modify((Element)n, c);
    			c.setCount( ctemp.getCount());
    			c.setPgCount(ctemp.getPgCount());
    		}
    	}
    	
    	return c;
    }
	
	private static class Count{
		private int count = 0;
		private int pgCount = 0;
	
		public Count(){}
		
		public Count(int count, int pgCount) {
			super();
			this.count = count;
			this.pgCount = pgCount;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public int getPgCount() {
			return pgCount;
		}
		public void setPgCount(int pgCount) {
			this.pgCount = pgCount;
		}
		public void incrementPgCount(){
			pgCount++;
		}
		public void incrementCount(){
			count++;
		}
		
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
			
//		String html = "<html><head><title></title><head>"+
//	"<body><p> “But how?”</p>"+
//" <p> “Through the skylight. We shall soon see how he managed it.” He swung himself up onto the roof. “Ah, yes,” he cried, “here’s the end of a long light ladder against the eaves. That is how he did it.”</p> "+
//" <p> “But it is impossible,” said Miss Hunter; “the ladder was not there when the Rucastles went away.”</p> "+
//" <p> “He has come back and done it. I tell you that he is a clever and dangerous man. I should not be very much surprised if this were he whose step I hear now upon the stair. I think, Watson, that it would be as well for you to have your pistol ready.”</p> "+
//" <p> The words were hardly out of his mouth before a man appeared at the door of the room, a very fat and burly man, with a heavy stick in his hand. Miss Hunter screamed and shrunk against the wall at the sight of him, but Sherlock Holmes sprang forward and confronted him.</p>"+ 
//" <p> “You villain!” said he, “where’s your daughter?”</p> "+
//" <p> The fat man cast his eyes round, and then up at the open skylight.</p> "+
//" <p> “It is for me to ask you that,” he shrieked, “you thieves! Spies and thieves! I have caught you, have I? You are in my power. I’ll serve you!” He turned and clattered down the stairs as hard as he could go.</p> "+
//" <p> “He’s gone for the dog!” cried Miss Hunter.</p> "+
//" <p> “I have my revolver,” said I.</p> </body></html>";
//		Elements ele = Jsoup.parse(html).getElementsByTag("body");
//		
//		System.out.println(modify(ele.get(0),new Count(0,0)));
//		
//		System.out.println(ele.toString());
		//processEpub("/home/manoj/Emma_Jane-Austen.epub");
//		Node n = new TextNode("<page id=23>", "");
//		System.out.println(StringEscapeUtils.unescapeHtml("&lt;page&gt;"));
		//Tag t = Tag.valueOf("<page>");
		//System.out.println(t);
	}
}
