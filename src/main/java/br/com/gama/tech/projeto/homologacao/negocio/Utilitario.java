package br.com.gama.tech.projeto.homologacao.negocio;

public class Utilitario {
	public String substituir(String str) {
		String retorno;
		retorno = str.replace('Ã', 'A');
		retorno = str.replace('Õ', 'O');
		retorno = str.replace('á', 'a');
		retorno = str.replace('é', 'e');
		retorno = str.replace('ú', 'u');
		retorno = str.replace('Á', 'A');
		retorno = str.replace('É', 'E');
		retorno = str.replace('Ú', 'U');
		retorno = str.replace('à', 'a');
		retorno = str.replace('Á', 'A');
		return retorno;
	}

}
