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
<title>WEPAS Paarungen zum Spieltag erfassen</title>
</head>
<body>
<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPSPIELTAGSPIELENEU)%> method="post">
<h1>WEPAS Paarungen zum Spieltag erfassen</h1>
<h3><a href="<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPSPIELTAGVERWALTUNG)%>">Zurück zur Verwaltung</a></h3>
<%
	//--->Spieltag lesen
	SpieltagBeanRemote sbr = (SpieltagBeanRemote)session.getAttribute(ApplConstants.SPIELTAG_BEAN);
	if(sbr == null)
	{
	    Context ctx = new InitialContext();
		Object ref = ctx.lookup(ApplConstants.JNDISPIELTAGBEAN);
		sbr = (SpieltagBeanRemote)PortableRemoteObject.narrow(ref, SpieltagBeanRemote.class);
		session.setAttribute(ApplConstants.SPIELTAG_BEAN, sbr);
	}
	
	EnSpieltag spieltag = null;
	String stringSpieltagnr = request.getParameter("spieltagnr");
	if(stringSpieltagnr != null)
	{ 
		try
		{
			spieltag = sbr.getSpieltag(new Integer(stringSpieltagnr).intValue());
			session.setAttribute("SpieltagVerwaltungSpieltag", spieltag);
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
		spieltag = (EnSpieltag)session.getAttribute("SpieltagVerwaltungSpieltag");
	}
	
	if(spieltag == null && stringSpieltagnr == null)
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPSPIELTAGVERWALTUNG%>"></jsp:forward>
<%		
		return;
	}
		
	//--->Vereine lesen
	VereinBeanRemote vereinStateless = (VereinBeanRemote)session.getAttribute(ApplConstants.SAVEVEREINREMOTE);
	if(vereinStateless == null)
	{
        Context ctx = new InitialContext();
		Object ref = ctx.lookup(ApplConstants.JNDIVEREINBEAN);
		vereinStateless = (VereinBeanRemote)PortableRemoteObject.narrow(ref, VereinBeanRemote.class);
		session.setAttribute(ApplConstants.SAVEVEREINREMOTE, vereinStateless);
	}

	Vector<EnVerein> vereine = null;
	try
	{
		EnVerein[] vereinarray = vereinStateless.getVerein();
		vereine = new Vector<EnVerein>(vereinarray.length);
		for(int i = 0; i < vereinarray.length; i++)
		{
			vereine.add(vereinarray[i]);
		}
	}
	catch(Exception e)
	{
		out.print("<br><b style='color:red'>" + e.getMessage() + "</b>");
		session.setAttribute(ApplConstants.SAVEVEREINREMOTE, null);
	}
	
	//--->SpielInterface besorgen
	SpielBeanRemote spbr = (SpielBeanRemote)session.getAttribute((ApplConstants.SPIEL_BEAN));
	if(spbr == null)
	{
		Context ctx = new InitialContext();
		Object ref = ctx.lookup(ApplConstants.JNDISPIELBEAN);
		spbr = (SpielBeanRemote)PortableRemoteObject.narrow(ref, SpielBeanRemote.class);
		session.setAttribute(ApplConstants.SPIEL_BEAN, spbr);
	}

	if(request.getParameter("speichern") != null)
	{
		try
		{
			Vector<EnSpiel> spiele = new Vector<EnSpiel>();
			for(int i = 0; i < spieltag.getAtSpieltagPaarungen(); i++)
			{
				EnVerein h = vereinStateless.getVerein(Integer.parseInt(request.getParameter(i + "H_DRO")));
				EnVerein g = vereinStateless.getVerein(Integer.parseInt(request.getParameter(i + "G_DRO")));
				spiele.add(new EnSpiel(spieltag.getIdSpieltag(), 
						Integer.parseInt(request.getParameter(i + "H_DRO")), 
						Integer.parseInt(request.getParameter(i + "G_DRO"))));
			}
			Iterator<EnSpiel> iter = spiele.iterator();
			while (iter.hasNext())
			{
				spbr.addSpiel(iter.next()); 
			}
		}
		catch(Exception e)
		{
			out.print("<br><b style='color:red'>Exception: " + e.toString() + "<br/>" + "</b><br/>" + e.getStackTrace());
			session.setAttribute(ApplConstants.SPIELTAG_BEAN, null);
		}
	}
%>


<h3>Paarungen für den 
<%
	out.print(spieltag.getAtSpieltagText());
%> 
erfassen</h3>
<%
	for(int i = 0; i < spieltag.getAtSpieltagPaarungen(); i++)
	{
		out.print("<br>\n Spiel " + (i + 1) + "\n");
		
		out.print("<select name='" + i + "H_DRO' size='1'>");
		out.print("<option value='0'>---Auswahl Heim---</option>");
		Iterator<EnVerein> iterH = vereine.iterator();
		while(iterH.hasNext())
		{
			EnVerein v = iterH.next();
			out.print("<option value='" + v.getIdVerein() + "'>" + v.getAtVereinName() + "</option>");
		}
		out.print("</select>");
		
		out.print("\n - \n");
		
		out.print("<select name='" + i + "G_DRO' size='1'>");
		out.print("<option value='0'>---Auswahl Gast---</option>");
		Iterator<EnVerein> iterG = vereine.iterator();
		while(iterG.hasNext())
		{
			EnVerein v = iterG.next();
			out.print("<option value='" + v.getIdVerein() + "'>" + v.getAtVereinName() + "</option>");
		}
		out.print("</select>");
		
		out.print("\n");
	}
%>

<br><br><input type="submit" name="speichern" value="Speichern" class="<%=ApplConstants.CSSSUBMIT2%>">
</form>
</body>
</html>