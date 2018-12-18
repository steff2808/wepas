<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page 
		 import="java.util.Vector"
		 import="java.util.Iterator"
		 import="javax.naming.*"
		 import="de.wepas.connector.*" 
		 import="de.wepas.constants.ApplConstants"
		 import="de.wepas.jpa.*" 
		 import="javax.rmi.PortableRemoteObject"
		 import="form.AdminForm"
		 import="de.wepas.util.HtmlTags"
         import="de.wepas.livegoals.*"
         import="util.LookupRemoteService"
         import="de.wepas.jpa.*"
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=ApplConstants.CSSFILEADMIN%>">
<title>WEPAS AdminNavi</title>
</head>
<body>
<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPADMINNAVI)%> method="post">
<%
	AdminForm form = new AdminForm(request);
	String message = "";
	if(form.isButtonNavigation())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPMAIN%>"></jsp:forward>
<%		
		return;
	}
	if(form.isButtonSpieltagVerw())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPSPIELTAGVERWALTUNG%>"></jsp:forward>
<%		
		return;
	}
	if(form.isButtonStorno())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPSTORNO%>"></jsp:forward>
<%		
		return;
	}
	if(form.isButtonTipperVerw())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPTIPPERVERWALTUNG%>"></jsp:forward>
<%		
		return;
	}
	if(form.isButtonVereinVerw())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPVEREINVERWALTUNG%>"></jsp:forward>
<%		
		return;
	}
	if(form.isButtonLivegoalsStatus())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPLIVEGOALSSTATUS%>"></jsp:forward>
<%		
		return;
	}
	LivegoalsBeanRemote livegoalsSingleton = LookupRemoteService.lookupLivegoalsSingletonRemote();
	if(form.isButtonCreate())
	{
		livegoalsSingleton.createLivegoalObject();
		message = (" (LivegoalObject CREATED)");
	}
	if(form.isButtonStartLG())
	{
		livegoalsSingleton.startLivegoals();
		message = (" (LivegoalObject STARTED)");
	}
	if(form.isButtonStoppLG())
	{
		livegoalsSingleton.stopLivegoals();
		message = (" (LivegoalObject STOPPED)");
	}
	if(form.isButtonDestroy())
	{
		livegoalsSingleton.destroyLivegoalObject();
		message = (" (LivegoalObject DESTROYED)");
	}
	if(form.isButtonLoadLG())
	{
		livegoalsSingleton.loadTESTData();
		message = (" (LivegoalObject DATA LOADED)");
	}
	if(form.isButtonSaveLG())
	{
		livegoalsSingleton.saveTESTData();
		message = (" (LivegoalObject DATA SAVED)");
	}
	
	if(form.getZupflegendesspiel() != null)
	{
		session.setAttribute(ApplConstants.IDSPIEL, form.getZupflegendesspiel().getIdSpiel());
%>
		<jsp:forward page="<%=ApplConstants.JSPERGEBNISPFLEGEN%>"></jsp:forward>
<%		
		return;
	}
%>
	<h1>WEPAS AdminNavigation<%=message%></h1>
	<table class='<%=ApplConstants.CSSTABLE1%>'>
		<tr>
