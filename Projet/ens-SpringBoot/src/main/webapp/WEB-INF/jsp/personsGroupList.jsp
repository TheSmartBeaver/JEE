<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="edit" value="/person/edit" />
<c:url var="groupList" value="/group/list" />

<html>
<head>
<title>Liste personnes d'un groupe</title>
<%@ include file="/WEB-INF/jsp/head-bootstrap.jsp"%>
</head>
<body>
<div class="container">
<h1>Menu</h1>
	<nav>
		<ul class="nav">
			<li class="nav-item"><a class="nav-link" href="/search">rechercher</a></li>
			<c:choose>
				<c:when test="${!empty sessionScope.sessionUtilisateur}">
					<p>connecté(e) avec l'adresse :
						${sessionScope.sessionUtilisateur.email}</p>
					<li class="nav-item"><a class="nav-link" href="/logout">logout</a></li>
					<br />
					<li class="nav-item"><a class="nav-link"
						href="/person/edit?id=${sessionScope.sessionUtilisateur.id}">modifier
							mes infos</a></li>
					<br />
				</c:when>
				<c:otherwise>
					<li class="nav-item"><a class="nav-link " href="/login">login</a>
						<br />
				</c:otherwise>
			</c:choose>
			<li class="nav-item"><a class="nav-link " href="${groupList}">liste
					des groupes</a> <br />
		</ul>
	</nav>
</div>
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
					<td><c:out value="${pers.firstName}" /></td>
					<td><i><c:out value="${pers.lastName}" /></i></td>
					<td><i><c:out value="${pers.website}" /></i></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>