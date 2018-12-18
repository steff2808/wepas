package sorts;

import java.util.Comparator;

import de.wepas.jpa.EnTipper;

public class SortTipperName implements Comparator<EnTipper>
{
//	private static Log log = LogFactory.getLog(SortTipperName.class);
	
	@Override
	public int compare(EnTipper arg0, EnTipper arg1)
	{
//		log.info(arg0.getAtTipperName() + "/" + arg1.getAtTipperName());
		return (arg0.getAtTipperName().toLowerCase() + arg0.getAtTipperVorname().toLowerCase())
				.compareTo(arg1.getAtTipperName().toLowerCase() + arg1.getAtTipperVorname().toLowerCase());
	}
}