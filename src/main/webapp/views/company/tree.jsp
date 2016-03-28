<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib tagdir="/WEB-INF/tags" prefix="recursion"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>Companies tree:</h4>
<h5>Company name || Earnings || Corporation earnings</h5>
<hr>
<c:forEach var="companyMain" items="${head }">
<li><c:out value="${companyMain.companyName } ||"></c:out>
			 <c:out value="${companyMain.estimatedAnnualEarnings } ||	"></c:out>
				<c:out value="${companyMain.allEarnings }"></c:out></li>
<recursion:tree list="${list }" company="${companyMain}"></recursion:tree>
<hr>
</c:forEach>
</body>
</html>