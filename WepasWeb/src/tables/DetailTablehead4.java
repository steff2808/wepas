package tables;

import de.wepas.util.HtmlTags;

public class DetailTablehead4
{
	String head4;
	int nbsp = 20;
	
	public DetailTablehead4()
	{
		super();
		StringBuffer sb = null;
		
		sb = new StringBuffer("");
		for(int i = 0; i < 9; i++)
		{
			sb.append(HtmlTags.wrapTD(HtmlTags.createNBSP(nbsp), null));
		}
		this.head4 = sb.toString();
	}
	
	public String getHtmlRow()
	{
		return this.head4;
	}
}
