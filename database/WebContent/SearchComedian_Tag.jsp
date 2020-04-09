<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Comedian/Tag</title>
</head>
<body>  
<form action="listCom" method="post" name="match" id="searchSubmit">   

    <center>
        <h1>Search a Comedian or Tags</h1>
        <br>
        <select id="selection">
        	<option value="comedian">Comedian</option>
        	<option value="tags">Tags</option>
        </select>
        &nbsp;
        <input type="text" id="userSearch" name="searchBar">&nbsp;
        <button onclick="document.getElementById('searchSubmit').submit();">></button>
    </center>
    
     
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Videos</h2></caption>
            <tr>
                <th>Comedian: </th><text value="document.getElementById('userSearch')"></text>
            </tr>
            <c:forEach var="listVideos" items="${listVidCom}">
                <tr>
                    <td><c:out value="${listVidoes.URL}" /></td>
                    
                    <td>
                        <a href="edit?user=<c:out value='${users.user}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?user=<c:out value='${users.user}' />">Delete</a>            
                    </td>
                </tr>
            </c:forEach>            
        </table>
    </div>
</form>
  
</body>
		
</html>