package tables;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Vector;

import util.LookupRemoteService;
import de.wepas.connector.EinstellungBeanRemote;
import de.wepas.connector.EinstellungException;
import de.wepas.connector.SpieltagBeanRemote;
import de.wepas.connector.SpieltagException;
import de.wepas.connector.VereinBeanRemote;
import de.wepas.connector.VereinException;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnSpieltag;
import de.wepas.jpa.EnTipper;
import de.wepas.jpa.EnVerein;
import de.wepas.livegoals.LivegoalsBeanRemote;
import de.wepas.util.HtmlTags;

public class MainTablehead2
{
	String pathimage = ApplConstants.PATHGROSS;
	
	String csssubmit2 = ApplConstants.CSSSUBMIT2;
	String csssubmit2h = ApplConstants.CSSSUBMIT2H;
	String csshell = ApplConstants.CSSHELL;
	String cssdunkel = ApplConstants.CSSDUNKEL;
	String csshead = ApplConstants.CSSHEAD;
	
	boolean spieltaganzeige;
	boolean tunier;
	boolean adminuser;
	EnSpieltag spieltag;
	EnTipper tipper;
	Vector<EnSpiel> spiele;
	EinstellungBeanRemote einstellungStateless;
	VereinBeanRemote vereinStateless;
	SpieltagBeanRemote spieltagStateless;
	LivegoalsBeanRemote livegoalsSingleton;
	
	public MainTablehead2(boolean tunier, EnTipper user, EnSpieltag spieltag, Vector<EnSpiel> spiele, EinstellungBeanRemote einstellungStateless, VereinBeanRemote vereinStateless, LivegoalsBeanRemote livegoalsSingleton)
	{
		super();
		
		// Nur nur am 1. und 2. April
		GregorianCalendar gc = new GregorianCalendar();
//		if (gc.get(GregorianCalendar.MONTH) == 1 && gc.get(GregorianCalendar.DAY_OF_MONTH) < 9)
		if (gc.get(GregorianCalendar.MONTH) == 3 && gc.get(GregorianCalendar.DAY_OF_MONTH) < 3)
		{
			this.pathimage = ApplConstants.PATHAPRIL;
		}

		
		
		this.tipper = user;
		this.tunier = tunier;
		this.spieltag = spieltag;
		this.spiele = spiele;
		this.einstellungStateless = einstellungStateless;
		this.vereinStateless = vereinStateless;
		this.livegoalsSingleton = livegoalsSingleton;
		this.spieltagStateless = LookupRemoteService.lookupSpieltagBeanRemote();
	}
	
	public String getHtmlRow()
	{
		if (this.tunier)
		{
			return this.getHtmlRowTunier();
		}
		else
		{
			return this.getHtmlRowLiga();
		}
	}
	
