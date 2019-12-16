<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<%@include file="/css/index.css"%>
</style>
<c:set var="previous_path" value="controller?command=Home_page" scope="session"/>
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
<fmt:message bundle="${local}" key="index.title" var="title" />
<fmt:message bundle="${local}" key="index.email.error" var="email_error" />
<fmt:message bundle="${local}" key="index.password.error" var="password_error" />
<fmt:message bundle="${local}" key="index.firstname.error" var="firstname_error" />
<fmt:message bundle="${local}" key="index.lastname.error" var="lastname_error" />
<fmt:message bundle="${local}" key="index.number.error" var="number_error" />
<fmt:message bundle="${local}" key="index.change" var="change" />
<fmt:message bundle="${local}" key="index.change.password" var="change_password" />
<fmt:message bundle="${local}" key="index.member" var="member" />
<fmt:message bundle="${local}" key="index.ru" var="ru" />
<fmt:message bundle="${local}" key="index.en" var="en" />
<title>${title}</title>
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
										<img src="image/england.png" />
									</button>
									<button type="submit" name="lang" value="ru" class="btn btn-default navbar-btn">
										<img src="image/russia.png" />
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
					<h4><span class="glyphicon glyphicon-lock"></span>${sign_in}</h4>
				</div>
				<div class="modal-body" style="padding: 40px 50px;">
					<form name="form" method="POST" action="controller">
						<input type="hidden" name="command" value="Login" />
						<div class="form-group">
							<label for="email"><span class="glyphicon glyphicon-user"></span>${email}</label> 
							<input type="text" name="email" value="" class="form-control" placeholder="${enter_email}" pattern ="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?" required/>
							<span class="form_error">${email_error}</span>
						</div>
						<div class="form-group">
							<label for="psw"><span class="glyphicon glyphicon-eye-open"></span>${password}</label> 
							<input type="password" name="password" value="" class="form-control" placeholder="${enter_password}" pattern="^[0-9a-zA-Z]{4,20}$" required />
							<span class="form_error">${password_error}</span>
						</div>
						<button type="submit" class="btn btn-success btn-block">
							<span class="glyphicon glyphicon-off"></span>${login}
						</button>
						<div class="forgot text-center">
						<p>
							<a href="#" data-toggle="modal" data-target="#">${forgot_password} </a>
						</p>
						</div>
					</form>
				</div>
				<div class="modal-footer" style="padding: 20px 60px;">
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
							<label for="First name"><span class="glyphicon glyphicon-user"></span>${firstname}</label> 
							<input type="text" name="firstName" value="" class="form-control" placeholder="${enter_firstname}" pattern="^[A-Z][a-zA-Z]{2,20}$" required />
							<span class="form_error">${firstname_error}</span>
						</div>
						<div class="form-group">
							<label for="Last name"><span class="glyphicon glyphicon-user"></span>${lastname}</label> 
							<input type="text" name="lastName" value="" class="form-control" placeholder="${enter_lastname}" pattern="^[A-Z][a-zA-Z]{5,20}$" required/>
							<span class="form_error">${lastname_error}</span>
						</div>
						<div class="form-group">
							<label for="Email"><span class="glyphicon glyphicon-envelope"></span>${email}</label>
							<input type="text" name="email" value="" class="form-control"placeholder="${enter_email}" pattern="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?" required />
							<span class="form_error">${email_error}</span>
						</div>
						<div class="form-group">
							<label for="Password"><span class="glyphicon glyphicon-eye-open"></span>${password}</label> 
							<input type="password" name="first_password" value="" class="form-control" placeholder="${enter_password}" pattern="^[0-9a-zA-Z]{4,20}$" required/>
							<span class="form_error">${password_error}</span>
						</div>
						<div class="form-group">
							<label for="Password"><span class="glyphicon glyphicon-eye-open"></span>${password}</label> 
							<input type="password" name="second_password" value="" class="form-control" placeholder="${enter_password}" pattern="^[0-9a-zA-Z]{4,20}$" required/>
							<span class="form_error">${password_error}</span>
						</div>
						<div class="form-group">
							<label for="Phone number"><span class="glyphicon glyphicon-phone-alt"></span>${number}</label> 
							<input type="text" name="phoneNumber" value="" class="form-control" placeholder="${enter_number}" pattern="(\+375|80) (29|25|44|33) (\d{3})-(\d{2})-(\d{2})$" required/>
							<span class="form_error">${number_error}</span>
						</div>
						<button type="submit" class="btn btn-success btn-block">
							<span class="glyphicon glyphicon-off"></span>${register}
						</button>
						<div class="forgot text-center">
						<p>
							<a href="#" data-toggle="modal" data-target="#">${forgot_password} </a>
						</p>
						</div>
					</form>
				</div>
				<div class="modal-footer" style="padding: 20px 60px;">
					<p>${member}
						<a href="#login" data-toggle="modal" data-target="#login">${signin_here}</a>
					</p>
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
						<span class="glyphicon glyphicon-eye-open"></span>${change_password}</h4>
				</div>
				<div class="modal-body" style="padding: 40px 50px;">
					<form name="form" method="POST" action="controller">
						<input type="hidden" name="command" value="Forgot_password" />
						<div class="form-group">
							<label for="Email"><span class="glyphicon glyphicon-envelope"></span>${email}</label>
							<input type="text" name="email" value="" class="form-control"placeholder="${enter_email}" pattern="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?" required />
							<span class="form_error">${email_error}</span>
						</div>
						<div class="form-group">
							<label for="Password"><span class="glyphicon glyphicon-eye-open"></span>${password}</label> 
							<input type="password" name="first_password" value="" class="form-control" placeholder="${enter_password}" pattern="^[0-9a-zA-Z]{4,20}$" required/>
							<span class="form_error">${password_error}</span>
						</div>
						<div class="form-group">
							<label for="Password"><span class="glyphicon glyphicon-eye-open"></span>${password}</label> 
							<input type="password" name="second_password" value="" class="form-control" placeholder="${enter_password}" pattern="^[0-9a-zA-Z]{4,20}$" required/>
							<span class="form_error">${password_error}</span>
						</div>
						<button type="submit" class="btn btn-success btn-block">
							<span class="glyphicon glyphicon-off"></span>${change}
						</button>
					</form>
				</div>
				<div class="modal-footer" style="padding: 20px 60px;">
					<p>${member}
						<a href="#login" data-toggle="modal" data-target="#login">${signin_here}</a>
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>