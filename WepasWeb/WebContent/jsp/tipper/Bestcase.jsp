<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
         import="de.wepas.constants.ApplConstants"
         import="de.wepas.jpa.EnTipper"
         import="form.BestcaseForm" 
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
<title>Bestcase</title>
</head>

<body>
<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPBESTCASE)%> method="post">
<h1>Bestcase</h1>
<p>Wenn es für mich optimal läuft:<p>
<%
	BestcaseForm form = new BestcaseForm(request); 
	if(form.isButtonWorstcase()) 
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPWORSTCASE%>"></jsp:forward>
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
	out.print(HtmlTags.wrapTD(HtmlTags.createSUBMITdisabled("bestcase", "Bestmöglich", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
	out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("worstcase", "Schlechtmöglich", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
%>		
		</tr>
	</table>
	<br>
<%
// 	if (request.getSession().getAttribute(ApplConstants.BESTCASETABLE) != null)       
// 	{  
// 		BestcaseTable bt = (BestcaseTable) request.getSession().getAttribute(ApplConstants.BESTCASETABLE);
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
	
	BestcaseTable bestcasetable = new BestcaseTable(livegoalsSingleton.getLivegoalsObject(), user);
	bestcasetable.analyse();
	
	out.println(bestcasetable.getHtml()); 
	
	out.println("<br>Bestspiel1: " + bestcasetable.getBestlivespiel1().getTitle() 
			+ " - " + bestcasetable.getBestlivespiel1().getNowToreheim() 
			+ ":" 	+  bestcasetable.getBestlivespiel1().getNowToregast());
	if(bestcasetable.getBestlivespiel2() != null)
	{
		out.println("<br>Bestspiel2: " + bestcasetable.getBestlivespiel2().getTitle() 
				+ " - " + bestcasetable.getBestlivespiel2().getNowToreheim() 
				+ ":" 	+  bestcasetable.getBestlivespiel2().getNowToregast());
	}
%>

</form>
</body>
</html>
