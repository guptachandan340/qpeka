<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Qpeka</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }
    </style>
    <script src="bootstrap/js/jquery.js"></script>
    <link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
	<script src="bootstrap/js/home/home.js"></script>
	
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="assets/ico/favicon.png">
 </head>
  <body>
    <!-- Button to trigger modal -->

	<!-- Login Modal -->
	<div id="login" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
			<h3 id="myModalLabel">Login</h3>
		</div>
		<div class="modal-body">
			<form class="form-signin">
				<input type="text" class="input-block-level" placeholder="Email address">
				<input type="password" class="input-block-level" placeholder="Password">
				<label class="checkbox">
				<input type="checkbox" value="remember-me"> Remember me
				</label>
				<button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
			    <button class="btn btn-medium btn-primary" type="submit">Login</button>
				
			</form>	
		</div>
		
	 </div>

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    
    <script src="bootstrap/js/bootstrap.js"></script>
  </body>
</html>
