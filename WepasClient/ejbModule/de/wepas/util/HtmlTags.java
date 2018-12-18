package de.wepas.util;

import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnTipp;
import de.wepas.livegoals.Livetipp;

public class HtmlTags
{
	public static String wrapB(String s)
	{
		return "<b>"+ s + "</b>";
	}
	
	public static String wrapUL(String s)
	{
		return "<ul>"+ s + "</ul>";
	}
	
	public static String wrapLI(String s)
	{
		return "<li>"+ s + "</li>";
	}
	
	public static String wrapH2(String s)
	{
		return "<h2>"+ s + "</h2>";
	}
	
	public static String wrapH3(String s)
	{
		return "<h3>"+ s + "</h3>";
	}
	
	public static String wrapTABLE(String s, String c)
	{
		if (c == null)
		{
			return "<table>" + '\n' + s + "</table>";
		}
		else
		{
			return "<table class='" + c + "'>" + '\n' + s  + "</table>";
		}
	}
	
	public static String wrapTR(String s, String c)
	{
		if (c == null)
		{
			return "<tr>" + '\n' + s  + "</tr>";
		}
		else
		{
			return "<tr class='" + c + "'>" + '\n'  + s  + "</tr>";
		}
	}
	
	public static String wrapP(String titel, String string)
	{
		return "<p title='" + titel + "'>" + string + "</p>";
	}
	
	public static String wrapTH(String s, String c)
	{
		if (c == null)
		{
			return "<th>" + s  + "</th>" + '\n';
		}
		else
		{
			return "<th class='" + c + "'>" + s + "</th>" + '\n';
		}
	}
	
	public static String wrapTH(String s, String c, int colspan)
	{
		if (c == null)
		{
			return "<th colspan = '" + colspan + "'>" + s  + "</th>" + '\n';
		}
		else
		{
			return "<th colspan = '" + colspan + "' class='" + c + "'>" + s  + "</th>" + '\n';
		}
	}
	
	public static String wrapTH(String s, String c, int colspan, int rowspan)
	{
		if (c == null)
		{
			return "<th colspan = '" + colspan + "' rowspan = '" + rowspan + "'>" + s  + "</th>" + '\n';
		}
		else
		{
			return "<th colspan = '" + colspan + "' rowspan = '" + rowspan + "' class='" + c + "'>" + s  + "</th>" + '\n';
		}
	}

	public static String wrapTH(int width, String s, String clas)
	{
		if (clas == null)
		{
			return "<th width='" + width + "'>" + s  + "</th>" + '\n';
		}
		else
		{
			return "<th class='" + clas + "' width='" + width +"'>" + s + "</th>" + '\n';
		}
	}

	public static String wrapTD(String s, String clas, int colspan)
	{
		if (clas == null)
		{
			return "<td colspan = '" + colspan + "'>" + s + "</td>" + '\n';
		}
		else
		{
			return "<td colspan = '" + colspan + "' class='" + clas + "'>" + s + "</td>" + '\n';
		}
	}

	public static String wrapTD(String s, String clas)
	{
		if (clas == null)
		{
			return "<td>" + s + "</td>" + '\n';
		}
		else
		{
			return "<td class='" + clas + "'>" + s + "</td>" + '\n';
		}
	}
	
	public static String wrapTD(String s, int rowspan, String clas)
	{
		if (clas == null)
		{
			return "<td rowspan='" + rowspan + "'>" + s + "</td>" + '\n';
		}
		else
		{
			return "<td class='" + clas + "' rowspan='" + rowspan + "'>" + s + "</td>" + '\n';
		}
	}

	public static String wrapTD(int width, String s, String clas)
	{
		if (clas == null)
		{
			return "<td  width='" + width +"'>" + s + "</td>" + '\n';
		}
		else
		{
			return "<td class='" + clas + "' width='" + width +"'>" + s + "</td>" + '\n';
		}
	}
	
