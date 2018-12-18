package de.wepas.counter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SpieltagBeanCounter
{
	private static Log log = LogFactory.getLog(SpieltagBeanCounter.class);
	
	private static int count = 0;
	private static int alive = 0;
	
	private SpieltagBeanCounter()
	{
		
	}
	
	public static int construct()
	{
		++alive;
		++count;
		log("ConstructBean Nr. " + count + " (" + alive + " alive)");
		return count;
	}
	
	public static void destroy(int count)
	{
		--alive;
		log("DestroyBean Nr. " + count + " (" + alive + " alive)");
	}
	
	private static void log(String s)
	{
		if (Constants.isLogstateless())
		{
			log.info(s);
		}
	}
	
}
