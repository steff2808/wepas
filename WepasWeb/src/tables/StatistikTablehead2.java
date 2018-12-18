package tables;

import de.wepas.constants.ApplConstants;
import de.wepas.util.HtmlTags;

public class StatistikTablehead2
{
	String cssweiss = ApplConstants.CSSWEISSL;
	String csssubmit1e = ApplConstants.CSSSUBMIT1E;

	String head2;

	boolean[] sortButton = {false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
	String[] sortName = {null, "sortname","sortanzahl", "sortpunkte", "sortboring", "sortkonto", "sortbonus",	"sorttagessiege",
			"sorttendenz", "sorttreffer", "sortexakt", "sortnieten", "sortblind", "sorthunger", "sortknapp", "sortwaldi", "sortnetzerdelling"};
	
	public StatistikTablehead2()
	{
		super();
		this.setSortButton(0);
	}
	
	private void create()
	{
		StringBuffer sb = new StringBuffer("");
		for(int i = 0; i < sortButton.length; i++)
		{
			if(sortName[i] == null)
			{
				sb.append(HtmlTags.wrapTH(HtmlTags.createNBSP(1), cssweiss));
			}
			else
			{
				if(sortButton[i])
				{
					sb.append(HtmlTags.wrapTH(HtmlTags.createSUBMITdisabled(sortName[i], "Sort", csssubmit1e), cssweiss));
				}
				else
				{
					sb.append(HtmlTags.wrapTH(HtmlTags.createSUBMIT(sortName[i], "Sort", csssubmit1e), cssweiss));
				}
			}
		}
		this.head2 = HtmlTags.wrapTR(sb.toString(), null);
	}
	
	public String getHtmlRow()
	{
		return this.head2;
	}
	
	public void setSortButton(int s)
	{
		for(int i = 0; i < sortButton.length; i++)
		{
			sortButton[i] = false;
		}
		sortButton[s] = true;
		create();
	}
}
