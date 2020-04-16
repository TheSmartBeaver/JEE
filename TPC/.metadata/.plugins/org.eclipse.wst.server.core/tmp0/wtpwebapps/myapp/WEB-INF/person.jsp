<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="myapp.Person" %>

<jsp:useBean id="person" scope="session" class="myapp.Person"/>
<jsp:setProperty  name="person"  property="name"  value="Albert" />
<jsp:setProperty  name="person"  property="id"  value="1" />
<jsp:setProperty  name="person"  property="mail"  value="beber@lol.fr" />


<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>PersonServlet</title>
    </head>
    
    <body>
        <h1>Person</h1>
        <table>
            <tr>
                <th>Attributs</th>
                <th>Valeurs</th>
            </tr>
            
            <tr>
                <td>ID</td>
                <td><%= person.getId() %></td>
            </tr>
            
            <tr>
                <td>Nom</td>
                <td><%= person.getName() %></td>
            </tr>
            
            <tr>
                <td>Adresse Mail</td>
                <td><%= person.getMail() %></td>
            </tr>
        </table>
</html>