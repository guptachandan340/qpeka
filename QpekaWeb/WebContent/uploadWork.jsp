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
     .label.valid {
   		width: 24px;
   		height: 24px;
   		background: url(assets/img/valid.png) center center no-repeat;
   		display: inline-block;
   		text-indent: -9999px;
   	  }
   	 .label.error {
   		font-weight: bold;
  		color: red;
  		padding: 2px 8px;
  		margin-top: 2px;
  	  }
    </style>
    <link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
	<script src="bootstrap/js/uploadWork/uploadWork.js"></script>
	<script src="bootstrap/js/bootstrap-tooltip.js"></script>
    <script src="bootstrap/js/bootstrap-popover.js"></script>
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

	 <div id="tnc" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
			<h4 id="myModalLabel">Terms and Conditions</h4>
		</div>
		<div class="modal-body">
			<div class="thumbnail">
				BY USING THE ORACLE WEB SITES, YOU AGREE TO THESE TERMS OF USE. IF YOU DO NOT AGREE TO THESE TERMS OF USE, PLEASE DO NOT USE THE ORACLE WEB SITES.

Welcome to the Oracle Web sites (the "Site"). Through the Site, you have access to a variety of resources and content. These include: (a) software and software as a service offerings ("Software"); (b) Web pages, data, messages, text, images, photographs, graphics, audio and video such as podcasts and Webcasts, and documents such as press releases, white papers and product data sheets ("Materials"); and (c) forums, discussion groups, chat areas, bulletin boards, blogs, wikis, e-mail functions, and other services in connection with which you can upload, download, share, email, post, publish, transmit or otherwise access or make available Content (as defined below) ("Community Services"). Software, Materials, Community Services, and other information, content and services are collectively referred to as "Content." By accessing or using the Site or the Content provided on or through the Site, you agree to follow and be bound by the following terms and conditions concerning your access to and use of the Site and the Content provided on or through the Site ("Terms of Use") and our Privacy Policy. Oracle Corporation and its affiliated companies ("We" or "Oracle") may revise the Terms of Use and Privacy Policy at any time without notice to you. The revised Terms of Use and Privacy Policy will be effective when posted. You can review the most current Terms of Use at http://www.oracle.com/html/terms.html and Privacy Policy at http://www.oracle.com/html/privacy.html.

1. Terms Applicable to Specific Content and Areas of the Site
Some areas of the Site or Content provided on or through the Site may have additional rules, guidelines, license agreements, user agreements or other terms and conditions that apply to your access or use of that area of the Site or Content (including terms and conditions applicable to a corporation or other organization and its users). If there is a conflict or inconsistency between the Terms of Use and the rules, guidelines, license agreement, user agreement or other terms and conditions for a specific area of the Site or for specific Content, the latter shall have precedence with respect to your access and use of that area of the Site or Content.

2. Use of Software
Your use of Software is subject to all agreements such as a license agreement or user agreement that accompanies or is included with the Software, ordering documents, exhibits, and other terms and conditions that apply ("License Terms"). In the event that Software is provided on or through the Site and is not licensed for your use through License Terms specific to the Software, you may use the Software subject to the following: (a) the Software may be used solely for your personal, informational, noncommercial purposes; (b) the Software may not be modified or altered in any way; and (c) the Software may not be redistributed.

3. Use of Materials
You may download, store, display on your computer, view, listen to, play and print Materials that Oracle publishes or broadcasts on the Site or makes available for download through the Site subject to the following: (a) the Materials may be used solely for your personal, informational, noncommercial purposes; (b) the Materials may not be modified or altered in any way; and (c) the Materials may not be redistributed.

4. Use of Community Services
Community Services are provided as a convenience to users and Oracle is not obligated to provide any technical support for, or participate in, Community Services. While Community Services may include information regarding Oracle products and services, including information from Oracle employees, they are not an official customer support channel for Oracle.

You may use Community Services subject to the following: (a) Community Services may be used solely for your personal, informational, noncommercial purposes; (b) Content provided on or through Community Services may not be redistributed; and (c) personal data about other users may not be stored or collected except where expressly authorized by Oracle.

5. Reservation of Rights
The Site and Content provided on or through the Site are the intellectual property and copyrighted works of Oracle or a third party provider. All rights, title and interest not expressly granted with respect to the Site and Content provided on or through the Site are reserved. All Content is provided on an "As Is" and "As Available" basis, and Oracle reserves the right to terminate the permissions granted to you in Sections 2, 3 and 4 above and your use of the Content at any time.

