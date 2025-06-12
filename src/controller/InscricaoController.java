package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Repository.InscricaoRepository;
import br.edu.fateczl.fila.Fila;
import model.Inscricao;
import view.elementos.IPainelInscricoes;
import view.elementos.TableActionEvent;

public class InscricaoController implements ActionListener, TableActionEvent {
	
	private String patternCodigoCurso = "^[A-Za-z]{3}[0-9]{3}$"; 
	private JComboBox<String> cpfProfessor;
	private JComboBox<String> codigoDisciplina;
	private JFormattedTextField codigoProcesso;
	private InscricaoRepository inscricaoRepository;
	private IPainelInscricoes callback;
	
	public InscricaoController(JComboBox<String> cpfProfessor, JComboBox<String> codigoDisciplina, JFormattedTextField codigoProcesso, IPainelInscricoes callback) {
		this.cpfProfessor = cpfProfessor;
		this.codigoDisciplina = codigoDisciplina;
		this.codigoProcesso = codigoProcesso;
		this.callback = callback;
		this.inscricaoRepository = new InscricaoRepository();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("Inscrever professor")) {
			try {
				inscreverProfessor();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	
	private void inscreverProfessor() throws Exception {
		String cpf = (String) cpfProfessor.getSelectedItem();
		String codDisc = (String) codigoDisciplina.getSelectedItem();
		String codProc = codigoProcesso.getText();
		
		/*
		 * Usei aquele regex pra forçar usuário a digitar os código de uma forma, pórem, o mais eficiente era usar o próprio Jformmated com mask
		 */
		
		if(codProc.isBlank() || !codProc.matches(patternCodigoCurso)) {
			throw new IllegalArgumentException("Digite um código de processo válido!");
		}
		
		Inscricao novaInscricao = new Inscricao(cpf, codDisc, codProc);
		inscricaoRepository.salvar(novaInscricao);
		callback.atualizarTabela();
	}


	public Fila<Inscricao> enfileirarInscricoes() throws Exception {
		Fila<Inscricao> filaDeInscricoes = inscricaoRepository.visualizarFila();
		return filaDeInscricoes;
	}

	@Override
	public void onEdit(int row) {
		System.out.println("Gabriel");
	}

	@Override
	public void onDelete(int row) {
		// TODO Auto-generated method stub
		
	}
}
