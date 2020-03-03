<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
        <SCRIPT type="text/JavaScript">
        function validate(user1, user2, pw)
        {	
        	
        	alert("This being accessed?");
        	var ListOfUsers = user1;
        	var Check = false;
        	
        	for(var i = 0; i < ListOfUsers.length; i++){
        		alert(ListOfUsers[i]);
        		if (ListOfUsers[i].username != user2.value ||  ListOfUsers[i].password != pw.value){
            		alert("Failed Login. Either Username or Password is incorrect.");
            		event.preventDefault();
            	}
            	else{
            		Check = true;
            	}
        	}
        	
        	if (Check == true){
        		alert("Login Success!");
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
            <a href="list">Login</a>
             
        </h2>
    </center>
    <div align="center">
            <form action="PeopleList.jsp" method="post" name="match">
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
                    <input type="email" name="username" size="45"/>"
                        />
                </td>
            </tr>
            <tr>
                <th>Password: </th>
                <td>
                    <input type="password" name="password" size="45"/>"
                        />
                </td>
            </tr>
            
            <script>
   	 var data = {
        <c:forEach items="${cTagBean.tagList}" var="ctag" varStatus="loop">
            '${ctag.name}': ${ctag.age}${!loop.last ? ',' : ''}
        </c:forEach>
    };
</script>
            
            <%String username = request.getParameter("username"); %>
           	<%String password = request.getParameter("password"); %> 
           	<%
           	PeopleDAO peopleList = new PeopleDAO();
           	List<Users> listOfUsers = peopleList.listAllPeople();
           	%>
           	
           	
           	
            <tr>
                <td colspan="2" align="center">
                    <button type="submit" name="validate_button" onclick="validate(listOfUsers, username, password)" value="Login" class="btn">Login</button>
                </td>
            </tr>
        </table>
        </form>
        
    </div> 

</body>
</html>
