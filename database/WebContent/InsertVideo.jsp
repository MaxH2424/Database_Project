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
            <form action="insertVideo" method="post" name="match">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                        Video upload
                </h2>
            </caption>
                <c:if test="${videos != null}">
                    <input type="hidden" name="id" value="<c:out value='${users.id}' />" />
                </c:if>           
                       <tr>
                <th>URL: </th>
                <td>
                    <input type="text" name="url" size="45"
                            value="<c:out value='${videos.URL}'/>"
                        />
                </td>
            </tr>
            <tr>
                <th>Title: </th>
                <td>
                    <input type="text" name="title" size="45"
                            value="<c:out value='${videos.title}' />"
                        />
                </td>
            </tr>
            
             <tr>
                <th>Description: </th>
                <td>
                    <input type="text" name="description" size="45"
                    	value="<c:out value='${videos.description}'/>"
                    />
                </td>
            </tr>
            
            <tr>
                <th>Tags: </th>
                <td>
                    <input type="text" name="tags" size="45"
                            value="<c:out value='${videos.tags}' />"
                        />
                </td>
            </tr>
            
            
            <%String url = request.getParameter("url"); %>
           	<%String title = request.getParameter("title"); %> 
           	<%String description = request.getParameter("description"); %>
           	<%String tags = request.getParameter("tags"); %>
           
           	
            <tr>
                <td colspan="2" align="center">
                    <button type="submit" name="validate_button" onclick="validate(url, title, description, tags)" value="Register" class="btn">Upload</button>
                </td>
            </tr>
        </table>
        </form>
        
    </div> 

</body>
</html>