	private String getHtmlRowLiga()
	{
		StringBuffer sb = new StringBuffer("");
		String cssSpieltag;
		String cssGesamt;
		if (spieltaganzeige)
		{
			cssSpieltag = csshell;
			cssGesamt = cssdunkel;
		}
		else
		{
			cssSpieltag = cssdunkel;
			cssGesamt = csshell;
		}
		String buttonTextDuty = "";
		String buttonTextFree = "";
		try
		{
			buttonTextDuty = spieltagStateless.getNextzuTippenDutySpieltag(this.tipper).getAtSpieltagText();
		}
		catch (SpieltagException e)
		{
			buttonTextDuty = "nicht Verfügbar";
		}
		try
		{
			buttonTextFree = spieltagStateless.getNextzuTippenFreeSpieltag(this.tipper).getAtSpieltagText();
		}
		catch (SpieltagException e)
		{
			buttonTextFree = "nicht Verfügbar";
		}
		
		if (adminuser)
		{
			sb.append(HtmlTags.wrapTH(
					HtmlTags.createSUBMIT(ApplConstants.BUTTONTIPPENDUTY, "Tippen", csssubmit2)
					+ HtmlTags.createBR(1) 
					+ HtmlTags.createSUBMIT("forum", "Forum", csssubmit2)
					+ HtmlTags.createBR(1) 
					+ HtmlTags.createSUBMIT("admin", "Admin", csssubmit2h)
					+ HtmlTags.createSUBMIT("storno", "Storno", csssubmit2h) 
					+ HtmlTags.createBR(1)
					+ HtmlTags.createSUBMIT("logout", "Logout", csssubmit2), csshell, 3));
		}
		else
		{
			if(ApplConstants.TESTFLAG)
			{
				sb.append(HtmlTags.wrapTH(
					HtmlTags.createSUBMIT(ApplConstants.BUTTONTIPPENDUTY, buttonTextDuty, csssubmit2)
					+ HtmlTags.createBR(1) 
					+ HtmlTags.createSUBMIT(ApplConstants.BUTTONTIPPENFREE, buttonTextFree, csssubmit2)
					+ HtmlTags.createBR(1) 
					+ HtmlTags.createSUBMIT(ApplConstants.BUTTONFORUM, "Forum", csssubmit2)
					+ HtmlTags.createBR(1) 
					+ HtmlTags.createSUBMIT(ApplConstants.BUTTONLOGOUT, "Logout", csssubmit2), csshell, 3));
			}
			else
			{
				sb.append(HtmlTags.wrapTH(
						HtmlTags.createSUBMIT(ApplConstants.BUTTONTIPPENDUTY, "Tippen", csssubmit2)
						+ HtmlTags.createBR(1) 
						+ HtmlTags.createSUBMIT(ApplConstants.BUTTONFORUM, "Forum", csssubmit2)
						+ HtmlTags.createBR(1) 
						+ HtmlTags.createSUBMIT(ApplConstants.BUTTONLOGOUT, "Logout", csssubmit2), csshell, 3));
			}
		}
		
		Iterator<EnSpiel> iter1 = spiele.iterator();
		while (iter1.hasNext())
		{
			EnSpiel spiel = iter1.next();
			EnVerein heim = null;
			EnVerein gast = null;
			try
			{
				heim = vereinStateless.getVerein(spiel.getFkSpielVereinHeim());
				gast = vereinStateless.getVerein(spiel.getFkSpielVereinGast());
			}
			catch (VereinException e)
			{
				e.printStackTrace();
			}
//			System.out.println("Heim: " + heim.toString());
//			System.out.println("Gast: " + gast.toString());
			if(spieltag.getAtSpieltagPaarungen() < 6)
			{
//				System.out.println("<6 " + spieltag.getAtSpieltagPaarungen());
				sb.append(HtmlTags.wrapTD(
						HtmlTags.wrapP(
								heim.getAtVereinName() + HtmlTags.createMINUS() + gast.getAtVereinName(),
								HtmlTags.wrapIMG50(heim.getAtVereinKurz(), pathimage) + HtmlTags.createNBSP(2)
											+ HtmlTags.wrapIMG50(gast.getAtVereinKurz(), pathimage)), csshead));
			}
			else
			{
//					System.out.println(">5 " + spieltag.getAtSpieltagPaarungen());
				sb.append(HtmlTags.wrapTD(
						HtmlTags.wrapP(
								heim.getAtVereinName() + HtmlTags.createMINUS() + gast.getAtVereinName(),
								HtmlTags.wrapIMG50(heim.getAtVereinKurz(), pathimage) + HtmlTags.createBR(1)
										+ HtmlTags.wrapIMG50(gast.getAtVereinKurz(), pathimage)), csshead));
			}
		}
		sb.append(HtmlTags.wrapTH(HtmlTags.createNBSP(1), cssSpieltag, 2));
		
		int i = 0;
		Iterator<EnSpiel> iter2 = this.spiele.iterator();
		while (iter2.hasNext())
		{
			if (iter2.next().getAtSpielIsPlayed())
			{
				i++;
			}
		}
		if (this.livegoalsSingleton.getLivegoalsObject() == null)
		{
			sb.append(HtmlTags.wrapTH(
					HtmlTags.createSUBMIT(ApplConstants.BUTTONTABELLE, "Tabelle", csssubmit2) + HtmlTags.createBR(1)
							+ HtmlTags.createSUBMIT(ApplConstants.BUTTONSTATISTIK, "Statistik", csssubmit2) + HtmlTags.createBR(1)
							+ HtmlTags.createSUBMIT(ApplConstants.BUTTONLAENDERRANKING, "Länderranking", csssubmit2), cssGesamt, 4));
		}
		else
		{
			sb.append(HtmlTags.wrapTH(
					HtmlTags.createSUBMIT(ApplConstants.BUTTONTABELLE, "Tabelle", csssubmit2) + HtmlTags.createBR(1)
							+ HtmlTags.createSUBMIT(ApplConstants.BUTTONSTATISTIK, "Statistik", csssubmit2) + HtmlTags.createBR(1)
							+ HtmlTags.createSUBMIT(ApplConstants.BUTTONLAENDERRANKING, "Länderranking", csssubmit2) + HtmlTags.createBR(1)
							+ HtmlTags.createSUBMIT(ApplConstants.BUTTONLIVEGOALS, "Livegoals", csssubmit2), cssGesamt, 4));
		}
		return HtmlTags.wrapTR(sb.toString() + '\n', null);
	}
	
