<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>Points page</title>
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script>


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
				var stay = obj.stay_price;
				var minute = obj.per_minute;
				var hour = obj.per_hour;
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
										+ ' data-state=' + state 
										+ ' data-stay=' + stay
										+ ' data-minute=' + minute
										+ ' data-hour=' + hour
										+ ' data-img='
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
									var stay = $(e.relatedTarget)
											.data('stay');
									var minute = $(e.relatedTarget)
											.data('minute');
									var hour = $(e.relatedTarget)
											.data('hour');
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
									$(e.currentTarget).find(
											'input[name="stay"]').val(
											"Stay price: " + stay);
									$(e.currentTarget).find(
											'input[name="minute"]').val(
											"Per minute price: " + minute);
									$(e.currentTarget).find(
											'input[name="hour"]').val(
											"Per hour price: " + hour);
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
		$("span").remove();
		Markers.init();
		
		var img = $('img[id="image"]'), imgWidth = img.width(), imgHeight = img
				.height();
		$('.zoomout').on('click', function() {
			imgWidth = imgWidth - 20;
			imgHeight = imgHeight - 20;
			$('img[id="image"]').width(imgWidth);
			$('img[id="image"]').height(imgHeight);
			$('span[class="marker"]').each(function(index, item) {
				$(item).css("padding-left", (imgWidth - 450) / 5);
				$(item).css("padding-top", (imgHeight - 450) / 5);
			});
		});

		$('.zoomin').on('click', function() {
			imgWidth = imgWidth + 20;
			imgHeight = imgHeight + 20;
			$('img[id="image"]').width(imgWidth);
			$('img[id="image"]').height(imgHeight);
			$('span[class="marker"]').each(function(index, item) {
				$(item).css("padding-left", (imgWidth - 450) / 5);
				$(item).css("padding-top", (imgHeight - 450) / 5);
			});
		});

	});
	function myFunction() {
		document.getElementById("form").submit(this);
	};

	</script>
	<style>
	<%@include file="/css/user_points.css"%>
	</style>
<c:set var="flag_error" value="${error}"></c:set>	
<c:set var="previous_path" value="/jsp/user/rental_point.jsp" scope="session" />
<c:set var="language" value="${sessionScope.lang}" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="properties.local" var="local" />
<fmt:message bundle="${local}" key="user.points.home" var="home" />
<fmt:message bundle="${local}" key="user.points.account" var="account" />
<fmt:message bundle="${local}" key="user.points.points" var="points" />
<fmt:message bundle="${local}" key="user.points.billing" var="billing" />
<fmt:message bundle="${local}" key="user.points.favorites" var="favorites" />
<fmt:message bundle="${local}" key="user.points.search" var="search" />
<fmt:message bundle="${local}" key="user.points.users" var="users" />
<fmt:message bundle="${local}" key="main.bicycles" var="bicycles" />
<fmt:message bundle="${local}" key="user.points.logout" var="logout" />
<fmt:message bundle="${local}" key="user.points.confirm" var="confirm" />
<fmt:message bundle="${local}" key="user.points.hello" var="hello" />
<fmt:message bundle="${local}" key="user.points.book.start" var="book_start" />
<fmt:message bundle="${local}" key="user.points.actual.start" var="actual_start" />
<fmt:message bundle="${local}" key="user.points.actual.end" var="cancel" />
<fmt:message bundle="${local}" key="user.points.orders" var="orders" />
<fmt:message bundle="${local}" key="user.points.no.orders" var="no_orders" />
<fmt:message bundle="${local}" key="user.points.not.move" var="not_move" />
<fmt:message bundle="${local}" key="user.points.in.trip" var="in_trip" />
<fmt:message bundle="${local}" key="user.points.order" var="make_order" />
<fmt:message bundle="${local}" key="user.points.order.info" var="order_info" />
<fmt:message bundle="${local}" key="user.points.error.active" var="error_active"/>
<fmt:message bundle="${local}" key="user.points.ru" var="ru" />
<fmt:message bundle="${local}" key="user.points.en" var="en" />
</head>
<body>
	 <jsp:include page="/jsp/user/user_navbar.jsp"/> 
	<div class="info text-center">
		<div class="container">
			<div class="col-sm-7">
				<div id="image-wrapper" data-captions='{"coords": ${json}}'>
					<div class="inner" id="inner">
						<img src="${pageContext.session.servletContext.contextPath}/image/map.png" id="image" class="image" />
					</div>
				</div>
				<div class="buttons">
					<button class="zoomout" data-zoom="out">
						<p class="glyphicon glyphicon-minus"></p>
					</button>
					<button class="zoomin" data-zoom="in">
						<p class="glyphicon glyphicon-plus"></p>
					</button>
				</div>
			</div>
			<div class="col-sm-4">
				<div style="color: #a94442">
                   <c:if test="${points_error =='true'}">
                      <p>${error_active}</p>
                   </c:if>
                </div>
				<c:choose>
					<c:when test="${order == null}">
						<h4>${no_orders}</h4>
					</c:when>
					<c:when test="${order != null}">
						<h3>${orders}</h3>
						<c:choose>
							<c:when test='${order.getActualStartTime().equals("")}'>
								<form id="form" method="POST" action="${pageContext.session.servletContext.contextPath}/controller">
									<input type="hidden" name="command" value="Start_move" />
									<div class="form-group">
										<h4 class="text-left">${book_start}:</h4><p class="form-control">${order.getBookedStartTime()}
									</div>
									<h4>${not_move}</h4>
									<input type="submit" value="btn btn-submit " id="road_button">${move}
								</form>
							</c:when>
							<c:when test='${!order.getActualStartTime().equals("")}'>
								<form id="form" method="POST" action="${pageContext.session.servletContext.contextPath}/controller">
									<input type="hidden" name="command" value="End_move" />
									<div class="form-group">
										<h4 class="text-left">${book_start}:</h4>
										<p class="form-control">${order.getBookedStartTime()}</p>
										<h4 class="text-left">${actual_start}: </h4>
										<p class="form-control">${order.getActualStartTime()}</p>
									</div>
				<h4>${in_trip}</h4>
				<button type="submit" class="btn btn-submit" id="road_button">${cancel}
						</button>
				</form>
			</c:when>
			</c:choose>
			</c:when>
		</c:choose>
	</div>
	</div>
	</div>
	
		<div class="modal fade" id="order" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" style="padding: 35px 50px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>${order_info}</h4>
				</div>
				<div class="modal-body" style="padding: 40px 50px;">
					<form name="form" method="POST" action="${pageContext.session.servletContext.contextPath}/controller">
						<input type="hidden" name="command" value="Order" />
						<div class="form-group">
							<img id="modal_image" src="" width=100 height=100>
							<input type="hidden" name="id" value="" />
							<input type="text" name="brand" value="" class="form-control" />
							<input type="text" name="color" value="" class="form-control" />
							<input type="text" name="state" value="" class="form-control" />
							<input type="text" name="stay" value="" class="form-control" />
							<input type="text" name="minute" value="" class="form-control" />
							<input type="text" name="hour" value="" class="form-control" />
						</div>
						<button type="submit" class="btn btn-success ">${make_order}
						</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	</script>
	</body>
</html>