<%@ page import="java.util.ArrayList"%> 
<%! @SuppressWarnings("unchecked") %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<title>Insert title here</title>
<style>
label{
    display:inline-block;
    width:100px;
    text-align:right;
    vertical-align:top;
}
.nappi{
	width:80px;
}
</style>  
</head>
<body>
 	<form method="post" action="Servlet_Upload" enctype="multipart/form-data" id="lomake">
        <label>Valitse kuva:</label><input type="file" name="uploadFile" id="upLoadFile"/><br>
        <label>(png/jpg/gif) </label><input class="nappi" type="button" value=" Tallenna " onclick="lataa()" />        
    </form><br>
    <img src="images/spinner.gif" id="spinner">
    <ol id="sortable">
    <%
	session.setAttribute("hieroja_id", request.getParameter("hieroja_id"));	
	if(request.getAttribute("kuvat")!=null){
		String polku = "images/uploaded/";
		ArrayList<String> hierojakuvat=(ArrayList<String>)request.getAttribute("kuvat");	
		String kuva="";				
		for(int i=0; i<hierojakuvat.size();i++){
			kuva = hierojakuvat.get(i).replace("hieroja_" + request.getParameter("hieroja_id"),"");
			out.print("<li class='ui-state-default'><img title='"+kuva+"' id='kuva_"+kuva+"' class='pikkukuvat' src='"+polku+kuva+"'><img class='poistakuva' id='"+kuva+"' src='images/delete.png'></li>");
		}	
	}
	%>
	</ol>  

<script>
	function lataa(){	
		var paatteet = ["png", "jpg", "gif"]; 
		var paate = document.getElementById("upLoadFile").value.split(".")[1];
		var kelvollinen = 0;
		for(var i=0;i<paatteet.length;i++){
			if(paatteet[i]==paate.toLowerCase()){
				kelvollinen=1;
				break;
			}
		}		
		if(kelvollinen==1&&document.getElementById("upLoadFile").value.length>0){
			document.getElementById("lomake").submit();
			$("#spinner").show();
		}
	}
	
	$(document).ready(function(){	
		$("#sortable").sortable({
			 stop: function(event, ui) {
				 	var kuvaJarjestys = "";
			        $("#sortable li img").each(function(i, el){			           
			            var kuva = $(el).attr("title");			            				
			            if(kuva!=null){
			            	kuva="hieroja_<%out.print(request.getParameter("hieroja_id"));%>" + kuva;			
			            	kuvaJarjestys+=kuva+";";
			            }			        	
			        });	
			        console.log(kuvaJarjestys);
			        $.ajax({url: "Servlet_JarjestaKuvat?kuvat="+kuvaJarjestys, success: function(result){}});
			    }			
		});   
		$("#spinner").hide();
		$("#spinner").css({
			"position": "absolute",
			"left": "100px",
			"top": "50px"
		});	
		$(".pikkukuvat").css({
			"height": "100px",
			"border": "1px solid black"
		});	
		$(".poistakuva").css({
			"height": "30px"			
		});	
		$(".poistakuva").click(function(){	
			var poistettava = "hieroja_<%out.print(request.getParameter("hieroja_id"));%>"+this.id;
			var r = confirm("Poista kuva " + this.id + "?");
			if (r == true) {
				$("#spinner").show();
				$.ajax({url: "Servlet_PoistaKuva?kuva_id="+poistettava, success: function(result){
					 document.location="Servlet_HaeKuvat?hieroja_id=<%out.print(request.getParameter("hieroja_id"));%>";
				}});
			}			 
		});		
	});
</script>
</body>
</html>
<%
if(request.getAttribute("message")!=null){	
	response.sendRedirect("Servlet_HaeKuvat?hieroja_id=" + request.getParameter("hieroja_id"));
	Thread.sleep(5000);//odotetaan 5 sekuntia, jotta kuva ehtii latautua
}
%>