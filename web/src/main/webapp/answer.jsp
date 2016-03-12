<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FDS</title>
<link rel="stylesheet" href="../assets/bootstrap.min.css">
<%= request.getAttribute("js").toString() %>
</head>
<body>
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a href="../" class="navbar-brand">FDS</a>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="col-lg-12">
			<div class="page-header" id="banner">
				<div class="row">
					<div class="col-lg-8 col-md-7 col-sm-6">
						<h1>Question - Easy</h1>
						<p class="lead">Solve this questions:</p>
					</div>
				</div>
			</div>
			<div class="bs-component">
				<table class="table table-striped table-hover ">
					<thead>
						<tr>
							<th>#</th>
							<th>Question</th>
							<th>Solution</th>
						</tr>
					</thead>
					<tbody>
						<%= request.getAttribute("questions").toString() %>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>