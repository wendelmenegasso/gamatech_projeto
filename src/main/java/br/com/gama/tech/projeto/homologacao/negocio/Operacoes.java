package br.com.gama.tech.projeto.homologacao.negocio;

import br.com.gama.tech.projeto.homologacao.model.BancoDeDados;

//Classe que contêm as Operações
public class Operacoes {
	public boolean aplicar(String agencia, String numero_conta, String valor) {
		BancoDeDados bd =  new BancoDeDados();
		bd.conectar();
		return bd.aplicar(agencia, numero_conta, valor);
	}
	public boolean sacar(String agencia, String numero_conta, String valor) {
		BancoDeDados bd =  new BancoDeDados();
		bd.conectar();
		return bd.retirada(agencia, numero_conta, valor);
	}
	public String extrato(String numero_conta) {
		String html = "";
		BancoDeDados bd = new BancoDeDados();
		bd.conectar();
		html = bd.extrato(numero_conta);
		return html;
	}

}
