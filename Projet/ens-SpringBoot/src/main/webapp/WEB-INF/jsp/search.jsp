<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<c:url var="groupList" value="/group/list" />

<html>
<head>
<meta charset="ISO-8859-1">
<title>RECHERCHE</title>
</head>
<body>

	<fieldset>
		<legend>Menu</legend>
		<c:choose>
			<c:when test="${!empty sessionScope.sessionUtilisateur}">
				<p>connecté(e) avec l'adresse :
					${sessionScope.sessionUtilisateur.email}</p>
				<a href="/logout"> logout </a>
				<br />
				<a href="/person/edit?id=${sessionScope.sessionUtilisateur.id}">
					modifier mes infos </a>
			</c:when>
			<c:otherwise>
				<a href="/login"> login </a>
				<br />
			</c:otherwise>
		</c:choose>
		<a href="${groupList}"> liste des groupes </a>
	</fieldset>

	<h1>Search</h1>
	<form
		action="<%=request.getContextPath()%>/search?action=searchByFirstName"
		method="post">
		Nom personne: <input type="text" name="firstName"> <br> <input
			type="submit" value="search">
	</form>

	<form
		action="<%=request.getContextPath()%>/search?action=searchByLastName"
		method="post">
		Prénom personne: <input type="text" name="lastName"> <br> <input
			type="submit" value="search">
	</form>

	<form
		action="<%=request.getContextPath()%>/search?action=searchByPartyName"
		method="post">
		Nom de groupe : <input type="text" name="partyName"> <br>
		<input type="submit" value="search">
	</form>
</body>
</html>