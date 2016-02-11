
$("#voters-info form").submit(
		function(e) {
			e.preventDefault();
			
			var data = {};
			$(this).serializeArray().each(function(x){data[x.name] = x.value;}); 
			console.log(data);
		}
);

