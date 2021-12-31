package br.com.gama.tech.projeto.homologacao;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
@Controller
@SpringBootApplication
public class HomologacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomologacaoApplication.class, args);
	}
	 @RequestMapping("/teste/jsp/")
	    public String home(Map<String, Object> model) {
	        model.put("message", "HowToDoInJava Reader !!");
	        return "NewFile";
		 }
//	    public void configureViewResolvers(ViewResolverRegistry registry) {
//	        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//	        resolver.setPrefix("/WEB-INF/jsp/");
//	        resolver.setSuffix(".jsp");
//	        resolver.setViewClass(JstlView.class);
//	        registry.viewResolver(resolver);
//	    }

}
