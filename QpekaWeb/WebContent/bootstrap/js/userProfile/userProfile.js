/**
 * 
 */
var updateAddr = function() {

	var reqBody = {"id":"5119008472d0d2bbd6526d61"};
	reqBody.address = {};
	reqBody.address.city = $('#city').val();
	reqBody.address.state = $('#state').val();
	reqBody.address.pincode = $('#pincode').val();
	reqBody.address.addressLine1 = $('#addressLine1').val();
	reqBody.address.addressLine2 = $('#addressLine2').val();
	reqBody.address.addressLine3 = $('#addressLine3').val();
	
	alert(JSON.stringify(reqBody));
	
	$.post(
		    "http://localhost:8080/QPEKA/profile?rType=update&request="+JSON.stringify(reqBody),
		    null,
		    function(data) { 
		    	alert(JSON.stringify(data));    	
		    },
		    "json"
		);
}

var addPref = function() {
	var genre = $('#genre').val();
	var present = false;
	var prefs = [];
	$('#prefs').children('span').each(function () {
		prefs.push($(this).text());
		if($(this).text()  == genre)
	    {
	    	present = true;
	    }
	});
	
	if(!present)
	{
		prefs.push(genre);
		var reqBody = {"id":"5119008472d0d2bbd6526d61", "interests":prefs};
		$.post(
			    "http://localhost:8080/QPEKA/profile?rType=update&request="+JSON.stringify(reqBody),
			    null,
			    function(data) { 
			    	alert(data);
			    	$('#prefs').append('&nbsp;<span class="label label-info">'+genre+'</span>&nbsp;');
			    },
			    "json"
			);
		
	}
		
}

var loadUser = function(uid) {
	
	$.get(
		    "http://localhost:8080/QPEKA/register?rType=getUser&uid="+uid,
		    null,
		    function(data) { 
		    	try
		    	{
		    		var keys  = ['firstName','middleName','lastName'];
			    	var address = ['state','city','pincode','addressLine1','addressLine2','addressLine3'];
			    	var general = ['nationality', 'email', 'desc', 'gender'];
			    	
			    	for(var i = 0 ; i < keys.length; i++)
			    	{
			    		$('#'+keys[i]).val(data.name[keys[i]]);			
			    	}
			    	
			    	for(var i = 0 ; i < address.length; i++)
			    	{
			    		$('#'+address[i]).val(data.address[address[i]]);
			    	}
			    	
			    	for(var i = 0 ; i < general.length; i++)
			    	{
			    		$('#'+general[i]).val(data[general[i]]);
			    	}
		    	}
		    	catch (e) {
					alert(e);
				}
		    },
		    "json"
		);
	
}