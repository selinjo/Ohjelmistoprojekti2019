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
<fieldset id="hieronnat">
	<legend>Omat varaukset</legend>
<br>
	<table id="omatHieronnat" class="table">
		<tr>
			<td>Ajankohta</td>
			<td>Hieroja</td>
			<td>Hieronta</td>
			<td>Lisätiedot</td>
		</tr>
	</table>
</fieldset>

<script>
$(document).ready(function(){
	$("#hieronnat").css({
		"margin": "auto",
		"width": "700px",
		"border-radius": "15px",
		"font-family": "Montserrat, sans-serif",
		"font-size": "18px"
	});
	
	$.getJSON("Servlet_HaeAsiakkaanHieronnat_Ajax", function(result){
        $.each(result, function(i, field){
        	var fPvm = field.Ajankohta;
        	var fPvm = $.datepicker.formatDate('dd.mm.yy', new Date(fPvm));
        	var fKlo = field.Kello.substring(0,5);
        	$("#omatHieronnat").append("<tr><td>"+fPvm+" "+fKlo+"</td><td>"+field.Etunimi+" "+field.Sukunimi+"</td><td>"+field.Hierontatyyppi+"</td><td>"+field.Lisatietoja+
        			"</td><td><img src='images/cancel.png' class='icon poista' id='poista_"+field.Varaus_id+"' title='Peruuta varaus'></td></tr>");
        });
        $(".poista").click(function(){
			document.location="Servlet_PeruutaVaraus?Varaus_id="+$(this)[0].id;
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
</script>
</body>
</html>