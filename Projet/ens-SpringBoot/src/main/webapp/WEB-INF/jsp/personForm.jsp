<!DOCTYPE html>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<%@ include file="/WEB-INF/jsp/head-bootstrap.jsp"%>
</head>

<body>

    <div class="container">
        <h1>Edit Person</h1>

        <form:form method="POST" modelAttribute="person">

            <form:errors path="*" cssClass="alert alert-danger" element="div" />

            <div class="form-group">
                <label for="firstName">firstName:</label>
                <form:input class="form-control" path="firstName" value="${firstname}"/>
                <form:errors path="firstName" cssClass="alert alert-warning"
                    element="div" />
            </div>
            <div class="form-group">
                <label for="lastName">lastName:</label>
                <form:textarea class="form-control" path="lastName" rows="4" />
                <form:errors path="lastName" cssClass="alert alert-warning"
                    element="div" />
            </div>
            <div class="form-group">
                <label for="password">password:</label>
                <form:textarea class="form-control" path="password" rows="4" />
                <form:errors path="password" cssClass="alert alert-warning"
                    element="div" />
            </div>
            <div class="form-group">
    			<label for="party">RAJOUTER LES GROUPES !! REGARDER un peu "types" de product et codeProduct car party est un champs complexe</label>
			</div>
		
            <div class="form-group">
                <button type="submit" class="btn btn-info">Submit</button>
            </div>
        </form:form>
    </div>

</body>
</html>