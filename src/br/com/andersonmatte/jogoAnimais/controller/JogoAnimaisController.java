package br.com.andersonmatte.jogoAnimais.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.com.andersonmatte.jogoAnimais.entidade.Animal;

/**
 * Classe que executa o jogo de animais.
 * 
 * @author Anderson Matte 
 * 
 */
public class JogoAnimaisController {

	private String nomeAnimalAquatico = null;
	private String nomeAnimalTerrestre = null;
	private String caracteristicaAnimalAquatico = null;
	private String caracteristicaAnimalTerrestre = null;

	private List<Animal> listaAnimalAquatico = new ArrayList<>();
	private List<Animal> listaAnimalTerrestre = new ArrayList<>();

	private ResourceBundle mensagensDoJogo = null;

	private int resposta;

	private static JogoAnimaisController jogoAnimaisController = new JogoAnimaisController();

	/**
	 * Método mconstrutor da classe JogoAnimaisController é aproveitada para carregar o
	 * arquivo de properties que contém as mensagens.
	 * 
	 */
	public JogoAnimaisController() {
		this.mensagensDoJogo = PropertyResourceBundle.getBundle("br.com.andersonmatte.jogoAnimais.mensagens.JogoAnimaisMensagens");
	}

	/**
	 * Método main executa o jogo de animais.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		jogoAnimaisController.jogar();
	}

	/**
	 * Método que controla o jogo e que chama os métodos lógicos conforme o tipo
	 * de aniaml aquatico ou terrestre.
	 * 
	 * @return Boolean
	 * 
	 */
	public void jogar() {
		this.resposta = JOptionPane.showConfirmDialog(null, this.mensagensDoJogo.getString("perguntaInicial"), this.mensagensDoJogo.getString("title.jogo"), JOptionPane.DEFAULT_OPTION, -1);
		if (this.resposta == JOptionPane.YES_OPTION) {
			this.resposta = JOptionPane.showConfirmDialog(null, this.mensagensDoJogo.getString("perguntaAgua"), this.mensagensDoJogo.getString("title.confirme"), JOptionPane.YES_NO_OPTION);
			if (this.resposta == JOptionPane.YES_OPTION) {
				this.controlaAnimalAquatico();
			} else if (this.resposta == JOptionPane.NO_OPTION) {
				this.controlaAnimalTerrestre();
			}		
		}
	}
	
