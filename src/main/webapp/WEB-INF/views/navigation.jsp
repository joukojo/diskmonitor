<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Diskmonitor</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href='<c:url value="/dailystatistics.htm" />'>Daily statistics</a></li>
				<li><a href='<c:url value="/hourlystatistics.htm" />' > Hourly statistics</a></li>
				<li><a href='<c:url value="/statistics.htm" />'>Statistics</a></li>
				<li><a href="#">Help</a></li>
			</ul>
		</div>
	</div>
</div>