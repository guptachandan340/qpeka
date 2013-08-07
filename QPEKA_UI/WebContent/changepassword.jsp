<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- 
function createUserObject(){
	//user = new Object();

	user = {
			userid : 2,
			username : "$2a$10$zULe5r0uoMmEYpquESq6duvy5Ne9uzzw/aZB/bLxHQ0dtjI9td3km",
			email : "gupta@gmail.com",
			created : 1375182490,
			lasaccess : 0,
			lastlogin : 0,
			status : 0,
			type : 0,
			timezone : NULL
		};
	//return user;
	document.forms[my_form][userObject].value = user;
	document.forms[my_form].submit();
}
 -->
</head>
<body>
<form action="v1.0/user/changepassword" method="post" id = "my_form">
       
		<p>
			Current Password : <input type="text" name="currentpassword" />
		</p>
		<p>
			new password : <input type="password" name="newpassword" />
		</p>
		<p>
			conform password : <input type="password" name="confirmpassword" />
		</p>
		<p>
		<input type = "submit" value="submit" >
		</p>
		<!-- 
		<p>
		<input type="hidden"  name = "userObject" value="hello"> 
		</p>
		</form>
</body>


<!-- 
<script language="Javascript">
//var user = window.location.href;

user1 = {
		userid : 2,
		username : "ankita",
		password : "$2a$10$GBtjQbLlMKkeZKBTjLBvBuDr6IG8i2NCaJXE96PylSmtUdqift0jS",
		email : "anki546.malani@gmail.com",
		created : 1375033767,
		lasaccess : 0,
		lastlogin : 0,
		status : 0,
		type : 0,
		timezone : NULL
	};
//return user;

document.my_form.userObject.value = user1;
//setTimeout('document.my_form.submit()',100);
</script>
-->
</html>