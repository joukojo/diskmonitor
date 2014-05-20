<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">


</head>
<body>

	<div class="container">
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
	<!-- Latest compiled and minified JavaScript -->
	<script
		src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

</body>
</html>