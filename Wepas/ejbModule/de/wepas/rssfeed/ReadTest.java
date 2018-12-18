package de.wepas.rssfeed;

public class ReadTest
{
	public static void main(String[] args)
	{
//		RSSFeedParser parser = new RSSFeedParser("file:///home/steff/datei.rss");
		RSSFeedParser parser = new RSSFeedParser("http://rss.kicker.de/live/bundesliga");
//		RSSFeedParser parser = new RSSFeedParser("http://rss.kicker.de/live/championsleague");
//		RSSFeedParser parser = new RSSFeedParser("http://rss.kicker.de/live/uefacup");
		Feed feed = parser.readFeed();
		System.out.println(feed);
		for (FeedMessage message : feed.getMessages())
		{
			System.out.println(message);
		}
	}
}
