package de.wepas.livegoals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.wepas.connector.SpielBeanLocal;
import de.wepas.connector.SpielException;
import de.wepas.connector.SpieltagBeanLocal;
import de.wepas.connector.SpieltagException;
import de.wepas.jpa.EnSpiel;
import de.wepas.util.LookupLocalService;

/*
 * Diese Bean ist genau einmal verfügbar. Per Timer wird Freitags ein LivegoalsObject 
 * angelegt und gespeichert. Sonntags wird das dann wieder destroyed.
 */

@Singleton
@LocalBean
public class LivegoalsBean implements LivegoalsBeanRemote, LivegoalsBeanLocal
{
	@Resource
	TimerService timerService;
	
	private static Log log = LogFactory.getLog(LivegoalsBean.class);
	private LivegoalsObject lgo = null;
	private int i = 0;
	
	public static final String KOMMANDO = "HSP";
	
	public void startLivegoals()
	{
		log.info("-->Start Livegoals in 10 sek, alle 1 min");
		timerService.createTimer(10 * 1000, 1 * 60 * 1000, KOMMANDO);
	}
	
	@Override
	public void saveTESTData()
	{
		log.info("-->Ich wurde ausgelöst :-)");
//		log.info("-->saveTESTData");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try
		{
			fos = new FileOutputStream("/opt/jboss-as-7.1.1.Final/standalone/log/lgo" + ++i + ".sav");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(this.lgo);
		}
		catch (FileNotFoundException e)
		{
			try
			{
				fos = new FileOutputStream("/home/steff/lgo" + ++i + ".sav");
				oos = new ObjectOutputStream(fos);
				oos.writeObject(this.lgo);
			}
			catch (FileNotFoundException e1)
			{
					e1.printStackTrace();
			}
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void loadTESTData()
	{
		log.info("-->loadTESTData");
		FileInputStream fis= null;
		ObjectInputStream ois = null;
		try
		{
			fis = new FileInputStream("/home/steff/lgo.sav");
			ois = new ObjectInputStream(fis);
			this.lgo = (LivegoalsObject) ois.readObject();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void insertTESTData()
	{
		log.info("-->insertTESTData");
		SpieltagBeanLocal stbl = LookupLocalService.lookupSpieltagBeanLocal();
		SpielBeanLocal spbl = LookupLocalService.lookupSpielBeanLocal();
		EnSpiel[] spielarray = null;
		try
		{
			spielarray = spbl.getSpiele(stbl.getNextSpieltag());
		}
		catch (SpielException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SpieltagException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Vector<Livespiel> livegames = new Vector<Livespiel>(9);
		for(int i = 0; i < spielarray.length; i++)
		{
			livegames.add(new Livespiel(spielarray[i]));
		}
	}
	
	public void stopLivegoals()
	{
		log.info("--> Stop Livegoals");
		for(Object obj : timerService.getTimers())
		{
			Timer timer = (Timer) obj;
			String bez = (String) timer.getInfo();
			if (bez.equals(KOMMANDO))
			{
				timer.cancel();
			}
		}
	}
	
	@Override
	public void createLivegoalObject()
	{
		log.info("--> CreateLivegoalObject()");
		lgo = new LivegoalsObject();
		log.info(lgo.toString());
	}

	@Override
	public void destroyLivegoalObject()
	{
		lgo.toString();
		log.info("--> Destroy - " + lgo.toString());
		lgo = null;
	}

	@Override
	public LivegoalsObject getLivegoalsObject()
	{
		return lgo; 
	}
	
	@Timeout
	public void timeout(Timer timer)
	{
		new LiveTicker();
	}

}
