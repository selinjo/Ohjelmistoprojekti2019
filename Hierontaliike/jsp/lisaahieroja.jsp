<%@include file="header.jsp" %>
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
<fieldset id="hierojaTiedot" class="container">
	<legend>Lisää hieroja</legend>
	<form action="Servlet_LisaaHieroja" method="post" id="hierojaLomake">
		<label>Etunimi</label>
			<input type="text" name="etunimi" id="etunimi" value="" class="form-control"><br>	
		<label>Sukunimi</label>
			<input type="text" name="sukunimi" id="sukunimi" value="" class="form-control"><br>
		<label>Sähköposti</label>
			<input type="text" name="sahkoposti" id="sahkoposti" value="" class="form-control"><br>	
		<label>Salasana</label>
			<input type="password" name="salasana" id="salasana" value="" class="form-control"><br>	
		<label>Toista salasana</label>
			<input type="password" name="salasana2" id="salasana2" value="" class="form-control"><br>	
		<label>Puhelin</label>
			<input type="text" name="puhelin" id="puhelin" value="" class="form-control"><br>
		<label>Lisätietoja</label>	
			<textarea rows="4" cols="30" name="lisatiedot" id="lisatiedot" class="form-control"></textarea><br>
		<label></label>	
			<input type="submit" value="Lisää hieroja" id="lisaaHierojaBtn" class="btn btn-success">
			<input type="button" value="Peruuta" id="peruutaBtn" class="btn btn-danger"><br>
			<%
			if(request.getParameter("ok")!=null){
				if(request.getParameter("ok").equals("0")){
					out.print("Lisääminen epäonnistui!");	
				}
			}
			%>
	</form><br><br>
</fieldset>

<script>
$(document).ready(function(){
	$("#hierojaTiedot").css({
		"margin": "auto",
		"width": "400px"
	});
	
	$("#hierojaLomake").validate({						
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
				required: true,
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
				required: "Puuttuu",
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
});

$("#peruutaBtn").click(function(){	
	document.location="hierojat.jsp";
});
</script>
</body>
</html>