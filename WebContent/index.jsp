<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Online book store</h2>
	<hr>
		<pre>
		<form action="VerifyUser">
			userid   <input type="text" name="userid">
			password <input type="text" name="password">
			Remember me<input type="checkbox" name="check" value="yes">
			
			User type  	owmer<input type="radio" name="utype" value="owner">
		         		Buyer<input type="radio" name="utype" value="buyer" checked="checked">
					<input type="submit" value="Login">		  
			</form>
		</pre>
	<hr>
	
	<a href="registration.jsp">New user</a>
</body>
</html>



