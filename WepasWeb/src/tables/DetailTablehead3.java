package tables;

import de.wepas.util.HtmlTags;

public class DetailTablehead3
{
	String head3;
	String head3a;
	
	public DetailTablehead3(int sD, String cssSd, int sG, String cssSg, int sK, String cssSk, int u, String cssU, int nK, String cssNk, int nG, String cssNg, int nD, String cssNd)
	{
		super();
		StringBuffer sb = null;
		
		sb = new StringBuffer("");
		sb.append(HtmlTags.wrapTD("deutlich", cssSd, 1));
		sb.append(HtmlTags.wrapTD("gut", cssSg, 1));
		sb.append(HtmlTags.wrapTD("knapp", cssSk, 1));
		sb.append(HtmlTags.wrapTD("unentschieden", cssU, 3));
		sb.append(HtmlTags.wrapTD("knapp", cssNk, 1));
		sb.append(HtmlTags.wrapTD("gut", cssNg, 1));
		sb.append(HtmlTags.wrapTD("deutlich", cssNd, 1));
		this.head3 = HtmlTags.wrapTR(sb.toString(), null);
		
		sb = new StringBuffer("");
		sb.append(HtmlTags.wrapTD("" + sD, cssSd));
		sb.append(HtmlTags.wrapTD("" + sG, cssSg));
		sb.append(HtmlTags.wrapTD("" + sK, cssSk));
		sb.append(HtmlTags.wrapTD("" + u, cssU, 3));
		sb.append(HtmlTags.wrapTD("" + nK, cssNk));
		sb.append(HtmlTags.wrapTD("" + nG, cssNg));
		sb.append(HtmlTags.wrapTD("" + nD, cssNd));
		this.head3a = HtmlTags.wrapTR(sb.toString(), null);
	}
	
	public String getHtmlRow()
	{
		return this.head3 + this.head3a;
	}
}
