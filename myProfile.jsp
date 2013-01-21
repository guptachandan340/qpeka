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
    
    <div class="span2">
    <br>
    <img src="assets/img/testimg2.jpg" style="height: 180px;width: 150px;"  class="img-polaroid"><br>
    <hr>
    <ul>
    	<li>My Profile</li>
    	<li>My Account</li>
    	<li>Upload</li>
    </ul>								
    </div>
    <div class="span7">
    <br>
    <h4>My Reads</h4>
   <div class="tabbable tabs-left" style="margin-bottom: 18px;">
		          <ul class="nav nav-tabs">
				    <li class="active"><a href="#tab11" data-toggle="tab">Books</a></li>
				    <li><a href="#tab12" data-toggle="tab">Short stories</a></li>
				    <li><a href="#tab13" data-toggle="tab">Poems</a></li>
				    <li><a href="#tab14" data-toggle="tab">Articles</a></li>
				  </ul>
				  <div class="tab-content" style="padding-bottom: 9px; border-bottom: 1px solid #ddd;">
				    <div class="tab-pane active" id="tab11">
				      	<div id="myCarousel" class="carousel slide">
					      <div class="carousel-inner">
					      <div class="item active">
					          <div class="row-fluid">
					          	<div class="span3">
					          		<img src="assets/img/testimg.jpg" style="height: 150px;width: 100px;" class="img-polaroid">					      
					          	</div>
					          	<div class="span3">
					          		<img src="assets/img/testimg1.jpg" style="height: 150px;width: 100px;" class="img-polaroid">
					          	</div>
					          	<div class="span3">
					          		<img src="assets/img/testimg2.jpg" style="height: 150px;width: 100px;" class="img-polaroid">
					          	</div>
					          	<div class="span3">
					          		<img src="assets/img/testimg1.jpg" style="height: 150px;width: 100px;" class="img-polaroid">
					          	</div>
					          </div>					          									          
					      </div>
					      <div class="item ">
					         <div class="row-fluid">
					          	<div class="span3">
					          		<img src="assets/img/testimg3.jpg" style="height: 150px;width: 100px;" class="img-polaroid">					      
					          	</div>
					          	<div class="span3">
					          		<img src="assets/img/testimg4.jpg" style="height: 150px;width: 100px;" class="img-polaroid">
					          	</div>
					          	<div class="span3">
					          		<img src="assets/img/testimg5.jpg" style="height: 150px;width: 100px;" class="img-polaroid">
					          	</div>
					          	<div class="span3">
					          		<img src="assets/img/testimg4.jpg" style="height: 150px;width: 100px;" class="img-polaroid">
					          	</div>
					          </div>				         
					       </div>
					      </div>
					      
					      <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
					      <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
					    </div><!-- /.carousel -->

				    </div>
				    <div class="tab-pane" id="tab12">
				      <div id="myCarousel" class="carousel slide">
					      <div class="carousel-inner">
					      <div class="item active">
					          <div class="row-fluid">
					          	<div class="span3">
					          		<img src="assets/img/testimg.jpg" style="height: 150px;width: 100px;" class="img-polaroid">					      
					          	</div>
					          	<div class="span3">
					          		<img src="assets/img/testimg1.jpg" style="height: 150px;width: 100px;" class="img-polaroid">
					          	</div>
					          	<div class="span3">
					          		<img src="assets/img/testimg2.jpg" style="height: 150px;width: 100px;" class="img-polaroid">
					          	</div>
					          	<div class="span3">
					          		<img src="assets/img/testimg1.jpg" style="height: 150px;width: 100px;" class="img-polaroid">
					          	</div>
					          </div>					          									          
					      </div>
					      <div class="item ">
					         <div class="row-fluid">
					          	<div class="span3">
					          		<img src="assets/img/testimg3.jpg" style="height: 150px;width: 100px;" class="img-polaroid">					      
					          	</div>
					          	<div class="span3">
					          		<img src="assets/img/testimg4.jpg" style="height: 150px;width: 100px;" class="img-polaroid">
					          	</div>
					          	<div class="span3">
					          		<img src="assets/img/testimg5.jpg" style="height: 150px;width: 100px;" class="img-polaroid">
					          	</div>
					          	<div class="span3">
					          		<img src="assets/img/testimg4.jpg" style="height: 150px;width: 100px;" class="img-polaroid">
					          	</div>
					          </div>				         
					       </div>
					      </div>
					      
					      <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
					      <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
					    </div><!-- /.carousel -->

				    </div>
				    <div class="tab-pane" id="tab13">
				      <div id="myCarousel" class="carousel slide">
					      <div class="carousel-inner">
					      <div class="item active">
					          <div class="row-fluid">
					          	<div class="span3">
					          		<img src="assets/img/testimg.jpg" style="height: 150px;width: 100px;" class="img-polaroid">					      
					          	</div>
					          	<div class="span3">
					          		<img src="assets/img/testimg1.jpg" style="height: 150px;width: 100px;" class="img-polaroid">
					          	</div>
					          	<div class="span3">
					          		<img src="assets/img/testimg2.jpg" style="height: 150px;width: 100px;" class="img-polaroid">
					          	</div>
					          	<div class="span3">
					          		<img src="assets/img/testimg1.jpg" style="height: 150px;width: 100px;" class="img-polaroid">
					          	</div>
					          </div>					          									          
					      </div>
					      <div class="item ">
					         <div class="row-fluid">
					          	<div class="span3">
					          		<img src="assets/img/testimg3.jpg" style="height: 150px;width: 100px;" class="img-polaroid">					      
					          	</div>
					          	<div class="span3">
					          		<img src="assets/img/testimg4.jpg" style="height: 150px;width: 100px;" class="img-polaroid">
					          	</div>
					          	<div class="span3">
					          		<img src="assets/img/testimg5.jpg" style="height: 150px;width: 100px;" class="img-polaroid">
					          	</div>
					          	<div class="span3">
					          		<img src="assets/img/testimg4.jpg" style="height: 150px;width: 100px;" class="img-polaroid">
					          	</div>
					          </div>				         
					       </div>
					      </div>
					      
					      <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
					      <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
					    </div><!-- /.carousel -->

				    </div>
				    <div class="tab-pane" id="tab14">
				     <div id="myCarousel" class="carousel slide">
					      <div class="carousel-inner">
					      <div class="item active">
					          <div class="row-fluid">
					          	<div class="span3">
					          		<img src="assets/img/testimg.jpg" style="height: 150px;width: 100px;" class="img-polaroid">					      
					          	</div>
					          	<div class="span3">
					          		<img src="assets/img/testimg1.jpg" style="height: 150px;width: 100px;" class="img-polaroid">
					          	</div>
					          	<div class="span3">
					          		<img src="assets/img/testimg2.jpg" style="height: 150px;width: 100px;" class="img-polaroid">
					          	</div>
					          	<div class="span3">
					          		<img src="assets/img/testimg1.jpg" style="height: 150px;width: 100px;" class="img-polaroid">
					          	</div>
					          </div>					          									          
					      </div>
					      <div class="item ">
					         <div class="row-fluid">
					          	<div class="span3">
					          		<img src="assets/img/testimg3.jpg" style="height: 150px;width: 100px;" class="img-polaroid">					      
					          	</div>
					          	<div class="span3">
					          		<img src="assets/img/testimg4.jpg" style="height: 150px;width: 100px;" class="img-polaroid">
					          	</div>
					          	<div class="span3">
					          		<img src="assets/img/testimg5.jpg" style="height: 150px;width: 100px;" class="img-polaroid">
					          	</div>
					          	<div class="span3">
					          		<img src="assets/img/testimg4.jpg" style="height: 150px;width: 100px;" class="img-polaroid">
					          	</div>
					          </div>				         
					       </div>
					      </div>
					      
					      <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
					      <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
					    </div><!-- /.carousel -->

				    </div>
				    
				  </div>
				</div> <!-- /tabbable -->
				
				<br>
				<div class="tabbable"> <!-- Only required for left/right tabs -->
				  <ul class="nav nav-tabs">
				    <li class="active"><a href="#tab1" data-toggle="tab">Updates</a></li>
				    <li><a href="#tab2" data-toggle="tab">Friends</a></li>
				    <li><a href="#tab3" data-toggle="tab">Fans</a></li>
				    <li><a href="#tab4" data-toggle="tab">Statistics</a></li>
				  </ul>
				  <div class="tab-content">
				    <div class="tab-pane active" id="tab1">
				        <div class="media">
			              <a class="pull-left" href="#">
			              	<img src="assets/img/testimg.jpg" style="height: 60px;width: 50px;" class="img-polaroid media-object">		               
			              </a>
			              <div class="media-body">
			                <b>Anonymous user</b><p class="pull-right"> <small class="muted">24th August, 2012</small></p><br>
			                <small>Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo.</small>
			              </div>
					    </div>
					    <div class="media">
			              <a class="pull-left" href="#">
			              	<img src="assets/img/testimg.jpg" style="height: 60px;width: 50px;" class="img-polaroid media-object">		               
			              </a>
			              <div class="media-body">
			                <b>Anonymous user</b><p class="pull-right"> <small class="muted">24th August, 2012</small></p><br>
			                <small>Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo.</small>
			              </div>
					    </div>
					    <div class="media">
			              <a class="pull-left" href="#">
			              	<img src="assets/img/testimg.jpg" style="height: 60px;width: 50px;" class="img-polaroid media-object">		               
			              </a>
			              <div class="media-body">
			                <b>Anonymous user</b><p class="pull-right"> <small class="muted">24th August, 2012</small></p><br>
			                <small>Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo.</small>
			              </div>
					    </div>
					    <div class="media">
			              <a class="pull-left" href="#">
			              	<img src="assets/img/testimg.jpg" style="height: 60px;width: 50px;" class="img-polaroid media-object">		               
			              </a>
			              <div class="media-body">
			                <b>Anonymous user</b><p class="pull-right"> <small class="muted">24th August, 2012</small></p><br>
			                <small>Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo.</small>
			              </div>
					    </div>
					    <div class="media">
			              <a class="pull-left" href="#">
			              	<img src="assets/img/testimg.jpg" style="height: 60px;width: 50px;" class="img-polaroid media-object">		               
			              </a>
			              <div class="media-body">
			                <b>Anonymous user</b><p class="pull-right"> <small class="muted">24th August, 2012</small></p><br>
			                <small>Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo.</small>
			              </div>
					    </div>
				    </div>				
				    <div class="tab-pane" id="tab2">
				     	 <div class="container-fluid">
						  <div class="row-fluid">
						    <div class="span6">
						     	<div class="media">
					              <a class="pull-left" href="#">
					              	<img src="assets/img/testimg.jpg" style="height: 60px;width: 50px;" class="img-polaroid media-object">		               
					              </a>
					              <div class="media-body">
					                <b>Anonymous user</b><p class="pull-right"> </p><br>
					                <small>(Reader)</small>
					              </div>
							    </div>
						    </div>
						    <div class="span6">
						       <div class="media">
					              <a class="pull-left" href="#">
					              	<img src="assets/img/testimg.jpg" style="height: 60px;width: 50px;" class="img-polaroid media-object">		               
					              </a>
					              <div class="media-body">
					                <b>Anonymous user</b><p class="pull-right"> </p><br>
					                <small>(Writer)</small>
					              </div>
							    </div>
						    </div>
						  </div>
						  <br>
						  <div class="row-fluid">
						    <div class="span6">
						     	<div class="media">
					              <a class="pull-left" href="#">
					              	<img src="assets/img/testimg.jpg" style="height: 60px;width: 50px;" class="img-polaroid media-object">		               
					              </a>
					              <div class="media-body">
					                <b>Anonymous user</b><p class="pull-right"> </p><br>
					                <small>(Reader)</small>
					              </div>
							    </div>
						    </div>
						    <div class="span6">
						       <div class="media">
					              <a class="pull-left" href="#">
					              	<img src="assets/img/testimg.jpg" style="height: 60px;width: 50px;" class="img-polaroid media-object">		               
					              </a>
					              <div class="media-body">
					                <b>Anonymous user</b><p class="pull-right"> </p><br>
					                <small>(Writer)</small>
					              </div>
							    </div>
						    </div>
						  </div>
						  <br>
						  <div class="row-fluid">
						    <div class="span6">
						     	<div class="media">
					              <a class="pull-left" href="#">
					              	<img src="assets/img/testimg.jpg" style="height: 60px;width: 50px;" class="img-polaroid media-object">		               
					              </a>
					              <div class="media-body">
					                <b>Anonymous user</b><p class="pull-right"> </p><br>
					                <small>(Reader)</small>
					              </div>
							    </div>
						    </div>
						    <div class="span6">
						       <div class="media">
					              <a class="pull-left" href="#">
					              	<img src="assets/img/testimg.jpg" style="height: 60px;width: 50px;" class="img-polaroid media-object">		               
					              </a>
					              <div class="media-body">
					                <b>Anonymous user</b><p class="pull-right"> </p><br>
					                <small>(Writer)</small>
					              </div>
							    </div>
						    </div>
						  </div>
						</div>	
				    </div>
				    <div class="tab-pane" id="tab3">
				     	 <div class="container-fluid">
						  <div class="row-fluid">
						    <div class="span6">
						     	<div class="media">
					              <a class="pull-left" href="#">
					              	<img src="assets/img/testimg.jpg" style="height: 60px;width: 50px;" class="img-polaroid media-object">		               
					              </a>
					              <div class="media-body">
					                <b>Anonymous user</b><p class="pull-right"> </p><br>
					                <small>(Reader)</small>
					              </div>
							    </div>
						    </div>
						    <div class="span6">
						       <div class="media">
					              <a class="pull-left" href="#">
					              	<img src="assets/img/testimg.jpg" style="height: 60px;width: 50px;" class="img-polaroid media-object">		               
					              </a>
					              <div class="media-body">
					                <b>Anonymous user</b><p class="pull-right"> </p><br>
					                <small>(Writer)</small>
					              </div>
							    </div>
						    </div>
						  </div>
						  <br>
						  <div class="row-fluid">
						    <div class="span6">
						     	<div class="media">
					              <a class="pull-left" href="#">
					              	<img src="assets/img/testimg.jpg" style="height: 60px;width: 50px;" class="img-polaroid media-object">		               
					              </a>
					              <div class="media-body">
					                <b>Anonymous user</b><p class="pull-right"> </p><br>
					                <small>(Reader)</small>
					              </div>
							    </div>
						    </div>
						    <div class="span6">
						       <div class="media">
					              <a class="pull-left" href="#">
					              	<img src="assets/img/testimg.jpg" style="height: 60px;width: 50px;" class="img-polaroid media-object">		               
					              </a>
					              <div class="media-body">
					                <b>Anonymous user</b><p class="pull-right"> </p><br>
					                <small>(Writer)</small>
					              </div>
							    </div>
						    </div>
						  </div>
						  <br>
						  <div class="row-fluid">
						    <div class="span6">
						     	<div class="media">
					              <a class="pull-left" href="#">
					              	<img src="assets/img/testimg.jpg" style="height: 60px;width: 50px;" class="img-polaroid media-object">		               
					              </a>
					              <div class="media-body">
					                <b>Anonymous user</b><p class="pull-right"> </p><br>
					                <small>(Reader)</small>
					              </div>
							    </div>
						    </div>
						    <div class="span6">
						       <div class="media">
					              <a class="pull-left" href="#">
					              	<img src="assets/img/testimg.jpg" style="height: 60px;width: 50px;" class="img-polaroid media-object">		               
					              </a>
					              <div class="media-body">
					                <b>Anonymous user</b><p class="pull-right"> </p><br>
					                <small>(Writer)</small>
					              </div>
							    </div>
						    </div>
						  </div>
						</div>	
				    </div>
				    <div class="tab-pane" id="tab4">
				    	<div class="container-fluid">
				    	<div class="span4">
						  
						  	<dl class="dl-horizontal">
							  <dt>Friends</dt>
							  <dd>10<i class="icon-hand-up"></i> <small>(since 12/12/2012)</small></dd>
							  <dt>Fans</dt>
							  <dd>5<i class="icon-hand-down"></i> <small>(since 12/12/2012)</small></dd>
							</dl>
						  	 
						  </div>
						<div class="span1">
				    	</div>
						 </div>
				    	
				    </div>
				  </div>
				</div>
			</div>
    	
    </div>
    <div class="span3">
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
