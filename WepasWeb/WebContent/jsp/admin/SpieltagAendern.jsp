<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*" 
		 import="java.math.BigDecimal"
         import="javax.naming.*" 
         import="de.wepas.connector.*"
         import="de.wepas.constants.*"
         import="de.wepas.jpa.*"
         import="javax.rmi.PortableRemoteObject"
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=ApplConstants.CSSFILEADMIN%>">
<title>WEPAS Spieltag ändern</title>
</head>

<body>
<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPSPIELTAGAENDERN)%> method="post">
<h1>Bestehenden WEPAS Spieltag ändern</h1>
<h3><a href="<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPSPIELTAGVERWALTUNG)%>">Zurück zur Spieltagverwaltung</a></h3>
<% 
	SpieltagBeanRemote sbr = (SpieltagBeanRemote)session.getAttribute(ApplConstants.SPIELTAG_BEAN);

	if(sbr == null)
	{
        Context ctx = new InitialContext();
		Object ref = ctx.lookup(ApplConstants.JNDISPIELTAGBEAN);
		sbr = (SpieltagBeanRemote)PortableRemoteObject.narrow(ref, SpieltagBeanRemote.class);
		session.setAttribute(ApplConstants.SPIELTAG_BEAN, sbr);
	}

	EnSpieltag spieltagAlt = null;
	String stringSpieltagnr = request.getParameter("spieltagnr");
	if(stringSpieltagnr != null)
	{
		try
		{
			spieltagAlt = sbr.getSpieltag(new Integer(stringSpieltagnr).intValue());
			session.setAttribute("SpieltagVerwaltungSpieltagAlt", spieltagAlt);
		}
		catch(SpieltagException e)
		{
			out.print("<br><b style='color:red'>" + e.getMessage() + "</b>");
		}
		catch(Exception e)
		{
			out.print("<br><b style='color:red'>" + e.getMessage() + "</b>");
			session.setAttribute(ApplConstants.SPIELTAG_BEAN, null);
		}
	}
	else
	{
		spieltagAlt = (EnSpieltag)session.getAttribute("SpieltagVerwaltungSpieltagAlt");
	}
	
	if(spieltagAlt == null && stringSpieltagnr == null)
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPSPIELTAGVERWALTUNG%>"></jsp:forward>
<%		
		return;
	}
	
	boolean spieltagKorrekt = true;
	boolean spieltagNummer = true;
	boolean spieltagText = true;
	boolean spieltagDuty = true;
	boolean spieltagPaarungen = true;
	boolean spieltagSupertipps = true;
	boolean spieltagKosten = true;
	boolean spieltagStart = true;
	boolean spieltagEnde = true;
	java.text.DateFormat formatter = new java.text.SimpleDateFormat("dd.MM.yyyy");
	
	int nummer = 0;
	String text = null;
	boolean duty = false;
	int paarungen = 0;
	int supertipps = 0;
	BigDecimal kosten = null;
	Date start = null;
	Date ende = null;
	
	if(request.getParameter("speichern") != null)
	{
		if(request.getParameter("nummer").length() != 0)
		{
			try
			{
				nummer = Integer.parseInt(request.getParameter("nummer"));
			}
			catch(NumberFormatException e)
			{
				spieltagNummer = false;
				throw new SpieltagException("<br/><b>--> Spieltagnummer ist fehlerhaft!</b><br/>" + e.getMessage());
			}
		}
		
		if(request.getParameter("text").length() != 0)
		{
			text = request.getParameter("text");
		}
		
		duty = request.getParameter("duty") != null;
		
		if(request.getParameter("paarungen").length() != 0)
		{
			try
			{
				paarungen = Integer.parseInt(request.getParameter("paarungen"));
			}
			catch(NumberFormatException e)
			{
				spieltagPaarungen = false;
				throw new SpieltagException("<br/><b>--> Anzahl Paarungen ist fehlerhaft!</b><br/>" + e.getMessage());
			}
		}
		
		if(request.getParameter("supertipps").length() != 0)
		{
			try
			{
				supertipps = Integer.parseInt(request.getParameter("supertipps"));
			}
			catch(NumberFormatException e)
			{
				spieltagSupertipps = false;
				throw new SpieltagException("<br/><b>--> Anzahl Supertipps ist fehlerhaft!</b><br/>" + e.getMessage());
			}
		}
		
		if(request.getParameter("kosten").length() != 0)
		{
			try
			{
				kosten = new BigDecimal(request.getParameter("kosten").replace(',', '.'));
				kosten.setScale(2);
			}
			catch(Exception e)
			{
				spieltagKosten = false;
				throw new SpieltagException("<br/><b>--> Kosten ist fehlerhaft!</b><br/>" + e.getMessage());
			}
		}
		
		if(request.getParameter("start").length() != 0)
		{
		try
			{
				start = formatter.parse(request.getParameter("start"));
			}
			catch(Exception e)
			{
				spieltagStart = false;
				throw new SpieltagException("<br/><b> Startdatum ist fehlerhaft!</b><br/>" + e.getMessage());
			}
		}
		
		if(request.getParameter("ende").length() != 0)
		{
			try
			{
				ende = formatter.parse(request.getParameter("ende"));
			}
			catch(Exception e)
			{
				spieltagEnde = false;
				throw new SpieltagException("<br/><b>--> Endedatum ist fehlerhaft!</b><br/>" + e.getMessage());
			}
		}
		
		if(spieltagKorrekt)
		{
			try
			{
				EnSpieltag spieltagNeu = new EnSpieltag();
				spieltagNeu.setIdSpieltag(spieltagAlt.getIdSpieltag());
				if(nummer != 0)
				{
					spieltagNeu.setAtSpieltagNummer(nummer);
				}
				else
				{
					spieltagNeu.setAtSpieltagNummer(spieltagAlt.getAtSpieltagNummer());
				}
				
				if(text != null)
				{
					spieltagNeu.setAtSpieltagText(text);
				}
				else
				{
					spieltagNeu.setAtSpieltagText(spieltagAlt.getAtSpieltagText());
				}
				
				spieltagNeu.setAtSpieltagIsDuty(duty);
				
				if(paarungen != 0)
				{
					spieltagNeu.setAtSpieltagPaarungen(paarungen);
				}
				else
				{
					spieltagNeu.setAtSpieltagPaarungen(spieltagAlt.getAtSpieltagPaarungen());
				}
				
				if(supertipps != 0)
				{
					spieltagNeu.setAtSpieltagSupertipps(supertipps);
				}
				else
				{
					spieltagNeu.setAtSpieltagSupertipps(spieltagAlt.getAtSpieltagSupertipps());
				}
				
				if(kosten != null)
				{
					spieltagNeu.setAtSpieltagKosten(kosten);	
				}
				else
				{
					spieltagNeu.setAtSpieltagKosten(spieltagAlt.getAtSpieltagKosten());
				}
				
				if(start != null)
				{
					spieltagNeu.setAtSpieltagStart(start);	
				}
				else
				{
					spieltagNeu.setAtSpieltagStart(spieltagAlt.getAtSpieltagStart());
				}
				
				if(ende != null)
				{
					spieltagNeu.setAtSpieltagEnde(ende);	
				}
				else
				{
					spieltagNeu.setAtSpieltagEnde(spieltagAlt.getAtSpieltagEnde());
				}
				
				sbr.changeSpieltag(spieltagNeu);
				spieltagAlt = spieltagNeu;
				session.setAttribute("SpieltagVerwaltungSpieltagAlt", spieltagNeu);
%>
				<jsp:forward page="<%=ApplConstants.JSPSPIELTAGVERWALTUNG%>"></jsp:forward>
<%		
			}
			catch(SpieltagException e)
			{
				out.print("<br><b style='color:red'>" + e.getMessage() + "</b>");
				spieltagKorrekt = false;
			}
			catch(Exception e)
			{
				out.print("<br><b style='color:red'>" + e.getMessage() + "</b>");
				session.setAttribute(ApplConstants.SPIELTAG_BEAN, null);
			}
		}
	}
	
	if(request.getParameter("loeschen") != null)
	{
		try
		{
			sbr.deleteSpieltag(spieltagAlt.getIdSpieltag());
%>
			<jsp:forward page="<%=ApplConstants.JSPSPIELTAGVERWALTUNG%>"></jsp:forward>
<%		
		}
		catch(SpieltagException e)
		{
			out.print("<br><b style='color:red'>" + e.getMessage() + "</b>");
			spieltagKorrekt = false;
		}
		catch(Exception e)
		{
			out.print("<br><b style='color:red'>" + e.getMessage() + "</b>");
			session.setAttribute(ApplConstants.SPIELTAG_BEAN, null);
		}
	}
