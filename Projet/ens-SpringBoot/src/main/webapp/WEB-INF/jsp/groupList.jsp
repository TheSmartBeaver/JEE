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
        <h1>Products (bootstrap)</h1>
        <table class="table table-hover">
            <c:forEach items="${availableGroups}" var="group">
                <tr>
                    <td> <c:out value="${group.id}" /></td>
                    <td><i><a href="${edit}?id=${group.id}">$<c:out value="${group.partyName}" /></a></i></td>
                </tr>
            </c:forEach>
        </table>
        <p>
            <a class="btn btn-info" href="${edit}">Create new product</a>
        </p>
    </div>

    <div class="container">
        <h1>Edit Product</h1>

        <form:form method="POST" modelAttribute="product">

            <form:errors path="*" cssClass="alert alert-danger" element="div" />

            <div class="form-group">
                <label for="name">Name:</label>
                <form:input class="form-control" path="name" />
                <form:errors path="name" cssClass="alert alert-warning"
                    element="div" />
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <form:textarea class="form-control" path="description" rows="4" />
                <form:errors path="description" cssClass="alert alert-warning"
                    element="div" />
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <form:input path="price" class="form-control" />
                <form:errors path="price" cssClass="alert alert-warning"
                    element="div" />
            </div>
            <div class="form-group">
    			<label for="type">Type:</label>
    			<form:select path="type" multiple="false" class="form-control">
        			<form:option value="" label="--- Select ---" />
        			<form:options items="${availableGroups}" />
    			</form:select>
    			<form:errors path="type" cssClass="alert alert-warning"
        			element="div" />
			</div>
			<div class="form-group">
    <label for="code">Code:</label>
    <form:input path="code" class="form-control" />
    <form:errors path="code" cssClass="alert alert-warning"
        element="div" />
</div>
            <div class="form-group">
                <button type="submit" class="btn btn-info">Submit</button>
            </div>
        </form:form>
    </div>

</body>
</html>