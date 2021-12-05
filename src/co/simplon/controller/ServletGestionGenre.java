package co.simplon.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.simplon.dao.*;
import co.simplon.model.*;

/**
 * Servlet implementation class ServletGestionGenre
 */
//@WebServlet("/ServletGestionGenre")
public class ServletGestionGenre extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nom;
	private DAO<Genre> genreDao;
	private String action;
	private Genre genre = null;
	private List<Genre> Lgenres = null;
	
	private boolean executed = false;
       
 	/**
	 * @see Servlet#init()
	 */
	public void init() throws ServletException {
		// TODO Auto-generated method stub
    	this.genreDao = DAOContext.getGenreDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		action = request.getParameter("action");
		if(action==null) { // on affiche la liste des genres à l'initialisation de la Servlet
			Lgenres = genreDao.lister();		
			request.setAttribute("Lgenres", Lgenres);
			this.getServletContext().getRequestDispatcher("/GestionGenres.jsp").forward(request,response);			
		}
		else {
			System.out.println("get : "+action);
			
			if(action.equals("modifier")) {
				System.out.println("get : "+executed);
				
				if(executed) { // on affiche la liste des genres lorsque la modification est déjà éxecutée
					Lgenres = genreDao.lister();		
					request.setAttribute("Lgenres", Lgenres);
					this.getServletContext().getRequestDispatcher("/GestionGenres.jsp").forward(request,response);
					executed = !executed;
				}
				else { // sinon on affiche les informations de l'genre sur le formulaire FormGenre.jsp
					String strId = request.getParameter("id");
					if(!strId.equals("")) {
						
						// on récupère l'genre à modifier par son id
						genre = (Genre)genreDao.getById(strId);
						
						// si l'genre est trouvé, on redirrige les informations de l'genre sur le formulaire FormGenre.jsp
						if(genre!=null) {
							request.setAttribute("genre", genre);
							this.getServletContext().getRequestDispatcher("/FormGenre.jsp?action="+action).forward(request,response);							
						}
					}					
				}				
			}
			else if(action.equals("supprimer")) {
				String strId = request.getParameter("id");
				if(!strId.equals("")) {
					
					// on récupère l'genre à supprimer par son id
					genre = (Genre)genreDao.getById(strId);
					
					// si l'genre est trouvé, on le supprime de la table genre
					if(genre!=null) {
						genreDao.supprimer(genre);
					}
					
					// on raffraichit l'affichage de la liste des genres
					Lgenres = genreDao.lister();		
					request.setAttribute("Lgenres", Lgenres);
					this.getServletContext().getRequestDispatcher("/GestionGenres.jsp").forward(request,response);
				}			
			}
			else if(action.equals("rechercher")) { // on affiche la liste des genres recherchés
				request.setAttribute("Lgenres", Lgenres);
				this.getServletContext().getRequestDispatcher("/GestionGenres.jsp").forward(request,response);				
			}
			// on affiche la liste des genres lorsqu'on clique sur le bouton Acceuil ou après l'ajout d'un genre
			else if(action.equals("acceuil")||action.equals("ajouter")) {
				Lgenres = genreDao.lister();		
				request.setAttribute("Lgenres", Lgenres);
				this.getServletContext().getRequestDispatcher("/GestionGenres.jsp").forward(request,response);				
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String strId = request.getParameter("id");
		nom = request.getParameter("nom");
		action = request.getParameter("action");
		
		System.out.println("post : "+action);
		
		genre = new Genre();
		genre.setNom(nom);
		strId = request.getParameter("id");
		if(!strId.equals("")) {
			try {
				id = Integer.parseInt(strId);
				genre.setGenreId(id);					
			}
			catch(NumberFormatException e) {
				e.printStackTrace();
			}
		}		
				
		if(action.equals("ajouter")) {
			genreDao.ajouter(genre);
		}
		else if(action.equals("rechercher")) {
			Lgenres = genreDao.rechercher(genre);
		}
		else if(action.equals("modifier")) {
			genreDao.modifier(genre);
			executed = !executed;
			
			System.out.println("post : "+executed);
		}
		doGet(request, response);
	}

}