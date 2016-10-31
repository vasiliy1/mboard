<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value="/resources/myscript.js" />"></script>
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
<title>Login Page</title>
</head>
<body>

	<h4 align="center">Message Board</h4>
	<p align="center">
		<c:if test="${not empty error}">
    ${error}
  </c:if>
	</p>
	<form method="post" action="<%=request.getContextPath()%>/auth">
		<table align="center">
			<tr>

				<td><input type="text" name="login" class="credentials" placeholder="username"></td>
			</tr>
			<tr>

				<td><input type="password" name="password" class="credentials" placeholder="password"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Sign in" id="login"></td>
			</tr>
		</table>


	</form>

	<p align="center">
		You can <a href="${pageContext.request.contextPath}/register">register new account</a>
	</p>

</body>
</html>