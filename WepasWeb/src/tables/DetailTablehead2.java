package tables;

import de.wepas.constants.ApplConstants;
import de.wepas.util.HtmlTags;

public class DetailTablehead2
{
	String cssweissl = ApplConstants.CSSWEISSL; 
	String cssweissm = ApplConstants.CSSWEISSM;
	String cssweissr = ApplConstants.CSSWEISSR;

	String head2;
	
	public DetailTablehead2(int s, int sS, int u, int uS, int n, int nS)
	{
		super();
		StringBuffer sb = new StringBuffer("");
		sb.append(HtmlTags.wrapTD("gewinnt " + HtmlTags.wrapB(s + " ") + "Tipps, davon " + HtmlTags.wrapB(sS + HtmlTags.createSTERN()), cssweissl, 3));
		sb.append(HtmlTags.wrapTD("unentschieden " + HtmlTags.wrapB(u + " ") + "Tipps davon " + HtmlTags.wrapB(uS + HtmlTags.createSTERN()), cssweissm, 3));
		sb.append(HtmlTags.wrapTD("gewinnt " + HtmlTags.wrapB(n + " ") + "Tipps davon " + HtmlTags.wrapB(nS + HtmlTags.createSTERN()), cssweissr, 3));
		this.head2 = HtmlTags.wrapTR(sb.toString(), null);
	}
	
	public String getHtmlRow()
	{
		return this.head2;
	}
}
