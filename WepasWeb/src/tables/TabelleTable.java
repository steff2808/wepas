package tables;

import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sorts.SortTabelleTableAuswaerts;
import sorts.SortTabelleTableGesamt;
import sorts.SortTabelleTableHeim;
import sorts.SortTabelleTableHin;
import sorts.SortTabelleTableRueck;
import sorts.SortTabelleTableTipper;
import de.wepas.connector.SpielBeanRemote;
import de.wepas.connector.SpielException;
import de.wepas.connector.TippBeanRemote;
import de.wepas.connector.VereinBeanRemote;
import de.wepas.connector.VereinException;
import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnTipper;
import de.wepas.jpa.EnVerein;

public class TabelleTable
{
	private static Log log = LogFactory.getLog(TabelleTable.class);
	
	TippBeanRemote tippStateless = null;
	Vector<TabelleTablerow> rows;
	TabelleTablehead1 head1;
	Vector<EnSpiel> spiele = null;
	Vector<EnVerein> vereine = null;
	char flag;
	
	public TabelleTable(EnTipper user, VereinBeanRemote vereinStateless, TippBeanRemote tippStateless, SpielBeanRemote spielStateless)
	{
		super();
		this.tippStateless = tippStateless;
		this.rows = new Vector<TabelleTablerow>(18); // ;-)
		this.head1 = new TabelleTablehead1();
		try
		{
			EnVerein[] vereinarray = vereinStateless.getVerein();
			vereine = new Vector<EnVerein>(vereinarray.length);
			for (int i = 0; i < vereinarray.length; i++)
			{
				vereine.add(vereinarray[i]);
			}
		}
		catch (VereinException e)
		{
			log.error("VereinException Zeile 47 " + e.getMessage());
		}
		
		try
		{
			EnSpiel[] spielarray = spielStateless.getSpiele();
			spiele = new Vector<EnSpiel>(spielarray.length);
			for (int i = 0; i < spielarray.length; i++)
			{
				spiele.add(spielarray[i]);
			}
		}
		catch (SpielException e)
		{
			log.error("SpielException Zeile 68 " + e.getMessage());
		}
		
		Iterator<EnVerein> iter1 = this.vereine.iterator();
		while (iter1.hasNext())
		{
			EnVerein verein = iter1.next();
			if(verein.getIdVerein() < 19)
			{
				TabelleTablerow row = new TabelleTablerow(verein, false);
//			Iterator<EnSpiel> iter2 = spiele.iterator();
//			while (iter2.hasNext())
//			{
//				EnSpiel spiel = iter2.next();
//				row.countGame(spiel);
//				EnTipp tipp = null;
//				try
//				{
//					tipp = tippStateless.getTipps(user, spiel);
//					if(tipp != null)
//					{
//						row.countTipp(tipp, spiel);
//					}
//				}
//				catch(TippException e)
//				{
//					log.error("TippException in Zeile 92 " + e.getMessage());
//				}
//			}
				this.rows.add(row);
			}
		}
		
//		GregorianCalendar gc = new GregorianCalendar();
//		if ((gc.get(GregorianCalendar.MONTH) == 3 && gc.get(GregorianCalendar.DAY_OF_MONTH) == 2) || user.getIdTipper() == 1 || user.getIdTipper() == 5
//				|| user.getIdTipper() == 2 || user.getIdTipper() == 27)
//		{
//			// Bei Günter nur am 2. April bei allen anderen unter 13 + Detlef
//			// Saft immer;
//			EnVerein verein = new EnVerein();
//			verein.setAtVereinKurz("gm");
//			verein.setAtVereinName("Günter Mutz");
//			verein.setIdVerein(99);
//			this.rows.add(new TabelleTablerow(verein, false));
//		}
		
		this.sortGesamt();
	}
	
	public String getHtmlRow()
	{
		StringBuffer sb = new StringBuffer("");
		switch(flag)
		{
			case 'H': sb.append(this.head1.getHtmlHRow()); break;
			case 'A': sb.append(this.head1.getHtmlARow()); break;
			default: sb.append(this.head1.getHtmlGRow()); break;
		}
		Iterator<TabelleTablerow> iter1 = this.rows.iterator();
		while (iter1.hasNext())
		{
			switch(flag)
			{
				case 'H': sb.append(iter1.next().getHtmlRowH()); break;
				case 'A': sb.append(iter1.next().getHtmlRowA()); break;
				case 'I': sb.append(iter1.next().getHtmlRowHr()); break;
				case 'R': sb.append(iter1.next().getHtmlRowRr()); break;
				case 'T': sb.append(iter1.next().getHtmlRowT()); break;
				default: sb.append(iter1.next().getHtmlRowG()); 
			}
		}
		return sb.toString();
	}
	
	public void sortGesamt()
	{
		flag = 'G';
		Collections.sort(rows, new SortTabelleTableGesamt());
		Iterator<TabelleTablerow> iter4 = this.rows.iterator();
		int i = 0;
		while (iter4.hasNext())
		{
			iter4.next().setPlatz(++i);
		}
	}
	
	public void sortHeim()
	{
		flag = 'H';
		Collections.sort(rows, new SortTabelleTableHeim());
		Iterator<TabelleTablerow> iter5 = this.rows.iterator();
		int i = 0;
		while (iter5.hasNext())
		{
			iter5.next().setPlatz(++i);
		}
	}
	
	public void sortAuswaerts()
	{
		flag = 'A';
		Collections.sort(rows, new SortTabelleTableAuswaerts());
		Iterator<TabelleTablerow> iter6 = this.rows.iterator();
		int i = 0;
		while (iter6.hasNext())
		{
			iter6.next().setPlatz(++i);
		}
	}
	
	public void sortHinrunde()
	{
		flag = 'I';
		Collections.sort(rows, new SortTabelleTableHin());
		Iterator<TabelleTablerow> iter7 = this.rows.iterator();
		int i = 0;
		while (iter7.hasNext())
		{
			iter7.next().setPlatz(++i);
		}
	}
	
	public void sortRueckrunde()
	{
		flag = 'R';
		Collections.sort(rows, new SortTabelleTableRueck());
		Iterator<TabelleTablerow> iter8 = this.rows.iterator();
		int i = 0;
		while (iter8.hasNext())
		{
			iter8.next().setPlatz(++i);
		}
	}
	
	public void sortTipper()
	{ 
		flag = 'T';
		Collections.sort(rows, new SortTabelleTableTipper());
		Iterator<TabelleTablerow> iter9 = this.rows.iterator();
		int i = 0;
		while (iter9.hasNext())
		{
			iter9.next().setPlatz(++i);
		}
	}
}
