package tables;

import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import sorts.SortBestcaseTablerow;
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

public class BestcaseTable
{
	private EnTipper tipper;
	private Livespiel livespiel1;
	private Livespiel livespiel2;
	private int platz;
	private int punkte;
	private Vector<BestcaseTablerow> bestcasetablerows;
	
	private int mybestplatz;
	private int mybestpunkte;
	private Livespiel mybestlivespiel1;
	private Livespiel mybestlivespiel2;
	private Vector<BestcaseTablerow> mybestcasetablerows;
	boolean mybineinzelsieger;
	private BestcaseTablehead2 head2;
	private BestcaseTablehead3 head3;
	
	public BestcaseTable(LivegoalsObject livegoalsobject, EnTipper tipper)
	{
		super();
		this.mybestplatz = 999;
		this.mybestpunkte = 0;
		this.mybestlivespiel1 = null;
		this.mybestlivespiel2 = null;
		this.mybineinzelsieger = false;
		this.mybestcasetablerows = null;
		this.head2 = new BestcaseTablehead2(livegoalsobject);
		this.head3 = new BestcaseTablehead3(livegoalsobject.getLivespiele());
	
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
			throw new RuntimeException("Ungültige Menge Livespiele für Bestcaseauswertung");
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
		this.head2 = new BestcaseTablehead2(livegoalsobject);
		this.head3 = new BestcaseTablehead3(livegoalsobject.getLivespiele());	
		
		this.bestcasetablerows = new Vector<BestcaseTablerow>();
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
			BestcaseTablerow bestcasetablerow = new BestcaseTablerow(tippersarray[i], tipps);
			if(tippersarray[i].getIdTipper() == tipper.getIdTipper())
			{
				bestcasetablerow.setOwn(true);
			}
			this.bestcasetablerows.add(bestcasetablerow);
//Jetzt habe ich ein Vector mit den Bestcaserows aller Tipper zum auswerten.			
		}
	}
	
	public void analyse()
	{
//-->1. Alle zu analysierenden Möglichkeiten in einen Vector speichern
		Vector<BestcaseSimulation> bcsv = new Vector<BestcaseSimulation>();
		bcsv.add(new BestcaseSimulation(0, 0));
		bcsv.add(new BestcaseSimulation(1, 1));
		bcsv.add(new BestcaseSimulation(2, 2));
		bcsv.add(new BestcaseSimulation(3, 3));
		bcsv.add(new BestcaseSimulation(6, 0));
		bcsv.add(new BestcaseSimulation(0, 6));
		bcsv.add(new BestcaseSimulation(5, 0));
		bcsv.add(new BestcaseSimulation(0, 5));
		bcsv.add(new BestcaseSimulation(3, 0));
		bcsv.add(new BestcaseSimulation(0, 3));
		bcsv.add(new BestcaseSimulation(4, 1));
		bcsv.add(new BestcaseSimulation(1, 4));
		bcsv.add(new BestcaseSimulation(2, 0));
		bcsv.add(new BestcaseSimulation(0, 2));
		bcsv.add(new BestcaseSimulation(3, 1));
		bcsv.add(new BestcaseSimulation(1, 3));
		bcsv.add(new BestcaseSimulation(4, 2));
		bcsv.add(new BestcaseSimulation(2, 4));
		bcsv.add(new BestcaseSimulation(1, 0));
		bcsv.add(new BestcaseSimulation(0, 1));
		bcsv.add(new BestcaseSimulation(2, 1));
		bcsv.add(new BestcaseSimulation(1, 2));
		bcsv.add(new BestcaseSimulation(3, 2));
		bcsv.add(new BestcaseSimulation(2, 3));
		
//-->2. Nur die Möglichkeiten untersuchen die anhand des Livespiels1 noch möglich sind		
		Vector<BestcaseSimulation>possible1 = new Vector<BestcaseSimulation>();
		Iterator<BestcaseSimulation> iter1 = bcsv.iterator();
		while(iter1.hasNext())
		{
			BestcaseSimulation bcs = iter1.next();
			if(bcs.getH() >= this.livespiel1.getNowToreheim() && bcs.getG() >= this.livespiel1.getNowToregast())
			{
				possible1.add(bcs);
			}
		}
		
//-->3. Nur die Möglichkeiten untersuchen die anhand des Livespiels2 noch möglich sind		
		Vector<BestcaseSimulation>possible2 = null;
		if(this.livespiel2 != null)
		{
			possible2 = new Vector<BestcaseSimulation>();
			Iterator<BestcaseSimulation> iter2 = bcsv.iterator();
			while(iter2.hasNext())
			{
				BestcaseSimulation bcs = iter2.next();
				if(bcs.getH() >= this.livespiel2.getNowToreheim() && bcs.getG() >= this.livespiel2.getNowToregast())
				{
					possible2.add(bcs);
				}
			}
		}
		
//-->4. Alle Möglichkeiten die noch mit Livespiel1 möglich sind analysieren
		Iterator<BestcaseSimulation> iterp1 = possible1.iterator();
		while(iterp1.hasNext())
		{
			BestcaseSimulation bcs1 = iterp1.next();
			Livespiel ls1c = (Livespiel)this.livespiel1.clone();
			ls1c.setNowToreheim(bcs1.getH());
			ls1c.setNowToregast(bcs1.getG());
			
			if(this.livespiel2 != null)
			{
//-->Zwei Spiele offen
				Iterator<BestcaseSimulation> iterp2 = possible2.iterator();
				while(iterp2.hasNext())
				{
					BestcaseSimulation bcs2 = iterp2.next();
					Livespiel ls2c = (Livespiel)this.livespiel2.clone();
					ls2c.setNowToreheim(bcs2.getH());
					ls2c.setNowToregast(bcs2.getG());
				
					Iterator<BestcaseTablerow> iter3 = this.bestcasetablerows.iterator();
					while(iter3.hasNext())
					{
						BestcaseTablerow bcr1 = iter3.next();
//						System.out.println(bcr1.getTipper().getAtTipperName() + ls1c.getNowToreheim() + ls1c.getNowToregast() + "/" + ls2c.getNowToreheim() + ls2c.getNowToregast());
						bcr1.simulateFirst(ls1c);
						bcr1.simulateSecond(ls2c);
					}
//-->6. Die Rows sortieren
					Collections.sort(this.bestcasetablerows, new SortBestcaseTablerow());
//-->7. Die Plätze vergeben
					Iterator<BestcaseTablerow>iter4 = this.bestcasetablerows.iterator();
					int pk = 999;
					int pl = 0;
					int i = 0;
					while(iter4.hasNext())
					{
						BestcaseTablerow bcr2 = iter4.next();
						bcr2.setPlatz(0);
						i++;
						if(bcr2.getPunkteGesamt() < pk)
						{
							pl = i;
							pk = bcr2.getPunkteGesamt();
							bcr2.setPlatz(pl);
						}
					}
					boolean einzelsieger = true;
					if(this.bestcasetablerows.get(0).getPunkteGesamt() == this.bestcasetablerows.get(1).getPunkteGesamt())
					{
						einzelsieger = false;
					}
					
//-->8. Den Tipper suchen und Platz (Platz1 oder Platz2 geht vor Punkte),sonst Punkte sichern wenn besser.
					Iterator<BestcaseTablerow> iter5 = this.bestcasetablerows.iterator();
					while(iter5.hasNext())
					{
						BestcaseTablerow bcr = iter5.next();
						if(bcr.getTipper().getIdTipper() == tipper.getIdTipper())
						{
							if((bcr.getPlatz() == 1 && !mybineinzelsieger && einzelsieger)
									||
								(bcr.getPlatz() == 1 && mybestplatz > 1)
									||
								(bcr.getPlatz() == 1 && mybestpunkte < bcr.getPunkteGesamt() && einzelsieger)
									||
								(bcr.getPlatz() == 2 && mybestplatz > 2)
									||
								(bcr.getPlatz() == 2 && mybestpunkte < bcr.getPunkteGesamt())
									||
								(mybestpunkte < bcr.getPunkteGesamt()) 
									|| 
								(mybestcasetablerows == null))
							{
								mybestplatz = bcr.getPlatz();
								mybestpunkte = bcr.getPunkteGesamt();
								mybestlivespiel1 = bcr.getLivespiel1();
								mybestlivespiel2 = bcr.getLivespiel2();
								if(bcr.getPlatz() == 1 && einzelsieger)
								{
									mybineinzelsieger = true;
								}
								mybestcasetablerows = new Vector<BestcaseTablerow>();
								Iterator<BestcaseTablerow> iter6 = bestcasetablerows.iterator();
								{
									while(iter6.hasNext())
									{
										BestcaseTablerow bctr = iter6.next();
										BestcaseTablerow clone = bctr.clone();
										mybestcasetablerows.add(clone);
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
				Iterator<BestcaseTablerow> iter3 = this.bestcasetablerows.iterator();
				while(iter3.hasNext())
				{
					BestcaseTablerow bcr1 = iter3.next();
					bcr1.simulateFirst(ls1c);
				}
//-->6. Die Rows sortieren
				Collections.sort(this.bestcasetablerows, new SortBestcaseTablerow());
//-->7. Die Plätze vergeben
				Iterator<BestcaseTablerow>iter4 = this.bestcasetablerows.iterator();
				int pk = 999;
				int pl = 0;
				{
					BestcaseTablerow bcr2 = iter4.next();
					bcr2.setPlatz(0);
					if(bcr2.getPunkteGesamt() < pk)
					{
						pl++;
						pk = bcr2.getPunkteGesamt();
						bcr2.setPlatz(pl);
					}
				}
				
				boolean einzelsieger = true;
				if(this.bestcasetablerows.get(0).getPunkteGesamt() == this.bestcasetablerows.get(1).getPunkteGesamt())
				{
					einzelsieger = false;
				}
//-->8. Den Tipper suchen und Platz (Platz1 oder Platz2 geht vor Punkte),sonst Punkte sichern wenn besser.
				Iterator<BestcaseTablerow> iter5 = this.bestcasetablerows.iterator();
				while(iter5.hasNext())
				{
					BestcaseTablerow bcr = iter5.next();
					if(bcr.getTipper().getIdTipper() == tipper.getIdTipper())
					{
						if((bcr.getPlatz() == 1 && !mybineinzelsieger && einzelsieger)
								||
							(bcr.getPlatz() == 1 && mybestplatz > 1)
								||
							(bcr.getPlatz() == 1 && mybestpunkte < bcr.getPunkteGesamt() && einzelsieger)
								||
							(bcr.getPlatz() == 2 && mybestplatz > 2)
								||
							(bcr.getPlatz() == 2 && mybestpunkte < bcr.getPunkteGesamt())
								||
							(mybestpunkte < bcr.getPunkteGesamt())
								||
							(mybestcasetablerows == null))
						{
							mybestplatz = bcr.getPlatz();
							mybestpunkte = bcr.getPunkteGesamt();
							mybestlivespiel1 = bcr.getLivespiel1();
							mybestlivespiel2 = bcr.getLivespiel2();
							if(bcr.getPlatz() == 1 && einzelsieger)
							{
								mybineinzelsieger = true;
							}
							mybestcasetablerows = new Vector<BestcaseTablerow>();
							Iterator<BestcaseTablerow> iter6 = bestcasetablerows.iterator();
							{
								while(iter6.hasNext())
								{
									BestcaseTablerow bctr = iter6.next();
									BestcaseTablerow clone = bctr.clone();
									mybestcasetablerows.add(clone);
								}
							}
						}
					}
				}	
			}
		}
		StringBuffer sb = new StringBuffer("");
		sb.append(HtmlTags.wrapTD(HtmlTags.wrapH3("Wenn"), null));
		sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG25(this.getBestlivespiel1().getHeim().getAtVereinKurz(), "vereine/gross/"), null)); 
		sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG25(this.getBestlivespiel1().getGast().getAtVereinKurz(), "vereine/gross/"), null)); 
		sb.append(HtmlTags.wrapTD(HtmlTags.wrapH3(this.getBestlivespiel1().getNowToreheim() 
				+ ":" 
				+ this.getBestlivespiel1().getNowToregast()), null));
		String bc1 = HtmlTags.wrapTR(sb.toString(), null);
		
		
		sb = new StringBuffer("");
		
		String bc2 = "";		
		if(this.getBestlivespiel2() != null)
		{
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapH3("und"), null));
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG25(this.getBestlivespiel2().getHeim().getAtVereinKurz(), "vereine/gross/"), null)); 
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG25(this.getBestlivespiel2().getGast().getAtVereinKurz(), "vereine/gross/"), null)); 
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapH3(this.getBestlivespiel2().getNowToreheim() 
					+ ":" 
					+ this.getBestlivespiel2().getNowToregast()), null));
			bc2 = HtmlTags.wrapTR(sb.toString(), null);
		}
		this.head2.setText(HtmlTags.wrapTABLE(bc1 + bc2, null));
	}
	
	public String getHtml()
	{
		StringBuffer sb = new StringBuffer("");
		sb.append(this.head2.getHtmlRow());
		sb.append(this.head3.getHtmlRow());
		Iterator<BestcaseTablerow> iter = this.mybestcasetablerows.iterator();
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

	public int getBestplatz()
	{
		return mybestplatz;
	}

	public int getBestpunkte()
	{
		return mybestpunkte;
	}

	public Livespiel getBestlivespiel1()
	{
		return mybestlivespiel1;
	}

	public Livespiel getBestlivespiel2()
	{
		return mybestlivespiel2;
	}

}
