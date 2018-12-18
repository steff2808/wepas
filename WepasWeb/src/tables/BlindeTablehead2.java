package tables;

import de.wepas.constants.ApplConstants;
import de.wepas.util.HtmlTags;

public class BlindeTablehead2
{
	String cssweiss = ApplConstants.CSSWEISSL;
	String csssubmit1 = ApplConstants.CSSSUBMIT1;

	String head2;

	public BlindeTablehead2()
	{
		super();
		StringBuffer sb = new StringBuffer("");
		sb.append(HtmlTags.wrapTH(HtmlTags.createSUBMIT("sortname", "Sort", csssubmit1), cssweiss));
		sb.append(HtmlTags.wrapTH(HtmlTags.createSUBMIT("sortspieltag", "Sort", csssubmit1), cssweiss));
		sb.append(HtmlTags.wrapTH(HtmlTags.createNBSP(1), cssweiss, 2));
		sb.append(HtmlTags.wrapTH(HtmlTags.createNBSP(1), cssweiss));
		sb.append(HtmlTags.wrapTH(HtmlTags.createSUBMIT("sortblindgrad", "Sort", csssubmit1), cssweiss));
		this.head2 = HtmlTags.wrapTR(sb.toString(), null);
	}
	
	public String getHtmlRow()
	{
		return this.head2;
	}
}
