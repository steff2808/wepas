package tables;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sorts.SortStatistikTableAnzahl;
import sorts.SortStatistikTableBlind;
import sorts.SortStatistikTableBonus;
import sorts.SortStatistikTableBoring;
import sorts.SortStatistikTableExakt;
import sorts.SortStatistikTableHunger;
import sorts.SortStatistikTableKnapp;
import sorts.SortStatistikTableKonto;
import sorts.SortStatistikTableName;
import sorts.SortStatistikTableNetzerdelling;
import sorts.SortStatistikTableNieten;
import sorts.SortStatistikTablePunkte;
import sorts.SortStatistikTableTagessiege;
import sorts.SortStatistikTableTendenz;
import sorts.SortStatistikTableTreffer;
import sorts.SortStatistikTableWaldi;
import de.wepas.connector.EinstellungException;
import de.wepas.connector.TippBeanRemote;
import de.wepas.connector.TipperBeanRemote;
import de.wepas.connector.TipperException;
import de.wepas.connector.WetteBeanRemote;
import de.wepas.connector.EinstellungBeanRemote;
import de.wepas.jpa.EnEinstellung;
import de.wepas.jpa.EnTipper;

public class StatistikTable
{
	private static Log log = LogFactory.getLog(StatistikTable.class);
	
	Vector<StatistikTablerow> rows;
	StatistikTablehead1 head1;
	StatistikTablehead2 head2;
	
	public StatistikTable(EnTipper user, WetteBeanRemote wetteStateless, 
			TipperBeanRemote tipperStateless, TippBeanRemote tippStateless, EinstellungBeanRemote einstellungStateless)
	{
		super();
		this.rows = new Vector<StatistikTablerow>(50);
		this.head1 = new StatistikTablehead1();
		this.head2 = new StatistikTablehead2();
		Vector<EnTipper> tipper = null;
		try
		{
			EnTipper[] tipperarray = tipperStateless.getTipper();
			tipper = new Vector<EnTipper>(tipperarray.length);
			for (int i = 0; i < tipperarray.length; i++)
			{
				tipper.add(tipperarray[i]);
			}
			Iterator<EnTipper> iter1 = tipper.iterator();
			while (iter1.hasNext())
			{
				this.rows.add(new StatistikTablerow(user, iter1.next(), wetteStateless, tippStateless));
			}
		}
		catch (TipperException e)
		{
			log.error("TipperException Zeile 69 " + e.getMessage());
		}
		this.calculatePlatz(einstellungStateless);
		this.sortPunkte();
	}
	
	public String getHtml()
	{
		StringBuffer sb = new StringBuffer("");
		sb.append(this.head1.getHtmlRow());
		sb.append(this.head2.getHtmlRow());
		Iterator<StatistikTablerow> iter1 = this.rows.iterator();
		while (iter1.hasNext())
		{
			sb.append(iter1.next().getHtmlRow());
		}
		return sb.toString();
	}
	
	public void sortName()
	{
		head2.setSortButton(1);
		Collections.sort(this.rows, new SortStatistikTableName());
		Iterator<StatistikTablerow> iterName= this.rows.iterator();
		while(iterName.hasNext())
		{
			iterName.next().setPlatz(0);
		}
	}
	
	public void sortAnzahl()
	{
		head2.setSortButton(2);
		Collections.sort(this.rows, new SortStatistikTableAnzahl());
		int gw = 999;
		int p = 0;
		Iterator<StatistikTablerow> iterAnzahl = this.rows.iterator();
		while (iterAnzahl.hasNext())
		{
			p++;
			StatistikTablerow row = iterAnzahl.next();
			row.setSortButton(2);
			if (row.getAnzahl() == gw)
			{
				row.setPlatz(0);
			}
			else
			{
				row.setPlatz(p);
				gw = row.getAnzahl();
			}
		}
	}
	
	public void sortKonto()
	{
		head2.setSortButton(5);
		Collections.sort(this.rows, new SortStatistikTableKonto());
		double gw = 99.99;
		int p = 0;
		Iterator<StatistikTablerow> iterKonto = this.rows.iterator();
		while (iterKonto.hasNext())
		{
			p++;
			StatistikTablerow row = iterKonto.next();
			row.setSortButton(5);
			if (row.getKonto() == gw)
			{
				row.setPlatz(0);
			}
			else
			{
				row.setPlatz(p);
				gw = row.getKonto();
			}
		}
	}
	
	public void sortPunkte()
	{
		head2.setSortButton(3);
		Collections.sort(this.rows, new SortStatistikTablePunkte());
		int gw = 999;
		int p = 0;
		Iterator<StatistikTablerow> iterPunkte = this.rows.iterator();
		while (iterPunkte.hasNext())
		{
			p++;
			StatistikTablerow row = iterPunkte.next();
			row.setSortButton(3);
			if (row.getPunkte() == gw)
			{
				row.setPlatz(0);
			}
			else
			{
				row.setPlatz(p);
				gw = row.getPunkte();
			}
		}
	}
	
