package de.wepas.counter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.wepas.connector.TippabgabeStatefulBean;

public class TippabgabeStatefulBeanCounter
{
	private static Log log = LogFactory.getLog(TippabgabeStatefulBeanCounter.class);
	
	private static int count = 0;
	private static int alive = 0;
	
	private TippabgabeStatefulBeanCounter()
	{
		
	}
	
	public static int construct(TippabgabeStatefulBean bean)
	{
		++alive; 
		++count;
		log("ConstructBean Nr. " + count + " (" + alive + " alive)");
		return count;
	}
	
	public static void destroy(int count, String s)
	{
		--alive;
		log("DestroyBean Nr. " + count + " (" + alive + " alive) von " + s);
	}
	
	public static void passivate(int count, TippabgabeStatefulBean bean)
	{
		log("PassivateBean Nr. " + count + " von " + bean.getTipper().getAtTipperVorname() + " " + bean.getTipper().getAtTipperName());
	}	
	
	public static void activate(int count, TippabgabeStatefulBean bean)
	{
		log("ActivateBean Nr. " + count + " von " + bean.getTipper().getAtTipperVorname() + " " + bean.getTipper().getAtTipperName());
	}
	
	private static void log(String s)
	{
		if(Constants.isLogstateful())
		{
			log.info(s);
		}
	}

}
