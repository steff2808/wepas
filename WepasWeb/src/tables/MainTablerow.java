package tables;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.StringFormat;
import de.wepas.connector.TippBeanRemote;
import de.wepas.connector.TippException;
import de.wepas.connector.WetteBeanRemote;
import de.wepas.connector.WetteException;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnSpieltag;
import de.wepas.jpa.EnTipp;
import de.wepas.jpa.EnTipper;
import de.wepas.jpa.EnWette;
import de.wepas.util.HtmlTags;

public class MainTablerow
{
	private static Log log = LogFactory.getLog(MainTablerow.class);
	
	String csshell = ApplConstants.CSSHELL;
	String csshellh = ApplConstants.CSSHELLH;
//	String cssdunkel = ApplConstants.CSSDUNKEL;
	String cssdunkel = ApplConstants.CSSHELL;
//	String cssdunkelh = ApplConstants.CSSDUNKELH;
	String cssdunkelh = ApplConstants.CSSHELLH;
	String csseigen = ApplConstants.CSSEIGEN;
	String cssnoeigen = ApplConstants.CSSNOEIGEN;
	String cssgewinn = ApplConstants.CSSGEWINN;
	String cssnohell = ApplConstants.CSSNOHELL;
	String cssnodunkel = ApplConstants.CSSNODUNKEL;
	
	char status;
	int tipperId;
	int tipperIdApril;
	boolean own;
	boolean owngetippt;
	
	boolean tunier = false;
	boolean geldkoenig = false;
	boolean leader = false;
	boolean imgeld = false;
	boolean zahler = false;
	boolean vollzahler = false;
	boolean vollzahlerbest = false;
	boolean letzter = false;
	boolean bubble = false;
	int platz;
	int platzgesamt;
	String name;
	String sortname;
	int punktespieltag;
	BigDecimal gewinn;
	
	Vector<EnTipp> tipps;
	
	int punktegesamt;
	BigDecimal geldgesamt;
	BigDecimal bonus;
	
	EnSpieltag spieltag;
	
	public MainTablerow(boolean tunier, EnTipper user, EnSpieltag spieltag, EnTipper tipper, WetteBeanRemote wbr, TippBeanRemote tippStateless)
	{
		super();
		this.tunier = tunier;
		this.tipperId = tipper.getIdTipper();
		this.tipperIdApril = 0;
		// Nur bei Günter und nur am 1. und 2. April
		GregorianCalendar gc = new GregorianCalendar();
		if (user.getIdTipper() == 18 && gc.get(GregorianCalendar.MONTH) == 3 && gc.get(GregorianCalendar.DAY_OF_MONTH) < 3)
		{
			this.tipperIdApril = 18;
		}
		// if(user.getIdTipper() == 1) //Oder Steff
		// {
		// this.tipperIdApril = 18;
		// }
		
		this.own = tipper.isOwn();
		this.platz = -1;
		this.platzgesamt = -1;
		this.spieltag = spieltag;
		this.name = tipper.getAtTipperVorname() + HtmlTags.createNBSP(1) + tipper.getAtTipperName();
		this.punktegesamt = tipper.getAtTipperPunkte();
		this.geldgesamt = tipper.getAtTipperKonto();
		this.bonus = BigDecimal.ZERO;
		this.sortname = tipper.getAtTipperName() + tipper.getAtTipperVorname();
		if (tipper.getAtTipperSiege() == 0)
		{
			this.vollzahler = true;
		}
		EnWette wette = null;
		try
		{
			wette = wbr.getWette(tipper, spieltag);
		}
		catch (WetteException e1)
		{
			e1.printStackTrace();
		}
		if (wette == null)
		{
			this.punktespieltag = 0;
			this.gewinn = new BigDecimal(0.0);
		}
		else
		{
			this.punktespieltag = wette.getAtWettePunkte();
			this.gewinn = wette.getAtWetteGewinn();
		}
		
		try
		{
			EnTipp[] tipparray = tippStateless.getTipps(tipper, spieltag);
			this.tipps = new Vector<EnTipp>(tipparray.length);
			for (int i = 0; i < tipparray.length; i++)
			{
				this.tipps.add(tipparray[i]);
			}
		}
		catch (TippException e)
		{
			log.error("TippException Zeile 128 " + e.getMessage());
		}
		this.punktegesamt = tipper.getAtTipperPunkte();
		this.geldgesamt = tipper.getAtTipperKonto();
	}
	
