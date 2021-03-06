<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
<%@include file="/css/bicycles.css"%>
</style>

<c:set var="previous_path" value="controller?command=Bicycles" scope="session" />
<c:set var="language" value="${sessionScope.lang}" />
<fmt:setLocale value="${language}" />

<fmt:setBundle basename="properties.local" var="local" />
<fmt:message bundle="${local}" key="bicycles.brand" var="brand" />
<fmt:message bundle="${local}" key="bicycles.enter.brand" var="enter_brand" />
<fmt:message bundle="${local}" key="bicycles.color" var="color" />
<fmt:message bundle="${local}" key="bicycles.speed" var="speed" />
<fmt:message bundle="${local}" key="bicycles.enter.color" var="enter_color" />
<fmt:message bundle="${local}" key="bicycles.state" var="state" />
<fmt:message bundle="${local}" key="bicycles.enter.state" var="enter_state" />
<fmt:message bundle="${local}" key="bicycles.enter.speed" var="enter_speed" />
<fmt:message bundle="${local}" key="bicycles.path" var="path" />
<fmt:message bundle="${local}" key="bicycles.enter.path" var="enter_path" />
<fmt:message bundle="${local}" key="bicycles.bicycle.table" var="bicycle_table" />
<fmt:message bundle="${local}" key="bicycles.not.located" var="not_located" />
<fmt:message bundle="${local}" key="bicycles.add.bicycle" var="add_bicycle" />
<fmt:message bundle="${local}" key="bicycles.create" var="create" />
<fmt:message bundle="${local}" key="bicycles.location" var="location" />
<fmt:message bundle="${local}" key="bicycles.creation.date" var="creation_date" />
<fmt:message bundle="${local}" key="bicycles.status" var="status" />
<fmt:message bundle="${local}" key="bicycles.enable" var="enable" />
<fmt:message bundle="${local}" key="bicycles.choose.brand" var="choose_brand" />
<fmt:message bundle="${local}" key="bicycles.choose.color" var="choose_color" />
<fmt:message bundle="${local}" key="bicycles.choose.state" var="choose_state" />
<fmt:message bundle="${local}" key="bicycles.brand.error" var="brand_error" />
<fmt:message bundle="${local}" key="bicycles.color.error" var="color_error" />
<fmt:message bundle="${local}" key="bicycles.choose.state" var="choose_state" />
<fmt:message bundle="${local}" key="bicycles.image.error" var="image_error" />
<fmt:message bundle="${local}" key="bicycles.speed.error" var="speed_error" />
<fmt:message bundle="${local}" key="bicycles.error.info" var="error_info" />
<fmt:message bundle="${local}" key="bicycles.title" var="title" />
<fmt:message bundle="${local}" key="bicycles.change.bicycle" var="change_info" />
<fmt:message bundle="${local}" key="bicycles.bicycle.info" var="bicycle_info" />
<fmt:message bundle="${local}" key="bicycles.update" var="update" />
<fmt:message bundle="${local}" key="bicycles.x" var="x" />
<fmt:message bundle="${local}" key="bicycles.y" var="y" />
<fmt:message bundle="${local}" key="bicycles.ru" var="ru" />
<fmt:message bundle="${local}" key="bicycles.en" var="en" />
<title>${title}</title>
</head>
<body>
	<jsp:include page="/jsp/admin/admin_navbar.jsp" />
	<div class="info ">
		<div class="container">
			<div class="row">
				<div class="col-sm-8">
					<h3>${bicycle_table}</h3>
					<div id="table-wrapper">
						<div class="table-inner">
							<div class="tab">
								<form name="form" method="POST" action="${pageContext.session.servletContext.contextPath}/controller">
									<div class="col-sm-1">
										<input type="hidden" name="command" value="Delete_bicycle">
										<button type="submit" class="btn">
											<i class="glyphicon glyphicon-trash"></i>
										</button>
									</div>
									<table class="table table-hover">
										<tr>
											<th><input type="checkbox" id="checkBoxAll" /></th>
											<th>${image}</th>
											<th>${brand}</th>
											<th>${color}</th>
											<th>${speed}</th>
											<th>${creation_date}</th>
											<th>${state}</th>
											<th>${status}</th>
											<th>${location}</th>
											<th></th>
										</tr>
										<c:forEach var="bicycle" items="${bicycles.entrySet()}">
											<tr>
												<td>
												<input type="checkbox" class="chkCheckBoxId" value='${bicycle.getKey().getId()}' name="id" />
												<td>
													<div id="bicycle-wrapper">
														<p>
															<img src="${bicycle.getKey().getImagePath()}">
														</p>
													</div>
												</td>
												<td><a href="#change_bicycle" data-toggle="modal" data-id='${bicycle.getKey().getId()}'>${bicycle.getKey().getBrand()}</a></td></td>
												<td>${bicycle.getKey().getColor()}</td>
												<td>${bicycle.getKey().getSpeed()}</td>
												<td>${bicycle.getKey().getDate()}</td>
												<td>${bicycle.getKey().getState()}</td>
												<td>${bicycle.getKey().getStatus()}</td>
												<c:choose>
													<c:when test='${bicycle.getValue().getBicycleId() == 0 || bicycle.getKey().getStatus() == "DISABLE"}'>
														<td>Not on map</td>
													</c:when>
													<c:when test='${bicycle.getValue().getBicycleId() != 0 && bicycle.getKey().getStatus() == "ENABLE"}'>
														<td>On map</td>
													</c:when>
												</c:choose>
												<c:choose>
													<c:when test='${bicycle.getKey().getStatus() == "DISABLE"}'>
														<td><a href="${pageContext.session.servletContext.contextPath}/controller?command=Enable_bicycle&id=${bicycle.getKey().getId()}"><i
																class="glyphicon glyphicon-check"></i></a></td>
													</c:when>
													<c:when test='${bicycle.getKey().getStatus() == "ENABLE"}'>
														<td><a href="${pageContext.session.servletContext.contextPath}/controller?command=Disable_bicycle&id=${bicycle.getKey().getId()}"><i
																class="glyphicon glyphicon-Lock"></i></a></td>
													</c:when>
												</c:choose>
											</tr>
										</c:forEach>
									</table>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-4">
					<h3>${add_bicycle}</h3>
					<div class="adding">
						<form class="add" method="POST" action="${pageContext.session.servletContext.contextPath}/controller">
							<input type="hidden" name="command" value="Add_bicycle" />
							<div class="form-group">
								<label for="Card master">${choose_brand}  </label> <select name="Brand" id="select" required>
									<c:forEach var="num" items="${priceLists}">
										<option value="${num.getBrand()}">${num.getBrand()}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for="Card master">${choose_color}  </label> <select name="Color" id="select" required>
									<option value="RED">RED</option>
									<option value="GREEN">GREEN</option>
									<option value="BLACK">BLACK</option>
									<option value="WHITE">WHITE</option>
									<option value="YELLOW">YELLOW</option>
									<option value="BLUE">BLUE</option>
									<option value="GRY">GRAY</option>
									<option value="PURPLE">PURPLE</option>
									<option value="ORANGE">ORANGE</option>
								</select>
							</div>
							<div class="form-group">
								<input type="text" name="MaxSpeed" value="" class="form-control" placeholder="${enter_speed}" pattern="^[0-9]{1,2}$" required /> <span class="form_error">${speed_error}</span>
							</div>
							<div class="form-group">
								<label for="Card master">${choose_state}  </label> <select name="State" id="select" required>
									<option value="GOOD">GOOD</option>
									<option value="WORN">WORN</option>
									<option value="BAD">BAD</option>
								</select>
							</div>
							<div class="form-group">
								<div class="row">
									<div class="col-sm-2">
										<input type="text" name="ImagePath" value="" class="form-control" id="imagePath" placeholder="${enter_path}" pattern="^.\/image\/[a-zA-z0-9]{1,100}.(png|jpg)$"
											required /> <span class="form_error">${image_error}</span>
									</div>
									<div class="col-sm-1">
										<div class="search">
											<div id="search-container">
												<image id="searchImage" src="./image/search.png" />
											</div>
											<input id="uploadImage" type="file" name="search_photo" placeholder="Photo" required="" capture>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-4">
									<input type="radio" class="toggle" name="status" value="ENABLE" id="type"> ${enable}
								</div>
								<div class="col-sm-1">
									<button type="submit" class="btn btn-submit" id="bicycle-button">${create}</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="change_bicycle" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" style="padding: 35px 50px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>
						<span class="glyphicon glyphicon-usd"></span> ${change_info}</h4>
				</div>
				<div class="modal-body" style="padding: 40px 50px;">
				<h3>${bicycle_info}</h3>
					<form name="form" method="POST" action="${pageContext.session.servletContext.contextPath}/controller">
						<input type="hidden" name="command" value="Change_bicycle" />
						<input type="hidden" name="id" value="" />
							<div class="form-group">
							<label for="Card master">${choose_color}  </label> <select name="Color" id="select" required>
									<option value="RED">RED</option>
									<option value="GREEN">GREEN</option>
									<option value="BLACK">BLACK</option>
									<option value="WHITE">WHITE</option>
									<option value="YELLOW">YELLOW</option>
									<option value="BLUE">BLUE</option>
									<option value="GRY">GRAY</option>
									<option value="PURPLE">PURPLE</option>
									<option value="ORANGE">ORANGE</option>
								</select>
							</div>
							<div class="form-group">
								<input type="text" name="MaxSpeed" value="" class="form-control" placeholder="${enter_speed}" pattern="^[0-9]{1,2}$" required /> <span class="form_error">${speed_error}</span>
							</div>
							<div class="form-group">
								<label for="Card master">${choose_state}  </label> <select name="State" id="select" required>
									<option value="GOOD">GOOD</option>
									<option value="WORN">WORN</option>
									<option value="BAD">BAD</option>
								</select>
							</div>
							<div class="form-group">
								<input type="text" name="ImagePath" value="" class="form-control" id="imagePath" placeholder="${enter_path}" pattern="^.\/image\/[a-zA-z0-9]{1,100}.(png|jpg)$"
											required /> <span class="form_error">${image_error}</span>
							</div>
						<button type="submit" class="btn btn-success btn-block">${update}</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	<%@include file="/js/bicycles.js"%>
	</script>
</body>
</html>