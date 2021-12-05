package co.simplon.dao;

import co.simplon.model.*;
import java.sql.*;

public class DAOContext {

	public static Connection getConnection() throws SQLException,ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblio?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
		db.setAutoCommit(true);
		return db;
	}
	
	public static DAO<Auteur> getAuteurDAO() {
		return new AuteurDAO();
	}	
	
	public static DAO<Genre> getGenreDAO() {
		return new GenreDAO();
	}	
	
	public static DAO<Livre> getLivreDAO() {
		return new LivreDAO();
	}
	
	public static DAO<Editeur> getEditeurDAO() {
		return new EditeurDAO();
	}
}
