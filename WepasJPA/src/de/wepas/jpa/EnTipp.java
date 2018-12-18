package de.wepas.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the en_tipp database table.
 * 
 */
@Entity
@NamedQueries(
		{ @NamedQuery(name = "EnTipp.findAll",       
		  query = "SELECT a FROM EnTipp a "), 
		  @NamedQuery(name = "EnTipp.findSupertreffer",
		  query = "SELECT a FROM EnTipp a WHERE a.atTippPunkte = 6"),
		  @NamedQuery(name = "EnTipp.findTreffer",
		  query = "SELECT a FROM EnTipp a WHERE a.atTippPunkte = 6 OR a.atTippPunkte = 3"),
		  @NamedQuery(name = "EnTipp.findByTipperSpiel", 
		  query = "SELECT a FROM EnTipp a WHERE a.idTipper = :tipper AND a.idSpiel = :spiel ORDER BY a.idTipp"),
		  @NamedQuery(name = "EnTipp.findById",       
		  query = "SELECT a FROM EnTipp a WHERE a.idTipp = :id"),
		  @NamedQuery(name = "EnTipp.findByTipper", 
		  query = "SELECT a FROM EnTipp a WHERE a.idTipper = :tipper ORDER BY a.idTipp"),
		  @NamedQuery(name = "EnTipp.findValidByTipper", 
		  query = "SELECT a FROM EnTipp a WHERE a.idTipper = :tipper AND a.atTippIsValid = 1 ORDER BY a.idTipp"),
		  @NamedQuery(name = "EnTipp.findBySpiel",       
		  query = "SELECT a FROM EnTipp a WHERE a.idSpiel = :spiel  ORDER BY a.idTipp"),
		  @NamedQuery(name = "EnTipp.findBlinde",       
		  query = "SELECT a FROM EnTipp a WHERE a.atTippPunktem > 3 AND a.atTippPunkte = 0"),
		})

@Table(name = "en_tipp")
public class EnTipp implements Serializable
{
//	private static Log log = LogFactory.getLog(EnTipp.class);
	private static final long serialVersionUID = 1843431454195047661L;

	@Id
	@Column(name = "id_tipp")
	private int idTipp;

	@Column(name = "at_tipp_is_differenz")
	private byte atTippIsDifferenz;

	@Column(name = "at_tipp_is_exakt")
	private byte atTippIsExakt;

	@Column(name = "at_tipp_is_supertipp")
	private byte atTippIsSupertipp;

	@Column(name = "at_tipp_is_tendenz")
	private byte atTippIsTendenz;

	@Column(name = "at_tipp_is_valid")
	private byte atTippIsValid;

	@Column(name = "at_tipp_punkte")
	private int atTippPunkte;

	@Column(name = "at_tipp_tor_gast")
	private int atTippTorGast;

	@Column(name = "at_tipp_tor_heim")
	private int atTippTorHeim;

	@Column(name = "id_spiel")
	private int idSpiel;

	@Column(name = "id_tipper")
	private int idTipper;
	
	@Column(name = "at_tipp_punktem")
	private int atTippPunktem;

	@Column(name = "at_tipp_punktek")
	private int atTippPunktek;
	
	@Transient
	private boolean numberFormat;
	
	public boolean isNumberFormat()
	{
		return numberFormat;
	}

	public void setNumberFormat(boolean numberFormat)
	{
		this.numberFormat = numberFormat;
	}

	public EnTipp()
	{
	}

	public int getIdTipp()
	{
		return this.idTipp;
	}

	public void setIdTipp(int idTipp)
	{
		this.idTipp = idTipp;
	}

	public boolean getAtTippIsDifferenz()
	{
		return this.atTippIsDifferenz == 1;
	}

	public void setAtTippIsDifferenz(boolean atTippIsDifferenz)
	{
		if(atTippIsDifferenz)
		{
			this.atTippIsDifferenz = 1;
		}
		else
		{
			this.atTippIsDifferenz = 0;
		}
	}

	public boolean getAtTippIsExakt()
	{
		return this.atTippIsExakt == 1;
	}

