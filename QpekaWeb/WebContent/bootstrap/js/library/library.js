/**
 * 
 */

var imgUrl = 'http://localhost:8080/QPEKA/image?book=';

var createListItem = function(workId, title, aFName, aMName, aLName) {
	
	var li = '<li class="span3">'+
        '<div class="thumbnail">'+
	    '<img src="'+imgUrl+workId+'" style="height: 120px;width: 120px;" class="img-polaroid">'+
	    '<div class="caption">'+            
	      '<b>'+title+'</b><br>'+
			'<small>'+aFName+' '+aMName+' '+aLName+'</small>'+							
	      '<p><a href="#"><small><b>more</b></small></a></p>'+
	    '</div>'+
	  '</div>'+
	'</li>';
	
	return li;
	
}

var createLibContent =  function(libType, category, section) {
	
	
	$.get(
		    'http://localhost:8080/QPEKA/work?actionType=getLibraryContent&type='+libType+'&category='+category+'&section='+section,
		    null,
		    function(data) { 
		    	
		    	var lists  = '<ul class="thumbnails">';
		    	
		    	for(var i=0; i < data.length; i++)
		    	{
		    		if((i % 4 == 0 && i > 0))
		    		{
		    			lists = lists + '</ul><ul class="thumbnails">';
		    		}
		    		
		    		var authorDetails = JSON.parse(data[i].authorDetails);
		    		lists = lists + createListItem(data[i]._id.$oid, data[i].title, 
		    				authorDetails.name.firstName, authorDetails.name.middleName, authorDetails.name.lastName);
		    	}
		    	
		    	lists = lists + '</ul>';
		    	
		    	$('#libcontent').append(lists);
		    },
		    "json"
		);
	
}