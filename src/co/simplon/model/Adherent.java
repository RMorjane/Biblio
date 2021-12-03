package co.simplon.model;

import java.util.Date;

public class Adherent {

	private int adherentId;
	private String nom,prenom,adresseMail,adressePostale;
	private Date dateNaissance,dateAdhesion;
	private CategoriePro categoriePro;
	
	public Adherent() {
		// TODO Auto-generated constructor stub
	}

	public int getAdherentId() {
		return adherentId;
	}

	public void setAdherentId(int adherentId) {
		this.adherentId = adherentId;
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

	public String getAdresseMail() {
		return adresseMail;
	}

	public void setAdresseMail(String adresseMail) {
		this.adresseMail = adresseMail;
	}

	public String getAdressePostale() {
		return adressePostale;
	}

	public void setAdressePostale(String adressePostale) {
		this.adressePostale = adressePostale;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Date getDateAdhesion() {
		return dateAdhesion;
	}

	public void setDateAdhesion(Date dateAdhesion) {
		this.dateAdhesion = dateAdhesion;
	}

	public CategoriePro getCategoriePro() {
		return categoriePro;
	}

	public void setCategoriePro(CategoriePro categoriePro) {
		this.categoriePro = categoriePro;
	}

}