	public void sortBoring()
	{
		head2.setSortButton(4);
		Collections.sort(this.rows, new SortStatistikTableBoring());
		int gw = 999;
		int p = 0;
		Iterator<StatistikTablerow> iterBoring = this.rows.iterator();
		while (iterBoring.hasNext())
		{
			p++;
			StatistikTablerow row = iterBoring.next();
			row.setSortButton(4);
			if (row.getBoring() == gw)
			{
				row.setPlatz(0);
			}
			else
			{
				row.setPlatz(p);
				gw = row.getBoring();
			}
		}
	}
	
	public void sortBonus()
	{
		head2.setSortButton(6);
		Collections.sort(this.rows, new SortStatistikTableBonus());
		double gw = 99.99;
		int p = 0;
		Iterator<StatistikTablerow> iterBonus = this.rows.iterator();
		while (iterBonus.hasNext())
		{
			p++;
			StatistikTablerow row = iterBonus.next();
			row.setSortButton(6);
			if (row.getBonus() == gw)
			{
				row.setPlatz(0);
			}
			else
			{
				row.setPlatz(p);
				gw = row.getBonus();
			}
		}
	}
	
	public void sortTagessiege()
	{
		head2.setSortButton(7);
		Collections.sort(this.rows, new SortStatistikTableTagessiege());
		int gw = 999;
		int p = 0;
		Iterator<StatistikTablerow> iterTagessiege = this.rows.iterator();
		while (iterTagessiege.hasNext())
		{
			p++;
			StatistikTablerow row = iterTagessiege.next();
			row.setSortButton(7);
			if (row.getTagessiege() == gw)
			{
				row.setPlatz(0);
			}
			else
			{
				row.setPlatz(p);
				gw = row.getTagessiege();
			}
		}
	}
	
	public void sortTendenz()
	{
		head2.setSortButton(8);
		Collections.sort(this.rows, new SortStatistikTableTendenz());
		int gw = 999;
		int p = 0;
		Iterator<StatistikTablerow> iterTendenz = this.rows.iterator();
		while (iterTendenz.hasNext())
		{
			p++;
			StatistikTablerow row = iterTendenz.next();
			row.setSortButton(8);
			if (row.getTendenz() == gw)
			{
				row.setPlatz(0);
			}
			else
			{
				row.setPlatz(p);
				gw = row.getTendenz();
			}
		}
	}
	
	public void sortTreffer()
	{
		head2.setSortButton(9);
		Collections.sort(this.rows, new SortStatistikTableTreffer());
		int gw = 999;
		int p = 0;
		Iterator<StatistikTablerow> iterTreffer = this.rows.iterator();
		while (iterTreffer.hasNext())
		{
			p++;
			StatistikTablerow row = iterTreffer.next();
			row.setSortButton(9);
			if (row.getTreffer() == gw)
			{
				row.setPlatz(0);
			}
			else
			{
				row.setPlatz(p);
				gw = row.getTreffer();
			}
		}
	}
	
	public void sortExakt()
	{
		head2.setSortButton(10);
		Collections.sort(this.rows, new SortStatistikTableExakt());
		int gw = 999;
		int p = 0;
		Iterator<StatistikTablerow> iterExakt = this.rows.iterator();
		while (iterExakt.hasNext())
		{
			p++;
			StatistikTablerow row = iterExakt.next();
			row.setSortButton(10);
			if (row.getExakt() == gw)
			{
				row.setPlatz(0);
			}
			else
			{
				row.setPlatz(p);
				gw = row.getExakt();
			}
		}
	}
	
	public void sortNieten()
	{
		head2.setSortButton(11);
		Collections.sort(this.rows, new SortStatistikTableNieten());
		int gw = 999;
		int p = 0;
		Iterator<StatistikTablerow> iterNieten = this.rows.iterator();
		while (iterNieten.hasNext())
		{
			p++;
			StatistikTablerow row = iterNieten.next();
			row.setSortButton(11);
			if (row.getNieten() == gw)
			{
				row.setPlatz(0);
			}
			else
			{
				row.setPlatz(p);
				gw = row.getNieten();
			}
		}
	}
	
	public void sortBlind()
	{
		head2.setSortButton(12);
		Collections.sort(this.rows, new SortStatistikTableBlind());
		int gw = 999;
		int p = 0;
		Iterator<StatistikTablerow> iterBlind = this.rows.iterator();
		while (iterBlind.hasNext())
		{
			p++;
			StatistikTablerow row = iterBlind.next();
			row.setSortButton(12);
			if (row.getBlind() == gw)
			{
				row.setPlatz(0);
			}
			else
			{
				row.setPlatz(p);
				gw = row.getBlind();
			}
		}
	}
	
	public void sortHunger()
	{
		head2.setSortButton(13);
		Collections.sort(this.rows, new SortStatistikTableHunger());
		int gw = 999;
		int p = 0;
		Iterator<StatistikTablerow> iterHunger = this.rows.iterator();
		while (iterHunger.hasNext())
		{
			p++;
			StatistikTablerow row = iterHunger.next();
			row.setSortButton(13);
			if (row.getHunger() == gw)
			{
				row.setPlatz(0);
			}
			else
			{
				row.setPlatz(p);
				gw = row.getHunger();
			}
		}
	}
	
