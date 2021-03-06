<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<c:url var="groupList" value="/group/list" />
<html>
<head>
<meta charset="utf-8" />
<title>Connexion</title>
<link type="text/css" rel="stylesheet" href="form.css" />
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
				<a href="/person/edit?id=${sessionScope.sessionUtilisateur.id}">
					modifier mes infos </a>
					<br/>
			</c:when>
		</c:choose>
		<a href="${groupList}"> liste des groupes </a>
		<br />
		<a href="/search"> rechercher </a>
	</fieldset>


	<form action="<%=request.getContextPath()%>/login" method="post">
		<fieldset>
			<legend>Connexion</legend>
			<p>Vous pouvez vous connecter via ce formulaire.</p>

			<label for="nom">Adresse email <span class="requis">*</span></label>
			<input type="email" id="email" name="email"
				value="<c:out value="${utilisateur.email}"/>" size="20"
				maxlength="60" /> <span class="erreur">${form.erreurs['email']}</span>
			<br /> <label for="motdepasse">Mot de passe <span
				class="requis">*</span></label> <input type="password" id="motdepasse"
				name="motdepasse" value="" size="20" maxlength="30" minlength="1" />
			<span class="erreur">${form.erreurs['motdepasse']}</span> <br /> <input
				type="submit" value="Connexion" class="sansLabel" /> <br />

			<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>

			<%-- Vérification de la présence d'un objet utilisateur en session --%>
			<c:if test="${!empty sessionScope.sessionUtilisateur}">
				<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
				<p class="succes">Vous êtes connecté(e) avec l'adresse :
					${sessionScope.sessionUtilisateur.email}</p>
			</c:if>

		</fieldset>
	</form>
</body>
</html>