<@layout.layout>

	<@layout.head>
		<title>Raziskovalec|Domov</title>
	</@layout.head>
	
	<@layout.body>
		<#assign iskanjeUrl>
			<@spring.url "/iskanje" />
		</#assign>
		<a href="${iskanjeUrl}">Iskanje</a>
	</@layout.body>
	
</@layout.layout>