<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Qpeka</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link href='http://fonts.googleapis.com/css?family=Gabriela'
	rel='stylesheet' type='text/css'>
<!-- Le styles -->
<script src="bootstrap/js/jquery.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script src="bootstrap/js/bootstrap-modal.js"></script>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet" />
<link href="bootstrap/css/custom.css" rel="stylesheet" />
<script src="bootstrap/js/landing/landing.js"></script>
<!-- <script language="javascript">
	    !function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");
	    </script>-->
<!-- <script  language = "javascript" src="js/custom.js">
         </script>-->


<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 0px;
	background-image: url('img/10.jpg');
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: cover;
    background-color:#F4FBFB;
}

p {
	font-family: gabriela, serif; <!-- change font-family --> font-size :
	14px;
	color: #4863a0;
}

h3 {
	color: #33cccc;
	font-family: gabriela, serif;
	<!--
	change
	font-family
	-->
}

.child {
	max-height: 100%;
}
</style>


<script type="text/javascript">
var msg = '<%=request.getParameter("msg")%>';
	var notify = function() {
		if (msg === 'success')
			alert('User details stores successfully');
	}
</script>
</head>
<body bgcolor="#C2C2C2" onload="notify();">

	<div class="row-fluid">
		<div class="span2"></div>
		<div class="span3">
			<img src="assets/img/logo.gif" style="width:45%; height:25%" alt="qpeka logo" />
		</div>
		<div class="span3">
			<br> <br> <br>
			<iframe
				src="https://www.facebook.com/plugins/like.php?href=http://www.facebook.com/QPeka&amp;width=250&amp;height=80&amp;show_faces=true&amp;colorscheme=light&amp;stream=false&amp;border_color&amp;header=false"
				scrolling="no" frameborder="0"
				style="border: none; overflow: hidden; width: 292px; height: 80px"
				allowTransparency="true"></iframe>
		</div>
		<div class="span2">
			<br> <br> <br> <a href="https://twitter.com/Qpeka"
				class="twitter-follow-button" data-show-count="true"
				data-size="large" data-dnt="true">Follow @Qpeka</a>


		</div>
		<div class="span2"></div>


	</div>
	<br>
	<div class="row-fluid">


		<div class="span10 ">
			<div class="tabbable tabs-right" style="padding-bottom: 9px; max-height: 360px; height: 360px;">
				<ul class="nav nav-tabs">
					<li class="active" ><a href="#tab1" data-toggle="tab" ><b><!--make bold-->What is Qpeka</b></a></li><!-- remove ? mark from tab-->
				    <li><a href="#tab2" data-toggle="tab" ><b><!--make bold-->Qpeka for Readers</b></b></a></li>
				    <li><a href="#tab3" data-toggle="tab"><b><!--make bold-->Qpeka for Writers</b></a></li>
					<li><a href="#tab4" data-toggle="tab"><b><!--make bold-->Qpeka Offers</b></a></li><!--change tab name -->
				</ul>

				<div class="span2"></div>
				<div class="tab-content" style="padding-bottom: 9px;">

					<div class="tab-pane active" id="tab1">
						<div class="span12">
							<h3 style="color: #33cccc">What is Qpeka</h3>
							<p>
								Qpeka is a unique reading platform, where readers can get books, short stories, poems, articles, blogs, all for FREE. We have an author-oriented approach to publishing on our platform which makes it possible for them to earn even though it’s free to the readers.<br><br>
                    The dynamics of our platform are the stepping stone to help us reach our goal to make the LARGEST ONLINE READING PLATFORM in the world. Do make sure to go through our concepts and take advantage of our offers for early registration!
							</p>
						</div>
					</div>
					<div class="tab-pane" id="tab2">
						<div class="span12">
							<h3 style="color: #33cccc">Qpeka for Reader</h3>
							<p>Whatever you wish to read at Qpeka is available for FREE.<br><br>
                    What's more, your reading progress is automatically logged by us. So, you can forget where you had stopped reading as the next time you login you can resume where you had left off earlier..We have an amazing reward points system as well, where readers get points and grow in levels for their involvement with the website. They can redeem the points for free gifts, free services etc.<br><br>
                    Qpeka also functions as a social interaction platform for readers and writers to share their views and connect with their friends. Thus, you can talk about your favorite reads, see your friend’s activities and recommend them something for reading etc.<br><br>
					We have loads of other interesting features coming as well including mobile applications for support on various portable devices, to ensure you have Qpeka when you want wherever you want.</p>
						</div>
					</div>
					<div class="tab-pane" id="tab3">
						<div class="span12">
							<h3 style="color: #33cccc">Qpeka for Writers</h3>
							<p>If you are an author, Qpeka is a perfect publishing platform for you. Along with the highest reader exposure on account of the free reading principle, Qpeka ensures you earn for every page of your work as soon as it read by someone. Thus, effectively, you earn royalty for every page read. Further, your level shall determine the rate of royalty per page.<br><br>
                    We also help author-publisher relations, by taking across promising content to publishers/ producers etc to be recreated in other artistic forms. A robust review system also provides you with the necessary inputs for your works. We also provide a host of other writer oriented services such as copyrighting, editing, cover designing, etc. to ensure that you can improve the quality of the works.<br><br>
					All in all, we at Qpeka are committed to give the best to our authors' community.<br><br></p>
						</div>
					</div>
					<div class="tab-pane" id="tab4">
						<div class="span12">
							<h3 style="color: #33cccc">Qpeka offers</h3>
							<br>
							<h4>Coming soon.....</h4>
