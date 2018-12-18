<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.*" 
         import="javax.naming.*" 
         import="de.wepas.connector.*"
         import="de.wepas.constants.*"
         import="de.wepas.jpa.*"
         import="javax.rmi.PortableRemoteObject"
  
%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=ApplConstants.CSSFILEADMIN%>">
<title>WEPAS Spieltag Verwaltung</title>
</head>
<body>
<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPSPIELTAGVERWALTUNG)%> method="post">
<%
	if(request.getParameter("adminnavi") != null)
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPADMINNAVI%>"></jsp:forward>
<%		
	}
%>
<h1>WEPAS Spieltag verwalten</h1>
<input type='submit' name='adminnavi' value='Zurück' class='<%=ApplConstants.CSSSUBMIT2%>'>
<%
	SpieltagBeanRemote spieltagStateless = (SpieltagBeanRemote)session.getAttribute(ApplConstants.SPIELTAG_BEAN);
	java.text.DateFormat formatter = new java.text.SimpleDateFormat("dd.MM.yyyy");

	if(spieltagStateless == null)
	{
        Context ctx = new InitialContext();
		Object ref = ctx.lookup(ApplConstants.JNDISPIELTAGBEAN);
		spieltagStateless = (SpieltagBeanRemote)PortableRemoteObject.narrow(ref, SpieltagBeanRemote.class);
		session.setAttribute(ApplConstants.SPIELTAG_BEAN, spieltagStateless);
	}

	Vector<EnSpieltag> liste = null;
	
	try
	{
		EnSpieltag spieltagarray[] = spieltagStateless.getSpieltag();
		liste = new Vector<EnSpieltag>(spieltagarray.length);
		for(int i = 0; i < spieltagarray.length; i++)
		{
			liste.add(spieltagarray[i]);
		}

		Iterator<EnSpieltag> iter = liste.iterator();
		
		if(iter.hasNext())
		{
%>
			<h3>Liste der vorhandenen Spieltage</h3>
			<table border="2" cellpadding="5">
				<tr>
					<th>ID</th>
					<th>Nr</th>
					<th>Spieltag</th>
					<th>Pflicht</th>
					<th>Paarungen</th>
					<th>Supertipps</th>
					<th>Kosten</th>
					<th>Einsatz</th>
					<th>Start</th>
					<th>Ende</th>
					<th>&nbsp;</th>
					<th>&nbsp;</th>
<!-- 					<th>&nbsp;</th> -->
				</tr>
<%
			while(iter.hasNext())
			{
				EnSpieltag spieltag = (EnSpieltag) iter.next();
				out.print("<tr>");
				out.print("<td align='right'>" + spieltag.getIdSpieltag() + "</td>");
				out.print("<td align='right'>" + spieltag.getAtSpieltagNummer() + "</td>");
				out.print("<td align='left'>" + spieltag.getAtSpieltagText() + "</td>");
				out.print("<td align='center'><input type='checkbox' name='duty' disabled ");
				if(spieltag.getAtSpieltagIsDuty())
				{
					 out.print("checked");
				}
				out.print(">");
				out.print("<td align='center'>" + spieltag.getAtSpieltagPaarungen() + "</td>");
				out.print("<td align='center'>" + spieltag.getAtSpieltagSupertipps() + "</td>");
				out.print("<td align='right'>" + spieltag.getAtSpieltagKosten().toString().replace('.', ',') + "</td>");
				out.print("<td align='right'>" + spieltag.getAtSpieltagEinsatz().toString().replace('.', ',') + "</td>");
				out.print("<td align='right'>" + formatter.format(spieltag.getAtSpieltagStart()) + "</td>");
				out.print("<td align='right'>" + formatter.format(spieltag.getAtSpieltagEnde()) + "</td>");
				out.print("<td align='center'><a href='/WepasWeb" + ApplConstants.JSPSPIELTAGAENDERN + "?spieltagnr=" + spieltag.getIdSpieltag() 
						+ "'><img src='../../img/links/stadion.png' width='40' height='40' border='0'></a></td>");
				out.print("<td align='center'><a href='/WepasWeb" + ApplConstants.JSPSPIELTAGSPIELENEU + "?spieltagnr=" + spieltag.getIdSpieltag() 
						+ "'><img src='../../img/links/spiel.png' width='40' height='40' border='0'></a></td>");
// 				out.print("<td align='center'><a href='/WepasWeb" + ApplConstants.JSPERGEBNISPFLEGEN + "?spieltagnr=" + spieltag.getIdSpieltag() 
// 						+ "'><img src='../../img/links/ball.png' width='40' height='40' border='0'></a></td>");	
				out.print("</tr>");
			}
%> 
			</table>
<%		
		}
		else
		{
%>
			<h3>Es sind noch keine Spieltage erfaßt!</h3>
<%
		}
	}
	catch(Exception e)
	{
		out.print("<br><b style='color:red'>" + e.getMessage() + "</b>");
		session.setAttribute(ApplConstants.SPIELTAG_BEAN, null);
	}
%>
	<br><br><h3><a href="/WepasWeb<%=ApplConstants.JSPSPIELTAGNEU%>">Neuen Spieltag anlegen</a></h3>
</form>
</body>
</html>