<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		
<style type="text/css">
  body{
   background:url(img/cloud.png)  no-repeat center center;
   background-size:cover;
   background-attachment:fixed;
   background-color:#CCCCCC;
}
#stu_table
  {
  font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
  width:80%;
  margin:0 auto;
  border-collapse:collapse;
  }

#stu_table td, #stu_table th 
  {
  font-size:1em;
  border:1px solid #98bf21;
  padding:3px 7px 2px 7px;
  }

#stu_table th 
  {
  font-size:1.1em;
  text-align:left;
  padding-top:5px;
  padding-bottom:4px;
  background-color:#A7C942;
  color:#ffffff;
  }

#stu_table tr.alt td 
  {
  color:#000000;
  background-color:#EAF2D3;
  }
  


</style>

	</head>
	<body>
		<table id="stu_table">
		<caption>学生信息</caption>
	<thead>
		<tr class="alt">
		<th>编号</th>
		<th>姓名</th>
		<th>性别</th>
		<th>年龄</th>
		<th>学号</th>	
		<th>分数</th>	
		<th>操作</th>	
		</tr>
	</thead>
	<c:forEach items="${pb.beanList}" var="stu">
	<tr>
		<td>${stu.stuId }</td>
		<td>${stu.stuName }</td>
		<td>${stu.stuSex }</td>
		<td>${stu.stuAge }</td>
		<td>${stu.stuNum }</td>
		<td>${stu.stuScore }</td>
		<td>
			<a href="<c:url value='/stuServlet?method=preEdit&stuId=${stu.stuId }'/>">编辑</a>
			<a href="<c:url value='/stuServlet?method=delete&stuId=${stu.stuId }'/>">删除</a>
		</td>
	</tr>
</c:forEach>	
</table>

<br/>
<%-- 
给出分页相差的链接
 --%>
<center>
第${pb.pc }页/共${pb.tp }页

<a href="${pb.url }&pc=1">首页</a>
<c:if test="${pb.pc > 1 }">
<a href="${pb.url }&pc=${pb.pc-1}">上一页</a>
</c:if>

<%-- 计算begin、end --%>
<c:choose>
	<%-- 如果总页数不足10页，那么把所有的页数都显示出来！ --%>
	<c:when test="${pb.tp <= 10 }">
		<c:set var="begin" value="1" />
		<c:set var="end" value="${pb.tp }" />
	</c:when>
	<c:otherwise>
		<%-- 当总页数>10时，通过公式计算出begin和end --%>
		<c:set var="begin" value="${pb.pc-5 }" />
		<c:set var="end" value="${pb.pc+4 }" />	
		<%-- 头溢出 --%>
		<c:if test="${begin < 1 }">
			<c:set var="begin" value="1" />
			<c:set var="end" value="10" />
		</c:if>	
		<%-- 尾溢出 --%>
		<c:if test="${end > pb.tp }">
			<c:set var="begin" value="${pb.tp - 9 }" />
			<c:set var="end" value="${pb.tp }" />
		</c:if>	
	</c:otherwise>
</c:choose>
<%-- 循环遍历页码列表 --%>
<c:forEach var="i" begin="${begin }" end="${end }">
	<c:choose>
		<c:when test="${i eq pb.pc }">
			[${i }]
		</c:when>
		<c:otherwise>
			<a href="${pb.url }&pc=${i}">[${i }]</a>	
		</c:otherwise>
	</c:choose>
	
</c:forEach>


<c:if test="${pb.pc < pb.tp }">
<a href="${pb.url }&pc=${pb.pc+1}">下一页</a>
</c:if>
<a href="${pb.url }&pc=${pb.tp}">尾页</a>
</center>
	</body>
</html>
