<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <meta charset="UTF-8">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Enquiry Details Entry</title>
</head>
<body>

<%
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader ("Expires", 0);

  

  %> 
<div align="center"><img src="images/logo_dpl.jpg" /></div>
<p>&nbsp;</p>
<a href='home.dpl'><span>Home</span></a><br>

<p><br>
</p>
<form name="form1" method="post" action="AddNewVendor.dpl">
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <table width="817" border="1" align="center">
    <tr>
      <th width="81" scope="row"><div align="left">Vendor Name</div></th>
      <td width="720"><label>
        <input name="vendor_name" type="text" size="150">
        
      </label></td>
    </tr>
    <tr>
      <th scope="row"><div align="left">Address</div></th>
      <td><label>
        <input type="text" name="address">
      </label></td>
    </tr>
    <tr>
      <th scope="row"><div align="left">Pin</div></th>
      <td><label>
        <input type="text" name="pin">
      </label></td>
    </tr>
    <tr>
      <th colspan="2" scope="row"><label>
        <input type="submit" name="Submit" value="Save">
      </label></th>
    </tr>
    
  </table>
  
  
</form>
<p>&nbsp; </p>
</body>
</html>