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
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnTipp;
import de.wepas.jpa.EnTipper;
import de.wepas.util.HtmlTags;

public class StatistikTablerow
{
	private static Log log = LogFactory.getLog(StatistikTablerow.class);
	
	String csseigen = ApplConstants.CSSEIGEN;
	String csseigenh = ApplConstants.CSSEIGENH;
	String csseigens = ApplConstants.CSSEIGENS;
	String csshellh = ApplConstants.CSSHELLH;
	String csshells = ApplConstants.CSSHELLS;
	String csshell = ApplConstants.CSSHELL;

	
	int tipperId;
	int tipperIdApril;
	boolean own;
	int platz;
	String name;
	String sortname;
	int anzahl;
	int punkte;
	int boring;
	double konto;
	double bonus;
	int tagessiege;
	int tendenz;
	int treffer;
	int volltreffer;
	int nieten;
	int blind;
	int hunger;
	int knapp;
	double waldi;
	double netzerdelling;
	boolean[] sortButton = {false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false};

	
	public StatistikTablerow(EnTipper user, EnTipper tipper, WetteBeanRemote wetteStateless, TippBeanRemote tippStateless)
	{
//		log.info("public HighscoreTablerow(EnWette wette, TipperBeanRemote tipperStateless, SpieltagBeanRemote spieltagStateless)");
		this.tipperId = tipper.getIdTipper();
		this.tipperIdApril = 0;
		//Nur bei Günter und nur am 1. und 2. April
		GregorianCalendar gc = new GregorianCalendar();
		if(user.getIdTipper() == 18 && gc.get(GregorianCalendar.MONTH) == 3 && gc.get(GregorianCalendar.DAY_OF_MONTH) < 3)  
		{
			this.tipperIdApril = 18;
		}
//		if(user.getIdTipper() == 1) //Oder Steff
//		{
//			this.tipperIdApril = 18;
//		}
		if(tipper.getIdTipper() == user.getIdTipper())
		{
			this.own = true;
		}
		this.sortname = tipper.getAtTipperName() + tipper.getAtTipperVorname();
		this.name = tipper.getAtTipperVorname() + " " + tipper.getAtTipperName();
		this.konto = tipper.getAtTipperKonto().doubleValue();
		this.tagessiege = tipper.getAtTipperSiege();
		this.punkte = tipper.getAtTipperPunkte();
		
		Vector<EnTipp> vec1 = new Vector<EnTipp>(20);
		try
		{
			EnTipp[] tipparray = tippStateless.getStatistikTipps(tipper);
			vec1 = new Vector<EnTipp>(tipparray.length);
			for(int i = 0; i < tipparray.length; i++)
			{
				vec1.add(tipparray[i]);
			}
		}
		catch (TippException e)
		{
			log.info("--->Kein Tipp zum Tipper " +  tipper.getAtTipperName() + " vorhanden! " + e.getMessage());
		}
		Iterator<EnTipp> iter1 = vec1.iterator();
		this.anzahl = vec1.size();
		while(iter1.hasNext())
		{
			EnTipp tipp = iter1.next();
			if(tipp.getAtTippPunkte() > 0)
			{
				if(tipp.getAtTippIsTendenz())
				{
					this.tendenz++;
				}
				if(tipp.getAtTippIsExakt())
				{
					this.treffer++;
					if(tipp.getAtTippIsSupertipp())
					{
						this.volltreffer++;
					}
				}
				if(tipp.getAtTippIsSupertipp())
				{
					this.boring = this.boring + tipp.getAtTippPunkte() / 2;
				}
				else
				{
					this.boring = this.boring + tipp.getAtTippPunkte();
				}
			}
			else
			{
				this.nieten++;
			}
			if(tipp.getAtTippBlind() > 0)
			{
				this.blind++;
			}
			if(tipp.getAtTippPunkte() == 0 && tipp.getAtTippBlind() == 1)
			{
				if(tipp.getAtTippTorsumme() == 1 || tipp.getAtTippTorsumme() == -1)
				{
					this.hunger++;
				}
			}
			if(tipp.getAtTippPunkte() > 0 && tipp.getAtTippBlind() == 1)
			{
				if(tipp.getAtTippTorsumme() == 1 || tipp.getAtTippTorsumme() == -1)
				{
					this.knapp++;
				}
			}
		}
		if(this.tendenz > 0)
		{	
			this.netzerdelling = (double) this.tendenz / (double) this.anzahl * 100;
			this.waldi = (double) this.punkte / (double) this.tendenz;
		}
	}
	
	public String getHtmlRow()
	{
//		log.info("public String getHtmlRow()");
		StringBuffer sb = new StringBuffer("");
		
		String cssHaben;
		String cssSoll;
		String cssHell;
		if(this.own)
		{
			cssHaben = csseigenh;
			cssSoll = csseigens;
			cssHell = csseigen;
		}
		else
		{
			cssHaben = csshellh;
			cssSoll = csshells;
			cssHell = csshell;
		}
		if(platz > 0)
		{
			sb.append(HtmlTags.wrapTD(platz + HtmlTags.createPUNKT(), cssHell));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(HtmlTags.createNBSP(1), cssHell));
		}
		
