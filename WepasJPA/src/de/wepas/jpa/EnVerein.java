package de.wepas.jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the en_verein database table.
 * 
 */
@Entity
@NamedQueries(
	{ @NamedQuery(name = "EnVerein.findAll", query = "SELECT a FROM EnVerein a ") })
@Table(name = "en_verein")
public class EnVerein implements Serializable
{
	private static final long serialVersionUID = -6280112764125855170L;
	
	@Id
	@Column(name = "id_verein")
	private int idVerein;
	
	@Column(name = "at_verein_kurz")
	private String atVereinKurz;
	
	@Column(name = "at_verein_name")
	private String atVereinName;

	@Column(name = "at_verein_regex")
	private String atVereinRegex;

	@Column(name = "at_verein_sp")
	private int atVereinSp;
	@Column(name = "at_verein_si")
	private int atVereinSi;
	@Column(name = "at_verein_un")
	private int atVereinUn;
	@Column(name = "at_verein_ni")
	private int atVereinNi;
	@Column(name = "at_verein_tg")
	private int atVereinTg;
	@Column(name = "at_verein_tk")
	private int atVereinTk;
	@Column(name = "at_verein_punke")
	private int atVereinPunkte;
	
	@Column(name = "at_verein_hsp")
	private int atVereinHSp;
	@Column(name = "at_verein_hsi")
	private int atVereinHSi;
	@Column(name = "at_verein_hun")
	private int atVereinHUn;
	@Column(name = "at_verein_hni")
	private int atVereinHNi;
	@Column(name = "at_verein_htg")
	private int atVereinHTg;
	@Column(name = "at_verein_htk")
	private int atVereinHTk;
	@Column(name = "at_verein_hpunke")
	private int atVereinHPunkte;
	
	@Column(name = "at_verein_asp")
	private int atVereinASp;
	@Column(name = "at_verein_asi")
	private int atVereinASi;
	@Column(name = "at_verein_aun")
	private int atVereinAUn;
	@Column(name = "at_verein_ani")
	private int atVereinANi;
	@Column(name = "at_verein_atg")
	private int atVereinATg;
	@Column(name = "at_verein_atk")
	private int atVereinATk;
	@Column(name = "at_verein_apunke")
	private int atVereinAPunkte;
	
	@Column(name = "at_verein_hrsp")
	private int atVereinHRSp;
	@Column(name = "at_verein_hrsi")
	private int atVereinHRSi;
	@Column(name = "at_verein_hrun")
	private int atVereinHRUn;
	@Column(name = "at_verein_hrni")
	private int atVereinHRNi;
	@Column(name = "at_verein_hrtg")
	private int atVereinHRTg;
	@Column(name = "at_verein_hrtk")
	private int atVereinHRTk;
	@Column(name = "at_verein_hrpunke")
	private int atVereinHRPunkte;
	
	@Column(name = "at_verein_rrsp")
	private int atVereinRRSp;
	@Column(name = "at_verein_rrsi")
	private int atVereinRRSi;
	@Column(name = "at_verein_rrun")
	private int atVereinRRUn;
	@Column(name = "at_verein_rrni")
	private int atVereinRRNi;
	@Column(name = "at_verein_rrtg")
	private int atVereinRRTg;
	@Column(name = "at_verein_rrtk")
	private int atVereinRRTk;
	@Column(name = "at_verein_rrpunke")
	private int atVereinRRPunkte;
	
	@Column(name = "at_verein_form")
	private String atVereinForm;
	
	public EnVerein()
	{
	}

	public int getIdVerein()
	{
		return idVerein;
	}

	public void setIdVerein(int idVerein)
	{
		this.idVerein = idVerein;
	}

	public String getAtVereinKurz()
	{
		return atVereinKurz;
	}

	public void setAtVereinKurz(String atVereinKurz)
	{
		this.atVereinKurz = atVereinKurz;
	}

	public String getAtVereinName()
	{
		return atVereinName;
	}

