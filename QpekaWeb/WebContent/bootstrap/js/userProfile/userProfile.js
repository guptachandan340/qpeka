/**

 * 
 */

var userProfileData = {};
var keys  = [ 'email', 'userName', 'gender', 'nationality', 'type','desc'];
var address = ['state','city','pincode','addressLine1','addressLine2','addressLine3'];
var languages = ['rlang', 'wlang'];
var names = ['firstName','middleName','lastName'];

var updateAddr = function(id) {

	var reqBody = {"id":id};
	reqBody.address = {};
	reqBody.address.city = $('#city').val();
	reqBody.address.state = $('#state').val();
	reqBody.address.pincode = $('#pincode').val();
	reqBody.address.addressLine1 = $('#addressLine1').val();
	reqBody.address.addressLine2 = $('#addressLine2').val();
	reqBody.address.addressLine3 = $('#addressLine3').val();
	
	alert(JSON.stringify(reqBody));
	
	$.post(
		    "http://"+host+"/QPEKA/profile?rType=update&request="+JSON.stringify(reqBody),
		    null,
		    function(data) { 
		    	alert(JSON.stringify(data));    	
		    },
		    "json"
		);
}

var updateGeneral = function(id) {
	
	var reqBody = {"id":id};
	
	reqBody.name = {};
	
	reqBody.userName = $('#userName').val();
	reqBody.email = $('#email').val();
	reqBody.name.firstName = $('#firstName').val();
	reqBody.name.middleName = $('#middleName').val();
	reqBody.name.lastName = $('#lastName').val();
	reqBody.gender = $('#gender').val();
	reqBody.year = $('#year').val();
	reqBody.month = $('#month').val();
	reqBody.day = $('#day').val();
	reqBody.website = $('#website').val();
	reqBody.desc = $('#desc').val();
	reqBody.nationality = $('#nationality').val();
	reqBody.type = $('#type').val();
	alert(JSON.stringify(reqBody));
	
	$.post(
		    "http://"+host+"/QPEKA/profile?rType=update&request="+JSON.stringify(reqBody),
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
			    "http://"+host+"/QPEKA/profile?rType=update&request="+JSON.stringify(reqBody),
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
		    "http://"+host+"/QPEKA/register?rType=getUser&uid="+uid,
		    null,
		    function(data) { 
		    	try
		    	{
		    		alert(JSON.stringify(data));
		    		userProfileData = data;
		    		populateBasicInfo();
		    		populateAddress();
		    		populateLang();
		    	}
		    	catch (e) {
					alert(e);
				}
		    },
		    "json"
		);
	
}

var populateBasicInfo = function() {
	for(var i = 0 ; i < names.length; i++)
	{
		$('#'+names[i]).val(userProfileData.name[names[i]]);			
	}
	
	for(var i = 0 ; i < keys.length; i++)
	{
		$('#'+keys[i]).val(userProfileData[keys[i]]);			
	}
	
}

var populateAddress = function() {
	for(var i = 0 ; i < address.length; i++)
	{
		$('#'+address[i]).val(userProfileData.address[address[i]]);			
	}
}

var populateLang  = function() {
	var rlangs = userProfileData.rLang;
	var wlangs = userProfileData.wLang;
	for(r in rlangs)
		$('#rlang1').append('&nbsp;<span class="label label-info">'+rlangs[r]+'</span>');
	for(w in wlangs)
		$('#wlang1').append('&nbsp;<span class="label label-info">'+wlangs[w]+'</span>');
}

var addRLang = function(id) {
	
	$.post(
		    "http://"+host+"/QPEKA/profile?rType=addrlang&uid="+id+"&lang="+$('#rlang').val(),
		    null,
		    function(data) { 
		    	alert(JSON.stringify(data));  
		    	$('#rlang1').append('&nbsp;<span class="label label-info">'+$('#rlang').val()+'</span>');
		    },
		    "json"
		);
}

var addWLang = function(id) {
	
	$.post(
			 "http://"+host+"/QPEKA/profile?rType=addwlang&uid="+id+"&lang="+$('#wlang').val(),
		    null,
		    function(data) { 
		    	alert(JSON.stringify(data));    
		    	$('#wlang1').append('&nbsp;<span class="label label-info">'+$('#wlang').val()+'</span>');
		    },
		    "json"
		);
}
