<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page 
         import="java.util.Vector"
         import="java.util.Iterator"
         import="javax.naming.*" 
         import="de.wepas.connector.*"
         import="de.wepas.jpa.*"
         import="de.wepas.constants.ApplConstants"
         import="javax.rmi.PortableRemoteObject"
         import="form.TippenForm"
         import="java.util.GregorianCalendar"
         import="java.sql.Timestamp"
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=ApplConstants.CSSFILETIPPER%>">
<title>Tippformular sonstige Tipps</title>
</head>
<body>
<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPTIPPENFREE)%> method="post">
<h1>Tippformular für sonstige freiwillige Tipps</h1>
<%
	TippenForm form = new TippenForm(request);
	Timestamp jetzt = new Timestamp(new GregorianCalendar().getTimeInMillis());

	if(form.isButtonNavigation())
	{
		if(session.getAttribute(ApplConstants.TIPPABGABE_STATEFULBEAN) != null)
		{
	TippabgabeStatefulBeanRemote tsbr = (TippabgabeStatefulBeanRemote)session.getAttribute((ApplConstants.TIPPABGABE_STATEFULBEAN));
	tsbr.remove();
	session.setAttribute(ApplConstants.TIPPABGABE_STATEFULBEAN, null);
		}
%>
		<jsp:forward page="<%=ApplConstants.JSPMAIN%>"></jsp:forward>
<%	
	}	
//--->Fehlerbehandlung
	if(form.getMessage().length() > 2);
	{
		out.println("<br><h3>" + form.getMessage() + "</h3>");
	}
	
	if(session.getAttribute(ApplConstants.TIPPABGABE_STATEFULBEAN) != null)
	{
		TippabgabeStatefulBeanRemote tippabgabeStateful = (TippabgabeStatefulBeanRemote)session.getAttribute((ApplConstants.TIPPABGABE_STATEFULBEAN));
		EnSpieltag zutippenderspieltag = tippabgabeStateful.getSpieltag();
		if(zutippenderspieltag == null)
		{
//---> Für diesen Tipper gibt es nichts mehr zu tippen			
%>	.
			<h3> Es gibt für Dich keine zu tippende Spieltage!</h3>
			<ul>
				<li>Die Tippsaison ist zuende ?</li>
				<li>Du hast bereits alle Spieltage zuende getippt ?</li>
				<li>Steff hat noch nicht alle Spieltage für diese Saison erfaßt <br>(Weil die genauen Termine noch nicht feststehen) ?</li>
				<li>Steff hat noch gar keine Spieltage erfaßt ?</li>
			</ul>
			<input type='submit' name='navigation' value='OK' class='<%=ApplConstants.CSSSUBMIT2%>'>
<%		
		}
		else
		{
//---> Es wurde der für diesen Tipper nächst zu tippende Spieltag gefunden			
%>
			<h3><%=tippabgabeStateful.getTipper().getAtTipperVorname()%> tippt <%=tippabgabeStateful.getSpieltag().getAtSpieltagText()%>
			Bitte mit <%=tippabgabeStateful.getSpieltag().getAtSpieltagSupertipps()%> Supertipps</h3>	
<%	
//---> StateLessSessionBean Vereine (wg. der Namen) vom Server besorgen
			Context ctx = new InitialContext();
			Object ref = ctx.lookup(ApplConstants.JNDIVEREINBEAN);
			VereinBeanRemote vereinStateless = (VereinBeanRemote)PortableRemoteObject.narrow(ref, VereinBeanRemote.class);

			EnSpiel[] spielarray = tippabgabeStateful.getSpiele();
			Vector<EnSpiel> spiele = new Vector<EnSpiel>(spielarray.length);
			for(int i = 0; i < spielarray.length; i++)
			{
				spiele.add(spielarray[i]);
			}
			Iterator<EnSpiel> iter2 = spiele.iterator();
			int i = 0;
%>
			<table border="2" cellpadding="5">
				<tr>
					<th>Anpfiff</th>
					<th>Paarung</th>
					<th>Tipp</th>
					<th>Supertipp</th>
				</tr>
<%		
				while(iter2.hasNext())
				{
					i++;
					EnSpiel spiel = iter2.next();
					EnVerein heim = vereinStateless.getVerein(spiel.getFkSpielVereinHeim());
					EnVerein gast = vereinStateless.getVerein(spiel.getFkSpielVereinGast());
%>	
					<tr class='css_low'>
						<td align='right'><%=util.StringFormat.format(spiel.getAtSpielAnpfiff())%>
						</td>
<%
						if(spiel.getAtSpielAnpfiff().compareTo(jetzt) < 0)
						{
%>						
							<td>
								<img src='../../img/vereine/klein/<%=heim.getAtVereinKurz()%>.png' width='40' height='27' border='0'>
								<img src='../../img/vereine/klein/vs.png' width='40' height='27' border='0'>
								<img src='../../img/vereine/klein/<%=gast.getAtVereinKurz()%>.png' width='40' height='27' border='0'>
								<b>Spiel ist bereits angepfiffen, Tipp wird nicht gewertet!</b>
							</td>
<%							
						}
						else
						{
%>																	
							<td>
								<img src='../../img/vereine/klein/<%=heim.getAtVereinKurz()%>.png' width='40' height='27' border='0'>
								<img src='../../img/vereine/klein/vs.png' width='40' height='27' border='0'>
								<img src='../../img/vereine/klein/<%=gast.getAtVereinKurz()%>.png' width='40' height='27' border='0'>
								<font size="+1">
									<%=heim.getAtVereinName()%> - <%=gast.getAtVereinName()%>
								</font>
							</td>
<%
						}
						String th;
						String tg;
						try
						{
							int ith = Integer.parseInt(request.getParameter(spiel.getIdSpiel() + "TXT_Tore_Hei"));
							th = "" + ith;
							int itg = Integer.parseInt(request.getParameter(spiel.getIdSpiel() + "TXT_Tore_Gas"));
							tg = "" + itg;
						}
						catch(NumberFormatException e)
						{
							th = "";
							tg = "";
						}
%>					
						<td>
							<input type='text' name='<%=spiel.getIdSpiel()%>TXT_Tore_Hei' value='<%=th%>' size='1' maxlength='1'>
							&nbsp;:&nbsp;
							<input type='text' name='<%=spiel.getIdSpiel()%>TXT_Tore_Gas' value='<%=tg%>' size='1' maxlength='1'>
							</td>
						<td>
							<input type='checkbox' name='<%=spiel.getIdSpiel()%>CB_SUPER'>
						</td>
					</tr>
<%			
				}
%>
			</table>
			<input type='submit' name='speichern' value='Speichern' class='<%=ApplConstants.CSSSUBMIT2%>'>
			<input type='submit' name='navigation' value='Zurück' class='<%=ApplConstants.CSSSUBMIT2%>'>
<%		
		}
	}
	else 
	{
%>
<%-- 		<input type='submit' name='tippen' value='Weitere Spieltage tippen' class='<%=ApplConstants.CSSSUBMIT2%>'>	 --%>
		<input type='submit' name='navigation' value='OK' class='<%=ApplConstants.CSSSUBMIT2%>'>
<%
	} 
%>
</form>	
</body>
</html>
