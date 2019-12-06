
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
				var brand = obj.brand;
				var imagePath = obj.imagePath;
				var color = obj.color;
				var state = obj.state;
				$('<span class="marker" id="marker"/>')
						.css({
							top : x,
							left : y,
							id : id
						})
						.html(
								'<span class="caption">'
										+ '<a href="#order" data-toggle="modal" data-book-id="order_id" data-id='
										+ id + ' data-brand=' + brand
										+ ' data-color=' + color
										+ ' data-state=' + state + ' data-img='
										+ imagePath + '>Click me</a>'
										+ '</span>').appendTo(target);

				$('#order')
						.on(
								'show.bs.modal',
								function(e) {
									var id = $(e.relatedTarget).data('id');
									var brand = $(e.relatedTarget)
											.data('brand');
									var color = $(e.relatedTarget)
											.data('color');
									var state = $(e.relatedTarget)
											.data('state');
									var imagePath = $(e.relatedTarget).data(
											'img');
									$(e.currentTarget).find('input[name="id"]')
											.val(id);
									$(e.currentTarget).find(
											'input[name="brand"]').val(
											"Brand type: " + brand);
									$(e.currentTarget).find(
											'input[name="state"]').val(
											"State: " + state);
									$(e.currentTarget).find(
											'input[name="color"]').val(
											"Color: " + color);
									document.getElementById("modal_image").src = imagePath;
								});

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
	Markers.init();

});
function myFunction() {
	document.getElementById("form").submit(this);
}