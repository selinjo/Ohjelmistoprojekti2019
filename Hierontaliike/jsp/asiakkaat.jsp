<%@include file="header.jsp" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Asiakas"%>
<%! @SuppressWarnings("unchecked") %>
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
<fieldset id="asiakasHaku">
	<legend>Asiakkaat</legend>	
	<input type="text" name="hakusana" id="hakusana" width="700">
	<img src="images/search.png" title="Etsi" class="icon" id="haeAsiakas">
</fieldset>
<br>
<fieldset id="asiakkaat">
	<div id="poistonVahvistus">
	<span id="poistettavaNimi"></span>
	<img src="images/accept.png" title="Poista" class="icon" id="vahvistaPoisto">
	<img src="images/cancel.png" title="Peruuta" class="icon" id="peruutaPoisto">
	</div>
	<div id="asiakasTiedot">
	<img src="images/cancel.png" title="Sulje" class="icon" id="suljeTiedot"><br><br>
	<span id="tiedotNimi"></span>
	</div>
<table class="table">

<%
if(request.getAttribute("asiakkaat")!=null){
ArrayList<Asiakas> asiakkaat = (ArrayList<Asiakas>)request.getAttribute("asiakkaat");
	for(int i = 0; i < asiakkaat.size(); i++) {
		out.print("<tr>");
		out.print("<td style='display:none;'>" + asiakkaat.get(i).getAsiakas_id() + "</td>");
		out.print("<td>" + asiakkaat.get(i).getSukunimi() + " " + asiakkaat.get(i).getEtunimi() +"</td>");
		out.print("<td align='right'>");
		out.print("<img src='images/details.png' class='icon tiedot' id='tiedot_"+asiakkaat.get(i).getAsiakas_id()+"' name='"
				+asiakkaat.get(i).getSukunimi()+" "+asiakkaat.get(i).getEtunimi()+"<br>"
				+asiakkaat.get(i).getKatuosoite()+"<br>"
				+asiakkaat.get(i).getPostinumero()+ " " +asiakkaat.get(i).getPostitoimipaikka()+"<br>"
				+asiakkaat.get(i).getPuhelin()+"<br>"
				+asiakkaat.get(i).getSahkoposti()+"<br>"
				+asiakkaat.get(i).getLisatiedot()+"' title='Kaikki tiedot'>&nbsp;");
		out.print("<img src='images/modify.png' class='icon muokkaa' id='muokkaa_"+asiakkaat.get(i).getAsiakas_id()+"' title='Muokkaa'>&nbsp;");
		out.print("<img src='images/delete.png' class='icon poista' id='poista_"+asiakkaat.get(i).getAsiakas_id()+"' name='"+asiakkaat.get(i).getEtunimi()+" "+asiakkaat.get(i).getSukunimi()+"' title='Poista'>");
		out.print("</td>");
		out.print("</tr>");
	}
}
%>

</table>
</fieldset>
<script>
$(document).ready(function(){
	$("#poistonVahvistus").hide();
	$("#asiakasTiedot").hide();
	
	$("#asiakasHaku").css({
		"margin": "auto",
		"width": "500px",
		"border-radius": "15px",
		"font-family": "Montserrat, sans-serif",
		"text-align": "center"
	});
	
	$("#hakusana").css({
		"margin": "auto",
		"width": "350px"
	});
	
	$("#asiakkaat").css({
		"margin": "auto",
		"width": "500px",
		"border-radius": "15px",
		"font-family": "Montserrat, sans-serif",
		"font-size": "18px"
	});
	
	if($("#kirjautunut_status").val()=="asiakas"){
		$('#menuAsiakkaat').hide();
		$('#menuHieronnat').hide();
		$('#menuHiTiedot').hide();
	}else if($("#kirjautunut_status").val()=="hieroja"){
		$('#menuAjanvaraus').hide();
		$('#menuAsHieronnat').hide();
		$('#menuAsTiedot').hide();
		$('.muokkaa').hide();
		$('.poista').hide();
	}else if($("#kirjautunut_status").val()=="admin"){
		$('#menuAjanvaraus').hide();
		$('#menuHieronnat').hide();
		$('#menuAsHieronnat').hide();
		$('#menuAsTiedot').hide();
		$('#menuHiTiedot').hide();
	}
});

$("#haeAsiakas").click(function(){
	document.location=encodeURI("Servlet_HaeAsiakkaat?hakusana=" + $("#hakusana").val());	
});

$(".muokkaa").click(function(){	
	document.location="Servlet_MuokkaaAsiakas?Asiakas_id=" + $(this)[0].id;
});

var poistettava_id;	
$(".poista").click(function(event){	
	poistettava_id=$(this)[0].id;
	$("#poistettavaNimi").html("Poistetaanko käyttäjä<br> " + $(this)[0].name + "?<br>");
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
	document.location="Servlet_PoistaAsiakas?Asiakas_id="+poistettava_id;
});

$("#peruutaPoisto").click(function(){
	$("#poistonVahvistus").hide();
});

var tiedot_id;
$(".tiedot").click(function(event){
	tiedot_id=$(this)[0].id;
	$("#tiedotNimi").html($(this)[0].name);
	$("#asiakasTiedot").css({ 
		"width": "250px", 
		"height": "250px",
		"background-color": "grey",
		"border-radius": "15px",
		"color": "white",
		"text-align": "left",
		"padding": "10px",
		"border": "1px solid black",
		"position": "absolute",
		"left": event.pageX +"px",
		"top": event.pageY +"px"
	});			
	$("#asiakasTiedot").show();
});

$("#suljeTiedot").click(function(){
	$("#asiakasTiedot").hide();
});
</script>
</body>
</html>