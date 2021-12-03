package co.simplon.dao;

import java.sql.*;

public class DAOContext {

	public static Connection getConnection() throws SQLException,ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblio?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
		db.setAutoCommit(true);
		return db;
	}
	
	public DAO getAuteurDAO() {
		return new AuteurDAO(this);
	}	
	
	public DAO getGenreDAO() {
		return new GenreDAO(this);
	}	
	
	public DAO getLivreDAO() {
		return new LivreDAO(this);
	}
	
	public DAO getEditeurDAO() {
		return new EditeurDAO(this);
	}
}
