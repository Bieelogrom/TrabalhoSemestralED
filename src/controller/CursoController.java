package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import model.Curso;

public class CursoController implements ActionListener {
	private JTextField txtnomecurso;
	private JTextField txtcodigocurso;
	private JComboBox<String> areaDoConhecimentoCurso;

	public CursoController(JTextField txtnomecurso, JTextField txtcodigocurso, JComboBox<String> areaDoConhecimentoCurso) {
		super();
		this.txtnomecurso = txtnomecurso;
		this.txtcodigocurso = txtcodigocurso;
		this.areaDoConhecimentoCurso = areaDoConhecimentoCurso;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("Adicionar curso")) {
			adicionarCurso();
		}
	}

	private void adicionarCurso() {
		// TODO Auto-generated method stub
		int codigoDoCurso = Integer.parseInt(txtcodigocurso.getText());
		String nomeDoCurso = txtnomecurso.getText();
		String areaDoConhecimento = (String) areaDoConhecimentoCurso.getSelectedItem();
		Curso novoCurso = new Curso(codigoDoCurso,nomeDoCurso,areaDoConhecimento);
		System.out.println(novoCurso.toString());
	}
	
	
}
