package tables;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sorts.SortMainTableGeldGesamt;
import sorts.SortMainTableKonto;
import sorts.SortMainTableName;
import sorts.SortMainTablePunkteGesamt;
import sorts.SortMainTablePunkteSpieltag;
import sorts.SortMainTableSpiel;
import de.wepas.connector.EinstellungBeanRemote;
import de.wepas.connector.EinstellungException;
import de.wepas.connector.ForumBeanRemote;
import de.wepas.connector.ForumException;
import de.wepas.connector.SpielBeanRemote;
import de.wepas.connector.SpielException;
import de.wepas.connector.VereinBeanRemote;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnEinstellung;
import de.wepas.jpa.EnForum;
import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnSpieltag;
import de.wepas.jpa.EnTipper;
import de.wepas.livegoals.LivegoalsBeanRemote;
import de.wepas.util.HtmlTags;

public class MainTable
{
	private static Log log = LogFactory.getLog(MainTable.class);
	
	String csseigen = ApplConstants.CSSEIGEN;
	String cssweiss = ApplConstants.CSSWEISSL;
	String csstable1 = ApplConstants.CSSTABLE1;
	String cssdiv1 = ApplConstants.CSSDIV1;
	String cssdiv2 = ApplConstants.CSSDIV2;
	MainTablehead1 head1;
	MainTablehead2 head2;
	MainTablehead3 head3;	
	MainTablehead4 head4;
	EnForum ganzneu;
	EnForum neu;
	EnForum etwasaelter;
	EnForum aelter;
	EnForum nochaelter;
	Vector<MainTablerow> rows;
	Vector<EnSpiel> spiele;
	EinstellungBeanRemote einstellungStateless;
	ForumBeanRemote forumStateless;
	boolean adminuser = false;
	int idlogin = 0;
	boolean tunier = false;
	boolean owngetippt = true;
	
	public MainTable(EnTipper user, EnSpieltag spieltag, SpielBeanRemote spielStateless, 
			VereinBeanRemote vereinStateless, EinstellungBeanRemote einstellungStateless, ForumBeanRemote forumStateless, LivegoalsBeanRemote lifegoalsSingleton)
	{
		EnEinstellung tuniermodus = null;
		try
		{
			tuniermodus = einstellungStateless.getEinstellung("tunier")[0];
			this.tunier = tuniermodus.getAtEinstellungWertBool() == 1;
//			System.out.println("ICH BIN IM TUNIERMODUS = " + this.tunier);
//			this.tunier = true;
		}
			catch (EinstellungException e)
		{
			log.error("EinstellungException in Zeile 70 " + e.getMessage());
		}

		this.head1 = new MainTablehead1(tunier, spieltag, einstellungStateless);
		this.rows = new Vector<MainTablerow>();
		try
		{
			EnSpiel[] spielarray = spielStateless.getSpiele(spieltag);
			this.spiele = new Vector<EnSpiel>(spielarray.length);
			for (int i = 0; i < spielarray.length; i++)
			{
				this.spiele.add(spielarray[i]);
			}
		}
		catch (SpielException e)
		{
			e.printStackTrace();
		}
		this.head2 = new MainTablehead2(tunier, user, spieltag, this.spiele, einstellungStateless, vereinStateless, lifegoalsSingleton);
		this.head3 = new MainTablehead3(tunier, this.spiele);
		this.head4 = new MainTablehead4(tunier, spieltag, this.spiele);
		this.einstellungStateless = einstellungStateless;
		this.forumStateless = forumStateless;

		EnForum[] forumarray = new EnForum[2];
		try
		{
			forumarray = forumStateless.getForum();
		}
		catch (ForumException e)
		{
			e.printStackTrace();
		}
		
		if(forumarray.length > 0)
		{
			this.ganzneu = forumarray[0];
		}
		else
		{
			this.ganzneu = new EnForum();
		}
		if(forumarray.length > 1)
		{
			this.neu = forumarray[1];
		}
		else
		{
			this.neu = new EnForum();
		}
		if(forumarray.length > 2)
		{
			this.etwasaelter = forumarray[2];
		}
		else
		{
			this.etwasaelter = new EnForum();
		}
		if(forumarray.length > 3)
		{
			this.aelter = forumarray[3];
		}
		else
		{
			this.aelter = new EnForum();
		}
		if(forumarray.length > 4)
		{
			this.nochaelter = forumarray[4];
		}
		else
		{
			this.nochaelter = new EnForum();
		}
	}
	
