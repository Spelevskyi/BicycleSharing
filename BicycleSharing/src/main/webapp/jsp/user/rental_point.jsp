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

<style>
<%@include file="/css/user_points.css"%>
</style>

<c:set var="previous_path" value="controller?command=User_points" scope="session" />
<c:set var="language" value="${lang}" />
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
<fmt:message bundle="${local}" key="user.points.error.active" var="error_active" />
<fmt:message bundle="${local}" key="user.points.ru" var="ru" />
<fmt:message bundle="${local}" key="user.points.move" var="move" />
<fmt:message bundle="${local}" key="user.points.map" var="map" />
<fmt:message bundle="${local}" key="user.points.title" var="title" />
<fmt:message bundle="${local}" key="user.points.en" var="en" />
<title>${title}</title>
</head>
<body>
	<jsp:include page="/jsp/user/user_navbar.jsp" />
	<div class="info">
		<div class="container">
			<div class="col-sm-7">
				<h3>${map} </h3>
				<div id="image-wrapper" data-captions='{"coords": ${json}}'>
					<div class="inner" id="inner">
						<img src="${pageContext.session.servletContext.contextPath}/image/map.png" id="image" class="image" />
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<c:choose>
					<c:when test="${order == null}">
						<h4>${no_orders}</h4>
					</c:when>
					<c:when test="${order != null}">
						<h3>${orders}</h3>
						<c:choose>
							<c:when test='${order.getActualStartTime().equals("")}'>
							<div class="adding">
								<form id="form" method="POST" action="${pageContext.session.servletContext.contextPath}/controller">
									<input type="hidden" name="command" value="Start_move" />
									<div class="form-group">
										<h4 class="text-left">${book_start}:</h4>
										<p class="form-control">${order.getBookedStartTime()}
										<h4>${not_move}....</h4>
									</div>
									<button type="submit" class="btn btn-submit" id="road-button">${move}</button>
								</form>
							</div>
							</c:when>
							<c:when test='${!order.getActualStartTime().equals("")}'>
							<div class="adding">
								<form id="form" method="POST" action="${pageContext.session.servletContext.contextPath}/controller">
									<input type="hidden" name="command" value="End_move" />
									<div class="form-group">
										<h4 class="text-left">${book_start}:</h4>
										<p class="form-control">${order.getBookedStartTime()}</p>
										<h4 class="text-left">${actual_start}:</h4>
										<p class="form-control">${order.getActualStartTime()}</p>
										<h4>${in_trip}....</h4>
									</div>
									<button type="submit" class="btn btn-submit" id="road-button">${cancel}</button>
								</form>
							</div>
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
						<button type="submit" class="btn btn-success ">${make_order}</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
	<%@include file="/js/user_points.js"%>
	</script>
</body>
</html>