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
<table width="892" border="1" align="center">
  <tr>
    <th width="111" scope="row">Enquiry No </th>
    <td width="251"><c:out value="${enq_no}"/></td>
    <td width="251"><strong>Enquiry Date</strong></td>
    <td width="251"><c:out value="${enq_date}"/></td>
	
  </tr>
  
</table>
<p><br>
</p>
<form name="form1" method="post" action="enq_details.dpl">
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <table width="817" border="1" align="center">
    <tr>
      <th width="81" scope="row"><div align="left">Description</div></th>
      <td width="720"><label>
        <input name="description" type="text" size="150">
        
      </label></td>
    </tr>
    <tr>
      <th scope="row"><div align="left">Quantity</div></th>
      <td><label>
        <input type="text" name="qty">
      </label></td>
    </tr>
    <tr>
      <th scope="row"><div align="left">Unit</div></th>
      <td><label>
        <input type="text" name="unit">
      </label></td>
    </tr>
    <tr>
      <th colspan="2" scope="row"><label>
        <input type="submit" name="Submit" value="Save and Add New Item">
      </label></th>
    </tr>
    <tr>
      <th colspan="2" scope="row"><a href="addVendor.dpl?enq_no=${enq_no}">Add Vendor</a></th>
    </tr>
  </table>
  
  <input type="hidden" id="enq_no" name="enq_no" value="<c:out value="${enq_no}"/>"/>
  <input type="hidden" id="enq_date" name="enq_date" value="<c:out value="${enq_date}"/>"/>
  <input type="hidden" id="user_id" name="user_id" value="12345"/>
</form>
<p>&nbsp; </p>
</body>
</html>