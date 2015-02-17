<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
  <title>AgileFreak</title>
  <meta name="description" content="free website template" />
  <meta name="keywords" content="enter your keywords here" />
  <meta http-equiv="content-type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=9" />
  <link rel="stylesheet" type="text/css" href="css/style.css" />
  <script type="text/javascript" src="js/jquery.min.js"></script>
  <script src="js/jquery-1.7.min.js"></script>
  <script type="text/javascript">    
      var contextPath = "${pageContext.request.contextPath}";
      var room = '${roomnumber}';
      var showcards = '${showcards}';
      var check;
      
  </script>
  <script type="text/javascript">
   $(document).ready(function() {
	   
	   
	   $('#showallcards').click(function() {
		    //ajax call to set showcards value true
			$.ajax({url:"${pagecontext.request.contextpath}/agilefreak/rest/checkusers/showcards",type: "GET", success:function(result){
			}
			});
		});
	   
		$('#xs').click(function() {
		    $('#userestimate').text("XS");
			//ajax call to set estimates
			$.ajax({url:"${pagecontext.request.contextpath}/agilefreak/rest/checkusers/estimate/XS/room/" + room,type: "GET", success:function(result){}
			});
		});
		
		$('#small').click(function() {
		    $('#userestimate').text("Small");
			//ajax call to set estimates
			$.ajax({url:"${pagecontext.request.contextpath}/agilefreak/rest/checkusers/estimate/Small/room/" + room,type: "GET", success:function(result){}
			});
		});
		
		$('#medium').click(function() {
		    $('#userestimate').text("Medium");
			//ajax call to set estimates
			$.ajax({url:"${pagecontext.request.contextpath}/agilefreak/rest/checkusers/estimate/Medium/room/" + room,type: "GET", success:function(result){}
			});
		});
		
		$('#large').click(function() {
		    $('#userestimate').text("Large");
			//ajax call to set estimates
			$.ajax({url:"${pagecontext.request.contextpath}/agilefreak/rest/checkusers/estimate/Large/room/" + room,type: "GET", success:function(result){}
			});
		});
		
		$('#xl').click(function() {
		    $('#userestimate').text("XL");
			//ajax call to set estimates
			$.ajax({url:"${pagecontext.request.contextpath}/agilefreak/rest/checkusers/estimate/XL/room/" + room,type: "GET", success:function(result){}
			});
		});
		
		$('#cantsay').click(function() {
		    $('#userestimate').text("Can'tSay");
			//ajax call to set estimates
			$.ajax({url:"${pagecontext.request.contextpath}/agilefreak/rest/checkusers/estimate/Can'tSay/room/" + room,type: "GET", success:function(result){}
			});
		});
		
		if($("#usercardnohide").val() == 0 && room)
			$('#showallcards').removeAttr('disabled');
             
		setInterval(function(){
		$.ajax({ 
		    type: 'GET', 
		    url: '${pagecontext.request.contextpath}/agilefreak/rest/checkusers/check/' + room, 
		    dataType: 'json',
		    success: function (data) { 
		    	var userCardNo = $("#usercardnohide").val();
		    	$('#never').empty();
		    	var i = 0;
		    	$.each(data, function(index, element) {
		    		if(i == userCardNo){
		    			if(element.estimate == null)
		    				$('#never').append("<div class='content_container'> <div class='content_image'></div><h2>Your Card</h2><p id='userestimate'><b>Your Card</b></p></div></div>");
		    			else	
         				    $('#never').append("<div class='content_container'> <div class='content_image'></div><h2>Your Card</h2><p id='userestimate'><b>" + element.estimate + "</b></p></div></div>");	
					}else{
						if(showcards)
							$('#never').append("<div class='content_container'><div class='content_image'></div><h2>Participant Card</h2><p><b>" + element.estimate + "</b></p></div>");
					    else	
     					    $('#never').append("<div class='content_container'><div class='content_image'></div><h2>Participant Card</h2><p><b>Only scrum master can reveal it.</b></p></div>");
					}
					i++;
		    	});
		    }
		});	
		
		}, 6000);
      
		
	});
	</script>
</head>

<body>
<form id="check" method="post" action="${pageContext.request.contextPath}/room">
  <div id="main">
  
	<!--<div id="menu_container">
	  <div id="menubar">
        <ul id="menu">
          
        </ul>
      </div>--><!--close menubar-->	
	</div><!--close menu_container-->  
  
    <div id="header">
	  <div id="banner">
	    <div id="welcome">
	      <h1>Agile Freak</h1>
	    </div><!--close welcome-->
	    <div id="slideshow">
             <h1>Join Room Number <input type="text" placeholder="Enter Room Number" name="roomnumber" id="roomnumber" size="11"> <input type="submit"
			value="JOIN"/></h1>
             
             <h1>Number Of Current Users : <c:out value="${result}" /></h1>
	    </div><!--close slideshow-->
       <h7><b id="xs">ExtraSmall</b>   <b id="small">Small</b>    <b id="medium">Medium</b>  <b id="large">Large</b> <b id="xl">ExtraLarge</b> <b id="cantsay">Can'tSay</b>  </h7>		  	  
	  </div><!--close banner-->	
       <h1>Room Number : <c:out value="${roomnumber}" /></h1>
       <input type="button" value="ShowAllCards" id="showallcards" disabled="disabled"/>
	</div><!--close header-->	
       
        <input type="hidden" id="usercardnohide" name="usercardnohide">
 	   
        
        <div id="site_content">		  
	 
	  <div id="content">
        <div id="never" class="content_item">
		  <h1></h1> 
          <p></p>   	
           		  <c:forEach items="${myList}" var="element" varStatus="loop">
   
   <c:choose>
      <c:when test="${loop.index == usercardno}">
      <script>
       $("#usercardnohide").val(${usercardno});
     </script>
      <div class="content_container">
		    <div class="content_image"></div>
			<h2>Your Card</h2>
			<p id='userestimate'><b>Choose Your Estimate.</b></p>
		  </div>
      </c:when>

      <c:otherwise>
     <div class="content_container">
		    <div class="content_image"></div>
			<h2>Participant Card</h2>
			<p><b>Only scrum master can reveal it.</b></p>
		  </div>
      </c:otherwise>
   </c:choose>
</c:forEach>


		  
		  

		</div><!--close content_item-->		
      </div><!--close content-->   
	</div><!--close site_content--> 
    
	<!--<div id="content_grey">
	  <div class="content_grey_container_box">
		<h4>Latest Blog Post</h4>
	    <p> Phasellus laoreet feugiat risus. Ut tincidunt, ante vel fermentum iaculis.</p>
		<div class="readmore">
		  <a href="#">Read more</a>
		</div>
	  </div>
      <div class="content_grey_container_box">
       <h4>Latest News</h4>
	    <p> Phasellus laoreet feugiat risus. Ut tincidunt, ante vel fermentum iaculis.</p>
	    <div class="readmore">
		  <a href="#">Read more</a>
		</div>
	  </div>
      <div class="content_grey_container_boxl">
		<h4>Latest Projects</h4>
	    <p> Phasellus laoreet feugiat risus. Ut tincidunt, ante vel fermentum iaculis.</p>
	    <div class="readmore">
		  <a href="#">Read more</a>
		</div>	  
	  </div>      
	  <br style="clear:both"/>
    </div>--><!--close content_grey-->   
 
  </div><!--close main-->
  
  <div id="footer_container">
    <div id="footer">
	 <h4>Easy way to estimate your user stories!</h4>
    </div><!--close footer-->  
  </div><!--close footer_container-->  
  </form>
</body>
</html>