6. Your Content
You agree that you will only upload, share, post, publish, transmit, or otherwise make available ("Share") on or through the Site Content that you have the right and authority to Share and for which you have the right and authority to grant to Oracle all of the licenses and rights set forth herein. By Sharing Content, you grant Oracle a worldwide, perpetual, royalty-free, irrevocable, nonexclusive, fully sublicensable license to use, reproduce, modify, adapt, translate, publish, publicly perform, publicly display, broadcast, transmit and distribute the Content for any purpose and in any form, medium, or technology now known or later developed. This includes, without limitation, the right to incorporate or implement the Content into any Oracle product or service, and to display, market, sublicense and distribute the Content as incorporated or embedded in any product or service distributed or offered by Oracle without compensation to you. You warrant that: (a) you have the right and authority to grant this license; (b) Oracle's exercise of the rights granted pursuant to this license will not infringe or otherwise violate any third party rights; and (c) all so-called moral rights in the Content have been waived to the full extent allowed by law.

You agree that you will not Share any Content that: (a) is binary executable code; (b) is false or misleading; (c) is defamatory, derogatory, degrading or harassing of another or constitutes a personal attack; (d) invades another's privacy or includes another's confidential, sensitive or personal information; (e) promotes bigotry, racism, hatred or harm against any group or individual; (f) is obscene or not in good taste; (g) violates or infringes or promotes the violation or infringement of another's rights, including intellectual property rights; (h) you do not have the right and authority to Share and grant the necessary rights and licenses for; (i) violates or promotes the violation of any applicable laws or regulations; (j) contains a solicitation of funds, goods or services, or promotes or advertises goods or services; or (k) contains any viruses, Trojan horses, or other components designed to limit or harm the functionality of a computer.

Oracle does not want to receive confidential information from you through or in connection with the Site. Notwithstanding anything that you may note or state in connection with Sharing Content, it shall not be considered confidential information and shall be received and treated by Oracle on a non-confidential and unrestricted basis.

7. Security, Passwords and Means of Accessing the Site and Content
You agree not to access or use the Site in any manner that could damage, disable, overburden, or impair any Oracle accounts, computer systems or networks. You agree not to attempt to gain unauthorized access to any parts of the Site or any Oracle accounts, computer systems or networks. You agree not to interfere or attempt to interfere with the proper working of the Site or any Oracle accounts, computer systems or networks. You agree not to use any robot, spider, scraper or other automated means to access the Site or any Oracle accounts, computer systems or networks without Oracle's express written permission.

Access to and use of password protected or secure areas of the Site is restricted to authorized users only. You agree not to share your password(s), account information, or access to the Site. You are responsible for maintaining the confidentiality of password(s) and account information, and you are responsible for all activities that occur under your password(s) or account(s) or as a result of your access to the Site. You agree to notify Oracle immediately of any unauthorized use of your password(s) or account(s).

8. No Unlawful or Prohibited Use
You agree not to use the Site or Content provided on or through the Site for any purpose that is unlawful or prohibited by these Terms of Use, or the rules, guidelines or terms of use posted for a specific area of the Site or Content provided on or through the Site.

9. Indemnity
You agree to indemnify and hold harmless Oracle, its officers, directors, employees and agents from and against any and all claims, liabilities, damages, losses or expenses, including reasonable attorneys' fees and costs, due to or arising out of Content that you Share, your violation of the Terms of Use or any additional rules, guidelines or terms of use posted for a specific area of the Site or Content provided on or through the Site, or your violation or infringement of any third party rights.

10. Monitoring
Oracle has no obligation to monitor the Site or screen Content that is Shared on or through the Site. However, Oracle reserves the right to review the Site and Content and to monitor all use of and activity on the Site, and to remove or choose not to make available on or through the Site any Content at its sole discretion.

11. Termination of Use
Oracle may, in its sole discretion, at any time discontinue providing or limit access to the Site, any areas of the Site or Content provided on or through the Site. You agree that Oracle may, in its sole discretion, at any time, terminate or limit your access to or use of the Site or any Content. Oracle will terminate or limit your access to or use of the Site if, under appropriate circumstances, you are determined to be a repeat infringer of third party copyright rights. You agree that Oracle shall not be liable to you or any third-party for any termination or limitation of your access to or use of the Site or any Content.

