package tables;

import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnTipp;
import de.wepas.jpa.EnTipper;
import de.wepas.livegoals.Livespiel;
import de.wepas.livegoals.Livetipp;
import de.wepas.util.HtmlTags;

public class BestcaseTablerow implements Cloneable
{
	private static Log log = LogFactory.getLog(BestcaseTablerow.class);
	
	String csshell = ApplConstants.CSSHELL;
	String cssdunkel = ApplConstants.CSSDUNKEL;
	String csseigen = ApplConstants.CSSEIGEN;
	String cssnoeigen = ApplConstants.CSSNOEIGEN;
	String cssgewinn = ApplConstants.CSSGEWINN;
	String cssnohell = ApplConstants.CSSNOHELL;
	String cssnodunkel = ApplConstants.CSSNODUNKEL;
	
	private EnTipper tipper;
	private boolean own;
	private Vector<EnTipp> tipps;
	private Vector<EnTipp> offeneTipps;
	private Livespiel livespiel1;
	private Livespiel livespiel2;
	private int punkteBisher = 0;
	private int punkteNoch = 0;
	private int platz = 0;
	public BestcaseTablerow(EnTipper tipper, Vector<EnTipp> tipps)
	{
		super();
		this.tipper = tipper;
		this.tipps = tipps;
		this.offeneTipps = new Vector<EnTipp>();
		Iterator<EnTipp> iter1 = this.tipps.iterator();
		while(iter1.hasNext())
		{
			EnTipp tipp = iter1.next();
			if(tipp.getAtTippIsValid() || (tipp.getAtTippTorHeim() == 9 && tipp.getAtTippTorGast() == 9))
			{
				this.punkteBisher = this.punkteBisher + tipp.getAtTippPunkte();
			}
			else
			{
				this.offeneTipps.add(tipp);
			}
		}
	}
	
	@Override
	public BestcaseTablerow clone()
	{
		BestcaseTablerow clone = new BestcaseTablerow(this.tipper, this.tipps);
		clone.setPlatz(platz);
		clone.livespiel1 = this.livespiel1;
		clone.livespiel2 = this.livespiel2;
		clone.punkteBisher = this.punkteBisher;
		clone.punkteNoch = this.punkteNoch;
		return clone;
	}
	
	public int getPunkteGesamt()
	{
		return this.punkteBisher + this.punkteNoch;
	}
	
	public void simulateFirst(Livespiel livespiel)
	{
		this.punkteNoch = 0;
		this.platz = 0;
		this.livespiel1 = livespiel;
		this.livespiel2 = null;
		if(this.offeneTipps.size() != 1 && this.offeneTipps.size() != 2)
		{
			throw new RuntimeException("--->Offene Tipps:" + this.offeneTipps.size() + " simulateFirst() Vector nicht 1 oder 2!");
		}
		EnTipp tipp = this.offeneTipps.firstElement();
		if(tipp.getIdSpiel() != livespiel.getSpiel().getIdSpiel())
		{
			throw new RuntimeException("simulateFirst() Spiel passt nicht zum Tipp");
		}
		int temp = 0;
		if(livespiel.getNowToreheim() > livespiel.getNowToregast() && tipp.getAtTippTorHeim() > tipp.getAtTippTorGast())
		{
			temp++;
			if(livespiel.getNowToreheim() - livespiel.getNowToregast() == tipp.getAtTippTorHeim() - tipp.getAtTippTorGast())
			{
				temp++;
				if(livespiel.getNowToreheim() == tipp.getAtTippTorHeim())
				{
					temp++;
				}
			}
		}
		if(livespiel.getNowToreheim() == livespiel.getNowToregast() && tipp.getAtTippTorHeim() == tipp.getAtTippTorGast())
		{
			temp++;
			if(livespiel.getNowToreheim() - livespiel.getNowToregast() == tipp.getAtTippTorHeim() - tipp.getAtTippTorGast())
			{
				temp++;
				if(livespiel.getNowToreheim() == tipp.getAtTippTorHeim())
				{
					temp++;
				}
			}
		}
		if(livespiel.getNowToreheim() < livespiel.getNowToregast() && tipp.getAtTippTorHeim() < tipp.getAtTippTorGast())
		{
			temp++;
			if(livespiel.getNowToreheim() - livespiel.getNowToregast() == tipp.getAtTippTorHeim() - tipp.getAtTippTorGast())
			{
				temp++;
				if(livespiel.getNowToreheim() == tipp.getAtTippTorHeim())
				{
					temp++;
				}
			}
		}
		if(tipp.getAtTippIsSupertipp())
		{
			temp = temp * 2;
		}
		this.punkteNoch = this.punkteNoch + temp;
	}
	
