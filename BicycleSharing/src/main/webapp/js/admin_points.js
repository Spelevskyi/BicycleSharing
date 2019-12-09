var Markers = {
	fn : {
		addMarkers : function() {
			var target = $('#image-wrapper');
			var data = target.attr('data-captions');
			var captions = $.parseJSON(data);
			var coords = captions.coords;
			for (var i = 0; i < coords.length; i++) {
				var obj = coords[i];
				var x = obj.x_coordinate;
				var y = obj.y_coordinate;
				var id = obj.id;
				$('<span class="marker" id="marker"/>').css({
					top : y,
					left : x,
					id : id
				}).html('<span class="caption"></span>').appendTo(target);
			}
		},
		showCaptions : function() {
			$('span.marker').live('click', function() {
				var $marker = $(this), $caption = $('span.caption', $marker);
				if ($caption.is(':hidden')) {
					$caption.slideDown(300);

				} else {
					$caption.slideUp(300);
				}
			});
		}
	},
	init : function() {
		this.fn.addMarkers();
		this.fn.showCaptions();
	}
};

$(function() {
	$("span").remove();
	Markers.init();
});
function myFunction() {
	document.getElementById("form").submit(this);
};
$(document).ready(function() {
	$("#image").on("click", function(event) {
		var x = event.pageX - this.offsetLeft - 143;
		var y = event.pageY - this.offsetTop - 150;
		document.getElementById('xCoordinate').value = x;
		document.getElementById('yCoordinate').value = y;
	});
});