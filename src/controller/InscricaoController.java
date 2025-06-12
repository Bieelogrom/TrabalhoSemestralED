package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Inscricao;

public class InscricaoController implements ActionListener {
	private JTextField cpfProfessor;
	private JTextField codigoDisciplina;
	
	public InscricaoController(JTextField cpfProfessor, JTextField codigoDisciplina) {
		this.cpfProfessor = cpfProfessor;
		this.codigoDisciplina = codigoDisciplina;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("Inscrever professor")) {
			inscreverProfessor();
		}
	}

	private void inscreverProfessor() {
		try {
			String cpf = cpfProfessor.getText();
			String disciplina = codigoDisciplina.getText();
			
			Inscricao novaInscricao = new Inscricao(cpf,disciplina);
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	
}
