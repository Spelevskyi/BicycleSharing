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
<%@include file="/css/error.css"%>
</style>

<c:set var="previous_path" value="/jsp/error/error404.jsp" scope="session" />
<c:set var="language" value="${lang}" />
<fmt:setLocale value="${language}" />
<fmt:message bundle="${local}" key="error.request" var="request" />
<fmt:message bundle="${local}" key="error.status" var="status" />
<fmt:message bundle="${local}" key="error.servlet" var="servlet" />
<fmt:message bundle="${local}" key="error.exception" var="exception" />
<fmt:message bundle="${local}" key="error.404.title" var="title" />
<title>${title}</title>

</head>
<body>
	<jsp:include page="/jsp/error/error_navbar.jsp"/> 
    <div>
        <h3>${request}: ${pageContext.errorData.requestURI}</h3>
        <br/>
        <h3>${servlet}: ${pageContext.errorData.servletName}</h3>
        <br/>
        <h3>${status}: ${pageContext.errorData.statusCode}</h3>
        <br/>
        <h3>${exception}: ${pageContext.exception.message}</h3>
	</div>
</body>
</html>