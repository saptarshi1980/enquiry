<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Enquiry Download</title>
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>

   <link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" />
   
  <script src="<c:url value="/resources/js/script.js" />"></script>
</head>
<body>

<div align="center"><img src="images/logo_dpl.jpg" /></div><br><br><br><br>
<div align="center">
<table border="1">
    <!-- here should go some titles... -->
    <tr>
        <th>Enqury No</th>
        <th>Enquiry Date</th>
        <th>Main Copy</th>
        <th>Office Copy</th>
    </tr>

<c:forEach items="${enq}" var="en">
        

<tr>
        
        <td>
            <c:out value="${en.enqRefNo}" />
        </td>
        <td>
            <c:out value="${en.enqDate}" />
        </td>
        
        <td>
            
            <a href="PrintEnq.dpl?enq_no=${en.enqRefNo}">Click here</a>
        </td>
        <td>
            
            <a href="PrintEnqOC.dpl?enq_no=${en.enqRefNo}">Click here</a>
        </td>
        
</tr>

</c:forEach>

</table>
</div>
<div id='cssmenu'>
<ul>
   <li class='active'><a href='home.dpl'><span>Home</span></a></li>
   <li class='active'><a href='SearchVendorPage.dpl' target="_blank"><span>Search Vendor Code</span></a></li>
   <li class='active'><a href='AddVendorPage.dpl' target="_blank"><span>Add Vendor</span></a></li>
   <li class='has-sub'><a href='#'><span>Enquiry Entry</span></a>
      <ul>
         <li><a href='new_enquiry_entry.dpl'><span>New Enquiry Entry</span></a></li>
         <li><a href='editEnqPage.dpl'><span>Edit Existing Enquiry</span></a></li>
         
      </ul>
   </li>
   <li class='has-sub'><a href='reportEnq.dpl'><span>Report on Enquiry</span></a>
      <ul>
         
         <li class='last'><a href='selectEnq.dpl'><span>Enquiry Print</span></a></li>
         <li><a href='listAllEnq.dpl'><span>All Enquiry List</span></a></li>
         <li class='last'><a href='#'><span>Contact</span></a></li>
      </ul>
   </li>
   <li class='last'><a href='reset.dpl'><span>Change Password</span></a></li>
   <li class='last'><a href='logout.dpl'><span>Logout</span></a></li>
</ul>
</div>

</body>
</html>