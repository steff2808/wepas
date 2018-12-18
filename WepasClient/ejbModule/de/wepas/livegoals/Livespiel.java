package de.wepas.livegoals;

import java.io.Serializable;
import java.util.GregorianCalendar;

import de.wepas.connector.VereinBeanLocal;
import de.wepas.connector.VereinException;
import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnVerein;
import de.wepas.util.LookupLocalService;

/*
 * Livegames werden durch Anlage des LivegoalsObject am Freitagabend mit 
 * angelegt und im Vector in LivegoalsObject gespeichert. Sie speichern
 * den aktuellen Stand eines Spiels und werden bei Veränderung historisiert.
 */

public class Livespiel implements Serializable
{
	private static final long serialVersionUID = 3572687104917793302L;
	
	private LivestatusEnum status;
	private GregorianCalendar timestamp;
	private EnSpiel spiel;
	private EnVerein heim;
	private int nowToreheim;
	private int nowToregast;
	private EnVerein gast;
	private String title;
	private String description;
	private String htmltabelle;
	private boolean lastgoal;
	
	public Livespiel(EnSpiel spiel)
	{
		super();
		this.status = LivestatusEnum.UNKNOWN;
		this.spiel = spiel;
		VereinBeanLocal vbl = LookupLocalService.lookupVereinBeanLocal();
		try
		{
			this.heim = vbl.getVerein(spiel.getFkSpielVereinHeim());
			this.gast = vbl.getVerein(spiel.getFkSpielVereinGast());
		}
		catch (VereinException e)
		{
			e.printStackTrace();
		}
		this.nowToreheim = spiel.getAtSpielToreHeim();
		this.nowToregast = spiel.getAtSpielToreGast();
		this.timestamp = new GregorianCalendar();
		this.lastgoal = false;
		this.htmltabelle = "<table><tr><td>LEER!</td></tr></table>";
	}
	
	@Override
	public Object clone()
	{
		Livespiel clone = new Livespiel(this.getSpiel());
		clone.setHeim(this.heim);
		clone.setGast(this.gast);
		clone.setStatus(this.getStatus());
		clone.setTitle(this.getTitle());
		clone.setTimestamp(new GregorianCalendar());
		return clone;
	}
	
	public LivestatusEnum getStatus()
	{
		return status;
	}
	public void setStatus(LivestatusEnum status)
	{
		this.status = status;
	}
	public GregorianCalendar getTimestamp()
	{
		return timestamp;
	}
	public void setTimestamp(GregorianCalendar timestamp)
	{
		this.timestamp = timestamp;
	}
	public EnSpiel getSpiel()
	{
		return spiel;
	}
	public void setSpiel(EnSpiel spiel)
	{
		this.spiel = spiel;
	}
	public EnVerein getHeim()
	{
		return heim;
	}
	public void setHeim(EnVerein heim)
	{
		this.heim = heim;
	}
	public EnVerein getGast()
	{
		return gast;
	}
	public void setGast(EnVerein gast)
	{
		this.gast = gast;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getHtmltabelle()
	{
		return htmltabelle;
	}
	public void setHtmltabelle(String htmltabelle)
	{
		this.htmltabelle = htmltabelle;
	}

	public int getNowToreheim()
	{
		return nowToreheim;
	}

	public void setNowToreheim(int nowToreheim)
	{
		this.nowToreheim = nowToreheim;
	}

	public int getNowToregast()
	{
		return nowToregast;
	}

	public void setNowToregast(int nowToregast)
	{
		this.nowToregast = nowToregast;
	}
	
	public boolean isLastgoal()
	{
		return lastgoal;
	}

	public void setLastgoal(boolean lastgoal)
	{
		this.lastgoal = lastgoal;
	}

	public String getNow()
	{
		if(this.status == LivestatusEnum.RUNNING || this.status == LivestatusEnum.FINISHED)
		{
			return this.getNowToreheim() + ":" + this.getNowToregast();
		}
		else
		{
			return "&nbsp;-&nbsp;:&nbsp;-&nbsp;";
		}
	}

}
