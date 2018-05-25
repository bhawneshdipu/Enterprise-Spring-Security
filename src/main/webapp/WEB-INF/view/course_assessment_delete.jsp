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

	<h2 class="text-center">Course Assessment Delete</h2>


	<div class='col-lg-8 col-md-offset-2'>
		<form class="form-horizontal" action="/admin/course_assessment/delete/${ sessionScope.course_assessment.caid}"
			method="post">
			<div class="form-group">
				<label class="control-label col-sm-2" for="cid">Course
					:</label>
				<div class="col-sm-10">
					<select class="form-control" id="cid" name="cid">
						<c:forEach var="course" items="${courseList}">
							<option value="${course.cid}" ${course.cid == sessionScope.course_assessment.cid ? 'selected="selected"' : ''}>${course.title}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="assessment">Assessment:</label>
				<div class="col-sm-10">
					<select class="form-control" id="aid" name="aid">
						<c:forEach var="assessment" items="${assessmentList}">
							<option value="${assessment.aid}" ${assessment.aid == sessionScope.course_assessment.aid ? 'selected="selected"' : ''}>${assessment.a1} ${assessment.a2}</option>
						</c:forEach>
					</select>

				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="semester">Semester:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="semester" name="semester" placeholder="Enter semester"  value="${sessionScope.course_assessment.semester }"/>
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
			<p class="text-muted text-center">Manish � 2018</p>
		</div>
	</footer>

	<script type="text/javascript" src="/js/jquery.min.js"></script>
	<script type="text/javascript" src="/js/bootstrap.min.js"></script>
</html>