12. Third Party Web Sites, Content, Products and Services
The Site provides links to Web sites and access to Content, products and services of third parties, including users, advertisers, affiliates and sponsors of the Site. Oracle is not responsible for third party Content provided on or through the Site and you bear all risks associated with the access and use of such Web sites and third party Content, products and services.

13. Disclaimer
EXCEPT WHERE EXPRESSLY PROVIDED OTHERWISE, THE SITE, AND ALL CONTENT PROVIDED ON OR THROUGH THE SITE, ARE PROVIDED ON AN "AS IS" AND "AS AVAILABLE" BASIS. ORACLE EXPRESSLY DISCLAIMS ALL WARRANTIES OF ANY KIND, WHETHER EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT WITH RESPECT TO THE SITE AND ALL CONTENT PROVIDED ON OR THROUGH THE SITE. ORACLE MAKES NO WARRANTY THAT: (A) THE SITE OR CONTENT WILL MEET YOUR REQUIREMENTS; (B) THE SITE WILL BE AVAILABLE ON AN UNINTERRUPTED, TIMELY, SECURE, OR ERROR-FREE BASIS; (C) THE RESULTS THAT MAY BE OBTAINED FROM THE USE OF THE SITE OR ANY CONTENT PROVIDED ON OR THROUGH THE SITE WILL BE ACCURATE OR RELIABLE; OR (D) THE QUALITY OF ANY CONTENT PURCHASED OR OBTAINED BY YOU ON OR THROUGH THE SITE WILL MEET YOUR EXPECTATIONS.

ANY CONTENT ACCESSED, DOWNLOADED OR OTHERWISE OBTAINED ON OR THROUGH THE USE OF THE SITE IS USED AT YOUR OWN DISCRETION AND RISK. ORACLE SHALL HAVE NO RESPONSIBILITY FOR ANY DAMAGE TO YOUR COMPUTER SYSTEM OR LOSS OF DATA THAT RESULTS FROM THE DOWNLOAD OR USE OF CONTENT.

ORACLE RESERVES THE RIGHT TO MAKE CHANGES OR UPDATES TO, AND MONITOR THE USE OF, THE SITE AND CONTENT PROVIDED ON OR THROUGH THE SITE AT ANY TIME WITHOUT NOTICE.

14. Limitation of Liability
IN NO EVENT SHALL ORACLE BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL OR CONSEQUENTIAL DAMAGES, OR DAMAGES FOR LOSS OF PROFITS, REVENUE, DATA OR USE, INCURRED BY YOU OR ANY THIRD PARTY, WHETHER IN AN ACTION IN CONTRACT OR TORT, ARISING FROM YOUR ACCESS TO, OR USE OF, THE SITE OR ANY CONTENT PROVIDED ON OR THROUGH THE SITE.

15. Exclusions and Limitations
SOME JURISDICTIONS DO NOT ALLOW THE DISCLAIMER OR EXCLUSION OF CERTAIN WARRANTIES OR THE DISCLAIMER, EXCLUSION OR LIMITATION OF CERTAIN LIABILITIES. TO THE EXTENT THAT THEY ARE HELD TO BE LEGALLY INVALID, DISCLAIMERS, EXCLUSIONS AND LIMITATIONS SET FORTH IN THESE TERMS OF USE, INCLUDING THOSE SET FORTH IN SECTIONS 13 AND 14, DO NOT APPLY AND ALL OTHER TERMS SHALL REMAIN IN FULL FORCE AND EFFECT.

16. Privacy Policy
Oracle is concerned about your privacy and has developed a policy to address privacy concerns. For more information, please see Oracle's Privacy Policy. Any personal information collected on this Site may be accessed and stored globally and will be treated in accordance with Oracle's Privacy Policy.

17. Note About Minors
Minors are not eligible to use the Site, and we ask that they do not submit any information to us.

