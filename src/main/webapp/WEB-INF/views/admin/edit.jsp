<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value="/resources/myscript.js" />"></script>
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
<title>Edit Page</title>
</head>
<body>
	<h2>Edit user</h2>
	<font color="red"><form:errors path="editingUser.*" /></font>

	<form:form method="POST" modelAttribute="editingUser" action="/mvc/admin/processedit"
		onsubmit="validateEmail()">
		<table>
			<tr>
				<td>Login:</td>
				<td><form:input path="login" /></td>
			</tr>
			<form:hidden path="password" />
			<tr>
				<td>Email:</td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td>First Name:</td>
				<td><form:input path="firstName" /></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><form:input path="lastName" /></td>
			</tr>
			<tr>
				<td>Date of birth(yyyy-MM-dd):</td>
				<td><form:input path="birthday" /></td>
			</tr>
			<tr>
				<td>Role</td>
				<td><form:radiobutton path="role.name" value="regular" />Regular<br> <form:radiobutton
						path="role.name" value="admin" />Admin</td>
			</tr>
		</table>
		<input type="submit" value="Edit">
	</form:form>
	<form action="${pageContext.request.contextPath}/admin/refresh" method="get">
		<button name="cancel" value="cancel">Cancel</button>
	</form>
</body>
</html>