package tables;

import de.wepas.constants.ApplConstants;
import de.wepas.util.HtmlTags;



public class HighscoreTablehead2
{
	String cssweiss = ApplConstants.CSSWEISSL;
	String csssubmit1 = ApplConstants.CSSSUBMIT1;

	String head2;

	public HighscoreTablehead2()
	{
		super();
		StringBuffer sb = new StringBuffer("");
		sb.append(HtmlTags.wrapTH(HtmlTags.createSUBMIT("sortname", "Sort", csssubmit1), cssweiss));
		sb.append(HtmlTags.wrapTH(HtmlTags.createSUBMIT("sortspieltag", "Sort", csssubmit1), cssweiss));
		sb.append(HtmlTags.wrapTH(HtmlTags.createSUBMIT("sortpunkte", "Sort", csssubmit1), cssweiss));
		sb.append(HtmlTags.wrapTH(HtmlTags.createSUBMIT("sortgewinn", "Sort", csssubmit1), cssweiss));
		this.head2 = HtmlTags.wrapTR(sb.toString(), null);
	}
	
	public String getHtmlRow()
	{
		return this.head2;
	}
}
