<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href='<c:url value="/css/bootstrap.min.css" />' />

<!-- Optional theme -->
<link rel="stylesheet"
	href='<c:url value="/css/bootstrap-theme.min.css" />' />
<link rel="stylesheet" href='<c:url value="/css/style.css" />'>

</head>
<body>
	<jsp:include page="navigation.jsp" />


	<div class="container-fluid">
		<h1>Statistics</h1>

		<p class="lead">Viewing date ${param.date}</p>

		<table class="table">
			<tr>
				<th>Executed</th>
				<th>Execution time</th>
			</tr>
			<c:forEach items="${events}" var="event">

				<tr>
					<td><c:out value="${event.formattedCreated}" /></td>
					<td><c:out value="${event.executionTime}" /> msecs</td>
				</tr>
			</c:forEach>

		</table>
	</div>
	
	<script
		src='<c:url value="/js/bootstrap.min.js" />'></script>

</body>
</html>