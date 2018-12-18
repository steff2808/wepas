package tables;

import de.wepas.constants.ApplConstants;
import de.wepas.util.HtmlTags;

public class SupertrefferTablehead1
{
	String cssweiss = ApplConstants.CSSWEISSL;

	String head1;

	public SupertrefferTablehead1()
	{
		super();
		StringBuffer sb = new StringBuffer("");
		sb.append(HtmlTags.wrapTH("Tipper", cssweiss));
		sb.append(HtmlTags.wrapTH("Spieltag", cssweiss));
		sb.append(HtmlTags.wrapTH("Spiel", cssweiss, 2));
		this.head1 = HtmlTags.wrapTR(sb.toString(), null);
	}
	
	public String getHtmlRow()
	{
		return this.head1;
	}
}
