
var request;

$("#voters-info form").submit(
		function(e) {
			e.preventDefault();
			
			var $form = $(this);
			
			// Abort any pending request
		    if (request) {
		        request.abort();
		    }
		    
		    // Let's select and cache all the fields
		    var $inputs = $form.find("input, select, button, textarea");

			// Serialize the data in the form
			var data = {};
			$form.serializeArray().map(function(x){data[x.name] = x.value;}); 
			
		    // Let's disable the inputs for the duration of the Ajax request.
		    $inputs.prop("disabled", true);

		    // Fire off the request
		    console.log("Request", data);
		    request = $.ajax({
		        url: "/user",
		        type: "post",
		        contentType: "application/json",
		        dataType: "json",
		        data: JSON.stringify(data)
		    });

		    // Callback handler that will be called on success
		    request.done(function (response, textStatus, jqXHR){
		        console.log("Response");
		        console.log(response, textStatus)
		    });

		    // Callback handler that will be called on failure
		    request.fail(function (jqXHR, textStatus, errorThrown){
		    	if (jqXHR.status == 404) {
		    		// no en base de datos
		    		alert("No esta en la base de datos");
		    	} else {
		    		// otro error desconocido
		    		console.error(textStatus, errorThrown);
		    	}
		    	console.log("Response");
		        console.error(jqXHR, textStatus, errorThrown);
		    });

		    // if the request failed or succeeded
		    request.always(function () {
		        // Reenable the inputs
		        $inputs.prop("disabled", false);
		    });

		}
);

