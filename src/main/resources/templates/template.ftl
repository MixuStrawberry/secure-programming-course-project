<html>
<head>
  <title>Static code analysis and Dependency vulnerability report</title>
</head>
<body>
	<table border="1">
		<tr><th>Source</th><th>Type</th><th>Severity</th><th>File</th><th>Message</th></tr>
		<#list vulnerabilities as vuln>
		<tr>
		    <td>${vuln.source}</td>
		    <td>${vuln.type}</td>
		    <td>${vuln.severity}</td>
			<td>${vuln.file}</td>
			<td>${vuln.message}</td>
		</tr>
		</#list>
	</table>
</body>
</html>