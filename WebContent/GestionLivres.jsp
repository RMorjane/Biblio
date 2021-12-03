<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Gestion des Livres</title>
	<link rel="stylesheet" href="./biblio.css">
</head>

<body>

    <h2>Gestion des Livres</h2>
    <br>
    
    <div class="ctn-main">
    
    	<table>
			<tr>
				<th>Code catalogue</th>
				<th>Titre du livre</th>
				<th>Auteur</th>
				<th>Genre</th>
				<th colspan="2">Action</th>
			</tr>
			<c:forEach var="livre" items="${Llivres}">
				<tr>
					<td><c:out value="${livre.codeCatalogue}"/></td>
					<td><c:out value="${livre.titre}"/></td>
					<td><c:out value="${livre.auteur.nom}  ${livre.auteur.prenom}"/></td>
					<td><c:out value="${livre.genre.nom}"/></td>
					<td><a href="ServletGestionLivre?action=modifier&codeCatalogue=${livre.codeCatalogue}">Modifier</a></td>
					<td><a href="#" onclick=confirmDelete("Livre","${livre.codeCatalogue}")>Supprimer</a></td>
				</tr>
			</c:forEach>
		</table>
		<br><hr>
		
		<form class="form-gestion" action="FormLivre.jsp" method="POST">
		    <button class="bouton" onclick="displayJsp(this,'Livre','ajouter');">Ajouter</button>
		    <button class="bouton" onclick="displayJsp(this,'Livre','rechercher');">Rechercher</button>
		</form>
          
    </div>
	<script src="./biblio.js"></script>
</body>
</html>