<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
  body{
    background:url(img/cloud.png) no-repeat center center;
   background-size:cover;
   background-attachment:fixed;
   background-color:#CCCCCC;
}

#div5{align="center" style="width:100%;height:40px"; background-color: yellow;}

#id6{
		width:300px;
		height: 1000px;
	}
</style>
</head>
	<body>
	当前有<%=application.getAttribute("userCount") %>人在线<br/>
	<h2 style="color:red;" align="center">${msg }</h2>
		<form action="<c:url value='/LoginServlet'/>" method="post" id="form1" >
			<input type="hidden" name="method" value="login"/>
			<h2 align="center">登录</h2>
		<div id="div1" align="center"><label>账号：</label><input id="username"  type="text" style="width:250px;height:35px; border-radius:15px; background: none transparent scroll repeat 0% 0%;" name="tName" /></div></br>
		<div id="div2" align="center"><label>密码：</label><input id="tpassword"  type="password" style="width:250px;height:35px;border-radius:15px;background: none;" name="tPassword" /></div></br>
		
		<%-- <div id3="div3" align="center"><input type="radio" name="gender" value="管理员" id="male"/>
		<label for="male">管理员</label><input type="radio" name="gender" value="普通成员" id="female"/><label for="female">普通成员</label></div></br>
		
		<input type="hidden" name="id" value="001" /><input type="button" value="注册" style="width:60px;height:30px;background-color: yellow;"/>
		--%>
		<div id="div4" align="center"><input type="submit" name="denglu"  value="登录"  style="width:60px;height:30px;background-color: yellow; " />

		<input type="reset" style="width:60px;height:30px;background-color: yellow;"/></div>
		</form>
		
	</body>
</html>
