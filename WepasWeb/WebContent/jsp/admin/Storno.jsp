<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page 
		 import="java.util.Iterator" 
         import="de.wepas.constants.ApplConstants"
         import="form.StornoForm"
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=ApplConstants.CSSFILEADMIN%>">
<title>Tippformular</title>
</head>

<body>
<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPSTORNO)%> method="post">
<h1>Stornoseite</h1>
<input type='submit' name='navigation' value='Zurück' class='<%=ApplConstants.CSSSUBMIT2%>'>
<input type='submit' name='ausfuehren' value='Ausführen' class='<%=ApplConstants.CSSSUBMIT2%>'>
<%
	StornoForm form = new StornoForm(request);
	form.todos(request);
	
	if(form.isButtonNavigation())
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPMAIN%>"></jsp:forward>
<%		
		return;
	}
	if(form.isButtonAusfuehren())
	{
		form.action(request);
%>
		<jsp:forward page="<%=ApplConstants.JSPMAIN%>"></jsp:forward>
<%		
		return;
	}


//--->Fehlerbehandlung
	if(form.getMessage().length() > 2);
	{
		out.println("<br><h3>" + form.getMessage() + "</h3>");
	}

	Iterator<String> iter1 = form.getMessages().iterator();
	while(iter1.hasNext())
	{
		out.println(iter1.next() + "<br/>");
	}
%>
</form>	
</body>
</html>