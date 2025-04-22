package model;

public class Professor {
	private String cpf;
	private String nome;
	private String area;
	private Float pontuacao;
	
	public Professor(String cpf, String nome, String area, Float pontuacao) {
		this.cpf = cpf;
		this.nome = nome;
		this.area = area;
		this.pontuacao = pontuacao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Float getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Float pontuacao) {
		this.pontuacao = pontuacao;
	}
	
	
}
