package model;

public class Inscricao {
	private String cpfProfessor;
	private String codigoDisciplina;
	
	public Inscricao(String cpfProfessor, String codigoDisciplina) {
		super();
		this.cpfProfessor = cpfProfessor;
		this.codigoDisciplina = codigoDisciplina;
	}

	public String getCpfProfessor() {
		return cpfProfessor;
	}

	public void setCpfProfessor(String cpfProfessor) {
		this.cpfProfessor = cpfProfessor;
	}

	public String getCodigoDisciplina() {
		return codigoDisciplina;
	}

	public void setCodigoDisciplina(String codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}

	@Override
	public String toString() {
		return cpfProfessor+";"+codigoDisciplina;
	}
}