	/**
	 * Metodo que realiza o controle dos animais aquaticos.Se já houver uma ou
	 * mais caracteristicas definidas pelo jogador, será consistida a informação
	 * de nome e caracterisca até o aceite do jogador ou cadastro de outro
	 * animal com a sua caracteristica.
	 */
	public void controlaAnimalAquatico(){
		if (this.listaAnimalAquatico != null && !this.listaAnimalAquatico.isEmpty()) {
			int contadorAquatico = 0;
			for (Animal animalAquatico : this.listaAnimalAquatico) {
				contadorAquatico++;
				if (animalAquatico != null) {
					this.resposta = JOptionPane.showConfirmDialog(null, this.mensagensDoJogo.getString("perguntaCaracteristica") + " " + animalAquatico.getCaracteristicaAnimal() + "?", " ", JOptionPane.YES_NO_OPTION);
					if (this.resposta == JOptionPane.YES_OPTION) {
						this.resposta = JOptionPane.showConfirmDialog(null, this.mensagensDoJogo.getString("perguntaNome") + " " + animalAquatico.getNomeAnimal() + "?", " ", JOptionPane.YES_NO_OPTION);
						if (this.resposta == JOptionPane.YES_OPTION) {
							JOptionPane.showMessageDialog(null, this.mensagensDoJogo.getString("respostaAcerto"), this.mensagensDoJogo.getString("title.jogoAnimais"), JOptionPane.DEFAULT_OPTION);
							jogar();
						} else if (this.resposta == JOptionPane.NO_OPTION) {
							animalAquatico.setNomeAnimal(JOptionPane.showInputDialog(null, this.mensagensDoJogo.getString("perguntaQualAnimal"), this.mensagensDoJogo.getString("title.desisto"), JOptionPane.DEFAULT_OPTION));
							if (animalAquatico.getNomeAnimal() != null) {
								animalAquatico.setCaracteristicaAnimal(JOptionPane.showInputDialog(null,
										this.mensagensDoJogo.getString("perguntaQualAnimalRespondida1")
										+ animalAquatico.getNomeAnimal() 
										+ this.mensagensDoJogo.getString("perguntaQualAnimalRespondida2")
										+ this.mensagensDoJogo.getString("macaco") + " "
										+ this.mensagensDoJogo.getString("perguntaQualAnimalRespondida3"), 
										this.mensagensDoJogo.getString("title.desisto"), JOptionPane.DEFAULT_OPTION));
								this.listaAnimalAquatico.add(animalAquatico);
								jogar();
							}
						}
						/*
						 * Se houver uma ou mais caracteristicas
						 * definidas pelo jogador, e mesmo assim não for
						 * selecionada. Então será questionado se o
						 * animal é um Tubarão.E se não for o Tubarão o
						 * jogador poderá cadastrar o seu animal.
						 */
					} else if (this.resposta == JOptionPane.NO_OPTION
							&& this.listaAnimalAquatico.size() == 1
							|| (this.resposta == JOptionPane.NO_OPTION && contadorAquatico + 1 > this.listaAnimalAquatico.size())) {
						this.resposta = JOptionPane.showConfirmDialog(null, this.mensagensDoJogo.getString("perguntaTubarao"), this.mensagensDoJogo.getString("title.confirme"), JOptionPane.YES_NO_OPTION);
						if (this.resposta == JOptionPane.YES_OPTION) {
							JOptionPane.showMessageDialog(null, this.mensagensDoJogo.getString("respostaAcerto"), this.mensagensDoJogo.getString("title.jogoAnimais"), JOptionPane.DEFAULT_OPTION);
							jogar();
						} else if (this.resposta == JOptionPane.NO_OPTION) {
							Animal novoAnimalAquatico = new Animal();
							novoAnimalAquatico.setNomeAnimal(JOptionPane.showInputDialog(null, this.mensagensDoJogo.getString("perguntaQualAnimal"), this.mensagensDoJogo.getString("title.desisto"), JOptionPane.DEFAULT_OPTION));
							if (novoAnimalAquatico.getNomeAnimal() != null) {
								novoAnimalAquatico.setCaracteristicaAnimal(JOptionPane.showInputDialog(null,
										this.mensagensDoJogo.getString("perguntaQualAnimalRespondida1")
										+ novoAnimalAquatico.getNomeAnimal()
										+ this.mensagensDoJogo.getString("perguntaQualAnimalRespondida2")
										+ this.mensagensDoJogo.getString("tubarao") + " "
										+ this.mensagensDoJogo.getString("perguntaQualAnimalRespondida3"),
										this.mensagensDoJogo.getString("title.desisto"), JOptionPane.DEFAULT_OPTION));
								this.listaAnimalAquatico.add(novoAnimalAquatico);
								jogar();
							}
						}
					}
				}
			}
		} else {
			/*
			 * Quando o animal do tipo aquatico e se ainda não houver
			 * uma caracteristica ainda definida pelo jogador, será
			 * questionado se o animal é Tubarão.
			 */
			this.resposta = JOptionPane.showConfirmDialog(null, this.mensagensDoJogo.getString("perguntaTubarao"), this.mensagensDoJogo.getString("title.confirme"), JOptionPane.YES_NO_OPTION);
			if (this.resposta == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, this.mensagensDoJogo.getString("respostaAcerto"), this.mensagensDoJogo.getString("title.jogoAnimais"), JOptionPane.DEFAULT_OPTION);
				jogar();
			} else if (this.resposta == JOptionPane.NO_OPTION) {
				this.nomeAnimalAquatico = JOptionPane.showInputDialog(null, this.mensagensDoJogo.getString("perguntaQualAnimal"), this.mensagensDoJogo.getString("title.desisto"), JOptionPane.DEFAULT_OPTION);
				if (this.nomeAnimalAquatico != null) {
					this.caracteristicaAnimalAquatico = JOptionPane.showInputDialog(null, 
							this.mensagensDoJogo.getString("perguntaQualAnimalRespondida1")
							+ this.nomeAnimalAquatico
							+ this.mensagensDoJogo.getString("perguntaQualAnimalRespondida2")
							+ this.mensagensDoJogo.getString("tubarao") + " "
							+ this.mensagensDoJogo.getString("perguntaQualAnimalRespondida3"),
							this.mensagensDoJogo.getString("title.desisto"), JOptionPane.DEFAULT_OPTION);
					Animal animalAquaticoAdicionado = new Animal();
					animalAquaticoAdicionado.setNomeAnimal(this.nomeAnimalAquatico);
					animalAquaticoAdicionado.setCaracteristicaAnimal(this.caracteristicaAnimalAquatico);
					this.listaAnimalAquatico.add(animalAquaticoAdicionado);
					jogar();
				}
			}
		}
	}
	
	/**
	 * Metodo que realiza o controle dos animais terrestres.Se já houver uma ou
	 * mais caracteristicas definidas pelo jogador, será consistida a informação
	 * de nome e caracterisca até o aceite do jogador ou cadastro de outro
	 * animal com a sua caracteristica.
	 */
	public void controlaAnimalTerrestre(){
		if (this.listaAnimalTerrestre != null && !this.listaAnimalTerrestre.isEmpty()) {
			int contadorTerrestre = 0;
			for (Animal animalTerrestre : this.listaAnimalTerrestre) {
				contadorTerrestre++;
				if (animalTerrestre != null) {
					this.resposta = JOptionPane.showConfirmDialog(null, this.mensagensDoJogo.getString("perguntaCaracteristica") + " " + animalTerrestre.getCaracteristicaAnimal() + "?", " ", JOptionPane.YES_NO_OPTION);
					if (this.resposta == JOptionPane.YES_OPTION) {
						this.resposta = JOptionPane.showConfirmDialog(null, this.mensagensDoJogo.getString("perguntaNome") + " " + animalTerrestre.getNomeAnimal() + "?", " ", JOptionPane.YES_NO_OPTION);
						if (this.resposta == JOptionPane.YES_OPTION) {
							JOptionPane.showMessageDialog(null, this.mensagensDoJogo.getString("respostaAcerto"), this.mensagensDoJogo.getString("title.jogoAnimais"), JOptionPane.DEFAULT_OPTION);
							jogar();
						} else if (this.resposta == JOptionPane.NO_OPTION) {
							animalTerrestre.setNomeAnimal(JOptionPane.showInputDialog(null, this.mensagensDoJogo.getString("perguntaQualAnimal"), this.mensagensDoJogo.getString("title.desisto"), JOptionPane.DEFAULT_OPTION));
							if (animalTerrestre.getNomeAnimal() != null) {
								animalTerrestre.setCaracteristicaAnimal(JOptionPane.showInputDialog(null,
										this.mensagensDoJogo.getString("perguntaQualAnimalRespondida1")
										+ animalTerrestre.getNomeAnimal() 
										+ this.mensagensDoJogo.getString("perguntaQualAnimalRespondida2")
										+ this.mensagensDoJogo.getString("macaco") + " "
										+ this.mensagensDoJogo.getString("perguntaQualAnimalRespondida3"), 
										this.mensagensDoJogo.getString("title.desisto"), JOptionPane.DEFAULT_OPTION));
								this.listaAnimalTerrestre.add(animalTerrestre);
								jogar();
							}
						}
						/*
						 * Se houver uma ou mais caracteristicas
						 * definidas pelo jogador, e mesmo assim não for
						 * selecionada. Então será questionado se o
						 * animal é um Macaco.E se não for o Macaco o
						 * jogador poderá cadastrar o seu animal.
						 */
					} else if (this.resposta == JOptionPane.NO_OPTION
							&& this.listaAnimalTerrestre.size() == 1
							|| (this.resposta == JOptionPane.NO_OPTION && contadorTerrestre + 1 > this.listaAnimalTerrestre.size())) {
						this.resposta = JOptionPane.showConfirmDialog(null, this.mensagensDoJogo.getString("perguntaMacaco"), this.mensagensDoJogo.getString("title.confirme"), JOptionPane.YES_NO_OPTION);
						if (this.resposta == JOptionPane.YES_OPTION) {
							JOptionPane.showMessageDialog(null, this.mensagensDoJogo.getString("respostaAcerto"), this.mensagensDoJogo.getString("title.jogoAnimais"), JOptionPane.DEFAULT_OPTION);
							jogar();
						} else if (this.resposta == JOptionPane.NO_OPTION) {
							Animal novoAnimalTerrestre = new Animal();
							novoAnimalTerrestre.setNomeAnimal(JOptionPane.showInputDialog(null, this.mensagensDoJogo.getString("perguntaQualAnimal"), this.mensagensDoJogo.getString("title.desisto"), JOptionPane.DEFAULT_OPTION));
							if (novoAnimalTerrestre.getNomeAnimal() != null) {
								novoAnimalTerrestre.setCaracteristicaAnimal(JOptionPane.showInputDialog(null,
										this.mensagensDoJogo.getString("perguntaQualAnimalRespondida1")
										+ novoAnimalTerrestre.getNomeAnimal()
										+ this.mensagensDoJogo.getString("perguntaQualAnimalRespondida2")
										+ this.mensagensDoJogo.getString("macaco") + " "
										+ this.mensagensDoJogo.getString("perguntaQualAnimalRespondida3"),
										this.mensagensDoJogo.getString("title.desisto"), JOptionPane.DEFAULT_OPTION));
								this.listaAnimalTerrestre.add(novoAnimalTerrestre);
								jogar();
							}
						}
					}
				}
			}
		} else {
			/*
			 * Quando o animal do tipo Terrestre e se ainda não houver
			 * uma caracteristica ainda definida pelo jogador, será
			 * questionado se o animal é Macaco.
			 */
			this.resposta = JOptionPane.showConfirmDialog(null, this.mensagensDoJogo.getString("perguntaMacaco"), this.mensagensDoJogo.getString("title.confirme"), JOptionPane.YES_NO_OPTION);
			if (this.resposta == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, this.mensagensDoJogo.getString("respostaAcerto"), this.mensagensDoJogo.getString("title.jogoAnimais"), JOptionPane.DEFAULT_OPTION);
				jogar();
			} else if (this.resposta == JOptionPane.NO_OPTION) {
				this.nomeAnimalTerrestre = JOptionPane.showInputDialog(null, this.mensagensDoJogo.getString("perguntaQualAnimal"), this.mensagensDoJogo.getString("title.desisto"), JOptionPane.DEFAULT_OPTION);
				if (this.nomeAnimalTerrestre != null) {
					this.caracteristicaAnimalTerrestre = JOptionPane.showInputDialog(null, 
							this.mensagensDoJogo.getString("perguntaQualAnimalRespondida1")
							+ this.nomeAnimalTerrestre
							+ this.mensagensDoJogo.getString("perguntaQualAnimalRespondida2")
							+ this.mensagensDoJogo.getString("macaco") + " "
							+ this.mensagensDoJogo.getString("perguntaQualAnimalRespondida3"),
							this.mensagensDoJogo.getString("title.desisto"), JOptionPane.DEFAULT_OPTION);
					Animal animalTerrestreAdicionado = new Animal();
					animalTerrestreAdicionado.setNomeAnimal(this.nomeAnimalTerrestre);
					animalTerrestreAdicionado.setCaracteristicaAnimal(this.caracteristicaAnimalTerrestre);
					this.listaAnimalTerrestre.add(animalTerrestreAdicionado);
					jogar();
				}
			}
		}
	}
	
}
