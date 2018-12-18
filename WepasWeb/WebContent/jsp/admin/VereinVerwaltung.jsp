<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.Vector" 
		 import="java.util.Iterator"
         import="de.wepas.connector.VereinBeanRemote"
         import="de.wepas.constants.ApplConstants"
         import="de.wepas.jpa.EnVerein"
         import="util.LookupRemoteService"
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=ApplConstants.CSSFILEADMIN%>">
<title>WEPAS Verein Verwaltung</title>
</head>

<body>
<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPVEREINVERWALTUNG)%> method="post">
<%
	if(request.getParameter("adminnavi") != null)
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPADMINNAVI%>"></jsp:forward>
<%		
	}
%>
<h1>WEPAS Verein verwalten</h1>
<input type='submit' name='adminnavi' value='Zurück' class='<%=ApplConstants.CSSSUBMIT2%>'>
<h3>Liste der vorhandenen Vereine</h3>
<table border="2" cellpadding="5">
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Kurz</th>
		<th>Logo</th>
		<th>Regex</th>
		<th>Spiele</th>
		<th>Si</th>
		<th>Un</th>
		<th>Ni</th>
		<th>Tore+</th>
		<th>Tore-</th>
		<th>Punkte</th>
		<th>&nbsp;</th>
	</tr>
<% 
	VereinBeanRemote vbr = (VereinBeanRemote)session.getAttribute(ApplConstants.SAVEVEREINREMOTE);
	if(vbr == null)
	{
		vbr = LookupRemoteService.lookupVereinBeanRemote();
		session.setAttribute(ApplConstants.SAVEVEREINREMOTE, vbr);
	}

	Vector<EnVerein> liste = null;
	
	try
	{
		EnVerein vereinarray[] = vbr.getVerein();
		liste = new Vector<EnVerein>(vereinarray.length);
		for(int i = 0; i < vereinarray.length; i++)
		{
			liste.add(vereinarray[i]); 
		}
		Iterator<EnVerein> iter = liste.iterator();
		while(iter.hasNext())
		{
			EnVerein verein = (EnVerein) iter.next();
			out.print("<tr>");
			out.print("<td align='right'>" + verein.getIdVerein() + "</td>");
			out.print("<td align='left'>" + verein.getAtVereinName() + "</td>");
			out.print("<td align='left'>" + verein.getAtVereinKurz() + "</td>");
			out.print("<td align='center'><img src='../../img/vereine/klein/" 
				+ verein.getAtVereinKurz() + ".png' width='40' height='27' border='0'></a></td>");
			out.print("<td align='left'>" + verein.getAtVereinRegex() + "</td>");
			out.print("<td align='right'>" + verein.getAtVereinSp() + "</td>");
			out.print("<td align='right'>" + verein.getAtVereinSi() + "</td>");
			out.print("<td align='right'>" + verein.getAtVereinUn() + "</td>");
			out.print("<td align='right'>" + verein.getAtVereinNi() + "</td>");
			out.print("<td align='right'>" + verein.getAtVereinTg() + "</td>");
			out.print("<td align='right'>" + verein.getAtVereinTk() + "</td>");
			out.print("<td align='right'>" + verein.getAtVereinPunkte() + "</td>");
			out.print("<td align='center'><a href='/WepasWeb" + ApplConstants.JSPVEREINAENDERN + "?vereinnr=" + verein.getIdVerein() 
					+ "'><img src='../../img/links/lupe.gif' width='24' height='24' border='0'></a></td>");
			out.print("</tr>");	
			out.print("<tr>");
			out.print("<td>&nbsp;</td>");
			out.print("<td colspan='12' align='left'>" + verein.getAtVereinForm() + "</td>");
			out.print("</tr>");
		}
	}
	catch(Exception e)
	{
		out.print("<br><b style='color:red'>" + e.getMessage() + "</b>");
		session.setAttribute(ApplConstants.SAVEVEREINREMOTE, null);
	}
%>
</table>
<br><br><h3><a href="/WepasWeb<%=ApplConstants.JSPVEREINNEU%>">Neuen Verein anlegen</a></h3>
</form>
</body>
</html>