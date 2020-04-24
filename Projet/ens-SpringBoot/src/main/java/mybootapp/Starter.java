package mybootapp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@SpringBootApplication
@ServletComponentScan //SERT à détecter mes servlets !!
@EnableJpaRepositories(basePackageClasses = Starter.class)
@EntityScan(basePackageClasses = Starter.class)
public class Starter extends SpringBootServletInitializer {
	
	public static boolean generatedRandomPersons = false;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Starter.class);
	}
		
	/*Message d'erreur pour le form ???????*/
	/*@Bean("messageSource")
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource r = new ResourceBundleMessageSource();
        r.setBasenames("/springapp/web/product");
        return r;
    }*/

	public static void main(String[] args) {
		SpringApplication.run(Starter.class, args);
	}
}
