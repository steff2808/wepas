package de.wepas.worker;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.wepas.connector.SpielBeanLocal;
import de.wepas.connector.SpielException;
import de.wepas.connector.SpieltagBeanLocal;
import de.wepas.connector.SpieltagException;
import de.wepas.connector.TippBeanLocal;
import de.wepas.connector.TippException;
import de.wepas.connector.TipperBeanLocal;
import de.wepas.connector.TipperException;
import de.wepas.connector.VereinBeanLocal;
import de.wepas.connector.VereinException;
import de.wepas.connector.WetteBeanLocal;
import de.wepas.connector.WetteException;
import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnSpieltag;
import de.wepas.jpa.EnTipp;
import de.wepas.jpa.EnTipper;
import de.wepas.jpa.EnVerein;
import de.wepas.jpa.EnWette;
import de.wepas.sort.SortWettePunkte;
import de.wepas.util.LookupLocalService;

@Stateful
@LocalBean
public class ErgebnispflegeBean implements ErgebnispflegeBeanRemote, ErgebnispflegeBeanLocal
{
	private static Log log = LogFactory.getLog(ErgebnispflegeBean.class);
	private TipperBeanLocal tipperStateless;
	private SpieltagBeanLocal spieltagStateless;
	private SpielBeanLocal spielStateless;
	private TippBeanLocal tippStateless;
	private VereinBeanLocal vereinStateless;
	private WetteBeanLocal wetteStateless;
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String maintenanceResult(EnSpiel spiel) throws ErgebnispflegeException
	{
		log.debug("Entering " + this.getClass().getName());
		String rc = "Pflege Spiel mit id " + spiel.getIdSpiel() + " mit " + spiel.getAtSpielToreHeim() + ":" 
				+ spiel.getAtSpielToreGast() + " RC:OK";
		
// --> 1. Die benötigten Interfaces holen		
		tipperStateless = LookupLocalService.lookupTipperBeanLocal();
		tippStateless = LookupLocalService.lookupTippBeanLocal();
		spieltagStateless = LookupLocalService.lookupSpieltagBeanLocal();
		vereinStateless = LookupLocalService.lookupVereinBeanLocal();
		wetteStateless = LookupLocalService.lookupWetteBeanLocal();
		spielStateless = LookupLocalService.lookupSpielBeanLocal();
		
// --> 2. Den Spieltag holen
		EnSpieltag spieltag = null;
		try
		{
			spieltag = spieltagStateless.getSpieltag(spiel.getFkSpielSpieltag());
		}
		catch (SpieltagException e)
		{
			e.printStackTrace();
			log.error("SpieltagException Zeile 74 " + e.getMessage());
		}
		boolean startnow = true;
		boolean justfinished = false;
		
// --> 3. Alle zu diesem Spieltag gehörenden Spiele lesen
		Vector<EnSpiel> spiele = null;
		try
		{
			EnSpiel[] spielarray = spielStateless.getSpiele(spieltag);
			spiele = new Vector<EnSpiel>(spielarray.length);
			for (int i = 0; i < spielarray.length; i++)
			{
				spiele.add(spielarray[i]);
			}
		}
		catch (SpielException e)
		{
			e.printStackTrace();
			log.error("SpielException Zeile 93 " + e.getMessage());
			return e.getMessage();
		}
		int i = 0;
		Iterator<EnSpiel> iter1 = spiele.iterator();
		while (iter1.hasNext())
		{
			EnSpiel pruefSpiel = iter1.next();
			if (pruefSpiel.getAtSpielIsPlayed())
			{
				i++;
			}
			if (pruefSpiel.getAtSpielIsPlayed())
			{
				// --> Ist ein Spiel bereits mit Ergebnis ist der Spieltag
				// bereits gestartet.
				startnow = false;
			}
			if (spieltag.getAtSpieltagPaarungen() - i == 1)
			{
				// --> Sind z.B. 8 von 9 Spielen des Spieltages zuende kommt
				// jetzt das letzte Spiel
				justfinished = true;
			}
		}
		
// -->4. Wenn der Spieltag gerade gestartet ist die Entitäten EnWette
// anlegen, Spieltageinsatz ermitteln und jedem Tipper einen Euro
// abknöpfen
		if (startnow)
		{
			Vector<EnTipper> zahlendeTipper = new Vector<EnTipper>(30);
			Vector<EnWette> neueWetten = new Vector<EnWette>(30);
			Vector<EnTipper> tippers = new Vector<EnTipper>(30);
			try
			{
				EnTipper[] tipperarray = tipperStateless.getTipper();
				for (int n = 0; n < tipperarray.length; n++)
				{
					tippers.add(tipperarray[n]);
				}
			}
			catch (TipperException e)
			{
				e.printStackTrace();
				log.error("TipperException Zeile 138 " + e.getMessage());
				return e.getMessage();
			}
			Iterator<EnTipper> iter2 = tippers.iterator();
			while (iter2.hasNext())
			{
				EnTipper tipper = iter2.next();
				if (spieltag.getAtSpieltagIsDuty())
				{
					EnWette wette = new EnWette();
					wette.setIdTipper(tipper.getIdTipper());
					wette.setIdSpieltag(spieltag.getIdSpieltag());
					spieltag.setAtSpieltagEinsatz(spieltag.getAtSpieltagEinsatz().add(spieltag.getAtSpieltagKosten()));
					tipper.setAtTipperKonto(tipper.getAtTipperKonto().subtract(spieltag.getAtSpieltagKosten()));
					zahlendeTipper.add(tipper);
//					log.info("-->vor neueWetten.add " + wette.toString());
					neueWetten.add(wette);
				}
				else
				{
					throw new RuntimeException("NYI Pflichtfreie Spieltage");
				}
			}
			try
			{
				EnWette[] wetten = new EnWette[neueWetten.size()];
				int w = 0;
				Iterator<EnWette> iter3 = neueWetten.iterator();
				while (iter3.hasNext())
				{
					EnWette wette = iter3.next();
					wetten[w] = wette;
					w++;
				}
				wetteStateless.addWette(wetten);
			}
			catch (WetteException e)
			{
				e.printStackTrace();
				log.error("WetteException Zeile 177" + e.getMessage());
				return e.getMessage();
			}
			try
			{
				spieltagStateless.changeSpieltag(spieltag);
			}
			catch (SpieltagException e)
			{
				e.printStackTrace();
				log.error("SpieltagException Zeile 187 " + e.getMessage());
				return e.getMessage();
			}
			try
			{
				Iterator<EnTipper> iter4 = zahlendeTipper.iterator();
				while (iter4.hasNext())
				{
					tipperStateless.changeTipper(iter4.next());
				}
			}
			catch (TipperException e)
			{
				e.printStackTrace();
				log.error("TipperException Zeile 201 " + e.getMessage());
				return e.getMessage();
			}
		}
		
// -->5. Bei einer Veränderung des Spieltages (neu gestartet, oder
// vollständig erledigt) diesen in der DB speichern.
		
		if (startnow)
		{
			spieltag.setAtSpieltagIsStarted(startnow);
		}
		if (justfinished)
		{
			spieltag.setAtSpieltagIsFinished(justfinished);
		}
		if (startnow || justfinished)
		{
			try
			{
				spieltagStateless.changeSpieltag(spieltag);
			}
			catch (SpieltagException e)
			{
				e.printStackTrace();
				log.error("SpieltagException Zeile 226 " + e.getMessage());
				return e.getMessage();
			}
		}
		
// -->6. Das Spiel mit dem Ergebnis auf isPlayed setzen
		spiel.setAtSpielIsPlayed(true);
		try
		{
			spielStateless.changeSpiel(spiel);
		}
		catch (SpielException e)
		{
			e.printStackTrace();
			log.error("SpielException Zeile 240 " + e.getMessage());
			return e.getMessage();
		}
		
// -->7. Die Bilanz der Vereine updaten
		EnVerein heim = null;
		EnVerein gast = null;
		try
		{
			heim = vereinStateless.getVerein(spiel.getFkSpielVereinHeim());
		}
		catch (VereinException e)
		{
			e.printStackTrace();
			return e.getMessage();
		}
		try
		{
			gast = vereinStateless.getVerein(spiel.getFkSpielVereinGast());
		}
		catch (VereinException e)
		{
			e.printStackTrace();
			return e.getMessage();
		}
		char[] formheim = heim.getAtVereinForm().toCharArray();
		char[] formgast = gast.getAtVereinForm().toCharArray();
		int position = spiel.getFkSpielSpieltag() * 2;
		position--;
		position--;
		System.out.println("-->Position = " + position);
		System.out.print("Heim: '");
		for(int q = 0; q < formheim.length; q++)
		{
			System.out.print(formheim[q]);
		}
		System.out.println("'");
		System.out.print("Gast: '");
		for(int q = 0; q < formgast.length; q++)
		{
			System.out.print(formgast[q]);
		}
		System.out.println("'");
		heim.setAtVereinSp(heim.getAtVereinSp() + 1);
		gast.setAtVereinSp(gast.getAtVereinSp() + 1);
		heim.setAtVereinHSp(heim.getAtVereinHSp() + 1);
		gast.setAtVereinASp(gast.getAtVereinASp() + 1);
		if (spieltag.getAtSpieltagNummer() < 18)
		{
			heim.setAtVereinHRSp(heim.getAtVereinHRSp() + 1);
			gast.setAtVereinHRSp(gast.getAtVereinHRSp() + 1);
		}
		else
		{
			heim.setAtVereinRRSp(heim.getAtVereinRRSp() + 1);
			gast.setAtVereinRRSp(gast.getAtVereinRRSp() + 1);
			
		}
		if (spiel.getAtSpielToreHeim() > spiel.getAtSpielToreGast())
		{
			heim.setAtVereinSi(heim.getAtVereinSi() + 1);
			gast.setAtVereinNi(gast.getAtVereinNi() + 1);
			heim.setAtVereinTg(heim.getAtVereinTg() + spiel.getAtSpielToreHeim());
			heim.setAtVereinTk(heim.getAtVereinTk() + spiel.getAtSpielToreGast());
			gast.setAtVereinTg(gast.getAtVereinTg() + spiel.getAtSpielToreGast());
			gast.setAtVereinTk(gast.getAtVereinTk() + spiel.getAtSpielToreHeim());
			heim.setAtVereinPunkte(heim.getAtVereinPunkte() + 3);
//			formheim[position] = ' ';
//			formheim[position + 1] = 's';
//			formgast[position] = ' ';
//			formgast[position + 1] = 'N';
			
			heim.setAtVereinHSi(heim.getAtVereinHSi() + 1);
			gast.setAtVereinANi(gast.getAtVereinANi() + 1);
			heim.setAtVereinHTg(heim.getAtVereinHTg() + spiel.getAtSpielToreHeim());
			heim.setAtVereinHTk(heim.getAtVereinHTk() + spiel.getAtSpielToreGast());
			gast.setAtVereinATg(gast.getAtVereinATg() + spiel.getAtSpielToreGast());
			gast.setAtVereinATk(gast.getAtVereinATk() + spiel.getAtSpielToreHeim());
			heim.setAtVereinHPunkte(heim.getAtVereinHPunkte() + 3);
			
			if (spieltag.getAtSpieltagNummer() < 18)
			{
				heim.setAtVereinHRSp(heim.getAtVereinHRSp() + 1);
				gast.setAtVereinHRSp(gast.getAtVereinHRSp() + 1);
				heim.setAtVereinHRSi(heim.getAtVereinHRSi() + 1);
				gast.setAtVereinHRNi(gast.getAtVereinHRNi() + 1);
				heim.setAtVereinHRTg(heim.getAtVereinHRTg() + spiel.getAtSpielToreHeim());
				heim.setAtVereinHRTk(heim.getAtVereinHRTk() + spiel.getAtSpielToreGast());
				gast.setAtVereinHRTg(gast.getAtVereinHRTg() + spiel.getAtSpielToreGast());
				gast.setAtVereinHRTk(gast.getAtVereinHRTk() + spiel.getAtSpielToreHeim());
				heim.setAtVereinHRPunkte(heim.getAtVereinHRPunkte() + 3);
				
			}
			else
			{
				heim.setAtVereinRRSp(heim.getAtVereinRRSp() + 1);
				gast.setAtVereinRRSp(gast.getAtVereinRRSp() + 1);
				heim.setAtVereinRRSi(heim.getAtVereinRRSi() + 1);
				gast.setAtVereinRRNi(gast.getAtVereinRRNi() + 1);
				heim.setAtVereinRRTg(heim.getAtVereinRRTg() + spiel.getAtSpielToreHeim());
				heim.setAtVereinRRTk(heim.getAtVereinRRTk() + spiel.getAtSpielToreGast());
				gast.setAtVereinRRTg(gast.getAtVereinRRTg() + spiel.getAtSpielToreGast());
				gast.setAtVereinRRTk(gast.getAtVereinRRTk() + spiel.getAtSpielToreHeim());
				heim.setAtVereinRRPunkte(heim.getAtVereinRRPunkte() + 3);
			}
		}
		if (spiel.getAtSpielToreHeim() == spiel.getAtSpielToreGast())
		{
			heim.setAtVereinUn(heim.getAtVereinUn() + 1);
			gast.setAtVereinUn(gast.getAtVereinUn() + 1);
			heim.setAtVereinTg(heim.getAtVereinTg() + spiel.getAtSpielToreHeim());
			heim.setAtVereinTk(heim.getAtVereinTk() + spiel.getAtSpielToreGast());
			gast.setAtVereinTg(gast.getAtVereinTg() + spiel.getAtSpielToreGast());
			gast.setAtVereinTk(gast.getAtVereinTk() + spiel.getAtSpielToreHeim());
			heim.setAtVereinPunkte(heim.getAtVereinPunkte() + 1);
			gast.setAtVereinPunkte(gast.getAtVereinPunkte() + 1);
			
//			formheim[position] = ' ';
//			formheim[position + 1] = 'u';
//			formgast[position] = ' ';
//			formgast[position + 1] = 'U';
			
			heim.setAtVereinHUn(heim.getAtVereinHUn() + 1);
			gast.setAtVereinAUn(gast.getAtVereinAUn() + 1);
			heim.setAtVereinHTg(heim.getAtVereinHTg() + spiel.getAtSpielToreHeim());
			heim.setAtVereinHTk(heim.getAtVereinHTk() + spiel.getAtSpielToreGast());
			gast.setAtVereinATg(gast.getAtVereinATg() + spiel.getAtSpielToreGast());
			gast.setAtVereinATk(gast.getAtVereinATk() + spiel.getAtSpielToreHeim());
			heim.setAtVereinHPunkte(heim.getAtVereinHPunkte() + 1);
			gast.setAtVereinAPunkte(gast.getAtVereinAPunkte() + 1);
			if (spieltag.getAtSpieltagNummer() < 18)
			{
				heim.setAtVereinHRSp(heim.getAtVereinHRSp() + 1);
				gast.setAtVereinHRSp(gast.getAtVereinHRSp() + 1);
				heim.setAtVereinHRUn(heim.getAtVereinHRUn() + 1);
				gast.setAtVereinHRUn(gast.getAtVereinHRUn() + 1);
				heim.setAtVereinHRTg(heim.getAtVereinHRTg() + spiel.getAtSpielToreHeim());
				heim.setAtVereinHRTk(heim.getAtVereinHRTk() + spiel.getAtSpielToreGast());
				gast.setAtVereinHRTg(gast.getAtVereinHRTg() + spiel.getAtSpielToreGast());
				gast.setAtVereinHRTk(gast.getAtVereinHRTk() + spiel.getAtSpielToreHeim());
				heim.setAtVereinHRPunkte(heim.getAtVereinHRPunkte() + 1);
				gast.setAtVereinHRPunkte(gast.getAtVereinHRPunkte() + 1);
			}
			else
			{
				heim.setAtVereinRRSp(heim.getAtVereinRRSp() + 1);
				gast.setAtVereinRRSp(gast.getAtVereinRRSp() + 1);
				heim.setAtVereinRRUn(heim.getAtVereinRRUn() + 1);
				gast.setAtVereinRRUn(gast.getAtVereinRRUn() + 1);
				heim.setAtVereinRRTg(heim.getAtVereinRRTg() + spiel.getAtSpielToreHeim());
				heim.setAtVereinRRTk(heim.getAtVereinRRTk() + spiel.getAtSpielToreGast());
				gast.setAtVereinRRTg(gast.getAtVereinRRTg() + spiel.getAtSpielToreGast());
				gast.setAtVereinRRTk(gast.getAtVereinRRTk() + spiel.getAtSpielToreHeim());
				heim.setAtVereinRRPunkte(heim.getAtVereinRRPunkte() + 1);
				gast.setAtVereinRRPunkte(gast.getAtVereinRRPunkte() + 1);
			}
		}
		if (spiel.getAtSpielToreHeim() < spiel.getAtSpielToreGast())
		{
			heim.setAtVereinNi(heim.getAtVereinNi() + 1);
			gast.setAtVereinSi(gast.getAtVereinSi() + 1);
			heim.setAtVereinTg(heim.getAtVereinTg() + spiel.getAtSpielToreHeim());
			heim.setAtVereinTk(heim.getAtVereinTk() + spiel.getAtSpielToreGast());
			gast.setAtVereinTg(gast.getAtVereinTg() + spiel.getAtSpielToreGast());
			gast.setAtVereinTk(gast.getAtVereinTk() + spiel.getAtSpielToreHeim());
			
			gast.setAtVereinPunkte(gast.getAtVereinPunkte() + 3);
//			formheim[position] = ' ';
//			formheim[position + 1] = 'n';
//			formgast[position] = ' ';
//			formgast[position + 1] = 'S';
			
			heim.setAtVereinHNi(heim.getAtVereinHNi() + 1);
			gast.setAtVereinASi(gast.getAtVereinASi() + 1);
			heim.setAtVereinHTg(heim.getAtVereinHTg() + spiel.getAtSpielToreHeim());
			heim.setAtVereinHTk(heim.getAtVereinHTk() + spiel.getAtSpielToreGast());
			gast.setAtVereinATg(gast.getAtVereinATg() + spiel.getAtSpielToreGast());
			gast.setAtVereinATk(gast.getAtVereinATk() + spiel.getAtSpielToreHeim());
			gast.setAtVereinAPunkte(gast.getAtVereinAPunkte() + 3);
			
			if (spieltag.getAtSpieltagNummer() < 18)
			{
				heim.setAtVereinHRSp(heim.getAtVereinHRSp() + 1);
				gast.setAtVereinHRSp(gast.getAtVereinHRSp() + 1);
				heim.setAtVereinHRNi(heim.getAtVereinHRNi() + 1);
				gast.setAtVereinHRSi(gast.getAtVereinHRSi() + 1);
				heim.setAtVereinHRTg(heim.getAtVereinHRTg() + spiel.getAtSpielToreHeim());
				heim.setAtVereinHRTk(heim.getAtVereinHRTk() + spiel.getAtSpielToreGast());
				gast.setAtVereinHRTg(gast.getAtVereinHRTg() + spiel.getAtSpielToreGast());
				gast.setAtVereinHRTk(gast.getAtVereinHRTk() + spiel.getAtSpielToreHeim());
				gast.setAtVereinHRPunkte(gast.getAtVereinHRPunkte() + 3);
			}
			else
			{
				heim.setAtVereinRRSp(heim.getAtVereinRRSp() + 1);
				gast.setAtVereinRRSp(gast.getAtVereinRRSp() + 1);
				heim.setAtVereinRRNi(heim.getAtVereinRRNi() + 1);
				gast.setAtVereinRRSi(gast.getAtVereinRRSi() + 1);
				heim.setAtVereinRRTg(heim.getAtVereinRRTg() + spiel.getAtSpielToreHeim());
				heim.setAtVereinRRTk(heim.getAtVereinRRTk() + spiel.getAtSpielToreGast());
				gast.setAtVereinRRTg(gast.getAtVereinRRTg() + spiel.getAtSpielToreGast());
				gast.setAtVereinRRTk(gast.getAtVereinRRTk() + spiel.getAtSpielToreHeim());
				gast.setAtVereinRRPunkte(gast.getAtVereinRRPunkte() + 3);
			}
		}
		heim.setAtVereinForm(String.valueOf(formheim));
		gast.setAtVereinForm(String.valueOf(formgast));
		try
		{
			vereinStateless.changeVerein(heim);
		}
		catch (VereinException e)
		{
			e.printStackTrace();
			return e.getMessage();
		}
		try
		{
			vereinStateless.changeVerein(gast);
		}
		catch (VereinException e)
		{
			e.printStackTrace();
			return e.getMessage();
		}
		
// -->8. Alle Tipps des Spiels updaten
		Vector<EnTipp> zuaenderndeTipps = null;
		Vector<EnWette> zuaenderndeWetten = new Vector<EnWette>();
		Vector<EnTipper> zuaenderndeTipper = new Vector<EnTipper>();
		try
		{
			
			EnTipp[] tipparray = tippStateless.getTipps(spiel);
			zuaenderndeTipps = new Vector<EnTipp>(tipparray.length);
			for (int n = 0; n < tipparray.length; n++)
			{
				zuaenderndeTipps.add(tipparray[n]);
			}
		}
		catch (TippException e)
		{
			e.printStackTrace();
			log.error("TippException Zeile 474" + e.getMessage());
			return e.getMessage();
		}
		Iterator<EnTipp> iter4 = zuaenderndeTipps.iterator();
		while (iter4.hasNext())
		{
			EnTipp tipp = iter4.next();
			tipp.analyse(spiel);
			EnTipper tipper = null;
			try
			{
				tipper = tipperStateless.getTipper(tipp.getIdTipper());
			}
			catch (TipperException e)
			{
				e.printStackTrace();
				log.error("TipperException Zeile 480 " + e.getMessage());
				return e.getMessage();
			}
			EnWette wette = null;
			try
			{
				wette = wetteStateless.getWette(tipper, spieltag);
			}
			catch (WetteException e)
			{
				e.printStackTrace();
				log.error("WetteException Zeile 491 " + e.getMessage());
				return e.getMessage();
			}
			wette.setAtWettePunkte(wette.getAtWettePunkte() + tipp.getAtTippPunkte());
			if (spieltag.getAtSpieltagIsDuty())
			{
				tipper.setAtTipperPunkte(tipper.getAtTipperPunkte() + tipp.getAtTippPunkte());
				// tipper.setAtTipperPunktem(tipper.getAtTipperPunktem() +
				// tipp.getAtTippPunktem());
				// tipper.setAtTipperPunktek(tipper.getAtTipperPunktek() +
				// tipp.getAtTippPunktek());
				// wette.setAtWettePunktem(wette.getAtWettePunktem() +
				// tipp.getAtTippPunktem());
				// wette.setAtWettePunktek(wette.getAtWettePunktek() +
				// tipp.getAtTippPunktek());
			}
			zuaenderndeWetten.add(wette);
			zuaenderndeTipper.add(tipper);
		}
		
// -->9. Hier jetzt alle geänderten Wetten, Tipper und Tipps
// zurückschreiben
		Iterator<EnTipp> iter5 = zuaenderndeTipps.iterator();
		while (iter5.hasNext())
		{
			try
			{
				tippStateless.changeTipp(iter5.next());
			}
			catch (TippException e)
			{
				e.printStackTrace();
				log.error("TippException Zeile 523 " + e.getMessage());
				return e.getMessage();
			}
		}
		Iterator<EnTipper> iter6 = zuaenderndeTipper.iterator();
		while (iter6.hasNext())
		{
			try
			{
				tipperStateless.changeTipper(iter6.next());
			}
			catch (TipperException e)
			{
				e.printStackTrace();
				log.error("TipperException Zeile 537 " + e.getMessage());
				return e.getMessage();
			}
		}
		Iterator<EnWette> iter7 = zuaenderndeWetten.iterator();
		while (iter7.hasNext())
		{
			try
			{
				wetteStateless.changeWette(iter7.next());
			}
			catch (WetteException e)
			{
				e.printStackTrace();
				log.error("WetteException Zeile 551 " + e.getMessage());
				return e.getMessage();
			}
		}
		
// -->10. Letztes Spiel erfaßt - alle Wetten des spieltages lesen und in
// einen Vector stellen.
		if (justfinished)
		{
			Vector<EnWette> zw = new Vector<EnWette>(30);
			Iterator<EnWette> iter8 = null;
			try
			{
				EnWette[] wettearray = wetteStateless.getWetten(spieltag);
				Vector<EnWette> wetten = new Vector<EnWette>(wettearray.length);
				for (int n = 0; n < wettearray.length; n++)
				{
					wetten.add(wettearray[n]);
				}
				iter8 = wetten.iterator();
			}
			catch (WetteException e)
			{
				e.printStackTrace();
				log.error("WetteException Zeile 575 " + e.getMessage());
				return e.getMessage();
			}
			while (iter8.hasNext())
			{
				zw.add(iter8.next());
			}

			Collections.sort(zw, new SortWettePunkte());
			int max1 = -1;
			int max2 = -1;
			int max3 = -1;
			Iterator<EnWette> iter9 = zw.iterator();

// -->11. Wenn es Geld gibt kommen die glücklichen Gewinner in
// diesen Vector
			Vector<EnWette> spieltagerste = new Vector<EnWette>();
			Vector<EnWette> spieltagzweite = new Vector<EnWette>();
			Vector<EnWette> spieltagdritte = new Vector<EnWette>();
			
			while (iter9.hasNext())
			{
				EnWette wette = iter9.next();
				if (max1 == -1)
				{
					// der allererste
					max1 = wette.getAtWettePunkte();
					wette.setAtWetteIsSieger(true);
					spieltagerste.add(wette);
					
				}
				else
				{
					if (max1 == wette.getAtWettePunkte())
					{
						// der zweite erste
						wette.setAtWetteIsSieger(true);
						spieltagerste.add(wette);
						if (max2 == -1)
						{
							max2 = -2;
						}
						else if (max2 == -2)
						{
							max3 = -2;
						}
					}
					else if (max2 == wette.getAtWettePunkte())
					{
						spieltagzweite.add(wette);
					}
					else if (max2 == -1 && spieltagerste.size() == 1)
					{
						// der zweite
						max2 = wette.getAtWettePunkte();
						spieltagzweite.add(wette);
					}
					else if (max3 == wette.getAtWettePunkte())
					{
						spieltagdritte.add(wette);
					}
					else if (max3 == -1 && spieltagerste.size() + spieltagzweite.size() == 2)
					{
						// der dritte
						max3 = wette.getAtWettePunkte();
						spieltagdritte.add(wette);
					}
				}
			}
			
// --> 11. Gewinnermittlung für das Treppchen
			double gewinn1 = 0.0;
			double gewinn2 = 0.0;
			// double gewinn3 = 0.0;
			Vector<EnTipper> gewinner = new Vector<EnTipper>();
			
			if (spieltagerste.size() == 1 && spieltagzweite.size() == 1)
			{
				gewinn1 = spieltag.getAtSpieltagEinsatz().doubleValue() * 0.7;
				gewinn2 = spieltag.getAtSpieltagEinsatz().doubleValue() * 0.3;
				log.info("-->Spieltagabschluss mit einem 1. und einem 2.");
			}
			if (spieltagerste.size() == 1 && spieltagzweite.size() > 1)
			{
				gewinn1 = spieltag.getAtSpieltagEinsatz().doubleValue() * 0.7;
				gewinn2 = spieltag.getAtSpieltagEinsatz().doubleValue() * 0.3 / spieltagzweite.size();
				log.info("-->Spieltagabschluss mit einem 1. und meheren  2.");
			}
			if (spieltagerste.size() > 1)
			{
				gewinn1 = spieltag.getAtSpieltagEinsatz().doubleValue() / spieltagerste.size();
				gewinn2 = 0.0;
			}
			
			// --> Nachfolgend isses bei drei Gewinnern des Spieltages
			// if (spieltagerste.size() == 1 && spieltagzweite.size() == 1)
			// {
			// gewinn1 = spieltag.getAtSpieltagEinsatz().doubleValue() * 0.6;
			// gewinn2 = spieltag.getAtSpieltagEinsatz().doubleValue() * 0.3;
			// gewinn3 = spieltag.getAtSpieltagEinsatz().doubleValue() * 0.1 /
			// spieltagdritte.size();
			// }
			// if (spieltagerste.size() == 1 && spieltagzweite.size() > 1)
			// {
			// gewinn1 = spieltag.getAtSpieltagEinsatz().doubleValue() * 0.6;
			// gewinn2 = spieltag.getAtSpieltagEinsatz().doubleValue() * 0.4 /
			// spieltagzweite.size();
			// gewinn3 = 0.0;
			// }
			// if (spieltagerste.size() == 2)
			// {
			// gewinn1 = spieltag.getAtSpieltagEinsatz().doubleValue() * 0.9 /
			// spieltagerste.size();
			// gewinn2 = 0.0;
			// gewinn3 = spieltag.getAtSpieltagEinsatz().doubleValue() * 0.1 /
			// spieltagdritte.size();
			// }
			// if (spieltagerste.size() > 2)
			// {
			// gewinn1 = spieltag.getAtSpieltagEinsatz().doubleValue() * 1.0 /
			// spieltagerste.size();
			// gewinn2 = 0.0;
			// gewinn3 = 0.0;
			// }
			
// --> 12. Gewinnverbuchung des/der Sieger
			Iterator<EnWette> iter10 = spieltagerste.iterator();
			while (iter10.hasNext())
			{
				EnWette wette = iter10.next();
				wette.setAtWetteGewinn(new BigDecimal(gewinn1));
				wette.setAtWetteIsGewonnen(true);
				EnTipper tipper = null;
				try
				{
					tipper = tipperStateless.getTipper(wette.getIdTipper());
				}
				catch (TipperException e)
				{
					e.printStackTrace();
					log.error("TipperException Zeile 715 " + e.getMessage());
					return e.getMessage();
				}
				tipper.setAtTipperKonto(tipper.getAtTipperKonto().add(wette.getAtWetteGewinn()));
				tipper.setAtTipperSiege(tipper.getAtTipperSiege() + 1);
				gewinner.add(tipper);
				try
				{
					wetteStateless.changeWette(wette);
				}
				catch (WetteException e)
				{
					e.printStackTrace();
					log.error("WetteException Zeile 747 " + e.getMessage());
					return e.getMessage();
				}
			}
			
// --> 13. Gewinnverbuchung des/der Zweiten
			Iterator<EnWette> iter11 = spieltagzweite.iterator();
			while (iter11.hasNext())
			{
				EnWette wette = iter11.next();
				wette.setAtWetteGewinn(new BigDecimal(gewinn2));
				wette.setAtWetteIsGewonnen(true);
				EnTipper tipper = null;
				try
				{
					tipper = tipperStateless.getTipper(wette.getIdTipper());
				}
				catch (TipperException e)
				{
					e.printStackTrace();
					log.error("TipperException Zeile 749 " + e.getMessage());
					return e.getMessage();
				}
				tipper.setAtTipperKonto(tipper.getAtTipperKonto().add(wette.getAtWetteGewinn()));
				gewinner.add(tipper);
				try
				{
					wetteStateless.changeWette(wette);
				}
				catch (WetteException e)
				{
					e.printStackTrace();
					log.error("WetteException Zeile 762 " + e.getMessage());
					return e.getMessage();
				}
			}
			
// --> 14. Gewinnverbuchung des/der Dritten
			// Iterator<EnWette> iter12 = spieltagdritte.iterator();
			// while (iter12.hasNext())
			// {
			// EnWette wette = iter12.next();
			// wette.setAtWetteGewinn(new BigDecimal(gewinn3));
			// wette.setAtWetteIsGewonnen(true);
			// EnTipper tipper = null;
			// try
			// {
			// tipper = tpbr.getTipper(wette.getIdTipper());
			// }
			// catch (TipperException e)
			// {
			// System.out.println("--> Exception " + e.getMessage() +
			// " in ErgebnisForm Zeile 595 " + this.getClass().getName());
			// e.printStackTrace();
			// }
			// tipper.setAtTipperKonto(tipper.getAtTipperKonto().add(wette.getAtWetteGewinn()));
			// tipper.setAtTipperFreiesiege(tipper.getAtTipperFreiesiege() + 1);
			// gewinner.add(tipper);
			// try
			// {
			// wbr.changeWette(wette);
			// }
			// catch (WetteException e)
			// {
			// System.out.println("--> Exception " + e.getMessage() +
			// " in ErgebnisForm Zeile 607 " + this.getClass().getName());
			// e.printStackTrace();
			// }
			// }
			
// --> 15. Tipper persistieren
			Iterator<EnTipper> iter13 = gewinner.iterator();
			while (iter13.hasNext())
			{
				try
				{
					tipperStateless.changeTipper(iter13.next());
				}
				catch (TipperException e)
				{
					e.printStackTrace();
					log.error("TipperException Zeile 811 " + e.getMessage());
					return e.getMessage();
				}
			}
		}
	return rc;	
	}
	
}
