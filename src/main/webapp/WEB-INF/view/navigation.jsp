<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Manish </a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
     <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Student
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="/admin/student/add">Add</a></li>
          <li><a href="/admin/students">List</a></li>
        </ul>
        
      </li>
      
     <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Lecturer
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="/admin/lecturer/add">Add</a></li>
          <li><a href="/admin/lecturers">List</a></li>
        </ul>
        
      </li>
    
     <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Courses
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="/admin/course/add">Add</a></li>
          <li><a href="/admin/courses">List</a></li>
        </ul>
        
      </li>
	 <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Enrollment
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="/admin/enrollment/add">Add</a></li>
          <li><a href="/admin/enrollments">List</a></li>
        </ul>
        
      </li>
	
	<li><a href="/about-us">About US</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
    
<%if(session.getAttribute("username")==null || session.getAttribute("username").toString().length()==0){ %>
	  <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
 <%}else{ %>
      <li><a href="#"><%=session.getAttribute("username") %></a></li>
      <li><a href="#"><%=session.getAttribute("role") %></a></li>
  <%} %>
    </ul>
  </div>
</nav>