18. Export Restrictions/Legal Compliance
You may not access, download, use or export the Site, or the Content provided on or through the Site, in violation of U.S. export laws or regulations, or in violation of any other applicable laws or regulations. You agree to comply with all export laws and restrictions and regulations of any United States or foreign agency or authority, and not to directly or indirectly provide or otherwise make available the services and products of Oracle in violation of any such restrictions, laws or regulations, or without all necessary approvals, including, without limitation, for the development, design, manufacture or production of nuclear, chemical or biological weapons of mass destruction and of missile technology. As applicable, you shall obtain and bear all expenses relating to any necessary licenses and/or exemptions with respect to your own use of the services of Oracle outside the U.S. Neither the services of Oracle nor the underlying information or technology may be downloaded or otherwise provided or made available, either directly or indirectly, (a) into Cuba, Iran, North Korea, Sudan, Syria or any other country subject to U.S. trade sanctions, to individuals or entities controlled by such countries, or to nationals or residents of such countries other than nationals who are lawfully admitted permanent residents of countries not subject to such sanctions; or (b) to anyone on the U.S. Treasury Department's list of Specially Designated Nationals and Blocked Persons or the U.S. Commerce Department's Table of Denial Orders. By agreeing to these Terms of Use, you agree to the foregoing and represent and warrant that you are not located in, under the control of, or a national or resident of any such country or on any such list.

19. Applicable Laws
All matters relating to your access to, and use of, the Site and Content provided on or through or uploaded to the Site shall be governed by U.S. federal law or the laws of the State of California. Any legal action or proceeding relating to your access to, or use of, the Site or Content shall be instituted in a state or federal court in San Francisco, San Mateo or Santa Clara County, California. You and Oracle agree to submit to the jurisdiction of, and agree that venue is proper in, these courts in any such legal action or proceeding.

20. Copyright/Trademark
Copyright© 1995, 2010, Oracle and/or its affiliates. All rights reserved.

Oracle and Java are registered trademarks of Oracle and/or its affiliates. Other names appearing on the Site may be trademarks of their respective owners.

For information on use of Oracle trademarks, go here.

For information on making claims of copyright infringement, go here.

