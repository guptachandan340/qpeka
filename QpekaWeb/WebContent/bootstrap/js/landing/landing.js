/**
 * 
 */
var check = false;
$(document).ready(function(){
	
	$('#awsomeform').validate({
	    rules: {
	      firstName: {
	        minlength: 1,
	        required: true
	      },
	      lastName: {
	    	minlength: 1,
	        required: true
	      },
	      email: {
	    	minlength: 1,
	        required: true
	      }
	    },
	    highlight: function(label) {
	    	$(label).closest('.control-group').addClass('error');
	    },
	    success: function(label) {
	    	label
	    		.text('OK!').addClass('valid')
	    		.closest('.control-group').addClass('success');
	    }
	  });
	  
}); // end document.ready

var setVar = function() {
	check = true;
};

var checkUserAvail  = function() {
	
	if(check == true)
	{
		var uname = $('#email').val();
		$.get(
			    "http://landing-qpeka.rhcloud.com/QPEKA/register?rType=authAvail&uname="+uname,
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