	public Vector<EnSpiel> getSpiele()
	{
		return spiele;
	}

	public Vector<MainTablerow> getRows()
	{
		return rows;
	}
	
	public void calculatePlatz()
	{
		head4.setOwngetippt(owngetippt);
		Collections.sort(this.rows, new SortMainTablePunkteGesamt());
// -->1. Ersteinmal ermitteln wiviel Geld es zu verteilen gibt.
		double gesamteinsatz = 0.0;
		EnEinstellung erster = null;
		EnEinstellung zweiter = null;
		EnEinstellung dritter = null;
		EnEinstellung vierter = null;
		EnEinstellung fuenfter = null;
		EnEinstellung sechster = null;
		EnEinstellung siebter = null;
		EnEinstellung achter = null;
		EnEinstellung neunter = null;
		EnEinstellung zehnter = null;
		EnEinstellung elfter = null;
		EnEinstellung zwoelfter = null;
		EnEinstellung dreizehnter = null;
		EnEinstellung vierzehnter = null;
		try
		{
			erster = einstellungStateless.getEinstellung("gesamt01")[0];
			zweiter = einstellungStateless.getEinstellung("gesamt02")[0];
			dritter = einstellungStateless.getEinstellung("gesamt03")[0];
			vierter = einstellungStateless.getEinstellung("gesamt04")[0];
			fuenfter = einstellungStateless.getEinstellung("gesamt05")[0];
			sechster = einstellungStateless.getEinstellung("gesamt06")[0];
			siebter = einstellungStateless.getEinstellung("gesamt07")[0];
			achter = einstellungStateless.getEinstellung("gesamt08")[0];
			neunter = einstellungStateless.getEinstellung("gesamt09")[0];
			zehnter = einstellungStateless.getEinstellung("gesamt10")[0];
			elfter = einstellungStateless.getEinstellung("gesamt11")[0];
			zwoelfter = einstellungStateless.getEinstellung("gesamt12")[0];
			dreizehnter = einstellungStateless.getEinstellung("gesamt13")[0];
			vierzehnter = einstellungStateless.getEinstellung("gesamt14")[0];
		}
		catch (EinstellungException e)
		{
			log.error("EinstellungException in Zeile 196 " + e.getMessage());
		}
		Vector<EnEinstellung> realLzw = new Vector<EnEinstellung>();
		realLzw.add(erster);
		realLzw.add(zweiter);
		realLzw.add(dritter);
		realLzw.add(vierter);
		realLzw.add(fuenfter);
		realLzw.add(sechster);
		realLzw.add(siebter);
		realLzw.add(achter);
		realLzw.add(neunter);
		realLzw.add(zehnter);
		realLzw.add(elfter);
		realLzw.add(zwoelfter);
		realLzw.add(dreizehnter);
		realLzw.add(vierzehnter);
		
		gesamteinsatz = gesamteinsatz
				+ erster.getAtEinstellungWertDec()
						.add(zweiter.getAtEinstellungWertDec()
						.add(dritter.getAtEinstellungWertDec()
						.add(vierter.getAtEinstellungWertDec()
						.add(fuenfter.getAtEinstellungWertDec()
						.add(sechster.getAtEinstellungWertDec()
						.add(siebter.getAtEinstellungWertDec()
						.add(achter.getAtEinstellungWertDec()
						.add(neunter.getAtEinstellungWertDec()
						.add(zehnter.getAtEinstellungWertDec()	
						.add(elfter.getAtEinstellungWertDec()
						.add(zwoelfter.getAtEinstellungWertDec()
						.add(dreizehnter.getAtEinstellungWertDec()
						.add(vierzehnter.getAtEinstellungWertDec()	
								))))))))))))).doubleValue();
// -->2. Ermitteln wieviel jeder bezahlen muss
//		double einzeleinsatz = (gesamteinsatz / this.rows.size());

		Vector<MainTablerow> realTipper = new Vector<MainTablerow>();
		Vector<MainTablerow> vollzahlerBest = new Vector<MainTablerow>();
		Vector<Double> realGewinne = new Vector<Double>();
		Iterator<MainTablerow> iter1 = this.rows.iterator();
		Iterator<EnEinstellung> iter2 = realLzw.iterator();
		double gewinn = 0.0;
		MainTablerow comp = null;
		while (iter2.hasNext())
		{
			EnEinstellung einstellung = iter2.next();
			MainTablerow row = iter1.next();
			if(row.getVollzahler())
			{
				vollzahlerBest.add(row);
			}
			if (comp == null) // der allererste Tipper
			{
				realTipper.add(row);
				comp = row;
				gewinn = einstellung.getAtEinstellungWertDec().doubleValue();
			}
			else
			{
				if (comp.getPunktegesamt() == row.getPunktegesamt())
				{
					realTipper.add(row);
					comp = row;
					gewinn = gewinn + einstellung.getAtEinstellungWertDec().doubleValue();
				}
				else
				{
					comp = row;
					int anzahl = realTipper.size();
					double einzelgewinn = 0.0;
					if (gewinn > 0.0)
					{
						einzelgewinn = gewinn / anzahl;
					}
					for (int l = 0; l < anzahl; l++)
					{
						realGewinne.add(new Double(einzelgewinn));
					}
					realTipper.clear();
					realTipper.add(row);
					gewinn = einstellung.getAtEinstellungWertDec().doubleValue();
				}
			}
		}
		boolean ende = false;
		while (iter1.hasNext())
		{
			MainTablerow row = iter1.next();
			if(row.getVollzahler())
			{
				vollzahlerBest.add(row);
			}
			if (comp.getPunktegesamt() == row.getPunktegesamt())
			{
				realTipper.add(row);
				comp = row;
			}
			else if (!ende)
			{
				comp = row;
				int anzahl = realTipper.size();
				double einzelgewinn = 0.0;
				if (gewinn > 0.0)
				{
					einzelgewinn = gewinn / anzahl;
				}
				for (int l = 0; l < anzahl; l++)
				{
					realGewinne.add(new Double(einzelgewinn));
				}
				realTipper.clear();
				realTipper.add(row);
				ende = true;
			}
		}
		
		Iterator<Double> iter3 = realGewinne.iterator();
		Collections.sort(this.rows, new SortMainTablePunkteGesamt());
		Collections.sort(vollzahlerBest, new SortMainTablePunkteGesamt());
//		vollzahlerBest.firstElement().setVollzahlerbest(true);
		Iterator<MainTablerow> iter4 = this.rows.iterator();
		int i = 0;
		int p = 0;
		int platz = 0;
		while (iter4.hasNext())
		{
			i++;
			MainTablerow row = iter4.next();
			if (row.getPunktegesamt() != p)
			{
				platz = i;
				p = row.getPunktegesamt();
			}
			row.setPlatzgesamt(platz);
			if (iter3.hasNext())
			{
//				row.setBonus(new BigDecimal(iter3.next() - einzeleinsatz).round(new MathContext(2)));
				row.setBonus(new BigDecimal(iter3.next()));
			}
			else
			{
//				row.setBonus(new BigDecimal(einzeleinsatz * -1));
				row.setBonus(new BigDecimal(0));
			}
		}

//Wer führt die Punkte?	Wer ist letzter?	
		Collections.sort(this.rows, new SortMainTablePunkteGesamt());
		Iterator<MainTablerow> iter5 = this.rows.iterator();
		MainTablerow firstP = iter5.next();
		MainTablerow secondP = iter5.next();
		MainTablerow thirdP = iter5.next();
		firstP.setLeader(true);
		if(secondP.getPunktegesamt() == firstP.getPunktegesamt())
		{
			secondP.setLeader(true);
		}
		if(thirdP.getPunktegesamt() == firstP.getPunktegesamt())
		{
			thirdP.setLeader(true);
		}
		MainTablerow prelastP = null;
		MainTablerow bubbleP = null;
		MainTablerow lastP = null;
		boolean nochKeinBubble = true;
		while(iter5.hasNext())
		{
			prelastP = lastP;
			lastP = iter5.next();
			if(nochKeinBubble && lastP.getBonus().intValue() == 0)
			{
				bubbleP = lastP;
				nochKeinBubble = false;
			}
			
		}
		bubbleP.setBubble(true);
//		lastP.setLetzter(true);
		if(prelastP.getPunktegesamt() == lastP.getPunktegesamt())
		{
//			prelastP.setLetzter(true);
		}
		

//Wer führt im Geld?
		Collections.sort(this.rows, new SortMainTableGeldGesamt());
		Iterator<MainTablerow> iter6 = this.rows.iterator();
		MainTablerow firstG = iter6.next();
		firstG.setLeader(true);
	}
	
