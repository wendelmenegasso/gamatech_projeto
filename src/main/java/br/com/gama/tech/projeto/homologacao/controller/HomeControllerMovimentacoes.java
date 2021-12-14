package br.com.gama.tech.projeto.homologacao.controller;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;

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

import br.com.gama.tech.projeto.homologacao.negocio.Operacoes;


@RestController
//Classe que contém todos os servlet com as operações bancárias.
public class HomeControllerMovimentacoes extends HttpServlet {
	String agencia, tipo_conta, conta, saldo;
	String nome, cpf, email, telefone;
	
	//Servlet "/extrato"
	//Lê o arquivo index.html linha a linha e joga dentro da variável HTML
	//E exibe o html.
	@RequestMapping("/extrato/")
	public String initExtrato(@RequestParam("numero_conta") String numero_conta){
		Operacoes op = new Operacoes();
		String html = op.extrato(numero_conta);
		return html;
	}

	@RequestMapping(value = "/retirada/")
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
	@RequestMapping("/aplicar/")
	//Servlet "/aplicar/"
	//Recebe os parametros necessários para realizar a operação
	public String initAplicar(@RequestParam("valor_aplicar") String saldo, 
			@RequestParam("agencia") String agencia,
			@RequestParam("numero_conta") String numero_conta) {
		Operacoes op = new Operacoes();
		if (op.aplicar(agencia, numero_conta, saldo)) {
			String html =""+ saldo + " Aplicado na conta " + numero_conta + " Agência " + agencia
					+ "<form action=/home/ method=post>"
					+ "<input type=submit value=Voltar>"
					+ "</form>";
			return html;
		}
		else return "Nâo foi possível aplicar";
	}
	@RequestMapping("/sacar/")
	//Servlet "/sacar/"
	//Recebe os parametros necessários para realizar a operação
	public String initSacar(@RequestParam("valor_aplicar") String saldo, 
			@RequestParam("agencia") String agencia,
			@RequestParam("numero_conta") String numero_conta) {
		Operacoes op = new Operacoes();
		if (op.sacar(agencia, numero_conta, saldo)) {
			String html =""+ saldo + " Sacado na conta " + numero_conta + " Agência " + agencia
			+ "<form action=/home/ method=post>"
			+ "<input type=submit value=Voltar>"
			+ "</form>";
			return html;
		}
		else return "Nâo foi possível aplicar";
	}
}
