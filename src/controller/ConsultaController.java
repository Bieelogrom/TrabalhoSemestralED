package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import br.edu.fateczl.fila.Fila;
import br.edu.fateczl.gabriel.Lista;
import model.Dicionario;
import model.Disciplina;
import model.Inscricao;
import model.MergeSort;
import model.Professor;

public class ConsultaController implements ActionListener {
	
	private InscricaoController inscricaoController;
	private ProfessorController professorController;
	private DefaultTableModel tableModel;
	private DisciplinaController disciplinaController;
	private JComboBox<String> comboBoxDisciplina;
	
	public ConsultaController(DefaultTableModel tableModel, JComboBox<String> comboBoxDisciplina) {
		this.inscricaoController = new InscricaoController();
		this.professorController = new ProfessorController();
		this.disciplinaController = new DisciplinaController();
		this.tableModel = tableModel;
		this.comboBoxDisciplina = comboBoxDisciplina;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("Consultar")) {
			try {
				consultarProcesso();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private void consultarProcesso() throws Exception {
		tableModel.setRowCount(0);
		Lista<Inscricao> listaDeInscricoes = inscricaoController.listaInscricoes();
		Lista<Professor> listaDeProfessores = professorController.listarProfessor();
		
		Lista<Professor> professoresInscritos = new Lista<>();
		for(int i = 0; i < listaDeProfessores.size(); i++) {
			Professor prof = listaDeProfessores.get(i);
			boolean inscrito = false;
			
			for (int j = 0; j < listaDeInscricoes.size(); j++) {
			    Inscricao ins = listaDeInscricoes.get(j);
			    if (prof.getCpf().equals(ins.getCpfProfessor()) && ins.getCodigoDisciplina().equals((String) comboBoxDisciplina.getSelectedItem())) {
			        boolean jaAdicionado = false;
			        for (int k = 0; k < professoresInscritos.size(); k++) {
			            if (professoresInscritos.get(k).getCpf().equals(prof.getCpf())) {
			                jaAdicionado = true;
			                break;
			            }
			        }
			        if (!jaAdicionado) {
			            professoresInscritos.addLast(new Professor(prof.getCpf(), prof.getNome(), prof.getArea(), prof.getPontuacao()));
			        }
			        break;
			    }
			}
	
		}
		
		
		int[] pontuacoes = new int[professoresInscritos.size()];
		for(int i = 0; i < professoresInscritos.size(); i++) {
			pontuacoes[i] = Math.round(professoresInscritos.get(i).getPontuacao());
		}
		
		MergeSort ms = new MergeSort();		
		pontuacoes = ms.ordenarArray(pontuacoes, 0, professoresInscritos.size() - 1);
		
		Lista<Professor> professoresOrdenados = new Lista<>();
		boolean[] usados = new boolean[professoresInscritos.size()];
		
		for (int i = professoresInscritos.size() - 1; i >= 0; i--) { 
			for (int j = 0; j < professoresInscritos.size(); j++) {
				if (!usados[j] && Math.round(professoresInscritos.get(j).getPontuacao()) == pontuacoes[i]) {
					professoresOrdenados.addLast(professoresInscritos.get(j));
					usados[j] = true;
					break;
				}
			}
		}
		
		tableModel.setRowCount(0);
		for(int i = 0; i < professoresOrdenados.size(); i++) {
			Professor professorInscrito = professoresOrdenados.get(i);
			tableModel.addRow(new Object[] {
					professorInscrito.getNome(),
					professorInscrito.getCpf(),
					professorInscrito.getArea(),
					professorInscrito.getPontuacao(),
			});
		}
	}
	
	//Estou cansado chefe
	public Dicionario carregarTabelaHash() throws Exception {
	    Fila<Inscricao> filaDeInscricoes = inscricaoController.enfileirarInscricoes();
	    Lista<String> codigosAtivos = new Lista<>();

	    while (!filaDeInscricoes.isEmpty()) {
	        Inscricao ins = filaDeInscricoes.remove();
	        String codDisc = ins.getCodigoDisciplina();
	        
	        boolean duplicado = false;
	        for (int i = 0; i < codigosAtivos.size(); i++) {
	            if (codigosAtivos.get(i).equals(codDisc)) {
	                duplicado = true;
	                break;
	            }
	        }
	        if (!duplicado) {
	            codigosAtivos.addLast(codDisc);
	        }
	    }

	    Fila<Disciplina> filaDisciplinas = disciplinaController.enfileirarDisciplinas();
	    Dicionario dicionario = new Dicionario(); // tamanho = 13 por padrão

	    while (!filaDisciplinas.isEmpty()) {
	        Disciplina d = filaDisciplinas.remove();
	        for (int i = 0; i < codigosAtivos.size(); i++) {
	            if (d.getCodigoDisciplina().equals(codigosAtivos.get(i))) {
	                dicionario.adicionarDisciplina(d);
	                break;
	            }
	        }
	    }

	    return dicionario;
	}

	
}
