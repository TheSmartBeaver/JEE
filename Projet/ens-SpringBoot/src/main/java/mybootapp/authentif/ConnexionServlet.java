package mybootapp.authentif;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import mybootapp.authentif.Utilisateur;
import mybootapp.dao.PersonService;
import mybootapp.authentif.ConnexionForm;

@WebServlet("/login")
public class ConnexionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 3176964357733003735L;
	public static final String ATT_USER         = "utilisateur";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String VUE              = "/WEB-INF/jsp/connexion.jsp";
    
    protected final Log logger = LogFactory.getLog(getClass());
    
    @Autowired
    PersonService personServ;

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page de connexion */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        ConnexionForm form = new ConnexionForm();
        System.err.println(personServ.findAllParties().toString());

        /* Traitement de la requête et récupération du bean en résultant */
        Utilisateur utilisateur = form.connecterUtilisateur( request );

        /**
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * Utilisateur à la session, sinon suppression du bean de la session.
         */
        if ( form.getErreurs().isEmpty() ) {
        	if(!authentification(utilisateur, form)) {
            	logger.info("mot de passe incorrect");
            	form.setResultat("Authentification échouée");
            	request.setAttribute( ATT_FORM, form );
            	/*Erreur détecté donc on redirige sur le formulaire*/
            	this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
            	return;
            }
        	/* Récupération de la session depuis la requête */
            HttpSession session = request.getSession();
            session.setAttribute( ATT_SESSION_USER, utilisateur );
            System.err.println("ATTRIBUTE :: "+session.getAttribute(ATT_SESSION_USER));
            
            /* Stockage du formulaire et du bean dans l'objet request */
            request.setAttribute( ATT_FORM, form );
            request.setAttribute( ATT_USER, utilisateur );

            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
            
        } else {
        	/*Erreur détecté donc on redirige sur le formulaire*/
        	this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }
    }
    /**
     * 
     * @param user l'objet utilisateur dont on va comparer les champs
     * @param form Objet qui servira à afficher d'éventuelles erreurs
     * @return true si authentification succès
     */
    public boolean authentification(Utilisateur user, ConnexionForm form) {
    	if(personServ.findByEmail(user.getEmail())==null) {
    		form.setErreur("email", "Mail inexistant");
    		
    	return false;
    	}
    	
    	if(personServ.findByEmail(user.getEmail()).getPassword().equals(user.getMotDePasse())) {
    		user.setId(personServ.findByEmail(user.getEmail()).getId());
    		return true;
    	}
    	else {
    		form.setErreur("motdepasse", "mot de passe incorrect");
    		return false;
    	}
    }
}