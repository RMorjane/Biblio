package co.simplon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import co.simplon.model.Auteur;
import co.simplon.model.Genre;
import co.simplon.model.Livre;

public class LivreDAO implements DAO<Livre> {

	public LivreDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ajouter(Livre livre) {
		// TODO Auto-generated method stub
		try {
			Connection db = DAOContext.getConnection();
			PreparedStatement ps_insert = null;
			ps_insert = db.prepareStatement("INSERT INTO livre(codeCatalogue,titre,auteurId,genreId) VALUES(?,?,?,?);");
			ps_insert.setString(1, livre.getCodeCatalogue());
			ps_insert.setString(2, livre.getTitre());
			ps_insert.setInt(3,livre.getAuteur().getAuteurId());
			ps_insert.setInt(4,livre.getGenre().getGenreId());
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
	public void modifier(Livre livre) {
		// TODO Auto-generated method stub
		try {
			Connection db = DAOContext.getConnection();
			PreparedStatement ps_update = db.prepareStatement("UPDATE livre SET titre=?,auteurId=?,genreId=? WHERE codeCatalogue=?;");
			ps_update.setString(1, livre.getTitre());
			ps_update.setInt(2, livre.getAuteur().getAuteurId());
			ps_update.setInt(3, livre.getGenre().getGenreId());
			ps_update.setString(4, livre.getCodeCatalogue());
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
	public void supprimer(Livre livre) {
		// TODO Auto-generated method stub
		try {
			Connection db = DAOContext.getConnection();
			PreparedStatement ps_delete = db.prepareStatement("DELETE FROM livre WHERE codeCatalogue=?;");
			ps_delete.setString(1, livre.getCodeCatalogue());
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
	public List<Livre> rechercher(Livre livre) {
		// TODO Auto-generated method stub
		List<Livre> Rlivres = new ArrayList<Livre>();
		try {
			Connection db = DAOContext.getConnection();
			PreparedStatement ps_find = null;

			String codeCatalogue = livre.getCodeCatalogue();
			String titre = livre.getTitre();
			
			int auteurId = 0;
			int genreId = 0;
			
			try {
				auteurId = livre.getAuteur().getAuteurId();
				genreId = livre.getGenre().getGenreId();				
			}
			catch(NullPointerException e) {
				e.printStackTrace();
			}
			
			StringBuffer query = new StringBuffer("SELECT * FROM livre WHERE codeCatalogue like ? and titre like ? ");
			
			if(auteurId==0) {
				if(genreId==0) {
					ps_find = db.prepareStatement(query.toString());
					ps_find.setString(1,codeCatalogue+"%");
					ps_find.setString(2,titre+"%");					
				}
				else {
					query.append("and genreId=? ");
					ps_find = db.prepareStatement(query.toString());
					ps_find.setString(1,codeCatalogue+"%");
					ps_find.setString(2,titre+"%");
					ps_find.setInt(3, genreId);
				}
			}
			else {
				if(genreId==0) {
					query.append("and auteurId=? ");
					ps_find = db.prepareStatement(query.toString());
					ps_find.setString(1,codeCatalogue+"%");
					ps_find.setString(2,titre+"%");
					ps_find.setInt(3, auteurId);
				}
				else {
					query.append("and auteurId=? and genreId=? ");
					ps_find = db.prepareStatement(query.toString());
					ps_find.setString(1,codeCatalogue+"%");
					ps_find.setString(2,titre+"%");
					ps_find.setInt(3, auteurId);
					ps_find.setInt(4, genreId);
				}				
			}
			
			ResultSet rs = ps_find.executeQuery();
			while(rs.next()) {
				codeCatalogue = rs.getString("codeCatalogue");
				titre = rs.getString("titre");
				
				auteurId = rs.getInt("auteurId");
				Auteur auteur = (Auteur)DAOContext.getAuteurDAO().getById(auteurId);
				
				genreId = rs.getInt("genreId");
				Genre genre = (Genre)DAOContext.getGenreDAO().getById(genreId);
				
				Livre rech = new Livre();
				rech.setCodeCatalogue(codeCatalogue);
				rech.setTitre(titre);
				rech.setAuteur(auteur);
				rech.setGenre(genre);
				Rlivres.add(rech);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return Rlivres;
	}

	@Override
	public List<Livre> lister() {
		// TODO Auto-generated method stub
		List<Livre> Llivres = new ArrayList<Livre>();
		try {
			Connection db = DAOContext.getConnection();
			Statement ps_select = db.createStatement();
			ResultSet rs = ps_select.executeQuery("SELECT * FROM livre;");
			while(rs.next()) {
				String codeCatalogue = rs.getString("codeCatalogue");
				String titre = rs.getString("titre");
				int auteurId = rs.getInt("auteurId");
				int genreId = rs.getInt("genreId");
				Auteur auteur = (Auteur)DAOContext.getAuteurDAO().getById(auteurId);
				Genre genre = (Genre)DAOContext.getGenreDAO().getById(genreId);
				Livre livre = new Livre();
				livre.setCodeCatalogue(codeCatalogue);
				livre.setTitre(titre);
				livre.setAuteur(auteur);
				livre.setGenre(genre);
				Llivres.add(livre);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}		
		return Llivres;
	}

	@Override
	public Livre getById(String strId) {
		// TODO Auto-generated method stub
		if(strId.equals("")) return null;
		else {
			try {
				Livre find = new Livre();
				find.setCodeCatalogue(strId);
				List<Livre> Llivres = this.rechercher(find);
				find = Llivres.get(0);
				System.out.println(find);
				return find;				
			}
			catch(ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
				return null;
			}			
		}
	}

	@Override
	public Livre getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
