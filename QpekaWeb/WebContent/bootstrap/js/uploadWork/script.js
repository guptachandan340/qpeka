$(document).ready(function(){

	$('#contact-form').validate({
	    rules: {
	      title: {
	        required: true
	      },
	      aName: {
		        required: true
		  },
		  language: {
		        required: true
		  },
		  category: {
		        required: true
		  }
	    },
	    highlight: function(label) {
	    	$(label).closest('.control-group').addClass('error');
	    },
	    success: function(label) {
	    	label
	    		.addClass('valid')
	    		.closest('.control-group').addClass('success');
	    }
	  });
	  
}); 

$(document).ready(function(){  
		try{
		        $('#contact-form').ajaxForm({
		        beforeSubmit: function() {
		            alert('Submitting...');
		        },
		        success: function(data) {
		        	window.location.href = "http://localhost:8080/QpekaWeb/bookDetail.jsp?id="+data._id;
		        }
		    });
		}
		catch (e) {
			alert(e);
		}
});

//$('#contact-form').submit(function(event) {
//	  alert('entered');
//	  try
//	  {
//	  /* stop form from submitting normally */
//	  event.preventDefault();
//
//	  /* get some values from elements on the page: */
//	   var values = $(this).serialize();
//
//	  /* Send the data using post and put the results in a div */
//	    $.ajax({
//	      url: "test.php",
//	      type: "post",
//	      data: values,
//	      success: function(){
//	          alert("success");
//	           $("#result").html('submitted successfully');
//	      },
//	      error:function(){
//	          alert("failure");
//	          $("#result").html('there is error while submit');
//	      }   
//	    }); 
//	   }
//	   catch(e){alert(e);}
//	});