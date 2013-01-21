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
		<br><br>
     	<div class="row-fluid">
     		<div class="span1"></div>
     		<div class="span10">
	     		<div class="tabbable" style="margin-bottom: 18px;">
					  <ul class="nav nav-tabs">
					    <li class="active"><a href="#tab1" data-toggle="tab">Personal Information</a></li>
					    <li><a href="#tab2" data-toggle="tab">Profile picture</a></li>
					    <li><a href="#tab3" data-toggle="tab">Perferences</a></li>
					    <li><a href="#tab4" data-toggle="tab">Languages</a></li>
					  </ul>
					  <div class="tab-content" style="padding-bottom: 9px; border-bottom: 1px solid #ddd;">
					    <!-- Perssonal Information -->
					    <div class="tab-pane active" id="tab1">
					      	
					      	<form class="form-horizontal">
							  <div class="control-group">
							    <label class="control-label" for="fname">First Name</label>
							    <div class="controls">
							      <input type="text" id="fname" placeholder="First Name">
							    </div>
							  </div>
							  <div class="control-group">
							    <label class="control-label" for="lname">Last Name</label>
							    <div class="controls">
							      <input type="text" id="lname" placeholder="Last Name">
							    </div>
							  </div>
							  <div class="control-group">
							    <label class="control-label" for="gender">Gender</label>
							     <div class="controls">
								    <select>
										  <option value="male">Male</option>
										  <option value="female">Female</option>
										 
								    </select>
								 </div>
							  </div>
							   <div class="control-group">
							    <label class="control-label" for="dob">Date of Birth</label>
							     <div class="controls">
								     <select name="year" class="span2">
										  <option value="2012">2012</option>
										  <option value="2013">2013</option>								 
								    </select >
								    <select name="month" class="span2">
										  <option value="jan">January</option>
										  <option value="feb">February</option>
										  <option value="mar">March</option>
										  <option value="apr">April</option>
										  <option value="may">May</option>
										  <option value="jun">June</option>
										  <option value="jul">July</option>
										  <option value="aug">August</option>
										  <option value="sep">September</option>
										  <option value="oct">October</option>
										  <option value="nov">November</option>
										  <option value="dec">December</option>								 
								    </select>
								    <select name="day" class="span1">
										  <option value="1">1</option>
										  <option value="2">2</option>								 
								    </select>
								 </div>
							  </div>
							  <div class="control-group">
							    <label class="control-label" for="city">City</label>
							     <div class="controls">
								     <input type="text" id="city" placeholder="City">
								 </div>
							  </div>					  
							  <div class="control-group">
							    <label class="control-label" for="state">State</label>
							     <div class="controls">
								     <input type="text" id="state" placeholder="State">
								 </div>
							  </div>
							  <div class="control-group">
							    <label class="control-label" for="country">Country</label>
							     <div class="controls">
								      <select name="country">
										  <option value="India">India</option>
										  <option value="China">China</option>		
										  <option value="Korea">Korea</option>		
										  <option value="US">US</option>								 
								    </select>
								 </div>
							  </div>
							  <div class="control-group">
							    <label class="control-label" for="website">Website</label>
							     <div class="controls">
								   <input type="text" id="website" class="span5"  placeholder="Website">
								 </div>
							  </div>
							  <div class="control-group">
							    <label class="control-label" for="aboutme">About me</label>
							     <div class="controls">
								  <textarea rows="5" cols="20" id="aboutme" class="span4" placeholder="About me"></textarea>
								 </div>
							  </div>
							  
							  <div class="control-group">
							    <div class="controls">					     
							      <button type="submit" class="btn">Edit</button>
							    </div>
							  </div>
							</form>
												      	
					      	
					    </div>
					    <div class="tab-pane" id="tab2">
					      <!-- Picture -->
					      <br>
					      <div class="row-fluid">
					      	<div class="span1"></div>
							<div class="span2">
								<img src="assets/img/ad1.jpg" style="height: 120px;width: 120px;" class="img-polaroid">
							</div>
							<div class="span8">
							<input type="file" id="website" class="span4"  placeholder="Website">
							<br>
							<button class="btn btn-small btn-primary" type="submit">Upload</button>								    
							</div>
							<div class="span1"></div>
						  </div>
						  <br>
					    </div>
					    <div class="tab-pane" id="tab3">
					      <!-- Genre -->
					       <div class="row-fluid">
					      	<div class="span1"></div>				      	
					      	<div class="span8">	     
					      	<form class="form-horizontal">							 
					      	 <div class="control-group">
							    <label class="control-label" for="aboutme">Preferences</label>
							     <div class="controls">
								 	<div class="thumbnail">
								     	<span class="label label-info">Info</span>
								    </div>
								 </div>
							  </div>		
							</form>					 				      	 
					      	</div>
					      	<div class="span1"></div>
					       </div>
					      
					    </div>
					    <div class="tab-pane" id="tab4">				 
	
					    </div>
					    
					  </div>
				</div> <!-- /tabbable -->
			</div>
			<div class="span1"></div>
      	</div>
    </div>

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