	public void setAtTippIsExakt(boolean atTippIsExakt)
	{
		if(atTippIsExakt)
		{
			this.atTippIsExakt = 1;
		}
		else
		{
			this.atTippIsExakt = 0;
		}
	}

	public boolean getAtTippIsSupertipp()
	{
		return this.atTippIsSupertipp == 1;
	}

	public void setAtTippIsSupertipp(boolean atTippIsSupertipp)
	{
		if(atTippIsSupertipp)
		{
			this.atTippIsSupertipp = 1;
		}
		else
		{
			this.atTippIsSupertipp = 0;
		}
	}

	public boolean getAtTippIsTendenz()
	{
		return this.atTippIsTendenz == 1;
	}

	public void setAtTippIsTendenz(boolean atTippIsTendenz)
	{
		if(atTippIsTendenz)
		{
			this.atTippIsTendenz = 1;
		}
		else
		{
			this.atTippIsTendenz= 0;
		}
	}

	public boolean getAtTippIsValid()
	{
		return this.atTippIsValid == 1;
	}

	public void setAtTippIsValid(boolean atTippIsValid)
	{
		if(atTippIsValid)
		{
			this.atTippIsValid = 1;
		}
		else
		{
			this.atTippIsValid= 0;
		}
	}

	public int getAtTippPunkte()
	{
		return this.atTippPunkte;
	}

	public void setAtTippPunkte(int atTippPunkte)
	{
		this.atTippPunkte = atTippPunkte;
	}

	public int getAtTippTorGast()
	{
		return this.atTippTorGast;
	}

	public void setAtTippTorGast(int atTippTorGast)
	{
		this.atTippTorGast = atTippTorGast;
	}

	public int getAtTippTorHeim()
	{
		return this.atTippTorHeim;
	}

	public void setAtTippTorHeim(int atTippTorHeim)
	{
		this.atTippTorHeim = atTippTorHeim;
	}

	public int getAtTippBlind()
	{
		return atTippPunktem;
	}

	public int getAtTippTorsumme()
	{
		return atTippPunktek;
	}

	public void setAtTippBlind(int atTippBlind)
	{
		this.atTippPunktem = atTippBlind;
	}

	public void setAtTippTorsumme(int atTippTorsumme)
	{
		this.atTippPunktek = atTippTorsumme;
	}

	public int getIdSpiel()
	{
		return this.idSpiel;
	}

	public void setIdSpiel(int idSpiel)
	{
		this.idSpiel = idSpiel;
	}

	public int getIdTipper()
	{
		return this.idTipper;
	}

	public void setIdTipper(int idTipper)
	{
		this.idTipper = idTipper;
	}

	@Override
	public String toString()
	{
		return "Spiel:" + this.idSpiel + " Tipper" + this.idTipper + " --> " + this.getAtTippTorHeim() + ":" + this.getAtTippTorGast() + "isSupertipp:" + this.getAtTippIsSupertipp() + " isPlayed: " + this.atTippIsValid;
	}

