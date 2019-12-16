<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
<%@include file="/css/main.css"%>
</style>

<c:set var="previous_path" value="controller?command=Confirm_user" scope="session" />
<c:set var="language" value="${sessionScope.lang}" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="properties.local" var="local" />
<fmt:message bundle="${local}" key="confirm.confirm" var="confirm" />
<fmt:message bundle="${local}" key="confirm.code" var="code" />
<fmt:message bundle="${local}" key="confirm.enter.confirm" var="enter_confirm" />
<fmt:message bundle="${local}" key="confirm.confirm.error" var="confirm_error" />
<fmt:message bundle="${local}" key="confirm.title" var="title" />
<title>${title}</title>
</head>
<body>
	<jsp:include page="/jsp/user/user_navbar.jsp" />
	<div class="modal fade" id="confirmUser" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" style="padding: 35px 50px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>
						<span class="glyphicon glyphicon-lock"></span>${confirm}</h4>
				</div>
				<div class="modal-body" style="padding: 40px 50px;">
					<form name="form" method="POST" action="${pageContext.session.servletContext.contextPath}/controller">
						<input type="hidden" name="command" value="Confirm_user" />
						<div class="form-group">
							<label for="code">${code}</label> <input type="text" name="code" value="" class="form-control" placeholder="${enter_confirm}" pattern="^[0-9]{1,5}$" required />
							<span class="form_error">${confirm_error}</span>
						</div>
						<button type="submit" class="btn btn-success btn-block">
							<span class="glyphicon glyphicon-off"></span>${confirm}
						</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
	<%@include file="/js/confirm.js"%>
	</script>
</body>
</html>