package de.wepas.rssfeed;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;




public class RSSFeedParser
{
	static final String TITLE = "title";
	static final String DESCRIPTION = "description";
	static final String CHANNEL = "channel";
	static final String LANGUAGE = "language";
	static final String COPYRIGHT = "copyright";
	static final String LINK = "link";
	static final String AUTHOR = "author";
	static final String ITEM = "item";
	static final String PUB_DATE = "pubDate";
	static final String GUID = "guid";
	
	final URL url;
	
	public RSSFeedParser(String feedUrl)
	{
		try
		{
			this.url = new URL(feedUrl);
		}
		catch (MalformedURLException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public Feed readFeed()
	{
		Feed feed = null;
		try
		{
			
			boolean isFeedHeader = true;
			// Set header values intial to the empty string
			String description = "";
			String title = "";
			String link = "";
			String language = "";
			String copyright = "";
			String author = "";
			String pubdate = "";
			String guid = "";
			
			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = read();
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// Read the XML document
//			PrintWriter out = null;
//			try
//			{
//				out = new PrintWriter("/home/steff/datei1.txt");
//			}
//			catch(Exception e)
//			{
//				System.out.println(e.getMessage());
//			}
			
			while (eventReader.hasNext())
			{
				XMLEvent event = eventReader.nextEvent();
				
//				out.println(event.toString()); //
				
				if (event.isStartElement())
				{
					if (event.asStartElement().getName().getLocalPart() == (ITEM))
					{
						if (isFeedHeader)
						{
							isFeedHeader = false;
							feed = new Feed(title, link, description, language, copyright, pubdate);
						}
						event = eventReader.nextEvent();
//						out.println(event.toString()); //
						continue;
					}
					
					if (event.asStartElement().getName().getLocalPart() == (TITLE))
					{
						event = eventReader.nextEvent();
						System.out.println(event.toString()); //KOMMENTAR
						title = event.asCharacters().getData();
						continue;
					}
					if (event.asStartElement().getName().getLocalPart() == (DESCRIPTION))
					{
						event = eventReader.nextEvent();
					    System.out.println(event.toString()); //Kommentar
						description = event.asCharacters().getData();
						continue;
					}
					
					if (event.asStartElement().getName().getLocalPart() == (LINK))
					{
						event = eventReader.nextEvent();
						System.out.println(event.toString()); //
						link = event.asCharacters().getData();
						continue;
					}
					
					if (event.asStartElement().getName().getLocalPart() == (GUID))
					{
						event = eventReader.nextEvent();
//						out.println(event.toString()); //
						guid = event.asCharacters().getData();
						continue;
					}
					if (event.asStartElement().getName().getLocalPart() == (LANGUAGE))
					{
						event = eventReader.nextEvent();
//						out.println(event.toString()); //
						language = event.asCharacters().getData();
						continue;
					}
					if (event.asStartElement().getName().getLocalPart() == (AUTHOR))
					{
						event = eventReader.nextEvent();
//						out.println(event.toString()); //
						author = event.asCharacters().getData();
						continue;
					}
					if (event.asStartElement().getName().getLocalPart() == (PUB_DATE))
					{
						event = eventReader.nextEvent();
//						out.println(event.toString()); //
						pubdate = event.asCharacters().getData();
						continue;
					}
					if (event.asStartElement().getName().getLocalPart() == (COPYRIGHT))
					{
						event = eventReader.nextEvent();
//						out.println(event.toString()); //
						copyright = event.asCharacters().getData();
						continue;
					}
				}
				else if (event.isEndElement())
				{
					if (event.asEndElement().getName().getLocalPart() == (ITEM))
					{
						FeedMessage message = new FeedMessage();
						message.setAuthor(author);
						message.setDescription(description);
						message.setGuid(guid);
						message.setLink(link);
						message.setTitle(title);
						feed.getMessages().add(message);
						event = eventReader.nextEvent();
						continue;
					}
				}
				
			}
//			out.close();
		}
		catch (XMLStreamException e)
		{
			throw new RuntimeException(e);
		}
		return feed;
		
	}
	
	private InputStream read()
	{
		try
		{
			return url.openStream();
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
}