<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page 
         import="de.wepas.constants.ApplConstants"
         import="form.SupertrefferForm"
         import="tables.SupertrefferTable"
         import="de.wepas.util.HtmlTags"
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=ApplConstants.CSSFILETIPPER%>">
<title>Supertreffer</title>
</head>

<body>
<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPSUPERTREFFER)%> method="post">
<h1>Supertreffer</h1>
<%
	SupertrefferForm form = new SupertrefferForm(request);
	if(form.isButtonNavigation())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPMAIN%>"></jsp:forward>
<%		
	}
	if(form.isButtonStatistik())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPSTATISTIK%>"></jsp:forward>
<%		
	}
	if(form.isButtonHighscore())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPHIGHSCORE%>"></jsp:forward>
<%		
	}
	if(form.isButtonTreppchen())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPTREPPCHEN%>"></jsp:forward>
<%		
	}
	if(form.isButtonTreffer())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPTREFFER%>"></jsp:forward>
<%		
	}
	if(form.isButtonBlinde())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPBLINDE%>"></jsp:forward>
<%		
	}
%>
	<table class='<%=ApplConstants.CSSTABLE1%>'>
		<tr>
<%
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("navigation", "Zurück", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("statistik", "Statistik", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("highscore", "Highscore", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("treppchen", "Treppchen", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
%>		
		</tr>
		<tr>
<%
			out.print(HtmlTags.wrapTD(HtmlTags.createNBSP(1), ApplConstants.CSSNOBORDER));
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMITdisabled("supertreffer", "6er Volltreffer", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("treffer", "Volltreffer", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("blinde", "Blinde Tipps", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
%>
		</tr>
	</table>
	<br>
	<table class='<%=ApplConstants.CSSTABLE1%>'>
<%   
	SupertrefferTable st = (SupertrefferTable)request.getSession().getAttribute(ApplConstants.SUPERTREFFERTABLE); 
	out.println(st.getHtmlRow());
%>	
	</table>	
</form>
</body>
</html>
