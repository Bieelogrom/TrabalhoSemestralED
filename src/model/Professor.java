package model;

public class Professor {
	private String cpf;
	private String nome;
	private String area;
	private float pontuacao;
	private String codigoProcesso;
	
	public Professor(String cpf, String nome, String area, float pontuacao) {
		this.cpf = cpf;
		this.nome = nome;
		this.area = area;
		this.pontuacao = pontuacao;
	}
	
	public Professor(String cpf, String nome, String area, float pontuacao, String codigoProcesso) {
		this.cpf = cpf;
		this.nome = nome;
		this.area = area;
		this.pontuacao = pontuacao;
		this.codigoProcesso = codigoProcesso;
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

	public String getCodigoProcesso() {
		return codigoProcesso;
	}

	public void setCodigoProcesso(String codigoProcesso) {
		this.codigoProcesso = codigoProcesso;
	}

	@Override
	public String toString() {
		return cpf+";"+nome+";"+area+";"+pontuacao;
	}
	
	
}
