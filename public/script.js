function validate() {
	var formData = {};
	$("#mainForm").serializeArray().map(function(input) {
		if (input.value) {
			formData[input.name] = input.value;
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
			var result = "";
			data.responseJSON[0].errors.forEach(function(element) {
	           	result += element.message + "\n";
	       	});
			
           	alert(result);
       }
     });
}