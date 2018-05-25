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

	<h2 class="text-center">Student  EDIT</h2>


	<div class='col-lg-8 col-md-offset-2'>
		<form class="form-horizontal" action="/admin/student/edit/${sessionScope.student.sid }" method="post">
			<div class="form-group">
				<label class="control-label col-sm-2" for="fname">Fname</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="fname" name="fname" value="${sessionScope.student.fname  }"
						placeholder="Enter FNAME">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="surname">Surname</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="surname" name="surname" value="${sessionScope.student.surname  }"
						placeholder="Enter surname">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Email</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="email" name="email" value="${sessionScope.student.email  }"
						placeholder="Enter email">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="major">Major</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="major" name="major" value="${sessionScope.student.major  }"
						placeholder="Enter Major">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="password">Password</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="password" name="password" value="${sessionScope.student.password  }"
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