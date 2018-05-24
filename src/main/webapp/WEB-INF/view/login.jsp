<!DOCTYPE html>
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css"
	type="text/css">
<%@ page isELIgnored="false" %>
</head>
<body>
	<div class="text-center">
		<img src="img/logo.jpg" alt="">
	</div>



	<div class='col-lg-12'>
		<div class="col-lg-3"></div>
		<div class="col-lg-5">
			<div id='alerttext'  class="alert col-sm-8 col-md-offset-4  " style="text-align: center;">
                <%
                    if(request.getParameter("msg")!=null){
                        out.print(request.getParameter("msg"));
                    }
                %>
            </div>
            <form class="form-horizontal" action="/login" method="POST" autocomplete="off" enctype="multipart/form-data">
                
                <div class="form-group">
                    <label class="control-label col-sm-4" for="group">Group:</label>
                    <div class="col-sm-8">
                    	<select class="form-control" name="group" id="group">
                    		<option value="" > Select</option>
                    		<option value="lecturer"> Lecturer</option>
                    		<option value="student">Student</option>
                    		<option value="administrator">Administrator</option>
                    		
                    	</select>
                    </div>
                </div>
                
                
                <div class="form-group">
                    <label class="control-label col-sm-4" for="username">Email:</label>
                    <div class="col-sm-8">
                        <input type="email" autocomplete="false" class="form-control" id="email" name="email" 
                            placeholder="Email " autofocus />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-4" for="password">Password:</label>
                    <div class="col-sm-8">
                        <input type="password" autocomplete="off" class="form-control" id="password" name="password" required="required" 
                            placeholder="Enter password"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-6 col-sm-6">
                        <button  type="submit" class="btn btn-default" id="login_btn" name="login_btn"
                            >Submit</button>
                    </div>
                </div>
            </form>
		</div>
	</div>

	<footer class="footer">
		<div class="container">
			<p class="text-muted text-center">Manish  © 2018</p>
		</div>
	</footer>
	
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</html>