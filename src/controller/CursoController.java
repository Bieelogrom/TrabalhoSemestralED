package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Repository.CursoRepository;
import model.Curso;

public class CursoController implements ActionListener {
	
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
	
}
