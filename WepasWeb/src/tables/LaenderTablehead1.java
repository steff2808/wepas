package tables;

import java.util.Iterator;
import java.util.Vector;

import util.StringFormat;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnTipper;
import de.wepas.util.HtmlTags;


public class LaenderTablehead1
{
	String cssweiss = ApplConstants.CSSWEISSL;
	String csshell = ApplConstants.CSSHELL;
	
	String head1;
	
	public LaenderTablehead1(Vector<Vector<EnTipper>> teamvectoren)
	{
		super();
		StringBuffer sb = new StringBuffer("");
		Iterator<Vector<EnTipper>> iter1 = teamvectoren.iterator();
		int t = 0;
		while(iter1.hasNext())
		{
			t++;
			Iterator<EnTipper> iter2 = iter1.next().iterator();
			String img = null;
			int p = 0;
			int i = 0;
			if(iter2.hasNext())
			{
				EnTipper tipper = iter2.next();
				p = tipper.getAtTipperPunkte();
				img = (HtmlTags.wrapIMG50(tipper.getAtTipperTeam(), "team/"));
				i++;
			}
			while(iter2.hasNext())
			{
				EnTipper tipper = iter2.next();
				p = p + tipper.getAtTipperPunkte();
				i++;
			}
			double q = (double) p / (double) i;
		sb.append(HtmlTags.wrapTH(HtmlTags.createNBSP(35) + HtmlTags.createBR(1)
				+ "<h1>" +  t + ". " + img + "</h1>", "csshell"));
		sb.append(HtmlTags.wrapTH(StringFormat.formatMoney(q) 
				+ HtmlTags.createBR(1) + " (absolut: " + p + ")", "cssweiss"));
		}
		this.head1 = HtmlTags.wrapTR(sb.toString(), null);
	}
	
	public String getHtmlRow()
	{
		return this.head1;
	}
}
