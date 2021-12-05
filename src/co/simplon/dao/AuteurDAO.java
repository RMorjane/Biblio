package co.simplon.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import co.simplon.model.Auteur;

public class AuteurDAO implements DAO<Auteur> {
	
	public AuteurDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ajouter(Auteur auteur) {
		// TODO Auto-generated method stub
		try {
			Connection db = DAOContext.getConnection();
			PreparedStatement ps_insert = null;
			ps_insert = db.prepareStatement("INSERT INTO auteur(nom,prenom) VALUES(?,?);");
			// la colonne auteurId est un entier auto incrémenté dans la table auteur
			ps_insert.setString(1, auteur.getNom());
			ps_insert.setString(2, auteur.getPrenom());
			ps_insert.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modifier(Auteur auteur) {
		// TODO Auto-generated method stub
		try {
			Connection db = DAOContext.getConnection();
			PreparedStatement ps_update = db.prepareStatement("UPDATE auteur SET nom=?,prenom=? WHERE auteurId=?;");
			ps_update.setString(1, auteur.getNom());
			ps_update.setString(2, auteur.getPrenom());
			ps_update.setInt(3, auteur.getAuteurId());
			ps_update.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void supprimer(Auteur auteur) {
		// TODO Auto-generated method stub
		try {
			Connection db = DAOContext.getConnection();
			PreparedStatement ps_delete = db.prepareStatement("DELETE FROM auteur WHERE auteurId=?;");
			ps_delete.setInt(1, auteur.getAuteurId());
			ps_delete.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Auteur> rechercher(Auteur auteur) {
		// TODO Auto-generated method stub
		List<Auteur> Rauteurs = new ArrayList<Auteur>();
		try {
			Connection db = DAOContext.getConnection();
			PreparedStatement ps_find = null;

			int id = auteur.getAuteurId();
			String nom = auteur.getNom();
			String prenom = auteur.getPrenom();
			
			StringBuffer query = new StringBuffer("SELECT * FROM auteur ");
			
			if(id==0) { // le champ Id est vide (id==0) et les autres champs sont remplis ou vides
				query.append("WHERE nom like ? and prenom like ?");
				ps_find = db.prepareStatement(query.toString());
				ps_find.setString(1,nom+"%");
				ps_find.setString(2,prenom+"%");
			}
			else { // le champ Id est rempli avec une valeur entière  et les autres champs sont remplis ou vides
				query.append("WHERE auteurId=? and nom like ? and prenom like ?");
				ps_find = db.prepareStatement(query.toString());
				ps_find.setInt(1,id);
				ps_find.setString(2,nom+"%");
				ps_find.setString(3,prenom+"%");				
			}
			
			ResultSet rs = ps_find.executeQuery();
			while(rs.next()) {
				id = rs.getInt("auteurId");
				nom = rs.getString("nom");
				prenom = rs.getString("prenom");
				Auteur rech = new Auteur();
				rech.setAuteurId(id);
				rech.setNom(nom);
				rech.setPrenom(prenom);
				Rauteurs.add(rech);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return Rauteurs;
	}

	@Override
	public List<Auteur> lister() {
		// TODO Auto-generated method stub
		List<Auteur> Lauteurs = new ArrayList<Auteur>();
		try {
			Connection db = DAOContext.getConnection();
			Statement ps_select = db.createStatement();
			ResultSet rs = ps_select.executeQuery("SELECT * FROM auteur;");
			while(rs.next()) {
				int id = rs.getInt("auteurId");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				Auteur auteur = new Auteur();
				auteur.setAuteurId(id);
				auteur.setNom(nom);
				auteur.setPrenom(prenom);
				Lauteurs.add(auteur);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}		
		return Lauteurs;
	}

	@Override
	public Auteur getById(String strId) {
		// TODO Auto-generated method stub
		if(strId.equals("")) {
			return new Auteur();
		}
		else {
			try {
				int id = Integer.parseInt(strId);
				return this.getById(id);
			}
			catch(NumberFormatException e) {
				e.printStackTrace();
				return null;
			}			
		}
	}

	@Override
	public Auteur getById(int id) {
		// TODO Auto-generated method stub
		if(id==0) return new Auteur();
		else {
			try {
				Auteur find = new Auteur();
				find.setAuteurId(id);
				List<Auteur> Lauteurs = this.rechercher(find);
				find = Lauteurs.get(0);
				return find;				
			}
			catch(ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
				return null;
			}			
		}
	}

}