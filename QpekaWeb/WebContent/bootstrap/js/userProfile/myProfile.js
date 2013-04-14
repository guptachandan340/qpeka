/**
 * 
 */
var generateFriendDiv = function(data) {
	
	var friends = '';
	
	for(var i = 0 ; i < data.length; i++ )
	{
		var uName = JSON.parse(data[i].name);
		var div = '<div class="span6"><div class="media">'+
	      '<a class="pull-left" href="#">'+
	      	'<img src="http://"+host+"/QPEKA/image?action=userimg&uid='+data[i].id+'" style="height: 60px;width: 50px;" class="img-polaroid media-object">'+            
	      '</a>'+
	      '<div class="media-body">'+
	        '<b>'+uName['firstName'] + ' ' + uName['middleName'] + ' ' + uName['lastName'] + '</b><p class="pull-right"> </p><br>'+
	        '<small>(Reader)</small>'+
	      '</div>'+
	    '</div></div>';
		
		if(i == 0)
			friends = friends + '<div class="row-fluid">' + div;
		else if(i % 2 == 0 && i != 0 && i < data.length-1)
			friends = friends + '</div><div class="row-fluid">' + div;
		else if(i == data.length-1)
			friends = friends + '</div>';
		else
			friends = friends + div;
	}
	return friends;
}

var loadFriends = function(uid) {
	
	$.get(
		    "http://"+host+"/QPEKA/association?action=getFriends&uid="+uid,
		    null,
		    function(data) { 
		    	$('#friends').append(generateFriendDiv(data));
		    },
		    "json"
		);
}

var loadFans = function(uid) {
	
	$.get(
		    "http://"+host+"/QPEKA/association?action=getFans&uid="+uid,
		    null,
		    function(data) { 
		    	$('#fans').append(generateFriendDiv(data));
		    },
		    "json"
		);
}

var init = function(uid) {
	alert(uid);
	loadFriends(uid);
	loadFans(uid);
}
