package tables;

import de.wepas.constants.ApplConstants;
import de.wepas.util.HtmlTags;



public class TreppchenTablehead1
{
	String cssweiss = ApplConstants.CSSWEISSL;

	String head1;
	
	public TreppchenTablehead1()
	{
		super();
		StringBuffer sb = new StringBuffer("");
		sb.append(HtmlTags.wrapTH("Plazierter", cssweiss));
		sb.append(HtmlTags.wrapTH("Spieltag", cssweiss));
		sb.append(HtmlTags.wrapTH("Punkte", cssweiss));
		sb.append(HtmlTags.wrapTH("Gewinn", cssweiss));
		this.head1 = HtmlTags.wrapTR(sb.toString(), null);
	}
	
	public String getHtmlRow()
	{
		return this.head1;
	}
}
