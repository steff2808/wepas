package tables;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.StringFormat;
import de.wepas.connector.SpieltagBeanRemote;
import de.wepas.connector.SpieltagException;
import de.wepas.connector.TipperBeanRemote;
import de.wepas.connector.TipperException;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnSpieltag;
import de.wepas.jpa.EnTipper;
import de.wepas.jpa.EnWette;
import de.wepas.util.HtmlTags;

public class HighscoreTablerow
{
	private static Log log = LogFactory.getLog(HighscoreTablerow.class);
	
	String csseigen = ApplConstants.CSSEIGEN;
	String csseigenh = ApplConstants.CSSEIGENH;
	String cssgewinn = ApplConstants.CSSGEWINN;
	String csshell = ApplConstants.CSSHELL;
	String csshellh = ApplConstants.CSSHELLH;
	
	boolean own;
	String namegesamt;
	String sortname;
	String spieltagtext;
	double gewinn;
	int spieltag;
	int punkte;
	
	public HighscoreTablerow(EnTipper user, EnWette wette, TipperBeanRemote tipperStateless, SpieltagBeanRemote spieltagStateless)
	{
//		log.info("public HighscoreTablerow(EnWette wette, TipperBeanRemote tipperStateless, SpieltagBeanRemote spieltagStateless)");
		this.punkte = wette.getAtWettePunkte();
		this.gewinn = wette.getAtWetteGewinn().doubleValue();
		EnTipper tipper = null;
		try
		{
			tipper = tipperStateless.getTipper(wette.getIdTipper());
		}
		catch (TipperException e)
		{
			e.printStackTrace();
			log.error("TipperException Zeile 42" + e.getMessage());
		}
		if(tipper.getIdTipper() == user.getIdTipper())
		{
			this.own = true;
		}
		this.sortname = tipper.getAtTipperName() + tipper.getAtTipperVorname();
		this.namegesamt = tipper.getAtTipperVorname() + " " + tipper.getAtTipperName();
		EnSpieltag spieltag = null;
		try
		{
			spieltag = spieltagStateless.getSpieltag(wette.getIdSpieltag());
			this.spieltagtext = spieltag.getAtSpieltagText();
			this.spieltag = spieltag.getAtSpieltagNummer();
		}
		catch (SpieltagException e)
		{
			e.printStackTrace();
			log.error("SpieltagException Zeile 57" + e.getMessage());
		}
		
	}
	
	public String getHtmlRow()
	{
//		log.info("public String getHtmlRow()");
		StringBuffer sb = new StringBuffer("");
		if(this.own)
		{
			sb.append(HtmlTags.wrapTD(this.namegesamt, csseigen));
			sb.append(HtmlTags.wrapTD(this.spieltagtext, csseigen));
			sb.append(HtmlTags.wrapTD(this.punkte + HtmlTags.createNBSP(1), csseigenh));
			sb.append(HtmlTags.wrapTD(StringFormat.formatMoney(this.gewinn) + HtmlTags.createEURO(), cssgewinn));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(this.namegesamt, csshell));
			sb.append(HtmlTags.wrapTD(this.spieltagtext, csshell));
			sb.append(HtmlTags.wrapTD(this.punkte + HtmlTags.createNBSP(1), csshellh));
			sb.append(HtmlTags.wrapTD(StringFormat.formatMoney(this.gewinn) + HtmlTags.createEURO(), cssgewinn));
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

	public double getGewinn()
	{
		return gewinn;
	}

	public int getSpieltag()
	{
		return spieltag;
	}
	
	public int getPunkte()
	{
		return punkte;
	}

	public void setSortname(String sortname)
	{
		this.sortname = sortname;
	}

	public void setGewinn(double gewinn)
	{
		this.gewinn = gewinn;
	}

	public void setSpieltag(int spieltag)
	{
		this.spieltag = spieltag;
	}
	
}
