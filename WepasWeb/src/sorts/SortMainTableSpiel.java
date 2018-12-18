package sorts;

import java.util.Comparator;
import java.util.Iterator;

import tables.MainTablerow;
import de.wepas.jpa.EnTipp;

public class SortMainTableSpiel implements Comparator<MainTablerow>
{
//	private static Log log = LogFactory.getLog(NavigationForm.class);
	
	int spielId;
	
	public SortMainTableSpiel(int spielId)
	{
		this.spielId = spielId;
	}
	
	@Override
	public int compare(MainTablerow arg0, MainTablerow arg1)
	{
		if(arg0.getTipperId() == arg0.getTipperIdApril()) return 1;
		if(arg1.getTipperId() == arg1.getTipperIdApril()) return -1;
		
		Iterator<EnTipp> iter0 = arg0.getTipps().iterator();
		EnTipp tipp0 = null;
		while (iter0.hasNext())
		{
			EnTipp tipp = iter0.next();
			if (tipp.getIdSpiel() == this.spielId)
			{
				tipp0 = tipp;
			}
		}
		Iterator<EnTipp> iter1 = arg1.getTipps().iterator();
		EnTipp tipp1 = null;
		while (iter1.hasNext())
		{
			EnTipp tipp = iter1.next();
			if (tipp.getIdSpiel() == this.spielId)
			{
				tipp1 = tipp;
			}
		}
		if (tipp1 == null && tipp0 == null)
		{
			return 0;
		}
		if (tipp1 == null)
		{
			return -1;
		}
		if (tipp0 == null)
		{
			return 1;
		}
		
		if (tipp1.getAtTippPunkte() > 0 || tipp0.getAtTippPunkte() > 0)
		{
			// --> Wenn ein Tipp Punkte hat wird aufgrund Punkte verglichen
			return tipp1.getAtTippPunkte() - tipp0.getAtTippPunkte();
		}
		if (tipp1.getAtTippTorHeim() - tipp1.getAtTippTorGast() > tipp0.getAtTippTorHeim() - tipp0.getAtTippTorGast())
		{
			// Heimsieg wird vor Auswärtssieg sortiert
			return 1;
		}
		if (tipp1.getAtTippTorHeim() - tipp1.getAtTippTorGast() == 0 && tipp0.getAtTippTorHeim() - tipp0.getAtTippTorGast() < 0)
		{
			// Dann Unentschieden vor Auswärtssieg
			return 1;
		}
		if (tipp1.getAtTippTorHeim() - tipp1.getAtTippTorGast() < tipp0.getAtTippTorHeim() - tipp0.getAtTippTorGast())
		{
			// Dann Auswärtssieg
			return -1;
		}
		if ((tipp1.getAtTippTorHeim() - tipp1.getAtTippTorGast()) != (tipp0.getAtTippTorHeim() - tipp0.getAtTippTorGast()))
		{
			// Sonst geht's nach Tordifferenz
			return ((tipp1.getAtTippTorHeim() - tipp1.getAtTippTorGast()) - (tipp0.getAtTippTorHeim() - tipp0.getAtTippTorGast()));
		}
		
		if(tipp1.getAtTippTorHeim() - tipp0.getAtTippTorHeim() == 0)
		{
			// Supertipps zuerst
			if(tipp1.getAtTippIsSupertipp() && tipp0.getAtTippIsSupertipp())
			{
				return 0;
			}
			if(tipp1.getAtTippIsSupertipp())
			{
				return 1;
			}
			if(tipp0.getAtTippIsSupertipp())
			{
				return -1;
			}
		}

		// Am Ende dann die geschossenen Tore
		return tipp1.getAtTippTorHeim() - tipp0.getAtTippTorHeim();
	}
}
