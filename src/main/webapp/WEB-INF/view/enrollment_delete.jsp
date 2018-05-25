<!DOCTYPE html>
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/bootstrap.min.css" type="text/css">
<%@ page isELIgnored="false"%>
<%@ page session="true"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>
<body>

	<%@include file="navigation.jsp"%>

	<h2 class="text-center">Enrollment ADD</h2>


	<div class='col-lg-8 col-md-offset-2'>
		<form class="form-horizontal" action="/admin/enrollment/delete/${sessionScope.enrollment.eid}"
			method="post">
			<div class="form-group">
				<label class="control-label col-sm-2" for="student">Student
					:</label>
				<div class="col-sm-10">
					<select class="form-control" id="sid" name="sid">
						<c:forEach var="student" items="${studentList}">
							<option value="${student.sid}" ${student.sid == sessionScope.enrollment.sid ? 'selected="selected"' : ''}>${student.fname} ${student.surname}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="course">Course:</label>
				<div class="col-sm-10">
					<select class="form-control" id="cid" name="cid">
						<c:forEach var="course" items="${sessionScope.courseList}">
							<option value="${course.cid}" ${course.cid == sessionScope.enrollment.cid ? 'selected="selected"' : ''}>${course.title}</option>
						</c:forEach>
					</select>

				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="semester">Semester:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="semester"
						name="semester" placeholder="Enter semester" value="${sessionScope.enrollment.semester }" />
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