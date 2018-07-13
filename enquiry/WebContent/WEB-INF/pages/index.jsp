<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DPL Enquiry Portal</title>
<script>
    history.forward();
</script>
</head>
<%
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader ("Expires", 0);

  

  %> 
  
<body>

<div align="center"><img src="images/logo_dpl.jpg" /></div><br><br><br><br>
<form id="form1" name="form1" method="POST" action="LoginAuth.dpl" >

  <div align="center">
    <p>&nbsp;</p>
    <table width="621" height="256" border="2" bordercolor="#993300">
      <tr>
        <td height="248" bgcolor="#99CC99"><div align="center">
            <p><strong><span class="style10">              DPL Enquiry LOGIN </span><br>
            </strong><span class="style7">Please provide your credentials (not case-sensitive) to login</span></p>
            <table width="272" height="118" border="1">
              <tr>
                <td width="146" bordercolor="#000000" bgcolor="#CCCC99"><span class="style9">User Id </span></td>
                <td width="225" bordercolor="#000000"><label>
                  <input type="text" name="username" id="username" />
                </label></td>
              </tr>
              <tr>
                <td bordercolor="#000000" bgcolor="#CCCC99"><span class="style9">Password</span></td>
                <td bordercolor="#000000"><label>
                  <input type="password" name="password" id="password"/>
                </label></td>
              </tr>
              
              <tr>
                <td colspan="2" bordercolor="#000000" bgcolor="#996666"><label>
                <div align="center">
                  <input type="submit" name="Submit" value="Login" />
                </div>
                </label></td>
              </tr>
            </table>
           
        </div></td>
      </tr>
    </table>
     <p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>
  </div>
</form>

</body>
</html>