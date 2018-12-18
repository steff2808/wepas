package de.wepas.jpa;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 * The persistent class for the en_einstellung database table.
 * 
 */
@Entity
@NamedQueries(
		{@NamedQuery(name = "EnEinstellung.findAll", 
		 query = "SELECT a FROM EnEinstellung a "),
		 @NamedQuery(name = "EnEinstellung.findBySchluessel",
		 query = "SELECT a FROM EnEinstellung a WHERE a.atEinstellungSchluessel = :schluessel"),
		 @NamedQuery(name = "EnEinstellung.findById",
		 query = "SELECT a FROM EnEinstellung a WHERE a.idEinstellung = :id"),		
		})


@Table(name = "en_einstellung")
public class EnEinstellung implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_einstellung")
	private int idEinstellung;

	@Column(name = "at_einstellung_schluessel")
	private String atEinstellungSchluessel;

	@Column(name = "at_einstellung_wert_bool")
	private byte atEinstellungWertBool;

	@Column(name = "at_einstellung_wert_cha10")
	private String atEinstellungWertCha10;

	@Column(name = "at_einstellung_wert_cha30")
	private String atEinstellungWertCha30;

	@Column(name = "at_einstellung_wert_dec")
	private BigDecimal atEinstellungWertDec;

	@Column(name = "at_einstellung_wert_int")
	private int atEinstellungWertInt;

	@Column(name = "fk_variabel")
	private int fkVariabel;

	public EnEinstellung()
	{
	}

	public int getIdEinstellung()
	{
		return this.idEinstellung;
	}

	public void setIdEinstellung(int idEinstellung)
	{
		this.idEinstellung = idEinstellung;
	}

	public String getAtEinstellungSchluessel()
	{
		return this.atEinstellungSchluessel;
	}

	public void setAtEinstellungSchluessel(String atEinstellungSchluessel)
	{
		this.atEinstellungSchluessel = atEinstellungSchluessel;
	}

	public byte getAtEinstellungWertBool()
	{
		return this.atEinstellungWertBool;
	}

	public void setAtEinstellungWertBool(byte atEinstellungWertBool)
	{
		this.atEinstellungWertBool = atEinstellungWertBool;
	}

	public String getAtEinstellungWertCha10()
	{
		return this.atEinstellungWertCha10;
	}

	public void setAtEinstellungWertCha10(String atEinstellungWertCha10)
	{
		this.atEinstellungWertCha10 = atEinstellungWertCha10;
	}

	public String getAtEinstellungWertCha30()
	{
		return this.atEinstellungWertCha30;
	}

	public void setAtEinstellungWertCha30(String atEinstellungWertCha30)
	{
		this.atEinstellungWertCha30 = atEinstellungWertCha30;
	}

	public BigDecimal getAtEinstellungWertDec()
	{
		return this.atEinstellungWertDec;
	}

	public void setAtEinstellungWertDec(BigDecimal atEinstellungWertDec)
	{
		this.atEinstellungWertDec = atEinstellungWertDec;
	}

	public int getAtEinstellungWertInt()
	{
		return this.atEinstellungWertInt;
	}

	public void setAtEinstellungWertInt(int atEinstellungWertInt)
	{
		this.atEinstellungWertInt = atEinstellungWertInt;
	}

	public int getFkVariabel()
	{
		return this.fkVariabel;
	}

	public void setfkVariabel(int fkVariabel)
	{
		this.fkVariabel = fkVariabel;
	}

}