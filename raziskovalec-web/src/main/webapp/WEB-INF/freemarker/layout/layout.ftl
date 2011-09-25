<#ftl encoding="UTF-8">

<#macro fragment name>
	<#local frag>
		<#nested>
	</#local>
	<#assign fragments = fragments + {name:frag}>
</#macro>

<#macro layout>
	<#assign fragments={}>
	<#local empty>
		<#nested>
	</#local>
	<#compress>
		<!doctype html>
		<html>
			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
				
				<#local html>
					<@spring.url "/styles/html.css" />
				</#local>
				<link href="${html}" type="text/css" rel="stylesheet" />
				<#local layout>
					<@spring.url "/styles/layout.css" />
				</#local>
				<link href="${layout}" type="text/css" rel="stylesheet" />
				
				<script type="text/javascript"
				src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js" ></script>
				
				<#local script>
					<@spring.url "/script/script.js" />
				</#local>
				<script type="text/javascript" src="${script}"></script>

				<#if fragments["head"]??>
					${fragments["head"]?trim}
				</#if>
			</head>
			<body>
				<!-- CONTENT: Holds all site content except for the footer.  This is what causes the footer to stick to the bottom -->
				<div id="content">	
					<!-- HEADER: Holds title, subtitle and header images -->
					<div id="header">
					
						<div id="title">
						  <h1>Raziskovalec.org</h1>
						  <h2>in the city</h2>
						</div>
						
						<img src="<@spring.url "/images/bg/balloons.gif" />" alt="balloons" class="balloons" />
						<img src="<@spring.url "/images/bg/header_left.jpg" />" alt="left slice" class="left" />
						<img src="<@spring.url "/images/bg/header_right.jpg" />" alt="right slice" class="right" />
	
					</div>
					
					
					
					<!-- MAIN MENU: Top horizontal menu of the site.  Use class="here" to turn the current page tab on -->
					<div id="mainMenu">
						<ul class="floatRight">
						
							<li><a href="<@spring.url "/" />" title="Home" <#if menuName="Home">class="here"</#if> >Home</a></li>
							<li><a href="<@spring.url "/iskanje" />" title="Iskanje" <#if menuName="Iskanje">class="here"</#if>>Iskanje</a></li>
							<li><a href="tags.html" title="View the styled tags">Tags</a></li>
							<li><a href="print.html" title="View the print layout">Print</a></li>
							<li><a href="http://fullahead.org/contact.html" title="Get in touch" class="last">Mail</a></li>
						</ul>
					</div>
				
				
				
				
					<!-- PAGE CONTENT BEGINS: This is where you would define the columns (number, width and alignment) -->
					<div id="page">
					
					
						<!-- 25 percent width column, aligned to the left -->
						<div class="width25 floatLeft leftColumn">
						
							<h1>Intro</h1>
						
						</div>
					
					
					
					
					    <!-- 75 percent width column, aligned to the right -->
					    <div class="width75 floatRight">
					    	<#if fragments["body"]??>
								${fragments["body"]?trim}
							</#if>
						</div>
				</div>
				
				
				<!-- FOOTER: Site footer for links, copyright, etc. -->
				<div id="footer">
				
				  <div id="width">
				    <span class="floatLeft">
				      <a href="http://www.raziskovalec.org">Raziskovalec.org</a>
				    </span>
				
				    <span class="floatRight">
				      <a href="index.html" title="Introduction">intro</a> <span class="grey">|</span>
				      <a href="help.html" title="Learn how to use the template">help</a> <span class="grey">|</span>
				      <a href="tags.html" title="View the styled tags">tags</a> <span class="grey">|</span>
				      <a href="print.html" title="View the print layout">print</a> <span class="grey">|</span>
				      <a href="http://fullahead.org/contact.html" title="Get in touch">mail</a>
				    </span>
				  </div>
				
				</div>
			</body>
		</html>
	</#compress>
</#macro>

<#macro head>
	<@fragment name="head">
		<#nested>
	</@fragment>
</#macro>

<#macro body menu>
    <#assign menuName=menu />
	<@fragment name="body">
		<#nested>
	</@fragment>
</#macro>