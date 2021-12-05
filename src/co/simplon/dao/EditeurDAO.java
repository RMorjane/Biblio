package co.simplon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import co.simplon.model.Editeur;

public class EditeurDAO implements DAO<Editeur> {
	
	public EditeurDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ajouter(Editeur editeur) {
		// TODO Auto-generated method stub
		try {
			Connection db = DAOContext.getConnection();
			PreparedStatement ps_insert = null;
			ps_insert = db.prepareStatement("INSERT INTO editeur(nom) VALUES(?);");
			// la colonne auteurId est un entier auto incrémenté dans la table auteur
			ps_insert.setString(1, editeur.getNom());
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
	public void modifier(Editeur editeur) {
		// TODO Auto-generated method stub
		try {
			Connection db = DAOContext.getConnection();
			PreparedStatement ps_update = db.prepareStatement("UPDATE editeur SET nom=? WHERE editeurId=?;");
			ps_update.setString(1, editeur.getNom());
			ps_update.setInt(2, editeur.getEditeurId());
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
	public void supprimer(Editeur editeur) {
		// TODO Auto-generated method stub
		try {
			Connection db = DAOContext.getConnection();
			PreparedStatement ps_delete = db.prepareStatement("DELETE FROM editeur WHERE editeurId=?;");
			ps_delete.setInt(1, editeur.getEditeurId());
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
	public Editeur getById(int id) {
		// TODO Auto-generated method stub
		if(id==0) return new Editeur();
		else {
			try {
				Editeur find = new Editeur();
				find.setEditeurId(id);
				List<Editeur> Lediteurs = this.rechercher(find);
				find = Lediteurs.get(0);
				return find;				
			}
			catch(ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
				return null;
			}			
		}
	}

	@Override
	public Editeur getById(String strId) {
		// TODO Auto-generated method stub
		if(strId.equals("")) return new Editeur();
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
	public List<Editeur> rechercher(Editeur editeur) {
		// TODO Auto-generated method stub
		List<Editeur> Rediteurs = new ArrayList<Editeur>();
		try {
			Connection db = DAOContext.getConnection();
			PreparedStatement ps_find = null;

			int id = editeur.getEditeurId();
			String nom = editeur.getNom();
			
			StringBuffer query = new StringBuffer("SELECT * FROM editeur ");
			
			System.out.println("Id de l'Editeur : "+id);
			
			if(id==0) { // le champ Id est vide (id==0) et les autres champs sont remplis ou vides
				query.append("WHERE nom like ?");
				ps_find = db.prepareStatement(query.toString());
				ps_find.setString(1,nom+"%");
			}
			else { // le champ Id est rempli avec une valeur entière  et les autres champs sont remplis ou vides
				query.append("WHERE editeurId=? and nom like ?");
				ps_find = db.prepareStatement(query.toString());
				ps_find.setInt(1,id);
				ps_find.setString(2,nom+"%");				
			}
			
			ResultSet rs = ps_find.executeQuery();
			while(rs.next()) {
				System.out.println("trouvé");
				id = rs.getInt("editeurId");
				nom = rs.getString("nom");
				Editeur rech = new Editeur();
				rech.setEditeurId(id);
				rech.setNom(nom);
				Rediteurs.add(rech);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return Rediteurs;
	}

	@Override
	public List<Editeur> lister() {
		// TODO Auto-generated method stub
		List<Editeur> Lediteurs = new ArrayList<Editeur>();
		try {
			Connection db = DAOContext.getConnection();
			Statement ps_select = db.createStatement();
			ResultSet rs = ps_select.executeQuery("SELECT * FROM editeur;");
			while(rs.next()) {
				int id = rs.getInt("editeurId");
				String nom = rs.getString("nom");
				Editeur editeur = new Editeur();
				editeur.setEditeurId(id);
				editeur.setNom(nom);
				Lediteurs.add(editeur);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}		
		return Lediteurs;
	}
}