package myTP.springapp.web;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * La méthode onStartup est automatiquement appelée par Spring pour initialiser l'application.
 * Cette dernière va créer une servlet générique (DispatcherServlet) qui va récupérer toutes les
 * requêtes (qui se terminent par .html ou qui débutent par /actions/) et les aiguiller vers le bon
 * contrôleur. Vous remarquerez que la classe est elle-même une point de configuration Spring
 * (@Configuration) qui est enregistré dans le contexte de l'application.
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = SpringStart.class)
public class SpringStart implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext ctx) throws ServletException {
        System.out.println("Starting....");
        // Init application context
        AnnotationConfigWebApplicationContext webCtx
            = new AnnotationConfigWebApplicationContext();
        webCtx.register(SpringStart.class);
        webCtx.setServletContext(ctx);
        // Init dispatcher servlet
        ServletRegistration.Dynamic servlet
            = ctx.addServlet("springapp", new DispatcherServlet(webCtx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("*.htm");
        servlet.addMapping("/actions/*");
    }

}