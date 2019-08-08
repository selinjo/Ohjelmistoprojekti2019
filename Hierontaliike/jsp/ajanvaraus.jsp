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
<fieldset id="ajanvaraus">
	<legend>Ajanvaraus</legend>
	<div>
		<select id="hierojat" class="form-control">
			<option>Valitse hieroja</option>
		</select>
		<br>
		<button type="button" id="vapaatAjatBtn" class="btn btn-default">Näytä vapaat ajat</button>
	</div>
	<br>
	<table id="hieronnat" class="table">
	
	</table>
</fieldset>

<script>
$(document).ready(function(){	
	$("#ajanvaraus").css({
		"margin": "auto",
		"width": "500px",
		"border-radius": "15px",
		"font-family": "Montserrat, sans-serif",
		"font-size": "18px",
		"text-align": "center"
	});
	
	$.getJSON("Servlet_Hierojat_Ajax", function(result){
        $.each(result, function(i, field){
        	$("#hierojat").append("<option value='"+field.Hieroja_id+"'>"+field.Sukunimi+" "+field.Etunimi+"</option>");            
        });
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

$("#vapaatAjatBtn").click(function(){
	$("#hieronnat").empty();
	$.getJSON("Servlet_HaeVapaatHieronnat_Ajax?Hieroja_id="+$("#hierojat").val(), function(result){
        $.each(result, function(i, field){
        	var fPvm = field.Ajankohta;
        	var fPvm = $.datepicker.formatDate('dd.mm.yy', new Date(fPvm));
        	var fKlo = field.Kello.substring(0,5);
        	$("#hieronnat").append("<tr><td>"+fPvm+" "+fKlo+"</td><td><button type='button' class='btn btn-success varaus' value='' id='varaus_"+field.Varaus_id+"' title='Varaa'>Valitse</button></td></tr>");
        });
    		$(".varaus").click(function(){
    			document.location="Servlet_UusiVaraus?Varaus_id="+$(this)[0].id;
    		});
    });
});

</script>
</body>
</html>