	private String getHtmlRowTunier()
	{
		StringBuffer sb = new StringBuffer("");
		String cssSpieltag;
		String cssGesamt;
		if (spieltaganzeige)
		{
			cssSpieltag = csshell;
			cssGesamt = csshell;
		}
		else
		{
			cssSpieltag = csshell;
			cssGesamt = csshell;
		}
		
		
		if (adminuser)
		{
			sb.append(HtmlTags.wrapTH(
					HtmlTags.createSUBMIT(ApplConstants.BUTTONTIPPENDUTY, "Tippen", csssubmit2) + HtmlTags.createBR(1) + HtmlTags.createSUBMIT("forum", "Forum", csssubmit2)
							+ HtmlTags.createBR(1) + HtmlTags.createSUBMIT("admin", "Admin", csssubmit2) + HtmlTags.createBR(1)
							+ HtmlTags.createSUBMIT(ApplConstants.BUTTONSTORNO, "Storno", csssubmit2) + HtmlTags.createBR(1)
							+ HtmlTags.createSUBMIT(ApplConstants.BUTTONLOGOUT, "Logout", csssubmit2), csshell, 3, 2));
		}
		else
		{
			sb.append(HtmlTags.wrapTH(
					HtmlTags.createSUBMIT(ApplConstants.BUTTONTIPPENDUTY, "Tippen", csssubmit2) + HtmlTags.createBR(1) + HtmlTags.createSUBMIT("forum", "Forum", csssubmit2)
							+ HtmlTags.createBR(1) + HtmlTags.createSUBMIT(ApplConstants.BUTTONLOGOUT, "Logout", csssubmit2), csshell, 3, 2));
		}

		Vector<String> gruppen = new Vector<String>();		
		Iterator<EnSpiel> iterxxx = spiele.iterator();
		int count = 0;
		int id = 0;
		while(iterxxx.hasNext())
		{
			EnSpiel spiel = iterxxx.next();
			count++;
			if(spiel.getFkSpielEinstellung() != id)
			{
				id = spiel.getFkSpielEinstellung();
				try
				{
					gruppen.add(einstellungStateless.getEinstellung(id).getAtEinstellungWertCha30());
				}
				catch (EinstellungException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		int colspan = count / gruppen.size();
		
		Iterator<String> iteryyy =gruppen.iterator();
		while(iteryyy.hasNext())
		{
			sb.append(HtmlTags.wrapTH(iteryyy.next(),csshead, colspan));
		}
		
		sb.append(HtmlTags.wrapTH(
		HtmlTags.createSUBMIT(ApplConstants.BUTTONSTATISTIK, "Statistik", csssubmit2) + HtmlTags.createBR(1)
				+ HtmlTags.createSUBMIT(ApplConstants.BUTTONLAENDERRANKING, "Länderranking", csssubmit2), cssGesamt, 4, 2));

		StringBuffer sb1 = new StringBuffer("");	
		Iterator<EnSpiel> iter1 = spiele.iterator();
		while (iter1.hasNext())
		{
			EnSpiel spiel = iter1.next();
			EnVerein heim = null;
			EnVerein gast = null;
			try
			{
				heim = vereinStateless.getVerein(spiel.getFkSpielVereinHeim());
				gast = vereinStateless.getVerein(spiel.getFkSpielVereinGast());
			}
			catch (VereinException e)
			{
				e.printStackTrace();
			}

			
			if(spieltag.getAtSpieltagPaarungen() < 6)
			{
//				System.out.println("<6 " + spieltag.getAtSpieltagPaarungen());
				sb1.append(HtmlTags.wrapTD(
						HtmlTags.wrapP(
								heim.getAtVereinName() + HtmlTags.createMINUS() + gast.getAtVereinName(),
								HtmlTags.wrapIMG50(heim.getAtVereinKurz(), pathimage) + HtmlTags.createNBSP(2)
										+ HtmlTags.wrapIMG50(gast.getAtVereinKurz(), pathimage)), csshead));
			}
			else
			{
//				System.out.println(">5 " + spieltag.getAtSpieltagPaarungen());
				sb1.append(HtmlTags.wrapTD(
						HtmlTags.wrapP(
								heim.getAtVereinName() + HtmlTags.createMINUS() + gast.getAtVereinName(),
								HtmlTags.wrapIMG50(heim.getAtVereinKurz(), pathimage) + HtmlTags.createBR(2)
										+ HtmlTags.wrapIMG50(gast.getAtVereinKurz(), pathimage)), csshead));
			}
		}
		if(!this.tunier)
		{
			sb1.append(HtmlTags.wrapTH(HtmlTags.createNBSP(1), cssSpieltag, 2));
		}
		
		int i = 0;
		Iterator<EnSpiel> iter2 = this.spiele.iterator();
		while (iter2.hasNext())
		{
			if (iter2.next().getAtSpielIsPlayed())
			{
				i++;
			}
		}
		sb.append(HtmlTags.wrapTR(sb1.toString(), null));		
		

		return HtmlTags.wrapTR(sb.toString() + '\n', null);
	}
	
	public boolean isSpieltaganzeige()
	{
		return spieltaganzeige;
	}
	public void setSpieltaganzeige(boolean spieltaganzeige)
	{
		this.spieltaganzeige = spieltaganzeige;
	}
	
	public boolean isAdminuser()
	{
		return adminuser;
	}
	
	public void setAdminuser(boolean adminuser)
	{
		this.adminuser = adminuser;
	}
}
