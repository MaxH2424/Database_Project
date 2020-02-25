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
            <a href="list">Login</a>
             
        </h2>
    </center>
    <div align="center">
            <form action="insert" method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                        Register
                </h2>
            </caption>
                <c:if test="${people != null}">
                    <input type="hidden" name="id" value="<c:out value='${people.id}' />" />
                </c:if>           
            <tr>
                <th>Username (Email): </th>
                <td>
                    <input type="text" name="name" size="45"
                            value="<c:out value='${people.name}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Password: </th>
                <td>
                    <input type="text" name="name" size="45"
                            value="<c:out value='${people.name}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Retype Password: </th>
                <td>
                    <input type="text" name="name" size="45"
                            value="<c:out value='${people.name}' />"
                        />
                </td>
            </tr>
            
            
            <tr>
                <th>First Name: </th>
                <td>
                    <input type="text" name="name" size="45"
                            value="<c:out value='${people.name}' />"
                        />
                </td>
            </tr>
            
            
            <tr>
                <th>Last Name: </th>
                <td>
                    <input type="text" name="address" size="45"
                            value="<c:out value='${people.address}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Age: </th>
                <td>
                    <input type="text" name="status" size="5"
                            value="<c:out value='${people.status}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Register" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>
