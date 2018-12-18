<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page 
         import="de.wepas.constants.ApplConstants"
         import="de.wepas.jpa.EnForum"
         import="util.LookupRemoteService"
         import="de.wepas.connector.ForumBeanRemote"
         import="javax.rmi.PortableRemoteObject"
         import="java.util.Vector"
         import="java.util.Iterator"
         import="form.ForumForm"
         import="de.wepas.util.HtmlTags"
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=ApplConstants.CSSFILETIPPER%>">
<title>Forum</title>
</head>

<body>
<form action=<%=response.encodeURL(request.getContextPath() + ApplConstants.JSPFORUM)%> method="post">
<h1>Forum</h1>
<%
	ForumForm form = new ForumForm(request); 
	if(form.isButtonNavigation()) 
	{
%>
		<jsp:forward page="<%=ApplConstants.JSPMAIN%>"></jsp:forward>
<%
	}
%>
	<table class='<%=ApplConstants.CSSTABLE1%>'>
		<tr> 
			<td><h3>Hier bitte deinen Kommentar eintragen und mit "Speichern" abschicken</h3></td>
		</tr>
		<tr>
<%
	out.print(HtmlTags.wrapTD(HtmlTags.createTEXTFIELD("text2", 160, 160), null));
%>		
		</tr>
		<tr>
<%
	out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("speichern", "Speichern", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
%>		
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
<%
	out.print(HtmlTags.wrapTD(HtmlTags.createSUBMIT("navigation", "Zurück", ApplConstants.CSSSUBMIT2), ApplConstants.CSSNOBORDER));
%>		
		</tr>
	</table>
	<h3>Bisherige Einträge:</h3>
<%
	ForumBeanRemote fbr = LookupRemoteService.lookupForumBeanRemote();
	try
	{
		EnForum[] forumarray = fbr.getForum();
		Vector<EnForum> beitraege = new Vector<EnForum>(forumarray.length);
		for(int i = 0;i < forumarray.length; i++)
		{
			beitraege.add(forumarray[i]);
		}
		Iterator<EnForum> iter1 = beitraege.iterator();
%>
		<table class='<%=ApplConstants.CSSTABLE1%>'>
<%
		while(iter1.hasNext())
		{
			EnForum beitrag = iter1.next();
%>
			<tr> 
				<td class='<%=ApplConstants.CSSWEISSL%>'><%=beitrag.getAtForumText1()%></td><td class='<%=ApplConstants.CSSEIGEN%>'><%=beitrag.getAtForumText2()%></td>
			</tr>
<%
		}
	}
	catch (Exception e)
	{
		
	}
%>
	</table>
</form>
</body>
</html>
