package tables;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.wepas.connector.VereinBeanRemote;
import de.wepas.connector.VereinException;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnSpieltag;
import de.wepas.jpa.EnTipp;
import de.wepas.jpa.EnTipper;
import de.wepas.jpa.EnVerein;
import de.wepas.util.HtmlTags;

public class TrefferTablerow
{
	private static Log log = LogFactory.getLog(TrefferTablerow.class);
	
	String csseigen = ApplConstants.CSSEIGEN;
	String csshell = ApplConstants.CSSHELL;
	
	boolean own;
	String namegesamt;
	String sortname;
	String spieltagtext;
	int spieltag;
	EnTipp tipp;
	String paarung;
	String ergebnis;
	
	public TrefferTablerow(EnTipper user, EnTipper tipper, EnTipp tipp, EnSpiel spiel, EnSpieltag spieltag, VereinBeanRemote vereinStateless)
	{
		own = user.getIdTipper() == tipper.getIdTipper();
		if(tipper.getIdTipper() == user.getIdTipper())
		{
			this.own = true;
		}
		this.namegesamt = tipper.getAtTipperVorname() + " " + tipper.getAtTipperName();
		this.sortname = tipper.getAtTipperName() + tipper.getAtTipperVorname();
		this.spieltagtext = spieltag.getAtSpieltagText();
		this.spieltag = spieltag.getIdSpieltag();
		this.tipp = tipp;
		EnVerein heim = null;
		EnVerein gast = null;
		try
		{
			heim = vereinStateless.getVerein(spiel.getFkSpielVereinHeim());
			gast = vereinStateless.getVerein(spiel.getFkSpielVereinGast());
		}
		catch (VereinException e)
		{
			log.error("VereinException Zeile 52 " + e.getMessage());
		}
		paarung = HtmlTags.wrapIMG35(heim.getAtVereinKurz(), "vereine/gross/") 
				+ HtmlTags.wrapIMG35("vs", "vereine/klein/") 
				+ HtmlTags.wrapIMG35(gast.getAtVereinKurz(), "vereine/gross/")
				+ heim.getAtVereinName() + HtmlTags.createMINUS() + gast.getAtVereinName();
		ergebnis = HtmlTags.getTippHTML(tipp);
	}
	
	public String getHtmlRow()
	{
		StringBuffer sb = new StringBuffer("");
		if(this.own)
		{
			sb.append(HtmlTags.wrapTD(this.namegesamt, csseigen));
			sb.append(HtmlTags.wrapTD(this.spieltagtext, csseigen));
			sb.append(HtmlTags.wrapTD(this.paarung + HtmlTags.createNBSP(1), csseigen));
			sb.append(this.ergebnis);
		}
		else
		{
			sb.append(HtmlTags.wrapTD(this.namegesamt, csshell));
			sb.append(HtmlTags.wrapTD(this.spieltagtext, csshell));
			sb.append(HtmlTags.wrapTD(this.paarung + HtmlTags.createNBSP(1), csshell));
			sb.append(this.ergebnis);
		}
		return HtmlTags.wrapTR(sb.toString(), null);
	}
	
	public void setOwn(boolean own)
	{
		this.own = own;
	}

	public boolean isOwn()
	{
		return own;
	}

	public String getSortname()
	{
		return sortname;
	}

	public int getSpieltag()
	{
		return spieltag;
	}
	
	public void setSortname(String sortname)
	{
		this.sortname = sortname;
	}

	public void setSpieltag(int spieltag)
	{
		this.spieltag = spieltag;
	}
	
}
