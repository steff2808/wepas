<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
         import="de.wepas.constants.ApplConstants"
         import="form.StatistikForm"
         import="tables.StatistikTable"
         import="de.wepas.util.HtmlTags"
%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=ApplConstants.CSSFILETIPPER%>">
<title>Statistikseite</title>
</head>

<body>
<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPSTATISTIK)%> method="post">
<h1>Statistikseite</h1>
<p>Diverse Statistiken - Um mehr über die einzelne Spalte zu erfahren bitte mit dem Mauszeiger auf die Überschrift gehen.<p>

<%
	StatistikForm form = new StatistikForm(request);
	if(form.isButtonNavigation())
{
%>
		<jsp:forward page="<%=ApplConstants.JSPMAIN%>"></jsp:forward>
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
	if(form.isButtonSupertreffer())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPSUPERTREFFER%>"></jsp:forward>
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
	 		out.print(HtmlTags.wrapTD(HtmlTags.createSUBMITdisabled("statistik", "Statistik", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("highscore", "Highscore", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("treppchen", "Treppchen", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
%>		
		</tr>
		<tr>
<%
			out.print(HtmlTags.wrapTD(HtmlTags.createNBSP(1), ApplConstants.CSSNOBORDER));
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("supertreffer", "6er Volltreffer", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("treffer", "Volltreffer", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
			out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("blinde", "Blinde Tipps", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
%>
		</tr>
	</table>
	<br>
	<table class='<%=ApplConstants.CSSTABLE1%>'>
<%   
	StatistikTable ht = (StatistikTable)request.getSession().getAttribute(ApplConstants.STATISTIKTABLE);
	out.println(ht.getHtml());
%>	
	</table>	
</form>
</body>
</html>
