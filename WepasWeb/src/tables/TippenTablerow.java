package tables;

import java.sql.Timestamp;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.LookupRemoteService;
import de.wepas.connector.VereinBeanRemote;
import de.wepas.connector.VereinException;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnSpieltag;
import de.wepas.jpa.EnVerein;
import de.wepas.util.HtmlTags;

public class TippenTablerow
{
	private static Log log = LogFactory.getLog(TippenTablerow.class);
	
	private String row;
	private final String CSSHELL = ApplConstants.CSSHELL;
	private VereinBeanRemote vereinStateless = LookupRemoteService.lookupVereinBeanRemote();
	
	public TippenTablerow(EnSpiel spiel, EnSpieltag spieltag, HttpServletRequest request)
	{
		super();
		Timestamp jetzt = new Timestamp(new GregorianCalendar().getTimeInMillis());
		StringBuffer sb = new StringBuffer("");
		EnVerein heim = null;
		EnVerein gast = null;
		try
		{
			heim = vereinStateless.getVerein(spiel.getFkSpielVereinHeim());
			gast = vereinStateless.getVerein(spiel.getFkSpielVereinGast());
		}
		catch (VereinException e)
		{
			log.equals("Verein nicht gefunden");
			e.printStackTrace();
		}
		sb.append(HtmlTags.wrapTD(util.StringFormat.format(spiel.getAtSpielAnpfiff()), CSSHELL));
		if(spiel.getAtSpielAnpfiff().compareTo(jetzt) < 0)
		{
			sb.append(HtmlTags.wrapTD(
					HtmlTags.wrapIMG50(heim.getAtVereinKurz(), "vereine/klein/") + 
					HtmlTags.wrapIMG50("vs", "vereine/klein/") + 
					HtmlTags.wrapIMG50(gast.getAtVereinKurz(), "vereine/klein/") + 
					HtmlTags.wrapB("Spiel ist bereits angepfiffen, Tipp wird nicht gewertet!"), CSSHELL));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(
					HtmlTags.wrapIMG50(heim.getAtVereinKurz(), "vereine/klein/") + 
					HtmlTags.wrapIMG50("vs", "vereine/klein/") + 
					HtmlTags.wrapIMG50(gast.getAtVereinKurz(), "vereine/klein/") + 
					heim.getAtVereinName() + HtmlTags.createMINUS() + gast.getAtVereinName(), CSSHELL));
		}
		
		String th;
		String tg;
		try
		{
			int ith = Integer.parseInt(request.getParameter(spiel.getIdSpiel() + ApplConstants.TXTTOREHEIM));
			th = "" + ith;
			int itg = Integer.parseInt(request.getParameter(spiel.getIdSpiel() + ApplConstants.TXTTOREGAST));
			tg = "" + itg;
		}
		catch(NumberFormatException e)
		{
			th = "";
			tg = "";
		}
		sb.append(HtmlTags.wrapTD(
				HtmlTags.createTEXTFIELD(spiel.getIdSpiel() + ApplConstants.TXTTOREHEIM, th, 1, 1) +
				HtmlTags.createNBSP(1) + 
				HtmlTags.createDOPPELPUNKT() + 
				HtmlTags.createNBSP(1) +
				HtmlTags.createTEXTFIELD(spiel.getIdSpiel() + ApplConstants.TXTTOREGAST, tg, 1, 1), CSSHELL));
		
		sb.append(HtmlTags.wrapTD(
				HtmlTags.createCHECKBOX(spiel.getIdSpiel() + "CB_SUPER"), CSSHELL)); 
		this.row = HtmlTags.wrapTR(sb.toString(), null);
	}
	
	public String getHtmlRow()
	{
		return this.row;
	}
	
}
