<link
	href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<div class="navbar">
	<c:url var="homeUrl" value="/project.do"/>
	<div class="navbar-inner">
		<div class="brand">Tracking system</div>
		<ul class="nav">
		${homeUrl}
			<li><a href="${homeUrl}">TEST</a></li>
			<li><a href="<c:url value="/project.do" />">Projects</a></li>
		</ul>
	</div>
</div>
