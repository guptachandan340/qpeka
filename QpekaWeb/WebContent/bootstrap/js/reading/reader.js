/**
 * 
 */

var loadToc = function(id) {
	
	$.get(
		    "http://localhost:8080/QPEKA/epub?action=getToc&bookId="+id,
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
		    "http://localhost:8080/QPEKA/epub?action=getContent&bookId="+id+"&resId="+resId,
		    null,
		    function(data) { 
		    	//alert(data);  
		    	$('#content').html(data);
		    },
		    "html"
		);
}