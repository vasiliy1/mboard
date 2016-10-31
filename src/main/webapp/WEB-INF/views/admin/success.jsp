<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Refresh" content="3;url=${pageContext.request.contextPath}/admin/refresh">
<script type="text/javascript" src="<c:url value="/resources/myscript.js" />"></script>
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
<title>Success</title>
</head>
<body>

	<p align="center">${successmessage}</p>
	<p align="center">
		You will be automatically redirected to <a href="${pageContext.request.contextPath}/admin/refresh">admin
			home</a> in a few seconds
	</p>

</body>
</html>