<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page 
         import="de.wepas.constants.ApplConstants"
         import="form.TippenForm"
         import="de.wepas.connector.TippabgabeStatefulBeanRemote"
         import="tables.TippenTable"
         import="de.wepas.util.HtmlTags"
%> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="<%=ApplConstants.CSSFILETIPPER%>">
	<title>Tippformular</title>
</head>
<body>
	<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPTIPPENDUTY)%> method="post">
	<h1>Tippformular</h1>
<%
	TippenForm form = new TippenForm(request);

	if(form.isButtonNavigation())
	{
		if(session.getAttribute(ApplConstants.TIPPABGABE_STATEFULBEAN) != null)
		{
			TippabgabeStatefulBeanRemote tsbr = (TippabgabeStatefulBeanRemote)session.getAttribute((ApplConstants.TIPPABGABE_STATEFULBEAN));
			tsbr.remove();
			session.setAttribute(ApplConstants.TIPPABGABE_STATEFULBEAN, null);
		}
%>
		<jsp:forward page="<%=ApplConstants.JSPMAIN%>"></jsp:forward>
<%	
	}	
		
	if(form.getStatusflag() == '9' || form.getStatusflag() == 'S' || form.getStatusflag() == 'T')
	{
		out.println(HtmlTags.wrapH3(form.getMessage()));
	}
	if(form.getStatusflag() != '9')
	{
		TippabgabeStatefulBeanRemote tippabgabeStateful = (TippabgabeStatefulBeanRemote) request.getSession()
				.getAttribute((ApplConstants.TIPPABGABE_STATEFULBEAN));
%>
		<p>
			<%=tippabgabeStateful.getTipper().getAtTipperVorname()%> 
			tippt den 
			<%=tippabgabeStateful.getSpieltag().getAtSpieltagText()%>
			, bitte mit 
			<%=tippabgabeStateful.getSpieltag().getAtSpieltagSupertipps()%> 
			Supertipps!
		</p>	

		<table class='<%=ApplConstants.CSSTABLE1%>'>
<%   
		TippenTable tippentable = (TippenTable)request.getSession().getAttribute(ApplConstants.TIPPENTABLE);
		out.println(tippentable.getHTML());
%>	
		</table>
		<input type='submit' name='speichern' value='Speichern' class='<%=ApplConstants.CSSSUBMIT2%>'>
<%
	}
%>	
	<input type='submit' name='navigation' value='Zurück' class='<%=ApplConstants.CSSSUBMIT2%>'>		
	</form>	
</body>
</html>