	public void setAtVereinName(String atVereinName)
	{
		this.atVereinName = atVereinName;
	}

	public String getAtVereinRegex()
	{
		return atVereinRegex;
	}

	public void setAtVereinRegex(String atVereinRegex)
	{
		this.atVereinRegex = atVereinRegex;
	}

	public int getAtVereinSp()
	{
		return atVereinSp;
	}

	public void setAtVereinSp(int atVereinSp)
	{
		this.atVereinSp = atVereinSp;
	}

	public int getAtVereinSi()
	{
		return atVereinSi;
	}

	public void setAtVereinSi(int atVereinSi)
	{
		this.atVereinSi = atVereinSi;
	}

	public int getAtVereinUn()
	{
		return atVereinUn;
	}

	public void setAtVereinUn(int atVereinUn)
	{
		this.atVereinUn = atVereinUn;
	}

	public int getAtVereinNi()
	{
		return atVereinNi;
	}

	public void setAtVereinNi(int atVereinNi)
	{
		this.atVereinNi = atVereinNi;
	}

	public int getAtVereinTg()
	{
		return atVereinTg;
	}

	public void setAtVereinTg(int atVereinTg)
	{
		this.atVereinTg = atVereinTg;
	}

	public int getAtVereinTk()
	{
		return atVereinTk;
	}

	public void setAtVereinTk(int atVereinTk)
	{
		this.atVereinTk = atVereinTk;
	}

	public int getAtVereinPunkte()
	{
		return atVereinPunkte;
	}

	public void setAtVereinPunkte(int atVereinPunkte)
	{
		this.atVereinPunkte = atVereinPunkte;
	}

	public int getAtVereinHSp()
	{
		return atVereinHSp;
	}

	public void setAtVereinHSp(int atVereinHSp)
	{
		this.atVereinHSp = atVereinHSp;
	}

	public int getAtVereinHSi()
	{
		return atVereinHSi;
	}

	public void setAtVereinHSi(int atVereinHSi)
	{
		this.atVereinHSi = atVereinHSi;
	}

	public int getAtVereinHUn()
	{
		return atVereinHUn;
	}

	public void setAtVereinHUn(int atVereinHUn)
	{
		this.atVereinHUn = atVereinHUn;
	}

	public int getAtVereinHNi()
	{
		return atVereinHNi;
	}

	public void setAtVereinHNi(int atVereinHNi)
	{
		this.atVereinHNi = atVereinHNi;
	}

	public int getAtVereinHTg()
	{
		return atVereinHTg;
	}

	public void setAtVereinHTg(int atVereinHTg)
	{
		this.atVereinHTg = atVereinHTg;
	}

	public int getAtVereinHTk()
	{
		return atVereinHTk;
	}

	public void setAtVereinHTk(int atVereinHTk)
	{
		this.atVereinHTk = atVereinHTk;
	}

	public int getAtVereinHPunkte()
	{
		return atVereinHPunkte;
	}

	public void setAtVereinHPunkte(int atVereinHPunkte)
	{
		this.atVereinHPunkte = atVereinHPunkte;
	}

	public int getAtVereinASp()
	{
		return atVereinASp;
	}

	public void setAtVereinASp(int atVereinASp)
	{
		this.atVereinASp = atVereinASp;
	}

	public int getAtVereinASi()
	{
		return atVereinASi;
	}

	public void setAtVereinASi(int atVereinASi)
	{
		this.atVereinASi = atVereinASi;
	}

	public int getAtVereinAUn()
	{
		return atVereinAUn;
	}

	public void setAtVereinAUn(int atVereinAUn)
	{
		this.atVereinAUn = atVereinAUn;
	}

	public int getAtVereinANi()
	{
		return atVereinANi;
	}

	public void setAtVereinANi(int atVereinANi)
	{
		this.atVereinANi = atVereinANi;
	}

	public int getAtVereinATg()
	{
		return atVereinATg;
	}

