<%@include file="header.jsp" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Hieroja"%>
<%! @SuppressWarnings("unchecked") %>
<%
if(request.getAttribute("hierojat")==null) {
	response.sendRedirect("Servlet_HaeHierojat");
	return;
}
%>
<%
String kirjautunut;
if(session.getAttribute("Kirjautunut_status")!=null){
	kirjautunut=session.getAttribute("Kirjautunut_status").toString();
}else{
	kirjautunut="";
}
%>
<input type="hidden" id=kirjautunut_status name="kirjautunut_status" value="<%out.print(kirjautunut);%>"/>
<br><br>
<fieldset id="lisaaHieroja">
	<legend>Hierojat</legend>	
	<input type="button" value="+ Lisää hieroja" id="lisaaHierojaBtn" class="btn btn-success">
</fieldset>

<br><br>
<fieldset id="hierojat">
	<div id="poistonVahvistus">
	<span id="poistettavaNimi"></span>
	<img src="images/accept.png" title="Poista" class="icon" id="vahvistaPoisto">
	<img src="images/cancel.png" title="Peruuta" class="icon" id="peruutaPoisto">
	</div>

<table class="table">

<%
String polku = "images/uploaded/";
ArrayList<Hieroja> hierojat = (ArrayList<Hieroja>)request.getAttribute("hierojat");
		String kuva="";	
		for(int i = 0; i < hierojat.size(); i++) {
		out.print("<tr>");
		out.print("<td>" + hierojat.get(i).getEtunimi() + " " + hierojat.get(i).getSukunimi() +"<br>");
		out.print(hierojat.get(i).getPuhelin() + "<br>");
		out.print(hierojat.get(i).getSahkoposti() + "<br>");
		out.print(hierojat.get(i).getLisatiedot() + "<br><br></td>");
		kuva = hierojat.get(i).getKuva_id().replace("hieroja_" + hierojat.get(i).getHieroja_id(),"");
		out.print("<td><img title='"+kuva+"' id='kuva_"+kuva+"' src='"+polku+kuva+"' class='profilepic'></td>");
		out.print("<td>");
		out.print("<img src='images/modify.png' class='icon muokkaa' id='muokkaa_"+hierojat.get(i).getHieroja_id()+"' title='Muokkaa'><br><br>");
		out.print("<img src='images/delete.png' class='icon poista' id='poista_"+hierojat.get(i).getHieroja_id()+"' name='"+hierojat.get(i).getEtunimi()+" "+hierojat.get(i).getSukunimi()+"' title='Poista'>");
		out.print("</td>");
		out.print("</tr>");
	}
%>

</table>
</fieldset>
<script>
$(document).ready(function(){
	$("#poistonVahvistus").hide();
	
	$("#lisaaHieroja").css({
		"margin": "auto",
		"width": "500px",
		"border-radius": "15px",
		"font-family": "Montserrat, sans-serif"
	});
	
	$("#hierojat").css({
		"margin": "auto",
		"width": "500px",
		"border-radius": "15px",
		"font-family": "Montserrat, sans-serif",
		"font-size": "18px"
	});
	
	if($("#kirjautunut_status").val()=="asiakas"){
		$('#menuAsiakkaat').hide();
		$('#menuHieronnat').hide();
		$('.muokkaa').hide();
		$('.poista').hide();
		$('#lisaaHierojaBtn').hide();
		$('#menuHiTiedot').hide();
	}else if($("#kirjautunut_status").val()=="hieroja"){
		$('#menuAjanvaraus').hide();
		$('#menuAsHieronnat').hide();
		$('#menuAsTiedot').hide();
		$('.muokkaa').hide();
		$('.poista').hide();
		$('#lisaaHierojaBtn').hide();
	}else if($("#kirjautunut_status").val()=="admin"){
		$('#menuAjanvaraus').hide();
		$('#menuHieronnat').hide();
		$('#menuAsHieronnat').hide();
		$('#menuAsTiedot').hide();
		$('#menuHiTiedot').hide();
	}
});

$(".muokkaa").click(function(){	
	document.location="Servlet_MuokkaaHieroja?Hieroja_id=" + $(this)[0].id;
});

var poistettava_id;	
$(".poista").click(function(event){	
	poistettava_id=$(this)[0].id;
	$("#poistettavaNimi").html("Poistetaanko hieroja<br> " + $(this)[0].name + "?<br>");
	$("#poistonVahvistus").css({ 
		"width": "250px", 
		"height": "100px",
		"background-color": "grey",
		"border-radius": "15px",
		"color": "white",
		"text-align": "center",
		"padding": "10px",
		"border": "1px solid black",
		"position": "absolute",
		"left": event.pageX +"px",
		"top": event.pageY +"px"
	});			
	$("#poistonVahvistus").show();
});

$("#vahvistaPoisto").click(function(){
	document.location="Servlet_PoistaHieroja?Hieroja_id="+poistettava_id;
});

$("#peruutaPoisto").click(function(){
	$("#poistonVahvistus").hide();
});

$("#lisaaHierojaBtn").click(function(){	
	document.location="lisaahieroja.jsp";
});
</script>
</body>
</html>