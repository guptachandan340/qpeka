/**
 * 
 */

//<dl class="dl-horizontal">
//							  <dt>ISBN</dt>
//							  <dd>0765331268 (ISBN13: 9780765331267)</dd>
//							  <dt>Edition language</dt>
//							  <dd>English</dd>
//							  <dt>Date  of publication</dt>
//							  <dd>July 5th 2011</dd>
//							  <dt>Publisher</dt>
//							  <dd>T.M.H</dd>
//							</dl>
							
//{"_id":{"$oid":"512cd10aebf77df4aa129275"},"title":"Marks work1","authorId":{"$oid":"512cd10aebf77df4aa129274"},"coverPageFile":"","edition":0,"category":"ART","type":"BOOK","numPages":100,"language":"English","ispublished":false,"description":"AWSOME BOOK0","metaData":"{\"searchKey\":\"art,history\"}"}
//{"_id":{"$oid":"512cd10aebf77df4aa129275"},"title":"Marks work1","authorId":{"$oid":"512cd10aebf77df4aa129274"},"coverPageFile":"","edition":0,"category":"ART","type":"BOOK","numPages":100,"language":"English","ispublished":false,"description":"AWSOME BOOK0","metaData":"{\"searchKey\":\"art,history\"}","author":{"_id":"512cd10aebf77df4aa129274","name":{"firstName":"Mark0","middleName":"Donald0","lastName":"Shane0"},"gender":"MALE","dob":1361891594964,"nationality":"INDIAN","imageFile":"","shortBio":"He is an awsome writer0","infoLink":"http://google.com/mark","genre":["ART"],"type":"LEVEL3"}}
var getBook = function(id) {
	
	$.get(
		    "http://localhost:8080/QPEKA/work?actionType=getbook&id="+id,
		    null,
		    function(data) { 
		    	$('#title').append(data.title);
		    	$('#author').append('by '+data.author.name.firstName + ' ' + data.author.name.middleName + ' ' + data.author.name.lastName);
		    	$('#desc').append(data.description);
		    	var infoString = '<dl class="dl-horizontal">';
		    	var isbn = '';
		    	if(data.ispublished == true)
		    		isbn = data.isbn;
		    	else
		    		isbn = 'NA';
		    	
		    	infoString += '<dt>ISBN</dt><dd>'+isbn+'</dd>';		 
		    	infoString += '<dt>Edition language</dt><dd>'+data.language+'</dd>';
		    	
		    	var dop = '';
		    	if(data.ispublished == true)
		    		dop = 'something';
		    	else
		    		dop = 'NA';
		    	
		    	infoString += '<dt>Date  of publication</dt><dd>'+dop+'</dd>';
		    	
		    	var pub = '';
		    	if(data.ispublished == true)
		    		pub = 'something';
		    	else
		    		pub = 'NA';
		    	
		    	infoString += '<dt>Publisher</dt><dd>'+pub+'</dd>';
		    	infoString += '</dl>';
		    	
		    	$('#details').append(infoString);
		    	alert(JSON.stringify(data));
		    },
		    "json"
		);
}