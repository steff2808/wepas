<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
         import="de.wepas.constants.ApplConstants"
         import="form.LaenderrankingForm"
         import="tables.LaenderTable"
         import="de.wepas.util.HtmlTags"
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=ApplConstants.CSSFILETIPPER%>">
<title>Länderranking</title>
</head>

<body>
<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPLAENDERRANKING)%> method="post">
<h1>Länderranking</h1>
<p>Durchschnittspunkte pro Tipp und Bundesland<p>
<% 
	LaenderrankingForm form = new LaenderrankingForm(request);
	if(form.isButtonNavigation())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPMAIN%>"></jsp:forward>
<%		
	}
%>

	<table class='<%=ApplConstants.CSSTABLE1%>'>

<%
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("navigation", "Zurück", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
%>		
	</table>
	<br>
	<table class='<%=ApplConstants.CSSTABLE1%>'>
<%  
	LaenderTable lt = (LaenderTable)request.getSession().getAttribute(ApplConstants.LAENDERTABLE);
	out.println(lt.getHtmlRow());
%>	
	</table>	
</form>
</body>
</html>
