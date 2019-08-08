<%@include file="header.jsp" %>

<br><br>
<fieldset class="container" id="kirjautuminen">
	<legend>Kirjautuminen</legend>	
	<form action="Servlet_Kirjaudu" method="post" id="kirjauduLomake">
	<label>Käyttäjätunnus</label>	
	<input type="text" name="tunnus" id="tunnus" class="form-control"><br>
	<label>Salasana</label>
	<input type="password" name="salasana" id="salasana" class="form-control"><br>
	<label></label>
	<input type="button" value="Kirjaudu" id="kirjaudu" class="btn btn-default">
	</form>
</fieldset>
<br>
<div class="rekisterointi" id="rekisterointi">
<a href="rekisterointi.jsp">Rekisteröidy</a>
</div>



<script>
$(document).ready(function(){
	$("#kirjautuminen").css({
		"margin": "auto",
		"width": "300px",
		"border-radius": "15px",
		"font-family": "Montserrat, sans-serif"
	});
	$("#rekisterointi").css({
		"text-align": "center",
		"margin": "auto",
		"font-family": "Montserrat, sans-serif"
	});
	$("#kirjaudu").click(function(){		
		if($("#tunnus").val().length>0&&$("#salasana").val().length>0){
			document.forms["kirjauduLomake"].submit();
		}
	});
	$("#tunnus").focus();
});
</script>

</body>
</html>