	public void setAtVereinATg(int atVereinATg)
	{
		this.atVereinATg = atVereinATg;
	}

	public int getAtVereinATk()
	{
		return atVereinATk;
	}

	public void setAtVereinATk(int atVereinATk)
	{
		this.atVereinATk = atVereinATk;
	}

	public int getAtVereinAPunkte()
	{
		return atVereinAPunkte;
	}

	public void setAtVereinAPunkte(int atVereinAPunkte)
	{
		this.atVereinAPunkte = atVereinAPunkte;
	}

	public int getAtVereinHRSp()
	{
		return atVereinHRSp;
	}

	public void setAtVereinHRSp(int atVereinHRSp)
	{
		this.atVereinHRSp = atVereinHRSp;
	}

	public int getAtVereinHRSi()
	{
		return atVereinHRSi;
	}

	public void setAtVereinHRSi(int atVereinHRSi)
	{
		this.atVereinHRSi = atVereinHRSi;
	}

	public int getAtVereinHRUn()
	{
		return atVereinHRUn;
	}

	public void setAtVereinHRUn(int atVereinHRUn)
	{
		this.atVereinHRUn = atVereinHRUn;
	}

	public int getAtVereinHRNi()
	{
		return atVereinHRNi;
	}

	public void setAtVereinHRNi(int atVereinHRNi)
	{
		this.atVereinHRNi = atVereinHRNi;
	}

	public int getAtVereinHRTg()
	{
		return atVereinHRTg;
	}

	public void setAtVereinHRTg(int atVereinHRTg)
	{
		this.atVereinHRTg = atVereinHRTg;
	}

	public int getAtVereinHRTk()
	{
		return atVereinHRTk;
	}

	public void setAtVereinHRTk(int atVereinHRTk)
	{
		this.atVereinHRTk = atVereinHRTk;
	}

	public int getAtVereinHRPunkte()
	{
		return atVereinHRPunkte;
	}

	public void setAtVereinHRPunkte(int atVereinHRPunkte)
	{
		this.atVereinHRPunkte = atVereinHRPunkte;
	}

	public int getAtVereinRRSp()
	{
		return atVereinRRSp;
	}

	public void setAtVereinRRSp(int atVereinRRSp)
	{
		this.atVereinRRSp = atVereinRRSp;
	}

	public int getAtVereinRRSi()
	{
		return atVereinRRSi;
	}

	public void setAtVereinRRSi(int atVereinRRSi)
	{
		this.atVereinRRSi = atVereinRRSi;
	}

	public int getAtVereinRRUn()
	{
		return atVereinRRUn;
	}

	public void setAtVereinRRUn(int atVereinRRUn)
	{
		this.atVereinRRUn = atVereinRRUn;
	}

	public int getAtVereinRRNi()
	{
		return atVereinRRNi;
	}

	public void setAtVereinRRNi(int atVereinRRNi)
	{
		this.atVereinRRNi = atVereinRRNi;
	}

	public int getAtVereinRRTg()
	{
		return atVereinRRTg;
	}

	public void setAtVereinRRTg(int atVereinRRTg)
	{
		this.atVereinRRTg = atVereinRRTg;
	}

	public int getAtVereinRRTk()
	{
		return atVereinRRTk;
	}

	public void setAtVereinRRTk(int atVereinRRTk)
	{
		this.atVereinRRTk = atVereinRRTk;
	}

	public int getAtVereinRRPunkte()
	{
		return atVereinRRPunkte;
	}

	public void setAtVereinRRPunkte(int atVereinRRPunkte)
	{
		this.atVereinRRPunkte = atVereinRRPunkte;
	}

	public String getAtVereinForm()
	{
		return atVereinForm;
	}

	public void setAtVereinForm(String atVereinForm)
	{
		this.atVereinForm = atVereinForm;
	}
	
	@Override
	public String toString()
	{
		return this.getIdVerein() + "/" + this.getAtVereinKurz() + "/" + this.getAtVereinName();
	}
}