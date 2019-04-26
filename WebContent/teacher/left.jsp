<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<base target="right">
<style>
 body{
   background:url(img/cloud.png)  no-repeat center center;
   background-size:cover;
   background-attachment:fixed;
   background-color:#CCCCCC;
	}

	li{
		list-style: none;
	}
</style>
	</head>
	<body>
		<ul>
			<li><a href="<c:url value='/stuServlet?method=findAll'/>"><strong>查询所有学生</strong></a></li><br />
			<li><a href="add.jsp" >添加学生</a></li><br />
			<li><a href="query.jsp">高级查询</a></li><br />
			</ul>
	</body>
</html>
