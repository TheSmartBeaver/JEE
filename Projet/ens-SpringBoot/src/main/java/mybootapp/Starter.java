package mybootapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


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

	
	/*@Bean("messageSource")
	public ResourceBundleMessageSource messageSource() {
	    ResourceBundleMessageSource r = new ResourceBundleMessageSource();
	    r.setBasenames("classpath:/mybootapp/web/person");
	    return r;
	}*/

	public static void main(String[] args) {
		SpringApplication.run(Starter.class, args);
	}
}
