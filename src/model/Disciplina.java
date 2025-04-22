package model;

import java.time.LocalTime;

public class Disciplina {
	private int codigoDisciplina;
	private String nomeDisciplina;
	private String diaDaSemanaDisciplina;
	private LocalTime horarioDisciplina;
	private int quantidadeHorasDisciplina;
	private Curso curso;
	
	public Disciplina(int codigoDisciplina, String nomeDisciplina, String diaDaSemanaDisciplina,
			LocalTime horarioDisciplina, int quantidadeHorasDisciplina, Curso curso) {
		this.codigoDisciplina = codigoDisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.diaDaSemanaDisciplina = diaDaSemanaDisciplina;
		this.horarioDisciplina = horarioDisciplina;
		this.quantidadeHorasDisciplina = quantidadeHorasDisciplina;
		this.curso = curso;
	}

	public int getCodigoDisciplina() {
		return codigoDisciplina;
	}

	public void setCodigoDisciplina(int codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public String getDiaDaSemanaDisciplina() {
		return diaDaSemanaDisciplina;
	}

	public void setDiaDaSemanaDisciplina(String diaDaSemanaDisciplina) {
		this.diaDaSemanaDisciplina = diaDaSemanaDisciplina;
	}

	public LocalTime getHorarioDisciplina() {
		return horarioDisciplina;
	}

	public void setHorarioDisciplina(LocalTime horarioDisciplina) {
		this.horarioDisciplina = horarioDisciplina;
	}

	public int getQuantidadeHorasDisciplina() {
		return quantidadeHorasDisciplina;
	}

	public void setQuantidadeHorasDisciplina(int quantidadeHorasDisciplina) {
		this.quantidadeHorasDisciplina = quantidadeHorasDisciplina;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	} 
	
	
}
