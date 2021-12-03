<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Gestion des Auteurs</title>
	<link rel="stylesheet" href="./biblio.css">
</head>

<body>

    <h2>Gestion des Auteurs</h2>
    <br>

    <div class="ctn-main">
    
    	<table>
			<tr>
				<th>Id</th>
				<th>Nom</th>
				<th>Prénom</th>
				<th colspan="2">Action</th>
			</tr>
			<c:forEach var="auteur" items="${Lauteurs}">
				<tr>
					<td><c:out value="${auteur.auteurId}"/></td>
					<td><c:out value="${auteur.nom}"/></td>
					<td><c:out value="${auteur.prenom}"/></td>
					<td><a href="ServletGestionAuteur?action=modifier&id=${auteur.auteurId}">Modifier</a></td>
					<td><a href="#" onclick=confirmDelete("Auteur","${auteur.auteurId}")>Supprimer</a></td>
				</tr>
			</c:forEach>
		</table>
		<br><hr>
		
		<form class="form-gestion" action="FormAuteur.jsp" method="POST">
		    <button class="bouton" onclick="displayJsp(this,'Auteur','ajouter');">Ajouter</button>
		    <button class="bouton" onclick="displayJsp(this,'Auteur','rechercher');">Rechercher</button>
		</form>
          
    </div>
    
	<script src="./biblio.js"></script>
</body>
</html>