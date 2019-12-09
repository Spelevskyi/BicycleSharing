
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
<%@include file="/css/favorites.css"%>
</style>

<c:set var="previous_path" value="controller?command=Favorites" scope="session" />
<c:set var="language" value="${lang}" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="properties.local" var="local" />
<fmt:message bundle="${local}" key="favorites.brand.table" var="brand_table" />
<fmt:message bundle="${local}" key="favorites.color.table" var="color_table" />
<fmt:message bundle="${local}" key="favorites.color" var="color" />
<fmt:message bundle="${local}" key="favorites.brand" var="brand" />
<fmt:message bundle="${local}" key="favorites.amount" var="amount" />
<fmt:message bundle="${local}" key="favorites.title" var="title" />
<title>${title}</title>
</head>
<body>
	<jsp:include page="/jsp/user/user_navbar.jsp" />
	<div class="info">
		<div class="container">
			<div class="row">
				<div class="col-sm-5">
					<h3>${brand_table}</h3>
					<div id="image-wrapper">
						<div class="inner">
							<div class="tab">
								<form name="form" method="POST" action="../controller">
									<table class="table table-hover">
										<tr>
											<th>${brand}</th>
											<th>${amount}</th>
										</tr>
										<c:forEach var="num" items="${brandSorted.entrySet()}">
											<tr>
												<td>${num.getValue()}</td>
												<td>${num.getKey()}</td>
											</tr>
										</c:forEach>
									</table>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-5">
					<h3>${color_table}</h3>
					<div id="image-wrapper">
						<div class="inner">
							<div class="tab">
								<form name="form" method="POST" action="../controller">
									<table class="table table-hover">
										<tr>
											<th>${color}</th>
											<th>${amount}</th>
										</tr>
										<c:forEach var="num" items="${colorSorted.entrySet()}">
										<tr>
											<td>${num.getValue()}</td>
											<td>${num.getKey()}</td>	
										</tr>
										</c:forEach>
									</table>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>