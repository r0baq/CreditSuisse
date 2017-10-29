function validate() {
	var formData = {};
	$("#mainForm").serializeArray().map(function(x) {
		if (x.value) {
			formData[x.name] = x.value;
		}
	});
	
	$.ajax({
       type: "POST",
       url: "http://localhost:8080/validate",
       contentType: "application/json",
       data: "[" + JSON.stringify(formData) + "]",
       success: function(data) {
           	alert("Data is valid");
       },
       error: function(data) {
			console.log(data.responseJSON);//RMR
			var result = "";
			data.responseJSON[0].errors.forEach(function(element) {
	           	result += element.message;
	       });
			
           	alert(result);
       }
     });
}