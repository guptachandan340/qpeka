/**
 * 
 */

var check = false;

var showModalLogin = function() {

	
	var login = '<div id="login" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">'+
	'<div class="modal-header">'+
		'<button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>'+
		'<h3 id="myModalLabel">Login</h3>'+
	'</div>'+
	'<div class="modal-body">'+
		'<form class="form-signin" action="http://localhost:8080/QPEKA/register" method="get">'+
			'<input id="uid" name="uid" type="text" class="input-block-level" placeholder="Email address">'+
			'<input id="password" name="password" type="password" class="input-block-level" placeholder="Password">'+
			' <input type="hidden" name="rType" id="rType" value="login">'+
			'<label class="checkbox">'+
			'<input type="checkbox" value="remember-me"> Remember me'+
			'</label>'+
			'<button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>'+
		    '<button type="submit" class="btn btn-medium btn-primary" >Login</button>'+
		'</form>'+	
	'</div>'+
 '</div>';

	$(login).modal().appendTo('body');
};


var showModalSignup = function() {

	
	var signup = '<div id="signup" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">'+
		'<div class="modal-header">'+
	'<button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>'+
	'<h3 id="myModalLabel">Sign Up</h3>'+
'</div>'+
'<div class="modal-body">'+
	'<form class="form-signin" action="http://localhost:8080/QPEKA/register" method="post" enctype="multipart/form-data" id="signin-form">'+
	    '<input id="firstName" name="firstName" type="text" class="input-block-level" placeholder="First Name">'+
	    '<input id="lastName" name="lastName" type="text" class="input-block-level" placeholder="Last Name">'+
	    '<select id="gender" name="gender"><option value="MALE">MALE</option><option value="FEMALE">FEMALE</option></select>'+
		'<input onblur="checkUserAvail();" onfocus="setVar();" id="email" name="email" type="text" class="input-block-level" placeholder="Email address">'+
		'<input id="password" name="password" type="password" class="input-block-level" placeholder="Password">'+
		'<input id="confirm" name="confirm" type="password" class="input-block-level" placeholder="Confirm">'+
		'<p id="error" class="text-error"></p><p id="success" class="text-success"></p>'+
		'<button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>'+
		'<button class="btn btn-medium btn-primary" type="submit">Sign Up</button>'+
	'</form>'+
'</div>'+
'</div>';
	
	$(signup).modal().appendTo('body');
};

var authenticate = function() {
	
	var uid = $('#uid').val();
	var pwd = $('#password').val();
	$.get(
		    "http://localhost:8080/QPEKA/register?rType=login&uid="+uid+'&password='+pwd,
		    null,
		    function(data) { 
		    	check = false;
		    	$('#success').text('');
		    	$('#error').text('');
		    	if(data.status == 'authenticated')
		    	{
		    		alert('SUCCESS');
		    		window.location.href = "http://localhost:8080/QpekaWeb/userProfileEdit.jsp?uid="+data.uid;
		    	}
		    	else
		    	{
		    		alert('FAILURE');
		    	}
		    },
		    "json"
		);

}

var setVar = function() {
	check = true;
};

var checkUserAvail  = function() {
	
	if(check == true)
	{
		var uname = $('#email').val();
		$.get(
			    "http://localhost:8080/QPEKA/register?rType=authAvail&uname="+uname,
			    null,
			    function(data) { 
			    	check = false;
			    	$('#success').text('');
			    	$('#error').text('');
			    	if(data.status == 'ok')
			    	{
			    		$('#success').text('Username Available..!');
			    	}
			    	else
			    	{
			    		$('#error').text('Username Un-available..!');
			    	}
			    },
			    "json"
			);
	
	}
}