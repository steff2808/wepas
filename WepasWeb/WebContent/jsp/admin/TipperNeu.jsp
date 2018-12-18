<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page 
	     import="de.wepas.connector.TipperBeanRemote"
	     import="de.wepas.connector.TipperException"
         import="de.wepas.constants.ApplConstants"
         import="de.wepas.jpa.EnTipper"
         import="util.LookupRemoteService"
         import="javax.rmi.PortableRemoteObject"
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=ApplConstants.CSSFILEADMIN%>">
<title>WEPAS Tipper Neuanlage</title>
</head>

<body>
<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPTIPPERNEU)%> method="post">
<h1>Neuen WEPAS-Tipper anlegen</h1>
<h3><a href="<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPTIPPERVERWALTUNG)%>">Zurück zur Verwaltung</a></h3>
<%
	TipperBeanRemote tbr = (TipperBeanRemote)session.getAttribute(ApplConstants.SAVETIPPERREMOTE);
	if(tbr == null)
	{
		tbr = LookupRemoteService.lookupTipperBeanRemote();
		session.setAttribute(ApplConstants.SAVETIPPERREMOTE, tbr);
	}

	boolean tipperKorrekt = true;
	boolean tipperVorname = true;
	boolean tipperName = true;
	boolean tipperLogin = true;
	boolean tipperPassword = true;
	boolean tipperTeam = true;
	boolean tipperFraktion = true;
	boolean tipperMail = true;
	boolean tipperTel = true;
	boolean tipperMobil = true;
	boolean tipperIban = true;
	
	if(request.getParameter("speichern") != null)
	{
		try
		{
			EnTipper tipper = new EnTipper();
			tipper.setAtTipperVorname(request.getParameter("vorname"));
			tipper.setAtTipperName(request.getParameter("name"));
			tipper.setAtTipperLogin(request.getParameter("login"));
			tipper.setAtTipperPassword(request.getParameter("password"));
			tipper.setAtTipperTeam(request.getParameter("team"));
			tipper.setAtTipperMail(request.getParameter("mail"));
			tipper.setAtTipperTel(request.getParameter("tel"));
			tipper.setAtTipperMobil(request.getParameter("mobil"));
			tipper.setAtTipperIban(request.getParameter("iban"));
			tipper.setAtTipperForm("0000000000000000000000000000000000000000000000000000000000000000000000");
			tbr.addTipper(tipper);
%>
			<jsp:forward page="<%=ApplConstants.JSPTIPPERVERWALTUNG%>"></jsp:forward>
<%		
		}
		catch(TipperException e)
		{
			out.print("<br><b style='color:red'>" + e.getMessage() + "</b>");
			tipperKorrekt = false;
		}
		catch(Exception e)
		{
			System.out.println("--> Exception in " + ApplConstants.JSPTIPPERNEU);
			e.printStackTrace();
			out.print("<br><b style='color:red'>" + e.getMessage() + "</b>");
			session.setAttribute(ApplConstants.SAVETIPPERREMOTE, null);
		}
	}
%>
<table border="0" cellpadding="5">
	<tr>
		<td>Name:</td>
		<td><input type="text" name="name" maxlength="10"
<%  
		if(tipperName == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(tipperKorrekt == false)
		{
			out.print(" value='" + request.getParameter("name") + "'");
		}
%>		
		></td>
	</tr>
	<tr>
		<td>Vorname:</td>
		<td><input type="text" name="vorname" maxlength="10"
<%  
		if(tipperVorname == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(tipperKorrekt == false)
		{
			out.print(" value='" + request.getParameter("vorname") + "'");
		}
%>		
		></td>
	</tr>
	<tr>
		<td>Login:</td>
		<td><input type="text" name="login" maxlength="10"
<%  
		if(tipperLogin == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(tipperKorrekt == false)
		{
			out.print(" value='" + request.getParameter("login") + "'");
		}
%>		
		></td>
	</tr>
	<tr>
		<td>Password:</td>
		<td><input type="text" name="password" maxlength="10"
<%  
		if(tipperPassword == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(tipperKorrekt == false)
		{
			out.print(" value='" + request.getParameter("password") + "'");
		}
%>		
		></td>
	</tr>
	<tr>
		<td>Team:</td>
		<td><input type="text" name="team" maxlength="2"
<%  
		if(tipperTeam == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(tipperKorrekt == false)
		{
			out.print(" value='" + request.getParameter("team") + "'");
		}
%>		
		></td>
	</tr>
	<tr>
		<td>E-Mail:</td>
		<td><input type="text" name="mail" maxlength="30"
<%  
		if(tipperMail == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(tipperKorrekt == false)
		{
			out.print(" value='" + request.getParameter("mail") + "'");
		}
%>		
		></td>
	</tr>	
	<tr>
		<td>Telefon:</td>
		<td><input type="text" name="tel" maxlength="20"
<%  
		if(tipperTel == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(tipperKorrekt == false)
		{
			out.print(" value='" + request.getParameter("tel") + "'");
		}
%>		
		></td>
	</tr>
	<tr>
		<td>Mobil:</td>
		<td><input type="text" name="mobil" maxlength="20"
<%  
		if(tipperMobil == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(tipperKorrekt == false)
		{
			out.print(" value='" + request.getParameter("mobil") + "'");
		}
%>		
		></td>
	</tr>	
	<tr>
		<td>Iban:</td>
		<td><input type="text" name="iban" maxlength="22"
<%  
		if(tipperIban == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(tipperKorrekt == false)
		{
			out.print(" value='" + request.getParameter("iban") + "'");
		}
%>		
		></td>
	</tr>	
	
	
</table>
<%
	if(tipperKorrekt == false)
	{
		out.print("<br>Der Tipper wurde nicht gespeichert!");
		out.print("<br>Bitte die gelben Felder korrigieren/ergänzen!");
	}
%>
<br><br><input type="submit" name="speichern" value="Speichern" class="<%=ApplConstants.CSSSUBMIT2%>">
</form>
</body>
</html>