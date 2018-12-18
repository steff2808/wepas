<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WEPAS / Login </title>

<script language="JavaScript1.2">
	var correctwidth=screen.width;
	var backpic;
	if (correctwidth==1920) backpic="/WepasWeb/img/backgrounds/1920back.jpg";
	else if (correctwidth==1280) backpic="/WepasWeb/img/backgrounds/1280back.jpg";
	else if (correctwidth==1152) backpic="/WepasWeb/img/backgrounds/1152back.jpg";
	else backpic="/WepasWeb/img/backgrounds/1024back.jpg";
</script>

<link rel="stylesheet" type="text/css" href="<%=ApplConstants.CSSFILETIPPER%>">

<script>
// 	document.write("<body style='background-image:url(" + backpic + ")'>");
//	document.write("<body>");
</script>
<!-- <body  style="background-image:url(/WepasWeb/back.jpg)"> -->
<body>
<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPINDEX)%> method="post">
<h1>WEPAS / Login </h1>
<%
	String cssSubmitSortSpiel = "css_submit4";
%>
<!-- <p>Das <font size='+1' color='#ff0000'>Forum</font> ist <font size='+1' color='#ff0000'>fertig!</font> </p> -->
<hr>
<%@ 
	page import="java.util.*" 
         import="javax.naming.*" 
         import="de.wepas.connector.*"
         import="de.wepas.constants.*"
         import="util.LookupRemoteService"
         import="de.wepas.jpa.*"
         import="javax.rmi.PortableRemoteObject"
         import="form.LoginForm" 
         import="sorts.SortTipperName"
%>
<%
	LoginForm form = new LoginForm(request); 
	form.action(request, response); 
	
//--->Fehlerbehandlung
	if(form.getStatusflag() == 'L' 
		|| form.getStatusflag() == 'F')
	{
		out.println("<br><h3>Fehler: " + form.getMessage() + "</h3>");
	}
	if(form.getStatusflag() == 'O')
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPMAIN%>"></jsp:forward>
<%
	}
%>

<table class='css_table0'>
<tr>
<td>Name
<select name='DropTipper' size='1'>
<option value='0'>---Auswahl---</option>
<%
	TipperBeanRemote tbr = (TipperBeanRemote)session.getAttribute(ApplConstants.SAVETIPPERREMOTE);
	if(tbr == null)
	{
		tbr = LookupRemoteService.lookupTipperBeanRemote();
		session.setAttribute(ApplConstants.SAVETIPPERREMOTE, tbr);
	}

	Vector<EnTipper> tippers = new Vector<EnTipper>();

//Jetzt geht's Schief!

	try
	{
		EnTipper[] tipperarray = tbr.getTipper();
		for (int i = 0; i < tipperarray.length; i++)
		{
			tippers.add(tipperarray[i]);
		}
		Collections.sort(tippers, new SortTipperName());  
		Iterator<EnTipper> iter1 = tippers.iterator();
		while(iter1.hasNext())
		{
			EnTipper tipper = iter1.next();
			out.println("<option value='" + tipper.getIdTipper() + "'>" + tipper.getAtTipperName() + ",&nbsp;" + tipper.getAtTipperVorname() + "</option>");
		}
	}
	catch(TipperException e)
	{
		System.out.println("Scheisse, TipperException und zwar---> " + e.getMessage());
		System.out.println(e.getStackTrace());
		out.print("<br><b style='color:red'>" + e.getMessage() + "</b>");
		session.setAttribute(ApplConstants.SAVETIPPERREMOTE, null);
	}
%>
</select>
</td>
<td>oder Loginnamen    	<input type='text'     name='TXT_Tip_Log' ></td>
<td>Password 			<input type='password' name='TXT_Tip_Pass' ></td>
<td>         			<input type='submit'   name='login' value='Login' class='css_submit2'></td>
</tr>
</table>
<%
//         response.setHeader("Cache-Control", "no-cache"); 	//Forces caches to obtain a new copy of the page from the origin server  
//         response.setHeader("Cache-Control", "no-store"); 	//Directs caches not to store the page under any circumstance  
//         response.setDateHeader("Expires", 0); 				//Causes the proxy cache to see the page as "stale"  
//         response.setHeader("Pragma", "no-cache"); 			//HTTP 1.0 backward compatibility  
%>
<hr>
<h3>----        Saison 2018/2019 Fuﬂball-Tipprunde         ----</h3> 
<hr>
<ul>
<li>Wenn es Probleme gibt bei der Tippabgabe gibt -  bitte E-Mail an ---&gt; stefan-meister at gmx.de oder auch gerne whatsApp </li>
</ul>

<hr>
<br>
<table class='css_table0'>
<tr>
	<td><b>Impressum f&uuml;r www wepas de </b></td>
</tr>
<tr>
	<td>Verantwortlich f&uuml;r diese Seite ist: Stefan Meister, Teichstr. 32, 21641 Apensen</td>
</tr>
<tr>
	<td>Hierbei handelt es sich um ein kleines privates Softwareprojekt bei dem sich</td>
</tr>
<tr>
	<td>nur von mir bekannte Personen beteiligen / anmelden d&uuml;rfen.</td>
</tr>
<tr>
	<td>Meine E-Mail ist: stefan-meister at gmx.de </td>
<!-- 	 --&gt;meinvorname minus meinnachname at gmx punkt de&lt;--.</td> -->
</tr>
</table>
<hr>
<table>
<tr>
<!-- <td align='center'><a href='http://de.wikipedia.org/wiki/Lubuntu'> -->
<!-- 	<img src='/WepasWeb/img/links/lubuntu.png' border='0'></a> -->
<!-- </td> -->
</tr>
</table>
</form>
</body>
</html>
