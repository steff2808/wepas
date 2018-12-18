<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page 
         import="de.wepas.connector.VereinBeanRemote"
         import="de.wepas.connector.VereinException"
         import="de.wepas.constants.ApplConstants"
         import="de.wepas.jpa.EnVerein"
         import="util.LookupRemoteService"
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=ApplConstants.CSSFILEADMIN%>">
<title>WEPAS Verein Neuanlage</title>
</head> 

<body>
<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPVEREINNEU)%> method="post">
<h1>Neuen WEPAS-Verein anlegen</h1>
<h3><a href="<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPVEREINVERWALTUNG)%>">Zurück zur Verwaltung</a></h3>
<%
	VereinBeanRemote vbr = (VereinBeanRemote)session.getAttribute(ApplConstants.SAVEVEREINREMOTE);
	if(vbr == null)
	{
		vbr = LookupRemoteService.lookupVereinBeanRemote();
		session.setAttribute(ApplConstants.SAVEVEREINREMOTE, vbr);
	}

	boolean vereinKorrekt = true;
	boolean vereinName = true;
	boolean vereinKurz = true;

	
	if(request.getParameter("speichern") != null)
	{
		try
		{
			EnVerein verein = new EnVerein();
			verein.setAtVereinName(request.getParameter("name"));
			verein.setAtVereinKurz(request.getParameter("kurz"));
			verein.setAtVereinSi(0);
			verein.setAtVereinUn(0);
			verein.setAtVereinNi(0);
			verein.setAtVereinTg(0);
			verein.setAtVereinTk(0);
			verein.setAtVereinPunkte(0);
			verein.setAtVereinForm("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			vbr.addVerein(verein);
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
<table border="0" cellpadding="5">
	<tr>
		<td>Name:</td>
		<td><input type="text" name="name" maxlength="30"
<%  
		if(vereinName == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(vereinKorrekt == false)
		{
			out.print(" value='" + request.getParameter("name") + "'");
		}
%>		
		></td>
	</tr>
	<tr>
		<td>Kurz:</td>
		<td><input type="text" name="kurz" maxlength="3"
<%  
		if(vereinKurz == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(vereinKorrekt == false)
		{
			out.print(" value='" + request.getParameter("kurz") + "'");
		}
%>		
		></td>
	</tr>
</table>
<%
	if(vereinKorrekt == false)
	{
		out.print("<br>Der Verein wurde nicht gespeichert!");
		out.print("<br>Bitte die gelben Felder korrigieren/ergänzen!");
	}
%>
<br><br><input type="submit" name="speichern" value="Speichern" class="<%=ApplConstants.CSSSUBMIT2%>">
</form>
</body>
</html>