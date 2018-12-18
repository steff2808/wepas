package tables;

import de.wepas.constants.ApplConstants;
import de.wepas.util.HtmlTags;

public class StatistikTablehead1
{
	String cssweiss = ApplConstants.CSSWEISSL;

	String head1;

	public StatistikTablehead1()
	{
		
		super();
		StringBuffer sb = new StringBuffer("");
		sb.append(HtmlTags.wrapTH(HtmlTags.wrapP
				("Platz in aktueller Sicht", "Platz"), cssweiss));
		sb.append(HtmlTags.wrapTH(HtmlTags.wrapP
				("Name des Tippers", "Tipper"), cssweiss));
		sb.append(HtmlTags.wrapTH(HtmlTags.wrapP
				("Anzahl der ausgewerteten Tipps f&uuml;r diese Statistikseite.", "&sum;"), cssweiss));
		sb.append(HtmlTags.wrapTH(HtmlTags.wrapP
				("Die tatsächlich erzielten Punkte", "Pkte"), cssweiss));
		sb.append(HtmlTags.wrapTH(HtmlTags.wrapP
				("So w&auml;re der Punktestand ohne die Verdopplung von Supertipps.", "ohST"), cssweiss));
		sb.append(HtmlTags.wrapTH(HtmlTags.wrapP
				("Der aktuelle Kontostand.", "&euro;"), cssweiss));
		sb.append(HtmlTags.wrapTH(HtmlTags.wrapP
				("Das w&uuml;rde rumkommen, wenn der Bonus jetzt ausgesch&uuml;ttet/berechnet w&uuml;rde.", "&euro;+Bo"), cssweiss));
		sb.append(HtmlTags.wrapTH(HtmlTags.wrapP
				("Anzahl der gewonnen Spieltage.", "SpTg"), cssweiss));
		sb.append(HtmlTags.wrapTH(HtmlTags.wrapP
				("Anzahl der Tipps, die nach der Tendenz her richtig sind.", "Tend"), cssweiss));
		sb.append(HtmlTags.wrapTH(HtmlTags.wrapP
				("Anzahl aller richtigen Ergebnistipps.", "3/6er"), cssweiss));
		sb.append(HtmlTags.wrapTH(HtmlTags.wrapP
				("Anzahl der exakt richtigen Supertipps (6 Punkte gemacht!).", "6er"), cssweiss));
		sb.append(HtmlTags.wrapTH(HtmlTags.wrapP
				("Anzahl aller Nietentipps ", "Niete"), cssweiss));
		sb.append(HtmlTags.wrapTH(HtmlTags.wrapP
				("Anzahl der aller blinden Tipps. (Tordifferenz mindestens 4 daneben.", "Blind"), cssweiss));
		sb.append(HtmlTags.wrapTH(HtmlTags.wrapP
				("Anzahl aller Hungertipps (Keine Punkte und nur ein Tor daneben)", "Hunger"), cssweiss));
		sb.append(HtmlTags.wrapTH(HtmlTags.wrapP
				("Anzahl der aller Tipps knapp daneben. (Nur ein statt drei Punkten und nur ein Tor daneben)", "Knapp"), cssweiss));
		sb.append(HtmlTags.wrapTH(HtmlTags.wrapP
				("Waldifaktor (Gl&uuml;cksritter) - wenn der Tipp gepunktet hat wurden im Durchschnitt so gepunktet.", "W-F"), cssweiss));
		sb.append(HtmlTags.wrapTH(HtmlTags.wrapP
				("Netzer/Delling Quotient (Fu&szlig;ballverstand). Prozent der Tipps, die gepunktet haben.", "N-D"), cssweiss));
		this.head1 = HtmlTags.wrapTR(sb.toString(), null);
	}
	
	public String getHtmlRow()
	{
		return this.head1;
	}
}
