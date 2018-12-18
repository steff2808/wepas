package de.wepas.jpa;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;

/**
 * The persistent class for the en_tipper database table.
 * 
 */
@Entity
@NamedQueries(
	{ @NamedQuery(name = "EnTipper.findAll", query = "SELECT a FROM EnTipper a "),
			@NamedQuery(name = "EnTipper.findByID", query = "SELECT a FROM EnTipper a WHERE a.idTipper = :id"),
			@NamedQuery(name = "EnTipper.findByLogin", query = "SELECT a FROM EnTipper a WHERE a.atTipperLogin = :login"),
			@NamedQuery(name = "EnTipper.findByTeam", query = "SELECT a FROM EnTipper a WHERE a.atTipperTeam = :team") })
@Table(name = "en_tipper")
public class EnTipper implements Serializable
{
	private static final long serialVersionUID = -7119224052914374269L;
	
	@Id
	@Column(name = "id_tipper")
	private int idTipper;
	
	@Column(name = "at_tipper_konto")
	private BigDecimal atTipperKonto;
	
	@Column(name = "at_tipper_login")
	private String atTipperLogin;
	
	@Column(name = "at_tipper_name")
	private String atTipperName;
	
	@Column(name = "at_tipper_password")
	private String atTipperPassword;
	
	@Column(name = "at_tipper_siege")
	private int atTipperSiege;
	
	@Column(name = "at_tipper_zweite")
	private int atTipperZweite;
	
	@Column(name = "at_tipper_punkte")
	private int atTipperPunkte;
	
	@Column(name = "at_tipper_vorname")
	private String atTipperVorname;
	
	@Column(name = "at_tipper_team")
	private String atTipperTeam;
	
	@Column(name = "at_tipper_mail")
	private String atTipperMail;
	
	@Column(name = "at_tipper_tel")
	private String atTipperTel;
	
	@Column(name = "at_tipper_mobil")
	private String atTipperMobil;
	
	@Column(name = "at_tipper_iban")
	private String atTipperIban;
	
	@Column(name = "at_tipper_form")
	private String atTipperForm;
	
	@Column(name = "at_tipper_tippennext")
	private String atTipperTippennext;
	
	@Transient
	private boolean own;
	
	public EnTipper()
	{
		this.atTipperLogin = "default";
		this.atTipperPassword = "default";
		this.atTipperKonto = new BigDecimal(0);
	}
	
	public int getIdTipper()
	{
		return this.idTipper;
	}
	
	public void setIdTipper(int idTipper)
	{
		this.idTipper = idTipper;
	}
	
	public BigDecimal getAtTipperKonto()
	{
		return this.atTipperKonto;
	}
	
	public void setAtTipperKonto(BigDecimal atTipperKonto)
	{
		this.atTipperKonto = atTipperKonto;
	}
	
	public String getAtTipperLogin()
	{
		return this.atTipperLogin;
	}
	
	public void setAtTipperLogin(String atTipperLogin)
	{
		this.atTipperLogin = atTipperLogin;
	}
	
	public String getAtTipperName()
	{
		return this.atTipperName;
	}
	
	public void setAtTipperName(String atTipperName)
	{
		this.atTipperName = atTipperName;
	}
	
	public String getAtTipperPassword()
	{
		return this.atTipperPassword;
	}
	
	public void setAtTipperPassword(String atTipperPassword)
	{
		this.atTipperPassword = atTipperPassword;
	}
	
	public int getAtTipperSiege()
	{
		return this.atTipperSiege;
	}
	
	public void setAtTipperSiege(int atTipperSiege)
	{
		this.atTipperSiege = atTipperSiege;
	}
	
	public int getAtTipperPunkte()
	{
		return this.atTipperPunkte;
	}
	
	public void setAtTipperPunkte(int atTipperPunkte)
	{
		this.atTipperPunkte = atTipperPunkte;
	}
	
	public String getAtTipperVorname()
	{
		return this.atTipperVorname;
	}
	
	public void setAtTipperVorname(String atTipperVorname)
	{
		this.atTipperVorname = atTipperVorname;
	}
	
	public String getAtTipperTeam()
	{
		return atTipperTeam;
	}
	
	public void setAtTipperTeam(String atTipperTeam)
	{
		this.atTipperTeam = atTipperTeam;
	}

	public int getAtTipperZweite()
	{
		return atTipperZweite;
	}
	
	public void setAtTipperZweite(int atTipperZweite)
	{
		this.atTipperZweite = atTipperZweite;
	}
	
	public String getAtTipperMail()
	{
		return atTipperMail;
	}
	
	public void setAtTipperMail(String atTipperMail)
	{
		this.atTipperMail = atTipperMail;
	}
	
	public String getAtTipperTel()
	{
		return atTipperTel;
	}
	
	public void setAtTipperTel(String atTipperTel)
	{
		this.atTipperTel = atTipperTel;
	}
	
	public String getAtTipperMobil()
	{
		return atTipperMobil;
	}
	
	public void setAtTipperMobil(String atTipperMobil)
	{
		this.atTipperMobil = atTipperMobil;
	}
	
	public String getAtTipperIban()
	{
		return atTipperIban;
	}
	
	public void setAtTipperIban(String atTipperIban)
	{
		this.atTipperIban = atTipperIban;
	}
	
	public String getAtTipperForm()
	{
		return atTipperForm;
	}
	
	public void setAtTipperForm(String atTipperForm)
	{
		this.atTipperForm = atTipperForm;
	}
	
	public String getAtTipperTippennext()
	{
		return atTipperTippennext;
	}

	public void setAtTipperTippennext(String atTipperTippennext)
	{
		this.atTipperTippennext = atTipperTippennext;
	}

	public boolean isOwn()
	{
		return own;
	}
	
	public void setOwn(boolean own)
	{
		this.own = own;
	}
	
}