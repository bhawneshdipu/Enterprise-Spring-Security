<!DOCTYPE html>
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/bootstrap.min.css"
	type="text/css">
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

</head>
<body>

<%@include file="navigation.jsp"%>
	
	<div class="text-center">
		<img src="img/logo.jpg" alt="">
	</div>



	<div class='col-lg-8 col-md-offset-2'>
		<table class="table table-responsive">
		<thead>
		    <tr>
                <td>Enrollment ID: </td>
                <td>SID:</td>
                <td>Course ID:</td>
                <td>Semester:</td>
                <td>Action:</td>  
            </tr>
		</thead>
		
		<tbody>
		<c:forEach items="${sessionScope.enrollmentList}" var="enrollment">
            <tr>
                <td><c:out value="${enrollment.eid}"/></td>
                <td><c:out value="${enrollment.sid}"/></td>
                <td><c:out value="${enrollment.cid}"/></td>
                <td><c:out value="${enrollment.semester}"/></td>
                
                <td>
	                <a class="btn btn-warning" href="/admin/enrollment/edit/${enrollment.eid }">Edit</a>
	                <a class="btn btn-danger" href="/admin/enrollment/delete/${enrollment.eid }">Delete</a>
	            </td>   
            </tr>
        </c:forEach>
		</tbody>
    </table>
	</div>

	<footer class="footer col-md-12">
		<div class="container">
			<p class="text-muted text-center">Manish  © 2018</p>
		</div>
	</footer>
	
	<script type="text/javascript" src="/js/jquery.min.js"></script>
	<script type="text/javascript" src="/js/bootstrap.min.js"></script>
</html>