	public String getHtmlRow(boolean spieltaganzeige)
	{
		String cssSpieltag;
		String cssGesamt;
		String cssNoGewinn;
		if (spieltaganzeige)
		{
			cssSpieltag = csshell;
			cssGesamt = cssdunkel;
			cssNoGewinn = cssnohell;
		}
		else
		{
			cssSpieltag = cssdunkel;
			cssGesamt = csshell;
			cssNoGewinn = cssnodunkel;
		}
		if (this.isOwn())
		{
			cssSpieltag = csseigen;
			cssGesamt = csseigen;
			cssNoGewinn = cssnoeigen;
		}
		
		StringBuffer sb = new StringBuffer("");
		if (this.platz < 1 || !spieltaganzeige)
		{
			sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHS, HtmlTags.createNBSP(1), csshell + "h"));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHS, this.getPlatz() + HtmlTags.createPUNKT(), csshell + "h"));
		}
		
		if (this.own)
		{
			sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHX, this.getName(), csseigen));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHX, this.getName(), csshell));
		}
		// Jetzt kommen die symbole
		
		if (this.tunier)
		{
//			if (this.letzter)
//			{
//				sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHM, HtmlTags.wrapIMG25("ouzo", "geld/"), csshell));
//			}
//			else if (this.leader)
			if (this.leader)
			{
				sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHM, HtmlTags.wrapIMG25("leaderpunkte", "geld/"), csshell));
			}
			else if (this.bonus.doubleValue() > 0.0)
			{
				sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHM, HtmlTags.wrapIMG25("imgeld", "geld/"), csshell));
			}
			else
			{
				sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHM, HtmlTags.wrapIMG25("imminusrot", "geld/"), csshell));
			}
		}
		else
		{
			if (this.letzter)
			{
				sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHM, HtmlTags.wrapIMG25("ouzo", "geld/"), csshell));
			}
			else if (this.vollzahlerbest)
			{
				sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHM, HtmlTags.wrapIMG25("absolut", "geld/"), csshell));
			}
			else if (this.vollzahler)
			{
				sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHM, HtmlTags.wrapIMG25("vollzahler", "geld/"), csshell));
			}
			else if (this.geldkoenig)
			{
				sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHM, HtmlTags.wrapIMG25("leadergeld", "geld/"), csshell));
			}
			else if (this.leader)
			{
				sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHM, HtmlTags.wrapIMG25("leaderpunkte", "geld/"), csshell));
			}
			else if (this.geldgesamt.doubleValue() < 0.0)
			{
				sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHM, HtmlTags.wrapIMG25("imminus", "geld/"), csshell));
			}
			else
			{
				sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHM, HtmlTags.wrapIMG25("imgeld", "geld/"), csshell));
			}
		}
		
		Iterator<EnTipp> iter1 = this.getTipps().iterator();
		// Iterator<EnTipp> iter2 =
		if (iter1.hasNext())
		{
			while (iter1.hasNext())
			{
				if (this.spieltag.getAtSpieltagIsStarted() || this.isOwnGetippt())
				{
					// Anzeige nur wenn Spieltag gestartet ist, oder selbst
					// getippt wurde
					sb.append(HtmlTags.getTippHTML(iter1.next()));
				}
				else
				{
					iter1.next();
					sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHM, HtmlTags.createHAKEN(), cssSpieltag));
				}
			}
		}
		else
		{
			sb.append(HtmlTags.wrapTD(HtmlTags.createNBSP(1), null, this.spieltag.getAtSpieltagPaarungen())); // mit
		}
		
		if (! this.tunier)
		{
			
			if (this.spieltag.getAtSpieltagIsStarted())
			{
				sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHM, Integer.toString(this.getPunktespieltag()), cssSpieltag + "h"));
			}
			else
			{
				sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHM, HtmlTags.createNBSP(1), cssSpieltag + "h"));
			}
			
			if (this.gewinn.doubleValue() > 0.0)
			{
				sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHL, StringFormat.formatMoney(this.getGewinn().doubleValue()) + HtmlTags.createEURO(), cssgewinn));
			}
			else
			{
				if (this.spieltag.getAtSpieltagIsFinished())
				{
					sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHL, HtmlTags.createMINUS(), cssNoGewinn));
				}
				else
				{
					sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHL, HtmlTags.createNBSP(1), cssSpieltag + "h"));
				}
			}
			
			if (this.platzgesamt < 1 || spieltaganzeige)
			{
				sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHS, HtmlTags.createNBSP(1), cssGesamt + "h"));
			}
			else
			{
				sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHS, this.getPlatzgesamt() + HtmlTags.createPUNKT(), cssGesamt + "h"));
			}
		}
		
		sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHM, "" + this.getPunktegesamt(), cssGesamt + "h"));
		
		if(! this.tunier)
		{
			if (this.getGeldgesamt().doubleValue() < 0.0)
			{
				sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHL, "" + StringFormat.formatMoney(this.getGeldgesamt().doubleValue()) + HtmlTags.createEURO(), cssGesamt + "s"));
			}
			else
			{
				sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHL, "" + StringFormat.formatMoney(this.getGeldgesamt().doubleValue()) + HtmlTags.createEURO(), cssGesamt + "h"));
			}
		}
		if (this.getBonus().doubleValue() < 0.0)
		{
			sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHL, "" + StringFormat.formatMoney(this.getBonus().doubleValue()) + HtmlTags.createEURO(), cssGesamt + "s"));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHL, "" + StringFormat.formatMoney(this.getBonus().doubleValue()) + HtmlTags.createEURO(), cssGesamt + "h"));
		}
		
		if (this.isOwn())
		{
			return HtmlTags.wrapTR(sb.toString() + '\n', csseigen);
		}
		else
		{
			return HtmlTags.wrapTR(sb.toString() + '\n', null);
		}
	}
	
	public char getStatus()
	{
		return status;
	}
	
	public boolean isOwn()
	{
		return own;
	}
	
	public boolean isOwnGetippt()
	{
		return owngetippt;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getSortname()
	{
		return sortname;
	}
	
	public int getPunktespieltag()
	{
		if(this.tunier)
		{
			return punktegesamt;
		}
		else
		{
			return punktespieltag;
		}
	}
	
	public BigDecimal getGewinn()
	{
		return gewinn;
	}
	
	public Vector<EnTipp> getTipps()
	{
		return tipps;
	}
	
	public int getPunktegesamt()
	{
		return punktegesamt;
	}
	
	public BigDecimal getGeldgesamt()
	{
		return geldgesamt;
	}
	
	public BigDecimal getBonus()
	{
		return bonus;
	}
	
	public String getPlatz()
	{
		if(this.tunier)
		{
			return "" + platzgesamt;
		}
		else
		{
			return "" + platz;
		}

	}
	
	public void setPlatz(int platz)
	{
		this.platz = platz;
	}
	
	public void setStatus(char status)
	{
		this.status = status;
	}
	
	public void setOwn(boolean own)
	{
		this.own = own;
	}
	
	public void setOwnGetippt(boolean owngetippt)
	{
		this.owngetippt = owngetippt;
	}
	
	public void setLeader(boolean leader)
	{
		this.leader = leader;
	}
	
	public void setBubble(boolean bubble)
	{
		this.bubble = bubble;
	}
	
	public void setGeldkoenig(boolean geldkoenig)
	{
		this.geldkoenig = geldkoenig;
	}
	
	public void setLetzter(boolean letzter)
	{
		this.letzter = letzter;
	}
	
	public void setVollzahlerbest(boolean vollzahlerbest)
	{
		this.vollzahlerbest = vollzahlerbest;
	}
	
	public boolean getVollzahler()
	{
		return this.vollzahler;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setPunktespieltag(int punktespieltag)
	{
		this.punktespieltag = punktespieltag;
	}
	
	public void setGewinn(BigDecimal gewinn)
	{
		this.gewinn = gewinn;
	}
	
	public void setPunktegesamt(int punktegesamt)
	{
		this.punktegesamt = punktegesamt;
	}
	
	public void setGeldgesamt(BigDecimal geldgesamt)
	{
		this.geldgesamt = geldgesamt;
	}
	
	public void setBonus(BigDecimal bonus)
	{
		this.bonus = bonus;
	}
	
	public int getPlatzgesamt()
	{
		return platzgesamt;
	}
	
	public void setPlatzgesamt(int platzgesamt)
	{
		this.platzgesamt = platzgesamt;
	}
	
	public int getTipperId()
	{
		return tipperId;
	}
	
	public int getTipperIdApril()
	{
		return tipperIdApril;
	}
	
	@Override
	public String toString()
	{
		return this.getName() + "/" + this.tipps.size();
	}
	
}
