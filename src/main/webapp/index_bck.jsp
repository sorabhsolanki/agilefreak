<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
	<form id="check" method="post" action="${pageContext.request.contextPath}/room">
		<label for="roomnumber"> Enter room number </label> <input type="text"
			name="roomnumber" id="roomnumber" /> <input type="submit"
			value="SUBMIT">

<c:forEach items="${myList}" var="element"> 
  <tr>
    <td>${element}</td>
  </tr>
</c:forEach>

<table>
<tr>
<td>No Of Users: <c:out value="${result}" /></td>
</tr>
</table>

	</form>
</body>
</html>