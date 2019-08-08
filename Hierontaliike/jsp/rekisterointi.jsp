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
<fieldset id="asiakasTiedot" class="container">
	<legend>Rekisteröidy</legend>
	<form action="Servlet_LisaaAsiakas" method="post" id="asiakasLomake">
		<label for="etunimi">Etunimi</label>
			<input type="text" name="etunimi" id="etunimi" value="" class="form-control"><br>	
		<label for="sukunimi">Sukunimi</label>
			<input type="text" name="sukunimi" id="sukunimi" value="" class="form-control"><br>	
		<label for="katuosoite">Katuosoite</label>
			<input type="text" name="katuosoite" id="katuosoite" value="" class="form-control"><br>	
		<label for="postinumero">Postinumero</label>
			<input type="text" name="postinumero" id="postinumero" value="" class="form-control">
			<span id="postitoimipaikka"></span>
			<span id="postitoimivirhe"></span><br>
		<label for="sahkoposti">Sähköposti</label>
			<input type="text" name="sahkoposti" id="sahkoposti" value="" class="form-control"><br>	
		<label for="salasana">Salasana</label>
			<input type="password" name="salasana" id="salasana" value="" class="form-control"><br>	
		<label for="salasana2">Salasana uudelleen</label>
			<input type="password" name="salasana2" id="salasana2" value="" class="form-control"><br>	
		<label for="puhelin">Puhelin</label>
			<input type="text" name="puhelin" id="puhelin" value="" class="form-control"><br>
		<label for="lisatietoja">Lisätietoja</label>	
			<textarea rows="4" cols="30" name="lisatiedot" id="lisatiedot" class="form-control"></textarea><br>
		<label></label>	
			<input type="submit" value="Rekisteröidy" id="lisaaAsiakasBtn" class="btn btn-success">
			<input type="button" value="Peruuta" id="peruutaBtn" class="btn btn-danger"><br>
			<%
			if(request.getParameter("ok")!=null){
				if(request.getParameter("ok").equals("0")){
					out.print("Rekisteröinti epäonnistui!");	
				}
			}
			%>
	</form>
		
</fieldset><br><br>

<script>
$(document).ready(function(){
	$("#asiakasTiedot").css({
		"margin": "auto",
		"width": "400px",
	});
	
	$("#postinumero").blur(function(){
	    $.ajax({url: "Servlet_Postitoimi_Ajax?postinumero="+$("#postinumero").val(), success: function(result){
	        $("#postitoimipaikka").html(result);
	    }});
	});
	
	$("#peruutaBtn").click(function(){	
		document.location="index.jsp";
	});
	
	$("#asiakasLomake").validate({						
		rules: {
			etunimi:
			{
				required: true,
				minlength: 2				
			},	
			sukunimi:
			{
				required: true,
				minlength: 2				
			},
			katuosoite:
			{
				required: true,
				minlength: 4
			},	
			postinumero:
			{
				required: true,
				number: true				
			},			
			sahkoposti:
			{
				required: true,
				email: true			
			},
			puhelin:
			{				
				minlength: 3				
			},
			salasana:
			{
				required: true,
				minlength: 8				
			},
			salasana2:
			{
				required: true,
				equalTo: salasana
			}			
		},
		messages: {
			etunimi:
			{     
				required: "Puuttuu",
				minlength: "Etunimi liian lyhyt"			
			},
			sukunimi:
			{
				required: "Puuttuu",
				minlength: "Sukunimi liian lyhyt"
			},
			katuosoite:
			{
				required: "Puuttuu",
				minlength: "Osoite liian lyhyt"
			},
			postinumero:
			{
				required: "Puuttuu",
				number: "Syötä vain numeroita"				
			},	
			sahkoposti:
			{
				required: "Puuttuu",
				email: "Tarkista sähköpostiosoite"			
			},
			puhelin:
			{				
				minlength: "Liian lyhyt"				
			},
			salasana:
			{
				required: "Puuttuu",
				minlength: "Väh. 8 merkkiä"				
			},
			salasana2:
			{
				required: "Puuttuu",
				equalTo: "Salasanat eivät täsmää"
			}
		},			
		submitHandler: function (form) {	
			if ($("#postitoimipaikka").html()!=""){
				document.forms["asiakasLomake"].submit();
			}else{
				$("#postitoimipaikka").html("<label class='error'>Tarkista postinumero</label>");
			}
		}
	});
	
});
</script>
</body>
</html>