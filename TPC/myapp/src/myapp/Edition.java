    package myapp;
     
    import java.io.IOException;
     
    import javax.servlet.RequestDispatcher;
    import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
     
    /**
     * Servlet implementation class guru_register
     */
    @WebServlet({ "/editionPerson" , "/edition"})
    public class Edition extends HttpServlet {
    	private static final long serialVersionUID = 1L;
    	
    	
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(request.getServletPath().contains("editionPerson")) {
    				request.getRequestDispatcher("/WEB-INF/edition.jsp").forward(request, response);
    			}
    	}
           
         protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		// TODO Auto-generated method stub
        	 System.out.println("On tente édition d'une personne !");
    		String id = request.getParameter("ID");
    		String nom = request.getParameter("Nom");
    		String mail = request.getParameter("mail");
    		
    		System.out.println("AHH: "+id+nom+mail);
    		
    		if(id.isEmpty() || nom.isEmpty() || mail.isEmpty())
    		{
    			/*TODO: On doit indiquer en ROUGE quels champs sont incorrects*/
    			RequestDispatcher req = request.getRequestDispatcher("/WEB-INF/edition.jsp");
    			req.include(request, response);
    		}
    		else
    		{
    			RequestDispatcher req = request.getRequestDispatcher("/WEB-INF/edition.jsp");
    			req.forward(request, response);
    		}
    	}
     
    }