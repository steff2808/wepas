package tables;

import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.wepas.connector.EinstellungBeanRemote;
import de.wepas.connector.EinstellungException;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnEinstellung;
import de.wepas.jpa.EnSpieltag;
import de.wepas.util.HtmlTags;

public class MainTablehead1
{
	private static Log log = LogFactory.getLog(MainTablehead1.class);
	
	String csssubmit4 = ApplConstants.CSSSUBMIT4;
	String cssblick = ApplConstants.CSSBLICK;
	String cssnoblick = ApplConstants.CSSNOBLICK;
	String cssnoBorder = ApplConstants.CSSNOBORDER;
	String csshell = ApplConstants.CSSNOHELL;
	String cssdunkel = ApplConstants.CSSDUNKEL;
	EinstellungBeanRemote einstellungStateless;
	boolean spieltaganzeige;
	boolean tunier;
	EnSpieltag spieltag;
	
	public MainTablehead1(boolean tunier, EnSpieltag spieltag, EinstellungBeanRemote einstellungStateless)
	{
		super();
		this.spieltag = spieltag;
		this.tunier = tunier;
		this.einstellungStateless = einstellungStateless;
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
		String cssSpieltag;
		if (spieltaganzeige)
		{
			cssSpieltag = csshell;
		}
		else
		{
			cssSpieltag = cssdunkel;
		}
		
		StringBuffer sb = new StringBuffer("");
		EnEinstellung wettbewerb = null;
		try 
		{
			wettbewerb = this.einstellungStateless.getEinstellung(spieltag.getFkSpieltagEinstellung());
		} 
		catch (EinstellungException e) 
		{
			log.error("EinstellungException in Zeile 68 " + e.getMessage());
		}
		
		if (spieltaganzeige)
		{
			sb.append(HtmlTags.wrapTH(wettbewerb.getAtEinstellungWertCha30() + HtmlTags.createBR(1) + spieltag.getAtSpieltagText() + " (" + util.StringFormat.formatDateShort(spieltag.getAtSpieltagStart()) + ")", cssblick, 3));
		}
		else
		{
			sb.append(HtmlTags.wrapTH(wettbewerb.getAtEinstellungWertCha30() + HtmlTags.createBR(1) + spieltag.getAtSpieltagText()+ " (" + util.StringFormat.formatDateShort(spieltag.getAtSpieltagStart()) + ")", cssnoblick, 3));
		}
		sb.append(HtmlTags.wrapTH(HtmlTags.createSUBMIT(ApplConstants.BUTTONPREV, "<", csssubmit4), cssnoBorder));
		sb.append(HtmlTags.wrapTH("www.wepas.de ", cssnoBorder, spieltag.getAtSpieltagPaarungen() - 2));
		sb.append(HtmlTags.wrapTH(HtmlTags.createSUBMIT(ApplConstants.BUTTONNEXT, ">", csssubmit4), cssnoBorder));
		
		sb.append(HtmlTags.wrapTH(HtmlTags.createNBSP(1), cssSpieltag, 2));
		EnEinstellung saison = null;
		try
		{
			saison = einstellungStateless.getEinstellung("saison")[0];
		}
		catch (EinstellungException e)
		{
			log.error("EinstellungException in Zeile 91 " + e.getMessage());
		}
		if (spieltaganzeige)
		{
			sb.append(HtmlTags.wrapTH(saison.getAtEinstellungWertCha30(), cssnoblick, 4));
		}
		else
		{
			sb.append(HtmlTags.wrapTH(saison.getAtEinstellungWertCha30(), cssblick, 4));
		}
		return HtmlTags.wrapTR(sb.toString() + '\n', null);
	}
	
	private String getHtmlRowTunier()
	{
		String cssSpieltag;
		if (spieltaganzeige)
		{
			cssSpieltag = csshell;
		}
		else
		{
			cssSpieltag = csshell;
		}
		
		StringBuffer sb = new StringBuffer("");
		if (spieltaganzeige)
		{
			sb.append(HtmlTags.wrapTH(spieltag.getAtSpieltagText(), cssblick, 3));
		}
		else
		{
			sb.append(HtmlTags.wrapTH(spieltag.getAtSpieltagText(), cssblick, 3));
		}
		sb.append(HtmlTags.wrapTH(HtmlTags.createSUBMIT(ApplConstants.BUTTONPREV, "<", csssubmit4), cssnoBorder));
		if(this.spieltag.getAtSpieltagPaarungen() > 3)
		{
			GregorianCalendar gc = new GregorianCalendar();
			if(gc.get(GregorianCalendar.DAY_OF_MONTH) == 29)
			{
				sb.append(HtmlTags.wrapTH("<marquee behavior='alternate'>ACHTUNG - bis morgen (16:00) die Tipps für das Achtelfinale abgeben!</marquee>", cssnoBorder, spieltag.getAtSpieltagPaarungen() - 2));
			}
			else if(gc.get(GregorianCalendar.DAY_OF_MONTH) == 30)	
			{
				sb.append(HtmlTags.wrapTH("<marquee behavior='alternate'>ACHTUNG - bis heute nachmittag (16:00) die Tipps für das Achtelfinale abgeben!</marquee>", cssnoBorder, spieltag.getAtSpieltagPaarungen() - 2));
			}
//			else if(gc.get(GregorianCalendar.DAY_OF_MONTH) == 3)
//			{
//				sb.append(HtmlTags.wrapTH("", cssnoBorder, spieltag.getAtSpieltagPaarungen() - 2));
//			}
//			else if(gc.get(GregorianCalendar.DAY_OF_MONTH) == 4)	
//			{
//				sb.append(HtmlTags.wrapTH("", cssnoBorder, spieltag.getAtSpieltagPaarungen() - 2));
//			}
			else
			{
				sb.append(HtmlTags.wrapTH("www.wepas.de", cssnoBorder, spieltag.getAtSpieltagPaarungen() - 2));
			}
//			sb.append(HtmlTags.wrapTH("www.wepas.de ", cssnoBorder, spieltag.getAtSpieltagPaarungen() - 2));
		}
		sb.append(HtmlTags.wrapTH(HtmlTags.createSUBMIT(ApplConstants.BUTTONNEXT, ">", csssubmit4), cssnoBorder));
		EnEinstellung saison = null;
		try
		{
			saison = einstellungStateless.getEinstellung("saison")[0];
		}
		catch (EinstellungException e)
		{
			log.error("EinstellungException in Zeile 138 " + e.getMessage());
		}
		if (spieltaganzeige)
		{
			sb.append(HtmlTags.wrapTH(saison.getAtEinstellungWertCha30(), cssblick, 2));
		}
		else
		{
			sb.append(HtmlTags.wrapTH(saison.getAtEinstellungWertCha30(), cssblick, 2));
		}
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
	
}
