let label_id = document.getElementById("label_id");
let id = document.getElementById("id");
let nom = document.getElementById("nom");
let prenom = document.getElementById("prenom");
let codeCatalogue = document.getElementById("codeCatalogue");
let titre = document.getElementById("titre");

let auteur = document.getElementById("auteur");
let genre = document.getElementById("genre");

function $_GET(param) {
	var vars = {};
	window.location.href.replace( location.hash, '' ).replace( 
		/[?&]+([^=&]+)=?([^&]*)?/gi, // regexp
		function( m, key, value ) { // callback
			vars[key] = value !== undefined ? value : '';
		}
	);

	if ( param ) {
		return vars[param] ? vars[param] : null;	
	}
	return vars;
}

function confirmDelete(classe,id){
	let text;
	let url;
	switch(classe){
		case 'Auteur':
			text = "Etes - vous sure de vouloir supprimer cet Auteur ?";
			url = "ServletGestionAuteur?action=supprimer&id="+id;
			break;
		case 'Genre':
			text = "Etes - vous sure de vouloir supprimer ce Genre ?";
			url = "ServletGestionGenre?action=supprimer&id="+id;
			break;
		case 'Editeur':
			text = "Etes - vous sure de vouloir supprimer cet Editeur ?";
			url = "ServletGestionEditeur?action=supprimer&id="+id;
			break;
		case 'Livre':
			text = "Etes - vous sure de vouloir supprimer ce Livre ?";
			url = "ServletGestionLivre?action=supprimer&codeCatalogue="+id;
			break;
	}	
    var r = confirm(text);
    if(r) window.location.href = url;
}

function saisieNonValide(classe,action){
	if(action=="ajouter" || action=="modifier"){
		switch(classe){
			case 'Auteur':
				return nom.value.trim()=="" || prenom.value.trim()=="";
				break;
			case 'Genre': case 'Editeur':
				return nom.value.trim()=="";
				break;
			case 'Livre':
				return codeCatalogue.value.trim()=="" || titre.value.trim()=="" || auteur.value.trim()=="" || genre.value.trim()=="";
				break;
		}
    }
    else if(action=="rechercher"){
    	if(classe=="Livre"){
    		return false;
    	}else{
    		switch(classe){
    			case 'Auteur': case 'Genre':
    		        let test = Number(id.value.trim());
    	        	return !(test==id.value.trim());
    	        	break;
    			case 'Livre':
    				return false;
    		}   		
    	}
    }
}

function submitForm(objet,classe,action){
    if(saisieNonValide(classe,action)){
    	if(action=="ajouter" || action=="modifier"){
    		alert("Veuillez saisir tous les champs svp!!!");
    	}
    	else if(action=="rechercher"){
    		alert("Veuillez saisir un nombre entier pour l'identifiant id svp!!!");
    	}
    }else{
    	switch(classe){
    		case 'Auteur': case 'Genre': case 'Editeur':
    			objet.form.action="ServletGestion"+classe+"?action="+action;
    			break;
    		case 'Livre':
    			let auteurId = auteur.value;
    			let genreId = genre.value;
    	        objet.form.action="ServletGestionLivre?action="+action+"&auteurId="+auteurId+"&genreId="+genreId;
    	        break;
    	}
        objet.form.submit();    	
    }
}

function initForm(classe,action,auteurId,genreId){
	switch(classe){
		case 'Auteur': case 'Genre': case 'Editeur':
			if(action=='rechercher'){
		        label_id.style.display = 'inline-block'
		        id.style.display = 'inline-block'				
			}else{
		    	label_id.style.display = "none"
			    id.style.display = "none"				
			}
			break;
		case 'Livre':
			if(action=='modifier'){
		    	label_id.style.display = "none"
		    	codeCatalogue.style.display = "none"
		    	auteur.value = auteurId
		    	genre.value = genreId
			}else if(action=='ajouter'){
		        label_id.style.display = 'inline-block'
			    codeCatalogue.style.display = 'inline-block'					
			}
			break;			
	}
}

function displayJsp(objet,classe,action){
	objet.form.action="Form"+classe+".jsp?action="+action;
	objet.form.submit();
}