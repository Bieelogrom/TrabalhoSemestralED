package model;

import br.edu.fateczl.gabriel.Lista;

//Não sei outro nome para isso
public class Dicionario {
	private Lista[] tabelaHashDicionario;
	
	public Dicionario() {
		setTabelaHashDicionario(new Lista[13]);
		inicializarTabelaHash();
	}

	private void inicializarTabelaHash() {
		int tamanho = getTabelaHashDicionario().length;
		for(int i = 0; i < tamanho; i++) {
			getTabelaHashDicionario()[i] = new Lista<>();
		}
	}
	
	public void adicionarDisciplina(Disciplina disc) {
		int posicao = disc.hashCode(getTabelaHashDicionario().length);
		getTabelaHashDicionario()[posicao].addFirst(disc);
	}

	public Lista[] getTabelaHashDicionario() {
		return tabelaHashDicionario;
	}

	public void setTabelaHashDicionario(Lista[] tabelaHashDicionario) {
		this.tabelaHashDicionario = tabelaHashDicionario;
	}
}
