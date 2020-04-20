<!DOCTYPE html>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="add" value="/message/add" />
<c:url var="remove" value="/message/removeAll" />
<c:url var="list" value="/message/list" />

<html>
<head>
<%@ include file="/WEB-INF/jsp/head-bootstrap.jsp"%>
</head>
<body>
    <div class="container">
        <h1>Messages</h1>

        <form action="${add}" method="POST" class="form-inline">
            <div class="form-group">
                <input name="text" size="20" class="form-control" />
            </div>
            <div class="form-group">
                <input type="submit" value="Add" class="form-control btn btn-info" />
            </div>
            <div class="form-group">
                <a class="btn btn-success" href="${list}">List</a>
            </div>
            <div class="form-group">
                <a class="btn btn-danger" href="${remove}">Remove All</a>
            </div>
        </form>

        <table class="table table-hover">
            <c:forEach items="${messages}" var="m">
                <tr>
                    <td><c:out value="${m.number}" /></td>
                    <td><c:out value="${m.text}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>