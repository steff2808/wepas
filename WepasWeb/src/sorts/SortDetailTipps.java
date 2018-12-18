package sorts;

import java.util.Comparator;

import de.wepas.jpa.EnTipp;

public class SortDetailTipps implements Comparator<EnTipp>
{
	@Override
	public int compare(EnTipp arg0, EnTipp arg1)
	{
		if(arg0.getAtTippIsValid() && arg1.getAtTippIsValid())
		{
			return arg1.getAtTippPunkte() - arg0.getAtTippPunkte();
		}
		if(arg0.getAtTippTorHeim() - arg0.getAtTippTorGast() != arg1.getAtTippTorHeim() - arg1.getAtTippTorGast())
		{
			int diff0 = arg0.getAtTippTorHeim() - arg0.getAtTippTorGast();
			int diff1 = arg1.getAtTippTorHeim() - arg1.getAtTippTorGast();
			return diff1 - diff0;
		}   
		if(arg0.getAtTippTorHeim() - arg0.getAtTippTorGast() == arg1.getAtTippTorHeim() - arg1.getAtTippTorGast())
		{
			return arg1.getAtTippTorHeim() - arg0.getAtTippTorGast();
		}  
		return 0;
	}
}