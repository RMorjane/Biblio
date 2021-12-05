package co.simplon.model;

public class Genre {

	private int genreId;
	private String nom;
	
	public Genre() {
		// TODO Auto-generated constructor stub
		this.nom = "";
	}

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return genreId + " - " + nom;
	}

}
