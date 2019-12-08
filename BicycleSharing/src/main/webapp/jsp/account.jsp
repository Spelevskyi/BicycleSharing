<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
<%@include file="/css/account.css"%>
</style>

<c:set var="previous_path" value="controller?command=Account_page" scope="session" />
<c:set var="language" value="${lang}" />
<fmt:setLocale value="${language}" />

<fmt:setBundle basename="properties.local" var="local" />
<fmt:message bundle="${local}" key="account.home" var="home" />
<fmt:message bundle="${local}" key="account.account" var="account" />
<fmt:message bundle="${local}" key="account.points" var="points" />
<fmt:message bundle="${local}" key="account.billing" var="billing" />
<fmt:message bundle="${local}" key="account.title" var="title" />
<fmt:message bundle="${local}" key="account.favorites" var="favorites" />
<fmt:message bundle="${local}" key="account.search" var="search" />
<fmt:message bundle="${local}" key="account.users" var="users" />
<fmt:message bundle="${local}" key="account.bicycles" var="bicycles" />
<fmt:message bundle="${local}" key="account.logout" var="logout" />
<fmt:message bundle="${local}" key="account.confirm" var="confirm" />
<fmt:message bundle="${local}" key="account.hello" var="hello" />
<fmt:message bundle="${local}" key="account.change.info" var="change_info" />
<fmt:message bundle="${local}" key="account.welcome" var="welcome" />
<fmt:message bundle="${local}" key="account.account.info" var="account_info" />
<fmt:message bundle="${local}" key="account.rental.info" var="rental_info" />
<fmt:message bundle="${local}" key="account.change.info" var="change_info" />
<fmt:message bundle="${local}" key="account.amount.rental" var="amount_rentals" />
<fmt:message bundle="${local}" key="account.last.date" var="last_date" />
<fmt:message bundle="${local}" key="account.email" var="email" />
<fmt:message bundle="${local}" key="account.number" var="number" />
<fmt:message bundle="${local}" key="account.registration.date" var="date" />
<fmt:message bundle="${local}" key="account.show.hidden" var="show_hidden" />
<fmt:message bundle="${local}" key="account.balance" var="balance" />
<fmt:message bundle="${local}" key="account.update.info" var="update_info" />
<fmt:message bundle="${local}" key="account.firstname" var="firstname" />
<fmt:message bundle="${local}" key="account.lastname" var="lastname" />
<fmt:message bundle="${local}" key="account.enter.firstname" var="enter_firstname" />
<fmt:message bundle="${local}" key="account.enter.lastname" var="enter_lastname" />
<fmt:message bundle="${local}" key="account.number" var="number" />
<fmt:message bundle="${local}" key="account.enter.number" var="enter_number" />
<fmt:message bundle="${local}" key="account.update" var="update" />
<fmt:message bundle="${local}" key="account.firstname.error" var="firstname_error" />
<fmt:message bundle="${local}" key="account.lastname.error" var="lastname_error" />
<fmt:message bundle="${local}" key="account.number.error" var="number_error" />
<fmt:message bundle="${local}" key="account.password.error" var="password_error" />
<fmt:message bundle="${local}" key="account.error.info" var="error_info" />
<fmt:message bundle="${local}" key="account.password" var="password" />
<fmt:message bundle="${local}" key="account.change.password" var="change_password" />
<fmt:message bundle="${local}" key="account.change" var="change" />
<fmt:message bundle="${local}" key="account.password.info" var="password_info" />
<fmt:message bundle="${local}" key="account.enter.password" var="enter_password" />
<fmt:message bundle="${local}" key="account.enter.second.password" var="enter_second_password" />
<fmt:message bundle="${local}" key="account.ru" var="ru" />
<fmt:message bundle="${local}" key="account.en" var="en" />
<title>${title}</title>