	public void analyse(EnSpiel spiel)
	{
		if(this.getAtTippTorGast() == 9 && this.getAtTippTorHeim() == 9)
		{
//---> Für ungültige Tipps gibt's nix!
			return;
		}
		this.setAtTippPunkte(0);
		if(this.getIdSpiel() != spiel.getIdSpiel())
		{
			return;
		}
//---> Stimmt die Tendenz?
		if((this.getAtTippTorHeim() > this.getAtTippTorGast() && spiel.getAtSpielToreHeim() > spiel.getAtSpielToreGast()) 
				||
			this.getAtTippTorHeim() == this.getAtTippTorGast() && spiel.getAtSpielToreHeim() == spiel.getAtSpielToreGast()
				||
			this.getAtTippTorHeim() < this.getAtTippTorGast() && spiel.getAtSpielToreHeim() < spiel.getAtSpielToreGast())
		{
//---> Richtig auf Sieg/Unentschieden/Niederlage getippt
			this.setAtTippIsTendenz(true);
			this.setAtTippPunkte(this.getAtTippPunkte() + 1);

//---> Stimmt die Tordifferenz?			
			if(this.getAtTippTorHeim() - this.getAtTippTorGast() == spiel.getAtSpielToreHeim() - spiel.getAtSpielToreGast())
			{
//---> Differen stimmt - also ein 2:0 Tipp hat bei 3:1 oder 4:2 die richtige Differnz
				this.setAtTippIsDifferenz(true);
				this.setAtTippPunkte(this.getAtTippPunkte() + 1);
//---> Ist das exakte Ergebnis richtig getippt?				
				if(this.getAtTippTorHeim() + this.getAtTippTorGast() == spiel.getAtSpielToreHeim() + spiel.getAtSpielToreGast())
				{
//---> Auch die Anzahl der Tore stimmt - Volltreffer!
					this.setAtTippIsExakt(true);
					this.setAtTippPunkte(this.getAtTippPunkte() + 1);
				}
			}
		}
		if(this.getAtTippIsSupertipp())
		{
			this.setAtTippPunkte(this.getAtTippPunkte() * 2);
		}
		this.setAtTippIsValid(true);
//log.info("Tipp: " + this.getAtTippTorHeim() + ":" + this.getAtTippTorGast() + "  Spiel: " + spiel.getAtSpielToreHeim() + ":" + spiel.getAtSpielToreGast());		
//log.info("difftipp: " + (this.getAtTippTorHeim() - this.getAtTippTorGast()));		
		int difftipp = this.getAtTippTorHeim() - this.getAtTippTorGast();
//log.info("diffspiel: " + (spiel.getAtSpielToreHeim() - spiel.getAtSpielToreGast()));		
		int diffspiel = spiel.getAtSpielToreHeim() - spiel.getAtSpielToreGast();
//log.info("blindtordiff: " + (difftipp + (diffspiel * -1)));		
		int blindTordiff = difftipp + (diffspiel * -1);
		if(blindTordiff < 0)
		{
			blindTordiff = blindTordiff * -1;
		}
		this.setAtTippBlind(blindTordiff);
		this.setAtTippTorsumme((spiel.getAtSpielToreHeim() + spiel.getAtSpielToreGast()) - (this.getAtTippTorHeim() + this.getAtTippTorGast()));
//log.info("blindtordiff: " + blindTordiff);	
//log.info("------------------------------------------------------");	
		
//		this.analysek(spiel);
//		this.analysem(spiel);
	}
	
//	private void analysem(EnSpiel spiel)
//	{
//		if(this.getAtTippTorGast() == 9 && this.getAtTippTorHeim() == 9)
//		{
////---> Für ungültige Tipps gibt's nix!
//			return;
//		}
////		this.setAtTippPunktem(0);
//		if(this.getIdSpiel() != spiel.getIdSpiel())
//		{
//			return;
//		}
//		
//		int basispunkte = 0;
//		boolean tendenz = false;
//		
////---> Stimmt die Tendenz?
//		if((getAtTippTorHeim() > this.getAtTippTorGast() && spiel.getAtSpielToreHeim() > spiel.getAtSpielToreGast()) 
//				||
//			this.getAtTippTorHeim() == this.getAtTippTorGast() && spiel.getAtSpielToreHeim() == spiel.getAtSpielToreGast()
//				||
//			this.getAtTippTorHeim() < this.getAtTippTorGast() && spiel.getAtSpielToreHeim() < spiel.getAtSpielToreGast())
//		{
////---> Richtig auf Sieg/Unentschieden/Niederlage getippt
//			tendenz = true;
//			basispunkte = 7;
////			this.setAt_tipp_punkte(this.getAt_tipp_punkte() + 1);
//
////---> Stimmt die Tordifferenz?			
//			if(this.getAtTippTorHeim() - this.getAtTippTorGast() == spiel.getAtSpielToreHeim() - spiel.getAtSpielToreGast())
//			{
////---> Differenz stimmt - also ein 2:0 Tipp hat bei 3:1 oder 4:2 die richtige Differnz
//				basispunkte =  8;
////				this.setAt_tipp_punkte(this.getAt_tipp_punkte() + 1);
////---> Ist das exakte Ergebnis richtig getippt?				
//				if(this.getAtTippTorHeim() + this.getAtTippTorGast() == spiel.getAtSpielToreHeim() + spiel.getAtSpielToreGast())
//				{
////---> Auch die Anzahl der Tore stimmt - Volltreffer!
//					basispunkte = 10;
////					this.setAt_tipp_punkte(this.getAt_tipp_punkte() + 1);
//				}
//			}
//		
//			int tph = spiel.getAtSpielToreHeim() - this.getAtTippTorHeim();
//			if(tph < 0)
//			{
//				basispunkte = basispunkte + tph;
//			}
//			else
//			{
//				basispunkte = basispunkte - tph;
//			}
//		
//			int tpg = spiel.getAtSpielToreGast() - this.getAtTippTorGast();
//			if(tpg < 0)
//			{
//				basispunkte = basispunkte + tpg;
//			}
//			else
//			{
//				basispunkte = basispunkte - tpg;
//			}
//		}	
//		if(tendenz && basispunkte < 0)
//		{
//			basispunkte = 1;
//		}
//		
////		this.setAtTippPunktem(basispunkte);
//		
//		if(this.getAtTippIsSupertipp())
//		{
////			this.setAtTippPunktem(this.getAtTippPunktem() * 2);
//		}
////System.out.println("--->PunkteMutz = " + this.getAtTippPunktem());
//	}
//
//	private void analysek(EnSpiel spiel)
//	{
//		if(this.getAtTippTorGast() == 9 && this.getAtTippTorHeim() == 9)
//		{
////---> Für ungültige Tipps gibt's nix!
//			return;
//		}
////		this.setAtTippPunktek(0);
//		if(this.getIdSpiel() != spiel.getIdSpiel())
//		{
//			return;
//		}
////---> Stimmt die Tendenz?
//		if((this.getAtTippTorHeim() > this.getAtTippTorGast() && spiel.getAtSpielToreHeim() > spiel.getAtSpielToreGast()) 
//				||
//			this.getAtTippTorHeim() == this.getAtTippTorGast() && spiel.getAtSpielToreHeim() == spiel.getAtSpielToreGast()
//				||
//			this.getAtTippTorHeim() < this.getAtTippTorGast() && spiel.getAtSpielToreHeim() < spiel.getAtSpielToreGast())
//		{
////---> Richtig auf Sieg/Unentschieden/Niederlage getippt
////			this.setAtTippPunktek(this.getAtTippPunktek() + 2);
//
////--->Stimmt die Tordifferenz bis auf ein Tor?
//			int it = this.getAtTippTorHeim() - this.getAtTippTorGast();
//			int is = spiel.getAtSpielToreHeim() - spiel.getAtSpielToreGast();
//			if(it - is < 2 && it - is > -2)
//			{
////---> Differenz stimmt oder weicht nur um ein Tor ab
////				this.setAtTippPunktek(this.getAtTippPunktek() + 1);
//			}
////---> Stimmt die Tordifferenz?			
//			if(this.getAtTippTorHeim() - this.getAtTippTorGast() == spiel.getAtSpielToreHeim() - spiel.getAtSpielToreGast())
//			{
////---> Differenz stimmt - also ein 2:0 Tipp hat bei 3:1 oder 4:2 die richtige Differnz
////				this.setAtTippPunktek(this.getAtTippPunktek() + 1);
////---> Ist das exakte Ergebnis richtig getippt?				
//				if(this.getAtTippTorHeim() + this.getAtTippTorGast() == spiel.getAtSpielToreHeim() + spiel.getAtSpielToreGast())
//				{
////---> Auch die Anzahl der Tore stimmt - Volltreffer!
////					this.setAtTippPunktek(this.getAtTippPunktek() + 1);
//				}
//			}
//		}
////		if(this.getAtTippIsSupertipp())
//		{
////			this.setAtTippPunktek(this.getAtTippPunktek() * 2);
//		}
//	}

}