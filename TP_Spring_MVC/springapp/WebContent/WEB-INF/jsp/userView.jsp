<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="login"  value="/actions/user/login" />
<c:url var="logout" value="/actions/user/logout" />
<c:url var="show"   value="/actions/user/show" />

<html>
<body>
    <h1>User</h1>

    <p>
    name : <c:out value="${user.name}" default="no name"/> | 
    <a href="${show}">Show</a> | <a href="${login}">Login</a> |
    <a href="${logout}">Logout</a>
    </p>
</body>
</html>