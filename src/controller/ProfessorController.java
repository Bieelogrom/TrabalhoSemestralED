package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Repository.ProfessorRepository;
import br.edu.fateczl.fila.Fila;
import br.edu.fateczl.gabriel.Lista;
import model.Professor;
import view.elementos.IPainelProfessores;
import view.elementos.TableActionEvent;

public class ProfessorController implements ActionListener, TableActionEvent {

	private JFormattedTextField cpfProfessor;
	private JTextField nomeProfessor;
	private JComboBox<String> areaConhecimento;
	private JFormattedTextField pontuacao;
	private ProfessorRepository professorRepository;
	private IPainelProfessores callback;
	
	public ProfessorController(JFormattedTextField cpfProfessor, JTextField nomeProfessor,
			JComboBox<String> areaConhecimento, JFormattedTextField pontuacao, IPainelProfessores callback) {
		this.cpfProfessor = cpfProfessor;
		this.nomeProfessor = nomeProfessor;
		this.areaConhecimento = areaConhecimento;
		this.pontuacao = pontuacao;
		this.professorRepository = new ProfessorRepository();
		this.callback = callback;
	}
	
	public ProfessorController() {
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
			float pontuacaoP = Float.parseFloat(pontuacao.getText());
			
			if(pontuacaoP < 0 || pontuacaoP > 100) {
				throw new IllegalArgumentException("Digite uma pontuação válida!");
			}
			
			Professor novoProfessor = new Professor(cpf,nome,area,pontuacaoP);
			professorRepository.salvar(novoProfessor);
			callback.limparTextos();
			JOptionPane.showMessageDialog(null, "Professor cadastrado com sucesso!");
		}catch(IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	public Lista<Professor> listarProfessor() throws Exception {
		Lista<Professor> listaDeProfessores = professorRepository.visualizarLista();
		return listaDeProfessores;
	}
	
	public Fila<Professor> enfileirarProfessores() throws IOException{
		Fila<Professor> filaDeProfessores = professorRepository.visualizar();
		return filaDeProfessores;
	}

	@Override
	public void onEdit(int row) {
		int editar = JOptionPane.showConfirmDialog(null, "Deseja editar?");
		if(editar == JOptionPane.YES_OPTION) {
			try {
				Lista<Professor> listaDeProfessor = professorRepository.visualizarLista();
				Professor professor = listaDeProfessor.get(row);
				
				String cpf;
				JFormattedTextField campoCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
				int option = JOptionPane.showConfirmDialog(null,campoCpf,"Digite o CPF do Professor",JOptionPane.OK_CANCEL_OPTION);
				cpf = campoCpf.getText();
				if(option != JOptionPane.OK_OPTION) return;
				
				String nome = JOptionPane.showInputDialog(null, "Digite o nome do professor", professor.getNome());
				
				String[] area = {"Ciências Agrárias","Ciências Biológicas","Ciências da Saúde","Ciências Exatas e da Terra","Ciências Sociais Aplicadas","Engenharias","Ciências Humanas","Linguística, Letras e Artes"};
				JComboBox<String> comboArea = new JComboBox<>(area);
				comboArea.setSelectedItem(professor.getArea());
				int comboAreaResultado = JOptionPane.showConfirmDialog(null, comboArea, "Selecione a área", JOptionPane.OK_CANCEL_OPTION);
				if (comboAreaResultado != JOptionPane.OK_OPTION) return;
				
				float pontuacao = Float.parseFloat(JOptionPane.showInputDialog(null,"Digite a pontuação", professor.getPontuacao()));

				listaDeProfessor.remove(row);
				professorRepository.remover(listaDeProfessor);
				
				professor.setCpf(cpf);
				professor.setNome(nome);
				professor.setArea((String) comboArea.getSelectedItem());
				professor.setPontuacao(pontuacao);
				
				professorRepository.salvar(professor);
				callback.atualizarTabela();;
				JOptionPane.showMessageDialog(null, "Disciplina editada com sucesso!");
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
	}

	@Override
	public void onDelete(int row) {
		int deletar = JOptionPane.showConfirmDialog(null, "Deseja excluir?");
		if(deletar == JOptionPane.YES_OPTION) {
			try {
				Lista<Professor> listaDeProfessores = professorRepository.visualizarLista();
				listaDeProfessores.remove(row);
				professorRepository.remover(listaDeProfessores);
				callback.atualizarTabela();
				JOptionPane.showMessageDialog(null, "Professor excluído com sucesso!");
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
	}

	public boolean professorExiste(String cpf) throws Exception {
		Lista<Professor> listaDeProfessores = listarProfessor();
		for(int i = 0; i < listaDeProfessores.size(); i++) {
			Professor professor = listaDeProfessores.get(i);
			if(professor.getCpf().equals(cpf)) {
				return true;
			}
		}
		return false;
	}
}
