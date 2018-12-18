package tables;

import de.wepas.constants.ApplConstants;
import de.wepas.util.HtmlTags;

public class BlindeTablehead1
{
	String cssweiss = ApplConstants.CSSWEISSL;

	String head1;

	public BlindeTablehead1()
	{
		super();
		StringBuffer sb = new StringBuffer("");
		sb.append(HtmlTags.wrapTH("Tipper", cssweiss));
		sb.append(HtmlTags.wrapTH("Spieltag", cssweiss));
		sb.append(HtmlTags.wrapTH("Spiel", cssweiss, 1));
		sb.append(HtmlTags.wrapTH("Ergebnis", cssweiss, 1));
		sb.append(HtmlTags.wrapTH("Tipp", cssweiss, 1));
		sb.append(HtmlTags.wrapTH("Blindgrad", cssweiss, 1));
		this.head1 = HtmlTags.wrapTR(sb.toString(), null);
	}
	
	public String getHtmlRow()
	{
		return this.head1;
	}
}