	public void simulateSecond(Livespiel livespiel)
	{
		this.livespiel2 = livespiel;
		if(this.offeneTipps.size() != 2)
		{
			throw new RuntimeException("simulateSecond() - Vector nicht 2!");
		}
		EnTipp tipp = this.offeneTipps.lastElement();
		if(tipp.getIdSpiel() != livespiel.getSpiel().getIdSpiel())
		{
			throw new RuntimeException("simulateFirst() Spiel passt nicht zum Tipp");
		}
		int temp = 0;
		if(livespiel.getNowToreheim() > livespiel.getNowToregast() && tipp.getAtTippTorHeim() > tipp.getAtTippTorGast())
		{
			temp++;
			if(livespiel.getNowToreheim() - livespiel.getNowToregast() == tipp.getAtTippTorHeim() - tipp.getAtTippTorGast())
			{
				temp++;
				if(livespiel.getNowToreheim() == tipp.getAtTippTorHeim())
				{
					temp++;
				}
			}
		}
		if(livespiel.getNowToreheim() == livespiel.getNowToregast() && tipp.getAtTippTorHeim() == tipp.getAtTippTorGast())
		{
			temp++;
			if(livespiel.getNowToreheim() - livespiel.getNowToregast() == tipp.getAtTippTorHeim() - tipp.getAtTippTorGast())
			{
				temp++;
				if(livespiel.getNowToreheim() == tipp.getAtTippTorHeim())
				{
					temp++;
				}
			}
		}
		if(livespiel.getNowToreheim() < livespiel.getNowToregast() && tipp.getAtTippTorHeim() < tipp.getAtTippTorGast())
		{
			temp++;
			if(livespiel.getNowToreheim() - livespiel.getNowToregast() == tipp.getAtTippTorHeim() - tipp.getAtTippTorGast())
			{
				temp++;
				if(livespiel.getNowToreheim() == tipp.getAtTippTorHeim())
				{
					temp++;
				}
			}
		}
		if(tipp.getAtTippIsSupertipp())
		{
			temp = temp * 2;
		}
		this.punkteNoch = this.punkteNoch + temp;
	}

	public EnTipper getTipper()
	{
		return this.tipper;
	}

	public Livespiel getLivespiel1()
	{
		return this.livespiel1;
	}

	public Livespiel getLivespiel2()
	{
		return this.livespiel2;
	}

	public int getPunkteBisher()
	{
		return this.punkteBisher;
	}

	public int getPunkteNoch()
	{
		return this.punkteNoch;
	}
	public int getPlatz()
	{
		return this.platz;
	}

	public void setPlatz(int platz)
	{
		this.platz = platz;
	}
	
	public boolean isOwn()
	{
		log.info("---> isown " + this.own + this.getTipper().getAtTipperName());
		return this.own;
	}
	
	public void setOwn(boolean own)
	{
		log.info("---> setown " + own);
		this.own = own;
		log.info("---> this.own = " + this.own + this.getTipper().getAtTipperName());
	}	
	
