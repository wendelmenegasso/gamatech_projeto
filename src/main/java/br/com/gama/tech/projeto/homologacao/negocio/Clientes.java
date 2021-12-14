package br.com.gama.tech.projeto.homologacao.negocio;

import br.com.gama.tech.projeto.homologacao.model.BancoDeDados;

public class Clientes {
	String nome, cpf, email, telefone, numero_conta;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNumero_conta() {
		return numero_conta;
	}

	public void setNumero_conta(String numero_conta) {
		this.numero_conta = numero_conta;
	}
	public boolean chamaBanco() {
		BancoDeDados bd = new BancoDeDados();
		bd.conectar();
		return bd.incluirCliente(this.nome, this.cpf, Integer.parseInt(this.numero_conta), this.telefone, this.email);
	}
}
