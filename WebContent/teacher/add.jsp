<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
  body{
   background:url(img/cloud.png) no-repeat center center;
   background-size:cover;
   background-attachment:fixed;
   background-color:#CCCCCC;
}

</style>
</head>
<body>
<h3 align="center">添加学生信息</h3>
<form action="<c:url value='/stuServlet'/>" method="post">
	<input type="hidden" name="method" value="add"/>
<table border="0" align="center" width="40%" style="margin-left: 100px;">
	<tr>
		<td width="100px">编号</td>
		<td width="40%">
			<input type="text" name="stuId"/>
		</td>
	</tr>
	<tr>
	<tr>
		<td width="100px">姓名</td>
		<td width="40%">
			<input type="text" name="stuName"/>
		</td>
	</tr>
	<tr>
		<td>性别</td>
		<td>
			<input type="radio" name="stuSex" value="男" id="male"/>
			<label for="male">男</label>
			<input type="radio" name="stuSex" value="女" id="female"/>
			<label for="female">女</label>
		</td>
	</tr>
	<tr>
		<td>年龄</td>
		<td>
			<input type="text" name="stuAge" id="stuAge" />
		</td>
	</tr>
	<tr>
		<td>学号</td>
		<td>
			<input type="text" name="stuNum"/>
		</td>		
	</tr>
	<tr>
		<td>分数</td>
		<td>
			<input type="text" name="stuScore"/>
		</td>
	</tr>
	
	<tr>
		<td>&nbsp;</td>
		<td>
			<input type="submit" value="添加" />
			<input type="reset" value="重置"/>
		</td>
		<td>&nbsp;</td>
	</tr>
</table>
</form>
  </body>
</html>