	public void sortKnapp()
	{
		head2.setSortButton(14);
		Collections.sort(this.rows, new SortStatistikTableKnapp());
		int gw = 999;
		int p = 0;
		Iterator<StatistikTablerow> iterKnapp = this.rows.iterator();
		while (iterKnapp.hasNext())
		{
			p++;
			StatistikTablerow row = iterKnapp.next();
			row.setSortButton(14);
			if (row.getKnapp() == gw)
			{
				row.setPlatz(0);
			}
			else
			{
				row.setPlatz(p);
				gw = row.getKnapp();
			}
		}
	}
	
	public void sortWaldi()
	{
		head2.setSortButton(15);
		Collections.sort(this.rows, new SortStatistikTableWaldi());
		double gw = 99.99;
		int p = 0;
		Iterator<StatistikTablerow> iterWaldi = this.rows.iterator();
		while (iterWaldi.hasNext())
		{
			p++;
			StatistikTablerow row = iterWaldi.next();
			row.setSortButton(15);
			if (row.getWaldi() == gw)
			{
				row.setPlatz(0);
			}
			else
			{
				row.setPlatz(p);
				gw = row.getWaldi();
			}
		}
	}
	
	public void sortNetzerdelling()
	{
		head2.setSortButton(16);
		Collections.sort(this.rows, new SortStatistikTableNetzerdelling());
		double gw = 99.99;
		int p = 0;
		Iterator<StatistikTablerow> iterNetzerdelling = this.rows.iterator();
		while (iterNetzerdelling.hasNext())
		{
			p++;
			StatistikTablerow row = iterNetzerdelling.next();
			row.setSortButton(16);
			if (row.getNetzerdelling() == gw)
			{
				row.setPlatz(0);
			}
			else
			{
				row.setPlatz(p);
				gw = row.getNetzerdelling();
			}
		}
	}
	
	private void calculatePlatz(EinstellungBeanRemote einstellungStateless)
	{
		Collections.sort(this.rows, new SortStatistikTablePunkte());
		// -->1. Ersteinmal ermitteln wiviel Geld es zu verteilen gibt.
		double gesamteinsatz = 0.0;
		EnEinstellung erster = null;
		EnEinstellung zweiter = null;
		EnEinstellung dritter = null;
		EnEinstellung vierter = null;
		try
		{
			erster = einstellungStateless.getEinstellung("gesamt01")[0];
			zweiter = einstellungStateless.getEinstellung("gesamt02")[0];
			dritter = einstellungStateless.getEinstellung("gesamt03")[0];
			vierter = einstellungStateless.getEinstellung("gesamt04")[0];
		}
		catch (EinstellungException e)
		{
			log.error("EinstellungException in Zeile 445 " + e.getMessage());
		}
		Vector<EnEinstellung> realLzw = new Vector<EnEinstellung>();
		realLzw.add(erster);
		realLzw.add(zweiter);
		realLzw.add(dritter);
		realLzw.add(vierter);
		
		gesamteinsatz = gesamteinsatz
				+ erster.getAtEinstellungWertDec()
						.add(zweiter.getAtEinstellungWertDec().add(dritter.getAtEinstellungWertDec().add(vierter.getAtEinstellungWertDec()))).doubleValue();
		// -->2. Ermitteln wieviel jeder bezahlen muss
		double einzeleinsatz = (gesamteinsatz / this.rows.size());
		Vector<StatistikTablerow> realTipper = new Vector<StatistikTablerow>();
		Vector<Double> realGewinne = new Vector<Double>();
		Iterator<StatistikTablerow> iter1 = this.rows.iterator();
		Iterator<EnEinstellung> iter2 = realLzw.iterator();
		double gewinn = 0.0;
		StatistikTablerow comp = null;
		while (iter2.hasNext())
		{
			EnEinstellung einstellung = iter2.next();
			StatistikTablerow row = iter1.next();
			if (comp == null) // der allererste Tipper
			{
				realTipper.add(row);
				comp = row;
				gewinn = einstellung.getAtEinstellungWertDec().doubleValue();
			}
			else
			{
				if (comp.getPunkte() == row.getPunkte())
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
			StatistikTablerow row = iter1.next();
			if (comp.getPunkte() == row.getPunkte())
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
		Collections.sort(this.rows, new SortStatistikTablePunkte());
		Iterator<StatistikTablerow> iter4 = this.rows.iterator();
		int i = 0;
		int p = 0;
		while (iter4.hasNext())
		{
			i++;
			StatistikTablerow row = iter4.next();
			if (row.getPunkte() != p)
			{
				p = row.getPunkte();
				row.setPlatz(i);
			}
			else
			{
				row.setPlatz(0);
			}
			if (iter3.hasNext())
			{
				row.setBonus(new BigDecimal(iter3.next() - einzeleinsatz).round(new MathContext(2)));
			}
			else
			{
				row.setBonus(new BigDecimal(einzeleinsatz * -1));
			}
			row.setBonus(new BigDecimal(row.getBonus() + row.getKonto()));
		}
	}
}
