package mybootapp.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import mybootapp.dao.DAOPerson;
import mybootapp.model.Party;
import mybootapp.model.Person;

@WebServlet("/search")
public class ResearchServlets extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public static final String VUE              = "/WEB-INF/jsp/search.jsp";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    
    @Autowired
    DAOPerson dao;

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	//TODO: Penser au Majuscule/Minuscule
    	
    	String action = req.getParameter("action");
    	HttpSession ses = req.getSession(true);
    	boolean connected = false;
    	if(ses.getAttribute(ATT_SESSION_USER)!=null) {
    		connected = true;
    	}
    	PrintWriter printWriter = resp.getWriter();
    	
    	if(action.equals("searchByLastName")) {
    		String lastName = req.getParameter("lastName");
    		
            printWriter.print("<html>");
            printWriter.print("<body>");
            
            printWriter.print("<h1>Résultat recherche par lastName</h1>");
            
            printWriter.print("<table>");
            if(!connected)
            	printWriter.print("<td>firstName</td><td>lastName</td><td>Groupe</td><td>SiteWeb</td>");
            else
            	printWriter.print("<td>firstName</td><td>lastName</td><td>Groupe</td><td>SiteWeb</td><td>Mail</td><td>Anniversaire</td>");
            
            for(Person p : dao.findPersonByLastNameLike("%" + lastName + "%")) {
            	printWriter.print("<tr>");
            	printWriter.print("<td>" + p.getFirstName() + "</td>");
            	printWriter.print("<td>" + p.getLastName() + "</td>");
            	printWriter.print("<td>" + p.getPersonParty().getPartyName() + "</td>");
            	printWriter.print("<td>"+ p.getWebsite() + "</td>");
            	if(connected) {
                	printWriter.print("<td>"+ p.getMail() + "</td>");
                	printWriter.print("<td>"+ p.getBirthDay() + "</td>");
            	}
            	printWriter.print("</tr>");
            }
            printWriter.print("</table>");
            
            printWriter.print("</body>");
            printWriter.print("</html>");
            printWriter.close();
        }
    	if(action.equals("searchByFirstName")) {
    		String firstName = req.getParameter("firstName");
    		//System.err.println("byFirstName : "+dao.findPersonByFirstNameLike("%" + firstName + "%"));
    		
    		printWriter.print("<html>");
            printWriter.print("<body>");
            
            printWriter.print("<h1>Résultat recherche par firstName</h1>");
            
            printWriter.print("<table>");
            if(!connected)
            	printWriter.print("<td>firstName</td><td>lastName</td><td>Groupe</td><td>SiteWeb</td>");
            else
            	printWriter.print("<td>firstName</td><td>lastName</td><td>Groupe</td><td>SiteWeb</td><td>Mail</td><td>Anniversaire</td>");
            
            for(Person p : dao.findPersonByLastNameLike("%" + firstName + "%")) {
            	printWriter.print("<tr>");
            	printWriter.print("<td>" + p.getFirstName() + "</td>");
            	printWriter.print("<td>" + p.getLastName() + "</td>");
            	printWriter.print("<td>" + p.getPersonParty().getPartyName() + "</td>");
            	printWriter.print("<td>"+ p.getWebsite() + "</td>");
            	if(connected) {
                	printWriter.print("<td>"+ p.getMail() + "</td>");
                	printWriter.print("<td>"+ p.getBirthDay() + "</td>");
            	}
            	printWriter.print("</tr>");
            }
            printWriter.print("</table>");
            
            printWriter.print("</body>");
            printWriter.print("</html>");
            printWriter.close();
        }
    	if(action.equals("searchByPartyName")) {
    		String partyName = req.getParameter("partyName");
    		//System.err.println("byPartyName : "+dao.findPartyByPartyNameLike("%" + partyName + "%"));
    		
    		printWriter.print("<html>");
            printWriter.print("<body>");
            
            printWriter.print("<h1>Résultat recherche par nom de Groupe</h1>");
            	printWriter.print("<h2>Groupes</h2>");
            for(Party p : dao.findPartyByPartyNameLike("%" + partyName + "%"))
            	printWriter.print("<p>" + p.getPartyName() + "<p>");
            
            printWriter.print("</body>");
            printWriter.print("</html>");
            printWriter.close();
        }
    	
    	
        

    }
}