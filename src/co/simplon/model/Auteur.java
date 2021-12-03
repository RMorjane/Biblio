package co.simplon.model;

public class Auteur {

	private int auteurId;
	private String nom,prenom;
	
	public Auteur() {
		// TODO Auto-generated constructor stub
		this.nom = "";
		this.prenom = "";
	}

	public int getAuteurId() {
		return auteurId;
	}

	public void setAuteurId(int auteurId) {
		this.auteurId = auteurId;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public String toString() {
		return auteurId + " - " + nom + " - " + prenom;
	}

}
