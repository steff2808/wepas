package tables;

import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnSpiel;
import de.wepas.livegoals.Livespiel;
import de.wepas.util.HtmlTags;

public class BestcaseTablehead3
{
	private static Log log = LogFactory.getLog(BestcaseTablehead3.class);
	
	String cssweiss = ApplConstants.CSSWEISSL;
	String csshead = ApplConstants.CSSHEAD;
	
	StringBuffer sb = null;
	
	public BestcaseTablehead3(Vector<Livespiel> spiele)
	{
		super();
		log.debug("Entering " + this.getClass().getName());
		sb = new StringBuffer("");
		sb.append(HtmlTags.wrapTH("Pl", cssweiss));
		sb.append(HtmlTags.wrapTH("Name",  cssweiss));
		Iterator<Livespiel> iter1 = spiele.iterator();
		while(iter1.hasNext())
		{
			sb.append(HtmlTags.wrapTH(iter1.next().getSpiel().getResult(), csshead));
		}
		sb.append(HtmlTags.wrapTH("Pkt", cssweiss));
	}
	
	public String getHtmlRow()
	{
		return HtmlTags.wrapTR(this.sb.toString() + '\n', csshead);
	}
	
}