	public static String wrapTD(int width, String s, String clas, int colspan)
	{
		if (clas == null)
		{
			return "<td colspan='" + colspan + "' width='" + width + "'>" + s + "</td>" + '\n';
		}
		else
		{
			return "<td colspan='" + colspan + "' class='" + clas + "' width='" + width + "'>" + s + "</td>" + '\n';
		}
	}

	
//	public static String wrapTDW(String s, String clas, int width)
//	{
//		if (clas == null)
//		{
//			return "<td width='" + width + "'>" + s + "</td>" + '\n';
//		}
//		else
//		{
//			return "<td width='" + width + "' class='" + clas + "'>" + s + "</td>" + '\n';
//		}
//	}
	

	
	
	public static String wrapSPAN(String string, String c)
	{
		return "<span class='" + c + "'>" + string + "</span>";
	}

	
	public static String wrapIMG50(String s, String path)
	{
		StringBuffer sb = new StringBuffer("");
		sb.append("<img src='/WepasWeb/img/" + path + s + ".png' width='40'  height='40' border='0'>");
		return sb.toString();
	}
	
	public static String wrapIMG35(String s, String path)
	{
		StringBuffer sb = new StringBuffer("");
		sb.append("<img src='/WepasWeb/img/" + path + s + ".png' width='35' height='35' border='0'>");
		return sb.toString();
	}
//	sb.append("<img src='/WepasWeb/img/" + path + s + ".png' width='35' height='24' border='0'>");
	
	public static String wrapIMG25(String s, String path)
	{
		StringBuffer sb = new StringBuffer("");
		sb.append("<img src='/WepasWeb/img/" + path + s + ".png' height='25' height='25' border='0'>");
		return sb.toString();
	}
	
