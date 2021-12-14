package br.com.gama.tech.projeto.homologacao.negocio;

import br.com.gama.tech.projeto.homologacao.model.BancoDeDados;

public class Contas {
	String agencia, tipo_conta, conta, saldo;

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

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	public boolean chamaBanco() {
		BancoDeDados bd = new BancoDeDados();
		bd.conectar();
		return bd.incluirContas(this.agencia, this.tipo_conta,this.saldo, this.conta);
	}
}
