<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
<%@include file="/css/users.css"%>
</style>

<c:set var="previous_path" value="controller?command=Users" scope="session" />
<c:set var="language" value="${sessionScope.lang}" />
<fmt:setLocale value="${language}" />

<fmt:setBundle basename="properties.local" var="local" />
<fmt:message bundle="${local}" key="users.table.info" var="table_info" />
<fmt:message bundle="${local}" key="users.firstname" var="firstname" />
<fmt:message bundle="${local}" key="users.lastname" var="lastname" />
<fmt:message bundle="${local}" key="users.email" var="email" />
<fmt:message bundle="${local}" key="users.password" var="password" />
<fmt:message bundle="${local}" key="users.number" var="number" />
<fmt:message bundle="${local}" key="users.registration" var="registration" />
<fmt:message bundle="${local}" key="users.balance" var="balance" />
<fmt:message bundle="${local}" key="users.confirmed" var="confirmed" />
<fmt:message bundle="${local}" key="users.amount" var="amount" />
<fmt:message bundle="${local}" key="users.status" var="status" />
<fmt:message bundle="${local}" key="users.title" var="title" />
<fmt:message bundle="${local}" key="users.online" var="online" />
<fmt:message bundle="${local}" key="users.error.info" var="error_info" />
<title>${title}</title>
</head>
<body>
	<jsp:include page="/jsp/admin/admin_navbar.jsp" />
	<div class="info">
		<div class="container">
			<h3>${table_info}</h3>
			<div id="table-wrapper">
				<div class="table-inner">
					<div class="tab">
						<form name="form" method="POST" action="${pageContext.session.servletContext.contextPath}/controller">
							<div class="row-button">
								<div class="col-sm-1">
									<input type="hidden" name="command" value="Delete_user">
									<button type="submit" class="btn">
										<i class="glyphicon glyphicon-trash"></i>
									</button>
								</div>
							</div>
							<table class="table table-hover">
								<tr>
									<th><input type="checkbox" id="checkBoxAll" /></th>
									<th>${firstname}</th>
									<th>${lastname}</th>
									<th>${email}</th>
									<th>${number}</th>
									<th>${registration}</th>
									<th>${amount}</th>
									<th>${balance}</th>
									<th>${confirmed}</th>
									<th>${online}</th>
									<th></th>
								</tr>
								<c:forEach var="user" items="${users}">
									<tr>
										<td><input type="checkbox" class="chkCheckBoxId" value="${user.getId()}" name="id" /></td>
										<td>${user.getFirstName()}</td>
										<td>${user.getLastName()}</td>
										<td>${user.getEmail()}</td>
										<td>${user.getPhoneNumber()}</td>
										<td>${user.getRegistrationDate()}</td>
										<td>${user.getRentalAmount()}</td>
										<td>${user.getCash()}</td>
										<td>${user.isConfirmed()}</td>
										<td>${user.isOnline()}</td>
										<c:choose>
											<c:when test='${user.getStatus() == "LOCKED"}'>
												<td><a href="${pageContext.session.servletContext.contextPath}/controller?command=Unlock_user&id=${user.getId()}"><i
														class="glyphicon glyphicon-check"></i></a></td>
											</c:when>
											<c:when test='${user.getStatus() == "UNLOCKED"}'>
												<td><a href="${pageContext.session.servletContext.contextPath}/controller?command=Lock_user&id=${user.getId()}"><i
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
	</div>
	<script type="text/javascript">
	<%@include file="/js/users.js"%>
	</script>
</body>
</html>