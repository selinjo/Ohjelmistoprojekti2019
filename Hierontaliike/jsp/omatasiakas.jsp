<%@include file="header.jsp" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Hieroja"%>
<%@ page import="model.Asiakas"%>

<%
Asiakas asiakas = null;
if(request.getAttribute("asiakas")==null) {
	response.sendRedirect("Servlet_MuokkaaAsiakasTiedot");
	return;
}else if( request.getAttribute("asiakas")!=null){
	asiakas = (Asiakas)request.getAttribute("asiakas");
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

<fieldset id="muokkaaAsiakas" class="container">
	<legend>Omat tiedot</legend>
	<form action="Servlet_MuokkaaAsiakasTiedot" method="post" id="muokkaaAsiakasLomake">
		<label for="etunimi">Etunimi:</label>
			<input type="text" name="etunimi" id="etunimi" value="<%out.print(asiakas.getEtunimi());%>" class="form-control"><br>	
		<label>Sukunimi</label>
			<input type="text" name="sukunimi" id="sukunimi" value="<%out.print(asiakas.getSukunimi());%>" class="form-control"><br>	
		<label>Katuosoite</label>
			<input type="text" name="katuosoite" id="katuosoite" value="<%out.print(asiakas.getKatuosoite());%>" class="form-control"><br>	
		<label>Postinumero</label>
			<input type="text" name="postinumero" id="postinumero" value="<%out.print(asiakas.getPostinumero());%>" class="form-control">&nbsp;
			<span id="postitoimipaikka"></span>
			<span id="postitoimivirhe"></span><br>
		<label>Sähköposti</label>
			<input type="text" name="sahkoposti" id="sahkoposti" value="<%out.print(asiakas.getSahkoposti());%>" class="form-control"><br>	
		<label>Salasana</label>
			<input type="password" name="salasana" id="salasana" value="" class="form-control"><br>	
		<label>Toista salasana</label>
			<input type="password" name="salasana2" id="salasana2" value="" class="form-control"><br>	
		<label>Puhelin</label>
			<input type="text" name="puhelin" id="puhelin" value="<%out.print(asiakas.getPuhelin());%>" class="form-control"><br>
		<label>Lisätietoja</label>	
			<textarea rows="4" cols="30" name="lisatiedot" id="lisatiedot" class="form-control"><%out.print(asiakas.getLisatiedot());%></textarea><br>
		<label></label>	
			<input type="submit" value="Tallenna muutokset" id="muokkaaAsiakasBtn" class="btn btn-success">
			
		<input type="hidden" name="Asiakas_id" value="<%out.print(asiakas.getAsiakas_id());%>">
	</form><br><br>
</fieldset>

<script>
$(document).ready(function(){
	
	$("#muokkaaAsiakas").css({
		"margin": "auto",
		"width": "400px",
		"border-radius": "15px",
		"font-family": "Montserrat, sans-serif"
	});
	
	$("#postinumero").blur(function(){
	    $.ajax({url: "Servlet_Postitoimi_Ajax?postinumero="+$("#postinumero").val(), success: function(result){
	        $("#postitoimipaikka").html(result);
	    }});
	});
	
	$("#muokkaaAsiakasLomake").validate({						
		rules: {
			etunimi:  {
				required: true,
				minlength: 2				
			},	
			sukunimi:  {
				required: true,
				minlength: 2				
			},
			katuosoite:  {
				required: true,
				minlength: 4
			},	
			postinumero:  {
				required: true,
				number: true				
			},			
			sahkoposti:  {
				required: true,
				email: true			
			},
			puhelin:  {				
				minlength: 3				
			},
			salasana:  {
				minlength: 8				
			},
			salasana2:  {
				equalTo: salasana
			}			
		},
		messages: {
			etunimi: {     
				required: "Puuttuu",
				minlength: "Etunimi liian lyhyt"			
			},
			sukunimi: {
				required: "Puuttuu",
				minlength: "Sukunimi liian lyhyt"
			},
			katuosoite: {
				required: "Puuttuu",
				minlength: "Osoite liian lyhyt"
			},
			postinumero: {
				required: "Puuttuu",
				number: "Syötä vain numeroita"				
			},	
			sahkoposti: {
				required: "Puuttuu",
				email: "Tarkista sähköpostiosoite"			
			},
			puhelin:  {				
				minlength: "Liian lyhyt"				
			},
			salasana: {
				minlength: "Väh. 8 merkkiä"				
			},
			salasana2: {
				equalTo: "Salasanat eivät täsmää"
			}	
		},			
		submitHandler: function (form) {	
			if ($("#postitoimipaikka").html()!=""){
				document.forms["muokkaaAsiakasLomake"].submit();
			}else{
				$("#postitoimipaikka").html("<label class='error'>Anna postinumero</label>");
			}			
		}		
	});   
	//Käynnistetään postitoimipaikan haku ajaxilla käymällä postinumero -kentässä ja poistumalla sieltä
	$("#postinumero").focus();
	$("#etunimi").focus();
	
	if($("#kirjautunut_status").val()=="asiakas"){
		$('#menuAsiakkaat').hide();
		$('#menuHieronnat').hide();
		$('#menuHiTiedot').hide();
	}else if($("#kirjautunut_status").val()=="hieroja"){
		$('#menuAjanvaraus').hide();
		$('#menuAsHieronnat').hide();
		$('#menuAsTiedot').hide();
	}else if($("#kirjautunut_status").val()=="admin"){
		$('#menuAjanvaraus').hide();
		$('#menuHieronnat').hide();
		$('#menuAsHieronnat').hide();
		$('#menuAsTiedot').hide();
		$('#menuHiTiedot').hide();
	}
});
</script>
</body>
</html>