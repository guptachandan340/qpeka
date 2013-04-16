/**
 * 
 */

var loadToc = function(id) {
	
	$.get(
		    "http://"+host+"/QPEKA/epub?action=getToc&bookId="+id,
		    null,
		    function(data) { 
		    	//alert(data);  
		    	$('#toc').html(data);
		    },
		    "html"
		);
}

var loadContent = function(id,resId) {
	
	$.get(
		    "http://"+host+"/QPEKA/epub?action=getContent&bookId="+id+"&resId="+resId,
		    null,
		    function(data) { 
		    	//alert(data);  
		    	$('#content').html(data);
		    },
		    "html"
		);
}