package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Repository.InscricaoRepository;
import br.edu.fateczl.fila.Fila;
import model.Inscricao;
import view.elementos.TableActionEvent;

public class InscricaoController implements ActionListener, TableActionEvent {
	
	private String patternCodigoCurso = "^[A-Za-z]{3}[0-9]{3}$"; 
	private JComboBox<String> cpfProfessor;
	private JComboBox<String> codigoDisciplina;
	private JFormattedTextField codigoProcesso;
	private InscricaoRepository inscricaoRepository;
	
	public InscricaoController(JComboBox<String> cpfProfessor, JComboBox<String> codigoDisciplina, JFormattedTextField codigoProcesso) {
		this.cpfProfessor = cpfProfessor;
		this.codigoDisciplina = codigoDisciplina;
		this.codigoProcesso = codigoProcesso;
		this.inscricaoRepository = new InscricaoRepository();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("Inscrever professor")) {
			System.out.println("erro");
		}
	}

	
	public Fila<Inscricao> enfileirarInscricoes() throws Exception {
		Fila<Inscricao> filaDeInscricoes = inscricaoRepository.visualizarFila();
		return filaDeInscricoes;
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
