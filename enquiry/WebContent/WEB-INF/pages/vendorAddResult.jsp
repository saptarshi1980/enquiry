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
        
        <td>
            Vendor Name -<c:out value="${name}" /> added with Serial no-<c:out value="${code}" />
        </td>
        <td>
           <c:out value="${en.vendorName}" />
        </td>
        
        
        
        
</tr>



</table>
</div>


</body>
</html>