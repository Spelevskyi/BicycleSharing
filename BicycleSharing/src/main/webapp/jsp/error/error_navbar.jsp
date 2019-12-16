<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<style>
<%@include file="/css/error.css"%>
</style>
<c:set var="language" value="${sessionScope.lang}" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="properties.local" var="local" />
<fmt:message bundle="${local}" key="main.welcome" var="welcome" />
<fmt:message bundle="${local}" key="main.search" var="search" />

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
				<li><a href="index.jsp">${welcome}</a></li>
			</ul>
		</div>
	</nav>
	<nav class="navbar navbar-inverse navbar-static-top navbar-lower">
		<div class="btn-group btn-group-sm divLang" id="center" role="group" aria-label="...">
			<button type="submit" name="lang" value="en" class="btn btn-default navbar-btn">
				<img src="${pageContext.session.servletContext.contextPath}/image/england.png" />
			</button>
			<button type="submit" name="lang" value="ru" class="btn btn-default navbar-btn">
				<img src="${pageContext.session.servletContext.contextPath}/image/russia.png" />
			</button>
		</div>
	</nav>
</body>
</html>