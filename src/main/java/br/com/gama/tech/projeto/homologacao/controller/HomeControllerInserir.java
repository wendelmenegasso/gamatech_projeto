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

import br.com.gama.tech.projeto.homologacao.negocio.Clientes;
import br.com.gama.tech.projeto.homologacao.negocio.Contas;




@RestController
public class HomeControllerInserir {
	String agencia, tipo_conta, conta, saldo;
	String nome, cpf, email, telefone;

	@RequestMapping(value = "/inserir/")
	//Método que pega a url /inserir/
	//Pega os parâmetros
	//Esse método é chamado quando o cliente quer incluuir uma conta
	//Os paramêtros são pegados da outra tela.
	public String inserir(@RequestParam("tipo_conta") String tipo_conta,
			@RequestParam("numero_conta") String numeroConta, @RequestParam("agencia_conta") String agencia,
			@RequestParam("nome_cliente") String nome, @RequestParam("cpf_cliente") String cpf,
			@RequestParam("telefone_contato") String telefone, @RequestParam("email_contato") String eMail)
			throws IOException, NamingException {
		this.agencia = agencia;
		this.saldo = "1000";
		this.conta = numeroConta;
		this.tipo_conta = tipo_conta;
		this.nome = nome;
		this.cpf = cpf;
		this.email = eMail;
		this.telefone = telefone;
		Clientes clientes = new Clientes();
		clientes.setNome(this.nome);
		clientes.setCpf(this.cpf);
		clientes.setEmail(eMail);
		clientes.setNumero_conta(this.conta);
		clientes.setTelefone(this.telefone);
		clientes.setAgencia_conta(this.agencia);
		Contas contas = new Contas();
		contas.setAgencia(this.agencia);
		contas.setConta(this.conta);
		contas.setSaldo(this.saldo);
		contas.setTipo_conta(this.tipo_conta);
		String html = "";
		if (clientes.chamaBanco()) {
			if (contas.chamaBanco()) {
				//Variável que retorna a tela que vai aparecer
				html = "<!DOCTYPE html>\r\n"
						+ "<html>\r\n"
						+ "\r\n"
						+ "<head>\r\n"
						+ "\r\n"
						+ "  <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\r\n"
						+ "  integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\r\n"
						+ "\r\n"
						+ "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js\"></script>\r\n"
						+ "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js\"></script>\r\n"
						+ "  \r\n"
						+ "  <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css\">\r\n"
						+ "\r\n"
						+ "  <!-- Favicons -->\r\n"
						+ "  <link rel=\"apple-touch-icon\" href=\"/docs/5.0/assets/img/favicons/apple-touch-icon.png\" sizes=\"180x180\">\r\n"
						+ "  <link rel=\"icon\" href=\"/docs/5.0/assets/img/favicons/favicon-32x32.png\" sizes=\"32x32\" type=\"image/png\">\r\n"
						+ "  <link rel=\"icon\" href=\"/docs/5.0/assets/img/favicons/favicon-16x16.png\" sizes=\"16x16\" type=\"image/png\">\r\n"
						+ "  <link rel=\"manifest\" href=\"/docs/5.0/assets/img/favicons/manifest.json\">\r\n"
						+ "  <link rel=\"mask-icon\" href=\"/docs/5.0/assets/img/favicons/safari-pinned-tab.svg\" color=\"#7952b3\">\r\n"
						+ "  <link rel=\"icon\" href=\"/docs/5.0/assets/img/favicons/favicon.ico\">\r\n"
						+ "  <meta name=\"theme-color\" content=\"#7952b3\">\r\n"
						+ "\r\n"
						+ "  <title> Banco </title>\r\n"
						+ "\r\n"
						+ "</head>\r\n"
						+ "\r\n"
						+ "<body class=\"text-center bg-light text-dark\">\r\n"
						+ "\r\n"
						+ "  <div class=\"container\">\r\n"
						+ "    <main>\r\n"
						+ "      <div class=\"py-5 text-center\">\r\n"
						+ "        <div class=\"container text-center\">\r\n"
						+ "          <h2 class=\"text-uppercase\">Confira seus dados!</h2>\r\n"
						+ "        </div>\r\n"
						+ "        <br />"+
						"<form action='/home/' method=\"post\">\r\n"
						+ "          <div class=\"form-group row\">\r\n"
						+ "            <label for=\"staticName\" class=\"col-sm-2 col-form-label\">Nome</label>\r\n"
						+ "            <div class=\"col-sm-2\">\r\n"
						+ "              <input type=\"text\" name=nome_cliente value=\""+this.nome+"\" readonly>\r\n"
						+ "            </div>\r\n"
						+ "          </div>\r\n"
						+ "          <div class=\"form-group row\">\r\n"
						+ "            <label for=\"staticCPF\" class=\"col-sm-2 col-form-label\">CPF</label>\r\n"
						+ "            <div class=\"col-sm-2\">\r\n"
						+ "              <input type=\"text\" name=\"cpf_cliente\" value=\""+this.cpf+"\" readonly>\r\n"
						+ "            </div>\r\n"
						+ "          </div>\r\n"
						+ "          <div class=\"form-group row\">\r\n"
						+ "            <label for=\"staticEmail\" class=\"col-sm-2 col-form-label\">Email</label>\r\n"
						+ "            <div class=\"col-sm-2\">\r\n"
						+ "              <input type=\"email\" name=\"email_contato\" value=\""+this.email+"\" readonly> <!-- no campo value pegar do bd -->\r\n"
						+ "            </div>\r\n"
						+ "          </div>\r\n"
						+ "          <div class=\"form-group row\">\r\n"
						+ "            <label for=\"staticTel\" class=\"col-sm-2 col-form-label\">Telefone</label>\r\n"
						+ "            <div class=\"col-sm-2\">\r\n"
						+ "              <input type=\"text\" name=\"telefone_contato\" value=\""+this.telefone+"\" readonly>\r\n"
						+ "            </div>\r\n"
						+ "          </div>\r\n"
						+ "        "
						+ "        <hr>\r\n"
						+ "        "
						+ "          <div class=\"form-group row\">\r\n"
						+ "            <label for=\"staticAgencia\" class=\"col-sm-2 col-form-label\">Agencia</label>\r\n"
						+ "            <div class=\"col-sm-2\">\r\n"
						+ "              <input type=\"text\" name=\"agencia_conta\" value=\""+this.agencia+"\" readonly>\r\n"
						+ "            </div>\r\n"
						+ "          </div>\r\n"
						+ "          <div class=\"form-group row\">\r\n"
						+ "            <label for=\"staticConta\" class=\"col-sm-2 col-form-label\">Conta</label>\r\n"
						+ "            <div class=\"col-sm-2\">\r\n"
						+ "              <input type=\"text\" name=\"numero_conta\" value=\""+this.conta+"\" readonly>\r\n"
						+ "            </div>\r\n"
						+ "          </div>\r\n"
						+ "          <div class=\"form-group row\">\r\n"
						+ "            <label for=\"staticTipo\" class=\"col-sm-2 col-form-label\">Tipo de Conta</label>\r\n"
						+ "            <div class=\"col-sm-2\">\r\n"
						+ "              <input type=\"text\" name=\"tipo_conta\" value=\""+this.tipo_conta+"\" readonly> \r\n"
						+ "            </div>\r\n"
						+ "          </div>\r\n"
						+ 			"<button type=\"submit\" class=\"btn m-2 btn-info btn-lg\">OK</button>"
						+ "        </form>\r\n"
						+ "        <hr>\r\n"
						+ ""       
						+ "\r\n"
						+ "\r\n"
						+ "      </body>\r\n"
						+ "      <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js\"\r\n"
						+ "      integrity=\"sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM\" crossorigin=\"anonymous\"></script>\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "      </html>";
			}
		} else {
			return "Erro ao inserir";
		}
		return html;
	}

}