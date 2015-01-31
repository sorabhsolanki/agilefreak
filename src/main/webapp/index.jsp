<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>Home</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="css/reset.css">
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
    <link rel="stylesheet" href="css/zerogrid.css" type="text/css" media="all">
	<link rel="stylesheet" href="css/responsive.css" type="text/css" media="all"> 
	<link rel="stylesheet" href="css/responsiveslides.css" />  
    <script src="js/jquery-1.7.min.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <script src="js/tms-0.4.1.js"></script>
    <script type="text/javascript" src="js/css3-mediaqueries.js"></script>
   <script type="text/javascript">    
      var contextPath = "${pageContext.request.contextPath}";
      var room = ${roomnumber};
      var check;
     </script>
<script type="text/javascript">
	$(document).ready(function() {
		
		$('.xs').click(function() {
			$('#userestimate').text("XS");
		});
		
		$('.small').click(function() {
		
		 
		});
		
		setInterval(function(){
			//Ajax call to check participantsm
			$.ajax({url:"${pagecontext.request.contextpath}/agilefreak/rest/checkusers/check/" + room,type: "get",success:function(result){
				var userCardNo = $("#usercardnohide").val();
				$('#never').empty();
				for (i = 0; i < result; i++) {
					if(i == userCardNo)
					    $('#never').append("<div class='col-1-4'><div class='wrap-col' style='margin: 20px;'><p id='userestimate' class='text-1'>User <strong>check</strong></p></div></div>");
					else
						$('#never').append("<div class='col-1-4'><div class='wrap-col' style='margin: 20px;'><p id='estimate' class='text-1'>User <strong>estimate</strong></p></div></div>");
				}
			  }});
		}, 6000);
	});
	</script>
	
</head>
<body>
<form id="check" method="post" action="${pageContext.request.contextPath}/room">
<div class="bg">
   <header>
       <div class="zerogrid main wrap">
<h1>

<label for="roomnumber"> Enter room number </label> <input type="text"
			name="roomnumber" id="roomnumber" /> <input type="submit"
			value="SUBMIT">
			Choose your estimate <label><div class="xs"> XS </div><div class="small"> S </div><div id="medium"> M </div><div id="large"> L </div><div id="xl"> XL </div><div id="not"> Can not say </div></label>
			
</h1> 
            <p>Number Of Current Users: <c:out value="${result}" /></p>
            <p>Room Number : <c:out value="${roomnumber}" /></p>
            
       </div>
       
       
   </header>
   <!--<div class="slider zerogrid">
   		<div class="rslides_container">
			<ul class="rslides" id="slider">
				<li><img src="images/slide-1.jpg" alt="" /></li>
				<li><img src="images/slide-2.jpg" alt="" /></li>
				<li><img src="images/slide-3.jpg" alt="" /></li>
			</ul>
		</div>
    </div>-->
   <!--==============================content================================-->
   <input type="hidden" id="usercardnohide">
   <section id="content" class="zerogrid">
   <div id="never" class="row block-1">
   <c:forEach items="${myList}" var="element" varStatus="loop">
   
   <c:choose>
      <c:when test="${loop.index == usercardno}">
      <script>
       $("#usercardnohide").val(${usercardno});
     </script>
      <div class="col-1-4"><div class="wrap-col" style="margin: 20px;">
            <p id="userestimate" class="text-1">User <strong>check</strong></p>
        </div></div>
      </c:when>

      <c:otherwise>
      <div class="col-1-4"><div class="wrap-col" style="margin: 20px;">
            <p id="estimate" class="text-1">User <strong>estimate</strong></p>
        </div></div>
      </c:otherwise>
   </c:choose>
</c:forEach>
 
</div>
            
   </section> 
  <!--==============================footer=================================-->
    <footer class="zerogrid">
    	<div class="wrapfooter">
        AgileFreak
        </div>
    </footer>	
</div> 
</form>
</body>
</html>
