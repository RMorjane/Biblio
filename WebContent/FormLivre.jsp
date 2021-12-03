<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="co.simplon.dao.DAO"
    import="co.simplon.dao.DAOContext"
    import="java.util.List"
	import="co.simplon.model.Auteur"
	import="co.simplon.model.Genre"
	import="co.simplon.model.Livre"    
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire</title>
    <link rel="stylesheet" href="./biblio.css">
</head>

<body onload="initForm('Livre','${param.action}','${livre.auteur.auteurId}','${livre.genre.genreId}');">
	<%!
		 DAOContext daoContext = new DAOContext();
		 DAO auteurDao = daoContext.getAuteurDAO();
		 DAO genreDao = daoContext.getGenreDAO();
		 List<Auteur> Lauteurs = auteurDao.lister();
		 List<Genre> Lgenres = genreDao.lister();
	%>
	<%		
		request.setAttribute("Lauteurs", Lauteurs);
		request.setAttribute("Lgenres", Lgenres);
	%>
	
    <h2>
	    <c:set var="action" scope="session" value="${param.action}"/>
	    <c:choose>
	    	<c:when test="${action=='ajouter'}">
	    		<c:out value="Ajout d'un Livre"/>
	    	</c:when>
	    	<c:when test="${action=='modifier'}">
	    		<c:out value="Modification d'un Livre"/>
	    	</c:when>
	    	<c:when test="${action=='rechercher'}">
	    		<c:out value="Recherche des Livres"/>
	    	</c:when>
	    </c:choose>
    </h2>
    <br>
    
	<form action="GestionLivres.jsp" method="POST">

		<div class="field">
			<label id="label_id" for="codeCatalogue">Code catalogue : </label>
			<input class="field" id="codeCatalogue" type="text" name="codeCatalogue" value="${livre.codeCatalogue}">		
	    </div><br>
	    
	    <div class="field">    
			<label for="titre">Titre : </label>
			<input class="field" id="titre" type="text" name="titre" value="${livre.titre}">		
	    </div><br>
	    
	    <div class="field">    
			<label for="auteur">Auteur : </label>
		    <select name="auteur" id="auteur">
		        <option value=""></option>
				<c:forEach var="auteur" items="${Lauteurs}">
					<option value="${auteur.auteurId}"><c:out value="${auteur.nom}  ${auteur.prenom}"/></option>
				</c:forEach>
		    </select>		
	    </div><br>
	    
	    <div class="field">    
			<label for="genre">Genre : </label>
		    <select name="genre" id="genre">
		        <option value=""></option>
				<c:forEach var="genre" items="${Lgenres}">
					<option value="${genre.genreId}"><c:out value="${genre.nom}"/></option>
				</c:forEach>
		    </select>		
	    </div><br>
	    
	    <div class="field">    
        	<input class="bouton" type="button" value="Valider" onclick="submitForm(this,'Livre','${param.action}');"/>
        	<input class="bouton" type="button" value="Acceuil" onclick="submitForm(this,'Livre','acceuil');"/>
        </div>
	                	
	</form>
	       
	<script src="./biblio.js"></script>
</body>
</html>