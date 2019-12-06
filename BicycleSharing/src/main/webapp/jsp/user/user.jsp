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
<c:set var="previous_path" value="controller?command=User_home_page" scope="session" />
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
<fmt:message bundle="${local}" key="main.user.balance" var="user_balance" />
<fmt:message bundle="${local}" key="main.replenish" var="replenish" />
<fmt:message bundle="${local}" key="main.enter.code" var="enter_code" />
<fmt:message bundle="${local}" key="main.enter.amount" var="enter_amount" />
<fmt:message bundle="${local}" key="main.code.error" var="code_error" />
<fmt:message bundle="${local}" key="main.cash.error" var="cash_error" />
<fmt:message bundle="${local}" key="main.number.error" var="number_error" />
<fmt:message bundle="${local}" key="main.enter.number" var="enter_number" />
<title>${title}</title>
</head>
<body>
	<jsp:include page="/jsp/user/user_navbar.jsp" />
	<div class="info">
		<div class="container">
			<div class="row">
				<div class="col-sm-4">
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
											<h4 class="text-left">${book_start}:</h4>
											<p class="form-control">${order.getBookedStartTime()}
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
											<h4 class="text-left">${actual_start}:</h4>
											<p class="form-control">${order.getActualStartTime()}</p>
										</div>
										<button type="submit" class="btn btn-submit" id="road_button">${cancel}</button>
									</form>
								</c:when>
							</c:choose>
						</c:when>
					</c:choose>
					<div class="cash">
						<form class="form" method="POST" action="${pageContext.session.servletContext.contextPath}/controller" id="card">
							<input type="hidden" name="command" value="Replenish_balance" />
							<div class="form-group">
								<select name="cardId" id="cardId">
									<option value="BELARUSBANK">BELARUSBANK</option>
									<option value="BELINVESTBANK">BELINVESTBANK</option>
									<option value="ALFABANK">ALFABANK</option>
								</select> <label for="Cash" id="label_cash">${user_balance} ${user.getCash()} $</label>
							</div>
							<div class="form-group">
								<input type="text" name="code" value="" class="form-control" placeholder="${enter_code}" pattern="^[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}$" required /> <span
									class="form_error">${code_error}</span>
							</div>
							<div class="form-group">
								<input type="text" name="amount" value="" class="form-control" placeholder="${enter_amount}" pattern="^[0-9]{1,3}\.[0-9]{1,2}$" required />
							</div>
							<div class="form-group">
								<input type="text" name="number" value="" class="form-control" placeholder="${enter_number}" pattern="^[0-9]{1,3}$" required />
								<span class="form_error">${number_error}</span>
							</div>
							<button type="submit" class="btn btn-submit" id="replenish">${replenish}</button>
						</form>
					</div>
				</div>
				<div class="col-sm-8">
					<h3>${history}</h3>
					<div id="table-wrapper">
						<div class="table-inner">
							<div class="tab">
								<form name="form" method="POST" action="${pageContext.session.servletContext.contextPath}/controller">
									<table class="table table-hover">
										<tr>
											<th>${book_start}</th>
											<th>${actual_start}</th>
											<th>${actual_end}</th>
											<th>${distance}</th>
											<th>${direction}</th>
											<th>${brand}</th>
											<th></th>
										</tr>
										<c:forEach var="num" items="${orderList.entrySet()}">
											<tr>
												<td>${num.getKey().getBookedStartTime()}</td>
												<td>${num.getKey().getActualStartTime()}</td>
												<td>${num.getKey().getActualEndTime()}</td>
												<td>${num.getKey().getDistance()}</td>
												<td>${num.getKey().getDirection()}</td>
												<td>${num.getValue().getBrand()}</td>
												<td>${num.getValue().getColor()}</td>
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