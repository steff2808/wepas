package tables;

import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import sorts.SortWorstcaseTablerow;
import de.wepas.connector.TippBeanLocal;
import de.wepas.connector.TippException;
import de.wepas.connector.TipperBeanLocal;
import de.wepas.connector.TipperException;
import de.wepas.jpa.EnTipp;
import de.wepas.jpa.EnTipper;
import de.wepas.livegoals.LivegoalsObject;
import de.wepas.livegoals.Livespiel;
import de.wepas.livegoals.LivestatusEnum;
import de.wepas.util.HtmlTags;
import de.wepas.util.LookupLocalService;

public class WorstcaseTable
{
	private EnTipper tipper;
	private Livespiel livespiel1;
	private Livespiel livespiel2;
	private int platz;
	private int punkte;
	private Vector<WorstcaseTablerow> worstcasetablerows;
	
	private int myworstplatz;
	private int myworstpunkte;
	private Livespiel myworstlivespiel1;
	private Livespiel myworstlivespiel2;
	private Vector<WorstcaseTablerow> myworstcasetablerows;
	boolean mybineinzelsieger;
	private WorstcaseTablehead2 head2;
	private WorstcaseTablehead3 head3;
	
	public WorstcaseTable(LivegoalsObject livegoalsobject, EnTipper tipper)
	{
		super();
		this.myworstplatz = 999;
		this.myworstpunkte = 0;
		this.myworstlivespiel1 = null;
		this.myworstlivespiel2 = null;
		this.mybineinzelsieger = false;
		this.myworstcasetablerows = null;
		this.head2 = new WorstcaseTablehead2(livegoalsobject);
		this.head3 = new WorstcaseTablehead3(livegoalsobject.getLivespiele());
		
		this.tipper = tipper;
		Iterator<Livespiel> livespiele = livegoalsobject.getLivespiele().iterator();
		Vector<Livespiel> pruefen = new Vector<Livespiel>();
		while(livespiele.hasNext())
		{
			Livespiel livespiel = livespiele.next();
			if(livespiel.getStatus() ==  LivestatusEnum.RUNNING || livespiel.getStatus() == LivestatusEnum.SETUP)
			{
				pruefen.add(livespiel);
			}
		}
		if(pruefen.size() == 2)
		{
			this.livespiel1 = pruefen.firstElement();
			this.livespiel2 = pruefen.lastElement();
		}
		else if(pruefen.size() == 1)
		{
			this.livespiel1 = pruefen.firstElement();
			this.livespiel2 = null;
		}
		else
		{
			throw new RuntimeException("Ungültige Menge Livespiele für Worstcaseauswertung");
		}
		
		TipperBeanLocal trbl = LookupLocalService.lookupTipperBeanLocal();
		TippBeanLocal tpbl = LookupLocalService.lookupTippBeanLocal();
		
		EnTipper[] tippersarray = null;
		try
		{
			tippersarray = trbl.getTipper();
		}
		catch (TipperException e)
		{
			e.printStackTrace();
		}

		this.worstcasetablerows = new Vector<WorstcaseTablerow>();
		for(int i = 0; i < tippersarray.length; i++)
		{
			Vector<EnTipp> tipps = null;
			EnTipp[] tipparray = null;
			try
			{
				tipparray = tpbl.getTipps(tippersarray[i], livegoalsobject.getSpieltag());
			}
			catch (TippException e)
			{
				e.printStackTrace();
			}	
			tipps = new Vector<EnTipp>(tipparray.length);
			for(int j = 0; j < tipparray.length; j++)
			{
				tipps.add(tipparray[j]);
			}
			WorstcaseTablerow worstcasetablerow = new WorstcaseTablerow(tippersarray[i], tipps);
			if(tippersarray[i].getIdTipper() == tipper.getIdTipper())
			{
				worstcasetablerow.setOwn(true);
			}
			this.worstcasetablerows.add(worstcasetablerow);
//Jetzt habe ich ein Vector mit den Worstcaserows aller Tipper zum auswerten.			
		}
	}
	
