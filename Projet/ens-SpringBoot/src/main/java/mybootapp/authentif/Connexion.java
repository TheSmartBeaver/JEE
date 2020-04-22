package mybootapp.authentif;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.constraints.Email;
import mybootapp.authentif.Utilisateur;
import mybootapp.dao.DAOPerson;
import mybootapp.model.Party;
import mybootapp.authentif.ConnexionForm;

@WebServlet("/login")
public class Connexion extends HttpServlet {
    public static final String ATT_USER         = "utilisateur";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String VUE              = "/WEB-INF/jsp/connexion.jsp";
    
    @Autowired
    DAOPerson dao;

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page de connexion */
    	System.err.println("Debut SERVLET connexion");
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	System.err.println("Debut POST SERVLET connexion");
        /* Préparation de l'objet formulaire */
        ConnexionForm form = new ConnexionForm();
        System.err.println(dao.findAllParties().toString());

        /* Traitement de la requête et récupération du bean en résultant */
        Utilisateur utilisateur = form.connecterUtilisateur( request );

        /**
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * Utilisateur à la session, sinon suppression du bean de la session.
         */
        if ( form.getErreurs().isEmpty() ) {
        	if(!authentification(utilisateur)) {
            	System.err.println("mot de passe incorrect");
            	
            }
        	/* Récupération de la session depuis la requête */
            HttpSession session = request.getSession();
            session.setAttribute( ATT_SESSION_USER, utilisateur );
            
        } else {
            //session.setAttribute( ATT_SESSION_USER, null );
        }

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
    
    public boolean authentification(Utilisateur user) {
    	dao.findAllPersons();
    	System.err.println("On cherche utilisateur avec cet email : "+user.getEmail());
    	if(dao.findByEmail(user.getEmail())==null) {
    		System.err.println("Mail inexistant");
    	return false;
    	}
    	
    	System.err.println(dao.findByEmail(user.getEmail()).getPassword()+"===="+user.getMotDePasse());
    	if(dao.findByEmail(user.getEmail()).getPassword().equals(user.getMotDePasse())) {
    		return true;
    	}
    	else
    		return false;
    }
}