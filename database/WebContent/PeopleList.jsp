<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>People Management Application</title>
</head>
<body>

<%-- need to debut this part of the code to make it work, ideally we would like to see 
 all people are listed intially when the page is run as the entry page.
 
<%
if(request.getParameter("listPeople") == null) { // we want to make sure that we already have all the people
	PeopleDAO peopleDAO = new PeopleDAO();        // listed in attribute 'listPeople'
	List<People> listPeople = peopleDAO.listAllPeople();
	request.setAttribute("listPeople", listPeople);       
}
%>
--%>

    <center>
        <h1>User Registration/Login</h1>
        <h2>
            <a href="new">Register</a>
            &nbsp;&nbsp;&nbsp;
            <a href="list">Login</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of People</h2></caption>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Password</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Age</th>
            </tr>
            <c:forEach var="users" items="${listUsers}">
                <tr>
                    <td><c:out value="${users.ID}" /></td>
                    <td><c:out value="${users.user}" /></td>
                    <td><c:out value="${users.pass}" /></td>
                    <td><c:out value="${users.first}" /></td>
                    <td><c:out value="${users.last}" /></td>
                    <td><c:out value="${users.age}" /></td>
                    
                    <td>
                        <a href="edit?id=<c:out value='${users.ID}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?id=<c:out value='${users.ID}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>