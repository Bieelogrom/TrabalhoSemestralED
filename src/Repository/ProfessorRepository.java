package Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


import br.edu.fateczl.fila.Fila;
import br.edu.fateczl.gabriel.Lista;
import model.Curso;
import model.Professor;

public class ProfessorRepository {
	private static final String PASTA = "SIGA";
	private static final String ARQUIVO = "Professores.csv";
	
	public void salvar(Professor novoProfessor) throws IOException {
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
		pw.write(novoProfessor.toString()+"\r\n");
		pw.flush();
		pw.close();
		fw.close();
	}

	public Fila<Professor> visualizar() throws IOException {
		Fila<Professor> filaDeProfessores = new Fila<>();
		String path = System.getProperty("user.home") + File.separator + PASTA;
		File arq = new File(path, ARQUIVO);
		if(arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while(linha != null) {
				String[] professor = linha.split(";");
				Professor professorDaFila = new Professor(professor[0], professor[1], professor[2], Float.parseFloat(professor[3]));
				filaDeProfessores.insert(professorDaFila);
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		return filaDeProfessores;
	}

	public Lista<Professor> visualizarLista() throws Exception {
		Lista<Professor> listaDeProfessores = new Lista<>();
		String path = System.getProperty("user.home") + File.separator + PASTA;
		File arq = new File(path, ARQUIVO);
		if(arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while(linha != null) {
				String[] professor = linha.split(";");
				Professor professorDaLista = new Professor(professor[0],professor[1],professor[2],Float.parseFloat(professor[3]));
				listaDeProfessores.addLast(professorDaLista);
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		return listaDeProfessores;
	}

	public void remover(Lista<Professor> listaDeProfessor) throws Exception {
		String path = System.getProperty("user.home") + File.separator + PASTA;
		File arq = new File(path, ARQUIVO);
		FileWriter fw = new FileWriter(arq);
		PrintWriter pw = new PrintWriter(fw);
		for(int i = 1; i < listaDeProfessor.size(); i++) {
			pw.write(listaDeProfessor.get(i).toString()+"\r\n");
		}
		pw.flush();
		pw.close();
		fw.close();
	}
}
