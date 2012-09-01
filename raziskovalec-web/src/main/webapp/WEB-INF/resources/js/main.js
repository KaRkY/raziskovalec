(function($) {
	
	$(document).ready(function(){
		$("#researchers tr").click(function (event){
			window.location.href=$(this).attr("data-url");
		});
	});
	
}(jQuery));
