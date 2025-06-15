package model;

public class MergeSort {
	public MergeSort() {
		// TODO Auto-generated constructor stub
	}
	
	public int[] ordenarArray(int[] vetor, int inicio, int fim) {
		if(inicio<fim) {
			int meio = (inicio + fim)/2;
			ordenarArray(vetor, inicio, meio);
			ordenarArray(vetor, meio + 1, fim);
			intercala(vetor, inicio, meio, fim);
		}
		
		return vetor;
	}

	private void intercala(int[] vetor, int inicio, int meio, int fim) {
		int[] novoVetor = new int[vetor.length];
		for(int i = inicio; i <= fim; i++) {
			novoVetor[i]=vetor[i];
		}
		
		int esq = inicio;
		int dir = meio + 1;
		
		for(int cont = inicio; cont <= fim; cont++) {
			if(esq > meio) {
				vetor[cont] = novoVetor[dir];
				dir++;
			}else if(dir > fim) {
				vetor[cont] = novoVetor[esq];
				esq++;
			}else if(novoVetor[esq]<novoVetor[dir]) {
				vetor[cont] = novoVetor[esq];
				esq++;
			}else {
				vetor[cont] = novoVetor[dir];
				dir++;
			}
		}
 	}
}
