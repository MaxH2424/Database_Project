<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Search Comedian/Tag</title>
</head>
<body>

<form action="CommentSectionSubmit" method="post">
    <center>
        <h1>Comment Center</h1>
        <br>
    </center>
    <div align="center">
            <caption><h2>Leave a Comment!</h2></caption>
		  					<select id="selection" name="selection"> 
		  						<option value=""></option>
		        				<option value="excellent">Excellent</option>
		        				<option value="good">Good</option>
		        				<option value="fair">Fair</option>
		        				<option value="poor">Poor</option>
	       					</select>&nbsp;&nbsp;&nbsp;<button>Submit</button><br>	       											
	  							<center>
	    						<textarea rows="25" cols="100" id="comment" name="comment"></textarea>
	    						</center>	    											
    	</div> 

</form>
</body>
		
</html>
</body>
</html>