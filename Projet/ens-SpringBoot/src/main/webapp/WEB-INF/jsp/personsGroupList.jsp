<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="edit" value="/person/edit" />
<c:url var="groupList" value="/group/list" />

<html>
<head>
<title>Hello :: Spring Application</title>
<%@ include file="/WEB-INF/jsp/head-bootstrap.jsp"%>
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
				<a href="person/edit/id=${sessionScope.sessionUtilisateur.id}"> modifier mes infos </a>
			</c:when>
			<c:otherwise>
				<a href="/login"> login </a>
				<br />
			</c:otherwise>
		</c:choose>
		<a href="${groupList}"> liste des groupes </a>
	</fieldset>

	<div class="container">
		<h1>
			Persons in Group sans les détails :
			<c:out value="${selectedGroup}" />
		</h1>
		<table class="table table-hover">
			<tr>
				<td><i>Nom</i></td>
				<td><i>Prénom</i></td>
				<td><i>Site Web</i></td>
			</tr>
			<c:forEach items="${personsInGroup}" var="pers">
				<tr>
					<td><a href="${edit}?id=${pers.id}"> <c:out
								value="${pers.firstName}" />
					</a></td>
					<td><i>$<c:out value="${pers.lastName}" /></i></td>
					<td><i>$<c:out value="${pers.website}" /></i></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>