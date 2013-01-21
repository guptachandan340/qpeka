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
    <link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="../assets/ico/favicon.png">
  </head>

  <body>

    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="#">
          	Qpeka
          </a>
          <div class="nav-collapse collapse">
            <p class="navbar-text pull-right">
              <a href="#login" class="navbar-link" data-toggle="modal">Login</a>&nbsp;&nbsp;|&nbsp;&nbsp;
	 		  <a href="#signup" class="navbar-link" data-toggle="modal">Sign Up</a>&nbsp;&nbsp;|&nbsp;&nbsp;
	 		  <a href="#" class="navbar-link">FAQ</a>&nbsp;&nbsp;|&nbsp;&nbsp;
	 		  <a href="#" class="navbar-link">Contact Us</a>&nbsp;&nbsp;|&nbsp;&nbsp;
	 		  <a href="#" class="navbar-link">About Us</a>
			</p>
            
            <ul class="nav">
              <li class="active"><a href="#">Home</a></li>
              <li><a href="#about">Books</a></li>
              <li><a href="#contact">Short Stories</a></li>
              <li><a href="#about">Poems</a></li>
              <li><a href="#contact">Articles</a></li>
            </ul>
          </div><!--/.nav-collapse -->
          <!-- Modal Login -->
        </div>
      </div>
    </div>
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
	
	<!-- SignUp Modal -->
	<div id="signup" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
			<h3 id="myModalLabel">Sign Up</h3>
		</div>
		<div class="modal-body">
			<form class="form-signin">
				<input type="text" class="input-block-level" placeholder="Email address">
				<input type="text" class="input-block-level" placeholder="Username">
				<input type="password" class="input-block-level" placeholder="Password">
				<input type="password" class="input-block-level" placeholder="Confirm">
				<button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
				<button class="btn btn-medium btn-primary" type="submit">Sign Up</button>
			</form>
		</div>
		
	 </div>
	
    <div class="container-fluid">
      <div class="row-fluid">
      	<div class="span2">
        <h4>Categories</h4>
        <br>
        <div class="thumbnail">
        <ul>
      
        	<li>Fiction</li>
			<li>Comedy</li>
			<li>Children</li>
			<li>Science</li>
			<li>War</li>
			<li>Politics</li>
			<li>Horror</li>
			<li>Computer</li>
			<li>Business</li>
			<li>Cooking</li>
			<li>Health</li>
			<li>History</li>
        
        </ul>
        </div>
        </div><!--/span-->
        <div class="span7">
        
        <div class="tabbable"> <!-- Only required for left/right tabs -->
		  <ul class="nav nav-tabs">
		    <li class="active"><a href="#tab1" data-toggle="tab">Popular</a></li>
		    <li><a href="#tab2" data-toggle="tab">Highest Rated</a></li>
		    <li><a href="#tab2" data-toggle="tab">Latest</a></li>
		  </ul>
		  <div class="tab-content">
		    <div class="tab-pane active" id="tab1">
		    <div class="thumbnail">
		    <div class="row-fluid">
		    <ul class="thumbnails">
		    		      
              <li class="span3">
                <div class="thumbnail">
                  <img src="assets/img/testimg1.jpg" style="height: 120px;width: 120px;" class="img-polaroid">	
                  <div class="caption">               
                    <b>Adventures of Sherlock Holmes</b><br>
					<small>Sir. Arthur Conan Doyle</small>							
					
                    <p><a href="#"><small><b>more</b></small></a></p>
                  </div>
                </div>
              </li>
              
              <li class="span3">
                <div class="thumbnail">
                  <img src="assets/img/testimg2.jpg" style="height: 120px;width: 120px;" class="img-polaroid">	
                  <div class="caption">               
                    <b>Adventures of Sherlock Holmes</b><br>
					<small>Sir. Arthur Conan Doyle</small>							
					
                    <p><a href="#"><small><b>more</b></small></a></p>
                  </div>
                </div>
              </li>
              
              <li class="span3">
                <div class="thumbnail">
                  <img src="assets/img/testimg3.jpg" style="height: 120px;width: 120px;" class="img-polaroid">	
                  <div class="caption">               
                    <b>Adventures of Sherlock Holmes</b><br>
					<small>Sir. Arthur Conan Doyle</small>							
					
                    <p><a href="#"><small><b>more</b></small></a></p>
                  </div>
                </div>
              </li>
              
              <li class="span3">
                <div class="thumbnail">
                  <img src="assets/img/testimg4.jpg" style="height: 120px;width: 120px;" class="img-polaroid">	
                  <div class="caption">               
                    <b>Adventures of Sherlock Holmes</b><br>
					<small>Sir. Arthur Conan Doyle</small>							
					
                    <p><a href="#"><small><b>more</b></small></a></p>
                  </div>
                </div>
              </li>
            </ul>
            <ul class="thumbnails">
		    		      
              <li class="span3">
                <div class="thumbnail">
                  <img src="assets/img/testimg1.jpg" style="height: 120px;width: 120px;" class="img-polaroid">	
                  <div class="caption">               
                    <b>Adventures of Sherlock Holmes</b><br>
					<small>Sir. Arthur Conan Doyle</small>							
					
                    <p><a href="#"><small><b>more</b></small></a></p>
                  </div>
                </div>
              </li>
              
              <li class="span3">
                <div class="thumbnail">
                  <img src="assets/img/testimg2.jpg" style="height: 120px;width: 120px;" class="img-polaroid">	
                  <div class="caption">               
                    <b>Adventures of Sherlock Holmes</b><br>
					<small>Sir. Arthur Conan Doyle</small>							
					
                    <p><a href="#"><small><b>more</b></small></a></p>
                  </div>
                </div>
              </li>
              
              <li class="span3">
                <div class="thumbnail">
                  <img src="assets/img/testimg3.jpg" style="height: 120px;width: 120px;" class="img-polaroid">	
                  <div class="caption">               
                    <b>Adventures of Sherlock Holmes</b><br>
					<small>Sir. Arthur Conan Doyle</small>							
					
                    <p><a href="#"><small><b>more</b></small></a></p>
                  </div>
                </div>
              </li>
              
              <li class="span3">
                <div class="thumbnail">
                  <img src="assets/img/testimg4.jpg" style="height: 120px;width: 120px;" class="img-polaroid">	
                  <div class="caption">               
                    <b>Adventures of Sherlock Holmes</b><br>
					<small>Sir. Arthur Conan Doyle</small>							
					
                    <p><a href="#"><small><b>more</b></small></a></p>
                  </div>
                </div>
              </li>
            </ul>
		    </div>
		    </div>
		    </div>
		    <div class="tab-pane" id="tab2">
		     <div class="thumbnail">
		      <p>Howdy, I'm in Section 2.</p>
		     </div>
		    </div>
		  </div>
		</div>
        
        </div><!--/span-->
       	<div class="span3">
       	<br>
       	<h4>Recommended</h4>
       	<div class="thumbnail">
                  <img src="assets/img/testimg4.jpg" style="height: 120px;width: 130px;" class="img-polaroid">	
                  <div class="caption">               
                    <b>Adventures of Sherlock Holmes</b><br>
					<small>- Sir. Arthur Conan Doyle
					<br>
					<p>
					With the same incomparable style and warm, inviting voice that have made her beloved by millions of 
					readers far and wide, "New York Times" bestselling author....   <a href="#"><b>more</b></a>
					</p>
					</small>							
					
                  </div>
        </div>
   
       	<h4>Featured</h4>
       	  	<div class="thumbnail">
                  <img src="assets/img/testimg3.jpg" style="height: 120px;width: 130px;" class="img-polaroid">	
                  <div class="caption">               
                    <b>Adventures of Sherlock Holmes</b><br>
					<small>- Sir. Arthur Conan Doyle
					<br>
					<p>
					With the same incomparable style and warm, inviting voice that have made her beloved by millions of 
					readers far and wide, "New York Times" bestselling author....   <a href="#"><b>more</b></a>
					</p>
					</small>							
					
                  </div>
        </div>
        
       	</div>
      </div><!--/row-->

      <hr>

      <footer>
        <p>&copy; Qpeka Inc. 2012</p>
      </footer>

    </div><!--/.fluid-container-->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="bootstrap/js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap-transition.js"></script>
    <script src="bootstrap/js/bootstrap-alert.js"></script>
    <script src="bootstrap/js/bootstrap-modal.js"></script>
    <script src="bootstrap/js/bootstrap-dropdown.js"></script>
    <script src="bootstrap/js/bootstrap-scrollspy.js"></script>
    <script src="bootstrap/js/bootstrap-tab.js"></script>
    <script src="bootstrap/js/bootstrap-tooltip.js"></script>
    <script src="bootstrap/js/bootstrap-popover.js"></script>
    <script src="bootstrap/js/bootstrap-button.js"></script>
    <script src="bootstrap/js/bootstrap-collapse.js"></script>
    <script src="bootstrap/js/bootstrap-carousel.js"></script>
    <script src="bootstrap/js/bootstrap-typeahead.js"></script>

  </body>
</html>
