<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page 
		 import="java.math.BigDecimal"
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
<title>WEPAS Tipper ändern</title>
</head>

<body>
<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPTIPPERAENDERN)%> method="post">
<h1>Bestehenden WEPAS Tipper ändern</h1>
<h3><a href="<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPTIPPERVERWALTUNG)%>">Zurück zur Tipperverwaltung</a></h3>
<% 
	TipperBeanRemote tbr = (TipperBeanRemote)session.getAttribute(ApplConstants.SAVETIPPERREMOTE);
	if(tbr == null)
	{
		tbr = LookupRemoteService.lookupTipperBeanRemote();
		session.setAttribute(ApplConstants.SAVETIPPERREMOTE, tbr);
	}

	EnTipper tipperAlt = null;
	String stringTippernr = request.getParameter(ApplConstants.SAVETIPPERNR);
	if(stringTippernr != null)
	{
		try
		{
			tipperAlt = tbr.getTipper(new Integer(stringTippernr).intValue());
			session.setAttribute(ApplConstants.SAVETIPPERALT, tipperAlt);
		}
		catch(TipperException e)
		{
			out.print("<br><b style='color:red'>" + e.getMessage() + "</b>");
		}
		catch(Exception e)
		{
			System.out.println("--> Exception in " + ApplConstants.JSPTIPPERAENDERN);
			e.printStackTrace();
			out.print("<br><b style='color:red'>" + e.getMessage() + "</b>");
			session.setAttribute(ApplConstants.SAVETIPPERREMOTE, null);
		}
	}
	else
	{
		tipperAlt = (EnTipper)session.getAttribute(ApplConstants.SAVETIPPERALT);
	}
	
	if(tipperAlt == null && stringTippernr == null)
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPTIPPERVERWALTUNG%>"></jsp:forward>
<%		
		return;
	}
	boolean tipperKorrekt = true;
	boolean nameKorrekt = true;
	boolean vornameKorrekt = true;
	boolean loginKorrekt = true;
	boolean passwordKorrekt = true;
	boolean kontoKorrekt = true;
	boolean kontofKorrekt = true;
	boolean teamKorrekt = true;
	boolean mailKorrekt = true;
	boolean telKorrekt = true;
	boolean mobilKorrekt = true;
	boolean ibanKorrekt = true;
	boolean formKorrekt = true;
	
	String name = null;
	String vorname = null;
	String login = null;
	String password = null;
	Double konto = 1000.0;
	String team = null;
	String mail = null;
	String tel = null;
	String mobil = null;
	String iban = null;
	String formstring = null;
	//BigDecimal preis = null;
	//Integer lagermenge = null;
	
	if(request.getParameter("speichern") != null)
	{
		if(request.getParameter("name").length() != 0)
		{
			name = request.getParameter("name");
		}
		
		if(request.getParameter("vorname").length() != 0)
		{
			vorname = request.getParameter("vorname");
			//try
			//{
			//	preis = new BigDecimal(request.getParameter("preis"));
			//}
			//catch(NumberFormatException e)
			//{
			//	tipperKorrekt = preisKorrekt = false;
			//}
		}
		
		if(request.getParameter("login").length() != 0)
		{
			login = request.getParameter("login");
			//try
			//{
			//	lagermenge = new Integer(request.getParameter("lagermenge"));
			//}
			//catch(NumberFormatException e)
			//{
			//	tipperKorrekt = lagermengeKorrekt = false;
			//}
		}
		if(request.getParameter("konto").length() != 0)
		{
//			konto = request.getParameter("konto");
			try
			{
				konto = Double.parseDouble(request.getParameter("konto").replace(',', '.'));
			}
			catch(NumberFormatException e)
			{
				kontoKorrekt = tipperKorrekt = false;
			}
		}

		if(request.getParameter("password").length() != 0)
		{
			password = request.getParameter("password");
		}
		
		if(request.getParameter("team").length() != 0)
		{
			team = request.getParameter("team");
		}

		if(request.getParameter("mail").length() != 0)
		{
			mail = request.getParameter("mail");
		}

		if(request.getParameter("tel").length() != 0)
		{
			tel = request.getParameter("tel");
		}

		if(request.getParameter("mobil").length() != 0)
		{
			mobil = request.getParameter("mobil");
		}

		if(request.getParameter("iban").length() != 0)
		{
			iban = request.getParameter("iban");
		}

		if(request.getParameter("formstring").length() != 0)
		{
			System.out.println("TipperAendern.jsp-->073");
			formstring = request.getParameter("formstring");
			System.out.println("TipperAendern.jsp-->074");
		}
		if(tipperKorrekt)
		{
			try
			{
				EnTipper tipperNeu = new EnTipper();
				tipperNeu.setIdTipper(tipperAlt.getIdTipper());
				tipperNeu.setAtTipperPunkte(tipperAlt.getAtTipperPunkte());
				tipperNeu.setAtTipperSiege(tipperAlt.getAtTipperSiege());
				if(name != null)
				{
					tipperNeu.setAtTipperName(name);
				}
				else
				{
					tipperNeu.setAtTipperName(tipperAlt.getAtTipperName());
				}
				if(vorname != null)
				{
					tipperNeu.setAtTipperVorname(vorname);
				}
				else
				{
					tipperNeu.setAtTipperVorname(tipperAlt.getAtTipperVorname());
				}
				if(login != null)
				{
					tipperNeu.setAtTipperLogin(login);
				}
				else
				{
					tipperNeu.setAtTipperLogin(tipperAlt.getAtTipperLogin());
				}
				if(password != null)
				{
					tipperNeu.setAtTipperPassword(password);
				}
				else
				{
					tipperNeu.setAtTipperPassword(tipperAlt.getAtTipperPassword());
				}
				if(konto < 1000.0)
				{
					tipperNeu.setAtTipperKonto(new BigDecimal(konto));	
				}
				else
				{
					tipperNeu.setAtTipperKonto(tipperAlt.getAtTipperKonto());
				}
				if(login != null)
				{
					tipperNeu.setAtTipperLogin(login);
				}
				else
				{
					tipperNeu.setAtTipperLogin(tipperAlt.getAtTipperLogin());
				}
				if(team != null)
				{
					tipperNeu.setAtTipperTeam(team);
				}
				else
				{
					tipperNeu.setAtTipperTeam(tipperAlt.getAtTipperTeam());
				}

				if(mail != null)
				{
					tipperNeu.setAtTipperMail(mail);
				}
				else
				{
					tipperNeu.setAtTipperMail(tipperAlt.getAtTipperMail());
				}
				
				if(tel != null)
				{
					tipperNeu.setAtTipperTel(tel);
				}
				else
				{
					tipperNeu.setAtTipperTel(tipperAlt.getAtTipperTel());
				}
				
				if(mobil != null)
				{
					tipperNeu.setAtTipperMobil(mobil);
				}
				else
				{
					tipperNeu.setAtTipperMobil(tipperAlt.getAtTipperMobil());
				}
				
				if(iban != null)
				{
					tipperNeu.setAtTipperIban(iban);
				}
				else
				{
					tipperNeu.setAtTipperIban(tipperAlt.getAtTipperIban());
				}
				
				if(formstring != null)
				{
					tipperNeu.setAtTipperForm(formstring);
				}
				else
				{
					tipperNeu.setAtTipperForm(tipperAlt.getAtTipperForm());
				}
				tbr.changeTipper(tipperNeu);
				tipperAlt = tipperNeu;
				session.setAttribute(ApplConstants.SAVETIPPERALT, tipperNeu);
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
				System.out.println("--> Exception in " + ApplConstants.JSPTIPPERAENDERN);
				e.printStackTrace();
				out.print("<br><b style='color:red'>" + e.getMessage() + "</b>");
				session.setAttribute(ApplConstants.SAVETIPPERREMOTE, null);
			}
		}
	}
	
	if(request.getParameter("loeschen") != null)
	{
		try
		{
			tbr.deleteTipper(tipperAlt.getIdTipper());
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
			out.print("<br><b style='color:red'>" + e.getMessage() + "</b>");
			session.setAttribute(ApplConstants.SAVETIPPERREMOTE, null);
		}
	}
