<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>查询</title>
   <style>
  body{
   background:url(img/cloud.png) no-repeat center center;
   background-size:cover;
   background-attachment:fixed;
   background-color:#CCCCCC;
}

</style> 
  </head>
  
  <body>
<h3 align="center">查询</h3>
<form action="<c:url value='/stuServlet'/>" method="get">
	<input type="hidden" name="method" value="query"/>
<table border="0" align="center" width="40%" style="margin-left: 100px;">
	<tr>
		<td width="100px">姓名</td>
		<td width="40%">
			<input type="text" name="stuName"/>
		</td>
	</tr>
	<tr>
		<td>性别</td>
		<td>
			<select name="stuSex">
				<option value="">性别</option>
				<option value="男">男</option>
				<option value="女">女</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>年龄</td>
		<td>
			<input type="text" name="stuAge"/>
		</td>		
	</tr>
	<tr>
		<td>学号</td>
		<td>
			<input type="text" name="stuNum"/>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>
			<input type="submit" value="搜索"/>
			<input type="reset" value="重置"/>
		</td>
		<td>&nbsp;</td>
	</tr>
</table>
</form>
  </body>
</html>
