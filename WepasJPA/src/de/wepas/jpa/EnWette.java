package de.wepas.jpa;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;

/**
 * The persistent class for the en_wette database table.
 * 
 */
@Entity
@NamedQueries(
		{@NamedQuery(name = "EnWette.findAll", 
		 query = "SELECT a FROM EnWette a "),
		 @NamedQuery(name = "EnWette.findSieger",
		 query = "SELECT a FROM EnWette a WHERE a.atWetteIsSieger = 1"),
		 @NamedQuery(name = "EnWette.findGewinner",
		 query = "SELECT a FROM EnWette a WHERE a.atWetteIsGewonnen = 1"),
		 @NamedQuery(name = "EnWette.findBySpieltag",
		 query = "SELECT a FROM EnWette a WHERE a.idSpieltag = :spieltag"),
		 @NamedQuery(name = "EnWette.findByTipper",
		 query = "SELECT a FROM EnWette a WHERE a.idTipper = :tipper"),
		 @NamedQuery(name = "EnWette.findByTipperSpieltag",
		 query = "SELECT a FROM EnWette a WHERE a.idTipper = :tipper AND a.idSpieltag = :spieltag"),
		})

@Table(name = "en_wette")
public class EnWette implements Serializable
{
	private static final long serialVersionUID = -4800619607094023330L;

	@Id
	@Column(name = "id_wette")
	private int idWette;

	@Column(name = "at_wette_gewinn")
	private BigDecimal atWetteGewinn;

	@Column(name = "at_wette_is_gewonnen")
	private byte atWetteIsGewonnen;

	@Column(name = "at_wette_is_sieger")
	private byte atWetteIsSieger;

	@Column(name = "at_wette_punkte")
	private int atWettePunkte;

	@Column(name = "id_spieltag")
	private int idSpieltag;

	@Column(name = "id_tipper")
	private int idTipper;

	@Column(name = "at_wette_punktef")
	private int atWettePunktef;

	@Column(name = "at_wette_punktem")
	private int atWettePunktem;

	@Column(name = "at_wette_punktek")
	private int atWettePunktek;


	public EnWette()
	{
		this.atWetteGewinn = BigDecimal.ZERO;
		this.atWettePunkte = 0;
	}

	public int getIdWette()
	{
		return this.idWette;
	}

	public void setIdWette(int idWette)
	{
		this.idWette = idWette;
	}

	public BigDecimal getAtWetteGewinn()
	{
		return this.atWetteGewinn;
	}

	public void setAtWetteGewinn(BigDecimal atWetteGewinn)
	{
		this.atWetteGewinn = atWetteGewinn;
	}

	public boolean getAtWetteIsGewonnen()
	{
		return this.atWetteIsGewonnen == 1;
	}

	public void setAtWetteIsGewonnen(boolean atWetteIsGewonnen)
	{
		if(atWetteIsGewonnen)
		{
			this.atWetteIsGewonnen = 1;
		}
		else
		{
			this.atWetteIsGewonnen = 0;
		}
	}

	public boolean getAtWetteIsSieger()
	{
		return this.atWetteIsSieger == 1;
	}

	public void setAtWetteIsSieger(boolean atWetteIsSieger)
	{
		if(atWetteIsSieger)
		{
			this.atWetteIsSieger = 1;
		}
		else
		{
			this.atWetteIsSieger = 0;
		}
	}

	public int getAtWettePunkte()
	{
		return this.atWettePunkte;
	}

	public void setAtWettePunkte(int atWettePunkte)
	{
		this.atWettePunkte = atWettePunkte;
	}

	public int getIdSpieltag()
	{
		return this.idSpieltag;
	}

	public void setIdSpieltag(int idSpieltag)
	{
		this.idSpieltag = idSpieltag;
	}

	public int getIdTipper()
	{
		return this.idTipper;
	}

	public void setIdTipper(int idTipper)
	{
		this.idTipper = idTipper;
	}

	public int getAtWettePunktem()
	{
		return atWettePunktem;
	}

	public int getAtWettePunktek()
	{
		return atWettePunktek;
	}

	public void setAtWettePunktem(int atWettePunktem)
	{
		this.atWettePunktem = atWettePunktem;
	}

	public void setAtWettePunktek(int atWettePunktek)
	{
		this.atWettePunktek = atWettePunktek;
	}

	@Override
	public String toString()
	{
		return super.toString() + "/idWette:" + idWette + "/idSpieltag:" + idSpieltag + "/idTipper:" + idTipper;
	}

}