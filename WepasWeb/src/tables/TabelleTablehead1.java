package tables;

import de.wepas.constants.ApplConstants;
import de.wepas.util.HtmlTags;

public class TabelleTablehead1
{
	String csssubmit1e = ApplConstants.CSSSUBMIT1E;
	
	String cssweissr = ApplConstants.CSSWEISSR;
	String cssweissl = ApplConstants.CSSWEISSL;
	String cssdgraur = ApplConstants.CSSDGRAUR;
	String cssdgraul = ApplConstants.CSSDGRAUL;

	String headG;
	String headH;
	String headA;
	
	public TabelleTablehead1()
	{
		super();
		StringBuffer sb = new StringBuffer("");
		sb.append(HtmlTags.wrapTD("Platz", 2, cssweissr));
		sb.append(HtmlTags.wrapTD(HtmlTags.createNBSP(1), 2, cssweissr));
		sb.append(HtmlTags.wrapTD("Verein", 2, cssweissl));
		sb.append(HtmlTags.wrapTD(HtmlTags.createSUBMITdisabled("gesamttabelle", "Sort", ApplConstants.CSSSUBMIT1E) + HtmlTags.createNBSP(5) + HtmlTags.wrapB("Gesamttabelle"), cssweissl, 8));
		sb.append(HtmlTags.wrapTD(HtmlTags.createSUBMIT("heimtabelle", "Sort", ApplConstants.CSSSUBMIT1E) + HtmlTags.createNBSP(5) + HtmlTags.wrapB("Heimtabelle"), cssdgraul, 8));
		sb.append(HtmlTags.wrapTD(HtmlTags.createSUBMIT("auswaertstabelle", "Sort", ApplConstants.CSSSUBMIT1E) + HtmlTags.createNBSP(5) + HtmlTags.wrapB("Auswärtstabelle"), cssdgraul, 8));
		String zeile1 = HtmlTags.wrapTR(sb.toString(), null);		
	
		sb = new StringBuffer("");
		sb.append(HtmlTags.wrapTD("Form", cssweissl));
		sb.append(HtmlTags.wrapTD("Sp", cssweissr));
		sb.append(HtmlTags.wrapTD("G", cssweissr));
		sb.append(HtmlTags.wrapTD("U", cssweissr));
		sb.append(HtmlTags.wrapTD("V", cssweissr));
		sb.append(HtmlTags.wrapTD("Tore", cssweissr));
		sb.append(HtmlTags.wrapTD("Diff", cssweissr));
		sb.append(HtmlTags.wrapTD("Punkte", cssweissr));
		
		sb.append(HtmlTags.wrapTD("Form", cssdgraul));
		sb.append(HtmlTags.wrapTD("Sp", cssdgraur));
		sb.append(HtmlTags.wrapTD("G", cssdgraur));
		sb.append(HtmlTags.wrapTD("U", cssdgraur));
		sb.append(HtmlTags.wrapTD("V", cssdgraur));
		sb.append(HtmlTags.wrapTD("Tore", cssdgraur));
		sb.append(HtmlTags.wrapTD("Diff", cssdgraur));
		sb.append(HtmlTags.wrapTD("Punkte", cssdgraur));
		
		sb.append(HtmlTags.wrapTD("Form", cssdgraul));
		sb.append(HtmlTags.wrapTD("Sp", cssdgraur));
		sb.append(HtmlTags.wrapTD("G", cssdgraur));
		sb.append(HtmlTags.wrapTD("U", cssdgraur));
		sb.append(HtmlTags.wrapTD("V", cssdgraur));
		sb.append(HtmlTags.wrapTD("Tore", cssdgraur));
		sb.append(HtmlTags.wrapTD("Diff", cssdgraur));
		sb.append(HtmlTags.wrapTD("Punkte", cssdgraur));
		String zeile2 = HtmlTags.wrapTR(sb.toString(), null);
		this.headG = zeile1 + zeile2;
		
		sb = new StringBuffer("");
		sb.append(HtmlTags.wrapTD("Platz", 2, cssweissr));
		sb.append(HtmlTags.wrapTD(HtmlTags.createNBSP(1), 2, cssweissr));
		sb.append(HtmlTags.wrapTD("Verein", 2, cssweissl));
		sb.append(HtmlTags.wrapTD(HtmlTags.createSUBMIT("gesamttabelle", "Sort", ApplConstants.CSSSUBMIT1E) + HtmlTags.createNBSP(5) + HtmlTags.wrapB("Gesamttabelle"), cssdgraul, 8));
		sb.append(HtmlTags.wrapTD(HtmlTags.createSUBMITdisabled("heimtabelle", "Sort", ApplConstants.CSSSUBMIT1E) + HtmlTags.createNBSP(5) + HtmlTags.wrapB("Heimtabelle"), cssweissl, 8));
		sb.append(HtmlTags.wrapTD(HtmlTags.createSUBMIT("auswaertstabelle", "Sort", ApplConstants.CSSSUBMIT1E) + HtmlTags.createNBSP(5) + HtmlTags.wrapB("Auswärtstabelle"), cssdgraul, 8));
		zeile1 = HtmlTags.wrapTR(sb.toString(), null);		
	
		sb = new StringBuffer("");
		sb.append(HtmlTags.wrapTD("Form", cssdgraul));
		sb.append(HtmlTags.wrapTD("Sp", cssdgraur));
		sb.append(HtmlTags.wrapTD("G", cssdgraur));
		sb.append(HtmlTags.wrapTD("U", cssdgraur));
		sb.append(HtmlTags.wrapTD("V", cssdgraur));
		sb.append(HtmlTags.wrapTD("Tore", cssdgraur));
		sb.append(HtmlTags.wrapTD("Diff", cssdgraur));
		sb.append(HtmlTags.wrapTD("Punkte", cssdgraur));
		
		sb.append(HtmlTags.wrapTD("Form", cssweissl));
		sb.append(HtmlTags.wrapTD("Sp", cssweissr));
		sb.append(HtmlTags.wrapTD("G", cssweissr));
		sb.append(HtmlTags.wrapTD("U", cssweissr));
		sb.append(HtmlTags.wrapTD("V", cssweissr));
		sb.append(HtmlTags.wrapTD("Tore", cssweissr));
		sb.append(HtmlTags.wrapTD("Diff", cssweissr));
		sb.append(HtmlTags.wrapTD("Punkte", cssweissr));
		
		sb.append(HtmlTags.wrapTD("Form", cssdgraul));
		sb.append(HtmlTags.wrapTD("Sp", cssdgraur));
		sb.append(HtmlTags.wrapTD("G", cssdgraur));
		sb.append(HtmlTags.wrapTD("U", cssdgraur));
		sb.append(HtmlTags.wrapTD("V", cssdgraur));
		sb.append(HtmlTags.wrapTD("Tore", cssdgraur));
		sb.append(HtmlTags.wrapTD("Diff", cssdgraur));
		sb.append(HtmlTags.wrapTD("Punkte", cssdgraur));
		zeile2 = HtmlTags.wrapTR(sb.toString(), null);
		this.headH = zeile1 + zeile2;
		
		sb = new StringBuffer("");
		sb.append(HtmlTags.wrapTD("Platz", 2, cssweissr));
		sb.append(HtmlTags.wrapTD(HtmlTags.createNBSP(1), 2, cssweissr));
		sb.append(HtmlTags.wrapTD("Verein", 2, cssweissl));
		sb.append(HtmlTags.wrapTD(HtmlTags.createSUBMIT("gesamttabelle", "Sort", ApplConstants.CSSSUBMIT1E) + HtmlTags.createNBSP(5) + HtmlTags.wrapB("Gesamttabelle"), cssdgraul, 8));
		sb.append(HtmlTags.wrapTD(HtmlTags.createSUBMIT("heimtabelle", "Sort", ApplConstants.CSSSUBMIT1E) + HtmlTags.createNBSP(5) +  HtmlTags.wrapB("Heimtabelle"), cssdgraul, 8));
		sb.append(HtmlTags.wrapTD(HtmlTags.createSUBMITdisabled("auswaertstabelle", "Sort", ApplConstants.CSSSUBMIT1E) + HtmlTags.createNBSP(5)  + HtmlTags.wrapB("Auswärtstabelle"), cssweissl, 8));
		zeile1 = HtmlTags.wrapTR(sb.toString(), null);		
		
		sb = new StringBuffer("");
		sb.append(HtmlTags.wrapTD("Form", cssdgraul));
		sb.append(HtmlTags.wrapTD("Sp", cssdgraur));
		sb.append(HtmlTags.wrapTD("G", cssdgraur));
		sb.append(HtmlTags.wrapTD("U", cssdgraur));
		sb.append(HtmlTags.wrapTD("V", cssdgraur));
		sb.append(HtmlTags.wrapTD("Tore", cssdgraur));
		sb.append(HtmlTags.wrapTD("Diff", cssdgraur));
		sb.append(HtmlTags.wrapTD("Punkte", cssdgraur));
		
		sb.append(HtmlTags.wrapTD("Form", cssdgraul));
		sb.append(HtmlTags.wrapTD("Sp", cssdgraur));
		sb.append(HtmlTags.wrapTD("G", cssdgraur));
		sb.append(HtmlTags.wrapTD("U", cssdgraur));
		sb.append(HtmlTags.wrapTD("V", cssdgraur));
		sb.append(HtmlTags.wrapTD("Tore", cssdgraur));
		sb.append(HtmlTags.wrapTD("Diff", cssdgraur));
		sb.append(HtmlTags.wrapTD("Punkte", cssdgraur));
		
		sb.append(HtmlTags.wrapTD("Form", cssweissl));
		sb.append(HtmlTags.wrapTD("Sp", cssweissr));
		sb.append(HtmlTags.wrapTD("G", cssweissr));
		sb.append(HtmlTags.wrapTD("U", cssweissr));
		sb.append(HtmlTags.wrapTD("V", cssweissr));
		sb.append(HtmlTags.wrapTD("Tore", cssweissr));
		sb.append(HtmlTags.wrapTD("Diff", cssweissr));
		sb.append(HtmlTags.wrapTD("Punkte", cssweissr));
		zeile2 = HtmlTags.wrapTR(sb.toString(), null);
		this.headA = zeile1 + zeile2;
	}
	
	public String getHtmlGRow()
	{
		return this.headG;
	}	
	
	public String getHtmlHRow()
	{
		return this.headH;
	}	
	
	public String getHtmlARow()
	{
		return this.headA;
	}
}
