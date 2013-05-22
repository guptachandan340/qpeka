<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Qpeka</title>
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
     <link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
	<link rel="stylesheet" href="css/reading.css" ></link>
	<script src="../config.js"></script>
    <style type="text/css">
      body {
        padding-top: 5px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }
    </style>
    <script type="text/javascript">
      var epub_dir = 'emma-jane_austen';
      var wid = '<%=request.getParameter("wid")%>';
    </script>
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.2.custom.min.js"></script>
    <script type="text/javascript" src="js/mousewheel.js"></script>    
    <script type="text/javascript" src="js/epubjs.js"></script>

</head>
<body>
	<div id="book" class="style1">
	<div class="row-fluid">
    	<div class="span1">
        </div>
        <div class="span10">
        <div class="row-fluid">
        	<div id="toc-container" class="span5">
        		<br>
        		<ol id="toc"></ol>
        	</div>
        	<div class="span7">
        		<h3 id="content-title"></h3>
        		 <!--This is a comment. Comments are not displayed in the browser-->
			       <div class="pagination pagination-mini">
					  <ul>
					    <li><a href="#" onclick="prev();">Prev</a></li>
					    <li><a href="#" onclick="next();">Next</a></li>
					  </ul>
					  &nbsp;
					  <ul>
					    <li><a href="#" onclick="bookmark();">BookMark</a></li>
					  </ul>
					</div>
					<div id="total-size"><div id="remaining"></div></div>
      				<div id="content" ></div>
        	</div>
        </div>
        </div>
        <div class="span1">
        </div>
    </div>
	
    </div>
</body>
</html>