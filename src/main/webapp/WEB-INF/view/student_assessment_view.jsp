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

	<h2 class="text-center">Student Assessment View</h2>


	<div class='col-lg-8 col-md-offset-2'>
			<div class="form-group">
				<label class="control-label col-sm-2" for="enrollment">Enrollment:</label>
				<div class="col-sm-10">
					<select class="form-control" id="eid" name="eid">
						<c:forEach var="enrollment" items="${sessionScope.enrollmentList}">
							<option value="${enrollment.eid}"
								${enrollment.eid == sessionScope.assessment.eid ? 'selected="selected"' : ''}>${enrollment.semester}</option>
						</c:forEach>
					</select>

				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="a1">A1:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="a1" name="a1"
						placeholder="Enter A1" value="${sessionScope.assessment.a1}" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="a2">A2:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="a2" name="a2"
						placeholder="Enter A2" value="${sessionScope.assessment.a2}" />
				</div>
			</div>

	</div>

	<footer class="col-md-12">
		<div class="container">
			<p class="text-muted text-center">Manish � 2018</p>
		</div>
	</footer>

	<script type="text/javascript" src="/js/jquery.min.js"></script>
	<script type="text/javascript" src="/js/bootstrap.min.js"></script>
</html>