<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="list" value="/person/list" />
<c:url var="newPerson" value="/person/new" />
<c:url var="findPersons" value="/person/find" />
<c:url var="affPainGroup" value="/party/affiche" />

<c:url var="css" value="/style.css" />

<html>
<head>
	<meta charset="UTF-8">
	<title>Spring boot application</title>
	<link rel="stylesheet" href="${css}">
</head>
<body>
	<h1>Person</h1>
	<div>
		<a href="${newPerson}">New Person</a> |
		<form action="${findPersons}" style='display:inline-block;'>
			<input name="firstName" size="10"/>
			<input type="submit" value="Find" />
		</form>
	</div>
	<c:forEach items="${persons}" var="person">
		<p>${person.id}: <c:out value="${person.firstName}" /></p>
	</c:forEach>
	
	<h1>Group</h1>
	<div>
		<form action="${affPainGroup}" style='display:inline-block;'>
			<input name="partyName" size="10"/>
			<input type="submit" value="affParty" />
		</form>
	</div>
	<c:forEach items="${partys}" var="party">
		<p>${party.id}: <c:out value="${party.partyName}" /></p>
	</c:forEach>
</body>

</html>