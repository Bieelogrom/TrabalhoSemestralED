package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Repository.ProfessorRepository;
import br.edu.fateczl.fila.Fila;
import model.Professor;
import view.elementos.TableActionEvent;

public class ProfessorController implements ActionListener, TableActionEvent {

	private JFormattedTextField cpfProfessor;
	private JTextField nomeProfessor;
	private JComboBox<String> areaConhecimento;
	private JTextField pontuacao;
	private ProfessorRepository professorRepository;
	
	public ProfessorController(JFormattedTextField cpfProfessor, JTextField nomeProfessor,
			JComboBox<String> areaConhecimento, JTextField pontuacao) {
		this.cpfProfessor = cpfProfessor;
		this.nomeProfessor = nomeProfessor;
		this.areaConhecimento = areaConhecimento;
		this.pontuacao = pontuacao;
		this.professorRepository = new ProfessorRepository();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("Cadastrar professor")) {
			cadastrarProfessor();
		}
	}

	private void cadastrarProfessor() {
		try {
			String cpf = cpfProfessor.getText();
			String nome = nomeProfessor.getText();
			String area = (String) areaConhecimento.getSelectedItem();
			float pontuacaoP = Float.parseFloat(pontuacao.getText().replace(",", "."));
			
			if(pontuacaoP < 0 || pontuacaoP > 100) {
				throw new IllegalArgumentException("Digite uma pontuação válida!");
			}
			
			Professor novoProfessor = new Professor(cpf,nome,area,pontuacaoP);
			professorRepository.salvar(novoProfessor);

			JOptionPane.showMessageDialog(null, "Professor cadastrado com sucesso!");
		}catch(IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	public Fila<Professor> enfileirarProfessores() throws IOException{
		Fila<Professor> filaDeProfessores = professorRepository.visualizar();
		return filaDeProfessores;
	}

	@Override
	public void onEdit(int row) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDelete(int row) {
		// TODO Auto-generated method stub
		
	}

}
