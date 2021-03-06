package br.com.gama.tech.projeto.homologacao.negocio;

import br.com.gama.tech.projeto.homologacao.model.BancoDeDados;

public class Contas {
	String agencia, tipo_conta, saldo;
	int conta;
	BancoDeDados bd = new BancoDeDados();
	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getTipo_conta() {
		return tipo_conta;
	}

	public void setTipo_conta(String tipo_conta) {
		this.tipo_conta = tipo_conta;
	}

	public int getConta() {
		return conta;
	}

	public void setConta(int conta) {
		this.conta = conta;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	public boolean chamaBanco() {
		bd.conectar();
		return bd.incluirContas(this.agencia, this.tipo_conta,this.saldo, this.conta+"");
	}
	public int ultimaConta() {
		bd.conectar();
		return bd.ultimaContaValida();
		
	}
}
