<!DOCTYPE html>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:url var="listGr" value="/personsGroup/list" />


<html>
<head>
<%@ include file="/WEB-INF/jsp/head-bootstrap.jsp"%>
<title>Liste groupes</title>
</head>

<body>
	<div class="container">
		<h1>Menu</h1>
		<nav>
			<ul class="nav">
					<c:choose>
						<c:when test="${!empty sessionScope.sessionUtilisateur}">
							<p>connecté(e) avec l'adresse :
								${sessionScope.sessionUtilisateur.email}</p>
							<li class="nav-item"><a class="nav-link" href="/logout">logout</a></li>
							<br />
							<li class="nav-item"><a class="nav-link"
								href="/person/edit?id=${sessionScope.sessionUtilisateur.id}">modifier
									mes infos</a></li>
									<br/>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link " href="/login">login</a>
								<br />
						</c:otherwise>
					</c:choose>
					<li class="nav-item"><a class="nav-link" href="/search">rechercher</a></li>
			</ul>
		</nav>
	</div>
	<div class="container">
		<h1>Groups List</h1>
		<table class="table table-hover">
			<c:forEach items="${availableGroups}" var="group">
				<tr>
					<td><c:out value="${group.id}" /></td>
					<td><i><a href="${listGr}?id=${group.id}"><c:out
									value="${group.partyName}" /></a></i></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>