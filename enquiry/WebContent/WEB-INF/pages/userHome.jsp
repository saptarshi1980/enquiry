<!doctype html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
   <meta charset='utf-8'>
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>

   <link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" />
   
  <script src="<c:url value="/resources/js/script.js" />"></script>
   <title>DPL Enquiry Portal</title>
   <script>
    history.forward();
</script>
</head>
<body>

<div align="center"><img src="images/logo_dpl.jpg" /></div><br><br><br><br>

<div align="center"><table border="1"><tr><td>User Id- ${user.userId} </td><td>Department - ${user.userDept}</td></tr></table></div>

<table width="513" border="1" align="center">
  <tr>
    <th width="436" scope="row">Welcome to DPL Enquiry System- ${user.userName}  </th>
  </tr>
</table>
<br><br><br><br><br>

<div id='cssmenu'>
<ul>
   <li class='active'><a href='index.dpl'><span>Home</span></a></li>
   <li class='active'><a href='SearchVendorPage.dpl' target="_blank"><span>Search Vendor Code</span></a></li>
   <li class='active'><a href='AddVendorPage.dpl' target="_blank"><span>Add Vendor</span></a></li>
   <li class='has-sub'><a href='#'><span>Enquiry Entry</span></a>
      <ul>
         <li><a href='new_enquiry_entry.dpl'><span>New Enquiry Entry</span></a></li>
         <li><a href='editEnqPage.dpl'><span>Edit Existing Enquiry</span></a></li>
         <li><a href='AddVendorToEnq.dpl'><span>Add vendor to existing Enquiry</span></a></li>
         <li><a href='DelVendorFromEnq.dpl'><span>Delete vendor from existing Enquiry</span></a></li>
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
<html>
