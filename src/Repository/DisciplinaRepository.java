package Repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import model.Disciplina;

public class DisciplinaRepository {
	private static final String PASTA = "SIGA";
	private static final String ARQUIVO = "Disciplinas.csv";
	
	public void salvar(Disciplina novaDisciplina) throws IOException {
		String path = System.getProperty("user.home") + File.separator + PASTA;
		File dir = new File(path);
		File arq = new File(path, ARQUIVO);
		boolean existe = false;
		if(arq.exists()) {
			existe = true;
		}
		FileWriter fw = new FileWriter(arq, existe);
		PrintWriter pw = new PrintWriter(fw);
		//\r\n adicionado para quebra de linha
		pw.write(novaDisciplina.toString()+"\r\n");
		pw.flush();
		pw.close();
		fw.close();
	}
}
