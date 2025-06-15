package model;

import java.util.Objects;

public class Disciplina {
	private String codigoDisciplina;
	private String nomeDisciplina;
	private String diaDaSemanaDisciplina;
	private String horarioDisciplina;
	private int quantidadeHorasDisciplina;
	private String curso;
	
	public Disciplina(String codigoDisciplina, String nomeDisciplina, String diaDaSemanaDisciplina,
			String horarioDisciplina, int quantidadeHorasDisciplina, String curso) {
		this.codigoDisciplina = codigoDisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.diaDaSemanaDisciplina = diaDaSemanaDisciplina;
		this.horarioDisciplina = horarioDisciplina;
		this.quantidadeHorasDisciplina = quantidadeHorasDisciplina;
		this.curso = curso;
	}

	public String getCodigoDisciplina() {
		return codigoDisciplina;
	}

	public void setCodigoDisciplina(String codigoDisciplina) {
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

	public String getHorarioDisciplina() {
		return horarioDisciplina;
	}

	public void setHorarioDisciplina(String horarioDisciplina) {
		this.horarioDisciplina = horarioDisciplina;
	}

	public int getQuantidadeHorasDisciplina() {
		return quantidadeHorasDisciplina;
	}

	public void setQuantidadeHorasDisciplina(int quantidadeHorasDisciplina) {
		this.quantidadeHorasDisciplina = quantidadeHorasDisciplina;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	

	public int hashCode(int tamanho) {
		int soma = 0;
		for(char c : codigoDisciplina.toCharArray()) {
			soma += c;
		}
		return soma % tamanho;
	}

	@Override
	public String toString() {
		return codigoDisciplina+";"+nomeDisciplina+";"+diaDaSemanaDisciplina+";"+horarioDisciplina
				+";"+quantidadeHorasDisciplina+";"+curso+";";
	} 
	
	
}
