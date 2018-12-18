package form;

import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sorts.SortVectorTipperLaenderranking;
import tables.LaenderTable;
import util.LookupRemoteService;
import de.wepas.connector.EinstellungBeanRemote;
import de.wepas.connector.EinstellungException;
import de.wepas.connector.TipperBeanRemote;
import de.wepas.connector.TipperException;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnEinstellung;
import de.wepas.jpa.EnTipper;

public class LaenderrankingForm
{
	private static Log log = LogFactory.getLog(LaenderrankingForm.class);
	
	private boolean ButtonNavigation;
	private TipperBeanRemote tipperStateless = null;
	private EinstellungBeanRemote einstellungStateless = null;
	
	public LaenderrankingForm(HttpServletRequest req) throws TipperException
	{
		log.debug("Entering " + this.getClass().getName());
		this.ButtonNavigation = req.getParameter("navigation") != null;
		
		if (this.ButtonNavigation)
		{
			return;
		}
		this.getResources();
		
		EnEinstellung[] einstellungarray = null;
		try
		{
			einstellungarray = this.einstellungStateless.getEinstellung("land");
		}
		catch (EinstellungException e)
		{
			e.printStackTrace();
			log.error("EinstellungException Zeile 91 " + e.getMessage());
		}
		Vector<EnEinstellung> teams = new Vector<EnEinstellung>(einstellungarray.length);
		for (int i = 0; i < einstellungarray.length; i++)
		{
			teams.add(einstellungarray[i]);
		}
		
		Vector<Vector<EnTipper>> teamvectoren = new Vector<Vector<EnTipper>>(teams.size());
		Iterator<EnEinstellung> iter1 = teams.iterator();
		while (iter1.hasNext())
		{
			EnEinstellung einstellung = iter1.next();
			EnTipper[] tipperarray = this.tipperStateless.getTeamTipper(einstellung.getAtEinstellungWertCha10().substring(0, 2));
			Vector<EnTipper> teamtippers = new Vector<EnTipper>(tipperarray.length);
			for (int i = 0; i < tipperarray.length; i++)
			{
				teamtippers.add(tipperarray[i]);
			}
			teamvectoren.add(teamtippers);
		}
		Collections.sort(teamvectoren, new SortVectorTipperLaenderranking());
		req.getSession().setAttribute(ApplConstants.LAENDERTABLE, new LaenderTable(teamvectoren));
	}
	
	private void getResources()
	{
		this.einstellungStateless = LookupRemoteService.lookupEinstellungBeanRemote();
		this.tipperStateless = LookupRemoteService.lookupTipperBeanRemote();
	}
	
	public boolean isButtonNavigation()
	{
		return ButtonNavigation;
	}
	
}
