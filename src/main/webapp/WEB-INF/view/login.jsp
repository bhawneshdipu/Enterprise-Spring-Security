<!DOCTYPE html>
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/bootstrap.min.css" type="text/css">
<%@ page isELIgnored="false"%>
<%@ page isELIgnored="false"%>
<%@ page session="true"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

</head>
<body>
	<div class="text-center">
		<h2>Please login</h2>

	</div>



	<div class='col-lg-12'>
		<div class="col-lg-3"></div>
		<div class="col-lg-5">
			<%if(request.getParameter("msg")!=null && request.getParameter("msg").toString().length()>0){ %>
			<div id='alerttext' class="alert alert-danger col-sm-8 col-md-offset-4  "
				style="text-align: center;">
				<%=request.getParameter("msg") %>
				
			<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
				<font color="red" style="text-align:center;"> Invalid email or password.</font>
			</c:if>
			</div>

			<%} %>


			<form class="form-horizontal" action="/login" method="POST"
				autocomplete="off" enctype="multipart/form-data">

				<div class="form-group">
					<label class="control-label col-sm-4" for="group">Group:</label>
					<div class="col-sm-8">
						<select class="form-control" name="group" id="group">
							<option value="">Select</option>
							<option value="lecturer">Lecturer</option>
							<option value="student">Student</option>
							<option value="admin">Admin</option>

						</select>
					</div>
				</div>


				<div class="form-group">
					<label class="control-label col-sm-4" for="username">Email:</label>
					<div class="col-sm-8">
						<input type="email" autocomplete="false" class="form-control"
							id="email" name="email" placeholder="Email " autofocus />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4" for="password">Password:</label>
					<div class="col-sm-8">
						<input type="password" autocomplete="off" class="form-control"
							id="password" name="password" required="required"
							placeholder="Enter password" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-6 col-sm-6">
						<button type="submit" class="btn btn-default" id="login_btn"
							name="login_btn">Submit</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<footer class="footer">
		<div class="container">
			<p class="text-muted text-center">Manish © 2018</p>
		</div>
	</footer>

	<script type="text/javascript" src="/js/jquery.min.js"></script>
	<script type="text/javascript" src="/js/bootstrap.min.js"></script>
</html>