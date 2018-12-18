<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page  
		 import="de.wepas.constants.ApplConstants" 
		 import="de.wepas.jpa.EnTipper"
		 import="de.wepas.jpa.EnTipp"
		 import="de.wepas.jpa.EnSpiel"
		 import="form.LivegoalsForm"
		 import="de.wepas.util.HtmlTags"  
		 import="util.LookupRemoteService"
		 import="de.wepas.livegoals.*"
		 import="org.apache.commons.logging.Log"
		 import="org.apache.commons.logging.LogFactory"
%>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="refresh" content="30"> 
<link rel="stylesheet" type="text/css" href="<%=ApplConstants.CSSFILETIPPER%>"> 
<title>Wepas.de Livegoals</title>
</head>

<body>
<form action=<%=response.encodeURL(request.getContextPath() +  ApplConstants.JSPLIVEGOALS)%> method="post">
<%
	Log log = LogFactory.getLog(this.getClass());	
	log.debug("Entering " + this.getClass().getName());
	LivegoalsForm form = new LivegoalsForm(request); 
	if(form.isButtonNavigation()) 
	{
		request.getSession().setAttribute(ApplConstants.STARTLIVEGOALS, null);
%>
		<jsp:forward page="<%=ApplConstants.JSPMAIN%>"></jsp:forward>
<%
	}
	if (form.isButtonWorstcase())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPWORSTCASE%>"></jsp:forward>
<%
	}
	if (form.isButtonBestcase()) 
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPBESTCASE%>"></jsp:forward>
<%
	}

%>		
<hr/>
<%
	LivegoalsBeanRemote livegoalsSingleton = LookupRemoteService.lookupLivegoalsSingletonRemote();
	EnTipper user = (EnTipper) request.getSession().getAttribute(ApplConstants.USER);
	if(user == null)
	{
		throw new RuntimeException("User ist null in Livegoals.jsp");
	}
	out.print(livegoalsSingleton.getLivegoalsObject().getHtmltabelleaktuell().replaceFirst(".*<td class='css_hell' width='200'>"
			+ user.getAtTipperVorname() + " " + user.getAtTipperName() + "</td>.*"
			, "<td class='css_eigen' width='200'>"+ user.getAtTipperVorname() + " " + user.getAtTipperName() + "</td>"));
%>
</form>
</body>
</html>