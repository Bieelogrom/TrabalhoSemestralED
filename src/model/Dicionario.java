package model;

import br.edu.fateczl.gabriel.Lista;

public class Dicionario {
	Lista[] tabelaHashDicionario;
	
	public Dicionario() {
		tabelaHashDicionario = new Lista[13];
		inicializarTabelaHash();
	}

	private void inicializarTabelaHash() {
		int tamanho = tabelaHashDicionario.length;
		for(int i = 0; i < tamanho; i++) {
			tabelaHashDicionario[i] = new Lista<>();
		}
	}
	
	public void adicionarDisciplina(Disciplina disc) {
		int posicao = disc.hashCode(tabelaHashDicionario.length);
		tabelaHashDicionario[posicao].addFirst(disc);
	}
}
