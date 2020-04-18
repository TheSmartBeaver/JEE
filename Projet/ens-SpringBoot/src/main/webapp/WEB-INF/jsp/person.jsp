<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="list" value="/person/list" />
<c:url var="newPerson" value="/person/new" />
<c:url var="findPersons" value="/person/find" />
<c:url var="css" value="/style.css" />

<html>
<head>
	<meta charset="UTF-8">
	<title>Spring boot application</title>
	<link rel="stylesheet" href="${css}">
</head>
<body>
	<h1>Courses</h1>
	<div>
		<a href="${newPerson}">New course</a> |
		<form action="${findPersons}" style='display:inline-block;'>
			<input name="firstName" size="10"/>
			<input type="submit" value="Find" />
		</form>
	</div>
	<c:forEach items="${persons}" var="persons">
		<p>${person.id}: <c:out value="${person.firstName}" /></p>
	</c:forEach>
</body>

</html>