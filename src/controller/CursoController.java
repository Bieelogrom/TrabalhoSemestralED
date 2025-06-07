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
import Repository.DisciplinaRepository;
import br.edu.fateczl.Lista;
import model.Curso;
import model.Disciplina;
import view.PainelCursos;
import view.elementos.IPainelCursos;
import view.elementos.TableActionEvent;

/*
 * daqui em diante é basicamente copiar e colar as lógicas.
 */
public class CursoController implements ActionListener, TableActionEvent {
	
	//Obriga o usuário a digitar o código do curso dentro do padrão.
	private String patternCodigoCurso = "^[A-Za-z]{3}[0-9]{3}$"; 
	private JButton btnSalvarCurso;
	private JTextField txtfCodigoCurso;
	private JTextField txtfNomeCurso;
	private JComboBox<String> comboxareaconhecimento;
	private CursoRepository cursoRepository;
	private IPainelCursos callback;
	private DisciplinaController disciplinaController;
	
	public CursoController(JButton btnSalvarCurso, JTextField txtfCodigoCurso, JTextField txtfNomeCurso, JComboBox<String> comboxareaconhecimento, IPainelCursos callback) {
		this.btnSalvarCurso = btnSalvarCurso;
		this.txtfCodigoCurso = txtfCodigoCurso;
		this.txtfNomeCurso = txtfNomeCurso;
		this.comboxareaconhecimento = comboxareaconhecimento;
		this.cursoRepository = new CursoRepository();
		this.disciplinaController = new DisciplinaController();
		this.callback = callback;
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
			callback.limparTextos();
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
		int editar = JOptionPane.showConfirmDialog(null, "Deseja editar?");
		if(editar == JOptionPane.YES_OPTION) {
			try {
				Lista<Curso> listaDeCursos = listarCursos();
				Curso curso = listaDeCursos.get(row);
				
				String[] opcoes = {
						"Ciências Agrárias", "Ciências Biológicas", "Ciências da Saúde",
						"Ciências Exatas e da Terra", "Ciências Sociais Aplicadas",
						"Engenharias", "Ciências Humanas", "Linguística, Letras e Artes"
					};
				
				String codigoCurso = JOptionPane.showInputDialog(null, "Digite o código do curso", curso.getCodigoCurso());
				if (codigoCurso == null) return;
				
				String nomeCurso = JOptionPane.showInputDialog(null, "Digite o nome do curso", curso.getNomeCurso());
				if (nomeCurso == null) return;
				
				JComboBox<String> combo = new JComboBox<>(opcoes);
				combo.setSelectedItem(curso.getAreaConhecimento());
				int areaResult = JOptionPane.showConfirmDialog(null, combo, "Selecione a área do curso", JOptionPane.OK_CANCEL_OPTION);
				if (areaResult != JOptionPane.OK_OPTION) return;
				
				if (!codigoCurso.matches(patternCodigoCurso) || nomeCurso.isBlank()) {
					JOptionPane.showMessageDialog(null, "Dados inválidos. A edição foi cancelada.");
					return;
				}
				
				listaDeCursos.remove(row);
				cursoRepository.remover(listaDeCursos);
				
				curso.setCodigoCurso(codigoCurso);
				curso.setNomeCurso(nomeCurso);
				curso.setAreaConhecimento((String) combo.getSelectedItem());
				
				cursoRepository.salvar(curso);
				callback.atualizarTabela();
				JOptionPane.showMessageDialog(null, "Curso editado com sucesso!");
			}catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

	@Override
	public void onDelete(int row) {
		int deletar = JOptionPane.showConfirmDialog(null, "Deseja excluir?");
		if(deletar == JOptionPane.YES_OPTION) {
			try {
				Lista<Curso> listaDeCursos = listarCursos();
				Curso cursoASerApagado = listaDeCursos.get(row);
				verificaDisciplinasVinculadas(cursoASerApagado);
				listaDeCursos.remove(row);
				cursoRepository.remover(listaDeCursos);
				callback.atualizarTabela();
				JOptionPane.showMessageDialog(null, "Curso excluído com sucesso!");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Método somente para fazer remoção da disciplina vinculada ao curso apagado.
	 * Código extremamente mirabolante, mas provavelmente nem segue boas práticas.
	 */
	private void verificaDisciplinasVinculadas(Curso cursoASerApagado) throws Exception {
		Lista<Disciplina> listaDeDisciplinas = disciplinaController.listarDisciplinas();
		for(int i = 0; i < listaDeDisciplinas.size(); i++) {
			Disciplina disciplina = listaDeDisciplinas.get(i);
			if(cursoASerApagado.getNomeCurso().equals(disciplina.getCurso())) {
				listaDeDisciplinas.remove(i);
				i--;
			}
		}
		disciplinaController.getDisciplinaRepository().remover(listaDeDisciplinas);
	}
	
}
