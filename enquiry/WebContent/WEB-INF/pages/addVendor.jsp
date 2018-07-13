<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
  <meta charset="UTF-8">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="//malsup.github.com/jquery.form.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" />
<script src="<c:url value="/resources/js/script.js" />"></script>
<title>Enquiry Vendor Entry</title>


<div ></div>

<script language="javascript">
$(document).ready(function() {
$('#vendor_code').change(function() {
      var q = $('#vendor_code').val();
      
      $.ajax({
         url: "GetVendor",
         data: {query:q},
         success: function(data) {
             
             $('#search-results').html(data);
         }
      });
  });
});
</script>
</head>
<body>

<%
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader ("Expires", 0);

  

  %> 
<div align="center"><img src="images/logo_dpl.jpg" /></div>
<a href='home.dpl'><span>Home</span></a><br>
<a href='SearchVendorPage.dpl' target="_blank"><span>Search Vendor Code</span></a>

<p>&nbsp;</p>
<br><br>
<table width="892" border="1" align="center">
  <tr>
    <th width="111" scope="row">Enquiry No </th>
    <td width="251"><c:out value="${enq_no}"/></td>
   </tr>
  
</table><br>
<table width="892" border="1" align="center">
  <tr>
    
    <td width="251"><div id="search-results" align="left"></div></td>
   </tr>
  
</table>
<p><br>
</p>
<form name="form1" method="post" action="SaveVendor.dpl">
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <table width="817" border="1" align="center">
    <tr>
      <th width="81" scope="row"><div align="left">Enquiry No</div></th>
      <td width="720"><label>
        <input name="enq_no" value="<c:out value="${enq_no}"/>" type="text" size="110">
      </label></td>
    </tr>
    <tr>
      <th scope="row"><div align="left">Vendor Code</div></th>
      <td><label>
        <input type="text" name="vendor_code" id="vendor_code" >
      </label></td>
    </tr>
    <tr>
      <th scope="row"></th>
      <td><label>
        
      </label></td>
    </tr>
    
    <tr>
      <th colspan="2" scope="row"><label>
        <input type="submit" name="Submit" value="Save and Add New Vendor">
      </label></th>
    </tr>
    
  </table>
  
  <input type="hidden" id="enq_no" name="enq_no" value="<c:out value="${enq_no}"/>"/>
  <input type="hidden" id="enq_date" name="enq_date" value="<c:out value="${enq_date}"/>"/>
  <input type="hidden" id="user_id" name="user_id" value="12345"/>
</form>

<p>&nbsp; </p>
</body>
</html>