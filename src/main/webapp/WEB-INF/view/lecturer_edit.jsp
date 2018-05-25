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

	<h2 class="text-center">Lecturer  EDIT</h2>


	<div class='col-lg-8 col-md-offset-2'>
		<form class="form-horizontal" action="/admin/lecturer/edit/${sessionScope.lecturer.lid }" method="post">
			<div class="form-group">
				<label class="control-label col-sm-2" for="gname">Gname</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="gname" name="gname" value="${sessionScope.lecturer.gname  }"
						placeholder="Enter GNAME">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="surname">Surname</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="surname" name="surname" value="${sessionScope.lecturer.surname  }"
						placeholder="Enter surname">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Email</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="email" name="email" value="${sessionScope.lecturer.email  }"
						placeholder="Enter email">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="major">Campus</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="campus" name="campus" value="${sessionScope.lecturer.campus  }"
						placeholder="Enter Campus">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="password">Password</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="password" name="password" value="${sessionScope.lecturer.password  }"
						placeholder="Enter Password">
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