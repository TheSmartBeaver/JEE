<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="edit" value="/person/edit" />
<c:url var="groupList" value="/group/list" />
<html>
<head>
<title>Personnes d'un groupe</title>
<%@ include file="/WEB-INF/jsp/head-bootstrap.jsp"%>
</head>
<body>
	<div class="container">
	<fieldset>
		<legend>Menu</legend>
		<c:choose>
			<c:when test="${!empty sessionScope.sessionUtilisateur}">
				<p>connecté(e) avec l'adresse :
					${sessionScope.sessionUtilisateur.email}</p>
				<a href="/logout"> logout </a>
				<br />
				<a href="/person/edit?id=${sessionScope.sessionUtilisateur.id}"> modifier mes infos </a>
				<br />
			</c:when>
			<c:otherwise>
				<a href="/login"> login </a>
				<br />
			</c:otherwise>
		</c:choose>
		<a href="${groupList}"> liste des groupes </a>
		<br />
		<a href="/search">rechercher</a>
	</fieldset>
	
		<h1>
			Persons in Group avec les détails :
			<c:out value="${selectedGroup}" />
		</h1>
		<table class="table table-hover">
			<tr>
				<td><i>Nom</i></td>
				<td><i>Prénom</i></td>
				<td><i>Site Web</i></td>
				<td><i>Date Naissance</i></td>
				<td><i>Mail</i></td>
			</tr>
			<c:forEach items="${personsInGroup}" var="pers">
				<tr>
					<td><c:out value="${pers.firstName}" /></td>
					<td><i><c:out value="${pers.lastName}" /></i></td>
					<td><i><c:out value="${pers.website}" /></i></td>
					<td><i><c:out value="${pers.birthDay}" /></i></td>
					<td><i><c:out value="${pers.mail}" /></i></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>