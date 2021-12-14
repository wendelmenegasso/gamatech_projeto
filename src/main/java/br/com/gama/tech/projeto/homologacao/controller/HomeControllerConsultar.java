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
public class HomeControllerConsultar {
	String agencia, tipo_conta, conta, saldo;
	String nome, cpf, email, telefone;
	
	//Método que é chamado via servlet /home/consultar/conta
	//Pega os valores via método post do formulário da outra tela
	//E retorna uma página HTML
	@PostMapping(name = "cpf_cliente", value = "/home/consultar/concluir/", params = "cpf_cliente")
	public String consultar(@RequestParam("cpf_cliente") String cpf) {
		String html = "";
		BancoDeDados bd = new BancoDeDados();
		bd.conectar();
		html += "<!DOCTYPE html>";
		html += "<html>";
		html += "<head>";

		html += "<link href=https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css rel=stylesheet integrity=sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC crossorigin=anonymous>";

		html += "<!-- Favicons -->";
		html += "<link rel=apple-touch-icon href=/docs/5.0/assets/img/favicons/apple-touch-icon.png sizes=180x180>";
		html += "<link rel=icon href=/docs/5.0/assets/img/favicons/favicon-32x32.png sizes=32x32 type=image/png>";
		html += "<link rel=icon href=/docs/5.0/assets/img/favicons/favicon-16x16.png sizes=16x16 type=image/png>";
		html += "<link rel=manifest href=/docs/5.0/assets/img/favicons/manifest.json>";
		html += "<link rel=mask-icon href=/docs/5.0/assets/img/favicons/safari-pinned-tab.svg color=#7952b3>";
		html += "<link rel=icon href=/docs/5.0/assets/img/favicons/favicon.ico>";
		html += "<meta name=theme-color content=#7952b3>";
		html += "<title> Banco </title>";
		html += "</head>";
		html += "<body class=text-center bg-light text-dark>";
		html += "<div class=container>";
		html += "<form action=/home/consultar/ method=post>";

		html += "<main>";
		html += "<div class=py-5 text-center>";
		html += "<div class=container text-center>";
		html += "<h2>Resultado da sua consulta</h2>";
		html += "</div>";
		html += "<div class=mx-auto style=width: 200px;>Insira seus dados</div>";
		html += "<br />";
		html += "<div class=col-sm-7>";
		html += "<label for=name class=form-label>Nome Completo</label>";

		html += "<input type=text class=form-control name=nome_cliente value=" + bd.consultaCPF(cpf) + " required>";
		html += "<div class=invalid-feedback>";
		html += "É necessário um nome válido";
		html += "</div>";
		html += "</div>";
		html += "<br />";
		html += "<div class=col-sm-4>";
		html += "<label for=name class=form-label>CPF</label>";
		html += "<input type=text class=form-control name=cpf_cliente  value=" + cpf + " required>";
		html += "<div class=invalid-feedback>";
		html += "É necessário um CPF válido";
		html += "</div>";
		html += "</div>";
		html += "<br />";
		html += "<div class=col-md-7 col-lg-8>";
		html += "<div class=row g-3>";
		html += "<div class=col-sm-6>";
		html += "<label for=firstName class=form-label>E-mail</label>";
		html += "<input type=text class=form-control name=email_contato value=" + bd.consultaEMail(cpf) + " required>";
		html += "<div class=invalid-feedback>";
		html += "É necessário um e-mail válido";
		html += "</div>";
		html += "</div>";

		html += "<div class=col-sm-4>";
		html += "<label for=lastName class=form-label>Telefone</label>";
		html += "<input type=text class=form-control name=telefone_contato value=" + bd.consultaTelefone(cpf)
				+ " required>";
		html += "<div class=invalid-feedback>";
		html += "É necessário um e-mail válido";
		html += "</div>";
		html += "</div>";
		html += "</div>";
		html += "</div>";

		html += "<hr class=my-4>";
		html += "<div class=col-md-10 col-lg-12>";
		html += "<div class=row g-3>";
		html += "<div class=col-sm-4>";
		html += "<label for=firstName class=form-label>Agencia</label>";
		html += "<input type=text class=form-control name=agencia_conta value=" + bd.consultaAgencia(cpf)
				+ " required>";
		html += "<div class=invalid-feedback>";
		html += "É necessário uma agencia válida";
		html += "</div>";
		html += "</div>";
		html += "<div class=col-sm-4>";
		html += "<label for==lastName class=form-label>Numero Conta</label>";
		html += "<input type=text class=form-control name=numero_conta value=" + bd.consultaConta(cpf) + " required>";
		html += "<div class=invalid-feedback>";
		html += "Numero de conta invalido";
		html += "</div>";
		html += "</div>";
		html += "</div>";
		html += "<br>";
		html += "</div>";
		html += "<h4>" + "Tipo de conta: <br><input type=text id=tipo_conta value=" + bd.consultaTipoConta(cpf)
				+ "></h4>";
		html += "<br />";
		html += "<input type=submit value=Voltar>";

		html += "</form>";

		html += "</body>";
		html += "<script src=https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js integrity=sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM crossorigin=anonymous></script>";
		html += "</html>";

		String arquivo = "../meuprojeto/src/main/resources/webapps/WEB-INF/jsp/consultar_resultado.jsp";

		return html;
	}
}
