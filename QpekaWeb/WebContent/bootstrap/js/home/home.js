/**
 * 
 */

var workOfDayImg = '<img src="assets/img/testimg4.jpg" style="height: 200px;width: 120px;"  class="img-polaroid"><br><br>';
var imgUrl = 'http://localhost:8080/QPEKA/image?book=';

var getWorkOfDayHtml = function(bookId, fname, mname, lname, desc, title) {

	var html = '<div class="row-fluid">'+
		'<div class="row-fluid">'+			          		
		'<div class="span3">'+
			'<img src="'+imgUrl+bookId+'" style="height: 200px;width: 120px;"  class="img-polaroid"><br><br>'+
		'</div>'+
		'<div class="span9">'+
			 '<blockquote>'+
				 '<h4>'+title+'</h4>'+
				 '<small>'+fname+' '+mname+' '+lname+'</small>'+
			 '</blockquote>'+
			 '<small class="text-info">Rating: 3.92/5 &nbsp;&nbsp; 3200 Ratings &nbsp;&nbsp; 890 Reviews</small><br><br>'+
			 '<small>'+desc+'</small>'+					 
		'</div>'+
	'</div>'+
	'</div>';
	
	return html;
}

var fetchWorkOfTheDay = function(type, div) {
	
	$.get(
		    "http://localhost:8080/QPEKA/work?actionType=workOfDay&type="+type,
		    null,
		    function(data) { 
		    	var authorDetails = JSON.parse(data.authorDetails);
		    	var html = getWorkOfDayHtml(data._id.$oid, authorDetails.name.firstName, authorDetails.name.middleName, authorDetails.name.lastName, 
		    			data.description, data.title);
		    	
		    	$('#'+div).append(html);
		    },
		    "json"
		);
	
};

var fetchWorksOfTheDay = function() {
	
	fetchWorkOfTheDay('BOOK', 'bod');
	fetchWorkOfTheDay('SHORTSTORY', 'ssod');
	fetchWorkOfTheDay('POEM', 'pod');
}

var createCarousalItemElement = function(work) {
	
	var element = '<div class="span3">'+
		'<img src="'+imgUrl+work+'" style="height: 150px;width: 100px;" class="img-polaroid">'+					      
	'</div>';
	
	return element;
}

var createCarousal = function(workarray, tabid, carousalId, string, active) {
	
	var html = '<div class="tab-pane '+active+'" id="'+tabid+'">'+
   					'<div id="'+carousalId+'" class="carousel slide">'+
   					'<div class="carousel-inner">';
	
	for(var i = 0 ; i < workarray.length; i++ )
	{
		if(i % 4 == 0)
		{
			if(i > 0)
				html = html + '</div></div>';
			if(i == 0)
				html = html + '<div class="item active"><div class="row-fluid">';
			else
				html = html + '<div class="item"><div class="row-fluid">';
		}
		html = html + createCarousalItemElement(workarray[i]._id.$oid);
		
		if(i == workarray.length-1 && i % 4 != 0)
			html = html + '</div></div>';
		
	}
	html = html + '</div>';
	
	html = html + '<div class="container">'+
	'<div class="span6 carousel-caption">'+				     			          
		'<p class="pull-right">'+string+'...</p>'+		             
	'</div>'+
	'</div>'+
	'<a class="left carousel-control" href="#'+carousalId+'" data-slide="prev">&lsaquo;</a>'+
	'<a class="right carousel-control" href="#'+carousalId+'" data-slide="next">&rsaquo;</a>'+
	'</div></div>';
	
	return html;
}

var fetchPopularHtml = function() {

	$.get(
		    "http://localhost:8080/QPEKA/work?actionType=recommendedWork&type=BOOK",
		    null,
		    function(data) { 	    	
		    	var html = createCarousal(data, 'tab1', 'popularbooks', 'Popular Books', 'active');
		 
		    	$('#popularcontent').append(html);
		    },
		    "json"
		);
	
	$.get(
		    "http://localhost:8080/QPEKA/work?actionType=recommendedWork&type=SHORTSTORY",
		    null,
		    function(data) { 	    	
		    	var html = createCarousal(data, 'tab2', 'popularss', 'Popular ShortStories', '');
		    	
		    	$('#popularcontent').append(html);
		    },
		    "json"
		);
	
	$.get(
		    "http://localhost:8080/QPEKA/work?actionType=recommendedWork&type=POEM",
		    null,
		    function(data) { 	    	
		    	var html = createCarousal(data, 'tab3', 'popularpoems', 'Popular Poems', '');
		    	
		    	$('#popularcontent').append(html);
		    },
		    "json"
		);
	
	$.get(
		    "http://localhost:8080/QPEKA/work?actionType=recommendedWork&type=ARTICLE",
		    null,
		    function(data) { 	    	
		    	var html = createCarousal(data, 'tab4', 'populararticles', 'Popular Articles', '' );
		    	
		    	$('#popularcontent').append(html);
		    },
		    "json"
		);
}

var init = function() {
	
	fetchWorksOfTheDay();
	fetchPopularHtml();
}