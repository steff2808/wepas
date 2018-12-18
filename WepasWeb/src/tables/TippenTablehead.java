package tables;

import de.wepas.constants.ApplConstants;
import de.wepas.util.HtmlTags;

public class TippenTablehead
{
	private String headRow = null;
	private final String CSSWEISSL = ApplConstants.CSSWEISSL;
	
	public TippenTablehead()
	{
		super();
		StringBuffer sb = new StringBuffer("");
		sb.append(HtmlTags.wrapTH("Anpfiff", null));
		sb.append(HtmlTags.wrapTH("Paarung", null));
		sb.append(HtmlTags.wrapTH("Tipp", null));
		sb.append(HtmlTags.wrapTH("Supertipp", null));
		this.headRow = HtmlTags.wrapTR(sb.toString(), CSSWEISSL);
	}
	
	public String getHtmlHeadRow()
	{
		return this.headRow;
	}	
}
