package co.simplon.dao;

import java.util.List;

public interface DAO<T> {

	public void ajouter(T objet);
	public void modifier(T objet);
	public void supprimer(T objet);
	public T getById(int id);
	public T getById(String strId);
	public List<T> rechercher(T objet);
	public List<T> lister();
	
}
