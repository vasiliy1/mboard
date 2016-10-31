<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="mytag" uri="mytag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value="/resources/myscript.js" />"></script>
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
<sec:authentication var="user" property="principal" />
<title>Admin Home</title>
</head>
<body>

	<h4 align="right">
		Admin ${user.firstName} (<a href="${pageContext.request.contextPath}/logout">log out</a>)
	</h4>
	<p align="right">
		Go to the <a href="${pageContext.request.contextPath}/board">Board</a>
	</p>
	<a class="button" href="${pageContext.request.contextPath}/admin/msgedit">Edit messages</a>
	<a class="button" href="${pageContext.request.contextPath}/admin/create">Add user</a><br><br>
	<table border="1" style="width: 100%">
		<c:forEach items="${users}" var="uservar">
			<tr>
				<td><c:out value="${uservar.login}"></c:out></td>
				<td><c:out value="${uservar.firstName}"></c:out></td>
				<td><c:out value="${uservar.lastName}"></c:out></td>
				<td><c:out value="${uservar.birthday}"></c:out></td>
				<td><c:out value="${uservar.role.name}"></c:out></td>
				<td>
					<form action="edit" method="post">
					<button name="edit" value="${uservar.login}">Edit</button>
					</form>
				</td>
				<td>
          <form action="delete" method="post">
          <button name="delete" value="${uservar.login}"
          onclick="return confirm('Are you sure?')">Delete</button>
          </form>
        </td>
			</tr>
		</c:forEach>
	</table>


</body>
</html>
