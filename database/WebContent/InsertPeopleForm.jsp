<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
        <SCRIPT type="text/JavaScript">
        function validate(user, pw, repw, fn, ln, gender,age,favorite)
        {	
        	if (user.value == "" || pw.value == "" || repw.value == "" || fn.value == "" || ln.value == "" || age.value == "" || gender.value== "" || favorite.value== ""){
        		alert("Please fill out all required forms");
        		event.preventDefault();
        	}
        	else if (pw.value != repw.value){
        		alert("Incorrect Passwords");
        		event.preventDefault();
        	}
        	else{
        		
        	}
        } 
    	</SCRIPT>  
    	
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
            <form action="insert" method="post" name="match">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                        Register
                </h2>
            </caption>
                <c:if test="${users != null}">
                    <input type="hidden" name="id" value="<c:out value='${users.id}' />" />
                </c:if>           
            <tr>
                <th>Username (Email): </th>
                <td>
                    <input type="email" name="username" size="45"
                            value="<c:out value='${users.username}'/>"
                        />
                 </td>
            </tr>
            <tr>
                <th>Password: </th>
                <td>
                    <input type="password" name="password" size="45"
                            value="<c:out value='${users.password}' />"
                        />
                </td>
            </tr>
            
             <tr>
                <th>Retype Password: </th>
                <td>
                    <input type="password" name="repassword" size="45"/>
                </td>
            </tr>
            
            <tr>
                <th>First Name: </th>
                <td>
                    <input type="text" name="first_name" size="45"
                            value="<c:out value='${users.first_name}' />"
                        />
                </td>
            </tr>
            
            
            <tr>
                <th>Last Name: </th>
                <td>
                    <input type="text" name="last_name" size="45"
                            value="<c:out value='${users.last_name}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Favorite Comedian (Number): </th>
                <td>
                    <input type="text" name="favorite" size="5"
                            value="<c:out value='${users.favorite}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Gender: </th>
                <td>
                    <input type="text" name="gender" size="5"
                            value="<c:out value='${users.gender}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Age: </th>
                <td>
                    <input type="text" name="age" size="5"
                            value="<c:out value='${users.age}' />"
                    />
                </td>
            </tr>
            
            
            <%String username = request.getParameter("username"); %>
           	<%String password = request.getParameter("password"); %> 
           	<%String repassword = request.getParameter("repassword"); %>
           	<%String first_name = request.getParameter("first_name"); %>
            <%String last_name = request.getParameter("last_name"); %>
            <%String gender = request.getParameter("gender"); %>
           	<%String age = request.getParameter("age"); %>
           	<%String favorite = request.getParameter("favorite"); %>
           	
            <tr>
                <td colspan="2" align="center">
                    <button type="submit" name="validate_button" onclick="validate(username, password, repassword, first_name, last_name, gender,age,favorite)" value="Register" class="btn">Register</button>
                </td>
            </tr>
        </table>
        </form>
        
    </div> 

</body>
</html>
