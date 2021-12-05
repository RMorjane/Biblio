<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Gestion des Genres</title>
	<link rel="stylesheet" href="./biblio.css">
</head>

<body>
	
    <h2>Gestion des Genres</h2>
    <br>
    
    <div class="ctn-main">
    
    	<table>
			<tr>
				<th>Id</th>
				<th>Nom</th>
				<th colspan="2">Action</th>
			</tr>
			<c:forEach var="genre" items="${Lgenres}">
				<tr>
					<td><c:out value="${genre.genreId}"/></td>
					<td><c:out value="${genre.nom}"/></td>
					<td><a href="ServletGestionGenre?action=modifier&id=${genre.genreId}">Modifier</a></td>
					<td><a href="#" onclick=confirmDelete("Genre","${genre.genreId}")>Supprimer</a></td>
				</tr>
			</c:forEach>
		</table>
		<br><hr>
		
		<form class="form-gestion" action="FormGenre.jsp" method="POST">
		    <button class="bouton" onclick="displayJsp(this,'Genre','ajouter');">Ajouter</button>
		    <button class="bouton" onclick="displayJsp(this,'Genre','rechercher');">Rechercher</button>
		</form>
          
    </div>
	<script src="./biblio.js"></script>
</body>
</html>