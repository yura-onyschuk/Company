<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>Companies:</h2>
<table class="table-companies">
<thead>
<tr>
<th>
Company Name
</th>
</tr>
</thead>
<tbody>
<c:forEach var="company" items="${companies }">
<tr>
<td><a href="showOneCompanyTree?companyID=${company.companyID }" data-trigger="hover" title="Show company tree">${company.companyName }</a></td>
<td><a href="editCompany?companyID=${company.companyID }">Edit</a></td>
<td><a href="deleteCompany?companyID=${company.companyID }" onclick="return confirm('Are you sure you want to delete?')" >Delete</a></td>
</tr>
</c:forEach>
</tbody>
</table>

