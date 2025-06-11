package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Repository.CursoRepository;
import Repository.DisciplinaRepository;
import br.edu.fateczl.fila.Fila;
import br.edu.fateczl.gabriel.Lista;
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
	private JTextField codigoProcesso;
	
	public DisciplinaController(IPainelDisciplinas callback, JTextField codigoDisciplina, JTextField nomeDisciplina, JComboBox diaDaSemanaDisciplina, JTextField quantidadeHorasCurso, JComboBox cursoDisciplina, JFormattedTextField horarioDisciplina, JTextField codigoProcesso) {
		this.cursoRepository = new CursoRepository();
		this.callback = callback;
		this.codigoDisciplina = codigoDisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.diaDaSemanaDisciplina = diaDaSemanaDisciplina;
		this.quantidadeHorasCurso = quantidadeHorasCurso;
		this.cursoDisciplina = cursoDisciplina;
		this.horarioDisciplina = horarioDisciplina;
		this.disciplinaRepository = new DisciplinaRepository();
		this.codigoProcesso = codigoProcesso;
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
			String processo = codigoProcesso.getText();
			
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
			if(processo.length() > 6) {
				throw new IllegalArgumentException("Digite um código de processo válido para a disciplina!");
			}
			
			Disciplina novaDisciplina = new Disciplina(codigo,processo,nome,diaDaSemana,horario,quantidadeHoras,curso);
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
		Lista<Curso> listaDeCursos = cursoRepository.visualizarLista();
		return listaDeCursos;
	}
	
	//Esse método está aqui para uso geral e também carregar a tabela de visualização.
	public Fila<Disciplina> enfileirarDisciplinas() throws Exception {
		Fila<Disciplina> filaDeDisciplinas = disciplinaRepository.visualizar();
		return filaDeDisciplinas;
	}
	
	public Lista<Disciplina> listarDisciplinas() throws Exception {
		Lista<Disciplina> listaDeDisciplinas = disciplinaRepository.visualizarLista();
		return listaDeDisciplinas;
	}

	@Override
	public void onEdit(int row) {
		int editar = JOptionPane.showConfirmDialog(null, "Deseja editar?");
		if(editar == JOptionPane.YES_OPTION) {
			try {
				Lista<Disciplina> listaDeDisciplinas = listarDisciplinas();
				Disciplina disciplina = listaDeDisciplinas.get(row);
				
				String[] opcoesSemana = {"Segunda-feira","Terça-feira","Quarta-feira","Quinta-feira","Sexta-feira","Sábado"};
				
				Lista<Curso> listaDeCursos = listarCursos();
				String[] cursosNome = new String[listaDeCursos.size()];
				for(int i = 0; i < listaDeCursos.size(); i++) {
					cursosNome[i] = listaDeCursos.get(i).getNomeCurso();
				}
				
				/*
				 * Conforme projeto vai crescendo o código vai ficando mais complexo;
				 */
				String codigoDisciplina = JOptionPane.showInputDialog(null,"Digite o código da disciplina",disciplina.getCodigoDisciplina());
				if(codigoDisciplina == null) return;
				
				String nomeDisciplina = JOptionPane.showInputDialog(null,"Digite o nome da disciplina",disciplina.getNomeDisciplina());
				if(nomeDisciplina == null) return;
				
				JComboBox<String> comboSemana = new JComboBox<>(opcoesSemana);
				comboSemana.setSelectedItem(disciplina.getDiaDaSemanaDisciplina());
				int diaDaSemanaResultado = JOptionPane.showConfirmDialog(null, comboSemana, "Selecione o dia da semana", JOptionPane.OK_CANCEL_OPTION);
				if (diaDaSemanaResultado != JOptionPane.OK_OPTION) return;
				
				/*
				 * KKKKKKKKKKKKKKKKKKKKKK
				 */
				int horasDisciplina;
				try {
					horasDisciplina = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite a carga horária da disciplina",disciplina.getQuantidadeHorasDisciplina()));
				}catch(NumberFormatException e) {
					throw new IllegalArgumentException("Digite apenas números no campo 'Quantidade de Horas'.");
				}
				
				String horarioDisciplinav;
				JFormattedTextField horarioDisciplina = new JFormattedTextField(new MaskFormatter("##:##/##:##"));
				int option = JOptionPane.showConfirmDialog(null,horarioDisciplina,"Digite o horário da disciplina",JOptionPane.OK_CANCEL_OPTION);
				horarioDisciplinav = horarioDisciplina.getText();
				if (option != JOptionPane.OK_OPTION) return;
					
				
				JComboBox<String> comboCurso = new JComboBox<>(cursosNome);
				comboCurso.setSelectedItem(disciplina.getCurso());
				int cursoNome = JOptionPane.showConfirmDialog(null, comboCurso, "Selecione o curso da disciplina", JOptionPane.OK_CANCEL_OPTION);
				if (cursoNome != JOptionPane.OK_OPTION) return;
				
				listaDeDisciplinas.remove(row);
				disciplinaRepository.remover(listaDeDisciplinas);

				disciplina.setCodigoDisciplina(codigoDisciplina);
				disciplina.setNomeDisciplina(nomeDisciplina);
				disciplina.setDiaDaSemanaDisciplina((String) comboSemana.getSelectedItem());
				disciplina.setQuantidadeHorasDisciplina(horasDisciplina);
				disciplina.setHorarioDisciplina(horarioDisciplinav);
				disciplina.setCurso((String) comboCurso.getSelectedItem());
				
				disciplinaRepository.salvar(disciplina);
				callback.atualizarTabela();
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
