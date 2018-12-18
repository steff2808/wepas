package tables;

import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.wepas.connector.TippabgabeStatefulBeanRemote;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnSpieltag;
import de.wepas.util.HtmlTags;

public class TippenTable
{
	private static Log log = LogFactory.getLog(TippenTable.class);

	TippenTablehead head;
	Vector<TippenTablerow> rows = new Vector<TippenTablerow>();
	boolean nothingToDo = false;
	
	public TippenTable(TippabgabeStatefulBeanRemote tippabgabeStateful, HttpServletRequest req)
	{
		super();
		log.debug("---> TippenTable");
		EnSpieltag zutippenderspieltag = tippabgabeStateful.getSpieltag();
		if(zutippenderspieltag == null)
		{
//---> Für diesen Tipper gibt es nichts mehr zu tippen				
			nothingToDo = true;
		}
		else
		{
			this.head = new TippenTablehead();
			
			EnSpiel[] spielarray = tippabgabeStateful.getSpiele();
			Vector<EnSpiel> spiele = new Vector<EnSpiel>(spielarray.length);
			for(int i = 0; i < spielarray.length; i++)
			{
				spiele.add(spielarray[i]);
			}
			Iterator<EnSpiel> iter1 = spiele.iterator();
			while(iter1.hasNext())
			{
				rows.add(new TippenTablerow(iter1.next(), tippabgabeStateful.getSpieltag(), req));
			}
		}
	}
	
	public String getHTML()
	{
        StringBuffer sb = new StringBuffer("");
		if (this.nothingToDo)
		{
			sb.append(HtmlTags.wrapH3("Es gibt für Dich keine zu tippende Spieltage!)"));
			sb.append(HtmlTags.wrapUL(
					HtmlTags.wrapLI("Die Tippsaison ist zuende ?") +
					HtmlTags.wrapLI("Du hast bereits alle Spieltage zuende getippt ?") +
					HtmlTags.wrapLI("Steff hat noch nicht alle Spieltage für diese Saison erfaßt (Weil die genauen Termine noch nicht feststehen) ?") +
					HtmlTags.wrapLI("Steff hat noch gar keine Spieltage erfaßt ?")));	
		}
		else
		{
			sb.append(this.head.getHtmlHeadRow());
			Iterator<TippenTablerow> iter2 = this.rows.iterator();
			while(iter2.hasNext())
			{
				sb.append(iter2.next().getHtmlRow());
			}
		}
		return sb.toString();
	}
}
