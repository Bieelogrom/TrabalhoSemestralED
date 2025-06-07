package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Repository.CursoRepository;
import br.edu.fateczl.Lista;
import model.Curso;
import view.elementos.IPainelDisciplinas;
import view.elementos.TableActionEvent;

public class DisciplinaController implements ActionListener, TableActionEvent  {

	private CursoRepository cursoRepository;
	private IPainelDisciplinas callback;
	
	public DisciplinaController(IPainelDisciplinas callback) {
		this.cursoRepository = new CursoRepository();
		this.callback = callback;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public Lista<Curso> listarCursos() throws Exception {
		Lista<Curso> listaDeCursos = cursoRepository.visualizar();
		return listaDeCursos;
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
