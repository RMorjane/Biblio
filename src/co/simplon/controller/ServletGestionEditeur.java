package co.simplon.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.simplon.dao.DAO;
import co.simplon.dao.DAOContext;
import co.simplon.model.Editeur;

/**
 * Servlet implementation class ServletGestionEditeur
 */

public class ServletGestionEditeur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private int id;
	private String nom;
	private DAO<Editeur> editeurDao;
	private String action;
	private Editeur editeur = null;
	private List<Editeur> Lediteurs = null;
	
	private boolean executed = false;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		// TODO Auto-generated method stub
    	this.editeurDao = DAOContext.getEditeurDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		action = request.getParameter("action");
		if(action==null) { // on affiche la liste des editeurs à l'initialisation de la Servlet
			Lediteurs = editeurDao.lister();		
			request.setAttribute("Lediteurs", Lediteurs);
			this.getServletContext().getRequestDispatcher("/GestionEditeurs.jsp").forward(request,response);			
		}
		else {
			System.out.println("get : "+action);
			
			if(action.equals("modifier")) {
				System.out.println("get : "+executed);
				
				if(executed) { // on affiche la liste des editeurs lorsque la modification est déjà éxecutée
					Lediteurs = editeurDao.lister();		
					request.setAttribute("Lediteurs", Lediteurs);
					this.getServletContext().getRequestDispatcher("/GestionEditeurs.jsp").forward(request,response);
					executed = !executed;
				}
				else { // sinon on affiche les informations de l'editeur sur le formulaire FormEditeur.jsp
					String strId = request.getParameter("id");
					if(!strId.equals("")) {
						
						//System.out.println("Id de l'Editeur : "+strId);
						
						// on récupère l'editeur à modifier par son id
						editeur = (Editeur)editeurDao.getById(strId);
						
						// si l'editeur est trouvé, on redirrige les informations de l'editeur sur le formulaire FormEditeur.jsp
						if(editeur!=null) {
							request.setAttribute("editeur", editeur);
							this.getServletContext().getRequestDispatcher("/FormEditeur.jsp?action="+action).forward(request,response);							
						}
					}					
				}				
			}
			else if(action.equals("supprimer")) {
				String strId = request.getParameter("id");
				if(!strId.equals("")) {
					
					// on récupère l'editeur à supprimer par son id
					editeur = (Editeur)editeurDao.getById(strId);
					
					// si l'editeur est trouvé, on le supprime de la table editeur
					if(editeur!=null) {
						editeurDao.supprimer(editeur);
					}
					
					// on raffraichit l'affichage de la liste des editeurs
					Lediteurs = editeurDao.lister();		
					request.setAttribute("Lediteurs", Lediteurs);
					this.getServletContext().getRequestDispatcher("/GestionEditeurs.jsp").forward(request,response);
				}			
			}
			else if(action.equals("rechercher")) { // on affiche la liste des editeurs recherchés
				request.setAttribute("Lediteurs", Lediteurs);
				this.getServletContext().getRequestDispatcher("/GestionEditeurs.jsp").forward(request,response);				
			}
			// on affiche la liste des editeurs lorsqu'on clique sur le bouton Acceuil ou après l'ajout d'un editeur
			else if(action.equals("acceuil")||action.equals("ajouter")) {
				Lediteurs = editeurDao.lister();		
				request.setAttribute("Lediteurs", Lediteurs);
				this.getServletContext().getRequestDispatcher("/GestionEditeurs.jsp").forward(request,response);				
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
		
		editeur = new Editeur();
		editeur.setNom(nom);
		strId = request.getParameter("id");
		if(!strId.equals("")) {
			try {
				id = Integer.parseInt(strId);
				editeur.setEditeurId(id);					
			}
			catch(NumberFormatException e) {
				e.printStackTrace();
			}
		}		
				
		if(action.equals("ajouter")) {
			editeurDao.ajouter(editeur);
		}
		else if(action.equals("rechercher")) {
			Lediteurs = editeurDao.rechercher(editeur);
		}
		else if(action.equals("modifier")) {
			editeurDao.modifier(editeur);
			executed = !executed;
			
			System.out.println("post : "+executed);
		}
		doGet(request, response);
	}

}