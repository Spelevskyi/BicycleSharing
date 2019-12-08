<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<style>
<%@include file="/css/main.css"%>
</style>
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
										<img src="${pageContext.session.servletContext.contextPath}/image/england.png" />
									</button>
									<button type="submit" name="lang" value="ru" class="btn btn-default navbar-btn">
										<img src="${pageContext.session.servletContext.contextPath}/image/russia.png" />
									</button>
								</div>
							</li>
						</ul>
					</form>
				</li>
			</ul>
		</div>
	</nav>	
</body>
</html>