	public void analyse()
	{
//-->1. Alle zu analysierenden Möglichkeiten in einen Vector speichern
		Vector<WorstcaseSimulation> wcsv = new Vector<WorstcaseSimulation>();
		wcsv.add(new WorstcaseSimulation(0, 0));
		wcsv.add(new WorstcaseSimulation(1, 1));
		wcsv.add(new WorstcaseSimulation(2, 2));
		wcsv.add(new WorstcaseSimulation(3, 3));
		wcsv.add(new WorstcaseSimulation(6, 0));
		wcsv.add(new WorstcaseSimulation(0, 6));
		wcsv.add(new WorstcaseSimulation(5, 0));
		wcsv.add(new WorstcaseSimulation(0, 5));
		wcsv.add(new WorstcaseSimulation(3, 0));
		wcsv.add(new WorstcaseSimulation(0, 3));
		wcsv.add(new WorstcaseSimulation(4, 1));
		wcsv.add(new WorstcaseSimulation(1, 4));
		wcsv.add(new WorstcaseSimulation(2, 0));
		wcsv.add(new WorstcaseSimulation(0, 2));
		wcsv.add(new WorstcaseSimulation(3, 1));
		wcsv.add(new WorstcaseSimulation(1, 3));
		wcsv.add(new WorstcaseSimulation(4, 2));
		wcsv.add(new WorstcaseSimulation(2, 4));
		wcsv.add(new WorstcaseSimulation(1, 0));
		wcsv.add(new WorstcaseSimulation(0, 1));
		wcsv.add(new WorstcaseSimulation(2, 1));
		wcsv.add(new WorstcaseSimulation(1, 2));
		wcsv.add(new WorstcaseSimulation(3, 2));
		wcsv.add(new WorstcaseSimulation(2, 3));
		
//-->2. Nur die Möglichkeiten untersuchen die anhand des Livespiels1 noch möglich sind		
		Vector<WorstcaseSimulation>possible1 = new Vector<WorstcaseSimulation>();
		Iterator<WorstcaseSimulation> iter1 = wcsv.iterator();
		while(iter1.hasNext())
		{
			WorstcaseSimulation wcs = iter1.next();
			if(wcs.getH() >= this.livespiel1.getNowToreheim() && wcs.getG() >= this.livespiel1.getNowToregast())
			{
				possible1.add(wcs);
			}
		}
		
//-->3. Nur die Möglichkeiten untersuchen die anhand des Livespiels2 noch möglich sind		
		Vector<WorstcaseSimulation>possible2 = null;
		if(this.livespiel2 != null)
		{
			possible2 = new Vector<WorstcaseSimulation>();
			Iterator<WorstcaseSimulation> iter2 = wcsv.iterator();
			while(iter2.hasNext())
			{
				WorstcaseSimulation wcs = iter2.next();
				if(wcs.getH() >= this.livespiel2.getNowToreheim() && wcs.getG() >= this.livespiel2.getNowToregast())
				{
					possible2.add(wcs);
				}
			}
		}
		
//-->4. Alle Möglichkeiten die noch mit Livespiel1 möglich sind analysieren
		Iterator<WorstcaseSimulation> iterp1 = possible1.iterator();
		while(iterp1.hasNext())
		{
			WorstcaseSimulation wcs1 = iterp1.next();
			Livespiel ls1c = (Livespiel)this.livespiel1.clone();
			ls1c.setNowToreheim(wcs1.getH());
			ls1c.setNowToregast(wcs1.getG());
			
			if(this.livespiel2 != null)
			{
//-->Zwei Spiele offen
				Iterator<WorstcaseSimulation> iterp2 = possible2.iterator();
				while(iterp2.hasNext())
				{
					WorstcaseSimulation wcs2 = iterp2.next();
					Livespiel ls2c = (Livespiel)this.livespiel2.clone();
					ls2c.setNowToreheim(wcs2.getH());
					ls2c.setNowToregast(wcs2.getG());
				
					Iterator<WorstcaseTablerow> iter3 = this.worstcasetablerows.iterator();
					while(iter3.hasNext())
					{
						WorstcaseTablerow Wcr1 = iter3.next();
//						System.out.println(wcr1.getTipper().getAtTipperName() + ls1c.getNowToreheim() + ls1c.getNowToregast() + "/" + ls2c.getNowToreheim() + ls2c.getNowToregast());
						Wcr1.simulateFirst(ls1c);
						Wcr1.simulateSecond(ls2c);
					}
//-->6. Die Rows sortieren
					Collections.sort(this.worstcasetablerows, new SortWorstcaseTablerow()); 
//-->7. Die Plätze vergeben
					Iterator<WorstcaseTablerow>iter4 = this.worstcasetablerows.iterator();
					int pk = 999;
					int pl = 0;
					int i = 0;
					while(iter4.hasNext())
					{
						WorstcaseTablerow wcr2 = iter4.next();
						i++;
						if(wcr2.getPunkteGesamt() < pk)
						{
							pl = i;
							pk = wcr2.getPunkteGesamt();
							wcr2.setPlatz(pl);
						}
					}
					boolean einzelsieger = true;
					if(this.worstcasetablerows.get(0).getPunkteGesamt() == this.worstcasetablerows.get(1).getPunkteGesamt())
					{
						einzelsieger = false;
					}
					
//-->8. Den Tipper suchen und Platz sichern wenn schlechter.
					Iterator<WorstcaseTablerow> iter5 = this.worstcasetablerows.iterator();
					while(iter5.hasNext())
					{
						WorstcaseTablerow wcr = iter5.next();
						if(wcr.getTipper().getIdTipper() == tipper.getIdTipper())
						{
							if((wcr.getPlatz() == 1 && mybineinzelsieger && !einzelsieger)
									||
								(wcr.getPlatz() > myworstplatz)	
									||
								(this.myworstcasetablerows == null))
							{
								myworstplatz = wcr.getPlatz();
								myworstpunkte = wcr.getPunkteGesamt();
								myworstlivespiel1 = wcr.getLivespiel1();
								myworstlivespiel2 = wcr.getLivespiel2();
								if(wcr.getPlatz() == 1 && einzelsieger)
								{
									mybineinzelsieger = true;
								}
								myworstcasetablerows = new Vector<WorstcaseTablerow>();
								Iterator<WorstcaseTablerow> iter6 = worstcasetablerows.iterator();
								{
									while(iter6.hasNext())
									{
										WorstcaseTablerow bctr = iter6.next();
										WorstcaseTablerow clone = bctr.clone();
										myworstcasetablerows.add(clone);
									}
								}
							}
						}
					}
				}
			}
			else
			{
//-->Nur noch ein Spiel offen
				Iterator<WorstcaseTablerow> iter3 = this.worstcasetablerows.iterator();
				while(iter3.hasNext())
				{
					WorstcaseTablerow wcr1 = iter3.next();
					wcr1.simulateFirst(ls1c);
				}
//-->6. Die Rows sortieren
				Collections.sort(this.worstcasetablerows, new SortWorstcaseTablerow());
//-->7. Die Plätze vergeben
				Iterator<WorstcaseTablerow>iter4 = this.worstcasetablerows.iterator();
				int pk = 999;
				int pl = 0;
				{
					WorstcaseTablerow wcr2 = iter4.next();
					wcr2.setPlatz(0);
					if(wcr2.getPunkteGesamt() < pk)
					{
						pl++;
						pk = wcr2.getPunkteGesamt();
						wcr2.setPlatz(pl);
					}
				}
				
				boolean einzelsieger = true;
				if(this.worstcasetablerows.get(0).getPunkteGesamt() == this.worstcasetablerows.get(1).getPunkteGesamt())
				{
					einzelsieger = false;
				}

//-->8. Den Tipper suchen und Platz (Platz1 oder Platz2 geht vor Punkte),sonst Punkte sichern wenn besser.
				Iterator<WorstcaseTablerow> iter5 = this.worstcasetablerows.iterator();
				while(iter5.hasNext())
				{
					WorstcaseTablerow wcr = iter5.next();
					if(wcr.getTipper().getIdTipper() == tipper.getIdTipper())
					{
						if((wcr.getPlatz() == 1 && mybineinzelsieger && !einzelsieger)
								||
							(wcr.getPlatz() > myworstplatz)	
								||
							(this.myworstcasetablerows == null))
						{
							myworstplatz = wcr.getPlatz();
							myworstpunkte = wcr.getPunkteGesamt();
							myworstlivespiel1 = wcr.getLivespiel1();
							myworstlivespiel2 = wcr.getLivespiel2();
							if(wcr.getPlatz() == 1 && einzelsieger)
							{
								mybineinzelsieger = true;
							}
							myworstcasetablerows = new Vector<WorstcaseTablerow>();
							Iterator<WorstcaseTablerow> iter6 = worstcasetablerows.iterator();
							{
								while(iter6.hasNext())
								{
									WorstcaseTablerow bctr = iter6.next();
									WorstcaseTablerow clone = bctr.clone();
									myworstcasetablerows.add(clone);
								}
							}
						}
					}
				}	
			}
		}
		StringBuffer sb = new StringBuffer("");
		sb.append(HtmlTags.wrapTD(HtmlTags.wrapH3("Wenn"), null));
		sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG25(this.getWorstlivespiel1().getHeim().getAtVereinKurz(), "vereine/gross/"), null)); 
		sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG25(this.getWorstlivespiel1().getGast().getAtVereinKurz(), "vereine/gross/"), null)); 
		sb.append(HtmlTags.wrapTD(HtmlTags.wrapH3(this.getWorstlivespiel1().getNowToreheim() 
				+ ":" 
				+ this.getWorstlivespiel1().getNowToregast()), null));
		String bc1 = HtmlTags.wrapTR(sb.toString(), null);
		
		
		sb = new StringBuffer("");
		
		String bc2 = "";		
		if(this.getWorstlivespiel2() != null)
		{
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapH3("und"), null));
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG25(this.getWorstlivespiel2().getHeim().getAtVereinKurz(), "vereine/gross/"), null)); 
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG25(this.getWorstlivespiel2().getGast().getAtVereinKurz(), "vereine/gross/"), null)); 
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapH3(this.getWorstlivespiel2().getNowToreheim() 
					+ ":" 
					+ this.getWorstlivespiel2().getNowToregast()), null));
			bc2 = HtmlTags.wrapTR(sb.toString(), null);
		}
		this.head2.setText(HtmlTags.wrapTABLE(bc1 + bc2, null));
	}
	
	public String getHtml()
	{
		StringBuffer sb = new StringBuffer("");
		sb.append(this.head2.getHtmlRow());
		sb.append(this.head3.getHtmlRow());
		Iterator<WorstcaseTablerow> iter = this.myworstcasetablerows.iterator();
		while (iter.hasNext())
		{
			sb.append(iter.next().getHtmlRow());
		}
		return HtmlTags.wrapTABLE(sb.toString(), null);
	}

	public Livespiel getLivespiel1()
	{
		return livespiel1;
	}

	public void setLivespiel1(Livespiel livespiel1)
	{
		this.livespiel1 = livespiel1;
	}

	public Livespiel getLivespiel2()
	{
		return livespiel2;
	}

	public void setLivespiel2(Livespiel livespiel2)
	{
		this.livespiel2 = livespiel2;
	}

	public int getPlatz()
	{
		return platz;
	}

	public void setPlatz(int platz)
	{
		this.platz = platz;
	}

	public int getPunkte()
	{
		return punkte;
	}

	public void setPunkte(int punkte)
	{
		this.punkte = punkte;
	}

	public int getWorstplatz()
	{
		return myworstplatz;
	}

	public int getWorstpunkte()
	{
		return myworstpunkte;
	}

	public Livespiel getWorstlivespiel1()
	{
		return myworstlivespiel1;
	}

	public Livespiel getWorstlivespiel2()
	{
		return myworstlivespiel2;
	}

}