	public static String createNBSP(int n)
	{
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < n; i++)
		{
			sb.append("&nbsp;");
		}
		return sb.toString();
	}
	
	public static String createBR(int n)
	{
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < n; i++)
		{
			sb.append("<br/>");
		}
		return sb.toString();
	}
	
	public static String createEURO()
	{
		return "&#8364;";
	}
	
	public static String createSTERN()
	{
		return "*";
	}
	
	public static String createHAKEN()
	{
		return "&#10004;";
	}
	
	public static String createPUNKT()
	{
		return ".";
	}
	
	public static String createFRAGEZEICHEN()
	{
		return "?";
	}
	
	public static String createKOMMA()
	{
		return ",";
	}
	
	public static String createMINUS()
	{
		return "-";
	}
	
	public static String createPLUS()
	{
		return "+";
	}
	
	public static String createGLEICH()
	{
		return "=";
	}
	
	public static String createKLAMMERAUF()
	{
		return "(";
	}
	
	public static String createKLAMMERZU()
	{
		return ")";
	}
	
	public static String createDOPPELPUNKT()
	{
		return ":";
	}
	
	public static String createTEXTFIELD(String name, int size, int max)
	{
		StringBuffer sb = new StringBuffer("<input type='text' name='");
		sb.append(name);
		sb.append("' ");
		sb.append("value='");
		sb.append("");
		sb.append("' ");
		if(size > 0)
		{
			sb.append("size='");
			sb.append(size);
			sb.append("' ");
		}
		if(max > 0)
		{
			sb.append("maxlength='");
			sb.append(max);
			sb.append("' ");
		}
		sb.append(">");
		return sb.toString();
	}
	
	public static String createTEXTFIELD(String name, String value, int size, int max)
	{
		StringBuffer sb = new StringBuffer("<input type='text' name='");
		sb.append(name);
		sb.append("' ");
		sb.append("value='");
		sb.append(value);
		sb.append("' ");
		if(size > 0)
		{
			sb.append("size='");
			sb.append(size);
			sb.append("' ");
		}
		if(max > 0)
		{
			sb.append("maxlength='");
			sb.append(max);
			sb.append("' ");
		}
		sb.append(">");
		return sb.toString();
	}

	public static String createCHECKBOX(String name)
	{
		StringBuffer sb = new StringBuffer("<input type='checkbox' name='");
		sb.append(name);
		sb.append("'>");
		return sb.toString();
	}
	
	public static String createSUBMIT(String name, String value, String css)
	{
		StringBuffer sb = new StringBuffer("");
		sb.append("<input type='submit' name='");
		sb.append(name);
		sb.append("' value='");
		sb.append(value);
		if (css != null)
		{
			sb.append("' class='");
			sb.append(css);
		}
		sb.append("'>");
		return sb.toString();
	}
	
	public static String createSUBMITdisabled(String name, String value, String css)
	{
		StringBuffer sb = new StringBuffer("");
		sb.append("<input type='submit' name='");
		sb.append(name);
		sb.append("' value='");
		sb.append(value);
		if (css != null)
		{
			sb.append("' class='");
			sb.append(css);
		}
		sb.append("' disabled>");
		return sb.toString();
	}
	
	public static String getLivetippHTML (Livetipp livetipp)
	{
		int w = ApplConstants.WIDTHM;
		String s;
		if(livetipp.getLivepunkte() - livetipp.getLivepunktehist() > 2)
		{
			if(livetipp.getTipp().getAtTippIsSupertipp())
			{
				s = wrapB(livetipp.getTipp().getAtTippTorHeim() + createDOPPELPUNKT() + livetipp.getTipp().getAtTippTorGast() + createSTERN());
			}
			else
			{	
				s = livetipp.getTipp().getAtTippTorHeim()  + HtmlTags.createDOPPELPUNKT() + livetipp.getTipp().getAtTippTorGast();
			}
			return HtmlTags.wrapTD(w, HtmlTags.wrapSPAN(livetipp.getLivepunkte() + HtmlTags.createNBSP(1), ApplConstants.CSSTIPPPKT) + s, ApplConstants.CSSTIPPPLPL);
		}
		if(livetipp.getLivepunkte() - livetipp.getLivepunktehist() > 0)
		{
			if(livetipp.getTipp().getAtTippIsSupertipp())
			{
				s = wrapB(livetipp.getTipp().getAtTippTorHeim() + createDOPPELPUNKT() + livetipp.getTipp().getAtTippTorGast() + createSTERN());
			}
			else
			{	
				s = livetipp.getTipp().getAtTippTorHeim()  + HtmlTags.createDOPPELPUNKT() + livetipp.getTipp().getAtTippTorGast();
			}
			return HtmlTags.wrapTD(w, HtmlTags.wrapSPAN(livetipp.getLivepunkte() + HtmlTags.createNBSP(1), ApplConstants.CSSTIPPPKT) + s, ApplConstants.CSSTIPPPL);			
		}
		if(livetipp.getLivepunkte() - livetipp.getLivepunktehist() < -2)
		{
			if(livetipp.getTipp().getAtTippIsSupertipp())
			{
				s = wrapB(livetipp.getTipp().getAtTippTorHeim() + createDOPPELPUNKT() + livetipp.getTipp().getAtTippTorGast() + createSTERN());
			}
			else
			{	
				s = livetipp.getTipp().getAtTippTorHeim()  + HtmlTags.createDOPPELPUNKT() + livetipp.getTipp().getAtTippTorGast();
			}
			return HtmlTags.wrapTD(w, HtmlTags.wrapSPAN(livetipp.getLivepunkte() + HtmlTags.createNBSP(1), ApplConstants.CSSTIPPPKT) + s, ApplConstants.CSSTIPPMI);			
		}
		if(livetipp.getLivepunkte() - livetipp.getLivepunktehist() < 0)
		{
			if(livetipp.getTipp().getAtTippIsSupertipp())
			{
				s = wrapB(livetipp.getTipp().getAtTippTorHeim() + createDOPPELPUNKT() + livetipp.getTipp().getAtTippTorGast() + createSTERN());
			}
			else
			{	
				s = livetipp.getTipp().getAtTippTorHeim()  + HtmlTags.createDOPPELPUNKT() + livetipp.getTipp().getAtTippTorGast();
			}
			return HtmlTags.wrapTD(w, HtmlTags.wrapSPAN(livetipp.getLivepunkte() + HtmlTags.createNBSP(1), ApplConstants.CSSTIPPPKT) + s, ApplConstants.CSSTIPPMIMI);			
		}
		if(livetipp.getTipp().getAtTippIsSupertipp())
		{
			s = wrapB(livetipp.getTipp().getAtTippTorHeim() + createDOPPELPUNKT() + livetipp.getTipp().getAtTippTorGast() + createSTERN());
		}
		else
		{	
			s = livetipp.getTipp().getAtTippTorHeim()  + HtmlTags.createDOPPELPUNKT() + livetipp.getTipp().getAtTippTorGast();
		}
		if(livetipp.getTipp().getAtTippTorGast() == 9 && livetipp.getTipp().getAtTippTorHeim() == 9)
		{
			return wrapTD(createMINUS() + createDOPPELPUNKT() + createMINUS(), ApplConstants.CSSTIPP0);
		}
		else
		{
			return wrapTD(w, HtmlTags.wrapSPAN(livetipp.getLivepunkte() + HtmlTags.createNBSP(1), ApplConstants.CSSTIPPPKT) + s, ApplConstants.CSSTIPPOHNE);
		}
	}
	
	public static String getTippHTML(EnTipp tipp)
	{
		int w = ApplConstants.WIDTHM;
		if(tipp.getAtTippTorGast() == 9 && tipp.getAtTippTorHeim() == 9)
		{
			return wrapTD(createMINUS() + createDOPPELPUNKT() + createMINUS(), ApplConstants.CSSTIPP0);
		}
		String s;
		if(tipp.getAtTippIsSupertipp())
		{
			s = wrapB(tipp.getAtTippTorHeim() + createDOPPELPUNKT() + tipp.getAtTippTorGast() + createSTERN());
		}
		else
		{	
			s = tipp.getAtTippTorHeim()  + HtmlTags.createDOPPELPUNKT() + tipp.getAtTippTorGast();
		}
		if(!tipp.getAtTippIsValid())
		{
			return HtmlTags.wrapTD(w, s, ApplConstants.CSSTIPPOHNE);
		}
		if(tipp.getAtTippIsExakt())
		{
			if(tipp.getAtTippIsSupertipp())
			{
				return HtmlTags.wrapTD(w, HtmlTags.wrapSPAN(tipp.getAtTippPunkte() + HtmlTags.createNBSP(1), ApplConstants.CSSTIPPPKT) + s, ApplConstants.CSSTIPP6);
			}
			else
			{
				return HtmlTags.wrapTD(w, HtmlTags.wrapSPAN(tipp.getAtTippPunkte() + HtmlTags.createNBSP(1), ApplConstants.CSSTIPPPKT) + s, ApplConstants.CSSTIPP3);
			}
		}
		if(tipp.getAtTippIsDifferenz())
		{
			if(tipp.getAtTippIsSupertipp())
			{
				return HtmlTags.wrapTD(w, HtmlTags.wrapSPAN(tipp.getAtTippPunkte() + HtmlTags.createNBSP(1), ApplConstants.CSSTIPPPKT) + s, ApplConstants.CSSTIPP4);
			}
			else
			{
				return HtmlTags.wrapTD(w, HtmlTags.wrapSPAN(tipp.getAtTippPunkte() + HtmlTags.createNBSP(1), ApplConstants.CSSTIPPPKT) + s, ApplConstants.CSSTIPP2);
			}
		}
		if(tipp.getAtTippIsTendenz())
		{
			if(tipp.getAtTippIsSupertipp())
			{
				return HtmlTags.wrapTD(w, HtmlTags.wrapSPAN(tipp.getAtTippPunkte() + HtmlTags.createNBSP(1), ApplConstants.CSSTIPPPKT) + s, ApplConstants.CSSTIPP2);
			}
			else
			{
				return HtmlTags.wrapTD(w, HtmlTags.wrapSPAN(tipp.getAtTippPunkte() + HtmlTags.createNBSP(1), ApplConstants.CSSTIPPPKT) + s, ApplConstants.CSSTIPP1);
			}
		}
		return HtmlTags.wrapTD(w, HtmlTags.wrapSPAN(0 + HtmlTags.createNBSP(1), ApplConstants.CSSTIPPPKT) + s, ApplConstants.CSSTIPP0);
	}

	public static String getCasetippHTML (Livetipp livetipp)
	{
		int w = ApplConstants.WIDTHM;
		String s;
		if(livetipp.getLivepunkte() - livetipp.getLivepunktehist() > 2)
		{
			if(livetipp.getTipp().getAtTippIsSupertipp())
			{
				s = wrapB(livetipp.getTipp().getAtTippTorHeim() + createDOPPELPUNKT() + livetipp.getTipp().getAtTippTorGast() + createSTERN());
			}
			else
			{	
				s = livetipp.getTipp().getAtTippTorHeim()  + HtmlTags.createDOPPELPUNKT() + livetipp.getTipp().getAtTippTorGast();
			}
			return HtmlTags.wrapTD(w, HtmlTags.wrapSPAN(livetipp.getLivepunkte() + HtmlTags.createNBSP(1), ApplConstants.CSSTIPPPKT) + s, ApplConstants.CSSTIPPCASE);
		}
		if(livetipp.getLivepunkte() - livetipp.getLivepunktehist() > 0)
		{
			if(livetipp.getTipp().getAtTippIsSupertipp())
			{
				s = wrapB(livetipp.getTipp().getAtTippTorHeim() + createDOPPELPUNKT() + livetipp.getTipp().getAtTippTorGast() + createSTERN());
			}
			else
			{	
				s = livetipp.getTipp().getAtTippTorHeim()  + HtmlTags.createDOPPELPUNKT() + livetipp.getTipp().getAtTippTorGast();
			}
			return HtmlTags.wrapTD(w, HtmlTags.wrapSPAN(livetipp.getLivepunkte() + HtmlTags.createNBSP(1), ApplConstants.CSSTIPPPKT) + s, ApplConstants.CSSTIPPCASE);			
		}
		if(livetipp.getLivepunkte() - livetipp.getLivepunktehist() < -2)
		{
			if(livetipp.getTipp().getAtTippIsSupertipp())
			{
				s = wrapB(livetipp.getTipp().getAtTippTorHeim() + createDOPPELPUNKT() + livetipp.getTipp().getAtTippTorGast() + createSTERN());
			}
			else
			{	
				s = livetipp.getTipp().getAtTippTorHeim()  + HtmlTags.createDOPPELPUNKT() + livetipp.getTipp().getAtTippTorGast();
			}
			return HtmlTags.wrapTD(w, HtmlTags.wrapSPAN(livetipp.getLivepunkte() + HtmlTags.createNBSP(1), ApplConstants.CSSTIPPPKT) + s, ApplConstants.CSSTIPPCASE);			
		}
		if(livetipp.getLivepunkte() - livetipp.getLivepunktehist() < 0)
		{
			if(livetipp.getTipp().getAtTippIsSupertipp())
			{
				s = wrapB(livetipp.getTipp().getAtTippTorHeim() + createDOPPELPUNKT() + livetipp.getTipp().getAtTippTorGast() + createSTERN());
			}
			else
			{	
				s = livetipp.getTipp().getAtTippTorHeim()  + HtmlTags.createDOPPELPUNKT() + livetipp.getTipp().getAtTippTorGast();
			}
			return HtmlTags.wrapTD(w, HtmlTags.wrapSPAN(livetipp.getLivepunkte() + HtmlTags.createNBSP(1), ApplConstants.CSSTIPPPKT) + s, ApplConstants.CSSTIPPCASE);			
		}
		if(livetipp.getTipp().getAtTippIsSupertipp())
		{
			s = wrapB(livetipp.getTipp().getAtTippTorHeim() + createDOPPELPUNKT() + livetipp.getTipp().getAtTippTorGast() + createSTERN());
		}
		else
		{	
			s = livetipp.getTipp().getAtTippTorHeim()  + HtmlTags.createDOPPELPUNKT() + livetipp.getTipp().getAtTippTorGast();
		}
		if(livetipp.getTipp().getAtTippTorGast() == 9 && livetipp.getTipp().getAtTippTorHeim() == 9)
		{
			return wrapTD(createMINUS() + createDOPPELPUNKT() + createMINUS(), ApplConstants.CSSTIPPCASE);
		}
		else
		{
			return wrapTD(w, HtmlTags.wrapSPAN(livetipp.getLivepunkte() + HtmlTags.createNBSP(1), ApplConstants.CSSTIPPPKT) + s, ApplConstants.CSSTIPPCASE);
		}
	}


}
