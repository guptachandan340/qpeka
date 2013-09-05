package com.qpeka.epub.provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.MediaType;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubReader;
import nl.siegmann.epublib.epub.EpubWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Tag;

public class EpubProcessorNew {
	
	private static final int PAGE_COUNT = 200;
	
	public static void processEpub(String bookPath,String dest) throws FileNotFoundException, IOException{
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
			//http-equiv=\"content-type\" content=\"text/html; charset=utf-8\"");
			Element elem = new Element(Tag.valueOf("meta"), "");
			elem.attr("http-equiv","content-type");
			elem.attr("content","text/html; charset=utf-8");
			doc.head().after(elem);
			System.out.println(doc.head().data());
			Element ele = doc.body();
			alterElement(ele);
			Count cTemp = modify(ele, cnt);
			cnt.setCount(cTemp.getCount());
			cnt.setPgCount(cTemp.getPgCount());
			doc.body().html(ele.html());
			res.setData(doc.html().getBytes());
			if(res.getMediaType() == null)
				res.setMediaType(new MediaType("html", "html"));

		}
		EpubWriter wr = new EpubWriter();
		wr.write(b, new FileOutputStream(new File(dest)));
		
	}
	
	private static void alterElement(Element e)
	{
		org.jsoup.select.Elements s = e.children();
		Iterator<Element> ele = s.iterator();
		int i = 0;
		while(ele.hasNext())
		{
			Element r = ele.next();
		
			if(!r.tag().getName().equals("p"))
			{
				r.tagName("p");//plain replace
//				Element rtemp = r.clone();
//				Element ep = new Element(Tag.valueOf("p"), "");
//				ep.appendChild(rtemp);
//				r.replaceWith(ep);
//				StringBuffer bf = new StringBuffer();
//				bf.append("<k>").append(r.toString()).append("</k>");
//				r.html(bf.toString());
//				System.out.println(r.tagName());
				
			}
			i++;
		}
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
        		List<Node> nodes = new ArrayList<Node>();
        		int j = 0;
        		TextNode ndTemp = new TextNode("", " ");
        		nodes.add(j, ndTemp);
        		for(int i = 0 ; i < arr.length ; i++)
        		{
        			if(arr[i].length() > 0)
        				c.incrementCount();
        			if(c.getCount() > PAGE_COUNT)
        			{
        				((TextNode)nodes.get(j)).text(((TextNode)nodes.get(j)).text() + " ");
        				j++;
        				nodes.add(j, new Element(Tag.valueOf("pageid="+c.getPgCount()),""));
        				j++;
        				nodes.add(j, new TextNode(" " + arr[i] + " ", ""));
        				//"<!--page id="+c.getPgCount()+ "--!>" + " " +  arr[i]);
        				//txt = txt + " " + "<!--page id="+c.getPgCount()+ "--!>"  + " " + arr[i]; //<div style='visibility:hidden'>Page="+pageCount+"</div>
        				c.incrementPgCount();
        				c.setCount(0);
        				
        			}
        			else{
        				//txt = txt + " " + arr[i];
        				((TextNode)nodes.get(j)).text(((TextNode)nodes.get(j)).text() + " " +  arr[i]);
        			}
        		}
        		if(nodes.size() > 1)
        		{
        			Element etemp = new Element(Tag.valueOf("span"), "");
        			nd.replaceWith(etemp);
        			for(Node d: nodes)
        			{
        				etemp.appendChild(d);
        			}
        		}
        		//nd.text(ndTemp.text());
        		
        		
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
	//	processEpub("/home/manoj/Emma_Jane-Austen.epub");
//		Node n = new TextNode("<page id=23>", "");
//		System.out.println(StringEscapeUtils.unescapeHtml("&lt;page&gt;"));
		//Tag t = Tag.valueOf("<page>");
		//System.out.println(t);
		processEpub("/home/rahul/testCode/hindi.epub","/home/rahul/testCode/hindi-conv.epub");
//		String[] a = {"br","","","",""};
//		EpubReader reader =  new EpubReader();
//		Book b = reader.readEpub(new FileInputStream(new File("/home/manoj/5180cc95d35d399cb58da7d8.2.epub")));
//		String ret = "";
//		for(String x :  b.getResources().getResourceMap().keySet())
//		{
//			Resource res = b.getResources().getResourceMap().get(x);
//			if(res.getHref().contains("@public@vhost@g@gutenberg@html@files@580@580-h@580-h-0.htm.html"))
//			{
//				 ret = new String(res.getData());
//			}
//		}
//		
//		Document doc = Jsoup.parse(ret, "UTF-8");
//		org.jsoup.select.Elements s = doc.body().children();
//		org.jsoup.select.Elements newElems = new Elements();
//		Iterator<Element> ele = s.iterator();
//		while(ele.hasNext())
//		{
//			Element r = ele.next();
//			
//			if(!r.tag().getName().equals("p"))
//			{
//				StringBuffer htm = new StringBuffer("<p>");
//				htm.append(r.html());
//				htm.append("</p>");
//				r.html(htm.toString());
//				//System.out.println("[MANOJ] = " + htm);
//				newElems.add(r);
//			}
//			else
//			{
//				newElems.add(r);
//			}
//		}
//		
//		ele = newElems.iterator();
//		while(ele.hasNext())
//		{
//			Element r = ele.next();
//			
//		//	System.out.println(r.tag().getName());
//			
//		}
		
		//System.out.println(newElems.html());
	}
}
