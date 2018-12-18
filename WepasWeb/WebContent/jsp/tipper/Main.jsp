<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page  
		 import="de.wepas.constants.ApplConstants" 
		 import="de.wepas.jpa.EnTipper"
		 import="test.Hund"
		 import="tables.MainTable"
		 import="form.MainForm" 
		 import="org.apache.commons.logging.Log"
		 import="org.apache.commons.logging.LogFactory"
%>
<html> 
<head>
<!-- <LINK REL="SHORTCUT ICON" HREF="wepas.ico"> -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" type="text/css" href="<%=ApplConstants.CSSFILETIPPER%>">
<title>Wepas.de Main Page</title>
</head>

<body>
<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPMAIN)%> method="post">
<%
	Log log = LogFactory.getLog(this.getClass());	
	log.debug("Entering " + this.getClass().getName());
	MainForm form = new MainForm(request);
	if (form.isButtonLogout())
	{
		request.getSession().invalidate();
%>
		<jsp:forward page="<%=ApplConstants.JSPINDEX%>"></jsp:forward>
<%
	}
	if (form.isButtonTippenduty())
	{ 
%>
		<jsp:forward page="<%=ApplConstants.JSPTIPPENDUTY%>">
		<jsp:param name="tipperID" value="<%=form.getUser().getIdTipper()%>"/>
		</jsp:forward>
<%
	}
	if (form.isButtonTippenfree())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPTIPPENFREE%>">
		<jsp:param name="tipperID" value="<%=form.getUser().getIdTipper()%>"/>
		</jsp:forward>
<%
	}
	if (form.isButtonForum())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPFORUM%>"></jsp:forward>
<%
	}
	if (form.isButtonAdmin())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPADMINNAVI%>"></jsp:forward>
<%
	}
	if (form.isButtonStorno())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPSTORNO%>"></jsp:forward>
<%
	}
	if (form.isButtonStatistik())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPSTATISTIK%>"></jsp:forward>
<%
	}
	if (form.isButtonLaenderranking())  
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPLAENDERRANKING%>"></jsp:forward>
<%
	}
	if (form.isButtonTabelle())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPGESAMTTABELLE%>"></jsp:forward>
<%
	} 
	if ((form.isButtonLivegoals() 
			&& request.getSession().getAttribute(ApplConstants.STARTLIVEGOALS) == null) 
			|| request.getSession().getAttribute(ApplConstants.STARTLIVEGOALS) == "LIVE")
	{
		request.getSession().setAttribute(ApplConstants.STARTLIVEGOALS, "LIVE"); 
%>
		<jsp:forward page="<%=ApplConstants.JSPLIVEGOALS%>"></jsp:forward>
<%
	}
	if (form.getInfoSpielId() > 0)
	{
		request.getSession().setAttribute(ApplConstants.DETAILSPIEL, form.getInfoSpielId());
%>
		<jsp:forward page="<%=ApplConstants.JSPDETAIL%>"></jsp:forward>
<%
	}
	Hund h = (Hund) this.getServletContext().getAttribute("hund");   //Warum auch immer
	if (request.getSession().getAttribute(ApplConstants.NAVITABLE) != null)       
	{
		MainTable maintable = (MainTable) request.getSession().getAttribute(ApplConstants.NAVITABLE);
		EnTipper tipper = (EnTipper) form.getUser();
		if (tipper.getIdTipper() == Integer.parseInt(getServletContext().getInitParameter("adminuser1"))) 
		{
			maintable.setAdminuser(true);
		}
%>
<table class='<%=ApplConstants.CSSTABLE1%>'>
<thead><%=maintable.getHtmlThead(form.isSpieltaganzeige())%></thead>
<tfoot><%=maintable.getHtmlTfoot()%></tfoot>  
<tbody><%=maintable.getHtmlTbody(form.isSpieltaganzeige())%></tbody>
</table>
<%		
	}
%>
</form>
</body>
</html>
