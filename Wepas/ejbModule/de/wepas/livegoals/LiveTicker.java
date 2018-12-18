package de.wepas.livegoals;

import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.wepas.connector.EinstellungBeanLocal;
import de.wepas.connector.EinstellungException;
import de.wepas.connector.SpielBeanLocal;
import de.wepas.connector.SpielException;
import de.wepas.connector.TippBeanLocal;
import de.wepas.connector.TippException;
import de.wepas.connector.TipperBeanLocal;
import de.wepas.connector.TipperException;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnTipp;
import de.wepas.jpa.EnTipper;
import de.wepas.rssfeed.Feed;
import de.wepas.rssfeed.FeedMessage;
import de.wepas.rssfeed.RSSFeedParser;
import de.wepas.util.HtmlTags;
import de.wepas.util.LookupLocalService;
import de.wepas.worker.ErgebnispflegeBeanLocal;
import de.wepas.worker.ErgebnispflegeException;

/*
 * Dieses Objekt wird beim Timeout der LivegoalsBean erzeugt. Es wird der RSS-Feed
 * abgearbeitet und auf Änderungen untersucht. Bei Spielstandänderungen wird das LivegoalsObject
 * geholt und gepflegt. Bei Spielende wird die Tipppflege angestossen.
 */

public class LiveTicker
{
	private static Log log = LogFactory.getLog(LiveTicker.class);
	private static String csstable1 = ApplConstants.CSSTABLE1;
	
	private LivegoalsBeanLocal lgl = null;
	
	public LiveTicker()
	{
		super();
		log.debug("-->Constructing " + this.getClass().getName());
		
		this.lgl = LookupLocalService.lookupLivegoalsBeanLocal();
		LivegoalsObject lgo = lgl.getLivegoalsObject();
		
		EinstellungBeanLocal einstellungStateless = LookupLocalService.lookupEinstellungBeanLocal();
		RSSFeedParser parser = null;
		
		try
		{
			System.out.println("--->" + einstellungStateless.getEinstellung(50).getAtEinstellungWertCha30());
			parser = new RSSFeedParser(einstellungStateless.getEinstellung(50).getAtEinstellungWertCha30());
		}
		catch (EinstellungException e1)
		{
			log.error("rssfeed aus einstellungstabelle holen klappte nicht");
			e1.printStackTrace();
		}
		//TODO --> hier bitte noch die fest verdrahte id (54) variablisieren
		Feed feed = parser.readFeed();
		if (lgo != null)
		{
			for (FeedMessage message : feed.getMessages())
			{
				try
				{
					Iterator<Livespiel> i1 = lgo.getLivespiele().iterator();
					while (i1.hasNext())
					{
						Livespiel lg = i1.next();
						if (message.getTitle().matches(lg.getHeim().getAtVereinRegex()) && message.getTitle().matches(lg.getGast().getAtVereinRegex()))
						{
							this.found(message, lg);
						}
					}
				}
				catch (NullPointerException e)
				{
					log.info("--> LivegoalsObject nicht vorhanden!");
				}
			}
		}
		
		if(this.lgl.getLivegoalsObject().getHtmltabelleaktuell() == null)
		{
			this.updateLivegoalsTable();
		}
	}
	
	private void found(FeedMessage message, Livespiel lg)
	{
//log.info(message.getTitle());
		lg.setTitle(message.getTitle());
		lg.setLastgoal(false);
		if (message.getTitle().matches("(.*[0-2][0-9]:[0-5]0 Uhr.*)"))
		{
			if (lg.getStatus() == LivestatusEnum.UNKNOWN)
			{
				log.info("Angesetzt:    " + lg.getHeim().getAtVereinName() + "-" + lg.getGast().getAtVereinName());
				lg.setStatus(LivestatusEnum.SETUP);
			}
		}
		if (message.getTitle().matches("(.*Live!.*)") && !message.getTitle().matches(".*Spielende.*"))
		{
			
			int h = 0;
			int g = 0;
			int i = 0;
			
			if(lg.getStatus() == LivestatusEnum.SETUP || lg.getStatus() == LivestatusEnum.UNKNOWN)
			{
				this.changed(message, lg, h, g);
			}
			
			if (message.getTitle().matches(".*2. Halbzeit.*"))
			{
				i = message.getTitle().indexOf(":");
			}
			else if (message.getTitle().matches(".*1. Halbzeit.*"))
			{
				i = message.getTitle().indexOf("(");
				i = i + 2;
			}
			else
			{
				return;
			}
			h = Integer.parseInt(message.getTitle().substring(i - 1, i));
			g = Integer.parseInt(message.getTitle().substring(i + 1, i + 2));
			if (lg.getStatus() == LivestatusEnum.UNKNOWN)
			{
				log.info("Läuft:        " + lg.getHeim().getAtVereinName() + "-" + lg.getGast().getAtVereinName() + " (" + lg.getSpiel().getAtSpielToreHeim()
						+ ":" + lg.getSpiel().getAtSpielToreGast() + ")");
				lg.setStatus(LivestatusEnum.RUNNING);
				lg.setNowToreheim(h);
				lg.setNowToregast(g);
				this.changed(message, lg, h, g);
			}
			if (lg.getStatus() == LivestatusEnum.SETUP)
			{
				log.info("Läuft:        " + lg.getHeim().getAtVereinName() + "-" + lg.getGast().getAtVereinName() + " (" + lg.getSpiel().getAtSpielToreHeim()
						+ ":" + lg.getSpiel().getAtSpielToreGast() + ")");
				lg.setStatus(LivestatusEnum.RUNNING);
				lg.setNowToreheim(h);
				lg.setNowToregast(g);
				this.changed(message, lg, h, g);
			}
			if (lg.getNowToreheim() != h || lg.getNowToregast() != g)
			{
				this.changed(message, lg, h, g);
			}
		}
		else if (message.getTitle().matches("(.* 0:0 .*)|(.* [0-9]:[0-9] .*)"))
		{
			if (lg.getStatus() == LivestatusEnum.UNKNOWN)
			{
				log.info("Beendet:      " + lg.getHeim().getAtVereinName() + "-" + lg.getGast().getAtVereinName() + " (" + lg.getNowToreheim()
						+ ":" + lg.getNowToregast() + ")");
				lg.setStatus(LivestatusEnum.FINISHED);
			}
			if (lg.getStatus() == LivestatusEnum.RUNNING)
			{
				this.maintenance(message, lg);
			}
		}
	}
	
