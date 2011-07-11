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
				
				<#local style>
					<@spring.url "/styles/style.css" />
				</#local>
				<link href="${style}" type="text/css" rel="stylesheet" />
				
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
				<div id="content">
					<div id="header">
						<#if fragments["header"]??>
							${fragments["header"]?trim}
						</#if>
					</div> <!-- End header -->
					<div id="body">
						<#if fragments["body"]??>
							${fragments["body"]?trim}
						</#if>
					</div> <!-- End body -->
					<div id="footer">
						<#if fragments["footer"]??>
							${fragments["footer"]?trim}
						</#if>
					</div> <!-- End footer-->
				</div> <!--End content-->
			</body>
		</html>
	</#compress>
</#macro>

<#macro head>
	<@fragment name="head">
		<#nested>
	</@fragment>
</#macro>

<#macro body>
	<@fragment name="body">
		<#nested>
	</@fragment>
</#macro>