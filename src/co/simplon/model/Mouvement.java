package co.simplon.model;

import java.util.Date;

public class Mouvement {

	private int mouvementId;
	private Date dateMouvement,dateUsure;
	private EtatUsure etatUsure;
	private TypeMouvement typeMouvement;
	private Adherent adherent;
	private Exemplaire exemplaire;
	
	public Mouvement() {
		// TODO Auto-generated constructor stub
	}

	public int getMouvementId() {
		return mouvementId;
	}

	public void setMouvementId(int mouvementId) {
		this.mouvementId = mouvementId;
	}

	public Date getDateMouvement() {
		return dateMouvement;
	}

	public void setDateMouvement(Date dateMouvement) {
		this.dateMouvement = dateMouvement;
	}

	public Date getDateUsure() {
		return dateUsure;
	}

	public void setDateUsure(Date dateUsure) {
		this.dateUsure = dateUsure;
	}

	public EtatUsure getEtatUsure() {
		return etatUsure;
	}

	public void setEtatUsure(EtatUsure etatUsure) {
		this.etatUsure = etatUsure;
	}

	public TypeMouvement getTypeMouvement() {
		return typeMouvement;
	}

	public void setTypeMouvement(TypeMouvement typeMouvement) {
		this.typeMouvement = typeMouvement;
	}

	public Adherent getAdherent() {
		return adherent;
	}

	public void setAdherent(Adherent adherent) {
		this.adherent = adherent;
	}

	public Exemplaire getExemplaire() {
		return exemplaire;
	}

	public void setExemplaire(Exemplaire exemplaire) {
		this.exemplaire = exemplaire;
	}

}
