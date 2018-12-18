package de.wepas.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the en_spieltag database table.
 * 
 */
@Entity
@NamedQueries(
	{ @NamedQuery(name = "EnSpieltag.findAll", query = "SELECT a FROM EnSpieltag a "),
	  @NamedQuery(name = "EnSpieltag.findCurrent", query = "SELECT a FROM EnSpieltag a WHERE a.atSpieltagIsStarted = 1 ORDER BY a.atSpieltagStart DESC"),
	  @NamedQuery(name = "EnSpieltag.findNext", query = "SELECT a FROM EnSpieltag a WHERE a.atSpieltagIsStarted = 0 ORDER BY a.atSpieltagStart"), })
@Table(name = "en_spieltag")
public class EnSpieltag implements Serializable
{
	private static final long serialVersionUID = -7245214932050919904L;
	
	@Id
	@Column(name = "id_spieltag")
	private int idSpieltag;
	
	@Column(name = "fk_spieltag_einstellung")
	private int fkSpieltagEinstellung;
	
	@Column(name = "at_spieltag_kosten")
	private BigDecimal atSpieltagKosten;
	
	@Column(name = "at_spieltag_einsatz")
	private BigDecimal atSpieltagEinsatz;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "at_spieltag_ende")
	private Date atSpieltagEnde;
	
	@Column(name = "at_spieltag_is_duty")
	private byte atSpieltagIsDuty;
	
	@Column(name = "at_spieltag_is_finished")
	private byte atSpieltagIsFinished;
	
	@Column(name = "at_spieltag_is_started")
	private byte atSpieltagIsStarted;
	
	@Column(name = "at_spieltag_nummer")
	private int atSpieltagNummer;
	
	@Column(name = "at_spieltag_paarungen")
	private int atSpieltagPaarungen;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "at_spieltag_start")
	private Date atSpieltagStart;
	
	@Column(name = "at_spieltag_supertipps")
	private int atSpieltagSupertipps;
	
	@Column(name = "at_spieltag_text")
	private String atSpieltagText;
	
	// private Collection<EnSpiel> spiele = new ArrayList<EnSpiel>();
	
	public EnSpieltag()
	{
		this.setAtSpieltagEinsatz(BigDecimal.ZERO);
	}
	
	public int getIdSpieltag()
	{
		return this.idSpieltag;
	}
	
	public void setIdSpieltag(int idSpieltag)
	{
		this.idSpieltag = idSpieltag;
	}
	
	public int getFkSpieltagEinstellung()
	{
		return fkSpieltagEinstellung;
	}
	
	public void setFkSpieltagEinstellung(int fkSpieltagEinstellung)
	{
		this.fkSpieltagEinstellung = fkSpieltagEinstellung;
	}
	
	public BigDecimal getAtSpieltagKosten()
	{
		return this.atSpieltagKosten;
	}
	
	public void setAtSpieltagKosten(BigDecimal atSpieltagKosten)
	{
		this.atSpieltagKosten = atSpieltagKosten;
	}
	
	public BigDecimal getAtSpieltagEinsatz()
	{
		return this.atSpieltagEinsatz;
	}
	
	public void setAtSpieltagEinsatz(BigDecimal atSpieltagEinsatz)
	{
		this.atSpieltagEinsatz = atSpieltagEinsatz;
	}
	
	public Date getAtSpieltagEnde()
	{
		return this.atSpieltagEnde;
	}
	
	public void setAtSpieltagEnde(Date atSpieltagEnde)
	{
		this.atSpieltagEnde = atSpieltagEnde;
	}
	
	public boolean getAtSpieltagIsDuty()
	{
		return this.atSpieltagIsDuty == 1;
	}
	
	public void setAtSpieltagIsDuty(boolean isDuty)
	{
		if (isDuty)
		{
			this.atSpieltagIsDuty = 1;
		}
		else
		{
			this.atSpieltagIsDuty = 0;
		}
	}
	
	public boolean getAtSpieltagIsFinished()
	{
		return this.atSpieltagIsFinished == 1;
	}
	
	public void setAtSpieltagIsFinished(boolean isFinished)
	{
		if (isFinished)
			this.atSpieltagIsFinished = 1;
		else
			this.atSpieltagIsFinished = 0;
	}
	
	public boolean getAtSpieltagIsStarted()
	{
		return this.atSpieltagIsStarted == 1;
	}
	
	public void setAtSpieltagIsStarted(boolean isStarted)
	{
		if (isStarted)
			this.atSpieltagIsStarted = 1;
		else
			this.atSpieltagIsStarted = 0;
	}
	
	public int getAtSpieltagNummer()
	{
		return this.atSpieltagNummer;
	}
	
	public void setAtSpieltagNummer(int atSpieltagNummer)
	{
		this.atSpieltagNummer = atSpieltagNummer;
	}
	
	public int getAtSpieltagPaarungen()
	{
		return this.atSpieltagPaarungen;
	}
	
	public void setAtSpieltagPaarungen(int atSpieltagPaarungen)
	{
		this.atSpieltagPaarungen = atSpieltagPaarungen;
	}
	
	public Date getAtSpieltagStart()
	{
		return this.atSpieltagStart;
	}
	
	public void setAtSpieltagStart(Date atSpieltagStart)
	{
		this.atSpieltagStart = atSpieltagStart;
	}
	
	public int getAtSpieltagSupertipps()
	{
		return this.atSpieltagSupertipps;
	}
	
	public void setAtSpieltagSupertipps(int atSpieltagSupertipps)
	{
		this.atSpieltagSupertipps = atSpieltagSupertipps;
	}
	
	public String getAtSpieltagText()
	{
		return this.atSpieltagText;
	}
	
	public void setAtSpieltagText(String atSpieltagText)
	{
		this.atSpieltagText = atSpieltagText;
	}
	
	// @OneToMany(fetch=FetchType.LAZY, mappedBy="spieltag")
	// public Collection<EnSpiel> getSpiele()
	// {
	// return this.spiele;
	// }
	// public void setSpiele(Collection<EnSpiel> spiele)
	// {
	// this.spiele = spiele;
	// }
}