%>
<table border="0" cellpadding="5">
	<tr>
		<td>TipperID:</td>
<%
System.out.println("TipperAendern.jsp-->12");
	
	out.print("<td>" + tipperAlt.getIdTipper() + "</td>");
%>		
	</tr>
	<tr>
		<td>Name:</td>
<%
		out.print("<td>" + tipperAlt.getAtTipperName() + "</td>");
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
		<td>Vorname:</td>
<%
		out.print("<td>" + tipperAlt.getAtTipperVorname() + "</td>");
%>		
		<td><input type="text" name="vorname" maxlength="8"
<%  
		if(vornameKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
%>		
		></td>
	</tr>
	<tr>
		<td>Login:</td>
<%
		out.print("<td>" + tipperAlt.getAtTipperLogin() + "</td>");
%>		
		<td><input type="text" name="login" maxlength="10"
<%  
		if(loginKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(loginKorrekt == false)
		{
			out.print(" value='" + request.getParameter("login") + "'");
		}
%>		
		></td>
	</tr>
	<tr>
		<td>Password:</td>
<%
		out.print("<td>" + tipperAlt.getAtTipperPassword() + "</td>");
%>		
		<td><input type="text" name="password" maxlength="10"
<%  
		if(passwordKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(passwordKorrekt == false)
		{
			out.print(" value='" + request.getParameter("password") + "'");
		}
%>		
		></td>

	</tr>
	<tr>
		<td>Konto plichtige spieltage:</td>
<%
		out.print("<td>" + tipperAlt.getAtTipperKonto() + "</td>");
%>		
		<td><input type="text" name="konto" maxlength="5"
<%  
		if(kontoKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(kontoKorrekt == false)
		{
			out.print(" value='" + request.getParameter("konto") + "'");
		}
%>		
		></td>
	</tr>
	<tr>
		<td>Team:</td>
<%
		out.print("<td>" + tipperAlt.getAtTipperTeam() + "</td>");
%>		
		<td><input type="text" name="team" maxlength="2"
<%  
		if(teamKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(teamKorrekt == false)
		{
			out.print(" value='" + request.getParameter("team") + "'");
		}
%>		
		></td>
	</tr>
	
	<tr>
		<td>E-Mail:</td>
<%
		out.print("<td>" + tipperAlt.getAtTipperMail() + "</td>");
%>		
		<td><input type="text" name="mail" maxlength="30"
<%  
		if(mailKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(mailKorrekt == false)
		{
			out.print(" value='" + request.getParameter("mail") + "'");
		}
%>		
		></td>
	</tr>
	
	<tr>
		<td>Telefon:</td>
<%
		out.print("<td>" + tipperAlt.getAtTipperTel() + "</td>");
%>		
		<td><input type="text" name="tel" maxlength="20"
<%  
		if(telKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(telKorrekt == false)
		{
			out.print(" value='" + request.getParameter("tel") + "'");
		}
%>		
		></td>
	</tr>
	
	<tr>
		<td>Mobil:</td>
<%
		out.print("<td>" + tipperAlt.getAtTipperMobil() + "</td>");
%>		
		<td><input type="text" name="mobil" maxlength="20"
<%  
		if(mobilKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(mobilKorrekt == false)
		{
			out.print(" value='" + request.getParameter("mobil") + "'");
		}
%>		
		></td>
	</tr>
	
	<tr>
		<td>Iban:</td>
<%
		out.print("<td>" + tipperAlt.getAtTipperIban() + "</td>");
%>		
		<td><input type="text" name="iban" maxlength="22"
<%  
		if(ibanKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(ibanKorrekt == false)
		{
			out.print(" value='" + request.getParameter("iban") + "'");
		}
%>		
		></td>
	</tr>
	

	
	<tr>
<%
		out.print("<td colspan='3'>" + tipperAlt.getAtTipperForm() + "</td>");
%>		
		</tr><tr><td colspan="3"><input type="text" name="formstring" size="70" maxlength="70"
<%  
		if(formKorrekt == false)
		{
			out.print(" style='background-color:yellow' ");
		}
		if(formKorrekt == false)
		{
			out.print(" value='" + request.getParameter("formstring") + "'");
		}
%>		
		></td>
	</tr>
	
</table>
<%
	if(tipperKorrekt == false)
	{
		out.print("<br>Der Tipper wurde nicht geändert!");
		out.print("<br>Bitte die gelben Felder korrigieren/ergänzen!");
	}
%>
<br><br>
<input type="submit" name="speichern" value="Änderungen speichern" class="<%=ApplConstants.CSSSUBMIT2%>">
<input type="submit" name="loeschen" value="Aus Datenbank löschen" class="<%=ApplConstants.CSSSUBMIT2%>" 
<%
// 	if(! tipperAlt.getAtTipperKonto().equals(BigDecimal.ZERO))
// 	{
// 		out.print(" disabled");
// 	}
%>
>
</form>
</body>
</html>