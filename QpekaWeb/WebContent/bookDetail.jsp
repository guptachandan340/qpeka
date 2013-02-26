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
	<script src="bootstrap/js/bookDetails/bookDetail.js"></script>
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

  <body onload="getBook('<%=request.getParameter("id")%>');">

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
      	<div class="span1"></div>
        <div class="span10">
        <!-- Row one -->
          <div class="row-fluid">
         	<div class="span8">
         	<br>
         	<div class="thumbnail">
         		<div class="row-fluid">	
         			<div class="span1">
         			</div>
         			<div class="span3">
         				<br>
	         			<img src="assets/img/testimg.jpg" style="height: 220px;width: 160px;" class="img-polaroid"><br><br>
	         			<div class="btn-group">				
						  <div class="btn-group">
			                <button class="btn btn-info btn-small dropdown-toggle" data-toggle="dropdown">Info <span class="caret"></span></button>
			               
			                <ul class="dropdown-menu">
			                 <small>
			                  <li><a href="#">Read this book</a></li>
			                  <li><a href="#">Rate this book</a></li>
			                  <li><a href="#">Buy this book</a></li>
			                  <li class="divider"></li>
			                  <li><a href="#">Search online</a></li>
			                  </small>
			                </ul>
			                
			              </div>
						</div>
						<span class="seostars"> &nbsp;&nbsp; <strong itemprop="average">3.7</strong> out of <i itemprop="best">5</i></span> 
			        </div>
			        <div class="span8">
			        
			        	<h3 id="title" ></h3>
			        	<small id="author" class="muted" ></small><br>
			        	<small id="ratings" class="text-success">Rating: 3.92/5 &nbsp;&nbsp; 3200 Ratings &nbsp;&nbsp; 890 Reviews</small><br><br>
			        	<p id="desc">
			        			
			        		
			        	</p>
			        	<hr>
			        	<small id="details">
<!-- 			        		<dl class="dl-horizontal"> -->
<!-- 							  <dt>ISBN</dt> -->
<!-- 							  <dd>0765331268 (ISBN13: 9780765331267)</dd> -->
<!-- 							  <dt>Edition language</dt> -->
<!-- 							  <dd>English</dd> -->
<!-- 							  <dt>Date  of publication</dt> -->
<!-- 							  <dd>July 5th 2011</dd> -->
<!-- 							  <dt>Publisher</dt> -->
<!-- 							  <dd>T.M.H</dd> -->
<!-- 							</dl> -->
						</small>
			        	<hr>		   
			       				       					       
			        </div>
         		</div>
         		
         		<div class="row-fluid">
         			<div class="span1">
         			</div>
         			<div class="span10">
         				<h4>User comments</h4>
         				<div class="media">
			              <a class="pull-left" href="#">
			              	<img src="assets/img/testimg.jpg" style="height: 60px;width: 50px;" class="img-polaroid media-object">		               
			              </a>
			              <div class="media-body">
			                <b>Anonymous user</b><p class="pull-right"> <small class="muted">24th August, 2012</small></p><br>
			                <small>Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo.</small>
			              </div>
					    </div>
					    <hr>
					    <div class="media">
					              <a class="pull-left" href="#">
					              	<img src="assets/img/testimg.jpg" style="height: 60px;width: 50px;" class="img-polaroid media-object">		               
					              </a>
					              <div class="media-body">
					                <b>Anonymous user</b><p class="pull-right"> <small class="muted">24th August, 2012</small></p><br>
					                <small>Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo.</small>
					              </div>
					    </div>
					    <hr>
					    <div class="media">
					              <a class="pull-left" href="#">
					              	<img src="assets/img/testimg.jpg" style="height: 60px;width: 50px;" class="img-polaroid media-object">		               
					              </a>
					              <div class="media-body">
					                <b>Anonymous user</b><p class="pull-right"> <small class="muted">24th August, 2012</small></p><br>
					                <small>Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo.</small>
					              </div>
					    </div>
         				<br>
         			</div>        	
         			<div class="span1">
         			</div>
         		</div>     		
  
         	</div>
         	<br>
         		<div class="row-fluid">
					<div class="span4">
						<h4>Experts speak</h4>
						<br>
		                <img src="assets/img/ad1.jpg" style="height: 60px;width: 50px;" class="img-polaroid">
		                <small>Alden Bell is a pseudonym for Joshua Gaylord, whose first novel, Hummingbirds, was released in Fall '09.
		                He teaches at a New York City prep school and is an adjunct professor at The New School. 
		                He lives in New York City with his wife, the Edgar Award-winning mystery writer, Megan Abbott.  </small> 
		                <br>
		                <a href="#"><small><b>More about author...</b></small></a>               		                 		          
					</div>
					<div class="span4">
						<h4>Polls/Surveys</h4>
						
		            	<small class="muted" >Which of the following is the best work by Agatha Christy?</small><br><br>
		            	
		            	<small class="text-success"><a href="#">a. Work A</a></small><br>
		            	<small class="text-success"><a href="#">b. Work B</a></small><br>
		            	<small class="text-success"><a href="#">c. Work C</a></small><br>    
		            	<small class="text-success"><a href="#">d. Work D</a></small><br>    
		            	<br>
		            	<a href="#"><small><b>More polls...</b></small></a>
		            </div>
					<div class="span4">
						<h4>Quizzes</h4>
						<a href="#">Guess the author.</a><br>
							<small class="muted">12 questions    |    23,908 times taken</small>
						<hr>
						
						<a href="#">The  hunger game quiz.</a><br>
							<small class="muted">12 questions    |    23,908 times taken</small>
						<hr>
						
						<a href="#">Guess the first sentence.</a><br>
							<small class="muted">12 questions    |    23,908 times taken</small>
						<hr>
												
						<br>
		            	<a href="#"><small><b>More quizzes...</b></small></a>
		                
					</div>
				</div>			   
         			   
	        </div>
	        
	        <div class="span4">
	        	<br>
	        	<div class="thumbnail">
	        		<br>
					<img src="assets/img/ad1.jpg" style="height: 250px;width: 300px;" class="img-polaroid">	
					<br>
				</div>      
				<br><br>
				<div class="thumbnail">
					<br>
					<img src="assets/img/ad2.jpg" style="height: 250px;width: 300px;" class="img-polaroid">
					<br>
				</div>
	        	<br><br>
	        	<div class="thumbnail">
                  <img data-src="holder.js/300x200" alt="">
                  <div class="caption">
                    <h4>Sponsored links</h4>
                    <img src="assets/img/ad1.jpg" style="height: 60px;width: 50px;" class="img-polaroid">
                    <a href="#">Cronicles of Narnia</a><br>
  					<small>delay showing and hiding the tooltip (ms) - does not apply to manual trigger type
					If a number is supplied, delay is applied to both hide/show</small>
					
					<hr>
					
					<img src="assets/img/ad2.jpg" style="height: 60px;width: 50px;" class="img-polaroid">
                    <a href="#">Cronicles of Narnia</a><br>
  					<small>delay showing and hiding the tooltip (ms) - does not apply to manual trigger type
					If a number is supplied, delay is applied to both hide/show</small>	      
                  </div>
                </div>   
	        </div>
          </div>
          <!-- Row two -->
           
        </div><!--/span-->
        <div class="span1"></div>
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
