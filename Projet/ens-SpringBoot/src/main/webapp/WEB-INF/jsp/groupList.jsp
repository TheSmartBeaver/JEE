<!DOCTYPE html>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:url var="listGr" value="/personsGroup/list" />
<c:url var="edit" value="person/edit/id=${sessionScope.sessionUtilisateur.id}" />

<html>
<head>
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
				<a href="${edit}"> modifier mes infos </a>
			</c:when>
			<c:otherwise>
				<a href="/login"> login </a>
				<br />
			</c:otherwise>
		</c:choose>
	</fieldset>
	<div class="container">
        <h1>Groups List</h1>
        <table class="table table-hover">
            <c:forEach items="${availableGroups}" var="group">
                <tr>
                    <td> <c:out value="${group.id}" /></td>
                    <td><i><a href="${listGr}?id=${group.id}">$<c:out value="${group.partyName}" /></a></i></td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>