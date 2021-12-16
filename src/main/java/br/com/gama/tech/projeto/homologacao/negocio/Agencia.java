package br.com.gama.tech.projeto.homologacao.negocio;

import br.com.gama.tech.projeto.homologacao.model.BancoDeDados;

public class Agencia {
	String bairro, cidade, estado, rua, numero;
	BancoDeDados bd = new BancoDeDados();
	public int chamaBanco() {
		Utilitario ut = new Utilitario();
		bd = new BancoDeDados();
		bd.conectar();
		return bd.incluirAgencia(ut.substituir(this.bairro), ut.substituir(this.cidade), ut.substituir(this.estado), ut.substituir(this.rua), ut.substituir(this.numero));
	}
	public String consultarAgencia() {
		bd.conectar();
		return bd.listarAgencia();
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
}
