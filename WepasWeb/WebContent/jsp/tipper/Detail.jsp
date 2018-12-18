<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
         import="de.wepas.constants.ApplConstants"
         import="form.DetailForm"
         import="tables.DetailTable"
         import="de.wepas.util.HtmlTags" 
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=ApplConstants.CSSFILETIPPER%>">
<title>Detailanzeige </title>
</head>

<body>
<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPDETAIL)%> method="post">
<% 
	DetailForm form = new DetailForm(request);
	if(form.isButtonNavigation())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPMAIN%>"></jsp:forward>
<%		
	}
%>

<h1>Detailanzeige der Tipps zu einem Spiel</h1>
	<table class='<%=ApplConstants.CSSTABLE1%>'>
		<tr>
<%
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("navigation", "Zurück", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
%>		
		</tr>
	</table>
	<br>
	<p>Alle Tipps sortiert nach Tendenzen</p>
	<table class='<%=ApplConstants.CSSTABLE1%>'>
<%  
 	DetailTable dt = (DetailTable)request.getSession().getAttribute(ApplConstants.DETAILTABLE); 
 	out.println(dt.getHtmlRow1());
%>	
	</table>	
	<br>
	<p>Alle Tipps sortiert nach Namen</p>
	<table class='<%=ApplConstants.CSSTABLE1%>'>
<%  
 	out.println(dt.getHtmlRow2()); 
%>	
	</table>	
	<table class='<%=ApplConstants.CSSTABLE1%>'>
		<tr>
<%
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("navigation", "Zurück", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
%>		
		</tr>
	</table>
</form>
</body> 
</html>