	public String getHtmlThead(boolean spieltaganzeige)
	{
		StringBuffer sb = new StringBuffer("");
		this.head1.setSpieltaganzeige(spieltaganzeige);
		this.head2.setSpieltaganzeige(spieltaganzeige);
		this.head2.setAdminuser(adminuser);
		sb.append(this.head1.getHtmlRow());
		sb.append(this.head2.getHtmlRow());
		sb.append(this.head3.getHtmlRow());
		sb.append(this.head4.getHtmlRow());
		return sb.toString();
	}
	
	public String getHtmlTbody(boolean spieltaganzeige)
	{
		StringBuffer sb = new StringBuffer("");
//		if(ApplConstants.FIXHEAD)
//		{
//			sb.append("<tr><td height='210'>&nbsp;</td></tr>\n");
//		}
		Vector<MainTablerow> table = this.getRows();
		Iterator<MainTablerow> iter6 = table.iterator();
		while (iter6.hasNext())
		{
			sb.append(iter6.next().getHtmlRow(spieltaganzeige));
		}	
		sb.append(this.head3.getHtmlRow());
		sb.append(this.head2.getHtmlRow());
		return sb.toString();
	}

	
	public String getHtmlTfoot()
	{
		StringBuffer sb = new StringBuffer("");
		int w1 = ApplConstants.WIDTHX + ApplConstants.WIDTHS + ApplConstants.WIDTHM;
		int w2 = (ApplConstants.WIDTHM * 11) + (ApplConstants.WIDTHL * 3) + (ApplConstants.WIDTHS);
		try
		{
			sb.append(HtmlTags.wrapTR(HtmlTags.wrapTD(HtmlTags.wrapB("Die letzen Einträge im Forum:"), null, 3), null));
			sb.append(HtmlTags.wrapTR(HtmlTags.wrapTD(w1, nochaelter.getAtForumText1(), cssdiv1, 3) + HtmlTags.wrapTD(w2, nochaelter.getAtForumText2(), cssdiv2, 15), null));
			sb.append(HtmlTags.wrapTR(HtmlTags.wrapTD(w1, aelter.getAtForumText1(), cssdiv1, 3) + HtmlTags.wrapTD(w2, aelter.getAtForumText2(), cssdiv2, 15), null));
			sb.append(HtmlTags.wrapTR(HtmlTags.wrapTD(w1, etwasaelter.getAtForumText1(), cssdiv1, 3) + HtmlTags.wrapTD(w2, etwasaelter.getAtForumText2(), cssdiv2, 15), null));
			sb.append(HtmlTags.wrapTR(HtmlTags.wrapTD(w1, neu.getAtForumText1(), cssdiv1, 3) + HtmlTags.wrapTD(w2, neu.getAtForumText2(), cssdiv2, 15), null));
			sb.append(HtmlTags.wrapTR(HtmlTags.wrapTD(w1, ganzneu.getAtForumText1(), cssdiv1, 3) + HtmlTags.wrapTD(w2, ganzneu.getAtForumText2(), cssdiv2, 15), null));
		}
		catch(Exception e)
		{
			
		}
		return sb.toString();
//		return HtmlTags.createBR(1) + "Die letzten fünf Einträge im Forum:" + HtmlTags.wrapTABLE(sb.toString(), csstable1);
	}

//	public String getHtml(boolean spieltaganzeige)
//	{
//		StringBuffer sb = new StringBuffer();
//
//		
//		sb = new StringBuffer("");
//
//	}
	
	public void sortTipperName()
	{
		head4.setSortButton(-4);
		Collections.sort(this.rows, new SortMainTableName());
	}

	public void sortTipperPunkteSpieltag()
	{
		head4.setSortButton(-3);
		Collections.sort(this.rows, new SortMainTablePunkteSpieltag());
	}
	
	public void sortTipperPunkteGesamt()
	{
		head4.setSortButton(-2);
		Collections.sort(this.rows, new SortMainTablePunkteGesamt());
	}
	
	public void sortTipperKonto()
	{ 
		head4.setSortButton(-1);
		Collections.sort(this.rows, new SortMainTableKonto());
	}
	
	public void sortSpiel(int spielId)
	{
		head4.setSortButton(spielId);
		Collections.sort(this.rows, new SortMainTableSpiel(spielId));
	}
	
	public boolean isAdminuser()
	{
		return adminuser;
	}
	
	public void setAdminuser(boolean adminuser)
	{
		this.adminuser = adminuser;
	}
	
	public void setIdlogin(int idlogin)
	{
		this.idlogin = idlogin;
	}
	
	public void setOwnGetippt(boolean ownGetippt)
	{
		this.owngetippt = ownGetippt;
	}
}
