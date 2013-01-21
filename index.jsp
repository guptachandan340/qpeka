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
     <script>
	$(document).ready(function() {
	
		alert($.url().param('ui'));
	
	});
  </script> 
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
            
            <ul id="testList" class="nav">
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
         		<div class="row-fluid">
         			<div class="span6">
         				<h4>Deciding what to read next?</h4>
						You’re in the right place. Tell us what titles or genres you’ve enjoyed in the past, and we’ll give you surprisingly insightful recommendations.
         			</div>
         			<div class="span6">
         				<h4>What are your friends reading?</h4>
						Chances are your friends are discussing their favorite (and least favorite) books on Goodreads. Want to learn more? Take the tour.
         			</div>
         		</div>
         		<br><br>
         		<div class="row-fluid">
         		<div class="span8">
         		<div class="thumbnail">
         			<br>
	         		<div id="myCarousel" class="carousel slide">
						<div class="carousel-inner">
						      <div class="item active">
						          <div class="row-fluid">
						          		<div class="row-fluid">	          		
											<div class="span3">
												<img src="assets/img/testimg2.jpg" style="height: 200px;width: 120px;"  class="img-polaroid"><br><br>
	<!-- 											<button class="btn btn-small btn-primary" type="button" >Start reading</button> -->
											</div>
											
											<div class="span9">
												 <blockquote>
													 <h4>Adventures of Sherlock Holmes</h4>
													 <small>Sir. Arthur Conan Doyle</small>
												 </blockquote>
												 <small class="text-info">Rating: 3.92/5 &nbsp;&nbsp; 3200 Ratings &nbsp;&nbsp; 890 Reviews</small><br><br>
												 <small>The Adventures of Sherlock Holmes is a collection of twelve stories by 
												 Arthur Conan Doyle, featuring his famous detective and illustrated by Sidney Paget.</small>					 
											</div>
										</div>
						          </div>		
						          <div class="container">					        
						            <div class="span6 carousel-caption">				     			          
						              	<p class="pull-right"> Book of the Day</p>		             
						            </div>
						          
						 		  </div>			         						          
						      </div>
						      <div class="item ">
						         <div class="row-fluid">
						          		<div class="row-fluid">			          		
											<div class="span3">
												<img src="assets/img/testimg4.jpg" style="height: 200px;width: 120px;"  class="img-polaroid"><br><br>
	<!-- 											<button class="btn btn-small btn-primary" type="button" >Start reading</button> -->
											</div>
											
											<div class="span9">
												 <blockquote>
													 <h4>Adventures of Sherlock Holmes</h4>
													 <small>Sir. Arthur Conan Doyle</small>
												 </blockquote>
												 <small class="text-info">Rating: 3.92/5 &nbsp;&nbsp; 3200 Ratings &nbsp;&nbsp; 890 Reviews</small><br><br>
												 <small>The Adventures of Sherlock Holmes is a collection <br>of twelve stories by 
												 Arthur Conan Doyle, featuring his famous <br> detective and illustrated by Sidney Paget.</small>					 
											</div>
										</div>
						         </div>	
						         <div class="container">
							            <div class="span6 carousel-caption">				     			          
							              	<p class="pull-right"> Article of the Day</p>		             
							            </div>
						 		 </div>			         
						      </div>
						 </div>					
						 <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
						 <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
					</div><!-- /.carousel -->
					</div>
					</div>
					<div class="span4">
						<h4>Quotes</h4>
		                <img src="assets/img/ad1.jpg" style="height: 60px;width: 50px;" class="img-polaroid">
		                <blockquote>
							<h5>A friend in need is a friend indeed</h5>
							<small>Anonymous</small>
						</blockquote>
		                <br>
		                <a href="#"><small><b>More quotes...</b></small></a>
					</div>
         		</div>
         		<br><br>
         		<div class="thumbnail">
         		<br>
         		<div class="tabbable tabs-left" style="margin-bottom: 18px;">
				  <ul class="nav nav-tabs">
				    <li class="active"><a href="#tab1" data-toggle="tab">Books</a></li>
				    <li><a href="#tab2" data-toggle="tab">Short stories</a></li>
				    <li><a href="#tab3" data-toggle="tab">Poems</a></li>
				    <li><a href="#tab4" data-toggle="tab">Articles</a></li>
				  </ul>
				  <div class="tab-content" style="padding-bottom: 9px; border-bottom: 1px solid #ddd;">
				    <div class="tab-pane active" id="tab1">
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
					      <div class="container">
					            <div class="span6 carousel-caption">				     			          
					              	<p class="pull-right"> Popular Books...</p>		             
					            </div>
					      </div>
					      <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
					      <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
					    </div><!-- /.carousel -->

				    </div>
				    <div class="tab-pane" id="tab2">
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
					      <div class="container">
					            <div class="span6 carousel-caption">				     			          
					              	<p class="pull-right"> Popular Short Stories...</p>		             
					            </div>
					      </div>
					      <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
					      <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
					    </div><!-- /.carousel -->

				    </div>
				    <div class="tab-pane" id="tab3">
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
					      <div class="container">
					            <div class="span6 carousel-caption">				     			          
					              	<p class="pull-right"> Popular Poems...</p>		             
					            </div>
					      </div>
					      <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
					      <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
					    </div><!-- /.carousel -->

				    </div>
				    <div class="tab-pane" id="tab4">
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
					      <div class="container">
					            <div class="span6 carousel-caption">				     			          
					              	<p class="pull-right"> Popular Articles...</p>		             
					            </div>
					      </div>
					      <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
					      <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
					    </div><!-- /.carousel -->

				    </div>
				    
				  </div>
				</div> <!-- /tabbable -->
				
				</div>
			    <br><br>
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
    
    <script src="bootstrap/js/bootstrap.js"></script>
  </body>
</html>