%>
<table border="0" cellpadding="5">
	<tr>
		<td>SpieltagID:</td>
<%
		out.print("<td>" + spieltagAlt.getIdSpieltag() + "</td>");
%>		
	</tr>
	
	<tr>
		<td>Nummer:</td>
<%
		out.print("<td>" + spieltagAlt.getAtSpieltagNummer() + "</td>");
%>		
		<td><input type="text" name="nummer"
<%  
		if(spieltagNummer == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(spieltagKorrekt == false)
		{
			out.print(" value='" + request.getParameter("nummer") + "'");
		}
%>		
		></td>
	</tr>	
	
	<tr>
		<td>Text:</td>
<%
		out.print("<td>" + spieltagAlt.getAtSpieltagText() + "</td>");
%>		
		<td><input type="text" name="text"
<%  
		if(spieltagText == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(spieltagKorrekt == false)
		{
			out.print(" value='" + request.getParameter("text") + "'");
		}
%>		
		></td>
	</tr>
	
	<tr>
		<td>Pflicht:</td>
<%
		out.print("<td>&nbsp;</td>");
		out.print("<td align='left'><input type='checkbox' name='duty' ");
		if(spieltagAlt.getAtSpieltagIsDuty())
		{
	 		out.print("checked");
		}
		out.print("></td>");
%>		
	</tr>
	
	<tr>
		<td>Paarungen:</td>
<%
		out.print("<td>" + spieltagAlt.getAtSpieltagPaarungen() + "</td>");
%>		
		<td><input type="text" name="paarungen" maxlength="1"
<%  
		if(spieltagPaarungen == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(spieltagKorrekt == false)
		{
			out.print(" value='" + request.getParameter("paarungen") + "'");
		}
%>		
		></td>
	</tr>
	
	<tr>
		<td>Supertipps:</td>
<%
		out.print("<td>" + spieltagAlt.getAtSpieltagSupertipps() + "</td>");
%>		
		<td><input type="text" name="supertipps" maxlength="1"
<%  
		if(spieltagSupertipps == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(spieltagKorrekt == false)
		{
			out.print(" value='" + request.getParameter("supertipps") + "'");
		}
%>		
		></td>
	</tr>
	
	<tr>
		<td>Kosten:</td>
<%
		out.print("<td>" + spieltagAlt.getAtSpieltagKosten().toString().replace('.', ',') + "</td>");
%>		
		<td><input type="text" name="kosten" maxlength="4"
<%  
		if(spieltagKosten == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(spieltagKorrekt == false)
		{
			out.print(" value='" + request.getParameter("kosten") + "'");
		}
%>		
		></td>
	</tr>
	
	<tr>
		<td>Start:</td>
<%
		out.print("<td align='right'>" + formatter.format(spieltagAlt.getAtSpieltagStart()) + "</td>");
%>		
		<td><input type="text" name="start" maxlength="10"
<%  
		if(spieltagStart == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(spieltagKorrekt == false)
		{
			out.print(" value='" + request.getParameter("start") + "'");
		}
%>		
		></td>
	</tr>
	
	<tr>
		<td>Ende:</td>
<%
		out.print("<td align='right'>" + formatter.format(spieltagAlt.getAtSpieltagEnde()) + "</td>");
%>		
		<td><input type="text" name="ende" maxlength="10"
<%  
		if(spieltagEnde == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(spieltagKorrekt == false)
		{
			out.print(" value='" + request.getParameter("ende") + "'");
		}
%>		
		></td>
	</tr>
</table>
<%
	if(spieltagKorrekt == false)
	{
		out.print("<br>Der Spieltag wurde nicht geändert!");
		out.print("<br>Bitte die gelben Felder korrigieren/ergänzen!");
	}
%>
<br><br>
<input type="submit" name="speichern" value="Änderungen speichern" class="<%=ApplConstants.CSSSUBMIT2%>">
<input type="submit" name="loeschen" value="Aus Datenbank löschen" class="<%=ApplConstants.CSSSUBMIT2%>">
</form>
</body>
</html>