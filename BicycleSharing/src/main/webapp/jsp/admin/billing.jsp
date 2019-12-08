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
<%@include file="/css/billing.css"%>
</style>

<c:set var="previous_path" value="controller?command=Admin_billing_page" scope="session" />
<c:set var="language" value="${lang}" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="properties.local" var="local" />
<fmt:message bundle="${local}" key="billing.price.list" var="price_list" />
<fmt:message bundle="${local}" key="billing.brand" var="brand" />
<fmt:message bundle="${local}" key="billing.unlockprice" var="unlockprice" />
<fmt:message bundle="${local}" key="billing.per.minute" var="per_minute" />
<fmt:message bundle="${local}" key="billing.per.hour" var="per_hour" />
<fmt:message bundle="${local}" key="billing.per.day" var="per_day" />
<fmt:message bundle="${local}" key="billing.three.hour" var="three_hour" />
<fmt:message bundle="${local}" key="billing.six.hour" var="six_hour" />
<fmt:message bundle="${local}" key="billing.nine.hour" var="nine_hour" />
<fmt:message bundle="${local}" key="billing.all.day" var="all_day" />
<fmt:message bundle="${local}" key="billing.regular" var="regular" />
<fmt:message bundle="${local}" key="billing.traveler" var="traveler" />
<fmt:message bundle="${local}" key="billing.new" var="newcustomer" />
<fmt:message bundle="${local}" key="billing.price.list" var="price_list" />
<fmt:message bundle="${local}" key="billing.edit" var="edit" />
<fmt:message bundle="${local}" key="billing.enter.unlock" var="enter_unlock" />
<fmt:message bundle="${local}" key="billing.enter.brand" var="enter_brand" />
<fmt:message bundle="${local}" key="billing.enter.per.minute" var="enter_per_minute" />
<fmt:message bundle="${local}" key="billing.enter.per.hour" var="enter_per_hour" />
<fmt:message bundle="${local}" key="billing.enter.per.day" var="enter_per_day" />
<fmt:message bundle="${local}" key="billing.enter.stay" var="enter_stay" />
<fmt:message bundle="${local}" key="billing.enter.three" var="enter_three" />
<fmt:message bundle="${local}" key="billing.enter.six" var="enter_six" />
<fmt:message bundle="${local}" key="billing.enter.nine" var="enter_nine" />
<fmt:message bundle="${local}" key="billing.enter.all.day" var="enter_all_day" />
<fmt:message bundle="${local}" key="billing.enter.regular" var="enter_regular" />
<fmt:message bundle="${local}" key="billing.enter.traveler" var="enter_traveler" />
<fmt:message bundle="${local}" key="billing.enter.new" var="enter_new" />
<fmt:message bundle="${local}" key="billing.stay.price" var="stay_price" />
<fmt:message bundle="${local}" key="billing.change.info" var="change_info" />
<fmt:message bundle="${local}" key="billing.price.error" var="price_error" />
<fmt:message bundle="${local}" key="billing.percentage.error" var="percentage_error" />
<fmt:message bundle="${local}" key="billing.day.price.error" var="day_error" />
<fmt:message bundle="${local}" key="billing.brand.error" var="brand_error" />
<fmt:message bundle="${local}" key="billing.update" var="update" />
<fmt:message bundle="${local}" key="billing.error.info" var="error_info" />
<title>${title}</title>
</head>
<body>
	<jsp:include page="/jsp/admin/admin_navbar.jsp" />
	<div class="info">
		<div class="container">
			<div class="row">
				<h3>${price_list}</h3>
				<div id="table-wrapper">
					<div class="inner">
						<div class="tab">
							<form name="form" method="POST" action="${pageContext.session.servletContext.contextPath}/controller">
								<div class="row-button">
									<div class="col-sm-1">
										<a href="#add_billing" data-toggle="modal" id="plus">Add</a>
									</div>
									<div class="col-sm-1">
										<input type="hidden" name="command" value="Delete_billing">
										<button type="submit" class="btn">
											<i class="glyphicon glyphicon-trash"></i>
										</button>
									</div>
								</div>
								<table class="table table-hover">
									<tr>
										<th><input type="checkbox" id="checkBoxAll" /></th>
										<th>${brand}</th>
										<th>${unlockprice}</th>
										<th>${per_minute}</th>
										<th>${per_hour}</th>
										<th>${three_hour}</th>
										<th>${six_hour}</th>
										<th>${nine_hour}</th>
										<th>${all_day}</th>
										<th>${regular}</th>
										<th>${traveler}</th>
										<th>${newcustomer}</th>
										<th></th>
									</tr>
									<c:forEach var="num" items="${priceLists}">
										<tr>
											<td><input type="checkbox" class="chkCheckBoxId" value='${num.getId()}' name="id" /></td>
											<td>${num.getBrand()}</td>
											<td>${num.getUnlockPrice()}</td>
											<td>${num.getPerMinutePrice()}</td>
											<td>${num.getPerHourPrice()}</td>
											<td>${num.getThreeHoursDiscount()}</td>
											<td>${num.getSixHoursDiscount()}</td>
											<td>${num.getNineHoursDiscount()}</td>
											<td>${num.getDaySale()}</td>
											<td>${num.getRegularCustomerDiscount()}</td>
											<td>${num.getTravelerDiscount()}</td>
											<td>${num.getNewCustomerDiscount()}</td>
											<td><a href="#change_billing" data-toggle="modal" data-id='${num.getId()}'>${edit}</a></td>
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
	<div class="modal fade" id="change_billing" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" style="padding: 35px 50px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>
						<span class="glyphicon glyphicon-usd"></span>${change_info}</h4>
				</div>
				<div class="modal-body" style="padding: 40px 50px;">
					<form name="form" method="POST" action="${pageContext.session.servletContext.contextPath}/controller">
						<input type="hidden" name="command" value="Change_billing" /> <input type="hidden" name="id" value="" />
						<div class="form-group">
							<label for="Unlock price"><span class="glyphicon glyphicon-usd"></span>${unlockprice}</label> <input type="text" name="UnlockPrice" value="" class="form-control"
								placeholder="${enter_unlock}" pattern="^[0-9]{1,3}\.[0-9]{1,2}$" required /> <span class="form_error">${price_error}</span>
						</div>
						<div class="form-group">
							<label for="Price per minute"><span class="glyphicon glyphicon-usd"></span>${per_minute}</label> <input type="text" name="PerMinutePrice" value=""
								class="form-control" placeholder="${enter_per_minute}" pattern="^[0-9]{1,3}\.[0-9]{1,2}$" required /> <span class="form_error">${price_error}</span>
						</div>
						<div class="form-group">
							<label for="Price per hour"><span class="glyphicon glyphicon-usd"></span>${per_hour}</label> <input type="text" name="PerHourPrice" value="" class="form-control"
								placeholder="${enter_per_hour}" pattern="^[0-9]{1,3}\.[0-9]{1,2}$" required /> <span class="form_error">${price_error}</span>
						</div>
						<div class="form-group">
							<label for="Price per day"><span class="glyphicon glyphicon-usd"></span>${per_day}</label> <input type="text" name="PerDayPrice" value="" class="form-control"
								placeholder="${enter_per_day}" pattern="^[0-9]{1,4}\.[0-9]{1,2}$" required /> <span class="form_error">${day_error}</span>
						</div>
						<div class="form-group">
							<label for="Stay price"><span class="glyphicon glyphicon-usd"></span>${stay_price}</label> <input type="text" name="StayPrice" value="" class="form-control"
								placeholder="${enter_stay}" pattern="^[0-9]{1,3}\.[0-9]{1,2}$" required /> <span class="form_error">${price_error}</span>
						</div>
						<div class="form-group">
							<label for="Three hour discount"><span class="glyphicon glyphicon-usd"></span>${three_hour}</label> <input type="text" name="ThreeHourDiscount" value=""
								class="form-control" placeholder="${enter_three}" pattern="^[0-9]{1,3}$" required /> <span class="form_error">${percentage_error}</span>
						</div>
						<div class="form-group">
							<label for="Six hour discount"><span class="glyphicon glyphicon-usd"></span>${six_hour}</label> <input type="text" name="SixHourDiscount" value=""
								class="form-control" placeholder="${enter_six}" pattern="^[0-9]{1,3}$" required /> <span class="form_error">${percentage_error}</span>
						</div>
						<div class="form-group">
							<label for="Nine hour discount"><span class="glyphicon glyphicon-usd"></span>${nine_hour}</label> <input type="text" name="NineHourDiscount" value=""
								class="form-control" placeholder="${enter_nine}" pattern="^[0-9]{1,3}$" required /> <span class="form_error">${percentage_error}</span>
						</div>
						<div class="form-group">
							<label for="Day sale"><span class="glyphicon glyphicon-usd"></span>${all_day}</label> <input type="text" name="DaySale" value="" class="form-control"
								placeholder="${enter_day_sale}" pattern="^[0-9]{1,3}$" required /> <span class="form_error">${percentage_error}</span>
						</div>
						<div class="form-group">
							<label for="Regular customer discount"><span class="glyphicon glyphicon-usd"></span>${regular}</label> <input type="text" name="RegularCustomerDiscount" value=""
								class="form-control" placeholder="${enter_regular}" pattern="^[0-9]{1,3}$" required /> <span class="form_error">${percentage_error}</span>
						</div>
						<div class="form-group">
							<label for="Traveler discount"><span class="glyphicon glyphicon-usd"></span>${traveler}</label> <input type="text" name="TravelerDiscount" value=""
								class="form-control" placeholder="${enter_traveler}" pattern="^[0-9]{1,3}$" required /> <span class="form_error">${percentage_error}</span>
						</div>
						<div class="form-group">
							<label for="New customer discount"><span class="glyphicon glyphicon-usd"></span>${newcustomer}</label> <input type="text" name="NewCustomerDiscount" value=""
								class="form-control" placeholder="${enter_new}" pattern="^[0-9]{1,3}$" required /> <span class="form_error">${percentage_error}</span>
						</div>
						<button type="submit" class="btn btn-success btn-block">${update}</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="add_billing" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" style="padding: 35px 50px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>
						<span class="glyphicon glyphicon-usd"></span>${change_info}</h4>
				</div>
				<div class="modal-body" style="padding: 40px 50px;">
					<form name="form" method="POST" action="${pageContext.session.servletContext.contextPath}/controller">
						<input type="hidden" name="command" value="Add_billing" />
						<div class="form-group">
							<label for="Brand"><span class="glyphicon glyphicon-user"></span>${brand}</label> <input type="text" name="Brand" value="" class="form-control"
								placeholder="${enter_brand}" pattern="^[A-Z]{2,20}$" required /> <span class="form_error">${brand_error}</span>
						</div>
						<div class="form-group">
							<label for="Unlock price"><span class="glyphicon glyphicon-usd"></span>${unlockprice}</label> <input type="text" name="UnlockPrice" value="" class="form-control"
								placeholder="${enter_unlock}" pattern="^[0-9]{1,3}\.[0-9]{1,2}$" required /> <span class="form_error">${price_error}</span>
						</div>
						<div class="form-group">
							<label for="Price per minute"><span class="glyphicon glyphicon-usd"></span>${per_minute}</label> <input type="text" name="PerMinutePrice" value=""
								class="form-control" placeholder="${enter_per_minute}" pattern="^[0-9]{1,3}\.[0-9]{1,2}$" required /> <span class="form_error">${price_error}</span>
						</div>
						<div class="form-group">
							<label for="Price per hour"><span class="glyphicon glyphicon-usd"></span>${per_hour}</label> <input type="text" name="PerHourPrice" value="" class="form-control"
								placeholder="${enter_per_hour}" pattern="^[0-9]{1,3}\.[0-9]{1,2}$" required /> <span class="form_error">${price_error}</span>
						</div>
						<div class="form-group">
							<label for="Price per day"><span class="glyphicon glyphicon-usd"></span>${per_day}</label> <input type="text" name="PerDayPrice" value="" class="form-control"
								placeholder="${enter_per_day}" pattern="^[0-9]{1,4}\.[0-9]{1,2}$" required /> <span class="form_error">${price_error}</span>
						</div>
						<div class="form-group">
							<label for="Stay price"><span class="glyphicon glyphicon-usd"></span>${stay_price}</label> <input type="text" name="StayPrice" value="" class="form-control"
								placeholder="${enter_stay}" pattern="^[0-9]{1,3}\.[0-9]{1,2}$" required /> <span class="form_error">${price_error}</span>
						</div>
						<div class="form-group">
							<label for="Three hour discount"><span class="glyphicon glyphicon-usd"></span>${three_hour}</label> <input type="text" name="ThreeHourDiscount" value=""
								class="form-control" placeholder="${enter_three}" pattern="^[0-9]{1,3}$" required /> <span class="form_error">${price_error}</span>
						</div>
						<div class="form-group">
							<label for="Six hour discount"><span class="glyphicon glyphicon-usd"></span>${six_hour}</label> <input type="text" name="SixHourDiscount" value=""
								class="form-control" placeholder="${enter_six}" pattern="^[0-9]{1,3}$" required /> <span class="form_error">${price_error}</span>
						</div>
						<div class="form-group">
							<label for="Nine hour discount"><span class="glyphicon glyphicon-usd"></span>${nine_hour}</label> <input type="text" name="NineHourDiscount" value=""
								class="form-control" placeholder="${enter_nine}" pattern="^[0-9]{1,3}$" required /> <span class="form_error">${price_error}</span>
						</div>
						<div class="form-group">
							<label for="Day sale"><span class="glyphicon glyphicon-usd"></span>${all_day}</label> <input type="text" name="DaySale" value="" class="form-control"
								placeholder="${enter_day_sale}" pattern="^[0-9]{1,3}$" required /> <span class="form_error">${price_error}</span>
						</div>
						<div class="form-group">
							<label for="Regular customer discount"><span class="glyphicon glyphicon-usd"></span>${regular}</label> <input type="text" name="RegularCustomerDiscount" value=""
								class="form-control" placeholder="${enter_regular}" pattern="^[0-9]{1,3}$" required /> <span class="form_error">${price_error}</span>
						</div>
						<div class="form-group">
							<label for="Traveler discount"><span class="glyphicon glyphicon-usd"></span>${traveler}</label> <input type="text" name="TravelerDiscount" value=""
								class="form-control" placeholder="${enter_traveler}" pattern="^[0-9]{1,3}$" required /> <span class="form_error">${price_error}</span>
						</div>
						<div class="form-group">
							<label for="New customer discount"><span class="glyphicon glyphicon-usd"></span>${newcustomer}</label> <input type="text" name="NewCustomerDiscount" value=""
								class="form-control" placeholder="${enter_new}" pattern="^[0-9]{1,3}$" required /> <span class="form_error">${price_error}</span>
						</div>
						<button type="submit" class="btn btn-success btn-block">${update}</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
		$('input[name=toggle]:checked').val();
		$(document).ready(function() {
			$('#checkBoxAll').click(function() {
				if ($(this).is(":checked"))
					$('.chkCheckBoxId').prop('checked', true);
				else
					$('.chkCheckBoxId').prop('checked', false);
			});
		});
		
		$('#change_billing').on('show.bs.modal', function(e) {
			var id = $(e.relatedTarget).data('id');
			$(e.currentTarget).find('input[name="id"]').val(id);
		});
	</script>
</body>
</html>