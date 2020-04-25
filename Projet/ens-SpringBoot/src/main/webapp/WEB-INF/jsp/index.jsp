<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="css" value="/style.css" />
<c:url var="groupList" value="/group/list" />

<html>
<head>
	<meta charset="UTF-8">
	<title>Bienvenue sur appli Groupe d'étudiant</title>
	<link rel="stylesheet" href="${css}">
</head>
<body>
	<h1>Bienvenue sur appli Groupe d'étudiant</h1>
	<p><a href="/login"> login </a></p>
	<p><a href="${groupList}"> liste des groupes </a> </p>
</body>
</html>