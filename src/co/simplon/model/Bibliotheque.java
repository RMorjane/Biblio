package co.simplon.model;

import java.util.List;
import java.util.ArrayList;

public class Bibliotheque {

	private String designation,adresse;
	private List<Adherent> listAdherents = new ArrayList<Adherent>();
	
	public Bibliotheque() {
		// TODO Auto-generated constructor stub
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public void inscrire(Adherent adherent) {
		listAdherents.add(adherent);
	}	
	
	public void desinscrire(Adherent adherent) {
		listAdherents.remove(adherent);
	}
}
