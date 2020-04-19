
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>List of Users</title>
</head>
<body>

<%-- 

<%
if(request.getParameter("listPeople") == null) { // we want to make sure that we already have all the people
	PeopleDAO peopleDAO = new PeopleDAO();        // listed in attribute 'listPeople'
	List<Users> listPeople = peopleDAO.listAllPeople();
	request.setAttribute("listPeople", listPeople);       
}
%>

--%>


    <center>
        <h1>User Registration/Login</h1>
        <h2>
            <a href="new">Register</a>
            &nbsp;&nbsp;&nbsp;
            <a href="login">Login</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of People</h2></caption>
            <tr>
                <th>Favorite</th>
                <th>Username</th>
                <th>Password</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Gender</th>
                <th>Age</th>
            </tr>
            <c:forEach var="users" items="${listUsers}">
                <tr>
                    <td><c:out value="${users.favorites}" /></td>
                    <td><c:out value="${users.user}" /></td>
                    <td><c:out value="${users.pass}" /></td>
                    <td><c:out value="${users.first}" /></td>
                    <td><c:out value="${users.last}" /></td>
                    <td><c:out value="${users.gender}" /></td>
                    <td><c:out value="${users.age}" /></td>
                    
                    <td>
                        <a href="edit?user=<c:out value='${users.user}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?user=<c:out value='${users.user}' />">Delete</a>            
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>