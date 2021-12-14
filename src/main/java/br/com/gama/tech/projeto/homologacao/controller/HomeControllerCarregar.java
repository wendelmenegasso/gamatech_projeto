package br.com.gama.tech.projeto.homologacao.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Map;


import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@RestController
public class HomeControllerCarregar {
	String agencia, tipo_conta, conta, saldo;
	String nome, cpf, email, telefone;

	// @RequestMapping(value = , method = RequestMethod.GET)

	@RequestMapping("/home/inserir/")
	public String initInserir() {
		String html = "";
		String arquivo = "../homologacao/src/main/resources/webapps/WEB-INF/jsp/index.html";
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
	@RequestMapping("/home/")
	public String initHome() {
		String html = "";
		String arquivo = "../homologacao/src/main/resources/webapps/WEB-INF/jsp/home_bank.html";
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
	@RequestMapping(value = "/home/inserir/voltar/")
	public String initVoltar(@RequestParam("tipo_conta") String tipo_conta,
			@RequestParam("numero_conta") String numeroConta, @RequestParam("agencia_conta") String agencia,
			@RequestParam("nome_cliente") String nome, @RequestParam("cpf_cliente") String cpf,
			@RequestParam("telefone_contato") String telefone, @RequestParam("email_contato") String eMail) {
		String html = "";
		String arquivo = "../homologacao/src/main/resources/webapps/WEB-INF/jsp/index.html";
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
//	@RequestMapping("/home/inserir/")
//	public String init() {
//		String html = "index.jsp";
//		
//		return html;
//	}
//	public String init() {
//		String html = "<html>"
//				+ "<head>"
//				+ "<script>"+
//				"function carregar(){"
//				+ " window.open('http://www.google.com.br','_self');"
//				+ "}"
//				+ "</script>"
//				+ "</head>"
//				+"<body onload=carregar()>"
//				+"</body>"
//				+ "</html>";
//		return html;
//	}
	@RequestMapping("/home/consultar/")
	public String initConsulta() {
		String html = "";
		String arquivo = "../homologacao/src/main/resources/webapps/WEB-INF/jsp/consultar.html";
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
	@RequestMapping("/home/operacao/sacar/")
	public String initAplicar() {
		String html = "";
		String arquivo = "../homologacao/src/main/resources/webapps/WEB-INF/jsp/operacao_sacar.html";
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
	@RequestMapping("/home/operacao/depositar/")
	public String initDepositar() {
		String html = "";
		String arquivo = "../homologacao/src/main/resources/webapps/WEB-INF/jsp/operacao_depositar.html";
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
	@RequestMapping("/home/operacao/extrato/")
	public String initExtrato() {
		String html = "";
		String arquivo = "../homologacao/src/main/resources/webapps/WEB-INF/jsp/operacao_extrato.html";
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
