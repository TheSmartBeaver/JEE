<!DOCTYPE html>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:url var="edit" value="/personsGroup/list" />

<html>
<head>
<%@ include file="/WEB-INF/jsp/head-bootstrap.jsp"%>
</head>

<body>

	<div class="container">
        <h1>Groups List</h1>
        <table class="table table-hover">
            <c:forEach items="${availableGroups}" var="group">
                <tr>
                    <td> <c:out value="${group.id}" /></td>
                    <td><i><a href="${edit}?id=${group.id}">$<c:out value="${group.partyName}" /></a></i></td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>