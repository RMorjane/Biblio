package co.simplon.model;

public class Livre {

	private String codeCatalogue,titre;
	private Auteur auteur = null;
	private Genre genre = null;
	
	public Livre() {
		// TODO Auto-generated constructor stub
		this.codeCatalogue = "";
		this.titre = "";
	}

	public String getCodeCatalogue() {
		return codeCatalogue;
	}

	public void setCodeCatalogue(String codeCatalogue) {
		this.codeCatalogue = codeCatalogue;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return codeCatalogue + " - " + titre + " - " + auteur + " - " + genre;
	}

}
