<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="edit" value="/person/edit" />

<html>
<head>
<title>Hello :: Spring Application</title>
<%@ include file="/WEB-INF/jsp/head-bootstrap.jsp"%>
</head>
<body>
	<div class="container">
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
					<td><a href="${edit}?id=${pers.id}"> <c:out
								value="${pers.firstName}" />
					</a></td>
					<td><i>$<c:out value="${pers.lastName}" /></i></td>
					<td><i>$<c:out value="${pers.website}" /></i></td>
					<td><i>$<c:out value="${pers.birthDay}" /></i></td>
					<td><i>$<c:out value="${pers.mail}" /></i></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>