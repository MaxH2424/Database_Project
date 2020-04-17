<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>People Application</title>
</head>
<body>
    <center>
        <h1>People Management</h1>
        <h2>
            <a href="new">Add New People</a>
            &nbsp;&nbsp;&nbsp;
            <a href="brand">List All People</a>
             
        </h2>
    </center>
    <div align="center">
            <form action="update" method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Edit an Existing People</h2>
            </caption>  
           
                <input type="hidden" name="refuser" value="${user.user}" />      
                <tr>
                	<th>Username: </th>
                		<td>
                    <input type="text" name="username" size="45"
                            value="<c:out value='${user.user}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>First Name: </th>
                <td>
                    <input type="text" name="first_name" size="45"
                            value="<c:out value='${user.first}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Last Name: </th>
                <td>
                    <input type="text" name="last_name" size="45"
                            value="<c:out value='${user.last }'/>"
                            />
                </td>
            </tr>
            <tr>
                <th>Age: </th>
                <td>
                    <input type="text" name="age" size="5"
                            value="<c:out value='${user.age }'/>"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>
