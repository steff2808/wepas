<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page 
         import="de.wepas.connector.VereinBeanRemote"
         import="de.wepas.connector.VereinException"
         import="de.wepas.constants.ApplConstants"
         import="de.wepas.jpa.EnVerein"
         import="util.LookupRemoteService"
         import="javax.rmi.PortableRemoteObject"
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=ApplConstants.CSSFILEADMIN%>">
<title>WEPAS Verein ändern</title>
</head>

<body>
<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPVEREINAENDERN)%> method="post"> 
<h1>Bestehenden WEPAS Verein ändern</h1>
<h3><a href="<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPVEREINVERWALTUNG)%>">Zurück zur Vereinverwaltung</a></h3>
<% 
	VereinBeanRemote vbr = (VereinBeanRemote)session.getAttribute(ApplConstants.SAVEVEREINREMOTE);
	if(vbr == null)
	{
		vbr = LookupRemoteService.lookupVereinBeanRemote();
		session.setAttribute(ApplConstants.SAVEVEREINREMOTE, vbr);
	}

	EnVerein vereinAlt = null;
	String stringVereinnr = request.getParameter(ApplConstants.SAVEVEREINNR);
	if(stringVereinnr != null)
	{
		try
		{
			vereinAlt = vbr.getVerein(new Integer(stringVereinnr).intValue());
			session.setAttribute(ApplConstants.SAVEVEREINALT, vereinAlt);
		}
		catch(VereinException e)
		{
			out.print("<br><b style='color:red'>" + e.getMessage() + "</b>");
		}
		catch(Exception e)
		{
			out.print("<br><b style='color:red'>" + e.getMessage() + "</b>");
			session.setAttribute(ApplConstants.SAVEVEREINREMOTE, null);
		}
	}
	else
	{
		vereinAlt = (EnVerein)session.getAttribute(ApplConstants.SAVEVEREINALT);
	}
	
	if(vereinAlt == null && stringVereinnr == null)
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPVEREINVERWALTUNG%>"></jsp:forward>
<%		
		return;
	}
	
	boolean vereinKorrekt = true;
	boolean nameKorrekt = true;
	boolean kurzKorrekt = true;
	boolean regexKorrekt = true;
	boolean spKorrekt = true;
	boolean siKorrekt = true;
	boolean unKorrekt = true;
	boolean niKorrekt = true;
	boolean tgKorrekt = true;
	boolean tkKorrekt = true;
	boolean punkteKorrekt = true;
	boolean hspKorrekt = true;
	boolean hsiKorrekt = true;
	boolean hunKorrekt = true;
	boolean hniKorrekt = true;
	boolean htgKorrekt = true;
	boolean htkKorrekt = true;
	boolean hpunkteKorrekt = true;
	boolean aspKorrekt = true;
	boolean asiKorrekt = true;
	boolean aunKorrekt = true;
	boolean aniKorrekt = true;
	boolean atgKorrekt = true;
	boolean atkKorrekt = true;
	boolean apunkteKorrekt = true;
	boolean hrspKorrekt = true;
	boolean hrsiKorrekt = true;
	boolean hrunKorrekt = true;
	boolean hrniKorrekt = true;
	boolean hrtgKorrekt = true;
	boolean hrtkKorrekt = true;
	boolean hrpunkteKorrekt = true;
	boolean rrspKorrekt = true;
	boolean rrsiKorrekt = true;
	boolean rrunKorrekt = true;
	boolean rrniKorrekt = true;
	boolean rrtgKorrekt = true;
	boolean rrtkKorrekt = true;
	boolean rrpunkteKorrekt = true;
	boolean formKorrekt = true;
	
	String name = null;
	String kurz = null;
	String regex = null;
	int sp = -1;
	int si = -1;
	int un = -1;
	int ni = -1;
	int tg = -1;
	int tk = -1;
	int punkte = -1;
	int hsp = -1;
	int hsi = -1;
	int hun = -1;
	int hni = -1;
	int htg = -1;
	int htk = -1;
	int hpunkte = -1;
	int asp = -1;
	int asi = -1;
	int aun = -1;
	int ani = -1;
	int atg = -1;
	int atk = -1;
	int apunkte = -1;
	int hrsp = -1;
	int hrsi = -1;
	int hrun = -1;
	int hrni = -1;
	int hrtg = -1;
	int hrtk = -1;
	int hrpunkte = -1;
	int rrsp = -1;
	int rrsi = -1;
	int rrun = -1;
	int rrni = -1;
	int rrtg = -1;
	int rrtk = -1;
	int rrpunkte = -1;
	String formstring = null;
	//BigDecimal preis = null;
	//Integer lagermenge = null;
	
	if(request.getParameter("speichern") != null)
	{
		if(request.getParameter("name").length() != 0)
		{
			name = request.getParameter("name");
		}
		
		if(request.getParameter("kurz").length() != 0)
		{
			kurz = request.getParameter("kurz");
			//try
			//{
			//	preis = new BigDecimal(request.getParameter("preis"));
			//}
			//catch(NumberFormatException e)
			//{
			//	vereinKorrekt = preisKorrekt = false;
			//}
		}
		
		if(request.getParameter("regex").length() != 0)
		{
			regex = request.getParameter("regex");
		}
		
		if(request.getParameter("sp").length() != 0)
		{
			try
			{
				sp = Integer.parseInt(request.getParameter("sp"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = spKorrekt = false;
			}
		}
		
		if(request.getParameter("si").length() != 0)
		{
			try
			{
				si = Integer.parseInt(request.getParameter("si"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = siKorrekt = false;
			}
		}
		
		if(request.getParameter("un").length() != 0)
		{
			try
			{
				un = Integer.parseInt(request.getParameter("un"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = unKorrekt = false;
			}
		}
		if(request.getParameter("ni").length() != 0)
		{
			try
			{
				ni = Integer.parseInt(request.getParameter("ni"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = niKorrekt = false;
			}
		}
		if(request.getParameter("tg").length() != 0)
		{
			try
			{
				tg = Integer.parseInt(request.getParameter("tg"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = tgKorrekt = false;
			}
		}
		if(request.getParameter("tk").length() != 0)
		{
			try
			{
				tk = Integer.parseInt(request.getParameter("tk"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = tkKorrekt = false;
			}
		}
		if(request.getParameter("punkte").length() != 0)
		{
			try
			{
				punkte = Integer.parseInt(request.getParameter("punkte"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = punkteKorrekt = false;
			}
		}
		
//heim
		if(request.getParameter("hsp").length() != 0)
		{
			try
			{
				hsp = Integer.parseInt(request.getParameter("hsp"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = hspKorrekt = false;
			}
		}
		
		if(request.getParameter("hsi").length() != 0)
		{
			try
			{
				hsi = Integer.parseInt(request.getParameter("hsi"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = hsiKorrekt = false;
			}
		}
		
		if(request.getParameter("hun").length() != 0)
		{
			try
			{
				hun = Integer.parseInt(request.getParameter("hun"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = hunKorrekt = false;
			}
		}
		if(request.getParameter("hni").length() != 0)
		{
			try
			{
				hni = Integer.parseInt(request.getParameter("hni"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = hniKorrekt = false;
			}
		}
		if(request.getParameter("htg").length() != 0)
		{
			try
			{
				htg = Integer.parseInt(request.getParameter("htg"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = htgKorrekt = false;
			}
		}
		if(request.getParameter("htk").length() != 0)
		{
			try
			{
				htk = Integer.parseInt(request.getParameter("htk"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = htkKorrekt = false;
			}
		}
		if(request.getParameter("hpunkte").length() != 0)
		{
			try
			{
				hpunkte = Integer.parseInt(request.getParameter("hpunkte"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = hpunkteKorrekt = false;
			}
		}
		
//auswärts
		if(request.getParameter("asp").length() != 0)
		{
			try
			{
				asp = Integer.parseInt(request.getParameter("asp"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = aspKorrekt = false;
			}
		}
		
		if(request.getParameter("asi").length() != 0)
		{
			try
			{
				asi = Integer.parseInt(request.getParameter("asi"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = asiKorrekt = false;
			}
		}
		
		if(request.getParameter("aun").length() != 0)
		{
			try
			{
				aun = Integer.parseInt(request.getParameter("aun"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = aunKorrekt = false;
			}
		}
		if(request.getParameter("ani").length() != 0)
		{
			try
			{
				ani = Integer.parseInt(request.getParameter("ani"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = aniKorrekt = false;
			}
		}
		if(request.getParameter("atg").length() != 0)
		{
			try
			{
				atg = Integer.parseInt(request.getParameter("atg"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = atgKorrekt = false;
			}
		}
		if(request.getParameter("atk").length() != 0)
		{
			try
			{
				atk = Integer.parseInt(request.getParameter("atk"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = atkKorrekt = false;
			}
		}
		if(request.getParameter("apunkte").length() != 0)
		{
			try
			{
				apunkte = Integer.parseInt(request.getParameter("apunkte"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = apunkteKorrekt = false;
			}
		}

//hinrunde
		if(request.getParameter("hrsp").length() != 0)
		{
			try
			{
				hrsp = Integer.parseInt(request.getParameter("hrsp"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = hrspKorrekt = false;
			}
		}
		
		if(request.getParameter("hrsi").length() != 0)
		{
			try
			{
				hrsi = Integer.parseInt(request.getParameter("hrsi"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = hrsiKorrekt = false;
			}
		}
		
		if(request.getParameter("hrun").length() != 0)
		{
			try
			{
				hrun = Integer.parseInt(request.getParameter("hrun"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = hrunKorrekt = false;
			}
		}
		if(request.getParameter("hrni").length() != 0)
		{
			try
			{
				hrni = Integer.parseInt(request.getParameter("hrni"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = hrniKorrekt = false;
			}
		}
		if(request.getParameter("hrtg").length() != 0)
		{
			try
			{
				hrtg = Integer.parseInt(request.getParameter("hrtg"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = hrtgKorrekt = false;
			}
		}
		if(request.getParameter("hrtk").length() != 0)
		{
			try
			{
				hrtk = Integer.parseInt(request.getParameter("hrtk"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = hrtkKorrekt = false;
			}
		}
		if(request.getParameter("hrpunkte").length() != 0)
		{
			try
			{
				hrpunkte = Integer.parseInt(request.getParameter("hrpunkte"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = hrpunkteKorrekt = false;
			}
		}
		
//rückrunde
		if(request.getParameter("rrsp").length() != 0)
		{
			try
			{
				rrsp = Integer.parseInt(request.getParameter("rrsp"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = rrspKorrekt = false;
			}
		}
		
		if(request.getParameter("rrsi").length() != 0)
		{
			try
			{
				rrsi = Integer.parseInt(request.getParameter("rrsi"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = rrsiKorrekt = false;
			}
		}
		
		if(request.getParameter("rrun").length() != 0)
		{
			try
			{
				rrun = Integer.parseInt(request.getParameter("rrun"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = rrunKorrekt = false;
			}
		}
		if(request.getParameter("rrni").length() != 0)
		{
			try
			{
				rrni = Integer.parseInt(request.getParameter("rrni"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = rrniKorrekt = false;
			}
		}
		if(request.getParameter("rrtg").length() != 0)
		{
			try
			{
				rrtg = Integer.parseInt(request.getParameter("rrtg"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = rrtgKorrekt = false;
			}
		}
		if(request.getParameter("rrtk").length() != 0)
		{
			try
			{
				rrtk = Integer.parseInt(request.getParameter("rrtk"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = rrtkKorrekt = false;
			}
		}
		if(request.getParameter("rrpunkte").length() != 0)
		{
			try
			{
				rrpunkte = Integer.parseInt(request.getParameter("rrpunkte"));
			}
			catch(NumberFormatException e)
			{
				vereinKorrekt = rrpunkteKorrekt = false;
			}
		}
	
		if(request.getParameter("formstring").length() != 0)
		{
				formstring = request.getParameter("formstring");
		}
	
		if(vereinKorrekt)
		{
			try
			{
				EnVerein vereinNeu = new EnVerein();
				vereinNeu.setIdVerein(vereinAlt.getIdVerein());
				if(name != null)
				{
					vereinNeu.setAtVereinName(name);
				}
				else
				{
					vereinNeu.setAtVereinName(vereinAlt.getAtVereinName());
				}
				if(kurz != null)
				{
					vereinNeu.setAtVereinKurz(kurz);
				}
				else
				{
					vereinNeu.setAtVereinKurz(vereinAlt.getAtVereinKurz());
				}
				if(regex != null)
				{
					vereinNeu.setAtVereinRegex(regex);
				}
				else
				{
					vereinNeu.setAtVereinRegex(vereinAlt.getAtVereinRegex());
				}
				if(sp != -1)
				{
					vereinNeu.setAtVereinSp(sp);
				}
				else
				{
					vereinNeu.setAtVereinSp(vereinAlt.getAtVereinSp());
				}
				if(si != -1)
				{
					vereinNeu.setAtVereinSi(si);
				}
				else
				{
					vereinNeu.setAtVereinSi(vereinAlt.getAtVereinSi());
				}
				if(un != -1)
				{
					vereinNeu.setAtVereinUn(un);
				}
				else
				{
					vereinNeu.setAtVereinUn(vereinAlt.getAtVereinUn());
				}
				if(ni != -1)
				{
					vereinNeu.setAtVereinNi(ni);
				}
				else
				{
					vereinNeu.setAtVereinNi(vereinAlt.getAtVereinNi());
				}
				if(tg != -1)
				{
					vereinNeu.setAtVereinTg(tg);
				}
				else
				{
					vereinNeu.setAtVereinTg(vereinAlt.getAtVereinTg());
				}
				if(tk != -1)
				{
					vereinNeu.setAtVereinTk(tk);
				}
				else
				{
					vereinNeu.setAtVereinTk(vereinAlt.getAtVereinTk());
				}
				if(punkte != -1)
				{
					vereinNeu.setAtVereinPunkte(punkte);
				}
				else
				{
					vereinNeu.setAtVereinPunkte(vereinAlt.getAtVereinPunkte());
				}
//heim
				if(hsp != -1)
				{
					vereinNeu.setAtVereinHSp(hsp);
				}
				else
				{
					vereinNeu.setAtVereinHSp(vereinAlt.getAtVereinHSp());
				}
				if(hsi != -1)
				{
					vereinNeu.setAtVereinHSi(hsi);
				}
				else
				{
					vereinNeu.setAtVereinHSi(vereinAlt.getAtVereinHSi());
				}
				if(hun != -1)
				{
					vereinNeu.setAtVereinHUn(hun);
				}
				else
				{
					vereinNeu.setAtVereinHUn(vereinAlt.getAtVereinHUn());
				}
				if(hni != -1)
				{
					vereinNeu.setAtVereinHNi(hni);
				}
				else
				{
					vereinNeu.setAtVereinHNi(vereinAlt.getAtVereinHNi());
				}
				if(htg != -1)
				{
					vereinNeu.setAtVereinHTg(htg);
				}
				else
				{
					vereinNeu.setAtVereinHTg(vereinAlt.getAtVereinHTg());
				}
				if(htk != -1)
				{
					vereinNeu.setAtVereinHTk(htk);
				}
				else
				{
					vereinNeu.setAtVereinHTk(vereinAlt.getAtVereinHTk());
				}
				if(hpunkte != -1)
				{
					vereinNeu.setAtVereinHPunkte(hpunkte);
				}
				else
				{
					vereinNeu.setAtVereinHPunkte(vereinAlt.getAtVereinHPunkte());
				}
				
//auswärts
				if(asp != -1)
				{
					vereinNeu.setAtVereinASp(asp);
				}
				else
				{
					vereinNeu.setAtVereinASp(vereinAlt.getAtVereinASp());
				}
				if(asi != -1)
				{
					vereinNeu.setAtVereinASi(asi);
				}
				else
				{
					vereinNeu.setAtVereinASi(vereinAlt.getAtVereinASi());
				}
				if(aun != -1)
				{
					vereinNeu.setAtVereinAUn(aun);
				}
				else
				{
					vereinNeu.setAtVereinAUn(vereinAlt.getAtVereinAUn());
				}
				if(ani != -1)
				{
					vereinNeu.setAtVereinANi(ani);
				}
				else
				{
					vereinNeu.setAtVereinANi(vereinAlt.getAtVereinANi());
				}
				if(atg != -1)
				{
					vereinNeu.setAtVereinATg(atg);
				}
				else
				{
					vereinNeu.setAtVereinATg(vereinAlt.getAtVereinATg());
				}
				if(atk != -1)
				{
					vereinNeu.setAtVereinATk(atk);
				}
				else
				{
					vereinNeu.setAtVereinATk(vereinAlt.getAtVereinATk());
				}
				if(apunkte != -1)
				{
					vereinNeu.setAtVereinAPunkte(apunkte);
				}
				else
				{
					vereinNeu.setAtVereinAPunkte(vereinAlt.getAtVereinAPunkte());
				}

//heim
				if(hrsp != -1)
				{
					vereinNeu.setAtVereinHRSp(hrsp);
				}
				else
				{
					vereinNeu.setAtVereinHRSp(vereinAlt.getAtVereinHRSp());
				}
				if(hrsi != -1)
				{
					vereinNeu.setAtVereinHRSi(hrsi);
				}
				else
				{
					vereinNeu.setAtVereinHRSi(vereinAlt.getAtVereinHRSi());
				}
				if(hrun != -1)
				{
					vereinNeu.setAtVereinHRUn(hrun);
				}
				else
				{
					vereinNeu.setAtVereinHRUn(vereinAlt.getAtVereinHRUn());
				}
				if(hrni != -1)
				{
					vereinNeu.setAtVereinHRNi(hrni);
				}
				else
				{
					vereinNeu.setAtVereinHRNi(vereinAlt.getAtVereinHRNi());
				}
				if(hrtg != -1)
				{
					vereinNeu.setAtVereinHRTg(hrtg);
				}
				else
				{
					vereinNeu.setAtVereinHRTg(vereinAlt.getAtVereinHRTg());
				}
				if(hrtk != -1)
				{
					vereinNeu.setAtVereinHRTk(hrtk);
				}
				else
				{
					vereinNeu.setAtVereinHRTk(vereinAlt.getAtVereinHRTk());
				}
				if(hrpunkte != -1)
				{
					vereinNeu.setAtVereinHRPunkte(hrpunkte);
				}
				else
				{
					vereinNeu.setAtVereinHRPunkte(vereinAlt.getAtVereinHRPunkte());
				}
		
//rückrunde
				if(rrsp != -1)
				{
					vereinNeu.setAtVereinRRSp(rrsp);
				}
				else
				{
					vereinNeu.setAtVereinRRSp(vereinAlt.getAtVereinRRSp());
				}
				if(rrsi != -1)
				{
					vereinNeu.setAtVereinRRSi(rrsi);
				}
				else
				{
					vereinNeu.setAtVereinRRSi(vereinAlt.getAtVereinRRSi());
				}
				if(rrun != -1)
				{
					vereinNeu.setAtVereinRRUn(rrun);
				}
				else
				{
					vereinNeu.setAtVereinRRUn(vereinAlt.getAtVereinRRUn());
				}
				if(rrni != -1)
				{
					vereinNeu.setAtVereinRRNi(rrni);
				}
				else
				{
					vereinNeu.setAtVereinRRNi(vereinAlt.getAtVereinRRNi());
				}
				if(rrtg != -1)
				{
					vereinNeu.setAtVereinRRTg(rrtg);
				}
				else
				{
					vereinNeu.setAtVereinRRTg(vereinAlt.getAtVereinRRTg());
				}
				if(rrtk != -1)
				{
					vereinNeu.setAtVereinRRTk(rrtk);
				}
				else
				{
					vereinNeu.setAtVereinRRTk(vereinAlt.getAtVereinRRTk());
				}
				if(rrpunkte != -1)
				{
					vereinNeu.setAtVereinRRPunkte(rrpunkte);
				}
				else
				{
					vereinNeu.setAtVereinRRPunkte(vereinAlt.getAtVereinRRPunkte());
				}
				
				if(formstring != null)
				{
					vereinNeu.setAtVereinForm(formstring);
				}
				else
				{
					vereinNeu.setAtVereinForm(vereinAlt.getAtVereinForm());
				}
				vbr.changeVerein(vereinNeu);
				vereinAlt = vereinNeu;
				session.setAttribute(ApplConstants.SAVEVEREINALT, vereinNeu);
%>
				<jsp:forward page="<%=ApplConstants.JSPVEREINVERWALTUNG%>"></jsp:forward>
<%		
			}
			catch(VereinException e)
			{
				out.print("<br><b style='color:red'>" + e.getMessage() + "</b>");
				vereinKorrekt = false;
			}
			catch(Exception e)
			{
				out.print("<br><b style='color:red'>" + e.getMessage() + "</b>");
				session.setAttribute(ApplConstants.SAVEVEREINREMOTE, null);
			}
		}
	}
	
	if(request.getParameter("loeschen") != null)
	{
		try
		{
			vbr.deleteVerein(vereinAlt.getIdVerein());
%>
			<jsp:forward page="<%=ApplConstants.JSPVEREINVERWALTUNG%>"></jsp:forward>
<%		
		}
		catch(VereinException e)
		{
			out.print("<br><b style='color:red'>" + e.getMessage() + "</b>");
			vereinKorrekt = false;
		}
		catch(Exception e)
		{
			out.print("<br><b style='color:red'>" + e.getMessage() + "</b>");
			session.setAttribute(ApplConstants.SAVEVEREINREMOTE, null);
		}
	}
%>
<table class="css_table2"><tr><td>
<table border="0" cellpadding="5">
	<tr>
		<td>VereinID:</td>
<%
		out.print("<td>" + vereinAlt.getIdVerein() + "</td>");
%>		
	</tr>
	
	<tr>
		<td>Name:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinName() + "</td>");
%>		
		<td><input type="text" name="name"
<%  
		if(nameKorrekt == false)
		{
			out.print(" value='" + request.getParameter("name") + "'");
		}
%>		
		></td>
	</tr>
	
	<tr>
		<td>Kurz:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinKurz() + "</td>");
%>		
		<td><input type="text" name="kurz" maxlength="3"
<%  
		if(kurzKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>
	
	<tr>
		<td>Logo:</td>
<%
		out.print("<td>" + "<img src='../../img/vereine/gross/" + vereinAlt.getAtVereinKurz() + ".png'>"  + "</td>");
%>		
	</tr>
	<tr>

		<td>Regex:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinRegex() + "</td>");
%>		
		<td><input type="text" name="regex" maxlength="30"
<%  
		if(regexKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>
	
</table>
</td>
<td>
<table>
	<tr class='css_weiss'>
		<td>Spiele:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinSp() + "</td>");
%>		
		<td><input type="text" name="sp" maxlength="2"
<%  
		if(spKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>

	<tr>
		<td>Siege:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinSi() + "</td>");
%>		
		<td><input type="text" name="si" maxlength="2"
<%  
		if(siKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>
	
	<tr>
		<td>Unentschieden:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinUn() + "</td>");
%>		
		<td><input type="text" name="un" maxlength="2"
<%  
		if(unKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>

	<tr>
		<td>Niederlagen:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinNi() + "</td>");
%>		
		<td><input type="text" name="ni" maxlength="2"
<%  
		if(niKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>

	<tr>
		<td>Tore+:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinTg() + "</td>");
%>		
		<td><input type="text" name="tg" maxlength="2"
<%  
		if(tgKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>
	
	<tr>
		<td>Tore-:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinTk() + "</td>");
%>		
		<td><input type="text" name="tk" maxlength="2"
<%  
		if(tkKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>

	<tr>
		<td>Punkte:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinPunkte() + "</td>");
%>		
		<td><input type="text" name="punkte" maxlength="2"
<%  
		if(punkteKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>
</table>
</td></tr>
<tr><td>	
<table>
<!-- heim	 -->
	<tr class='css_weiss'>
		<td>Heimpiele:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinHSp() + "</td>");
%>		
		<td><input type="text" name="hsp" maxlength="2"
<%  
		if(hspKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>

	<tr>
		<td>Heimsiege:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinHSi() + "</td>");
%>		
		<td><input type="text" name="hsi" maxlength="2"
<%  
		if(hsiKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>
	
	<tr>
		<td>Heimunentschieden:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinHUn() + "</td>");
%>		
		<td><input type="text" name="hun" maxlength="2"
<%  
		if(hunKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>

	<tr>
		<td>Heimniederlagen:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinHNi() + "</td>");
%>		
		<td><input type="text" name="hni" maxlength="2"
<%  
		if(hniKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>

	<tr>
		<td>Heimtore+:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinHTg() + "</td>");
%>		
		<td><input type="text" name="htg" maxlength="2"
<%  
		if(htgKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>
	
	<tr>
		<td>Heimtore-:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinHTk() + "</td>");
%>		
		<td><input type="text" name="htk" maxlength="2"
<%  
		if(htkKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>

	<tr>
		<td>Heimpunkte:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinHPunkte() + "</td>");
%>		
		<td><input type="text" name="hpunkte" maxlength="2"
<%  
		if(hpunkteKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>
</table>
</td>
<td>	
<table>		
<!-- auswärts --> 
	<tr class='css_weiss'>
		<td>Auswärtspiele:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinASp() + "</td>");
%>		
		<td><input type="text" name="asp" maxlength="2"
<%  
		if(aspKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>

	<tr>
		<td>Auswärtssiege:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinASi() + "</td>");
%>		
		<td><input type="text" name="asi" maxlength="2"
<%  
		if(asiKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>
	
	<tr>
		<td>Auswärtsunentschieden:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinAUn() + "</td>");
%>		
		<td><input type="text" name="aun" maxlength="2"
<%  
		if(aunKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>

	<tr>
		<td>Auswärtsniederlagen:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinANi() + "</td>");
%>		
		<td><input type="text" name="ani" maxlength="2"
<%  
		if(aniKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>

	<tr>
		<td>Auswärtstore+:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinATg() + "</td>");
%>		
		<td><input type="text" name="atg" maxlength="2"
<%  
		if(atgKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>
	
	<tr>
		<td>Auswärtstore-:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinATk() + "</td>");
%>		
		<td><input type="text" name="atk" maxlength="2"
<%  
		if(atkKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>

	<tr>
		<td>Auswärtspunkte:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinAPunkte() + "</td>");
%>		
		<td><input type="text" name="apunkte" maxlength="2"
<%  
		if(apunkteKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>
</table>
</td></tr>
<tr><td>	
<table>
<!-- hinrunde --> 
	<tr class='css_weiss'>
		<td>Hinrundenspiele:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinHRSp() + "</td>");
%>		
		<td><input type="text" name="hrsp" maxlength="2"
<%  
		if(hrspKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>

	<tr>
		<td>Hinrundensiege:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinHRSi() + "</td>");
%>		
		<td><input type="text" name="hrsi" maxlength="2"
<%  
		if(hrsiKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>
	
	<tr>
		<td>Hinrundenunentschieden:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinHRUn() + "</td>");
%>		
		<td><input type="text" name="hrun" maxlength="2"
<%  
		if(hrunKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>

	<tr>
		<td>Hinrundenniederlagen:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinHRNi() + "</td>");
%>		
		<td><input type="text" name="hrni" maxlength="2"
<%  
		if(hrniKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>

	<tr>
		<td>Hinrundentore+:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinHRTg() + "</td>");
%>		
		<td><input type="text" name="hrtg" maxlength="2"
<%  
		if(hrtgKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>
	
	<tr>
		<td>Hinrundentore-:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinHRTk() + "</td>");
%>		
		<td><input type="text" name="hrtk" maxlength="2"
<%  
		if(hrtkKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>

	<tr>
		<td>Hinrundenpunkte:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinHRPunkte() + "</td>");
%>		
		<td><input type="text" name="hrpunkte" maxlength="2"
<%  
		if(hrpunkteKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>
</table>
</td>
<td>	
<table>		
<!-- rückrunde --> 	
	<tr class='css_weiss'>
		<td>Rückrundenspiele:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinRRSp() + "</td>");
%>		
		<td><input type="text" name="rrsp" maxlength="2"
<%  
		if(rrspKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>

	<tr>
		<td>Rückrundensiege:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinRRSi() + "</td>");
%>		
		<td><input type="text" name="rrsi" maxlength="2"
<%  
		if(rrsiKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>
	
	<tr>
		<td>Rückrundenunentschieden:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinRRUn() + "</td>");
%>		
		<td><input type="text" name="rrun" maxlength="2"
<%  
		if(rrunKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>

	<tr>
		<td>Rückrundenniederlagen:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinRRNi() + "</td>");
%>		
		<td><input type="text" name="rrni" maxlength="2"
<%  
		if(rrniKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>

	<tr>
		<td>Rückrundentore+:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinRRTg() + "</td>");
%>		
		<td><input type="text" name="rrtg" maxlength="2"
<%  
		if(rrtgKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>
	
	<tr>
		<td>Rückrundentore-:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinRRTk() + "</td>");
%>		
		<td><input type="text" name="rrtk" maxlength="2"
<%  
		if(rrtkKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>

	<tr>
		<td>Rückrundenpunkte:</td>
<%
		out.print("<td>" + vereinAlt.getAtVereinRRPunkte() + "</td>");
%>		
		<td><input type="text" name="rrpunkte" maxlength="2"
<%  
		if(rrpunkteKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>
</table> 
</td></tr></table>
<table>
	<tr class='css_weiss'>
<%
		out.print("<td colspan='3'>" + vereinAlt.getAtVereinForm() + "</td>");
%>		
		</tr><tr><td colspan="3"><input type="text" name="formstring" size="68" maxlength="68"
<%  
		if(formKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>
</table>

<%
	if(vereinKorrekt == false)
	{
		out.print("<br>Der Verein wurde nicht geändert!");
		out.print("<br>Bitte die gelben Felder korrigieren/ergänzen!");
	}
%>
<br><br>
<input type="submit" name="speichern" value="Änderungen speichern" class="<%=ApplConstants.CSSSUBMIT2%>">
<input type="submit" name="loeschen" value="Aus Datenbank löschen" class="<%=ApplConstants.CSSSUBMIT2%>">

</form>
</body>
</html>