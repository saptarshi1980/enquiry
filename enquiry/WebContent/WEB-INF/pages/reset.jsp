<!DOCTYPE html>
<html >
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <meta charset="UTF-8">
  <title>PASSWORD RESET</title>
  
  
  
      <link rel="stylesheet" href="<c:url value="/css/style.css" />">

      <script src="<c:url value="/js/index.js" />"></script>
      <script>
    
</script>
</head>


  <body>
  
<div class="container">
<div align="center"><img src="images/logo_dpl.jpg" /></div><br><br>
	<section id="content">
		<form action="passwordReset.dpl" method="GET">
			<h3>Input your credential</h3>
			<div>
			<input type="text" placeholder="Username" required="" id="username" name="username"/>
			</div>
			<div>
			<input type="password" placeholder="Current Password" required="" id="password" name="password" />
			</div>
			
			<div>
			<input type="password" placeholder="New Password" required="" id="newpassword" name="newpassword" />
			</div>
			<div>
				<input type="submit" value="Submit" />
				
				
			</div>
		</form><!-- form -->
		
	</section><!-- content -->
</div><!-- container -->

  

</body>
</html>
