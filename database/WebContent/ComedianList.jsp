
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Favorites list</title>
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
        <h1>List of Comedians</h1>
        <h2>
            
            <a href="signOut">Sign out</a>
            &nbsp;&nbsp;&nbsp;
            <a href="searchInterface">Back to Menu</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <c:forEach var="Comedians" items="${listComedians}">
                <tr>
                      <td><c:out value="${Comedians.fName}" /></td>                   
	                  <td><c:out value="${Comedians.lName}" /></td>
                      <td><a href="insertFavorite?id=<c:out value='${Comedians.id}' />">add to favorites</a></td> 
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>