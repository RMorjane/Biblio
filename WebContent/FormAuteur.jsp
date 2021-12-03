<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire</title>
    <link rel="stylesheet" href="./biblio.css">
</head>

<body onload="initForm('Auteur','${param.action}');">
    <h2>
	    <c:set var="action" scope="session" value="${param.action}"/>
	    <c:choose>
	    	<c:when test="${action=='ajouter'}">
	    		<c:out value="Ajout d'un Auteur"/>
	    	</c:when>
	    	<c:when test="${action=='modifier'}">
	    		<c:out value="Modification d'un Auteur"/>
	    	</c:when>
	    	<c:when test="${action=='rechercher'}">
	    		<c:out value="Recherche des Auteurs"/>
	    	</c:when>
	    </c:choose>
    </h2>
    <br>
    
	<form action="GestionAuteurs.jsp" method="POST">

		<div class="field">
			<label id="label_id" for="id">Id : </label>
			<input class="field" id="id" type="text" name="id" value="${auteur.auteurId}">		
	    </div><br>
	    
	    <div class="field">   
			<label for="nom">Nom : </label>
			<input class="field" id="nom" type="text" name="nom" value="${auteur.nom}">		
	    </div><br>
	     
	    <div class="field">   
			<label for="prenom">Prénom : </label>
			<input class="field" id="prenom" type="text" name="prenom" value="${auteur.prenom}">		
	    </div><br>
	    
	    <div class="field">   
        	<input class="bouton" type="button" value="Valider" onclick="submitForm(this,'Auteur','${param.action}');"/>
        	<input class="bouton" type="button" value="Acceuil" onclick="submitForm(this,'Auteur','acceuil');"/>
        </div>
	                	
	</form>
	       
	<script src="./biblio.js"></script>
</body>
</html>