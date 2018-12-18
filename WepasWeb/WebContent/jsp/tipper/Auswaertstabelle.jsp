<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
         import="de.wepas.constants.ApplConstants"
         import="form.TabelleForm"
         import="tables.TabelleTable"
         import="de.wepas.util.HtmlTags"
%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=ApplConstants.CSSFILETIPPER%>">
<title>Bundesliga Auswärtstabelle</title>
</head>
<body>
<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPAUSWAERTSTABELLE)%> method="post">
<% 
	TabelleForm form = new TabelleForm(request);
	if(form.isButtonNavigation())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPMAIN%>"></jsp:forward>
<%		
	}
	if(form.isButtonGesamttabelle())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPGESAMTTABELLE%>"></jsp:forward>
<%		
	}
	if(form.isButtonHeimtabelle())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPHEIMTABELLE%>"></jsp:forward>
<%		
	}
	if(form.isButtonTippertabelle())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPTIPPERTABELLE%>"></jsp:forward>
<%		
	}
%>
	<table class='<%=ApplConstants.CSSTABLE1%>'>
		<tr>
<%
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("navigation", "Zurück", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
//	 		out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("gesamttabelle", "Tabelle", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
//			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("heimtabelle", "Heimtabelle", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
//			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMITdisabled("auswaertstabelle", "Auswärtstabelle", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
//			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("tippertabelle", "Meine Tabelle", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
%>		
		</tr>
	</table>
	<br>
	<table class='<%=ApplConstants.CSSTABLE1%>'>
<%  
	TabelleTable tt = (TabelleTable)request.getSession().getAttribute(ApplConstants.TABELLETABLE);
	out.println(tt.getHtmlRow());
%>	
	</table>	 
</form>
</body>
</html>
