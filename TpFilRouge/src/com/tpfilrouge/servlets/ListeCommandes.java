package com.tpfilrouge.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListeCommandes extends HttpServlet {
   private static final String ATT_COMMANDE = "commande";
   private static final String ATT_FORM     = "form";

   private static final String VUE          = "/WEB-INF/listeCommandes.jsp";

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
   }
}