		if(sortButton[1])
		{
			sb.append(HtmlTags.wrapTD(name, csseigen));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(name, cssHell));
		}
		
		if(sortButton[2])
		{
			sb.append(HtmlTags.wrapTD(anzahl + HtmlTags.createNBSP(1), csseigen));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(anzahl + HtmlTags.createNBSP(1), cssHell));
		}
		
		if(sortButton[3])
		{
			sb.append(HtmlTags.wrapTD(punkte + HtmlTags.createNBSP(1), csseigen));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(punkte + HtmlTags.createNBSP(1), cssHell));
		}
		
		if(sortButton[4])
		{
			sb.append(HtmlTags.wrapTD(boring + HtmlTags.createNBSP(1), csseigen));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(boring + HtmlTags.createNBSP(1), cssHell));
		}
		
		if(sortButton[5])
		{
			if(this.konto < 0.0)
			{
				sb.append(HtmlTags.wrapTD(StringFormat.formatMoney(this.konto) + HtmlTags.createEURO(), csseigens));
			}
			else
			{
				sb.append(HtmlTags.wrapTD(StringFormat.formatMoney(this.konto) + HtmlTags.createEURO(), csseigenh));
			}
		}
		else
		{
			if(this.konto < 0.0)
			{
				sb.append(HtmlTags.wrapTD(StringFormat.formatMoney(this.konto) + HtmlTags.createEURO(), cssSoll));
			}
			else
			{
				sb.append(HtmlTags.wrapTD(StringFormat.formatMoney(this.konto) + HtmlTags.createEURO(), cssHaben));
			}
		}
		
		if(sortButton[6])
		{
			if(this.bonus < 0.0)
			{
				sb.append(HtmlTags.wrapTD(StringFormat.formatMoney(this.bonus) + HtmlTags.createEURO(), csseigens));
			}
			else
			{
				sb.append(HtmlTags.wrapTD(StringFormat.formatMoney(this.bonus) + HtmlTags.createEURO(), csseigenh));
			}
		}
		else
		{
			if(this.bonus < 0.0)
			{
				sb.append(HtmlTags.wrapTD(StringFormat.formatMoney(this.bonus) + HtmlTags.createEURO(), cssSoll));
			}
			else
			{
				sb.append(HtmlTags.wrapTD(StringFormat.formatMoney(this.bonus) + HtmlTags.createEURO(), cssHaben));
			}
		}
		
		if(sortButton[7])
		{
			sb.append(HtmlTags.wrapTD(tagessiege + HtmlTags.createNBSP(1), csseigen));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(tagessiege + HtmlTags.createNBSP(1), cssHell));
		}

		if(sortButton[8])
		{
			sb.append(HtmlTags.wrapTD(tendenz + HtmlTags.createNBSP(1), csseigen));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(tendenz + HtmlTags.createNBSP(1), cssHell));
		}
		
		if(sortButton[9])
		{
			sb.append(HtmlTags.wrapTD(treffer + HtmlTags.createNBSP(1), csseigen));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(treffer + HtmlTags.createNBSP(1), cssHell));
		}
		
		if(sortButton[10])
		{
			sb.append(HtmlTags.wrapTD(volltreffer + HtmlTags.createNBSP(1), csseigen));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(volltreffer+ HtmlTags.createNBSP(1), cssHell));
		}
		
		if(sortButton[11])
		{
			sb.append(HtmlTags.wrapTD(nieten + HtmlTags.createNBSP(1), csseigen));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(nieten + HtmlTags.createNBSP(1), cssHell));
		}
		
		if(sortButton[12])
		{
			sb.append(HtmlTags.wrapTD(blind + HtmlTags.createNBSP(1), csseigen));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(blind + HtmlTags.createNBSP(1), cssHell));
		}
		
		if(sortButton[13])
		{
			sb.append(HtmlTags.wrapTD(hunger + HtmlTags.createNBSP(1), csseigen));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(hunger + HtmlTags.createNBSP(1), cssHell));
		}
		
		if(sortButton[14])
		{
			sb.append(HtmlTags.wrapTD(knapp + HtmlTags.createNBSP(1), csseigen));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(knapp + HtmlTags.createNBSP(1), cssHell));
		}

		if(sortButton[15])
		{
			sb.append(HtmlTags.wrapTD(StringFormat.formatMoney(waldi) + HtmlTags.createNBSP(1), csseigenh));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(StringFormat.formatMoney(waldi) + HtmlTags.createNBSP(1), cssHaben));
		}
		
		if(sortButton[16])
		{
			sb.append(HtmlTags.wrapTD(StringFormat.formatMoney(netzerdelling) + HtmlTags.createNBSP(1), csseigenh));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(StringFormat.formatMoney(netzerdelling) + HtmlTags.createNBSP(1), cssHaben));
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

	public void setPlatz(int p)
	{
		this.platz = p;
	}
	
	public void setBonus(BigDecimal b)
	{
		this.bonus = b.doubleValue();
	}
	
	public String getSortname()
	{
		return sortname;
	}

	public int getPlatz()
	{
		return platz;
	}

	public String getName()
	{
		return name;
	}

	public int getAnzahl()
	{
		return anzahl;
	}

	public int getPunkte()
	{
		return punkte;
	}

	public int getBoring()
	{
		return boring;
	}

	public double getKonto()
	{
		return konto;
	}

	public double getBonus()
	{
		return bonus;
	}

	public int getTagessiege()
	{
		return tagessiege;
	}

	public int getTendenz()
	{
		return tendenz;
	}

	public int getTreffer()
	{
		return treffer;
	}

	public int getExakt()
	{
		return volltreffer;
	}

	public int getNieten()
	{
		return nieten;
	}

	public int getBlind()
	{
		return blind;
	}

	public int getHunger()
	{
		return hunger;
	}

	public int getKnapp()
	{
		return knapp;
	}

	public double getWaldi()
	{
		return waldi;
	}

	public double getNetzerdelling()
	{
		return netzerdelling;
	}
	
	public int getTipperId()
	{
		return tipperId;
	}
	public int getTipperIdApril()
	{
		return tipperIdApril;
	}
	
	public void setSortButton(int s)
	{
		for(int i = 0; i < sortButton.length; i++)
		{
			sortButton[i] = false;
		}
		sortButton[s] = true;
	}

}
