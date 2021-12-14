package br.com.gama.tech.projeto.homologacao.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
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
public class HomeControllerAlterar {
	String agencia, tipo_conta, conta, saldo;
	String nome, cpf, email, telefone;


	@RequestMapping("/home/alterar/")
	//Mostra a página alterar.html
	//Servlet "/home/alterar/"
		
	public String initAlterar() {
		String html = "";
		String arquivo = "../homologacao/src/main/resources/webapps/WEB-INF/jsp/alterar.html";
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

	@RequestMapping(value = "/home/alterar/concluir/")
	//Retorno da tela alterar.
		//Servlet "/home/deletar/alterar/concluir"
		
	public String initAlterarConcluir(@RequestParam("numero_conta_cliente") String numero_conta_cliente,
			@RequestParam("nome_cliente") String nome,
			@RequestParam("telefone_cliente") String telefone, @RequestParam("email_cliente") String eMail) {
		String html = "";
		BancoDeDados bd = new BancoDeDados();
		bd.conectar();
		try {
			bd.atualizaCliente(numero_conta_cliente, nome, eMail, telefone);
			html = "<h1>Atualizado com súcesso!</h1>"
					+ "<form action=/home/ method=post>"
					+ "<button type=submit class=btn m-2 btn-info btn-lg>Voltar></button>"
					+ "</form>";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return html;
	}
}
