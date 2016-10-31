<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value="/resources/myscript.js" />"></script>
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
<title>Success</title>
</head>
<body>
	<jsp:useBean id="registeredUser" class="ua.hypson.mvc.entity.User" scope="request" />
	<p align="center">
		Congratulations,
		<jsp:getProperty property="firstName" name="registeredUser" />
	</p>
	<p align="center">You have been successfully registered</p>
	<p align="center">
		You can <a href="${pageContext.request.contextPath}/login">log in</a> now
	</p>

</body>
</html>