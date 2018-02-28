package br.ufrpe.easyestacionamento.negocio.beans;

import java.io.Serializable;

public abstract class Pessoa implements Serializable {

	private static final long serialVersionUID = -6559411942790459699L;
	private String nome;
	private Long cpf;
	private String telefone;

	public Pessoa() {

	}

	public Pessoa(String nome, Long cpf, String telefone) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCpf() {
		return cpf;
	}

	public void Long(Long cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone + "]";
	}

}
