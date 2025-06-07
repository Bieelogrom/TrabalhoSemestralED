package Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import br.edu.fateczl.Lista;
import model.Curso;
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
	
	public Lista<Disciplina> visualizar() throws IOException{
		Lista<Disciplina> listaDeDisciplinas = new Lista<>();
		String path = System.getProperty("user.home") + File.separator + PASTA;
		File arq = new File(path, ARQUIVO);
		if(arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while(linha != null) {
				String[] disciplina = linha.split(";");
				Disciplina disciplinaDaLista = new Disciplina(disciplina[0], disciplina[1], disciplina[2], disciplina[3], Integer.parseInt(disciplina[4]), disciplina[5]);
				listaDeDisciplinas.addFirst(disciplinaDaLista);
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		return listaDeDisciplinas;
	}
	
	public void remover(Lista<Disciplina> listaDeDisciplinasAtualizadas) throws Exception{
		String path = System.getProperty("user.home") + File.separator + PASTA;
		File arq = new File(path, ARQUIVO);
		FileWriter fw = new FileWriter(arq);
		PrintWriter pw = new PrintWriter(fw);
		for(int i = 0; i < listaDeDisciplinasAtualizadas.size(); i++) {
			pw.write(listaDeDisciplinasAtualizadas.get(i).toString()+"\r\n");
		}
		pw.flush();
		pw.close();
		fw.close();
	}

}
