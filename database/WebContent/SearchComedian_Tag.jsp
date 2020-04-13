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
<form action="searchInterface" method="post" name="match" id="searchSubmit">   

    <center>
        <h1>Search a Comedian or Tags</h1>
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
            <caption><h2>List of Videos</h2></caption>
            <tr>
                <th>URL: </th>
            </tr>
            <c:forEach var="listVidCom" items="${listVidCom}">
                <tr>
                    <td><a href="${listVidCom.url}"><c:out value="${listVidCom.url}"/></a></td>
                </tr>
            </c:forEach>
         
        </table>
    </div>
</form>
  
</body>
		
</html>