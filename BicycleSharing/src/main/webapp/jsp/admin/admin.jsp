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
<%@include file="/css/main.css"%>
</style>

<script>
	$(document).ready(function() {
		if ('${error[0]}' == "true") {
			$("#userError").modal('show');
		}
	});
</script>

<c:set var="previous_path" value="controller?command=Admin_home_page" scope="session" />
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
<fmt:message bundle="${local}" key="main.title" var="title" />
<fmt:message bundle="${local}" key="main.users.debts" var="users_debts" />
<fmt:message bundle="${local}" key="main.debt.amount" var="debt_amount" />
<fmt:message bundle="${local}" key="main.debt.date" var="debt_date" />
<fmt:message bundle="${local}" key="main.debt.user" var="debt_user" />
<fmt:message bundle="${local}" key="main.debt.email" var="debt_email" />
<fmt:message bundle="${local}" key="main.debt.rentals" var="debt_rentals" />
<fmt:message bundle="${local}" key="main.debt.last" var="debt_last" />
<fmt:message bundle="${local}" key="main.error.info" var="error_info" />

<title>${title}</title>
</head>
<body>
	<jsp:include page="/jsp/admin/admin_navbar.jsp" />
	<div class="info">
		<div class="container">
			<div class="row">
				<h3>${users_debts}</h3>
				<div id="table-wrapper">
					<div class="table-inner">
						<div class="tab">
							<form name="form" method="POST" action="${pageContext.session.servletContext.contextPath}/controller">
								<table class="table table-hover">
									<tr>
										<th>${debt_amount}</th>
										<th>${debt_date}</th>
										<th>${debt_user}</th>
										<th>${debt_email}</th>
										<th>${debt_rentals}</th>
										<th>${debt_last}</th>
									</tr>
									<c:forEach var="debt" items="${debts.entrySet()}">
										<tr>
											<td>${debt.getKey().getAmount().doubleValue()}</td>
											<td>${debt.getKey().getCreationDate()}</td>
											<td>${debt.getValue().getFirstName()}  ${debt.getValue().getLastName()}</td>
											<td>${debt.getValue().getEmail()}</td>
											<td>${debt.getValue().getRentalAmount()}</td>
											<td>${debt.getValue().getLastRentalDate()}</td>
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
</body>
</html>