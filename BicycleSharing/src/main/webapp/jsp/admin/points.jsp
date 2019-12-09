<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
<%@include file="/css/admin_points.css"%>
</style>

</head>
<c:set var="previous_path" value="controller?command=Points" scope="session" />
<c:set var="language" value="${lang}" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="properties.local" var="local" />
<fmt:message bundle="${local}" key="admin.points.home" var="home" />
<fmt:message bundle="${local}" key="admin.points.account" var="account" />
<fmt:message bundle="${local}" key="admin.points.points" var="points" />
<fmt:message bundle="${local}" key="admin.points.billing" var="billing" />
<fmt:message bundle="${local}" key="admin.points.favorites" var="favorites" />
<fmt:message bundle="${local}" key="admin.points.search" var="search" />
<fmt:message bundle="${local}" key="admin.points.users" var="users" />
<fmt:message bundle="${local}" key="admin.points.bicycles" var="bicycles" />
<fmt:message bundle="${local}" key="admin.points.logout" var="logout" />
<fmt:message bundle="${local}" key="admin.points.confirm" var="confirm" />
<fmt:message bundle="${local}" key="admin.points.hello" var="hello" />
<fmt:message bundle="${local}" key="admin.points.zoom.in" var="zoom_in" />
<fmt:message bundle="${local}" key="admin.points.zoom.out" var="zoom_out" />
<fmt:message bundle="${local}" key="admin.points.create" var="create" />
<fmt:message bundle="${local}" key="admin.points.enter.x" var="enter_x" />
<fmt:message bundle="${local}" key="admin.points.enter.y" var="enter_y" />
<fmt:message bundle="${local}" key="admin.points.x.coordinate" var="x_coordinate" />
<fmt:message bundle="${local}" key="admin.points.y.coordinate" var="y_coordinate" />
<fmt:message bundle="${local}" key="admin.points.x.error" var="x_error" />
<fmt:message bundle="${local}" key="admin.points.y.error" var="y_error" />
<fmt:message bundle="${local}" key="admin.points.add.form" var="add_form" />
<fmt:message bundle="${local}" key="admin.points.error.info" var="error_info" />
<fmt:message bundle="${local}" key="admin.points.title" var="title" />
<fmt:message bundle="${local}" key="admin.points.map" var="map" />
<fmt:message bundle="${local}" key="admin.points.choose.bicycle" var="choose_bicycle" />
<fmt:message bundle="${local}" key="admin.points.ru" var="ru" />
<fmt:message bundle="${local}" key="admin.points.en" var="en" />
<title>${title}</title>
<body>
	<jsp:include page="/jsp/admin/admin_navbar.jsp" />
	<div class="info">
		<div class="container">
			<div class="row">
				<div class="col-sm-7">
					<h3>${map}</h3>
					<div id="image-wrapper" data-captions='{"coords": ${json}}'>
						<div class="inner" id="inner">
							<img src="${pageContext.session.servletContext.contextPath}/image/map.png" id="image" class="image" />
						</div>
					</div>
				</div>
				<div class="col-sm-4">
					<h3>${add_form}</h3>
					<div class="adding">
						<form id="form" method="POST" action="${pageContext.session.servletContext.contextPath}/controller">
							<input type="hidden" name="command" value="Add_bicycle_with_location" />
							<div class="form-group">
								<label for="Card master">${choose_bicycle}  </label>  <select name="bicycleId" id="select" required>
									<c:forEach var="num" items='${not_located.entrySet()}'>
										<c:choose>
											<c:when test='${num.getValue().getBicycleId() == 0}'>
												<option value='${num.getKey().getId()}'>${num.getKey().getId()} ${num.getKey().getBrand()} ${num.getKey().getColor()}</option>
											</c:when>
										</c:choose>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<h4 class="text-left">${x_coordinate}:</h4>
								<input type="text" name="X" id="xCoordinate" value="" class="form-control" placeholder="${enter_x}" pattern="^[0-9]{1,3}$" required /> 
								<span class="form_error">${x_error}</span>
							</div>
							<div class="form-group">
								<h4 class="text-left">${y_coordinate}:</h4>
								<input type="text" name="Y" id="yCoordinate" value="" class="form-control" placeholder="${enter_y}" pattern="^[0-9]{1,3}$" required />
								<span class="form_error">${y_error}</span>
							</div>
							<button type="submit" class="btn btn-submit" id="road-button">${create}</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	<%@include file="/js/admin_points.js"%>
	</script>
</body>
</html>