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
        <h1>Persons (bootstrap)</h1>
        <table class="table table-hover">
            <c:forEach items="${persons}" var="pers">
                <tr>
                    <td><a href="${edit}?id=${pers.id}">
                        <c:out value="${pers.firstName}" />
                    </a></td>
                    <td><i>$<c:out value="${pers.lastName}" /></i></td>
                </tr>
            </c:forEach>
        </table>
        <p>
            <a class="btn btn-info" href="${edit}">Create new person</a>
        </p>
    </div>
</body>
</html>