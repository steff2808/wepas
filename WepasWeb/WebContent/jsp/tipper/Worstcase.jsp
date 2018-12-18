<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
         import="de.wepas.constants.ApplConstants"
         import="de.wepas.jpa.EnTipper"
         import="form.WorstcaseForm" 
         import="tables.*" 
         import="de.wepas.util.HtmlTags" 
         import="util.LookupRemoteService"
         import="de.wepas.livegoals.*"
         import="java.util.Vector"
         import="java.util.Iterator"
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=ApplConstants.CSSFILETIPPER%>">
<title>Worstcase</title>
</head>

<body>
<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPBESTCASE)%> method="post">
<h1>Worstcase</h1>
<p>Wenn es für mich richtig blöde läuft:<p>
<%
	WorstcaseForm form = new WorstcaseForm(request); 
	if(form.isButtonBestcase()) 
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPBESTCASE%>"></jsp:forward>
<%
	}
	if(form.isButtonLivegoals())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPLIVEGOALS%>"></jsp:forward>
<%
	}
%>
	<table class='<%=ApplConstants.CSSTABLE1%>'>
		<tr>
<%
out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("livegoals", "Zurück", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("bestcase", "Bestmöglich", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
out.print(HtmlTags.wrapTD(HtmlTags.createSUBMITdisabled("worstcase", "Schlechtmöglich", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
%>		
		</tr>
	</table>
	<br>
<%
// 	if (request.getSession().getAttribute(ApplConstants.BESTCASETABLE) != null)       
// 	{  
// 		WorstcaseTable bt = (WorstcaseTable) request.getSession().getAttribute(ApplConstants.BESTCASETABLE);
// 		EnTipper tipper = (EnTipper) form.getUser();
// 		if (tipper.getIdTipper() == Integer.parseInt(getServletContext().getInitParameter("adminuser1"))) 
// 		{ 
// 	bt.setAdminuser(true);
// 		}
// 		try
// 		{
	LivegoalsBeanRemote livegoalsSingleton = LookupRemoteService.lookupLivegoalsSingletonRemote();
	EnTipper user = (EnTipper) request.getSession().getAttribute(ApplConstants.USER);
	if(user == null)
	{
		throw new RuntimeException("User ist null in Livegoals.jsp");
	}
	 
	WorstcaseTable worstcasetable = new WorstcaseTable(livegoalsSingleton.getLivegoalsObject(), user);
	worstcasetable.analyse();
	
	out.println(worstcasetable.getHtml()); 
	
	out.println("<br>Worstspiel1: " + worstcasetable.getWorstlivespiel1().getTitle() 
			+ " - " + worstcasetable.getWorstlivespiel1().getNowToreheim() 
			+ ":" 	+  worstcasetable.getWorstlivespiel1().getNowToregast());
	if(worstcasetable.getWorstlivespiel2() != null)
	{
		out.println("<br>Worstspiel2: " + worstcasetable.getWorstlivespiel2().getTitle() 
				+ " - " + worstcasetable.getWorstlivespiel2().getNowToreheim() 
				+ ":" 	+  worstcasetable.getWorstlivespiel2().getNowToregast());
	}
%>

</form>
</body>
</html>
