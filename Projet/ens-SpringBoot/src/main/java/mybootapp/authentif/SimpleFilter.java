package mybootapp.authentif;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE,
		DispatcherType.ERROR }, urlPatterns = { "/acces/*" })
public class SimpleFilter implements Filter {

	public static final String ACCES_CONNEXION = "/WEB-INF/jsp/connexion.jsp";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.err.println("DEBUT RestrictionFilter");
		/* Cast des objets request et response */
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		/*
		 * System.err.printf("Before %s\n", request); if (request instanceof
		 * HttpServletRequest) { HttpServletRequest hr = (HttpServletRequest) request;
		 * System.err.printf("Before Http Request %s\n", hr); } chain.doFilter(request,
		 * response); System.err.printf("After %s\n", request);
		 */

		/* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /**
         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
         * l'utilisateur n'est pas connecté.
         */
        if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
            /* Redirection vers la page publique */
        	System.err.println("UTILISATEUR pas co !");
            request.getRequestDispatcher( ACCES_CONNEXION ).forward( request, response );
        } else {
            /* Affichage de la page restreinte */
        	System.err.println("UTILISATEUR co !");
            chain.doFilter( request, response );
        }

	}
}