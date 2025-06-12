package Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import br.edu.fateczl.fila.Fila;
import model.Curso;
import model.Inscricao;

public class InscricaoRepository {
	private static final String PASTA = "SIGA";
	private static final String ARQUIVO = "Inscricoes.csv";
	
	/*
	 * Objetivo é criar uma pasta onde vão ficar os csv de cada processo seletivo;
	 */
	public void salvar(Inscricao novaInscricao) throws IOException {
		String path = System.getProperty("user.home") + File.separator + PASTA;
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdir();
		}
		File arq = new File(path, ARQUIVO);
		boolean existe = false;
		if(arq.exists()) {
			existe = true;
		}
		FileWriter fw = new FileWriter(arq, existe);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(novaInscricao.toString()+"\r\n");
		pw.flush();
		pw.close();
		fw.close();
	}

	public Fila<Inscricao> visualizarFila() throws Exception {
		Fila<Inscricao> filaDeInscricoes = new Fila<>();
		String path = System.getProperty("user.home") + File.separator + PASTA;
		File arq = new File(path, ARQUIVO);
		if(arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while(linha != null) {
				String[] inscricao = linha.split(";");
				Inscricao cursoDaLista = new Inscricao(inscricao[0], inscricao[1], inscricao[2]);
				filaDeInscricoes.insert(cursoDaLista);
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		return filaDeInscricoes;
	}
}
