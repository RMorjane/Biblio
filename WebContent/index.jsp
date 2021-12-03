<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Biblio</title>
    <link rel="stylesheet" href="./main.css">
</head>
<body background="./biblio.jpg">

    <div class="main-nav">
        <div class="logo"></div>
        <ul class="nav-links">
            <li><a href="index.jsp">Home</a></li>
            <li><a href="index.jsp?bean=Auteur">Auteur</a></li>
            <li><a href="index.jsp?bean=Genre">Genre</a></li>
            <li><a href="index.jsp?bean=Editeur">Editeur</a></li>
            <li><a href="index.jsp?bean=Livre">Livre</a></li>
       </ul>
       <div id="burger" class="burger">
            <div class="line line1"></div>
            <div class="line line2"></div>
            <div class="line line3"></div>
       </div>
    </div>
    
	<iframe id="ctn" name="containt" src="" frameborder="0" allowfullscreen></iframe>
	
	<script src="./main.js"></script>
</body>

</html>