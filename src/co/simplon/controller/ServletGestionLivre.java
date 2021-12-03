package co.simplon.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.simplon.dao.DAO;
import co.simplon.dao.DAOContext;
import co.simplon.model.Auteur;
import co.simplon.model.Genre;
import co.simplon.model.Livre;

/**
 * Servlet implementation class ServletGestionLivre
 */
public class ServletGestionLivre extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String codeCatalogue,titre,strAuteurId,strGenreId;
	private Livre livre = null;
	private Auteur auteur = null;
	private Genre genre = null;
	private DAO livreDao,auteurDao,genreDao;
	private String action;
	private List<Livre> Llivres = null;
	private List<Auteur> Lauteurs = null;
	private List<Genre> Lgenres = null;
	
	private boolean executed = false;
       
 	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		// TODO Auto-generated method stub
    	DAOContext daoContext = new DAOContext();
    	this.livreDao = daoContext.getLivreDAO();
    	this.auteurDao = daoContext.getAuteurDAO();
    	this.genreDao = daoContext.getGenreDAO();
    	Lauteurs = auteurDao.lister();
    	Lgenres = genreDao.lister();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		action = request.getParameter("action");
		if(action==null) { // on affiche la liste des livres à l'initialisation de la Servlet
			Llivres = livreDao.lister();		
			request.setAttribute("Llivres", Llivres);
			this.getServletContext().getRequestDispatcher("/GestionLivres.jsp").forward(request,response);			
		}
		else {
			System.out.println("get : "+action);
			
			if(action.equals("modifier")) {
				System.out.println("get : "+executed);
				
				if(executed) { // on affiche la liste des livres lorsque la modification est déjà éxecutée
					Llivres = livreDao.lister();		
					request.setAttribute("Llivres", Llivres);
					this.getServletContext().getRequestDispatcher("/GestionLivres.jsp").forward(request,response);
					executed = !executed;
				}
				else { // sinon on affiche les informations du livre sur le formulaire FormLivre.jsp
					codeCatalogue = request.getParameter("codeCatalogue");
					System.out.println(codeCatalogue);
					if(!codeCatalogue.equals("")) {
						
						// on récupère le livre à modifier par son code catalogue
						livre = (Livre)livreDao.getById(codeCatalogue);
						
						System.out.println(livre);
						
						// si le livre est trouvé, on redirrige les informations du livre sur le formulaire FormLivre.jsp
						if(livre!=null) {
							int auteurId = livre.getAuteur().getAuteurId();
							int genreId = livre.getGenre().getGenreId();
							System.out.println("auteur : "+auteurId);
							System.out.println("genre : "+genreId);
							request.setAttribute("livre", livre);
							request.setAttribute("Lauteurs", Lauteurs);
							request.setAttribute("Lgenres", Lgenres);
							this.getServletContext().getRequestDispatcher("/FormLivre.jsp?action="+action+"&auteurId="+auteurId+"&genreId="+genreId).forward(request,response);							
						}
					}					
				}				
			}
			else if(action.equals("supprimer")) {
				codeCatalogue = request.getParameter("codeCatalogue");
				if(!codeCatalogue.equals("")) {
					
					// on récupère le livre à supprimer par son code catalogue
					livre = new Livre();
					livre.setCodeCatalogue(codeCatalogue);
					livreDao.supprimer(livre);
					
					// on raffraichit l'affichage de la liste des livres
					Llivres = livreDao.lister();		
					request.setAttribute("Llivres", Llivres);
					this.getServletContext().getRequestDispatcher("/GestionLivres.jsp").forward(request,response);
				}			
			}
			else if(action.equals("rechercher")) { // on affiche la liste des livres recherchés
				request.setAttribute("Llivres", Llivres);
				this.getServletContext().getRequestDispatcher("/GestionLivres.jsp").forward(request,response);				
			}
			// on affiche la liste des genres lorsqu'on clique sur le bouton Acceuil ou après l'ajout d'un livre
			else if(action.equals("acceuil") || action.equals("ajouter")) {
				Llivres = livreDao.lister();		
				request.setAttribute("Llivres", Llivres);
				this.getServletContext().getRequestDispatcher("/GestionLivres.jsp").forward(request,response);				
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		codeCatalogue = request.getParameter("codeCatalogue");
		titre = request.getParameter("titre");
		strAuteurId = request.getParameter("auteurId");
		strGenreId = request.getParameter("genreId");
		action = request.getParameter("action");
		
		System.out.println("post : "+action);
		System.out.println("code catalogue : "+codeCatalogue);
		System.out.println("titre : "+titre);
		
		livre = new Livre();
		livre.setCodeCatalogue(codeCatalogue);
		livre.setTitre(titre);
		
		auteur = (Auteur)auteurDao.getById(strAuteurId);
		livre.setAuteur(auteur);
		
		genre = (Genre)genreDao.getById(strGenreId);
		livre.setGenre(genre);
		
		System.out.println("auteur : "+auteur.getAuteurId());
		System.out.println("genre : "+genre.getGenreId());
				
		if(action.equals("ajouter")) {			
			livreDao.ajouter(livre);
		}
		else if(action.equals("rechercher")) {
			Llivres = livreDao.rechercher(livre);
		}
		else if(action.equals("modifier")) {
			livreDao.modifier(livre);
			executed = !executed;
			
			System.out.println("post : "+executed);
		}
		doGet(request, response);
	}
}