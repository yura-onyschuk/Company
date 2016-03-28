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
        alert("Name must be filled out!!!");
        return false;
    }
}
</script>
</head>
<body>
	<form action="addCompany" method="post" name="myForm" onsubmit="return validateForm()">
		<h2>Input data:</h2>
		Company name <input type="text" name="companyName" title="Please enter unique company name"> <br>
		Estimated annual earnings <input type="text" maxlength="9"
			name="estimatedAnnualEarnings" onkeypress="return isNumber(event)"> <br>
			Parent
	company  
		<select name="parent" >
		<option selected="selected" value="0"></option>
		<c:forEach var="company" items="${companies }">
		<option value="${company.companyID }">${company.companyName }</option>
		</c:forEach>
		</select><br>
		<button type="submit">Submit</button>
	</form>
</body>
</html>