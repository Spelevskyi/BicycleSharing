<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<style>
<%@include file="/css/confirm.css"%>
</style>
<c:set var="previous_path" value="/jsp/confirm_navbar.jsp" scope="session"/>
<c:set var="language" value="${sessionScope.lang}" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="properties.local" var="local" />
<fmt:message bundle="${local}" key="index.home" var="home" />
<fmt:message bundle="${local}" key="index.email" var="email" />
<fmt:message bundle="${local}" key="index.enter.email" var="enter_email" />
<fmt:message bundle="${local}" key="index.firstname" var="firstname" />
<fmt:message bundle="${local}" key="index.lastname" var="lastname" />
<fmt:message bundle="${local}" key="index.enter.firstname" var="enter_firstname" />
<fmt:message bundle="${local}" key="index.number" var="number" />
<fmt:message bundle="${local}" key="index.enter.number" var="enter_number" />
<fmt:message bundle="${local}" key="index.enter.lastname" var="enter_lastname" />
<fmt:message bundle="${local}" key="index.not.member" var="not_member" />
<fmt:message bundle="${local}" key="index.forgot.password" var="forgot_password" />
<fmt:message bundle="${local}" key="index.signup.here" var="signup_here" />
<fmt:message bundle="${local}" key="index.signin.here" var="signin_here" />
<fmt:message bundle="${local}" key="index.login" var="login" />
<fmt:message bundle="${local}" key="index.register" var="register" />
<fmt:message bundle="${local}" key="index.enter.password" var="enter_password" />
<fmt:message bundle="${local}" key="index.password" var="password" />
<fmt:message bundle="${local}" key="index.contact" var="contact" />
<fmt:message bundle="${local}" key="index.about" var="about" />
<fmt:message bundle="${local}" key="index.search" var="search" />
<fmt:message bundle="${local}" key="index.signup" var="sign_up" />
<fmt:message bundle="${local}" key="index.signin" var="sign_in" />
<fmt:message bundle="${local}" key="index.news" var="news" />
<fmt:message bundle="${local}" key="index.ru" var="ru" />
<fmt:message bundle="${local}" key="index.en" var="en" />
</head>
<body>
	<nav class="navbar-inverse nav-upper">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Bicycle Sharing</a>
			</div>
			<ul class="nav navbar-nav">
				<li><input type="text" placeholder="${search}.."></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#login" data-toggle="modal" data-target="#login"><i class="fa fa-fw fa-user"></i>${sign_in}</a></li>
				<li><a href="#registration" data-toggle="modal" data-target="#registration"><i class="fa fa-fw fa-user-plus"></i>${sign_up}</a></li>
			</ul>
		</div>
	</nav>
	<nav class="navbar navbar-inverse navbar-static-top navbar-lower">
		<div class="container-fluid">
			<ul class="nav navbar-nav navbar-left">
				<li><a class="active" href="index.jsp"><i class="fa fa-fw fa-home"></i>${home}</a></li>
				<li><a href="index.jsp"><i class="fa fa-fw fa-phone"></i>${contact}</a></li>
				<li><a href="index.jsp"><i class="fa fa-fw fa-envelope"></i>${news}</a></li>
				<li><a href="index.jsp"><i class="fa fa-fw fa-book"></i>${about}</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li>
					<form action="controller" method="POST">
						<input type="hidden" value="Change_language" name="command">
						<ul>
							<li>
								<div class="btn-group btn-group-sm divLang" id="center" role="group" aria-label="...">
									<button type="submit" name="lang" value="en" class="btn btn-default navbar-btn">
										<img src="../image/england.png" />
									</button>
									<button type="submit" name="lang" value="ru" class="btn btn-default navbar-btn">
										<img src="../image/russia.png" />
									</button>
								</div>
							</li>
						</ul>
					</form>
				</li>
			</ul>
		</div>
	</nav>
	<div class="modal fade login" id="login" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" style="padding: 35px 50px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>
						<span class="glyphicon glyphicon-lock"></span>${sign_in}</h4>
				</div>
				<div class="modal-body" style="padding: 40px 50px;">
					<form name="form" method="POST" action="controller">
						<input type="hidden" name="command" value="Login" />
						<div class="form-group">
							<label for="email"><span class="glyphicon glyphicon-user"></span>${email}</label> <input type="text" name="email" value="" class="form-control"
								placeholder="${enter_email}" />
						</div>
						<div class="form-group">
							<label for="psw"><span class="glyphicon glyphicon-eye-open"></span>${password}</label> <input type="password" name="password" value="" class="form-control"
								placeholder="${enter_password}" />
						</div>
						<button type="submit" class="btn btn-success btn-block">
							<span class="glyphicon glyphicon-off"></span>${login}
						</button>
						<p>
							<a href="#">${forgot_password} </a>
						</p>
					</form>
				</div>
				<div class="modal-footer" style="padding: 40px 50px;">
					<p>${not_member}
						<a href="#registration" data-toggle="modal" data-target="#registration">${signup_here}</a>
					</p>
				</div>
			</div>

		</div>
	</div>
	<div class="modal fade" id="registration" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" style="padding: 35px 50px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>
						<span class="glyphicon glyphicon-lock"></span>${sign_up}</h4>
				</div>
				<div class="modal-body" style="padding: 40px 50px;">
					<form name="form" method="POST" action="controller">
						<input type="hidden" name="command" value="Register" />
						<div class="form-group">
							<label for="First name"><span class="glyphicon glyphicon-user"></span>${firstname}</label> <input type="text" name="firstName" value="" class="form-control"
								placeholder="${enter_firstname}" />
						</div>
						<div class="form-group">
							<label for="Last name"><span class="glyphicon glyphicon-user"></span>${lastname}</label> <input type="text" name="lastName" value="" class="form-control"
								placeholder="${enter_lastname}" />
						</div>
						<div class="form-group">
							<label for="Email"><span class="glyphicon glyphicon-eye-open"></span>${email}</label> <input type="text" name="email" value="" class="form-control"
								placeholder="${enter_email}" />
						</div>
						<div class="form-group">
							<label for="Password"><span class="glyphicon glyphicon-eye-open"></span>${password}</label> <input type="password" name="password" value="" class="form-control"
								placeholder="${enter_password}" />
						</div>
						<div class="form-group">
							<label for="Phone number"><span class="glyphicon glyphicon-eye-open"></span>${number}</label> <input type="text" name="phoneNumber" value="" class="form-control"
								placeholder="${enter_number}" />
						</div>
						<button type="submit" class="btn btn-success btn-block">
							<span class="glyphicon glyphicon-off"></span>${register}
						</button>
						<div class="form-group">
							<a href="#">${forgot_password}</a>
						</div>
					</form>
				</div>
				<div class="modal-footer" style="padding: 40px 50px;">
					<p>${not_member}<a href="#login" data-toggle="modal" data-target="#login">${signin_here}</a>
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>