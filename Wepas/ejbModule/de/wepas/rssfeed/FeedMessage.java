package de.wepas.rssfeed;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*
 * Represents one RSS message
 */

public class FeedMessage
{
	String title;
	String description;
	String link;
	String author;
	String guid;
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		if(!title.matches("(.*XXX.*)"))
		{
			this.title = title;
			return;
		}
		File file = new File("/home/steff/workspace/Wepas/title.txt");
		FileReader fr = null;
		int c;
		StringBuffer buff = new StringBuffer();
		try
		{
			fr = new FileReader(file);
			while ((c = fr.read()) != -1)
			{
				buff.append((char) c);
			}
			fr.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		this.title = buff.toString();
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getLink()
	{
		return link;
	}
	
	public void setLink(String link)
	{
		this.link = link;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	public void setAuthor(String author)
	{
		this.author = author;
	}
	
	public String getGuid()
	{
		return guid;
	}
	
	public void setGuid(String guid)
	{
		this.guid = guid;
	}
	
	@Override
	public String toString()
	{
		return "FeedMessage \n[\n title = " + title + "\n description = " + description + "\n link = " + link + "\n author = " + author + "\n guid = " + guid
				+ "\n]";
	}
}
