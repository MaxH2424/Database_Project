<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<style>
/* Dropdown Button */
.dropbtn {
  background-color: #4CAF50;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
}

/* The container <div> - needed to position the dropdown content */
.dropdown {
  position: relative;
  display: inline-block;
}

/* Dropdown Content (Hidden by Default) */
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

/* Links inside the dropdown */
.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

/* Change color of dropdown links on hover */
.dropdown-content a:hover {background-color: #ddd;}

/* Show the dropdown menu on hover */
.dropdown:hover .dropdown-content {display: block;}

/* Change the background color of the dropdown button when the dropdown content is shown */
.dropdown:hover .dropbtn {background-color: #3e8e41;}
</style>
<meta charset="ISO-8859-1">
<title>Search Comedian/Tag</title>
</head>
<body>  
<form action="searchInterface" method="post" name="match" id="searchSubmit">   

    <center>
        <h1>Comment Center</h1>
        <br>
        <select id="selection">
        	<option value="comedian">Comedian</option>
        	<option value="tags">Tags</option>
        </select>
        &nbsp;
        <input type="text" id="userSearch" name="searchBar">&nbsp;
        <button>></button>
    </center>
    
     
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Leave a Comment!</h2></caption>
            <tr>
                <th>Video URL: </th>
                <th>Title: </th>
                <th>Description: </th>
                <th></th>
            </tr>
            <c:forEach var="listUTD" items="${listUTD}">
                <tr>
                    <td><c:out value="${listUTD.url}"/></td>
                    <td><c:out value="${listUTD.title}"/></td>
                    <td><c:out value="${listUTD.description}"/></td>
                    <td>
	                    <div class="dropdown">
	  						<button class="dropbtn">Comment</button>
	  						<div class="dropdown-content">
		  						<select id="selection">
		        					<option value="excellent">Excellent</option>
		        					<option value="good">Good</option>
		        					<option value="fair">Fair</option>
		        					<option value="poor">Poor</option>
	       						</select>
	  							<center>Comment!</center>
	    						<textarea rows="10" cols="20"></textarea>
	    						<button>Submit</button>
		  					</div>
						</div>  
                    </td>
				
                </tr>
            </c:forEach>
                      
        </table>
    </div>
    </form>
</form>
  
</body>
		
</html>