	public String getHtmlRow()
	{

		StringBuffer sb = new StringBuffer("");
		if (this.platz < 1)
		{
			sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHS, HtmlTags.createNBSP(1), csshell + "h"));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHS, this.getPlatz() + HtmlTags.createPUNKT(), csshell + "h"));
		}
		sb.append(HtmlTags.wrapTD(this.tipper.getAtTipperVorname() + " " + this.getTipper().getAtTipperName(), csshell));
		Iterator<EnTipp> iter1 = this.tipps.iterator();
		while (iter1.hasNext())
		{
			EnTipp tipp = iter1.next();
			if(tipp.getAtTippIsValid() || tipp.getAtTippTorHeim() + tipp.getAtTippTorGast() == 18)
			{
				sb.append(HtmlTags.getTippHTML(tipp));
			}
			else
			{
				if(tipp.getIdSpiel() == this.livespiel1.getSpiel().getIdSpiel())
				{
					Livetipp livetipp = new Livetipp(tipp);
					if(livespiel1.getNowToreheim() > livespiel1.getNowToregast() && tipp.getAtTippTorHeim() > tipp.getAtTippTorGast())
					{
						livetipp.setLivepunkte(livetipp.getLivepunkte() + 1);
						if(livespiel1.getNowToreheim() - livespiel1.getNowToregast() == tipp.getAtTippTorHeim() - tipp.getAtTippTorGast())
						{
							livetipp.setLivepunkte(livetipp.getLivepunkte() + 1);
							if(livespiel1.getNowToreheim() == tipp.getAtTippTorHeim() && livespiel1.getNowToregast() == tipp.getAtTippTorGast())
							{
								livetipp.setLivepunkte(livetipp.getLivepunkte() + 1);
							}
						}
					}
					if(livespiel1.getNowToreheim() == livespiel1.getNowToregast() && tipp.getAtTippTorHeim() == tipp.getAtTippTorGast())
					{
						livetipp.setLivepunkte(livetipp.getLivepunkte() + 1);
						if(livespiel1.getNowToreheim() - livespiel1.getNowToregast() == tipp.getAtTippTorHeim() - tipp.getAtTippTorGast())
						{
							livetipp.setLivepunkte(livetipp.getLivepunkte() + 1);
							if(livespiel1.getNowToreheim() == tipp.getAtTippTorHeim() && livespiel1.getNowToregast() == tipp.getAtTippTorGast())
							{
								livetipp.setLivepunkte(livetipp.getLivepunkte() + 1);
							}
						}
					}
					if(livespiel1.getNowToreheim() < livespiel1.getNowToregast() && tipp.getAtTippTorHeim() < tipp.getAtTippTorGast())
					{
						livetipp.setLivepunkte(livetipp.getLivepunkte() + 1);
						if(livespiel1.getNowToreheim() - livespiel1.getNowToregast() == tipp.getAtTippTorHeim() - tipp.getAtTippTorGast())
						{
							livetipp.setLivepunkte(livetipp.getLivepunkte() + 1);
							if(livespiel1.getNowToreheim() == tipp.getAtTippTorHeim() && livespiel1.getNowToregast() == tipp.getAtTippTorGast())
							{
								livetipp.setLivepunkte(livetipp.getLivepunkte() + 1);
							}
						}
					}
					if(tipp.getAtTippIsSupertipp())
					{
						livetipp.setLivepunkte(livetipp.getLivepunkte() * 2);
					}
					sb.append(HtmlTags.getCasetippHTML(livetipp));
				}
				if(this.livespiel2 != null && tipp.getIdSpiel() == this.livespiel2.getSpiel().getIdSpiel())
				{
					Livetipp livetipp = new Livetipp(tipp);
					if(livespiel2.getNowToreheim() > livespiel2.getNowToregast() && tipp.getAtTippTorHeim() > tipp.getAtTippTorGast())
					{
						livetipp.setLivepunkte(livetipp.getLivepunkte() + 1);
						if(livespiel2.getNowToreheim() - livespiel2.getNowToregast() == tipp.getAtTippTorHeim() - tipp.getAtTippTorGast())
						{
							livetipp.setLivepunkte(livetipp.getLivepunkte() + 1);
							if(livespiel2.getNowToreheim() == tipp.getAtTippTorHeim() && livespiel2.getNowToregast() == tipp.getAtTippTorGast())
							{
								livetipp.setLivepunkte(livetipp.getLivepunkte() + 1);
							}
						}
					}
					if(livespiel2.getNowToreheim() == livespiel2.getNowToregast() && tipp.getAtTippTorHeim() == tipp.getAtTippTorGast())
					{
						livetipp.setLivepunkte(livetipp.getLivepunkte() + 1);
						if(livespiel2.getNowToreheim() - livespiel2.getNowToregast() == tipp.getAtTippTorHeim() - tipp.getAtTippTorGast())
						{
							livetipp.setLivepunkte(livetipp.getLivepunkte() + 1);
							if(livespiel2.getNowToreheim() == tipp.getAtTippTorHeim() && livespiel2.getNowToregast() == tipp.getAtTippTorGast())
							{
								livetipp.setLivepunkte(livetipp.getLivepunkte() + 1);
							}
						}
					}
					if(livespiel2.getNowToreheim() < livespiel2.getNowToregast() && tipp.getAtTippTorHeim() < tipp.getAtTippTorGast())
					{
						livetipp.setLivepunkte(livetipp.getLivepunkte() + 1);
						if(livespiel2.getNowToreheim() - livespiel2.getNowToregast() == tipp.getAtTippTorHeim() - tipp.getAtTippTorGast())
						{
							livetipp.setLivepunkte(livetipp.getLivepunkte() + 1);
							if(livespiel2.getNowToreheim() == tipp.getAtTippTorHeim() && livespiel2.getNowToregast() == tipp.getAtTippTorGast())
							{
								livetipp.setLivepunkte(livetipp.getLivepunkte() + 1);
							}
						}
					}
					if(tipp.getAtTippIsSupertipp())
					{
						livetipp.setLivepunkte(livetipp.getLivepunkte() * 2);
					}
					sb.append(HtmlTags.getCasetippHTML(livetipp));
				}
			}
		}
		sb.append(HtmlTags.wrapTD( "" + this.getPunkteGesamt(), csshell + "h"));
		sb.append(HtmlTags.wrapTD("(" + this.getPunkteBisher() + "+" + this.getPunkteNoch() + ")", csshell + "h"));
		if (this.isOwn())
		{
			log.info("OOWWN");
			return HtmlTags.wrapTR(sb.toString() + '\n', csseigen);
		}
		else
		{
			return HtmlTags.wrapTR(sb.toString() + '\n', csshell);
		}
	}
}
