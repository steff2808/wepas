package de.wepas.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the en_spiel database table.
 * 
 */
@Entity
@NamedQueries(
		{ @NamedQuery(name = "EnSpiel.findAll",        query = "SELECT a FROM EnSpiel a "), 
		  @NamedQuery(name = "EnSpiel.findBySpieltag", query = "SELECT a FROM EnSpiel a WHERE a.fkSpielSpieltag = :spieltag ORDER BY a.idSpiel"),
		  @NamedQuery(name = "EnSpiel.findOffene",     query = "SELECT a FROM EnSpiel a ORDER BY a.idSpiel"),
		  @NamedQuery(name = "EnSpiel.findById",       query = "SELECT a FROM EnSpiel a WHERE a.idSpiel = :id")})
@Table(name = "en_spiel")
public class EnSpiel implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_spiel")
	private int idSpiel;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "at_spiel_anpfiff")
	private Date atSpielAnpfiff;
	
	@Column(name = "at_spiel_is_played")
	private byte atSpielIsPlayed;
	
	@Column(name = "at_spiel_tore_gast")
	private int atSpielToreGast;

	@Column(name = "at_spiel_tore_heim")
	private int atSpielToreHeim;

	@Column(name = "fk_spiel_spieltag")
	private int fkSpielSpieltag;
	
	@Column(name = "fk_spiel_einstellung")
	private int fkSpielEinstellung;

	@Column(name = "fk_spiel_verein_gast")
	private int fkSpielVereinGast;
	
	@Column(name = "fk_spiel_verein_heim")
	private int fkSpielVereinHeim;
//	private EnSpieltag spieltag;

	public EnSpiel()
	{
	}
	
	public EnSpiel(int fkSpieltag, int fkHeim, int fkGast)
	{
		this.atSpielIsPlayed = 0;
		this.atSpielToreGast = 0;
		this.atSpielToreHeim = 0;
		this.fkSpielSpieltag = fkSpieltag;
		this.fkSpielEinstellung = 0;
		this.fkSpielVereinHeim = fkHeim;
		this.fkSpielVereinGast = fkGast;
	}

	@Override
	public String toString()
	{
		return "HeimID: " + this.getFkSpielVereinHeim() + " - " + "GastID: " + this.getFkSpielVereinGast();
	}
	
	public int getIdSpiel()
	{
		return this.idSpiel;
	}

	public void setIdSpiel(int idSpiel)
	{
		this.idSpiel = idSpiel;
	}

	public Date getAtSpielAnpfiff()
	{
		return this.atSpielAnpfiff;
	}

	public void setAtSpielAnpfiff(Date atSpielAnpfiff)
	{
		this.atSpielAnpfiff = atSpielAnpfiff;
	}

	public boolean getAtSpielIsPlayed()
	{
		return this.atSpielIsPlayed == 1;
	}

	public void setAtSpielIsPlayed(boolean atSpielIsPlayed)
	{
		if(atSpielIsPlayed)
		{
			this.atSpielIsPlayed = 1;
		}
		else
		{
			this.atSpielIsPlayed = 0;
		}

	}

	public int getAtSpielToreGast()
	{
		return this.atSpielToreGast;
	}

	public void setAtSpielToreGast(int atSpielToreGast)
	{
		this.atSpielToreGast = atSpielToreGast;
	}

	public int getAtSpielToreHeim()
	{
		return this.atSpielToreHeim;
	}

	public void setAtSpielToreHeim(int atSpielToreHeim)
	{
		this.atSpielToreHeim = atSpielToreHeim;
	}

	public int getFkSpielSpieltag()
	{
		return this.fkSpielSpieltag;
	}

	public void setFkSpielSpieltag(int fkSpielSpieltag)
	{
		this.fkSpielSpieltag = fkSpielSpieltag;
	}

	public int getFkSpielVereinGast()
	{
		return this.fkSpielVereinGast;
	}

	public void setFkSpielVereinGast(int fkSpielVereinGast)
	{
		this.fkSpielVereinGast = fkSpielVereinGast;
	}

	public int getFkSpielVereinHeim()
	{
		return this.fkSpielVereinHeim;
	}

	public void setFkSpielVereinHeim(int fkSpielVereinHeim)
	{
		this.fkSpielVereinHeim = fkSpielVereinHeim;
	}
	
	
	
	public int getFkSpielEinstellung()
	{
		return fkSpielEinstellung;
	}

	public void setFkSpielEinstellung(int fkSpielEinstellung)
	{
		this.fkSpielEinstellung = fkSpielEinstellung;
	}

	public void setAtSpielIsPlayed(byte atSpielIsPlayed)
	{
		this.atSpielIsPlayed = atSpielIsPlayed;
	}

	@Transient
	public String getResult()
	{
		if(this.getAtSpielIsPlayed())
		{
			return this.getAtSpielToreHeim() + ":" + this.getAtSpielToreGast();
		}
		else
		{
			return "&nbsp;-&nbsp;:&nbsp;-&nbsp;";
		}
	}
	
//	@ManyToOne
//	@JoinColumn(name="id_spieltag")
//	public EnSpieltag getSpieltag()
//	{
//		return this.spieltag;
//	}
//	public void setSpieltag(EnSpieltag spieltag)
//	{
//		this.spieltag = spieltag;
//	}
}