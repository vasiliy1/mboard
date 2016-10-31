<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value="/resources/myscript.js" />"></script>
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
<sec:authentication var="user" property="principal" />
<title>Success</title>
</head>
<body>


	<h2 align="center">Hello, ${user.firstName}!</h2>
	<br>
	 <p align="center">
    Go to the <a href="${pageContext.request.contextPath}/board">Board</a>
  </p>
	<p align="center">
		Click <a href="${pageContext.request.contextPath}/logout">here</a> to log out
	</p>

</body>
</html>