<%
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("navigation", "Zurück", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("tipperverwaltung", "Tipper", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("vereinverwaltung", "Vereine", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("spieltagverwaltung", "Spieltage", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
%>		
		</tr>
		<tr>
<%
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("create", "Livegoals Create", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("startlg", "Livegoals Start", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("stopplg", "Livegoals Stopp", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("destroy", "Livegoals Destroy", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
%>		
		</tr>
		<tr>
<%
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("livegoalsstatus", "Livegoals Status", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
			out.print(HtmlTags.wrapTD(HtmlTags.createNBSP(1), ApplConstants.CSSNOBORDER));
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("loadlg", "Laden Testdaten", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("savelg", "Speichern Testdaten", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
%>		
		</tr>
	</table>

	<h3>Zu pflegende Spiele</h3>
<%
	VereinBeanRemote vbr = null;
	Vector<EnVerein> vereine = null;
	try
	{
		
		vbr = (VereinBeanRemote)session.getAttribute(ApplConstants.SAVEVEREINREMOTE);
		if(vbr == null)
		{
		 	Context ctx = new InitialContext();
			Object ref = ctx.lookup(ApplConstants.JNDIVEREINBEAN);
			vbr = (VereinBeanRemote)PortableRemoteObject.narrow(ref, VereinBeanRemote.class);
			session.setAttribute(ApplConstants.SAVEVEREINREMOTE, vbr);
		}

		EnVerein[] vereinarray = vbr.getVerein();
		vereine = new Vector<EnVerein>(vereinarray.length);
		for(int i = 0; i < vereinarray.length; i++)
		{
			vereine.add(vereinarray[i]);
		}

//		vereine = vbr.getVerein();
	}
	catch(Exception e)
	{
		System.out.println("Exception " + e.getMessage() + "in " + ApplConstants.JSPADMINNAVI + " / 107 aufgetreten");
	}

%>
	<table class='<%=ApplConstants.CSSTABLE1%>'>
		<tr>
			<th>Paarung</th>
			<th>&nbsp;</th>
		</tr>
<%
	Iterator<EnSpiel> iter1 = form.getSpieleOffen().iterator();	
	int gw = 99;
	boolean flipflop = false;
	String css = "";
	while(iter1.hasNext())
	{
		EnSpiel spiel = iter1.next();
		if(gw != spiel.getFkSpielSpieltag())
		{
			gw = spiel.getFkSpielSpieltag();
			if(flipflop)
			{
				flipflop = false;
				css = ApplConstants.CSSWEISSL;
			}
			else
			{
				flipflop = true;
				css = ApplConstants.CSSROTL;
			}
		}
		EnVerein heim = null;
		EnVerein gast = null;
		Iterator<EnVerein> iterH = vereine.iterator();
		while (iterH.hasNext())
		{
			EnVerein verein = iterH.next();
			if (verein.getIdVerein() == spiel.getFkSpielVereinHeim())
			{
				heim = verein;
			}
		}
		Iterator<EnVerein> iterG = vereine.iterator();
		while (iterG.hasNext())
		{
			EnVerein verein = iterG.next();
			if (verein.getIdVerein() == spiel.getFkSpielVereinGast())
			{
				gast = verein;
			}
		}
%>
		<tr class='<%=css%>'>
<%		
		out.println("<td>" 
			+ "<img src='../../img/vereine/klein/" + heim.getAtVereinKurz() + ".png' width='40' height='40' border='0'>"
			+ "<img src='../../img/vereine/klein/vs.png'>"
			+ "<img src='../../img/vereine/klein/" + gast.getAtVereinKurz() + ".png' width='40' height='40' border='0'>"
			+ "");
		out.println(heim.getAtVereinName() + " - " + gast.getAtVereinName() + "</td>");
		if(spiel.getAtSpielIsPlayed())
		{
			out.println("<td>" + spiel.getAtSpielToreHeim() + " : " + spiel.getAtSpielToreGast() + "</td></tr>");	
		}
		else
		{
			String buttonname = "zupflegendesspiel" + spiel.getIdSpiel();
			out.println("<td><input type='submit' name='" + buttonname + "' value='erfassen' class='" + ApplConstants.CSSSUBMIT2 + "'></td>");
		}
%>
		</tr>
<%
	}
%>
	</table>
	<blink>ERLEDIGT! Sortieren in der Navianzeige</blink>
<h3>Bugfixe:</h3>
<li>Storno Tabellenfunktion zurücksetzen</li>
<li></li>

</ul>


<h3>Muss aus alter Version noch migriert werden:</h3>
<ul>
<li>Wenn 2 gewinnen wird falsch gerechnet</li>
<li>Storno suboptimal / Siege werden wohl nicht zurückgesetzt</li>
<li>Parametrisierung Spieltaggewinne</li>
<li>Bulletin</li>

</ul>

<h3>Neue Disignelemente:</h3>
<ul>
<li>Stadionsymbole</li>
<li>Fixe Überschrift</li>
<li>Tabellen und Buttonlayout</li>
<li>Liveticker mit Symbolen</li>
</ul>

<h3>Neue technische Features:</h3>
<ul>
<li>Worst - Bestcasebutton 0:0 1:1 2:2 3:3 1:0 2:1 3:2 0:1 1:2 2:3 2:0 3:1 0:2 1:3 3:0 4:1 0:3 1:4 4:0 0:4 5:0 0:5</li>
<li>Lifechat / Über MessageDriven Bean</li>
<li>Übersicht wer wann seinen Tipp abgegeben hat</li>
<li>Erinnerungsmails wenn Tipp vergessen wurde an bekannte E-Mailadresse</li>
<li>Adminpflege Einstellungen</li>
<li>Sonderwetten mit kann und 0,50</li>
<li>lgoals mit 1 min blink</li>
<li>lg mit best und wcanzeige</li>




<h3>Neue Herausforderungen:</h3>
<ul>
<li>Langfristige fragen wie:</li>
<li>Wer wird Herbstmeister</li>
<li>Welchen Platz belege ich bei der Tipprunde</li>
<li>Wer wird Torschützenkönig</li>
<li>Welcher Trainer fliegt zuerst</li>
<li>Wer steigt ab</li>
<li>Wo steht ein bestimmter Verein von mir?</li>
<li></li>
</ul>	
	
	
	
<ul>
<li><blink>ERLEDIGT! Sortieren in der Navianzeige</blink></li>
<li>Tabellenjsp</li>
<li>Smiley für im Geld und Zahler + Krone, dafür Ouzo</li>
<li>Formatierung / Ausrichtung Geldbeträge </li>
<li>Layout immer grau wenn Navizurück</li>
<li>Bundesligatabelle</li>
<li>Highscore</li>
<li>Spiel/Tipp Detailanzeige</li>
<li>Tipp soirtierung Layoutinfo welches spiel sortiert wurde. am beste button farbig und disablen</li>
<li>Statisktikmodul aber nur layout wie gelbes Kreuz und erklärung wenn sortiert</li>
<li>Vollzahlersymbole</li>
<li>XML-RSS implementieren und seite periodisch updaten / über Singleon-Bean?</li>
<li>Automatische Pflege / Über TimerBeans</li>
</ul>	
<h3>Erledigt und funktioniert wie in alt:</h3>
<ul>
<li>Tippformular</li>
<li>Spieltagpflege</li>
<li>Anzeige des Spiels</li>
<li>Anzeigeschummler</li>
</ul>
<table style="border-style: none; margin-top: 10px;">
	<tbody>
		<tr><td>Id</td><td><%=session.getId() %></td></tr>
		<tr><td>CreationTime</td><td><%=session.getCreationTime() %></td></tr>
		<tr><td>MaxInactiveInterval</td><td><%=session.getMaxInactiveInterval() %></td></tr>
		<tr><td>ServletContext</td><td><%=session.getServletContext() %></td></tr>
	</tbody>
</table>
</form>
</body>
</html>