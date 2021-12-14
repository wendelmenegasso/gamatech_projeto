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
			String html ="<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "\r\n"
					+ "<head>\r\n"
					+ "\r\n"
					+ "  <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\r\n"
					+ "  integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\r\n"
					+ "\r\n"
					+ "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js\"></script>\r\n"
					+ "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js\"></script>\r\n"
					+ "  <script src=\"https://cdn.jsdelivr.net/gh/plentz/jquery-maskmoney@master/dist/jquery.maskMoney.min.js\"></script>\r\n"
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
					+ "  <title> Banco Foo Data </title>\r\n"
					+ "</head>\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "<body class=\"text-center bg-light text-dark\">"+ saldo + " Aplicado na conta " + numero_conta + " Agência " + agencia
					+ "<form action=/home/ method=post>"
					+ "<button type=submit>Voltar</button>"
					+ "</form>";
			return html;
		}
		else return "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "\r\n"
				+ "<head>\r\n"
				+ "\r\n"
				+ "  <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\r\n"
				+ "  integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\r\n"
				+ "\r\n"
				+ "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js\"></script>\r\n"
				+ "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js\"></script>\r\n"
				+ "  <script src=\"https://cdn.jsdelivr.net/gh/plentz/jquery-maskmoney@master/dist/jquery.maskMoney.min.js\"></script>\r\n"
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
				+ "  <title> Banco Foo Data </title>\r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "<body class=\"text-center bg-light text-dark\">Nâo foi possível aplicar"				+ "<form action=/home/ method=post>"
				+ "<button type=submit> class=\"btn m-2 btn-primary btn-lg\"Voltar</button>"
				+ "</form>";
	}
	@RequestMapping("/sacar/")
	//Servlet "/sacar/"
	//Recebe os parametros necessários para realizar a operação
	public String initSacar(@RequestParam("valor_aplicar") String saldo, 
			@RequestParam("agencia") String agencia,
			@RequestParam("numero_conta") String numero_conta) {
		Operacoes op = new Operacoes();
		if (op.sacar(agencia, numero_conta, saldo)) {
			String html ="<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "\r\n"
					+ "<head>\r\n"
					+ "\r\n"
					+ "  <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\r\n"
					+ "  integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\r\n"
					+ "\r\n"
					+ "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js\"></script>\r\n"
					+ "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js\"></script>\r\n"
					+ "  <script src=\"https://cdn.jsdelivr.net/gh/plentz/jquery-maskmoney@master/dist/jquery.maskMoney.min.js\"></script>\r\n"
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
					+ "  <title> Banco Foo Data </title>\r\n"
					+ "</head>\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "<body class=\"text-center bg-light text-dark\">"+ saldo + " Sacado na conta " + numero_conta + " Agência " + agencia
			+ "<form action=/home/ method=post>"
			+ "<button type=submit class=\"btn m-2 btn-primary btn-lg\">Voltar</button>"
			+ "</form>";
			return html;
		}
		else {
			String html ="<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "\r\n"
					+ "<head>\r\n"
					+ "\r\n"
					+ "  <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\r\n"
					+ "  integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\r\n"
					+ "\r\n"
					+ "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js\"></script>\r\n"
					+ "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js\"></script>\r\n"
					+ "  <script src=\"https://cdn.jsdelivr.net/gh/plentz/jquery-maskmoney@master/dist/jquery.maskMoney.min.js\"></script>\r\n"
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
					+ "  <title> Banco Foo Data </title>\r\n"
					+ "</head>\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "<body class=\"text-center bg-light text-dark\">Nâo foi possível fazer o saque - saldo insuficiente <br><form action=/home/ method=post>"
				+ "<button type=submit class=\"btn m-2 btn-info btn-lg\">Voltar</button>"
				+ "</form>";
			return html;
		}
	}
}
