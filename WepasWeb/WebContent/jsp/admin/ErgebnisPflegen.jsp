<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page 
		import="java.util.Vector" 
		import="java.util.Iterator" 
		import="javax.naming.*"
		import="de.wepas.connector.*" 
		import="de.wepas.constants.ApplConstants"
		import="de.wepas.jpa.*" 
		import="form.ErgebnisForm"
		import="javax.rmi.PortableRemoteObject"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=ApplConstants.CSSFILEADMIN%>">
<title>Wepas.de Ergebnis pflegen</title>
</head>

<body>
	<form
		action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPERGEBNISPFLEGEN)%>
		method="post">
		<h1>WEPAS Spielergebnis pflegen</h1>
		<input type='submit' name='adminnavi' value='Zurück'
			class='<%=ApplConstants.CSSSUBMIT2%>'>
		<%
			ErgebnisForm form = new ErgebnisForm(request);
			
			if (request.getParameter("AdminNavi") != null)
			{
		%>
		<jsp:forward page="<%=ApplConstants.JSPADMINNAVI%>"></jsp:forward>
		<%
			}
			
			if (request.getParameter("speichern") != null)
			{
				form.action();
		%>
		<jsp:forward page="<%=ApplConstants.JSPADMINNAVI%>"></jsp:forward>
		<%
			}
			
			//Ab hier wird das in der Session gespeicherte Spiel zur pflege angezeigt.	
			int spielId = (Integer) session.getAttribute(ApplConstants.IDSPIEL);
			SpielBeanRemote spbr = (SpielBeanRemote) session.getAttribute(ApplConstants.SPIEL_BEAN);
			if (spbr == null)
			{
				Context ctx = new InitialContext();
				Object ref = ctx.lookup(ApplConstants.JNDISPIELBEAN);
				spbr = (SpielBeanRemote) PortableRemoteObject.narrow(ref, SpielBeanRemote.class);
				session.setAttribute(ApplConstants.SPIEL_BEAN, spbr);
			}
			VereinBeanRemote vbr = (VereinBeanRemote) session.getAttribute(ApplConstants.SAVEVEREINREMOTE);
			if (vbr == null)
			{
				Context ctx = new InitialContext();
				Object ref = ctx.lookup(ApplConstants.JNDIVEREINBEAN);
				vbr = (VereinBeanRemote) PortableRemoteObject.narrow(ref, VereinBeanRemote.class);
				session.setAttribute(ApplConstants.SAVEVEREINREMOTE, vbr);
			}
			
			Vector<EnVerein> vereine = null;
			try
			{
				EnVerein[] vereinarray = vbr.getVerein();
				vereine = new Vector<EnVerein>(vereinarray.length);
				for (int i = 0; i < vereinarray.length; i++)
				{
					vereine.add(vereinarray[i]);
				}
				//		vereine = vbr.getVerein();
			}
			catch (Exception e)
			{
				System.out.println("--> Exception " + e.getMessage() + " in " + ApplConstants.JSPERGEBNISPFLEGEN + " Zeile 72 " + this.getClass().getName());
				e.printStackTrace();
			}
			
			EnSpiel spiel = spbr.getSpiel(spielId);
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
			session.setAttribute("EnSpiel", spiel);
			out.print("<br>" + "<img src='../../img/vereine/gross/" + heim.getAtVereinKurz() + ".png' width='50' height='50' border='0'>"
					+ "<img src='../../img/vereine/klein/vs.png'>" + "<img src='../../img/vereine/gross/" + gast.getAtVereinKurz()
					+ ".png' width='50' height='50' border='0'>" + "");
			out.print(heim.getAtVereinName() + " - " + gast.getAtVereinName());
			out.print("<input type='text' name='toreHeim' value='' size='1' maxlength='1'>");
			out.print(" : ");
			out.println("<input type='text' name='toreGast' value='' size='1' maxlength='1'>");
		%>
		<input type='submit' name='speichern' value='Speichern'
			class='<%=ApplConstants.CSSSUBMIT2%>'>
	</form>
</body>
</html>