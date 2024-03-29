package myapp.web;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
									/* création de chemin se terminant par html = pointe vers ma servlet */
@WebServlet({ "/MyServlet", "/mySERVLET", "*.html", "/myPerson" })
public class Edition extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edition() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Start "+this);
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Stop "+this);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		if(request.getServletPath().contains("MyServlet")) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
			else if(request.getServletPath().contains("myPerson")) {
				request.getRequestDispatcher("/WEB-INF/person.jsp").forward(request, response);
				
			}
		
			else if(request.getServletPath().contains("editionPerson")) {
				request.getRequestDispatcher("/WEB-INF/edition.jsp").forward(request, response);
				
			}
		else if(request.getServletPath().contains(".html")) { 
			response.getWriter().append(request.getServletPath()); /*On affiche le chemin sur la page*/
			response.getWriter().append(request.getParameter("a"));
			response.getWriter().append(request.getParameter("b"));
		
		} else {
			response.getWriter().append("Bonjour");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
