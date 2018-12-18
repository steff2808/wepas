<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<title>WEPAS Spieltag Neuanlage</title>
</head>

<body>
<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPSPIELTAGNEU)%> method="post">
<h1>Neuen WEPAS-Spieltag anlegen</h1>
<h3><a href="<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPSPIELTAGVERWALTUNG)%>">Zurück zur Verwaltung</a></h3>
<%
	//TODO: Anstosszeiten einfügen	

	SpieltagBeanRemote sbr = (SpieltagBeanRemote)session.getAttribute(ApplConstants.SPIELTAG_BEAN);
	if(sbr == null)
	{
        Context ctx = new InitialContext();
		Object ref = ctx.lookup(ApplConstants.JNDISPIELTAGBEAN);
		sbr = (SpieltagBeanRemote)PortableRemoteObject.narrow(ref, SpieltagBeanRemote.class);
		session.setAttribute(ApplConstants.SPIELTAG_BEAN, sbr);
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
	
	if(request.getParameter("speichern") != null)
	{
		try
		{
			EnSpieltag spieltag = new EnSpieltag();
			try
			{
				spieltag.setAtSpieltagNummer(Integer.parseInt(request.getParameter("nummer")));
			}
			catch(NumberFormatException e)
			{
				spieltagNummer = false;
				throw new SpieltagException("<br/><b>--> Spieltagnummer ist fehlerhaft!</b><br/>" + e.getMessage());
			}
			spieltag.setAtSpieltagText(request.getParameter("text"));
			spieltag.setAtSpieltagIsDuty(request.getParameter("duty") != null);
			try
			{
				spieltag.setAtSpieltagPaarungen(Integer.parseInt(request.getParameter("paarungen")));
			}
			catch(NumberFormatException e)
			{
				spieltagPaarungen = false;
				throw new SpieltagException("<br/><b>--> Anzahl Paarungen ist fehlerhaft!</b><br/>" + e.getMessage());
			}
			try
			{
				spieltag.setAtSpieltagSupertipps(Integer.parseInt(request.getParameter("supertipps")));
			}
			catch(NumberFormatException e)
			{
				spieltagSupertipps = false;
				throw new SpieltagException("<br/><b>--> Anzahl Supertipps ist fehlerhaft!</b><br/>" + e.getMessage());
			}
			try
			{
				BigDecimal bd = new BigDecimal(request.getParameter("kosten").replace(',', '.'));
				bd.setScale(2);
				spieltag.setAtSpieltagKosten(bd);
			}
			catch(Exception e)
			{
				spieltagKosten = false;
				throw new SpieltagException("<br/><b>--> Kosten ist fehlerhaft!</b><br/>" + e.getMessage());
			}
			try
			{
				Date date = formatter.parse(request.getParameter("start"));
				spieltag.setAtSpieltagStart(date);
			}
			catch(Exception e)
			{
				spieltagStart = false;
				throw new SpieltagException("<br/><b>--> Startdatum ist fehlerhaft!</b><br/>" + e.getMessage());
			}
			try
			{
				Date date = formatter.parse(request.getParameter("ende"));
				spieltag.setAtSpieltagEnde(date);
			}
			catch(Exception e)
			{
				spieltagEnde = false;
				throw new SpieltagException("<br/><b>--> Endedatum ist fehlerhaft!</b><br/>" + e.getMessage());
			}
			sbr.addSpieltag(spieltag);
%>
			<jsp:forward page="<%=ApplConstants.JSPSPIELTAGVERWALTUNG%>"></jsp:forward>
<%		
		}
		catch(SpieltagException e)
		{
			spieltagKorrekt = false;
			out.print("<br><b style='color:red'>SpieltagException:<br/>" + e.toString() + "</b><br/>" + e.getStackTrace());
		}
		catch(Exception e)
		{
			spieltagKorrekt = false;
			out.print("<br><b style='color:red'>Exception: " + e.toString() + "<br/>" + "</b><br/>" + e.getStackTrace());
			session.setAttribute(ApplConstants.SPIELTAG_BEAN, null);
		}
	}
%>
<table border="0" cellpadding="5">
	<tr>
		<td>Nummer:</td>
		<td><input type="text" name="nummer" maxlength="2"
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
		<td><input type="text" name="text" maxlength="15"
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
		<td><input type="checkbox" name="duty" checked
	
		></td>
	</tr>
	<tr>
		<td>Paarungen:</td>
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
		<td><input type="text" name="kosten" maxlength="5"
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
		out.print("<br>Der Spieltag wurde nicht gespeichert!");
		out.print("<br>Bitte korrigieren/ergänzen Sie die gelben Felder!");
	}
%>
<br><br><input type="submit" name="speichern" value="Speichern" class="<%=ApplConstants.CSSSUBMIT2%>">
</form>
</body>
</html>