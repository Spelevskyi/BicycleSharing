<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="welcometag" uri="customtags" %>
<html>
<head>
<style>
<%@include file="/css/main.css"%>
</style>
<c:set var="language" value="${sessionScope.lang}" />
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
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><welcometag:hello role="${role}" name="${user.getFirstName()}"/>
				<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#"></a></li>
						<li><a href="#"></a></li>
						<li><a href="#"></a></li>
						<li><a href="#"></a></li>
						<li><a href="#"></a></li>
						<li><a href="#"></a></li>
						<li><a href="#"></a></li>
						<li><a href="#"></a></li>
						<c:choose>
							<c:when test="${user.isConfirmed() == false}">
								<li><a href="${pageContext.session.servletContext.contextPath}/controller?command=Confirm_page&email=${user.getEmail()}">${confirm}</a></li>
								<li><a href="${pageContext.session.servletContext.contextPath}/controller?command=Logout">${logout}</a></li>
							</c:when>
							<c:when test="${user.isConfirmed() == true}">	
								<li><a href="#"></a></li>
								<li><a href="#"></a></li>
								<li><a href="${pageContext.session.servletContext.contextPath}/controller?command=Logout">${logout}</a></li>
							</c:when>
						</c:choose>
					</ul></li>
			</ul>
		</div>
	</nav>
	<nav class="navbar navbar-inverse navbar-static-top navbar-lower">
		<c:choose>
			<c:when test="${user.isConfirmed() == true && user.getStatus() == 'LOCKED'}">
				<div class="container-fluid">
					<ul class="nav navbar-nav navbar-left">
						<li><a href="${pageContext.session.servletContext.contextPath}/controller?command=User_home_page"><i class="fa fa-fw fa-home"></i>${home}</a></li>
						<li><a href="${pageContext.session.servletContext.contextPath}/controller?command=User_account_page"><i class="fa fa-fw fa-user-circle"></i>${account}</a></li>
						<li><a href="${pageContext.session.servletContext.contextPath}/controller?command=User_points"><i class="fa fa-fw fa-map-marker"></i>${points}</a></li>
						<li><a href="${pageContext.session.servletContext.contextPath}/controller?command=User_billing_page"><i class="fa fa-fw fa-money"></i>${billing}</a></li>
						<li><a href="${pageContext.session.servletContext.contextPath}/controller?command=Favorites"><i class="fa fa-fw fa-heart"></i>${favorites}</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li>
							<form action="${pageContext.session.servletContext.contextPath}/controller" method="POST">
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
			</c:when>
			<c:when test="${user.isConfirmed() == true && user.getStatus() == 'UNLOCKED'}">
				<div class="container-fluid">
					<ul class="nav navbar-nav navbar-left">
						<li><a href="${pageContext.session.servletContext.contextPath}/controller?command=User_home_page"><i class="fa fa-fw fa-home"></i>${home}</a></li>
						<li><a href="#"><i class="fa fa-fw fa-user-circle"></i>${account}</a></li>
						<li><a href="#"><i class="fa fa-fw fa-map-marker"></i>${points}</a></li>
						<li><a href="${pageContext.session.servletContext.contextPath}/controller?command=User_billing_page"><i class="fa fa-fw fa-money"></i>${billing}</a></li>
						<li><a href="#"><i class="fa fa-fw fa-heart"></i>${favorites}</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li>
							<form action="${pageContext.session.servletContext.contextPath}/controller" method="POST">
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
			</c:when>
			<c:when test="${user.isConfirmed() == false && (user.getStatus() == 'UNLOCKED' || user.getStatus() == 'LOCKED')}">
				<div class="container-fluid">
					<ul class="nav navbar-nav navbar-left"  style="z-index: 1">
						<li><a class="active" href="#"><i class="fa fa-fw fa-home"></i>${home}</a></li>
						<li><a href="#"><i class="fa fa-fw fa-user-circle"></i>${account}</a></li>
						<li><a href="#"><i class="fa fa-fw fa-map-marker"></i>${points}</a></li>
						<li><a href="#"><i class="fa fa-fw fa-money"></i>${billing}</a></li>
						<li><a href="#"><i class="fa fa-fw fa-heart"></i>${favorites}</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li>
							<form action="#" method="POST">
								<input type="hidden" value="Change_language" name="command">
								<ul>
									<li>
										<div class="btn-group btn-group-sm divLang" id="center" role="group" aria-label="...">
											<button type="submit" name="lang" value="en" class="btn btn-default navbar-btn">
												<img src="../../image/england.png" />
											</button>
											<button type="submit" name="lang" value="ru" class="btn btn-default navbar-btn">
												<img src="../../image/russia.png" />
											</button>
										</div>
									</li>
								</ul>
							</form>
						</li>
					</ul>
				</div>
			</c:when>
		</c:choose>
	</nav>
	</body>
</html>