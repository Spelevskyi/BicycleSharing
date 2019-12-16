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
<%@include file="/css/user_billing.css"%>
</style>

<c:set var="previous_path" value="controller?command=User_billing_page" scope="session" />
<c:set var="language" value="${sessionScope.lang}" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="properties.local" var="local" />
<fmt:message bundle="${local}" key="billing.create.card" var="create_card" />
<fmt:message bundle="${local}" key="billing.choose.master" var="choose_master" />
<fmt:message bundle="${local}" key="billing.add.card" var="add_card" />
<fmt:message bundle="${local}" key="billing.card.table" var="card_table" />
<fmt:message bundle="${local}" key="billing.master" var="master" />
<fmt:message bundle="${local}" key="billing.balance" var="balance" />
<fmt:message bundle="${local}" key="billing.code.error" var="code_error" />
<fmt:message bundle="${local}" key="billing.enter.code" var="enter_code" />
<fmt:message bundle="${local}" key="billing.idnumber.error" var="number_error" />
<fmt:message bundle="${local}" key="billing.enter.idnumber" var="enter_number" />
<fmt:message bundle="${local}" key="billing.date.error" var="date_error" />
<fmt:message bundle="${local}" key="billing.enter.date" var="enter_date" />
<fmt:message bundle="${local}" key="billing.title" var="title" />
<fmt:message bundle="${local}" key="billing.debt.table" var="debt_table" />
<fmt:message bundle="${local}" key="billing.amount" var="amount" />
<fmt:message bundle="${local}" key="billing.creation" var="creation" />
<fmt:message bundle="${local}" key="billing.validation.date" var="validation_date" />
<fmt:message bundle="${local}" key="billing.ru" var="ru" />
<fmt:message bundle="${local}" key="billing.en" var="en" />
<title>${title}</title>
</head>
<body>
	<jsp:include page="/jsp/user/user_navbar.jsp" />
	<div class="info">
		<div class="container">
			<div class="row">
				<div class="col-sm-4">
					<h3>${create_card}</h3>
					<div class="adding">
						<form name="form" method="POST" action="${pageContext.session.servletContext.contextPath}/controller" id="card">
							<input type="hidden" name="command" value="Create_card" />
							<div class="form-group">
								<label for="Card master">${choose_master}</label> <select name="master" id="select">
									<option value="BELARUSBANK">BELARUSBANK</option>
									<option value="BELINVESTBANK">BELINVESTBANK</option>
									<option value="ALFABANK">ALFABANK</option>
								</select>
							</div>
							<div class="form-group">
								<input type="text" name="code" value="" class="form-control" placeholder="${enter_code}" pattern="^[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}$" required /> <span
									class="form_error">${code_error}</span>
							</div>
							<div class="form-group">
								<input type="text" name="id_number" value="" class="form-control" placeholder="${enter_number}" pattern="^[0-9]{3}$" required /> <span class="form_error">${number_error}</span>
							</div>
							<div class="form-group">
								<input type="text" name="date" value="" class="form-control" placeholder="${enter_date}" pattern="^[0-9]{4}-[0-9]{2}-[0-9]{2}$" required /> <span
									class="form_error">${date_error}</span>
							</div>
							<button type="submit" class="btn btn-success btn-block" id="bill-button">${add_card}</button>
						</form>
					</div>
				</div>
				<div class="col-sm-8">
					<h3>${card_table}</h3>
					<div id="bill-wrapper">
						<div class="bill-inner">
							<div class="tab">
								<form name="form" method="POST" action="${pageContext.session.servletContext.contextPath}/controller">
									<div class="row-button">
										<div class="col-sm-1">
											<input type="hidden" name="command" value="Delete_card" onclick="return confirm('Are you sure?')">
											<button type="submit" class="btn">
												<i class="glyphicon glyphicon-trash"></i>
											</button>
										</div>
									</div>
									<table class="table table-hover">
										<tr>
											<th><input type="checkbox" id="checkBoxAll" /></th>
											<th>${master}</th>
											<th>${balance}</th>
											<th></th>
										</tr>
										<c:forEach var="num" items='${cards}'>
											<tr>

												<td><input type="checkbox" class="chkCheckBoxId" value='${num.getId()}' name="id" /></td>
												<td>${num.getType()}</td>
												<td>${num.getBalance().doubleValue()}</td>
												<c:choose>
													<c:when test='${num.getStatus() == "DISABLE"}'>
														<td><a href="${pageContext.session.servletContext.contextPath}/controller?command=Enable_card&id=${num.getId()}"><i
																class="glyphicon glyphicon-check"></i></a></td>
													</c:when>
													<c:when test='${num.getStatus() == "ENABLE"}'>
														<td><a href="${pageContext.session.servletContext.contextPath}/controller?command=Disable_card&id=${num.getId()}"><i
																class="glyphicon glyphicon-Lock"></i></a></td>
													</c:when>
												</c:choose>
											</tr>
										</c:forEach>
										</tbody>
									</table>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="debets">
					<h3>${debt_table}</h3>
					<div id="bill-wrapper">
						<div class="bill-inner">
							<div class="tab">
								<form name="form" method="POST" action="${pageContext.session.servletContext.contextPath}/controller">
									<table class="table table-hover">
										<tr>
											<th>${amount}</th>
											<th>${creation}</th>
											<th></th>
										</tr>
										<c:forEach var="num" items='${debts}'>
											<tr>
												<td>${num.getAmount()}</td>
												<td>${num.getCreationDate()}</td>
												<td><a href="${pageContext.session.servletContext.contextPath}/controller?command=Pay_debt&id=${num.getId()}"><i class="glyphicon glyphicon-usd"></i></a></td>
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
	<script type="text/javascript">
	<%@include file="/js/user_billing.js"%>
	</script>
</body>
</html>