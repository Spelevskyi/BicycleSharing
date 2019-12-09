<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="welcometag" uri="customtags"%>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
<%@include file="/css/message.css"%>
</style>

<c:set var="language" value="${lang}" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="properties.local" var="local" />
<fmt:message bundle="${local}" key="main.home" var="home" />
<fmt:message bundle="${local}" key="main.account" var="account" />
<fmt:message bundle="${local}" key="main.points" var="points" />
<fmt:message bundle="${local}" key="main.billing" var="billing" />
<fmt:message bundle="${local}" key="main.favorites" var="favorites" />
<fmt:message bundle="${local}" key="main.search" var="search" />
<fmt:message bundle="${local}" key="main.logout" var="logout" />
<fmt:message bundle="${local}" key="main.confirm" var="confirm" />
<fmt:message bundle="${local}" key="main.hello" var="hello" />
<fmt:message bundle="${local}" key="main.ru" var="ru" />
<fmt:message bundle="${local}" key="main.en" var="en" />
<fmt:message bundle="${local}" key="main.book.start" var="book_start" />
<fmt:message bundle="${local}" key="main.actual.start" var="actual_start" />
<fmt:message bundle="${local}" key="main.actual.end" var="actual_end" />
<fmt:message bundle="${local}" key="main.cancel" var="cancel" />
<fmt:message bundle="${local}" key="main.orders" var="orders" />
<fmt:message bundle="${local}" key="main.no.orders" var="no_orders" />
<fmt:message bundle="${local}" key="main.not.move" var="not_move" />
<fmt:message bundle="${local}" key="main.in.trip" var="in_trip" />
<fmt:message bundle="${local}" key="main.history" var="history" />
<fmt:message bundle="${local}" key="main.direction" var="direction" />
<fmt:message bundle="${local}" key="main.distance" var="distance" />
<fmt:message bundle="${local}" key="main.error.info" var="error_info" />
</head>
<body>
	<c:choose>
		<c:when test="${role == 'ADMIN'}">
			<jsp:include page="/jsp/admin/admin_navbar.jsp" />
		</c:when>
		<c:when test="${role == 'USER'}">
			<jsp:include page="/jsp/user/user_navbar.jsp" />
		</c:when>
		<c:when test="${role == 'GUEST'}">
			<jsp:include page="/jsp/confirm_navbar.jsp" />
		</c:when>
	</c:choose>
	<div class="modal fade" id="pointError" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" style="padding: 35px 50px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>
						<span class="glyphicon glyphicon-alert"></span> ${error_info}</h4>
				</div>
				<div class="modal-body" style="padding: 40px 50px;">
					<h4>${error}</h4>
				</div>
			</div>
		</div>
	</div>
	<script>
	<%@include file="/js/message.js"%>
	</script>
</body>
</html>