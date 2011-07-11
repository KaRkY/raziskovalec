<#ftl encoding="UTF-8">

<@layout.layout>

	<@layout.head>
		<title>Raziskovalec|Iskanje</title>
	</@layout.head>
	
	<@layout.body>
		<#assign postUrl>
			<@spring.url "/iskanje" />
		</#assign>
		<form action="${postUrl}" method="post">
			<input name="iskalniParameter" type="text" value="${iskalniParameter}" />
			<input name="submit" value="I&#353;&#269;i" type="submit" />
		</form>
	</@layout.body>
	
</@layout.layout>