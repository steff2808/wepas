<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page  
		 import="java.util.*" 
         import="javax.naming.*" 
         import="de.wepas.livegoals.*"
         import="de.wepas.constants.*"
         import="util.LookupRemoteService"
         import="de.wepas.jpa.*"
         import="javax.rmi.PortableRemoteObject"
         import="java.text.DateFormat"
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TESTWEPAS</title>
<link rel="stylesheet" type="text/css" href="<%=ApplConstants.CSSFILEADMIN%>">
<!-- <script type="text/javascript"> -->
<!--  	alert(screen.width + "x" + screen.height); -->
<!-- </script> -->
</head>
<body>
<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPLIVEGOALSSTATUS)%> method="post">
<%
	if(request.getParameter("adminnavi") != null)
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPADMINNAVI%>"></jsp:forward>
<%		
	}
%>
<h3>WEPAS Test DisplayLivetickerObject</h3>
<input type='submit' name='adminnavi' value='Zurück' class='<%=ApplConstants.CSSSUBMIT2%>'>
<%
	String cssSubmitSortSpiel = "css_submit4";
%>
<hr>
<%
	LivegoalsBeanRemote lbr = null;
	lbr = LookupRemoteService.lookupLivegoalsSingletonRemote();
	try
	{
		String s = lbr.getLivegoalsObject().getSpieltag().getAtSpieltagText();
		out.println("<h3>V21:32 Test des " + s + "</h3>");
		LivegoalsObject lgo = lbr.getLivegoalsObject();
		
		out.println("<b>--- A K T U E L L -----</b><br/>");
		Iterator<Livespiel> iter1 = lgo.getLivespiele().iterator();
		while(iter1.hasNext())
		{
	out.println(iter1.next().getTitle() + "<br/>");
		}
		out.println("<b>--- H I S T O R I E ---</b><br/>");
		Iterator<Livespiel> iter2 = lgo.getLivehistorie().iterator();
		while(iter2.hasNext())
		{
	DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
	DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
	
	Livespiel lg = iter2.next();

	String ts = dateFormat.format(lg.getTimestamp().getTime())
			+ " um " + timeFormat.format(lg.getTimestamp().getTime()) + " Uhr : ";
	out.println(ts + lg.getTitle() + "<br/>");
		}
	}
	catch(NullPointerException e)
	{
		out.println("<h3>LivegoalsBean nicht auf dem Server vorhanden!</h3>");
	}
%>
</form>
</body>
</html>
