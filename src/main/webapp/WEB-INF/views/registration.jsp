<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="botDetect" uri="botDetect"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value="/resources/myscript.js" />"></script>
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
<title>Registration Page</title>
</head>
<body>
	<font color="red"><form:errors path="user.*" /></font>

	<form:form method="POST" modelAttribute="user" action="register"
		onsubmit="return comparePasswords() && validateEmail()">
		<table class="registration">
			<tr>
				<td>Login:</td>
				<td><form:input path="login" cssClass="regform" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:password path="password" id="password" cssClass="regform" /></td>
			</tr>
			<tr>
				<td>Repeat password:</td>
				<td><input type="password" name="password2" id="password2" class="regform"></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><form:input path="email" cssClass="regform" /></td>
			</tr>
			<tr>
				<td>First Name:</td>
				<td><form:input path="firstName" cssClass="regform" /></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><form:input path="lastName" cssClass="regform" /></td>
			</tr>
			<tr>
				<td>Date of birth(yyyy-MM-dd):</td>
				<td><form:input path="birthday" cssClass="regform" /></td>
			</tr>
		</table>
		<table class="registration">
			<tr>
				<td><botDetect:captcha id="validationCaptcha" /></td>
			</tr>
			<tr>
				<td><input type="text" name="captchaText" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Register" id="register" /></td>
			</tr>
		</table>
	</form:form>
	<p align="center">
		<a class="button" href="${pageContext.request.contextPath}/login">Back to Login Page</a>
	</p>

</body>
</html>