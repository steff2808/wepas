package tables;

import java.util.Iterator;
import java.util.Vector;

import util.LookupRemoteService;
import de.wepas.connector.VereinBeanRemote;
import de.wepas.connector.VereinException;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnSpieltag;
import de.wepas.jpa.EnVerein;
import de.wepas.livegoals.LivegoalsObject;
import de.wepas.livegoals.Livespiel;
import de.wepas.util.HtmlTags;

public class BestcaseTablehead2
{
	String csshell = ApplConstants.CSSHELL;
	String csshead = ApplConstants.CSSHEAD;

	boolean spieltaganzeige;
	boolean adminuser;
	EnSpieltag spieltag;
	Vector<Livespiel> spiele;
	VereinBeanRemote vereinStateless;
	private String text;
	
	public BestcaseTablehead2(LivegoalsObject livegoalsobject)
	{
		this.spieltag = livegoalsobject.getSpieltag();
		this.spiele = livegoalsobject.getLivespiele();
		this.vereinStateless = LookupRemoteService.lookupVereinBeanRemote();
	}

	public String getHtmlRow()
	{
		StringBuffer sb = new StringBuffer("");
		String cssSpieltag;
		cssSpieltag = csshell;
		sb.append(HtmlTags.wrapTD(this.text,null, 2)); 
		Iterator<Livespiel> iter1 = spiele.iterator();
		while(iter1.hasNext())
		{
			EnSpiel spiel = iter1.next().getSpiel();
			EnVerein heim = null;
			EnVerein gast = null;
			try
			{
				heim = vereinStateless.getVerein(spiel.getFkSpielVereinHeim());
				gast = vereinStateless.getVerein(spiel.getFkSpielVereinGast());
			}
			catch (VereinException e)
			{
				e.printStackTrace();
			}
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapP(heim.getAtVereinName() + HtmlTags.createMINUS() 
					+ gast.getAtVereinName(), HtmlTags.wrapIMG50(heim.getAtVereinKurz(), "vereine/gross/")
					+ HtmlTags.createBR(1) + HtmlTags.wrapIMG50(gast.getAtVereinKurz(), "vereine/gross/")), csshead));
		}
		sb.append(HtmlTags.wrapTH(HtmlTags.createNBSP(1), cssSpieltag, 2));
		sb.append(HtmlTags.wrapTH(HtmlTags.createNBSP(1),null)); 
		return HtmlTags.wrapTR(sb.toString() + '\n', null);
	}
	
	public boolean isSpieltaganzeige()
	{
		return spieltaganzeige;
	}

	public void setSpieltaganzeige(boolean spieltaganzeige)
	{
		this.spieltaganzeige = spieltaganzeige;
	}

	public boolean isAdminuser()
	{
		return adminuser;
	}

	public void setAdminuser(boolean adminuser)
	{
		this.adminuser = adminuser;
	}

	public void setText(String text) 
	{
		this.text = text;
	}
	
}
