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

import br.com.gama.tech.projeto.homologacao.model.BancoDeDados;


@RestController
public class HomeControllerDeletar {
	String agencia, tipo_conta, conta, saldo;
	String nome, cpf, email, telefone;

	// @RequestMapping(value = , method = RequestMethod.GET)

	@RequestMapping("/home/deletar/")
	//Tela principal de deletar
	//Carregar o arquivo deletar.html
	//Servlet "/home/deletar/"
	public String initDeletar() {
		String html = "";
		String arquivo = "../homologacao/src/main/resources/webapps/WEB-INF/jsp/deletar.html";
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
	
	@RequestMapping(value = "/home/deletar/concluir/")
	//Retorno da tela deletar.
	//Servlet "/home/deletar/concluir/"
	public String initConcluirDeletar(@RequestParam("numero_conta") String numeroConta){
		String html = "";
		BancoDeDados bd = new BancoDeDados();
		bd.conectar();
		if(bd.deletarConta(numeroConta)) {
			html = "<H1> Deletado com sucesso</H1>"
					+ "<form action=/home/ method=post>"
					+"<input type=submit value=Voltar>"
					+ "</form>";
		}
		else html = "Erro ao deletar!";
		return html;
	}
}