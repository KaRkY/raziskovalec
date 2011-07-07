<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<c:url value="/styles/style.css" var="style" />
<link href="${style}" type="text/css" rel="stylesheet" />

<c:url value="/script/script.js" var="script" />

<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"
></script>

<script type="text/javascript" src="${script}"></script>

<title><tiles:getAsString name="page-name" /> - <tiles:getAsString
		name="title"
	/>
</title>
</head>
<body>
	<div id="main">
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>
		<div id="site_content">
			<div class="sidebar">sdfdsfdsf</div>
			<div id="content">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>