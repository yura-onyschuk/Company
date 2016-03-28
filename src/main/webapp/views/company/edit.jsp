<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
    	alert("Enter number values!!!");
        return false;
    }
    return true;
}
function validateForm() {
    var x = document.forms["myForm"]["companyName"].value;
    if (x == null || x == "") {
        alert("Name must be filled out");
        return false;
    }
}
</script>
</head>
<body>
<form action="saveCompany" method="post"  name="myForm" onsubmit="return validateForm()">
	<h2>Edit Data</h2>
	<input type="hidden" name="companyID" value="${company.companyID }">
	Company name: <input type="text" name="companyName"
		value="${company.companyName }"> <br> Estimated annual
	earnings: <input type="text" name="estimatedAnnualEarnings"
		value="${company.estimatedAnnualEarnings }" maxlength="9" onkeypress="return isNumber(event)"> <br> Parent
	company name:  
		<select name="parent" >
		<option selected="selected" value="-1">Leave old parent company</option>
		<option value="0">Make without parent</option>
		<c:forEach var="Company" items="${companies }">
		<option value="${Company.companyID }">${Company.companyName }</option>
		</c:forEach>
		</select><br>
	<button type="submit">Submit</button><br>
</form>
</body>
</html>


