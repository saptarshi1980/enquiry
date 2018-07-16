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


<body>

<%
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader ("Expires", 0);

  

  %> 
<div align="center"><img src="images/logo_dpl.jpg" /></div>


<p>&nbsp;</p>

<a href='SearchVendorPage.dpl'><span>Search Another Vendor Code</span></a>
<br><br>


<p><br>
</p>
<form name="form1" method="post" action="SearchVendor.dpl">
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <table width="817" border="1" align="center">
    <tr>
      <th width="81" scope="row"><div align="left">Vendor name</div></th>
      <td width="720"><label>
        <input name="vendor_name" type="text"  size="75">
      </label></td>
    </tr>
          <th scope="row"></th>
      <td><label>
        
      </label></td>
    </tr>
    
    <tr>
      <th colspan="2" scope="row"><label>
        <input type="submit" name="Submit" value="Search Vendor">
      </label></th>
    </tr>
    
  </table>
  
  
  
</form>

<p>&nbsp; </p>
</body>
</html>