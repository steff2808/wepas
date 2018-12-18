package de.wepas.livegoals;

import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.wepas.connector.VereinBeanLocal;
import de.wepas.connector.VereinException;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnVerein;
import de.wepas.sort.SortLiveTablePunkteSpieltag;
import de.wepas.util.HtmlTags;
import de.wepas.util.LookupLocalService;

/*
 * Dies ist ein temporäres Objekt und lebt nur während Ausführung der changed-Methode
 * im LiveTicker. Aufgabe ist es eine sortierte Tabelle zu basteln, die zum Zeitpunkt
 * der Spieländerung aktuell ist.
 */

public class LivegoalsTable
{
	private static Log log = LogFactory.getLog(LivegoalsTable.class);
	
	String cssweiss = ApplConstants.CSSWEISSL;
	String csshell = ApplConstants.CSSHELL;
	String csshead = ApplConstants.CSSHEAD;
	String csslaeuft = ApplConstants.CSSGRUENL;
	
	String head = null;
	Vector<LivegoalsTablerow>rows;
	
	
	public LivegoalsTable(Vector<Livespiel> livegames)
	{
		log.debug("-->Constructing " + this.getClass().getName());
		this.rows = new Vector<LivegoalsTablerow>(40);
		
		StringBuffer sb = new StringBuffer("");

//----------------------------------------------Überschriftzeile 1
		sb.append(HtmlTags.wrapTH(HtmlTags.wrapH3("Livegoals")  + HtmlTags.createBR(1) 
				+ HtmlTags.createSUBMIT("navigation", "Zurück", ApplConstants.CSSSUBMIT2), null, 2));
		VereinBeanLocal vbl = LookupLocalService.lookupVereinBeanLocal();
		Iterator<Livespiel> iter1 = livegames.iterator();
		while (iter1.hasNext())
		{
			Livespiel lg = iter1.next();
			EnSpiel spiel = lg.getSpiel();
			EnVerein heim = null;
			EnVerein gast = null;
			try
			{
				heim = vbl.getVerein(spiel.getFkSpielVereinHeim());
				gast = vbl.getVerein(spiel.getFkSpielVereinGast());
			}
			catch (VereinException e)
			{
				e.printStackTrace();
			}
			sb.append(HtmlTags.wrapTD(
					HtmlTags.wrapP(
							heim.getAtVereinName() + HtmlTags.createMINUS() + gast.getAtVereinName(),
							HtmlTags.wrapIMG50(heim.getAtVereinKurz(), "vereine/gross/") + HtmlTags.createBR(1)
									+ HtmlTags.wrapIMG50(gast.getAtVereinKurz(), "vereine/gross/")), csshead));
		}
		
		LivegoalsBeanLocal lbl = LookupLocalService.lookupLivegoalsBeanLocal();
		Iterator<Livespiel> iterlbl = lbl.getLivegoalsObject().getLivespiele().iterator();
		int i = 0;
		while(iterlbl.hasNext())
		{
			Livespiel ls = iterlbl.next();
			if(ls.getSpiel().getAtSpielIsPlayed())
			{
				i++;
			}
		}
		
		if(ApplConstants.CASEBUTTONS && i > 6 && i < 9)
		{
			sb.append(HtmlTags.wrapTH(
					  HtmlTags.wrapH3("Simulationen")  
					+ HtmlTags.wrapP("Hier wird bezogen auf meine noch ausstehenden Tipps der Bestmögliche oder der Schlechtmögliche weitere Verlauf errechnet. Somit kann man Fragen beantworten wie z.B.: kann ich noch den Spieltag gewinnen? oder ist mein Sieg wirklich schon sicher?", "Erklärung hier") 
					+ HtmlTags.createSUBMIT("bestcase", "Bestmöglich", ApplConstants.CSSSUBMIT2)
					+ HtmlTags.createBR(1) 
					+ HtmlTags.createSUBMIT("worstcase", "Schlechtmöglich", ApplConstants.CSSSUBMIT2)
					, null, 2));
		}
		else
		{
			sb.append(HtmlTags.wrapTH(
					  HtmlTags.wrapH3("Simulationen")  
					+ HtmlTags.wrapP("Hier wird bezogen auf meine noch ausstehenden Tipps der Bestmögliche oder der Schlechtmögliche weitere Verlauf errechnet. Somit kann man Fragen beantworten wie z.B.: kann ich noch den Spieltag gewinnen? oder ist mein Sieg wirklich schon sicher?", "Erklärung hier") 
					+ HtmlTags.createSUBMITdisabled("bestcase", "Bestmöglich", ApplConstants.CSSSUBMIT2)
					+ HtmlTags.createBR(1) 
					+ HtmlTags.createSUBMITdisabled("worstcase", "Schlechtmöglich", ApplConstants.CSSSUBMIT2)
					, null, 2));
		}
		String head1 = HtmlTags.wrapTR(sb.toString(), null);
		
//----------------------------------------------Überschriftzeile 2
		sb = new StringBuffer("");
		sb.append(HtmlTags.wrapTH("Platz", cssweiss));
		sb.append(HtmlTags.wrapTH("Name", cssweiss));
		
		Iterator<Livespiel> iter2 = livegames.iterator();
		while (iter2.hasNext())
		{
			Livespiel lg = iter2.next();
			if(lg.getStatus() == LivestatusEnum.FINISHED)
			{
				sb.append(HtmlTags.wrapTH(lg.getNow(), csshead));
			}
			else if(lg.getStatus() == LivestatusEnum.RUNNING)
			{
				sb.append(HtmlTags.wrapTH(lg.getNow(), csslaeuft));
			}
			else
			{
				sb.append(HtmlTags.wrapTH(lg.getSpiel().getResult(), csshead));
			}
		}
		sb.append(HtmlTags.wrapTH("Punkte", cssweiss));
		sb.append(HtmlTags.wrapTH("Gesamt", cssweiss));
		
		this.head = head1 + HtmlTags.wrapTR(sb.toString(), null);
	}
	
	public String getHead()
	{
		return this.head;
	}
	
	public Vector<LivegoalsTablerow> getRows()
	{
		return rows;
	}
	
	public void sortTipperPunkteSpieltag()
	{
		Collections.sort(this.rows, new SortLiveTablePunkteSpieltag());
		Iterator<LivegoalsTablerow> iter1 = this.getRows().iterator();
		int i = 0;
		int gruppenwechsel = 99;
		while (iter1.hasNext())
		{
			i++;
			LivegoalsTablerow row = iter1.next();
			row.setPlatz(0);
			if (gruppenwechsel != row.getPunktespieltag())
			{
				gruppenwechsel = row.getPunktespieltag();
				row.setPlatz(i);
			}
		}
	}
}
