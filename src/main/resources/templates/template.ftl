<html>
<head>
  <title>Static code analysis and Dependency vulnerability report</title>
  <style>
  	.severity-high {
  		background-color: Red;
  		color: white;
  	}
  	.severity-medium {
  		background-color: Orange;
  		color: black;
  	}
  	.severity-low {
  		background-color: Yellow;
  		color: black;
  	}
  	table {
  		border: 1px solid black;
  		border-collapse: collapse;
	}
	
	th, td {
		border: 1px solid black;
	}
  </style>
</head>
<body>
	<h1>Static Code Analysis & Dependency Vulnerability Scan Report</h1>
	<br>
	<table style="border=1px;">
		<tr>
			<th>Source</th>
			<th>Type</th>
			<th>Severity</th>
			<th>File</th>
			<th>Message</th>
		</tr>
		<#list vulnerabilities as vuln>
		<tr>
		    <td>${vuln.source}</td>
		    <td>${vuln.type}</td>
		    <td class= "
	            <#if vuln.severity == 'HIGH'>
	                severity-high
	            <#elseif vuln.severity == 'MEDIUM'>
	                severity-medium
	            <#else>
	                severity-low
	            </#if>
		    ">
		    	${vuln.severity}
		    </td>
			<td>${vuln.file}</td>
			<td>${vuln.message}</td>
		</tr>
		</#list>
	</table>
</body>
</html>