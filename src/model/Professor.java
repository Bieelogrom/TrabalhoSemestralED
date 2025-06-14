package model;

public class Professor {
	private String cpf;
	private String nome;
	private String area;
	private float pontuacao;
	
	public Professor(String cpf, String nome, String area, float pontuacao) {
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

	public void setPontuacao(float pontuacao) {
		this.pontuacao = pontuacao;
	}

	@Override
	public String toString() {
		return cpf+";"+nome+";"+area+";"+pontuacao;
	}
	
	
}
