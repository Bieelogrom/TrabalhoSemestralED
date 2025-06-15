package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Repository.DisciplinaRepository;
import Repository.InscricaoRepository;
import br.edu.fateczl.fila.Fila;
import br.edu.fateczl.gabriel.Lista;
import model.Disciplina;
import model.Inscricao;
import model.Professor;
import view.elementos.IPainelInscricoes;
import view.elementos.TableActionEvent;

public class InscricaoController implements ActionListener, TableActionEvent {
	
	private String patternCodigoCurso = "^[A-Za-z]{3}[0-9]{3}$"; 
	private JComboBox<String> cpfProfessor;
	private JComboBox<String> codigoDisciplina;
	private JFormattedTextField codigoProcesso;
	private InscricaoRepository inscricaoRepository;
	private IPainelInscricoes callback;
	private DisciplinaController disciplinaController;
	private ProfessorController professorController;
	
	public InscricaoController(JComboBox<String> cpfProfessor, JComboBox<String> codigoDisciplina, JFormattedTextField codigoProcesso, IPainelInscricoes callback) {
		this.cpfProfessor = cpfProfessor;
		this.codigoDisciplina = codigoDisciplina;
		this.codigoProcesso = codigoProcesso;
		this.callback = callback;
		this.inscricaoRepository = new InscricaoRepository();
		this.disciplinaController = new DisciplinaController();
		this.professorController = new ProfessorController();
	}
	
	public InscricaoController() {
		this.inscricaoRepository = new InscricaoRepository();
		this.professorController = new ProfessorController();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("Inscrever professor")) {
			try {
				inscreverProfessor();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	
	private void inscreverProfessor() throws Exception {
		String cpf = (String) cpfProfessor.getSelectedItem();
		String codDisc = (String) codigoDisciplina.getSelectedItem();
		String codProc = codigoProcesso.getText();
		
		
		if(cpf == null || cpf.isEmpty() || codDisc == null || codDisc.isEmpty() || codProc == null || codProc.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios.");
		    return;
		}
		/*
		 * Usei aquele regex pra forçar usuário a digitar os código de uma forma, pórem, o mais eficiente era usar o próprio Jformmated com mask
		 */
		
		if(codProc.isBlank() || !codProc.matches(patternCodigoCurso)) {
			throw new IllegalArgumentException("Digite um código de processo válido!");
		}
		
		if(!professorController.professorExiste(cpf)) {
			 JOptionPane.showMessageDialog(null, "Professor não encontrado.");
			 return;
		}
		
		if(jaInscrito(cpf, codDisc)) {
			 JOptionPane.showMessageDialog(null, "Professor já está inscrito nessa disciplina!.");
			 return;
		}
		
		Inscricao novaInscricao = new Inscricao(cpf, codDisc, codProc);
		inscricaoRepository.salvar(novaInscricao);
		callback.atualizarTabela();
	}

	
	private boolean jaInscrito(String cpf, String codDisciplina) throws Exception {
		Lista<Inscricao> listaDeIncricoes = listaInscricoes();
		for(int i = 0; i < listaDeIncricoes.size(); i++) {
			Inscricao inscrito = listaDeIncricoes.get(i);
			if(inscrito.getCpfProfessor().equals(cpf) && codDisciplina.equals(inscrito.getCodigoDisciplina())) {
				return true;
			}
		}
		return false;
	}

	public Fila<Inscricao> enfileirarInscricoes() throws Exception {
		Fila<Inscricao> filaDeInscricoes = inscricaoRepository.visualizarFila();
		return filaDeInscricoes;
	}
	
	public Lista<Inscricao> listaInscricoes() throws Exception{
		Lista<Inscricao> listaDeInscricao = inscricaoRepository.visualizarLista();
		return listaDeInscricao;
	}

	@Override
	public void onEdit(int row) {
		int editar = JOptionPane.showConfirmDialog(null, "Deseja editar?");
		if(editar == JOptionPane.YES_OPTION) {
			try {
				Lista<Inscricao> inscritos = listaInscricoes();
				Lista<Disciplina> disciplinas = disciplinaController.listarDisciplinas();
				Lista<Professor> professores = professorController.listarProfessor();
				Inscricao inscrito = inscritos.get(row);
				
				String codigoDisciplinaC;
			
				String[] codigosDasDisciplinas = new String[disciplinas.size()];
				for(int i = 0; i < disciplinas.size(); i++) {
					codigosDasDisciplinas[i] = disciplinas.get(i).getCodigoDisciplina();
				}
				
				String[] cpfDosProfessores = new String[professores.size()];
				for(int i = 0; i < professores.size(); i++) {
					cpfDosProfessores[i] = professores.get(i).getCpf();
				}
				
				JComboBox<String> comboBoxCodigoDeDisciplinas = new JComboBox<>(codigosDasDisciplinas);
				comboBoxCodigoDeDisciplinas.setSelectedItem(codigoDisciplina.getSelectedItem());
				int codigoBox = JOptionPane.showConfirmDialog(null, comboBoxCodigoDeDisciplinas, "Selecione o código da disciplina", JOptionPane.OK_CANCEL_OPTION);
				if (codigoBox != JOptionPane.OK_OPTION) return;
				
				JComboBox<String> comboBoxCpfDoProfessor = new JComboBox<>(cpfDosProfessores);
				comboBoxCpfDoProfessor.setSelectedItem(cpfProfessor.getSelectedItem());
				int codigoBox2 = JOptionPane.showConfirmDialog(null, comboBoxCpfDoProfessor, "Selecione o CPF do professor", JOptionPane.OK_CANCEL_OPTION);
				if (codigoBox2 != JOptionPane.OK_OPTION) return;
				
				String campocodigoprocesso;
				JFormattedTextField campocodigoprocessos = new JFormattedTextField(new MaskFormatter("COD###"));
				int option = JOptionPane.showConfirmDialog(null,campocodigoprocessos,"Digite o horário da disciplina",JOptionPane.OK_CANCEL_OPTION);
				campocodigoprocesso = campocodigoprocessos.getText();
				if (option != JOptionPane.OK_OPTION) return;
				
				
				inscritos.remove(row);
				inscricaoRepository.remover(inscritos);
				
				inscrito.setCodigoDisciplina((String) comboBoxCodigoDeDisciplinas.getSelectedItem());
				inscrito.setCpfProfessor((String) comboBoxCpfDoProfessor.getSelectedItem());
				inscrito.setCodigoProcesso(campocodigoprocesso);
				
				inscricaoRepository.salvar(inscrito);
				callback.atualizarTabela();
				JOptionPane.showMessageDialog(null, "Inscrição editada com sucesso!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}

	@Override
	public void onDelete(int row) {
		int deletar = JOptionPane.showConfirmDialog(null, "Deseja excluir?");
		if(deletar == JOptionPane.YES_OPTION) {
			try {
				Lista<Inscricao> listaDeInscritos = inscricaoRepository.visualizarLista();
				listaDeInscritos.remove(row);
				inscricaoRepository.remover(listaDeInscritos);
				callback.atualizarTabela();
				JOptionPane.showMessageDialog(null, "Inscrição excluída com sucesso!");
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
	}

	public InscricaoRepository getInscricaoRepository() {
		return this.inscricaoRepository;
	}
}