<!-- 							<form class="form-horizontal"> -->
<!-- 								<div class="control-group"> -->
<!-- 									<label class="control-label" for="inputEmail">Email</label> -->
<!-- 									<div class="controls"> -->
<!-- 										<input type="text" id="inputEmail" placeholder="Email"> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="control-group"> -->
<!-- 									<label class="control-label" for="inputPassword">Password</label> -->
<!-- 									<div class="controls"> -->
<!-- 										<input type="password" id="inputPassword" -->
<!-- 											placeholder="Password"> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="control-group"> -->
<!-- 									<div class="controls"> -->
<!-- 										<label class="checkbox"> <input type="checkbox"> -->
<!-- 											Remember me </label> -->
<!-- 										<button type="submit" class="btn">Sign in</button> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</form> -->

							</h3>
						</div>
					</div>


				</div>
				<!-- /tabbable -->
			</div>

			<div class="row">
				<div class="span4"></div>
				<div class="span4">
					<a style="text-decoration: none;" href="#login" class="navbar-link" data-toggle="modal"><button  class="btn btn-large btn-block btn-primary" type="button" >This is Awesome!!</button></a>
				</div>
				<div class="span4">
					<!-- Login Modal -->
					<div id="login" class="modal hide fade" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">X</button>
							<h3 id="myModalLabel">Keep Me Posted...</h3>
						</div>
						<div class="modal-body">
							<form class="form-signin" id="awsomeform" name="awsomeform"
								action="http://landing-qpeka.rhcloud.com/QPEKA/register"
								method="post" enctype="multipart/form-data">
								<input type="text" class="input-block-level" name="firstName"
									id="firstName" placeholder="First Name"> <input
									type="text" class="input-block-level" name="lastName"
									id="lastName" placeholder="Last Name"> <input
									type="text" onblur="checkUserAvail();" onfocus="setVar();"
									class="input-block-level" name="email" id="email"
									placeholder="Email address"> <input type="hidden"
									name="landing" id="landing" value="true">'
								<p id="error" class="text-error"></p>
								<p id="success" class="text-success"></p>
								<label class="checkbox"> <input type="checkbox"
									name="writer" value="remember-me"> Click Here if you
									are a writer </label>
								<button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
								<button class="btn btn-medium btn-primary" type="submit"
									onClick="return isValid(this)">Notify</button>

							</form>
						</div>
					</div>


				</div>



			</div>
		</div>
	</div>
	<!-- Le javascript ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="bootstrap/js/bootstrap-transition.js"></script>
	<script src="bootstrap/js/bootstrap-alert.js"></script>
	<script src="bootstrap/js/bootstrap-dropdown.js"></script>
	<script src="bootstrap/js/bootstrap-scrollspy.js"></script>
	<script src="bootstrap/js/bootstrap-tab.js"></script>
	<script src="bootstrap/js/bootstrap-tooltip.js"></script>
	<script src="bootstrap/js/bootstrap-popover.js"></script>
	<script src="bootstrap/js/bootstrap-button.js"></script>
	<script src="bootstrap/js/bootstrap-collapse.js"></script>
	<script src="bootstrap/js/bootstrap-carousel.js"></script>
	<script src="bootstrap/js/bootstrap-typeahead.js"></script>
	<script src="assets/js/jquery.validate.min.js"></script>
	<script src="bootstrap/js/jquery-form.js"></script>
	<script src="js/custom.js"></script>

</body>
</html>