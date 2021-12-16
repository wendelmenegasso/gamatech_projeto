package br.com.gama.tech.projeto.homologacao.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.GetMapping;
@RestController
//@RequestMapping(method=RequestMethod.POST)
public class TesteController {
//	@RequestMapping("/teste/")
//	public String initTeste() {
//		String html = "";
//		String arquivo = "teste.jsp";
//		return arquivo;
//	}
//}
	//@RequestMapping(value="/teste/", method=RequestMethod.POST)
	@RequestMapping("/teste/")
	public ModelAndView homePost() {
		    ModelAndView modelAndView = new ModelAndView("bank_home");
		    modelAndView.addObject("message", "Baeldung");
		    return modelAndView;
    }
}
