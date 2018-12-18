package tables;

import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnVerein;
import de.wepas.util.HtmlTags;

public class DetailTablehead1
{
	String cssjustl = ApplConstants.CSSJUSTL;
	String cssjustm = ApplConstants.CSSJUSTM;
	String cssjustr = ApplConstants.CSSJUSTR;
	
	String head1;

	public DetailTablehead1(EnSpiel spiel, EnVerein heim, EnVerein gast)
	{
		super();
		StringBuffer sb = new StringBuffer("");
		
		sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG50(heim.getAtVereinKurz(), "vereine/gross/"), cssjustr, 1));
		sb.append(HtmlTags.wrapTD(HtmlTags.wrapB(heim.getAtVereinName()), cssjustl, 2));
		sb.append(HtmlTags.wrapTD(HtmlTags.createMINUS(), cssjustm, 3));
		sb.append(HtmlTags.wrapTD(HtmlTags.wrapB(gast.getAtVereinName()), cssjustr, 2));
		sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG50(gast.getAtVereinKurz(), "vereine/gross/"), cssjustl, 1));
		this.head1 = HtmlTags.wrapTR(sb.toString(), null);
	}
	
	public String getHtmlRow()
	{
		return this.head1;
	}
}
