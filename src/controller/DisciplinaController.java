package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Repository.CursoRepository;
import Repository.DisciplinaRepository;
import br.edu.fateczl.Lista;
import model.Curso;
import model.Disciplina;
import view.elementos.IPainelDisciplinas;
import view.elementos.TableActionEvent;

public class DisciplinaController implements ActionListener, TableActionEvent  {

	private String patternCodigoDisciplina = "^[A-Za-z]{3}[0-9]{3}$"; 
	private CursoRepository cursoRepository;
	private IPainelDisciplinas callback;
	private JTextField codigoDisciplina;
	private JTextField nomeDisciplina;
	private JComboBox diaDaSemanaDisciplina;
	private JTextField quantidadeHorasCurso;
	private JComboBox cursoDisciplina;
	private JFormattedTextField horarioDisciplina;
	private DisciplinaRepository disciplinaRepository;
	
	public DisciplinaController(IPainelDisciplinas callback, JTextField codigoDisciplina, JTextField nomeDisciplina, JComboBox diaDaSemanaDisciplina, JTextField quantidadeHorasCurso, JComboBox cursoDisciplina, JFormattedTextField horarioDisciplina) {
		this.cursoRepository = new CursoRepository();
		this.callback = callback;
		this.codigoDisciplina = codigoDisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.diaDaSemanaDisciplina = diaDaSemanaDisciplina;
		this.quantidadeHorasCurso = quantidadeHorasCurso;
		this.cursoDisciplina = cursoDisciplina;
		this.horarioDisciplina = horarioDisciplina;
		this.disciplinaRepository = new DisciplinaRepository();
	}
	
	public DisciplinaController() {
		this.cursoRepository = new CursoRepository();
		this.disciplinaRepository = new DisciplinaRepository();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("Cadastrar nova disciplina")) {
			cadastrarDisciplina();
		}
	}
	
	private void cadastrarDisciplina() {
		/*
		 * Try catchs por todo lado
		 */
		try {
			String codigo = codigoDisciplina.getText();
			String nome = nomeDisciplina.getText();
			String diaDaSemana = (String) diaDaSemanaDisciplina.getSelectedItem();
			String horario = horarioDisciplina.getText();
			
			int quantidadeHoras;
			try {
				quantidadeHoras = Integer.parseInt(quantidadeHorasCurso.getText());
			}catch(NumberFormatException e) {
				throw new IllegalArgumentException("Digite apenas números no campo 'Quantidade de Horas'.");
			}
			
			String curso = (String) cursoDisciplina.getSelectedItem();
			
			if(!codigo.matches(patternCodigoDisciplina)) {
				throw new IllegalArgumentException("Digite um código da disciplina válido!");
			}
			if(nome.isBlank()) {
				throw new IllegalArgumentException("Digite um nome válido para a disciplina!");
			}
			
			Disciplina novaDisciplina = new Disciplina(codigo,nome,diaDaSemana,horario,quantidadeHoras,curso);
			disciplinaRepository.salvar(novaDisciplina);
			callback.limparTextos();
			JOptionPane.showMessageDialog(null, "Disciplina cadastrado com sucesso!");
		}catch(IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}


	//Esse método está aqui para carregar o combobox de cursos.
	public Lista<Curso> listarCursos() throws Exception {
		Lista<Curso> listaDeCursos = cursoRepository.visualizar();
		return listaDeCursos;
	}
	
	//Esse método está aqui para uso geral e também carregar a tabela de visualização.
	public Lista<Disciplina> listarDisciplinas() throws Exception {
		Lista<Disciplina> listaDeDisciplinas = disciplinaRepository.visualizar();
		return listaDeDisciplinas;
	}

	@Override
	public void onEdit(int row) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDelete(int row) {
		int deletar = JOptionPane.showConfirmDialog(null, "Deseja excluir?");
		if(deletar == JOptionPane.YES_OPTION) {
			try {
				Lista<Disciplina> listaDeDisciplinas = listarDisciplinas();
				listaDeDisciplinas.remove(row);
				disciplinaRepository.remover(listaDeDisciplinas);
				callback.atualizarTabela();
				JOptionPane.showMessageDialog(null, "Disciplina excluído com sucesso!");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public DisciplinaRepository getDisciplinaRepository() {
		return this.disciplinaRepository;
	}
}
