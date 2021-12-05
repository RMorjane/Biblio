<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Gestion des Editeurs</title>
	<link rel="stylesheet" href="./biblio.css">
</head>

<body>

    <h2>Gestion des Editeurs</h2>
    <br>
    
    <div class="ctn-main">
    
    	<table>
			<tr>
				<th>Id</th>
				<th>Nom</th>
				<th colspan="2">Action</th>
			</tr>
			<c:forEach var="editeur" items="${Lediteurs}">
				<tr>
					<td><c:out value="${editeur.editeurId}"/></td>
					<td><c:out value="${editeur.nom}"/></td>
					<td><a href="ServletGestionEditeur?action=modifier&id=${editeur.editeurId}">Modifier</a></td>
					<td><a href="#" onclick=confirmDelete("Editeur","${editeur.editeurId}")>Supprimer</a></td>
				</tr>
			</c:forEach>
		</table>
		<br><hr>
		
		<form class="form-gestion" action="FormEditeur.jsp" method="POST">
		    <button class="bouton" onclick="displayJsp(this,'Editeur','ajouter');">Ajouter</button>
		    <button class="bouton" onclick="displayJsp(this,'Editeur','rechercher');">Rechercher</button>
		</form>
          
    </div>
	<script src="./biblio.js"></script>
</body>
</html>