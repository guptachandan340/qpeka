$(document).ready(function(){
	
	//{"_id":{"$oid":"514870871aa87b91a2f384bc"},"name":{"firstName":"Manoj","middleName":"","lastName":"Thakur"},"gender":"MALE","dob":1363701895932,"nationality":"","imageFile":"","shortBio":"","infoLink":"","genre":[],"type":"LEVEL3"}
	$.get(
		    "http://"+host+"/QPEKA/register?rType=getAuthor&aid="+aid,
		    null,
		    function(data) { 
		    	//alert(JSON.stringify(data));
		    	$('#aName').value(data.name.firstName + ' ' + data.name.middleName + ' ' + data.name.lastName);
		    	$("#aName").prop('disabled', true);
		    },
		    "json"
		);
	
	$('#ispublished').change(function(){
		var temp = ($('#ispublished').val() === 'true')?true:false;
		
		$("#isbn").prop('disabled', !temp);
		$("#edition").prop('disabled', !temp);
		$("#publisherName").prop('disabled', !temp);
		$("#pday").prop('disabled', !temp);
		$("#pmonth").prop('disabled', !temp);
		$("#pyear").prop('disabled', !temp);
		
	});
	
	$('#work').validate({
	    rules: {
	      title: {
	        minlength: 5,
	        required: true
	      },
	      aName: {
	        required: true
	      },
	      file: {
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

var blurFields = function() {
	var pub = $('#ispublished').val();
	alert('ENTERED = ' + pub);
	if(pub == 'false'){
		$("pub").prop('disabled', true);
		$("isbn").prop('disabled', true);
		$("pday").prop('disabled', true);
		$("pmonth").prop('disabled', true);
		$("pyear").prop('disabled', true);
		$("edition").prop('disabled', true);
	}
	else if(pub == 'true'){
		$("pub").prop('disabled', false);
		$("isbn").prop('disabled', false);
		$("pday").prop('disabled', false);
		$("pmonth").prop('disabled', false);
		$("pyear").prop('disabled', false);
		$("edition").prop('disabled', false);
	}
	
};
 
var upload = function() {
	var title = $('#title').val();
	var aname = $('#aName').val();
	var pname = $('#pName').val();
	var desc = $('#description').val();
	var lang = $('#language').val();
	var type = $('#type').val();
	var genre = $('#category').val();
	var award = $('#award').val();
	var loc = $('#location').val();
	var publishd = $('#ispublished').val();
	var pub = $('#publisherName').val();
	var pyear = $('#pyear').val();
	var pmonth = $('#pmonth').val();
	var pdate = $('#pday').val();
	var edition = $('#edition').val();
	var file = $('#file').val();
	var isbn = $('#isbn').val();
	
	var query = '?title='+title+'&aname='+aname+'&pname='+pname+'&description='+desc+'&language='+lang+'&type='+type+'&category='+genre+'&award='+award+'&loc='+loc+'&ispublished='+publishd+'&publisherName='+pub+'&pyear='+pyear+'&pmonth='+pmonth+'&pdate='+pdate+'&edition='+edition+'&file='+file+'&isbn='+isbn;
	alert(query);
	$.post(
		    "http://"+host+"/QPEKA/upload"+query,
		    null,
		    function(data) { 
		    	//alert(JSON.stringify(data));
		    	window.location.replace("http://"+host+"/QpekaWeb/bookDetail.jsp?id="+data._id);
		    },
		    "json"
		);
};