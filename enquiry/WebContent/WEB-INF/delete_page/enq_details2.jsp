<!DOCTYPE html>
<html >
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <meta charset="UTF-8">
  <title>DPL Online Enquiry System</title>
  
  
  
      <link rel="stylesheet" href="<c:url value="/css/style.css" />">

      <script src="<c:url value="/js/index.js" />"></script>
      <script>
    history.forward();
</script>

<script type="text/javascript">
function checkDate(){
	
	var enq_date = document.forms["form1"]["enq_date"].value;
	var open_date = document.forms["form1"]["open_date"].value;
    var pattern = /^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$/;
    if (enq_date == null || enq_date == "" || !pattern.test(enq_date)||open_date == null || open_date == "" || !pattern.test(open_date)) {
        alert("Invalid Enquiry date or Opening Date Format.");
        return false;
    }
    else {
        return true
    }
	
	
}
</script>
</head>
<%
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader ("Expires", 0);

  

  %> 
  

  <body>
  
<div class="container"></div>
<div align="center"><img src="images/logo_dpl.jpg" /></div><br><br>
	<section id="content">
		<form name="form1" action="enq_basic.dpl" method="POST" onsubmit="return checkDate()">
			<h3><c:out value="${enq_no}"/></h3>    <h3><c:out value="${enq_date}"/></h3>
			<div>
				<input type="text" placeholder="Description" required="" id="desc" name="desc"/>
			</div>
			<div>
				<input type="text" placeholder="Quantity" required="" id="qty" name="qty"/>
			</div>
			<div>
				<input type="text" placeholder="Unit" required="" id="unit" name="unit"/>
			</div>
			
			<div ALIGN="center">
				<input type="submit" value="Next" />
			</div>
			
			<div ALIGN="center">
				<A href="EnqFinish">Finish This Enquiry<</A>
			</div>
			
			<input type="hidden" id="enq_no" name="enq_no"/>
			<input type="hidden" id="user_id" name="user_id" value="12345"/>
			
		</form><!-- form -->
		
	</section><!-- content -->
</div><!-- container -->
<br><br><br>
  <table style="width: 100%;" bgcolor="black">
    <tr >
        <marquee scrolldelay="80" scrollamount="3" onMouseOver="document.all.test.stop()" onMouseOut="document.all.test.start()"><td align="center"><font color="white">dEMO </font></td>
        
    </tr>
   
</table>

</body>
</html>


