<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>People Application</title>
</head>
<body>

    <center>
        <h1>User Register/Login</h1>
        <h2>
            <a href="new">Register</a>
            &nbsp;&nbsp;&nbsp;
            <a href="login">Login</a>
             
        </h2>
    </center>
    <div align="center">
            <form action="searchInterface" method="post" name="match">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                        Login
                </h2>
            </caption>
                <c:if test="${users != null}">
                    <input type="hidden" name="id" value="<c:out value='${users.id}' />" />
                </c:if>           
            <tr>
                <th>Username (Email): </th>
                <td>
                    <input type="text" name="username" size="45"/>
                        
                </td>
            </tr>
            <tr>
                <th>Password: </th>
                <td>
                    <input type="password" name="password" size="45"/>
                        
                </td>
            </tr>
            
            <%String username = request.getParameter("username"); %>
           	<%String password = request.getParameter("password"); %> 
           	
           	
           	
            <tr>
                <td colspan="2" align="center">
                    <button type="submit" name="validate_button"value="Login" class="btn">Login</button>
                </td>
            </tr>
        </table>
        </form>
        
    </div> 

</body>
</html>
