package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import br.edu.fateczl.gabriel.Lista;
import model.Inscricao;
import model.Professor;

public class ConsultaController implements ActionListener {
	
	private InscricaoController inscricaoController;
	private ProfessorController professorController;
	private DefaultTableModel tableModel;
	
	public ConsultaController(DefaultTableModel tableModel) {
		this.inscricaoController = new InscricaoController();
		this.professorController = new ProfessorController();
		this.tableModel = tableModel;
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
		Lista<Inscricao> listaDeInscricoes = inscricaoController.listaInscricoes();
		Lista<Professor> listaDeProfessores = professorController.listarProfessor();
		
		Lista<Professor> professoresInscritos = new Lista<>();
		for(int i = 0; i < listaDeProfessores.size(); i++) {
			Professor prof = listaDeProfessores.get(i);
			boolean inscrito = false;
			
			for(int j = 0; j < listaDeInscricoes.size(); j++) {
				Inscricao ins = listaDeInscricoes.get(j);
				if(prof.getCpf().equals(ins.getCpfProfessor())) {
					prof.setCodigoProcesso(ins.getCodigoProcesso());
					professoresInscritos.addLast(new Professor(prof.getCpf(),prof.getNome(),prof.getArea(),prof.getPontuacao(),prof.getCodigoProcesso()));
					break;
				}
			}
	
		}
		
		tableModel.setRowCount(0);
		for(int i = 0; i < professoresInscritos.size(); i++) {
			Professor professorInscrito = professoresInscritos.get(i);
			tableModel.addRow(new Object[] {
					professorInscrito.getNome(),
					professorInscrito.getCpf(),
					professorInscrito.getArea(),
					professorInscrito.getPontuacao(),
					professorInscrito.getCodigoProcesso()
			});
		}
	}
	
}
