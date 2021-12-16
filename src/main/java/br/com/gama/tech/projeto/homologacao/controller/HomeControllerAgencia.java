package br.com.gama.tech.projeto.homologacao.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gama.tech.projeto.homologacao.negocio.Agencia;
import br.com.gama.tech.projeto.homologacao.negocio.Utilitario;
@RestController
public class HomeControllerAgencia {
	Utilitario ut = new Utilitario();
	@RequestMapping("/home/agencia/")
	public String initAgencia() {
		String html = "";
		String arquivo = "../homologacao/src/main/resources/webapps/WEB-INF/jsp/bank_agencia.html";
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
	@RequestMapping("/home/agencia/consultar/")
	public String initConsultarAgencia() {
		Agencia agencia =  new Agencia();
		String html = agencia.consultarAgencia();
		
		
		return html;
	}
	@RequestMapping(value = "/home/agencia/concluir/")
	public String initVoltar(@RequestParam("bairro") String bairro,
			@RequestParam("cidade") String cidade, @RequestParam("estado") String estado,
			@RequestParam("rua") String rua, @RequestParam("numero") String numero ){
		Agencia agencia = new Agencia();
		System.out.println(ut.substituir(bairro));
		agencia.setBairro(ut.substituir(bairro));
		agencia.setCidade(ut.substituir(cidade));
		agencia.setEstado(ut.substituir(estado));
		agencia.setNumero(ut.substituir(numero));
		agencia.setRua(ut.substituir(rua));
		String html = "";
		html += "<form action=/home/ method=post> A agência cadastrada é a: "+agencia.chamaBanco()+"<br><button type=submit>Voltar</button></form>";
		return html;
	}
}