21. Contact Information
If you have any questions regarding these Terms of Use, please contact Oracle at trademar_us@oracle.com. If you have any other questions, contact information is available at the Contact Oracle page on the Site.
			</div>
			<label class="checkbox">
		      <input type="checkbox"> I accept the terms and conditions
		    </label>
			<button class="btn btn-small" data-dismiss="modal" aria-hidden="true">Cancel</button>
			<button class="btn btn-small btn-primary" type="submit">Accept</button>
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
    <div class="span10">
    	<div class="container-fluid">
     	<div class="row-fluid">
     		<div class="span12">
	     			 <div style="padding-bottom: 9px; border-bottom: 1px solid #ddd;">
						
					 <div class="tab-pane active" id="tab1">
					 <form action="http://localhost:8080/QPEKA/upload" method="post" enctype="multipart/form-data" id="contact-form" class="form-horizontal">
					  <fieldset>
					    <legend>Upload Work</legend>
					    <div class="control-group">
							    <label class="control-label" for="title">Title</label>
							    <div class="controls">
							      <input type="text" name="title" id="title" placeholder="Title">		
							    </div>
							     
							  </div>
							 
							  <div class="control-group">
							    <label class="control-label" for="aName">Author Name</label>
							    <div class="controls">
							      <input type="text" name="aName" id="aName" placeholder="Author Name">
							      <input type="hidden" name="authorId" id="authorId" value="<%=request.getParameter("aid")%>">
							    </div>
							  </div>
							  <div class="control-group">
							    <label class="control-label" for="pName">Pen Name</label>
							    <div class="controls">
							      <input type="text" name="pName" id="pName" placeholder="Pen Name">
							    </div>
							  </div>
							  <div class="control-group">
							    <label class="control-label" for="description">Description</label>
							     <div class="controls">
								  <textarea rows="5" cols="20" name="description" id="description" class="span4" placeholder="Description"></textarea>
								 </div>
							  </div>
							  <div class="control-group">
							    <label class="control-label" for="language">Language</label>
							     <div class="controls">
								    <select id="language" name="language">
								    		<option value="HINDI">HINDI</option>
											<option value="ENGLISH">ENGLISH</option>
										 
								    </select>
								 </div>
							  </div>
							  <div class="control-group">
							    <label class="control-label" for="type">Type of Work</label>
							     <div class="controls">
								    <select id="type" name="type">
										  <option value="BOOK">BOOK</option>
										  <option value="SHORTSTORY">SHORTSTORY</option>
										  <option value="POEM">POEM</option>
										  <option value="ARTICLE">ARTICLE</option>
								    </select>
								 </div>
							  </div>
							  <div class="control-group">
							    <label class="control-label" for="category">Genre</label>
							     <div class="controls">
								    <select id="category" name="category">
										  <option value="COMEDY">COMEDY</option>
										  <option value="FICTION">FICTION</option>
										  <option value="HORROR">HORROR</option>
										 
								    </select>
								 </div>
							  </div>
							  <div class="control-group">
							    <label class="control-label" for="award">Award</label>
							    <div class="controls">
							      <input type="text" id="award" name="award" placeholder="Award">
							    </div>
							  </div>
							  <div class="control-group">
							    <label class="control-label" for="location">Location</label>
							    <div class="controls">
							      <input type="text" id="location" name="location" placeholder="Location">
							    </div>
							  </div>
							  <div class="control-group">
							    <label class="control-label" for="characters">Characters</label>
							     <div class="controls">
								  <textarea rows="5" cols="20" id="characters" name="characters" class="span4" placeholder="Characters"></textarea>
								 </div>
							  </div>
							  <div class="control-group">
							    <label class="control-label" for="ispublished">Published?</label>
							     <div class="controls">
								    <select id="ispublished" name="ispublished">
										  <option value="true">Yes</option>
										  <option value="false">No</option>										 
								    </select>
								 </div>
							  </div>

							  <div class="control-group">
							    <label class="control-label" for="publisherName">Publisher</label>
							    <div class="controls">
							      <input type="text" id="publisherName" name="publisherName" placeholder="Publisher">
							    </div>
							  </div>
							  <div class="control-group">
							    <label class="control-label" for="isbn">ISBN</label>
							    <div class="controls">
							      <input type="text" id="isbn" name="isbn" placeholder="ISBN">
							    </div>
							  </div>
							  <div class="control-group">
							    <label class="control-label" for="dop">Date of Publication</label>
							     <div class="controls">
								     <select id="pyear" name="pyear" class="span2">
								     	  <%for(int i = 0 ; i < 100 ; i++){%>
										  <option value="<%=1912+i%>"><%=1912+i%></option>								
										  <%} %>								 
								    </select >
								    <select id="pmonth" name="pmonth" class="span2">
										  <option value="0">January</option>
										  <option value="1">February</option>
										  <option value="2">March</option>
										  <option value="3">April</option>
										  <option value="4">May</option>
										  <option value="5">June</option>
										  <option value="6">July</option>
										  <option value="7">August</option>
										  <option value="8">September</option>
										  <option value="9">October</option>
										  <option value="10">November</option>
										  <option value="11">December</option>								 
								    </select>
								    <select id="pday" name="pday" class="span1">
								    	  <%for(int i = 0 ; i < 30 ; i++){%>
										  	<option value="<%=i%>"><%=i+1%></option>									 
										  <%} %>						 
								    </select>
								 </div>
							  </div>
							  <div class="control-group">
							    <label class="control-label" for="edition">Edition</label>
							    <div class="controls">
							      <input type="text" id="edition" name="edition" placeholder="Edition">
							    </div>
							  </div>							  
							  
							  <div class="control-group">
							    <label class="control-label" for="file">Upload File</label>
							    <div class="controls">
							      <input type="file" id="file" name="file" placeholder="File to Upload">
							    </div>
							  </div>
	              		<div class="form-actions">
			            	<button type="submit" class="btn btn-primary btn-small">Submit</button>
	        			</div>
					  </fieldset>
					</form>
					    </div>
					   				   
				</div> <!-- /tabbable -->
			</div>
      	</div>
    </div>
    </div>
    <div class="span3">
    </div>
    </div><!--/row-->

      <footer>
        <p>&copy; Qpeka Inc. 2012</p>
      </footer>

    </div><!--/.fluid-container-->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="bootstrap/js/bootstrap-transition.js"></script>
    <script src="bootstrap/js/bootstrap-alert.js"></script>
    <script src="bootstrap/js/bootstrap-modal.js"></script>
    <script src="bootstrap/js/bootstrap-dropdown.js"></script>
    <script src="bootstrap/js/bootstrap-scrollspy.js"></script>
    <script src="bootstrap/js/bootstrap-tab.js"></script>
    <script src="bootstrap/js/bootstrap-button.js"></script>
    <script src="bootstrap/js/bootstrap-collapse.js"></script>
    <script src="bootstrap/js/bootstrap-carousel.js"></script>
    <script src="bootstrap/js/bootstrap-typeahead.js"></script>
    <script src="assets/js/jquery-1.7.1.min.js"></script>
	<script src="assets/js/jquery.validate.min.js"></script>
	<script src="bootstrap/js/uploadWork/script.js"></script>
	<script src="bootstrap/js/jquery-form.js"></script>
  </body>
</html>
