<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Interface</title>
</head>
<body style="background-color:#00FFFF;">
<form action="list" method="post" name="match">
	<center>
		Search Interface<br><br><br><button style="height:20px;width:140px">List of People</button>
	</center>
</form>
<form action="goToVideoPg" method="post" name="match">
	<center>
		<br><button style="height:20px;width:140px">Create a Video</button>
	</center>
</form>
<form action="search" method="post" name="match">
	<center>
		<br><button style="height:20px;width:140px">Comedian/Tags</button>
	</center>
</form>
<form action="comment" method="post" name="match">
	<center>
		<br><button style ="height:20px;width:140px">Leave a Comment!</button>
	</center>
</form>
</body>
</html>