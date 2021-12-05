package co.simplon.controller;

import co.simplon.dao.*;
import co.simplon.model.*;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletGestionAuteur
 */
//@WebServlet("/ServletGestionAuteur")
public class ServletGestionAuteur extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private int id;
	private String nom,prenom;
	private DAO<Auteur> auteurDao;
	private String action;
	private Auteur auteur = null;
	private List<Auteur> Lauteurs = null;
	
	private boolean executed = false;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGestionAuteur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		// TODO Auto-generated method stub
    	this.auteurDao = DAOContext.getAuteurDAO();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		action = request.getParameter("action");
		if(action==null) { // on affiche la liste des auteurs à l'initialisation de la Servlet
			Lauteurs = auteurDao.lister();		
			request.setAttribute("Lauteurs", Lauteurs);
			this.getServletContext().getRequestDispatcher("/GestionAuteurs.jsp").forward(request,response);			
		}
		else {
			System.out.println("get : "+action);
			
			if(action.equals("modifier")) {
				System.out.println("get : "+executed);
				
				if(executed) { // on affiche la liste des auteurs lorsque la modification est déjà éxecutée
					Lauteurs = auteurDao.lister();		
					request.setAttribute("Lauteurs", Lauteurs);
					this.getServletContext().getRequestDispatcher("/GestionAuteurs.jsp").forward(request,response);
					executed = !executed;
				}
				else { // sinon on affiche les informations de l'auteur sur le formulaire FormAuteur.jsp
					String strId = request.getParameter("id");
					if(!strId.equals("")) {
						
						// on récupère l'auteur à modifier par son id
						auteur = (Auteur)auteurDao.getById(strId);
						
						// si l'auteur est trouvé, on redirrige les informations de l'auteur sur le formulaire FormAuteur.jsp
						if(auteur!=null) {
							request.setAttribute("auteur", auteur);
							this.getServletContext().getRequestDispatcher("/FormAuteur.jsp?action="+action).forward(request,response);							
						}
					}					
				}				
			}
			else if(action.equals("supprimer")) {
				String strId = request.getParameter("id");
				if(!strId.equals("")) {
					
					// on récupère l'auteur à supprimer par son id
					auteur = (Auteur)auteurDao.getById(strId);
					
					// si l'auteur est trouvé, on le supprime de la table auteur
					if(auteur!=null) {
						auteurDao.supprimer(auteur);
					}
					
					// on raffraichit l'affichage de la liste des auteurs
					Lauteurs = auteurDao.lister();		
					request.setAttribute("Lauteurs", Lauteurs);
					this.getServletContext().getRequestDispatcher("/GestionAuteurs.jsp").forward(request,response);
				}			
			}
			else if(action.equals("rechercher")) { // on affiche la liste des auteurs recherchés
				request.setAttribute("Lauteurs", Lauteurs);
				this.getServletContext().getRequestDispatcher("/GestionAuteurs.jsp").forward(request,response);				
			}
			// on affiche la liste des auteurs lorsqu'on clique sur le bouton Acceuil ou après l'ajout d'un auteur
			else if(action.equals("acceuil")||action.equals("ajouter")) {
				Lauteurs = auteurDao.lister();		
				request.setAttribute("Lauteurs", Lauteurs);
				this.getServletContext().getRequestDispatcher("/GestionAuteurs.jsp").forward(request,response);				
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		nom = request.getParameter("nom");
		prenom = request.getParameter("prenom");
		action = request.getParameter("action");
		
		System.out.println("post : "+action);
		
		auteur = new Auteur();
		auteur.setNom(nom);
		auteur.setPrenom(prenom);
		String strId = request.getParameter("id");
		if(!strId.equals("")) {
			try {
				id = Integer.parseInt(strId);
				auteur.setAuteurId(id);					
			}
			catch(NumberFormatException e) {
				e.printStackTrace();
			}
		}
				
		if(action.equals("ajouter")) {
			auteurDao.ajouter(auteur);
		}
		else if(action.equals("rechercher")) {
			Lauteurs = auteurDao.rechercher(auteur);
		}
		else if(action.equals("modifier")) {
			auteurDao.modifier(auteur);
			executed = !executed;
			
			System.out.println("post : "+executed);
		}
		
		doGet(request,response);
	}
}