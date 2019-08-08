<%@include file="header.jsp" %>
<%@ page import="model.Varaus"%>
<%@ page import="helper.Formatoija"%>

<%
Varaus varaus = null;
if( request.getAttribute("varaus")!=null){
	varaus = (Varaus)request.getAttribute("varaus");	
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
<fieldset id="teeVaraus" class="container">
	<legend>Tee varaus</legend>
	<form action="Servlet_UusiVaraus" method="post" id="uusiVarausLomake">
		<label for="hierontatyypit">Valitse hierontatyyppi</label>
			<select id="hierontatyypit" name="hierontatyypit" class="form-control">
				<option id="valitseHieronta">Valitse hieronta</option>
			</select><br>
			<span id="htError"></span>
		<label>Hieroja</label><br>
			<span><%out.print(varaus.getHierojaenimi());%></span>	
			<span><%out.print(varaus.getHierojasnimi());%></span><br><br>
		<label>Ajankohta</label><br>
			<span>
			<%Formatoija pvmF = new Formatoija();
			out.print(pvmF.muutaPaivays(varaus.getAjankohta()));%>
			</span><br>
			<span>Klo <%out.print(pvmF.muutaKello(varaus.getKello()));%></span><br><br>
		<label for="hinta">Hinta</label><br>
			<span id="hinta"></span><br><br>	
		<label for="lisatietoja">Lisätietoja</label>	
			<textarea rows="4" cols="30" name="lisatietoja" id="lisatietoja" class="form-control"></textarea><br>
		<label></label>	
			<input type="submit" value="Vahvista varaus" id="uusiVarausBtn" class="btn btn-success">
			<input type="button" value="Peruuta" id="peruutaBtn" class="btn btn-danger"><br>
			
		<input type="hidden" id="varaus_id" name="varaus_id" value="<%out.print(varaus.getVaraus_id());%>">
		<input type="hidden" id="inputHinta" name="inputHinta">
	</form><br><br>
</fieldset>

<script>
$(document).ready(function(){
	$("#teeVaraus").css({
		"margin": "auto",
		"width": "400px",
		"border-radius": "15px",
		"font-family": "Montserrat, sans-serif"
	});
	
	$.getJSON("Servlet_Hierontatyypit_Ajax", function(result){
        $.each(result, function(i, field){
        	$("#hierontatyypit").append("<option value='"+field.Hieronta_id+"'>"+field.Hierontatyyppi+"</option>");
        });
        $.ajax({url: "Servlet_Hinta_Ajax?Hieronta_id="+field.Hieronta_id, success: function(result){
	        $("#hinta").val(result);
	    }});
    });
	
	$("#hierontatyypit").change(function(){			
		var hieronta_id = $(this).val(); 
		$.getJSON("Servlet_Hinta_Ajax?Hieronta_id="+hieronta_id, function(result){
			$("#hinta").html("");
			$.each(result, function(i, field){
	        	$("#hinta").append(field.Hinta+" euroa");
	        	$("#inputHinta").val(field.Hinta);
	        });			
	    });
		$("#hierontatyypit option[value='-1']").remove(); //poistetaan "Valitse hieronta" vaihtoehto
	});
	
	$("#uusiVarausLomake").validate({			
		submitHandler: function (form) {	
			if ($("#hinta").html()!=""){
				document.forms["uusiVarausLomake"].submit();
			}else{
				$("#htError").html("<label class='error'>!!!VALITSE HIERONTA!!!</label><br>");
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
	document.location="ajanvaraus.jsp";
});
</script>
</body>
</html>