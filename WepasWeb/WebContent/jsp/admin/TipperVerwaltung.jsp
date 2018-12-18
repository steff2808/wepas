<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page 
		 import="java.util.Vector" 
		 import="java.util.Iterator"
         import="de.wepas.connector.TipperBeanRemote"
         import="de.wepas.constants.ApplConstants"
         import="de.wepas.jpa.EnTipper"
         import="util.LookupRemoteService"
         import="javax.rmi.PortableRemoteObject"
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=ApplConstants.CSSFILEADMIN%>">
<title>WEPAS Tipper Verwaltung</title>
</head>

<body>
<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPTIPPERVERWALTUNG)%> method="post">
<%
	if(request.getParameter("adminnavi") != null)
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPADMINNAVI%>"></jsp:forward>
<%		
	}
%>
<h1>WEPAS Tipper verwalten</h1>
<input type='submit' name='adminnavi' value='Zurück' class='<%=ApplConstants.CSSSUBMIT2%>'>
<h3>Liste der vorhandenen Tipper</h3>
<table border="2" cellpadding="5">
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Vorname</th>
		<th>Login</th>
		<th>Passwort</th>
		<th>Siege</th>
		<th>Treppchen</th>
		<th>Team</th>
		<th>Fraktion</th>
		<th>Geld Pflicht/Frei</th>
		<th>&nbsp;</th>
	</tr>
<%
	TipperBeanRemote tbr = (TipperBeanRemote)session.getAttribute(ApplConstants.SAVETIPPERREMOTE);
	if(tbr == null)
	{
		tbr = LookupRemoteService.lookupTipperBeanRemote();
		session.setAttribute(ApplConstants.SAVETIPPERREMOTE, tbr);
	}

	Vector<EnTipper> liste = null;
	
	try
	{
		EnTipper[] tipperarray = tbr.getTipper();
		liste = new Vector<EnTipper>(tipperarray.length);
		for(int i = 0; i < tipperarray.length; i++)
		{
			liste.add(tipperarray[i]);
		}
		Iterator<EnTipper> iter = liste.iterator();
		while(iter.hasNext())
		{
			EnTipper tipper = (EnTipper) iter.next();
			out.print("<tr>");
			out.print("<td align='left'><b>" + tipper.getIdTipper() + "</b></td>");
			out.print("<td align='left'><b>" + tipper.getAtTipperName() + "</b></td>");
			out.print("<td align='left'><b>" + tipper.getAtTipperVorname() + "</b></td>");
			out.print("<td align='left'>" + tipper.getAtTipperLogin() + "</td>");
			out.print("<td align='left'>" + tipper.getAtTipperPassword() + "</td>");
			out.print("<td align='right'>" + tipper.getAtTipperSiege() + "</td>");
			out.print("<td align='left'>" + tipper.getAtTipperTeam() + "</td>");
			out.print("<td align='center'><a href='/WepasWeb"+ ApplConstants.JSPTIPPERAENDERN + "?tippernr=" + tipper.getIdTipper() 
					+ "'><img src='../../img/links/lupe.gif' width='24' height='24' border='0'></a></td>");
			out.print("</tr>");

			out.print("<tr>");
			out.print("<td>&nbsp;</td>");
			out.print("<td colspan='2'  align='left'>" + tipper.getAtTipperMail() + "</td>");
			out.print("<td colspan='2'  align='left'>" + tipper.getAtTipperTel() + "</td>");
			out.print("<td colspan='2'  align='left'>" + tipper.getAtTipperMobil() + "</td>");
			out.print("<td colspan='3'  align='left'>" + tipper.getAtTipperIban() + "</td>");
			out.print("<td>&nbsp;</td>");
			out.print("</tr>");			
			
			out.print("<tr>");
			out.print("<td>&nbsp;</td>");
			out.print("<td colspan='10' align='left'>" + tipper.getAtTipperForm() + "</td>");
			out.print("</tr>");			
		}
	}
	catch(Exception e)
	{
		out.print("<br><b style='color:red'>" + e.getMessage() + "</b>");
		session.setAttribute(ApplConstants.SAVETIPPERREMOTE, null);
	}
%>
</table>
<br><br><h3><a href="/WepasWeb<%=ApplConstants.JSPTIPPERNEU%>">Neuen Tipper anlegen</a></h3>
</form>
</body>
</html>