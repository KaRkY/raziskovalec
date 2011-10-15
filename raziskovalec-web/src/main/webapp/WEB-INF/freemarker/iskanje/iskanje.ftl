<@layout.layout>

	<@layout.head>
		<title>Raziskovalec | Iskanje</title>
	</@layout.head>
	
	<@layout.body "Iskanje">
		<#assign postUrl>
			<@spring.url "/iskanje" />
		</#assign>
		<div class="gradient">
			<div class="search">
				<form action="${postUrl}" method="post">
					<input name="iskalniParameter" type="text" value="${iskalniParameter}" />
					<input name="submit" value="Išči" type="submit" />
				</form>
			</div>
		</div>
	</@layout.body>
	
</@layout.layout>