	private void changed(FeedMessage message, Livespiel lg, int h, int g)
	{
		log.debug("-->Entering " + this.getClass().getName());
		Livespiel lgclone = (Livespiel) lg.clone();
		this.lgl.getLivegoalsObject().addLivehistorie(lgclone);

		if(h > 0 || g > 0)
		{
			log.info("-->Stand:   " + lg.getHeim().getAtVereinName() + " - " + lg.getGast().getAtVereinName() 
				+ "-> Alt: " + lg.getNowToreheim() + ":" + lg.getNowToregast() + "-> Neu: " + h + ":" + g);
		}
		lg.setNowToreheim(h);
		lg.setNowToregast(g);
		lg.setLastgoal(true);
		
		this.updateLivegoalsTable();
	}
	
	private void maintenance(FeedMessage message, Livespiel lg)
	{
		log.info("Maintenance:  " + lg.getHeim().getAtVereinName() + " - " + lg.getGast().getAtVereinName());
		int i = message.getTitle().indexOf(":");
		int h = Integer.parseInt(message.getTitle().substring(i - 1, i));
		int g = Integer.parseInt(message.getTitle().substring(i + 1, i + 2));
		EnSpiel spiel = lg.getSpiel();
		SpielBeanLocal sbl = LookupLocalService.lookupSpielBeanLocal();
		// Das Spiel nochmal aktuell holen wg. Prüfung, ob es anderweitig gepflegt wurde
		try
		{
			spiel = sbl.getSpiel(spiel.getIdSpiel());
		}
		catch (SpielException e)
		{
			e.printStackTrace();
		}
		if (spiel.getAtSpielIsPlayed())
		{
			log.info("Spiel wurde schon gepflegt");
		}
		else
		{
			spiel.setAtSpielToreHeim(h);
			spiel.setAtSpielToreGast(g);
			ErgebnispflegeBeanLocal epl = LookupLocalService.lookupErgebnispflegeBeanLocal();
			try
			{
				log.info(epl.maintenanceResult(spiel));
			}
			catch (ErgebnispflegeException e)
			{
				e.printStackTrace();
				log.error(e.getMessage());
			}
			lg.setStatus(LivestatusEnum.FINISHED);
			this.updateLivegoalsTable();
		}
	}
	
	private void updateLivegoalsTable()
	{
		TipperBeanLocal trbl = LookupLocalService.lookupTipperBeanLocal();
		TippBeanLocal tpbl = LookupLocalService.lookupTippBeanLocal();
		
		EnTipper[] tippersarray = null;
		try
		{
			tippersarray = trbl.getTipper();
		}
		catch (TipperException e)
		{
			e.printStackTrace();
		}
		LivegoalsTable lgtable = new LivegoalsTable(this.lgl.getLivegoalsObject().getLivespiele());
		for(int i = 0; i < tippersarray.length; i++)
		{
			Vector<EnTipp> tipps = null;
			EnTipp[] tipparray = null;
			try
			{
				tipparray = tpbl.getTipps(tippersarray[i], lgl.getLivegoalsObject().getSpieltag());
			}
			catch (TippException e)
			{
				e.printStackTrace();
			}
			tipps = new Vector<EnTipp>(tipparray.length);
			for(int j = 0; j < tipparray.length; j++)
			{
				tipps.add(tipparray[j]);
			}
			lgtable.getRows().add(new LivegoalsTablerow(tippersarray[i], tipps, lgl.getLivegoalsObject().getLivespiele()));
		}
		lgtable.sortTipperPunkteSpieltag();
		
		StringBuffer sb = new StringBuffer("");
		sb.append(lgtable.getHead());
		Iterator<LivegoalsTablerow> iter1 = lgtable.getRows().iterator();
		while(iter1.hasNext())
		{	
			sb.append(iter1.next().getHtmlRow());
		}
		this.lgl.getLivegoalsObject().setHtmltabelleaktuell(HtmlTags.wrapTABLE(sb.toString(), csstable1));
	}
	
}
