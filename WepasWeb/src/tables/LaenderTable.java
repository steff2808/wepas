package tables;

import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import sorts.SortTipperPunkte;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnTipper;
import de.wepas.util.HtmlTags;

public class LaenderTable
{
	
	String cssweiss = ApplConstants.CSSWEISSL;
	String csseigen = ApplConstants.CSSEIGEN;
	
	Vector<Vector<EnTipper>> teamvectoren;
	LaenderTablehead1 head1;

	public LaenderTable(Vector<Vector<EnTipper>> teamvectoren)
	{
		super();
		this.teamvectoren = teamvectoren;
		this.head1 = new LaenderTablehead1(this.teamvectoren);
	}
	public String getHtmlRow()
	{
		StringBuffer sb = new StringBuffer("");
		
		Vector<Iterator<EnTipper>> vec1 = new Vector<Iterator<EnTipper>>(teamvectoren.size());
		Iterator<Vector<EnTipper>> iter1 = teamvectoren.iterator();
		while(iter1.hasNext())
		{
			Vector<EnTipper> vec0 = iter1.next();
			Collections.sort(vec0, new SortTipperPunkte());
			vec1.add(vec0.iterator());
		}
		boolean eof = false;
		while(! eof)
		{
			eof = true;
			Iterator<Iterator<EnTipper>> iter2 =  vec1.iterator();
			StringBuffer sr = new StringBuffer("");
			while(iter2.hasNext())
			{
				Iterator<EnTipper> iter3 = iter2.next();
				if(iter3.hasNext())
				{
					eof = false;
					EnTipper tipper = iter3.next();
					sr.append(HtmlTags.wrapTD(tipper.getAtTipperVorname() + HtmlTags.createNBSP(1) + tipper.getAtTipperName(), csseigen));
					sr.append(HtmlTags.wrapTD(Integer.toString(tipper.getAtTipperPunkte()), cssweiss));
				}
				else
				{
					sr.append(HtmlTags.wrapTD(HtmlTags.createNBSP(1), null, 2));
				}
			}
			if(! eof)
			{
				sb.append(HtmlTags.wrapTR(sr.toString(), null));
			}
		}
		return head1.getHtmlRow() + sb.toString();
	}
}
