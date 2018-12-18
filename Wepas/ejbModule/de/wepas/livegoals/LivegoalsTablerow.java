package de.wepas.livegoals;

import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnTipp;
import de.wepas.jpa.EnTipper;
import de.wepas.util.HtmlTags;

/*
 * Hier wird eine Tabellenzeile mit den Tipps eines Tippers erzeugt.
 */


public class LivegoalsTablerow
{
	private static Log log = LogFactory.getLog(LivegoalsTablerow.class);
	private String csshell = ApplConstants.CSSHELL;
	
	private EnTipper tipper = null;
	private int platz;
	private String name;
	private Vector<Livetipp> livetipps;
	private int punktespieltag;
	
	public LivegoalsTablerow(EnTipper tipper, Vector<EnTipp> tipps, Vector<Livespiel> livespiele)
	{
		super();
		log.debug("-->Constructing " + this.getClass().getName());
		this.tipper = tipper;
		this.platz = 0;
		this.punktespieltag = 0;
		this.name = tipper.getAtTipperVorname() + " " + tipper.getAtTipperName();
		
		this.livetipps = new Vector<Livetipp>(9);
		Iterator<EnTipp> iter0 = tipps.iterator();
		while(iter0.hasNext())
		{
			this.livetipps.add(new Livetipp(iter0.next()));
		}
		
		Iterator<Livespiel> iter1 = livespiele.iterator();
		while (iter1.hasNext())
		{
			Livespiel lg = iter1.next();
//-->Ich habe ein aktuelles Spiel gefunden

			Iterator<Livetipp> iter2 = this.livetipps.iterator();
			while (iter2.hasNext())
			{
				Livetipp livetipp = iter2.next();
				
				if(livetipp.getTipp().getIdSpiel() == lg.getSpiel().getIdSpiel())
				{
//-->Dieses Spiel passt zu genau diesem Livetipp
					
					if (lg.getStatus() == LivestatusEnum.RUNNING)
					{
//-->Dieses Spiel läuft noch!
						
						livetipp.setLivepunktehist(livetipp.getLivepunkte());
						livetipp.setLivepunkte(0);
						
						if 	(
								livetipp.getTipp().getAtTippTorHeim() < 9 && livetipp.getTipp().getAtTippTorGast() < 9 &&
								(lg.getNowToreheim() > lg.getNowToregast() && livetipp.getTipp().getAtTippTorHeim() > livetipp.getTipp().getAtTippTorGast() 
								|| lg.getNowToreheim() == lg.getNowToregast() && livetipp.getTipp().getAtTippTorHeim() == livetipp.getTipp().getAtTippTorGast())
								|| lg.getNowToreheim() < lg.getNowToregast() && livetipp.getTipp().getAtTippTorHeim() < livetipp.getTipp().getAtTippTorGast()
							)
						{
							if (lg.getNowToreheim() - lg.getNowToregast() == livetipp.getTipp().getAtTippTorHeim() - livetipp.getTipp().getAtTippTorGast())
							{
								if (lg.getNowToreheim() == livetipp.getTipp().getAtTippTorHeim() && lg.getNowToregast() == livetipp.getTipp().getAtTippTorGast())
								{
									if (livetipp.getTipp().getAtTippIsSupertipp())
									{
										livetipp.setLivepunkte(6);
									}
									else
									{
										livetipp.setLivepunkte(3);
									}
								}
								else
								{
									if (livetipp.getTipp().getAtTippIsSupertipp())
									{
										livetipp.setLivepunkte(4);
									}
									else
									{
										livetipp.setLivepunkte(2);
									}
								}
							}
							else
							{
								if (livetipp.getTipp().getAtTippIsSupertipp())
								{
									livetipp.setLivepunkte(2);
								}
								else
								{
									livetipp.setLivepunkte(1);
								}
							}
						}
						this.punktespieltag = this.punktespieltag + livetipp.getLivepunkte();
						log.debug("-->LITipp Spiel:" + livetipp.getTipp().getIdSpiel() + " Tipper:" + livetipp.getTipp().getIdTipper() + " Punkte:" + livetipp.getLivepunkte() + "/" + livetipp.getLivepunktehist());

					}
					else
					{
						this.punktespieltag = this.punktespieltag + livetipp.getTipp().getAtTippPunkte();
						log.debug("-->ENTipp Spiel:" + livetipp.getTipp().getIdSpiel() + " Tipper:" + livetipp.getTipp().getIdTipper() + " Punkte:" + livetipp.getTipp().getAtTippPunkte());
					}
				}
			}
		}
	}

	public String getHtmlRow()
	{
		log.debug("-->Entering getHtmlRow " + this.getClass().getName());
		StringBuffer sb = new StringBuffer("");
		if(this.getPlatz() > 0)
		{
			sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHS, this.getPlatz() + HtmlTags.createPUNKT(), csshell + "h"));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHS, HtmlTags.createNBSP(1), csshell + "h"));
		}
		sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHX, this.getName(), csshell));
		Iterator<Livetipp> iter1 = this.livetipps.iterator();
		if(iter1.hasNext())
		{
			while(iter1.hasNext())
			{
				Livetipp livetipp = iter1.next();
				if(livetipp.getTipp().getAtTippIsValid())
				{
					//Tipp ist ausgewertet
					sb.append(HtmlTags.getTippHTML(livetipp.getTipp()));
				}
				else
				{
					//Tipp ist vorläufig, da Spiel noch nicht beendet
					sb.append(HtmlTags.getLivetippHTML(livetipp));
				}
			}
		}
		else
		{
			sb.append(HtmlTags.wrapTD(HtmlTags.createNBSP(1), null, 9));
		}
		sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHS, Integer.toString(this.getPunktespieltag()), csshell + "h"));
		sb.append(HtmlTags.wrapTD(ApplConstants.WIDTHS, Integer.toString(this.tipper.getAtTipperPunkte() + this.getPunktespieltag()), csshell + "h"));
//		log.info(HtmlTags.wrapTR(sb.toString(), null));
		return HtmlTags.wrapTR(sb.toString() + '\n', null);
	}

	public int getPlatz()
	{
		return platz;
	}

	public void setPlatz(int platz)
	{
		this.platz = platz;
	}

	public String getName()
	{
		return name;
	}

	public int getPunktespieltag()
	{
		return punktespieltag;
	}
}
