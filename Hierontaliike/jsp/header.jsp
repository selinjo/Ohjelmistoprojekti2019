<%
if(session.getAttribute("savePath")==null){
	String savePath ="C:/Users/fin4jseli/Desktop/OP_Harjoitustyo/Hierontaliike/WebContent/images/uploaded/";
	session.setAttribute("savePath", savePath);		
}
if(!request.getRequestURI().equals("/Hierontaliike/index.jsp")		//jos kutsutaan jotakin muuta kuin index.jsp tai rekisterointi.jsp -sivua
		&& !request.getRequestURI().equals("/Hierontaliike/rekisterointi.jsp")){
	if(session.getAttribute("Kirjautunut_nimi")==null){			//ja kirjautumista ei ole tapahtunut
		response.sendRedirect("index.jsp");
	}
}
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/jquery.validate.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script><!-- datepickeriä varten -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css"><!-- datepickeriä varten -->



<title>Hierontaliike</title>
<style>
.header{
	width:100%;
	height: 175px;
	background-color: #2d272a;
	text-align: center;
}
.headerKirjautunut{
	width:100%;
	background-color: #2d272a;
	padding: 14px 30px;
	font-family: Montserrat, sans-serif;
	font-size: 25px;
	color: white;
	text-align:center;
}
.header h1{
	color: white;
	margin-top: 0px;
	font-family: Montserrat, sans-serif;
	font-size: 100px;
	text-align:center;
}
.menu ul{
	list-style-type: none;
    margin: 0px;
    padding: 0px;
    overflow: hidden;
    background-color: #333;
	display: flex;
	justify-content: center;
}
.menu li {
	float: left;
}
.menu li a{
	font-family: Montserrat, sans-serif;
	color: white;
	text-align: center;
	display: block;
	padding: 14px 30px;
	text-decoration: none;
}
.menu li a:hover:not(.active) {
    background-color: #111;
}
label.error {
    color:red;
    font-size: 15px;
}
.kirjauduUlos {
	background-color: black;
}
.icon{
	height:20px;
}
.profilepic{
	height:100px;
}
</style>
</head>
<body>

<div class="header" id="header">
	<h1>HIERONTALIIKE</h1>
</div>

<div class="headerKirjautunut" id="headerKirjautunut">
<%
if(session.getAttribute("Kirjautunut_nimi")!=null){
out.print("Hei, ");
out.print(session.getAttribute("Kirjautunut_nimi"));
out.print("!");
}
%>
</div>

<div class="menu" id="menu">
	<ul>
		<li><a href="ajanvaraus.jsp" id="menuAjanvaraus">Ajanvaraus</a></li>
		<li><a href="hierojat.jsp" id="menuHierojat">Hierojat</a></li>
		<li><a href="asiakkaat.jsp" id="menuAsiakkaat">Asiakkaat</a></li>
		<li><a href="hieronnat.jsp" id="menuHieronnat">Omat hieronnat</a></li>
		<li><a href="asiakkaanhieronnat.jsp" id="menuAsHieronnat">Omat varaukset</a></li>
		<li><a href="omatasiakas.jsp" id="menuAsTiedot">Omat tiedot</a></li>
		<li><a href="omathieroja.jsp" id="menuHiTiedot">Omat tiedot</a></li>
		<li class="kirjauduUlos"><a href="Servlet_Kirjaudu?out=1" id="menuKirjauduUlos">Kirjaudu ulos</a></li>
	</ul>
</div>

<%
if(session.getAttribute("Kirjautunut_nimi")==null){		
	out.println("<script>");
	out.println("$('#menuAjanvaraus').hide();");
	out.println("$('#menuHieronnat').hide();");
	out.println("$('#menuAsHieronnat').hide();");
	out.println("$('#menuAsTiedot').hide();");
	out.println("$('#menuHiTiedot').hide();");
	out.println("$('#menuHierojat').hide();");
	out.println("$('#menuKirjauduUlos').hide();");
	out.println("$('#menuAsiakkaat').hide();");
	out.println("</script>");	
}
%>