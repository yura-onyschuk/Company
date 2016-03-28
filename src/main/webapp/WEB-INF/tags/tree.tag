<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="list" type="java.util.List" required="true"%>
<%@ attribute name="company" type="ua.company.entity.Company" required="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="recursion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul>
	<c:forEach var="comp" items="${list }">
		<c:if test="${ comp.parentID ==  company.companyID}">
			<li><c:out value="${comp.companyName } ||"></c:out>
			 <c:out value="${comp.estimatedAnnualEarnings } ||"></c:out>
				<c:out value="${comp.allEarnings }"></c:out></li>
				<recursion:tree list="${list }" company="${ comp}"></recursion:tree>
		</c:if>
	</c:forEach>
</ul>