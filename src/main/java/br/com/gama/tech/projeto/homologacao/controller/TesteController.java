package br.com.gama.tech.projeto.homologacao.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class TesteController {
	@RequestMapping("/teste/")
	public String initTeste() {
		String html = "";
		String arquivo = "../homologacao/src/main/resources/webapps/WEB-INF/jsp/teste.jsp";
		try {
			BufferedReader br = new BufferedReader(new FileReader(arquivo));

			while (br.ready()) {
				html += br.readLine();
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return html;
	}
}
