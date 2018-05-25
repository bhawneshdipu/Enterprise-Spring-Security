<!DOCTYPE html>
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/bootstrap.min.css" type="text/css">
<%@ page isELIgnored="false"%>
</head>
<body>

	<%@include file="navigation.jsp"%>

	<h2 class="text-center">Course ADD</h2>


	<div class='col-lg-8 col-md-offset-2'>
		<form class="form-horizontal" action="/admin/course/add" method="post">
			<div class="form-group">
				<label class="control-label col-sm-2" for="title">Title:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="title" name="title"
						placeholder="Enter Title">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="prerequisites">Prerequisites:</label>
				<div class="col-sm-10">
					<textarea  class="form-control" id="prerequisites" name="prerequisites"
						placeholder="Enter prerequisite"></textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Submit</button>
				</div>
			</div>
		</form>

	</div>

	<footer class="col-md-12">
		<div class="container">
			<p class="text-muted text-center">Manish © 2018</p>
		</div>
	</footer>

	<script type="text/javascript" src="/js/jquery.min.js"></script>
	<script type="text/javascript" src="/js/bootstrap.min.js"></script>
</html>