package Repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
		criarTabelaInscricao(novaInscricao.getCpfProfessor(),novaInscricao.getCodigoDisciplina());
		//\r\n adicionado para quebra de linha
		pw.write(novaInscricao.toString()+"\r\n");
		pw.flush();
		pw.close();
		fw.close();
	}

	private void criarTabelaInscricao(String cpf, String cod) {
		// TODO Auto-generated method stub
		
	}
}
