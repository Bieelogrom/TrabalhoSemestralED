package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Repository.CursoRepository;
import br.edu.fateczl.Lista;
import model.Curso;
import view.elementos.TableActionEvent;

public class CursoController implements ActionListener, TableActionEvent {
	
	//Obriga o usuário a digitar o código do curso dentro do padrão.
	private String patternCodigoCurso = "^[A-Za-z]{3}[0-9]{3}$"; 
	private JButton btnSalvarCurso;
	private JTextField txtfCodigoCurso;
	private JTextField txtfNomeCurso;
	private JComboBox<String> comboxareaconhecimento;
	private CursoRepository cursoRepository;
	
	public CursoController(JButton btnSalvarCurso, JTextField txtfCodigoCurso, JTextField txtfNomeCurso, JComboBox<String> comboxareaconhecimento) {
		this.btnSalvarCurso = btnSalvarCurso;
		this.txtfCodigoCurso = txtfCodigoCurso;
		this.txtfNomeCurso = txtfNomeCurso;
		this.comboxareaconhecimento = comboxareaconhecimento;
		this.cursoRepository = new CursoRepository();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("Cadastrar curso")) {
			cadastrarCurso();
		}
	}


	private void cadastrarCurso() {
		try {
			String codigoCurso = txtfCodigoCurso.getText();
			String nomeCurso = txtfNomeCurso.getText();
			String areaConhecimentoCurso = (String) comboxareaconhecimento.getSelectedItem();
			
			if(!codigoCurso.matches(patternCodigoCurso)) {
				throw new IllegalArgumentException("Digite um código de curso válido!");
			}
			if(nomeCurso.isBlank()) {
				throw new IllegalArgumentException("Digite um nome válido para o curso!");
			}
			Curso novoCurso = new Curso(codigoCurso, nomeCurso, areaConhecimentoCurso);
			cursoRepository.salvar(novoCurso);
			JOptionPane.showMessageDialog(null, "Curso cadastrado com sucesso!");
		}catch(IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	
	public Lista<Curso> listarCursos() throws Exception {
		Lista<Curso> listaDeCursos = cursoRepository.visualizar();
		return listaDeCursos;
	}

	/*
	 * Métodos especificamente criados para os dois botões de ação na tabela de cursos.
	 */
	@Override
	public void onEdit(int row) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDelete(int row) {
		int deletar = JOptionPane.showConfirmDialog(null, "Deseja excluir?");
		if(deletar == JOptionPane.YES_OPTION) {
			try {
				Lista<Curso> listaDeCursos = listarCursos();
				listaDeCursos.remove(row);
				cursoRepository.remover(listaDeCursos);
				JOptionPane.showMessageDialog(null, "Curso excluído com sucesso!");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
