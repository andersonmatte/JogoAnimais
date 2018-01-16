package br.com.andersonmatte.jogoAnimais.entidade;

/**
 * Classe que contém os atributos dos animais.
 * 
 * @author Anderson Matte 
 * 
 */
public class Animal {

	private String nomeAnimal;
	
	private String caracteristicaAnimal;

	public String getNomeAnimal() {
		return nomeAnimal;
	}

	public void setNomeAnimal(String nomeAnimal) {
		this.nomeAnimal = nomeAnimal;
	}

	public String getCaracteristicaAnimal() {
		return caracteristicaAnimal;
	}

	public void setCaracteristicaAnimal(String caracteristicaAnimal) {
		this.caracteristicaAnimal = caracteristicaAnimal;
	}
	
}
