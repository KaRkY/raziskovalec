<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="logo">
	<div id="logo_text">
		<h1>
			<a href="index.html">Projekt<span class="logo_colour">Raziskovalec</span>
			</a>
		</h1>
		<h2>
			<tiles:getAsString name="title" />
		</h2>
	</div>
</div>

<c:set var="menuSelect">
	<tiles:getAsString name="menu" />
</c:set>

<div id="menubar">
	<ul id="menu">
		<li class="${menuSelect eq 'home' ? 'selected' : ''}"><a
			href='<c:url value="/" />'
		>Domov </a></li>
		<li class="${menuSelect eq 'persons' ? 'selected' : ''}"><a
			href='<c:url value="/persons" />'
		>Test</a></li>
		<li><a href="page.html">A Page</a></li>
		<li><a href="another_page.html">Another Page</a></li>
		<li><a href="contact.html">Contact Us</a></li>
	</ul>
</div>