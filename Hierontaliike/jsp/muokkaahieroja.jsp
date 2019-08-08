<%@include file="header.jsp" %>
<%@ page import="model.Hieroja"%> 

<%
Hieroja hieroja = null;
if( request.getAttribute("hieroja")!=null){
	hieroja = (Hieroja)request.getAttribute("hieroja");	
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

<fieldset id="muokkaaHieroja" class="container">
	<legend>Muokkaa hierojaa <%out.print(hieroja.getEtunimi() + " " + hieroja.getSukunimi());%></legend>
	<form action="Servlet_MuokkaaHieroja" method="post" id="muokkaaHierojaLomake">
		<label>Profiilikuva</label><br>
		<%
		String polku = "images/uploaded/";
		String kuva = hieroja.getKuva_id().replace("hieroja_" + hieroja.getHieroja_id(),"");
		out.print("<img title='"+kuva+"' id='kuva_"+kuva+"' src='"+polku+kuva+"' class='profilepic'>");
		 %>
			<button type="button" class="btn btn-primary" id="lisaaKuva">Lisää/muokkaa kuvia</button><br><br>
	
		<label for="etunimi">Etunimi</label>
			<input type="text" name="etunimi" id="etunimi" value="<%out.print(hieroja.getEtunimi());%>" class="form-control"><br>	
		<label for="sukunimi">Sukunimi</label>
			<input type="text" name="sukunimi" id="sukunimi" value="<%out.print(hieroja.getSukunimi());%>" class="form-control"><br>	
		<label for="sahkoposti">Sähköposti</label>
			<input type="text" name="sahkoposti" id="sahkoposti" value="<%out.print(hieroja.getSahkoposti());%>" class="form-control"><br>	
		<label for="salasana">Salasana</label>
			<input type="password" name="salasana" id="salasana" value="" class="form-control"><br>	
		<label for="salasana2">Toista salasana</label>
			<input type="password" name="salasana2" id="salasana2" value="" class="form-control"><br>	
		<label for="puhelin">Puhelin</label>
			<input type="text" name="puhelin" id="puhelin" value="<%out.print(hieroja.getPuhelin());%>" class="form-control"><br>
		<label for="lisatiedot">Lisätietoja</label>	
			<textarea rows="4" cols="30" name="lisatiedot" id="lisatiedot" class="form-control"><%out.print(hieroja.getLisatiedot());%></textarea><br>
		<label></label>	
			<input type="submit" value="Vahvista muutos" id="muokkaaHierojaBtn" class="btn btn-success">
			<input type="button" value="Peruuta" id="peruutaBtn" class="btn btn-danger"><br>
			
		<input type="hidden" name="Hieroja_id" value="<%out.print(hieroja.getHieroja_id());%>">
	</form><br><br>
</fieldset>
<script>
$(document).ready(function(){
	
	$("#muokkaaHieroja").css({
		"margin": "auto",
		"width": "400px",
		"border-radius": "15px",
		"font-family": "Montserrat, sans-serif"
	});
	
	$("#muokkaaHierojaLomake").validate({						
		rules: {
			etunimi:  {
				required: true,
				minlength: 2				
			},	
			sukunimi:  {
				required: true,
				minlength: 2				
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
		}		
	});
	
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
	
	$("#lisaaKuva").click(function(event){
		window.open('Servlet_HaeKuvat?hieroja_id=' + <%out.print(hieroja.getHieroja_id());%> ,null, 'toolbar=no,status=no,width=400,height=600,left=150,top=100,scrollbars=yes')
	});
});

$("#peruutaBtn").click(function(){	
	document.location="hierojat.jsp";
});
</script>
</body>
</html>









