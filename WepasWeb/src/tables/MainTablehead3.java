package tables;

import java.util.Iterator;
import java.util.Vector;

import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnSpiel;
import de.wepas.util.HtmlTags;

public class MainTablehead3
{
	String cssweiss = ApplConstants.CSSWEISSL;
	String csshead = ApplConstants.CSSHEAD;
	
	StringBuffer sb = null;
	
	public MainTablehead3(boolean tunier, Vector<EnSpiel> spiele)
	{
		super();
		sb = new StringBuffer("");
		
		if (tunier)
		{
			sb.append(HtmlTags.wrapTH("Pl", cssweiss));
			sb.append(HtmlTags.wrapTH("Name", cssweiss));
			sb.append(HtmlTags.wrapTH(HtmlTags.createNBSP(1), cssweiss));
			Iterator<EnSpiel> iter1 = spiele.iterator();
			while (iter1.hasNext())
			{
				sb.append(HtmlTags.wrapTH(iter1.next().getResult(), csshead));
			}
//			sb.append(HtmlTags.wrapTH("Pkt", cssweiss));
//			sb.append(HtmlTags.wrapTH("Gew", cssweiss));
//			sb.append(HtmlTags.wrapTH("Pl", cssweiss));
			sb.append(HtmlTags.wrapTH("Pkt", cssweiss));
			sb.append(HtmlTags.wrapTH("Geld", cssweiss));
//			sb.append(HtmlTags.wrapTH("Pott", cssweiss));
		}
		else
		{
			sb.append(HtmlTags.wrapTH("Pl", cssweiss));
			sb.append(HtmlTags.wrapTH("Name", cssweiss));
			sb.append(HtmlTags.wrapTH(HtmlTags.createNBSP(1), cssweiss));
			Iterator<EnSpiel> iter1 = spiele.iterator();
			while (iter1.hasNext())
			{
				sb.append(HtmlTags.wrapTH(iter1.next().getResult(), csshead));
			}
			sb.append(HtmlTags.wrapTH("Pkt", cssweiss));
			sb.append(HtmlTags.wrapTH("Gew", cssweiss));
			sb.append(HtmlTags.wrapTH("Pl", cssweiss));
			sb.append(HtmlTags.wrapTH("Pkt", cssweiss));
			sb.append(HtmlTags.wrapTH("Geld", cssweiss));
			sb.append(HtmlTags.wrapTH("Pott", cssweiss));
		}
	}
	
	public String getHtmlRow()
	{
		return HtmlTags.wrapTR(this.sb.toString() + '\n', csshead);
	}
	
}