</head>
<body>
	<c:choose>
		<c:when test="${role == 'ADMIN'}">
			<jsp:include page="/jsp/admin/admin_navbar.jsp" />
		</c:when>
		<c:when test="${role == 'USER'}">
			<jsp:include page="/jsp/user/user_navbar.jsp" />
		</c:when>
	</c:choose>
	<div class="info text-center">
		<h1>${welcome}</h1>
		<div class="row">
			<div class="col-sm-6">
				<div class="account">
					<form id="avatar">
						<div id="profile-container">
							<image id="profileImage" src="${user.getImagePath()}" />
						</div>
						<input id="imageUpload" type="file" name="profile_photo" placeholder="Photo" required="" capture>
					</form>
					<form id="formId" action="${pageContext.session.servletContext.contextPath}/controller" method="POST">
						<input type="hidden" value="Change_avatar" name="command" /> <input type="hidden" name="ImagePath" id="ImagePathId" value="" />
					</form>
					<div class="form">
						<a href="#change_profile" data-toggle="modal" class="change-profile" type="submit">${change_info}</a>
					</div>
					<div class="form">
						<a href="#change_password" data-toggle="modal" class="change-profile" type="submit">${change_password}</a>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="account-info text-left">
					<div class="main-info">
						<h2>${user.getFirstName()}  ${user.getLastName()}</h2>
						<h4>${email}:  ${user.getEmail()}</h4>
					</div>
					<ul class="hidden-info">
						<li class="dropdown"><button class="dropdown-toggle" data-toggle="dropdown">${show_hidden}<b class="caret"></b>
							</button>
							<ul class="dropdown-menu">
								<li class="dropdown-header">${account_info}</li>
								<li class="divider"></li>
								<li><a href="#"></a></li>
								<li class="email"><a href="#">${number}: ${user.getPhoneNumber()}</a></li>
								<li class="email"><a href="#">${date}: ${user.getRegistrationDate()}</a></li>
								<li><a href="#"></a></li>
								<li><a href="#"></a></li>
								<li class="dropdown-header">${rental_info}</li>
								<li class="divider"></li>
								<li><a href="#"></a></li>
								<li class="email"><a href="#">${amount_rentals}: ${user.getRentalAmount()}</a></li>
								<li class="email"><a href="#">${last_date}: ${user.getLastRentalDate()}</a></li>
							</ul></li>
					</ul>
					<div class="account-cash text-left">
						<h3>${balance}:  ${user.getCash()}$</h3>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="change_profile" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" style="padding: 35px 50px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>
						<span class="glyphicon glyphicon-pencil"></span> ${update}
					</h4>
				</div>
				<div class="modal-body" style="padding: 40px 50px;">
					<h3>${update_info} ${user.getFirstName()} ${user.getLastName()}</h3>
					<form name="form" method="POST" action="${pageContext.session.servletContext.contextPath}/controller">
						<input type="hidden" name="command" value="Change_profile" /> <input type="hidden" name="id" value="${user.getId()}" />
						<div class="form-group">
							<label for="First name"><span class="glyphicon glyphicon-user"></span> ${firstname}</label> <input type="text" name="firstName" value="${user.getFirstName()}"
								class="form-control" placeholder="${enter_firstname}" pattern="^[A-Z][a-zA-Z]{2,20}$" required /> <span class="form_error">${firstname_error}</span>
						</div>
						<div class="form-group">
							<label for="Last name"><span class="glyphicon glyphicon-user"></span> ${lastname}</label> <input type="text" name="lastName" value="${user.getLastName()}"
								class="form-control" placeholder="${enter_lastname}" pattern="^[A-Z][a-zA-Z]{5,20}$" required /> <span class="form_error">${lastname_error}</span>
						</div>
						<div class="form-group">
							<label for="Phone number"><span class="glyphicon glyphicon-phone-alt"></span> ${number}</label> <input type="text" name="phoneNumber"
								value="${user.getPhoneNumber()}" class="form-control" placeholder="${enter_number}" pattern="(\+375|80) (29|25|44|33) (\d{3})-(\d{2})-(\d{2})$" required /> <span
								class="form_error">${number_error}</span>
						</div>
						<button type="submit" class="btn btn-success btn-block"> ${update}</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="change_password" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" style="padding: 35px 50px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>
						<span class="glyphicon glyphicon-eye-open"></span> ${password_info}
					</h4>
				</div>
				<div class="modal-body" style="padding: 40px 50px;">
					<h3>${password_info} ${user.getFirstName()} ${user.getLastName()}</h3>
					<form name="form" method="POST" action="controller">
						<input type="hidden" name="command" value="Change_password" />
						<div class="form-group">
							<label for="Password"><span class="glyphicon glyphicon-eye-open"></span> ${password}</label> <input type="password" name="first_password" value=""
								class="form-control" placeholder="${enter_password}" pattern="^[0-9a-zA-Z]{4,20}$" required /> <span class="form_error">${password_error}</span>
						</div>
						<div class="form-group">
							<label for="Password"><span class="glyphicon glyphicon-eye-open"></span> ${password}</label> <input type="password" name="second_password" value=""
								class="form-control" placeholder="${enter_second_password}" pattern="^[0-9a-zA-Z]{4,20}$" required /> <span class="form_error">${password_error}</span>
						</div>
						<button type="submit" class="btn btn-success btn-block">
							<span class="glyphicon glyphicon-off"></span> ${change}
						</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
		$("#profileImage").click(function(e) {
			$("#imageUpload").click();
		});

		function fasterPreview(uploader) {
			if (uploader.files && uploader.files[0]) {
				var path = window.URL.createObjectURL(uploader.files[0]);
				$('#profileImage').attr('src', path);
			}
		}

		$("#imageUpload").change(function() {
			fasterPreview(this);
			var path, url;
			url = this.value;
			url = url.split("\\");
			path = "./image/" + url[2];
			document.getElementById('ImagePathId').value = path;
			document.getElementById("formId").submit();
		});
	</script>
</body>
</html>