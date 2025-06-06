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

public class CursoRepository {
	
	private static final String PASTA = "SIGA";
	private static final String ARQUIVO = "Cursos.csv";
	
	public void salvar(Curso novoCurso) throws IOException {
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
		//\r\n adicionado para quebra de linha
		pw.write(novoCurso.toString()+"\r\n");
		pw.flush();
		pw.close();
		fw.close();
	}
	
	public Lista<Curso> visualizar() throws Exception{
		Lista<Curso> listaDeCursos = new Lista<>();
		String path = System.getProperty("user.home") + File.separator + PASTA;
		File arq = new File(path, ARQUIVO);
		if(arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while(linha != null) {
				String[] curso = linha.split(";");
				Curso cursoDaLista = new Curso(curso[0], curso[1], curso[2]);
				listaDeCursos.addFirst(cursoDaLista);
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		return listaDeCursos;
	}
	
	public void remover(Lista<Curso> listaDeCursosAtualizada) throws Exception{
		String path = System.getProperty("user.home") + File.separator + PASTA;
		File arq = new File(path, ARQUIVO);
		FileWriter fw = new FileWriter(arq);
		PrintWriter pw = new PrintWriter(fw);
		for(int i = 0; i < listaDeCursosAtualizada.size(); i++) {
			pw.write(listaDeCursosAtualizada.get(i).toString()+"\r\n");
		}
		pw.flush();
		pw.close();
		fw.close();
	}
}
