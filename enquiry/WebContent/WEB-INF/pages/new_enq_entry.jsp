<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>DPL Online Enquiry System</title>
  
  
  
      <%-- <link rel="stylesheet" href=" <c:url value="/css/style.css" /> "> --%>

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
<body>
<div align="center"> <img src="images/logo_dpl.jpg" /></div><br><br>
<a href='home.dpl'><span>Home</span></a>
<form name="form1" action="enq_basic.dpl" method="POST" onsubmit="return checkDate()">
  <p>&nbsp;</p>
  <p align="center">&nbsp;</p>
  <div align="center">
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <table width="543" border="1">
      <tr>
        <th width="215" scope="row"><div align="left">Enquiry No </div></th>
        <td width="312"><label>
          <input id="enq_no" name="enq_no" type="text" size="51">
        </label></td>
      </tr>
      <tr>
        <th scope="row"><div align="left">Enqury Date (DD/MM/YYYY) </div></th>
        <td><label>
          <input id="enq_date" name="enq_date" type="text" size="11" maxlength="10">
        </label></td>
      </tr>
      <tr>
        <th scope="row"><div align="left">Opening Date (DD/MM/YYYY)</div></th>
        <td><label>
          <input id="open_date" name="open_date" type="text" size="11" maxlength="10">
        </label></td>
      </tr>
      <tr>
        <th colspan="2" scope="row"><label>
          <input type="submit" name="Submit" value="Next">
        </label></th>
      </tr>
      </table>  
  </div>
  <p>&nbsp;</p>
</form>
</body>
</html>