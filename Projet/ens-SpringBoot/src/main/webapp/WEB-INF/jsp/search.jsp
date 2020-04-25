<html>
<head>
<meta charset="ISO-8859-1">
<title>RECHERCHE</title>
</head>
<body>

<h1>Search</h1>
 <form action="<%= request.getContextPath() %>/search?action=searchByFirstName" method="post">
  First Name: <input type="text" name="firstName">
  <br>
  <input type="submit" value="search">
 </form>
 
 <form action="<%= request.getContextPath() %>/search?action=searchByLastName" method="post">
  last Name: <input type="text" name="lastName">
  <br>
  <input type="submit" value="search">
 </form>
 
 <form action="<%= request.getContextPath() %>/search?action=searchByPartyName" method="post">
  Nom de groupe : <input type="text" name="partyName">
  <br>
  <input type="submit" value="search">
 </form>
</body>
</html>