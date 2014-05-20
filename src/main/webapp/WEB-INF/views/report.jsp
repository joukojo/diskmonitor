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

		<h1>Execution statistics</h1>

		<table class="table">

			<tr>
				<th>Date</th>
				<th>Amount of executions</th>
				<th>Minimum</th>
				<th>Average</th>
				<th>Maximum</th>
			</tr>
			
			<c:forEach items="${statistics}" var="statistic">

				<tr>
					<td><c:out value="${statistic.field}" /></td>
					<td><c:out value="${statistic.count }" /></td>
					<td><c:out value="${statistic.mininum}" /></td>
					<td><c:out value="${statistic.average}" /></td>
					<td><c:out value="${statistic.maximum}" /></td>
				</tr>
			</c:forEach>
		</table>


	</div>

</body>
</html>