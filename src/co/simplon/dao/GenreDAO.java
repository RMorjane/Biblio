package co.simplon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import co.simplon.model.Genre;

public class GenreDAO implements DAO<Genre> {

	private DAOContext daoContext;
	
	public GenreDAO(DAOContext daoContext) {
		// TODO Auto-generated constructor stub
		this.daoContext = daoContext;
	}

	@Override
	public void ajouter(Genre genre) {
		// TODO Auto-generated method stub
		try {
			Connection db = daoContext.getConnection();
			PreparedStatement ps_insert = null;
			ps_insert = db.prepareStatement("INSERT INTO genre(nom) VALUES(?);");
			// la colonne auteurId est un entier auto incrémenté dans la table auteur
			ps_insert.setString(1, genre.getNom());
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
	public void modifier(Genre genre) {
		// TODO Auto-generated method stub
		try {
			Connection db = daoContext.getConnection();
			PreparedStatement ps_update = db.prepareStatement("UPDATE genre SET nom=? WHERE genreId=?;");
			ps_update.setString(1, genre.getNom());
			ps_update.setInt(2, genre.getGenreId());
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
	public void supprimer(Genre genre) {
		// TODO Auto-generated method stub
		try {
			Connection db = daoContext.getConnection();
			PreparedStatement ps_delete = db.prepareStatement("DELETE FROM genre WHERE genreId=?;");
			ps_delete.setInt(1, genre.getGenreId());
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
	public Genre getById(int id) {
		// TODO Auto-generated method stub
		if(id==0) return new Genre();
		else {
			try {
				Genre find = new Genre();
				find.setGenreId(id);
				List<Genre> Lgenres = this.rechercher(find);
				find = Lgenres.get(0);
				return find;				
			}
			catch(ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
				return null;
			}			
		}
	}

	@Override
	public Genre getById(String strId) {
		// TODO Auto-generated method stub
		if(strId.equals("")) return new Genre();
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
	public List<Genre> rechercher(Genre genre) {
		// TODO Auto-generated method stub
		List<Genre> Rgenres = new ArrayList<Genre>();
		try {
			Connection db = daoContext.getConnection();
			PreparedStatement ps_find = null;

			int id = genre.getGenreId();
			String nom = genre.getNom();
			
			StringBuffer query = new StringBuffer("SELECT * FROM genre ");
			
			if(id==0) { // le champ Id est vide (id==0) et les autres champs sont remplis ou vides
				query.append("WHERE nom like ?");
				ps_find = db.prepareStatement(query.toString());
				ps_find.setString(1,nom+"%");
			}
			else { // le champ Id est rempli avec une valeur entière  et les autres champs sont remplis ou vides
				query.append("WHERE genreId=? and nom like ?");
				ps_find = db.prepareStatement(query.toString());
				ps_find.setInt(1,id);
				ps_find.setString(2,nom+"%");				
			}
			
			ResultSet rs = ps_find.executeQuery();
			while(rs.next()) {
				id = rs.getInt("genreId");
				nom = rs.getString("nom");
				Genre rech = new Genre();
				rech.setGenreId(id);
				rech.setNom(nom);
				Rgenres.add(rech);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return Rgenres;
	}

	@Override
	public List<Genre> lister() {
		// TODO Auto-generated method stub
		List<Genre> Lgenres = new ArrayList<Genre>();
		try {
			Connection db = daoContext.getConnection();
			Statement ps_select = db.createStatement();
			ResultSet rs = ps_select.executeQuery("SELECT * FROM genre;");
			while(rs.next()) {
				int id = rs.getInt("genreId");
				String nom = rs.getString("nom");
				Genre genre = new Genre();
				genre.setGenreId(id);
				genre.setNom(nom);
				Lgenres.add(genre);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}		
		return Lgenres;
	}
}
