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
<fieldset id="lisaaHieronta">
	<legend>Omat hieronnat</legend>
		<form action="Servlet_LisaaHieronta" method="post" id="lisaaHierontaLomake">
			<input type="text" name="ajankohta" id="ajankohta" placeholder="pvm">
			<select id="kello"></select>
			<input type="submit" value="+ Lis‰‰ uusi aika" class="btn btn-success">
			
			<input type="hidden" name="inputKello" id="inputKello">
		</form>
</fieldset>
<br>
<fieldset id="hieronnat">
	<table id="omatHieronnat" class="table">
		<tr>
			<td>Ajankohta</td>
			<td>Asiakas</td>
			<td>Hieronta</td>
			<td>Lis‰tiedot</td>
		</tr>
	</table>
</fieldset>

<script>
$(document).ready(function(){
	$("#ajankohta").datepicker({ dateFormat: 'dd.mm.yy' });
	
	$("#lisaaHieronta").css({
		"margin": "auto",
		"width": "500px",
		"border-radius": "15px",
		"font-family": "Montserrat, sans-serif",
		"font-size": "18px",
		"text-align": "center"
	});
	
	$("#hieronnat").css({
		"margin": "auto",
		"width": "700px",
		"border-radius": "15px",
		"font-family": "Montserrat, sans-serif",
		"font-size": "18px"
	});
	
	$.getJSON("Servlet_HaeOmatHieronnat_Ajax", function(result){
        $.each(result, function(i, field){
        	var fPvm = field.Ajankohta;
        	var fPvm = $.datepicker.formatDate('dd.mm.yy', new Date(fPvm));
        	var fKlo = field.Kello.substring(0,5);
        	$("#omatHieronnat").append("<tr><td>"+fPvm+" "+fKlo+"</td><td>"+field.Etunimi+" "+field.Sukunimi+"</td><td>"+field.Hierontatyyppi+"</td><td>"+field.Lisatietoja+
        			"</td><td><img src='images/cancel.png' class='icon poista' id='poista_"+field.Varaus_id+"' title='Peruuta aika'></td><td><img src='images/accept.png' class='icon hyvaksy' id='hyvaksy_"+field.Varaus_id+"' title='Hyv‰ksy aika'></td></tr>");
        });
		$(".poista").click(function(){
			document.location="Servlet_PeruutaAika?Varaus_id="+$(this)[0].id;
		});
		$(".hyvaksy").click(function(){
			document.location="Servlet_HyvaksyAika?Varaus_id="+$(this)[0].id;
		});
    });
	
    $("#kello").change(function(){
        var valittu = $(this).val();
        $("#inputKello").val(valittu);
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

// Kellonajat select -kentt‰‰n
var valinta = "";
var i = 0;
for(var i = 0; i < 24; i++)
{
    var j = zeroFill(i, 2);
    valinta += "<option value='"+ j +"00'>"+ j + ":00" + "</option>";
        valinta += "<option value='"+ j +"30'>"+ j + ":30" + "</option>";
}
$("#kello").html(valinta);

function zeroFill( number, width )
{
  width -= number.toString().length;
  if ( width > 0 )
  {
    return new Array( width + (/\./.test( number ) ? 2 : 1) ).join( '0' ) + number;
  }
  return number + "";
}


</script>
</body>
</html>